var userName = null;
var saveBtn = null;
var Flag;

$(function(){
    userName = $("#userName");
    saveBtn = $("#btnUserName");

    userName.next().html("*");

    userName.on("blur",function(){
        $.ajax({
            type:"GET",
            url:"/RDC_wechat_war_exploded/modify",
            data:{method:"confirmUserName",userName:userName.val()},
            dataType:"json",
            success:function(data){
                if(data.result === "userCanUse"){//昵称可用
                    userName.next().html("昵称可用");
                    Flag = true;
                }else if(data.result === "noUserName"){//昵称为空
                    userName.next().html("昵称不可以为空");
                    Flag = false;
                }else if(data.result === "sessionError"){//当前用户session过期，请重新登录
                    userName.next().html("当前用户session过期，请重新登录");
                    Flag = false;
                }else if(data.result === "alreadyName"){//昵称已存在
                    userName.next().html("昵称已存在，请重新输入");
                    Flag = false;
                }
            },
            error:function(data){
                //请求出错
                userName.next().html("请求出错");
                Flag = false;
            }
        });
    }).on("focus",function(){
        userName.next().html("请输入新的昵称");
    });


    saveBtn.on("click",function(){
        if(Flag === true){
            if(confirm("确定要修改昵称？")){
                $("#nameFrom").submit();
            }
        }else{
            console.log(Flag);
            alert("输入有误!")
        }
    });
});