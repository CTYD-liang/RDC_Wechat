
//发送给谁的信息
var toName;
//发送给谁的id
var toId;
//用户的没名字
var username;
//用户id
var userid;
//用户的角色
var userRole;
//所有好友的id和用户名称集合
var map;
//获取用户名和用户的角色
//点击好友名称会进行的操作
function onlineChat(name,id){
    console.log("进入点击方法")
    toName = name;
    toId = id;
    console.log(toName);
    console.log(toId);
    // 清空聊天区
    $("#content").html("");
    $("#new").html("当前正与"+toName+"聊天");
    //sessionStorage
    var chatdata = sessionStorage.getItem(toName);
    if (chatdata != null){
        $("#content").html(chatdata);
    }
}

$(function () {
    $.ajax({
        url:"/RDC_wechat_war_exploded/SendChat",
        type:"get",
        dataType:"json",
        success:function (res) {
            //获取到了用户名
            username = res.userName;
            userRole = res.role;
            userid = res.id;
            //显示在线信息
            $("#username").html( userRole +":" + username +"<span style='color: red'>离线</span>");
        },
        //同步请求，只有上面好了才会接着下面
        async:false
    });

    //获取该用户的所有好友id和用户名或者昵称
    $.ajax({
        url:"/RDC_wechat_war_exploded/chatTogether",
        type:"get",
        dataType:"json",
        success:function (res) {
            //返回一个json字符串
            //获取服务端推送过来的消息(字符串)
            map= res
            console.log("--------")
            console.log(map);
            console.log("--------")
        },
        //同步请求，只有上面好了才会接着下面
        async:false
    });

    var ws = new WebSocket("ws://localhost:8080/RDC_wechat_war_exploded/chat");
    //给ws绑定事件
    ws.onopen = function (ev) {
        //在线
        $("#username").html( userRole +":" + username +"<span <span style='color: green'>在线</span>");
    }

    //接收服务端推送过来的消息
    ws.onmessage = function (evt) {
        //获取服务端推送过来的消息(字符串)
        var datastr = evt.data;
        //将datastr转换为json对象
        var res = JSON.parse(datastr);
        //判断是否是系统消息
        if(res.system){
            //好友列表展示
            //系统广播展示
            //获取在线好友的列表 一个集合
            var names = res.message;
            var userlistStr = "";
            var broadcastListStr = "";
            for (var name of names){
                console.log(name);
                for (var key in map) {
                    if (name===key){
                        userlistStr += "<a onclick='onlineChat(\""+map[key]+"\",\""+key+"\")'>" + map[key] + "</a></br>";
                        broadcastListStr += "<p>你的好友" +map[key] + "上线了</p>";
                    }
                }
            }
            //渲染好友列表和系统广播
            $("#hylist").html(userlistStr);
            $("#xtlist").html(broadcastListStr);
        }else {
            //不是系统消息
            //将服务端推送的消息进行展示
            //res.message就是一条消息
            var str = "<span id='mes_left' style='color: #009fe9'>"+ res.message +"</span></br>";
            if (toId === res.fromName){
                $("#content").append(str);
            }
            var chatdata = sessionStorage.getItem(res.fromName);
            if (chatdata !== null){
                str = chatdata + str;
            }
            sessionStorage.setItem(res.fromName,str);
        }
    }

    ws.onclose = function (ev) {
        $("#username").html("用户："+ username +"<span>下线</span>");
    }


    //点击发送消息会进行的操作
    $("#submit").click(function () {
        //获取输入的内容
        var data = $("#input_text").val();
        //清空输入区内容
        $("#input_text").val("");
        var json = {"toName": toId ,"message": data};
        //要是好友不为空,就将数据展示在聊天区
        var str = "<span id='mes_right' style='color: red'>"+ data +"</span></br>";
        var chatData = sessionStorage.getItem(toId);
        if(toId!==undefined){
            $("#content").append(str);
        }
        if (chatData !== null){
            str += chatData;
        }
        sessionStorage.setItem(toId,str);
        //将发送数据给服务端
        ws.send(JSON.stringify(json));
    })
})