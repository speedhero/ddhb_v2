<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.huatek.framework.sso.SSOServiceManagement"%>
<%@page import="com.huatek.hbwebsite.house.entity.HouseNew"%>
<%@page import="java.util.List"%>
<%@page import="com.huatek.hbwebsite.util.FrontSystemSettingUtil"%>

<%--出租房搜索基本URL --%>
<c:set var="searchBaseUrlForRent" value="${globalUrl}welcome.show?actionMethod=dimQuery&type=1&searchModule=2"></c:set>
<%--二手房搜索基本URL --%>
<c:set var="searchBaseUrlForSale" value="${globalUrl}welcome.show?actionMethod=dimQuery&type=1&searchModule=1"></c:set>   
<%--
<c:set var="searchBaseUrlForRent" value="${globalUrl}rent.show?actionMethod=dimquery&type=0&order=Asc&sort=sortIndex&ispage=1&tabIndex=0"></c:set>
二手房搜索基本URL 
<c:set var="searchBaseUrlForSale" value="${globalUrl}houseSecond.show?actionMethod=dimquery&ispage=1&tabIndex=0&type=1"></c:set>   
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0" /> 
<title>${title}</title>
<link rel="shortcut icon" type="image/x-icon" href="${globalUrl}images/hshb16.ico" media="screen" />
<link href="${globalUrl}css/homepage2/common.css" rel="stylesheet" type="text/css" />
<link href="${globalUrl}css/jquery.jqplot.min.css" rel="stylesheet" type="text/css" />
<link href="${globalUrl}css/select/jquery.select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">var globalUrl="${globalUrl}"; //全局根路径变量</script>
<script type="text/javascript" src="${globalUrl}js/jquery-1.8.3.js"></script>
<!-- jqplot -->
<!--[if IE]><script type="text/javascript" src="${globalUrl}js/jsCharts/excanvas.js"></script><![endif]-->
<script type="text/javascript" src="${globalUrl}js/jsCharts/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="${globalUrl}js/jsCharts/jqplot.highlighter.min.js"></script>
<script type="text/javascript" src="${globalUrl}js/jsCharts/jqplot.dateAxisRenderer.min.js"></script>
<script type="text/javascript" src="${globalUrl}js/select/jquery.selectReplace.js"></script>
<!-- 新增 -->
<script type="text/javascript" src="${globalUrl}js/jsCharts/jqplot.barRenderer.min.js"></script>
<script type="text/javascript" src="${globalUrl}js/jsCharts/jqplot.categoryAxisRenderer.min.js"></script>

<script type="text/javascript" src="${globalUrl}js/jquery.artDialog.source.js"></script>
<script type="text/javascript" src="${globalUrl}js/artDialog.iframeTools.source.js"></script>

<script type="text/javascript" src="${globalUrl}js/commonGround/box.js"></script>
<script type="text/javascript" src="${globalUrl}js/homepage.js"></script>
<script type="text/javascript" src="${globalUrl}js/hshb.common.js"></script>
<style type="text/css">
#priceChartTab {margin: 5px;}
#priceChartTab .selected {padding: 10px 30px; float: left; color: #FFFFFF; background-color: #555555; cursor: pointer;}
#priceChartTab .normal {padding: 10px 30px; float: left; color: #000000; background-color: #BBBBBB; cursor: pointer;}
</style>

</head>

