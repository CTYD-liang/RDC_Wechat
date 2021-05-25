package com.rdc_wechat.servlet;

import com.rdc_wechat.service.email.EmailServiceImpl;
import com.rdc_wechat.util.RandomCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 86178
 */
public class SendEmailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        //1.获取邮件的收件人
        String emailCode = req.getParameter("emailCode");
        //2.获得随机数
        String emailSession = RandomCode.getCode()+"";
        //3.交给业务层处理
        EmailServiceImpl emailService = new EmailServiceImpl(emailCode,emailSession);
        //4.多线程发送邮件
        emailService.start();
        //5.将邮箱号和验证码存入session
        req.getSession().setAttribute("email",emailCode);
        req.getSession().setAttribute("emailCode_session",emailSession);
        //6.重定向到重置页面
        req.setAttribute("cc_error","验证码已发送，请注意查收！");
        resp.sendRedirect(req.getContextPath()+"/password01.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}

