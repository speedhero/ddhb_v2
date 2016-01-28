<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/houseSecond.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/ad/adAndActivity.css">

<script type="text/javascript">
_baseSearchUrl = "${globalUrl}houseSecond.show?actionMethod=dimquery";
var comparedItemsArray;
var historyItemsArray;
var searchInput = "";
var currentURL = '${sharedUrl}';
//var searchURL = "";

$(document).ready(function(){
	try{
		$("#selectorder").replaceAllSelect();	
	}catch(e){
		if(window.console) console.log(e);
	}
	$(".xiala span").css("color","#444444");
	searchInput = getUrlParam("searchInput");
	searchMap.put("ispage", 1);

//	searchURL = currentURL;
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
		var autoMaticOption = { pageCapacity:1 };
		$("#top").startAutomate(autoMaticOption);
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
	   	if (previousWidth > 758 && winWidth <= 758){
	   		var autoMaticOption = { pageCapacity:1 };
	      $("#top").startAutomate(autoMaticOption);
	   	}
	   	previousWidth = winWidth;
	});
	//document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000);
--%>
	createSearchArea(_conditionJson, '${globalUrl}houseSecond.show?actionMethod=dimquery');
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
	if (comparedItemInCookie == ""){
		comparedItemsArray=new Array();
	}else{
		comparedItemsArray = JSON.parse(comparedItemInCookie); 
	}
	initComparedItems(comparedItemsArray, "secondHouseCompare");
       
	var historyItemInCookie = '${secondHouseHistory}';
 	if (historyItemInCookie == ''){
 		historyItemsArray = new Array();
 	}else{
 		historyItemsArray = JSON.parse(historyItemInCookie);
 	}
 	initHistoryItems(historyItemsArray, "secondHouseHistory");  

   	
	$("#_Ten_rightDiv").css("z-index", "999");
});
<%--
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
function changeRelativeSelect(selecctedValue) {
	sortselect('${globalUrl}houseSecond.show?actionMethod=dimquery');
}
	
function startToShare(){
	startShare("${globalUrl}houseSecond.show");
}
function nextPage(algorithm , sumPage){
	var pageLabel = $('#pageLabel').html();
	//上一页
//	$('#pageAId1').show();;
//		$('#pageAId2').show();;
	pageLabel = pageLabel * 1 + algorithm * 1;
	if(pageLabel <= 1){
		pageLabel = 1;
//		$('#pageAId1').hide();
	}else if(pageLabel >= sumPage){
		//下一页
		pageLabel = sumPage;
//		$('#pageAId2').hide();
	}
	
	actionUrl = "${globalUrl}/houseSecond.show?actionMethod=dimquery";
	nextPageInformation(actionUrl, pageLabel);
	$("#pageLabel").text(pageLabel);
}
function searchCondition(selectName){
	var selectOptionVal = $("select[name='" +selectName + "']").val();
	var actionUrl = "${globalUrl}/houseSecond.show?actionMethod=dimquery";
	searchSelectHouse(actionUrl, selectName, selectOptionVal);
}
</script>
<script type="text/javascript"></script>
<form name="houseSecond">
	<div style="margin-top: 20px">
		<div class="Location">
			<a onclick="window.location.href='${globalUrl}welcome.show?actionMethod=welcome'">首页</a> > <a href="#">杭州二手房</a>
		</div>
	</div>
	<div class="x">
		<div id="searchMenuDiv" class="companyRight" style="width: 100%; margin-top: 12px;"></div>
	</div>
	<%@include file="/WEB-INF/jsp/ddhb/front/ad/adTopBar.jsp"%>

	<!-- END -->
	<input type="hidden" id="Brand" value="0" /> 
	<input type="hidden" id="Price" value="0" /> 
	<input type="hidden" id="Size" value="0" /> 
	<img id="searchPicture" src="" style="display: none;" />

	<div class="houseList">
		<div class="lbt">
			<div class="tab_co">
			  <%--
			  <a id="checkbtn1" onclick="saveCookies('imgShape');" href="javascript:void(0);" class="a_1 lbta">大图模式</a>
			  <a id="checkbtn2" onclick="saveCookies('dataShape');" href="javascript:void(0);" class="a_2 a_2a lbta">列表模式</a>
			   --%>
			  <a id="checkbtn1" class="a_1 lbta">大图模式</a>
			  <a id="checkbtn2" class="a_2 a_2a lbta">列表模式</a>
			</div>
			<div class="paixu">
				<span>排序：</span>
				<span><a id="orderRules1" class="one" onclick="orderRule('${globalUrl}/houseSecond.show?actionMethod=dimquery' , 'sortIndex','1');">默认</a></span>		
				<span><a id="orderRules2" onclick="orderRule('${globalUrl}/houseSecond.show?actionMethod=dimquery' , 'area','2');">面积</a></span>
				<span><a id="orderRules3" onclick="orderRule('${globalUrl}/houseSecond.show?actionMethod=dimquery' , 'price','3');">总价</a></span>
				<span><a id="orderRules4" onclick="orderRule('${globalUrl}/houseSecond.show?actionMethod=dimquery' , 'unitPrice','4');">单价</a></span>
			</div>
			<%-- 当前页和总页数 --%>
			<div class="fenPage">
				<a id="pageAId1" onclick="nextPage(-1)" >&lt;</a>
				<span>
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
			<span>共有<span style="float:none;padding:0;" id="dynamichousecount">${pageBean.totalRows}</span>套房源</span>
			<%--
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
			--%>
		</div>
		<div style="clear: both;"></div>
		<div id="changelist">
			<%@include file="/WEB-INF/jsp/ddhb/front/house/houseSecond_search_list.jsp" %>
		</div>
	</div>
	
	<%@include file="/WEB-INF/jsp/ddhb/front/ad/adButtompBar.jsp" %>
</form>
<script type="text/javascript">
$(function(){ 
	$("#searchMenu15").mouseover(function(e){ 
		var toolTip = "<div id='tooltip' width='100px' height='12px' style='position:absolute;border:solid #aaa 1px;background-color:#F9F9F9'>" + "请选择首付、月供和贷款年限后搜索房源" + "</div>"; 
		$("body").append(toolTip); 
		$("#tooltip").css({ 
			"top" :e.pageY + "px", 
			"left" :e.pageX + "px" 
		}); 
		$("#searchMenu15").mouseout(function(){ 
			$("#tooltip").remove(); 
		}); 
		//显示提示框后，提示框跟随鼠标移动
		$("#searchMenu15").mousemove(function(e){
			$("#tooltip").css({ 
				"top" :(e.pageY+10) + "px", 
				"left" :(e.pageX+15) + "px" 
			});
		}); 
	//alert("Y:" + e.pageY + "X:" + e.pageX); 
	}); 
});
</script>
<style>
.searchLabel[searchitemid="26"], .searchLabel[searchitemid="27"], .searchLabel[searchitemid="28"]{color:red;}
</style>
