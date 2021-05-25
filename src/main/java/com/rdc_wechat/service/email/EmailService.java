package com.rdc_wechat.service.email;

/**
 * @author 86178
 */
public interface EmailService {
    /**
     * 发送邮件
     * @param emailCode
     * @param code
     */
    public void sendEmail(String emailCode,String code);
}
