<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="h" uri="/WEB-INF/tld/hshb.tld"%>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/houseSecond.css"/>
<%-- <link rel="stylesheet" type="text/css" href="${globalUrl}css/ad/adAndActivity.css"> --%>
<%-- 图片延迟加载 --%>
<script type="text/javascript" src="${globalUrl}js/lazyload/jquery.lazyload.js?ver=1.0.0"></script> 
<script type="text/javascript">
jQuery(document).ready(function($){
	$("img").lazyload({
     placeholder : '${globalUrl}js/lazyload/grey.gif', //加载图片前的占位图片
     effect      : "fadeIn" //加载图片使用的效果(淡入)
	});
});
</script>
<script type="text/javascript">
<%--
_baseSearchUrl = "${globalUrl}houseSecond.show?actionMethod=dimquery";
var comparedItemsArray;
var historyItemsArray;
var searchInput = "";
var currentURL = '${sharedUrl}';
--%>
$(document).ready(function(){
	<%-- 
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
--%>
	<%-- 对比 --%>
	var comparedItemInCookie = '${secondHouseCompare}';
	if (comparedItemInCookie == ""){
		comparedItemsArray=new Array();
	}else{
		comparedItemsArray = JSON.parse(comparedItemInCookie); 
	}
	initComparedItems(comparedItemsArray, "secondHouseCompare");
       <%-- 历史记录 --%>
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
function changeRelativeSelect(selecctedValue) {
	sortselect('${globalUrl}houseSecond.show?actionMethod=dimquery');
}
function startToShare(){
	startShare("${globalUrl}houseSecond.show");
}=
function nextPage(algorithm , sumPage){
	var pageLabel = $('#pageLabel').html();
	//上一页
	pageLabel = pageLabel * 1 + algorithm * 1;
	if(pageLabel <= 1){
		pageLabel = 1;
	}else if(pageLabel >= sumPage){
		//下一页
		pageLabel = sumPage;
	}
	
	actionUrl = "${globalUrl}/houseSecond.show?actionMethod=dimquery";
	nextPageInformation(actionUrl, pageLabel);
	$("#pageLabel").text(pageLabel);
}
--%>
</script>
<form name="houseSecond">
	<div style="margin-top: 20px">
		<div class="Location">
			<a href="${globalUrl}">首页</a> > <a href="${globalUrl}chushou">杭州二手房</a>
		</div>
	</div>

	<c:import url="/WEB-INF/jsp/hshb/front/house/sale/search_condition.jsp"></c:import>

	<!-- END -->
	<input type="hidden" id="Brand" value="0" /> 
	<input type="hidden" id="Price" value="0" /> 
	<input type="hidden" id="Size" value="0" /> 
	<img id="searchPicture" src="" style="display: none;" />

	<div class="houseList">
		<div class="lbt">
			<div class="tab_co">
			  <a id="checkbtn1" class="a_1 lbta">大图模式</a>
			  <a id="checkbtn2" class="a_2 a_2a lbta">列表模式</a>
			</div>
			<div class="paixu">
				<span>排序：</span>
				<span><a id="orderRules1" href="<h:surl p='s1' />"${h:containsEntry(params, "s", "1")?"  class='one'" : ""}>默认</a></span>		
				<span><a id="orderRules2" href="<h:surl p='s2' />"${h:containsEntry(params, "s", "2")?"  class='one'" : ""}>面积</a></span>
				<span><a id="orderRules3" href="<h:surl p='s3' />"${h:containsEntry(params, "s", "3")?"  class='one'" : ""}>总价</a></span>
				<span><a id="orderRules4" href="<h:surl p='s4' />"${h:containsEntry(params, "s", "4")?"  class='one'" : ""}>单价</a></span>
			</div>
			<%-- 当前页和总页数 --%>
			<div class="fenPage">
				<a href="<h:surl p="n${pageBean.pageNum<=1? 1: pageBean.pageNum - 1}"/>" id="pageAId1">&lt;</a>
				<span>
					<label style="color:#f60;" id="pageLabel">${pageBean.pageNum }</label> / <label id="totalPages">${pageBean.pages}</label>
				</span>
				<a href="<h:surl p="n${pageBean.pageNum >= pageBean.pages? pageBean.pages : pageBean.pageNum + 1}"/>" id="pageAId2">&gt;</a>
			</div>
			<span>共有<span style="float:none;padding:0;" id="dynamichousecount">${pageBean.total}</span>套房源</span>
		</div>
		<div style="clear: both;"></div>
		<div id="changelist">
			<c:import url="/WEB-INF/jsp/hshb/front/house/sale/house_list.jsp"></c:import>
		</div>
	</div>
</form>
<%-- 
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
--%>
<style>
.searchLabel[searchitemid="26"], .searchLabel[searchitemid="27"], .searchLabel[searchitemid="28"]{color:red;}
</style>
