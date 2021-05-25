package com.rdc_wechat.pojo;


/**
 * 朋友圈实体类
 * @author 86178
 */
public class Img {
    /**
     * 朋友圈的id
     */
    private  Integer id;
    /**
     * 用户名称
     */
    private  String name;
    /**
     * 用户文案
     */
    private String text;
    /**
     * 用户的图片
     */
    private String  pictureUrl;
    /**
     * 日期
     */
    private String date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
