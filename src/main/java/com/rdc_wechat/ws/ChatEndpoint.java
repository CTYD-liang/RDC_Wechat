package com.rdc_wechat.ws;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rdc_wechat.pojo.Message;
import com.rdc_wechat.pojo.User;
import com.rdc_wechat.service.friend.FriendServiceImpl;
import com.rdc_wechat.util.Constants;
import com.rdc_wechat.util.MessageUtils;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 总通信管道
 * @author 86178
 */
@ServerEndpoint(value = "/chat",configurator = GetHttpSessionConfig.class)
public class ChatEndpoint {

    /*
       刚开始：
       每一个点进这个用页面的用户，都会调用这个方法
       然后将自己的名字作为键，以对象作为值存到一个map集合中
       当要发消息的时候，是给所有的对象都发一遍消息，消息的格式是json
       发送完毕后再在前端进行判断是不是
     */

    /*
      我现在要做的事情：
      1.你登录的时候，只有朋友才能看到你进入聊天室（后台实现）
      2.在好友那里按照昵称来显示用户的姓名(可以交给前端实现)
     */

    /*判断是不是好友
      1.获取用户名称然后再数据库中查询是出id
      2.获取到用户的id再结合用户的id一起到前端进行验证
      3.如果是则返或，不是则不做要求
    */

    /**
     *  用来存储每个用户客户端对象的ChatEndpoint对象
     */
    private static Map<String, ChatEndpoint> onlineUsers = new ConcurrentHashMap<>();
    /**
     * 声明session对象，通过对象可以发送消息给指定的用户
     */
    private Session session;
    /**
     *声明HttpSession对象，我们之前在HttpSession对象中存储了用户名
     */
    private HttpSession httpSession;


    /**
     * 连接建立时被调用
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        //将局部session对象赋值给成员session
        this.session = session;

        //获取httpSession对象
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;

        //从httpSession中获取用户名
        String userName = (String) httpSession.getAttribute("userName");

        FriendServiceImpl friendService = new FriendServiceImpl();
        int id = friendService.getId(userName);
        //将用户id转换为字符串类型
        String userId =String.valueOf(id);
        //将所有的对象都存在这个容器中
        onlineUsers.put(userId,this);

        //将当前在线好友的好友推送给所有的客户端
        //1.获取消息
        String message = MessageUtils.getMessage(true, null,getNames());
        //2.调用方法进行系统消息的推送
        broadAllUsers(message);
    }

    /**
     * 进行消息的推送
     * @param message
     */
    private void broadAllUsers(String message){
        //要将该消息推送给所有的客户端
        Set<String> names = onlineUsers.keySet();
        for (String name:names) {
            ChatEndpoint chatEndpoint = onlineUsers.get(name);
            try {
                chatEndpoint.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取用户名
     * @return
     */
    private Set<String> getNames(){
        //获取map集合中所有的键，也就是所有进入到这个聊天界面的用户名
        return onlineUsers.keySet();
    }

    /**
     * 接收到客户端信息时候被调用
     */
    @OnMessage
    public void onMessage(String message,Session session){
        try {
            //将message转换为message对象
            ObjectMapper mapper = new ObjectMapper();
            Message mess = mapper.readValue(message, Message.class);
            //获取要将数据发送给的用户id
            String toName = mess.getToName();
            System.out.println("好友："+ toName);
            //获取消息数据
            String data = mess.getMessage();
            System.out.println("信息："+ data);
            //获取当前登录的用户
            User user = (User) httpSession.getAttribute(Constants.USER_SESSION);
            int userId = user.getId();
            String uid = String.valueOf(userId);
            //获取推送给指定用户消息的消息格式数据
            String resultMessage = MessageUtils.getMessage(false, uid, data);
            //发送数据
            if(toName!=null){
                ChatEndpoint chatEndpoint = onlineUsers.get(toName);
                try {
                    chatEndpoint.session.getBasicRemote().sendText(resultMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接关闭时被调用
     */
    @OnClose
    public void onClose(Session session){
        //获取用户名
        String userName = (String) httpSession.getAttribute("userName");
        FriendServiceImpl friendService = new FriendServiceImpl();
        int id = friendService.getId(userName);
        //将用户id转换为字符串类型
        String userId =String.valueOf(id);
        //从容器中删除指定的用户
        onlineUsers.remove(userId);
        //获取推送的消息
        String message = MessageUtils.getMessage(true, null, getNames());
        //将消息广播给所有用户
        broadAllUsers(message);
    }
}
