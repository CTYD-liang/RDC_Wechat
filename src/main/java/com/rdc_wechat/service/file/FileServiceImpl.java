package com.rdc_wechat.service.file;

import com.rdc_wechat.dao.BaseDao;
import com.rdc_wechat.dao.file.FileDao;
import com.rdc_wechat.dao.file.FileDaoImpl;
import com.rdc_wechat.pojo.Img;

import javax.servlet.jsp.jstl.sql.Result;
import java.sql.Connection;
import java.util.List;

/**
 * 实现文件管理接口
 * @author 86178
 */
public class FileServiceImpl implements FileService {

    /**
     * 业务层调用Dao层
     */
    private FileDao fileDao;

    public FileServiceImpl() {
        fileDao = new FileDaoImpl();
    }

    /**
     * 上传头像
     * @param id
     * @param bytes
     * @return
     */
    @Override
    public boolean updateUserHead(int id, byte[] bytes) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if (fileDao.updateHeadPicture(connection, id, bytes) == 1) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.release(connection, null, null);
        }
        return flag;
    }

    /**
     * 取头像
     * @param id
     * @return
     */
    @Override
    public byte[] getImg(int id) {
        Connection connection = null;
        byte[] b = null;
        Result res = null;
        try {
            connection = BaseDao.getConnection();
            b = fileDao.getImg(connection,id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.release(connection,null,null);
        }
        return b;
    }


    /**
     * 发朋友圈
     * @param id
     * @param text
     * @param url
     * @param date
     * @return
     */
    @Override
    public boolean uploadCircle(int id, String text, String url, String date) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if (fileDao.uploadCircle(connection, id, text,url,date) == 1) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.release(connection, null, null);
        }
        return flag;
    }

    /**
     * 查看朋友圈
     * @param userName
     * @param id
     * @param con
     * @return
     */
    @Override
    public List<Img> seeCircle(String userName, int id, int con) {
        Connection connection = null;
        List<Img> imgs = null;
        try {
            connection = BaseDao.getConnection();
            imgs = fileDao.seeCircle(connection, userName, id, con);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.release(connection, null, null);
        }
        return imgs;
    }
}
