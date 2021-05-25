<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>修改用户密码</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" >
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/modifyPassword.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span4">
        </div>
        <div class="span4">
            <h3>
                修改用户密码
            </h3>
            <form class="form-horizontal" id="userForm" name="userForm" action="${pageContext.request.contextPath}/modify" method="post">
                <input type="hidden" name="method" value="savepwd">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="info">${requestScope.message}</div>
                <div class="control-group">
                    <label class="control-label" for="oldpassword" >原密码:</label>
                    <div class="controls">
                        <input id="oldpassword" name="oldpassword" type="password" placeholder="请输入原密码"/>
                        <span style="color: red; "></span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="newpassword" >新密码:</label>
                    <div class="controls">
                        <input id="newpassword"name="newpassword" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"type="password" placeholder="请输入新密码"/>
                        <span style="color: red; "></span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="rnewpassword">确认密码:</label>
                    <div class="controls">
                        <input id="rnewpassword" name="rnewpassword" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" type="password" placeholder="请确认密码"/>
                        <span style="color: red; "></span>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <button class="btn" name="save" id="save" type="button" >修改</button>
                    </div>
                </div>
            </form>
        </div>
        <br/>
        <div class="span4">
            <a href="../jsp/main.jsp" target="_self"><span style=" float:left;color:#31acfb">返回主菜单<</span></a>
        </div>
    </div>
</div>
</body>
</html>
