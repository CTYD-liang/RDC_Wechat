package com.rdc_wechat.dao.file;

import com.rdc_wechat.pojo.Img;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * 文件操作接口
 * @author 86178
 */
public interface FileDao {

    /**
     * 将头像存进数据库
     * @param id
     * @param b
     * @return
     * @throws Exception
     */
    public int updateHeadPicture(Connection connection,int id, byte[] b) throws Exception;

    /**
     * 取出头像
     * @param connection
     * @param id
     * @return
     * @throws Exception
     */
    public byte[] getImg(Connection connection, int id) throws Exception;



    /**
     * 上传朋友圈
     * @param connection
     * @param id
     * @param text
     * @param url
     * @param date
     * @return
     * @throws Exception
     */
    public int uploadCircle(Connection connection, int id, String text, String url, String date) throws Exception;


    /**
     * 查看朋友圈
     * @param connection
     * @param id
     * @return
     * @throws Exception
     */
    public List<Img> seeCircle(Connection connection, String userName,int id,int con) throws Exception;
}
