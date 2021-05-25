package com.rdc_wechat.service.user;

import com.rdc_wechat.dao.BaseDao;
import com.rdc_wechat.dao.user.UserDao;
import com.rdc_wechat.dao.user.UserDaoImpl;
import com.rdc_wechat.pojo.User;

import java.sql.Connection;

/**
 * @author 86178
 */
public class UserServiceImpl implements UserService{
    /**
     * 业务层调用Dao层
     */
    private UserDao userDao;
    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    /**
     * 登录验证
     * @param userCode
     * @param password
     * @return
     */
    @Override
    public User login(String userCode, String password) {
        Connection connection = null;
        User user = null;
        try {
            connection = BaseDao.getConnection();
            //通过业务层调用对应的数据库操作
            user = userDao.getLoginUser(connection, userCode,password);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.release(connection,null,null);
        }
        return user;
    }

    /**
     *通过邮箱查找用户
     * @param email
     * @return
     */
    @Override
    public int login(String email) {
        Connection connection = null;
        int flag = 0;
        try {
            connection = BaseDao.getConnection();
            //通过业务层调用对应的数据库操作
            flag = userDao.getLoginUser(connection, email);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.release(connection,null,null);
        }
        return flag;
    }

    /**
     * 判断账号不能相等
     * @param userCode
     * @return
     */
    @Override
    public int getLoginUserByCode(String userCode) {
        Connection connection = null;
        int flag = 0;
        try {
            connection = BaseDao.getConnection();
            //通过业务层调用对应的数据库操作
            flag = userDao.getLoginUserByCode(connection, userCode);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.release(connection,null,null);
        }
        return flag;
    }

    /**
     * 注册
     * @param userCode
     * @param password
     */
    @Override
    public int register(String userCode, String password, String eamil,int id){
        Connection connection = null;
        int flag = 0;
        try {
            connection = BaseDao.getConnection();
            flag = userDao.insertRegisterUser(connection, userCode, password,eamil,id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.release(connection,null,null);
        }
        return flag;
    }

    /**
     * 修改密码
     * @param email
     * @param password
     * @return
     */
    @Override
    public int update(String email, String password) {
        Connection connection = null;
        int flag = 0;
        try {
            connection = BaseDao.getConnection();
            flag = userDao.updatePassword(connection,email,password);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.release(connection,null,null);
        }
        return flag;
    }

    /**
     *通过id修改密码
     * @param id
     * @param password
     * @return
     */
    @Override
    public boolean update(int id, String password) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if (userDao.updatePassword(connection,id,password)>0){
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.release(connection,null,null);
        }
        return flag;
    }

    /**
     * 判断昵称是否重复
     * @param id
     * @param userName
     * @return
     */
    @Override
    public int alreadyUserName( int id,String userName) {
        Connection connection = null;
        int flag = 0;
        try {
            connection = BaseDao.getConnection();
            //通过业务层调用对应的数据库操作
            flag = userDao.getUserName(connection,id,userName);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.release(connection,null,null);
        }
        return flag;
    }

    /**
     * 修改昵称
     * @param id
     * @param userName
     * @return
     */
    @Override
    public boolean updateUserName(int id, String userName) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if (userDao.updateUserName(connection,id,userName) == 1){
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.release(connection,null,null);
        }
        return flag;
    }

    /**
     * 修改用户个人信息
     * @param user
     * @return
     */
    @Override
    public boolean updateUserMessage(User user) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if (userDao.updateUserMessage(connection,user) == 1){
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.release(connection,null,null);
        }
        return flag;
    }
}
