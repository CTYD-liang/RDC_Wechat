<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>忘记密码</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/password.css" />
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
        <div class="formBox">
            <h3>登录密码重置</h3>
            <ul>
                <li class="mainCol firLi">&gt;身份验证</li>
                <li class="mainCol">&gt;登录密码重置</li>
                <li class="lastLi">&gt;重置完成</li>
            </ul>
            <img src="images/line2.png" />
            <label class="e0">${requestScope.cc_error}</label>
            <form action=" ${pageContext.request.contextPath}/forgetCode.do" method="post" onsubmit="return pass();">
            <div class="itembox itembox_2">
                <label>邮箱验证码 &nbsp;:</label>
                <input name="mailCode" id="code1" type="text"  placeholder="请输入邮箱验证码" class="yzm"></span>
                <div class="yzmbox2" onclick="window.location.href='${pageContext.request.contextPath}/common/password.jsp'" >上一步</div>
            </div>
            <div class="itembox itembox_2">
                <label>登录密码&nbsp;:</label>
                <input name="firstcode" id="code2" type="password" placeholder="请输入新登录密码"></span>
            </div>
            <div class="itembox itembox_2">
                <label>确认密码&nbsp;:</label>
                <input  id="code3"type="password" placeholder="请再次输入新密码"></span>
            </div>
            <div class="itembox itembox_2">
                <label>验证码&nbsp;:</label>
                <input name="checkCode" id="code4" type="text" placeholder="请输入验证码" class="yzm"></span>
                <div class="yzmbox">
                    <img src="<%=request.getContextPath() %>/image" id="picture" alt="验证码" onclick="Change();"/>
                </div>
            </div>
             <div class="btnBox">
                 <button >重置</button>
             </div>
                <script type="text/javascript">
                    function pass() {
                        var mainCode = document.getElementById('code1');
                        var firstcode = document.getElementById('code2');
                        var code = document.getElementById('code3');
                        var code1 = document.getElementById('code4');
                        if(mainCode.value===""||firstcode.value===""||code.value===""||code1.value===""){
                            alert("验证码或密码不能为空！")
                            return false;
                        }else {
                            if (firstcode.value===code.value){
                                console.log(mainCode.value);
                                console.log(firstcode.value);
                                console.log(code.value);
                            return true;
                            }else {
                                alert("密码不一致，请重新输入！")
                                return false;
                            }
                        }
                    }
                </script>
            </form>
        </div>
    </div>
</div>
</body>
</html>
