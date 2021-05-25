package com.rdc_wechat.servlet;

import com.rdc_wechat.pojo.Message;
import com.rdc_wechat.pojo.User;
import com.rdc_wechat.service.friend.FriendServiceImpl;
import com.rdc_wechat.util.Constants;
import com.rdc_wechat.util.PageSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 通讯录管理
 * @author 86178
 */
@WebServlet("/manageFriends")
public class UserFriendsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        System.out.println("调用方法："+method);
        if("query".equals(method)){
            //查询不是黑名单的朋友ifBlack!=0-->=1
            this.queryAllFriends(req, resp,method,"展示",Constants.NUMBER_ZERO);
        }else if ("addFriends".equals(method)){
            //进入添加朋友的页面
            this.addFriends(req, resp);
        }else if ("modify".equals(method)){
            //修改是不是黑名单
            this.modifyFriend(req, resp,method);
        }else if("delFriend".equals(method)){
            //删除在不在黑名单里
            this.delFriend(req, resp,method);
        }else if ("blackFriends".equals(method)){
            //拉进黑名单，到底在黑名单你还是在外面
            this.blackFriends(req, resp,method);
        }else if ("allBlackFriend".equals(method)){
            //查询黑名单的朋友,ifBlack!=1-->=0
            this.queryAllFriends(req, resp,method,"展示",Constants.NUMBER_ONE);
        }else if ("sendRequest".equals(method)){
            //同意发送验证请求
            this.sendRequest(req, resp);
        }else if ("requestPage".equals(method)){
            //显示信息页面
            this.requestPage(req, resp);
        }else if ("messageBack".equals(method)){
            //对信息进行操作
            this.messgeBack(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * 查找所有的朋友
     * @param req
     * @param resp
     */
    public void queryAllFriends(HttpServletRequest req, HttpServletResponse resp,String method,String message,int con){
        String queryname = req.getParameter("queryname");
        //获取用户的信息
        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        int userId = 0;
        int totalCount = 0;
        if (user!=null){
            userId = user.getId();
        }else {
            req.getRequestDispatcher("error.jsp");
        }
        List<User> userList= null;
        int currentPageNO = Constants.PAGE_NUMBER;
        int pageSize = Constants.PAGE_SIZE;

        //获得页码且是第一页码
        String pageIndex = req.getParameter("pageIndex");
        FriendServiceImpl friendService = new FriendServiceImpl();
        if (pageIndex!=null){
            currentPageNO = Integer.parseInt(pageIndex);
        }

        //获取总页数
        //非黑名单为-->ifBlack!=0
        //黑名单为-->ifBlack!=1
        if (queryname==null) {
            //获取朋友的总数(上一页和下一页)
            totalCount  = friendService.getFriendCount(userId,con);
        }else if ("".equals(queryname)){
            //如果搜索为空则获取总数
            totalCount  = friendService.getFriendCount(userId,con);
        } else{
            //如果有特定搜索
            totalCount = friendService.getFriendCount(userId,con,queryname,currentPageNO,pageSize);
        }

        //分页支持
        PageSupport support = new PageSupport();
        //设置当前的页数
        support.setCurrentPageNo(currentPageNO);
        //设置当前的总记录数
        support.setTotalCount(totalCount);
        //设置页面大小3
        support.setPageSize(pageSize);
        //设置页面
        support.setTotalPageCount(totalCount);
        //获取总页数
        int totalPageCount = support.getTotalPageCount();

        //控制首页和尾页
        //如果页面小于1，显示第一页
        if (totalPageCount<1){
            currentPageNO = Constants.PAGE_NUMBER;
        }else if (currentPageNO>totalPageCount){
            //要是大于最后一页
            currentPageNO = totalPageCount;
        }

        //查询不是黑名单里的用户
        if (queryname==null) {
            //如果搜索为空则获取总的用户列表
            userList = friendService.getFriendMessage(userId,con,currentPageNO, pageSize);
        }else if ("".equals(queryname)){
            //如果搜索为空则获取总的用户列表
            userList = friendService.getFriendMessage(userId,con,currentPageNO, pageSize);
        } else{
            //如果有特定搜索
            userList = friendService.getFriendMessage(userId,con,queryname, currentPageNO, pageSize);
        }

        req.setAttribute("userList",userList);
        req.setAttribute("totalCount",totalCount);
        req.setAttribute("currentPageNo",currentPageNO);
        req.setAttribute("totalPageCount",totalPageCount);

        //返回前端页面
        try {
            req.setAttribute("error",message+"成功！");
            if ("query".equals(method)){
                req.getRequestDispatcher("/jsp/userlist.jsp").forward(req,resp);
            }else if ("allBlackFriend".equals(method)){
                req.getRequestDispatcher("/jsp/blackFriends.jsp").forward(req,resp);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *设置好友昵称
     * @param req
     * @param resp
     */
    public void modifyFriend(HttpServletRequest req, HttpServletResponse resp,String method){
        //获得修改的昵称
        String id = req.getParameter("uid");
        int fid =Integer.parseInt(id);
        //获得转发页面标记
        String icon = req.getParameter("icon");
        int Icon =Integer.parseInt(icon);
        //如果是1，则是黑名单
        if (Icon==1){
            method = "allBlackFriend";
        }else if (Icon==0){
            //如果是0则不是
            method = "query";
        }
        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        int userId = 0;
        if (user!=null){
            userId = user.getId();
        }else {
            req.getRequestDispatcher("error.jsp");
        }
        String name = req.getParameter("name");
        FriendServiceImpl friendService = new FriendServiceImpl();
        int i = friendService.setFriendName(fid, userId, name);
        if (i==1){
            //从前端获取
            //黑名单ifBlack ！=1 -->=0
            //非黑名单ifBlack ！=0 -->=1
            this.queryAllFriends(req, resp,method,"修改",Icon);
        }else{
            try {
                req.setAttribute("error","修改失败！");
                if ("query".equals(method)){
                    req.getRequestDispatcher("/jsp/userlist.jsp").forward(req,resp);
                }else if ("allBlackFriend".equals(method)){
                    req.getRequestDispatcher("/jsp/blackFriends.jsp").forward(req,resp);
                }
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除好友
     * @param req
     * @param resp
     */
    public void delFriend(HttpServletRequest req, HttpServletResponse resp,String method){
        String id = req.getParameter("uid");
        int fid =Integer.parseInt(id);
        //获得转发页面标记
        String icon = req.getParameter("icon");
        int Icon =Integer.parseInt(icon);
        //如果是1，则是黑名单
        if (Icon==1){
            method = "allBlackFriend";
        }else if (Icon==0){
            //如果是0则不是
            method = "query";
        }
        int userId = 0;
        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        if (user!=null){
            userId = user.getId();
        }else {
            req.getRequestDispatcher("error.jsp");
        }
        FriendServiceImpl friendService = new FriendServiceImpl();
        int i = friendService.delFriend(fid, userId);
        if(i==1){
            this.queryAllFriends(req, resp,method,"删除",Icon);
        }else{
            try {
                req.setAttribute("error","删除失败！");
                if ("query".equals(method)){
                    req.getRequestDispatcher("/jsp/userlist.jsp").forward(req,resp);
                }else if ("allBlackFriend".equals(method)){
                    req.getRequestDispatcher("/jsp/blackFriends.jsp").forward(req,resp);
                }
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 黑名单设置
     * @param req
     * @param resp
     */
    public void blackFriends(HttpServletRequest req, HttpServletResponse resp,String method){
        String id = req.getParameter("uid");
        int fid =Integer.parseInt(id);
        String c = req.getParameter("con");
        int con =Integer.parseInt(c);
        //如果是拉进黑名单，con为0-->也代表此时还不是黑名单
        //拉出黑名单，则con为1-->也代表此时是黑名单
        //数据库你面1不是黑名单，哦、0才是黑名单

        //如果是con，则现在是黑名单，返回也要返回黑名单
        if (con==1){
            method = "allBlackFriend";
        }else if (con==0){
            //如果是0则现在不是黑名单，返回也要返回正常页面
            method = "query";
        }
        int userId = 0;
        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        if (user!=null){
            userId = user.getId();
        }else {
            req.getRequestDispatcher("error.jsp");
        }
        FriendServiceImpl friendService = new FriendServiceImpl();
        int i = friendService.manageBlackFriends(con, fid, userId);
        if(i==1){
            this.queryAllFriends(req, resp,method,"操作",con);
        }else{
            try {
                req.setAttribute("error","操作失败！");
                if ("query".equals(method)){
                    req.getRequestDispatcher("/jsp/userlist.jsp").forward(req,resp);
                }else if ("allBlackFriend".equals(method)){
                    req.getRequestDispatcher("/jsp/blackFriends.jsp").forward(req,resp);
                }
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 添加好友
     * @param req
     * @param resp
     */
    public void addFriends(HttpServletRequest req, HttpServletResponse resp){
        /*
          进入页面什么都没有显示，
          然后按查询方法，显示查询的用户
          显示在页面，进行操作
          首先丛数据库中查找到用户
         */
        String queryname = req.getParameter("queryname");
        System.out.println("查找"+queryname);
        //获取用户的信息
        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        int userId = 0;
        int totalCount = 0;
        if (user!=null){
            userId = user.getId();
        }else {
            req.getRequestDispatcher("error.jsp");
        }
        List<User> userList= null;
        int currentPageNO = Constants.PAGE_NUMBER;
        int pageSize = Constants.PAGE_SIZE;

        //获得页码且是第一页码
        String pageIndex = req.getParameter("pageIndex");
        FriendServiceImpl friendService = new FriendServiceImpl();
        if (pageIndex!=null){
            currentPageNO = Integer.parseInt(pageIndex);
        }
        //获取总的用户列表
        userList = friendService.getUser(queryname,userId,currentPageNO, pageSize);


        //获取不是好友用户的总数


        totalCount = friendService.getNotFriendsCount(userId,queryname);

        System.out.println("总的记录数："+totalCount);
        //分页支持
        PageSupport support = new PageSupport();
        //设置当前的页数
        support.setCurrentPageNo(currentPageNO);
        //设置当前的总记录数
        support.setTotalCount(totalCount);
        //设置页面大小3
        support.setPageSize(pageSize);
        //设置页面
        support.setTotalPageCount(totalCount);
        //获取总页数
        int totalPageCount = support.getTotalPageCount();

        //控制首页和尾页
        //如果页面小于1，显示第一页
        if (totalPageCount<1){
            currentPageNO = Constants.PAGE_NUMBER;
        }else if (currentPageNO>totalPageCount){
            //要是大于最后一页
            currentPageNO = totalPageCount;
        }
        req.setAttribute("userList",userList);
        req.setAttribute("totalCount",totalCount);
        req.setAttribute("currentPageNo",currentPageNO);
        req.setAttribute("totalPageCount",totalPageCount);
        //返回前端页面
        try {
            req.setAttribute("error","展示成功！");
            req.getRequestDispatcher("/jsp/addFriends.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     * @param req
     * @param resp
     */
    public void sendRequest(HttpServletRequest req, HttpServletResponse resp){
        //发送对象的id
        String uid = req.getParameter("uid");
        int tid = Integer.parseInt(uid);
        //验证消息
        String message = req.getParameter("message");
        //用户的id
        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        int fid = user.getId();

        FriendServiceImpl friendService = new FriendServiceImpl();
        //是验证消息，并且还没有读 icon->1 ifRead->0
        int i = friendService.requestMessageIn(message, tid, fid,Constants.NUMBER_ONE,Constants.NUMBER_ZERO);
        if (i==1){
            try {
                req.setAttribute("error","发送成功！");
                req.getRequestDispatcher("/jsp/addFriends.jsp").forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                req.setAttribute("error","发送失败！");
                req.getRequestDispatcher("/jsp/addFriends.jsp").forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 进入消息查询页面
     * @param req
     * @param resp
     */
    public void requestPage(HttpServletRequest req, HttpServletResponse resp){
        //用户的id
        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        int fid = user.getId();
        List<Message> list = null;

        FriendServiceImpl friendService = new FriendServiceImpl();
        list = friendService.requestMessageOut(fid);
        if (list!=null) {
            for (Message l : list) {
                System.out.println(l.getIcon());
                System.out.println(l.getFromName());
                System.out.println(l.getMessage());
            }
            try {
                req.setAttribute("messagelist", list);
                req.setAttribute("error", "消息展示成功！");
                req.getRequestDispatcher("/jsp/myMessage.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                req.setAttribute("messagelist", list);
                req.setAttribute("error", "消息为空！");
                req.getRequestDispatcher("/jsp/myMessage.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 对验证信息进行操作
     * 0.同意
     * 1.拒绝
     * 2.已读
     * @param req
     * @param resp
     */
    public void messgeBack(HttpServletRequest req, HttpServletResponse resp){
        //发送消息的对象姓名
        String tname = req.getParameter("fname");
        //判断操作类型
        String con = req.getParameter("con");
        int i = Integer.parseInt(con);
        //获得短信内容
        String text = req.getParameter("text");
        //用户的id
        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        int fid = user.getId();
        String userName = user.getUserName();

        String message = null;

        FriendServiceImpl friendService = null;

        boolean flag = false;
        if(i==0){
            message = "【"+userName+"】同意了你的好友请求！";
            //同意好友的请求
            friendService = new FriendServiceImpl();
            //获得用户id
            int tid = friendService.getId(tname);
            //将好友信息存进数据库
            int a1 = friendService.addFriends(fid, tid);
            //向好友发送信息 0不是验证消息，0表示没读
            int a2 = friendService.requestMessageIn(message, tid, fid, Constants.NUMBER_ZERO, Constants.NUMBER_ZERO);
            //将消息设置为已读
            int a3 = friendService.setIsRead(fid, text);
            if(a1+a2+a3==3){
                flag = true;
            }
        }else if (i==1){
            //拒绝好友请求
            friendService = new FriendServiceImpl();
            //拒绝理由
            message = req.getParameter("message");
            //获得用户id
            int tid = friendService.getId(tname);
            //向好友发送信息 0不是验证消息，0表示没读
            int a1 = friendService.requestMessageIn(message, tid, fid, Constants.NUMBER_ZERO, Constants.NUMBER_ZERO);
            //将消息设置为已读
            int a2 = friendService.setIsRead(fid, text);
            if(a1+a2==2){
                flag = true;
            }
        }else {
            //将消息设置已读
            friendService = new FriendServiceImpl();
            int a = friendService.setIsRead(fid, text);
            if (a==1){
                flag = true;
            }
        }

        if (flag){
            try {
                req.setAttribute("error","操作成功！");
                req.getRequestDispatcher("/manageFriends?method=requestPage").forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                req.setAttribute("error","操作失败！");
                req.getRequestDispatcher("/manageFriends?method=requestPage").forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
