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
    <title>消息通知页面</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" >
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js" ></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%-- 好友操作
    点击显示消息页面的时候从消息表中查找所有未读消息
    对消息进行操作
    同意则将好友id插入好友表
    拒绝则将该消息删除并给对方发送拒绝的消息
    对方可以在自己的消息通知中查看

    还要判断信息是不是验证信息，如果是则有同意或删除选项，
    如果没有则应该只有已读选项
--%>
<div class="right">
    <div class="location" align="center">
        <h2 style="color: #009fe9">消息通知页面:</h2>
        <br/>
        <h4 style="color: red"></h4>
    </div>
    <!--用户-->
    <br/>
    <br/>
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="30%">消息通知</th>
            <th width="50%">用户操作</th>
        </tr>
        <c:forEach var="mes" items="${messagelist }" varStatus="status">
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${mes.icon==1}"><!-- 如果 -->
                            <span>【${mes.fromName}】请求添加你为好友！</span>
                            <br/>
                            <span>【附加信息】${mes.message}</span>
                        </c:when>
                        <c:otherwise>
                            <span>【${mes.fromName}】回复：</span>
                            <br/>
                            <span>【附加信息】${mes.message}</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <%--如果是验证消息，则显示这两个按钮，否则不显示--%>
                    <c:if test="${mes.icon==1}">
                        <span><a class="yes" href="javascript:;" message=${mes.message} fname=${mes.fromName }>同意</a></span>
                        <span><a class="no" href="javascript:;" message=${mes.message} fname=${mes.fromName }>拒绝</a></span>
                    </c:if>
                    <span><a class="del" href="javascript:;" message=${mes.message} fname=${mes.fromName }>已读</a></span>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <h3 style="color: green">${error}</h3>
        </div>
    </div>
</div>
<br/>
<br/>
<div align="center">
    <a href="${pageContext.request.contextPath}/jsp/main.jsp" target="_self"><span style="color:#31acfb">返回主菜单<</span></a>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/myMessage.js"></script>
</body>
</html>
