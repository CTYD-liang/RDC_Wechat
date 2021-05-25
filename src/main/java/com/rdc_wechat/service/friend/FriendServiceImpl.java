package com.rdc_wechat.service.friend;

import com.rdc_wechat.dao.BaseDao;
import com.rdc_wechat.dao.friend.FriendDao;
import com.rdc_wechat.dao.friend.FriendDaoImpl;
import com.rdc_wechat.pojo.Message;
import com.rdc_wechat.pojo.User;

import javax.servlet.jsp.jstl.sql.Result;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 86178
 */
public class FriendServiceImpl implements FriendService {


    /**
     * 业务层调用Dao层
     */
    private FriendDao friendDao;

    public FriendServiceImpl() {
        friendDao = new FriendDaoImpl();
    }

    /**
     * 查询好友总数
     * @param id
     * @return
     */
    @Override
    public int getFriendCount(int id,int con) {
        Connection connection = null;
        int count = 0;
        Result res = null;
        try {
            connection = BaseDao.getConnection();
            count = friendDao.getFriendCount(connection,id,con);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.release(connection,null,null);
        }
        return count;
    }

    /**
     * 查询好友信息
     * @param id
     * @param con
     * @param currentPageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<User> getFriendMessage(int id, int con,int currentPageNo, int pageSize) {
        Connection connection = null;
        List<User> userList = new ArrayList<User>();
        Result res = null;
        try {
            connection = BaseDao.getConnection();
            userList = friendDao.getFriendMessage(connection,id,con,currentPageNo,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.release(connection,null,null);
        }
        return userList;
    }

    /**
     * 设置好友昵称
     * @param fid
     * @param uid
     * @param name
     * @return
     */
    @Override
    public int setFriendName(int fid, int uid, String name) {
        Connection connection = null;
        int flag = 0;
        try {
            connection = BaseDao.getConnection();
            flag = friendDao.setFriendName(connection, fid, uid, name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.release(connection, null, null);
        }
        return flag;
    }

    /**
     * 获得昵称
     * @param fid
     * @param uid
     * @return
     */
    @Override
    public User getFriendName(int fid, int uid) {
        Connection connection = null;
        User user = null;
        try {
            connection = BaseDao.getConnection();
            user = friendDao.getFriendName(connection, fid, uid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.release(connection, null, null);
        }
        return user;
    }

    /**
     * 删除好友
     * @param fid
     * @param uid
     * @return
     */
    @Override
    public int delFriend(int fid, int uid) {
        Connection connection = null;
        int flag = 0;
        try {
            connection = BaseDao.getConnection();
            flag = friendDao.delFriend(connection, fid, uid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.release(connection, null, null);
        }
        return flag;
    }

    /**
     * 黑名单管理
     * @param con
     * @param fid
     * @param uid
     * @return
     */
    @Override
    public int manageBlackFriends(int con, int fid, int uid) {
        Connection connection = null;
        int flag = 0;
        try {
            connection = BaseDao.getConnection();
            flag = friendDao.manageBlackFriends(connection, con,fid,uid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.release(connection, null, null);
        }
        return flag;
    }

    /**
     * 模糊查询好友
     * @param id
     * @param con
     * @param message
     * @param currentPageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<User> getFriendMessage(int id, int con,String message, int currentPageNo, int pageSize) {
        Connection connection = null;
        List<User> userList = new ArrayList<User>();
        Result res = null;
        try {
            connection = BaseDao.getConnection();
            userList = friendDao.getFriendMessage(connection,id,con,message,currentPageNo,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.release(connection,null,null);
        }
        return userList;
    }

    /**
     * 获取好友总数
     * @param id
     * @param con
     * @param message
     * @param currentPageNo
     * @param pageSize
     * @return
     */
    @Override
    public int getFriendCount(int id,int con, String message, int currentPageNo, int pageSize) {
        Connection connection = null;
        int count = 0;
        Result res = null;
        try {
            connection = BaseDao.getConnection();
            count = friendDao.getFriendCount(connection, id,con,message,currentPageNo,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.release(connection,null,null);
        }
        return count;
    }

    /**
     * 查询不是好友的用户信息
     * @param uid
     * @param message
     * @param currentPageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<User> getUser(String message,int uid, int currentPageNo, int pageSize) {
        Connection connection = null;
        List<User> userList = new ArrayList<User>();
        Result res = null;
        try {
            connection = BaseDao.getConnection();
            userList = friendDao.getUser(connection,uid,message,currentPageNo,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.release(connection,null,null);
        }
        return userList;
    }

    /**
     * 查询不是好友用户的总数
     * @param uid
     * @param message
     * @return
     */
    @Override
    public int getNotFriendsCount(int uid, String message) {
        Connection connection = null;
        int flag = 0;
        try {
            connection = BaseDao.getConnection();
            flag = friendDao.getNotFriendsCount(connection,uid,message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.release(connection, null, null);
        }
        return flag;
    }

    /**
     * 将请求信息存进数据库
     * @param message
     * @param tid
     * @param fid
     * @return
     */
    @Override
    public int requestMessageIn(String message, int tid, int fid,int icon,int ifRead) {
        Connection connection = null;
        int flag = 0;
        try {
            connection = BaseDao.getConnection();
            flag = friendDao.requestMessageIn(connection,message,tid,fid,icon,ifRead);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.release(connection, null, null);
        }
        return flag;
    }

    /**
     * 查询用户姓名
     * @param id
     * @return
     */
    @Override
    public String getName(int id) {
        Connection connection = null;
        String name = null;
        try {
            connection = BaseDao.getConnection();
            name = friendDao.getName(connection,id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.release(connection, null, null);
        }
        return name;
    }

    /**
     * 查用户id
     * @param name
     * @return
     */
    @Override
    public int getId(String name) {
        Connection connection = null;
        int userid = 0;
        try {
            connection = BaseDao.getConnection();
            userid = friendDao.getId(connection,name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.release(connection, null, null);
        }
        return userid;
    }

    /**
     * 取出验证信息
     * @param id
     * @return
     */
    @Override
    public List<Message> requestMessageOut(int id) {
        Connection connection = null;
        List<Message> list = new ArrayList<Message>();
        Result res = null;
        try {
            connection = BaseDao.getConnection();
            list = friendDao.requestMessageOut(connection,id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.release(connection,null,null);
        }
        return list;
    }

    /**
     * 设置消息已读
     * @param id
     * @param message
     * @return
     */
    @Override
    public int setIsRead(int id, String message) {
        Connection connection = null;
        int flag = 0;
        try {
            connection = BaseDao.getConnection();
            flag = friendDao.setIsRead(connection,id,message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.release(connection, null, null);
        }
        return flag;
    }

    /**
     * 添加好友请求
     * @param uid
     * @param fid
     * @return
     */
    @Override
    public int addFriends(int uid, int fid) {
        Connection connection = null;
        int flag = 0;
        try {
            connection = BaseDao.getConnection();
            flag = friendDao.addFriends(connection,uid,fid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.release(connection, null, null);
        }
        return flag;
    }

    /**
     * 根据用户昵称或名字判断是不是好友且不是黑名单的
     * @param id
     * @param con
     * @return
     */
    @Override
    public List<User> seeFriend(int id, int con) {
        Connection connection = null;
        List<User> list = null;
        try {
            connection = BaseDao.getConnection();
            list = friendDao.seeFriend(connection,id,con);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.release(connection, null, null);
        }
        return list;
    }
}
