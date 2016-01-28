<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--
<script type="text/javascript" src="${globalUrl}js/search/myMap_new.js"></script>
<script type="text/javascript" src="${globalUrl}js/housesecond/housesecond.js"></script>
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.js"></script>
<script type="text/javascript" src="${globalUrl}js/cookie/cookie.js"></script>
<script type="text/javascript" src="${globalUrl}js/compare/compare.js"></script>
<script type="text/javascript" src="${globalUrl}js/compare/history.js"></script>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/searchPlugin/jquery.search.css"/>
<link type="text/css" rel="stylesheet" href="${globalUrl}css/compare/comparePanel.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/style.css"/>
<!-- 弹出框js引用 -->
<script type="text/javascript" src="${globalUrl}js/jquery.artDialog.source.js"></script>
<script type="text/javascript" src="${globalUrl}js/artDialog.iframeTools.source.js"></script>
<link type="text/css" rel="stylesheet" href="${globalUrl}js/skins/gray.css">
<script type="text/javascript" id="bdshare_js" data="type=tools" ></script>
<script type="text/javascript" id="bdshell_js"></script>
--%>
<script type="text/javascript">
var comparedItemsArray;
var historyItemsArray;
$(document).ready(function(){
	initSearchMap();
	$("#selectorder").replaceAllSelect();
	$(".xiala span").css("color","#444444");
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

	var str = $.parseJSON('${jsonString}');
	option={
		searchItems:str,
		url:'${globalUrl}houseSecond.show?actionMethod=dimquery',
		searchMap:searchMap
	};
	
	$("#searchMenuDiv").createSearch(option);
	
	//document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000);
	
	$(".compareDIvMenu").click(function(){
		changeCompareMenu($(this));
	});
	
	//delCookie("secondHouseCompare");
	var comparedItemInCookie = getCookie("secondHouseCompare");
	if (comparedItemInCookie === null){
		comparedItemsArray=new Array();
	}else{
		comparedItemsArray = JSON.parse(comparedItemInCookie); 
	}
       initComparedItems(comparedItemsArray, "secondHouseCompare");
       //delCookie("secondHouseHistory");
   	var historyItemInCookie = getCookie("secondHouseHistory");
   	if (historyItemInCookie === null){
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
*/
function changeRelativeSelect(selecctedValue) {
	sortselect('${globalUrl}houseSecond.show?actionMethod=dimquery');
}
</script>
<script type="text/javascript">
</script>
<form name="houseSecond">
	<!-- END -->
	<input type="hidden" id="Brand" value="0" /> 
	<input type="hidden" id="Price" value="0" />
	<input type="hidden" id="Size" value="0" />
	
	<img id="searchPicture" src="" style="display:none;"/>

	<div class="houseList">
		<div class="lbt"><span>共有${pageBean.totalRows}套房源</span>
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
		<a id="checkbtn1" onclick="saveCookies('imgShape');" href="javascript:void(0);"  class="a_1 lbta">大图模式</a>
		<a id="checkbtn2" onclick="saveCookies('dataShape');" href="javascript:void(0);" class="a_2 a_2a lbta">列表模式</a></div>
		--%>
		<a id="checkbtn1" class="a_1 lbta">大图模式</a>
		<a id="checkbtn2" class="a_2 a_2a lbta">列表模式</a></div>
		<div style="clear: both;"></div>
		<div id="changelist">
			<%@include file="/WEB-INF/jsp/ddhb/front/house/houseSecond_search_list.jsp" %>
		</div>
		
		
		<div id="compareDiv" style="display:none;">
		 	<div id="menuDiv">
		 		<div id="compareMenu" class="compareDIvMenu compareMenuSelected">对比栏</div>
		 		<div id="historyDiv" class="compareDIvMenu">最近浏览</div>
		 		<div id="hiddenCompareDiv" onclick="hiddenCompareDiv()">隐藏</div>
		 		<div class="clearDiv"></div>
		 	</div>
		 	<div id="compareContentContainer">
		 		<div id="compareContent">
		 			<div class="compareItem" id="compareItem1">
		 				<div class="textContent" id="textContent1">
			 				<div class="compareNumber">1</div>
			 				<div class="compareText">继续添加对比二手房</div>
		 				</div>
		 			</div>
		 			<div class="compareSeperationLine"></div>
		 			<div class="compareItem" id="compareItem2">
		 				<div class="textContent" id="textContent2">
			 				<div class="compareNumber">2</div>
			 				<div class="compareText">继续添加对比二手房</div>
		 				</div>
		 			</div>
		 			<div class="compareSeperationLine"></div>
		 			<div class="compareItem" id="compareItem3">
		 				<div class="textContent" id="textContent3">
			 				<div class="compareNumber">3</div>
			 				<div class="compareText">继续添加对比二手房</div>
		 				</div>
		 			</div>
		 			<div class="compareSeperationLine"></div>
		 			<div class="compareItem" id="compareItem4">
		 				<div class="textContent" id="textContent4">
			 				<div class="compareNumber">4</div>
			 				<div class="compareText">继续添加对比二手房</div>
		 				</div>
		 			</div>
		 			<div class="compareSeperationLine"></div>
		 			<div class="compareItem">
		 				<div id="startCompareButton">
		 					<div class="clearCompareDiv">
		 						<div class="startButtondiv">
			 						<img src="${globalUrl}images/compare/startCompare.png" onclick="startCompare('${globalUrl}houseSecond.show?actionMethod=houseSecondCompare')">
		 						</div>
		 						<div class="clearCompareLanText" onclick="clearCompareItem('secondHouseCompare')"><span>清空对比栏</span></div>
		 					</div>
		 				</div>
		 				<div id="clearCompareButton"></div>
		 			</div>
		 			<div class="clearDiv"></div>
		 		</div>
		 	</div>
		 	<div id="historyListContainer">
		 		<div id="listContent">
		 			<div class="historyItem" id="historyItem1">
		 				<div class="textContent" id="historyTextContent1">
			 				<div class="compareNumber">1</div>
			 				<div class="compareText">二手房浏览历史</div>
		 				</div>
		 			</div>
		 			<div class="compareSeperationLine"></div>
		 			<div class="historyItem" id="historyItem2">
		 				<div class="textContent" id="historyTextContent2">
			 				<div class="compareNumber">2</div>
			 				<div class="compareText">二手房浏览历史</div>
		 				</div>
		 			</div>
		 			<div class="compareSeperationLine"></div>
		 			<div class="historyItem" id="historyItem3">
		 				<div class="textContent" id="historyTextContent3">
			 				<div class="compareNumber">3</div>
			 				<div class="compareText">二手房浏览历史</div>
		 				</div>
		 			</div>
		 			<div class="compareSeperationLine"></div>
		 			<div class="historyItem" id="historyItem4">
		 				<div class="textContent" id="historyTextContent4">
			 				<div class="compareNumber">4</div>
			 				<div class="compareText">二手房浏览历史</div>
		 				</div>
		 			</div>
		 			<div class="compareSeperationLine"></div>
		 			<div class="historyItem" id="historyItem5">
		 				<div class="textContent" id="historyTextContent5">
			 				<div class="compareNumber">5</div>
			 				<div class="compareText">二手房浏览历史</div>
		 				</div>
		 			</div>
		 		</div>
		 	</div>
		 </div>
	</div>
</form>
