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
    <title>我的微信主页</title>

    <!-- Bootstrap -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/web/images/favicon.ico">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" >

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js" ></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
    var username;
    var userRole;
    $(function () {
        $.ajax({
            url: "/RDC_wechat_war_exploded/SendChat",
            type: "get",
            dataType: "json",
            success: function (res) {
                //获取到了用户名
                username = res.userName;
                userRole = res.role;
                //显示在线信息
                $("#welcome").html( userRole+"：" + username+ "  <span style='color: black'>欢迎你!</span>");
            },
            //同步请求，只有上面好了才会接着下面
            async: false
        });
    })

    $(function () {
        //退出登录
        $(".logout").on("click",function(){
            var userObj = $(this);
            var message = "您确定要退出登录吗？"
            if(confirm(message)===true){
                window.location.href="/RDC_wechat_war_exploded//logOut.do";
            }else {
                return false;
            }
        });
    })
</script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <br/>
            <a href="headPicture.jsp"><img alt="头像" src="${pageContext.request.contextPath}/showImg?method=showPicture" class="img-circle" style="float: right;width: 100px;height: 100px" /></a>
            <p id="welcome">

            </p>
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#" class="disabled">首页</a>
                </li>
                <li>
                    <a href="#" data-toggle="dropdown" class="dropdown-toggle">聊天吧</a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="chat.jsp">在线好友</a>
                        </li>
                        <li>
                            <a href="chatAll.jsp">发起群聊</a>
                        </li>
                    </ul>
                </li>
                <li >
                    <a href="#" data-toggle="dropdown" class="dropdown-toggle">朋友圈</a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/showImg?method=showCircle">查看圈圈</a>
                        </li>
                        <li>
                            <a href="postFriendCircle.jsp">我要发布</a>
                        </li>
                    </ul>
                </li>
                <li >
                    <a href="#" data-toggle="dropdown" class="dropdown-toggle">通讯录</a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/jsp/addFriends.jsp">添加好友</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/manageFriends?method=query">我的好友</a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown pull-right">
                    <a href="#" data-toggle="dropdown" class="dropdown-toggle">我的</a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="modifyUserMessage.jsp">修改个人信息</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/manageFriends?method=requestPage">通知消息</a>
                        </li>
                        <li>
                            <a href="modifyPassword.jsp">修改密码</a>
                        </li>
                        <li class="divider">
                        </li>
                        <li>
                            <a class="logout" target="_self" style="color: deepskyblue" href="#">退出登录</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <br/>
            <label style="color: #009fe9">关注我们</label>
            <br/>
            <div class="carousel slide" id="carousel-439447">
                <ol class="carousel-indicators">
                    <li data-slide-to="0" data-target="#carousel-439447">
                    </li>
                    <li data-slide-to="1" data-target="#carousel-439447" class="active">
                    </li>
                    <li data-slide-to="2" data-target="#carousel-439447">
                    </li>
                </ol>
                <div class="carousel-inner">
                    <div class="item">
                        <img alt="" src="../images/500.jpg" />
                        <div class="carousel-caption">
                            <h4>
                                First Thumbnail label
                            </h4>
                            <p>
                                Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
                            </p>
                        </div>
                    </div>
                    <div class="item active">
                        <img alt="" src="../images/500.jpg" />
                        <div class="carousel-caption">
                            <h4>
                                Second Thumbnail label
                            </h4>
                            <p>
                                Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
                            </p>
                        </div>
                    </div>
                    <div class="item">
                        <img alt="" src="../images/404.jpg" />
                        <div class="carousel-caption">
                            <h4>
                                Third Thumbnail label
                            </h4>
                            <p>
                                Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
                            </p>
                        </div>
                    </div>
                </div> <a class="left carousel-control" href="#carousel-439447" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-439447" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
