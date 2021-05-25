package com.rdc_wechat.dao.user;

import com.rdc_wechat.pojo.User;

import java.sql.Connection;

/**
 * @author 86178
 */
public interface UserDao {

    /**
     * 得到登录用户
     * @param connection
     * @param userCode
     * @param password
     * @return
     * @throws Exception
     */
    public User getLoginUser(Connection connection,String userCode,String password) throws Exception;

    /**
     * 判断是否有已经注册的用户
     * @param connection
     * @param email
     * @return
     * @throws Exception
     */
    public int getLoginUser(Connection connection,String email) throws Exception;


    /**
     * 根据账号判断是否有注册的用户
     * @param connection
     * @param userCode
     * @return
     * @throws Exception
     */
    public int getLoginUserByCode(Connection connection,String userCode) throws Exception;

    /**
     * 注册功能
     * @param connection
     * @param userCode
     * @param password
     * @return
     * @throws Exception
     */
    public int insertRegisterUser(Connection connection,String userCode,String password,String email,int id) throws Exception;

    /**
     * 忘记密码
     * @param email
     * @param password
     * @return
     */
    public int updatePassword(Connection connection,String email,String password) throws Exception;

    /**
     * 修改密码
     * @param connection
     * @param id
     * @param password
     * @return
     * @throws Exception
     */
    public int updatePassword(Connection connection,int id,String password) throws Exception;

    /**
     * 查询除了制定id外的用户昵称
     * @param connection
     * @param id
     * @param userName
     * @return
     * @throws Exception
     */
    public int getUserName(Connection connection, int id,String userName) throws Exception;

    /**
     * 修改用户昵称
     * @param connection
     * @param id
     * @param userName
     * @return
     * @throws Exception
     */
    public int updateUserName(Connection connection,int id,String userName) throws Exception;

    /**
     * 修改个人信息
     * @param connection
     * @param user
     * @return
     * @throws Exception
     */
    public int updateUserMessage(Connection connection,User user) throws Exception;


}
