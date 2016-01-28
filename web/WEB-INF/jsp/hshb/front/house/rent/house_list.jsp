<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="h" uri="/WEB-INF/tld/hshb.tld"%>

<!-- 大图模式 -->
<div id="image_list" style="display:none;">
	<c:import url="/WEB-INF/jsp/hshb/front/house/rent/house_block.jsp"/>
	<div style="clear: both; height: 0px;"></div>
</div>
<%-- <input type="hidden" id="houseTotleRows" value="${pageBean.total}" />--%>
<!-- 列表 -->
<div id="list_list" style="display:none;">
	<div class="wd_ls">
		<ul>
			<c:forEach var="house" items="${houseList}" varStatus="status">
				<li>
					<div class="bianh" style="cursor: pointer;">

						<a href="<h:surl houseId="${house.houseId}"/>.html" target="_blank">
						<img src="${house.coverImage eq null? globalUrl.concat('/image/house_no_picture.png') : pictureHost.concat(h:replaceAll(house.coverImage,'/renthouse/','/renthouse/s4/'))}" width="226" height="170">					
						<span>共${house.picCount eq null?0:house.picCount}张</span>
						</a>
					</div>
					<div class="wd_rt">
						<div class="nbbh">内部编号：<span>${house.houseId}</span>
						</div>
						<div class="biaot" id="nameTitle">
							<a href="<h:surl houseId='${house.houseId }' />.html" target="_blank">${house.title}</a>
						</div>
						<div class="xiaoq">
							<span> 
							<a href="<h:surl p='v_${house.communityId}' />">${house.communityName}</a>
							</span>
							<p>
								<a href="<h:surl p="a_${house.countyId}_"/>">${house.countyName }</a> - <a href="<h:surl p="a_${house.countyId}_${house.cbdWebsiteId}"/>">
									${house.cbdWsiteName }</a>
							</p>
						</div>
						<div class="maid_lb">
						<!-- 配置信息 -->
<%--
							<c:forEach var="items" items="${searchItems}" >
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
 --%>
							<c:forEach items="${house.houseFurnitures}" var="fur">
							<span>
								<img src="${pictureHost}${fur.iconUrl}" title="${fur.clfName}">
							</span>
							</c:forEach>
						</div>
						<div class="jushi">${house.shi}室${house.ting}厅</div>
						<div class="mianji"><fmt:formatNumber value="${house.area}" type="currency" pattern="#" />平米</div>
						<div class="danjia" style="text-align: right;">
							<!-- 出租类型 -->	 
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
							<a href="${globalUrl}broker/${house.publisherId}.html" target="_blank">${house.agentName}</a>
							<span>${house.telephone}</span>
						</div>
						<div class="zjia">
							<span><fmt:formatNumber value="${house.rentPrice}" type="currency" pattern="#" /></span><p>元/月</p>
						</div>
						<div class="daikan">
							<a housePictureUrl="${pictureHost}${house.coverImage }"
								inCompareItem="false" class="compareButton dbi_lik fl"
								onclick="event.cancelBubble = true;addCompareItem(this, 'rentHouseCompare')"
								compareId="${house.houseId }"
								brokerId="${house.publisherId}"
								compareTitle="${house.title}"
								area="${house.area }" shi="${house.shi }" ting="${house.ting }"
								comparePrice="${house.rentPrice}">对比</a>

							<c:if test="${LoginMember ne null }">
								<c:if test="${house.collectId != null}">
									<a id="collect${house.erpId}two" href="javascript:void(0);"
										brokerid="${house.publisherId }"
										collId="${house.collectId}" class="shouc_lik fl"
										hosueobjectId="${house.erpId}" houseprice="${house.rentPrice}"
										isCollect="0"
										onclick="event.cancelBubble = true;isCollectInformation(this,1);">收藏</a>
								</c:if>
								<c:if test="${house.collectId == null}">
									<a id="collect${house.erpId}two" href="javascript:void(0);"
										brokerid="${house.publisherId }" collId="${house.collectId}"
										hosueobjectId="${house.erpId}" houseprice="${house.rentPrice}"
										isCollect="1" class="shouc_lik fl"
										onclick="event.cancelBubble = true;isCollectInformation(this,1);">收藏</a>
								</c:if>
							</c:if>
							<c:if test="${LoginMember eq null }">
								<a class="shouc_lik fl" href="javascript:loginBox();">收藏</a>
								<a onclick="loginBox()"></a>
							</c:if>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<c:if test="${houseList.size() eq 0 }">
<h3 style="color: red; margin-top: 20px; margin-left: 50px">很抱歉，没有查询到符合要求的租赁房源！</h3>
</c:if>
<div class=page>
<c:import url="/WEB-INF/jsp/hshb/front/commonInfoShow/pager.jsp"/>
</div>
<script type="text/javascript">
$(document).ready(function(){
	initShowType();
});
</script>

