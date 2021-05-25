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
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>修改个人信息</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" >
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/confirmUserName.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/updateUserMessgge.js"></script>
</head>
<body>
<div>
    <h1 style="text-align: center;color: deepskyblue">
        欢迎来到个人信息修改页面!
    </h1>
</div>
<div class="container">
    <div class="row">
        <div class="span8">
            <form method="post" action="${pageContext.request.contextPath}/modify" id="nameFrom" name="nameFrom">
                <input type="hidden" name="method" value="updateUserName">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <fieldset>
                    <legend>设置用户昵称</legend>
                    <label>昵称:</label>
                    <div class="info" style="color: red">${requestScope.message}</div>
                    <br/>
                    <input type="text" name="userName" id="userName" />
                    <span style="color: red; "></span>
                    <br/>
                    <br/>
                    <p>
                        <button type="button" id="btnUserName" class="btnUserName">修改</button>
                    </p>
                </fieldset>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="span6">
            <h3>
                修改用户个人信息
            </h3>
            <form class="form-horizontal" id="messageFrom"  name="messageFrom" action="${pageContext.request.contextPath}/modify" method="post">
                <input type="hidden" name="method" value="updateUserMessage">
                <div class="info" style="color: blue">${requestScope.messagetip}</div>
                <div class="control-group">
                    <label class="control-label" for="inputAge" >年龄</label>
                    <div class="controls">
                        <input id="inputAge" type="text" name="inputAge" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="最多输入三个数字"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" >性别</label>
                    <div class="controls">
                        <label class="checkbox"><input type="radio" name="sex" id="bsex" value="男" checked="checked"/> 男</label>
                        <label class="checkbox"><input type="radio" name="sex" id="gsex" value="女"/> 女</label>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="inputBirth" >生日:不能为空!</label>
                    <div class="controls">
                        <input id="inputBirth" type="date" name="inputBirth" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="inputAddress" >地址</label>
                    <div class="controls">
                        <input id="inputAddress" type="text" name="inputAddress" />
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <button type="button" style="color: #009fe9;border-color: red" id="save" name="save" class="btn">修改</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <br/>
    <div>
        <a href="${pageContext.request.contextPath}/jsp/main.jsp" target="_self"><span style=" float:left;color:#31acfb">返回主菜单<</span></a>
    </div>
</div>
</body>
</html>
