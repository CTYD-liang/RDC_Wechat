var oldpassword = null;
var newpassword = null;
var rnewpassword = null;
var saveBtn = null;

var oldpasswordFlag;
var newpasswordFlag;
var rnewpasswordFlag;

$(function(){
    oldpassword = $("#oldpassword");
    newpassword = $("#newpassword");
    rnewpassword = $("#rnewpassword");
    saveBtn = $("#save");

    oldpassword.next().html("*");
    newpassword.next().html("*");
    rnewpassword.next().html("*");


    oldpassword.on("blur",function(){
        $.ajax({
            type:"GET",
            url:"/RDC_wechat_war_exploded/modify",
            data:{method:"pwdmodify",oldpassword:oldpassword.val()},
            dataType:"json",
            success:function(data){
                if(data.result === "true"){//旧密码正确
                    oldpassword.next().html("旧密码正确");
                    oldpasswordFlag = true;
                }else if(data.result === "false"){//旧密码输入不正确
                    oldpassword.next().html("旧密码错误");
                    oldpasswordFlag = false;
                }else if(data.result === "sessionerror"){//当前用户session过期，请重新登录
                    oldpassword.next().html("当前用户session过期，请重新登录");
                    oldpasswordFlag = false;
                }else if(data.result === "error"){//旧密码输入为空
                    oldpassword.next().html("旧密码输入为空");
                    oldpasswordFlag = false;
                }
            },
            error:function(data){
                //请求出错
                oldpassword.next().html("请求出错");
                oldpasswordFlag = false;
            }
        });
    }).on("focus",function(){
        oldpassword.next().html("请输入原密码");
    });

    newpassword.on("focus",function(){
        newpassword.next().html("密码必须为数字字母或小数点");
    }).on("blur",function(){
        if(newpassword.val() !== ""){
            newpassword.next().html("密码合法");
            newpasswordFlag = true;
        }else{
            newpassword.next().html("密码不能为空");
            newpasswordFlag = false;
        }
    });

    rnewpassword.on("focus",function(){
        rnewpassword.next().html("请输入与上面一致的密码");
    }).on("blur",function(){
        if(rnewpassword.val() !== "" && newpassword.val() === rnewpassword.val()){
            rnewpassword.next().html("密码一致");
            rnewpasswordFlag = true;
            return true;
        }else{
            rnewpassword.next().html("密码有误");
            rnewpasswordFlag = false;
        }
    });


    saveBtn.on("click",function(){
        if(oldpasswordFlag===true&&newpasswordFlag===true&&rnewpasswordFlag===true){
            if(confirm("确定要修改密码？")){
                $("#userForm").submit();
            }
        }else{
            alert("输入有误!")
        }
    });
});