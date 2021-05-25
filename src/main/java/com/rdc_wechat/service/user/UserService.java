package com.rdc_wechat.service.user;

import com.rdc_wechat.pojo.User;

import java.sql.Connection;

/**
 * @author 86178
 */
public interface UserService {

    /**
     * 用户登录
     * @param userCode
     * @param password
     * @return
     */
    public User login(String userCode,String password);

    /**
     * 查找有无同邮箱用户
     * @param email
     * @return
     */
    public int login(String email);

    /**
     * 判断账号是否相同
     * @param userCode
     * @return
     */
    public int getLoginUserByCode(String userCode);

    /**
     * 用户注册
     * @param userCode
     * @param password
     * @return
     */
    public int register(String userCode,String password,String email,int id);

    /**
     * 修改数据
     * @param email
     * @param password
     * @return
     */
    public int update(String email,String password);

    /**
     * 根据id修改密码
     * @param id
     * @param password
     * @return
     */
    public boolean update(int id,String password);

    /**
     * 判断昵称是否重复
     * @param id
     * @param userName
     * @return
     */
    public int alreadyUserName(int id,String userName);

    /**
     * 根据id修改昵称
     * @param id
     * @param userName
     * @return
     */
    public boolean updateUserName(int id,String userName);

    /**
     * 修改用户个人信息
     * @param user
     * @return
     */
    public boolean updateUserMessage(User user);

}