<body>
<script type="text/javascript">
//价格走势图
//TODO 
$(document).ready(function(){
	//挂牌均价
	var medianListings = [
				<c:forEach var="salePrice" items="${medianListing}" varStatus="status">
				  [ "${salePrice[0]}".substring(0, 10), ${salePrice[1]}]<c:if test="${status.last == false}">,</c:if>
				</c:forEach>
	];
	//成交均价
	var averageTransationPrices = [
				<c:forEach var="salePrice" items="${averageTransationPrice}" varStatus="status">
				[ "${salePrice[0]}".substring(0, 10), ${salePrice[1]}]<c:if test="${status.last == false}">,</c:if>
				</c:forEach>
	  	                    ];
	//成交量
	var volumes =[
				<c:forEach var="salePrice" items="${volume}" varStatus="status">
				[ "${salePrice[0]}".substring(0, 10), ${salePrice[1]}]<c:if test="${status.last == false}">,</c:if>
				</c:forEach>  
	              ];
	  	if(medianListings.length>0 && averageTransationPrices.length>0 && volumes.length>0){
	  		var options = [
	  						//{data: dataForAxis1, label: "挂牌均价", color: "#00FF00"},
	  						//{data: dataForAxis2, label: "成交均价", color: "#FF0000"}
	  						
	  						{data: medianListings, label: "挂牌均价", color: "#A67D3D"},
	  						{data: volumes, label: "成交量", color: "#D3D3D3"},
	  						{data: averageTransationPrices, label: "成交均价", color: "#238E68"}
	  						
	  		            ];
	  		            //showDoublePriceChart(options, "rentPriceChart", "价格走势图");
	  		showHomePrice(options, "rentPriceChart", "价格走势图");
	  	}
});
</script>
<div id="header">
   <div class="head_con">
      <div class="logo">
       <a href="#"><img alt="" src="${globalUrl}images/homepage2/lm_logo.jpg" width="150" height="60" border="0" /></a>
    </div>
      <div class="nav">
       <ul>
        <li onClick="window.location.href='${globalUrl}welcome.show?actionMethod=welcome'"><a>首页</a></li>
        <li onClick="window.location.href='${globalUrl}houseSecond.show?actionMethod=dimquery&type='+type2"><a>二手房</a></li>
        <li onClick="window.location.href='${globalUrl}rent.show?actionMethod=dimquery&type='+type2" ><a>租赁</a></li>
        <li onClick="window.location.href='${globalUrl}houseNew.show?actionMethod=dimquery'"><a>新房</a></li>
        <li onClick="window.location.href='${globalUrl}community.show?actionMethod=dimquery&type='+type2"><a>小区</a></li>
        <li class="nav_a6"><a href="#">更多</a>
        <div class="nav_more">
           <a onClick="window.open('${globalUrl}company.show?actionMethod=showinvite')">加入我们</a>
           <a onClick="window.open('${globalUrl}company.show?actionMethod=showservice')">投诉建议</a>
           <a onClick="window.open('${globalUrl}broker.show?actionMethod=questionAndAnswer')">问答&amp;攻略</a>
           <a onClick="window.open('${globalUrl}contract.show?actionMethod=contractQuery')">交易查询</a>
           <a onClick="addFavour();">收藏本站</a>
        </div>
      </li>
      <%-- 登陆成功返回这张页面，LoginMember值仍为空，暂时未找到清空session的代码 --%>
     <li class="user_login" <c:if test="${LoginMember != null }"> style="display:none;" </c:if>>
     	<button id="user_log" class="bnt_user" onClick="loginBox();">登陆</button>
     	<button id="user_reg" class="bnt_user" onClick="registerBox();">注册</button>
     </li>
	   <li class="dl_zc_h"<c:if test="${LoginMember == null}"> style="display:none;" </c:if>>
			<a href="#" class="dl_name" style=" white-space: nowrap;overflow: hidden;text-overflow: ellipsis;"><c:out value="${LoginMember.accName }"></c:out></a>
	       <div class="dlh_wd">
	         <a href="${globalUrl}usercenter.do?actionMethod=appointment&isCutPage=false">预约管理</a>
	         <a href="${globalUrl}usercenter.do?actionMethod=contactQuery&isCutPage=false">合同进度查询</a>
	         <div class="bor"></div>
	         <a href="${globalUrl}usercenter.do?actionMethod=collectment&isCutPage=false">收藏夹管理</a>
	         <a href="${globalUrl}usercenter.do?actionMethod=priceNotice&isCutPage=false">置换价格订阅</a>
	         <a href="${globalUrl}usercenter.do?actionMethod=cutQuestion">问答管理</a>
	         <div class="bor"></div>
	         <a href="${globalUrl}usercenter.do?actionMethod=scoreManager&isCutPage=false">积分管理</a>
	         <a href="${globalUrl}usercenter.do?actionMethod=memberInformatinManage&isCutPage=false">个人信息管理</a>
	         <a onClick="if(confirm('确认退出吗？')){return true;}else{return false;}" href="${globalUrl}sso.show?actionMethod=logout">退出登录</a>
	       </div>
         </li>
     </ul>
    </div>
   </div>
