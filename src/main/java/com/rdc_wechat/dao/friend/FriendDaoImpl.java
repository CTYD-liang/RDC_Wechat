package com.rdc_wechat.dao.friend;


import com.rdc_wechat.dao.BaseDao;
import com.rdc_wechat.pojo.Message;
import com.rdc_wechat.pojo.RequestMessage;
import com.rdc_wechat.pojo.User;
import com.rdc_wechat.service.friend.FriendServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 好友管理操作
 * @author 86178
 */
public class FriendDaoImpl implements FriendDao{

    /**
     * 根据用户id查询好友总数
     * @param connection
     * @param id
     * @param con 是不是黑名单
     * @return
     * @throws Exception
     */
    @Override
    public int getFriendCount(Connection connection, int id,int con) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        int count = 0;
        if (connection != null) {
            String sql = "SELECT COUNT(1) AS COUNT FROM user_friends  WHERE f_userid = ? AND ifBlack != ?";
            Object[] params = {id,con};
            resultSet = BaseDao.research(connection, pstm, null, sql, params);
            while (resultSet.next()){
               count = resultSet.getInt("count");
            }
        }
        BaseDao.release(connection,pstm,resultSet);
        return count;
    }

    /**
     * 查询好友的信息
     * @param connection
     * @param id
     * @param con 是不是黑名单
     * @param currentPageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public List<User> getFriendMessage(Connection connection, int id, int con,int currentPageNo, int pageSize) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<User>();
        List<Integer> list = new ArrayList<Integer>();
        currentPageNo = (currentPageNo-1)*pageSize;
        User u = null;
        int fId = 0;
        if (connection != null) {
            String sql = "SELECT * FROM user_friends WHERE f_userid = ? AND ifBlack != ? ORDER BY f_id  ASC LIMIT ?,?";
            Object[] params = {id,con,currentPageNo,pageSize};
            resultSet = BaseDao.research(connection, pstm, resultSet, sql, params);
            while (resultSet.next()){
                fId = resultSet.getInt("f_id");
                list.add(fId);
            }
            for (Integer i:list) {
                //在数据库中，分页显示 limit startIndex，pageSize；总数
                //当前页  (当前页-1)*页面大小
                //0,5	1,0	 01234
                //5,5	5,0	 56789
                //10,5	10,0 10~
                String sql1 = "SELECT * FROM mywechat_user WHERE id = ?";
                Object[] params1 = {i};
                ResultSet res = BaseDao.research(connection, pstm, null, sql1, params1);
                while (res.next()){
                    User user = new User();
                    user.setId(res.getInt("id"));
                    user.setUserCode(res.getString("userCode"));
                    user.setUserName(res.getString("userName"));
                    user.setAge(res.getString("age"));
                    user.setSex(res.getString("gender"));
                    user.setBirthday(res.getString("birthday"));
                    user.setAddress(res.getString("address"));
                    //查找用户昵称
                    FriendServiceImpl service = new FriendServiceImpl();
                    //判断用户昵称
                    u = service.getFriendName(res.getInt("id"), id);
                    if (u.getUserName()!=null){
                        user.setUserName(u.getUserName());
                    }
                    userList.add(user);
                }
            }
        }
        BaseDao.release(connection,pstm,resultSet);
        return userList;
    }

    /**
     * 获取好友昵称
     * @param connection
     * @param fid
     * @param uid
     * @return
     * @throws Exception
     */
    @Override
    public User getFriendName(Connection connection, int fid, int uid) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        User user = null;
        if(connection!=null){
            String sql = "select * from user_friends where f_id = ? and f_userid = ?";
            Object[] params = {fid,uid};
            resultSet = BaseDao.research(connection, pstm, resultSet, sql, params);
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("f_name"));
            }
            BaseDao.release(connection,pstm,resultSet);
        }
        return user;
    }

    /**
     * 设置好友昵称
     * @param connection
     * @param fid
     * @param uid
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public int setFriendName(Connection connection, int fid, int uid, String name) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        int flag = 0;
        if (connection != null) {
            String sql = "UPDATE user_friends SET f_name = ? where f_id = ? and f_userid = ?";
            Object[] params = {name,fid,uid};
            flag = BaseDao.update(connection, pstm, resultSet, sql, params);
        }
        BaseDao.release(connection,pstm,resultSet);
        return flag;
    }

    /**
     * 删除好友
     * @param connection
     * @param fid
     * @param uid
     * @return
     * @throws Exception
     */
    @Override
    public int delFriend(Connection connection, int fid, int uid) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        int flag = 0;
        if (connection != null) {
            String sql = "DELETE FROM user_friends WHERE f_id = ? AND f_userid = ?";
            Object[] params = {fid,uid};
            flag = BaseDao.update(connection, pstm, resultSet, sql, params);
        }
        BaseDao.release(connection,pstm,resultSet);
        return flag;
    }


    /**
     * 黑名单管理
     * @param connection
     * @param con 是不是黑名单
     * @param fid
     * @param uid
     * @return
     * @throws Exception
     */
    @Override
    public int manageBlackFriends(Connection connection,int con, int fid, int uid) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        int flag = 0;
        if (connection != null) {
            String sql = "UPDATE user_friends SET ifBlack = ? WHERE f_id = ? AND f_userid = ?";
            Object[] params = {con,fid,uid};
            flag = BaseDao.update(connection, pstm, resultSet, sql, params);
        }
        BaseDao.release(connection,pstm,resultSet);
        return flag;
    }

    /**
     * 模糊查询好友
     * @param connection
     * @param id
     * @param con 是不是黑名单
     * @param message
     * @param currentPageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public List<User> getFriendMessage(Connection connection, int id, int con,String message,int currentPageNo, int pageSize) throws Exception {
        PreparedStatement pstm = null;
        ResultSet res = null;
        List<User> userList = new ArrayList<User>();
        List<Object> list = new ArrayList<Object>();
        currentPageNo = (currentPageNo-1)*pageSize;
        User u = null;
        if (connection != null) {

            StringBuilder sql = new StringBuilder();
//            SELECT * FROM mywechat_user AS m
//            INNER JOIN user_friends AS u
//            ON u.f_id = m.id
//            WHERE u.f_userid = 1
//            AND u.ifBlack = 1
//            AND (m.userCode LIKE '%号%'
//            OR m.userName LIKE '%号%'
//            OR u.f_name LIKE '%号%')
//            ORDER BY m.id
//            ASC LIMIT 0,3

            sql.append("SELECT * FROM mywechat_user AS m\n");
            sql.append(" INNER JOIN user_friends AS u\n");
            sql.append("ON u.f_id = m.id\n");
            sql.append("WHERE u.f_userid = ?\n");
            list.add(id);
            sql.append("AND u.ifBlack != ?\n");
            list.add(con);
            sql.append("AND (m.userCode LIKE ?\n");
            list.add("%"+message+"%");
            sql.append("OR m.userName LIKE ?\n");
            list.add("%"+message+"%");
            sql.append("OR u.f_name LIKE ?)\n");
            list.add("%"+message+"%");
            sql.append("ORDER BY m.id\n");
            sql.append("ASC LIMIT ?,?\n");
            list.add(currentPageNo);
            list.add(pageSize);
            Object[] params = list.toArray();
            res = BaseDao.research(connection, pstm, res, sql.toString(), params);
            while (res.next()) {
                User user = new User();
                user.setId(res.getInt("id"));
                user.setUserCode(res.getString("userCode"));
                user.setUserName(res.getString("userName"));
                user.setAge(res.getString("age"));
                user.setSex(res.getString("gender"));
                user.setBirthday(res.getString("birthday"));
                user.setAddress(res.getString("address"));
                //查找用户昵称
                FriendServiceImpl service = new FriendServiceImpl();
                u = service.getFriendName(res.getInt("id"), id);
                if (u.getUserName() != null) {
                    user.setUserName(u.getUserName());
                }
                userList.add(user);
            }
        }
        BaseDao.release(connection,pstm,res);
        return userList;
    }

    /**
     * 指定查询朋友的总数
     * @param connection
     * @param id
     * @param con 是不是黑名单
     * @param message
     * @param currentPageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public int getFriendCount(Connection connection, int id, int con, String message, int currentPageNo, int pageSize) throws Exception {
        PreparedStatement pstm = null;
        ResultSet res = null;
        int count = 0;
        List<Object> list = new ArrayList<Object>();
        currentPageNo = (currentPageNo-1)*pageSize;
        if (connection != null) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT COUNT(1) AS COUNT FROM mywechat_user AS m\n");
            sql.append(" INNER JOIN user_friends AS u\n");
            sql.append("ON u.f_id = m.id\n");
            sql.append("WHERE u.f_userid = ?\n");
            list.add(id);
            sql.append("AND u.ifBlack != ?\n");
            list.add(con);
            sql.append("AND (m.userCode LIKE ?\n");
            list.add("%"+message+"%");
            sql.append("OR m.userName LIKE ?\n");
            list.add("%"+message+"%");
            sql.append("OR u.f_name LIKE ?)\n");
            list.add("%"+message+"%");
            sql.append("ORDER BY m.id\n");
            sql.append("ASC LIMIT ?,?\n");
            list.add(currentPageNo);
            list.add(pageSize);
            Object[] params = list.toArray();
            res = BaseDao.research(connection, pstm, res, sql.toString(), params);
            while (res.next()) {
                count = res.getInt("count");
            }
        }
        BaseDao.release(connection,pstm,res);
        return count;
    }

    /**
     * 查询不是好友用户的总数
     * @param connection
     * @param uid
     * @param message
     * @return
     */
    @Override
    public int getNotFriendsCount(Connection connection, int uid, String message) throws Exception {
         /*
          首先用模糊查询查出所有的用户
          不应该查出自己
          然后查出来的id跟后面的好友表对比
         */
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<User>();
        List<Object> str = new ArrayList<Object>();
        User fu = null;
        int count = 0;
        if (connection != null) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM mywechat_user WHERE (userCode LIKE ? OR userName LIKE ? ) AND id<>?");
            str.add("%"+message+"%");
            str.add("%"+message+"%");
            str.add(uid);
            Object[] params = str.toArray();
            resultSet = BaseDao.research(connection, pstm, resultSet, sql.toString(), params);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setUserName(resultSet.getString("userName"));
                user.setAge(resultSet.getString("age"));
                user.setSex(resultSet.getString("gender"));
                user.setBirthday(resultSet.getString("birthday"));
                user.setAddress(resultSet.getString("address"));
                userList.add(user);
            }
            for (User u:userList) {
                FriendServiceImpl friendService = new FriendServiceImpl();
                fu = friendService.getFriendName(u.getId(), uid);
                if (fu==null){
                    count ++;
                }
            }
        }
        BaseDao.release(connection,pstm,resultSet);
        return count;
    }

    /**
     * 查询不是好友的用户
     * @param connection
     * @param uid
     * @param currentPageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public List<User> getUser(Connection connection,int uid, String message,int currentPageNo, int pageSize) throws Exception {
        /*
          首先用模糊查询查出所有的用户
          不应该查出自己
          然后查出来的id跟后面的好友表对比
         */
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<User>();
        List<User> list = new ArrayList<User>();
        List<Object> str = new ArrayList<Object>();
        User user1 = null;
        currentPageNo = (currentPageNo-1)*pageSize;
        if (connection != null) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM mywechat_user WHERE (userCode LIKE ? OR userName LIKE ? ) AND id<>? ORDER BY id ASC LIMIT ?,?");
            str.add("%"+message+"%");
            str.add("%"+message+"%");
            str.add(uid);
            str.add(currentPageNo);
            str.add(pageSize);
            Object[] params = str.toArray();
            resultSet = BaseDao.research(connection, pstm, resultSet, sql.toString(), params);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setUserName(resultSet.getString("userName"));
                user.setAge(resultSet.getString("age"));
                user.setSex(resultSet.getString("gender"));
                user.setBirthday(resultSet.getString("birthday"));
                user.setAddress(resultSet.getString("address"));
                userList.add(user);
            }
            for (User u:userList) {
                FriendServiceImpl friendService = new FriendServiceImpl();
                user1 = friendService.getFriendName(u.getId(), uid);
                if (user1==null){
                    list.add(u);
                }
            }
        }
        BaseDao.release(connection,pstm,resultSet);
        return list;
    }

    /**
     * 将请求信息存进数据库
     * @param connection
     * @param message
     * @param tid
     * @param fid
     * @return
     * @throws Exception
     */
    @Override
    public int requestMessageIn(Connection connection, String message, int tid, int fid,int icon,int ifRead) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        int flag = 0;
        if (connection != null) {
            String sql = "INSERT INTO friends_request(uid,sid,`text`,icon,ifRead) VALUES (?,?,?,?,?)";
            Object[] params = {fid,tid,message,icon,ifRead};
            flag = BaseDao.update(connection, pstm, resultSet, sql, params);
        }
        BaseDao.release(connection,pstm,resultSet);
        return flag;
    }

    /**
     * 将验证信息取出来
     * @param connection
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public List<Message> requestMessageOut(Connection connection, int id) throws Exception {
        PreparedStatement pstm = null;
        ResultSet res = null;
        List<RequestMessage> list = new ArrayList<RequestMessage>();
        List<Message> messageList = new ArrayList<Message>();
        if (connection != null) {
            String sql = "SELECT * FROM friends_request WHERE sid = ? and ifRead = 0";
            Object[] params = {id};
            res = BaseDao.research(connection, pstm, res, sql, params);
            while (res.next()) {
                RequestMessage requestMessage = new RequestMessage();
                //消息来自
                requestMessage.setFromId(res.getInt("uid"));
                //消息内容
                requestMessage.setText(res.getString("text"));
                //是否是验证消息
                requestMessage.setIcon(res.getInt("icon"));
                list.add(requestMessage);
            }
            for (RequestMessage req:list) {
                FriendServiceImpl friendService = new FriendServiceImpl();
                String name = friendService.getName(req.getFromId());
                Message message = new Message();
                //消息来自
                message.setFromName(name);
                //消息内容
                message.setMessage(req.getText());
                //消息是否是验证消息
                message.setIcon(req.getIcon());
                messageList.add(message);
            }
        }
        BaseDao.release(connection,pstm,res);
        return messageList;
    }

    /**
     * 用id获取用户信息
     * @param connection
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public String getName(Connection connection, int id) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        String name = null;
        if(connection!=null){
            String sql = "select userName from mywechat_user where id = ?";
            Object[] params = {id};
            resultSet = BaseDao.research(connection, pstm, resultSet, sql, params);
            if(resultSet.next()){
                name = resultSet.getString("userName");
            }
            BaseDao.release(connection,pstm,resultSet);
        }
        return name;
    }

    /**
     * 由用户名查询用户id
     * @param connection
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public int getId(Connection connection, String name) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        int userid = 0;
        if(connection!=null){
            String sql = "select id from mywechat_user where userName = ?";
            Object[] params = {name};
            resultSet = BaseDao.research(connection, pstm, resultSet, sql, params);
            if(resultSet.next()){
                userid = resultSet.getInt("id");
            }
            BaseDao.release(connection,pstm,resultSet);
        }
        return userid;
    }

    /**
     * 设置消息已读
     * @param connection
     * @param id
     * @param message
     * @return
     * @throws Exception
     */
    @Override
    public int setIsRead(Connection connection, int id, String message) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        int flag = 0;
        if(connection!=null){
            String sql = "UPDATE friends_request SET ifRead = 1 WHERE sid = ? AND `text` = ?";
            Object[] params = {id,message};
            flag = BaseDao.update(connection, pstm, resultSet, sql, params);
            BaseDao.release(connection,pstm,resultSet);
        }
        return flag;
    }

    /**
     * 同意添加好友
     * @param connection
     * @param uid
     * @param fid
     * @return
     * @throws Exception
     */
    @Override
    public int addFriends(Connection connection, int uid, int fid) throws Exception {
        PreparedStatement pstm = null;
        ResultSet res = null;
        int flag = 0;
        int update = 0;
        int update1 = 0;
        try {
            //关闭数据库自动提交，自动开启事务
            connection.setAutoCommit(false);
            //执行事务
            String sql1 = "INSERT INTO user_friends(f_id,f_userid) VALUES (?,?)";
            Object[] params = {uid,fid};
            update = BaseDao.update(connection, pstm, res, sql1, params);
            String sql2 = "INSERT INTO user_friends(f_id,f_userid) VALUES (?,?)";
            Object[] params1 = {fid,uid};
            update1 = BaseDao.update(connection, pstm, res, sql2, params1);
            //业务完毕，提交事务
            connection.commit();
           if(update+update1==2){
               flag = 1;
           }
        } catch (SQLException throwables) {
            try {
                connection.rollback();//如果失败就回滚
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }finally{
            BaseDao.release(connection,pstm,res);
        }
        return flag;
    }

    /**
     * 根据id判断是不是用户好友并且不是黑名单好友
     * @param connection
     * @param id
     * @param con
     * @return
     * @throws Exception
     */
    @Override
    public List<User> seeFriend(Connection connection, int id, int con) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<User>();
        List<Integer> list = new ArrayList<Integer>();
        User u = null;
        int fId = 0;
        if (connection != null) {
            String sql = "SELECT * FROM user_friends WHERE f_userid = ? AND ifBlack != ?";
            Object[] params = {id,con};
            resultSet = BaseDao.research(connection, pstm, resultSet, sql, params);
            while (resultSet.next()){
                fId = resultSet.getInt("f_id");
                list.add(fId);
            }
            for (Integer i:list) {
                String sql1 = "SELECT * FROM mywechat_user WHERE id = ?";
                Object[] params1 = {i};
                ResultSet res = BaseDao.research(connection, pstm, null, sql1, params1);
                while (res.next()){
                    User user = new User();
                    user.setId(res.getInt("id"));
                    user.setUserCode(res.getString("userCode"));
                    user.setUserName(res.getString("userName"));
                    user.setAge(res.getString("age"));
                    user.setSex(res.getString("gender"));
                    user.setBirthday(res.getString("birthday"));
                    user.setAddress(res.getString("address"));
                    //查找用户昵称
                    FriendServiceImpl service = new FriendServiceImpl();
                    //判断用户昵称
                    u = service.getFriendName(res.getInt("id"), id);
                    if (u.getUserName()!=null){
                        user.setUserName(u.getUserName());
                    }
                    userList.add(user);
                }
            }
        }
        BaseDao.release(connection,pstm,resultSet);
        return userList;
    }
}
