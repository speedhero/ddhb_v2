<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.simple.phone.js"></script>
<script type="text/javascript" src="${globalUrl}js/search/myMap_new.js"></script>
<script type="text/javascript" src="${globalUrl}js/housesecond/housesecond.js"></script>
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.simple.js"></script>
<%-- <script type="text/javascript" src="${globalUrl}js/cookie/cookie.js"></script>
<script type="text/javascript" src="${globalUrl}js/compare/compare.js"></script> 
<script type="text/javascript" src="${globalUrl}js/compare/history.js"></script> --%>
<script type="text/javascript" src="${globalUrl}js/commonGround/chatTools.js"></script>
<%-- <script type="text/javascript" src="${globalUrl}js/zclip/jquery.zclip.js"></script> --%>
<script type="text/javascript" src="${globalUrl}js/zclip/jquery.zclip.min.js"></script>

<!-- 分享按钮 -->
<script type="text/javascript" src="${globalUrl}js/share.js"></script>
<%-- <link type="text/css" rel="stylesheet" href="${globalUrl}css/searchPlugin/jquery.search.css"/> 
<link type="text/css" rel="stylesheet" href="${globalUrl}css/compare/comparePanel.css">--%>
<link type="text/css" rel="stylesheet" href="${globalUrl}css/jjr.css">
<title>经纪人- ${title } </title>
<style>
.searchFieldContentDiv { margin-left: 60px; text-align: center; }
</style>

<script type="text/javascript">
var comparedItemsArray;
var historyItemsArray;
var postUrl = "";
var isopen = false;
var brokerId = '${broker.erpId}';
var housetype = '${housetype}';

	//房源 问答切换
	function switchHouseA(){
		$("#answerA").removeClass("one");
		$("#houseA").addClass("one");
		$("#rightContent1").css("display","none");
		$("#rightContent2").css("display","block");
	}
	function switchAnswerA(){
		$("#houseA").removeClass("one");
		$("#answerA").addClass("one");
		$("#rightContent2").css("display","none");
		$("#rightContent1").css("display","block");
		getData("${globalUrl}broker.show?actionMethod=getBrokerAnswered&brokerid=${broker.erpId }","","rightContent1");
	}
