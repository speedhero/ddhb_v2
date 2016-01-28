<%@page import="com.huatek.hbwebsite.util.FrontSystemSettingUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.houseItem{border:1px solid #F3F3F3;}
.houseItem:hover{border:1px solid #75BE40;}
</style>
<div class="pc_ls pc_ls_xq">
    <ul>
    	<c:forEach var="house" items="${pageBean.dataList}" varStatus="item">
	         <li class="houseItem">
              	<div class="pt">
	              	<c:if test="${house.houseUrl eq null}">
						<img style="cursor: pointer;" onclick="window.open('${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=${house.houseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}')" 
							src="${globalUrl}image/house_no_picture.png" width="226" height="170"/>
	              	</c:if>
	              	<c:if test="${house.houseUrl ne null}">
	              	    <img style="cursor: pointer;" onclick="window.open('${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=${house.houseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}')" 
                            src="${pictureHost }${house.houseUrl }" width="226" height="170"/>
	              	</c:if>
				</div>
				<div class="many"><p><fmt:formatNumber value="${house.price/10000}" pattern="##" /></p>万元</div>
	            <div class="tj_txt" style="width:92%">
                	<div class="jiant_s" onclick="moveClick(this);" ><a href="javascript:void(0);"></a></div>
	                <div class="pm"><span><fmt:formatNumber value="${house.unitPrice}" pattern="#" /></span>元/平米</div>
	                <div class="name_a"><div style="white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">${(houseAppraise != null) ? houseAppraise.title : house.title}</div></div>
	                <div class="tj_sx">
	                	<span><fmt:formatNumber pattern="#">${house.area}</fmt:formatNumber></span>
	                	平米&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                	<span>${house.shi}</span>室
	                	<span>${house.ting}</span>厅
	                	<span>${house.wei}</span>卫
	                	<br><c:out value="${house.community.communityName }"></c:out>
	                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                	<c:out value="${house.community.region.countyName }"></c:out> - 
	                	<c:out value="${house.community.cbd.parentCBD.name }"></c:out><br>
	                	<fmt:formatDate value="${house.community.startSaleDate}" type="date" pattern="yyyy"/>年建&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                	<c:out value="${house.broker.store.storeName}"></c:out>
                	</div>
	                <div class="color_wd">
										<c:forEach items="${house.houseTags}" var="tag">
											<span style="background: none repeat scroll 0 0 ${tag.tagColor}; color:${tag.fontColor};">${tag.tagName}</span>
										</c:forEach>
	                </div>
	                <div class="an_xq">
                    	<a onclick="window.open('${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=${house.houseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}')" class="xq">查看详情</a>
                       	<p>
                   			<c:if test="${LoginMember == null }">
								<a href="javascript:void(0);" collId="${house.collectId}" hosueobjectId="${house.shelvingID}"
								houseprice="${house.price}" onclick="loginBox('collect');">收藏</a>
							</c:if>
							<c:if test="${LoginMember != null }">
								<c:if test="${house.collectId != null}">
	                                <a id="collect${house.erpId}"  href="javascript:void(0);" style="background-color:#cb4f4d" 
	                                	collId="${house.collectId}" hosueobjectId="${house.shelvingID}" houseprice="${house.price}" isCollect="0"
										onclick="isCollectHouseSendCommunityBelong(this);" >收藏</a>
								</c:if>
								<c:if test="${house.collectId == null}">
	                                <a id="collect${house.erpId}"  href="javascript:void(0);"  collId="${house.collectId}" 
	                                	hosueobjectId="${house.shelvingID}" houseprice="${house.price}" isCollect="1"
										onclick="isCollectHouseSendCommunityBelong(this);" >收藏</a>
								</c:if>
							</c:if>
                        <a href="javascript:void(0);" housePictureUrl="${pictureHost}${house.houseUrl }" inCompareItem="false" 
                        	onclick="reloadCompareDiv('secondHouseCompare','${globalUrl}');reloadHistoryDiv('secondHouseHistory','${globalUrl}',$('#loginStatus').val());reInitCompareDiv('secondHouseCompare','${globalUrl}');$('a[compareId=${house.houseNo }]').addClass('addCompare');addCompareToDiv(this,'S','${house.houseNo }');"
							compareId="${house.houseNo }" brokerId="${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}" 
							compareTitle="${(houseAppraise != null) ? houseAppraise.title : house.title}"
							area="${house.area }"  shi="${house.shi }" ting="${house.ting }" comparePrice="${house.price}" 
							compareUnitPrice="${house.unitPrice}" class="phoneCompare">对比</a>
                        </p>
                    </div>
	            </div>
	        </li>
        </c:forEach>
    </ul>
</div>