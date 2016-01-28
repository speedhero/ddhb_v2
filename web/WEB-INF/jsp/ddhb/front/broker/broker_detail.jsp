<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.simple.phone.js"></script>
<script type="text/javascript" src="${globalUrl}js/search/myMap_new.js"></script>
<script type="text/javascript" src="${globalUrl}js/housesecond/housesecond.js"></script>
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.simple.js"></script>
<script type="text/javascript" src="${globalUrl}js/cookie/cookie.js"></script>
<script type="text/javascript" src="${globalUrl}js/compare/compare.js"></script>
<script type="text/javascript" src="${globalUrl}js/compare/history.js"></script>
<script type="text/javascript" src="${globalUrl}js/commonGround/chatTools.js"></script>
<script type="text/javascript" src="${globalUrl}js/zclip/jquery.zclip.js"></script>
<script type="text/javascript" src="${globalUrl}js/zclip/jquery.zclip.min.js"></script>

<!-- 分享按钮 -->
<script type="text/javascript" src="${globalUrl}js/share.js"></script>
<link type="text/css" rel="stylesheet" href="${globalUrl}css/searchPlugin/jquery.search.css"/>
<link type="text/css" rel="stylesheet" href="${globalUrl}css/compare/comparePanel.css">
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
/*	
	var searchMap = new Map();
	function initSearchMap(){
		searchMap.put("order", "Asc");
		searchMap.put("brokerId", brokerId);
		searchMap.put("housetype", housetype);
		searchMap.put("ispage", 1);
	}
*/

$(document).ready(function() {
	//initSearchMap();
	
	var str = $.parseJSON('${jsonString}');
	option={
		searchItems:str,
		url:'${globalUrl}broker.show?actionMethod=dimquery',
		searchMap:searchMap
	};
	var winWidth = 1024;
 	if (window.innerWidth){
 		 winWidth = window.innerWidth;
 	}else if ((document.body) && (document.body.clientWidth)){
 		winWidth = document.body.clientWidth;
 	}
	if (winWidth <= 758){
		$("#searchMenuDiv").createSearchForPhone(option);
	}else{
		$("#searchMenuDiv").createSearch(option);
	}
	var previousWidth = winWidth;
	$(window).resize(function(){
		var winWidth = 1024;
       	if (window.innerWidth){
       		winWidth = window.innerWidth;
       	}else if ((document.body) && (document.body.clientWidth)){
       		winWidth = document.body.clientWidth;
       	}
       	if (previousWidth < 758 && winWidth >= 758){
       		$("#searchMenuDiv").empty();
       		$("#searchMenuDiv").createSearch(option);
       	}
       	if (previousWidth >= 758 && winWidth < 758){
       		$("#searchMenuDiv").empty();
       		$("#searchMenuDiv").createSearchForPhone(option);
       	}
       	previousWidth = winWidth;
   	});
	
	createSearchArea(_conditionJson, '${globalUrl}broker.show?actionMethod=dimquery');
	
		$('#selectpaixu').change(function(){
			var tempstr = $(this).children('option:selected').val();
		  	if(tempstr == "priceHeight") {
		  		if(housetype == "1") {
			  		sort = "price";
			  		order = "Desc";
			  		searchMap.put("sort", "price");
			  		searchMap.put("order", "Desc");
		  		} else {
			  		sort = "rentPrice";
			  		order = "Desc";
			  		searchMap.put("sort", "rentPrice");
			  		searchMap.put("order", "Desc");
		  		}
		  	} else if(tempstr == "priceLow"){	
		  		if(housetype == "1") {
			  		sort = "price";
			  		order = "Asc";
			  		searchMap.put("sort", "price");
			  		searchMap.put("order", "Asc");
		  		} else {
			  		sort = "rentPrice";
			  		order = "Asc";
			  		searchMap.put("sort", "rentPrice");
			  		searchMap.put("order", "Asc");
		  		}
		  	} else if(tempstr == "areaHeight"){
		  		sort = "area";
		  		order = "Desc";
		  		searchMap.put("sort", "area");
		  		searchMap.put("order", "Desc");
		  	} else if(tempstr == "areaLow"){
		  		sort = "area";
		  		order = "Asc";
		  		searchMap.put("sort", "area");
		  		searchMap.put("order", "Asc");
		  	} else {
		  		sort = "sortIndex";
		  		order = "Asc";
		  		searchMap.put("sort", "sortIndex");
		  		searchMap.put("order", "Asc");
			}
		  	showSelectedField("${globalUrl}broker.show?actionMethod=dimquery");
		});
		<%--
		$("#historyDiv").click(function(){
			$("#compareContentContainer").css("display", "none");
			$("#historyListContainer").css("display", "block");
			$("#historyDiv").addClass("one");
			$("#compareMenu").removeClass("one");
		});
			
		$("#compareMenu").click(function(){
			$("#compareContentContainer").css("display", "block");
			$("#historyListContainer").css("display", "none");
			$("#historyDiv").removeClass("one");
			$("#compareMenu").addClass("one");
		});
		--%>
		if('${housetype}' == 1){
			var comparedItemInCookie = '${secondHouseCompare}';
			if (comparedItemInCookie == ""){
				comparedItemsArray=new Array();
			}else{
				comparedItemsArray = JSON.parse(comparedItemInCookie); 
			}
	        initComparedItems(comparedItemsArray, "secondHouseCompare");
	        
	     	var historyItemInCookie = '${secondHouseHistory}';
	    	if (historyItemInCookie == ''){
	    		historyItemsArray = new Array();
	    	}else{
	    		historyItemsArray = JSON.parse(historyItemInCookie);
	    	}
	    	initHistoryItems(historyItemsArray, "secondHouseHistory");  
		}
		else{
			var comparedItemInCookie = '${rentHouseCompare}';
			if (comparedItemInCookie == ''){
				comparedItemsArray=new Array();
			}else{
				comparedItemsArray = JSON.parse(comparedItemInCookie); 
			}
	        initComparedItems(comparedItemsArray, "rentHouseCompare");
	        
	    	var historyItemInCookie = '${rentHouseHistory}';
	    	if (historyItemInCookie == ''){
	    		historyItemsArray = new Array();
	    	}else{
	    		historyItemsArray = JSON.parse(historyItemInCookie);
	    	}
	    	
	    	initHistoryItems(historyItemsArray, "rentHouseHistory");
		}
	});

