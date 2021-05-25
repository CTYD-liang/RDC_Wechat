//查看黑名单
$(function(){
    //通过jquery的class选择器（数组）
    //对每个class为viewUser的元素进行动作绑定（click）

    //发送好友请求
    $(".postUser").on("click",function(){
        var obj = $(this);
        var icon = obj.attr("icon");
        var code = prompt("请输入您想要发送的信息！");
        if(code!==null){
            var msg = "您确定要发送吗？";
            if (confirm(msg)===true){
                //你也可以在这里做其他的操作
                window.location.href="/RDC_wechat_war_exploded/manageFriends?method=sendRequest&uid="+ obj.attr("userid")+"&message="+code;
            }else{
                return false;
            }
        }else {
            return false;
        }
    });
});