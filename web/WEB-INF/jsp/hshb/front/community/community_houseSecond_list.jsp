<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="h" uri="/WEB-INF/tld/hshb.tld"%>
<script type="text/javascript" src="${globalUrl}js/search/myMap_new.js"></script>
<script type="text/javascript" src="${globalUrl}js/housesecond/housesecond.js"></script>
<script type="text/javascript" src="${globalUrl}js/detail/detail.js"></script>
<script type="text/javascript">
var globalUrl = '${globalUrl}';
$(document).ready(function() {
	var idStr = $.cookie("lastSelected");
	if (idStr == "imgShape") {
		$("#checkbtn2").removeAttr("style").css("cursor", "pointer");
		$('#image_list').css("display", "block");
		$('#list_list').css("display", "none");
		setType0();
	} else {
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
function isCollectHouseSendCommunityBelong(obj){
	var $clickCollect = $(obj);
	if($clickCollect.attr("isCollect")==0) {
	//取消收藏
		keepOffCommunityBelong($clickCollect.attr("collId"), $clickCollect.attr("id"));	
	} else {
	//添加收藏
		keepOppenCommunityBelong($clickCollect.attr("hosueobjectId"), $clickCollect.attr("houseprice"), $clickCollect.attr("id"));
	}
}
//添加收藏
function keepOppenCommunityBelong(searchno, priceCc, id){
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=addtoCollection";
	var collectType = 0;
	var platCollection = {
			ObjectId: searchno,
			collectType: collectType,
			priceCc: priceCc,
		};
	_keepOppen(actionUrl, platCollection, collectType, id );
}

//取消收藏
function keepOffCommunityBelong(collectId ,id){
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=cancleMyCollection&collectionId=" + collectId;
	_keepOff(actionUrl, id, 0);
}
</script>
<style type="text/css">
#nameTitle a:hover{color: #75BE40;}
</style>
<div id="image_list" style="display:none;">
	<%--@include file="/WEB-INF/jsp/ddhb/front/community/community_houseSecond_image_list.jsp"--%>
	<div style="clear: both; height: 0px;"></div>
</div>
<input type="hidden" id="houseTotleRows" value="${pageBean.total}"/>
<!-- 列表显示 -->
<div id="list_list" style="display:block;">
	<div class="lb_lx" style="display:none;">
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
			<c:forEach var="house" items="${houseList}" varStatus="status">
			<li>
					<c:set var="prefixAddress" value="${globalUrl}chushou/" />
					<div class="bianh" style="cursor: pointer;">
						<a href="${prefixAddress}${house.houseId}.html" target="_blank">
						<img src="${house.coverImage eq null? globalUrl.concat('/image/house_no_picture.png') : pictureHost.concat(house.coverImage)}" width="226" height="170">
						</a>
						<span>共${house.pictureCount}张</span>
					</div>
					<div class="wd_rt">
						<div class="nbbh">内部编号：<span>${house.houseId}</span></div>
						<div class="biaot">
							<a href="${prefixAddress}${house.houseId}.html" target="_blank">${house.title}</a>
						</div>
						<div class="xiaoq">
							<span>
								<!-- <a href="<h:surl p="a__${house.community.erpId}"/>">${house.community.communityName} </a> -->
								${house.community.communityName}
							</span>
						</div>
						<div class="maid">
							<c:forEach items="${house.houseTags}" var="tag">
								<a href="<h:surl p="t${tag.erpId}"/>">
									<span style="background-color:${tag.tagColor}; color:${tag.fontColor};">${tag.tagName}</span>
								</a>
							</c:forEach>
														
							<!-- 对比 和 收藏 START -->
							<a housePictureUrl="${pictureHost}${house.coverImage }" inCompareItem="false" 
						    onclick="event.cancelBubble = true;addCompareItem(this, 'secondHouseCompare')"
						    compareId="${house.houseId }" brokerId="${publisher.erpId}" 
						    compareTitle="${house.title}"
						    area="${house.area }"  shi="${house.shi }" ting="${house.ting }" comparePrice="${house.price}" 
						    compareUnitPrice="${house.unitPrice}" class="dbi_lik fr">对比</a>
						<c:if test="${LoginMember == null }">
							<a class="shouc_lik fr" href="javascript:void(0);" collId="${house.collectId}" hosueobjectId="${house.erpId}" 
							     brokerId="${hosue.publisher.erpId }" houseprice="${house.price}" 
							     onclick="event.cancelBubble = true;loginBox('collect');">收藏</a>
						</c:if>
						<c:if test="${LoginMember != null }">
							<c:if test="${house.collectId != null}">
								<a class="shouc_lik fr" id="collect${house.erpId}two"  href="javascript:void(0);"  
								collId="${house.collectId}" hosueobjectId="${house.erpId}" houseprice="${house.price}" isCollect="0" 
								brokerId="${house.publisher.erpId }" onclick="event.cancelBubble = true;isCollectHouseSend(this);" >收藏</a>
							</c:if>
							<c:if test="${house.collectId == null}">
								<a class="shouc_lik fr" id="collect${house.erpId}two"  href="javascript:void(0);" collId="${house.collectId}" 
								    hosueobjectId="${house.erpId}" houseprice="${house.price}" isCollect="1" 
								    brokerId="${house.publisher.erpId }" onclick="event.cancelBubble = true;isCollectHouseSend(this);" >收藏</a>
							</c:if>
						</c:if>
							<!-- END -->
						</div>
						<div class="jushi">
							<p>
								<a href="<h:surl p="a_${house.county.erpId}_"/>">${house.county.countyName }</a> - <a href="<h:surl p="a_${house.county.erpId}_${house.websiteCbd.websiteId}"/>">
									${house.websiteCbd.name }</a>
							</p>
						</div>
						<div class="zjia">
							<span><fmt:formatNumber value="${house.price/10000}" type="currency" pattern="##" /></span><p>万元</p>
						</div>
						<div class="danjia">
							<p style="float: right;">元/平米</p>
							<span style="float: right;"><fmt:formatNumber value="${house.unitPrice}" type="currency" pattern="#" /></span> <br>
						</div>
						<div class="personCel">
							<a href="${globalUrl }broker/${house.publisherId}.html" target="_blank">${house.publisher.agentName}</a>
							<span>${house.publisher.telephone}</span>
						</div>
						<div class="daikan">
							${house.shi}室${house.ting}厅
							<fmt:formatNumber value="${house.area}" type="currency" pattern="#" />平米
							${house.orientation.orientationName}
							&nbsp;&nbsp;${house.storeyCount}层
							<c:if test="${house.houseVervicalLocation == 1}">下部</c:if>
							<c:if test="${house.houseVervicalLocation == 2}">中部</c:if>
							<c:if test="${house.houseVervicalLocation == 3}">上部</c:if>
							
						</div>
					</div>
				</li>
		</c:forEach>
		</ul>
	</div>
</div>
<c:if test="${pageBean.total eq 0 }">
	<h3 style="color: red; margin-top: 20px; margin-left: 50px">很抱歉，没有查询到符合要求的二手房源！</h3>
</c:if>
<div class=page>
<c:import url="/WEB-INF/jsp/hshb/front/commonInfoShow/pager.jsp"/>
</div>
