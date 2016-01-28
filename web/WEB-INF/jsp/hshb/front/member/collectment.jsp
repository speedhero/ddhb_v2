<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
function cancel(mid){
	var r=confirm("您确认要删除吗？");
	if(r==true){
		$.ajax({
			type : "post",
			url : "${globalUrl}usercenter.do?actionMethod=delCollect&mid=" + mid,
			dataType : "json",
			success : function (data) {
				if (data.result == 'success') {
					alert("删除成功！");
					window.location.href = "${globalUrl}usercenter.do?actionMethod=collectment&isCutPage=false";
				}else {
					alert("操作失败！");
				}
			}
		}); 
	  }
}
</script>
<div class="cp_rt">
        	<div class="licn"><a onclick='window.location.href="${globalUrl}"'>首页</a>&nbsp;>&nbsp;个人中心&nbsp;>&nbsp;收藏夹管理</div>
            <div class="gr_xxi">您好，你目前收藏了<b>${pageBean.totalRows }</b>条信息，其中二手房信息<b>${secondCount }</b>条，租房信息<b>${rentCount }</b>条，小区信息<b>${communityCount }</b>条。</div>
            <form:form name="collectForm" id="collectForm" action="${globalUrl}usercenter.do?actionMethod=collectment&isCutPage=true">
            <div class="gr_ls">
            	<div class="gr_tit">
                	<ul>
                    	<li class="li_1">序号</li>
                    	<li class="li_2">名称</li>
                    	<li class="li_3">类型</li>
                    	<li class="li_4">面积</li>
                    	<li class="li_5">价格/租金</li>
                    	<li class="li_5" style="text-align: center;">操作</li>
                    </ul>
                </div>
                <c:forEach var="house" items="${pageBean.dataList}" varStatus="status">
                <div class="gr_nlb">
                	<div class="gr_nrb">
                        <ul>
                            <li class="li_1"><b><c:out value="${(pageBean.currentPage - 1) * pageBean.pageSize + status.index + 1 }"></c:out></b></li>
                            <li class="li_2">
								<c:if test="${house.collectType eq 0 }">
									<a href="${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=${house.houseSecond.houseNo}&brokerId=${house.houseSecond.broker.erpId}"><c:out value="${house.houseSecond.title }"></c:out></a>
								</c:if>
								<c:if test="${house.collectType eq 1 }">
									<a href="${globalUrl}rent.show?actionMethod=houseRentDetail&houseNo=${house.houseRent.hourseNo}&brokerId=${house.houseRent.broker.erpId}"><c:out value="${house.houseRent.title }"></c:out></a>
								</c:if>
								<c:if test="${house.collectType eq 2 }">
									<a href="${globalUrl}community.show?actionMethod=communityDetail&id=${house.community.erpId}"><c:out value="${house.community.communityName }"></c:out></a>
								</c:if>
							</li>
                            <li class="li_3"><span>类型:</span>
								<c:if test="${house.collectType eq 0 }">
									二手
								</c:if>
								<c:if test="${house.collectType eq 1 }">
									租赁
								</c:if>
								<c:if test="${house.collectType eq 2 }">
									小区
								</c:if>
							</li>
                            
                            <li class="li_4"><span>面积:</span>
								<c:if test="${house.collectType eq 0 }">
									<fmt:formatNumber value="${house.houseSecond.area }" pattern="0"/>平米
								</c:if>
								<c:if test="${house.collectType eq 1 }">
									<fmt:formatNumber value="${house.houseRent.area }" pattern="0"/>平米
								</c:if>
								<c:if test="${house.collectType eq 2 }">
									<fmt:formatNumber value="${house.community.floorArea }" pattern="0"/>平米
								</c:if>
							</li>
                            <li class="li_5"><span>价格/租金:</span>
								<c:if test="${house.collectType eq 0 }">
									<fmt:formatNumber value="${house.houseSecond.price/10000 }" pattern="0"/>万元
								</c:if>
								<c:if test="${house.collectType eq 1 }">
									<fmt:formatNumber value="${house.houseRent.rentPrice }" pattern="0"/>元/月
								</c:if>
								<c:if test="${house.collectType eq 2 }">
								</c:if>
							</li>
                            <li class="li_5"><a href="javascript:void(0);" class="dy" onclick="cancel(${house.id});">删除</a></li>
                        </ul>
                    </div>
                    <div class="clear"></div>
                </div>
                </c:forEach>
            </div>
            <div class="page" style="display:block;">
				<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false" formName="collectForm" offerPageSize="20,50,100" isExistForm="true" showAreal="usercenterDetailDiv"/>
			</div>
			</form:form>
        </div>
