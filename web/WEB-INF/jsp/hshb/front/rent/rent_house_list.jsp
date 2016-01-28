<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
<title>租房 - ${title } </title>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/houseRent.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}/css/ad/adAndActivity.css">

<script type="text/javascript" src="${globalUrl}/js/search/myMap_new.js"></script>
<script type="text/javascript" src="${globalUrl}/js/housesecond/housesecond.js"></script>

<script type="text/javascript" src="${globalUrl}/js/search/jquery.search.js"></script>
<script type="text/javascript" src="${globalUrl}/js/search/jquery.search.phone.js"></script>

<!-- 弹出框js引用 -->
<script type="text/javascript" src="${globalUrl}/js/jquery.artDialog.source.js"></script>
<script type="text/javascript" src="${globalUrl}/js/artDialog.iframeTools.source.js"></script>
<link type="text/css" rel="stylesheet" href="${globalUrl}/js/skins/gray.css">

<link rel="stylesheet" type="text/css" href="${globalUrl}css/style.css"/>
<script type="text/javascript" src="${globalUrl }/js/select/jquery.selectReplace.js"></script>
<link rel="stylesheet" type="text/css" href="${globalUrl}/css/select/jquery.select.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}/css/searchPlugin/jquery.search.phone.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}/css/searchPlugin/jquery.search.css"/>

<style rel="stylesheet" type="text/css">
	#searchMenuBar{}
	.itemMenuSelected{border-top-color: rgb(221, 221, 221);}
	.selectedField{color: rgb(255, 102, 0);}
	#expandOrHiddenBar{background: rgb(255, 155, 75);}
	#expandOrHiddenBar:hover{background: rgb(228, 123, 39);}
	.toSearchIcon:hover {background-color: rgb(255, 155, 75);}
	.selectHoverVal{color:rgb(255, 155, 75);}
	.cschover {background: rgb(255, 155, 75);}
	.searchXL .cs .xljt:hover{background-color:rgb(255, 155, 75);}
	.displaySubField{border: 1px solid rgb(255, 102, 0);}
	.anabledOperation{color: rgb(102, 102, 102);}
	.subSelectedField{color: rgb(255, 102, 0);}
	.cSearch .xlicon:hover{background-color:rgb(255, 155, 75);}
	.cSearch .xlxx{border-color: rgb(255, 155, 75);}
	.cSearch a:hover{background-color:rgb(255, 155, 75);}
	.itemMenu:hover{border-top: 1px solid rgb(221, 221, 221);}
	@media screen and (max-width: 758px){
		#searchMenuBarPhone{background: rgb(255, 155, 75);}
		.phoneItemMenuSelected{border-top-color: rgb(255, 102, 0);}
		.searchXL .xlnr a:hover{background: rgb(255, 155, 75);}
		.searchXL .xlnr{border-color: rgb(255, 102, 0);}
		.cschover {background: rgb(255, 155, 75);}
		.multiItem:hover{color: rgb(255, 155, 75);}
		.searchXL .cs .xljt:hover{background-color:rgb(255, 155, 75);}
	}
	
