<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.huatek.hbwebsite.util.FrontSystemSettingUtil"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="/WEB-INF/tld/hshb.tld"%>
<%-- 
<script type="text/javascript" src="${globalUrl}js/detail/detail.js"></script>
<style>
	.wd_ls .hover .bianh a {background: url("../ddhb/image/y_jian_xq.gif") no-repeat scroll 0 0 rgba(0, 0, 0, 0);}
	@media screen and (max-width: 758px) {
		#selectorder{display:none;}
		.lbt span {padding-right: 4px;}
	}
	.db_tit{background: rgb(255, 102, 0);}
	.duibi{border-color: rgb(255, 102, 0);}
	.db_tit a.one, .db_tit a:hover{border-top-color: rgb(255, 102, 0);}
	.db_ls li.nob p a{color:rgb(255, 102, 0);}
	.db_tit span a:hover{background: rgb(255, 102, 0);}
	.an_xq a{height:25px;line-height:25px;margin-right:10px;float:left;color:#fff; background:#adadad;padding:0 10px;}
	.an_xq a:hover{ background:rgb(255, 102, 0);}
</style>
<script type="text/javascript" src="${globalUrl}js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${globalUrl}js/jquery.scrollTo.1.4.js"></script>
<script type="text/javascript" src="${globalUrl}js/cookie/cookie.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$.scrollTo('#listTop',500);
});
</script>

<script type="text/javascript">
$(document).ready(function(){
	//initShowType();
});
</script>
--%>
<script type="text/javascript">
<%-- 
var searchURL = '${sharedUrl}';
$(document).ready(function() {
	if (comparedItemsArray && comparedItemsArray.length > 0){
		for (var kk = 0; kk < comparedItemsArray.length > 0; kk++){
			$("a[compareid='" + comparedItemsArray[kk].id + "']").attr("inCompareItem", "true");
		  $("a[compareid='" + comparedItemsArray[kk].id + "']").addClass("addCompare");//css("background-color", "#cb4f4d");
		}
	}
	$("#dynamichousecount").text($("#houseTotleRows").val());
});

/**
 * 刷新页面
 
function refreshPageAfterChangeShowType(){
	var isSearch = "${isSearch}";
	var communityId = "${comId}";
	if("" != communityId){
		showSelectedField("${globalUrl}community.show?actionMethod=dimquery");
	}else{
		showSelectedField(searchURL);
	}
}
*/
--%>
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
	}
*/
	switchShowType(_showTypeFlag);

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
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=cancleMyCommunityCollection&collectionId=" + objId;
	_keepOff(actionUrl, id, 3);
}

function isCollectCommunity(obj){
	var $clickCollect = $(obj);
	if($clickCollect.attr("isCollect")==0) {
		//取消收藏
		keepOff($clickCollect.attr("id"),$clickCollect.attr("collId"));	
	} else {
		//添加收藏
		keepOppen($clickCollect.attr("id"),$clickCollect.attr("collId"), 2);
	}
}

function changeRelativeSelect(selecctedValue) {
	sortselect('${globalUrl}rent.show?actionMethod=dimquery');
}
--%>
</script>
 
<div class="communityevery" id="image_list" style="display: none;">
	<div class="xqpc_ls">
		<ul>
			<c:forEach var="community" items="${communityList}">
				<li style="height: 280px;">
					<div class="pt" onclick="window.open('${globalUrl}xiaoqu/${community.erpId}.html')" style="cursor: pointer;">
						<a href="<h:surl communityId='${community.erpId }' />.html">
						<img src="${community.coverImage eq null? globalUrl.concat('/image/house_no_picture.png') : pictureHost.concat(community.coverImage)}" width="226" height="170">
						</a>
						<p>共有${community.picCount==null ? 0 : community.picCount} 张照片</p>
					</div>
					<div class="tj_txt" style="height: 90px;">
						<div class="name_a">
							<a onclick="window.open('${globalUrl}xiaoqu/${community.erpId}.html')">${community.communityName}</a>
						</div>
						<div class="pm_s">
								<b><fmt:formatNumber value="${community.currentMonthShAvgPrice }" pattern="#"></fmt:formatNumber></b>
								M<sup>2</sup>(均价)
						</div>
						<div class="pm">
							<span> <a onclick="${globalUrl}xiaoqu/a_${community.regionId}_">
									${community.countyName} </a>  - 
									<a onclick="${globalUrl}xiaoqu/a__${community.websiteId}">
									${community.websiteName} </a>
							</span>
							<p> 建成年代：<fmt:formatDate value="${community.buildYear}" pattern="yyyy" /></p>
						</div>
						<div class="pm_sx">
							<span style="width: 50%; float: left;">二手房数量：${community.shCount}</span>
							<span style="width: 50%; float: left;">出租房数量：${community.rentCount}</span>
						</div>
						<%--
						<div class="an_xq">
							<a href="community.show?actionMethod=communityDetail&id=${community.erpId}" class="xq">查看详情</a>
							<c:if test="${LoginMember == null }">
								<a href="javascript:void(0);" collId="${community.collectId}" onclick="loginBox('collect');">收藏</a>
							</c:if>
							<c:if test="${LoginMember != null }">
								<c:if test="${community.collectId != null}">
									<a id="collect${community.erpId}" style="background-color:#cb4f4d" collId="${community.erpId}"  isCollect="0" onclick="isCollectInformation(this,3);" >收藏</a>
								</c:if>
								<c:if test="${community.collectId == null}">
									<a id="collect${community.erpId}" collId="${community.erpId}"  isCollect="1" onclick="isCollectInformation(this,3);">收藏</a>
								</c:if>
							</c:if>
							<a housePictureUrl="${pictureHost}${community.communityUrl }" inCompareItem="false" class="compareButton phoneCompare" 
								onclick="addCompareItem(this, 'communityCompare');"
								compareId="${community.erpId }" compareTitle="${community.communityName }"
								area="${community.communityArea }" comparePrice="${community.currentSHAvePrice}">对比</a>
						</div>
						--%>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div style="clear: both; height: 0px;"></div>
