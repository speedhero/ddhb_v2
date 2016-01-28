<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!--<style>
.listNumberFontStyle{font-size:18px;font-family:黑体;color: #d04042;}
.pc_ls .name_a a:hover {color: #ff9b4b;}
.db_tit{background: rgb(255, 155, 75);}
.duibi{border-color: rgb(255, 155, 75);}
.db_tit a.one, .db_tit a:hover{border-top-color: rgb(255, 155, 75);}
.db_ls li.nob p a{color:rgb(255, 155, 75);}
.db_tit span a:hover{background: rgb(255, 155, 75);}
#nameTitle a:hover{color: #ff9b4b;}
</style>-->
<%--
<script type="text/javascript" src="${globalUrl}js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${globalUrl}js/jquery.scrollTo.1.4.js"></script>
<script type="text/javascript" src="${globalUrl}js/cookie/cookie.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.scrollTo('#listTop',500);
});
</script>
 --%>
<script type="text/javascript">
function loginBox2(target, housetype){
	var url = "${globalUrl}login.show?actionMethod=loginCheck&target="+target;
	_openDialog('login', "&nbsp;&nbsp;登录", url, "get", "html");
}
</script>
<!-- 图片显示 -->
<div id="image_list" style="display:none;">
	<%@include file="/WEB-INF/jsp/ddhb/front/rent_list.jsp"%>
	<div style="clear: both; height: 0px;"></div>
