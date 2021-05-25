package com.rdc_wechat.servlet;

import com.rdc_wechat.service.user.UserService;
import com.rdc_wechat.service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 86178
 */
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String userCode = req.getParameter("userName");
        String password = req.getParameter("userPassword");
        String email = req.getParameter("emailBox");
        String checkCode = req.getParameter("checkCode");
        //先获取生成的验证码，因为图片的验证码是一次单独的请求
        HttpSession session = req.getSession();
        String checkCodeSession = (String) session.getAttribute("checkCode_session");
        //删除验证码，这样返回的时候验证码就会重新刷一次
        session.removeAttribute("checkCode_session");
        //第一个是为了防止空指针
        if (checkCodeSession != null && checkCodeSession.equalsIgnoreCase(checkCode)) {
            UserService userService = new UserServiceImpl();
            int alreadylogin = userService.login(email);
            if(alreadylogin==1){
                //存储提示信息到request
                req.setAttribute("cc_error", "邮箱已经被注册，注册失败！");
                req.getRequestDispatcher("register01.jsp").forward(req, resp);
            }else {

                int loginUserByCode = userService.getLoginUserByCode(userCode);
                if (loginUserByCode==1){
                    //存储提示信息到request
                    req.setAttribute("cc_error", "账号已经被注册，注册失败！");
                    req.getRequestDispatcher("register01.jsp").forward(req, resp);
                }else {
                    int flag = userService.register(userCode, password, email,2);
                    if (flag == 1) {
                        //跳转到成功页面面
                        resp.sendRedirect(req.getContextPath() + "/common/registerSuccess.jsp");
                    } else {
                        //存储提示信息到request
                        req.setAttribute("cc_error", "未知错误，注册失败！");
                        req.getRequestDispatcher("register01.jsp").forward(req, resp);
                    }
                }
            }
        }else{
            //存储提示信息到request
            req.setAttribute("cc_error", "验证码错误");
            req.getRequestDispatcher("register01.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