.wd_ls .biaot a:hover { color: #ff9b4b; }
.x{margin-top:-12px;}
</style>
 --%>
 
<script type="text/javascript">
_baseSearchUrl = "${globalUrl}/rent.show?actionMethod=dimquery";
var comparedItemInCookie;
var historyItemsArray;
var comparedItemsArray
var searchInput = "";
var searchURL = "";
$(document).ready(function(){
	initSearchMap();
	try{
		$("#selectorder").replaceAllSelect();	
	}catch(e){
		if(window.console) console.log(e);
	}
	
	$(".xiala span").css("color","#444444");
	searchInput = getUrlParam("searchInput");
	searchMap.put("ispage", 1);
	var currentURL = '${sharedUrl}';
	searchURL = currentURL;
	if(currentURL.indexOf("tabIndex") == -1){
		setJSONValue("tabIndex", 0);
	}
	if (currentURL != ''){
		currentURL = currentURL.split("?")[1];
		var arrayTemp = currentURL.split("&");
		for (var k = 0; k < arrayTemp.length; k++) {
			var key = arrayTemp[k].split("=")[0];
			var value = arrayTemp[k].split("=")[1];
			if (key != 'actionMethod'){
				searchMap.put(key, value);
			}
		}
	}
<%--
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
	//document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000);
--%>
	createSearchArea(_conditionJson, '${globalUrl}/rent.show?actionMethod=dimquery');

	$("#privateSearchItemContent").find(".narrowIcon").attr('src', '${globalUrl}/images/search/narrow_yellow.png');
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

 	$("#_Ten_rightDiv").css("z-index", "999");
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

function startToShare(){
	startShare("rent.show");
}

function nextPage(algorithm , sumPage){
	var pageLabel = $('#pageLabel').html();
	//上一页
//	$('#pageAId1').show();
//		$('#pageAId2').show();
	pageLabel = pageLabel * 1 + algorithm * 1;
	if(pageLabel <= 1){
		pageLabel = 1;
//		$('#pageAId1').hide();
	}else if(pageLabel >= sumPage){
		//下一页
		pageLabel = sumPage;
//		$('#pageAId2').hide();
	}
	
	actionUrl = "${globalUrl}/rent.show?actionMethod=dimquery";
	nextPageInformation(actionUrl, pageLabel);
	$("#pageLabel").text(pageLabel);
}

function searchCondition(selectName){
	var selectOptionVal = $("select[name='" +selectName + "']").val();
	var actionUrl = "${globalUrl}/rent.show?actionMethod=dimquery";
	searchSelectHouse(actionUrl, selectName, selectOptionVal);
}
</script>

<form name="houseRent">
<div style="margin-top: 5px;">
	<div class="Location"><a onclick='window.location.href="welcome.show?actionMethod=welcome"' style="cursor: pointer;">首页</a> > 杭州租房</div>
</div>

	<div class="x">
		<div id="searchMenuDiv" class="companyRight" style="width:100%; margin-top:12px;"></div>
    </div>
     <%@include file="/WEB-INF/jsp/ddhb/front/ad/adTopBar.jsp" %> 
	<div id="houserenList">
		<div class="lbt">
			<div class="tab_co">
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
				<span><a id="orderRules1" class="one" onclick="orderRule('${globalUrl}/rent.show?actionMethod=dimquery' , 'sortIndex','1');">默认</a></span>		
				<span><a id="orderRules2" onclick="orderRule('${globalUrl}/rent.show?actionMethod=dimquery' , 'area','2');">面积</a></span>
				<span><a id="orderRules3" onclick="orderRule('${globalUrl}/rent.show?actionMethod=dimquery' , 'community.startSaleDate','3');">年份</a></span>
				<span><a id="orderRules4" onclick="orderRule('${globalUrl}/rent.show?actionMethod=dimquery' , 'rentPrice','4');">租金</a></span>
			</div>
			<%-- 当前页和总页数 --%>
			<div class="fenPage">
				<a id="pageAId1" onclick="nextPage(-1)" >&lt;</a>
				<span>
						<fmt:formatNumber var="pageNumber" value="${(pageBean.totalRows / pageBean.pageSize) }" pattern="#"/> 
						<c:if test="${pageBean.totalRows % pageBean.pageSize == 0 }"><fmt:formatNumber var="page_1" value="0" pattern="#"/></c:if>
						<c:if test="${pageBean.totalRows % pageBean.pageSize != 0 }"><fmt:formatNumber var="page_1" value="1" pattern="#"/></c:if>
						<label style="color:#f60;" id="pageLabel">${pageBean.currentPage }</label> / <label id="totalPages">${pageNumber + page_1}</label>
				</span>
				<a id="pageAId2" onclick="nextPage(1,${pageNumber})">&gt;</a>
			</div>
			<%-- END --%>
			<%--
			<div id="selectorder" style="float:left; margin:7px 0 0 0;">
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
			--%>
		    <div style="float:right;padding-right:10px; text-align:left;"><span style="float:left;">共有</span><span style="color:#f60; float:left; font-weight:bold;" id="dynamichousecount">${pageBean.totalRows}</span><span style="float:left;">套房源</span></div>
	</div>
		<div style="clear: both;"></div>
		<div id="changelist">
			<%@include file="/WEB-INF/jsp/ddhb/front/rent/houseRent_search_list.jsp" %>
		</div>
		<img id="searchPicture" src="" style="display:none;"/>
	</div>
	
	<%@include file="/WEB-INF/jsp/ddhb/front/ad/adButtompBar.jsp" %>
	
</form>
