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
    <script type="text/javascript">
            function check(strEmail) {
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
        <div class="formBox">
            <h3>登录密码重置</h3>
            <ul>
                <li class="mainCol firLi">&gt;身份验证</li>
                <li>&gt;登录密码重置</li>
                <li class="lastLi">&gt;重置完成</li>
            </ul>
            <img src="../images/line.png" />
            <form action="${pageContext.request.contextPath}/sendEmail.do" method="post" onsubmit="return a();">
            <div class="itembox">
                <label>邮箱号&nbsp;:</label>
                <input type="text" name="emailCode" id="emailBox" placeholder="请输入邮箱号"></span>
            </div>
             <div class="btnBox">
                 <button>下一步</button>
             </div>
                <div>
                 <a href="../login.jsp" target="_self"><span style=" float:left;color:#31acfb">返回上一步<</span></a>
                </div>
                <script>
                    function a(){
                        var inputEmail = document.getElementById('emailBox');
                        if(inputEmail.value!==""){
                            var flag = check(inputEmail.value);
                            return flag;
                        }else{
                            alert("邮箱不能为空！");
                            return false;
                        }
                    }
                </script>
            </form>
        </div>
    </div>
</div>
</body>
</html>