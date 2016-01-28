<%@page import="com.huatek.hbwebsite.util.FrontSystemSettingUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/style.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/ystg.css"/>
<script type="text/javascript">
var globalUrl = '${globalUrl}';
$(document).ready(function(){
	$(".tongji").html("共有在售项目" +  '${houseCount }' + "个，售罄项目" + '${houseCountEnd }' + "个");
});
</script>
<input type="hidden" id="houseCountHidden" value="${houseCount}"/>
<input type="hidden" id="houseCountEndHidden" value="${houseCountEnd}"/>
<script type="text/javascript">
intDiffArray = new Array();
for(var i = 0; i < intervalArray.length; i++){
	clearInterval(intervalArray[i]);
}
intervalArray = new Array();
</script>
<c:forEach var="houseNew" items="${pageBean.dataList}" varStatus="i">
<script type="text/javascript">
intDiffArray.push(parseInt('${houseNew.remainDays.day}') * 24 * 60 * 60 + parseInt('${houseNew.remainDays.hour}') * 60 * 60 + parseInt('${houseNew.remainDays.min}') * 60  + parseInt('${houseNew.remainDays.sec}'));
timer(intDiffArray['${i.index}'], '${i.index}');
</script>
<div class="xiangm">
   	<div class="xm_pt">
   		<img src="${pictureHost}${houseNew.pictureUrl}" style="width: 465px; height: 260px;" alt=""/>
   		<!-- <div class="xmbh">项目编号：${houseNew.projectNo}</div> -->
   	</div>
       <div class="xm_wd">
       	<h2>${houseNew.buildingName }</h2>
       	<h3>${houseNew.adv }</h3>
       		<!-- 
           <p>已有<b>${houseNew.entrants }</b>人报名<br>剩余时间：
           <span class="remainsDay">${houseNew.remainDays.day }</span>天
           <span class="remainsHour">${houseNew.remainDays.hour }</span>小时
           <span class="remainsMinute">${houseNew.remainDays.min }</span>分
           <span class="remiansSecond">${houseNew.remainDays.sec }</span>秒
           <br>均价<span>${houseNew.averagePrice}</span>元/㎡起&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总价<span>${houseNew.minPrice }</span>万起</p>
			-->
        <p>距离报名截止还有：<span>${houseNew.remainDays.day}</span>天
	           <span>${houseNew.remainDays.hour }</span>小时
	           <span>${houseNew.remainDays.min }</span>分</p>
        <p>均价<span>${houseNew.averagePrice}</span>元/㎡起&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总价<span>${houseNew.minPrice }</span>万起</p>
       </div>
       <div class="clear"></div>
       <div class="fzxx">
       	<div class="wd">${houseNew.recommandContent}</div>
           <div class="tel">咨询电话：<p>${houseNew.hotline}</p></div>
       </div>
       <div class="clear"></div>
       <div class="qkk"><a onclick="window.open('${globalUrl}houseNew.show?actionMethod=showDetail&id=${houseNew.id}')">去看看</a></div>
   </div>
</c:forEach>
<c:if test="${pageBean.totalRows eq 0 }">	
	<h3 style="color: red; margin-top: 20px; margin-left: 50px">很抱歉，没有查询到符合要求的新房房源！</h3>
</c:if>
<div class="page">
<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false"
	formName="houseNew" offerPageSize="20,50,100" isExistForm="true"
	queryFunction="changePages()" />
</div>
<script type="text/javascript">
function changePages() {
	var currvalue = document.getElementsByName("currentPage")[0].value;
	setJSONValue("currentPage", currvalue);
	showSelectedField("${globalUrl}houseNew.show?actionMethod=dimquery");
}
</script>