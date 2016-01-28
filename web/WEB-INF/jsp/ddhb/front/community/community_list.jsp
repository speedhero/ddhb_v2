<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--
<script type="text/javascript" src="${globalUrl}/js/search/myMap_new.js"></script>
<script type="text/javascript" src="${globalUrl}/js/community/community_list.js"></script>

<script type="text/javascript" src="${globalUrl}/js/search/jquery.search.js"></script>
<script type="text/javascript" src="${globalUrl}/js/search/jquery.search.phone.js"></script>

<!-- 弹出框js引用 -->
<script type="text/javascript" src="${globalUrl}/js/jquery.artDialog.source.js"></script>
<script type="text/javascript" src="${globalUrl}/js/artDialog.iframeTools.source.js"></script>
<link type="text/css" rel="stylesheet" href="${globalUrl}/js/skins/gray.css">

<script type="text/javascript" id="bdshare_js" data="type=tools" ></script>
<script type="text/javascript" id="bdshell_js"></script>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/style.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}/css/xq.css">

<link rel="stylesheet" type="text/css" href="${globalUrl}/css/searchPlugin/jquery.search.phone.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}/css/searchPlugin/jquery.search.css"/>

<script type="text/javascript" src="${globalUrl}/js/select/jquery.selectReplace.js"></script>
<link rel="stylesheet" type="text/css" href="${globalUrl}/css/select/jquery.select.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}/css/ad/adAndActivity.css">
 --%>
<script type="text/javascript">
_baseSearchUrl = "${globalUrl}/community.show?actionMethod=dimquery";
var comparedItemsArray;
var historyItemsArray;
var searchInput = "";
//var searchURL = "";
var currentURL = '${sharedUrl}';
$(document).ready(function() {
	initSearchMap();
	$("#selectorder").replaceAllSelect();
	$(".xiala span").css("color","#444444");
	
	searchInput = getUrlParam("searchInput");
	searchMap.put("ispage", 1);

	//searchURL = currentURL;
	if(currentURL.indexOf("tabIndex") == -1){
		setJSONValue("tabIndex", 0);
	}
	
	parseSearchCondition(currentURL);
	
<%--	
	var str = $.parseJSON('${jsonString}');
	option={
		searchItems:str,
		url:'${globalUrl}/community.show?actionMethod=dimquery',
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
--%>
	createSearchArea(_conditionJson, '${globalUrl}/community.show?actionMethod=dimquery');

	$("#privateSearchItemContent").find(".narrowIcon").attr('src', '${globalUrl}/images/search/narrow_green.png');
	document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000);
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
	var comparedItemInCookie = '${communityCompare}';
	if (comparedItemInCookie == ''){
		comparedItemsArray=new Array();
	}else{
		comparedItemsArray = JSON.parse(comparedItemInCookie); 
	}
  initComparedItems(comparedItemsArray, "communityCompare");
    
  //delCookie("communityHistory");
	var historyItemInCookie = '${communityHistory}';
	if (historyItemInCookie == ''){
		historyItemsArray = new Array();
	}else{
		historyItemsArray = JSON.parse(historyItemInCookie);
	}
	initHistoryItems(historyItemsArray, "communityHistory");

	$("#_Ten_rightDiv").css("z-index", "999");
});


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
function startToShare(){
	startShare("community.show");
}

function changeRelativeSelect(selecctedValue) {
	sortselect();
}

function nextPage(algorithm , sumPage){
	var pageLabel = $('#pageLabel').html();
	//上一页
	pageLabel = pageLabel * 1 + algorithm * 1;
	if(pageLabel <= 1){
		pageLabel = 1;
	//	$('#pageAId1').hide();
	}else if(pageLabel >= sumPage){
		//下一页
		pageLabel = sumPage;
	//	$('#pageAId2').hide();
	}else{
	//	$('#pageAId1').show();;
	//	$('#pageAId2').show();;
	}
	
	actionUrl = "${globalUrl}/community.show?actionMethod=dimquery";
	nextPageInformation(actionUrl, pageLabel);
	$("#pageLabel").text(pageLabel);
}

//Modify by hejianbo 单击链接排序
function orderCommunity(actionUrl, orderType, number){
	setJSONValue("sort", orderType);
    setJSONValue("order", "asc");
    //临时弄了一个选中链接的颜色 和没选中时的CSS样式
    var idValue = '#orderRules' + number;
    for(var i = 0;i<5;i++){
    	var idValues =  '#orderRules' + i;
	    if(idValue == idValues ){
	    	$(idValues).css('color','red');
	    	$(idValues).addClass("one");
	    }else{
	    	$(idValues).css('color','black');
	    	$(idValues).removeClass("one");
	    }
	    
	    showSelectedField(actionUrl);
    }
//	postDataByURL2(actionUrl , postUrl,"changelist");
}

function nextPageInformation(actionUrl,currentPage){
	setJSONValue("currentPage", currentPage);
	showSelectedField(actionUrl);
	
}
function searchCondition(selectName){
	var selectOptionVal = $("select[name='" +selectName + "']").val();
	var actionUrl = "${globalUrl}/community.show?actionMethod=dimquery";
	setJSONValue(selectName, selectOptionVal);
	showSelectedField(actionUrl);
}

function searchCondition(selectName){
	var selectOptionVal = $("select[name='" +selectName + "']").val();
	var actionUrl = "${globalUrl}/houseSecond.show?actionMethod=dimquery";
	searchSelectHouse(actionUrl, selectName, selectOptionVal);
}