</div>
<div id="search_main">
   <div class="ad_tit">
       <img alt="" src="${globalUrl}images/homepage2/tit_img.png" width="560" height="80" border="0" />
   </div>
   <div class="search_box">
       	<div class="menuBar">
	        <a id="menuId_1" idx="1" containerId="contentContainer_1" class="menuItem menuId_1 menuId_1selected menuSeledted">二手房</a>
	        <a id="menuId_2" idx="2" containerId="contentContainer_2" class="menuItem menuId_2">租赁</a>
	        <a id="menuId_3" idx="3" containerId="contentContainer_3" class="menuItem menuId_3">小区</a>
     	</div>
		<div style="clear:both;"></div>
		<div id="searchContainerDiv">
	   		<input type="text" id="inputSearch" class="search_key" />
	   		<button id="search_botton" class="search_bnt"></button>
	   		<input type="hidden" id="searchModule" name="searchModule" value="1"></input>
			<!-- http://www.hshb.cn/welcome.show?actionMethod=dimQuery&type=1&searchModule=1&inputSearch=米市巷 -->
	   	</div>
	   	  	<div class="searchLinkDiv">
	       	<div class="link_Key" idx="1" id="searchLinkDiv_1">热点：
	       	<c:forEach var="item" items="${saleHotCommunityList}" varStatus="status" end="5">
	       		<a href="${item.linkUrl}">${fn:replace(item.linkName, '二手房', '')}</a>
	       	</c:forEach>
	       	</div>
	       	<div class="link_Key" idx="2" id="searchLinkDiv_2" style="display: none;">热点：
	       	<c:forEach var="item" items="${rentHotCommunityList}" varStatus="status" end="5">
	       		<a href="${searchBaseUrlForRent}&ddhb_one_community.cbd.parentCBD.websiteId=${item.erpId}">${item.communityName}</a>
	       	</c:forEach>
	       	</div>
	       	<div class="link_Key" idx="3" id="searchLinkDiv_3" style="display: none;">热点：
	       	<c:forEach var="item" items="${saleHotCommunityList}" varStatus="status" end="5">
	       		<a href="${item.linkUrl}">${fn:replace(item.linkName, '二手房', '')}</a>
	       	</c:forEach>
	       	</div>
	     	<!-- <button id="search_map" class="search_map"></button> -->
	   	</div>
	</div>
</div>
<div id="Menu">
   <ul>
      <li><a class="m_1" href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=1">我要买房</a></li>
      <li><a class="m_2" href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=2">我要卖房</a></li>
      <li><a class="m_3" href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=4">我要出租</a></li>
      <li><a class="m_4" href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=3">我要租房</a></li>
      <li class="bus"><a class="m_5" href="${globalUrl}contract.show?actionMethod=contractQuery">交易查询</a></li>
  </ul>
</div>

