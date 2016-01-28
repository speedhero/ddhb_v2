<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<title>租房 - ${title } </title>
<script type="text/javascript">
var globalUrl = '${globalUrl}';
$(document).ready(function() {
	/*
	var idStr = $.cookie("lastSelected");
	if (idStr == "imgShape") {
		$("#checkbtn1").addClass("a_1a");
		$("#checkbtn2").removeClass("a_2a");
		$("#checkbtn2").removeAttr("style").css("cursor", "pointer");
		$('#image_list').css("display", "block");
		$('#list_list').css("display", "none");
		setType0();
	} else {
		$("#checkbtn2").addClass("a_2a");
		$("#checkbtn1").removeClass("a_1a");
		$("#checkbtn1").removeAttr("style").css("cursor", "pointer"); 
		$('#list_list').css("display", "block");
		$('#image_list').css("display", "none");
		setType1();
	}
	*/
	
	if (comparedItemsArray && comparedItemsArray.length > 0){
		for (var kk = 0; kk < comparedItemsArray.length > 0; kk++){
			$("img[compareid='" + comparedItemsArray[kk].id + "']").attr("incompareitem", "true");
			$("img[compareid='" + comparedItemsArray[kk].id + "']").attr("src", "${globalUrl}images/compare/selectedCompareId.png");
		}
	}
	$("#dynamichousecount").text($("#houseTotleRows").val());
});

/**
 * 显示模式切换后，刷新页面
 */
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
	/*
	if (StringId == "dataShape") {
		$("#checkbtn2").addClass("a_2a");
		$("#checkbtn1").removeClass("a_1a");
		$("#checkbtn1").removeAttr("style").css("cursor", "pointer"); 
		$('#list_list').css("display", "block");
		$('#image_list').css("display", "none");
		setType1();
	} else {
		$("#checkbtn1").addClass("a_1a");
		$("#checkbtn2").removeClass("a_2a");
		$("#checkbtn2").removeAttr("style").css("cursor", "pointer");
		$('#image_list').css("display", "block");
		$('#list_list').css("display", "none");
		setType0();
	}*/
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
<style>
.listNumberFontStyle{
	font-size:18px;
	font-family:黑体;
	color: #d04042;
}
</style>
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
            	<li onclick="hoverColumnItem(this);">
                	<div class="bianh"><p>${house.hourseNo}</p><a href="javascript:void(0);""></a></div>
                    <div class="wd_rt">
                        <div class="nbbh">内部编号：<span>${house.hourseNo}</span></div>
                        <div class="biaot"><a href="#">${(houseAppraise != null) ? houseAppraise.title : house.title}</a></div>
                        <div class="xiaoq">${house.community.communityName}<p>${house.community.region.countyName } - ${house.community.cbd.parentCBD.name }</p></div>
                        <div class="maid">
                        	<c:forEach var="furs" items="${furList}">
								<c:forEach var="furId" items="${house.furIdList}">
									<c:if test="${furs.erpId == furId}">
										<span >
											<img src="${pictureHost}${furs.imgUrl}"  title="<c:out value="${furs.furName }"></c:out>">
										</span>
									</c:if>
								</c:forEach>
							</c:forEach>
                        </div>
                        <div class="jushi"><b>${house.shi}</b>室<b>${house.ting}</b>厅</div>
                        <div class="mianji"><fmt:formatNumber value="${house.area}" type="currency" pattern="#" /><p class="unitFont" style="margin-top:5px;">平米</p></div>
                        <div class="danjia">${house.rentType.rentTypeName}</div>
                        <div class="zjia"><fmt:formatNumber value="${house.rentPrice}" type="currency" pattern="#" /><p class="unitFont" style="margin-top:5px;">元/月</p></div>
                        <div class="xq_an">
                        		<a housePictureUrl="${pictureHost}${house.houseUrl }" inCompareItem="false" class="compareButton dbi_lik fl" onclick="event.cancelBubble = true;addCompareItem(this, 'rentHouseCompare')"
									  compareId="${house.hourseNo }" brokerId="${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}" compareTitle="${(houseAppraise != null) ? houseAppraise.title : house.title}"
									  area="${house.area }"  shi="${house.shi }" ting="${house.ting }" comparePrice="${house.rentPrice}" >对比</a>
                                 <c:if test="${LoginMember == null }">
								<a href="javascript:void(0);" collId="${house.collectId}" class="shouc_lik fl" onclick="event.cancelBubble = true;loginBox('collect');">收藏</a>
								</c:if>
								<c:if test="${LoginMember != null }">
								<c:if test="${house.collectId != null}">
                                <a id="collect${house.erpId}two"  href="javascript:void(0);" class="shouc_lik fl" collId="${house.collectId}" hosueobjectId="${house.objectId}" houseprice="${house.rentPrice}" isCollect="0"
									onclick="event.cancelBubble = true;isCollectRent(this);" >收藏</a>
									</c:if>
									<c:if test="${house.collectId == null}">
                                <a id="collect${house.erpId}two"  href="javascript:void(0);"  collId="${house.collectId}" hosueobjectId="${house.objectId}"
										houseprice="${house.rentPrice}" isCollect="1" class="shouc_lik fl"
									onclick="event.cancelBubble = true;isCollectRent(this);" >收藏</a>
									</c:if>
								</c:if>
                                <a onclick="event.cancelBubble = true;" href="${globalUrl}rent.show?actionMethod=houseRentDetail&houseNo=${house.hourseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}" class="dbi_gz_lb_see fl">查看详情</a>
                                </div>
                    </div>
                </li>
            </c:forEach>
            </ul>
        </div>
</div>
<c:if test="${pageBean.totalRows eq 0 }">	
</c:if>
<div class="page">
<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false"
	formName="houseRent" offerPageSize="20,50,100" isExistForm="true"
	queryFunction="changePages('${globalUrl}rent.show?actionMethod=dimquery', '2');" />
</div>
<script type="text/javascript">
/*
function changePages() {
	var currvalue = document.getElementsByName("currentPage")[0].value;
	setJSONValue("currentPage", currvalue);
	var isSearch = "${isSearch}";
	if(isSearch == 1){
		showMySelectedField("${globalUrl}houseSearch.show?actionMethod=doSearch&houseType=2&searchInput=${searchinput}&isCutPage=1&searchType=1&requesttype=1&currentPage=" + currvalue);
	}else {
		showSelectedField("${globalUrl}rent.show?actionMethod=dimquery");
	}
}

function showMySelectedField(actionUrl){
	postDataByURL2(actionUrl , null, "changelist");
}
*/
</script>