package com.rdc_wechat.service.friend;


import com.rdc_wechat.pojo.Message;
import com.rdc_wechat.pojo.User;

import java.sql.Connection;
import java.util.List;

/**
 * @author 86178
 */
public interface FriendService {


    /**
     * 根据id查询好友总数
     * @param id
     * @param con
     * @return
     */
    public int getFriendCount(int id,int con);

    /**
     * 查询好友的信息
     * @param id
     * @param con
     * @param currentPageNo
     * @param pageSize
     * @return
     */
    public List<User> getFriendMessage(int id, int con, int currentPageNo, int pageSize);

    /**
     * 设置好友昵称
     * @param fid
     * @param uid
     * @param name
     * @return
     */
    public int setFriendName(int fid,int uid,String name);

    /**
     * 获得好友昵称
     * @param fid
     * @param uid
     * @return
     */
    public User getFriendName(int fid,int uid);

    /**
     * 删除好友
     * @param fid
     * @param uid
     * @return
     */
    public int delFriend(int fid,int uid);

    /**
     * 黑名单管理
     * @param con
     * @param fid
     * @param uid
     * @return
     */
    public int manageBlackFriends(int con,int fid,int uid);

    /**
     *查询好友
     * @param id
     * @param con
     * @param message
     * @param currentPageNo
     * @param pageSize
     * @return
     */
    public List<User> getFriendMessage(int id, int con,String message,int currentPageNo,int pageSize);

    /**
     * 获取查询好友总数
     * @param id
     * @param con
     * @param message
     * @param currentPageNo
     * @param pageSize
     * @return
     */
    public int  getFriendCount(int id, int con,String message,int currentPageNo,int pageSize);

    /**
     * 查询不是好友用户的信息
     * @param uid
     * @param message
     * @param currentPageNo
     * @param pageSize
     * @return
     */
    public List<User> getUser(String message,int uid,int currentPageNo, int pageSize);

    /**
     * 查找不是好有用户的总数
     * @param uid
     * @param message
     * @return
     */
    public int getNotFriendsCount(int uid, String message);

    /**
     * 将请求信息存进数据库
     * @param message
     * @param tid
     * @param fid
     * @return
     */
    public int requestMessageIn(String message ,int tid,int fid,int icon,int ifRead);

    /**
     * 查询用户姓名
     * @param id
     * @return
     */
    public String getName(int id);

    /**
     * 由用户名查询用户id
     * @param name
     * @return
     * @throws Exception
     */
    public int getId(String name);

    /**
     * 取出所有的验证信息
     * @param id
     * @return
     */
    public List<Message> requestMessageOut(int id);

    /**
     * 设置消息已读
     * @param id
     * @param message
     * @return
     * @throws Exception
     */
    public int setIsRead(int id,String message);

    /**
     * 同意好友请求
     * 两方面都要添加
     * 可以开启事务
     * @param uid
     * @param fid
     * @return
     * @throws Exception
     */
    public int addFriends(int uid,int fid);

    /**
     *根据id判断是不是好友且不是黑名单的
     * @param id
     * @param con
     * @return
     */
    public List<User>  seeFriend(int id,int con);

}
