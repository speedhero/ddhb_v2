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
<!--<style>
.selectedField{color:#75be40;}
.searchFieldContentDiv{margin-left:47px;}
.searchXL .cs .xljt:hover{background-color:rgb(117, 190, 64);}
.searchXL .xlnr{border-color: rgb(117, 190, 64);}
.searchXL .xlnr a:hover{background-color:rgb(117, 190, 64);}
.toSearchIcon:hover{background-color:rgb(117, 190, 64);}
.cSearch .xlicon:hover{background-color:rgb(117, 190, 64);}
.cSearch .xlxx {border:1px solid rgb(117, 190, 64);}
.xlxx a:hover{background-color:rgb(117, 190, 64);}
.wd_ls .hover .bianh a {background: url(image/y_jian_xq.gif) no-repeat;}
#menuDiv{background-image: none;}
#nameTitle a:hover{color: #75BE40;}
</style>-->

<script type="text/javascript">
$(document).ready(function(){
	var searchMap = new Map();
	var idStr = $.cookie("lastSelected");
	if (idStr == "imgShape") {
		$("#checkbtn1").addClass("a_1a");
		$("#checkbtn2").removeClass("a_2a");
		$("#checkbtn2").removeAttr("style").css("cursor", "pointer");
		$('#image_list').css("display", "block");
		$('#list_list').css("display", "none");
	} else {
		$("#checkbtn2").addClass("a_2a");
		$("#checkbtn1").removeClass("a_1a");
		$("#checkbtn1").removeAttr("style").css("cursor", "pointer"); 
		$('#list_list').css("display", "block");
		$('#image_list').css("display", "none");
	}
	
	$("#selectorder").replaceAllSelect();
	$(".xiala span").css("color","#444444");
	$(".cSearch span").css("float", "inherit");
	searchMap.put("housetype", "1");
	searchMap.put("communityId", '${comId}');
	/*
	var str = $.parseJSON('${jsonString}');
	option={
		searchItems:str,
		searchMap:searchMap,
		url:'${globalUrl}community.show?actionMethod=dimdetailquery'
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
	
	reloadCompareDiv("secondHouseCompare","${globalUrl}");
	reloadHistoryDiv("secondHouseHistory","${globalUrl}",$("#loginStatus").val());
	reInitCompareDiv("secondHouseCompare","${globalUrl}");
});
<%--
function saveCookies(StringId) {
	if(searchMap){
		searchMap.put("ispage", "1");
	}
	/*
	if (StringId == "dataShape") {
		$("#checkbtn2").addClass("a_2a");
		$("#checkbtn1").removeClass("a_1a");
		$("#checkbtn1").removeAttr("style").css("cursor", "pointer"); 
		$('#list_list').css("display", "block");
		$('#image_list').css("display", "none");
		setType1();
	} else {
		$("#checkbtn1").addClass("a_1a");
		$("#checkbtn2").removeClass("a_2a");
		$("#checkbtn2").removeAttr("style").css("cursor", "pointer");
		$('#image_list').css("display", "block");
		$('#list_list').css("display", "none");
		setType0();
	}*/
	switchShowType(StringId);
	
	$.cookie('lastSelected', StringId, { expires : 365 }, { path : "/" });// 存储 cookie , expires: 设定时间天数参数， path：设置cookies存储路径参数
}
--%>
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
</script>
<form name="houseSecond">
	<div class="xcon xpxcon">
		<div id="searchMenuDiv" style="width:100%;">
			<!-- 查询条件 开始 -->
				 <div id="searchContent">
        <div class="itemLine">
            <div class="searchLabelContainer">
                <div class="searchLabel" style="width:auto; padding-right:5px;" searchitemid="60">价格:</div>
                <div class="clearDiv"></div>
            </div>
            <div class="searchFieldContentDiv" fieldscontentid="60">
                <div class="searchField searchUnlimited selectedField" hassubvalue="true" columnname="ddhb_two_price">
                    <span>不限</span>
                </div>
                <!-- 价格 开始循环 -->
                <div class="searchField" columnname="ddhb_two_price" fieldvalue="0@500000">
                    <span>50万以下</span>
                </div>
                <!-- 价格 结束循环 -->
               
                <div class="inputDiv">
                    <div class="rangeContentDiv">
                        <input type="text" privateinput="true" columnname="ddhb_two_price" class="minValue">
                       		 一
                        <input type="text" privateinput="true" columnname="ddhb_two_price" class="maxValue">
                        <span>万元</span>
                    </div>
                    <div class="toSearchIcon" columnname="ddhb_two_price"></div>
                </div>
                <div class="clearDiv"></div>
            </div>
            <div class="clearDiv"></div>
        </div>
        <div class="itemLine">
            <div class="searchLabelContainer">
                <div class="searchLabel" style="width:auto; padding-right:5px;" searchitemid="61">面积:</div>
                <div class="clearDiv"></div>
            </div>
            <div class="searchFieldContentDiv" fieldscontentid="61">
                <div class="searchField searchUnlimited selectedField" hassubvalue="true" columnname="ddhb_two_area">
                    <span>不限</span>
                </div>
                <!-- 面积 开始 循环 -->
                <div class="searchField" columnname="ddhb_two_area" fieldvalue="0@40">
                    <span>40平以下</span>
                </div>
                <!-- 面积 结束 循环 -->
                <div class="inputDiv">
                    <div class="rangeContentDiv">
                        <input type="text" privateinput="true" columnname="ddhb_two_area" class="minValue">
                        	一
                        <input type="text" privateinput="true" columnname="ddhb_two_area" class="maxValue">
                        <span>平米</span>
                    </div>
                    <div class="toSearchIcon" columnname="ddhb_two_area"></div>
                </div>
                <div class="clearDiv"></div>
            </div>
            <div class="clearDiv"></div>
        </div>
        <div class="itemLine">
            <div class="searchLabelContainer">
                <div class="searchLabel" style="width:auto; padding-right:5px;" searchitemid="62">居室:</div>
                <div class="clearDiv"></div>
            </div>
            <div class="searchFieldContentDiv" fieldscontentid="62">
                <div class="searchField searchUnlimited selectedField" hassubvalue="true" columnname="ddhb_one_shi">
                    <span>不限</span>
                </div>
                <!-- 居室开始循环 -->
                <div class="searchField" ismulty="false" columnname="ddhb_one_shi" fieldvalue="1">
                    <span>一居</span>
                </div>
                <!-- 居室循环结束 -->
                
                <div class="clearDiv"></div>
            </div>
            <div class="clearDiv"></div>
        </div>
    </div>
    <div id="selectContent" style="background-color:#ffffff;">
        <div class="customerContainer" containerid="63">
            <div class="customSelect">
                <span class="label">电梯:</span>
                <div class="searchXL">
                    <div class="cs">
                        <div class="csc" selectedvalue="" columnname="ddhb_one_haslift">不限</div>
                        <div class="xljt">
                            <img src="http://www.hshb.cn/images/search/selectXL.png">
                        </div>
                        <div class="xlnr">
                            <a optionvalue="" class="unlimit">不限</a>
                            <!-- 电梯 开始 循环 -->
                            <a optionvalue="1">有电梯</a>
                            <!-- 电梯 结束 循环 -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="customerContainer" containerid="64">
            <div class="customSelect">
                <span class="label">装修:</span>
                <div class="searchXL">
                    <div class="cs">
                        <div class="csc" selectedvalue="" columnname="ddhb_one_decoration.erpId">不限</div>
                        <div class="xljt">
                            <img src="http://www.hshb.cn/images/search/selectXL.png">
                        </div>
                        <div class="xlnr">
                            <a optionvalue="" class="unlimit">不限</a>
                            <!-- 装修 开始循环 -->
                            <a optionvalue="1">精装</a>
                            <!-- 专修 循环 结束 -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="customerContainer" containerid="65">
            <div class="customSelect">
                <span class="label">税费:</span>
                <div class="searchXL">
                    <div class="cs">
                        <div class="csc" selectedvalue="" columnname="ddhb_one_tags">不限</div>
                        <div class="xljt">
                            <img src="http://www.hshb.cn/images/search/selectXL.png">
                        </div>
                        <div class="xlnr">
                            <a optionvalue="" class="unlimit">不限</a>
                            <a optionvalue="b91edfdd-5d11-48c0-af89-211ec3ae53fc">免双税</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="clearDiv"></div>
    </div>
			<!-- 查询条件结束 -->		
		</div>
    </div>
	<!-- END -->
	<input type="hidden" id="Brand" value="0" /> 
	<input type="hidden" id="Price" value="0" />
	<input type="hidden" id="Size" value="0" />
	<input type="hidden" id="loginStatus" value="<c:if test="${LoginMember == null }">no</c:if>" />
	
	<img id="searchPicture" src="" style="display:none;"/>

	<div class="houseList">
		<div class="lbt"><span>共有<span id="secondTotals" style="float:none; padding-right:0px; color:#f60; font-weight:400;">${pageBean.totalRows}</span>套房源</span>
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
		<a id="checkbtn1" onclick="saveCookies('imgShape');" href="javascript:void(0);"  class="a_1 lbta">图片模式</a>
		<a id="checkbtn2" onclick="saveCookies('dataShape');" href="javascript:void(0);" class="a_2 a_2a ">列表模式</a></div>
		--%>
		<%-- <a id="checkbtn1" class="a_1 lbta">图片模式</a>--%>
		<a id="checkbtn2" style="margin-left:5px;" class="a_2 a_2a ">列表模式</a></div>
		
		<div style="clear: both;"></div>
		<div id="changelist">
			<%@include file="/WEB-INF/jsp/ddhb/front/community/community_houseSecond_list.jsp" %>
		</div>
	</div>
</form>
