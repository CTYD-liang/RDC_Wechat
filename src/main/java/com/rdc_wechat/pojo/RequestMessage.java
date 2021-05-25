package com.rdc_wechat.pojo;

/**
 * 请求的消息
 * @author 86178
 */
public class RequestMessage {
    /**
     * 发送人的id
     */
    private int fromId;
    /**
     * 请求消息内容
     */
    private String text;
    /**
     * 是否是系统消息
     */
    private int icon;
    /**
     * 是否已读
     */
    private int ifRead;

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getIfRead() {
        return ifRead;
    }

    public void setIfRead(int ifRead) {
        this.ifRead = ifRead;
    }
}
