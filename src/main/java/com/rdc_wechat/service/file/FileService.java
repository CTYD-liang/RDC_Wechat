package com.rdc_wechat.service.file;

import com.rdc_wechat.pojo.Img;
import java.util.List;

/**
 * @author 86178
 */
public interface FileService {

    /**
     * 存头像
     * @param id
     * @param bytes
     * @return
     */
    public boolean updateUserHead(int id,byte[] bytes);

    /**
     * 取头像
     * @param id
     * @return
     */
    public byte[] getImg(int id);

    /**
     * 发朋友圈
     * @param id
     * @param text
     * @param url
     * @param date
     * @return
     */
    public boolean uploadCircle(int id, String text, String url, String date);

    /**
     * 查看朋友圈
     * @param userName
     * @param id
     * @param con
     * @return
     */
    public List<Img> seeCircle(String userName, int id, int con);

}
