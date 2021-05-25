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
    <title>我要发布</title>
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
<br/>
<br/>
<div align="center">
    <a href="${pageContext.request.contextPath}/jsp/main.jsp" target="_self">
        <span style=" color:#31acfb">返回主菜单</span>
    </a>
</div>
<div align="center">
    <h1 style="color: deepskyblue">点击选择你想要发的照片</h1>
    <br/>
    <form action="${pageContext.request.contextPath}/upload" method="post" target="_self" enctype="multipart/form-data" >
        <input type="hidden" id="method" name = "method" value = "postFriendCircle">
        <div class="info" style="color: blue">
            ${requestScope.messageAlert}
        </div>
        <br/>
        <br/>
        <p>
            <tr>
                <td>暂时只能选择一张的图片：</td><span style="color:red;">暂时只支持jpg和png格式</span>
                <br/>
                <br/>
                <td>
                    <input id="file" type="file"  name="Filename">
                </td>
            </tr>
            <br/>
            <tr>
                <td align="center" colspan="2">
                    <label>输入你想要发表的文字</label>
                    <br/>
                    <textarea id="content" name="text" style="resize: none;wrap-option: psychial;width: 500px;height: 100px"></textarea>
                </td>
            </tr>
            <br/>
            <tr>
                <td align="center" colspan="2">
                    <input id="button" type="submit"  value="发表">
                </td>
            </tr>
        </p>
    </form>
</div>
<div align="center">
    <h3 align="left">已发布：</h3>
    <div id="showMe">

    </div>
</div>
</body>
</html>