<div id="main_body">
   <div id="price_News" class="m_d15">
       <div class="priceBox">
         <div class="tit_Div">
           <div class="tit">房价走势</div>
         <div class="rig_link"><a class="cal_bnt" href="javascript:void(0);" onClick="openCalcDialog();">贷款计算器</a></div>
       </div>
       <div id="rentPriceChart" typ="rent" class="price_con" style="width: 660px; height: 310px;">
       	  <!-- <img id="pricePreImage" alt="" src="${globalUrl}images/homepage2/price.jpg" width="660" height="310" border="0" />  -->
       </div>
       <%--
       	价格走势图位置，暂时先不用动态图
       <div id="priceChartTab">
       	<ul>
			<li class="selected" typ="sale">置换</li>
			<li class="normal" typ="rent">租赁</li>
		</ul>
       </div>
       	<div style="clear:both;"></div>
       <div id="salePriceChart" typ="sale" class="price_con" style="width: 660px; height: 280px;">
          <img id="pricePreImage" alt="" src="${globalUrl}images/homepage2/price_img.jpg" width="660" height="330" border="0" />
       </div>
       <div id="rentPriceChart" typ="rent" class="price_con" style="width: 660px; height: 280px; display: none;">
       	  <img id="pricePreImage" alt="" src="${globalUrl}images/homepage2/price_img.jpg" width="660" height="330" border="0" />
       </div>
        --%>
     </div>
     <div class="newsBox">
       <div class="tit_Div"><div class="tit">行业动态</div></div>
				<div class="news_con">
					<ul>
						<c:forEach var="news" items="${newPageBean.dataList}" varStatus="status">
							<li<c:if test="${status.count%2==0}"> class="sec"</c:if>>
								<a title="${news.newsTitle}" href="${globalUrl}company.show?actionMethod=showNewsDetail&news_id=${news.id}" target="_hydt">${fn:substring(news.newsTitle, 0, 23)}<c:if test="${fn:length(news.newsTitle)>24}">...</c:if></a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
   </div>
   <div id="ad_Box">
       <a href="${globalUrl}houseSecond.show?actionMethod=dimquery&type=1" target="_esf"><img alt="" src="${pictureHost}/activityManage/banner_sale.gif" width="1000" height="80" border="0" /></a>
   </div>
   <div id="house_Box" class="_secondhouse">
      <div class="tit_Div m_d15">
        <div class="tit" style="cursor:pointer;" onClick="window.open('${globalUrl}houseSecond.show?actionMethod=dimquery&type='+type2);">二手房</div>
        <div class="rig_link" id="_cityRange">
          <c:forEach var="item" items="${cityAreaList}" varStatus="status"><a href="${searchBaseUrlForSale}&ddhb_one_community.region.erpId=${item.erpId}">${item.countyName}</a></c:forEach>
          <a href="${searchBaseUrlForSale}"><b>更多&gt;&gt;</b></a>
        </div>
    </div>
    <div class="area_txt">
        <div class="area_link">
          <ul>
          	<li style="padding: 4px 0;">
           <c:set var="_tStr" value=""></c:set>
           <c:forEach var="item" items="${saleHotCommunityList}" varStatus="status">
           <c:set var="_linkName" value="${fn:replace(item.linkName, '二手房', '')}"></c:set>
           <c:set var="_tStr" value="${_tStr}${_linkName}"></c:set>
           <c:if test="${fn:length(_tStr)<=20}">
           <a href="${item.linkUrl}">${_linkName}</a><c:if test="${status.last==false}"><span>丨</span></c:if>
           </c:if>
           </c:forEach>
           </li>
           <li style="padding: 4px 0;"><a href="${searchBaseUrlForSale}&ddhb_one_subwayline.erpId=1">地铁1号线</a><span>丨</span><a href="${searchBaseUrlForSale}&ddhb_one_subwayline.erpId=2">地铁2号线</a></li>
           <li style="padding: 4px 0;"><a href="${searchBaseUrlForSale}&ddhb_two_price=50@100">50-100万</a><span>丨</span><a href="${searchBaseUrlForSale}&ddhb_two_price=100@150">100-150万</a><span>丨</span><a href="${searchBaseUrlForSale}&ddhb_two_price=200@300">200-300万</a><span>丨</span><a href="${searchBaseUrlForSale}&ddhb_two_price=200@2147483647">200万以上</a></li>
           <li style="padding: 4px 0;"><a href="${searchBaseUrlForSale}&ddhb_one_shi=1">一居室</a><span>丨</span><a href="${searchBaseUrlForSale}&ddhb_one_shi=2">二居室</a><span>丨</span><a href="${searchBaseUrlForSale}&ddhb_one_shi=3">三居室</a><span>丨</span><a href="${searchBaseUrlForSale}&ddhb_one_shi=4">四居室</a></li>
           <li style="padding: 4px 0;"><a href="${searchBaseUrlForSale}&ddhb_two_area=50@100">50-100平</a><span>丨</span><a href="${searchBaseUrlForSale}&ddhb_two_area=100@120">100-120平</a><span>丨</span><a href="${searchBaseUrlForSale}&ddhb_two_area=120@150">120-150平</a><span>丨</span><a href="${searchBaseUrlForSale}&ddhb_two_area=150@2147483647">150平以上</a></li>
        </ul>
      </div>
      <div class="house_txt_list">
          <div class="house_list_tit">置业顾问热推</div>
        <div class="house_list_con">
           <ul>
          <c:forEach var="house" items="${secondList}" varStatus="status" begin="6" end="12" >
          <li><a style="width: 145px;" href="${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=${house.houseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}">${house.community.region.countyName } - ${house.community.communityName }</a><span>${house.shi}室${house.ting}厅&nbsp;&nbsp;<fmt:formatNumber pattern="#">${house.area}</fmt:formatNumber>平米</span><span class="pri"><fmt:formatNumber value="${house.price/10000}" pattern="##"/>万</span></li>
          </c:forEach>
           </ul>
        </div>
      </div>
    </div>
    <div class="widow_list">
        <ul>
        <c:forEach var="house" items="${secondList}" varStatus="status" end="5" >
          <li>
           <div class="imgDiv">
              <a href="${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=${house.houseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}">
                <c:if test="${house.pictureUrl eq null}"><img src="${globalUrl}image/house_no_picture.png"></c:if>
              <c:if test="${house.pictureUrl ne null }"><img src="${pictureHost }${house.pictureUrl }"></c:if>
              </a>
              <div class="housArea">
               <c:set var="desc" value="${(houseAppraise != null) ? houseAppraise.title : house.title}"></c:set>
               <span class="fl" title="${desc}">${fn:substring(desc, 0, 10)}<c:if test="${fn:length(desc)>10}">...</c:if></span>
             <span class="fr"><fmt:formatNumber pattern="#">${house.area}</fmt:formatNumber>m<sup>2</sup></span>
            </div>
           </div>
           <div class="housPrice">
              <div class="house_tit" title="${desc}">${house.community.region.countyName } - ${house.community.communityName }</div>
            <div class="house_type">${house.shi}室${house.ting}厅${house.wei}卫</div>
            <div class="house_price"><fmt:formatNumber value="${house.price/10000}" pattern="##"/>万</div>
           </div>
        </li>
        </c:forEach>
      </ul>
    </div>
   </div>
   <div id="ad_Box">
    <a href="${globalUrl}rent.show?actionMethod=graduationSeason" target="_czf"><img alt="" src="${pictureHost}/activityManage/banner_rent_graduationSeason.jpg" width="1000" height="80" border="0" /></a>
   </div>
   <div id="house_Box" class="_renthouse">
      <div class="tit_Div m_d15">
        <div class="tit" style="cursor:pointer;" onClick="window.open('${globalUrl}rent.show?actionMethod=dimquery&type='+type2);">出租房</div>
        <div class="rig_link">
          <c:forEach var="item" items="${cityAreaList}" varStatus="status"><a href="${globalUrl}rent.show?actionMethod=dimquery&type=0&order=Asc&sort=sortIndex&ispage=0&tabIndex=0&ddhb_one_community.region.erpId=${item.erpId}">${item.countyName}</a></c:forEach>
          <a href="${globalUrl}rent.show?actionMethod=dimquery&type=0"><b>更多&gt;&gt;</b></a>
        </div>
    </div>
    <div class="area_txt">
        <div class="area_link">
          <ul>
             <li style="padding: 4px 0;">
              <c:set var="_tStr" value=""></c:set>
               <c:forEach var="item" items="${rentHotCommunityList}" varStatus="status">
               <c:set var="_tStr" value="${_tStr}${item.communityName}"></c:set>
               <c:if test="${fn:length(_tStr)<=20}">
               <a href="${searchBaseUrlForRent}&ddhb_one_community.cbd.parentCBD.websiteId=${erpId}">${item.communityName}</a><c:if test="${status.last==false}"><span>丨</span></c:if>
               </c:if>
               </c:forEach>
             </li>
           <li style="padding: 4px 0;"><a href="${searchBaseUrlForRent}&ddhb_one_subwayline.erpId=1">地铁1号线</a><span>丨</span><a href="${searchBaseUrlForRent}&ddhb_one_subwayline.erpId=2">地铁2号线</a></li>
           <li style="padding: 4px 0;"><a href="${searchBaseUrlForRent}&ddhb_one_shi=1">一居室</a><span>丨</span><a href="${searchBaseUrlForRent}&ddhb_one_shi=2">二居室</a><span>丨</span><a href="${searchBaseUrlForRent}&ddhb_one_shi=3">三居室</a><span>丨</span><a href="${searchBaseUrlForRent}&ddhb_one_shi=4">四居室</a></li>
           <li style="padding: 4px 0;"><a href="${searchBaseUrlForRent}&ddhb_two_rentPrice=0@1500">1500元以下</a><span>丨</span><a href="${searchBaseUrlForRent}&ddhb_two_rentPrice=1500@2000">1500-2000元</a><span>丨</span><a href="${searchBaseUrlForRent}&ddhb_two_rentPrice=2000@3000">2000-3000元</a><span>丨</span><a href="${searchBaseUrlForRent}&ddhb_two_rentPrice=3000@100000">3000元以上</a></li>
        </ul>
      </div>
      <div class="house_txt_list">
          <div class="house_list_tit">置业顾问热推</div>
        <div class="house_list_con">
           <ul>
          <c:forEach var="house" items="${rentList}" varStatus="status" begin="6" end="12" >
          <li><a style="width: 130px;" href="${globalUrl}rent.show?actionMethod=houseRentDetail&houseNo=${house.hourseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}">${house.community.region.countyName } - ${house.community.communityName }</a><span>${house.shi}室${house.ting}厅&nbsp;&nbsp;<fmt:formatNumber pattern="#">${house.area}</fmt:formatNumber>平米</span><span class="pri"><fmt:formatNumber value="${house.rentPrice}" pattern="##"/>元/月</span></li>
          </c:forEach>
           </ul>
        </div>
      </div>
    </div>
    <div class="widow_list">
        <ul>
        <c:forEach var="house" items="${rentList}" varStatus="status" end="5" >
          <li>
           <div class="imgDiv">
              <a href="${globalUrl}rent.show?actionMethod=houseRentDetail&houseNo=${house.hourseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}">
                <c:if test="${house.pictureUrl eq null}"><img src="${globalUrl}image/house_no_picture.png"></c:if>
              <c:if test="${house.pictureUrl ne null }"><img src="${pictureHost }${house.pictureUrl }"></c:if>
              </a>
              <div class="housArea">
                <c:set var="desc" value="${(houseAppraise != null) ? houseAppraise.title : house.title}"></c:set>
               <span class="fl" title="${desc}">${fn:substring(desc, 0, 10)}<c:if test="${fn:length(desc)>10}">...</c:if></span>
             <span class="fr"><fmt:formatNumber pattern="#">${house.area}</fmt:formatNumber>m<sup>2</sup></span>
            </div>
           </div>
           <div class="housPrice">
              <div class="house_tit" title="${desc}">${house.community.region.countyName } - ${house.community.communityName }</div>
            <div class="house_type" title="${house.shi}室${house.ting}厅${house.wei}卫">${house.shi}室${house.ting}厅</div>
            <div class="house_price"><fmt:formatNumber value="${house.rentPrice}" pattern="##"/>元/月</div>
           </div>
        </li>
        </c:forEach>
      </ul>
    </div>
   </div>
   <div id="ad_Box">
     <a href="${globalUrl}houseNew.show?actionMethod=dimquery" target="_xlp"><img alt="" src="${pictureHost}/activityManage/banner_new.jpg" width="1000" height="80" border="0" /></a>
   </div>
   <div id="house_New">
      <div class="tit_Div m_d15">
        <div class="tit" style="cursor:pointer;" onClick="window.open('${globalUrl}houseNew.show?actionMethod=dimquery');">热门楼盘</div>
        <div class="rig_link">
          <a href="${globalUrl}houseNew.show?actionMethod=dimquery"><b>更多&gt;&gt;</b></a>
        </div>
    </div>
        <div id="newHous">
            <ul>
            <c:forEach var="house" items="${newHouseList}" varStatus="status" end="3" >
               <li class="<c:if test="${status.first}">l_h</c:if><c:if test="${status.first ne true}">l</c:if>">
                  <a class="houName">${house.buildingName}</a>
                <a class="objName" title="${house.adv}">${fn:substring(house.adv, 0, 14)}<c:if test="${fn:length(house.adv)>14}">...</c:if></a>
               </li>
            </c:forEach>
            </ul>
            <div class="slide_r">
      		<c:forEach var="house" items="${newHouseList}" varStatus="status" end="3" >
               <div class="<c:if test="${status.first}">cont_l</c:if><c:if test="${status.first ne true}">hide</c:if>" id="sc_${status.count}">
            <div class="newhousImgDiv">
             <a href="${globalUrl}houseNew.show?actionMethod=showDetail&id=${house.id}"><img alt="" src="${pictureHost}${house.pictureUrl}" width="465" height="260" border="0" /></a>
           <div class="objNames" title="${house.adv}">${fn:substring(house.adv, 0, 25)}<c:if test="${fn:length(house.adv)>25}">...</c:if></div>
          </div>
          <div class="newhousInfoDiv">
           <div class="newhouseName">${house.buildingName}</div>
           <div class="newhouseInfo" style="padding: 5px 0 0 0;">${house.recommandContent}</div>
           <c:set var="remDays"><%= (int)(1+java.lang.Math.random()*(10-1+1))%></c:set>
           <div class="newhousePri" style="padding: 0 0 0 20px;">距离报名截止还有：<span>${house.remainDays.day}</span>天
	           <span>${house.remainDays.hour }</span>小时
	           <span>${house.remainDays.min }</span>分
	       </div>
	       <div class="newhousePri" style="padding: 0 0 10px 20px;">均价<span>${house.averagePrice}</span>元/m<sup>2</sup>起&nbsp;&nbsp;&nbsp;&nbsp;总价<span>${house.minPrice}</span>万起<br/>
           </div>
           <div class="newhouseAction">
              <a class="bnt_act fr" href="${globalUrl}houseNew.show?actionMethod=showDetail&id=${house.id}">我要报名</a>
           </div>
          </div>
              </div>
          </c:forEach>
            </div>
        </div>    
   </div>
   <!-- 
   <div id="ad_Box">
        <a href="#"><img alt="" src="${globalUrl}images/homepage2/ad_img01.jpg" width="1000" height="160" border="0" /></a>
   </div>
   -->
