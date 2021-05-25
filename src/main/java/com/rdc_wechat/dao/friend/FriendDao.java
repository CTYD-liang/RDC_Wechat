package com.rdc_wechat.dao.friend;

import com.rdc_wechat.pojo.Message;
import com.rdc_wechat.pojo.User;

import java.sql.Connection;
import java.util.List;

/**
 * 好友
 * @author 86178
 */
public interface FriendDao {


    /**
     * 用户的好友总数
     * @param connection
     * @param id 指定用户
     * @param con 是否是黑名单
     * @return
     * @throws Exception
     */
    public int getFriendCount(Connection connection,int id,int con) throws Exception;

    /**
     * 查询好友的信息
     * @param connection
     * @param id
     * @param con 是否是黑名单
     * @param currentPageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<User> getFriendMessage(Connection connection, int id,int con,int currentPageNo, int pageSize) throws Exception;


    /**
     * 获取好友昵称
     * @param connection
     * @param fid
     * @param uid
     * @return
     * @throws Exception
     */
    public User getFriendName(Connection connection,int fid,int uid) throws Exception;


    /**
     * 设置好友昵称
     * @param connection
     * @param fid
     * @param uid
     * @param name
     * @return
     * @throws Exception
     */
    public int setFriendName(Connection connection,int fid,int uid,String name) throws Exception;

    /**
     * 删除好友
     * @param connection
     * @param fid
     * @param uid
     * @return
     * @throws Exception
     */
    public int delFriend(Connection connection,int fid,int uid) throws Exception;


    /**
     * 黑名单管理
     * @param connection
     * @param con 是否是黑名单
     * @param fid
     * @param uid
     * @return
     * @throws Exception
     */
    public int manageBlackFriends(Connection connection,int con,int fid,int uid) throws Exception;

    /**
     * 根据用户微信号或者名称
     * @param connection
     * @param id
     * @param con 是否是黑名单
     * @param message
     * @param currentPageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<User> getFriendMessage(Connection connection,int id, int con,String message,int currentPageNo,int pageSize) throws Exception;


    /**
     * 获取查询的总数
     * @param connection
     * @param id
     * @param con 是否是黑名单
     * @param message
     * @param currentPageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public int  getFriendCount(Connection connection,int id, int con, String message,int currentPageNo,int pageSize) throws Exception;


    /**
     * 获得不是好友用户的总数
     * @param connection
     * @param uid
     * @param message
     * @return
     * @throws Exception
     */
    public int getNotFriendsCount(Connection connection, int uid, String message) throws Exception;


    /**
     * 查询不是好友的用户
     * @param connection
     * @param uid
     * @param currentPageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<User> getUser(Connection connection,int uid,String message,int currentPageNo, int pageSize) throws Exception;


    /**
     * 将请求信息存进数据库
     * @param connection
     * @param message
     * @param tid
     * @param fid
     * @return
     * @throws Exception
     */
    public int requestMessageIn(Connection connection,String message ,int tid,int fid,int icon,int ifRead) throws Exception;

    /**
     * 查询表中的验证信息
     * @param connection
     * @param id
     * @return
     * @throws Exception
     */
    public List<Message> requestMessageOut(Connection connection, int id) throws Exception;

    /**
     * 由id查询用户信息
     * @param connection
     * @param id
     * @return
     * @throws Exception
     */
    public String getName(Connection connection,int id) throws Exception;

    /**
     * 由用户名查询用户id
     * @param connection
     * @param name
     * @return
     * @throws Exception
     */
    public int getId(Connection connection,String name) throws Exception;

    /**
     * 设置消息已读
     * @param connection
     * @param id
     * @param message
     * @return
     * @throws Exception
     */
    public int setIsRead(Connection connection,int id,String message) throws Exception;

    /**
     * 同意好友请求
     * 两方面都要添加
     * 可以开启事务
     * @param connection
     * @param uid
     * @param fid
     * @return
     * @throws Exception
     */
    public int addFriends(Connection connection,int uid,int fid) throws Exception;

    /**
     * 根据id查找好友
     * @param connection
     * @param id
     * @param con
     * @return
     * @throws Exception
     */
    public List<User> seeFriend(Connection connection,int id,int con) throws Exception;
}
