<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
function cancel(mid){
var r=confirm("您确认要取消吗？");
if(r==true){
	$.ajax({
		type : "post",
		url : "${globalUrl}usercenter.do?actionMethod=cancelAppointment&mid=" + mid,
		dataType : "json",
		success : function (data) {
			if (data.result == 'success') {
				alert("取消成功！");
				window.location.href = "${globalUrl}usercenter.do?actionMethod=appointment&isCutPage=false";
			}else {
				alert("操作失败！");
			}
		}
	}); 
  }else{ }
}
</script>
<div class="cp_rt">
     	<div class="licn"><a onclick='window.location.href="${globalUrl}"'>首页</a>&nbsp;>&nbsp;个人中心&nbsp;>&nbsp;预约管理</div>
         <div class="gr_xxi">您好，你目前共预约<b>${pageBean.totalRows }</b>次带看服务，其中二手房<b>${secondCount }</b>条，租房信息<b>${rentCount }</b>条。</div>
         <form:form name="appointForm" id="appointForm" action="${globalUrl}usercenter.do?actionMethod=appointment&isCutPage=true">
         <div class="gr_ls">
         	<div class="gr_tit">
             	<ul>
                 	<li class="li_1">序号</li>
                 	<li class="li_2">名称</li>
                 	<li class="li_3">类型</li>
                 	<li class="li_4">面积</li>
                 	<li class="li_5">价格/租金</li>
                 	<li class="li_5">计划时间</li>
                 	<li class="li_5" style="text-align: center;">操作</li>
                 </ul>
             </div>
             <c:forEach var="house" items="${pageBean.dataList}" varStatus="status">
             <div class="gr_nlb">
             	<div class="gr_nrb">
                     <ul>
                         <li class="li_1"><b><c:out value="${(pageBean.currentPage - 1) * pageBean.pageSize + status.index + 1 }"></c:out></b></li>
                         <li class="li_2">
					<c:if test="${house.houseType eq 1 }"><c:out value="${house.secondHouse.title }"></c:out></c:if>
					<c:if test="${house.houseType eq 2 }"><c:out value="${house.rentHouse.title }"></c:out></c:if>
				</li>
                         <li class="li_3"><span>类型:</span>
					<c:if test="${house.houseType eq 1 }">二手房</c:if>
					<c:if test="${house.houseType eq 2 }">租赁房</c:if>
				</li>
                         
                         <li class="li_4"><span>面积:</span>
					<c:if test="${house.houseType eq 1 }">
						<fmt:formatNumber value="${house.secondHouse.area }" pattern="0"/>平米
					</c:if>
					<c:if test="${house.houseType eq 2 }">
						<fmt:formatNumber value="${house.rentHouse.area }" pattern="0"/>平米
					</c:if>
				</li>
                         <li class="li_5"><span>价格/租金:</span>
				<c:if test="${house.houseType eq 1 }">
					<fmt:formatNumber value="${house.secondHouse.price/10000 }" pattern="0"/>万元
				</c:if>
				<c:if test="${house.houseType eq 2 }">
					<fmt:formatNumber value="${house.rentHouse.rentPrice }" pattern="0"/>元/月
				</c:if>
				</li>
				 <li class="li_5"><span>计划时间:</span>
				<div><fmt:formatDate value="${house.bespeakTimeStart }" pattern="yyyy-MM-dd"/></div>
				<div><fmt:formatDate value="${house.bespeakTimeStart }" pattern="HH:mm"/>-<fmt:formatDate value="${house.bespeakTimeEnd }" pattern="HH:mm"/></div>
				</li>
                         <li class="li_5"><a href="javascript:void(0);" class="dy" onclick="cancel(${house.id});">取消预约</a></li>
                     </ul>
                 </div>
                 <div class="clear"></div>
             </div>
             </c:forEach>
         </div>
         <div class="page" style="display:block;">
	<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false" formName="appointForm" offerPageSize="20,50,100" isExistForm="true" showAreal="usercenterDetailDiv"/>
</div>
</form:form>
</div>
