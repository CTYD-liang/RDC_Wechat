<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>500错误页面</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}web/images/favicon.ico">
</head>
<body>
    <img src="${pageContext.request.contextPath}web/images/500.jpg" alt="500错误!">
    <br/>
   <%=exception.getMessage()%>
</body>
</html>
