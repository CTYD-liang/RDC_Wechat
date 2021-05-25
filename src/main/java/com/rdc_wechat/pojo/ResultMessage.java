package com.rdc_wechat.pojo;

/**
 * 服务器发送给浏览器的websocket数据
 * @author 86178
 */
public class ResultMessage {
    private boolean isSystem;
    private String fromName;
    private Object message;

    public boolean isSystem() {
        return isSystem;
    }

    public void setSystem(boolean system) {
        isSystem = system;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
