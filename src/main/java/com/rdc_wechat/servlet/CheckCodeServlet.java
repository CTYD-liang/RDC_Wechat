package com.rdc_wechat.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 生成验证图片类
 * @author 86178
 */
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //内存中创建,获得一张图片
        BufferedImage image = new BufferedImage(80,40,BufferedImage.TYPE_INT_RGB);
        //得到一支笔在图片上作图
        Graphics2D graphics = (Graphics2D)image.getGraphics();
        //设置颜色
        graphics.setColor(Color.white);
        //填充
        graphics.fillRect(0,0,80,40);
        graphics.setColor(Color.BLUE);
        //线条的长度大概1
        graphics.drawRect(0,0,79,39);
        //给图片写数据
        graphics.setColor(Color.red);
        graphics.setFont(new Font(null,Font.BOLD,20));
        String checkCodeSession = makeNum();
        graphics.drawString(checkCodeSession,0,20);
        Random ran = new Random();
        graphics.setColor(Color.GREEN);
        //随机生成坐标点
        for (int i = 0; i < 10; i++) {
            int x1 = ran.nextInt(80);
            int x2 = ran.nextInt(80);

            int y1 = ran.nextInt(40);
            int y2 = ran.nextInt(40);
            graphics.drawLine(x1,y1,x2,y2);
        }

        //告诉浏览器，这个请求用图片的方式打开
        resp.setContentType("image/jpeg");
        //网站存在缓存，不让浏览器缓存
        resp.setDateHeader("expires",-1);
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Pragma","no-cache");

        //把图片写给浏览器
        ImageIO.write(image,"jpg", resp.getOutputStream());

        //将验证码存入session
        req.getSession().setAttribute("checkCode_session",checkCodeSession);

    }
    //生成随机数
    private String makeNum(){
        Random random = new Random();
        String num = random.nextInt(9999) + "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4-num.length() ; i++) {
            sb.append("0");
        }
        num = sb.toString() + num;
        return num;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
