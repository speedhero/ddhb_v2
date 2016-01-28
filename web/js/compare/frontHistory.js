function changeHistory(obj, historyItemsArray, cookieKeyStr){
	if (historyItemsArray.length == 5){
		for (var i = 1; i < historyItemsArray.length; i++){
			historyItemsArray[i - 1] = historyItemsArray[i];
		}
		historyItemsArray[4] = obj;
	}else{
		historyItemsArray[historyItemsArray.length] = obj;
	}
	//setCookie(cookieKeyStr, JSON.stringify(historyItemsArray));
	saveHistoryItem(cookieKeyStr, JSON.stringify(historyItemsArray));
}

function initHistoryItems(historyItemsArray, cookieKeyStr,flag){
	for (var i = 0; i < historyItemsArray.length; i++){
		var pageUrl = historyItemsArray[i].pageUrl;
		var straa =  "<div class='houseCompareItem' pageUrl='" + i + "'>" +
	    "<div class='db_pt'><a href='javascript:void(0);'><img src='" + historyItemsArray[i].url + "' onclick='toPage(\"" +pageUrl + "\")'></a></div>" + 
	    "<div class='db_name'>" + historyItemsArray[i].title + "</div>" +
	    "<div class='db_zd'><p><span><b>" ;
		
		if(cookieKeyStr == "rentHouseHistory"){
			straa = straa + parseInt(historyItemsArray[i].price) + "</b>元/每月</span><b>" + parseInt(historyItemsArray[i].area) + "</b>平米</p><b>" + historyItemsArray[i].shi + "</b>室<b>" + historyItemsArray[i].ting + "</b>厅</div>";
		}else{
			straa = straa + parseInt(historyItemsArray[i].price/10000) + "</b>万</span><b>" + parseInt(historyItemsArray[i].area) + "</b>平米</p><b>" + historyItemsArray[i].shi + "</b>室<b>" + historyItemsArray[i].ting + "</b>厅</div>";
		}
		
	    $("#historyTextContent" + flag + ( i + 1)).css("display", "none");
		$("#historyItem"+ flag + ( i + 1)).append(straa);
	}
}

function toPage(pageUrl){
	window.open(pageUrl);
}

function openHistory(isShowCompare){
/*	$("#compareDiv").css("display", "block");
	$("#historyListContainer").css("display", "block");
	if(isShowCompare != ""){
		$("#compareMenu").removeClass("one");
		$("#historyDiv").addClass("one");
		$("#compareContentContainer").css("display", "none");
		
	}else{
		$("#compareMenu").css("display", "none");
		$(".db_tjswd").html("房源浏览记录");
		showAllHistory();
	}*/
	if(isShowCompare != ""){
		$("#compareDiv").css("display", "block");
		$("#historyListContainer").css("display", "block");
		$("#compareMenu").removeClass("one");
		$("#historyDiv").addClass("one");
		$("#compareContentContainer").css("display", "none");
		
	}else{
		if($("#loginStatusForIcon").val() == "no"){
			loginBox('browseHistory');
		}else{
			var url = globalUrl +"usercenter.do?actionMethod=browseHistory&isCutPage=false";
			window.open(url);
		}
	}
}

//清除掉历史记录栏中的所有记录
function showAllHistory(){
	for (var i= 0; i < 4; i++){
		$("#historyItem" + (i + 1)).find(".houseCompareItem").remove();
		$("#historyTextContent" + (i + 1)).css("display", "block");
	}
	var historyCookie = getCookie("allHistory");
	var historyCookieObj;
	if (historyCookie === null){
		historyCookieObj=new Array();
	}else{
		historyCookieObj = JSON.parse(historyCookie); 
	}
	
	for (var i = 0; i < historyCookieObj.length; i++){
		var pageUrl = historyCookieObj[i].pageUrl;
		var straa =  "<div class='houseCompareItem' pageUrl='" + i + "'>" +
	    "<div class='db_pt'><a href='javascript:void(0);'><img src='" + historyCookieObj[i].url + "' onclick='toPage(\"" +pageUrl + "\")'></a></div>" + 
	    "<div class='db_name'>" + historyCookieObj[i].title + "</div>" +
	    "<div class='db_zd'><p><span><b>" ;
		
		if(historyCookieObj[i].historyType == "R"){
			straa = straa + parseInt(historyCookieObj[i].price) + "</b>元/每月</span><b>" + parseInt(historyCookieObj[i].area) + "</b>平米</p><b>" + historyCookieObj[i].shi + "</b>室<b>" + historyCookieObj[i].ting + "</b>厅</div>";
		}else if(historyCookieObj[i].historyType == "S"){
			straa = straa + parseInt(historyCookieObj[i].price/10000) + "</b>万</span><b>" + parseInt(historyCookieObj[i].area) + "</b>平米</p><b>" + historyCookieObj[i].shi + "</b>室<b>" + historyCookieObj[i].ting + "</b>厅</div>";
		}
		else{
			straa = straa + parseInt(historyCookieObj[i].price) + "</b>元/平米</span><b>" + parseInt(historyCookieObj[i].area) + "</b>平米</p></div>";
		}
		
		
	    $("#historyTextContent" + ( i + 1)).css("display", "none");
		$("#historyItem" + ( i + 1)).append(straa);
	}
}

function addHistoryToCookie(obj, relativeUrl){
	var historyObj = new Object();
	if ($(obj).attr("historyType") == 'S'){
		historyObj.historyType = "S";
	}else if ($(obj).attr("historyType") == 'R'){
		historyObj.historyType = "R";
	}else{
		historyObj.historyType = "C";
	}
	historyObj.pageUrl = relativeUrl;
	historyObj.id = $(obj).attr("compareId");
	historyObj.url = $(obj).attr("housePictureUrl");
	historyObj.title = $(obj).attr("compareTitle");
	historyObj.area = $(obj).attr("area");
	historyObj.shi = $(obj).attr("shi");
	historyObj.ting = $(obj).attr("ting");
	historyObj.price = $(obj).attr("comparePrice");
	
	var historyCookie = getCookie("allHistory");
	var historyCookieObj;
	if (historyCookie === null){
		historyCookieObj=new Array();
	}else{
		historyCookieObj = JSON.parse(historyCookie); 
	}
	
	if (historyCookieObj.length == 5){
		historyCookieObj.splice(0,1);
	}
	historyCookieObj.push(historyObj);
	//setCookie("allHistory", JSON.stringify(historyCookieObj));
	saveHistoryItem("allHistory", JSON.stringify(historyCookieObj));
}

/**
 * 将对历史字符串存储到后台中
 * @param cookieKeyStr
 * @param content
 */
function saveHistoryItem(cookieKeyStr, content){
	var dataString = {"cookieKeyStr" : cookieKeyStr, "contentStr": content};
	$.ajax({
		type : "POST",
		url : globalUrl+"userBrowsedHistory.show?actionMethod=updateHistoryItem",
		data : dataString,
		dataType: "json",
		success : function(data) {
			
		},
		error:function(){
			alert("保存浏览历史失败！");
		}
	});
}
