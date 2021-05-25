$(function(){
	//通过jquery的class选择器（数组）
	//对每个class为viewUser的元素进行动作绑定（click）

	//拉入黑名单
	$(".blackUser").on("click",function(){
		var userObj = $(this);
		var message = "您确定要将用户【"+userObj.attr("username")+"】拉进黑名单吗？"
		if(confirm(message)===true){
			var con = 0;
			window.location.href="/RDC_wechat_war_exploded/manageFriends?method=blackFriends&uid="+ userObj.attr("userid")+"&con="+con;
		}else {
			return false;
		}
	});

	//修改好友昵称
	$(".postUser").on("click",function(){
		var obj = $(this);
		var icon = obj.attr("icon");
		var code = prompt("请输入您想要修改的好友昵称！");
		if(code!==null){
			var msg = "您确定要修改吗？";
			if (confirm(msg)===true){
				//你也可以在这里做其他的操作
				window.location.href="/RDC_wechat_war_exploded/manageFriends?method=modify&uid="+ obj.attr("userid")+"&name="+code+"&icon="+icon;
			}else{
				return false;
			}
		}else {
			return false;
		}
	});

	//删除用户
	$(".deleteUser").on("click",function(){
		var userObj = $(this);
		var icon = userObj.attr("icon");
		var message = "您确定要删除用户【"+userObj.attr("username")+"】吗？"
		if(confirm(message)===true){
			//你也可以在这里做其他的操作
			window.location.href="/RDC_wechat_war_exploded/manageFriends?method=delFriend&uid="+ userObj.attr("userid")+"&icon="+icon;
		}else {
			return false;
		}
	});

	//拉出黑名单
	$(".outBlackUser").on("click",function () {
		var userObj = $(this);
		var message = "您确定要将用户【"+userObj.attr("username")+"】拉出黑名单吗？"
		if(confirm(message)===true){
			var con = 1;
			window.location.href="/RDC_wechat_war_exploded/manageFriends?method=blackFriends&uid="+ userObj.attr("userid")+"&con="+con;
		}else {
			return false;
		}
	})

	//举报好友(还没开发出来)
	$(".sayToAdmin").on("click",function () {
		var userObj = $(this);
		var message = "您确定要举报用户【"+userObj.attr("username")+"】吗？"
		if(confirm(message)===true){
			alert("暂未开发！")
			return false;
			// window.location.href="/RDC_wechat_war_exploded/manageFriends?method=#";
		}else {
			return false;
		}
	})
});