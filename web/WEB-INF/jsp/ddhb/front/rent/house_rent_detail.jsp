<%@page import="org.apache.el.lang.ELSupport"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/style.css"/>
<link type="text/css" rel="stylesheet" href="${globalUrl}css/album.css" />
<link rel="stylesheet" type="text/css" href="${globalUrl}css/rentDetail.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/jquery.ad-gallery.css">

<script src="${globalUrl}js/jquery.ad-gallery.js"></script>
<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
<script src="http://map.qq.com/api/js?v=2.exp&key=Z3YBZ-JVFHV-6W2P2-U653S-DCY22-KVFDG"></script>
<!-- 弹出框js引用 -->
<script type="text/javascript" src="${globalUrl}js/jquery.artDialog.source.js"></script>
<script type="text/javascript" src="${globalUrl}js/artDialog.iframeTools.source.js"></script>
<link type="text/css" rel="stylesheet" href="${globalUrl}js/skins/gray.css">
<!-- 价格走势图 -->
<script type="text/javascript" src="${globalUrl}js/jsCharts/excanvas.js"></script>
<!--[if IE]><script type="text/javascript" src="${globalUrl}js/jsCharts/excanvas.js"></script><![endif]-->
<script type="text/javascript" src="${globalUrl}js/jsCharts/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="${globalUrl}js/jsCharts/jqplot.highlighter.min.js"></script>
<script type="text/javascript" src="${globalUrl}js/jsCharts/jqplot.dateAxisRenderer.min.js"></script>
<link type="text/css" rel="stylesheet" href="${globalUrl}css/jquery.jqplot.min.css" />

<script type="text/javascript" src="${globalUrl}js/album.js" ></script>
<script type="text/javascript" src="${globalUrl}js/detail/detail.js"></script>
<script type="text/javascript" src="${globalUrl}js/commonGround/chatTools.js"></script>
<script type="text/javascript" src="${globalUrl}js/commonGround/box.js"></script>
<script type="text/javascript" src="${globalUrl}js/commonGround/notice.js"></script>

