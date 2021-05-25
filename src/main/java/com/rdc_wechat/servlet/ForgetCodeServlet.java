package com.rdc_wechat.servlet;

import com.rdc_wechat.service.user.UserServiceImpl;
import com.rdc_wechat.util.Md5Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 86178
 */
public class ForgetCodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //输入的邮箱验证码
        String mailCode = req.getParameter("mailCode");
        //密码
        String firstcode = req.getParameter("firstcode");
        //验证码
        String checkCode = req.getParameter("checkCode");
        //获取session中的信息
        HttpSession session = req.getSession();
        //获取图片验证码
        String checkCodeSession = (String) session.getAttribute("checkCode_session");
        //获取邮箱号
        String email = (String)session.getAttribute("email");
        //获取邮箱验证码
        String emailCodeSession = (String)session.getAttribute("emailCode_session");
        //删除验证码，这样返回的时候验证码就会重新刷一次
        session.removeAttribute("checkCode_session");
        //判断验证码是否正确
        if (checkCodeSession != null && checkCodeSession.equalsIgnoreCase(checkCode)) {
            //判断邮箱
            if(emailCodeSession!=null&&emailCodeSession.equalsIgnoreCase(mailCode)){
                String nowPassword = Md5Utils.makeMd5(firstcode);
                UserServiceImpl userService = new UserServiceImpl();
                int update = userService.update(email, nowPassword);
                //跳转成功页面
                resp.sendRedirect(req.getContextPath()+"/common/passwordSuccess.jsp");
            }else{
                //存储提示信息到request
                req.setAttribute("cc_error", "邮箱验证码错误！");
                req.getRequestDispatcher("password01.jsp").forward(req, resp);
            }

        }else{
            //存储提示信息到request
            req.setAttribute("cc_error", "验证码错误");
            req.getRequestDispatcher("password01.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
