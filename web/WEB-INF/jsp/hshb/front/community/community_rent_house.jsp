<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="h" uri="/WEB-INF/tld/hshb.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style type="text/css">
#nameTitle a:hover { color: #75BE40; }
</style>
<script type="text/javascript" src="${globalUrl}js/search/myMap_new.js"></script>
<script type="text/javascript" src="${globalUrl}js/housesecond/housesecond.js"></script>
<script type="text/javascript">
var globalUrl = '${globalUrl}';
$(document).ready(function() {
	var idStr = $.cookie("lastSelected");
	if (idStr == "imgShape") {
		$("#checkbtn1").addClass("a_1a");
		$("#checkbtn2").removeClass("a_2a");
		$("#checkbtn2").removeAttr("style").css("cursor", "pointer");
		$('#image_list').css("display", "block");
		$('#list_list').css("display", "none");
		setType0();
	} else {
		$("#checkbtn2").addClass("a_2a");
		$("#checkbtn1").removeClass("a_1a");
		$("#checkbtn1").removeAttr("style").css("cursor", "pointer"); 
		$('#list_list').css("display", "block");
		$('#image_list').css("display", "none");
		setType1();
	}
	if (comparedItemsArray && comparedItemsArray.length > 0){
		for (var kk = 0; kk < comparedItemsArray.length > 0; kk++){
			$("img[compareid='" + comparedItemsArray[kk].id + "']").attr("incompareitem", "true");
			$("img[compareid='" + comparedItemsArray[kk].id + "']").attr("src", "${globalUrl}images/compare/selectedCompareId.png");
		}
	}
});

</script>
<script type="text/javascript">
	$("#rentTotals").html('${pageBean.total}');
</script>
<div id="image_list" style="display: none;">
	<%--@include file="/WEB-INF/jsp/hshb/front/community/community_rent_image_list.jsp"--%>
	<div style="clear: both; height: 0px;"></div>
</div>
<input type="hidden" id="houseTotleRows" value="${pageBean.total}" />
<!-- 列表显示 -->

<div id="list_list" >
	<div class="wd_ls">
		<ul>
			<c:forEach var="house" items="${houseList}" varStatus="status">
				<li>
					<div class="bianh"  style="cursor: pointer;">
					<a href="${globalUrl}chuzu/${house.houseId}.html" target="_blank">
						<img src="${house.coverImage eq null? globalUrl.concat('/image/house_no_picture.png') : pictureHost.concat(house.coverImage)}" width="226" height="170">					
						<span>共${house.pictureCount}张</span>
						</a>
					</div>
					<div class="wd_rt">
						<div class="nbbh">内部编号：<span>${house.houseId}</span></div>
						<div class="biaot" id="nameTitle">
						<a href="${globalUrl}chuzu/${house.houseId}.html" target="_blank">${house.title}</a>
						</div>
						<div class="xiaoq">
						<span>
						<a href="<h:surl p='q${house.communityId}' />">${house.communityName}</a>
						</span>
						<p>
						<a href="<h:surl p="a_${house.countyId}_"/>">${house.countyName }</a> - <a href="<h:surl p="a_${house.countyId}_${house.cbdWebsiteId}"/>">
									${house.cbdWsiteName }</a>
						</p></div>
						<div class="maid_lb">
							<c:forEach var="furs" items="${searchItems}">
								<c:if test="${items.id eq 81}">
									<c:forEach var="field" items="${items.fields }" varStatus="s2">
										<c:if test="${fn:contains(house.furniture, field.id) }">
											<span>
												<img src="${pictureHost }${field.maxfieldvalue }"
												title="${field.fieldname}">
											</span>
										</c:if>
									</c:forEach>
								</c:if>
							</c:forEach>
        	 				<a housePictureUrl="${pictureHost}${house.coverImage }" 
        	 				inCompareItem="false" class="compareButton dbi_lik fr" 
        	 				onclick="event.cancelBubble = true;addCompareItem(this, 'rentHouseCompare')"
							 compareId="${house.houseId }" 
							 brokerId="${house.publisherId}" 
							 compareTitle="${house.title}"
							 area="${house.area }"  shi="${house.shi }" ting="${house.ting }" comparePrice="${house.rentPrice}" >对比</a>

							 <c:if test="${LoginMember ne null }">
								<c:if test="${house.collectId != null}">
									<a id="collect${house.erpId}two" class="shouc_lik fr"  
									href="javascript:void(0);" 
									brokerid="${house.publisherId }" style="background-color:#cb4f4d" 
									collId="${house.collectId}" hosueobjectId="${house.erpId}" 
									houseprice="${house.rentPrice}" isCollect="0"
									onclick="event.cancelBubble = true;isCollectRent(this);" >收藏</a>
								</c:if>
								<c:if test="${house.collectId == null}">
									<a id="collect${house.erpId}two" class="shouc_lik fr" href="javascript:void(0);"
									 brokerid="${house.publisherId  }" collId="${house.collectId}" hosueobjectId="${house.erpId}"
									houseprice="${house.rentPrice}" isCollect="1"
									onclick="event.cancelBubble = true;isCollectRent(this);" >收藏</a>
								</c:if>
							</c:if>
							<c:if test="${LoginMember == null }">
								<a class="shouc_lik fr" href="javascript:loginBox2();">收藏</a>
								<a onclick="loginBox()"></a>
							</c:if>
						</div>
						<div class="jushi">${house.shi}室${house.ting}厅</div>
						<div class="mianji"><fmt:formatNumber value="${house.area}" type="currency" pattern="#" />平米</div>
						<div class="danjia" style="text-align: right;">
							<c:forEach var="item" items="${searchItems }" varStatus="s1">
								<c:if test="${item.id eq 80 }">
									<c:forEach var="field" items="${item.fields}" varStatus="s2">
										<c:if test="${house.rentTypeId eq field.id }">[${field.fieldname}]</c:if>
									</c:forEach>
								</c:if>
							</c:forEach>
						<br>
						<%-- 
						<c:if test="${house.previousRentPrice - house.rentPrice < 0}">已上涨${-(house.previousRentPrice - house.rentPrice)}元</c:if>
						<c:if test="${house.previousRentPrice - house.rentPrice > 0}">已降价${house.previousRentPrice - house.rentPrice }元</c:if>
						--%>
						</div>
						<div class="personCel">
							<a href="${globalUrl}broker/${house.publisherId }.html" target="_blank" >${house.agentName}</a>
							<span>${house.telephone}</span>
						</div>
						<div class="zjia"><span><fmt:formatNumber value="${house.rentPrice}" type="currency" pattern="#" /></span><p>元/月</p></div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<c:if test="${houseList.size() eq 0 }">
	<h3 style="color: red; margin-top: 20px; margin-left: 50px">很抱歉，没有查询到符合要求的出租房源！</h3>
</c:if>
<div class="page">
		<c:import url="/WEB-INF/jsp/hshb/front/commonInfoShow/pager.jsp" />
</div>
