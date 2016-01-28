<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style type="text/css">
#nameTitle a:hover{color: #75BE40;}
</style>
<script type="text/javascript" src="${globalUrl}js/search/myMap_new.js"></script>
<script type="text/javascript" src="${globalUrl}js/housesecond/housesecond.js"></script>
<script type="text/javascript" src="${globalUrl}js/detail/detail.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	//根据cookie确定显示大图模式还是列表模式，默认是列表模式
	var lastDisplayType = $.cookie("lastSelected");
	if (lastDisplayType == "imgShape") {
		$("#checkbtn2").removeAttr("style").css("cursor", "pointer");
		$('#image_list').css("display", "block");
		$('#list_list').css("display", "none");
		setType1();
	} else {
		$("#checkbtn1").removeAttr("style").css("cursor", "pointer"); 
		$('#list_list').css("display", "block");
		$('#image_list').css("display", "none");
		setType0();
	}
	if (comparedItemsArray && comparedItemsArray.length > 0){
		for (var kk = 0; kk < comparedItemsArray.length > 0; kk++){
			$("img[compareid='" + comparedItemsArray[kk].id + "']").attr("incompareitem", "true");
			$("img[compareid='" + comparedItemsArray[kk].id + "']").attr("src", "${globalUrl}images/compare/selectedCompareId.png");
		}
	}
});
function isCollectHouseSend(obj){
	var collObj = $(obj);
	if(collObj.attr("isCollect")==0) {
	//取消收藏
		keepOff(collObj.attr("id"), collObj.attr("collId"));	
	} else {
		//添加收藏
		keepOppen(collObj.attr("id"), "3", collObj.attr("hosueobjectId"), collObj.attr("brokerId"), collObj.attr("houseprice"));
	}
}
/*
function keepOppen(searchno, priceCc, id){
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=addtoCollection";
	var platCollection = { ObjectId: searchno, collectType: "0", priceCc: priceCc };
	_keepOppen(actionUrl, platCollection, '3', id);
}

function keepOff(collectId ,id){
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=cancleMyCollection&collectionId=" + collectId; 
	_keepOff(actionUrl, id, 0);
}

*/
</script>
<script type="text/javascript">
$("#secondTotals").html('${pageBean.totalRows}');
</script>
<div id="image_list" style="display:none;">
	<%@include file="/WEB-INF/jsp/ddhb/front/community/community_houseSecond_image_list.jsp"%>
	<div style="clear: both; height: 0px;"></div>