</div>
<input type="hidden" id="houseTotleRows" value="${pageBean.totalRows}" />
<!-- 列表 -->
<div id="list_list" style="display:none;">
	<div class="lb_lx" style="display:none;">
		<ul>
			<li class="li_1">内部编号</li>
			<li class="li_2">标题</li>
			<li class="li_3">小区区域</li>
			<li class="li_4">室内配置</li>
			<li class="li_5">居室</li>
			<li class="li_6">面积</li>
			<li class="li_7">租赁方式</li>
			<li class="li_8">租金</li>
		</ul>
	</div>
	<div class="wd_ls">
		<ul>
			<c:forEach var="house" items="${pageBean.dataList}" varStatus="status">
				<li>
					<div class="bianh"
						onclick="window.open('${globalUrl}/rent.show?actionMethod=houseRentDetail&houseNo=${house.hourseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}')"
						style="cursor: pointer;">
						<c:if test="${house.pictureUrl eq null}"><img src="${globalUrl}/image/house_no_picture.png"></c:if>
						<c:if test="${house.pictureUrl ne null }"><img src="${pictureHost }${house.pictureUrl }" width="226" height="170" /></c:if>
						<span>共${house.housePicSize}张</span>
					</div>
					<div class="wd_rt">
						<div class="nbbh">内部编号：<span>${house.hourseNo}</span>
						</div>
						<div class="biaot" id="nameTitle">
							<a onclick="window.open('${globalUrl}/rent.show?actionMethod=houseRentDetail&houseNo=${house.hourseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}')">${(houseAppraise != null) ? houseAppraise.title : house.title}</a>
						</div>
						<div class="xiaoq">
							<span> <a onclick="window.open('${globalUrl}/rent.show?actionMethod=dimquery&type=1&ddhb_one_community.erpId=${house.community.erpId }')">
									${house.community.communityName} </a>
							</span>
							<p>
								<a onclick="window.open('${globalUrl}/rent.show?actionMethod=dimquery&type=1&ddhb_one_community.region.erpId=${house.community.region.erpId}&tabIndex=0')">
									${house.community.region.countyName } </a> - <a onclick="window.open('${globalUrl}/rent.show?actionMethod=dimquery&type=1&ddhb_one_community.region.erpId=${house.community.region.erpId}&tabIndex=0&ddhb_one_community.cbd.parentCBD.websiteId=${house.community.cbd.parentCBD.websiteId}')">
									${house.community.cbd.parentCBD.name } </a>
							</p>
						</div>
						<div class="maid_lb">
							<c:forEach var="furs" items="${furList}">
								<c:forEach var="furId" items="${house.furIdList}">
									<c:if test="${furs.erpId == furId}">
										<span> <img src="${pictureHost}${furs.imgUrl}" title="<c:out value='${furs.furName }'></c:out>">
										</span>
									</c:if>
								</c:forEach>
							</c:forEach>
						</div>
						<div class="jushi">${house.shi}室${house.ting}厅</div>
						<div class="mianji"><fmt:formatNumber value="${house.area}" type="currency" pattern="#" />平米</div>
						<div class="danjia" style="text-align: right;">
							[${house.rentType.rentTypeName}] <br>
							<%-- 
						<c:if test="${house.previousRentPrice - house.rentPrice < 0}">已上涨${-(house.previousRentPrice - house.rentPrice)}元</c:if>
						<c:if test="${house.previousRentPrice - house.rentPrice > 0}">已降价${house.previousRentPrice - house.rentPrice }元</c:if>
						--%>
						</div>
						<div class="personCel">
							<a onclick="window.open('${globalUrl}/broker.show?actionMethod=brokerDetail&brokerId=${house.broker.erpId}&housetype=2')">${house.broker.bname}</a>
							<span>${house.broker.telephone}</span>
						</div>
						<div class="zjia">
							<span><fmt:formatNumber value="${house.rentPrice}" type="currency" pattern="#" /></span><p>元/月</p>
						</div>
						<div class="daikan">
							<%--
        			<span>带看${house.daikan}次</span> 
        			<p><a onclick="window.open('${globalUrl}/broker.show?actionMethod=brokerDetail&brokerId=${house.broker.erpId}&housetype=2')" >${house.broker.bname}</a> 
        	 &nbsp; ${house.broker.telephone}&nbsp;于  <fmt:formatDate value="${house.lastModified}" pattern="yyyy-MM-dd HH:mm:ss"/> 发布
        	 		</p> 
        	 		--%>
							<a housePictureUrl="${pictureHost}${house.pictureUrl }"
								inCompareItem="false" class="compareButton dbi_lik fl"
								onclick="event.cancelBubble = true;addCompareItem(this, 'rentHouseCompare')"
								compareId="${house.hourseNo }"
								brokerId="${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}"
								compareTitle="${(houseAppraise != null) ? houseAppraise.title : house.title}"
								area="${house.area }" shi="${house.shi }" ting="${house.ting }"
								comparePrice="${house.rentPrice}">对比</a>

							<c:if test="${LoginMember != null }">
								<c:if test="${house.collectId != null}">
									<a id="collect${house.erpId}two" href="javascript:void(0);"
										brokerid="${house.broker.erpId }"
										collId="${house.collectId}" class="shouc_lik fl"
										hosueobjectId="${house.erpId}" houseprice="${house.rentPrice}"
										isCollect="0"
										onclick="event.cancelBubble = true;isCollectRent(this);">收藏</a>
								</c:if>
								<c:if test="${house.collectId == null}">
									<a id="collect${house.erpId}two" href="javascript:void(0);"
										brokerid="${house.broker.erpId }" collId="${house.collectId}"
										hosueobjectId="${house.erpId}" houseprice="${house.rentPrice}"
										isCollect="1" class="shouc_lik fl"
										onclick="event.cancelBubble = true;isCollectRent(this);">收藏</a>
								</c:if>
							</c:if>
							<c:if test="${LoginMember == null }">
								<a class="shouc_lik fl" href="javascript:loginBox2();">收藏</a>
								<a onclick="loginBox()"></a>
							</c:if>
						</div>
						<%--
						<div class="xq_an">
							<a housePictureUrl="${pictureHost}${house.pictureUrl }" inCompareItem="false" class="compareButton phoneCompare" onclick="event.cancelBubble = true;addCompareItem(this, 'rentHouseCompare')"
							 compareId="${house.hourseNo }" brokerId="${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}" compareTitle="${(houseAppraise != null) ? houseAppraise.title : house.title}"
							 area="${house.area }"  shi="${house.shi }" ting="${house.ting }" comparePrice="${house.rentPrice}" >对比</a>
							<c:if test="${LoginMember == null }">
							 <a href="javascript:void(0);" collId="${house.collectId}" brokerid="${house.broker.erpId }" onclick="event.cancelBubble = true;loginBox('collect');">收藏</a>
							</c:if>
							<c:if test="${LoginMember != null }">
								<c:if test="${house.collectId != null}">
									<a id="collect${house.erpId}two"  href="javascript:void(0);" brokerid="${house.broker.erpId }" style="background-color:#cb4f4d" collId="${house.collectId}" hosueobjectId="${house.erpId}" houseprice="${house.rentPrice}" isCollect="0"
									onclick="event.cancelBubble = true;isCollectRent(this);" >收藏</a>
								</c:if>
								<c:if test="${house.collectId == null}">
									<a id="collect${house.erpId}two"  href="javascript:void(0);" brokerid="${house.broker.erpId }" collId="${house.collectId}" hosueobjectId="${house.erpId}"
									houseprice="${house.rentPrice}" isCollect="1"
									onclick="event.cancelBubble = true;isCollectRent(this);" >收藏</a>
								</c:if>
							</c:if>
							<a onclick="event.cancelBubble = true;" href="rent.show?actionMethod=houseRentDetail&houseNo=${house.hourseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}" class="xq">查看详情</a>
						</div>
						 --%>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<c:if test="${pageBean.totalRows eq 0 }">
