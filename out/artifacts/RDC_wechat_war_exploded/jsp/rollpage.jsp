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
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/rollpage.js"></script>
</head>
<body>
 		<div class="page-bar">
			<ul class="page-num-ul clearfix">
				<%--5条记录/页面大小3/第一页/总共2页--%>
				<li>共${param.totalCount }条记录&nbsp;&nbsp; ${param.currentPageNo }/${param.totalPageCount }页</li>
				<c:if test="${param.currentPageNo > 1}">
					<a href="javascript:page_nav(document.forms[0],1);">首页</a>
					<a href="javascript:page_nav(document.forms[0],${param.currentPageNo-1});">上一页</a>
				</c:if>
				<c:if test="${param.currentPageNo < param.totalPageCount }">
					<a href="javascript:page_nav(document.forms[0],${param.currentPageNo+1 });">下一页</a>
					<a href="javascript:page_nav(document.forms[0],${param.totalPageCount });">最后一页</a>
				</c:if>
				&nbsp;&nbsp;
			</ul>
		 <span class="page-go-form"><label>跳转至</label>
	     <input type="text" name="inputPage" id="inputPage" class="page-key" />页
	     <button type="button" class="page-btn" onClick='jump_to(document.forms[0],document.getElementById("inputPage").value)'>GO</button>
		</span>
		</div> 
</body>
</html>