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

function hidediv(){
	$(".floatdiv").first().css("display", "none");
	isopen = false;
}

function setJSONValue(key, newValue){
	searchMap.put(key, newValue);
}

function sortselect(){
	var sortvalue = $("#sortmodule").children('option:selected').attr("sort");
	var ordervalue = $("#sortmodule").children('option:selected').attr("order");
    sort = sortvalue;
    order = ordervalue;
	setJSONValue("sort", sortvalue);
    setJSONValue("order", ordervalue);
    showSelectedField(globalUrl +'community.show?actionMethod=dimquery');
}
/*
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
	postDataByURL2(actionUrl, postUrl, "changelist");
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
/*
function saveCookies(StringId) {
	if(searchMap){
		searchMap.put("ispage", "1");
	}
	if (StringId == "dataShape") {
		$('#list_list').css("display", "block");
		$('#image_list').css("display", "none");
		setType1();
		$('#t1').css("background-color", "#000000");
		$('#t2').css("background-color", "#CCCCCC");
	} else {
		$('#image_list').css("display", "block");
		$('#list_list').css("display", "none");
		setType0();
		$('#t2').css("background-color", "#000000");
		$('#t1').css("background-color", "#CCCCCC");
	}
	alert("community_list");
	postDataByURL2("community.show?actionMethod=dimquery", jsonStringInit, "changelist");
	$.cookie('lastSelected', StringId, { expires : 365 }, { path : "/" }); // 存储 cookie , expires: 设定时间天数参数， path：设置cookies存储路径参数
}
*/

