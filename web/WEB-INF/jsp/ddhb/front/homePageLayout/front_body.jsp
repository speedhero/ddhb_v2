<%@page import="com.huatek.hbwebsite.house.entity.HouseNew"%>
<%@page import="java.util.List"%>
<%@page import="com.huatek.hbwebsite.util.FrontSystemSettingUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.huatek.framework.sso.SSOServiceManagement"%>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/automate/jquery.automate.plugin.css" />

<!--[if lt IE 8]>
<script type="text/javascript" src="${globalUrl}js/responsive/respond.src.js"></script>
<![endif]-->
<style>
.addCompare{background-color:#cb4f4d !important;}
.adBar{height:86px; width:950px; margin-top:10px; overflow:hidden; margin-bottom: 10px;}
.adContainer{width:100%; height:86px;}
.imgClass{width:100%; height:100%; cursor: pointer;}
.navigationBar{margin:0 auto; height:25px; margin-top:10px;}
.navigationBar ul{list-style: none;font-size: 12px;line-height: 14px;}
.navigationBar ul li {list-style: none;padding: 0px;margin: 0px;float: left;width: 20px;height: 20px;cursor: pointer;padding: 0 5px;}
.navigationBar ul li a {width: 20px;height: 20px;display: block; cursor: pointer;}
.goLeftBut {background-image: url("${globalUrl}image/leftan_1.gif");background-repeat: no-repeat;background-position: center; cursor: pointer;}
.selectedaDian {background-image: url("${globalUrl}image/dian_b.gif");background-repeat: no-repeat;background-position: center;}
.unselectedaDian{background-image: url("${globalUrl}image/dian_a.gif");background-repeat: no-repeat;background-position: center;}
.toRightBut{background-image: url("${globalUrl}image/rightan_1.gif");background-repeat: no-repeat;background-position: center; cursor: pointer;}
.item111{float:left; width:310px; height:111px;}
.item111notfirst{margin-left: 10px;}
.item111ContPAD{width:752px; height: 90px;}
.item111ContPC{width:950px;}
.item111PAD{float:left;}
.item111ContPhone{width:1500px; height:83px;}
.item111PH{height:83px; float:left; width:230px;}
.item111PHnotfirst{height:83px; margin-left:5px;}
.item111ContPhone{display:none;}
#top111NaPhoneS{display:none;}
#top111NaPhoneB{display:none;}
.item{float:left; width:960px;}
.topAdBar{display:none;}
.bottomAdBar{display:block;}
#activityPic{padding-left:5px;}
#adBottomPC{height:250px;}
.classBottom, .adBottomItem{width:100%; height:250px; cursor: pointer;}
.classBottom{position: relative;}
.adBottomItem{position:absolute;}

@media screen and (max-width: 960px)and (min-width: 758px) {
	.item111{width:248px; height:90px;}
	.item111notfirst{margin-left: 4px;}
	.item111ContPC{display:block;}
	#top111NaPhoneS{display:none;}
	#top111NaPhoneB{display:none;}
	.item111ContPhone{display: none;}
	.item{ width:760px; height:200px;}
	#activityPic{padding-left:0px;}
}

@media screen and (max-width: 758px){
	#top111NaPhoneS{display:none;}
	#top111NaPhoneB{display:block;}
	.item111PH{width: 230px;height: 82px;}
	.item111ContPC{display:none;}
	.item111ContPhone{display: block;}
	.item{ width:230px; height:123px;}
	.topAdBar{display:none;}
	#activityPic{padding-left:0px;}
	.classBottom, .adBottomItem{height:123px;}
}

@media screen and (max-width: 470px) {
	#top111NaPhoneS{display:block;}
	#top111NaPhoneB{display:none;}
	.item111PH{width:310px;}
	.item111ContPC{display:none;}
	.item111ContPhone{display: block;}
	.item111ContPhone{width:950px; height:83px;}
	.item{ width:310px; height:81px;}
	.topAdBar{display:block;}
	#activityPic{padding-left:0px;}
	.classBottom, .adBottomItem{height:81px;}
}

</style>
<script type="text/javascript">
var currentAdLocation = 0;

//添加收藏
function keepOppen(searchno, priceCc, id, brokerId, houseType){
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=addtoCollection";
	var platCollection = {
			ObjectId: searchno,
			collectType: houseType,
			priceCc: priceCc,
			brokerId: brokerId
		};
	_keepOppen(actionUrl, platCollection, houseType, id);
}

//取消收藏
function keepOff(collectId ,id){
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=cancleMyCollection&collectionId=" + collectId;
	_keepOff(actionUrl, id, 0);
}

</script>
<script type="text/javascript" src="${globalUrl}js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${globalUrl}js/homepage.js"></script>
<script type="text/javascript" src="${globalUrl}js/automate/jquery.automate.plugin.js"></script>
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.homepage.js"></script>
<link type="text/css" rel="stylesheet" href="${globalUrl}css/searchPlugin/jquery.search.homepage.css"/>
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.homepage.phone.js"></script>
<link type="text/css" rel="stylesheet" href="${globalUrl}css/searchPlugin/jquery.search.homepage.phone.css"/>
<script type="text/javascript" src="${globalUrl}js/cookie/cookie.js"></script>
<script type="text/javascript" src="${globalUrl}js/compare/frontCompare.js"></script>
<script type="text/javascript" src="${globalUrl}js/compare/frontHistory.js"></script>
<script type="text/javascript" src="${globalUrl}js/detail/detail.js"></script>
<script type="text/javascript" src="${globalUrl}js/commonGround/box.js"></script>
<script type="text/javascript">
	var tag=true;
	var type = 1;
	var searchunselecticon=["homepage_53.png","homepage_55.png","homepage_57.png"];
	var searchselecticon=["homepage_54.png","homepage_56.png","homepage_58.png"];
	
	var navigatorunselecticon=["index1s.png","index2s.png","index3s.png","index4s.png"];
	var navigatorselecticon=["index1.png","index2.png","index3.png","index4.png"];
	var widthTmp=0;
	var comparedItemsArray;
	var comparedItemsArrayHouseSecond;
	var historyItemsArray;
	var comparedItemsArrayHouseRent;
	var historyItemsArray2;
	$(document).ready(function() {
		//房源推荐按钮切换
		$("#recommandedSecondSelect").click(function() {
			$("#recommandedRentSelect").removeAttr("style").css("cursor", "pointer");
			var $test=$("#ContentContainer1").find("div[class='slick-track']");
			$("#recommandedSecondSelect").addClass("one");
			$("#recommandedRentSelect").removeClass("one");
			$("#searchContentContainer11").css("display", "block");
			$("#searchContentContainer21").css("display", "none");
		});

		$("#recommandedRentSelect").click(function() {
			$("#recommandedSecondSelect").removeAttr("style").css("cursor", "pointer");
			$("#recommandedRentSelect").addClass("one");
			$("#recommandedSecondSelect").removeClass("one");
			$("#searchContentContainer11").css("display", "none");
			$("#searchContentContainer21").css("display", "block");
		});
		
		//以下这部分是旧主页，现在已不用
		var str = $.parseJSON('${jsonString}');
		option={
			searchItems:str
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
		
		$("#contractQuery").click(function(){
			var cardNo = $("#cardNo").val();
			var contractNo = $("#contractNo").val();
			var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
			var reg1 = /^([a-zA-Z0-9-]+)$/;
			if (cardNo.trim() == '请输入身份证号' || cardNo.trim() == ''){
				alert("身份证号不能为空");
				return;
			}else if(reg.test(cardNo) == false){
				alert("身份证号非法，请查证！");
				return;
			}
			if (contractNo.trim() == '' || contractNo.trim() == '请输入合同号'){
				alert("合同号不能为空");
				return;
			}else if(reg1.test(contractNo) == false){
				alert("合同号包含非法字符！");
				return;
			}
			var url="${globalUrl}contract.show?actionMethod=contractQuery&cardNo=" + cardNo + "&contractNo=" + contractNo;
			window.open(url);
		});
		
		//二手房对比初始化
		$("#historyDivHouseSecond").click(function(){
			$("#compareContentContainerHouseSecond").css("display", "none");
			$("#historyListContainerHouseSecond").css("display", "block");
			$("#historyDivHouseSecond").addClass("one");
			$("#compareMenuHouseSecond").removeClass("one");
		});
			
		$("#compareMenuHouseSecond").click(function(){
			$("#compareContentContainerHouseSecond").css("display", "block");
			$("#historyListContainerHouseSecond").css("display", "none");
			$("#historyDivHouseSecond").removeClass("one");
			$("#compareMenuHouseSecond").addClass("one");
		});
		 var comparedItemInCookie = '${secondHouseCompare}';
		if (comparedItemInCookie == ''){
			comparedItemsArrayHouseSecond=new Array();
		}else{
			comparedItemsArrayHouseSecond = JSON.parse(comparedItemInCookie); 
		}
        initComparedItems(comparedItemsArrayHouseSecond, "secondHouseCompare","HouseSecond");
        
     	var historyItemInCookie = '${secondHouseHistory}'
    	if (historyItemInCookie == ''){
    		historyItemsArray = new Array();
    	}else{
    		historyItemsArray = JSON.parse(historyItemInCookie);
    	}
    	initHistoryItems(historyItemsArray, "secondHouseHistory","HouseSecond"); 
    	
    	//租房对比初始化
    	$("#historyDivHouseRent").click(function(){
			$("#compareContentContainerHouseRent").css("display", "none");
			$("#historyListContainerHouseRent").css("display", "block");
			$("#historyDivHouseRent").addClass("one");
			$("#compareMenuHouseRent").removeClass("one");
		});
			
		$("#compareMenuHouseRent").click(function(){
			$("#compareContentContainerHouseRent").css("display", "block");
			$("#historyListContainerHouseRent").css("display", "none");
			$("#historyDivHouseRent").removeClass("one");
			$("#compareMenuHouseRent").addClass("one");
		});
		
		//delCookie("rentHouseCompare");
		var comparedItemInCookie2 = '${rentHouseCompare}';
		if (comparedItemInCookie2 == ''){
			comparedItemsArrayHouseRent=new Array();
		}else{
			comparedItemsArrayHouseRent = JSON.parse(comparedItemInCookie2); 
		}
        initComparedItems(comparedItemsArrayHouseRent, "rentHouseCompare","HouseRent");
        
        //delCookie("rentHouseHistory");
    	var historyItemInCookie2 = '${rentHouseHistory}';
    	if (historyItemInCookie2 == ''){
    		historyItemsArray2 = new Array();
    	}else{
    		historyItemsArray2 = JSON.parse(historyItemInCookie2);
    	}
    	initHistoryItems(historyItemsArray2, "rentHouseHistory","HouseRent");
    	
    	 var autoMaticOption = {
   			 pageCapacity:3
       	 };
         $("#shRecommand").startAutomate(autoMaticOption);  
         $("#rhRecommand").startAutomate(autoMaticOption);
         var newHouseOption = {
   			 pageCapacity:1
       	 };
         $("#newHouseRecommanded").startAutomate(newHouseOption);  
         //picCarousel();
         //页面宽度变化时重新计算轮播点
         var previousWidth = winWidth;
         $(window).resize(function(){
        	 picCarousel();
    	 });
         
     	 $("#top111NaPhoneB .adian").click(function(){
     		var currentDian = $("#top111NaPhoneB").find(".selectedaDian");
     	 	var currentDianPosition = $(currentDian).attr("position");
     	 	var clickedDianPosition = $(this).attr("position");
     	 	if (currentDianPosition == clickedDianPosition){
     	 		return;
     	 	}
     	 	var currentPosition = parseInt(currentDianPosition);
     	 	var clickedPosition = parseInt(clickedDianPosition);
     	 	if (currentPosition > clickedPosition){
     	 		left = "+=230px";
    			$(".item111ContPhone").animate({ "margin-left": left }, "slow" );
     	 	}else{
     	 		left = "-=230px";
    			$(".item111ContPhone").animate({ "margin-left": left }, "slow" );
     	 	}
     	 	$(currentDian).removeClass("selectedaDian");
			$(currentDian).addClass("unselectedaDian");
			$(this).removeClass("unselectedaDian");
			$(this).addClass("selectedaDian");
     	 });
     	 
     	$("#top111NaPhoneB .toRightBut").click(function(){
     		var currentDian = $("#top111NaPhoneB").find(".selectedaDian");
     		var currentPosition = parseInt($(currentDian).attr("position"));
     		if (currentPosition == 2){
     			return;
     		}
    	 	left = "-=230px";
   			$(".item111ContPhone").animate({ "margin-left": left }, "slow" );
			var shouldShowDian = $("#top111NaPhoneB").find(".unselectedaDian");
   			$(currentDian).removeClass("selectedaDian");
			$(currentDian).addClass("unselectedaDian");
			$(shouldShowDian).removeClass("unselectedaDian");
			$(shouldShowDian).addClass("selectedaDian");
     	});
     	
     	$("#top111NaPhoneB .goLeftBut").click(function(){
     		var currentDian = $("#top111NaPhoneB").find(".selectedaDian");
     		var currentPosition = parseInt($(currentDian).attr("position"));
     		if (currentPosition == 1){
     			return;
     		}
    	 	left = "+=230px";
   			$(".item111ContPhone").animate({ "margin-left": left }, "slow" );
			var shouldShowDian = $("#top111NaPhoneB").find(".unselectedaDian");
   			$(currentDian).removeClass("selectedaDian");
			$(currentDian).addClass("unselectedaDian");
			$(shouldShowDian).removeClass("unselectedaDian");
			$(shouldShowDian).addClass("selectedaDian");
     	});
     	
     	
     	$(".phoneBar .adian").click(function(){
     		var navigationObj = $(this).parent().parent();
     		var currentDian = $(navigationObj).find(".selectedaDian");
     	 	var currentDianPosition = $(currentDian).attr("position");
     	 	var clickedDianPosition = $(this).attr("position");
     	 	var currentPosition = parseInt(currentDianPosition);
     	 	var clickedPosition = parseInt(clickedDianPosition);
     	 	if (currentPosition == clickedPosition){
     	 		return;
     	 	}
     	 	var itemContainer = $(this).attr("relativeContainer");
     	 	var itemContainerObj = $("." + itemContainer);
     	 	var itemCount = $(itemContainerObj).find(".item").length;
     	 	var itemWidth = $(itemContainerObj).find(".item").first().next().css("width");
     	 	var itemWidthInt = parseInt(itemWidth.replace("px",""));
     	 	var marginLeft = $(itemContainerObj).find(".item").first().next().css("margin-left");
     	 	var marginLeftInt = parseInt(marginLeft.replace("px",""));
     	 	
     	 	var positionDistance = clickedPosition - currentPosition;
     	 	var left = 0;
     	 	if (positionDistance > 0){
     	 		left = positionDistance * (itemWidthInt + marginLeftInt);
     	 		var leftStr = "-=" + left + "px";
     	 		$(itemContainerObj).animate({ "margin-left": leftStr }, "slow" );
     	 	}else{
     	 		left = positionDistance * (itemWidthInt + marginLeftInt);
     	 		var leftStr = "+=" + (-1 * left) + "px";
     	 		$(itemContainerObj).animate({ "margin-left": leftStr }, "slow" );
     	 	}
     	 	$(currentDian).removeClass("selectedaDian");
			$(currentDian).addClass("unselectedaDian");
			$(this).removeClass("unselectedaDian");
			$(this).addClass("selectedaDian");
     	});
     	
     	$(".phoneBar .goLeftBut").click(function(){
     		toAutomate($(this), "left");
     	});
     	
		$(".phoneBar .toRightBut").click(function(){
			toAutomate($(this), "right");
     	});
		
		function toAutomate(obj, leftOrRight){
			var currentDian = $(obj).parent().parent().find(".selectedaDian");
     	 	var currentDianPosition = $(currentDian).attr("position");
     	 	var currentPosition = parseInt(currentDianPosition);
     	 	var theLastPosition = parseInt($(obj).parent().parent().find(".adian").last().attr("position"));
     	 	if (leftOrRight == "left"){
     	 		if (currentPosition == 1){
         	 		return;
         	 	}
     	 	}else if (leftOrRight == "right"){
     	 		if (currentPosition == theLastPosition){
         	 		return;
         	 	}
     	 	}else{
     	 		return;
     	 	}
     	 	var shouldSelected = "";
     	 	if (leftOrRight == "left"){
	     	 	shouldSelected = $(currentDian).parent().prev().children().first();
     	 	}else{
	     	 	shouldSelected = $(currentDian).parent().next().children().first();
     	 	}
     	 	var itemContainer = $(obj).attr("relativeContainer");
     	 	var itemContainerObj = $("." + itemContainer);
     	 	var itemCount = $(itemContainerObj).find(".item").length;
     	 	var itemWidth = $(itemContainerObj).find(".item").first().next().css("width");
     	 	var itemWidthInt = parseInt(itemWidth.replace("px",""));
     	 	var marginLeft = $(itemContainerObj).find(".item").first().next().css("margin-left");
     	 	var marginLeftInt = parseInt(marginLeft.replace("px",""));
     	 	
     	 	left = itemWidthInt + marginLeftInt;
     	 	var leftStr = "";
     	 	if (leftOrRight == "left"){
     	 		leftStr = "-=" + (-1 * left) + "px";
     	 	}else{
     	 		leftStr = "+=" + (-1 * left) + "px";
     	 	}
 	 		
 	 		$(itemContainerObj).animate({ "margin-left": leftStr }, "slow" );
 	 		$(currentDian).removeClass("selectedaDian");
			$(currentDian).addClass("unselectedaDian");
			$(shouldSelected).removeClass("unselectedaDian");
			$(shouldSelected).addClass("selectedaDian");
		}
		
		function resetCaserol(){
			$(".phoneBar .selectedaDian").each(function(){
           		//移动
           		var relativeContainer = $(this).attr("relativeContainer");
           		var relativeContainerObj = $("." + relativeContainer);
           		$(relativeContainerObj).css( "margin-left", "0px" );
           		
           		//点
           		var parentContainer = $(this).parent().parent().find(".adian[position='1']");
           		$(this).removeClass("selectedaDian").addClass("unselectedaDian");
           		$(parentContainer).removeClass("unselectedaDian").addClass("selectedaDian");
           });
			
			var obj = $("#top111NaPhoneB").find(".selectedaDian");
       		$(".item111ContPhone").css( "margin-left", "0px" );
       		
       		//点
       		var parentContainer = $(obj).parent().parent().find(".adian[position='1']");
       		$(obj).removeClass("selectedaDian").addClass("unselectedaDian");
       		$(parentContainer).removeClass("unselectedaDian").addClass("selectedaDian");
		}
         
         function picCarousel(){
	       	 var autoMaticOption = {
   				 pageCapacity:2
   			 };
	       	 var winWidth = 1024;
	       	 if (window.innerWidth){
	       		 winWidth = window.innerWidth;
	       	 }else if ((document.body) && (document.body.clientWidth)){
	       		winWidth = document.body.clientWidth;
	       	 }
        	 if(winWidth > 470 && winWidth <= 758 && (previousWidth < 470 || previousWidth > 758)){
   		           $("#shRecommand").startAutomate(autoMaticOption);  
   		           $("#rhRecommand").startAutomate(autoMaticOption);
   		           $("#newHouseRecommanded").startAutomate(newHouseOption);
   		        	resetCaserol();
       		 }else if(winWidth <= 470 && previousWidth > 470){
   				   autoMaticOption = {pageCapacity : 1};
   		           $("#shRecommand").startAutomate(autoMaticOption);  
   		           $("#rhRecommand").startAutomate(autoMaticOption);
   		           $("#newHouseRecommanded").startAutomate(newHouseOption); 
   		        	resetCaserol();
       		 }else if(winWidth > 758 && previousWidth <= 758){
       			autoMaticOption = {pageCapacity : 3};
       			$("#shRecommand").startAutomate(autoMaticOption);  
       			resetCaserol();
   			 }
        	 if (winWidth >= 758){
        		 $("#searchMenuDiv").empty();
        		 $("#searchMenuDiv").createSearch(option);
        	 }else{
        		 $("#searchMenuDiv").empty();
        		 $("#searchMenuDiv").createSearchForPhone(option);
        	 }
        	 previousWidth = winWidth;
         }
         
         $(".adBottomItem img").click(function() {
 			var adItemId = $(this).attr("adItemId");
 			if (typeof(adItemId) == "undifined"){
 				return;
 			}
 			$.ajax({
 				type : "POST",
 				url : "${globalUrl}adBrowsedCounter.show?actionMethod=counter&adItemID=" + adItemId,
 				success : function(data) {
 				},
 				error : function() {
 				}
 			});
 			var toUrl = $(this).attr("toUrl");
 			if (typeof(toUrl) == "undifined" || toUrl == ""){
 				return;
 			}
 			window.open(toUrl);
 		});
	});


function contractQuery(){
	var url="${globalUrl}contract.show?actionMethod=contractQuery";
	window.open(url);
}

function loginBox(target, housetype){
	var url = "${globalUrl}login.show?actionMethod=loginCheck&target="+target;
	_openDialog('login', "&nbsp;&nbsp;登录", url, "get", "html");
}
</script>

<div style="width: 100%; height: 100%;">
	<div class="t_jsuo">
		<div id="searchMenuDiv" style="width:100%; height:100%;"></div>
	</div>
	<div>
		<c:if test="${topADBar ne null }">
			<div class="three_pt sy_ad" id="activityPic">
			<c:if  test="${topADBar.items.size() > 0 }">
				<div class="adContainer item111ContPC">
	               <div class="item111"><img class="imgClass" <c:if test="${topADBar.items.get(0).itemType == 0 }">onclick="window.open('${topADBar.items.get(0).linkedUrl }');" toUrl="${topADBar.items.get(0).linkedUrl }"</c:if><c:if test="${topADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${topADBar.items.get(0).id}"</c:if> adItemId="${topADBar.items.get(0).id }" title="${topADBar.items.get(0).title }" src="${pictureHost }${topADBar.items.get(0).pictureIconPC}"/></div>
	               <div class="item111 item111notfirst"><img class="imgClass" <c:if test="${topADBar.items.get(1).itemType == 0 }">onclick="window.open('${topADBar.items.get(1).linkedUrl }');" toUrl="${topADBar.items.get(1).linkedUrl }"</c:if><c:if test="${topADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${topADBar.items.get(1).id}"</c:if> adItemId="${topADBar.items.get(1).id }" title="${topADBar.items.get(1).title }" src="${pictureHost }${topADBar.items.get(1).pictureIconPC}"/></div>
	               <div class="item111 item111notfirst"><img class="imgClass" <c:if test="${topADBar.items.get(2).itemType == 0 }">onclick="window.open('${topADBar.items.get(2).linkedUrl }');" toUrl="${topADBar.items.get(2).linkedUrl }"</c:if><c:if test="${topADBar.items.get(2).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${topADBar.items.get(2).id}"</c:if> adItemId="${topADBar.items.get(2).id }" title="${topADBar.items.get(2).title }" src="${pictureHost }${topADBar.items.get(2).pictureIconPC}"/></div>
	               <div style="clear:both;"></div>
	           </div>
	           <div style="clear:both;"></div>
			    <div class="adContainer item111ContPhone">
	               <div class="item111PH item"><img class="imgClass" <c:if test="${topADBar.items.get(0).itemType == 0 }">onclick="window.open('${topADBar.items.get(0).linkedUrl }');" toUrl="${topADBar.items.get(0).linkedUrl }"</c:if><c:if test="${topADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${topADBar.items.get(0).id}"</c:if> adItemId="${topADBar.items.get(0).id }" title="${topADBar.items.get(0).title }" src="${pictureHost }${topADBar.items.get(0).pictureIconPad}"/></div>
	               <div class="item111PH item item111PHnotfirst"><img class="imgClass" <c:if test="${topADBar.items.get(1).itemType == 0 }">onclick="window.open('${topADBar.items.get(1).linkedUrl }');" toUrl="${topADBar.items.get(1).linkedUrl }"</c:if><c:if test="${topADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${topADBar.items.get(1).id}"</c:if> adItemId="${topADBar.items.get(1).id }" title="${topADBar.items.get(1).title }" src="${pictureHost }${topADBar.items.get(1).pictureIconPad}"/></div>
	               <div class="item111PH item item111PHnotfirst"><img class="imgClass" <c:if test="${topADBar.items.get(2).itemType == 0 }">onclick="window.open('${topADBar.items.get(2).linkedUrl }');" toUrl="${topADBar.items.get(2).linkedUrl }"</c:if><c:if test="${topADBar.items.get(2).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${topADBar.items.get(2).id}"</c:if> adItemId="${topADBar.items.get(2).id }" title="${topADBar.items.get(2).title }" src="${pictureHost }${topADBar.items.get(2).pictureIconPad}"/></div>
	               <div style="clear:both;"></div>
	           </div>
			   <div style="clear:both;"></div>
	         </c:if> 
	           <div class="navigationBar" id="top111NaPhoneB" style="width:120px;">
	               <ul id="carouselTopA">
		               <li><a class="goLeftBut"></a></li>
		               <li><a class="adian selectedaDian" position="1"></a></li>
		               <li><a class="adian unselectedaDian" position="2"></a></li>
		               <li><a class="toRightBut"></a></li>
		               <div style="clear:both;"></div>
		           </ul>
	           </div>
	           <div class="navigationBar phoneBar topAdBar" id="top111NaPhoneA" style="width:150px;">
	               <ul  id="carouselTopB">
		               <li><a class="goLeftBut" relativeContainer="item111ContPhone"></a></li>
		               <li><a class="adian selectedaDian" position="1" relativeContainer="item111ContPhone"></a></li>
		               <li><a class="adian unselectedaDian" position="2" relativeContainer="item111ContPhone"></a></li>
		               <li><a class="adian unselectedaDian" position="3" relativeContainer="item111ContPhone"></a></li>
		               <li><a class="toRightBut" relativeContainer="item111ContPhone"></a></li>
		               <div style="clear:both;"></div>
		           </ul>
	           </div>
	           
	           <div style="clear:both;"></div>
			</div>
		</c:if>
			<div class="sy_gr">
				<div class="four_k">
					<a href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=1" class="a_1">我要买房1</a>
					<a href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=2" class="a_2">我要卖房2</a>
					<a href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=4" class="a_3">我要出租3</a>
					<a href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=3" class="a_4">我要租房4</a>
				</div>
				<div class="htcx">
					<p>合同交易进程查询：</p>
					<div class="ht_ipt">
						<b><input value="请输入身份证号" type="text" onblur="if(this.value.length==0){this.value='请输入身份证号'};" onfocus="if(this.value=='请输入身份证号'){this.value=''};" id="cardNo">
						<input  value="请输入合同号" type="text" onblur="if(this.value.length==0){this.value='请输入合同号'};" onfocus="if(this.value=='请输入合同号'){this.value=''};" id="contractNo"></b><a href="javascript:void(0);" id="contractQuery">查询</a>
					</div>
				</div>
			</div>
			<div style="clear:both;"></div>
	</div>
</div>

<div style="clear: both; height: 0px;"></div>
<div class="contentDiv">
	<div style="clear: both; height: 0px;"></div>
	<div class="con_k">
		<div class="tj_left">
		<div class="tj_tit"><a href="javascript:void(0);" id="recommandedSecondSelect" class="one">二手房源推荐</a><a id="recommandedRentSelect" >租房房源推荐</a></div>
			<div id="searchContentContainer11" class="headtabunselect2">
				<div class="tj_ls" id="shRecommand">
					<div class="block" style="width:10000px;">
						<c:forEach var="house" items="${secondList}" varStatus="item">
							<div class="item">
								<div class="pt" onclick="window.open('${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=${house.houseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}')" style="cursor:pointer;">
									<c:if test="${house.pictureUrl eq null}">
								    	<img src="${globalUrl}image/house_no_picture.png">
								    </c:if>
								    <c:if test="${house.pictureUrl ne null }">
									    <img src="${pictureHost }${house.pictureUrl }">
								    </c:if>
								</div>
								<div class="many"><p><fmt:formatNumber value="${house.price/10000}" pattern="##"/></p>万元</div>
								<div class="tj_txt" style="text-align: left;">
									<div class="jiant_s" onclick="moveClick(this);"><a href="javascript:void(0);"></a></div>
									<div class="pm"><span><fmt:formatNumber value="${house.unitPrice}" pattern="#" /></span>元/平米</div>
									<div class="name_a"><a>${(houseAppraise != null) ? houseAppraise.title : house.title}</a></div>
									<div class="tj_sx"><span><fmt:formatNumber pattern="#">${house.area}</fmt:formatNumber></span>平米&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${house.shi}</span>室<span>${house.ting}</span>厅<span>${house.wei}</span>卫<br>${house.community.communityName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${house.community.region.countyName } - ${house.community.cbd.parentCBD.name }<br><fmt:formatDate value="${house.community.startSaleDate}" type="date" pattern="yyyy"/>年建&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${house.broker.store.storeName}</div>
									<div class="color_wd">
										<c:forEach items="${tagList}" var="tags">
											<c:forEach items="${house.tagIdList}" var="tagId">
												<c:if test="${tags.erpId == tagId}">
													<span style="background: none repeat scroll 0 0 ${tags.tagColor}; color:${tags.fontColor};">${tags.tagName}</span>
												</c:if>
											</c:forEach>
										</c:forEach>
									</div>
									<div class="an_xq">
										<a href="${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=${house.houseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}" class="xq">查看详情</a>
										<c:if test="${LoginMember == null }">
											<a href="javascript:void(0);" collId="${house.collectId}" hosueobjectId="${house.erpId}" brokerid="${house.broker.erpId }"
											 houseprice="${house.price}" onclick="loginBox('collect');">收藏</a>
										</c:if>
										<c:if test="${LoginMember != null }">
											<c:if test="${house.collectId != null}">
												<a id="collect${house.erpId}a" href="javascript:void(0);" style="background-color:#cb4f4d" collId="${house.collectId}" hosueobjectId="${house.erpId}"
												    houseprice="${house.price}" isCollect="0" brokerid="${house.broker.erpId }"
												    onclick="isCollectHouseSend(this, '0');" >收藏</a>
											</c:if>
											<c:if test="${house.collectId == null}">
												<a id="collect${house.erpId}b" href="javascript:void(0);"  collId="${house.collectId}" hosueobjectId="${house.erpId}"
												    houseprice="${house.price}" isCollect="1" brokerid="${house.broker.erpId }"
												    onclick="isCollectHouseSend(this, '0');" >收藏</a>
											</c:if>
										</c:if>
										<a  href="javascript:void(0);" housePictureUrl="${pictureHost}${house.houseUrl }" inCompareItem="false" onclick="addCompareItem(this, 'secondHouseCompare',0)"
										   compareId="${house.houseNo }" brokerId="${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}" compareTitle="${(houseAppraise != null) ? houseAppraise.title : house.title}"
										   area="${house.area }"  shi="${house.shi }" ting="${house.ting }" comparePrice="${house.price}" compareUnitPrice="${house.unitPrice}" class="phoneCompare">对比</a>
									</div>
								</div>
							</div>
						</c:forEach>
						<div style="clear:both;"></div>
					</div>
				</div>
			</div>
			<div id="searchContentContainer21" class="headtabunselect2" style="display: none;">
				<div class="tj_ls" id="rhRecommand">
					<div class="block" style="width:10000px;">
						<c:forEach var="house" items="${rentList}" varStatus="item">
							<div class="item">
								<div class="pt" onclick="window.open('${globalUrl}rent.show?actionMethod=houseRentDetail&houseNo=${house.hourseNo}&brokerId=${house.broker.erpId}')" style="cursor:pointer;">
								    <c:if test="${house.pictureUrl eq null}">
								    	<img src="${globalUrl}image/house_no_picture.png">
								    </c:if>
								    <c:if test="${house.pictureUrl ne null }">
									   <img src="${pictureHost }${house.pictureUrl }">
								    </c:if>
								</div>
								<div class="many"><p><fmt:formatNumber value="${house.rentPrice}" pattern="#" /></p>元/月</div>
								<div class="tj_txt" style="text-align: left;">
									<div class="jiant_s" onclick="moveClick(this);"><a href="javascript:void(0);"></a></div>
									<div class="pm"><span>[${house.rentType.rentTypeName}]</span></div>
									<div class="name_a"><a>${(houseAppraise != null) ? houseAppraise.title : house.title}</a></div>
									<div class="tj_sx"><span><fmt:formatNumber pattern="#">${house.area}</fmt:formatNumber></span>平米&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${house.shi}</span>室<span>${house.ting}</span>厅<span>${house.wei}</span>卫<br>${house.community.communityName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${house.community.region.countyName } - ${house.community.cbd.parentCBD.name }<br><fmt:formatDate value="${house.community.startSaleDate}" type="date" pattern="yyyy"/>年建&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${house.broker.store.storeName}</div>
									<div class="color_wd">
										<c:forEach var="furs" items="${furList}">
											<c:forEach var="furId" items="${house.furIdList}">
												<c:if test="${furs.erpId == furId}">
													<img style="width:auto;height:auto" src="${pictureHost}${furs.imgUrl}" title="<c:out value="${furs.furName }"></c:out>">
												</c:if>
											</c:forEach>
										</c:forEach>
									</div>
									<div class="an_xq">
										<a href="${globalUrl}rent.show?actionMethod=houseRentDetail&houseNo=${house.hourseNo}&brokerId=${house.broker.erpId}" class="xq">查看详情</a>
											<c:if test="${LoginMember == null }">
												<a href="javascript:void(0);" collId="${house.collectId}" hosueobjectId="${house.erpId}" brokerid="${house.broker.erpId }"
												houseprice="${house.rentPrice}" onclick="loginBox('collect');">收藏</a>
											</c:if>
										<c:if test="${LoginMember != null }">
											<c:if test="${house.collectId != null}">
												<a id="collectRent${house.erpId}a" href="javascript:void(0);" style="background-color:#cb4f4d" collId="${house.collectId}" hosueobjectId="${house.erpId}"
												houseprice="${house.rentPrice}" isCollect="0" brokerid="${house.broker.erpId }"
												onclick="isCollectRent(this, '1');" >收藏</a>
											</c:if>
											<c:if test="${house.collectId == null}">
												<a id="collectRent${house.erpId}b" href="javascript:void(0);"  collId="${house.collectId}" hosueobjectId="${house.erpId}"
												houseprice="${house.rentPrice}" isCollect="1" brokerid="${house.broker.erpId }"
												onclick="isCollectRent(this, '1');" >收藏</a>
											</c:if>
										</c:if>
										<a href="javascript:void(0);" housePictureUrl="${pictureHost}${house.pictureUrl }" inCompareItem="false" class="compareButton phoneCompare" onclick="addCompareItem(this, 'rentHouseCompare',1)"
										compareId="${house.hourseNo }" brokerId="${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}" compareTitle="${(houseAppraise != null) ? houseAppraise.title : house.title}"
										area="${house.area }"  shi="${house.shi }" ting="${house.ting }" comparePrice="${house.rentPrice}">对比</a>
									</div>
								</div>
							</div>
						</c:forEach>
						<div style="clear:both;"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="tj_right">
			<div class="tj_tit"><a href="${globalUrl}houseNew.show?actionMethod=dimquery">新盘开售</a></div>
			<div class="kp_ls" id="newHouseRecommanded">
				<div class="block" style="width:5000px;">
					<huatek:homeNewHouseRecommanded newHouseList="${newHouseList }" pictureHost="${pictureHost }"/>
				</div>
			</div>
		</div>
	</div>
	<div style="clear: both; height: 0px;"></div>
</div>
<c:if test="${bottomADBar.items.size() > 0}">
	<div class="classBottom" style="margin-bottom: 20px;">
		<c:if test="${bottomADBar.items.size() > 0}">
			<c:forEach var="itemList" items="${bottomADBar.items}" varStatus="item">
	       		 <div class="adBottomItem">
	           		<img style="width:100%; height:100%;" <c:if test="${itemList.itemType == 0 }">toUrl="${itemList.linkedUrl }"</c:if><c:if test="${itemList.itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${itemList.id}"</c:if> adItemId="${itemList.id }" 
	           			title="${itemList.title }" src="${pictureHost }${itemList.pictureIconPhone}"/>
	           	</div>
	       	</c:forEach>
	     </c:if>
	</div>
</c:if>
<div class="duibi_w">
	<div id="compareDivHouseSecond" class="duibi" style="display:none;">
	 	<div id="menuDivHouseSecond" class="db_tit">
	 		<span>
	 			<a id="hiddenCompareDivHouseSecond" onclick="hiddenCompareDiv('HouseSecond')" href="javascript:void(0);">隐藏</a>
	 			<a id="openCompareDivHouseSecond" style="display:none;" onclick="openCompareDiv('HouseSecond')" href="javascript:void(0);">打开</a>
	 			<a id="closeCompareDivHouseSecond" onclick="closeCompareDiv('HouseSecond')" href="javascript:void(0);">关闭</a>
	 		</span>
	 			<a href="javascript:void(0)" id="compareMenuHouseSecond" class="one">房源对比</a>
	 			<a href="javascript:void(0);" id="historyDivHouseSecond">浏览记录</a>
	 	</div>
	 	<div id="compareContentContainerHouseSecond" class="db_ls">
	 		<ul id="compareContent">
            	<li id="compareItemHouseSecond1">
            		<div id="textContentHouseSecond1">
	                	<div class="db_tjs">1</div>
	                    <div class="db_tjswd">继续添加对比二手房</div>
                    </div>
                </li>
            	<li id="compareItemHouseSecond2">
            		<div id="textContentHouseSecond2">
	                	<div class="db_tjs">2</div>
	                    <div class="db_tjswd">继续添加对比二手房</div>
	                </div>
                </li>
            	<li id="compareItemHouseSecond3">
                	<div id="textContentHouseSecond3">
	                	<div class="db_tjs">3</div>
	                    <div class="db_tjswd">继续添加对比二手房</div>
	                </div>
                </li>
            	<li id="compareItemHouseSecond4">
                	<div id="textContentHouseSecond4">
	                	<div class="db_tjs">4</div>
	                    <div class="db_tjswd">继续添加对比二手房</div>
	                </div>
                </li>
            	<li class="nob"><a href="javascript:void(0);" onclick="startCompare('${globalUrl}houseSecond.show?actionMethod=houseSecondCompare')">开始对比</a><p><a href="javascript:void(0);" onclick="clearCompareItem('secondHouseCompare')">清空对比栏</a></p></li>
            </ul>
	 	</div>
	 	<div id="historyListContainerHouseSecond" class="db_ls" style="display:none;">
	 		<ul id="listContent">
            	<li id="historyItemHouseSecond1">
            		<div id="historyTextContentHouseSecond1">
	                	<div class="db_tjs">1</div>
	                    <div class="db_tjswd">二手房浏览历史</div>
                    </div>
                </li>
            	<li  id="historyItemHouseSecond2">
            		<div id="historyTextContentHouseSecond2">
	                	<div class="db_tjs">2</div>
	                    <div class="db_tjswd">二手房浏览历史</div>
	                </div>
                </li>
            	<li  id="historyItemHouseSecond3">
                	<div id="historyTextContentHouseSecond3">
	                	<div class="db_tjs">3</div>
	                    <div class="db_tjswd">二手房浏览历史</div>
	                </div>
                </li>
            	<li  id="historyItemHouseSecond4">
                	<div id="historyTextContentHouseSecond4">
	                	<div class="db_tjs">4</div>
	                    <div class="db_tjswd">二手房浏览历史</div>
	                </div>
                </li>
            	<li  class="nob">
            		<c:if test="${LoginMember == null }">
					<a href="javascript:void(0);" onclick="loginBox('browseHistory','S');">更多</a>
					</c:if>
					<c:if test="${LoginMember != null }">
					<a href='javascript:void(0);' onclick="window.open('${globalUrl}usercenter.do?actionMethod=browseHistory&isCutPage=false&houseType=0');">更多 </a>
					</c:if>
                </li>
            </ul>
	 	</div>
	 </div>
	 <div id="compareDivHouseRent" class="duibi" style="display:none;">
	 	<div id="menuDivHouseRent" class="db_tit">
	 		<span>
	 			<a id="hiddenCompareDivHouseRent" onclick="hiddenCompareDiv('HouseRent')" href="javascript:void(0);">隐藏</a>
	 			<a id="openCompareDivHouseRent" style="display:none;" onclick="openCompareDiv('HouseRent')" href="javascript:void(0);">打开</a>
	 			<a id="closeCompareDivHouseRent" onclick="closeCompareDiv('HouseRent')" href="javascript:void(0);">关闭</a>
	 		</span>
	 			<a href="javascript:void(0)" id="compareMenuHouseRent" class="one">房源对比</a>
	 			<a href="javascript:void(0);" id="historyDivHouseRent">浏览记录</a>
	 	</div>
	 	<div id="compareContentContainerHouseRent" class="db_ls">
	 		<ul id="compareContentHouseRent">
            	<li id="compareItemHouseRent1">
            		<div id="textContentHouseRent1">
	                	<div class="db_tjs">1</div>
	                    <div class="db_tjswd">继续添加对比租房房源</div>
                    </div>
                </li>
            	<li id="compareItemHouseRent2">
            		<div id="textContentHouseRent2">
	                	<div class="db_tjs">2</div>
	                    <div class="db_tjswd">继续添加对比租房房源</div>
	                </div>
                </li>
            	<li id="compareItemHouseRent3">
                	<div id="textContentHouseRent3">
	                	<div class="db_tjs">3</div>
	                    <div class="db_tjswd">继续添加对比租房房源</div>
	                </div>
                </li>
            	<li id="compareItemHouseRent4">
                	<div id="textContentHouseRent4">
	                	<div class="db_tjs">4</div>
	                    <div class="db_tjswd">继续添加对比租房房源</div>
	                </div>
                </li>
            	<li class="nob"><a href="javascript:void(0);" onclick="startCompare('${globalUrl}rent.show?actionMethod=houseRentCompare')">开始对比</a><p><a href="javascript:void(0);" onclick="clearCompareItem('rentHouseCompare')">清空对比栏</a></p></li>
            </ul>
	 	</div>
	 	<div id="historyListContainerHouseRent" class="db_ls" style="display:none;">
	 		<ul id="listContent">
            	<li id="historyItemHouseRent1">
            		<div id="historyTextContentHouseRent1">
	                	<div class="db_tjs">1</div>
	                    <div class="db_tjswd">租房房源浏览历史</div>
                    </div>
                </li>
            	<li  id="historyItemHouseRent2">
            		<div id="historyTextContentHouseRent2">
	                	<div class="db_tjs">2</div>
	                    <div class="db_tjswd">租房房源浏览历史</div>
	                </div>
                </li>
            	<li  id="historyItemHouseRent3">
                	<div id="historyTextContentHouseRent3">
	                	<div class="db_tjs">3</div>
	                    <div class="db_tjswd">租房房源浏览历史</div>
	                </div>
                </li>
            	<li  id="historyItemHouseRent4">
                	<div id="historyTextContentHouseRent4">
	                	<div class="db_tjs">4</div>
	                    <div class="db_tjswd">租房房源浏览历史</div>
	                </div>
                </li>
            	<li  class="nob">
            		<c:if test="${LoginMember == null }">
					<a href="javascript:void(0);" onclick="loginBox('browseHistory','R');">更多</a>
					</c:if>
					<c:if test="${LoginMember != null }">
					<a href='javascript:void(0);' onclick="window.open('${globalUrl}usercenter.do?actionMethod=browseHistory&isCutPage=false&houseType=1');">更多 </a>
					</c:if>
                </li>
            </ul>
	 	</div>
	</div>
	
	<div id="compareDiv" class="duibi" style="display:none;">
	 	<div id="menuDiv" class="db_tit">
	 		<span>
	 			<a id="hiddenCompareDiv" onclick="hiddenCompareDiv('')" href="javascript:void(0);">隐藏</a>
	 			<a id="openCompareDiv" style="display:none;" onclick="openHistory()" href="javascript:void(0);">打开</a>
	 			<a id="closeCompareDiv" onclick="closeCompareDiv('')" href="javascript:void(0);">关闭</a>
	 		</span>
 			<a href="javascript:void(0)" id="compareMenu" class="one">房源对比</a>
 			<a href="javascript:void(0);" id="historyDiv">浏览记录</a>
	 	</div>
	 	<div id="compareContentContainer" class="db_ls" style="display:none;">
	 		<ul id="compareContent">
            	<li id="compareItem1">
            		<div id="textContent1">
	                	<div class="db_tjs">1</div>
	                    <div class="db_tjswd">继续添加对比二手房</div>
                    </div>
                </li>
            	<li id="compareItem2">
            		<div id="textContent2">
	                	<div class="db_tjs">2</div>
	                    <div class="db_tjswd">继续添加对比二手房</div>
	                </div>
                </li>
            	<li id="compareItem3">
                	<div id="textContent3">
	                	<div class="db_tjs">3</div>
	                    <div class="db_tjswd">继续添加对比二手房</div>
	                </div>
                </li>
            	<li id="compareItem4">
                	<div id="textContent4">
	                	<div class="db_tjs">4</div>
	                    <div class="db_tjswd">继续添加对比二手房</div>
	                </div>
                </li>
            	<li class="nob"><a href="javascript:void(0);" onclick="startCompare('${globalUrl}houseSecond.show?actionMethod=houseSecondCompare')">开始对比</a><p><a href="javascript:void(0);" onclick="clearCompareItem('secondHouseCompare')">清空对比栏</a></p></li>
            </ul>
	 	</div>
	 	<div id="historyListContainer" class="db_ls" style="display:none;">
	 		<ul id="listContent">
            	<li id="historyItem1">
            		<div id="historyTextContent1">
	                	<div class="db_tjs">1</div>
	                    <div class="db_tjswd">二手房浏览历史</div>
                    </div>
                </li>
            	<li  id="historyItem2">
            		<div id="historyTextContent2">
	                	<div class="db_tjs">2</div>
	                    <div class="db_tjswd">二手房浏览历史</div>
	                </div>
                </li>
            	<li  id="historyItem3">
                	<div id="historyTextContent3">
	                	<div class="db_tjs">3</div>
	                    <div class="db_tjswd">二手房浏览历史</div>
	                </div>
                </li>
            	<li  id="historyItem4">
                	<div id="historyTextContent4">
	                	<div class="db_tjs">4</div>
	                    <div class="db_tjswd">二手房浏览历史</div>
	                </div>
                </li>
            	<li  class="nob">
            		<c:if test="${LoginMember == null }">
					<a href="javascript:void(0);" onclick="loginBox('browseHistory','S');">更多</a>
					</c:if>
					<c:if test="${LoginMember != null }">
					<a href='javascript:void(0);' onclick="window.open('${globalUrl}usercenter.do?actionMethod=browseHistory&isCutPage=false&houseType=0');">更多 </a>
					</c:if>
                </li>
            </ul>
	 	</div>
	 </div>
</div>

<script type="text/javascript">
function bottomAdBarChange(){
	var bottomAdBarItems = $(".adBottomItem");
	if ($(bottomAdBarItems).length <= 1){
		return;
	}
	var startZIndex = 20;
	for (var i = currentAdLocation; i < $(bottomAdBarItems).length; i++){
		$(bottomAdBarItems[i]).css("z-index", startZIndex);
		startZIndex -= 2;
		if (i != currentAdLocation){
			$(bottomAdBarItems[i]).fadeIn();
		}
	}
	
	for (var j = 0; j < currentAdLocation; j++){
		$(bottomAdBarItems[j]).css("z-index", startZIndex);
		startZIndex -= 2;
		if (j != currentAdLocation){
			$(bottomAdBarItems[j]).fadeIn();
		}
	}
	$(bottomAdBarItems[currentAdLocation]).fadeOut(2000);
	currentAdLocation = ((currentAdLocation + 1) % $(bottomAdBarItems).length);
}
$(document).ready(function(){
	setInterval(bottomAdBarChange,4000);
});
</script>