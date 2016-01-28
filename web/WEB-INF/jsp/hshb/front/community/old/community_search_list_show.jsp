<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--
<title>小区- ${title } </title>
<script type="text/javascript" src="${globalUrl}js/search/myMap_new.js"></script>
<script type="text/javascript" src="${globalUrl}js/community/community_list.js"></script>
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.js"></script>
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.phone.js"></script>

<!-- 弹出框js引用 -->
<script type="text/javascript" src="${globalUrl}js/jquery.artDialog.source.js"></script>
<script type="text/javascript" src="${globalUrl}js/artDialog.iframeTools.source.js"></script>
<link type="text/css" rel="stylesheet" href="${globalUrl}js/skins/gray.css">

<script type="text/javascript" id="bdshare_js" data="type=tools" ></script>
<script type="text/javascript" id="bdshell_js"></script>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/style.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/xq.css">

<link rel="stylesheet" type="text/css" href="${globalUrl}css/searchPlugin/jquery.search.phone.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/searchPlugin/jquery.search.css"/>

<script type="text/javascript" src="${globalUrl}js/select/jquery.selectReplace.js"></script>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/select/jquery.select.css">
--%>
<!--<style rel="stylesheet" type="text/css">
#searchMenuBar{background-color: rgb(117, 190, 64)}
.itemMenuSelected{border-top-color: rgb(117, 190, 64);}
.selectedField{color: rgb(117, 190, 64);}
#expandOrHiddenBar{background: rgb(117, 190, 64);}
.toSearchIcon:hover {background-color: rgb(117, 190, 64);}
.selectHoverVal{color:rgb(117, 190, 64);}
.cschover {background: rgb(117, 190, 64);}
.searchXL .cs .xljt:hover{background-color:rgb(117, 190, 64);}
.displaySubField{border: 1px solid rgb(117, 190, 64);}
.clearIcon{background-image: url("${globalUrl}images/search/shareSearch_green.png");}
.saveIcon{background-image: url("${globalUrl}images/search/savesearch_green.png");}
.shareIcon{background-image: url("${globalUrl}images/search/shareSearch_green.png");}
.anabledOperation{color: rgb(117, 190, 64);}
.subSelectedField{color: rgb(117, 190, 64);}
.cSearch .xlicon:hover{background-color:rgb(117, 190, 64);}
.cSearch .xlxx{border-color: rgb(117, 190, 64);}
.cSearch a:hover{background-color:rgb(117, 190, 64);}
@media screen and (max-width: 758px){
	#searchMenuBarPhone{background: rgb(117, 190, 64);}
	.phoneItemMenuSelected{border-top-color: rgb(117, 190, 64);}
	.searchXL .xlnr a:hover{background: rgb(117, 190, 64);}
	.searchXL .xlnr{border-color: rgb(117, 190, 64);}
	.cschover {background: rgb(117, 190, 64);}
	.multiItem:hover{color: rgb(117, 190, 64);}
	.searchXL .cs .xljt:hover{background-color:rgb(117, 190, 64);}
}
</style>-->

