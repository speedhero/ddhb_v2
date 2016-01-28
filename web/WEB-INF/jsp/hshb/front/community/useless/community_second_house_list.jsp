<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link type="text/css" rel="stylesheet" href="${globalUrl}css/community/community_houselist.css">
<script type="text/javascript" src="${globalUrl}js/search/myMap_new.js"></script>
<script type="text/javascript" src="${globalUrl}js/housesecond/housesecond.js"></script>
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.simple.js"></script>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/searchPlugin/jquery.search.css"/>
<style>
.searchFieldContentDiv { margin-left: 72px; text-align: center; }
</style>
<script type="text/javascript">
var searchMap = new Map();
var postUrl = "";
var isopen = false;
function initSearchMap(){
	searchMap.put("type", 0);
	searchMap.put("order", "Asc");
	searchMap.put("sort", "sortIndex");
	searchMap.put("ispage", 1);
}
$(document).ready(function(){
	initSearchMap();
	searchMap.put("housetype", "1");
	searchMap.put("communityId", '${comId}');
	var str = $.parseJSON('${jsonString}');
	option={
		searchItems:str,
		url:'${globalUrl}community.show?actionMethod=dimdetailquery',
		searchMap:searchMap
	};
	$("#searchMenuDiv").createSearch(option);
});
</script>
<div style="padding-left:10px;padding-bottom:10px;background-color:#ffffff;"><div id="searchMenuDiv" style="width:100%; background-color: #ffffff;"></div></div>
<div class="checkedbar" style="background: url('${globalUrl}images/housedetail/uncheckedlist.png');">
	<div id="checkbtn1" style="cursor: pointer; color: #000000; background: url('${globalUrl}images/housedetail/checkeditem.png');" onclick="saveCookies('imgShape');">
	<div style="width: 16px; height: 16px; margin: 13px -20px 0px 30px;"><img src="${globalUrl}images/housedetail/picturechecked.png";></div>大图模式</div>
	<div id="checkbtn2" style="cursor: pointer;" onclick="saveCookies('dataShape');">
	<div style="width: 16px; height: 16px; margin: 13px -20px 0px 30px;"><img src="${globalUrl}images/housedetail/datalistuncheck.png";></div>列表模式</div>
	<div style="color: #ffffff; float: right; width: 215px; font-size: 15px;">共有 <span id="dynamichousecount" >${pageBean.totalRows}</span> 套符合要求的房子</span></div>
	<div  style="float:right; margin-top: 10px;">
		<select id="sortmodule" onchange="javascript:sortselect('${globalUrl}community.show?actionMethod=dimdetailquery')">
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
		</select>
	</div>
</div>
<div style="clear: both;"></div>
<div id="changelist">
	<%@include file="/WEB-INF/jsp/ddhb/front/house/houseSecond_search_list.jsp" %>
</div>