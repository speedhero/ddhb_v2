function addHistoryItem(obj, cookieKeyStr, relativeUrl){
	for (var i = 0; i < historyItemsArray.length; i++){
		if (historyItemsArray[i].id == $(obj).attr("compareId")){
			initHistoryItems(historyItemsArray, cookieKeyStr);
			return;
		}
	}
	createhHistoryObjStr(obj, historyItemsArray, cookieKeyStr, relativeUrl);
	addHistoryToCookie(obj, relativeUrl);
}

function createhHistoryObjStr(obj, historyItemsArray, cookieKeyStr, relativeUrl){
	var compareItem = {};
	compareItem.pageUrl = relativeUrl;
	compareItem.id = $(obj).attr("compareId");
	compareItem.url = $(obj).attr("housePictureUrl");
	compareItem.title = $(obj).attr("compareTitle");
	compareItem.area = $(obj).attr("area");
	compareItem.shi = $(obj).attr("shi");
	compareItem.ting = $(obj).attr("ting");
	compareItem.price = $(obj).attr("comparePrice");
	changeHistory(compareItem, historyItemsArray, cookieKeyStr);
	displayHistory(historyItemsArray, cookieKeyStr);
}

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

function displayHistory(historyItemsArray, cookieKeyStr){
	$("[historyItemsArrayIndex]").remove();
	for (var i = 0; i < 5; i++){
		$("#historyItem" + (i + 1) + "").css("display","block");
	}
	initHistoryItems(historyItemsArray, cookieKeyStr);
}

function initHistoryItems(historyItemsArray, cookieKeyStr){
	for (var i = 0; i < historyItemsArray.length; i++){
		var pageUrl = historyItemsArray[i].pageUrl;
		var straa =  "<div class='houseCompareItem' pageUrl='" + i + "'>" +
	    "<div class='db_pt'><a href='javascript:void(0);'><img src='" + historyItemsArray[i].url + "' onclick='toPage(\"" +pageUrl + "\")'></a></div>" + 
	    "<div class='db_name'>" + historyItemsArray[i].title + "</div>" +
	    "<div class='db_zd'><p><span><b>" ;
		
		if(cookieKeyStr == "rentHouseHistory"){
			straa = straa + parseInt(historyItemsArray[i].price) + "</b>元/每月</span><b>" + parseInt(historyItemsArray[i].area) + "</b>平米</p><b>" + historyItemsArray[i].shi + "</b>室<b>" + historyItemsArray[i].ting + "</b>厅</div>";
		}else if(cookieKeyStr == "secondHouseHistory"){
			straa = straa + parseInt(historyItemsArray[i].price/10000) + "</b>万</span><b>" + parseInt(historyItemsArray[i].area) + "</b>平米</p><b>" + historyItemsArray[i].shi + "</b>室<b>" + historyItemsArray[i].ting + "</b>厅</div>";
		}
		else{
			straa = straa + parseInt(historyItemsArray[i].price) + "</b>元/平米</span><b>" + parseInt(historyItemsArray[i].area) + "</b>平米</p></div>";
		}
		
		
	    $("#historyTextContent" + ( i + 1)).css("display", "none");
		$("#historyItem" + ( i + 1)).append(straa);
	}
}

function toPage(pageUrl){
	window.open(pageUrl);
}

function openHistory(isShowCompare, gloUrl){
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
			//var url = "${globalUrl}usercenter.do?actionMethod=browseHistory&isCutPage=false";
			var gUrl = gloUrl || globalUrl;
			var url = gUrl + "usercenter.do?actionMethod=browseHistory&isCutPage=false";
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
function reloadHistoryDiv(cookieStr,globalurl,loginFlag){
	if(cookieStr == "secondHouseHistory"){
		$("#historyItem1").empty();
		$("#historyItem1").append('<div id="historyTextContent1"><div class="db_tjs">1</div><div class="db_tjswd">二手房浏览历史</div></div>');
		$("#historyItem2").empty();
		$("#historyItem2").append('<div id="historyTextContent2"><div class="db_tjs">2</div><div class="db_tjswd">二手房浏览历史</div></div>');
		$("#historyItem3").empty();
		$("#historyItem3").append('<div id="historyTextContent3"><div class="db_tjs">3</div><div class="db_tjswd">二手房浏览历史</div></div>');
		$("#historyItem4").empty();
		$("#historyItem4").append('<div id="historyTextContent4"><div class="db_tjs">4</div><div class="db_tjswd">二手房浏览历史</div></div>');
		if(loginFlag == "no"){
			$("#moreBtn").attr("onclick","loginBox('browseHistory','0')");
		}
	}else if(cookieStr == "rentHouseHistory"){
		$("#historyItem1").empty();
		$("#historyItem1").append('<div id="historyTextContent1"><div class="db_tjs">1</div><div class="db_tjswd">租房房源浏览历史</div></div>');
		$("#historyItem2").empty();
		$("#historyItem2").append('<div id="historyTextContent2"><div class="db_tjs">2</div><div class="db_tjswd">租房房源浏览历史</div></div>');
		$("#historyItem3").empty();
		$("#historyItem3").append('<div id="historyTextContent3"><div class="db_tjs">3</div><div class="db_tjswd">租房房源浏览历史</div></div>');
		$("#historyItem4").empty();
		$("#historyItem4").append('<div id="historyTextContent4"><div class="db_tjs">4</div><div class="db_tjswd">租房房源浏览历史</div></div>');
		if(loginFlag == "no"){
			$("#moreBtn").attr("onclick","loginBox('browseHistory','1')");
		}
	}else{
		$("#historyItem1").empty();
		$("#historyItem1").append('<div id="historyTextContent1"><div class="db_tjs">1</div><div class="db_tjswd">小区浏览历史</div></div>');
		$("#historyItem2").empty();
		$("#historyItem2").append('<div id="historyTextContent2"><div class="db_tjs">2</div><div class="db_tjswd">小区浏览历史</div></div>');
		$("#historyItem3").empty();
		$("#historyItem3").append('<div id="historyTextContent3"><div class="db_tjs">3</div><div class="db_tjswd">小区浏览历史</div></div>');
		$("#historyItem4").empty();
		$("#historyItem4").append('<div id="historyTextContent4"><div class="db_tjs">4</div><div class="db_tjswd">小区浏览历史</div></div>');
		if(loginFlag == "no"){
			$("#moreBtn").attr("onclick","loginBox('browseHistory','2')");
		}
	}
}


/**
 * 将对历史字符串存储到后台中
 * @param cookieKeyStr
 * @param content
 */
function saveHistoryItem(cookieKeyStr, content){
	var dataString = {"cookieKeyStr" : cookieKeyStr, "contentStr": content};
	//alert("cookieKeyStr = " + dataString.cookieKeyStr + ", contentStr= " + dataString.contentStr);
	//alert(globalUrl+"userBrowsedHistory.show?actionMethod=updateHistoryItem");
	$.ajax({
		type : "POST",
		url : globalUrl+"userBrowsedHistory.show?actionMethod=updateHistoryItem",
		data : dataString,
		dataType: "json",
		success : function(data) {
			//alert("浏览历史保存成功。");
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			//alert(textStatus);
			alert("保存浏览历史失败！");
	    }
	});
}