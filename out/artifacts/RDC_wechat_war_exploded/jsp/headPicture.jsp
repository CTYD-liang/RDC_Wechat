<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>上传头像</title>
    <!-- Bootstrap -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" >

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js" ></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div align="center">
    <h1 style="color: deepskyblue">点击选择你想要上传的头像</h1>
    <br/>
    <form action="${pageContext.request.contextPath}/upload" method="post" target="_self" enctype = "multipart/form-data" >
        <input type="hidden" name="method" value="uploadHeadPicture">
        <div class="info" style="color: blue">
            ${requestScope.messAlert}
        </div>
        <br/>
        <br/>
        <p>
            <tr>
                <td>选择上传的图片：</td><span style="color:red;">暂时只支持jpg和png格式</span>
                <br/>
                <br/>
                <td><input id="file1" type="file" name="Filename">
                </td>
            </tr>
            <br/>
            <br/>
            <tr>
                <td align="center" colspan="2">
                    <input id="button" type="submit" value="上传">
                </td>
            </tr>
        </p>
    </form>
</div>
<br/>
<br/>
<br/>
<div align="center">
<%--取图片的查询条件--%>
    <img style="width: 120px;height: 120px" alt="头像为空" src="${pageContext.request.contextPath}/showImg?method=showPicture">
</div>
<br/>
<br/>
<br/>
<div align="center">
    <a href="${pageContext.request.contextPath}/jsp/main.jsp" target="_self"><span style=" color:#31acfb">返回主菜单</span></a>
</div>
</body>
</html>