<%--底部广告开始 --%>
<c:if test="${bottomADBar.items.size() > 0}">
<style type="text/css">
<%-- 本插件可以自适应图片宽高 --%>
<c:set var="_imgW" value="1000px"></c:set>
<c:set var="_imgH" value="160px"></c:set>
/* 效果CSS开始 */
.mBan2 { overflow: hidden; }
.mBan2 .slideBox{ width:100%; height:auto; position:relative; }
.mBan2 .slideBox .hd{ height: 10px; overflow: hidden; position: absolute; bottom: 4px; z-index: 1; width: 81px; background: url(../images/btnBg.png) no-repeat; margin-left: -46px; left: 50%; padding: 4px 0px 0px 11px; }
.mBan2 .slideBox .hd ul{ zoom: 1; }
.mBan2 .slideBox .hd ul li{ float: left; width: 6px; height: 6px; background: url(../images/btn1.png) no-repeat; cursor: pointer; margin-right: 10px; }
.mBan2 .slideBox .hd ul li.on{ background: url(../images/btn2.png) no-repeat; }
.mBan2 .slideBox .bd{ position:relative; height:100%; z-index:0;   }
.mBan2 .slideBox .bd ul,.slideBox .bd ul li{width:100%!important}
.mBan2 .slideBox .bd img{ width:100%; height:auto; display:block;  }
/* 效果CSS结束 */
</style>
<div class="mBan2">
	<div id="slideBox" class="slideBox">
		<div class="hd">
			<ul><c:forEach var="item" items="${bottomADBar.items}" varStatus="status"><li></li></c:forEach></ul>
		</div>
		<div class="bd">
			<ul>
			<c:forEach var="item" items="${bottomADBar.items}" varStatus="status">
				<li>
				<c:if test="${item.itemType == 0 }">
					<a href="${item.linkedUrl}" target="_blank" />
				</c:if>
				<c:if test="${item.itemType == 1 }">
					<a href="${globalUrl}welcome.show?actionMethod=activityDetail&id=${item.id}" target="_blank" />
				</c:if>
				<img src="${pictureHost }${item.pictureIconPhone}" style="width:${_imgW}; height:${_imgH};"/></a></li>
			</c:forEach>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript" src="${globalUrl}js/jquery.SuperSlide.js"></script>

