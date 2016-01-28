<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
function cancel(mid){
	var r = confirm("您确认要取消吗？");
	if(r==true){
		$.ajax({
			type : "POST",
			url : "${globalUrl}usercenter.do?actionMethod=cancelPriceNotice&mid='" + mid + "'",
			dataType : "json",
			success : function (data) {
				if (data.result == 'success') {
					alert("取消成功！");
					window.location.href = "${globalUrl}usercenter.do?actionMethod=priceNotice&isCutPage=false";
				}else {
					alert("操作失败！");
				}
			}
		}); 
	  }
	else{
	}
}
function zhankai(obj){
	$obj = $(obj);
	$target  = $obj.parent().parent().parent().parent();
	if($target.hasClass("gr_nlb_xs")){
		$target.removeClass("gr_nlb_xs");
	}else{
		$target.addClass("gr_nlb_xs");
	}
}
</script>
<!--右侧开始-->
     <div class="cp_rt">
     	<div class="licn"><a onclick='window.location.href="${globalUrl}"'>首页</a>&nbsp;>&nbsp;个人中心&nbsp;>&nbsp;置换价格订阅</div>
         <div class="gr_xxi">您好，你目前订阅了<b>${pageBean.totalRows }</b>条价格更新信息，其中<b>${noticeCount }</b>条信息价格发生了变动。</div>
         	<form:form name="noticeForm" id="noticeForm" action="${globalUrl}usercenter.do?actionMethod=priceNotice&isCutPage=true">
         <div class="gr_ls">
         	<div class="gr_tit">
             	<ul>
                 	<li class="li_1">序号</li>
                 	<li class="li_2">名称</li>
                 	<li class="li_3">面积</li>
                 	<li class="li_4">订阅价格</li>
                 	<li class="li_5">当前价格</li>
                 	<li class="li_6">操作</li>
                 </ul>
             </div>
             <c:forEach var="house" items="${pageBean.dataList}" varStatus="status">
             <div class="gr_nlb">
             	<div class="gr_nrb">
                     <ul>
                         <li class="li_1"><b>1</b></li>
                         <li class="li_2">
					<c:if test="${house.houseType eq 1 }">
						<a href="${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=${house.houseSecond.houseNo}&brokerId=${house.houseSecond.broker.erpId}"><c:out value="${house.houseSecond.title }"></c:out></a>
					</c:if>
					<c:if test="${house.houseType eq 2 }">
						<a href="${globalUrl}rent.show?actionMethod=houseRentDetail&houseNo=${house.houseRent.hourseNo}&brokerId=${house.houseRent.broker.erpId}"><c:out value="${house.houseRent.title }"></c:out></a>
					</c:if>
				</li>
                         <li class="li_3"><span>面积:</span>
					<c:if test="${house.houseType eq 1 }">
						<fmt:formatNumber value="${house.houseSecond.area }" pattern="0"/>平米
					</c:if>
					<c:if test="${house.houseType eq 2 }">
						<fmt:formatNumber value="${house.houseRent.area }" pattern="0"/>平米
					</c:if>
				</li>
                         <li class="li_4"><span>订阅价格:</span>
					<c:if test="${house.houseType eq 1 }">
						<fmt:formatNumber value="${house.currentPrice/10000 }" pattern="0"/>万元
					</c:if>
					<c:if test="${house.houseType eq 2 }">
						<fmt:formatNumber value="${house.currentPrice }" pattern="0"/>元/月
					</c:if>
				</li>
                <li class="li_5"><span>当前价格:</span><b <c:if test="${house.priceFlag < 0 }">class="x"</c:if><c:if test="${house.priceFlag ge 0 }">class="s"</c:if> >
				<c:if test="${house.houseType eq 1 }">
					<fmt:formatNumber value="${house.houseSecond.price/10000 }" pattern="0"/>万元
				</c:if>
				<c:if test="${house.houseType eq 2 }">
					<fmt:formatNumber value="${house.houseRent.rentPrice }" pattern="0"/>元/月
				</c:if>
				</b></li>
                <li class="li_6"><a href="javascript:void(0);" class="jl" onclick="zhankai(this);">历史记录</a><a href="javascript:void(0);" class="dy" onclick="cancel('${house.erpId}');">取消订阅</a></li>
              </ul>
                 </div>
                 <div class="lsjl">
                 	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                       <tr>
                         <th scope="col">序号</th>
                         <th scope="col">价格</th>
                         <th scope="col">更新时间</th>
                       </tr>
                       <c:if test="${house.houseType eq 1 }">
                       <c:forEach var="historyList" items="${house.houseSecond.priceChangeHistory }">
                       <tr>
                         <td>${historyList.historyNo}</td>
                         <td><fmt:formatNumber value="${historyList.newPrice/10000 }" pattern="0"/>万</td>
                         <td><fmt:formatDate value="${historyList.updateTime }" type="date" />
                         </td>
                       </tr>
                       </c:forEach>
                       </c:if>
                       <c:if test="${house.houseType eq 2 }">
                       <c:forEach var="historyList" items="${house.houseRent.priceChangeHistory }">
                       <tr>
                         <td>${historyList.historyNo}</td>
                         <td><fmt:formatNumber value="${historyList.newPrice }" pattern="0"/>元/月</td>
                         <td><fmt:formatDate value="${historyList.updateTime }" type="date" /></td>
                       </tr>
                       </c:forEach>
                       </c:if>
                      </table>
                     <div class="jiao"></div>
					</div>
                 <div class="clear"></div>
             </div>
             </c:forEach>
         </div>
         <div class="page" style="display:block;">
	<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false" formName="noticeForm" offerPageSize="20,50,100" isExistForm="true" showAreal="usercenterDetailDiv"/>
</div>
</form:form>
</div>
<!--右侧结束-->
