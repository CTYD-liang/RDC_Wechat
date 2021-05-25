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
    <title>朋友圈</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" >
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js" ></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="right">
    <div class="location" align="center">
        <h2 style="color: #009fe9">你现在所在的位置是:</h2>
        <br/>
        <h4 style="color: red">朋友圈</h4>
        <div align="center">
            <a href="${pageContext.request.contextPath}/jsp/main.jsp" target="_self"><span style="color:#31acfb">返回主菜单<</span></a>
        </div>
    </div>
    <!--用户-->
    <br/>
    <br/>
    <br/>
    <br/>
    <div align="center">
        <c:forEach var="circle" items="${circleList }" varStatus="status">
            <div>
                <div>
                    <%--发表的人的昵称账号或密码--%>
                    <span>${circle.name}</span>
                </div>
                <div>
                    <%--发表的文字--%>
                    <span>${circle.text }</span>
                </div>
                <div>
                    <%--发表的图片--%>
                    <span><img src="${pageContext.request.contextPath}/images/${circle.pictureUrl}" style="width: 500px;height: 300px"></span>
                </div>
                <div>
                    <%--发表的时间--%>
                    <span>${circle.date}</span>
                </div>
                <div>
                    <span><a class="good" href="javascript:;" userid=${circle.id }  username=${circle.name }>点赞</a></span>
                    <span><a class="comment" href="javascript:;" userid=${circle.id }  username=${circle.name }>评论</a></span>
                    <span><a class="delete" href="javascript:;" userid=${circle.id }  username=${circle.name }>删除</a>></span>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>