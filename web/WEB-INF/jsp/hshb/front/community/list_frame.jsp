<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="h" uri="/WEB-INF/tld/hshb.tld"%>

<script type="text/javascript">
_baseSearchUrl = "${globalUrl}/community.show?actionMethod=dimquery";
var comparedItemsArray;
var historyItemsArray;
//var searchInput = "";
//var searchURL = "";
var currentURL = '${sharedUrl}';
$(document).ready(function() {
	<%-- 
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
	
	createSearchArea(_conditionJson, '${globalUrl}/community.show?actionMethod=dimquery');
	--%>
	$("#privateSearchItemContent").find(".narrowIcon").attr('src', '${globalUrl}/images/search/narrow_green.png');
	document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000);

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



function startToShare(){
	startShare("community.show");
}
<%--
function changeRelativeSelect(selecctedValue) {
	sortselect();
}
 --%>
</script>
<div style="margin-top: 5px;" class="Location"><a href="${globalUrl}" style="cursor: pointer;color: #999999;">首页</a> > <a href="${globalUrl}xiaoqu">杭州小区</a></div>
<div>
	<div class="x">
		<div id="searchMenuDiv" class="companyRight" style="width:100%;"></div>
	</div>
</div>
<c:import url="/WEB-INF/jsp/hshb/front/community/search_condition.jsp" />
<div class="lbt">
	<div class="tab_co">
	  <a id="checkbtn1" class="a_1 lbta">大图模式</a>
		<a id="checkbtn2" class="a_2 a_2a lbta">列表模式</a>
	</div>
	<div class="paixu">
		<span>排序：</span>
		<span><a  href="<h:surl p='s1' />"${h:containsEntry(params, "s", "1")?"  class='one'" : ""}>默认</a></span>
		<span><a  href="<h:surl p='s7' />"${h:containsEntry(params, "s", "7")?"  class='one'" : ""}>面积</a></span>
		<span><a  href="<h:surl p='s8' />"${h:containsEntry(params, "s", "8")?"  class='one'" : ""}>价格</a></span>
		<span><a  href="<h:surl p='s9' />"${h:containsEntry(params, "s", "9")?"  class='one'" : ""}>年份</a></span>
		
	</div>
		<div class="fenPage">
			<a href="<h:surl p="n${pageBean.pageNum<=1? 1: pageBean.pageNum - 1}"/>" id="pageAId1">&lt;</a>
			<span>
				<label style="color:#f60;" id="pageLabel">${pageBean.pageNum }</label> / <label id="totalPages">${pageBean.pages}</label>
			</span>
			<a href="<h:surl p='n${pageBean.pageNum >= pageBean.pages? pageBean.pages : pageBean.pageNum + 1}'/>" id="pageAId2">&gt;</a>
		</div>
	<span>共有<span style="float:none;padding:0;" id="dynamichousecount">${pageBean.total}</span>套房源</span>
</div>
<form name="community">
	<div id="changelist">
		<%@include file="/WEB-INF/jsp/hshb/front/community/community_list.jsp" %>
	</div>
	<img id="searchPicture" src="" style="display:none;"/>
</form>