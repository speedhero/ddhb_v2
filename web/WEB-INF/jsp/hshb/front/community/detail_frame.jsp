<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="h" uri="/WEB-INF/tld/hshb.tld"%>
<!-- 分享按钮 -->
<script type="text/javascript" src="${globalUrl}js/homepage.js"></script>
<script type="text/javascript" src="${globalUrl}js/share.js"></script>
<script type="text/javascript" src="${globalUrl }/js/compare/communityCompare.js"></script>
<script type="text/javascript" src="${globalUrl}js/commonGround/chatTools.js"></script>
<style>
.ac{float:left; width:174px;height:50px;text-align: center; line-height: 38px;font-size:18px; margin-top:-10px; position: relative;}
.ac .ac_t{width:100%; height:38px; cursor: pointer;}
.ac .a_jiao{height:10px;}
.ac .a_jiao img{height:10px; width:20px; visibility: hidden;}
.frontpic{background: #E1E1E1 url(${globalUrl}image/zp_a.gif) no-repeat 34px 11px;border-left: 1px solid rgb(160, 160, 160);border-bottom: 1px solid rgb(160, 160, 160);}
.frontmap{background: #E1E1E1 url(${globalUrl}image/dt_a.gif) no-repeat 34px 11px;border-bottom: 1px solid rgb(160, 160, 160);border-left: 1px solid rgb(160, 160, 160); border-right: 1px solid rgb(160, 160, 160);}
.frontpicSelected{background: #FFFFFF url(${globalUrl}image/zp_b_xq.gif) no-repeat 34px 11px;color: #75BE40;}
.frontmapSelected{background: #FFFFFF url(${globalUrl}image/dt_b_xq.gif) no-repeat 34px 11px;color: #75BE40;}
.frontpicHover{background: #FFFFFF url(${globalUrl}image/zp_b_xq.gif) no-repeat 34px 11px;color: #75BE40;}
.frontmapHover{background: #FFFFFF url(${globalUrl}image/dt_b_xq.gif) no-repeat 34px 11px;color: #75BE40;}
.frontPicImgClass{display:inline;}
.frontMapImgClass{display:inline;}
.frontPicImgHoverClass{display:inline;}
.frontMapImgHoverClass{display:inline;}
.db_tit{background: rgb(117, 190, 64);}
.duibi{border-color: rgb(117, 190, 64);}
.db_tit a.one, .db_tit a:hover{border-top-color: rgb(117, 190, 64);}
.db_ls li.nob p a{color:rgb(117, 190, 64);}
.db_tit span a:hover{background: rgb(117, 190, 64);}
/* .wuye_k .an_db a:hover{color:rgb(117, 190, 64);} */
#phoneCompare{display:none;}
#PcPadCompare{display:block;}

.lb_lx .li_4 { padding: 0 20px; width: 176px; }
.borkerItem{width: 110px;height: 260px;background: #f3f3f3;margin: 0 8px 0 12px;float: left;padding: 5px 14px;color: #666;}
.borkerItem p {padding: 5px 0 10px;}
.borkerItem .rwtp {width: 100px;height: 130px;position: relative; margin:0 auto;}
.borkerItem .name {font-size: 18px;padding: 0;text-align: center;padding-top: 10px;}
.borkerItem .tel {font-size: 15px;font-family: arial;text-align: center;background: url(${globalUrl}image/iph.gif) no-repeat 1px;color: #f60;padding: 4px 0;}
.borkerItem .qqwx {text-align: center;padding: 5px 0 10px 0;}
.borkerItem .qqwx a {display: inline-block;}
.borkerItem .qqwx img {width: 19px;height: 19px;margin: 0 5px;}

.nr_tit .gz a:hover { color: rgb(117, 190, 64); }

.myjt{width:28px;float:left;height:28px;background: url('${globalUrl}image/tit_jt_xq.gif') no-repeat; display:block;margin-top:0;padding:0; cursor:pointer;}
.myjtr{width:28px;float:left;height:28px;background: url('${globalUrl}image/tit_jt_xq_right.gif') no-repeat; display:block;margin-top:0;padding:0; cursor:pointer;}
.selectedDian {
    background-image: url("${globalUrl}image/dian_b_xq.gif");
    background-position: center center;
    background-repeat: no-repeat;
}
#house_share{width:30px;}

</style>
<script type="text/javascript">
var comparedItemsArray;
var historyItemsArray;
var markers = [];
var jsonParams = {
	"" : ""
};
var houseType;
var communityId;
function setJsonParam(key, value) {
	jsonParams[key] = value;
}

initBaiDuShare();
function clearJsonParam() {
	jsonParams = {"" : ""};
	setJsonParam("communityId", communityId);
}

var tmp;
$(document).ready(function() {
	picCarousel();

	function getParam(pname) {
	   var params = location.search.substr(1); //  获取参数 平且去掉？
	   var arrParam = params.split('&');
	   var maoString = arrParam[arrParam.length-1];
	   var mao = maoString.split("=");
	   return mao[1];
	}
	 
	var mao = $("#" + getParam()); //获得锚点  
	if (mao != undefined) {//判断对象是否存在  
	   mao.click();
	}

	//initMapOnTheLeft();
	
	initQRCode("${globalUrl}");
	
	$(window).resize(function() {
		initQRCode("${globalUrl}");
	});
	
	//initJJAndMap();
   $(window).resize(function() {
		 if($("#photoAlbumHead").css("display") == "block") {
			 resizeAlertImage("photoAlbumHead");
	     }
		 if($("#photoAlbum").css("display") == "block") {
			 resizeAlertImage("photoAlbum");
		 }
   });
   
	//delCookie("secondHouseCompare");
	var comparedItemInCookie = '${communityCompare}';
	if (comparedItemInCookie == ''){
		comparedItemsArray=new Array();
	}else{
		comparedItemsArray = JSON.parse(comparedItemInCookie); 
	}
	initComparedItems(comparedItemsArray, "communityCompare");
	
	var historyObj = $("#compareContainerContainer").first();
  	//delCookie("communityHistory");
	var historyItemInCookie = '${communityHistory}';
	if (historyItemInCookie == ''){
		historyItemsArray = new Array();
	}else{
		historyItemsArray = JSON.parse(historyItemInCookie);
	}
	
	var currentPageUrl = window.location.href;
	var urlArray = currentPageUrl.split("?");
	var relativeUrl = currentPageUrl.substring(urlArray[0].lastIndexOf("/") + 1, currentPageUrl.length);
	
	addHistoryItem(historyObj, "communityHistory", relativeUrl);
	
	communityId = "${community.erpId}";
	setJsonParam("communityId", communityId);
	
	/**导航栏上浮**/
	//获取要定位元素距离浏览器顶部的距离
	var navH = $("#checkbardiv").offset().top;
	tmp = navH;
	//滚动条事件
	$(window).scroll(function(){
		//获取滚动条的滑动距离
		var scroH = $(this).scrollTop();
		var distant1 = $("#itemcontent1").offset().top-$("#titleBar").height() - 40;
		var distant2 = $("#itemcontent2").offset().top-$("#titleBar").height() - 40;
		var distant3 = $("#itemcontent3").offset().top-$("#titleBar").height() - 40;
		var distant4 = $("#itemcontent4").offset().top-$("#titleBar").height() - 40;
		//滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定
		if(scroH>=navH){
			$("#checkbardiv").css("position","fixed").css("top", 0).css("width",$(".nr").width());
		}else if(scroH<navH){
			$("#checkbardiv").css("position","relative");
		}
		
		if(scroH>=distant4){
			$("#titleBar").find(".one").removeClass("one");
			$("#photo").addClass("one");
		}
		else if(scroH>=distant3){
			$("#titleBar").find(".one").removeClass("one");
			$("#price").addClass("one");
		}else if(scroH>=distant2){
			$("#titleBar").find(".one").removeClass("one");
			$("#facilities").addClass("one");
		}
		else if(scroH>=navH){
			$("#titleBar").find(".one").removeClass("one");
			$("#details").addClass("one");
		}
	}); 
	 $("#zsShow").click(function(){
	    	$(this).css("display", "none");
	    	$(".jgzssj").css("display", "block");
	 });
	 
	 $("#_Ten_rightDiv").css("z-index", "999");
});

/**照片轮播**/
function pictureRotate(){
	$('.hot-event').nav({
		t:5000,	//轮播时间
		a:1000  //过渡时间
	});
}

/**初始化百度分享**/
function initBaiDuShare(){
	window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"1","bdMiniList":["qzone","tsina","weixin","tqq","sqq"],"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};
	with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
}
	
//初始化二维码
function initQRCode(globalUrl){
	var url = globalUrl + "/community.show?actionMethod=drawcode&id=${community.erpId}&pictureType=";
	_initQRCode(url);
}

function getMyData(actionUrl, dataString, resultArea, successMessage, callBack) {
	try {
		showProcess();
		hidenErrorMessage();
		hidenSuccessMessage();
		$.ajax({
			type : "POST",
			url : actionUrl,
			data : dataString,
			dataType : "html",
			success : function(data, statuText, responseObj) {
				hiddenProcess();
				if (this.url.indexOf('actionMethod=logout') > 0) {
					window.location.reload();
					return;
 				}
 				if (data.indexOf('loginForm') > 0) {
 					window.location.reload();
 					return;
 				}
 				$('#' + resultArea).html(data);
 				if (successMessage) {
 					showSuccessDirect(successMessage);
 				}
 				if (callBack) {
 					callBack(data, 1);
 				}
 				
 				var idStr = $.cookie("lastSelected");  
 		    	saveMyCookies(idStr);

 			},
 			error : function(msg) {
 				hiddenProcess();
 				if (msg.status == 3000 || msg.status == 500) {
 					if ($('#' + resultArea).html() == '正在加载......') {
 						$('#' + resultArea).html('页面加载失败');
 					}
 					showErrorDirect(msg.responseText);
 				} else if (msg.status == 0) {
 					showErrorDirect(cannot_connect_server);
 					if ($('#' + resultArea).html() == '正在加载......') {
 						$('#' + resultArea).html('页面加载失败');
 					}
 				} else {
 					$('#' + resultArea).html(msg.responseText);
 				}
 				if (callBack) {
 					callBack(msg, -1);
 				}
 			}
 		});
 	} catch (e) {
 		hiddenProcess();
 		alert('出错了！');
 	}
}

function changeSecondAndRent(housetype) {
	$("#titleBar").find(".one").removeClass("one");
	clearJsonParam();
	var navH = $("#checkbardiv").offset().top;
	$(window).unbind("scroll").bind("scroll",function(){
		//获取滚动条的滑动距离
		var scroH = $(this).scrollTop();
		//滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定
		if(scroH>=navH){
			$("#checkbardiv").css("position","fixed").css("top", 0).css("width",$(".nr").width());
		}else if(scroH<navH){
			$("#checkbardiv").css("position","relative");
		}
	});
 	$("#changePart1").hide();
 	$("#changePart2").hide();
 	$('#communityRentSecond').empty();
 	$("#communityRentSecond").css("display", "block");
 	setJsonParam("currentPage", 1);
 	setJsonParam("housetype", housetype);
 	getMyData('${globalUrl}xiaoqu/search/q_${community.inital}_${community.erpId}', jsonParams ,'communityRentSecond');
 	//getMyData('${globalUrl}community.show?actionMethod=communityhouse', jsonParams ,'communityRentSecond');
 	//var a = $("#communityRentSecond").offset().top;
 	var position = $("#checkbardiv").css("position");
	var scroll_offset;
	if(position != "fixed") {
		scroll_offset = $("#communityRentSecond").offset().top - $("#checkbardiv").height() - 20;
	} else {
		scroll_offset = $("#communityRentSecond").offset().top - $("#checkbardiv").height();
	}
	$("html,body").animate({scrollTop:scroll_offset}, 'slow');
	//$("html,body").animate({scrollTop:a}, 'slow');
	if(housetype == 1){
		$("#titleBar").children().removeClass("one");
		$("#houseSecondBar").addClass("one");
		
	}else{
		$("#titleBar").children().removeClass("one");
		$("#houseRentBar").addClass("one");
	}
}

function saveMyCookies(StringId) {
	if (StringId == "dataShape") {
		$("#checkbtn2").addClass("a_2a");
		$("#checkbtn1").removeClass("a_1a");
		$("#checkbtn1").removeAttr("style").css("cursor", "pointer"); 
		//$('#list_list').css("display", "block");
		$('#image_list').css("display", "none");
		setType1();
	} else {
		$("#checkbtn1").addClass("a_1a");
		$("#checkbtn2").removeClass("a_2a");
		$("#checkbtn2").removeAttr("style").css("cursor", "pointer");
		$('#image_list').css("display", "block");
		//$('#list_list').css("display", "none");
		setType0();
	}
	
	$.cookie('lastSelected', StringId, { expires : 365 }, { path : "/" });// 存储 cookie , expires: 设定时间天数参数， path：设置cookies存储路径参数
}

function moveClick(obj){
	var $move =$(obj).parent();
	if($move.find('.color_wd').is(":visible") == false) {
		//显示
		$move.find('.color_wd').slideToggle( "slow");
		animateOn($move);
		$move.find(".jiant_s").find("a").css("background-image","url('image/jiant_x.png')");
		$move.find(".an_xq").css("display","block");
	}else {
		//取消
    $move.stop(true,true);
    $move.find('.color_wd').stop(true, true);
		animateOff($move);
		$move.find(".jiant_s").find("a").css("background-image","url('image/jiant_s.png')");
		$move.find(".an_xq").css("display","none");
		$move.find('.color_wd').slideToggle( "slow");
	}
}
function animateOn(param) {
	param.animate({
   	 /* top:"80px" */
   	 top:"0px"
	});
}
function animateOff(param) {
	param.animate({
   	 /* top:"80px" */
   	 top:"137px"
	});
}
//转换到小区二手房页面
function changeSecond(communitySecond) {
	$('#communityRentSecond').empty();
	getData('${globalUrl}community.show?actionMethod=communityhouse&communityId=${community.erpId}&housetype=1','','communityRentSecond');
	$("#communityRentSecond").css("display", "block");
	$("#communityRentSecond1").css("display", "none");
	var a = $("#communityRentSecond").offset().top;
	$("html,body").animate({scrollTop:a}, 'slow');
	$("#changePart1").css("display", "none");
	$("#changePart2").css("display", "none");
}
<%--
function nonHouseChangePages() {
	var currvalue = document.getElementsByName("currentPage")[0].value;
	setJsonParam("currentPage", currvalue);
	//postDataByURL2("${globalUrl}houseSearch.show?actionMethod=doSearch&searchInput=${searchinput}&houseType=1&isPage=1&currentPage=" + currvalue, "", "changelist");
	getMyData('${globalUrl}community.show?actionMethod=communityhouse', jsonParams ,'communityRentSecond');
}
--%>
function clickMorePhoto(){
	$(".clickMorePhoto").removeClass("clickMorePhoto");
	$("#photoMore").hide();
}
function clickMapMore(){
	$(".moreMap").css("display", "block");
	$("#mapMore").css("display", "none");
}
function clickMoreContent(){
	$(".moreContent").removeClass("moreContent");
	$("#contentMore").hide();
}
    
function changeRelativeSelect(selecctedValue) {
	var sortvalue = $("#sortmodule").children('option:selected').attr("sort");
	var ordervalue = $("#sortmodule").children('option:selected').attr("order");
  sort = sortvalue;
  order = ordervalue;
  setJsonParam("sort", sortvalue);
	setJsonParam("order", ordervalue);
	postDataByURL2('${globalUrl}community.show?actionMethod=dimdetailquery' , jsonParams, "changelist");
}

//页面宽度变化时重新计算轮播点
$(window).resize(function(){
	picCarousel();
});

function picCarousel(){
	var autoMaticOption = {pageCapacity : 3};
	var winWidth = 1024;
	if (window.innerWidth){
		winWidth = window.innerWidth;
	}else if ((document.body) && (document.body.clientWidth)){
		winWidth = document.body.clientWidth;
	}
	if(winWidth > 470 && winWidth <= 758){
		$("#brokerlist").startAutomate(autoMaticOption);  
	}else if(winWidth <= 470){
		autoMaticOption = {pageCapacity : 2};
		$("#brokerlist").startAutomate(autoMaticOption);  
	}else if(winWidth > 758){
		autoMaticOption = {pageCapacity : 5};
		$("#brokerlist").startAutomate(autoMaticOption);  
	}
}

function arrowLeft(){
	var Obj = $("#barbar");
	var marginLeft = $(Obj).css("margin-left");
	var containerWidthStr = $(Obj).css("width");
	var containerWidth = parseInt(containerWidthStr.replace("px"),"");
	var leftCount = parseInt(marginLeft.replace("px"),"");
	var showLeft = 0;
	if (leftCount - 70 + containerWidth > 0){
		showLeft = leftCount - 70;
	}else{
		showLeft = containerWidth - 280;
	}
	var ShowLeftStr = showLeft + "px";
	$("#barbar").animate({"margin-left":ShowLeftStr}, 'slow'); 
}

function arrowRight(){
	var Obj = $("#barbar");
	var marginLeft = $(Obj).css("margin-left");
	var containerWidthStr = $(Obj).css("width");
	var containerWidth = parseInt(containerWidthStr.replace("px"),"");
	var leftCount = parseInt(marginLeft.replace("px"),"");
	var showLeft = 40;
	if (leftCount < 70){
		showLeft = 0;
	}else{
		showLeft = containerWidth +70;
	}
	var ShowLeftStr = showLeft + "px";
	$("#barbar").animate({"margin-left":ShowLeftStr}, 'slow'); 
}

function isCollectCommunity(obj){
	var $clickCollect = $(obj);
	if($clickCollect.attr("isCollect")==0) {
	//取消收藏
		keepOff($clickCollect.attr("id"),$clickCollect.attr("collId"));	
	} else {
	//添加收藏
		keepOppen($clickCollect.attr("id"),$clickCollect.attr("collId"),2);
	}	
}

//收藏
function keepOppen(id,objId,type){
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=addCommunitytoCollection";
	var platCollection = {
			ObjectId: objIc,
			collectType: type,
		};
	_keepOppen(actionUrl, platCollection, '3', id);	
}
//取消收藏
function keepOff(id,objId){
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=cancleMyCommunityCollection&collectionId=" + objId;
	_keepOff(actionUrl, id, 3);
}

function reloadCommunity(){
	reloadCompareDiv("communityCompare","${globalUrl}");
	reloadHistoryDiv("communityHistory","${globalUrl}",$("#loginStatus").val());
}

function loginBox(target, housetype){
	var url = "${globalUrl}login.show?actionMethod=loginCheck&target="+target;
	_openDialog('login', "&nbsp;&nbsp;登录", url, "get", "html");
}
function showWeixin(broker_id){
	var tagName = "BWeixin_" + broker_id;
	_showWeixin(tagName, 'BWeixin', '评价人微信');
}


//小区地理坐标
var _location = "${community.location}";

//分享
function shareHouse(){
	$("#sharePanel").css("display", "block");
}
</script>

<!--头部结束-->
<div class="Location">
	<a onclick='window.location.href="${globalUrl}"'>首页</a> > 
	<a onclick='window.location.href="${globalUrl}xiaoqu"'>杭州小区</a> > 
	<a>${community.communityName }</a>
</div>

<!--详情页头开始-->
<div class="zhxq_t">
	<input type="hidden" id="loginStatus" value="<c:if test="${LoginMember == null }">no</c:if>" />
	<div class="hou_tit_con">
	    <div class="zh_dbt">${community.communityName }</div>
	</div>
    <div class="dt_pt">
    	<div class="dt_pts hot-event" id="toppic">
<%-- 这里显示所有图片还是 就封面
		<c:forEach items="${houseHeadPics }" var="headpic" varStatus="status">
				<div class="event-item dt_pts"  <c:if test="${status.index == 0}">style="display:block"</c:if> <c:if test="${status.index != 0}">style="display:none"</c:if>>
					<img src="${pictureHost}${headpic.picUrl }" onclick="clickImageHead(${status.index+1});" class="photo" style="width: 100%;cursor: pointer;" alt="室内图片" />
				</div>
			</c:forEach>
 --%>
 			
 				<c:set var="_coverImage" value="${community.coverImage}" />
 			<c:if test="${community.coverImage eq null }">
 				<c:set var="_coverImage" value="${globalUrl}image/house_no_picture.png" />
 			</c:if>
 			<img src="${pictureHost}${_coverImage}" onclick="clickImageHead(${status.index+1});" class="photo" style="width: 100%;cursor: pointer;" alt="室内图片" />
		</div>
		<div class="dt_pts" id="topmap" style="display: none;"></div>
        <div class="dt_bq">
        	<div class="ac"><div class="a_jiao"><img id="frontPicImg" ischoosed = "true" class="frontPicImgClass" src="${globalUrl}image/tm_sj.png"/></div><div class="ac_t frontpic frontpicSelected"  id="frontpic" onclick="changePic();">照片</div></div>
          <div class="ac"><div class="a_jiao"><img id="frontMapImg" ischoosed = "false" src="${globalUrl}image/tm_sj.png"/></div><div class="ac_t frontmap" id="frontmap" onclick="changeMap();">地图</div></div>
          <div style="clear: both;"></div>
        </div>
    </div>
    <div id="midle_box">
	  <div class="qian_k">
    	<div class="q_s">
        	<div class="junj"><p>本月均价</p><b><fmt:formatNumber value="${community.currentMonthShAvgPrice }" pattern="##"/></b><p>（元/平）</p></div>
            <div class="q_sx">
            	<c:if test="${community.huanbishangyuerh ne 0 }">
                <p>90天内变化：</p>
								<p>
                	<c:if test="${community.huanbishangyuerh eq 0}">
                		<span class="s">${community.huanbishangyuerh }%</span>
               		</c:if>
               		<c:if test="${community.huanbishangyuerh gt 0}">
               			<span class="s">${community.huanbishangyuerh }%</span>
            		</c:if>
            		<c:if test="${community.huanbishangyuerh lt 0}">
            			<span class="x"><c:out value="${fn:substring(community.huanbishangyuerh, 1, 5 )}"></c:out>%</span>
           			</c:if>
							</p>
							</c:if>
            </div>
        </div>
        <div class="q_sl">
            <p>二手房：<span>${community.shCount }</span>套房源</p>
            <p>出租房：<span>${community.rentCount }</span>套房源</p>
        </div>
        <div class="q_dz">片区商圈：${community.county.countyName } - ${community.cbdWebsite.name }</div>
				<div class="q_dz">小区地址：${community.communityAddress }</div>
      </div>
		<div class="wuye_k">
			<div class="line_k">物业类型：${community.commonUsage.usageName }</div>
			<div class="line_k">建成年代：<fmt:formatDate value="${community.buildYear }" pattern="yyyy" /></div>
			<%--<div class="line_k">物业公司：${community.propertyManagement }</div> --%>
			<div class="line_k">开发商：${community.developer }</div>
		</div>
		<div class="dbfx" id="PcPadCompare">
			<div style="float: right; padding-right: 10px;">
				<a class="gz"<c:if test="${LoginMember == null }"> onclick="loginBox();"</c:if><c:if test="${LoginMember != null }"> onclick="_keepOppen('${community.erpId }', '2')"</c:if>>收藏</a>
				<a class="dbi" id="compareContainerContainer" historyType="C"
					housePictureUrl="${pictureHost}${community.coverImage }"
					inCompareItem="false" class="compareButton"
					onclick="reloadCommunity();addCompareToDiv(this, 'C','${community.erpId }');"
					compareId="${community.erpId }"
					compareTitle="${community.communityName }"
					area="${community.communityArea }"
					comparePrice="${community.currentMonthShAvgPrice}">对比</a> <a class="fx"
					data-cmd="more" href="javaScript:shareHouse();">分享</a>
			</div>
			<div style="clear:both;"></div>
			<div id="sharePanel"
				style="height: 30px; float: right; display: none;width:130px;">
				<a onclick="shareInformation('tqq')" ><img src="${globalUrl}image/jjr_wb_ico.png"  alt="" /></a>
			   	<a onclick="shareInformation('tsina')"><img src="${globalUrl}image/jjr_sn_ico.png" alt="" /></a>
			   	<a onclick="shareInformation('trrw')"><img src="${globalUrl}image/jjr_rrw_ico.png" alt="" /></a>
			   	<a onclick="shareInformation('tdbw')"><img src="${globalUrl}image/jjr_dbw_ico.png" alt="" /></a>			  
			   	<input type="hidden" id="shopName" value="豪世华邦-小区-${community.communityName}" />
			   	<input type="hidden" id="broderWebShop" value="${globalUrl}xiaoqu/${community.erpId }.html" />
			</div>
		</div>
	</div>
	<div class="ewm_k">
    	<div class="top_bm">小区编号：<c:out value="${community.communityNo}"></c:out></div>
    	<div class="ewm_pt" style="overflow: hidden;"><img id="qrCode" src="" style="float:right; 	width:auto; height:auto; margin-right:3px;"/></div>
    	<div class="bm_yc">小区编号：<c:out value="${community.communityNo}"></c:out></div>
    	<div class="ewm_wd">扫描二维码获取小区信息</div>
    </div>
</div>
<div class="nr">
	<div class="nr_tit" id="checkbardiv">
        <div class="tit_a"  id="titleBar">
        	<span onclick="arrowLeft()" style="display:none" class="myjt"></span>
        	<div id="nvBar">
			    <div id="barbar">
	        		<a onclick="goDiv('${globalUrl}', 'itemcontent1', this)" class="a_1 ${(communitySearchHouseRent eq null && communitySearchHouseSecond eq null )?'':'one' }" id="details" >小区详情</a>
	        		<a onclick="goDiv('${globalUrl}','itemcontent2', this)" class="a_2" id="facilities">生活配套</a>
	        		<a onclick="goDiv('${globalUrl}', 'itemcontent3', this)" class="a_3"  id="price">价格行情</a>
	        		<a onclick="goDiv('${globalUrl}','itemcontent4', this)" class="a_4" id="photo">小区相册</a>
	        		<a id="houseSecondBar" onclick='changeSecondAndRent("1");' href="#communityRentSecond" class="${communitySearchHouseSecond eq null ?'':'one' }">二手房源</a>
	        		<a id="houseRentBar"   onclick='changeSecondAndRent("2");' href="#communityRentSecond" class="${communitySearchHouseRent eq null ?'':'one' }">出租房源</a>
	       		</div>
       		</div>
       		<span onclick="arrowRight()" style="display:none" class="myjtr"></span>
       	</div>
    </div>
    
    <c:set var="cssStyle" value="${communitySearchHouseSecond eq null ?'none':'block' }"/>
    <c:if test="${cssStyle eq 'none' }">
    	<c:set var="cssStyle" value="${communitySearchHouseRent eq null ?'none':'block' }"/>
    </c:if>
    <div  id="communityRentSecond" style="display: ${cssStyle}">
    <c:if test="${communitySearchHouseRent ne null }">
    	<%@include file="/WEB-INF/jsp/hshb/front/community/community_rent_search_condition.jsp"%>
    </c:if>
    <c:if test="${communitySearchHouseSecond ne null }">
    	<%@include file="/WEB-INF/jsp/hshb/front/community/community_houseSecond_search_condition.jsp"%>
    </c:if>
    </div>

	<div id="changePart1">
		<div class="nr_ar" id="itemcontent1">
			<div class="nr_zb zb_1">
				<div class="biao"></div>
				<div class="b_wd">小区详情</div>
			</div>
			<div class="nr_yb">
				<div class="d_x">
					<ul>
						<li>小区名称<p>${community.communityName}</p></li>
						<li>开发商<p>${community.developer}</p></li>
						<li>建成年代<p><fmt:formatDate value="${community.buildYear}" pattern="yyyy" /></p></li>
						<li>建筑面积<p>${community.communityArea}</p></li>
						<li>占地面积<p>0</p></li>
						<li>容积率<p>${community.plotRatio}</p></li>
						<%--<li>物业公司<p>${community.propertyManagement}</p></li>  --%>
						 <li>住宅类型<p>${community.commonUsage.usageName}</p></li>
						<li>物业费（元）<p>${community.propertyExpense}</p></li>
						<li>绿化率<p>0</p></li>
						<li>车位<p>${community.parkingCount}</p></li>
					</ul>
				</div>
				<div class="nr_wd moreContent">${community.introduction }</div>
				<div class="nr_wd moreContent">
	 			<c:forEach items="${community.nearestStores}" var="nearStore" begin="0" end="0" varStatus="status">
                                 最近门店：${nearStore.storeName }&nbsp;&nbsp;&nbsp;&nbsp;
                                 地址：${nearStore.storeAddress}&nbsp;&nbsp;&nbsp;&nbsp;                                 
                                 电话：${nearStore.telephoneNo }
						<br />
					</c:forEach>
					
				</div>
				<div class="shou" id="contentMore">
					<a onclick="clickMoreContent();" class="jia">展开更多</a>
				</div>
			</div>
		</div>

		<div class="nr_ar" id="itemcontent2">
			<div class="nr_zb zb_3">
				<div class="biao"></div>
				<div class="b_wd">生活配套</div>
			</div>

			<div class="nr_yb">
				<div class="d_x">
					<ul>
						<li>小区名称<p>${community.communityName }</p></li>
						<li>竣工时间<p><fmt:formatDate value="${community.buildYear }" pattern="yyyy.MM.dd" /></p></li>
						<li>开发商<p>${community.developer }</p></li>
						<li>绿化率<p>0</p></li>
					</ul>
				</div>
				<div class="d_x">
					<ul>
						<li>交通
							<p>
							<c:forEach items="${communityStationMappings }" var="cstms">${cstms.station.stationName },</c:forEach>
							<c:forEach items="${cSubMapping }" var="csubm" varStatus="stat">
								<c:if test="${!stat.last}">${csubm.subwayStation.stationName },</c:if>
								<c:if test="${stat.last}">${csubm.subwayStation.stationName }</c:if>
							</c:forEach>
							</p>
						</li>
					</ul>
				</div>
<%@include file="/WEB-INF/jsp/ddhb/front/commonInfoShow/common_streetview.jsp"%>
				</div>
				<div class="shou" id="mapMore">
					<a onclick="clickMapMore();" class="jia">展开更多</a>
				</div>
			</div>
		</div>

		<div class="nr_ar" id="itemcontent3">
			<div class="nr_zb zb_5">
				<div class="biao"></div>
				<div class="b_wd">价格行情</div>
			</div>
			<div class="nr_yb">
				<%@include file="/WEB-INF/jsp/hshb/front/community/community_chart.jsp"%>

				<!-- <div class="shou"><a href="#" class="jia">展开更多</a></div> -->
				<div class="shou">
					<a class="jia" id="zsShow">展开更多</a>
				</div>
			</div>
		</div>

		<div class="nr_ar" id="itemcontent4">
			<div class="nr_zb zb_4">
				<div class="biao"></div>
				<div class="b_wd">房源详情</div>
			</div>
			<div class="nr_yb">
				<div class="nr_xcls">
					<p>小区照片共有 ${community.pictures.size()} 张照片 <a onclick="clickImage(1);return false;">浏览全部照片</a></p>
					<ul>
						<c:forEach items="${community.pictures }" var="headpic" begin="0" end="0" varStatus="status">
							<li><a><img src="${pictureHost}${headpic.pictureUri }" alt="" onclick="clickImage(${status.index+1});" /></a></li>
						</c:forEach>

						<c:forEach items="${community.pictures }" var="headpic" begin="1" varStatus="status">
							<li class="clickMorePhoto"><a><img src="${pictureHost}${headpic.pictureUri }" alt="" onclick="clickImage(${status.index+1});" /></a></li>
						</c:forEach>
					</ul>
				</div>
				<div class="shou" id="photoMore">
					<a onclick="clickMorePhoto();" class="jia">展开更多</a>
				</div>
			</div>
		</div>
	</div>
</div>

<!--内容结束-->
<div id="changePart2">

<!--顾问开始-->
<div class="guwen">
	<div class="nr_tt"><span>共有 ${community.expertList.size() } 位置业人员服务于本房源</span><b>小区专家</b></div>
	<div id="brokerlist" class="xqzj_ls">
		<div class="block" style="width:10000px;">
			<c:forEach items="${community.expertList }" var="expert">
				<c:if test="${expert.broker ne null}">
				<div class="item borkerItem">
					<p>需要帮助请联系我</p>
					<div class="rwtp"><a ><c:if test="${expert.broker.photographPath == ''}"><img src="${globalUrl}images/broker/head.jpg" style="width: 135px; height: 180px;" onclick="window.open('${globalUrl}broker/${expert.broker.erpId}.html')" /></c:if><c:if test="${expert.broker.photographPath != ''}"><img src="${pictureHost }${expert.broker.photographPath}" onclick="window.open('${globalUrl}broker/${expert.broker.erpId}.html')"/></c:if></a></div>
					<div class="name"><c:if test="${expert.broker.agentName eq null}">&nbsp;</c:if><c:if test="${expert.broker.agentName eq ''}">&nbsp;</c:if><c:out value="${expert.broker.agentName}"></c:out></div>
					<div class="tel"><c:if test="${expert.broker.agentName eq null}">暂无</c:if><c:if test="${expert.broker.agentName eq ''}">暂无</c:if><c:out value="${expert.broker.telephone}"></c:out></div>
					<div class="qqwx">
					<c:if test="${expert.broker.qq != null}"><a href='tencent://message/?uin=${expert.broker.qq }&Site=qq&Menu=yes' onclick="qqcao('${expert.broker.qq }')"><img src="${globalUrl}/image/qq.gif" alt=""/></a>
					</c:if>
					<a href="javascript:void(0);"><c:if test="${not empty expert.broker.wechatUrl}"><img src="${globalUrl}/image/wx.gif" onclick="showWeixin('${expert.broker.erpId}');" alt=""/></c:if></a>
					</div>
					<c:if test="${expert.broker ne null}">
						 <c:if test="${expert.broker.wechatUrl ne null}">
							<div id="BWeixin_${expert.broker.erpId }" style="display: none;"><img src="${pictureHost }${expert.broker.wechatUrl}"/></div>
						</c:if>
					</c:if>
				</div>
				</c:if>
			</c:forEach>
			<div style="clear: both;"></div>
		</div>
	</div>
</div>

<!--顾问结束-->

<!--推荐开始-->

<div class="tjjl">
	<div class="nr_tt"><b>附近小区：</b></div>
	<!--图片列表开始-->
		<div class="xqpc_ls">
			<ul>
				<c:forEach var="_community" items="${recommandedCommunity}" end="3">
					<li>
						<div class="pt">
							<a href="${globalUrl}xiaoqu/${_community.erpId}.html" target="_blank">
								<img src="${_community.coverImage eq null? globalUrl.concat('/image/house_no_picture.png') : pictureHost.concat(_community.coverImage)}" width="226" height="170">
							</a>
							<p>共有${_community.picCount==null ? 0 : _community.picCount}张照片</p>
						</div>
						<div class="tj_txt">
							<div class="name_a">
								<a href="${globalUrl}xiaoqu/${_community.erpId}.html"><c:out value="${_community.communityName}"></c:out></a>
							</div>
							<div class="pm_s">
									<b style="line-height: 30px;"><fmt:formatNumber value="${_community.currentMonthShAvgPrice }" pattern="#"></fmt:formatNumber></b>
									M<sup>2</sup>(均价)
							</div>
							<div class="pm">
								<span>${_community.countyName}- ${_community.websiteName}</span>
								<p>建成年代：<fmt:formatDate value="${_community.buildYear}" pattern="yyyy" /></p>
							</div>
							<div class="pm_sx">
								<span style="width: 50%; float: left;">二手房数量：${_community.shCount}</span>
								<span style="width: 50%; float: left;">出租房数量：${_community.shCount}</span>
							</div>
							<div class="an_xq">
								<a onclick="window.location.href='${globalUrl}xiaoqu/${_community.erpId}.html'" class="xq">查看详情</a>
								<c:if test="${LoginMember == null }">
									<a href="javascript:void(0);" collId="" onclick="loginBox();">收藏</a>
								</c:if>
								<c:if test="${LoginMember != null }">
									<%-- <c:if test="${community.collectId != null}">
										<a id="collect${community.erpId}" style="background-color: #cb4f4d" collId="${community.erpId}" isCollect="0" onclick="isCollectCommunity(this);">收藏</a>
									</c:if>
									<c:if test="${community.collectId == null}">
										<a id="collect${community.erpId}" collId="${community.erpId}" isCollect="1" onclick="isCollectCommunity(this);">收藏</a>
									</c:if>
									--%>
									<a id="collect${_community.erpId}" collId="${_community.erpId}" isCollect="1" onclick="isCollectCommunity(this);">收藏</a>
								</c:if>
								<a housePictureUrl="${pictureHost}${_community.coverImage }"
									inCompareItem="false" class="compareButton phoneCompare"
									onclick="$('a[compareId=${_community.erpId }]').addClass('addCompare');addCompareItem(this, 'communityCompare','${_community.erpId }');"
									compareId="${_community.erpId }"
									compareTitle="${_community.communityName }"
									area="${_community.communityArea }"
									comparePrice="${_community.currentMonthShAvgPrice}">对比</a>
							</div>
							<div style="clear:both;"></div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		<!--图片列表结束-->
	<!-- <div class="shou"><a href="#" class="jia">展开更多</a></div> -->
</div>

<!--推荐结束-->
</div>
<!-- 照片放大隐藏框 -->
<c:set var="Pictures" value="${community.pictures}"></c:set>
<%@include file="/WEB-INF/jsp/hshb/front/commonInfoShow/common_slider.jsp"%>
<%-- 
<div id="photoAlbumHead" class="tcc_zp lanrenzhijia" style="display: none; position: fixed;">
	<div class="zp_con">
		<img src="${globalUrl }/image/xxx.png" style="width: auto; height: auto; right: 0; top: 0; position: absolute; cursor: pointer;" onclick="$('#photoAlbumHead').hide();$('#blackGround').hide();">
		<span id="prevTopHead" class="btn prev"></span> 
		<span id="nextTopHead" class="btn next"></span>
		<div id="picBoxHead" class="picBox">
			<ul id="picCenter" class="cf">
				<c:forEach var="pic" items="${houseHeadPics}">
					<li>
						<a style="width: 100%;">
						<img src='${pictureHost}${pic.picUrl}' />
						<span>${community.communityName },
						<fmt:formatNumber value="0" pattern="#"></fmt:formatNumber>平米,
						<fmt:formatNumber value="${community.currentMonthShAvgPrice}" pattern="#"></fmt:formatNumber>元/平米
						</span>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div id="listBoxHead" class="listBox zp_xt">
			<ul id="picListHead" class="cf">
				<c:forEach var="pic" items="${houseHeadPics}">
					<li><i class='arr2'></i><a><img src='${pictureHost}${pic.picUrl}' /></a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
--%>
<script type="text/javascript">
	$(document).ready(function(){
		//分享网址 START
		var webUrl = document.location.toString();
		//把最后的#符号给去掉
		var re = new RegExp("#", "g");
		webUrl = webUrl.replace(re, "");
		$("#broderWebShop").attr("value",webUrl);
		//END
	});
</script>
