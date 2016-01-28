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
function isCollectRentCommunityBelong(obj){
	var $clickCollect = $(obj);
	if($clickCollect.attr("isCollect")==0) {
		//取消收藏
		keepOffCommunityBelong($clickCollect.attr("collId"), $clickCollect.attr("id"));	
	} else {
		//添加收藏
		keepOppenCommunityBelong($clickCollect.attr("hosueobjectId"), $clickCollect.attr("houseprice"), $clickCollect.attr("id"));
	}
}
function keepOppenCommunityBelong(searchno, priceCc, id){
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=addtoCollection";
	var collectType = 1;
	var platCollection = {
			ObjectId: searchno,
			collectType: collectType,
			priceCc: priceCc,
		};
	
	_keepOppen(actionUrl, platCollection, collectType, id );
}

//取消收藏
function keepOffCommunityBelong(collectId ,id){
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=cancleMyCollection&collectionId=" + collectId ;
	_keepOff(actionUrl, id, 1);
}
</script>
<style>
.houseItem{border:1px solid #F3F3F3;}
.houseItem:hover{border:1px solid #75BE40;}
</style>
<div class="pc_ls pc_ls_xq">
    <ul>
   		<c:forEach var="house" items="${pageBean.dataList}" varStatus="item">
	         <li class="houseItem">
              	<div class="pt">
              	<c:if test="${house.pictureUrl eq null}">
              	     <img onclick="window.open('${globalUrl}rent.show?actionMethod=houseRentDetail&houseNo=${house.hourseNo}&brokerId=${house.broker.erpId}')"  
                        src="${globalUrl}image/house_no_picture.png" width="226" height="170"/>
              	</c:if>
				<c:if test="${house.pictureUrl ne null}">
                     <img onclick="window.open('${globalUrl}rent.show?actionMethod=houseRentDetail&houseNo=${house.hourseNo}&brokerId=${house.broker.erpId}')"  
                        src="${pictureHost }${house.houseUrl }" width="226" height="170"/>
                </c:if>	
				</div>
				<div class="many"><p><fmt:formatNumber value="${house.rentPrice}" pattern="#"/></p>元/月</div>
	            <div class="tj_txt" style="width:90%">
                	<div class="jiant_s" onclick="moveClick(this);" ><a href="javascript:void(0);"></a></div>
	                <div class="pm"><span>[${house.rentType.rentTypeName}]</span></div>
					<div class="name_a"><div style="white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">${(houseAppraise != null) ? houseAppraise.title : house.title}</div></div>
                    <div class="tj_sx"><span><fmt:formatNumber pattern="#">${house.area}</fmt:formatNumber></span>平米&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${house.shi}</span>室<span>${house.ting}</span>厅<span>${house.wei}</span>卫<br>${house.community.communityName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${house.community.region.countyName } - ${house.community.cbd.parentCBD.name }<br><fmt:formatDate value="${house.community.startSaleDate}" type="date" pattern="yyyy"/>年建&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${house.broker.store.storeName}</div>
	                <div class="color_wd">
	                 	<c:forEach var="furs" items="${furList}">
							<c:forEach var="furId" items="${house.furIdList}">
								<c:if test="${furs.erpId == furId}">
									<img style="width:auto;height:auto" src="${pictureHost}${furs.imgUrl}" title="<c:out value="${furs.furName }"></c:out>">
								</c:if>
							</c:forEach>
						</c:forEach>
					</div>
	                <div class="an_xq">
						<a onclick="window.open('${globalUrl}rent.show?actionMethod=houseRentDetail&houseNo=${house.hourseNo}&brokerId=${house.broker.erpId}')" class="xq">查看详情</a>
						<p>
						<c:if test="${LoginMember == null }">
							<a href="javascript:void(0);" collId="${house.collectId}"
								brokerid="${house.broker.erpId }"
								onclick="event.cancelBubble = true;loginBox('collect');">收藏</a>
						</c:if>
						<c:if test="${LoginMember != null }">
							<c:if test="${house.collectId != null}">
								<a id="collect${house.erpId}" href="javascript:void(0);"
									brokerid="${house.broker.erpId }"
									style="background-color: #cb4f4d" collId="${house.collectId}"
									hosueobjectId="${house.shelvingID}" houseprice="${house.rentPrice}"
									isCollect="0"
									onclick="event.cancelBubble = true;isCollectRentCommunityBelong(this);">收藏</a>
							</c:if>
							<c:if test="${house.collectId == null}">
								<a id="collect${house.erpId}" href="javascript:void(0);"
									brokerid="${house.broker.erpId }" collId="${house.collectId}"
									hosueobjectId="${house.shelvingID}" houseprice="${house.rentPrice}"
									isCollect="1"
									onclick="event.cancelBubble = true;isCollectRentCommunityBelong(this);">收藏</a>
							</c:if>
						</c:if>
						<a href="javascript:void(0);" housePictureUrl="${pictureHost}${house.houseUrl }" inCompareItem="false" 
							class="compareButton" onclick="reloadCompareDiv('rentHouseCompare','${globalUrl}');reloadHistoryDiv('rentHouseHistory','${globalUrl}',$('#loginStatus').val());reInitCompareDiv('rentHouseCompare','${globalUrl}');$('a[compareId=${house.hourseNo }]').addClass('addCompare');addCompareToDiv(this, 'R','${house.hourseNo }');"
							compareId="${house.hourseNo }" brokerId="${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}" 
							compareTitle="${(houseAppraise != null) ? houseAppraise.title : house.title}"
							area="${house.area }"  shi="${house.shi }" ting="${house.ting }" comparePrice="${house.rentPrice}">对比</a>
				 		</p>
				 	</div>
	            </div>
	        </li>
        </c:forEach>
    </ul>
</div>