</div>
<input type="hidden" id="houseTotleRows" value="${pageBean.total}"/>
<div id="list_list" style="display: none;">
	<div class="lb_lx" style="display: none;">
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
			<c:forEach var="community" items="${communityList}" varStatus="status">
				<li>
					<div class="bianh" style="cursor: pointer;">
						<a href="<h:surl communityId='${community.erpId }' />.html" target="_blank">
						<img src="${community.coverImage eq null? globalUrl.concat('/image/house_no_picture.png') : pictureHost.concat(community.coverImage)}" width="226" height="170">
						</a>
						<p>共有 ${community.picCount==null ? 0 : community.picCount } 张照片</p>
					</div>
					<div class="wd_rt">
						<div class="nbbh">
							内部编号： <span>${community.erpId}</span> <br> 小区面积（平米）： <span>${community.communityArea }</span>
							<b>绿化率：<span>${community.landscapingRatio }</span></b>
						</div>
						<div class="biaot">
							<a onclick="window.open('${globalUrl}xiaoqu/${community.erpId}.html')" target="_blank">${community.communityName}</a>
						</div>
						<div class="xiaoq_xq">
							<p>
								<a onclick="window.open('${globalUrl}xiaoqu/a_${community.regionId}_')">
									${community.countyName} </a> - <a
									onclick="window.open('${globalUrl}xiaoqu/a__${community.websiteId}')">
									${community.websiteName} </a>
							</p>
						</div>
						<div class="jushi_xq" style="padding:0; margin:0; width:100%;"><span style="float:left;">二手房：${community.shCount }套；出租房：${community.rentCount }套；</span>
								<c:if test="${community.rentCount gt 0 }">
									<a class="dbi_gz_lb_see" onclick="window.open('${globalUrl}chuzu/a_${community.regionId}_${community.websiteId}')">查看在租房源</a>
								</c:if>
								<c:if test="${community.shCount gt 0 }">
									<a class="dbi_gz_lb_see" onclick="window.open('${globalUrl}chushou/a_${community.regionId}_${community.websiteId}')">查看在售房源</a>
								</c:if>
						<a class="dbi_gz_lb_see" onclick="window.open('${globalUrl}xiaoqu/${community.erpId}.html')">查看小区</a>
							</div>
						<div class="zjia">
							<p>均价</p>
							<span><fmt:formatNumber value="${community.currentMonthShAvgPrice }" pattern="#"></fmt:formatNumber></span>
							<p>（元/平米）</p>
						</div>
						<div class="danjia">
							<c:if test="${community.huanbishangyuerh ne 0}">
								<span>90天内变化：</span>
								<p>
									<c:if test="${community.huanbishangyuerh ge 0}"><span style="padding-left: 13px" class="s">${community.huanbishangyuerh }%</span></c:if>
									<c:if test="${community.huanbishangyuerh lt 0}">
										<span style="padding-left: 13px" class="x"> <c:out value="${fn:substring(community.huanbishangyuerh, 1, 5 )}"></c:out>%</span>
									</c:if>
								</p>
								<div style="clear: both;"></div>
							</c:if>
						</div>
						<div class="daikan_xq">
							<span>建成年代：<fmt:formatDate value="${community.buildYear}" pattern="yyyy" /></span> 
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<c:if test="${communityList.size() eq 0 }">
	<h3 style="color: red; margin-top: 20px; margin-left: 50px">很抱歉，没有查询到符合要求的小区信息！</h3>
</c:if>
<div class="page">
<c:import url="/WEB-INF/jsp/hshb/front/commonInfoShow/pager.jsp"/>
</div>