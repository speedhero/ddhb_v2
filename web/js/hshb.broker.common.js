
var comparedItemsArray;
var historyItemsArray;
var postUrl = "";
var isopen = false;

//var globalUrl = '${globalUrl}';
//var brokerId = '${broker.erpId}';
//var housetype = '${housetype}';
//var comparedItemInCookie = '${secondHouseCompare}';
//var _searchItems = '${jsonString}';
var _housetype = '${housetype}';
var historyItemInCookie = '${secondHouseHistory}';
var comparedItemInCookie = '${rentHouseCompare}';
var historyItemInCookie = '${rentHouseHistory}';

function qqcao(qq) {
	var type = undefined;
	var param = "";
	var sid = 2;
	var rawuin = qq/* <{$qq}> */;
	var qsig = "undefined";
	var QQApi = {
		openURL : function(url) {
			var iframe = document.createElement('iframe');
			iframe.style.display = 'none';
			iframe.onload = function() { iframe.parentNode.removeChild(i); };
			iframe.src = url;
			document.body.appendChild(i);
			var returnValue = QQApi.__RETURN_VALUE;
			QQApi.__RETURN_VALUE = undefined;
			return returnValue;
		},

		isAppInstalled : function(scheme) {
			var parameters = { 'scheme' : scheme };
			var r = QQApi.openURL('jsbridge://app/isInstalled_?p=' + encodeURIComponent(JSON.stringify(parameters)));
			return r ? r.result : null;
		},

		isQQWebView : function() {
			return QQApi.isAppInstalled('mqq') == true;
		},

		__RETURN_VALUE : undefined
	};

	var usa = navigator.userAgent;
	var mobile_q_jump = {
		android : "https://play.google.com/store/apps/details?id=com.tencent.mobileqq",
		ios : "itms-apps://itunes.apple.com/cn/app/qq-2011/id444934666?mt=8",
		winphone : "http://www.windowsphone.com/zh-cn/store/app/qq/b45f0a5f-13d8-422b-9be5-c750af531762",
		pc : "http://mobile.qq.com/index.html"
	};
	var isMQ = 0;
	if (typeof type == "undefined") type = 1;

	var _device;
	if (usa.indexOf("Android") > -1) {
		_device = "android";
	} else if (usa.indexOf("iPhone") > -1 || usa.indexOf("iPad") > -1
			|| usa.indexOf("iPod") > -1) {
		_device = "ios";
	} else if (usa.indexOf("Windows Phone") > -1
			|| usa.indexOf("WPDesktop") > -1) {
		_device = "winphone";
	} else {
		_device = "pc";
	}
	if (_device == "ios") {
		// 防止循环
		if (history.pushState)
			history.pushState({}, "t", "#");
		isMQ = QQApi.isQQWebView();
		if (!isMQ) {
			var sc = document.createElement("script");
			sc.src = "http://__.qq.com/api/qqapi.js";
			sc.onload = function() {
				if (window['iOSQQApi']) {
					isMQ = iOSQQApi.device.isMobileQQ();
				}
			};
			document.body.appendChild(sc);
		}
	} else if (_device == "pc" && qsig != "undefined") {
		window.open(qsig, "_self");
	}
	if (type == 1) {// 手Q
		var isSuccess = true;
		var f = document.createElement("iframe");
		f.style.display = "none";
		document.body.appendChild(f);
		f.onload = function() {
			isSuccess = false;
		};
		if (_device == "ios" && sid == 1) {
			f.src = "mqqapi://card/show_pslcard?src_type=internal&version=1&uin="
					+ rawuin + "&card_type=person&source=qrcode";
		}
		if (_device == "ios" && sid == 2) {// ios并且为群名片
			f.src = "mqqapi://card/show_pslcard?src_type=internal&version=1&uin="
					+ rawuin + "&card_type=person&source=qrcode";
		} else if (_device != "pc") {
			var url = window.location.href.split("&");
			f.src = "mqqopensdkapi://bizAgent/qm/qr?url="
					+ encodeURIComponent(url[0]);
		}
		if (_device == "android" && sid == 1) {
			f.src = "mqqapi://card/show_pslcard?src_type=internal&version=1&uin="
					+ rawuin + "&card_type=person&source=qrcode";
		}
		if (_device == "android" && sid == 2) {// ios并且为群名片
			f.src = "mqqapi://card/show_pslcard?src_type=internal&version=1&uin="
					+ rawuin + "&card_type=person&source=qrcode";
		}
		var now = Date.now();
		setTimeout(function() {
			if ((_device == "ios" && !isMQ && Date.now() - now < 2000)
					|| (_device == "android" && !isSuccess)
					|| ((_device == "winphone" && Date.now() - now < 2000))) {
				var jumpUrl = mobile_q_jump[_device];
				if (jumpUrl)
					window.open(jumpUrl, "_self");
			}
		}, 1500);
	}
}

/*
 * var searchMap = new Map(); 
 * function initSearchMap(){
 *  searchMap.put("order", "Asc");
 *  searchMap.put("brokerId", brokerId);
 *  searchMap.put("housetype", housetype);
 *  searchMap.put("ispage", 1); 
 * }
 */

