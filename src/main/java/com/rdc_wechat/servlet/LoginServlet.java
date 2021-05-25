package com.rdc_wechat.servlet;

import com.rdc_wechat.pojo.User;
import com.rdc_wechat.service.user.UserService;
import com.rdc_wechat.service.user.UserServiceImpl;
import com.rdc_wechat.util.Constants;
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
public class LoginServlet extends HttpServlet {
    /**
     *  调用业务层代码
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String userCode = req.getParameter("username");
        String passWord = req.getParameter("passWord");
        String checkCode = req.getParameter("checkCode");
        String check = req.getParameter("check");
        //加密
        String password = Md5Utils.makeMd5(passWord);

        //先获取生成的验证码，因为图片的验证码是一次单独的请求
        HttpSession session = req.getSession();
        String checkCodeSession = (String) session.getAttribute("checkCode_session");
        //删除验证码，这样返回的时候验证码就会重新刷一次
        session.removeAttribute("checkCode_session");
        //第一个是为了防止空指针
        if (checkCodeSession != null && checkCodeSession.equalsIgnoreCase(checkCode)) {
            //判断用户名和密码是否正确
            //调用业务层代码，与数据库中的代码作对比
            UserService userService = new UserServiceImpl();
            //查询登录的人
            User user = userService.login(userCode, password);
            if(user!= null){
                req.getSession().setAttribute(Constants.USER_SESSION,user);
                if(user.getUserName()!=null){
                    req.getSession().setAttribute("userName",user.getUserName());
                }else{
                    req.getSession().setAttribute("userName",userCode);
                }

                if("1".equals(check)){
                    //跳转到管理员页面
                    //跳转到用户主页面
//                    resp.sendRedirect(req.getContextPath()+"/jsp/main.jsp");
                }else{
                    //跳转到用户主页面
                    resp.sendRedirect(req.getContextPath()+"/jsp/main.jsp");
                }
            }else {
                req.setAttribute("cc_error","用户名或密码错误！");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }
        }else{
            //存储提示信息到request
            req.setAttribute("cc_error", "验证码错误");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
