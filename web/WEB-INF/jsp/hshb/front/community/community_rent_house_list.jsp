<%@page import="com.huatek.hbwebsite.util.FrontSystemSettingUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${globalUrl}js/search/myMap_new.js"></script>
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.simple.js"></script>
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.simple.phone.js"></script>
<script type="text/javascript" src="${globalUrl}js/housesecond/housesecond.js"></script>
<link type="text/css" rel="stylesheet" href="${globalUrl}css/compare/comparePanel.css">
<link type="text/css" rel="stylesheet" href="${globalUrl}js/skins/gray.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/searchPlugin/jquery.search.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/searchPlugin/jquery.search.simple.phone.css"/>
<!--<style type="text/css">
.selectedField{color:#75be40;}
.searchFieldContentDiv{margin-left:47px;}
.cSearch .xlicon:hover{background-color:rgb(117, 190, 64);}
.cSearch .xlxx{border-color: rgb(117, 190, 64);}
.cSearch a:hover{background-color:rgb(117, 190, 64);}
.searchXL .cs .xljt:hover{background-color:rgb(117, 190, 64);}
.searchXL .xlnr{border-color: rgb(117, 190, 64);}
.searchXL .xlnr a:hover{background-color:rgb(117, 190, 64);}
.toSearchIcon:hover{background-color:rgb(117, 190, 64);}
.cSearch .xlicon:hover{background-color:rgb(117, 190, 64);}
.wd_ls .hover .bianh a {background: url(image/y_jian_xq.gif) no-repeat;}
#menuDiv{background-image: none;}
#nameTitle a:hover{color: #75BE40;}
</style>-->
<script type="text/javascript">
/*
function setType0() {
	setJSONValue('type', 0);
	type3 = 0;
}

function setType1() {
	setJSONValue("type", 1);
	type3 = 1;
}
*/
$(document).ready(function(){
	var searchMap = new Map();
	
	if (comparedItemsArray && comparedItemsArray.length > 0){
		for (var kk = 0; kk < comparedItemsArray.length > 0; kk++){
			$("img[compareid='" + comparedItemsArray[kk].id + "']").attr("incompareitem", "true");
			$("img[compareid='" + comparedItemsArray[kk].id + "']").attr("src", "${globalUrl}images/compare/selectedCompareId.png");
		}
	}
	
	initSearchMap();
	$("#selectorder").replaceAllSelect();
	$(".xiala span").css("color","#444444");
	$(".cSearch span").css("float", "inherit");
	
	searchMap.put("housetype", "2");
	searchMap.put("communityId", '${comId}');
	
	/*
	var str = $.parseJSON('${jsonString}');
	option={
		searchItems:str,
		url:'${globalUrl}community.show?actionMethod=dimdetailquery',
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
	createSearchArea(_conditionJson, '${globalUrl}community.show?actionMethod=dimdetailquery');
	
	reloadCompareDiv("rentHouseCompare","${globalUrl}");
	reloadHistoryDiv("rentHouseHistory","${globalUrl}",$("#loginStatus").val());
	reInitCompareDiv('rentHouseCompare','${globalUrl}');
});

<%--
function saveCookies(_showTypeFlag) {
	switchShowType(_showTypeFlag);
	
	$.cookie('lastSelected', _showTypeFlag, { expires : 365 }, { path : "/" });// 存储 cookie , expires: 设定时间天数参数， path：设置cookies存储路径参数
}
--%>
</script>
<form name="houseSecond">
	<div class="xcon xpxcon">
		<div id="searchMenuDiv" style="width:100%;"></div>
    </div>
	<!-- END -->
	<input type="hidden" id="Brand" value="0" /> 
	<input type="hidden" id="Price" value="0" />
	<input type="hidden" id="Size" value="0" />
	<input type="hidden" id="loginStatus" value="<c:if test="${LoginMember == null }">no</c:if>" />
	
	<img id="searchPicture" src="" style="display:none;"/>

	<div class="houseList">
		<div class="lbt"><span>共有<span id="rentTotals" style="float:none; color:#f60; font-weight:400; padding-right:0px;">${pageBean.totalRows}</span>套出租房</span>
			<div id="selectorder" style="float:right; margin-top:5px; margin-right:10px;">
				<div class="xiala xl_w145" style="width:170px;">
					<select id="sortmodule" onchange="javascript:sortselect('${globalUrl}rent.show?actionMethod=dimquery')" relativeSelectId="true">
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
		<%-- <a id="checkbtn1" onclick="saveCookies('imgShape');" href="javascript:void(0);" class="a_1 lbta">大图模式</a>
		<a id="checkbtn2" onclick="saveCookies('dataShape');" href="javascript:void(0);" class="a_2 a_2a ">列表模式</a></div>
		--%>
		<%-- <a id="checkbtn1" class="a_1 lbta">大图模式</a>--%>
		<a id="checkbtn2" style="margin-left:5px;" class="a_2 a_2a ">列表模式</a></div>

		<div style="clear: both;"></div>
		<div id="changelist">
			<%@include file="/WEB-INF/jsp/ddhb/front/community/community_rent_house.jsp" %> 
		</div>
	</div>
</form>