</script>
<form name="houses">
<div class="jjr_main">
    <div class="Location"><a href="${globalUrl}">首页</a>>杭州置业经纪人</div>
	<div class="jjr_banner" style="background:url(${globalUrl}image/${broker.backgroundImage}) left top no-repeat;">
	    <div class="jjr_name">${broker.agentName}的店铺</div>
		<div class="jjr_link">
		    <p>
			   <img src="${globalUrl}image/jjr_share_ico.png" alt="" /><span style="float:left;">分享到：</span>
			   <a onclick="shareInformation('tqq')" ><img src="${globalUrl}image/jjr_wb_ico.png" style="margin-right:6px;" alt="" /></a>
			   <a onclick="shareInformation('tsina')"><img src="${globalUrl}image/jjr_sn_ico.png" alt="" /></a>
			   <input type="hidden" id="shopName" value="【${housetype eq 1 ? '二手房' : '租赁' }_${broker.agentName}的网上店铺】学区房、特价房、免税房……根据您的需要，帮您轻松安家。豪世华邦，您身边的置业专家" />
			</p>
			<div style="clear:both;"></div>
			<span>
			   <input id="broderWebShop" type="text" value="${broderWebShop }" />
			   <a href="#" id="copy-dynamic">复制店铺地址</a>
			</span>
		</div>
	</div>
	<div class="jjr_l">
	    <div class="brok_box">
		    <div class="rwtp">
			    <a href="#"><c:if test="${broker.photographPath == ''}">
					<img src="${globalUrl}images/broker/head.jpg" alt="" />
				</c:if>
				<c:if test="${broker.photographPath != ''}"></c:if><img src="${pictureHost}${broker.photographPath}" alt="" /></a>
		    </div>
			<div class="rwtname">
			    <div class="name">${broker.agentName}</div>
				<span><%-- 从业年限：5年以上 --%></span>
				<span><%-- 近30天带看量：${broker.daikan} --%></span>
				<span>
				    <c:if test="${broker.qq != null && broker.qq != ''}">
					    <a href="tencent://message/?uin=${broker.qq }&Site=qq&Menu=yes" onclick="qqcao(${broker.qq })">
						   <img src="${globalUrl}image/qq.gif" title="和我交谈" alt="" />
					    </a>
				    </c:if>
				    <c:if test="${broker.wechatUrl != null && broker.wechatUrl != ''}">
				    <div id="BrokerWexin" style="display: none;width: 300px;"><img src="${pictureHost }${broker.wechatUrl}"/></div>
					    <a href="#">
						   <img src="${globalUrl}image/wx.gif" onclick="_showWeixin('BrokerWexin','wchatDisplay','经纪人微信');" alt="" />
					    </a>
				    </c:if>
				</span>
			</div>
			<div style="clear:both;"></div>
			<div class="ihp">${broker.telephone}</div>
			<ul>
				<li>
					<p>所在门店：</p>
					<div>
						<div style="float: left;">店名：</div>
						<div style="float: left;">
							<c:out value="${broker.store.storeName }"></c:out>
						</div>
						<div style="clear: both;"></div>
					</div>
					<div>
						<div style="float: left;">地址：</div>
						<div style="float: left;" class="men_ads">
							<c:out value="${broker.store.storeAddress }"></c:out>
						</div>
						<div style="clear: both;"></div>
					</div>
					<div>
						<div style="float: left;">电话：</div>
						<div style="float: left;">
							<c:out value="${broker.store.telephoneNo }"></c:out>
						</div>
						<div style="clear: both;"></div>
					</div>
				</li>
				<li>
					<%-- 计数，熟悉板块+熟悉小区 <= 30 --%>
					<c:set var="i" value="0"></c:set>
					<p>熟悉板块：</p> <c:forEach items="${cbdExportList }" var="cbdExport">
							<span><c:out value="${cbdExport.cbd.cbdName }"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<c:set var="i" value="${i+1}" />						
					</c:forEach>
				</li>
				<li>
					<p>熟悉小区：</p> 
					<c:forEach items="${communityList }" var="community">
					<c:if test="${community.community ne null }">
						<c:if test="${i le 30 }">
						<a href="${globalUrl}xiaoqu/${community.community.erpId }.html" target="_blank">
						   <c:out value="${community.community.communityName }"></c:out>
						</a>
						<c:set var="i" value="${i+1 }" />
						</c:if>
					</c:if>
					</c:forEach>
					<div style="clear:both;"></div>
				</li>
			</ul>
		</div>
		<div class="ask_box">
		    <div class="ask_hed">我的问答 <div style="float:right;font-size:10px;"><a onclick="window.open('${globalUrl}/broker/questionAndAnswer')">更多>></a></div></div>
			<div class="ask_inf">回答：<b>${broderAnseredList.size()}</b>条&nbsp;&nbsp;｜&nbsp;&nbsp;被采纳：<b>${broderAnseredCount}</b>条</div>
			<c:forEach items="${broderAnseredList }" end = "1" var="broderAnsered">
			<p>问：${broderAnsered.questionStategy.title }</p>
			<span>答：${broderAnsered.answeredContent }</span>
			</c:forEach>
		</div>
		<div class="login_numb">
		    <p>店铺访问量</p>
			<span >
			<c:forEach items="${accessQuantityList}" var="accessQuantity">
			   <b>${accessQuantity }</b>
			   </c:forEach>
			   <div style="clear:both"></div>
			</span>
			
		</div>
	</div>
	<!--<div class="padzd">
		所在门店名称：
		<c:out value="${broker.store.storeName }"></c:out>
		&nbsp;&nbsp;&nbsp;&nbsp;地址：
		<c:out value="${broker.store.storeAddress }"></c:out>
		&nbsp;&nbsp;&nbsp;&nbsp;电话：
		<c:out value="${broker.store.telephoneNo }"></c:out>
	</div>-->
	<div class="jjr_r">
		<div class="jr_an">
			<c:choose>
				<c:when test="${housetype eq 2}">
					<a id="houseA" onclick="switchHouseA();" href="javascript:void(0);" class="one">代出租房源</a>
				</c:when>
				<c:otherwise>
					<a id="houseA" onclick="switchHouseA();" href="javascript:void(0);"class="one">代售房源</a>
				</c:otherwise>
			</c:choose>
			<a id="answerA" onclick="switchAnswerA();" onclick='getData("${globalUrl}broker.show?actionMethod=getBrokerAnswered&brokerid=${broker.erpId }","","rightContent");'
				class="wd">我的问答</a>
		</div>
		<div class="jjr_info">
		<%-- 有些经纪人的自我描述字符过长，导致文字超出div, 难看,实例：经纪人Id：f0e3418b-712e-4971-a208-2ce5b035432c --%>
		<c:choose>
			<c:when test="${fn:length(broker.introduction) > 210 }">
				<c:out value="${fn:substring(broker.introduction,0,210) }..."></c:out>
			</c:when>
			<c:otherwise>
				<c:out value="${broker.introduction }" />
			</c:otherwise>
		</c:choose>
		</div>
		
		<div id="rightContent1" style="display:none;">
		</div>
		<div id="rightContent2" style="display:block;">
				<div class="jjr_search_box">
					<div id="searchMenuDiv" ></div>
				</div>
		<div class="jjr_list">	
			<div class="jjr_list_head">
			    我的房源
			</div>
			<div id="changelist" class="jjr_list_con">
			    
				<%-- <c:if test="${pageBean.totalRows eq 0 }">
				<h3 style="color: red; margin-top: 20px; margin-left: 50px">很抱歉，没有查询到符合要求的房源！</h3>
			</c:if> --%>
				<c:choose>
					<c:when test="${housetype eq 2}"><%@include
							file="/WEB-INF/jsp/hshb/front/broker/rent_list_broker.jsp"%></c:when>
					<c:otherwise><%@include
							file="/WEB-INF/jsp/hshb/front/broker/house_list_broker.jsp"%></c:otherwise>
				</c:choose>
				<div style="clear: both;"></div>
				<c:if test="${!empty pageBean }">
				<div class="page">
					<c:import url="/WEB-INF/jsp/hshb/front/commonInfoShow/pager.jsp"/>
				</div>
				</c:if>
			</div>
		</div>
		</div>
	</div>
</div>
</form>
<script type="text/javascript">
//点击文本框复制其内容到剪贴板上方法
$(document).ready(function(){
var broderWebShop = $('#broderWebShop').attr('value');
$('a#copy-dynamic').zclip({
path:'${globalUrl}js/zclip/ZeroClipboard.swf',
copy:function(){return broderWebShop;}
});
});

</script>

 