</div>
<input type="hidden" id="houseTotleRows" value="${pageBean.totalRows}"/>
<!-- 列表显示 -->
<div id="list_list" style="display: none;">
	<div class="lb_lx" style="display: none;">
		<ul>
			<li class="li_1">内部编号</li>
			<li class="li_2">标题</li>
			<li class="li_3">小区区域</li>
			<li class="li_4">房源卖点</li>
			<li class="li_5">居室</li>
			<li class="li_6">面积<p>（平米）</p></li>
			<li class="li_7">单价<p>（元/平米）</p></li>
			<li class="li_8">总价<p>（万元）</p></li>
		</ul>
	</div>
	<!--列表抬头结束-->
	<!--文字列表开始-->
	<div class="wd_ls">
		<ul>
			<c:forEach var="house" items="${pageBean.dataList}"
				varStatus="status">
				<li>
					<div class="bianh"
						onclick="window.open('${globalUrl}/houseSecond.show?actionMethod=houseSecondDetail&houseNo=${house.houseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}')"
						style="cursor: pointer;">
						<c:if test="${house.pictureUrl eq null}"><img src="${globalUrl}/image/house_no_picture.png"></c:if>
						<c:if test="${house.pictureUrl ne null }"><img src="${pictureHost }${house.pictureUrl }" width="226" height="170"></c:if>
						<span>共${house.housePicSize}张</span>
					</div>
					<div class="wd_rt">
						<div class="nbbh">
							内部编号：<span>${house.houseNo}</span>
						</div>
						<div class="biaot">
							<a onclick="window.open('${globalUrl}/houseSecond.show?actionMethod=houseSecondDetail&houseNo=${house.houseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}')">${(houseAppraise != null) ? houseAppraise.title : house.title}</a>
						</div>
						<div class="xiaoq">
							<span> <a onclick="window.open('${globalUrl}/houseSecond.show?actionMethod=dimquery&type=1&ddhb_one_community.erpId=${house.community.erpId }')">${house.community.communityName}
							</a>
							</span>
						</div>
						<div class="maid">
							<c:forEach items="${tagList}" var="tags">
								<c:forEach items="${house.tagIdList}" var="tagId">
									<c:if test="${tags.erpId == tagId}">
										<a onclick="window.open('${globalUrl}/houseSecond.show?actionMethod=dimquery&type=1&ddhb_one_tags=${house.tags}')">
											<span style="background-color:${tags.tagColor}; color:${tags.fontColor};">&nbsp;${tags.tagName}&nbsp;</span>
										</a>
									</c:if>
								</c:forEach>
							</c:forEach>
							<!-- 对比 和 收藏 START -->
							<a housePictureUrl="${pictureHost}${house.pictureUrl }"
								inCompareItem="false"
								onclick="event.cancelBubble = true;addCompareItem(this, 'secondHouseCompare')"
								compareId="${house.houseNo }"
								brokerId="${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}"
								compareTitle="${(houseAppraise != null) ? houseAppraise.title : house.title}"
								area="${house.area }" shi="${house.shi }" ting="${house.ting }"
								comparePrice="${house.price}"
								compareUnitPrice="${house.unitPrice}" class="dbi_lik fr">对比</a>
							<c:if test="${LoginMember == null }">
								<a href="javascript:void(0);" collId="${house.collectId}"
									hosueobjectId="${house.objectId}" class="shouc_lik fr"
									brokerId="${house.broker.erpId }" houseprice="${house.price}"
									onclick="event.cancelBubble = true;loginBox('collect');">收藏</a>
							</c:if>
							<c:if test="${LoginMember != null }">
								<c:if test="${house.collectId != null}">
									<a id="collect${house.erpId}two" href="javascript:void(0);"
										collId="${house.collectId}" hosueobjectId="${house.objectId}"
										houseprice="${house.price}" isCollect="0"
										brokerId="${house.broker.erpId }" class="shouc_lik fr"
										onclick="event.cancelBubble = true;isCollectHouseSend(this);">收藏</a>
								</c:if>
								<c:if test="${house.collectId == null}">
									<a id="collect${house.erpId}two" href="javascript:void(0);"
										collId="${house.collectId}" hosueobjectId="${house.objectId}"
										houseprice="${house.price}" isCollect="1"
										brokerId="${house.broker.erpId }" class="shouc_lik fr"
										onclick="event.cancelBubble = true;isCollectHouseSend(this);">收藏</a>
								</c:if>
							</c:if>
							<!-- END -->
						</div>
						<div class="jushi">
							<p>
								<a
									onclick="window.open('${globalUrl}/houseSecond.show?actionMethod=dimquery&type=1&ddhb_one_community.region.erpId=${house.community.region.erpId}&tabIndex=0')">
									${house.community.region.countyName }</a> - <a
									onclick="window.open('${globalUrl}/houseSecond.show?actionMethod=dimquery&type=1&ddhb_one_community.region.erpId=${house.community.region.erpId}&tabIndex=0&ddhb_one_community.cbd.parentCBD.websiteId=${house.community.cbd.parentCBD.websiteId}')">
									${house.community.cbd.parentCBD.name }</a>
							</p>
						</div>
						<div class="zjia">
							<span><fmt:formatNumber value="${house.price/10000}" type="currency" pattern="##" /></span><p>万元</p>
						</div>
						<div class="danjia">
							<p style="float: right;">元/平米</p>
							<span style="float: right;"><fmt:formatNumber value="${house.unitPrice}" type="currency" pattern="#" /></span> <br>
							<%--
						<c:if test="${house.previousUnitPrice - house.unitPrice < 0}">已上涨
						<fmt:formatNumber type="number" value="${-(house.previousUnitPrice - house.unitPrice)}" pattern="0.00" maxFractionDigits="2"/>
						元/平米</c:if>
						<c:if test="${house.previousUnitPrice - house.unitPrice > 0}">已降价
						<fmt:formatNumber type="number" value="${(house.previousUnitPrice - house.unitPrice)}" pattern="0.00" maxFractionDigits="2"/>
						元/平米</c:if>
						--%>
						</div>
						<div class="personCel">
							<a onclick="window.open('${globalUrl}/broker.show?actionMethod=brokerDetail&brokerId=${house.broker.erpId}&housetype=1')">${house.broker.bname}</a>
							<span>${house.broker.telephone}</span>
						</div>
						<div class="daikan">
							${house.shi}室${house.ting}厅
							<fmt:formatNumber value="${house.area}" type="currency" pattern="#" />平米 
							${house.orientations.orientationName}
							&nbsp;&nbsp;${house.storyCount}层
							<c:if test="${house.vervicalLocation == 1}">下部</c:if>
							<c:if test="${house.vervicalLocation == 2}">中部</c:if>
							<c:if test="${house.vervicalLocation == 3}">上部</c:if>

						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<c:if test="${pageBean.totalRows eq 0 }">
<h3 style="color: red; margin-top: 20px; margin-left: 50px">很抱歉，没有查询到符合要求的二手房源！</h3>
</c:if>
<div class=page>
<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false"
		formName="houseSecond" offerPageSize="20,50,100" isExistForm="true" queryFunction="changePages()"/>
</div>