<script type="text/javascript">
jQuery(".slideBox").slide({mainCell:".bd ul",effect:"fold",autoPlay:true,trigger:"click"});
</script>
</c:if>
<%--底部广告结束 --%>
</div>

<div class="footerDiv">
<tiles:insertAttribute name="ddhb.footer" />
</div>

<script type="text/javascript">
/* 新楼盘页签切换脚本 */
var slideTabIndex=1; 
var sTabList = document.getElementById("house_New").getElementsByTagName("li"); 
for(var i=0;i<sTabList.length; i++){
  var obj = sTabList[i].getElementsByTagName("a")[0]; 
  sTabList[i].getElementsByTagName("a")[0].id="slideBtn_"+(i+1); 
  if (obj.addEventListener) {
    obj.addEventListener("mouseover", overSlide, false ); 
  } else if (obj.attachEvent) { 
    obj.attachEvent( "onmouseover", overSlide ); 
  }
}
function overSlide(e){ 
  var e = window.event || e; 
  var srcElement = e.srcElement || e.target; 
  var newTabIndex=srcElement.id.replace("slideBtn_","");
  var pos = srcElement.parentNode.className;
  if(newTabIndex==""||pos.indexOf("_h")!=-1) return; 
  document.getElementById("slideBtn_"+slideTabIndex).parentNode.className=document.getElementById("slideBtn_"+slideTabIndex).parentNode.className.replace("_h",""); 
  document.getElementById("sc_"+slideTabIndex).className="hide"; 
  document.getElementById("sc_"+newTabIndex).className="cont_"+pos; 
  srcElement.parentNode.className = pos+"_h"; 
  slideTabIndex=newTabIndex;
}
</script>
<script type="text/javascript">
/**
 * 初始化价格趋势图
 */