$(document).ready(function() {
	// initSearchMap();
	//var str = $.parseJSON('${jsonString}');
	var str = $.parseJSON(_searchItems);
	option = {
		searchItems : str,
		url : globalUrl+'broker.show?actionMethod=dimquery',
		searchMap : searchMap
	};
	var winWidth = 1024;
	if (window.innerWidth) {
		winWidth = window.innerWidth;
	} else if ((document.body) && (document.body.clientWidth)) {
		winWidth = document.body.clientWidth;
	}
	if (winWidth <= 758) {
		$("#searchMenuDiv").createSearchForPhone(option);
	} else {
		$("#searchMenuDiv").createSearch(option);
	}
	var previousWidth = winWidth;
	$(window).resize(function() {
		var winWidth = 1024;
		if (window.innerWidth) {
			winWidth = window.innerWidth;
		} else if ((document.body) && (document.body.clientWidth)) {
			winWidth = document.body.clientWidth;
		}
		if (previousWidth < 758 && winWidth >= 758) {
			$("#searchMenuDiv").empty();
			$("#searchMenuDiv").createSearch(option);
		}
		if (previousWidth >= 758 && winWidth < 758) {
			$("#searchMenuDiv").empty();
			$("#searchMenuDiv").createSearchForPhone(option);
		}
		previousWidth = winWidth;
	});

	$('#selectpaixu').change(function() {
		var tempstr = $(this).children('option:selected').val();
		if (tempstr == "priceHeight") {
			if (housetype == "1") {
				sort = "price";
				order = "Desc";
				searchMap.put("sort", "price");
				searchMap.put("order", "Desc");
			} else {
				sort = "rentPrice";
				order = "Desc";
				searchMap.put("sort", "rentPrice");
				searchMap.put("order", "Desc");
			}
		} else if (tempstr == "priceLow") {
			if (housetype == "1") {
				sort = "price";
				order = "Asc";
				searchMap.put("sort", "price");
				searchMap.put("order", "Asc");
			} else {
				sort = "rentPrice";
				order = "Asc";
				searchMap.put("sort", "rentPrice");
				searchMap.put("order", "Asc");
			}
		} else if (tempstr == "areaHeight") {
			sort = "area";
			order = "Desc";
			searchMap.put("sort", "area");
			searchMap.put("order", "Desc");
		} else if (tempstr == "areaLow") {
			sort = "area";
			order = "Asc";
			searchMap.put("sort", "area");
			searchMap.put("order", "Asc");
		} else {
			sort = "sortIndex";
			order = "Asc";
			searchMap.put("sort", "sortIndex");
			searchMap.put("order", "Asc");
		}
		showSelectedField(globalUrl+"broker.show?actionMethod=dimquery");
	});

	$("#historyDiv").click(function() {
		$("#compareContentContainer").css("display", "none");
		$("#historyListContainer").css("display", "block");
		$("#historyDiv").addClass("one");
		$("#compareMenu").removeClass("one");
	});

	$("#compareMenu").click(function() {
		$("#compareContentContainer").css("display", "block");
		$("#historyListContainer").css("display", "none");
		$("#historyDiv").removeClass("one");
		$("#compareMenu").addClass("one");
	});

	//将对比栏还原，重新初始化对比栏
	$("#compareContentContainer .houseCompareItem").each(function(){
		$(this).remove();
	});
	$("#textContent1").css("display", "block");
	$("#textContent2").css("display", "block");
	$("#textContent3").css("display", "block");
	$("#textContent4").css("display", "block");
	$("[inCompareItem='true']").each(function(){
		$(this).attr("inCompareItem", "false");
		$(this).removeClass("addCompare");
	});
	
	if (_housetype == 1) {
		//var comparedItemInCookie = '${secondHouseCompare}';
		if (comparedItemInCookie == "") {
			comparedItemsArray = new Array();
		} else {
			comparedItemsArray = JSON.parse(comparedItemInCookie);
		}
		initComparedItems(comparedItemsArray, "secondHouseCompare");
		//var historyItemInCookie = '${secondHouseHistory}';
		if (historyItemInCookie == '') {
			historyItemsArray = new Array();
		} else {
			historyItemsArray = JSON.parse(historyItemInCookie);
		}
		initHistoryItems(historyItemsArray, "secondHouseHistory");
	} else {
		//var comparedItemInCookie = '${rentHouseCompare}';
		if (comparedItemInCookie == '') {
			comparedItemsArray = new Array();
		} else {
			comparedItemsArray = JSON.parse(comparedItemInCookie);
		}
		initComparedItems(comparedItemsArray, "rentHouseCompare");

		//var historyItemInCookie = '${rentHouseHistory}';
		if (historyItemInCookie == '') {
			historyItemsArray = new Array();
		} else {
			historyItemsArray = JSON.parse(historyItemInCookie);
		}

		initHistoryItems(historyItemsArray, "rentHouseHistory");
	}
});
/*
function showSelectedField() {
	var keyArray = searchMap.keys();
	if (keyArray.length > 0) {
		var posData = "";
		for (var i = 0; i < keyArray.length; i++) {
			posData += keyArray[i] + "=" + searchMap.get(keyArray[i]);
			if (i < keyArray.length - 1) {
				posData += "&";
			}
		}
	}

	$("select[selectId]").each(function() {
		var columnName = $(this).attr("columnName");
		var fieldValue = $(this).val();
		if (fieldValue != -1) {
			if (posData === undefined) {
				posData = columnName + "=" + fieldValue;
			} else {
				posData += "&";
				posData += columnName + "=" + fieldValue;
			}
		}
	});
	postDataByURL2(optionDefault.url, posData, "changelist");
}
*/
function wchatDisplay() {
	var dataContent = document.getElementById("BrokerWexin");
	art.dialog({
		id : 'wchatDisplay',
		title : "经纪人微信",
		content : dataContent,
		lock : true,
		drag : false,
		resize : false,
		max : false,
		min : false,
		zIndex : 99999
	});
}