package com.rdc_wechat.util;

/**
 * 随机数类
 * @author 86178
 */
public class RandomCode {
    /**
     * 获得随机数
     * @return
     */
    public static int getCode(){
        return (int) (Math.random() * 8999 + 1000);
    }
}
