<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>注册</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link type="text/css" rel="stylesheet" href="css/zhuce.css" />
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js" ></script>
    <!--MD5工具类-->
    <script src="https://cdn.bootcss.com/blueimp-md5/2.10.0/js/md5.min.js"></script>
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
    <script type="text/javascript">
        function checkCode(strEmail) {
            if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(strEmail)) {
                alert("邮箱输入格式不正确！")
                return false;
            } else {
                alert("邮箱格式正确！")
                return true;
            }
        }
    </script>
</head>

<body>
<div class="main">
    <div class="main0">
        <div class="main_left">
            <img src="images/zhuce-image-3.png" class="theimg"/>
            <img src="images/zhuce-image-2.png" class="secimg"/>
            <img src="images/zhuce-image-1.png" class="firimg"/>
        </div>
        <div class="main_right">
            <div class="main_r_up">
                <img src="images/user.png" />
                <div class="pp">注册</div>
            </div>
            <div class="sub"><p>已经注册？<a href="login.jsp"><span class="blue">请登录</span></a></p></div>
            <label class="e0">${requestScope.cc_error}</label>
            <form action="${pageContext.request.contextPath}/register.do" method="post" onsubmit="return sub();" >
            <div class="txt txt0">
                <span style="float:left">验证码:</span>
                <input name="checkCode" type="text" placeholder="请输入验证码" class="txtyzmdx"/>
                <img src="${pageContext.request.contextPath}/image" id="picture" class="yzmimg" alt="验证码" onclick="Change();"/>
            </div>
            <div class="txt txt0">
                <span style="letter-spacing:8px;">账号名:</span>
                <input name="userName" id="userName" type="text" class="txtphone" maxlength="10"onkeyup="this.value=this.value.replace(/[^\d]/g,'') " placeholder="请输入最多8位数字"/>
            </div>
            <div class="txt txt0">
                <span style="letter-spacing:4px;">登录密码:</span>
                <input  type="password" id="userPassword" class="txtphone"onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" placeholder="请输入数字或英文或小数点"/>
                <input type="hidden" name="userPassword" id="MD5_userPassword">
            </div>
            <div class="txt txt0">
                <span style="letter-spacing:4px;">用户邮箱:</span>
                <input name="emailBox" id="Email" type="text" class="txtphone" placeholder="请输入正确的邮箱"/>
            </div>
            <div class="txt txt0">
                <a href="login.jsp"><span style=" float:left;color:#31acfb">返回上一步<</span></a>
                <button class="zhucebtn" >确认注册</button>
                <script>
                    function sub() {
                        var username = document.getElementById('userName');
                        var userPassword = document.getElementById('userPassword');
                        var Email = document.getElementById('Email');
                        var md5_password = document.getElementById('MD5_userPassword');
                        if(userPassword.value===""||Email.value===""||username.value===""){
                            alert("用户名、密码、邮箱均不能为空，请重新输入");
                            return false;
                        }else {
                            var flag = checkCode(Email.value);
                            if(flag===true){
                                md5_password.value = md5(userPassword.value);
                                return true;
                            }else {
                                return false;
                            }
                        }
                    }
                </script>
            </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