function initCharts(){
	function initSaleChart(){
	  //$("#pricePreImage").each(function(){ $(this).hide();});
	  var data = [
	<c:forEach var="salePrice" items="${listUnitPriceTrend}" varStatus="status">
	  [ "${salePrice[1]}", ${salePrice[0]}]<c:if test="${status.last == false}">,</c:if>
	</c:forEach>
	  ];
	  //initChart(data, "salePriceChart", "二手房挂牌均价");
	  showPriceChart(data, "rentPriceChart", "出租房挂牌均价", "#FF9F12");
	  $("#salePriceChart").show();
	}

	function initRentChart(){
	  //$("#pricePreImage").each(function(){ $(this).hide();});
	  var data = [
	<c:forEach var="rentPrice" items="${listRentPriceTrend}" varStatus="status">
	  [ "${rentPrice[1]}".substring(0, 10), ${rentPrice[0]}]<c:if test="${status.last == false}">,</c:if>
	</c:forEach>
	  ];
	  //initChart(data, "rentPriceChart", "出租房挂牌均价", "#FF9F12");
	  showPriceChart(data, "rentPriceChart", "出租房挂牌均价", "#FF9F12");
	}
	
	initSaleChart();
	initRentChart();
}
$(document).ready(function(){
	//暂时禁用动态图表
  //initCharts();
});
</script>
</body>
</html>

