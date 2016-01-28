<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.navigationdiv {font-size: 12px; color: #999999; margin-left: 10px; height: 20px;}
.checkedbar {float: left; width: 100%; margin-top: 20px;}
.checkedbar div {width: 128px; float: left; height: 100%; color: #ffffff; text-align: center; line-height: 42px;}
.searchMB{height:36px;width:100%; background-color:green;}
.MI{height:36px; float:left;width:130px;line-height: 36px; text-align: center;cursor: pointer; color: white; font-size: 12px;}
.MISelect{background-color: white; border-top: 4px solid green;margin-top: -4px; color: black;}
</style>

<script type="text/javascript">
$(document).ready(function(){
	if ('${pageType}' == 1){
		$(".searchMB").css("background-color", "rgb(63, 184, 177)");
		$(".MISelect").css("border-top-color", "rgb(63, 184, 177)");
	}
	if ('${pageType}' == 2){
		$(".searchMB").css("background-color", "rgb(255, 155, 75)");
		$(".MISelect").css("border-top-color", "rgb(255, 155, 75)");
	}
	if ('${pageType}' == 3){
		$(".searchMB").css("background-color", "rgb(117, 190, 64)");
		$(".MISelect").css("border-top-color", "rgb(117, 190, 64)");
	}
});
</script>

<div id="mainSearchDiv">
	<div style="margin-top: 20px; ">
		<div class="navigationdiv"><a onclick='window.location.href="${globalUrl}welcome.show?actionMethod=welcome"' style="cursor: pointer;">首页</a> > 搜索</div>
	</div>
	
	<div class="searchMB">
		<div class="MI <c:if test='${pageType == 1 }'>MISelect</c:if>" id="buttonSecond" onclick="window.location.href='${globalUrl}houseSearch.show?actionMethod=doSearch&searchInput=${searchinput}&houseType=1'">二手房</div>
		<div class="MI <c:if test='${pageType == 2 }'>MISelect</c:if>" id="buttonRent" onclick="window.location.href='${globalUrl}houseSearch.show?actionMethod=doSearch&searchInput=${searchinput}&houseType=2'">租赁房</div>
		<div class="MI <c:if test='${pageType == 3 }'>MISelect</c:if>" id="buttonCommunity" onclick="window.location.href='${globalUrl}houseSearch.show?actionMethod=doSearch&searchInput=${searchinput}&houseType=3'">小区</div>
		<div style="clear: both;"></div>
	</div>
	
	<div style="clear: both;"></div>
	<c:if test="${pageType eq 1 }">
		<div id="houseSecondListDiv">
			<%@include file="/WEB-INF/jsp/ddhb/front/house/house_search_list_show.jsp"%>
		</div>
	</c:if>

	<c:if test="${pageType eq 2 }">
		<div id="houseRentListDiv">
			<%@include file="/WEB-INF/jsp/ddhb/front/rent/house_rent_search_list.jsp"%>
		</div>
	</c:if>

	<c:if test="${pageType eq 3 }">
		<div id="houseCommunityListDiv">
			<%@include file="/WEB-INF/jsp/ddhb/front/community/community_search_list_show.jsp"%>
		</div>
	</c:if>
</div>