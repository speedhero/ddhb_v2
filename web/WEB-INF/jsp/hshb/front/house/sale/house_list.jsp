<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="h" uri="/WEB-INF/tld/hshb.tld"%>
<%@taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %>

<script type="text/javascript">
$(document).ready(function() {
	try{
		initShowType();	
	}catch(e){
		log(e);
	}
});
</script>
<%-- 
<script type="text/javascript">
/**
 * 显示模式切换后刷新页面
 */
 /*
function refreshPageAfterChangeShowType(){
	if("${houseAppraise == null}" != "false") {
		var isSearch = "${isSearch}";
		if(isSearch == 1){
			window.location.href="${globalUrl}/houseSearch.show?actionMethod=doSearch&houseType=1&searchInput=${searchinput}&searchType=1&requesttype=1";
		}else {
			var communityId = "${comId}";
			if("" != communityId){
				showSelectedField("community.show?actionMethod=communityhouse&housetype=1&ispage=1");
			}else {
				showSelectedField(searchURL);
			};
		};
	}
}
*/
</script>
--%>
<div id="listTop"></div>
<div id="image_list" style="display: none;">
	<c:import url="/WEB-INF/jsp/hshb/front/house/sale/house_block.jsp"/>
	<div style="clear: both; height: 0px;"></div>
</div>
<input type="hidden" id="houseTotleRows" value="${pageBean.total}" />
<!-- 列表显示 -->
<div id="list_list" style="display: none;">
	<div class="lb_lx" style="display: none;">
		<ul>
			<li class="li_1">内部编号</li>
			<li class="li_2">标题</li>
			<li class="li_3">小区区域</li>
			<li class="li_4">房源卖点</li>
			<li class="li_5">居室</li>
			<li class="li_6">面积</li>
			<li class="li_7">总价</li>
			<li class="li_8">单价</li>
		</ul>
	</div>
	<!--列表抬头结束-->
	<!--文字列表开始-->
	<div class="wd_ls">
		<ul>
			<c:forEach var="house" items="${houseList}" varStatus="status">
				<cache:cache key="_sale_list_${house.houseId}" time="60">			
				<li>
					<div class="bianh" style="cursor: pointer;">
						<a href="<h:surl houseId="${house.houseId}"/>.html" target="_blank">
						<img src="${house.coverImage eq null? globalUrl.concat('/image/house_no_picture.png') : pictureHost.concat(h:replaceAll(house.coverImage, '/secondhouse/', '/secondhouse/s4/'))}" width="226" height="170">
						<span>共${house.picCount eq null?0:house.picCount}张</span>
						</a>
					</div>
					<div class="wd_rt">
						<div class="nbbh">内部编号：<span>${house.houseId}</span></div>
						<div class="biaot">
							<a href="<h:surl houseId="${house.houseId}"/>.html" target="_blank">${house.title}</a>
						</div>
						<div class="xiaoq">
							<span>
								<a href="<h:surl p="v_${house.community.erpId}"/>">${house.community.communityName} </a>
							</span>
						</div>
						<div class="maid">
							<c:forEach items="${house.houseTags}" var="tag">
								<span style="background-color:${tag.tagColor}; color:${tag.fontColor};">
								<a href="<h:surl p="t${tag.erpId }" />" style="color:#FFFFFF" >${tag.tagName}</a>
								</span>
							</c:forEach>

							<!-- 对比 和 收藏 START -->
							<a housePictureUrl="${pictureHost}${house.coverImage}" inCompareItem="false" 
						    onclick="event.cancelBubble = true;addCompareItem(this, 'secondHouseCompare')"
						    compareId="${house.houseId }" brokerId="${publisher.erpId}" 
						    compareTitle="${house.title}"
						    area="${house.area }"  shi="${house.shi }" ting="${house.ting }" comparePrice="${house.price}" 
						    compareUnitPrice="${house.unitPrice}" class="dbi_lik fr">对比</a>
						    
						<c:if test="${LoginMember eq null }">
							<a href="javascript:void(0);" collId="${house.collectId}" hosueobjectId="${house.erpId}" 
							     brokerId="${publisher.erpId }" houseprice="${house.price}"  class="shouc_lik fr"
							     onclick="event.cancelBubble = true;loginBox('collect');">收藏</a>
						</c:if>
						<c:if test="${LoginMember ne null }">
							<c:if test="${house.collectId != null}">
								<a id="collect${house.erpId}two"  href="javascript:void(0);" class="shouc_lik fr"
								collId="${house.collectId}" hosueobjectId="${house.erpId}" houseprice="${house.price}" isCollect="0" 
								brokerId="${house.broker.erpId }" onclick="event.cancelBubble = true;isCollectInformation(this,0);" >收藏</a>
							</c:if>
							<c:if test="${house.collectId == null}">
								<a id="collect${house.erpId}two"  href="javascript:void(0);" collId="${house.collectId}" 
								    hosueobjectId="${house.erpId}" houseprice="${house.price}" isCollect="1" class="shouc_lik fr" 
								    brokerId="${house.publisher.erpId }" onclick="event.cancelBubble = true;isCollectInformation(this,0);" >收藏</a>
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
							<a href="${globalUrl }broker/${house.publisherId}.html" >${house.publisher.agentName}</a>
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
				</cache:cache>
			</c:forEach>
		</ul>
	</div>
</div>
<c:if test="${houseList.size() eq 0 }">
<h3 style="color: red; margin-top: 20px; margin-left: 50px">很抱歉，没有查询到符合要求的二手房房源！</h3>
</c:if>
<div class=page>
<c:import url="/WEB-INF/jsp/hshb/front/commonInfoShow/pager.jsp"/>
</div>