<h3 style="color: red; margin-top: 20px; margin-left: 50px">很抱歉，没有查询到符合要求的租赁房源！</h3>
</c:if>
<div class="page">
<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false" formName="houseRent" 
offerPageSize="20,50,100" isExistForm="true" 
queryFunction="houseChangePages('${globalUrl}rent.show?actionMethod=dimquery', '2')" />
<%--houseChangePages() --%>
</div>
<script type="text/javascript">
$(document).ready(function(){
	initShowType();
});
</script>
<script type="text/javascript">
//var globalUrl = '${globalUrl}';
$(document).ready(function(){
	try{
		if (comparedItemsArray && comparedItemsArray.length > 0){
			for (var kk = 0; kk < comparedItemsArray.length ; kk++){
				$("a[compareid='" + comparedItemsArray[kk].id + "']").attr("inCompareItem", "true");
			  $("a[compareid='" + comparedItemsArray[kk].id + "']").addClass("addCompare");//css("background-color", "#cb4f4d");
			}
		}
	}catch(e){
		if(window.console) console.log(e);
	}
	$("#dynamichousecount").text($("#houseTotleRows").val());

	//页数
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
				$('#pageAId2').hide();
			}else{
				$('#pageAId2').show();
			}
			$("#pageLabel").text('${pageBean.currentPage }');
			$("#totalPages").text(_totalPage);
		}
	}
});

function refreshPageAfterChangeShowType(){
	if("${houseAppraise == null}" != "false") {
		var isSearch = "${isSearch}";
		if(isSearch == 1){
			window.location.href="${globalUrl}houseSearch.show?actionMethod=doSearch&houseType=1&searchInput=${searchinput}&searchType=1&requesttype=1";
		}else {
			var communityId = "${comId}";
			if("" != communityId){
				showSelectedField("${globalUrl}community.show?actionMethod=communityhouse&housetype=1&ispage=1");
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
			window.location.href="${globalUrl}houseSearch.show?actionMethod=doSearch&houseType=1&searchInput=${searchinput}&searchType=1&requesttype=1";
		}else {
			var communityId = "${comId}";
			if("" != communityId){
				showSelectedField("${globalUrl}community.show?actionMethod=communityhouse&housetype=1&ispage=1");
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
<script type="text/javascript">
<%--
function houseChangePages() {
	var currvalue = document.getElementsByName("currentPage")[0].value;
	setJSONValue("currentPage", currvalue);
	var isSearch = "${isSearch}";
	$("#pageLabel").text(currvalue);
	if(isSearch == 1){
		showMySelectedField("${globalUrl}/houseSearch.show?actionMethod=doSearch&houseType=2&searchInput=${searchinput}&isCutPage=1&searchType=1&requesttype=1&currentPage=" + currvalue);
	}else {
		showSelectedField("${globalUrl}/rent.show?actionMethod=dimquery");
	}
}

function showMySelectedField(actionUrl){
	postDataByURL2(actionUrl , null, "changelist");
}
--%>
</script>