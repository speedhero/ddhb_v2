<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
$(document).ready(function(){
	var dataForAxis1  = [<c:forEach var="trend" items="${trendList}" varStatus="s">
	  ["${fn:substring(trend.calculatedate,0,10)}", ${trend.priceAnnounced}]${s.last?'':','}
		</c:forEach>];
	var dataForAxis2 = [<c:forEach var="trend" items="${trendList}" varStatus="s">
	  ["${fn:substring(trend.calculatedate,0,10)}", ${trend.rentAveragePrice}]${s.last?'':','}
		</c:forEach>];

	if(dataForAxis1.length>0 && dataForAxis2.length>0){
		var options = [
	               {data: dataForAxis1, label: "二手房挂牌均价", color: "#00FF00"},
	               {data: dataForAxis2, label: "出租房挂牌均价", color: "#FF0000"}
	          	];
	          	
		showDoublePriceChart(options, "communityprice2", "近12个月房源价格走势");
	}
});

</script>
<div id="communityprice2" style="width: 100%; height: 280px;"></div>
<%--
<div id="communityprice22" class="jgzs" style="width: 240px; height: 140px;"></div>
<div class="jgzssj">
	<div style="float: left;">
		<div class="jtsj" style="background-color: #F55A5A;">
			本案本月均价
			<p>元/平米</p>
			<fmt:formatNumber value="${community.currentSHAvePrice}" />
		</div>
		<div class="jtsj" style="background-color: #DBDBDB; color: #000000;">
			本案上月均价
			<p>元/平米</p>
			<fmt:formatNumber value="${lastMonthPriceSecond}" />
		</div>
	</div>
	<div class="bfb">
		<span class="bfbwz">本案比上月<c:if test="${secondHousePercent ge 0}">增长</c:if>
			<c:if test="${secondHousePercent lt 0}">下降</c:if></span> <span
			class="bfbsz"
			style="color:<c:if test="${secondHousePercent ge 0}">#F55A5A</c:if><c:if test="${secondHousePercent lt 0}">#64AD70</c:if>">
			<c:if test="${secondHousePercent eq 0}">${secondHousePercent }%</c:if>
			<c:if test="${secondHousePercent gt 0}">${fn:substring(secondHousePercent, 1, 5 )}%</c:if>
			<c:if test="${secondHousePercent lt 0}">
				<c:out value="${fn:substring(secondHousePercent, 1, 5 )}"></c:out>%</c:if>
		</span>
	</div>
</div>
<div id="communityprice3" class="jgzs" style="width: 390px; height: 280px;"></div>
<div id="communityprice33" class="jgzs" style="width: 240px; height: 140px;"></div>
<div class="jgzssj">
	<div style="float: left;">
		<div class="jtsj" style="background-color: #F55A5A;">
			本案本月
			<p>元/月</p>
			<fmt:formatNumber value="${community.currentRHAvePrice}" />
		</div>
		<div class="jtsj" style="background-color: #DBDBDB; color: #000000;">
			本案上月
			<p>元/月</p>
			<fmt:formatNumber value="${lastMonthPriceRent}" />
		</div>
	</div>
	<div class="bfb">
		<span class="bfbwz">本案比上月<c:if test="${rentHousePercent ge 0}">增长</c:if>
			<c:if test="${rentHousePercent lt 0}">下降</c:if>
		</span>
			<span class="bfbsz" style="color:<c:if test="${rentHousePercent ge 0}">#F55A5A</c:if><c:if test="${rentHousePercent lt 0}">#64AD70</c:if>">
			<c:if test="${rentHousePercent eq 0}">${rentHousePercent }%</c:if>
			<c:if test="${rentHousePercent gt 0}">${fn:substring(rentHousePercent, 1, 5 )}%</c:if>
			<c:if test="${rentHousePercent lt 0}">
				<c:out value="${fn:substring(rentHousePercent, 1, 5 )}"></c:out>%</c:if>
		</span>
	</div>
</div>
--%>