package com.rdc_wechat.dao.user;

import com.rdc_wechat.dao.BaseDao;
import com.rdc_wechat.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author 86178
 */
public class UserDaoImpl implements UserDao {

    /**
     * 查询登录用户
     * @param connection
     * @param userCode
     * @param password
     * @return
     * @throws Exception
     */
    @Override
    public User getLoginUser(Connection connection, String userCode, String password) throws Exception {

        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        User user = null;

        if(connection!=null){
            String sql = "select * from mywechat_user where userCode = ? and userPassword = ?";
            Object[] params = {userCode,password};

            resultSet = BaseDao.research(connection, pstm, null, sql, params);

            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                //用户账号
                user.setUserCode(resultSet.getString("userCode"));
                //用户昵称
                user.setUserName(resultSet.getString("userName"));
                //用户密码
                user.setUserPassword(resultSet.getString("userPassword"));
                //用户年龄
                user.setAge(resultSet.getString("age"));
                //用户性别
                user.setSex(resultSet.getString("gender"));
                //用户生日
                user.setBirthday(resultSet.getString("birthday"));
                //用户邮箱
                user.setEmail(resultSet.getString("userEmail"));
                //用户地址
                user.setAddress(resultSet.getString("address"));
                //用户角色
                user.setUserRole(resultSet.getInt("userRole"));
            }
            BaseDao.release(connection,pstm,resultSet);
        }
        return user;
    }

    /**
     * 判断是否有已经注册用户
     * @param connection
     * @param email
     * @return
     * @throws Exception
     */
    @Override
    public int getLoginUser(Connection connection, String email) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        int flag = 0;
        if(connection!=null){
            String sql = "select * from mywechat_user where userEmail = ?";
            Object[] params = {email};

            resultSet = BaseDao.research(connection, pstm, resultSet, sql, params);

            if(resultSet.next()){
               flag = 1;
            }
            BaseDao.release(connection,pstm,resultSet);
        }
        return flag;
    }

    /**
     *
     * @param connection
     * @param userCode
     * @return
     * @throws Exception
     */
    @Override
    public int getLoginUserByCode(Connection connection, String userCode) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        int flag = 0;
        if(connection!=null){
            String sql = "select * from mywechat_user where userCode = ?";
            Object[] params = {userCode};

            resultSet = BaseDao.research(connection, pstm, resultSet, sql, params);

            if(resultSet.next()){
                flag = 1;
            }
            BaseDao.release(connection,pstm,resultSet);
        }
        return flag;
    }

    /**
     * 注册用户
     * @param connection
     * @param userCode
     * @param password
     * @throws Exception
     */
    @Override
    public int insertRegisterUser(Connection connection, String userCode, String password, String email,int id) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        int flag = 0;

        if (connection != null) {
            String sql = "INSERT INTO mywechat_user(userCode,userPassword,userEmail,userRole) VALUES (?,?,?,?)";
            Object[] params = {userCode, password,email,id};
            flag = BaseDao.update(connection, pstm, resultSet, sql, params);
        }
        BaseDao.release(connection,pstm,resultSet);
        return flag;
    }

    /**
     * 忘记密码
     * @param email
     * @param password
     * @return
     */
    @Override
    public int updatePassword(Connection connection, String email, String password) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        int flag = 0;

        if (connection != null) {
            String sql = "UPDATE mywechat_user SET userPassword = ? WHERE userEmail = ?";
            Object[] params = {password,email};
            flag = BaseDao.update(connection, pstm, resultSet, sql, params);
        }
        BaseDao.release(connection,pstm,resultSet);
        return flag;
    }

    /**
     * 修改密码
     * @param connection
     * @param id
     * @param password
     * @return
     * @throws Exception
     */
    @Override
    public int updatePassword(Connection connection, int id, String password) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        int flag = 0;
        if (connection != null) {
            String sql = "UPDATE mywechat_user SET userPassword = ? WHERE id = ?";
            Object[] params = {password,id};
            flag = BaseDao.update(connection, pstm, resultSet, sql, params);
        }
        BaseDao.release(connection,pstm,resultSet);
        return flag;
    }

    /**
     * 查询用户昵称是否重复
     * @param connection
     * @param userName
     * @return
     * @throws Exception
     */
    @Override
    public int getUserName(Connection connection,int id, String userName) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        int flag = 0;
        if(connection!=null){
            String sql = "select * from mywechat_user where userName = ? AND id<>?";
            Object[] params = {userName,id};
            resultSet = BaseDao.research(connection, pstm, resultSet, sql, params);
            if(resultSet.next()){
                flag = 1;
            }
            BaseDao.release(connection,pstm,resultSet);
        }
        return flag;
    }

    /**
     * 修改用户昵称
     * @param connection
     * @param id
     * @param userName
     * @return
     * @throws Exception
     */
    @Override
    public int updateUserName(Connection connection, int id, String userName) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        int flag = 0;
        if (connection != null) {
            String sql = "UPDATE mywechat_user SET userName = ? WHERE id = ?";
            Object[] params = {userName,id};
            flag = BaseDao.update(connection, pstm, resultSet, sql, params);
        }
        BaseDao.release(connection,pstm,resultSet);
        return flag;
    }

    /**
     * 修改用户个人信息
     * @param connection
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public int updateUserMessage(Connection connection, User user) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        int flag = 0;
        if (connection != null) {
            String sql = "UPDATE mywechat_user SET age = ?,gender =?,birthday = ?,address = ? WHERE id = ?";
            Object[] params = {user.getAge(),user.getSex(),user.getBirthday(),user.getAddress(),user.getId()};
            flag = BaseDao.update(connection, pstm, resultSet, sql, params);
        }
        BaseDao.release(connection,pstm,resultSet);
        return flag;
    }

}
