<%@page import="com.huatek.hbwebsite.util.FrontSystemSettingUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="h" uri="/WEB-INF/tld/hshb.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript" src="${globalUrl}js/search/myMap_new.js"></script>
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.simple.js"></script>
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.simple.phone.js"></script>
<script type="text/javascript" src="${globalUrl}js/housesecond/housesecond.js"></script>
<script type="text/javascript" src="${globalUrl }js/compare/communityCompare.js"></script>
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
	createSearchArea(_conditionJson, '${globalUrl}community.show?actionMethod=dimdetailquery');
	
	reloadCompareDiv("secondHouseCompare","${globalUrl}");
	reloadHistoryDiv("secondHouseHistory","${globalUrl}",$("#loginStatus").val());
	reInitCompareDiv("secondHouseCompare","${globalUrl}");
	
	//手动输入条件,查询
	$(".inputDiv").each(function(){
		var inputDiv = $(this);
		$(this).find(".toSearchIcon").click(function(){
			var minValue = inputDiv.find(".minValue").val();
			var maxValue = inputDiv.find(".maxValue").val();
			if (!maxValue){
				alert("最大值,必须要填")
				return false;
			}
			if(!minValue || minValue < 0 )
				minValue = 0;
			if(minValue > maxValue){
				alert("最小值必须小于最大值");
				return false;
			}
			var unit = $(this).attr("unitname");
			var jump = $(this).attr("jump");
			location.href = jump  + unit + minValue + "_" + maxValue;
			//alert(jump + "/" + unit + minValue + "-" + maxValue);
		})
	});
	
	//选中下拉框  查询
	$("select").change(function(){
		
		//alert($(this).find("option:selected").val());
		location.href = $(this).find("option:selected").val();
	});
});
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
                <div class="searchField searchUnlimited ${h:containsKey(params, 'p')?'':'selectedField'}" hassubvalue="true" columnname="ddhb_two_price">
                    <span><a href="<h:surl p="p"/>">不限</a></span>
                </div>
                <!-- 价格 开始循环 -->
               <c:forEach var="items" items="${searchItems}" varStatus="s1">
					<c:if test="${items.id==17}">
						<c:forEach var="fields" items="${items.fields}" varStatus="s2">
							<c:set var="_price" value="${fields.minfieldvalue}-${fields.maxfieldvalue}" />
							<div class="searchField ${h:containsEntry(params, 'p', _price)? 'selectedField' : ''}" 
								fieldvalue="${fields.minfieldvalue}@${fields.maxfieldvalue}">
								<span><a href="<h:surl p="p${_price}"/>">${fields.fieldname}</a></span>
							</div>		
						</c:forEach>	
					</c:if>
				</c:forEach>
                <!-- 价格 结束循环 -->
               
                <div class="inputDiv">
                    <div class="rangeContentDiv">
                        <input type="text" privateinput="true" columnname="ddhb_two_price" class="minValue">
                       		 -
                        <input type="text" privateinput="true" columnname="ddhb_two_price" class="maxValue">
                        <span>万元</span>
                    </div>
                    <div class="toSearchIcon" columnname="ddhb_two_price" unitname="p" jump="<h:surl p="p" />"></div>
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
                <div class="searchField searchUnlimited ${h:containsKey(params, 'a')?'':'selectedField'}" hassubvalue="true" columnname="ddhb_two_area">
                    <span><a href="<h:surl p="a"/>">不限</a></span>
                </div>
                <!-- 面积 开始 循环 -->
               <c:forEach var="items" items="${searchItems}" varStatus="s1">
					<c:if test="${items.id==18}">
						<c:forEach var="fields" items="${items.fields}" varStatus="s2">
				               <c:set var="_area" value="${fields.minfieldvalue}-${fields.maxfieldvalue}" />
				                <div class="searchField ${h:containsEntry(params, 'a', _area)? 'selectedField' : ''}" 
				                	isrange="true" 
				                	isprivateitem="false" 
				                	hassubvalue="false" 
				                	columnname="ddhb_two_area" 
				                	fieldvalue="${fields.minfieldvalue}@${fields.maxfieldvalue}"><span><a href="<h:surl p="a${_area}"/>">${fields.fieldname}</a></span></div>
						</c:forEach>
					</c:if>
				</c:forEach>
                <!-- 面积 结束 循环 -->
                <div class="inputDiv">
                    <div class="rangeContentDiv">
                        <input type="text" privateinput="true" columnname="ddhb_two_area" class="minValue">
                        	-
                        <input type="text" privateinput="true" columnname="ddhb_two_area" class="maxValue">
                        <span>平米</span>
                    </div>
                    <div class="toSearchIcon" columnname="ddhb_two_area" unitname="a" jump="<h:surl p="a"/>" ></div>
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
                <div class="searchField searchUnlimited ${h:containsKey(params, 'r')?'':'selectedField'}" hassubvalue="true" columnname="ddhb_one_shi">
                    <span> <a href="<h:surl p="r"/>"> 不限</a></span>
                </div>
                <!-- 居室开始循环 -->
              <c:forEach var="items" items="${searchItems}" varStatus="s1">
					<c:if test="${items.id==19}">
						<c:forEach var="fields" items="${items.fields}" varStatus="s2">
				                <div class="searchField ${h:containsEntry(params, 'zj', s2.count.toString())? 'selectedField' : ''}" 
				                	isprivateitem="false" 
				                	hassubvalue="false" 
				                	ismulty="false" 
				                	columnname="ddhb_one_shi" 
				                	fieldvalue="${fields.id}"><span><a href="<h:surl p="r${fields.id}"/>">${fields.fieldname}</a></span></div>
						</c:forEach>
					</c:if>
				</c:forEach>
                <!-- 居室循环结束 -->
                
                <div class="clearDiv"></div>
            </div>
            <div class="clearDiv"></div>
        </div>
    </div>
    <div id="selectContent" style="background-color:#ffffff;">
        <div class="customerContainer" containerid="64">
            <select name="ddhb_one_decoration.erpId" 
	              columnname="ddhb_one_decoration.erpId" 
	              searchitemid="22" id="22" 
	              onchange="document.location.href=this.value;;">
                <option value="<h:surl p="z${fields.id}"/>">装修</option>
					<c:forEach var="items" items="${searchItems}" varStatus="s1">
						<c:if test="${items.id==22}">
							<c:forEach var="fields" items="${items.fields}" varStatus="s2">
								<option value="<h:surl p="z${fields.id}"/>">${fields.fieldname}</option>
							</c:forEach>
						</c:if>
					</c:forEach>
              </select>
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
		<div class="lbt"><span>共有<span id="secondTotals" style="float:none; padding-right:0px; color:#f60; font-weight:400;">${pageBean.total}</span>套房源</span>
		<div id="selectorder" style="float:right; margin-top:5px; margin-right:10px;">
			<div class="paixu">
				<span>排序：</span>
				<span><a id="orderRules1" href="<h:surl p='s1' />"${h:containsEntry(params, "s", "1")?"  class='one'" : ""}>默认</a></span>		
				<span><a id="orderRules2" href="<h:surl p='s2' />"${h:containsEntry(params, "s", "2")?"  class='one'" : ""}>面积</a></span>
				<span><a id="orderRules3" href="<h:surl p='s3' />"${h:containsEntry(params, "s", "3")?"  class='one'" : ""}>总价</a></span>
				<span><a id="orderRules4" href="<h:surl p='s4' />"${h:containsEntry(params, "s", "4")?"  class='one'" : ""}>单价</a></span>
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
			<%@include file="/WEB-INF/jsp/hshb/front/community/community_houseSecond_list.jsp" %>
		</div>
	</div>
</form>
