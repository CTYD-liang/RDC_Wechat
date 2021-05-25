package com.rdc_wechat.pojo;

/**
 * 浏览器发送给服务器的websocket数据
 * @author 86178
 */
public class Message {

    /**
     * 消息来自
     */
    private String fromName;
    /**
     * 消息发给
     */
    private String toName;
    /**
     * 消息内容
     */
    private String message;
    /**
     * 是否是验证消息
     */
    private int icon;
    /**
     * 是否已读
     */
    private int ifRead;

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
