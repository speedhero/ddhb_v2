<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- 
<script type="text/javascript" src="${globalUrl}js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${globalUrl}js/cookie/cookie.js"></script>
 --%>
<script type="text/javascript">
$(document).ready(function() {
	try{
		initShowType();	
	}catch(e){
		log(e);
	}
});
</script>

<script type="text/javascript">
$(document).ready(function() {
	try{
		if (comparedItemsArray && comparedItemsArray.length > 0){
			for (var kk = 0; kk < comparedItemsArray.length > 0; kk++){
				$("a[compareid='" + comparedItemsArray[kk].id + "']").attr("inCompareItem", "true");
			  $("a[compareid='" + comparedItemsArray[kk].id + "']").addClass("addCompare");//css("background-color", "#cb4f4d");
			}
		}
	}catch(e){
		if(window.console) console.log(e);
	}

	try{
		$("#dynamichousecount").text($("#houseTotleRows").val());
		
		//var objs = $('input[name="pageSize"]');
		var objs = document.getElementsByName("pageSize");
		if(objs.length>0){
			var _pageSize = objs[0].value;
			var _totalRows = $("#houseTotleRows").val();
			if(_pageSize != 0){
				//var _totalPage = parseInt(_totalRows) / parseInt(_pageSize);
				//if(parseInt(_totalRows) % parseInt(_pageSize)>0) _totalPage++;
				
				var _totalPage =Math.ceil( parseInt(_totalRows) / parseInt(_pageSize) );
				// if(parseInt(_totalRows) % parseInt(_pageSize)>0) _totalPage++;
				if(_totalPage <= 1){
					_totalPage = 1;
					//$('#pageAId2').hide();
				}
				$("#pageLabel").text('${pageBean.currentPage }');
				$("#totalPages").text(_totalPage);
			}
		}
	}catch(e){
		if(window.console) console.log(e);
	}
});

/**
 * 显示模式切换后刷新页面
 */
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

<%--
function saveCookies(_showTypeFlag) {
	if(searchMap){
		searchMap.put("ispage", "1");
	}

	switchShowType(_showTypeFlag);
	
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
	$.cookie('lastSelected', _showTypeFlag, { expires : 365 }, { path : "/" }); // 存储 cookie , expires: 设定时间天数参数， path：设置cookies存储路径参数
}

function setType0() {
	setJSONValue('type', 0);
	type3 = 0;
}

function setType1() {
	setJSONValue("type", 1);
	type3 = 1;
}
--%>

</script>
<div id="listTop"></div>
<div id="image_list" style="display: none;">
	<%@include file="/WEB-INF/jsp/ddhb/front/house_list.jsp"%>
	<div style="clear: both; height: 0px;"></div>
