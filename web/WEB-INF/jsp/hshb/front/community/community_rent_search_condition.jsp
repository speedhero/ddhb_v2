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
	
	
	createSearchArea(_conditionJson, '${globalUrl}community.show?actionMethod=dimdetailquery');
	
	reloadCompareDiv("rentHouseCompare","${globalUrl}");
	reloadHistoryDiv("rentHouseHistory","${globalUrl}",$("#loginStatus").val());
	reInitCompareDiv('rentHouseCompare','${globalUrl}');
	
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
	})
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
		<div id="searchMenuDiv" style="width:100%;">
			<div id="searchContent">
      <div class="itemLine">
        <div class="searchLabelContainer">
          <div class="searchLabel" style="width:auto; padding-right:5px;" searchitemid="67">租金:</div>
          <div class="clearDiv"></div>
        </div>
        <div class="searchFieldContentDiv" fieldscontentid="67">
          <div class="searchField searchUnlimited ${h:containsKey(params, 'p')?'':'selectedField'}" hassubvalue="true"
          columnname="ddhb_two_rentPrice">
            <span><a href="<h:surl p='p' />">不限</a></span>
          </div>
          <!-- 租金循环开始 -->
			<c:forEach var="items" items="${searchItems}" varStatus="s1">
				<c:if test="${items.id==35}">
					<c:forEach var="field" items="${items.fields}" varStatus="s2">
						<c:set var="_price" value="${field.minfieldvalue}-${field.maxfieldvalue}" />
						<div class="searchField ${h:containsEntry(params, 'p', _price)? 'selectedField' : ''}" 
						fieldvalue="${field.minfieldvalue}@${field.maxfieldvalue}">
							<span><a href="<h:surl p="p${_price}"/>">${field.fieldname}</a></span>
						</div>
					</c:forEach>	
				</c:if>
			</c:forEach>
          <!-- 租金循环结束 -->
          <div class="inputDiv">
            <div class="rangeContentDiv">
              <input type="text" privateinput="true" columnname="ddhb_two_rentPrice" class="minValue">
              		-
              <input type="text" privateinput="true" columnname="ddhb_two_rentPrice" class="maxValue">
              <span>元/月</span>
            </div>
            <div class="toSearchIcon" unitname="p" columnname="ddhb_two_rentPrice" jump="<h:surl p="p"/>"></div>
          </div>
          <div class="clearDiv"></div>
        </div>
        <div class="clearDiv"></div>
      </div>
      <div class="itemLine">
        <div class="searchLabelContainer">
          <div class="searchLabel" style="width:auto; padding-right:5px;" searchitemid="68">面积:</div>
          <div class="clearDiv"></div>
        </div>
        <div class="searchFieldContentDiv" fieldscontentid="68">
          <div class="searchField searchUnlimited ${h:containsKey(params, 'a')?'':'selectedField'}" hassubvalue="true"
          columnname="ddhb_two_area">
            <span><a href="<h:surl p="a"/>">不限</a></span>
          </div>
          <!-- 面积开始循环 -->
			<c:forEach var="items" items="${searchItems}" varStatus="s1">
				<c:if test="${items.id==36}">
					<c:forEach var="field" items="${items.fields}" varStatus="s2">
						<c:set var="_area" value="${field.minfieldvalue}-${field.maxfieldvalue}" /> 
							<div class="searchField ${h:containsEntry(params, 'a', _area)? 'selectedField' : ''} " 
							columnname="ddhb_two_area" fieldvalue="${field.minfieldvalue}@${field.maxfieldvalue}">
							<span><a href="<h:surl p="a${_area}"/>">${field.fieldname}</a></span>
							</div>
					</c:forEach>
				</c:if>
			</c:forEach>
          <!-- 面积结束循环 -->
          
          <div class="inputDiv">
            <div class="rangeContentDiv">
              <input type="text" privateinput="true" columnname="ddhb_two_area" class="minValue">
              		-
              <input type="text" privateinput="true" columnname="ddhb_two_area" class="maxValue">
              <span>平米	</span>
            </div>
            <div class="toSearchIcon" columnname="ddhb_two_area" unitname="a" jump="<h:surl p="a"/>" ></div>
          </div>
          <div class="clearDiv"></div>
        </div>
        <div class="clearDiv"></div>
      </div>
      <div class="itemLine">
        <div class="searchLabelContainer">
          <div class="searchLabel" style="width:auto; padding-right:5px;" searchitemid="69">居室:</div>
          <div class="clearDiv"></div>
        </div>
        <div class="searchFieldContentDiv" fieldscontentid="69">
          <div class="searchField searchUnlimited ${h:containsKey(params, 'r')?'':'selectedField'}" hassubvalue="true"
          columnname="ddhb_one_shi">
            <span><a href="<h:surl p="r"/>">不限</a></span>
          </div>
          <!-- 居室循环开始 -->
			<c:forEach var="items" items="${searchItems}" varStatus="s1">
				<c:if test="${items.id==37}">
					<c:forEach var="field" items="${items.fields}" varStatus="s2">
						<c:set var="count" value="${s2.count} " />
						<div class="searchField ${h:containsEntry(params, 'r', count.trim())? 'selectedField' : ''}" 
						columnname="ddhb_one_shi" 
						fieldvalue="${field.id}"><span><a href="<h:surl p="r${s2.count}"/>">${field.fieldname}</a></span></div>
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
      <div class="customerContainer" containerid="71">
        <div class="customSelect">
          <div class="searchXL">
            <div class="cs">
           		 <select name="ddhb_one_decoration.erpId" id="40" 
           		 onchange="searchCondition('ddhb_one_decoration.erpId');">
                               <option value="<h:surl p="z${fields.id}"/>">装修</option>
                                <c:forEach var="items" items="${searchItems}" varStatus="s1">
									<c:if test="${items.id==40}">
										<c:forEach var="field" items="${items.fields}" varStatus="s2">
										<c:set var="count" value="${s2.count} " />
											<option value="<h:surl p="z${field.id}"/>" ${h:containsEntry(params, "z", count.trim())? "selected='selected'" :""} >${field.fieldname}</option>
										</c:forEach>
									</c:if>
								</c:forEach>
                            </select>
            </div>
          </div>
        </div>
      </div>
      <div class="clearDiv"></div>
    </div>
		</div>
    </div>
	<!-- END -->
	<input type="hidden" id="Brand" value="0" /> 
	<input type="hidden" id="Price" value="0" />
	<input type="hidden" id="Size" value="0" />
	<input type="hidden" id="loginStatus" value="<c:if test="${LoginMember == null }">no</c:if>" />
	
	<img id="searchPicture" src="" style="display:none;"/>

	<div class="houseList">
		<div class="lbt"><span>共有<span id="rentTotals" style="float:none; color:#f60; font-weight:400; padding-right:0px;">${pageBean.total}</span>套出租房</span>
			<div class="paixu">
				<span>排序：</span>
				<span><a id="orderRules1" href="<h:surl p='s1' />"${h:containsEntry(params, "s", "1")?"  class='one'" : ""}>默认</a></span>		
				<span><a id="orderRules2" href="<h:surl p='s2' />"${h:containsEntry(params, "s", "2")?"  class='one'" : ""}>面积</a></span>
				<span><a id="orderRules3" href="<h:surl p='s5' />"${h:containsEntry(params, "s", "5")?"  class='one'" : ""}>年份</a></span>
				<span><a id="orderRules4" href="<h:surl p='s6' />"${h:containsEntry(params, "s", "6")?"  class='one'" : ""}>租金</a></span>
				
			</div>
		<%-- <a id="checkbtn1" onclick="saveCookies('imgShape');" href="javascript:void(0);" class="a_1 lbta">大图模式</a>
		<a id="checkbtn2" onclick="saveCookies('dataShape');" href="javascript:void(0);" class="a_2 a_2a ">列表模式</a></div>
		--%>
		<%-- <a id="checkbtn1" class="a_1 lbta">大图模式</a>--%>
		<a id="checkbtn2" style="margin-left:5px;" class="a_2 a_2a ">列表模式</a></div>

		<div style="clear: both;"></div>
		<div id="changelist">
			<%@include file="/WEB-INF/jsp/hshb/front/community/community_rent_house.jsp" %> 
		</div>
	</div>
</form>
