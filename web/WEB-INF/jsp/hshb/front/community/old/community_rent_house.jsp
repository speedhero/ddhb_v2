<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	$("#rentTotals").html('${pageBean.totalRows}');
</script>
<div id="image_list" style="display: none;">
	<%@include
		file="/WEB-INF/jsp/ddhb/front/community/community_rent_image_list.jsp"%>
	<div style="clear: both; height: 0px;"></div>
</div>
<input type="hidden" id="houseTotleRows" value="${housePics.totalRows}" />
<!-- 列表显示 -->

<div id="list_list" style="display: none;">
	<!--<div class="lb_lx">
		<ul>
			<li class="li_1">内部编号</li>
			<li class="li_2">标题</li>
			<li class="li_3">小区区域</li>
			<li class="li_4">室内配置</li>
			<li class="li_5">居室</li>
			<li class="li_6">面积
				<p>（平米）</p>
			</li>
			<li class="li_7">租赁方式</li>
			<li class="li_8">租金
				<p>（元/月）</p>
			</li>
		</ul>
	</div>-->
	<div class="wd_ls">
		<ul>
			<c:forEach var="house" items="${housePics.dataList}" varStatus="status">
				<li>
					<div class="bianh" onclick="window.open('${globalUrl}/rent.show?actionMethod=houseRentDetail&houseNo=${house.hourseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}')" style="cursor: pointer;">
					 <c:if test="${house.pictureUrl eq null}">
		                 <img src="${globalUrl}/image/house_no_picture.png">
		             </c:if>
		             <c:if test="${house.pictureUrl ne null }">
					     <img src="${pictureHost }${house.pictureUrl }" width="226" height="170"/>
		             </c:if>
					 <span>共${house.housePicSize}张</span>
					</div>
					<div class="wd_rt">
						<div class="nbbh">内部编号：<span>${house.hourseNo}</span></div>
						<div class="biaot" id="nameTitle"><a onclick="window.open('${globalUrl}/rent.show?actionMethod=houseRentDetail&houseNo=${house.hourseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}')">${(houseAppraise != null) ? houseAppraise.title : house.title}</a></div>
						<div class="xiaoq">
						<span>
						<a onclick="window.open('${globalUrl}/rent.show?actionMethod=dimquery&type=1&ddhb_one_community.erpId=${house.community.erpId }')">
						${house.community.communityName}
						</a>
						</span>
						<p>
						<a onclick="window.open('${globalUrl}/rent.show?actionMethod=dimquery&type=1&ddhb_one_community.region.erpId=${house.community.region.erpId}&tabIndex=0')">
						${house.community.region.countyName }
						</a> 
						- 
						<a onclick="window.open('${globalUrl}/rent.show?actionMethod=dimquery&type=1&ddhb_one_community.region.erpId=${house.community.region.erpId}&tabIndex=0&ddhb_one_community.cbd.parentCBD.websiteId=${house.community.cbd.parentCBD.websiteId}')">
						${house.community.cbd.parentCBD.name }
						</a>
						</p></div>
						<div class="maid_lb">
							<c:forEach var="furs" items="${furList}">
								<c:forEach var="furId" items="${house.furIdList}">
									<c:if test="${furs.erpId == furId}">
										<span>
										  <img src="${pictureHost}${furs.imgUrl}"  title="<c:out value='${furs.furName }'></c:out>">
										</span>
									</c:if>
								</c:forEach>
							</c:forEach>
        	 				<a housePictureUrl="${pictureHost}${house.pictureUrl }" inCompareItem="false" class="compareButton dbi_lik fr" onclick="event.cancelBubble = true;addCompareItem(this, 'rentHouseCompare')"
							 compareId="${house.hourseNo }" brokerId="${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}" compareTitle="${(houseAppraise != null) ? houseAppraise.title : house.title}"
							 area="${house.area }"  shi="${house.shi }" ting="${house.ting }" comparePrice="${house.rentPrice}" >对比</a>

							 <c:if test="${LoginMember != null }">
								<c:if test="${house.collectId != null}">
									<a id="collect${house.erpId}two" class="shouc_lik fr"  href="javascript:void(0);" brokerid="${house.broker.erpId }" style="background-color:#cb4f4d" collId="${house.collectId}" hosueobjectId="${house.erpId}" houseprice="${house.rentPrice}" isCollect="0"
									onclick="event.cancelBubble = true;isCollectRent(this);" >收藏</a>
								</c:if>
								<c:if test="${house.collectId == null}">
									<a id="collect${house.erpId}two" class="shouc_lik fr" href="javascript:void(0);" brokerid="${house.broker.erpId }" collId="${house.collectId}" hosueobjectId="${house.erpId}"
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
						<div class="danjia" style="text-align: right;">[${house.rentType.rentTypeName}]
						<br>
						<%-- 
						<c:if test="${house.previousRentPrice - house.rentPrice < 0}">已上涨${-(house.previousRentPrice - house.rentPrice)}元</c:if>
						<c:if test="${house.previousRentPrice - house.rentPrice > 0}">已降价${house.previousRentPrice - house.rentPrice }元</c:if>
						--%>
						</div>
						<div class="personCel">
							<a onclick="window.open('${globalUrl}/broker.show?actionMethod=brokerDetail&brokerId=${house.broker.erpId}&housetype=2')">${house.broker.bname}</a>
							<span>${house.broker.telephone}</span>
						</div>
						<div class="zjia"><span><fmt:formatNumber value="${house.rentPrice}" type="currency" pattern="#" /></span><p>元/月</p></div>
					   <%-- <div class="daikan">
        			
        			<span>带看${house.daikan}次</span> 
        			<p><a onclick="window.open('${globalUrl}/broker.show?actionMethod=brokerDetail&brokerId=${house.broker.erpId}&housetype=2')" >${house.broker.bname}</a> 
        	 &nbsp; ${house.broker.telephone}&nbsp;于  <fmt:formatDate value="${house.lastModified}" pattern="yyyy-MM-dd HH:mm:ss"/> 发布
        	 		</p> 
        	 		
					    </div>--%>
						<!--<div class="xq_an">
							<a housePictureUrl="${pictureHost}${house.pictureUrl }" inCompareItem="false" class="compareButton phoneCompare" onclick="event.cancelBubble = true;addCompareItem(this, 'rentHouseCompare')"
							 compareId="${house.hourseNo }" brokerId="${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}" compareTitle="${(houseAppraise != null) ? houseAppraise.title : house.title}"
							 area="${house.area }"  shi="${house.shi }" ting="${house.ting }" comparePrice="${house.rentPrice}" >对比</a>
							<c:if test="${LoginMember == null }">
							 <a href="javascript:void(0);" collId="${house.collectId}" brokerid="${house.broker.erpId }" onclick="event.cancelBubble = true;loginBox('collect');">收藏</a>
							</c:if>
							<c:if test="${LoginMember != null }">
								<c:if test="${house.collectId != null}">
									<a id="collect${house.erpId}two"  href="javascript:void(0);" brokerid="${house.broker.erpId }" style="background-color:#cb4f4d" collId="${house.collectId}" hosueobjectId="${house.erpId}" houseprice="${house.rentPrice}" isCollect="0"
									onclick="event.cancelBubble = true;isCollectRent(this);" >收藏</a>
								</c:if>
								<c:if test="${house.collectId == null}">
									<a id="collect${house.erpId}two"  href="javascript:void(0);" brokerid="${house.broker.erpId }" collId="${house.collectId}" hosueobjectId="${house.erpId}"
									houseprice="${house.rentPrice}" isCollect="1"
									onclick="event.cancelBubble = true;isCollectRent(this);" >收藏</a>
								</c:if>
							</c:if>
							<a onclick="event.cancelBubble = true;" href="rent.show?actionMethod=houseRentDetail&houseNo=${house.hourseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}" class="xq">查看详情</a>
						</div>-->
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<c:if test="${pageBean.totalRows eq 0 }">
	<h3 style="color: red; margin-top: 20px; margin-left: 50px">很抱歉，没有查询到符合要求的出租房源！</h3>
</c:if>
<div class="page">
	<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false"
		formName="houseRent" offerPageSize="20,50,100" isExistForm="true"
		queryFunction="houseChangePages()" />
</div>
