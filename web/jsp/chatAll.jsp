<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <base target="_blank">
    <title>群聊页面</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/chat.css" />
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" >
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%--显示当前登登录信息--%>
<h3 style="text-align: center" id="username"></h3>
<div>
    <div id="left">
        <h4 id="new"></h4>
        <%--设置一个开始页面--%>
        <div id="content" >

        </div >
        <%--发送消息的界面--%>
        <div id="input">
            <input type="text" id="input_text"  placeholder="在此处输入文字信息"/>
            <br/>
            <br/>
            <button id="submit">发送</button>
        </div>
    </div>
    <div id="right">
        <div id="top">
            <p>在线的好友</p>
            <div id="hylist">

            </div>
        </div>
        <div id="bottom">
            <p>系统广播</p>
            <div id="xtlist">

            </div>
        </div>
        <br/>
        <div>
            <a href="../jsp/main.jsp" target="_self"><span style=" float:left;color:#31acfb">返回主菜单<</span></a>
        </div>
    </div>
</div>
</body>
</html>
