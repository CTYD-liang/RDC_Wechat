<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>忘记密码</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link type="text/css" rel="stylesheet" href="../css/password.css" />
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js" ></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var height=$(document).height();
            $('.main').css('height',height);
        })
    </script>
</head>

<body>
<div class="main">
    <div class="main0">
        <div class="formBox">
            <h3>登录密码重置</h3>
            <ul>
                <li class="mainCol firLi">&gt;身份验证</li>
                <li class="mainCol">&gt;登录密码重置</li>
                <li class="mainCol lastLi">&gt;重置完成</li>
            </ul>
            <img src="../images/line3.png" />
            <p class="sub"><span>-^0^-</span>  新登录密码重置成功，请重新登录!</p>
            <div class="btnBox_2">
                <button onclick="window.location.href='../login.jsp'">重新登录</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>