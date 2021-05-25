package com.rdc_wechat.servlet;

import com.rdc_wechat.pojo.User;
import com.rdc_wechat.service.file.FileService;
import com.rdc_wechat.service.file.FileServiceImpl;
import com.rdc_wechat.util.Constants;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 上传头像发朋友圈
 * @author 86178
 */
public class UploadPictureServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        //设置显示编码
        resp.setCharacterEncoding("UTF-8");
        // 验证请求是否满足要求（post 请求 / enctype 是否以multipart打头
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        // 如果不满足要求就立即结束对该请求的处理
        if (!isMultipart) {
            req.setAttribute("messageAlert","上传图片失败！");
            return;
        }
        String method = null;
        String text = null;
        //存取上传文件
        List files = new ArrayList();
        //创建一个解析器工厂
        DiskFileItemFactory fu = new DiskFileItemFactory();
        //得到解析器，处理上传的文件数据，并将表单中每个输入项封装成一个FileItem 对象中
        ServletFileUpload upload = new ServletFileUpload(fu);
        upload.setHeaderEncoding("UTF-8");
        try {
            //取得表单的数据内容
            List<FileItem> list = upload.parseRequest(req);
            //此层增强for循环遍历表单中有多少个上传文件将文件存到list中
            for(FileItem items:list){
                //判断是否不是文件
                if(items.isFormField()){
                    //如果是文本类型
                    if("method".equals(items.getFieldName())){
                        method = items.getString("UTF-8");
                        System.out.println(method);
                    }else if("text".equals(items.getFieldName())){
                        text = items.getString("UTF-8");
                        System.out.println(text);
                    }
                }else{
                    //文件类型
                    files.add(items);
                }
            }
            if("uploadHeadPicture".equals(method)){
                this.uploadHeadPicture(req,resp,files);
            }else if ("postFriendCircle".equals(method)){
                this.uploadCircle(req, resp,files,text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


    /**
     * 存头像
     * @param req
     * @param resp
     */
    public void uploadHeadPicture(HttpServletRequest req, HttpServletResponse resp,  List files) throws Exception{
        //插入标志
        boolean flag = false;
        //存取上传人id
        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        Integer userId = user.getId();
        for(int i=0;i<files.size();i++) {
            //从集合取出文件
            FileItem item = (FileItem) files.get(i);
            //获得文件名
            String filename = item.getName();
            String substring = null;
            if (!Objects.equals(filename, "")){
                //获取文件后缀
                substring = filename.substring(filename.length() - 3);
                System.out.println(substring);
                if ("jpg".equals(substring) || "png".equals(substring)) {
                    //将文件转为输入流
                    InputStream input = item.getInputStream();
                    //将字节数组直接存进去数据库就可以
                    byte[] buffer = new byte[input.available()];
                    input.read(buffer);
                    FileService fileService = new FileServiceImpl();
                    flag = fileService.updateUserHead(userId, buffer);
                    //将流关闭
                    input.close();
                    if (flag) {
                        req.setAttribute("messageAlert", "发布成功！");
                    } else {
                        req.setAttribute("messageAlert", "发布失败！");
                    }
                }else{
                    req.setAttribute("messageAlert", "图片格式不正确！");
                }
            } else {
                FileService fileService = new FileServiceImpl();
                flag = fileService.updateUserHead(userId,null);
                if (flag) {
                    req.setAttribute("messageAlert", "头像为空！");
                } else {
                    req.setAttribute("messageAlert", "发布失败！");
                }
            }
            try {
                req.getRequestDispatcher("/jsp/headPicture.jsp").forward(req,resp);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 发朋友圈
     * @param req
     * @param resp
     * @throws Exception
     */
    public void uploadCircle(HttpServletRequest req, HttpServletResponse resp,List files,String text)throws Exception {
        System.out.println("进入发朋友圈？");
        //插入标志
        boolean flag = false;
        //存取上传人id
        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        Integer userId = user.getId();
        System.out.println("用户id："+userId);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (int i = 0; i < files.size(); i++) {
            //从集合取出文件
            FileItem item = (FileItem) files.get(i);
            //获得文件名
            String filename = item.getName();
            if("".equals(text)&& "".equals(filename)){
                req.setAttribute("messageAlert", "文字和照片不能同时为空！");
            }else{
            String substring = null;
                if (!Objects.equals(filename, "")){
                //获取文件后缀
                substring = filename.substring(filename.length() - 3);
                System.out.println(substring);
                    if ("jpg".equals(substring) || "png".equals(substring)) {
                    // 给文件重新命名防止重复
                    String imgName = UUID.randomUUID() + "."+substring;
                    System.out.println(imgName);
                    // 将上传的文件保存到服务器
                    String path="D:\\IDEA2020\\javacode\\RDC_wechat\\web\\images";
                    item.write(new File(path, imgName));
                    // 把服务器中的路径添加到数据库中
                    System.out.println("访问路径：" + imgName);
                    // 将路径保存到数据
                    FileService fileService = new FileServiceImpl();
                    flag = fileService.uploadCircle(userId, text, imgName, ft.format(new Date()));
                        if (flag) {
                            req.setAttribute("messageAlert", "发布成功！");
                        } else {
                            req.setAttribute("messageAlert", "发布失败！");
                        }
                    }else{
                        req.setAttribute("messageAlert", "图片格式不正确！");
                    }
                } else {
                    FileService fileService = new FileServiceImpl();
                    flag = fileService.uploadCircle(userId, text, "",ft.format(new Date()));
                    if (flag) {
                        req.setAttribute("messageAlert", "发布成功！");
                    } else {
                        req.setAttribute("messageAlert", "发布失败！");
                    }
                }
            }
        }
        try {
            req.getRequestDispatcher("/jsp/postFriendCircle.jsp").forward(req,resp);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