//下拉框搜索
function searchSelectHouse(actionUrl, key, value){
	var sumNumber = $(".selectedSearchItemDiv[name='"+key+"']").length;
	
	var txt = $("select[name='" +key + "'] ").find("option:selected").text();
	if( sumNumber > 0 )
	//先删除同个类型下的查询条件 比如已查询出房龄，再次查询房龄的其他条件
		removeSelectCss(key,actionUrl);
	setJSONValue(key, value);
	if(value = ""){
	$("select[name='" +key + "']  option[value='"+value+"'] ").attr("selected",true)
	$("#allSelectedItemsDiv").append("<div class='selectedSearchItemDiv' onclick= removeSelectCss('" + key + "','" + actionUrl
			+ "'); name='" + key 
			+ "' ><div class='columnDiv'" 
			+  " fieldvalue='" + value
			+ "' selectedColumn='" + key 
			+ "'>" + txt + "</div><div class='removeColumnDiv'  " 
			+ " columnname='" + key + "' fieldvalue='" + value + "' ismulty='false' ></div><div class='clearDiv'></div></div>");
	}
	showSelectedField(actionUrl);
}
//删除select所选的内容
function removeSelectCss(key, actionUrl){
	$(".selectedSearchItemDiv[name='"+key+"']").remove();
	setJSONValue(key, "");
	showSelectedField(actionUrl);
	$("select[name='" +key + "']  option[value=''] ").attr("selected",true)
}
</script>
<div style="margin-top: 5px;" class="Location"><a onclick='window.location.href="welcome.show?actionMethod=welcome"' style="cursor: pointer;color: #999999;">首页</a> > 杭州小区</div>
<div>
	<div class="x">
		<div id="searchMenuDiv" class="companyRight" style="width:100%;"></div>
	</div>
</div>

<%--@include file="/WEB-INF/jsp/ddhb/front/ad/adTopBar.jsp" --%>	

<div class="lbt">
	<div class="tab_co">
		<%--
	  <a id="checkbtn1" href="javascript:void(0);" onclick="saveCookies('imgShape');" class="a_1 a_1a lbta">大图模式</a>
	  <a id="checkbtn2" onclick="saveCookies('dataShape');"  href="javascript:void(0);" class="a_2 lbta">列表模式</a>
	  --%>
	  <%--
	  <a id="checkbtn1" onclick="saveCookies('imgShape');" href="javascript:void(0);" class="a_1 lbta">大图模式</a>
		<a id="checkbtn2" onclick="saveCookies('dataShape');" href="javascript:void(0);" class="a_2 a_2a lbta">列表模式</a>
		--%>
	  <a id="checkbtn1" class="a_1 lbta">大图模式</a>
		<a id="checkbtn2" class="a_2 a_2a lbta">列表模式</a>
	</div>
	
	<%-- Modify By hejianbo 20150512 start --%>
	<div class="paixu">
		<span>排序：</span>
		<span><a id="orderRules1" class="one"  onclick="orderCommunity('${globalUrl}/community.show?actionMethod=dimquery' , 'sortIndex','1');">默认</a></span>		
		<span><a id="orderRules2"  onclick="orderCommunity('${globalUrl}/community.show?actionMethod=dimquery' , 'floorArea','2');">面积</a></span>
		<span><a id="orderRules3"  onclick="orderCommunity('${globalUrl}/community.show?actionMethod=dimquery' , 'currentSHAvePrice','3');">价格</a></span>
		<span><a id="orderRules4"  onclick="orderCommunity('${globalUrl}/community.show?actionMethod=dimquery' , 'startSaleDate','4');">年份</a></span>
	</div>
	<%-- 当前页和总页数 --%>
	<div class="fenPage">
		<a id="pageAId1" onclick="nextPage(-1)" >&lt;</a>
		<span><%-- 
				<c:if test="${pageBean.totalRows % pageBean.pageSize == 0 }"><fmt:formatNumber var="pageNumber" value="${(pageBean.totalRows / pageBean.pageSize) }" pattern="#"/> </c:if>
				<c:if test="${pageBean.totalRows % pageBean.pageSize != 0 }"><fmt:formatNumber var="pageNumber" value="${(pageBean.totalRows / pageBean.pageSize)+1 }" pattern="#"/></c:if>
				<label id="pageLabel">${pageBean.currentPage }</label> /${pageNumber}
			--%>
				<fmt:formatNumber var="pageNumber" value="${(pageBean.totalRows / pageBean.pageSize) }" pattern="#"/>
				<c:if test="${pageBean.totalRows % pageBean.pageSize == 0 }"><fmt:formatNumber var="page_1" value="0" pattern="#"/></c:if>
				<c:if test="${pageBean.totalRows % pageBean.pageSize != 0 }"><fmt:formatNumber var="page_1" value="1" pattern="#"/></c:if>
				<label style="color:#f60;" id="pageLabel">${pageBean.currentPage }</label> /
				<label id="totalPages">
				${pageNumber + page_1}
				</label>
		</span>
		<a id="pageAId2" onclick="nextPage(1,${pageNumber})">&gt;</a>
	</div>
	<%-- END --%>
	<%--
	<div id="selectorder" style="float:left; margin:7px 0 0 0;">
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
--%>
	<div style="float:right;padding-right:10px; text-align:left;"><span style="float:left;">共有</span><span style="color:#f60; float:left; font-weight:bold;" id="dynamichousecount">${pageBean.totalRows}</span><span style="float:left;">个小区</span></div>
</div>
<form name="community">
	<div id="changelist">
		<%@include file="/WEB-INF/jsp/ddhb/front/community/community_search_list.jsp" %>
	</div>
	<img id="searchPicture" src="" style="display:none;"/>
</form>