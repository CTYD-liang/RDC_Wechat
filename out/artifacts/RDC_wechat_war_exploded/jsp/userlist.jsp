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
    <title>好友管理页面</title>
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
                <h4 style="color: red">用户管理页面</h4>
				<div align="center">
				<a href="${pageContext.request.contextPath}/manageFriends?method=allBlackFriend" target="_self"><span style="color:black">查看黑名单</span></a>
				</div>
            </div>
            <div class="search">
           		<form method="get" action="${pageContext.request.contextPath }/manageFriends">
					<input name="method" value="query" class="input-text" type="hidden">
					 <span>微信号或名称：</span>
					 <%--搜索框--%>
					 <input name="queryname" class="input-text"	type="text"  placeholder="支持模糊查询">
					 <%--保证进入查询的时候是第一页--%>
					 <input type="hidden" name="pageIndex" value="1"/>
					 <input	value="查 询" type="submit" id="searchbutton">
					 <a href="${pageContext.request.contextPath}/manageFriends?method=query" target="_self"><span style="color:red">退出搜素</span></a>
				</form>
            </div>
            <!--用户-->
			<br/>
			<br/>
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="20%">用户账号</th>
                    <th width="20%">用户昵称</th>
                    <th width="10%">年龄</th>
                    <th width="10%">性别</th>
                    <th width="10%">生日</th>
                    <th width="10%">地址</th>
                    <th width="20%">操作</th>
                </tr>
				<c:forEach var="user" items="${userList }" varStatus="status">
					<tr>
						<td>
						<span>${user.userCode }</span>
						</td>
						<td>
						<span>${user.userName }</span>
						</td>
						<td>
                        <span>${user.age}</span>
						</td>
						<td>
						<span>${user.sex}</span>
						</td>
						<td>
						<span>${user.birthday}</span>
						</td>
						<td>
						<span>${user.address}</span>
						</td>
						<td>
						<span><a class="blackUser" href="javascript:;" userid=${user.id } icon="0" username=${user.userName }>拉入黑名单</a></span>
						<span><a class="postUser" href="javascript:;" userid=${user.id } icon="0" username=${user.userName }>昵称备注</a></span>
                        <span><a class="deleteUser" href="javascript:;" userid=${user.id } icon="0" username=${user.userName }>删除好友</a>></span>
                        <span><a class="sayToAdmin" href="javascript:;" userid=${user.id } icon="0" username=${user.userName }>举报好友</a>></span>
						</td>
					</tr>
				</c:forEach>
			</table>
			<input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
			<br/>
			<br/>
			<br/>
		  	<c:import url="/jsp/rollpage.jsp">
				<%--总记录--%>
	          	<c:param name="totalCount" value="${totalCount}"/>
				<%--现在的页数--%>
	          	<c:param name="currentPageNo" value="${currentPageNo}"/>
				<%--总页数--%>
	          	<c:param name="totalPageCount" value="${totalPageCount}"/>
          	</c:import>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/userlist.js"></script>
</body>
</html>