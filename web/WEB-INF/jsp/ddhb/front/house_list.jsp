<%@page import="com.huatek.hbwebsite.util.FrontSystemSettingUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${globalUrl}js/detail/detail.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('.housephotorightdown img').click(function(e){
		   e.stopPropagation();
	});
});
function isCollectHouseSend(obj){
	var $clickCollect = $(obj);
	if($clickCollect.attr("isCollect")==0) {
	    //取消收藏
		keepOff($clickCollect.attr("collId"), $clickCollect.attr("id"));	
	} else {
	    //添加收藏
		keepOppen($clickCollect.attr("hosueobjectId"), $clickCollect.attr("houseprice"), $clickCollect.attr("id"), $clickCollect.attr("brokerId"));
	}
}
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
//收藏二手房
function keepOppen(searchno, priceCc, id, brokerId){
	var collectType = 0;
	var platCollection = {
			ObjectId: searchno,
			collectType: collectType,
			priceCc: priceCc,
			brokerId:brokerId
		};
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=addtoCollection";
	_keepOppen(actionUrl, platCollection, collectType, id );
}

//取消收藏
function keepOff(collectId ,id){
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=cancleMyCollection&collectionId=" + collectId;
	_keepOff(actionUrl, id, 0);
} 
</script>
<div class="pc_ls">
	<ul>
		<c:forEach var="house" items="${pageBean.dataList}" varStatus="item">
			<li>
				<div class="pt" onclick="window.open('${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=${house.houseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}')" style="cursor: pointer;">
					<% //<c:if test="${house.houseUrl eq null}"> %>
					<c:if test="${house.pictureUrl eq null}">
					    <img src="${globalUrl}image/house_no_picture.png">
					</c:if>
					<% //<c:if test="${house.houseUrl ne null }"> %>
					<c:if test="${house.pictureUrl ne null }">
					    <img src="${pictureHost }${house.pictureUrl }" width="226" height="170">
					</c:if>
					<span>共${house.housePicSize}张</span>
				</div>
				<div class="many"><p><fmt:formatNumber value="${house.price/10000}" pattern="##" /></p>万元</div>
				<div class="tj_txt">
					<div class="name_a"><a onclick="window.open('${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=${house.houseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}')">${(houseAppraise != null) ? houseAppraise.title : house.title}</a></div>
					
					<div class="chengxq">
					<a onclick="window.open('${globalUrl}houseSecond.show?actionMethod=dimquery&type=1&ddhb_one_community.erpId=${house.community.erpId }&ddhb_one_community.region.erpId=${house.community.region.erpId}&ddhb_one_community.cbd.parentCBD.websiteId=${house.community.cbd.parentCBD.websiteId}')" >
					${house.community.communityName }</a>
					&nbsp;&nbsp;
					<a onclick="window.open('${globalUrl}houseSecond.show?actionMethod=dimquery&type=1&ddhb_one_community.region.erpId=${house.community.region.erpId}&tabIndex=0')">
					${house.community.region.countyName }
					</a>
					 -	
					 <a onclick="window.open('${globalUrl}houseSecond.show?actionMethod=dimquery&type=1&ddhb_one_community.region.erpId=${house.community.region.erpId}&tabIndex=0&ddhb_one_community.cbd.parentCBD.websiteId=${house.community.cbd.parentCBD.websiteId}')">
					 <c:out value="${house.community.cbd.parentCBD.name }"></c:out>
					 </a>					  
					 </div>
					<div class="tj_sx">
					   <span><fmt:formatNumber	pattern="#">${house.area}</fmt:formatNumber></span>M<sup>2</sup>&nbsp;&nbsp;${house.shi}室${house.ting}厅${house.wei}卫</span>
					   <div class="pms">
					      <span><fmt:formatNumber value="${house.unitPrice}" pattern="#" /></span>元/M<sup>2</sup>
					   </div>					
					</div>
					
					<div style="clear:both;"></div>
					<div class="tab_year">
					   <span style="float:left;"><fmt:formatDate value="${house.community.startSaleDate}" type="date" pattern="yyyy"/>年建&nbsp;${house.broker.store.storeName}</span>
						<!-- 收藏 和 对比  start-->
							<a housePictureUrl="${pictureHost}${house.pictureUrl }" inCompareItem="false" 
						    onclick="event.cancelBubble = true;addCompareItem(this, 'secondHouseCompare')"
						    compareId="${house.houseNo }" brokerId="${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}" 
						    compareTitle="${(houseAppraise != null) ? houseAppraise.title : house.title}"
						    area="${house.area }"  shi="${house.shi }" ting="${house.ting }" comparePrice="${house.price}" 
						    compareUnitPrice="${house.unitPrice}" class="dbi_lik fr">对比</a>
						<c:if test="${LoginMember == null }">
							<a href="javascript:void(0);" collId="${house.collectId}" hosueobjectId="${house.objectId}" 
							     brokerId="${house.broker.erpId }" houseprice="${house.price}" class="shouc_lik fr"
							     onclick="event.cancelBubble = true;loginBox('collect');">收藏</a>
						</c:if>
						<c:if test="${LoginMember != null }">
							<c:if test="${house.collectId != null}">
								<a id="collect${house.erpId}two"  href="javascript:void(0);"  class="shouc_lik fr"
								collId="${house.collectId}" hosueobjectId="${house.objectId}" houseprice="${house.price}" isCollect="1" 
								brokerId="${house.broker.erpId }" onclick="event.cancelBubble = true;isCollectHouseSend(this);" >收藏</a>
							</c:if>
							<c:if test="${house.collectId == null}">
								<a id="collect${house.erpId}two"  href="javascript:void(0);" collId="${house.collectId}" 
								    hosueobjectId="${house.objectId}" houseprice="${house.price}" isCollect="1" class="shouc_lik fr"
								    brokerId="${house.broker.erpId }" onclick="event.cancelBubble = true;isCollectHouseSend(this);" >收藏</a>
							</c:if>
						</c:if>
						<!-- END -->
					</div>
					<div class="color_wd">
						<c:forEach items="${tagList}" var="tags">
							<c:forEach items="${house.tagIdList}" var="tagId">
								<c:if test="${tags.erpId == tagId}">
								   <a onclick="window.open('${globalUrl}houseSecond.show?actionMethod=dimquery&type=1&ddhb_one_tags=${house.tags}')">
								    <span style="background-color:${tags.tagColor}; color:${tags.fontColor};">&nbsp;${tags.tagName}&nbsp;</span>
								    </a>
								</c:if>
							</c:forEach>
						</c:forEach>
					</div>
				</div>
			</li>
		</c:forEach>
	</ul>
</div>