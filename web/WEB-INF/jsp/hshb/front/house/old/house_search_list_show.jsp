<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--

<link rel="stylesheet" type="text/css" href="${globalUrl}css/searchPlugin/jquery.search.phone.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/searchPlugin/jquery.search.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/style.css"/>
<!-- 弹出框js引用 -->
<link type="text/css" rel="stylesheet" href="${globalUrl}js/skins/gray.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/select/jquery.select.css">

<script type="text/javascript" src="${globalUrl}js/search/myMap_new.js"></script>
<script type="text/javascript" src="${globalUrl}js/housesecond/housesecond.js"></script>
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.js"></script>
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.phone.js"></script>
<script type="text/javascript" src="${globalUrl}js/cookie/cookie.js"></script>
<script type="text/javascript" src="${globalUrl}js/compare/compare.js"></script>
<script type="text/javascript" src="${globalUrl}js/compare/history.js"></script>
<script type="text/javascript" src="${globalUrl}js/jquery.artDialog.source.js"></script>
<script type="text/javascript" src="${globalUrl}js/artDialog.iframeTools.source.js"></script>
<script type="text/javascript" src="${globalUrl}js/select/jquery.selectReplace.js"></script>
<script type="text/javascript" id="bdshare_js" data="type=tools" ></script>
<script type="text/javascript" id="bdshell_js"></script>

--%>

<script type="text/javascript">
var comparedItemsArray;
var historyItemsArray;
var searchInput = "";
$(document).ready(function(){
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
		url:'${globalUrl}houseSecond.show?actionMethod=dimquery',
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
	createSearchArea(_conditionJson, '${globalUrl}houseSecond.show?actionMethod=dimquery');
	
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
	
	var comparedItemInCookie = '${secondHouseCompare}';
	if (comparedItemInCookie == ''){
		comparedItemsArray=new Array();
	}else{
		comparedItemsArray = JSON.parse(comparedItemInCookie); 
	}
	
	initComparedItems(comparedItemsArray, "secondHouseCompare");
	//delCookie("secondHouseHistory");
	  
	var historyItemInCookie = '${secondHouseHistory}';
	if (historyItemInCookie == ''){
		historyItemsArray = new Array();
	}else{
		historyItemsArray = JSON.parse(historyItemInCookie);
	}
	initHistoryItems(historyItemsArray, "secondHouseHistory");  

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
	sortselect('${globalUrl}houseSecond.show?actionMethod=dimquery');
}
</script>
<script type="text/javascript">
</script>
<form name="houseSecond">

<div class="Location"><a href="#">首页</a> > <a href="#">杭州二手房</a></div>
<div class="x">
	<div id="searchMenuDiv" class="companyRight" style="width:100%; margin-top:12px;"></div>
</div>

<!-- END -->
<input type="hidden" id="Brand" value="0" /> 
<input type="hidden" id="Price" value="0" />
<input type="hidden" id="Size" value="0" />
<img id="searchPicture" src="" style="display:none;"/>

<div class="houseList">
	<div class="lbt">
		<span>共有<span style="float:none;padding:0;" id="dynamichousecount">${pageBean.totalRows}</span>套符合要求的房子</span>
		<div id="selectorder" style="float:right; margin-top:5px; margin-right:10px;">
			<div class="xiala xl_w145" style="width:170px;">
			<select id="sortmodule" onchange="javascript:sortselect('${globalUrl}houseSecond.show?actionMethod=dimquery')" relativeSelectId="true">
				<option sort="sortIndex" order="asc" selected="selected">默认排序</option>
				<option sort="price" order="desc">价格由高至低</option>
				<option sort="price" order="asc">价格由低至高</option>
				<option sort="area" order="desc">面积由大至小</option>
				<option sort="area" order="asc">面积由小至大</option>
				<option sort="unitPrice" order="desc">单价由高至低</option>
				<option sort="unitPrice" order="asc">单价由低至高</option>
				<option sort="shi" order="desc">居室由大至小</option>
				<option sort="shi" order="asc">居室由小至大</option>
				<option sort="community.buildYear" order="asc">建成年代由早至晚</option>
				<option sort="community.buildYear" order="desc">建成年代由晚至早</option>
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
		<%@include file="/WEB-INF/jsp/ddhb/front/house/house_second_search_list_show.jsp" %>
	</div>
</div>
<script type="text/javascript">
(function($){

})(jQuery)
</script>
</form>