<style>
.houseItem {border:1px solid #F3F3F3;}
.houseItem:hover{border:1px solid #FF9B4B;}
.addCompare{background-color:inherit !important;}
.grayBack{background-color: gray;}
.testQ{background-color: gray;}
.aui_nw_right {background-image: url("${globalUrl}gray/ddhb_bak.png"); height: 32px; margin-right: 0; width: 15px;}
.aui_nw_left {background-image: url("${globalUrl}gray/ddhb_bak.png"); height: 32px; margin-left: 0; width: 15px;}
.aui_content { border: 0 none; text-align: left;}
.aui_w, .aui_e { background-image: none;}
.aui_close {  background-image: url("${globalUrl}js/skins/gray/closeDialog.png"); background-position: 0 -96px; height: 16px;  text-indent: -9999em; top: 10px; width: 15px;}
.fontColorWhite{color:#ffffff;}
.fontColorGray{color:#666666;}
.ac{float:left; width:174px;height:50px;text-align: center; line-height: 38px;font-size:18px; margin-top:-10px; position: relative;}
.ac .ac_t{width:100%; height:38px; cursor: pointer;}
.ac .a_jiao{height:10px;}
.ac .a_jiao img{height:10px; width:20px; visibility: hidden;}
.frontpic{background: #E1E1E1 url(${globalUrl}image/zp_a.gif) no-repeat 34px 11px;border-left: 1px solid rgb(160, 160, 160);border-bottom: 1px solid rgb(160, 160, 160);}
.frontmap{background: #E1E1E1 url(${globalUrl}image/dt_a.gif) no-repeat 34px 11px;border-bottom: 1px solid rgb(160, 160, 160);border-left: 1px solid rgb(160, 160, 160); border-right: 1px solid rgb(160, 160, 160);}
.frontpicSelected{background: #FFFFFF url(${globalUrl}image/zp_b_zl.gif) no-repeat 34px 11px;color: #FF9B4B;}
.frontmapSelected{background: #FFFFFF url(${globalUrl}image/dt_b_zl.gif) no-repeat 34px 11px;color: #FF9B4B;}
.frontpicHover{background: #FFFFFF url(${globalUrl}image/zp_b_zl.gif) no-repeat 34px 11px;color: #FF9B4B;}
.frontmapHover{background: #FFFFFF url(${globalUrl}image/dt_b_zl.gif) no-repeat 34px 11px;color: #FF9B4B;}
.frontPicImgClass{display:inline;}
.frontMapImgClass{display:inline;}
.frontPicImgHoverClass{display:inline;}
.frontMapImgHoverClass{display:inline;}
#phoneCompare{display:none;}
#PcPadCompare{display:block;}
@media screen and (max-width: 758px){
	#nvBar{position: relative;width:500px;float:left;height:28px;}
	#barbar{width:370px; float:left;}
	.ac{width: 234px;}
}
@media screen and (max-width: 470px) {
	.compareButton{display:none;}
	#nvBar{position: relative;width:155px;overflow:hidden;float:left;height:28px;}
	#barbar{width:280px; float:left;}.compareButton{display:none;}
	.ac{width: 154px;}
	#phoneCompare{display:block;}
	#PcPadCompare{display:none;}
}
/* .tit_a a.jtr,.tit_a a.jtr:hover{background: url(${globalUrl}image/tit_jt_zl_right.gif) no-repeat; } */
.myjt{width:28px;float:left;height:28px;background: url('${globalUrl}image/tit_jt_zl.gif') no-repeat; display:block;margin-top:0;padding:0; cursor:pointer;}
.myjtr{width:28px;float:left;height:28px;background: url('${globalUrl}image/tit_jt_zl_right.gif') no-repeat; display:block;margin-top:0;padding:0; cursor:pointer;}
.db_tit{background: rgb(255, 155, 75);}
.duibi{border-color: rgb(255, 155, 75);}
.db_tit a.one, .db_tit a:hover{border-top-color: rgb(255, 155, 75);}
.db_ls li.nob p a{color:rgb(255, 155, 75);}
.db_tit span a:hover{background: rgb(255, 155, 75);}
.xs_k .dbfx a:hover{color:rgb(255, 155, 75);}
.nr_tit .gz a:hover {color: rgb(255, 155, 75);
}
</style>
<script type="text/javascript">
function _initCharts(size){
	var arrayz = [<c:forEach var="trend" items="${trendList}" varStatus="s">
	    ["${trend.dateAndMonth}".substring(0,10), ${trend.price}]${s.last?'':','}
      	</c:forEach>]
	initCharts(size, arrayz);
}

var historyItemsArray;
var comparedItemsArray;
$(document).ready(function() {
	try{
		var isIphoneSafari = navigator.userAgent.indexOf("Safari") > -1 && navigator.userAgent.indexOf("Chrome") < 0 && navigator.userAgent.indexOf("iPhone") > -1;
		$(this).addClass("frontpicHover");
		$("#frontPicImg").css("visibility", "visible"); 
		if(!isIphoneSafari) {
			$("#frontpic").mouseenter(function(){
				 $(this).addClass("frontpicHover");
				$("#frontPicImg").css("visibility", "visible"); 
			});
			$("#frontpic").mouseleave(function(){
				$(this).removeClass("frontpicHover");
				if($("#frontPicImg").attr("ischoosed") != "true") {
					$("#frontPicImg").css("visibility", "hidden");
				}
			});
			$("#frontmap").mouseenter(function(){
				$(this).addClass("frontmapHover");
				$("#frontMapImg").css("visibility", "visible"); 
			});
			$("#frontmap").mouseleave(function(){
				$(this).removeClass("frontmapHover");
				if($("#frontMapImg").attr("ischoosed") != "true") {
					$("#frontMapImg").css("visibility", "hidden"); 
				}
			});
		}
	}catch(e){
		if(window.console) console.log(e);
	}

	try{
		$("#frontpic").click(function(){
			$("#frontPicImg").attr("ischoosed", "true");
			$("#frontMapImg").attr("ischoosed", "false");
			$("#frontpic").addClass("frontpicSelected");
			$("#frontmap").removeClass("frontmapSelected");
			$("#frontPicImg").css("visibility", "hidden");
			$("#frontMapImg").css("visibility", "hidden"); 
			$("#frontPicImg").css("visibility", "visible");
		});
	}catch(e){
		if(window.console) console.log(e);
	}

	try{
		$("#frontmap").click(function(){
			$("#frontPicImg").attr("ischoosed", "false");
			$("#frontMapImg").attr("ischoosed", "true");
			$("#frontmap").addClass("frontmapSelected");
			$("#frontpic").removeClass("frontpicSelected");
			$("#frontMapImg").css("visibility", "visible"); 
			$("#frontPicImg").css("visibility", "hidden");
		});
	}catch(e){
		if(window.console) console.log(e);
	}

	try{
		var comparedItemInCookie = '${rentHouseCompare}';
		if (comparedItemInCookie == ''){
			comparedItemsArray=new Array();
		}else{
			comparedItemsArray = JSON.parse(comparedItemInCookie); 
		}
		initComparedItems(comparedItemsArray, "rentHouseCompare");
	}catch(e){
		if(window.console) console.log(e);
	}

	try{
		var historyObj = $("#compareContainerContainer").first();
		var historyItemInCookie = '${rentHouseHistory}';
		if (historyItemInCookie == ''){
			historyItemsArray = new Array();
		}else{
			historyItemsArray = JSON.parse(historyItemInCookie);
		} 
	 	var currentPageUrl = window.location.href;
		var urlArray = currentPageUrl.split("?");
		var relativeUrl = currentPageUrl.substring(urlArray[0].lastIndexOf("/") + 1, currentPageUrl.length);
		addHistoryItem(historyObj, "rentHouseHistory", relativeUrl);
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
		compareIsOpen();
--%>
	}catch(e){
		if(window.console) console.log(e);
	}

	try{
		//走势图
		var size = ${trendList.size()};
		if(size == 0){
			$('#priceChart').text("暂时没有历史走势图!");
			$('#priceChart3').text("暂时没有历史走势图!");
		} else {
			_initCharts(size);
		} 
		
		if($(document).width() <= 470) {
		   $("#priceChart").css("display", "none");
		   $("#priceChart3").css("display", "block");
		   $(".myjt").css("display", "block");
		   $(".myjtr").css("display", "block");
	  } else {
		   $("#priceChart").css("display", "block");
		   $("#priceChart3").css("display", "none");
		   $(".myjt").css("display", "none");
		   $(".myjtr").css("display", "none");
	  }
	}catch(e){
		if(window.console) console.log(e);
	}
	
	try{
		$(window).resize(function() {
		  if($(document).width() <= 470) {
			   $("#priceChart").css("display", "none");
			   $("#priceChart3").css("display", "block");
			   $(".myjt").css("display", "block");
			   $(".myjtr").css("display", "block");
		   } else {
			   $("#priceChart").css("display", "block");
			   $("#priceChart3").css("display", "none");
			   $(".myjt").css("display", "none");
			   $(".myjtr").css("display", "none");
		   }
		  if($("#photoAlbumHead").css("display") == "block") {
				 resizeAlertImage("photoAlbumHead");
	      }
		  if($("#photoAlbum").css("display") == "block") {
				 resizeAlertImage("photoAlbum");
		  }
		  if($("#photoComm").css("display") == "block") {
				 resizeAlertImage("photoComm");
		  }
	  });
	}catch(e){
		if(window.console) console.log(e);
	}

	try{
		/**相似查询**/
		var idStr = $.cookie("lastSelected");
		if(idStr == "imgShape") {
			$("#sshuxing").attr("onclick", 'window.open("${globalUrl}rent.show?actionMethod=dimquery&type=0&ispage=0&ddhb_one_shi=${house.shi}")');
			$("#sslouceng").attr("onclick", 'window.open("${globalUrl}rent.show?actionMethod=dimquery&type=0&ispage=0&ddhb_two_storyCount=${house.storyCount - 2}@${house.storyCount + 2}&ddhb_one_vervicalLocation=${house.vervicalLocation}")');
			$("#sschaoxiang").attr("onclick", 'window.open("${globalUrl}rent.show?actionMethod=dimquery&type=0&ispage=0&ddhb_one_orientations.id=${house.orientations.id}")');
			$("#ssxiaoqu").attr("onclick", 'window.open("${globalUrl}rent.show?actionMethod=dimquery&type=0&ispage=0&ddhb_one_community.erpId=${house.community.erpId}")');
		} else {
			$("#sshuxing").attr("onclick", 'window.open("${globalUrl}rent.show?actionMethod=dimquery&type=1&ispage=0&ddhb_one_shi=${house.shi}")');
			$("#sslouceng").attr("onclick", 'window.open("${globalUrl}rent.show?actionMethod=dimquery&type=1&ispage=0&ddhb_two_storyCount=${house.storyCount - 2}@${house.storyCount + 2}&ddhb_one_vervicalLocation=${house.vervicalLocation}")');
			$("#sschaoxiang").attr("onclick", 'window.open("${globalUrl}rent.show?actionMethod=dimquery&type=1&ispage=0&ddhb_one_orientations.id=${house.orientations.id}")');
			$("#ssxiaoqu").attr("onclick", 'window.open("${globalUrl}rent.show?actionMethod=dimquery&type=1&ispage=0&ddhb_one_community.erpId=${house.community.erpId}")');
		}
	}catch(e){
		if(window.console) console.log(e);
	}

	try{
		var houseSecondNo = "${house.hourseNo}";
		var temp = "";
		if($.cookie('houseRentHistory') == undefined || $.cookie('houseRentHistory') == "undefined"){
			//if cookie does not contain the key,init cookie
			$.cookie('houseRentHistory', houseSecondNo);
		} else {
			var arrays = $.cookie('houseRentHistory').split(",");
			if(arrays.length < 5){
				for (var i = 0; i < arrays.length; i++) {
					if(arrays[i] != ""){
						temp += arrays[i] + ",";
					}
				}
				if(jQuery.inArray(houseSecondNo, arrays) == -1){
					temp = temp + houseSecondNo;
				}
			} else {
				//not contain, push and shift
				if(jQuery.inArray(houseSecondNo, arrays) == -1){
					arrays.push(houseSecondNo);//append to the end
					arrays.shift();//remove the first one
				}
				temp = arrays;
			}
			$.cookie('houseRentHistory', temp);//set cookie
		}
	}catch(e){
		if(window.console) console.log(e);
	}

	try{

		/**导航栏上浮**/
		//获取要定位元素距离浏览器顶部的距离
		var navH = $("#checkbardiv").offset().top;
		//滚动条事件
		$(window).scroll(function(){
			//获取滚动条的滑动距离
			var scroH = $(this).scrollTop();
			var distant1 = $("#itemcontent1").offset().top-$("#titleBar").height() - 50;
			var distant2 = $("#itemcontent2").offset().top-$("#titleBar").height() - 50;
			var distant3 = $("#itemcontent3").offset().top-$("#titleBar").height() - 50;
			var distant4 = $("#itemcontent4").offset().top-$("#titleBar").height() - 50;
			var distant5 = $("#itemcontent5").offset().top-$("#titleBar").height() - 50;
			//滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定
			if(scroH>=navH){
				$("#checkbardiv").css("position","fixed").css("top", 0).css("width",$(".nr").width());
			}else if(scroH<navH){
				$("#checkbardiv").css("position","relative");
			}
			$("#titleBar").find(".one").removeClass("one");
			if(scroH>=distant5){
				$("#price").addClass("one");
			}else if(scroH>=distant4){
				$("#photo").addClass("one");
			}
			else if(scroH>=distant3){
				$("#facilities").addClass("one");
			}else if(scroH>=distant2){
				$("#housePhoto").addClass("one");
			}
			else {
				$("#details").addClass("one");
			}
		});
	}catch(e){
		if(window.console) console.log(e);
	}

	try{
		$("#zsShow").click(function(){
			$(this).css("display", "none");
			$("#zsHidden").css("display", "block");
		});
		$("#_Ten_rightDiv").css("z-index", "999");
	}catch(e){
		if(window.console) console.log(e);
	}	
});


//预约带看
var dataString={
		"brokerId":'${houseAppraise.broker.erpId}',
		"searchno":'${house.hourseNo}',
		"houseId":'${house.erpId}',
		"housetype":'2',
		"houseArea":'${house.area}',
		"houseTing":'${house.ting}',
		"houseShi":'${house.shi}',
		"houseWei":'${house.wei}',
		"communityName":'${house.community.communityName}',
		"housePrice":'${house.rentPrice}'
};

function daikan(){
	var actionUrl = "${globalUrl}houseSecond.show?actionMethod=bespeak";
	var id = "daikan";
	var title = "&nbsp;&nbsp;预约服务";
	var _dataString = dataString;
	var type ="post";
	_services(actionUrl, id, title, _dataString, type);
}

//收藏租赁
function keepOppen(){
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=addtoCollection";
	var houseId = '${house.erpId}';
	var brokerId = '${brokerId}';
	var collectType = 1;
	var priceCc = '${house.rentPrice }';
	var platCollection = {
			ObjectId: houseId,
			collectType: collectType,
			priceCc: priceCc,
			brokerId:brokerId
		};
	_keepOppen(actionUrl, platCollection, collectType);
}

/*
//初始化二维码
function initQRCode(globalUrl){
	var url = "${globalUrl}houseSecond.show?actionMethod=drawcode&id=${houseAppraise.erpId}&houseType=2&houseId=${house.hourseNo}&pictureType=";
	_initQRCode(url);
};
*/

function loginBox(target, housetype){
	var url = "${globalUrl}login.show?actionMethod=loginCheck&target="+target;
	_openDialog('login', "&nbsp;&nbsp;登录", url, "get", "html");
}
function consignmentRent(){
	var url = "${globalUrl}rent.show?actionMethod=consignmentDetail";
	_openDialog('register', "&nbsp;&nbsp;注册<a onclick=\"infoShow('registerReason');\" style=\"cursor:pointer; color:red;\">？</a>", url, "get", "html");
}
function showWeixin(broker_id){
	var tagName = "BWeixin_" + broker_id;
	_showWeixin(tagName, 'BWeixin', '评价人微信');
}

</script>
<div class="Location">
	<a onclick='window.location.href="${globalUrl}welcome.show?actionMethod=welcome"' style="cursor: pointer;">首页</a> > 
	<a onclick='window.location.href="${globalUrl}rent.show?actionMethod=dimquery&type=1"' style="cursor: pointer;">杭州出租房</a> > 
	<a onclick='window.location.href="${globalUrl}welcome.show?actionMethod=dimQuery&type=1&searchModule=2&ddhb_one_community.region.erpId=${house.community.region.erpId}"' style="cursor: pointer;">${house.community.region.countyName}出租房</a> >
	${house.community.communityName}出租房
</div>
<!--详情页头开始-->
<div class="zhxq_t">
	<div class="hou_tit_con">
		<div class="zh_dbt" id="titleValue">
 			${(houseAppraise != null) ? houseAppraise.title : house.title}
		</div>
	</div>
	<div class="dt_pt">
		<div class="dt_pts hot-event" id="toppic">
			<c:forEach items="${houseHeadPics }" var="headpic" varStatus="status">
				<div class="event-item dt_pts"
					<c:if test="${status.index == 0}">style="display:block"</c:if>
					<c:if test="${status.index != 0}">style="display:none"</c:if>>
					<img src="${pictureHost}${headpic.picUrl }" onclick="clickImageHead(${status.index+1});" class="photo" style="width: 100%; cursor: pointer;" alt="室内图片" />
				</div>
			</c:forEach>
		</div>
		<div class="dt_pts" id="topmap" style="display: none;"></div>
		<div class="dt_bq">
			<div class="ac">
				<div class="a_jiao">
					<img id="frontPicImg" ischoosed="true" class="frontPicImgClass" src="${globalUrl}image/tm_sj.png" />
				</div>
				<div class="ac_t frontpic frontpicSelected" id="frontpic" onclick="changePic();">照片</div>
			</div>
			<div class="ac">
				<div class="a_jiao">
					<img id="frontMapImg" ischoosed="false" src="${globalUrl}image/tm_sj.png" />
				</div>
				<div class="ac_t frontmap" id="frontmap" onclick="changeMap();">地图</div>
			</div>
			<div style="clear: both;"></div>
		</div>
	</div>
	<div id="midle_box">
		<div class="jia_pm">
			<div class="jpm_s">
				<div class="sjia">
					<span>月租金</span><b><fmt:formatNumber pattern="#" value="${house.rentPrice}" /></b>（元/月）
				</div>
				<div class="mianji"><span>付款方式：</span>${house.payforWay }</div>
				<div class="danjia"><span>租赁形式：</span>${house.rentType.rentTypeName }</div>
			</div>
			<div class="jpm_x">
				<div class="sjiang">
					<span>价格浮动：</span>
					<c:if test="${house.previousRentPrice - house.rentPrice < 0}">
						<div class="sheng">
							<span class="s"><span style="color: #f60">${-(house.previousRentPrice - house.rentPrice)}</span>元</span>
						</div>
					</c:if>
					<c:if test="${house.previousRentPrice - house.rentPrice >= 0}">
						<div class="jiang">
							<span class="j"><span style="color: #f60">${house.previousRentPrice - house.rentPrice }</span>元</span>
						</div>
					</c:if>

					<%--  
					<c:if test="${house.ratioToCommunity >=0 }"> <div class="sheng">所属小区均价参照<span class="s">${house.ratioToCommunity}%</span></div></c:if>
					<c:if test="${house.ratioToCommunity < 0}"> <div class="jiang">所属小区均价参照<span class="j">${-house.ratioToCommunity}%</span></div></c:if>
					--%>
				</div>
				<div class="danjia">
					<p>面积：</p>
					<b><fmt:formatNumber pattern="#" value="${house.area}" />m<up>2</up></b>
				</div>
				<div class="yuyue" style="margin-top: 10px;">
					<a href="javascript:void(0);" onclick="daikan()">预约服务</a>
				</div>
			</div>
		</div>
		<div class="xs_k">
			<div class="sameCommunity" style="padding-top: 35px; padding-bottom: 11px;">
				<span>小区：</span>
				<div class="community_name">${house.community.communityName}</div>
				<div class="same_community_link" id="ssxiaoqu">相似小区</div>
			</div>
			<div class="xs">
				<ul>
					<li><span>户型：</span><span>${house.shi}室${house.ting}厅${house.wei}卫</span><a id="sshuxing" href="javascript:void(0);">相似户型</a></li>
					<li>
						<span>楼层：</span>
						<span>${house.storyCount } 
							<c:if test="${house.vervicalLocation == 1}">层下部</c:if> 
							<c:if test="${house.vervicalLocation == 2}">层中部</c:if> 
							<c:if test="${house.vervicalLocation == 3}">层上部</c:if>
						</span>
						<a id="sslouceng" href="javascript:void(0);">相似楼层</a>
					</li>
					<li><span>朝向：</span><span>${house.orientations.orientationName}</span><a id="sschaoxiang" href="javascript:void(0);">相似朝向</a></li>
					<%-- 
         	<li><span>所属小区：</span><p class="p"><a id="ssxiaoqu" href="javascript:void(0);">相似小区</a>${house.community.communityName}</p></li>
         	--%>
				</ul>
			</div>
			<div class="san_k">
				<div class="ewm">
					<img id="qrCode" src="${globalUrl}houseSecond.show?actionMethod=drawcode&id=${houseAppraise.erpId}&pictureType=alert&houseType=2&houseId=${house.hourseNo}" style="float: right;" src="" width="68" height="67" alt="" onclick="alertQrCode2();"/>
					<p><a href="javascript:void(0);" onclick="alertQrCode2();">扫描二维码获取房源信息</a></p>
				</div>
			</div>
		</div>
		<div class="dbfx" id="PcPadCompare">
			<div style="float: right; padding-right: 10px;">
				<a class="gz" href="javascript:void(0);"
					<c:if test="${LoginMember == null }">onclick="loginBox();"</c:if>
					<c:if test="${LoginMember != null }">onclick="keepOppen()"</c:if>>收藏</a>
				<a class="jb" href="javascript:void(0)" onclick="shaminform();">虚假举报</a>
				<a class="dbi" id="compareContainerContainer"
					housePictureUrl="${pictureHost}${house.pictureUrl }"
					inCompareItem="false"
					onclick="addCompareItem(this, 'rentHouseCompare')"
					compareId="${house.hourseNo }" historyType="R"
					brokerId="${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}"
					compareTitle="${(houseAppraise != null) ? houseAppraise.title : house.title}"
					area="${house.area }" shi="${house.shi }" ting="${house.ting }"
					comparePrice="${house.rentPrice}" style="float: left;">对比</a> <a
					class="fx" data-cmd="more">分享</a> <span class="pl">评价(${appraiseList.size()})</span>
			</div>
		</div>
		<div class="dbfx" id="phoneCompare">
			<div class="bdsharebuttonbox sharePaddingRight" style="float: left; text-align: center;">
				<a style="width: 40px; height: 24px; line-height: 26px; margin: 0; background-image: url('null');" class="bds_more" data-cmd="more">分享</a>
			</div>
			<a style="float: left; width: 1px; padding: 0;">|</a> <a href="javascript:void(0);" onclick="shaminform();" style="float: right">虚假举报</a>
		</div>
	</div>
	<div class="man">
		<div class="r_bh">内部编号：${house.hourseNo}</div>
		<c:if test="${houseAppraise != null}">
			<div class="wd_lx">需要帮助请联系我</div>
			<div class="rwtp">
				<c:if test="${houseAppraise.broker == null || houseAppraise.broker.photograph == ''}">
					<img src="${globalUrl}images/broker/head.jpg" alt="" />
				</c:if>
				<c:if test="${houseAppraise.broker != null}">
					<a href="#"><img onclick="window.open('${globalUrl}broker.show?actionMethod=brokerDetail&brokerId=${houseAppraise.broker.erpId}&housetype=2')" src="${pictureHost}${houseAppraise.broker.photograph}" alt="" /></a>
				</c:if>
			</div>
		</c:if>
		<div class="m_con">
			<div class="r_bh_c">内部编号：<br>${house.hourseNo}</div>
			<c:if test="${houseAppraise != null}">
				<div class="wd_lx_c">需要帮助请联系我</div>
				<div class="name"><c:out value="${houseAppraise.broker.bname}"></c:out></div>
				<div class="ihp"><c:out value="${houseAppraise.broker.telephone}"></c:out></div>
				<div class="qqwx">
					<c:if test="${house.broker.qq != null && house.broker.qq != ''}">
						<a href="tencent://message/?uin=${houseAppraise.broker.qq }&Site=qq&Menu=yes" onclick="qqcao(${houseAppraise.broker.qq })"><img src="${globalUrl}image/qq.gif" alt="" /></a>
					</c:if>
					<c:if test="${house.broker.weCharUrl != null && house.broker.weCharUrl != ''}">
						<a href="javascript:void(0);"><img src="${globalUrl}image/wx.gif" onclick="wchatDisplay();" alt="" /></a>
					</c:if>
				</div>
			</c:if>
		</div>
	</div>
</div>
<div id="fangda" style="display: none;">
	<img id="qrCode2" src="${globalUrl}houseSecond.show?actionMethod=drawcode&id=${houseAppraise.erpId}&pictureType=alert&houseType=2&houseId=${house.hourseNo}" />
</div>
<div id="BrokerWexin" style="display: none;">
	<img src="${pictureHost }${house.broker.weCharUrl}" />
</div>
<!--详情页头结束-->
<div id="peiTao">
	<div class="spa_tit">房间已有配套设施：</div>
	<div class="dt_color">
		<c:forEach items="${furList}" var="furs">
			<c:if test="${furs.flag eq true}">
				<img src="${pictureHost}${furs.imgUrl}" style="width: auto;" title="<c:out value="${furs.furName }"></c:out>">
			</c:if>
			<c:if test="${furs.flag eq false }">
				<img src="${pictureHost}${furs.imgDisUrl}" style="width: auto;" title="<c:out value="${furs.furName }"></c:out>">
			</c:if>
		</c:forEach>
	</div>
</div>
<!--内容开始-->
<div class="nr">
	<div class="nr_tit" id="checkbardiv">
		<div class="tit_a" id="titleBar">
			<span onclick="arrowLeft();" style="display: none;" class="myjt"></span>
			<div id="nvBar">
				<div id="barbar">
					<a id="details" onclick="goDiv('${globalUrl}', 'itemcontent1')" class="a_1 one">房源详情</a> <a id="housePhoto" onclick="goDiv('${globalUrl}', 'itemcontent2')" class="a_2">室内照片</a>
					<a id="facilities" onclick="goDiv('${globalUrl}', 'itemcontent3')" class="a_3">生活配套</a> <a id="photo" onclick="goDiv('${globalUrl}', 'itemcontent4')" class="a_4">小区相册</a>
					<%-- <a id="price" onclick="goDiv('${globalUrl}', 'itemcontent5')">价格走势</a> --%>
				</div>
			</div>
			<span onclick="arrowRight();" class="myjtr" style="display: none;"></span>
		</div>
	</div>
	<div class="nr_ar" id="itemcontent1">
		<div class="nr_zb zb_1">
			<div class="biao"></div>
			<div class="b_wd">房源详情</div>
		</div>
		<div class="nr_yb">
			<div class="d_x">
				<ul>
					<li>住房类型<p>${house.usage.usageName}</p></li>
					<li>看房时间<p>${house.timeToSee}</p></li>
					<li>装修<p>${house.decoration.decorationName}</p></li>
				</ul>
			</div>
			<div class="nr_wd contentMore">
				<c:out value="${house.content }" escapeXml="false"></c:out>
			</div>
			<div class="nr_wd contentMore">
				<c:forEach items="${house.community.nearStoreList}" var="nearStore" begin="0" end="0" varStatus="status">
					最近门店：<c:out value="${nearStore.storeName }"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;
					地址：<c:out value="${nearStore.storeAddress}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;                                 
					电话：<c:out value="${nearStore.telephoneNo }"></c:out><br />
				</c:forEach>
			</div>
			<div class="shou" id="moreContent">
				<a onclick="clickContentMore();" class="jia">展开更多</a>
			</div>
		</div>
	</div>
	<div class="nr_ar" id="itemcontent2">
		<div class="nr_zb zb_2">
			<div class="biao"></div>
			<div class="b_wd">室内照片</div>
		</div>
		<div class="nr_yb">
			<div class="nr_ptls">
				<p> 室内照片共有 <c:out value="${housePics.size()}" /> 张照片 </p>
				<ul>
					<c:forEach items="${housePics}" var="headpic" varStatus="status"
						begin="0" end="0">
						<li><a href="javascript:void(0);"><img
								src="${pictureHost}${headpic.picUrl}" alt=""
								onclick="clickImage(${status.index+1});" /></a></li>
					</c:forEach>
					<c:forEach items="${housePics}" var="headpic" varStatus="status"
						begin="1">
						<li class="photoMore"><a href="javascript:void(0);"><img
								src="${pictureHost}${headpic.picUrl}" alt=""
								onclick="clickImage(${status.index+1});" /></a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="shou" id="morePhoto">
				<a onclick="clickPhotoMore();" class="jia">展开更多</a>
			</div>
		</div>
	</div>
	<div class="nr_ar" id="itemcontent3">
		<div class="nr_zb zb_3">
			<div class="biao"></div>
			<div class="b_wd">生活配套</div>
		</div>
		<div class="nr_yb">
			<div class="d_x">
				<ul>
					<li>小区名称<p>${house.community.communityName }</p></li>
					<li>竣工时间 <p><fmt:formatDate value="${house.community.buildYear}" pattern="yyyy.MM.dd" /></p></li>
					<li>开发商<p>${house.community.developer }</p></li>
					<li>绿化率<p>${house.community.landScaping }%</p></li>
				</ul>
			</div>
			<div class="d_x">
				<ul>
					<li>交通
						<p>
							<c:forEach items="${cStaMapping }" var="cstms">${cstms.station.stationName },</c:forEach>
							<c:forEach items="${cSubMapping }" var="csubm" varStatus="stat">
								<c:if test="${!stat.last}">${csubm.subwayStation.stationName },</c:if>
								<c:if test="${stat.last}">${csubm.subwayStation.stationName }</c:if>
							</c:forEach>
						</p>
					</li>
				</ul>
			</div>
			<%@include file="/WEB-INF/jsp/ddhb/front/commonInfoShow/common_streetview.jsp"%>
			<div class="shou" id="mapMore">
				<a onclick="clickMoreMap();" class="jia">展开更多</a>
			</div>
		</div>
	</div>
	<div class="nr_ar" id="itemcontent4">
		<div class="nr_zb zb_4">
			<div class="biao"></div>
			<div class="b_wd">小区相册</div>
		</div>
		<div class="nr_yb">
			<div class="nr_xcls">
				<p>
					小区共有<c:out value="${communityPics.size()}" />张照片 
					<a href="javascript:void(0);"<c:if test="${communityPics.size() >0 }">  onclick="window.open('${globalUrl}community.show?actionMethod=communityDetail&id=${communityPics.get(0).houseId}&mao=price');return false;" </c:if>>浏览全部照片</a>
				</p>
				<ul>
					<c:if test="${communityPics.size() >0 }">
						<c:forEach items="${communityPics }" var="headpic" begin="0"
							end="0" varStatus="status">
							<li><a href="javascript:void(0);"><img
									src="${pictureHost }${headpic.picUrl }" alt=""
									onclick="clickCommImage(${status.index+1});" /></a></li>
						</c:forEach>
						<c:forEach items="${communityPics }" var="headpic" begin="1"
							varStatus="status">
							<li class="communityPhotoMore"><a href="javascript:void(0);"><img
									src="${pictureHost }${headpic.picUrl }" alt=""
									onclick="clickCommImage(${status.index+1});" /></a></li>
						</c:forEach>
					</c:if>
				</ul>
			</div>
			<div class="shou" id="moreCommunityPhoto">
				<a onclick="clickCommunityPhotoMore();" class="jia">展开更多</a>
			</div>
		</div>
	</div>
	<%-- 
	<div class="nr_ar" id="itemcontent5">
		<div class="nr_zb zb_5">
			<div class="biao"></div>
			<div class="b_wd">价格走势</div>
		</div>
		<div class="nr_yb">
			<div id="priceChart" class="jgzs"
				style="width: 390px; height: 280px;"></div>
			<div id="priceChart3" class="jgzs"
				style="width: 240px; height: 140px;"></div>
			<div class="jgzssj" id="zsHidden">
				<div style="float: left;">
					<div class="jtsj" style="background-color: #F55A5A;">
						本案本月
						<p>元/月</p>
						<fmt:formatNumber value="${thisMonthPrice}" />
					</div>
					<div class="jtsj"
						style="background-color: #DBDBDB; color: #000000;">
						本案上月
						<p>元/月</p>
						<fmt:formatNumber value="${laseMonthPrice}" />
					</div>
				</div>
				<div class="bfb">
					<span class="bfbwz">本案比上月<c:if
							test="${aroundLaseMonth ge 0}">增长</c:if> <c:if
							test="${aroundLaseMonth lt 0}">下降</c:if></span> <span class="bfbsz"
						style="color:<c:if test="${aroundLaseMonth ge 0}">#F55A5A</c:if><c:if test="${aroundLaseMonth lt 0}">#64AD70</c:if>">
						<c:if test="${aroundLaseMonth eq 0}">${aroundLaseMonth }%</c:if> <c:if
							test="${aroundLaseMonth gt 0}">${fn:substring(aroundLaseMonth, 1, 5 )}%</c:if>
						<c:if test="${aroundLaseMonth lt 0}">
							<c:out value="${fn:substring(aroundLaseMonth, 1, 5 )}"></c:out>%</c:if>
					</span>
				</div>

				<div style="float: left; margin-top: 5px">
					<div class="jtsj" style="background-color: #64AD70;">
						所在小区 本月均价
						<p>元/月</p>
						<fmt:formatNumber value="${thisMonthPriceCommunity}" />
					</div>
					<div class="jtsj"
						style="background-color: #DBDBDB; color: #000000;">
						所在小区 上月均价
						<p>元/月</p>
						<fmt:formatNumber value="${lastMonthPriceCommunity}" />
					</div>
				</div>
				<div class="bfb" style="margin-top: 5px;">
					<span class="bfbwz">${house.community.communityName}比上月<c:if
							test="${aroundLaseMonthCommunity ge 0}">增长</c:if> <c:if
							test="${aroundLaseMonthCommunity lt 0}">下降</c:if></span> <span
						class="bfbsz"
						style="color:<c:if test="${aroundLaseMonthCommunity ge 0}">#F55A5A</c:if><c:if test="${aroundLaseMonthCommunity lt 0}">#64AD70</c:if>">
						<c:if test="${aroundLaseMonthCommunity eq 0}">${aroundLaseMonthCommunity }%</c:if>
						<c:if test="${aroundLaseMonthCommunity gt 0}">${fn:substring(aroundLaseMonthCommunity, 1, 5 )}%</c:if>
						<c:if test="${aroundLaseMonthCommunity lt 0}">
							<c:out value="${fn:substring(aroundLaseMonthCommunity, 1, 5 )}"></c:out>%</c:if>
					</span>
				</div>
			</div>
			<div class="shou">
				<a class="jia" id="zsShow">展开更多</a>
			</div>
		</div>
	</div>
	--%>
	<%-- 不知道为什么 把价格走势给去掉 会导致地图显示不出 STRAT --%>
	<div class="nr_ar" id="itemcontent5" style="display: none;">
		<div id="priceChart" class="jgzs" style="width: 390px; height: 280px;"></div>
		<div id="priceChart3" class="jgzs"
			style="width: 240px; height: 140px;"></div>
	</div>
</div>
<!--内容结束-->

<c:if test=" ${appraiseList.size()>0}">
<!--顾问开始-->
<div class="guwen">
	<div class="nr_tt">
		<span>共有 ${appraiseList.size()} 位置业人员服务于本房源</span><b>服务本房源的置业顾问：</b>
	</div>
	<div class="gw_ls">
		<ul>
			<c:forEach items="${appraiseList }" var="apps" begin="0" end="2">
				<li><c:if
						test="${apps.broker ne null && apps.broker.photograph != ''}">
						<div class="gwls_t"
							onclick="window.open('${globalUrl}broker.show?actionMethod=brokerDetail&brokerId=${apps.broker.erpId}&housetype=1')">
							<a href="javascript:void(0);"><img
								src="${pictureHost }${apps.broker.photograph}" alt="" /></a>
						</div>
					</c:if> <c:if
						test="${apps.broker eq null || apps.broker.photograph == ''}">
						<div class="gwls_t">
							<a href="javascript:void(0);"><img
								src="${globalUrl}images/broker/head.jpg" alt="" /></a>
						</div>
					</c:if>
					<div class="gw_r">
						<c:if test="${apps.broker != null}">
							<div class="gwname">${apps.broker.store.storeName}-
								${apps.broker.bname}</div>
						</c:if>
						<div class="gw_tel">
							<c:if
								test="${apps.broker.telephone != ' ' && apps.broker.telephone != null}">
								<b>${apps.broker.telephone}</b>
							</c:if>
							<c:if test="${apps.broker.qq != ' '  && apps.broker.qq != null}">
								<span><a
									href="tencent://message/?uin=${apps.broker.qq }&Site=qq&Menu=yes"
									onclick="qqcao(${apps.broker.qq })"></a><img
									src="${globalUrl}image/qq.gif" alt="" /></span>
							</c:if>
							<c:if
								test="${apps.broker.weCharUrl != '' && apps.broker.weCharUrl != null}">
								<span><img src="${globalUrl}image/wx.gif"
									onclick="showWeixin('${apps.broker.erpId }');" alt="" /></span>
							</c:if>
						</div>
						<!-- <div id="BWeixin" style="display: none;"><img src="${pictureHost }${apps.broker.weCharUrl}"/></div>-->
						<c:if test="${apps.broker ne null}">
							<div id="BWeixin_${apps.broker.erpId }" style="display: none;">
								<img src="${pictureHost }${apps.broker.weCharUrl}" />
							</div>
						</c:if>
						<div class="gw_tj">
							<b>推荐理由：</b> ${apps.title}
						</div>
						<div class="gw_jj">${apps.content}</div>
						<div class="gw_kf">
							<!-- 近 30 天带看量：<c:if test="${apps.broker == null }">0</c:if>${apps.broker.daikan} 次<span>&nbsp;&nbsp;&nbsp;&nbsp; 持有房源：<c:if test="${apps.broker == null }">0</c:if>${apps.broker.houseCount} 套</span>-->
						</div>
					</div></li>
			</c:forEach>
		</ul>
		<!-- <div class="shou"><a href="#" class="jia">展开更多</a></div> -->
	</div>
</div>
<!--顾问结束-->
</c:if>

<c:if test="${recommendList.size()>0}">
<!--推荐开始-->
<div class="tjjl">
	<div class="nr_tt">
		<b>您可能喜欢：</b>
	</div>
	<div class="tjjl">
		<ul>
			<c:forEach var="houseR" items="${recommendList}" begin="0" end="2">
				<li class="li_1 houseItem">
					<div class="pt"
						onclick="window.open('${globalUrl}rent.show?actionMethod=houseRentDetail&houseNo=${houseR.hourseNo}&brokerId=${(houseAppraise != null) ? houseAppraise.broker.erpId : houseR.broker.erpId}')">
						<c:if test="${houseR.pictureUrl eq null}">
							<img src="${globalUrl}image/house_no_picture.png" width="226" height="170" />
						</c:if>
						<c:if test="${houseR.pictureUrl ne null}">
							<img src="${pictureHost }${houseR.pictureUrl }" />
						</c:if>
						<div class="pt_info">
							<span style="float: left; width: 80px;">${fn:substring(houseR.title, 0, 20)}<c:if test="${fn:length(houseR.title)>20}">...</c:if></span> 
							<span style="float: left; margin-top: -2px;"><fmt:formatNumber value="${houseR.area}" pattern="##"></fmt:formatNumber>m<sup>2</sup></span>
							<span style="float: right;">[${house.rentType.rentTypeName}]</span>
						</div>
					</div>
					<div class="pt_s">
						<p style="width: 80px;">${houseR.community.region.countyName } - ${houseR.community.communityName }</p>
						<span>${houseR.shi}室${houseR.ting}厅${houseR.wei}卫</span> <b><fmt:formatNumber value="${houseR.rentPrice }" pattern="##"></fmt:formatNumber>元/月</b>
					</div> 
					<!--
					<div class="pt_s"><p>房租（元/月）</p><span>${houseR.rentPrice - house.rentPrice > 0 ? "高于":"低于"}本案<fmt:formatNumber value="${houseR.rentPrice - house.rentPrice > 0 ? (houseR.rentPrice - house.rentPrice):-(houseR.rentPrice - house.rentPrice)}"  pattern="##"></fmt:formatNumber>元</span><b><fmt:formatNumber value="${houseR.rentPrice }" pattern="##"></fmt:formatNumber></b></div>
          <div class="pt_s"><p>面积（平米）</p><span>${houseR.area - house.area > 0 ? "高于":"低于"}本案<fmt:formatNumber value="${houseR.area - house.area > 0 ? (houseR.area - house.area):-(houseR.area - house.area)}" pattern="##"></fmt:formatNumber> 平米</span><b><fmt:formatNumber value="${houseR.area}" pattern="##"></fmt:formatNumber></b></div>
          <div class="ziduan"><span>户型：</span>${houseR.shi} 室 ${houseR.ting} 厅 ${houseR.wei} 卫<br><span>楼层：</span>${houseR.storey} <c:if test="${houseR.vervicalLocation == 1}">层下部</c:if>
					<c:if test="${houseR.vervicalLocation == 2}">层中部</c:if>
					<c:if test="${houseR.vervicalLocation == 3}">层上部</c:if><br><span>朝向：</span>${houseR.orientations.orientationName}<br><span>小区：</span>${houseR.community.communityName}
					</div>
					-->
				</li>
			</c:forEach>
		</ul>
	</div>
	<!-- <div class="shou"><a href="#" class="jia">展开更多</a></div> -->
</div>
<!--推荐结束-->
</c:if>

<!-- 虚假举报隐藏框 -->
<div id="dialoginform" style="display: none;">
	<%@include file="/WEB-INF/jsp/ddhb/front/company/company_shaminform_dialog.jsp"%>
</div>
<!-- 照片放大隐藏框 -->
<c:set var="Pictures" value="${housePics}"></c:set>
<%@include file="/WEB-INF/jsp/ddhb/front/commonInfoShow/common_slider.jsp"%>
<%--
<div id="blackGround" class="tm_hei" style="display: none; position: fixed;"></div>
<div id="photoAlbum" class="tcc_zp lanrenzhijia" style="display: none; position: fixed;">
	<div class="zp_con">
		<img src="${globalUrl}image/xxx.png" style="width: auto; height: auto; right: 5px; top: 5px; position: absolute; cursor: pointer;" onclick="$('#photoAlbum').hide();$('#blackGround').hide();"> 
			<span id="prevTop" class="btn prev"></span> 
			<span id="nextTop" class="btn next"></span>
		<div id="picBox" class="picBox">
			<ul id="picCenter" class="cf">
				<c:forEach var="pic" items="${housePics}">
					<li><a style="width: 100%; height: 100%;"><img src='${pictureHost}${pic.picUrl}' /><span>${house.community.communityName },<fmt:formatNumber
									value="${house.area }" pattern="#"></fmt:formatNumber>平米,${house.shi }室${house.ting }厅${house.wei }卫,<fmt:formatNumber
									value="${house.rentPrice}" pattern="#"></fmt:formatNumber>元/月
						</span></a></li>
				</c:forEach>
			</ul>
		</div>
		<div id="listBox" class="listBox zp_xt">
			<ul id="picList" class="cf">
				<c:forEach var="pic" items="${housePics}">
					<li><i class='arr2'></i><a><img src='${pictureHost}${pic.picUrl}' /></a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
 --%>
<div id="photoAlbumHead" class="tcc_zp lanrenzhijia" style="display: none; position: fixed;">
	<div class="zp_con">
		<img src="${globalUrl}image/xxx.png"
			style="width: auto; height: auto; right: 5px; top: 5px; position: absolute; cursor: pointer;"
			onclick="$('#photoAlbumHead').hide();$('#blackGround').hide();">
		<span id="prevTopHead" class="btn prev"></span> <span id="nextTopHead"
			class="btn next"></span>
		<div id="picBoxHead" class="picBox">
			<ul id="picCenter" class="cf">
				<c:forEach var="pic" items="${houseHeadPics}">
					<li><a style="width: 100%; height: 100%;"><img
							src='${pictureHost}${pic.picUrl}' /><span>${house.community.communityName },<fmt:formatNumber
									value="${house.area }" pattern="#"></fmt:formatNumber>平米,${house.shi }室${house.ting }厅${house.wei }卫,<fmt:formatNumber
									value="${house.rentPrice}" pattern="#"></fmt:formatNumber>元/月
						</span></a></li>
				</c:forEach>
			</ul>
		</div>
		<div id="listBoxHead" class="listBox zp_xt">
			<ul id="picListHead" class="cf">
				<c:forEach var="pic" items="${houseHeadPics}">
					<li><i class='arr2'></i><a><img
							src='${pictureHost}${pic.picUrl}' /></a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
<div id="photoComm" class="tcc_zp lanrenzhijia" style="display: none; position: fixed;">
	<div class="zp_con">
		<img src="${globalUrl}image/xxx.png"
			style="width: auto; height: auto; right: 5px; top: 5px; position: absolute; cursor: pointer;"
			onclick="$('#photoComm').hide();$('#blackGround').hide();"> <span
			id="prevTopComm" class="btn prev"></span> <span id="nextTopComm"
			class="btn next"></span>
		<div id="picBoxComm" class="picBox">
			<ul id="picCenterComm" class="cf">
				<c:forEach items="${communityPics }" var="headpic">
					<li><a style="width: 100%; height: 100%;"><img
							src="${pictureHost }${headpic.picUrl }" alt="" /></a></li>
				</c:forEach>
			</ul>
		</div>
		<div id="listBoxComm" class="listBox zp_xt">
			<ul id="picListComm" class="cf">
				<c:forEach items="${communityPics }" var="headpic">
					<li><i class='arr2'></i><a><img src="${pictureHost }${headpic.picUrl }" alt="" /></a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
