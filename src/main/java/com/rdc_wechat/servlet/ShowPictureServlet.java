package com.rdc_wechat.servlet;

import com.rdc_wechat.pojo.Img;
import com.rdc_wechat.pojo.User;
import com.rdc_wechat.service.file.FileService;
import com.rdc_wechat.service.file.FileServiceImpl;
import com.rdc_wechat.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 显示图片或者朋友圈
 * @author 86178
 */
public class ShowPictureServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if ("showPicture".equals(method)){
            try {
                this.showPicture(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if ("showCircle".equals(method)){
            try {
                this.showCircle(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * 显示图片
     * @param req
     * @param resp
     */
    public void showPicture(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("image/*");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        //获取当前用户id
        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        Integer userId = user.getId();
        byte[] buff = null;
        OutputStream os = null;
        FileService fileService = new FileServiceImpl();
        try {
            buff = fileService.getImg(userId);
            if (buff!=null){
                //获得输出流
                os = resp.getOutputStream();
                //将其输出页面
                os.write(buff);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示朋友圈
     * @param req
     * @param resp
     */
    public void showCircle(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        int userId = user.getId();
        String userName = user.getUserName();

        FileServiceImpl fileService = new FileServiceImpl();
        List<Img> imgs = fileService.seeCircle(userName, userId, Constants.NUMBER_ZERO);
        if(imgs!=null){

            req.setAttribute("circleList", imgs);
            req.getRequestDispatcher("/jsp/showFriendsCircle.jsp").forward(req,resp);
        }else{

        }
    }

}