<script>
var comparedItemsArray;
var historyItemsArray;
var searchInput = "";
$(document).ready(function() {
	initSearchMap();
	$("#selectorder").replaceAllSelect();
	$(".xiala span").css("color","#444444");
	
	searchInput = getUrlParam("searchInput");
	searchMap.put("ispage", 1);
	var currentURL = document.location.toString();
	if(currentURL.indexOf("tabIndex") == -1){
		setJSONValue("tabIndex", 0);
	}
	currentURL = currentURL.split("?")[1];
	var arrayTemp = currentURL.split("&");
	for (var k = 0; k < arrayTemp.length; k++) {
		var key = arrayTemp[k].split("=")[0];
		var value = arrayTemp[k].split("=")[1];
		if (key != 'actionMethod'){
			searchMap.put(key, value);
		}
	}
	
	/*
	var str = $.parseJSON('${jsonString}');
	option={
		searchItems:str,
		url:'${globalUrl}community.show?actionMethod=dimquery',
		searchMap:searchMap,
		displayHiddenBar:false
	};
	
	var winWidth = 1024;
   	if (window.innerWidth){
   		 winWidth = window.innerWidth;
   	}else if ((document.body) && (document.body.clientWidth)){
   		winWidth = document.body.clientWidth;
   	}
	if (winWidth <= 758){
		$("#searchMenuDiv").createSearchForPhone(option);
	}else{
		$("#searchMenuDiv").createSearch(option);
	}
	
	var previousWidth = winWidth;
	$(window).resize(function(){
		var winWidth = 1024;
       	if (window.innerWidth){
       		winWidth = window.innerWidth;
       	}else if ((document.body) && (document.body.clientWidth)){
       		winWidth = document.body.clientWidth;
       	}
       	if (previousWidth < 758 && winWidth >= 758){
       		$("#searchMenuDiv").empty();
       		$("#searchMenuDiv").createSearch(option);
       	}
       	if (previousWidth >= 758 && winWidth < 758){
       		$("#searchMenuDiv").empty();
       		$("#searchMenuDiv").createSearchForPhone(option);
       	}
       	
       	previousWidth = winWidth;
   	});
	*/
	createSearchArea(_conditionJson, '${globalUrl}community.show?actionMethod=dimquery');
	
	$("#privateSearchItemContent").find(".narrowIcon").attr('src', '${globalUrl}images/search/narrow_green.png');
	//document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000);
<%--
	$("#historyDiv").click(function(){
		$("#compareContentContainer").css("display", "none");
		$("#historyListContainer").css("display", "block");
		$("#historyDiv").addClass("one");
		$("#compareMenu").removeClass("one");
	});
		
	$("#compareMenu").click(function(){
		$("#compareContentContainer").css("display", "block");
		$("#historyListContainer").css("display", "none");
		$("#historyDiv").removeClass("one");
		$("#compareMenu").addClass("one");
	});
	compareIsOpen();

--%>
	//delCookie("secondHouseCompare");
	var comparedItemInCookie = getCookie("communityCompare");
	if (comparedItemInCookie === null){
		comparedItemsArray=new Array();
	}else{
		comparedItemsArray = JSON.parse(comparedItemInCookie); 
	}
    initComparedItems(comparedItemsArray, "communityCompare");
    
  	//delCookie("communityHistory");
	var historyItemInCookie = getCookie("communityHistory");
	if (historyItemInCookie === null){
		historyItemsArray = new Array();
	}else{
		historyItemsArray = JSON.parse(historyItemInCookie);
	}
	initHistoryItems(historyItemsArray, "communityHistory");
});
function changeRelativeSelect(selecctedValue) {
	sortselect();
}
<%--
function setType0() {
	setJSONValue('type', 0);
	type = 0;
}

function setType1() {
	setJSONValue("type", 1);
	type = 1;
}

function fangda(){
	var dataContent = document.getElementById("searchPicture");
	art.dialog({
		id:'searchPictures',
 		title: "请扫描二维码",
 		content: dataContent,
 		lock: true,
 		drag: false,
 	    resize: false,
 	    max: false,
 	    min: false,
 	  	zIndex: 99999
	});
}
--%>

</script>
<div class="Location"><a onclick='window.location.href="${globalUrl}welcome.show?actionMethod=welcome"' style="cursor: pointer;color: #999999;">首页</a> > 杭州小区</div>
<div style="margin-top: 20px;">
	<div class="x">
		<div id="searchMenuDiv" class="companyRight" style="width:100%; margin-top:12px;"></div>
	</div>
</div>

<div class="lbt">
	<span>共有<span style="float:none;padding:0; color:#f60; font-weight:400;" id="dynamichousecount">${pageBean.totalRows}</span>套房源</span>
	<div id="selectorder" style="float:right; margin-top:5px; margin-right:10px;">
	<div class="xiala xl_w145" style="width:170px;">

	<select id="sortmodule" onchange="javascript:sortselect()" relativeSelectId="true">
		<option sort="sortIndex" order="asc" selected="selected">默认排序</option>
		<option sort="currentSHAvePrice" order="desc">价格由高至低</option>
		<option sort="currentSHAvePrice" order="asc">价格由低至高</option>
		<option sort="floorArea" order="desc">面积由大至小</option>
		<option sort="floorArea" order="asc">面积由小至大</option>
		<option sort="startSaleDate" order="asc">建成年代由早至晚</option>
		<option sort="startSaleDate" order="desc">建成年代由晚至早</option>
	</select>
	</div>
</div>
<%--
	<a id="checkbtn1" href="javascript:void(0);" onclick="saveCookies('imgShape');" class="a_1 lbta">大图模式</a>
	<a id="checkbtn2" href="javascript:void(0);" onclick="saveCookies('dataShape');" class="a_2 a_2a lbta">列表模式</a>
	--%>
	<a id="checkbtn1" class="a_1 lbta">大图模式</a>
	<a id="checkbtn2" class="a_2 a_2a lbta">列表模式</a>
</div>
<form name="community">
	<div id="changelist">
		<%@include file="/WEB-INF/jsp/ddhb/front/community/community_search_list.jsp" %>
	</div>
	<img id="searchPicture" src="" style="display:none;"/>
</form>
