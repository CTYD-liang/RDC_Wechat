package com.rdc_wechat.pojo;

/**
 * 错误时候给浏览器提示信息
 * @author 86178
 */
public class Result {
    private boolean flag;
    private String message;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
