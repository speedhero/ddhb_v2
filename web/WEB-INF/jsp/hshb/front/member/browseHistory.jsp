<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="cp_rt">
 	<div class="licn"><a onclick='window.location.href="${globalUrl}"'>首页</a>&nbsp;>&nbsp;个人中心&nbsp;>&nbsp;我的足迹</div>
     <div class="gr_xxi">
     	<c:if test="${houseType == 0 }">您好，你目前共浏览了<b>${pageBean.totalRows }</b>次二手房源。</c:if>
     	<c:if test="${houseType == 1 }">您好，你目前共浏览了<b>${pageBean.totalRows }</b>次租赁房源。</c:if>
     	<c:if test="${houseType == 2 }">您好，你目前共浏览了<b>${pageBean.totalRows }</b>次小区房源。</c:if>
     	<c:if test="${houseType == null }">您好，你目前共浏览了<b>${pageBean.totalRows }</b>次房源，其中二手房<b><c:if test="${secondCount == null }">0</c:if><c:if test="${secondCount != null }">${secondCount}</c:if></b>条，租赁房<b><c:if test="${rentCount == null }">0</c:if><c:if test="${rentCount != null }">${rentCount}</c:if></b>条，小区<b><c:if test="${commCount == null }">0</c:if><c:if test="${commCount != null }">${commCount}</c:if></b>条。</c:if>
     </div>
     <form:form name="browseForm" id="browseForm" action="${globalUrl}usercenter.do?actionMethod=browseHistory&isCutPage=true">
     <div class="gr_ls">
     	<div class="gr_tit">
         	<ul>
             	<li class="li_1">序号</li>
             	<li class="li_2">名称</li>
             	<li class="li_3">类型</li>
             	<li class="li_4">面积</li>
             	<li class="li_5">价格/租金</li>
             	<li class="li_6">最后浏览时间</li>
             </ul>
         </div>
         <c:forEach var="browseHistory" items="${pageBean.dataList}" varStatus="status" >
         <div class="gr_nlb">
         	<div class="gr_nrb">
                 <ul>
                     <li class="li_1"><b><c:out value="${(pageBean.currentPage - 1) * pageBean.pageSize + status.index + 1 }"></c:out></b></li>
                     <li class="li_2">
						<c:if test="${browseHistory.objectType eq 0 }">
							<a href="${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=${browseHistory.secondHouse.houseNo}&brokerId=${browseHistory.secondHouse.broker.erpId}"><c:out value="${browseHistory.secondHouse.title }"></c:out></a>
						</c:if>
						<c:if test="${browseHistory.objectType eq 1 }">
							<a href="${globalUrl}rent.show?actionMethod=houseRentDetail&houseNo=${browseHistory.rentHouse.hourseNo}&brokerId=${browseHistory.rentHouse.broker.erpId}"><c:out value="${browseHistory.rentHouse.title }"></c:out></a>
						</c:if>
						<c:if test="${browseHistory.objectType eq 2 }">
							<a href="${globalUrl}community.show?actionMethod=communityDetail&id=${browseHistory.community.erpId}"><c:out value="${browseHistory.community.communityName }"></c:out></a>
						</c:if>
					</li>
					<li class="li_3"><span>类型:</span>
						<c:if test="${browseHistory.objectType eq 0 }">
							二手
						</c:if>
						<c:if test="${browseHistory.objectType eq 1 }">
							租赁
						</c:if>
						<c:if test="${browseHistory.objectType eq 2 }">
							小区
						</c:if>
					</li>
                     
                     <li class="li_4"><span>面积:</span>
						<c:if test="${browseHistory.objectType eq 0 }">
							<fmt:formatNumber value="${browseHistory.secondHouse.area }" pattern="0"/>平米
						</c:if>
						<c:if test="${browseHistory.objectType eq 1 }">
							<fmt:formatNumber value="${browseHistory.rentHouse.area }" pattern="0"/>平米
						</c:if>
						<c:if test="${browseHistory.objectType eq 2 }">
							<fmt:formatNumber value="${browseHistory.community.floorArea }" pattern="0"/>平米
						</c:if>
					</li>
                     <li class="li_5"><span>价格/租金:</span>
						<c:if test="${browseHistory.objectType eq 0 }">
							<fmt:formatNumber value="${browseHistory.secondHouse.price/10000 }" pattern="0"/>万元
						</c:if>
						<c:if test="${browseHistory.objectType eq 1 }">
							<fmt:formatNumber value="${browseHistory.rentHouse.rentPrice }" pattern="0"/>元/月
						</c:if>
						<c:if test="${browseHistory.objectType eq 2 }">
							<%-- <fmt:formatNumber value="${browseHistory.community.currentSHAvePrice }" pattern="0"/> 元/平米 --%>
						</c:if>
					</li>
                     <li class="li_6"><span>最后浏览时间:</span>
                     <fmt:formatDate value="${browseHistory.modifiedTime }" pattern="yy-MM-dd HH:mm"/>
                     </li>
                 </ul>
             </div>
             <div class="clear"></div>
         </div>
         </c:forEach>
         <input name="houseType" type="hidden" value="${houseType }">
     </div>
     <div class="page" style="display:block;">
	<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false" formName="browseForm" offerPageSize="20,50,100" isExistForm="true" showAreal="usercenterDetailDiv"/>
</div>
</form:form>
</div>
