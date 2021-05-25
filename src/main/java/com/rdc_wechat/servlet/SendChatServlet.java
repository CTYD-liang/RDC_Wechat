package com.rdc_wechat.servlet;

import com.alibaba.fastjson.JSONArray;
import com.rdc_wechat.pojo.User;
import com.rdc_wechat.service.role.RoleServiceImpl;
import com.rdc_wechat.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 86178
 */

@WebServlet(name="SendChatServlet", urlPatterns="/SendChat")
public class SendChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        //post方法如果不设置,则会出现乱码
        resp.setCharacterEncoding("UTF-8");
        Map<String, String> resultMap = new HashMap<>();
        String userName = (String) req.getSession().getAttribute("userName");
        resultMap.put("userName",userName);
        //获取用户的角色
        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        int userId = user.getId();
        int userRole = user.getUserRole();
        //查找角色名称
        RoleServiceImpl roleService = new RoleServiceImpl();
        String rloeName = roleService.getRloe(userRole);
        String id = String.valueOf(userId);
        resultMap.put("role",rloeName);
        resultMap.put("id",id);
        //写返回的数据
        PrintWriter pw = resp.getWriter();
        //如果昵称不为空则输出昵称
        //否则输出账号
        //写成json格式返回
        pw.print(JSONArray.toJSONString(resultMap));
        pw.flush();
        pw.close();
    }
}
