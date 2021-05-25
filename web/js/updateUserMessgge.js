var userAge = null;
var userSex = null;
var userBirth = null;
var userAddress = null;
var saveBtn = null;

$(function () {

    saveBtn = $("#save");

    saveBtn.on("click",function(){
        //获取年龄
        userAge = $("#inputAge").val();
        console.log(userAge);
        //获取性别
        userSex = $('input[name = "sex"]:checked').val();
        console.log(userSex);
        //获取生日
        userBirth = $("#inputBirth").val();
        console.log(userBirth);
        //获取地址
        userAddress = $("#inputAddress").val();
        console.log(userAddress);
        if(userBirth!==""){
            if(confirm("确定要修改个人信息？")){
                $("#messageFrom").submit();
            }
        }else{
            alert("生日不能为空!")
        }
    });
})