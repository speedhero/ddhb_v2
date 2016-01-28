<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.el.lang.ELSupport"%>
<%@page import="java.util.List"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="h" uri="/WEB-INF/tld/hshb.tld"%>

<%-- <link rel="stylesheet" type="text/css" href="${globalUrl}css/style.css"/> --%>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/houseSecond.css"/>
<%-- <link rel="stylesheet" type="text/css" href="${globalUrl}css/houseDetail.css"/> --%>
<%-- <link rel="stylesheet" type="text/css" href="${globalUrl}css/jquery.ad-gallery.css"> --%>

<%-- <script src="${globalUrl}js/jquery.ad-gallery.js"></script> 
<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
<script src="http://map.qq.com/api/js?v=2.exp&key=Z3YBZ-JVFHV-6W2P2-U653S-DCY22-KVFDG"></script>
--%> 
<!-- 弹出框js引用 -->
<%-- <script type="text/javascript" src="${globalUrl}js/jquery.artDialog.source.js"></script> 
<script type="text/javascript" src="${globalUrl}js/artDialog.iframeTools.source.js"></script>
<link type="text/css" rel="stylesheet" href="${globalUrl}js/skins/gray.css">--%>
<!-- 分享按钮 -->
<script type="text/javascript" src="${globalUrl}js/share.js"></script>
<script type="text/javascript" src="${globalUrl}js/commonGround/chatTools.js"></script>
<!-- 一下被注释，会影响到 照片和地图的两个字体样式 -->
<style>
.addCompare{background-color:inherit !important;}
.grayBack{background-color: gray;}
.ac{float:left; width:174px;height:50px;text-align: center; line-height: 38px;font-size:18px; margin-top:-10px; position: relative;}
.ac .ac_t{width:100%; height:45px; cursor: pointer;}
.ac .a_jiao{height:10px;}
.ac .a_jiao img{height:10px; width:20px; visibility: hidden;}
.frontpic{background: #E1E1E1 url(${globalUrl}image/zp_a.gif) no-repeat 34px 11px;border-left: 1px solid rgb(160, 160, 160);border-bottom: 1px solid rgb(160, 160, 160);}
.frontmap{background: #E1E1E1 url(${globalUrl}image/dt_a.gif) no-repeat 34px 11px;border-bottom: 1px solid rgb(160, 160, 160);border-left: 1px solid rgb(160, 160, 160); border-right: 1px solid rgb(160, 160, 160);}
.frontpicSelected{background: #FFFFFF url(${globalUrl}image/zp_b.gif) no-repeat 34px 11px;color: #3FB8B1;}
.frontmapSelected{background: #FFFFFF url(${globalUrl}image/dt_b.gif) no-repeat 34px 11px;color: #3FB8B1;}
.frontpicHover{background: #FFFFFF url(${globalUrl}image/zp_b.gif) no-repeat 34px 11px;color: #3FB8B1;}
.frontmapHover{background: #FFFFFF url(${globalUrl}image/dt_b.gif) no-repeat 34px 11px;color: #3FB8B1;}
.frontPicImgClass{display:inline;}
.frontMapImgClass{display:inline;}
.frontPicImgHoverClass{display:inline;}
.frontMapImgHoverClass{display:inline;}
#phoneCompare{display:none;}
#PcPadCompare{display:block;}
.jia_pm .mianji {padding: 0; font-size: 14px; }
.jia_pm .danjia { padding: 0;}
.jia_pm .sjiang{padding:0px;}
.jia_pm .daik{padding-top:0px; padding-bottom:5px;}
.sameCommunity{padding: 5px 0;}
.jia_pm .daik a{margin-top: 1px;}

/* .tit_a a.jtr,.tit_a a.jtr:hover{background: url(${globalUrl}image/tit_jt_right.gif) no-repeat; } */
.myjt{width:28px;float:left;height:28px;background: url('${globalUrl}image/tit_jt.gif') no-repeat; display:block;margin-top:0;padding:0; cursor:pointer;}
.myjtr{width:28px;float:left;height:28px;background: url('${globalUrl}image/tit_jt_right.gif') no-repeat; display:block;margin-top:0;padding:0; cursor:pointer;}

</style>
<script type="text/javascript">
var isDialog = false;
<%--
//趋势图
function _initCharts(size){
	 var arrayz = [
      <c:forEach var="trend" items="${trendList}" varStatus="s">
      ["${trend.dateAndMonth}".substring(0,10), ${trend.unitPrice}]${s.last?'':','}
    </c:forEach>
	];
	initCharts(size, arrayz);
}
--%>
//预约带看
var dataString={
		"brokerId":'${brokerId}',
		"searchno":'${house.houseId}',
		"houseId":'${house.erpId}',
		"housetype":'1',
		"houseArea":'${house.area}',
		"houseTing":'${house.ting}',
		"houseShi":'${house.shi}',
		"houseWei":'${house.wei}',
		"communityName":'${house.community.communityName}',
		"housePrice":'${house.price}'
};

function daikan(){
	var url = "${globalUrl}houseSecond.show?actionMethod=bespeak";
	var id = "daikan";
	var title = "&nbsp;&nbsp;预约服务";
	var _dataString = dataString;
	var type ="post";
	_services(url, id, title, _dataString, type);
}
<%--
//收藏二手房
function keepOppen(){
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=addtoCollection";
	var houseId = '${house.erpId}';
	var brokerId = '${brokerId}';
	var collectType = 0;
	var priceCc = '${house.price }';
	var platCollection = {
		ObjectId: houseId,
		collectType: collectType,
		priceCc: priceCc,
		brokerId: brokerId
	};
	_keepOppen(actionUrl, platCollection, collectType);
}
--%>
function changeCompareMenu(obj){
	$(".compareMenuSelected").first().removeClass("compareMenuSelected");
	$(obj).addClass("compareMenuSelected");
	$("#hiddenCompareDiv").css("display", "block");
	if ($(obj).attr("id") == 'compareMenu'){
		$("#compareContentContainer").css("display", "block");
		$("#historyListContainer").css("display", "none");
	}else if ($(obj).attr("id") == 'historyDiv'){
		$("#compareContentContainer").css("display", "none");
		$("#historyListContainer").css("display", "block");
	}
}

function addBack(divId){
	$('#'+divId).addClass('testQ');
}

function removeBack(divId){
	$('#'+divId).removeClass('testQ');
} 

function openCalcDialog(){
	var url = "${globalUrl}houseSecond.show?actionMethod=getCalculator&unitPrice=${house.unitPrice}&area=${house.area}";
	openModelAjaxDialog('calculator', "&nbsp;&nbsp;个人住房商业贷款", url );
}

function loginBox(target, housetype){	
	var flag = housetype;
	var url = "${globalUrl}login.show?actionMethod=loginCheck&target="+target;
	var gray = "<link type='text/css' rel='stylesheet' href='${globalUrl}js/skins/gray.css'>";
	var dlzc = "<link rel='stylesheet' type='text/css' href='${globalUrl}css/css.css'>";
	var css = "<link rel='stylesheet' type='text/css' href='${globalUrl}css/dlzc.css'>";
	 if(flag != "") url = url+"&housetype="+flag;
	_openDialog('login', "&nbsp;&nbsp;登录", url, "get", "html", gray, dlzc, css);
}

function consignment(){
	var url = "${globalUrl}houseSecond.show?actionMethod=consignmentDetail";
	_consignment(url);
}

</script>
<script type="text/javascript">
var historyItemsArray;
var comparedItemsArray;
$(document).ready(function() {
	<%--
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
--%>
	$("#frontpic").click(function(){
		$("#frontPicImg").attr("ischoosed", "true");
		$("#frontMapImg").attr("ischoosed", "false");
		$("#frontpic").addClass("frontpicSelected");
		$("#frontmap").removeClass("frontmapSelected");
		$("#frontPicImg").css("visibility", "hidden");
		$("#frontMapImg").css("visibility", "hidden"); 
		$("#frontPicImg").css("visibility", "visible");
	});
	
	$("#frontmap").click(function(){
		$("#frontPicImg").attr("ischoosed", "false");
		$("#frontMapImg").attr("ischoosed", "true");
		$("#frontmap").addClass("frontmapSelected");
		$("#frontpic").removeClass("frontpicSelected");
		$("#frontMapImg").css("visibility", "visible"); 
		$("#frontPicImg").css("visibility", "hidden");
	});
	
	try{
		$(".compareDIvMenu").click(function(){
			changeCompareMenu($(this));
		});
		var comparedItemInCookie = '${secondHouseCompare}';
		if (comparedItemInCookie == ''){
			comparedItemsArray=new Array();
		}else{
			comparedItemsArray = JSON.parse(comparedItemInCookie); 
		}
		
	    initComparedItems(comparedItemsArray, "secondHouseCompare");
	}catch(e){
		if(window.console) console.log(e.description);
	}

   try{
   	var historyObj = $("#compareContainerContainer").first();
   	var historyItemInCookie = '${secondHouseHistory}';
   	if (historyItemInCookie == ''){
   		historyItemsArray = new Array();
   	}else{
   		historyItemsArray = JSON.parse(historyItemInCookie);
   	}

    	var currentPageUrl = window.location.href;
   	var urlArray = currentPageUrl.split("?");
   	var relativeUrl = currentPageUrl.substring(urlArray[0].lastIndexOf("/") + 1, currentPageUrl.length);
   	addHistoryItem(historyObj, "secondHouseHistory", relativeUrl);

   }catch(e){
   	if(window.console) console.log(e.description);
   }

	try{
		/**相似查询**/
		var idStr = $.cookie("lastSelected");
		if(idStr == "imgShape") {
			$("#sshuxing").attr("onclick", 'window.open("${globalUrl}chushou/r${house.shi}")');
			<%-- //${globalUrl}houseSecond.show?actionMethod=dimquery&type=0&ispage=0&ddhb_two_storyCount=${house.storeyCount - 2}@${house.storeyCount + 2}&ddhb_one_vervicalLocation=${house.houseVervicalLocation} --%>
			$("#sslouceng").attr("onclick", 'window.open("${globalUrl}chushou?floor=${house.storeyCount - 2}@${house.storeyCount + 2}&vervicalLocation=${house.houseVervicalLocation}")');
			<%-- //${globalUrl}houseSecond.show?actionMethod=dimquery&type=0&ispage=0&ddhb_one_orientations.id=${house.orientation.orientationNo} --%>
			$("#sschaoxiang").attr("onclick", 'window.open("${globalUrl}chushou/f${house.orientation.orientationNo}")');
			<%-- //${globalUrl}houseSecond.show?actionMethod=dimquery&type=0&ispage=0&ddhb_one_community.erpId=${house.community.erpId} --%>
			$("#ssxiaoqu").attr("onclick", 'window.open("${globalUrl}chushou/q${house.communityId}")');
		} else {
			<%-- //${globalUrl}houseSecond.show?actionMethod=dimquery&type=1&ispage=0&ddhb_one_shi=${house.shi} --%>
			$("#sshuxing").attr("onclick", 'window.open("${globalUrl}chushou/r${house.shi}")');
			<%-- //${globalUrl}houseSecond.show?actionMethod=dimquery&type=1&ispage=0&ddhb_two_storyCount=${house.storeyCount - 2}@${house.storeyCount + 2}&ddhb_one_vervicalLocation=${house.houseVervicalLocation} --%>
			$("#sslouceng").attr("onclick", 'window.open("${globalUrl}chushou?floor=${house.storeyCount - 2}@${house.storeyCount + 2}&vervicalLocation=${house.houseVervicalLocation}")');
			<%-- //${globalUrl}houseSecond.show?actionMethod=dimquery&type=1&ispage=0&ddhb_one_orientations.id=${house.orientation.orientationNo} --%>
			$("#sschaoxiang").attr("onclick", 'window.open("${globalUrl}chushou/f${house.orientation.orientationNo}")');
			<%-- //${globalUrl}houseSecond.show?actionMethod=dimquery&type=1&ispage=0&ddhb_one_community.erpId=${house.community.erpId} --%>
			$("#ssxiaoqu").attr("onclick", 'window.open("${globalUrl}chushou/q${house.community.erpId}")');
		}
	}catch(e){
		if(window.console) console.log(e.description);
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
			//var distant5 = $("#itemcontent5").offset().top-$("#titleBar").height() - 50;
			//滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定
			if(scroH>=navH){
				$("#checkbardiv").css("position","fixed").css("top", 0).css("width",$(".nr").width());
			}else if(scroH<navH){
				$("#checkbardiv").css("position","relative");
			}
			$("#titleBar").find(".one").removeClass("one");
			if(scroH>=distant4){
				$("#photo").addClass("one");
			}else if(scroH>=distant3){
				$("#facilities").addClass("one");
			}else if(scroH>=distant2){
				$("#housePhoto").addClass("one");
			}else {
				$("#details").addClass("one");
			} 
		}); 
	}catch(e){
		if(window.console) console.log(e.description);
	}

	try{
		//浏览历史推荐
	    var houseSecondNo = "${house.houseId}";
		var temp = "";
		if($.cookie('houseSecondHistory') == undefined || $.cookie('houseSecondHistory') == "undefined"){
			//if cookie does not contain the key,init cookie
			$.cookie('houseSecondHistory', houseSecondNo);
		} else {
			var arrays = $.cookie('houseSecondHistory').split(",");
			if(arrays.length < 5){
				for (var i = 0; i < arrays.length; i++) {
					if(arrays[i] != "") temp += arrays[i] + ",";
				}
				if(jQuery.inArray(houseSecondNo, arrays) == -1) temp = temp + houseSecondNo;
			} else {
				//not contain, push and shift
				if(jQuery.inArray(houseSecondNo, arrays) == -1){
					arrays.push(houseSecondNo);//append to the end
					arrays.shift();//remove the first one
				}
				temp = arrays;
			}
			$.cookie('houseSecondHistory', temp);//set cookie
		}
	}catch(e){
		if(window.console) console.log(e.description);
	}

   try{
    $("#zsShow").click(function(){
    	$(this).css("display", "none");
    	$("#zsHidden").css("display", "block");
    });
   }catch(e){
   	if(window.console) console.log(e.description);
   }
});
function showWeixin(broker_id){
	var tagName = "BWeixin_" + broker_id;
	_showWeixin(tagName, 'BWeixin', '评价人微信');
}
//分享
function shareHouse(){
	$("#sharePanel").css("display", "block");
}
</script>
<div class="Location">
	<a href="${globalUrl}" style="cursor: pointer;">首页</a> > 
	<a href="${globalUrl}chushou" style="cursor: pointer;" target="_chushou">杭州二手房</a> >
	<a href="${globalUrl}chushou/a_${house.community.county.erpId}_" target="_chushou">${house.community.county.countyName }二手房 </a>> 
	<a href="${globalUrl }chushou/v_${house.community.erpId}" target="_chushou">${house.community.communityName}二手房</a>
</div>
<div class="zhxq_t">
    <div class="hou_tit_con">
      <div class="zh_dbt" id="titleValue" >${house.title}</div>
      <div class="dt_color">
				<c:forEach items="${house.houseTags}" var="tag">
					<span style="background-color:${tag.tagColor}; color:${tag.fontColor};">${tag.tagName}</span>
				</c:forEach>
      </div>
    </div>
    <div style="clear:both"></div>
    <div class="dt_pt">
      <div class="dt_pts hot-event" id="toppic">
        <div class="event-item dt_pts" style="display:block">
          <img src="${pictureHost}${house.coverImage ne null?house.coverImage:house.pictures[0].pictureUri }" class="photo" style="width: 100%;cursor: pointer;" alt="房源照片" />
        </div>
      </div>
      <div class="dt_pts" id="topmap" style="display: none;"></div>
        <div class="dt_bq">
          <div class="ac"><div class="a_jiao"><img id="frontPicImg" ischoosed = "true" class="frontPicImgClass" src="${globalUrl}image/tm_sj.png"/></div><div class="ac_t frontpic frontpicSelected"  id="frontpic" onclick="changePic();">照片</div></div>
          <div class="ac"><div class="a_jiao"><img id="frontMapImg" ischoosed = "false" src="${globalUrl}image/tm_sj.png"/></div><div class="ac_t frontmap" id="frontmap" onclick="changeMap();">地图</div></div>
          <div style="clear: both;"></div>
        </div>
      </div>
      <div id="midle_box">
				<div class="jia_pm">
					<div class="jpm_s">
						<div class="sjia">
							<span>售价：</span><b><fmt:formatNumber value="${house.price/10000}" /></b>（万元）
						</div>
						<div class="mianji">
							<span>面积：</span><b><fmt:formatNumber pattern="#" value="${house.area}" /></b>（平米）
						</div>
						<div class="danjia">
							<span>单价：</span><b><fmt:formatNumber pattern="#" value="${house.unitPrice}" /></b>（元/平米）
						</div>
					</div>
					<div class="jpm_x">
						<div class="sjiang">
							<span>价格浮动：</span>
							<c:if test="${house.previousUnitPrice - house.unitPrice < 0}">
								<span><fmt:formatNumber type="number" value="${-(house.previousUnitPrice - house.unitPrice)}" pattern="0.00" maxFractionDigits="2" /></span>
								<span class="j">元/平米</span>
							</c:if>
							<c:if test="${house.previousUnitPrice - house.unitPrice >= 0}">
								<span><fmt:formatNumber type="number" value="${(house.previousUnitPrice - house.unitPrice)}" pattern="0.00" maxFractionDigits="2" /></span>
								<span class="s">元/平米</span>
							</c:if>
						</div>
						<div class="daik">
							<span>贷款助手：</span> 
							<span style="font-weight: bold; color: #090;"><fmt:formatNumber type="number" value="${loanMonths / 12}" /></span><span> 年</span> 
							<span style="font-weight: bold; color: #090;"> ${loanRatios}</span><span>成商贷</span>
							<div style="clear: both;"></div>
							<b><fmt:formatNumber  value="${loanAssets }" pattern="#0.00"/></b><span>元/月</span>
							<a href="javascript:void(0);" onclick="openCalcDialog();">房贷计算器</a>
						</div>
					</div>
					<div class="yuyue"><a href="javascript:void(0);" onclick="daikan();">预约服务</a></div>
				</div>
				<div class="xs_k">
					<div class="sameCommunity" style="padding-top: 35px; padding-bottom: 11px;">
						<span>小区：</span>
						<div class="community_name" style="color: #09F;">${house.community.communityName}</div>
						<div class="same_community_link" id="ssxiaoqu">相似小区</div>
					</div>
					<div class="xs">
						<ul>
							<li><span>户型：</span><span>${house.shi}室${house.ting}厅${house.wei}卫</span><a id="sshuxing" href="javascript:void(0);">相似户型</a></li>
							<li>
								<span>楼层：</span>
								<span>${house.storeyCount}
									<c:if test="${house.houseVervicalLocation == 1}">层下部</c:if>
									<c:if test="${house.houseVervicalLocation == 2}">层中部</c:if>
									<c:if test="${house.houseVervicalLocation == 3}">层上部</c:if>
								</span>
								<a id="sslouceng" href="javascript:void(0);">相似楼层</a>
							</li>
							<li><span>朝向：</span><span>${house.orientation.orientationName}</span><a id="sschaoxiang" href="javascript:void(0);">相似朝向</a></li>
						</ul>
					</div>
					<div class="san_k">
						<div class="ewm">
							<img id="qrCode"
								src="${globalUrl}houseSecond.show?actionMethod=drawcode&id=${houseAppraise.erpId}&pictureType=alert&houseType=1&houseId=${house.houseId}&fromERP=${fromERP}&brokerId=${brokerId}"
								onclick="alertQrCode2()" style="float: right;" width="68"
								height="67" alt="" />
							<p>
								<a href="javascript:void(0);" onclick="alertQrCode2()">扫描二维码获取房源信息</a>
							</p>
						</div>
					</div>
				</div>
				<div class="dbfx" id="PcPadCompare">
					<div style="float: right; padding-right: 10px;">
						<c:if test="${fromERP eq null }">
							<a  id="collect${house.erpId}two" collId="${house.collectId}" class="gz" href="javascript:void(0);"  hosueobjectId=${house.erpId } houseprice="${house.price}" isCollect="1"  isCollect="1"  brokerId="${brokerId}" onclick="${LoginMember == null ? "loginBox();" : "isCollectInformation(this,0);"}">收藏</a>
						</c:if>
						<a class="jb" href="javascript:void(0);" onclick="shaminform();">虚假举报</a>
						<c:if test="${fromERP eq null }">
							<a href="javascript:void(0);" id="compareContainerContainer"
								housePictureUrl="${pictureHost}${house.coverImage }"
								inCompareItem="false"
								onclick="addCompareItem(this, 'secondHouseCompare')"
								compareId="${house.houseId }"
								brokerId="${(houseAppraise != null) ? houseAppraise.publisher.erpId : house.publisher.erpId}"
								compareTitle="${(houseAppraise != null) ? houseAppraise.title : house.title}"
								area="${house.area }" shi="${house.shi }" ting="${house.ting }"
								historyType="S" comparePrice="${house.price}"
								compareUnitPrice="${house.unitPrice}" class="dbi">对比</a>
						</c:if>
							<a	class="fx" data-cmd="more" href="javaScript:shareHouse();">分享</a> <span class="pl">
							<a href="#publishDiv">评价(${appraiseList.size()})</a> </span>
					</div>
					<div style="clear:both;"></div>
					<div id="sharePanel" style="height: 30px; float: right; display: none;width:130px;">
						<a onclick="shareInformation('tqq')" ><img src="${globalUrl}image/jjr_wb_ico.png"  alt="" /></a>
					   	<a onclick="shareInformation('tsina')"><img src="${globalUrl}image/jjr_sn_ico.png" alt="" /></a>
					   	<a onclick="shareInformation('trrw')"><img src="${globalUrl}image/jjr_rrw_ico.png" alt="" /></a>
					   	<a onclick="shareInformation('tdbw')"><img src="${globalUrl}image/jjr_dbw_ico.png" alt="" /></a>
					   	<input type="hidden" id="shopName" value="豪世华邦-二手房-${house.title}" />
					   	<input type="hidden" id="broderWebShop" value="${globalUrl}chushou/${house.houseId}.html" />
					</div>
				</div>
				<div class="dbfx" style="display: none" id="phoneCompare">
					<div class="bdsharebuttonbox sharePaddingRight" style="float: left; text-align: center;">
						<a style="width: 40px; height: 24px; line-height: 26px; margin: 0; background-image: url('null');" class="bds_more" data-cmd="more">分享</a>
					</div>
					<a style="float: left; width: 1px; padding: 0;">|</a> <a href="javascript:void(0);" onclick="shaminform();" style="float: right">虚假举报</a>
				</div>
			</div>
			<div class="man">
				<div class="r_bh">内部编号：${house.houseId}</div>
<c:if test="${house.publisher != null}">
				<div class="wd_lx">需要帮助请联系我</div>
				<div class="rwtp">
					<c:if test="${ house.publisher.photographPath == ''}">
						<img src="${globalUrl}images/broker/head.jpg" alt="" />
					</c:if>
					<c:if test="${notExistBrokerUri eq null }">
						<a href="${globalUrl}broker/${house.publisher.erpId}.html" title="查看${house.publisher.agentName}的资料和全部房源" target="_broker">
							<img src="${pictureHost}${house.publisher.photographPath}" alt="" />
						</a>
					</c:if>
					<c:if test="${notExistBrokerUri ne null }">
						<img src="${pictureHost}${house.publisher.photographPath}" alt="" />
					</c:if>
				</div>
</c:if>
				<div class="m_con">
					<div class="r_bh_c">内部编号：${house.houseId}</div>
<c:if test="${house.publisher != null}">
					<div class="wd_lx_c">需要帮助请联系我</div>
					<div class="name">${house.publisher.agentName}</div>
					<div class="ihp">${house.publisher.telephone}</div>
					<div class="qqwx">
						<c:if test="${house.publisher.qq != null && house.publisher.qq != ''}">
							<a href="tencent://message/?uin=${house.publisher.qq }&Site=qq&Menu=yes" onclick="qqcao(${house.publisher.qq })"><img src="${globalUrl}image/qq.gif" alt="" /></a>
						</c:if>
						<c:if test="${house.publisher.wechatUrl != null && house.publisher.wechatUrl != ''}">
							<a href="#"><img src="${globalUrl}image/wx.gif" onclick="wchatDisplay();" alt="" /></a>
						</c:if>
					</div>
</c:if>
				</div>
			</div>
		</div>
		<!-- 二维码放大隐藏 -->
		<div id="fangda" style="display: none;">
			<img id="qrCode2" src="${globalUrl}houseSecond.show?actionMethod=drawcode&id=${houseAppraise.erpId}&pictureType=alert&houseType=1&houseId=${house.houseId}" />
		</div>
		<div id="BrokerWexin" style="display: none;"><img src="${pictureHost }${house.publisher.wechatUrl}" /></div>
		<!--详情页头结束-->
		<!--内容开始-->
		<div class="nr">
			<div class="nr_tit" id="checkbardiv">
				<div class="tit_a" id="titleBar">
					<span onclick="arrowLeft();" style="display: none" class="myjt"></span>
					<div id="nvBar">
						<div id="barbar">
							<a id="details" onclick="goDiv('${globalUrl}', 'itemcontent1')" class="a_1 one">房源详情</a> 
							<a id="housePhoto" onclick="goDiv('${globalUrl}', 'itemcontent2')" class="a_2">室内照片</a>
							<a id="facilities" onclick="goDiv('${globalUrl}', 'itemcontent3')" class="a_3">生活配套</a> 
							<a id="photo" onclick="goDiv('${globalUrl}', 'itemcontent4')" class="a_4">小区相册</a>
						</div>
					</div>
					<span onclick="arrowRight();" class="myjtr" style="display: none"></span>
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
							<li>产权性质<p>${house.property}</p></li>
							<li>看房时间<p>${house.timeToSee}</p></li>
							<li>装修<p>${house.decoration.decorationName}</p></li>
							<li>使用状况<p>${house.usageStatus}</p></li>
						</ul>
					</div>
					<div class="nr_wd contentMore">
						<c:out value="${house.content }" escapeXml="false"></c:out>
					</div>
					<div class="nr_wd contentMore">
						<c:forEach items="${house.community.nearestStores}" var="nearStore" begin="0" end="0" varStatus="status">
			                       附近门店：${nearStore.storeName }&nbsp;&nbsp;&nbsp;&nbsp;
			                       地址：${nearStore.storeAddress}&nbsp;&nbsp;&nbsp;&nbsp;                                 
			                       电话：${nearStore.telephoneNo }
							<br/>
						</c:forEach>
					</div>
					<div class="shou" id="moreContent"><a onclick="clickContentMore();" class="jia">展开更多</a></div>
				</div>
			</div>
			<div class="nr_ar" id="itemcontent2">
				<div class="nr_zb zb_2">
					<div class="biao"></div>
					<div class="b_wd">室内照片</div>
				</div>
				<div class="nr_yb">
					<div class="nr_ptls">
						<p>室内照片共有${house.pictures.size()}张照片</p>
						<ul>
							<!--<li>
							 <a href="javascript:void(0);"><img src="${pictureHost}${house.coverImage}" alt="" onclick="clickCommImage(${status.index+1});" />
							</a> </li>-->
							<c:forEach items="${house.pictures}" var="pic" begin="0" varStatus="status">
								<li class="photoMore"><a href="javascript:void(0);"><img src="${pictureHost }${pic.pictureUri}" alt="" onclick="clickImage(${status.index+1});" /></a></li>
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
							<li>竣工时间<p><fmt:formatDate value="${house.community.buildYear}" pattern="yyyy.MM.dd" /></p></li>
							<li>开发商<p>${house.community.developer }</p></li>
							<li>绿化率<p>${house.community.landscapingRatio }%</p></li>
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
					<c:import url="/WEB-INF/jsp/hshb/front/commonInfoShow/common_streetview.jsp"/>
					<div class="shou" id="mapMore">
						<a onclick="clickMoreMap();" class="jia">展开更多</a>
					</div>
				</div>
			</div>
			
<c:set var="comm" value="${house.community}" />
			<div class="nr_ar" id="itemcontent4">
				<div class="nr_zb zb_4">
					<div class="biao"></div>
					<div class="b_wd">小区相册</div>
				</div>
				<div class="nr_yb">
					<div class="nr_xcls">
						<c:if test="${comm.pictures.size() <= 0}"><p>小区暂无照片</p></c:if>
						<c:if test="${comm.pictures.size() > 0}">
							<p>小区共有${comm.pictures.size()}张照片 <a href="<h:surl basePath="xiaoqu" communityId="${comm.erpId}" />.html" target="_xiaoqu">浏览小区详情</a></p>
							<ul>
								<c:set var="communityPics" value="${comm.pictures}" />
								<c:forEach items="${comm.pictures }" var="pic" varStatus="status">
									<li class="communityPhotoMore"><a href="javascript:void(0);"><img src="${pictureHost}${pic.pictureUri}" alt="" onclick="clickImage2(${status.index+1})" /></a></li>
								</c:forEach>
							</ul>
						</c:if>
					</div>
					<div class="shou" id="moreCommunityPhoto">
						<a onclick="clickCommunityPhotoMore();" class="jia">展开更多</a>
					</div>
				</div>
			</div>
       </div>
    	<!--内容结束-->
<c:if test="${appraiseList.size()>0}">
        <div class="guwen" id="publishDiv">
        	<div class="nr_tt"><span>共有 ${appraiseList.size()} 位置业人员服务于本房源</span><b>服务本房源的置业顾问：</b></div>
            <div class="gw_ls">
            	<ul>
	<c:forEach items="${appraiseList }" var="apps" >
               	<li>
               		<div class="gwls_t">
               		<c:if test="${apps.publisher ne null }">
               			<a href="${globalUrl }broker/${apps.publisher.erpId}.html" target="_broker">
               			
    					<c:if test="${h:isNotEmpty(apps.publisher.photographPath)}">
               				<img src="${pictureHost }${apps.publisher.photographPath}" alt="" width="99" height="130" border="0"/>
    					</c:if>
    					<c:if test="${h:isEmpty(apps.publisher.photographPath)}">
               				<img src="${globalUrl}images/broker/head.jpg" alt=""/>
    					</c:if>
               			</a>
               		</c:if>
               		<c:if test="${apps.publisher eq null }">
               			<img src="${globalUrl}images/broker/head.jpg" alt=""/>
               		</c:if>
               		</div>
                  <div class="gw_r">
                  	<c:if test="${apps.publisher ne null }">
                 	<div class="gwname">${apps.publisher.store.storeName} - ${apps.publisher.agentName}</div>
					<div class="gw_tel"><c:if test="${apps.publisher.telephone != ' ' && apps.publisher.telephone != null}"><b>${apps.publisher.telephone}</b></c:if>                     
                     <c:if test="${apps.publisher.qq != ' '  && apps.publisher.qq != null}"><span><a href="tencent://message/?uin=${apps.publisher.qq }&Site=qq&Menu=yes>" onclick="qqcao(${apps.publisher.qq })"><img src="${globalUrl}image/qq.gif" alt=""/></a></span></c:if>
                     <c:if test="${apps.publisher.wechatUrl != '' && apps.publisher.wechatUrl != null}"><span><img src="${globalUrl}image/wx.gif" onclick="showWeixin('${apps.publisher.erpId }');" alt=""/></span></c:if></div>
                     <%-- <div id="BWeixin" style="display: none;"><img src="${pictureHost }${apps.publisher.wechatUrl}"/></div> --%>
                     <c:if test="${apps.publisher.wechatUrl eq null }">
                     <div id="BWeixin_${apps.publisher.erpId }" style="display: none;"><img src="${pictureHost }${apps.publisher.wechatUrl}"/></div>
                     </c:if>
                     <div class="gw_tj"><b>推荐理由：</b> ${apps.title}</div>
                     <div class="gw_jj">${apps.content}</div>
                     <div class="gw_kf"><%-- 近 30 天带看量：<c:if test="${apps.publisher == null }">0</c:if>${apps.publisher.daikan} 次&nbsp;&nbsp;&nbsp;&nbsp; <span>持有房源：<c:if test="${apps.publisher == null }">0</c:if>${apps.publisher.houseCount} 套</span>--%></div>
                  	</c:if>
                  	<c:if test="${apps.publisher eq null }">
                 		<div class="gwname"> - ${apps.brokerName}</div>
                 		<div class="gw_tel"></div>
                 		 <div class="gw_tj"><b>推荐理由：</b> ${apps.title}</div>
                     	<div class="gw_jj">${apps.content}</div>
                     	<div class="gw_kf"><%-- 近 30 天带看量：<c:if test="${apps.publisher == null }">0</c:if>${apps.publisher.daikan} 次&nbsp;&nbsp;&nbsp;&nbsp; <span>持有房源：<c:if test="${apps.publisher == null }">0</c:if>${apps.publisher.houseCount} 套</span>--%></div>
                 	</c:if>
                  </div>
              	</li>
	</c:forEach>
              </ul>
          </div>
      </div>
</c:if>

    	<c:if test="${recommendList.size()>0}">
    	<!--推荐开始-->
        <div class="tjjl">
        	<div class="nr_tt"><b>您可能喜欢：</b></div>
            <div class="tjjl">
            	<ul>
            	<c:forEach var="houseR" items="${recommendList}" begin="0" end="2">
                	<li class="li_1 houseItem">
                    <div class="pt" onclick="window.open('${globalUrl}chushou/${houseR.houseId}.html')" >
                      <c:if test="${houseR.coverImage eq null}"><img src="${globalUrl}image/house_no_picture.png" width="226" height="170"/></c:if>
                      <c:if test="${houseR.coverImage ne null}"><img src="${pictureHost }${houseR.coverImage }"/></c:if>
                      <div class="pt_info">
                         <span style="float:left;">${h:limitStr(houseR.title, 10, "...")}</span>
                         <span style="float:right; margin-top:-5px;"><fmt:formatNumber value="${houseR.area}" pattern="##" />m<sup>2</sup></span>
                      </div>
                    </div>
                    <div class="pt_s">
                      <p>
                      <a href="${globalUrl }chushou/a_${houseR.community.county.erpId }_" target="_chushou">${houseR.community.county.countyName }</a> - 
                      <a href="${globalUrl }chushou/q${houseR.community.erpId }" target="_chushou">${houseR.community.communityName }</a></p>
                      <span>${houseR.shi}室${houseR.ting}厅${houseR.wei}卫</span>
                      <b><fmt:formatNumber value="${houseR.price/10000}" pattern="##" />万</b>
                    </div>
                    </li>
                </c:forEach>
                </ul>
            </div>
            <!-- <div class="shou"><a href="#" class="jia">展开更多</a></div> -->
        </div>
        </c:if>
        <div id="dialoginform" style="display: none;">
          <%@include file="/WEB-INF/jsp/hshb/front/company/company_shaminform_dialog.jsp" %>
					<div style="display: none;">
					<div id="containe">
						<div id="gallery" class="ad-gallery" style="padding: 30px; background: #e1eef5;">
							<div class="ad-image-wrapper"></div>
							<div class="ad-controls"></div>
							<div class="ad-nav">
								<div class="ad-thumbs">
									<ul class="ad-thumb-list" style="text-align: center;"> </ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
<!-- 照片放大隐藏框 -->
<c:set var="Pictures" value="${house.pictures}"></c:set>
<%@include file="/WEB-INF/jsp/hshb/front/commonInfoShow/common_slider.jsp"%>
<c:if test="${communityPics.size() >0 }">
<%@include file="/WEB-INF/jsp/hshb/front/commonInfoShow/common_slider2.jsp"%>
</c:if>
<%-- 
<div id="photoAlbumHead" class="tcc_zp lanrenzhijia" style="display:none;position: fixed;">
	<div class="zp_con">
		<img src="${globalUrl}image/xxx.png" style="width:auto;height:auto;right:5px;top:5px;position: absolute;cursor: pointer;" onclick="$('#photoAlbumHead').hide();$('#blackGround').hide();">
		<span id="prevTopHead" class="btn prev"></span> 
		<span id="nextTopHead" class="btn next"></span>
	  	<div id="picBoxHead" class="picBox">
	    	<ul id="picCenterHead" class="cf">
	    	<c:forEach var="pic" items="${house.pictures}" >
          <li>
          <a style="width:100%;height:100%;"><img src='${pictureHost}${pic.pictureUri}'/>
          	<span>${house.community.communityName },<fmt:formatNumber value="${house.area }" pattern="#">
          	</fmt:formatNumber>平米,${house.shi }室${house.ting }厅${house.wei }卫,
          	<fmt:formatNumber value="${house.price/10000 }" pattern="#" />万</span>
          </a>
          </li>
				</c:forEach>
	    	</ul>
		</div>
		<div id="listBoxHead" class="listBox zp_xt">
			<ul id="picListHead" class="cf">
			<c:forEach var="pic" items="${house.pictures}" >
				<li><i class='arr2'></i><a><img src='${pictureHost}${pic.pictureUri}' /></a></li>
			</c:forEach>
			</ul>
		</div>
	</div>
</div>
<div id="photoComm" class="tcc_zp lanrenzhijia" style="display:none;position: fixed;">
	<div class="zp_con">
		<img src="${globalUrl}image/xxx.png" style="width:auto;height:auto;right:5px;top:5px;position: absolute;cursor: pointer;" onclick="$('#photoComm').hide();$('#blackGround').hide();">
		<span id="prevTopComm" class="btn prev"></span> 
		<span id="nextTopComm" class="btn next"></span>
    <div id="picBoxComm" class="picBox">
      <ul id="picCenterComm" class="cf">
      <c:forEach items="${communityPics }" var="headpic"> 
        <li><a style="width:100%;height:100%;"><img src="${pictureHost }${headpic.pictureUri }" alt=""/></a></li>
      </c:forEach>
      </ul>
		</div>
		<div id="listBoxComm" class="listBox zp_xt">
			<ul id="picListComm" class="cf">
      <c:forEach items="${house.pictures }" var="headpic">
        <li><i class='arr2'></i><a><img src="${pictureHost }${headpic.pictureUri }" alt=""/></a></li>
      </c:forEach>
      </ul>
		</div>
	</div>
</div>
--%>
<script type="text/javascript">
$(document).ready(function(){

	//分享网址 start
	var webUrl = document.location.toString();
	//把最后的#符号给去掉
	var re = new RegExp("#", "g");
	webUrl = webUrl.replace(re, "");
	$("#broderWebShop").attr("value",webUrl);
	//ENd
})

</script>