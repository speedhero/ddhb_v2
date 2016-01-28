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

</script>
<div class="pc_ls">
	<ul>
		<c:forEach var="house" items="${houseList}" varStatus="item">
			<li>
				<div class="pt" onclick="window.open('${globalUrl}chuzu/${house.houseId}.html')" style="cursor: pointer;">
					 <c:if test="${house.coverImage eq null}">
		                 <img src="${globalUrl}image/house_no_picture.png">
		             </c:if>
		             <c:if test="${house.coverImage ne null }">
					     <img src="${pictureHost }${house.coverImage }" width="226" height="170"/>
		             </c:if>
				</div>
				<div class="name_a"><a href="${globalUrl}chuzu/${house.houseId}.html" target="_blank" >${house.title}</a></div>
				<div class="jjr_tj_sx"><fmt:formatNumber pattern="#">${house.area}</fmt:formatNumber>平米</div>
				<div class="jjr_chengxq" style="width:90px">
				<a href="${globalUrl}chuzu/a_${house.countyId}_">${house.countyName }</a>
				 - 
				 <a href="${globalUrl}chuzu/a_${house.countyId}_${house.cbdWebsiteId}">${house.cbdWsiteName }</a></div>
				<div class="jjr_huxin">${house.shi}室${house.ting}厅${house.wei}卫</div>
				<div class="jjr_zprice"><fmt:formatNumber value="${house.rentPrice}" pattern="#"/>元/月</div>				
			</li>
		</c:forEach>
	</ul>
</div>
