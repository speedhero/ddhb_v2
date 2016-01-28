<%@page import="com.huatek.hbwebsite.util.FrontSystemSettingUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<title>小区 - ${title } </title>
<script type="text/javascript" src="${globalUrl}js/detail/detail.js"></script>
<style>
.wd_ls .hover .bianh a { background: url("${globalUrl}image/y_jian_xq.gif") no-repeat scroll 0 0 rgba(0, 0, 0, 0); }
@media screen and (max-width: 758px) {
#selectorder{display:none;}
.lbt span { padding-right: 4px; }
</style>
<script type="text/javascript">
$(document).ready(function() {
	initShowType();
	
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
	}*/
	if (comparedItemsArray && comparedItemsArray.length > 0){
		for (var kk = 0; kk < comparedItemsArray.length > 0; kk++){
			$("img[compareid='" + comparedItemsArray[kk].id + "']").attr("incompareitem", "true");
			$("img[compareid='" + comparedItemsArray[kk].id + "']").attr("src", "${globalUrl}images/compare/selectedCompareId.png");
		}
	}
	$("#dynamichousecount").text($("#houseTotleRows").val());
});

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
			window.location.href="${globalUrl}houseSearch.show?actionMethod=doSearch&houseType=3&searchInput=${searchinput}&searchType=1&requesttype=1";
		}else {
			if(searchInput == "" || searchInput == null) {
				showSelectedField('${globalUrl}community.show?actionMethod=dimquery');
			}else{
				showSelectedField(searchURL);
			}
		}
	}
	$.cookie('lastSelected', _showTypeFlag, { expires : 365 }, { path : "/" }); // 存储 cookie , expires: 设定时间天数参数， path：设置cookies存储路径参数
}

//添加小区收藏
function keepOppen(id,objId, type){
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=addCommunitytoCollection";
	var platCollection = {
			ObjectId: objId,
			collectType: type,
		};	
	_keepOppen(actionUrl, platCollection, 3, id);
}

//取消收藏
function keepOff(id,objId){
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=cancleMyCommunityCollection&collectionId=" + objId ;
	_keepOff(actionUrl, id, 3);
}
	
function isCollectCommunity(obj){
	var $clickCollect = $(obj);
	if($clickCollect.attr("isCollect")==0) {
	//取消收藏
		keepOff($clickCollect.attr("id"),$clickCollect.attr("collId"));	
	} else {
	//添加收藏
		keepOppen($clickCollect.attr("id"),$clickCollect.attr("collId"),2);
	}
	
}
function changeRelativeSelect(selecctedValue) {
	sortselect('${globalUrl}rent.show?actionMethod=dimquery');
}

</script>
<div class="communityevery" id="image_list" style="margin-top: 20px; display: none;">
	<div class="xqpc_ls">
           <ul>
           <c:forEach var="community" items="${pageBean.dataList}">
               <li>
                   <div class="pt" onclick="window.location.href='${globalUrl}community.show?actionMethod=communityDetail&id=${community.erpId}'" style="cursor: pointer;"><img src="${pictureHost }${community.communityUrl }" width="226" height="170"  alt=""/><p>共有 ${community.picCount } C张照片</p></div>
                   <div class="tj_txt">
                       <div class="name_a"><a href="#">${community.communityName}</a></div>
                       <div class="pm"><span>${community.region.countyName} - ${community.cbd.parentCBD.name}</span>建成年代：<fmt:formatDate value="${community.startSaleDate}" pattern="yyyy"/></div>
                       <div class="pm_sx">
                       	<div class="pm_s"><b><fmt:formatNumber value="${community.currentSHAvePrice }" pattern="#"></fmt:formatNumber></b><p style="padding-top:7px;">均价（元/平米）</p></div>
                           <div class="sx">
                           	<p>90天内变化：：<c:if test="${community.compareWithLastMonth ge  0}"><span style="padding-left:13px" class="s">${community.compareWithLastMonth }%</span></c:if><c:if test="${community.compareWithLastMonth lt 0}"><span style="padding-left:13px" class="x"><c:out value="${fn:substring(community.compareWithLastMonth, 1, 5 )}"></c:out>%</span></c:if></p>
                           	<%-- <p>同比去年：<c:if test="${community.aroundLastYear ge  0}"><span style="padding-left:13px" class="s">${community.aroundLastYear }%</span></c:if>--%>
                           	<c:if test="${community.aroundLastYear lt 0}"><span style="padding-left:13px" class="x"><c:out value="${fn:substring(community.aroundLastYear, 1, 5 )}"></c:out>%</span></c:if></p>
                           </div>
                       </div>
                       <div class="sl">
                           <span><p>二手房数量：</p>${community.shCount}</span>
                           <span><p>出租房数量：</p>${community.rentCount}</span>
                       </div>
                       <div class="an_xq">
                               <a href="${globalUrl}community.show?actionMethod=communityDetail&id=${community.erpId}" class="xq">查看详情</a>
                                <c:if test="${LoginMember == null }">
							<a href="javascript:void(0);" collId="${community.collectId}"
							onclick="loginBox('collect');">收藏</a>
							</c:if>
							<c:if test="${LoginMember != null }">
							<c:if test="${community.collectId != null}">
                               <a id="collect${community.erpId}" style="background-color:#cb4f4d" collId="${community.erpId}"  isCollect="0" onclick="isCollectCommunity(this);" >收藏</a>
								</c:if>
								<c:if test="${community.collectId == null}">
                               <a id="collect${community.erpId}" collId="${community.erpId}"  isCollect="1" onclick="isCollectCommunity(this);">收藏</a>
								</c:if>
							</c:if>
                               <a  housePictureUrl="${pictureHost}${community.communityUrl }" inCompareItem="false" class="compareButton phoneCompare" onclick="addCompareItem(this, 'communityCompare')"
							 compareId="${community.erpId }" compareTitle="${community.communityName }"
							 area="${community.communityArea }" comparePrice="${community.currentSHAvePrice}">对比</a>
                               </div>
                   </div>
               </li>
           </c:forEach>
           </ul>
       </div>
	<div style="clear: both; height: 0px;"></div>
