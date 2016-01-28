var postUrl = "";
var isopen = false;
/*
var searchMap = new Map();
function initSearchMap(){
	searchMap.put("type", 0);
	searchMap.put("order", "Asc");
	searchMap.put("sort", "sortIndex");
	searchMap.put("ispage", 1);
}
*/

function hidediv(){
	$(".floatdiv").first().css("display", "none");
	isopen = false;
}

function sortselect(actionUrl){
	var sortvalue = $("#sortmodule").children('option:selected').attr("sort");
	var ordervalue = $("#sortmodule").children('option:selected').attr("order");
    sort = sortvalue;
    order = ordervalue;
	setJSONValue("sort", sortvalue);
    setJSONValue("order", ordervalue);
    showSelectedField(actionUrl);
}
/*

function setJSONValue(key, newValue){
	searchMap.put(key, newValue);
}

function showSelectedField(actionUrl){
	var keyArray = searchMap.keys();
	if (keyArray.length > 0){
		postUrl = "";
		for (var i = 0; i < keyArray.length; i++){
			postUrl += keyArray[i] + "=" + searchMap.get(keyArray[i]);
			if (i < keyArray.length - 1){
				postUrl += "&";
			}
		}
	}
	postDataByURL2(actionUrl , postUrl, "changelist");
}
*/

/*

function startShare(show){
	var urlToShare = shareCondition(show);
    $("#bdshare").attr("data", "{'url':'" + urlToShare + "'}");
}

function shareCondition(show){
	var currentURL = document.location.toString();
	
	if (currentURL.indexOf("welcome.show") > 0){
		currentURL = currentURL.replace("welcome.show", show);
	}
	
	currentURL = currentURL.split("&")[0];
	
	
	var keyArray = searchMap.keys();
	if (keyArray.length > 0){
		var postUrl = "";
		for (var i = 0; i < keyArray.length; i++){
			if (keyArray[i] != 'ispage'){
				postUrl += keyArray[i] + "=" + searchMap.get(keyArray[i]);
			}else{
				postUrl += keyArray[i] + "=0";
			}
			if (i < keyArray.length - 1){
				postUrl += "&";
			}
		}
	}
	
	$("select[selectId]").each(function(){
		var columnName = $(this).attr("columnName");
		var fieldValue = $(this).val();
		if (fieldValue != -1){
			if (postUrl === undefined){
				postUrl = columnName + "=" + fieldValue;
			}else{
				postUrl += "&";
				postUrl += columnName + "=" + fieldValue;
			}
		}
	});
	
	var returnedUrl = "";
	var dataString = {"sharedUrl": currentURL + "&" + postUrl};
	$.ajax({
		type : "POST",
		url : "toShortUrl.show?actionMethod=shortUrl",
		data : dataString,
		dataType: "json",
		async: false,
		success : function(data) {
			if (data.operationStatus){
				returnedUrl = data.returnedUrl;
			}else{
				alert("分享失败，请联系管理员！");
			}
		},
		error : function() {
			alert("分享失败，请联系管理员！");
		}
	});
	return returnedUrl;
}
*/

function showSearchField(actionUrl){
	var keyArray = searchMap.keys();
	if (keyArray.length > 0){
		postUrl = "";
		for (var i = 0; i < keyArray.length; i++){
			postUrl += keyArray[i] + "=" + searchMap.get(keyArray[i]);
			if (i < keyArray.length - 1){
				postUrl += "&";
			}
		}
	}
	postDataByURL2(actionUrl , postUrl, "communityRentSecond");
}

function changeCompareMenu(obj){
	$(".compareMenuSelected").first().removeClass("compareMenuSelected");
	$(obj).addClass("compareMenuSelected");
	$("#hiddenCompareDiv").css("display", "block");
	if ($(obj).attr("id") == 'compareMenu'){
		$("#compareContentContainer").css("display", "block");
		$("#historyListContainer").css("display", "none");
	}else if ($(obj).attr("id") == 'historyDiv'){
		$("#compareContentContainer").css("display", "none");
		$("#historyListContainer").css("display", "block");
	}
}


//Modify by hejianbo 单击链接排序
function orderRule(actionUrl, orderType, number){
	setJSONValue("sort", orderType);
    setJSONValue("order", "asc");
    //临时弄了一个选中链接的颜色 和没选中时的CSS样式
    var idValue = '#orderRules' + number;
    for(var i = 0;i<5;i++){
    	var idValues =  '#orderRules' + i;
   if(idValue == idValues ){
    	$(idValues).css('color','red');
    	$(idValues).addClass("one");
    }
    else{
    	$(idValues).css('color','black');
    	$(idValues).removeClass("one");
    }
//    添加样式
//    $("#target").addClass("newClass");
//    移除CSS样式
//    $("#target").removeClass("oldClass");
//    添加或者移除CSS类
//    	$("#target").toggleClass("newClass")
}
    showSelectedField(actionUrl);
//	postDataByURL2(actionUrl , postUrl,"changelist");
}

function nextPageInformation(actionUrl,currentPage){
	setJSONValue("currentPage", currentPage);
	showSelectedField(actionUrl);
}
//下拉框搜索
function searchSelectHouse(actionUrl, key, value){
	var sumNumber = $(".selectedSearchItemDiv[name='"+key+"']").length;
	if( sumNumber > 0 )
	//先删除同个类型下的查询条件 比如已查询出房龄，再次查询房龄的其他条件
		removeSelectCss(key,actionUrl);
	setJSONValue(key, value);
	var txt = $("select[name='" +key + "'] ").find("option:selected").text();
	$("#allSelectedItemsDiv").append("<div class='selectedSearchItemDiv' onclick= removeSelectCss('" + key + "','" + actionUrl
			+ "'); name='" + key 
			+ "' ><div class='columnDiv'" 
			+  " fieldvalue='" + value
			+ "' selectedColumn='" + key 
			+ "'>" + txt + "</div><div class='removeColumnDiv'  " 
			+ " columnname='" + key + "' fieldvalue='" + value + "' ismulty='false' ></div><div class='clearDiv'></div></div>");
	showSelectedField(actionUrl);
}

//删除select所选的内容
function removeSelectCss(key, actionUrl){
	$(".selectedSearchItemDiv[name='"+key+"']").remove();
	setJSONValue(key, "");
	showSelectedField(actionUrl);
	$("select[name='" +key + "']  option[value=''] ").attr("selected",true)
}
