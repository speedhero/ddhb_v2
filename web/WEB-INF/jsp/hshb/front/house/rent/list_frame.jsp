<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="h" uri="/WEB-INF/tld/hshb.tld"%>
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
_baseSearchUrl = "${globalUrl}/rent.show?actionMethod=dimquery";
var comparedItemInCookie;
var historyItemsArray;
var comparedItemsArray
var searchInput = "";
var searchURL = "";
--%>
$(document).ready(function(){
	
	$("#privateSearchItemContent").find(".narrowIcon").attr('src', '${globalUrl}/images/search/narrow_yellow.png');
	//比较
	//delCookie("rentHouseCompare");
	var comparedItemInCookie = '${rentHouseCompare}';
	if (comparedItemInCookie == ''){
		comparedItemsArray=new Array();
	}else{
		comparedItemsArray = JSON.parse(comparedItemInCookie); 
	}
  initComparedItems(comparedItemsArray, "rentHouseCompare");
  //历史
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
<%--
//分享
function startToShare(){
	startShare("rent.show");
}
--%>
</script>

<form name="houseRent">
<div style="margin-top: 5px;">
	<div class="Location">
	<a href="${globalUrl}" style="cursor: pointer;">首页</a> > <a href="${globalUrl}chuzu">杭州租房</a></div>
</div>

	<div class="x">
		<div id="searchMenuDiv" class="companyRight" style="width:100%; margin-top:12px;"></div>
    </div>
      <%-- <%@include file="/WEB-INF/jsp/ddhb/front/ad/adTopBar.jsp" %> --%>
      <c:import url="/WEB-INF/jsp/hshb/front/house/rent/search_condition.jsp" /><%-- --%> 
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
			<div class="paixu">
				<span>排序：</span>
				<span><a id="orderRules1" href="<h:surl p='s1' />"${h:containsEntry(params, "s", "1")?"  class='one'" : ""}>默认</a></span>		
				<span><a id="orderRules2" href="<h:surl p='s2' />"${h:containsEntry(params, "s", "2")?"  class='one'" : ""}>面积</a></span>
				<span><a id="orderRules3" href="<h:surl p='s5' />"${h:containsEntry(params, "s", "5")?"  class='one'" : ""}>年份</a></span>
				<span><a id="orderRules4" href="<h:surl p='s6' />"${h:containsEntry(params, "s", "6")?"  class='one'" : ""}>租金</a></span>
				
			</div>
			 <div class="fenPage">
			 	<a href="<h:surl p="n${pageBean.pageNum<=1? 1: pageBean.pageNum - 1}"/>" id="pageAId1">&lt;</a>
				<span>
					<label style="color:#f60;" id="pageLabel">${pageBean.pageNum }</label> / <label id="totalPages">${pageBean.pages}</label>
				</span>
				<a href="<h:surl p='n${pageBean.pageNum >= pageBean.pages? pageBean.pages : pageBean.pageNum + 1}'/>" id="pageAId2">&gt;</a>
			</div>
			<span>共有<span style="float:none;padding:0;" id="dynamichousecount">${pageBean.total}</span>套房源</span>
			<%-- END 
		    <div style="float:right;padding-right:10px; text-align:left;"><span style="float:left;">共有</span><span style="color:#f60; float:left; font-weight:bold;" id="dynamichousecount">${pageBean.totalRows}</span><span style="float:left;">套房源</span></div>
			--%>
	</div>
		<div style="clear: both;"></div>
		<div id="changelist">
			<%--<%@include file="/WEB-INF/jsp/ddhb/front/rent/houseRent_search_list.jsp"  --%>
			<c:import url="/WEB-INF/jsp/hshb/front/house/rent/house_list.jsp"></c:import>
		</div>
		<img id="searchPicture" src="" style="display:none;"/>
	</div>
	
	<%@include file="/WEB-INF/jsp/ddhb/front/ad/adButtompBar.jsp" %>
	
</form>