/*
function showSelectedField(){
   	var keyArray = searchMap.keys();
   	if (keyArray.length > 0){
   		var postUrl = "";
   		for (var i = 0; i < keyArray.length; i++){
   			postUrl += keyArray[i] + "=" + searchMap.get(keyArray[i]);
   			if (i < keyArray.length - 1){
   				postUrl += "&";
   			}
   		}
   	}
   	
   	$("select[selectId]").each(function(){
   		var columnName = $(this).attr("columnName");
   		var fieldValue = $(this).val();
   		if (fieldValue != -1){
   			if (postUrl === undefined){
   				postUrl = columnName + "=" + fieldValue;
   			}else{
   				postUrl += "&";
   				postUrl += columnName + "=" + fieldValue;
   			}
   		}
   	});
   	postDataByURL2(optionDefault.url, postUrl, "changelist");
   }
*/
</script>
<form name="houses">
<div class="jjr_main">
    <div class="Location"><a href="${globalUrl}welcome.show?actionMethod=welcome">首页</a>>杭州置业经纪人</div>
	<div class="jjr_banner" style="background:url(${globalUrl}image/${broker.backgroundImage}) left top no-repeat;">
	    <div class="jjr_name">${broker.bname}的店铺</div>
		<div class="jjr_link">
		    <p>
			   <img src="${globalUrl}image/jjr_share_ico.png" alt="" /><span style="float:left;">分享到：</span>
			   <a onclick="shareInformation('tqq')" ><img src="${globalUrl}image/jjr_wb_ico.png" style="margin-right:6px;" alt="" /></a>
			   <a onclick="shareInformation('tsina')"><img src="${globalUrl}image/jjr_sn_ico.png" alt="" /></a>
			   <input type="hidden" id="shopName" value="【${housetype eq 1 ? '二手房' : '租赁' }_${broker.bname}的网上店铺】学区房、特价房、免税房……根据您的需要，帮您轻松安家。豪世华邦，您身边的置业专家" />
			</p>
			<div style="clear:both;"></div>
			<span>
			   <input id="broderWebShop" type="text" value="${broderWebShop }" />
			   <!-- <a onclick="setCopyLink();">复制店铺地址</a> -->
			   <a href="#" id="copy-dynamic">复制店铺地址</a>
			</span>
		</div>
	</div>
	<div class="jjr_l">
	    <div class="brok_box">
		    <div class="rwtp">
			    <a href="#"><c:if test="${broker.photograph == ''}">
					<img src="${globalUrl}images/broker/head.jpg" alt="" />
				</c:if>
				<c:if test="${broker.photograph != ''}"></c:if><img src="${pictureHost}${broker.photograph}" alt="" /></a>
		    </div>
			<div class="rwtname">
			    <div class="name">${broker.bname}</div>
				<span><%-- 从业年限：5年以上--%></span>
				<span>近30天带看量：${broker.daikan}</span>
				<span>
				    <c:if test="${broker.qq != null && broker.qq != ''}">
					    <a href="tencent://message/?uin=${broker.qq }&Site=qq&Menu=yes" onclick="qqcao(${broker.qq })">
						   <img src="${globalUrl}image/qq.gif" title="和我交谈" alt="" />
					    </a>
				    </c:if>
				    <c:if test="${broker.weCharUrl != null && broker.weCharUrl != ''}">
				    <div id="BrokerWexin" style="display: none;width: 300px;"><img src="${pictureHost }${broker.weCharUrl}"/></div>
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
						<a onclick="window.open('${globalUrl}community.show?actionMethod=communityDetail&id=${community.community.erpId }');">
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
					<a onclick='getData("${globalUrl}broker.show?actionMethod=brokerHouse&brokerId=${broker.erpId}&housetype=${housetype}","","rightContent");'
						class="one">代出租房源</a>
				</c:when>
				<c:otherwise>
					<a onclick='getData("${globalUrl}broker.show?actionMethod=brokerHouse&brokerId=${broker.erpId}&housetype=${housetype}","","rightContent");'
						class="one">代售房源</a>
				</c:otherwise>
			</c:choose>
			<a onclick='getData("${globalUrl}broker.show?actionMethod=getBrokerAnswered&brokerid=${broker.erpId }","","rightContent");'
				class="wd">我的问答</a>
		</div>
		<div class="jjr_info">${broker.introduction }</div>
		
		<div id="rightContent">
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
							file="/WEB-INF/jsp/ddhb/front/rent/rent_list_broker.jsp"%></c:when>
					<c:otherwise><%@include
							file="/WEB-INF/jsp/ddhb/front/house/house_list_broker.jsp"%></c:otherwise>
				</c:choose>
				<div style="clear: both;"></div>
				<div class="page">
					<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false"
						formName="houses" offerPageSize="20,50,100" isExistForm="true"
						queryFunction="nonHouseChangePages('${globalUrl}broker.show?actionMethod=dimquery')" />
				</div>
			</div>
		</div>
		</div>
	</div>
</div>
</form>
<script type="text/javascript">
//点击文本框复制其内容到剪贴板上方法
$(document).ready(function(){
//$('a#copy-description').zclip({
//path:'${globalUrl}js/ZeroClipboard.swf',
//copy:$('#broderWebShop').attr("value")
//});
// The link with ID "copy-description" will copy
// the text of the paragraph with ID "description"
var broderWebShop = $('#broderWebShop').attr('value');
$('a#copy-dynamic').zclip({
path:'${globalUrl}js/zclip/ZeroClipboard.swf',
copy:function(){return $('#broderWebShop').attr('value');}
});
// The link with ID "copy-dynamic" will copy the current value
// of a dynamically changing input with the ID "dynamic"
});

</script>

<script type="text/javascript">
function nonHouseChangePages() {
	var brokerId = "${broker.erpId}";
	var housetype = "${housetype}";
	var currvalue = document.getElementsByName("currentPage")[0].value;
	setJSONValue("currentPage", currvalue);
	setJSONValue("brokerId",brokerId);
	setJSONValue("housetype",housetype);
	showSelectedField("${globalUrl}broker.show?actionMethod=dimquery");
}</script>
 