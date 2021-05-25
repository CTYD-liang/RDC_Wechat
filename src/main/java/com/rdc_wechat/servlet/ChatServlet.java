package com.rdc_wechat.servlet;

import com.alibaba.fastjson.JSONArray;
import com.rdc_wechat.pojo.User;
import com.rdc_wechat.service.friend.FriendService;
import com.rdc_wechat.service.friend.FriendServiceImpl;
import com.rdc_wechat.util.Constants;
import com.rdc_wechat.ws.ChatEndpoint;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 86178
 */
@WebServlet(name="ChatServlet", urlPatterns="/chatTogether")
public class ChatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入方法2");
        resp.setContentType("application/json");
        //post方法如果不设置,则会出现乱码
        resp.setCharacterEncoding("UTF-8");
        Map<String, String> resultMap = new HashMap<>();
        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        //获取自己的id
        int userId = user.getId();
        //从好友表中获取不是黑名单的好友的id和昵称或者用户名
        FriendService friendService = new FriendServiceImpl();
        //不是黑名单-->!=0
        List<User> userList = friendService.seeFriend(userId, Constants.NUMBER_ZERO);
        for (User u:userList) {
            String fid = String.valueOf(u.getId());
            System.out.println(fid);
            System.out.println(u.getUserName());
            //将所有的用户的id转成字符
            resultMap.put(fid,u.getUserName());
        }
        //写返回的数据
        PrintWriter pw = resp.getWriter();
        //如果昵称不为空则输出昵称
        //否则输出账号
        //写成json格式返回
        System.out.println(JSONArray.toJSONString(resultMap));
        pw.print(JSONArray.toJSONString(resultMap));
        pw.flush();
        pw.close();
    }
}
