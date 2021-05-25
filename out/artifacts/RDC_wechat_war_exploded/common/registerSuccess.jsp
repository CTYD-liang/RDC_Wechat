<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>注册</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link type="text/css" rel="stylesheet" href="../css/zhuce.css" />
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
        <div class="main_left">
            <img src="../images/zhuce-over-3.png" class="theimg"/>
            <img src="../images/zhuce-over-2.png" class="secimg"/>
            <img src="../images/zhuce-over-1.png" class="firimg"/>
        </div>
        <div class="main_right">
            <div class="main_r_up">
                <img src="../images/user.png" />
                <div class="pp">注册</div>
            </div>
            <div class="sub"><p>已经注册？<a href="../login.jsp"><span class="blue">请登录</span></a></p></div>
            <div>
                <div class="font24"><span class="blue" style=" padding-right:20px">-^0^-</span>注册成功！</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
