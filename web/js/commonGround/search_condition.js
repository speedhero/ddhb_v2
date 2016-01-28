/**
 * 新的小区,二手房,租赁 的查询条件，公用的JQuery
 */
$(function(){
	//选中下拉框  查询
	$("select").change(function(){
		location.href = $(this).find("option:selected").val();
	});
	$(".itemMenu").each(function(){
		$(this).click(function(){
			//改变样式
			$(".itemMenu").removeClass("itemMenuSelected");
			$(this).addClass("itemMenuSelected");
			$(".diaplayPrivateDiv").addClass("hiddenPrivateDiv");
			$(".diaplayPrivateDiv").removeClass("diaplayPrivateDiv");
			//显示对应的模块
			var menuid = $(this).attr("menuid");
			$("#privateSearchContent" + menuid).removeClass("hiddenPrivateDiv").addClass("diaplayPrivateDiv");
			$("#publicSearchItemContent").css("display", "block");
			
			var menuId = $(this).attr("menuid");
			if(menuId == 50)
				$("#publicSearchItemContent").css("display", "none");
		});
	});
	
	//把多余的搜索条件字符去掉
	var zimu ;
	$(".subFileAndKeyContainer .subFieldsKey").each(function() {
        //如果两个相连的字母是一样的，则删除第二个
		var _zimu = $(this).html();
		if(_zimu == zimu)
			$(this).remove();
		else
			zimu = _zimu;
    });
	
	//手动输入条件,查询
	$(".inputDiv").each(function(){
		var inputDiv = $(this);
		$(this).find(".toSearchIcon").click(function(){
			var minValue = inputDiv.find(".minValue").val()* 1;
			var maxValue = inputDiv.find(".maxValue").val() * 1;
			if (!maxValue){
				alert("最大值,必须要填")
				return false;
			}
			if(!minValue || minValue < 0 )
				minValue = 0;
			if(minValue > maxValue){
				alert("最小值必须小于最大值");
				return false;
			}
			var unit = $(this).attr("unitname");
			var jump = $(this).attr("jump");
			location.href = jump  + unit + minValue + "-" + maxValue;
			//alert(jump + "/" + unit + minValue + "-" + maxValue);
		})
	});
	
	//清除功能
	$("#clearContainer").click(function(){
		var disable = $(this).attr("buttonDisable");
		if(disable == "false"){
			location.href= $(this).attr("jump");
		}
	});
	//分享
	$("#shareContainer").click(function(){
		var disable = $(this).attr("buttonDisable");
		if(disable == "false"){
			$("#sharePanel").css("display","block");
		}
	});
	//保存条件
	$("#shareContainer").click(function(){
//		var disable = $(this).attr("buttonDisable");
//		if(disable == "false"){
//			$("#sharePanel").css("display","block");
//		}
	});
	//通过焦点进行更新页面
	$("input.yearBefore").blur(function(){
		var value = this.value;
		var reg=/\d\d\d\d/;  
		if(!reg.test(value)){
			alert("请输入正确的年份");
			return false;
		}
		location.href = $(this).attr("jump")+$(this).attr("unitname")+value;			
	});
	
	//保存按钮的单击事件
	$("#saveContainer").click(function() {
		if ($(this).attr("buttondisable") == 'true') {
			return;
		}
		var condition = $(this).attr("condition");
		//房源类别
		var houseType = $(this).attr("type");
		//保存搜索的名称
		var savedString = "";
		//保存搜索的关键字
		var savedField = "";
		//取出所有'本次找房条件'的数据
		$(".columnDiv").each(function() {
			savedString += $(this).html();
			savedString += ",";
			savedField += $(this).attr("searchfield");
		});
		if (savedString == '') {
			alert("您保存的查询条件不能为空");
			return null;
		}
		
		var dataString = {"savedUrl" : condition, "fieldNames": savedString};
		$.ajax({
			type : "POST",
			url : globalUrl + "saveInformation/" + houseType,
			data : dataString,
			dataType: "json",
			success : function(data) {
				var resultJson = data;
				if (resultJson.resultCode == '3'){
					alert("请登录后再保存");
					return;
				}else if (resultJson.resultCode == '2'){
					alert("您已经保存过该搜索条件了");
					return;
				}else if (resultJson.resultCode == '1'){
					alert("保存成功，您可以进入个人中心查看");
					var saveContainer = $("#saveContainer");
					$(saveContainer).addClass("disableOperation");
					$(saveContainer).removeClass("anabledOperation");
					$(saveContainer).attr("buttondisable", "true");
					var saveIcon = $(".saveIcon");
					$(saveIcon).addClass("disabledSaveIcon");
					$(saveIcon).removeClass("saveIcon");
				}
			},
			error : function() {
			}
		});
		
	});
	//----------------
});