</div>
<input type="hidden" id="houseTotleRows" value="${pageBean.totalRows}" />
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
			<c:forEach var="house" items="${pageBean.dataList}" varStatus="status">
				<li>
					<div class="bianh" onclick="window.open('${globalUrl}/houseSecond.show?actionMethod=houseSecondDetail&houseNo=${house.houseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}')" style="cursor: pointer;">
						<c:if test="${house.pictureUrl eq null}">
							<img src="${globalUrl}/image/house_no_picture.png">
						</c:if>
						<c:if test="${house.pictureUrl ne null }">
							<img src="${pictureHost }${house.pictureUrl }" width="226" height="170">
						</c:if>
						<span>共${house.housePicSize}张</span>
					</div>
					<div class="wd_rt">
						<div class="nbbh">内部编号：<span>${house.houseNo}</span></div>
						<div class="biaot">
							<a onclick="window.open('${globalUrl}/houseSecond.show?actionMethod=houseSecondDetail&houseNo=${house.houseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}')">${(houseAppraise != null) ? houseAppraise.title : house.title}</a>
						</div>
						<div class="xiaoq">
							<span>
								<a onclick="window.open('${globalUrl}/houseSecond.show?actionMethod=dimquery&type=1&ddhb_one_community.erpId=${house.community.erpId }')">${house.community.communityName} </a>
							</span>
						</div>
						<div class="maid">
						<%-- 
							<c:forEach items="${tagList}" var="tags">
								<c:forEach items="${house.tagIdList}" var="tagId">
									<c:if test="${tags.erpId == tagId}">
										<a onclick="window.open('${globalUrl}/houseSecond.show?actionMethod=dimquery&type=1&ddhb_one_tags=${house.tags}')">
											<span style="background-color:${tags.tagColor}; color:${tags.fontColor};">&nbsp;${tags.tagName} &nbsp;</span>
										</a>
									</c:if>
								</c:forEach>
							</c:forEach>
							--%>
							<c:forEach items="${house.houseTags}" var="tag">
								<span style="background-color:${tag.tagColor}; color:${tag.fontColor};">${tag.tagName}</span>
							</c:forEach>
							<!-- 对比 和 收藏 START -->
							<a housePictureUrl="${pictureHost}${house.pictureUrl }" inCompareItem="false" 
						    onclick="event.cancelBubble = true;addCompareItem(this, 'secondHouseCompare')"
						    compareId="${house.houseNo }" brokerId="${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}" 
						    compareTitle="${(houseAppraise != null) ? houseAppraise.title : house.title}"
						    area="${house.area }"  shi="${house.shi }" ting="${house.ting }" comparePrice="${house.price}" 
						    compareUnitPrice="${house.unitPrice}" class="dbi_lik fr">对比</a>
						<c:if test="${LoginMember == null }">
							<a href="javascript:void(0);" collId="${house.collectId}" hosueobjectId="${house.objectId}" 
							     brokerId="${house.broker.erpId }" houseprice="${house.price}"  class="shouc_lik fr"
							     onclick="event.cancelBubble = true;loginBox('collect');">收藏</a>
						</c:if>
						<c:if test="${LoginMember != null }">
							<c:if test="${house.collectId != null}">
								<a id="collect${house.erpId}two"  href="javascript:void(0);" class="shouc_lik fr"
								collId="${house.collectId}" hosueobjectId="${house.objectId}" houseprice="${house.price}" isCollect="0" 
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
						<div class="jushi">
							<p>
								<a onclick="window.open('${globalUrl}/houseSecond.show?actionMethod=dimquery&type=1&ddhb_one_community.region.erpId=${house.community.region.erpId}&tabIndex=0')">
									${house.community.region.countyName }</a> - <a onclick="window.open('${globalUrl}/houseSecond.show?actionMethod=dimquery&type=1&ddhb_one_community.region.erpId=${house.community.region.erpId}&tabIndex=0&ddhb_one_community.cbd.parentCBD.websiteId=${house.community.cbd.parentCBD.websiteId}')">
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
						<%--<div class="xq_an">
						<a housePictureUrl="${pictureHost}${house.houseUrl }" inCompareItem="false" 
						    onclick="event.cancelBubble = true;addCompareItem(this, 'secondHouseCompare')"
						    compareId="${house.houseNo }" brokerId="${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}" 
						    compareTitle="${(houseAppraise != null) ? houseAppraise.title : house.title}"
						    area="${house.area }"  shi="${house.shi }" ting="${house.ting }" comparePrice="${house.price}" 
						    compareUnitPrice="${house.unitPrice}" class="phoneCompare">对比</a>
						<c:if test="${LoginMember == null }">
							<a href="javascript:void(0);" collId="${house.collectId}" hosueobjectId="${house.objectId}" 
							     brokerId="${house.broker.erpId }" houseprice="${house.price}" 
							     onclick="event.cancelBubble = true;loginBox('collect');">收藏</a>
						</c:if>
						<c:if test="${LoginMember != null }">
							<c:if test="${house.collectId != null}">
								<a id="collect${house.erpId}two"  href="javascript:void(0);" style="background-color:#cb4f4d" 
								collId="${house.collectId}" hosueobjectId="${house.objectId}" houseprice="${house.price}" isCollect="0" 
								brokerId="${house.broker.erpId }" onclick="event.cancelBubble = true;isCollectHouseSend(this);" >收藏</a>
							</c:if>
							<c:if test="${house.collectId == null}">
								<a id="collect${house.erpId}two"  href="javascript:void(0);" collId="${house.collectId}" 
								    hosueobjectId="${house.objectId}" houseprice="${house.price}" isCollect="1" 
								    brokerId="${house.broker.erpId }" onclick="event.cancelBubble = true;isCollectHouseSend(this);" >收藏</a>
							</c:if>
						</c:if>
						<a href="${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=${house.houseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}" onclick="event.cancelBubble = true;" class="xq">查看详情</a>
					</div>--%>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<div class=page>
	<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false" 
	formName="houseSecond" offerPageSize="20,50,100" isExistForm="true" 
	queryFunction="houseChangePages('${globalUrl}houseSecond.show?actionMethod=dimquery', '1');" />
</div>

<script type="text/javascript">
/*
function houseChangePages() {
	var currvalue = document.getElementsByName("currentPage")[0].value;
	var isSearch = "${isSearch}";
	setJSONValue("currentPage", currvalue);
	if(isSearch == 1){
		showMySelectedField("${globalUrl}/houseSearch.show?actionMethod=doSearch&houseType=1&searchInput=${searchinput}&isCutPage=1&searchType=1&requesttype=1&currentPage=" + currvalue);
	}else {
		showSelectedField("${globalUrl}/houseSecond.show?actionMethod=dimquery");
	}
}
*/
</script>
