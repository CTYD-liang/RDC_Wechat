package com.rdc_wechat.pojo;

import java.util.Date;

public class User {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户账号
     */
    private String userCode;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 用户年龄
     */
    private String age;
    /**
     * 用户性别
     */
    private String sex;
    /**
     * 用户生日
     */
    private String birthday;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户地址
     */
    private String address;
    /**
     * 用户标识（1为管理员，2为用户）
     */
    private Integer userRole;


    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

}
