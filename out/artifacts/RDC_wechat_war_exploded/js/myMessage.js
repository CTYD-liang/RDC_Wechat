$(function(){
    //通过jquery的class选择器（数组）
    //对每个class为viewUser的元素进行动作绑定（click）
    //同意好友请求后将消息改为已读并发送同意消息
    $(".yes").on("click",function(){
        var userObj = $(this);
        var text = userObj.attr("message");
        var message = "您确定要将用户【"+userObj.attr("fname")+"】添加为好友吗？";
        if(confirm(message)===true){
            var con = 0;
            window.location.href="/RDC_wechat_war_exploded/manageFriends?method=messageBack&fname="+ userObj.attr("fname")+"&con="+con+"&text="+text;
        }else {
            return false;
        }
    });

    //拒绝好友请求后删除该消息，并发送绝消息
    $(".no").on("click",function () {
        var obj = $(this);
        var text = obj.attr("message");
        var code = prompt("请输入您拒绝的理由！");
        if(code!==null){
            var msg = "您确定要拒绝用户【"+obj.attr("fname")+"】的好友请求吗？";
            if (confirm(msg)===true){
                var con = 1;
                window.location.href="/RDC_wechat_war_exploded/manageFriends?method=messageBack&fname="+ obj.attr("fname")+"&con="+con+"&message="+code+"&text="+text;
            }else{
                return false;
            }
        }else {
            return false;
        }
    })

    //将消息设置为已读，不发送消息
    $(".del").on("click",function () {
        var userObj = $(this);
        var text = userObj.attr("message");
        var message = "您确定要将消息设置为已读吗？";
        if(confirm(message)===true){
            var con = 2;
            window.location.href="/RDC_wechat_war_exploded/manageFriends?method=messageBack&fname="+ userObj.attr("fname")+"&con="+con+"&text="+text;
        }else {
            return false;
        }
    })
});