</div>
	
<input type="hidden" id="houseTotleRows" value="${pageBean.totalRows}"/>
<div id="list_list" style="display: none;">
	<div class="lb_lx">
    	<ul>
        	<li class="li_1">内部编号</li>
        	<li class="li_2">小区名称</li>
        	<li class="li_3">小区区域</li>
        	<li class="li_9">二手房数量</li>
        	<li class="li_9">出租房数量</li>
        	<li class="li_9 li_10">小区面积<p>（平米）</p></li>
        	<li class="li_9 li_10">绿化率</li>
        </ul>
    </div>
			<div class="wd_ls">
        	<ul>
        	<c:forEach var="community" items="${pageBean.dataList}" varStatus="status">
            	<li onclick="hoverColumnItem(this);">
                	<div class="bianh"><p>${community.communityNo}</p><a ></a></div>
                    <div class="wd_rt">
                        <div class="nbbh">内部编号：<span>${community.communityNo}</span><br>小区面积（平米）：<span>${community.communityArea }</span><b>绿化率：<span>${community.landScaping }</span></b></div>
                        <div class="biaot"><a href="#">${community.communityName}</a></div>
                        <div class="xiaoq"><p>${community.region.countyName } - ${community.cbd.parentCBD.name }</p></div>
                        <div class="shu">${community.shCount }</div>
                        <div class="shu">${community.rentCount }</div>
                        <div class="shu yc">${community.communityArea }</div>
                        <div class="shu yc">${community.landScaping }</div>
						<div class="xq_an">
							<a housePictureUrl="${pictureHost}${community.communityUrl }"
								inCompareItem="false" class="compareButton phoneCompare"
								onclick="event.cancelBubble = true;addCompareItem(this, 'communityCompare')"
								compareId="${community.erpId }"
								compareTitle="${community.communityName }"
								area="${community.communityArea }"
								comparePrice="${community.currentSHAvePrice}">对比</a>
							<c:if test="${LoginMember == null }">
								<a href="javascript:void(0);" collId="${community.collectId}" onclick="event.cancelBubble = true;loginBox('collect');">收藏</a>
							</c:if>
							<c:if test="${LoginMember != null }">
								<c:if test="${community.collectId != null}">
									<a id="collect${community.erpId}two"
										style="background-color: #cb4f4d" collId="${community.erpId}"
										isCollect="0"
										onclick="event.cancelBubble = true;isCollectCommunity(this);">收藏</a>
								</c:if>
								<c:if test="${community.collectId == null}">
									<a id="collect${community.erpId}two"
										collId="${community.erpId}" isCollect="1"
										onclick="event.cancelBubble = true;isCollectCommunity(this);">收藏</a>
								</c:if>
							</c:if>
							<a
								href="${globalUrl}community.show?actionMethod=communityDetail&id=${community.erpId}"
								onclick="event.cancelBubble = true;" class="xq">查看详情</a>
						</div>
					</div>
                </li>
                </c:forEach>
            </ul>
        </div>

</div>
<c:if test="${pageBean.totalRows eq 0 }">
	<h3 style="color: red; margin-top: 20px; margin-left: 50px">很抱歉，没有查询到符合要求的小区信息！</h3>
</c:if>
<div class="page">
<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false"
		formName="community" offerPageSize="20,50,100" isExistForm="true" 
		queryFunction="houseChangePages()"/>
</div>

<script type="text/javascript">
function houseChangePages() {
	var currvalue = document.getElementsByName("currentPage")[0].value;
	var isSearch = "${isSearch}";
	setJSONValue("currentPage", currvalue);
	if(isSearch == 1){
		showMySelectedField("${globalUrl}houseSearch.show?actionMethod=doSearch&houseType=3&searchInput=${searchinput}&isCutPage=1&searchType=1&requesttype=1&currentPage=" + currvalue);
	}else {
		showSelectedField('${globalUrl}community.show?actionMethod=dimquery');
	}
}
</script>