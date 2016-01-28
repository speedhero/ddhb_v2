<%@page import="com.huatek.hbwebsite.util.FrontSystemSettingUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="h" uri="/WEB-INF/tld/hshb.tld"%>
<%-- <script type="text/javascript" src="${globalUrl}js/detail/detail.js"></script> --%>
<script type="text/javascript">
<%-- 
$(document).ready(function() {
	$('.housephotorightdown img').click(function(e){
		   e.stopPropagation();
	});
});
--%>
<%-- 
function moveClick(obj){
	var $move =$(obj).parent();
	if($move.find('.color_wd').is(":visible") == false) {
		//显示
		$move.find('.color_wd').slideToggle( "slow");
		animateOn($move);
		$move.find(".jiant_s").find("a").css("background-image","url('${globalUrl}image/jiant_x.png')");
		$move.find(".an_xq").css("display","block");
	}else {
		//取消
		$move.stop(true,true);
		$move.find('.color_wd').stop(true, true);
		animateOff($move);
		$move.find(".jiant_s").find("a").css("background-image","url('${globalUrl}image/jiant_s.png')");
		$move.find(".an_xq").css("display","none");
		$move.find('.color_wd').slideToggle( "slow");
	}
}

function animateOn(param) {
	param.animate({
   	 /* top:"80px" */
   	 top:"29px"
	});
}

function animateOff(param) {
	param.animate({
   	 /* top:"80px" */
   	 top:"174px"
	});
}

function isCollectRent(obj){
	var $clickCollect = $(obj);
	if($clickCollect.attr("isCollect")==0) {
		//取消收藏
		keepOff($clickCollect.attr("collId"), $clickCollect.attr("id"));	
	} else {
		//添加收藏
		keepOppen($clickCollect.attr("hosueobjectId"), $clickCollect.attr("houseprice"), $clickCollect.attr("id"), $clickCollect.attr("brokerId"));
	}
}
//收藏
function keepOppen(searchno, priceCc, id, brokerId){
	var collectType = 1;
	var platCollection = {
			ObjectId: searchno,
			collectType: collectType,
			priceCc: priceCc,
			brokerId:brokerId
		};
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=addtoCollection";
	_keepOppen(actionUrl, platCollection, collectType, id);
}

//取消收藏
function keepOff(collectId ,id){
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=cancleMyCollection&collectionId=" + collectId;
	_keepOff(actionUrl, id, 1);
}
--%>
</script>
<div class="pc_ls">
	<ul>
		<c:forEach var="house" items="${houseList}" varStatus="item">
			<li style="height:353px;">
				<div class="pt" onclick="window.open('${globalUrl}chuzu/${house.houseId}.html')" style="cursor: pointer;">
					<img src="${house.coverImage eq null? globalUrl.concat('/image/house_no_picture.png') : pictureHost.concat(house.coverImage)}" width="226" height="170">
					 <div class="inf_div">
					    <p class="fl">共${house.picCount eq null?0:house.picCount}张</p>
						<p class="fr">
							<c:forEach var="item" items="${searchItems }" varStatus="s1">
								<c:if test="${item.id eq 80 }">
									<c:forEach var="field" items="${item.fields}" varStatus="s2">
										<c:if test="${house.rentTypeId eq field.id }">[${field.fieldname}]</c:if>
									</c:forEach>
								</c:if>
							</c:forEach>
						</p>
					 </div>
				</div>
				<div class="many"><p><fmt:formatNumber value="${house.rentPrice}" pattern="#"/></p>元/月</div>
				<div class="tj_txt" style="height:168px;">
					<div class="name_a"><a href="${globalUrl}chuzu/${house.houseId}.html" target="_blank">${house.title}</a></div>
					<div class="chengxq"><a href="<h:surl p='v_${house.communityId}' />">${house.communityName }</a>&nbsp;&nbsp;
						<a href="<h:surl p="a_${house.countyId}_"/>">${house.countyName }</a>
						 - 
						 <a href="<h:surl p="a_${house.countyId}_${house.cbdWebsiteId}"/>">${house.cbdWsiteName}</a></div>
					<div class="tj_sx">					
						<span><fmt:formatNumber pattern="#">${house.area}</fmt:formatNumber>平米&nbsp;</span>
						<span>${house.shi}室${house.ting}厅${house.wei}卫</span>
						<!-- 添加对比 收藏  start-->
							<a housePictureUrl="${pictureHost}${house.coverImage }" 
							inCompareItem="false" class="dbi_lik fr" 
							onclick="event.cancelBubble = true;addCompareItem(this, 'rentHouseCompare')"
							 compareId="${house.houseId }" 
							 brokerId="${house.publisherId}"
							 area="${house.area }"  
							 compareTitle="${house.title}"
							 shi="${house.shi }" 
							 ting="${house.ting }" 
							 comparePrice="${house.rentPrice}" >对比</a>
							 <c:if test="${LoginMember != null }">
								<c:if test="${house.collectId != null}">
									<a id="collect${house.erpId}two"  href="javascript:void(0);" brokerid="${house.publisherId}" class="shouc_lik fr" collId="${house.collectId}" hosueobjectId="${house.erpId}" houseprice="${house.rentPrice}" isCollect="0"
									onclick="event.cancelBubble = true;isCollectInformation(this,1);" >收藏</a>
								</c:if>
								<c:if test="${house.collectId == null}">
									<a id="collect${house.erpId}two"  href="javascript:void(0);" brokerid="${house.publisherId}" collId="${house.collectId}" hosueobjectId="${house.erpId}"
									houseprice="${house.rentPrice}" isCollect="1" class="shouc_lik fr"
									onclick="event.cancelBubble = true;isCollectInformation(this,1);" >收藏</a>
								</c:if>
							</c:if>
							<c:if test="${LoginMember == null }">
								<a class="shouc_lik fr" href="javascript:loginBox();">收藏</a>
								<a onclick="loginBox()"></a>
							</c:if>
						<!-- END -->
					</div>
					<div style="clear:both;"></div>
					<div class="color_wd">
<%--
						<c:forEach var="items" items="${searchItems}" >
							<c:if test="${items.id eq 81}">
								<c:forEach var="field" items="${items.fields }" varStatus="s2">
									<c:if test="${fn:contains(house.furniture, field.id) }">
										<span>
											<img src="${pictureHost }${field.maxfieldvalue }"
											title="${field.fieldname}" style="width:24px;">
										</span>
									</c:if>
								</c:forEach>
							</c:if>
						</c:forEach>
 --%>
						<c:forEach items="${house.houseFurnitures}" var="fur">
							<img src="${pictureHost}${fur.iconUrl}" title="${fur.clfName}" style="width:19px;">
						</c:forEach>
					</div>
				</div>
			</li>
		</c:forEach>
	</ul>
</div>
