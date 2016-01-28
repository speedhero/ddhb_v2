<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
<title>租房 - ${title } </title>
<link rel="stylesheet" type="text/css" href="${globalUrl}/css/select/jquery.select.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}/css/searchPlugin/jquery.search.phone.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}/css/searchPlugin/jquery.search.css"/>
<link type="text/css" rel="stylesheet" href="${globalUrl }/js/skins/gray.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/style.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}/css/zl.css">

<script type="text/javascript" src="${globalUrl}/js/search/myMap_new.js"></script>
<script type="text/javascript" src="${globalUrl}/js/housesecond/housesecond.js"></script>

<script type="text/javascript" src="${globalUrl}/js/search/jquery.search.js"></script>
<!-- <script type="text/javascript" src="${globalUrl}/js/search/jquery.search.phone.js"></script>-->

<!-- 弹出框js引用 -->
<script type="text/javascript" src="${globalUrl }/js/jquery.artDialog.source.js"></script>
<script type="text/javascript" src="${globalUrl }/js/artDialog.iframeTools.source.js"></script>
<script type="text/javascript" src="${globalUrl }/js/select/jquery.selectReplace.js"></script>
--%>
 
<!--<style rel="stylesheet" type="text/css">
#searchMenuBar{background-color: rgb(255, 155, 75);}
.itemMenuSelected{border-top-color: rgb(255, 155, 75);}
.selectedField{color: rgb(255, 155, 75);}
#expandOrHiddenBar{background: rgb(255, 155, 75);}
#expandOrHiddenBar:hover{background: rgb(228, 123, 39);}
.toSearchIcon:hover {background-color: rgb(255, 155, 75);}
.selectHoverVal{color:rgb(255, 155, 75);}
.cschover {background: rgb(255, 155, 75);}
.searchXL .cs .xljt:hover{background-color:rgb(255, 155, 75);}
.displaySubField{border: 1px solid rgb(255, 155, 75);}
.displaySubField_xq {border: 1px solid rgb(255, 155, 75);}
.clearIcon{background-image: url("images/search/clearSearch_yellow.png");}
.saveIcon{background-image: url("images/search/savesearch_yellow.png");}
.shareIcon{background-image: url("images/search/shareSearch_yellow.png");}
.anabledOperation{color: rgb(255, 155, 75);}
.subSelectedField{color: rgb(255, 155, 75);}
.cSearch .xlicon:hover{background-color:rgb(255, 155, 75);}
.cSearch .xlxx{border-color: rgb(255, 155, 75);}
.cSearch a:hover{background-color:rgb(255, 155, 75);}
@media screen and (max-width: 758px){
	#searchMenuBarPhone{background: rgb(255, 155, 75);}
	.phoneItemMenuSelected{border-top-color: rgb(255, 155, 75);}
	.searchXL .xlnr a:hover{background: rgb(255, 155, 75);}
	.searchXL .xlnr{border-color: rgb(255, 155, 75);}
	.cschover {background: rgb(255, 155, 75);}
	.multiItem:hover{color: rgb(255, 155, 75);}
	.searchXL .cs .xljt:hover{background-color:rgb(255, 155, 75);}
}
</style>-->

<script type="text/javascript">
var comparedItemInCookie;
var historyItemsArray;
var searchInput = "";
$(document).ready(function(){
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
	var str = $.parseJSON(_conditionJson);
	option={
		searchItems:str,
		url:'${globalUrl}/rent.show?actionMethod=dimquery',
		searchMap:searchMap
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
	createSearchArea(_conditionJson, '${globalUrl}/rent.show?actionMethod=dimquery');
	
	$("#privateSearchItemContent").find(".narrowIcon").attr('src', '${globalUrl}/images/search/narrow_yellow.png');
	
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
	//delCookie("rentHouseCompare");
	var comparedItemInCookie = '${rentHouseCompare}';
	if (comparedItemInCookie == ''){
		comparedItemsArray=new Array();
	}else{
		comparedItemsArray = JSON.parse(comparedItemInCookie); 
	}
       initComparedItems(comparedItemsArray, "rentHouseCompare");
       
       //delCookie("rentHouseHistory");
   	var historyItemInCookie = '${rentHouseHistory}';
   	if (historyItemInCookie == ''){
   		historyItemsArray = new Array();
   	}else{
   		historyItemsArray = JSON.parse(historyItemInCookie);
   	}
   	
   	initHistoryItems(historyItemsArray, "rentHouseHistory");
   	
   	
});
/*
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
*/
function changeRelativeSelect(selecctedValue) {
	sortselect('rent.show?actionMethod=dimquery');
}
</script>

<div style="margin-top: 20px;">
	<div class="Location"><a onclick='window.location.href="welcome.show?actionMethod=welcome"' style="cursor: pointer;">首页</a> > 杭州租房</div>
</div>

<form name="houseRent">
	<div class="x">
		<div id="searchMenuDiv" class="companyRight" style="width:100%; margin-top:12px;"></div>
    </div>
    	
	<div id="houserenList">
		<div class="lbt">
		<span>共有<span style="float:none;padding:0;" id="dynamichousecount">${pageBean.totalRows}</span>套符合要求的房子</span>
			<div id="selectorder" style="float:right; margin-top:5px; margin-right:10px;">
				<div class="xiala xl_w145" style="width:170px;">
				<select id="sortmodule" onchange="javascript:sortselect('rent.show?actionMethod=dimquery')" relativeSelectId="true">
					<option sort="sortIndex" order="asc" selected="selected">默认排序</option>
					<option sort="rentPrice" order="desc">租金由高至低</option>
					<option sort="rentPrice" order="asc">租金由低至高</option>
					<option sort="area" order="desc">面积由大至小</option>
					<option sort="area" order="asc">面积由小至大</option>
					<option sort="shi" order="desc">居室由大至小</option>
					<option sort="shi" order="asc">居室由小至大</option>
					<option sort="community.startSaleDate" order="asc">建成年代由早至晚</option>
					<option sort="community.startSaleDate" order="desc">建成年代由晚至早</option>
					<option sort="lastModified" order="asc">发布时间由新至旧</option>
					<option sort="lastModified" order="desc">发布时间由旧至新</option>
				</select>
				</div>
			</div>
			<%--
		<a id="checkbtn1" onclick="saveCookies('imgShape');" href="javascript:void(0);" class="a_1 lbta">大图模式</a>
		<a id="checkbtn2" onclick="saveCookies('dataShape');" href="javascript:void(0);" class="a_2 a_2a lbta">列表模式</a>
		 --%>
		<a id="checkbtn1" class="a_1 lbta">大图模式</a>
		<a id="checkbtn2" class="a_2 a_2a lbta">列表模式</a>
	</div>
		<div style="clear: both;"></div>
		<div id="changelist">
			<%@include file="/WEB-INF/jsp/ddhb/front/rent/house_rent_search_list_show.jsp" %>
		</div>
		<img id="searchPicture" src="" style="display:none;"/>
	</div>
</form>
