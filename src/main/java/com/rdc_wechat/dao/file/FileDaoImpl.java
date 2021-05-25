package com.rdc_wechat.dao.file;

import com.rdc_wechat.dao.BaseDao;
import com.rdc_wechat.pojo.Img;
import com.rdc_wechat.pojo.User;
import com.rdc_wechat.service.friend.FriendService;
import com.rdc_wechat.service.friend.FriendServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 86178
 */
public class FileDaoImpl implements FileDao{
    /**
     * 将图片存进数据库
     * @param id
     * @param b
     * @return
     * @throws Exception
     */
    @Override
    public int updateHeadPicture(Connection connection,int id, byte[] b) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        int flag = 0;
        if (connection != null) {
            String sql = "UPDATE mywechat_user SET userHead = ? where id = ?";
            Object[] params = {b,id};
            flag = BaseDao.update(connection, pstm, resultSet, sql, params);
        }
        BaseDao.release(connection,pstm,resultSet);
        return flag;
    }

    /**
     * 取出图片
     * @param connection
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public byte[] getImg(Connection connection, int id) throws Exception {

        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        byte[] b = null;
        if (connection!= null){
            String sql = "select userHead from mywechat_user where id =?";
            Object[] parms = {id};
            resultSet = BaseDao.research(connection, pstm, resultSet, sql, parms);
            if(resultSet.next()){
                b = resultSet.getBytes("userHead");
            }
        }
        BaseDao.release(connection,pstm,resultSet);
        return b;
    }


    /**
     *
     * @param connection
     * @param id
     * @param text
     * @param url
     * @param date
     * @return
     * @throws Exception
     */
    @Override
    public int uploadCircle(Connection connection, int id, String text, String url, String date) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        int flag = 0;
        if (connection != null) {
            String sql = "INSERT INTO user_cirle(des,img,user_id,date) VALUES (?,?,?,?)";
            Object[] params = {text,url,id,date};
            flag = BaseDao.update(connection, pstm, resultSet, sql, params);
        }
        BaseDao.release(connection,pstm,resultSet);
        return flag;
    }

    /**
     * 查看朋友圈
     * @param connection
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public List<Img> seeCircle(Connection connection, String userName,int id,int con) throws Exception {
        PreparedStatement pstm = null;
        ResultSet res = null;
        List<Img> circle = new ArrayList<Img>();
        Img img = null;
        int uId = 0;
        if (connection != null) {
            FriendServiceImpl friendService = new FriendServiceImpl();
            //获取不是黑名单的好友
            List<User> userList = friendService.seeFriend(id, con);
            //将自己加进去
            User user = new User();
            user.setId(id);
            user.setUserName(userName);
            userList.add(user);
            for (User u : userList) {
                uId = u.getId();
                String sql = "SELECT * FROM `user_cirle` WHERE user_id = ?";
                Object[] params = {uId};
                res = BaseDao.research(connection, pstm, res, sql, params);
                while(res.next()){
                    img = new Img();
                    img.setId(res.getInt("id"));
                    img.setName(u.getUserName());
                    img.setText(res.getString("des"));
                    img.setPictureUrl(res.getString("img"));
                    System.out.println(res.getString("img"));
                    img.setDate(res.getString("date"));
                    circle.add(img);
                }
            }
        }
        return circle;
    }
}
