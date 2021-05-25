package com.rdc_wechat.servlet;

import com.alibaba.fastjson.JSONArray;
import com.rdc_wechat.pojo.User;
import com.rdc_wechat.service.user.UserService;
import com.rdc_wechat.service.user.UserServiceImpl;
import com.rdc_wechat.util.Constants;
import com.rdc_wechat.util.Md5Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 实现Servlet的复用
 * 记得将代码提取成方法
 * @author 86178
 */
public class UserServlet extends HttpServlet {
    /**
     * 调用方法进行判断
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        if (Objects.equals(method, "savepwd")){
            this.updatePassword(req, resp);
        }else if (Objects.equals(method, "pwdmodify")){
            this.confirmPassword(req,resp);
        }else if (Objects.equals(method, "confirmUserName")){
            this.confirmUserName(req, resp);
        }else if (Objects.equals(method, "updateUserName")){
            this.updateUserName(req, resp);
        }else if(Objects.equals(method, "updateUserMessage")){
            this.updateUserMessage(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * 修改密码
     * @param req
     * @param resp
     */
    public void updatePassword(HttpServletRequest req, HttpServletResponse resp){
        Object obj = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");
        String s = Md5Utils.makeMd5(newpassword);
        boolean flag = false;
        if(obj!= null&&newpassword!=null){
            UserService userService = new UserServiceImpl();
            flag = userService.update(((User)obj).getId(),s);
            if(flag){
                req.setAttribute("message","修改密码成功,请退出,使用新密码重新登录!");
            }else {
                req.setAttribute("message","密码修改失败!");
            }
        }else {
            req.setAttribute("message","新密码不能为空或session已过期");
        }

        try {
            req.getRequestDispatcher("/jsp/modifyPassword.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证旧密码是否正确
     * @param req
     * @param resp
     */
    public void confirmPassword(HttpServletRequest req, HttpServletResponse resp){
       //从Session中获取信息
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");
        String s = Md5Utils.makeMd5(oldpassword);
        Map<String, String> resultMap = new HashMap<>();

        if (o==null){
            //session失效或过期了
            resultMap.put("result","sessionerror");
        }else if (oldpassword==null){
            //输入密码为空
            resultMap.put("result","error");
        }else {
            String userPassword = ((User) o).getUserPassword();
            if (userPassword.equals(s)){
                resultMap.put("result","true");
            }else{
                resultMap.put("result","false");
            }
        }

        resp.setContentType("application/json");
        try {
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断用户昵称是否已经存在
     * @param req
     * @param resp
     */
    public void confirmUserName(HttpServletRequest req, HttpServletResponse resp){
        //从Session中获取id
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        //获取新的昵称
        String userName = req.getParameter("userName");
        Map<String, String> resultMap = new HashMap<>();
        if(o==null){
            //session失效或过期了
            resultMap.put("result","sessionError");
        }else if (Objects.equals(userName, "")){
            //昵称输入为空
            resultMap.put("result","noUserName");
        }else{
            UserService userService = new UserServiceImpl();
            //查询除自己外所有用户昵称
            int i = userService.alreadyUserName(((User)o).getId(),userName);
            if (i==1){
                //昵称重复
                resultMap.put("result","alreadyName");
            }else{
                //可用昵称
                resultMap.put("result","userCanUse");
            }
        }

        resp.setContentType("application/json");
        try {
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改昵称
     * @param req
     * @param resp
     */
    public void updateUserName(HttpServletRequest req, HttpServletResponse resp){

        //不设置的话接收会出现中文乱码!
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Object obj = req.getSession().getAttribute(Constants.USER_SESSION);
        String userName = req.getParameter("userName");
        boolean flag = false;
        if(obj!= null&& !Objects.equals(userName, "")){
            UserService userService = new UserServiceImpl();
            flag = userService.updateUserName(((User)obj).getId(),userName);
            if(flag){
                req.setAttribute("message","昵称修改成功!");
            }else {
                req.setAttribute("message","昵称修改失败!");
            }
        }else {
            System.out.println(userName);
            req.setAttribute("message","昵称为空!");
        }

        try {
            req.getRequestDispatcher("/jsp/modifyUserMessage.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改个人信息
     * @param req
     * @param resp
     */
    public void updateUserMessage(HttpServletRequest req, HttpServletResponse resp){
        Object obj = req.getSession().getAttribute(Constants.USER_SESSION);
        String age = req.getParameter("inputAge");
        String sex = req.getParameter("sex");
        String birth = req.getParameter("inputBirth");
        String address = req.getParameter("inputAddress");

        User user = new User();
        user.setId(((User)obj).getId());
        user.setAge(age);
        user.setSex(sex);
        user.setBirthday(birth);
        user.setAddress(address);
        boolean flag = false;
        if((obj != null) && !Objects.equals(birth, "")){
            UserService userService = new UserServiceImpl();
            flag = userService.updateUserMessage(user);
            if(flag){
                req.setAttribute("messagetip","个人信息修改成功!");
            }else {
                req.setAttribute("messagetip","个人信息修改失败!");
            }
        }else {
            req.setAttribute("messagetip","生日不能为空!");
        }
        try {
            req.getRequestDispatcher("/jsp/modifyUserMessage.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
