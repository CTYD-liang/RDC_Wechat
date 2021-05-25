package com.rdc_wechat.service.email;

import com.sun.mail.util.MailSSLSocketFactory;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * @author 86178
 */
public class EmailServiceImpl extends Thread implements EmailService{

    private String emailCode;
    private String code;
    public EmailServiceImpl(String emaiCode,String code){
       this.emailCode = emaiCode;
       this.code = code;
    }
    @Override
    public void run() {
        //配置
        Properties prop = new Properties();
        //1.设置QQ邮件服务器
        prop.setProperty("mail.host", "smtp.qq.com");
        //2.邮件发送协议
        prop.setProperty("mail.transport.protocol", "smtp");
        //3.需要验证用户名密码
        prop.setProperty("mail.smtp.auth", "true");

        //4.关于QQ邮箱，还要设置SSL加密，加上以下代码即可
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        if (sf != null) {
            sf.setTrustAllHosts(true);
        }
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        //使用JavaMail发送邮件的5个步骤
        //1.创建定义整个应用程序所需的环境信息的 Session 对象
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名、授权码
                return new PasswordAuthentication("1932896399@qq.com", "kgumxtawwfhliihc");
            }
        });

        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);

        //2、通过session得到transport对象
        Transport ts = null;
        try {
            ts = session.getTransport();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

        try {
            //3、使用邮箱的用户名和授权码连上邮件服务器
            if (ts != null) {
                ts.connect("smtp.qq.com", "1932896399@qq.com", "kgumxtawwfhliihc");
            }
            //4、创建邮件
            //创建邮件对象
            MimeMessage message = new MimeMessage(session);
            //指明邮件的发件人
            message.setFrom(new InternetAddress("1932896399@qq.com"));
            //指明邮件的收件人
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailCode));
            //邮件的标题
            message.setSubject("验证码");
            //邮件的文本内容
            message.setContent("【WR科技】验证码"+code+"用于找回"+emailCode+"的密码，请勿泄露给他人！", "text/html;charset=UTF-8");
            //5、发送邮件
            if (ts != null) {
                ts.sendMessage(message, message.getAllRecipients());
            }
            //6.关闭连接
            if (ts != null) {
                ts.close();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmail(String emailCode, String code) {

    }
}

