//document.forms[0].submit提交的地址即对应的action地址如果想要改变，可以使用
// document.forms[0].action = theUrl;

function page_nav(frm,num){
		//访问表单名称为pageIndex的一个属性并赋值
		frm.pageIndex.value = num;
		//提交表单
		frm.submit();
}

function jump_to(frm,num){
    //alert(num);
	//验证用户的输入
	var regexp=/^[1-9]\d*$/;
	var totalPageCount = document.getElementById("totalPageCount").value;
	//alert(totalPageCount);
	if(!regexp.test(num)){
		alert("请输入大于0的正整数！");
		return false;
	}else if((num-totalPageCount) > 0){
		alert("请输入小于总页数的页码");
		return false;
	}else{
		page_nav(frm,num);
	}
}