<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>RDC_wechat登录页面</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link type="text/css" rel="stylesheet" href="css/login.css" />
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js" ></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var height=$(document).height();
            $('.main').css('height',height);
        })
    </script>
    <%--切换验证码,加载完成后点击验证码图片--%>
    <script type="text/javascript">
        function Change() {
            var img = document.getElementById("picture")
            //设置时间戳
            var date = new Date().getTime();
            img.src="<%=request.getContextPath() %>/image?"+date;
        }
    </script>
</head>
<body>
<div class="main">
    <div class="main0">
        <div class="main_left">
            <img src="${pageContext.request.contextPath}/images/login-image-3.png" class="theimg" alt="R"/>
            <img src="${pageContext.request.contextPath}/images/login-image-2.png" class="secimg" alt="D"/>
            <img src="${pageContext.request.contextPath}/images/login-image-1.png" class="firimg" alt="C"/>
        </div>
        <div class="main_right">
            <div class="main_r_up">
                <img src="images/user.png" alt=" "/>
                <div class="pp">RDC_WECHAT登录</div>
            </div>
            <div class="sub"><p>还没有账号？<a href="register01.jsp"><span class="blue">立即注册</span></a></p></div>
            <label class="e0">${requestScope.cc_error}</label>
            <%--表单提交到loginservlet界面--%>
            <form action="<%=request.getContextPath() %>/login.do" method="post">
                <div class="txt">
                  <span style="letter-spacing:8px;">用户名:</span>
                    <input name="username" type="text" class="txtphone" placeholder="请输入正确的账号"/>
                </div>
                <div class="txt">
                   <span style="letter-spacing:4px;">登录密码:</span>
                    <input  name="passWord" type="password" class="txtphone" placeholder="请输入登录密码"/>
                </div>
                <div class="txt">
                   <span style=" float:left;letter-spacing:8px;">验证码:</span>
                    <input name="checkCode" type="text" class="txtyzm" placeholder="请输入页面验证码"/>
                <img src="${pageContext.request.contextPath}/image" id="picture" class="yzmimg" alt="验证码" onclick="Change();"/>
                验证码看不清可点击更换
                </div>
                <div class="xieyi">
                    <input name="check"  type="checkbox" value= "1" />
                管理员 <a href="common/password.jsp"><span class="blue" style=" padding-left:130px; cursor:pointer">忘记密码?</span></a>
                </div>
                <input type="submit" value="登录" class="xiayibu">
            </form>
        </div>
    </div>
</div>
</body>
</html>
