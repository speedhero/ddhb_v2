<%@page import="com.huatek.hbwebsite.util.FrontSystemSettingUtil"%>
<%@page import="com.huatek.hbwebsite.util.SystemTitleUtil"%>
<%@page import="com.huatek.hbwebsite.util.CompanyInfo"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" src="${globalUrl}js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${globalUrl}js/select/jquery.selectReplace.js"></script>
<script type="text/javascript" src="${globalUrl}js/jquery.scrollTo.1.4.js"></script>
<script type="text/javascript" src="${globalUrl}js/automate/jquery.automate.plugin.js"></script>

<%--第三方通用js --%>
<%-- 下面的日期控件不能跨域 --%>
<script type="text/javascript" src="${globalUrl}js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${globalUrl}js/jquery.artDialog.source.js"></script>
<script type="text/javascript" src="${globalUrl}js/artDialog.iframeTools.source.js"></script>
<script type="text/javascript" src="${globalUrl}js/cookie/cookie.js"></script>

<!-- 价格走势图 -->
<script type="text/javascript" src="${globalUrl}js/jsCharts/excanvas.js"></script>
<!--[if IE]><script type="text/javascript" src="${globalUrl}js/jsCharts/excanvas.js"></script><![endif]-->
<script type="text/javascript" src="${globalUrl}js/jsCharts/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="${globalUrl}js/jsCharts/jqplot.highlighter.min.js"></script>
<script type="text/javascript" src="${globalUrl}js/jsCharts/jqplot.dateAxisRenderer.min.js"></script>

<%-- 
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.js"></script>
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.phone.js"></script>
--%>
<script type="text/javascript" src="${globalUrl}js/album.js" ></script>

<script type="text/javascript" src="${globalUrl}js/jquery.ad-gallery.js"></script>
<%--
<script type="text/javascript" src="${globalUrl}js/slickPlugin/slick.js"></script>
<script type="text/javascript" src="${globalUrl}js/slickPlugin/scripts.js"></script>
 --%>

<!-- 地图js引用 -->
<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
<script src="http://map.qq.com/api/js?v=2.exp&key=Z3YBZ-JVFHV-6W2P2-U653S-DCY22-KVFDG"></script>

<%--本项目js --%>
<script type="text/javascript" src="${globalUrl}js/ht_frame.js"></script>
<script type="text/javascript" src="${globalUrl}js/compare/compare.js"></script>
<script type="text/javascript" src="${globalUrl}js/compare/history.js"></script>
<script type="text/javascript" src="${globalUrl}js/hshb.common.js"></script>
<script type="text/javascript" src="${globalUrl}js/commonGround/box.js"></script>
<%--
<script type="text/javascript" src="${globalUrl}js/search/myMap_new.js"></script>
<script type="text/javascript" src="${globalUrl}js/housesecond/housesecond.js"></script>
--%>
<script type="text/javascript" src="${globalUrl}js/community/community_list.js"></script>

<script type="text/javascript" src="${globalUrl}js/housesecond/house_details.js"></script>
<script type="text/javascript" src="${globalUrl}js/detail/detail.js"></script>
<%-- <script type="text/javascript" src="${globalUrl}js/commonGround/chatTools.js"></script> --%>
<script type="text/javascript" src="${globalUrl}js/commonGround/box.js"></script>
<script type="text/javascript" src="${globalUrl}js/commonGround/notice.js"></script>

<script type="text/javascript" id="bdshare_js" data="type=tools" ></script>
<script type="text/javascript" id="bdshell_js"></script>

<script type="text/javascript">
var globalUrl = '${globalUrl}';
/* var globalUrl_ht = '${ht_globalUrl}/'; //后台全局路径*/
var cannot_connect_server = '不能连接到应用服务器，请检查网络或服务是否开启。';
var welcome='<huatek:urlDecode value="${welcome}"/>';
$(document).ready(function(){
  if(welcome!=''){
    getData(welcome,'','workspace');
  }
});
</script>
<script type="text/javascript">
$(document).ready(function(){
	if(document.getElementById("bdshell_js"))
		document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000);
});
</script>
<script type="text/javascript">
<%--
//构造搜索条件区功能
var _conditionJson = '${jsonString}';
var _baseSearchUrl = "";	//这是搜索基本URL，在每个页面里要单独设置
var searchURL = '${sharedUrl}';
var previousWidth = -1;

function createSearchArea( conditionJson, baseSearchUrl){
	if(conditionJson && conditionJson!="" && baseSearchUrl && baseSearchUrl!=""){
		var str = $.parseJSON(conditionJson);
		option={
			searchItems:str,
			//url:'${globalUrl}/rent.show?actionMethod=dimquery',
			url: baseSearchUrl,
			searchMap:searchMap,
			displayHiddenBar:false
		};
		if(previousWidth <1)
			previousWidth = getWinWidth();

		resizeSearchArea(option);

		$(window).resize(function(){
			resizeSearchArea(option);
 		});
	}
}

function getWinWidth(){
	var winWidth = 1024;
 	if (window.innerWidth){
 		winWidth = window.innerWidth;
 	}else if ((document.body) && (document.body.clientWidth)){
 		winWidth = document.body.clientWidth;
 	}
 	return winWidth;
}

function resizeSearchArea(option){
	var winWidth = 1024;
 	winWidth = getWinWidth();
	try{
		if (($("#searchMenuDiv").html()=="" && winWidth>=758) || (previousWidth < 758 && winWidth >= 758)){
			$("#searchMenuDiv").empty();
			$("#searchMenuDiv").createSearch(option);
		}
		if (previousWidth >= 758 && winWidth < 758){
			$("#searchMenuDiv").empty();
			$("#searchMenuDiv").createSearchForPhone(option);
		}
		if (previousWidth > 758 && winWidth <= 758){
			var autoMaticOption = { pageCapacity:1 };
			$("#top").startAutomate(autoMaticOption);
		}
	}catch(e){
		if(window.console) console.log(e);
	}
	previousWidth = winWidth;
}
--%>
</script>
<script type="text/javascript">
$(document).ready(function(){
	//初始化浏览历史框
	$("#historyDiv").click(function(){
		$("#compareContentContainer").css("display", "none");
		$("#historyListContainer").css("display", "block");
		$("#historyDiv").addClass("one");
		$("#compareMenu").removeClass("one");
	});
	
	//初始化房源比较框
	$("#compareMenu").click(function(){
		$("#compareContentContainer").css("display", "block");
		$("#historyListContainer").css("display", "none");
		$("#historyDiv").removeClass("one");
		$("#compareMenu").addClass("one");
	});
	try{
	compareIsOpen();
	}catch(e){
		log.console(e);
	}
});
</script>
<script type="text/javascript">
var backType = '${backType}';

//大图模式和列表模式切换
function switchShowType(flag){
	if (flag == "imgShape") {
		$("#checkbtn1").addClass("a_1a");
		$("#checkbtn2").removeClass("a_2a");
		$('#image_list').css("display", "block");
		$('#list_list').css("display", "none");
		setType0();
	} else {
		$("#checkbtn1").removeClass("a_1a");
		$("#checkbtn2").addClass("a_2a");
		$('#image_list').css("display", "none");
		$('#list_list').css("display", "block");
		setType1();
	}
}
function setType0() {
	setJSONValue('type', 0);
	_showType = 0;
}
function setType1() {
	setJSONValue("type", 1);
	_showType = 1;
}
/*初始化显示模式*/
function initShowType(){
	var flag = $.cookie("lastSelected");
	switchShowType(flag);
}

$(document).ready(function() {
	<%--
	$("#loginedUserInfoDiv").click(function() {
		if($("#loginedFunctionMenu").css("display")== "none" ){
			$("#loginUserInfoDiv").css({'background-image':'url(${globalUrl}images/homepage/login_clicked.png)'});
			$("#loginedFunctionMenu").slideToggle("slow");
		} else {
			$("#loginedFunctionMenu").slideToggle("slow");
			setTimeout(function changeUnclickImg(){$("#loginUserInfoDiv").css({'background-image':'url(${globalUrl}images/homepage/loginWhiteInput.png)'});},620);
		}
	});
--%>
	try{
		initShowType();	
	}catch(e){
		if(window.console) console.log(e);
	}
});

<%--
var timeout = 500;
var closetimer = 0;
var ddmenuitem = 0;

 * 以下几个函数有什么用尚不清楚
function jsddm_open() {
	jsddm_canceltimer();
	jsddm_close();
	ddmenuitem = $(this).find('ul').eq(0).css('visibility', 'visible');
}

function jsddm_close() {
	if (ddmenuitem)
		ddmenuitem.css('visibility', 'hidden');
}

function jsddm_timer() {
	closetimer = window.setTimeout(jsddm_close, timeout);
}

function jsddm_canceltimer() {
	if (closetimer) {
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}

$(document).ready(function() {
	$('#jsddm > li').bind('mouseover', jsddm_open);
	$('#jsddm > li').bind('mouseout', jsddm_timer);
});

function isLogined(){
	if($("#loginedUserInfoDiv").is(":visible") == true){
		return true;
	} else{
		window.location("${globalUrl}login.show?actionMethod=loginCheck")
		return false;
	}
}
function texthint() {
	var obj = document.getElementById('texthint');
	if (obj.value == _searchHintText) {
		obj.value = '';
		obj.style.color = '#000';
	}
}

--%>
</script>
<script type="text/javascript">
<%--
if(typeof(houseChangePages)=="undefined"){
/**
 * 翻页回调函数
 */
function houseChangePages(queryUrl, houseType) {
	$("#pageLabel").text(currvalue);
	var currvalue = document.getElementsByName("currentPage")[0].value;
	var isSearch = "${isSearch}";
	setJSONValue("currentPage", currvalue);
	if(typeof(isSearch)!="undefined" && isSearch == 1){
		showMySelectedField("${globalUrl}houseSearch.show?actionMethod=doSearch&houseType="+houseType+"&searchInput=${searchinput}&isCutPage=1&searchType=1&requesttype=1&currentPage=" + currvalue);
	}else {
		//showSelectedField('${globalUrl}community.show?actionMethod=dimquery');
		//showSelectedField(queryUrl);
		//postDataByURL2(queryUrl, null, "changelist");
		showSelectedField(queryUrl);
	}
}
}

if(typeof(nonHouseChangePages)=="undefined"){
/**
 * 非房源翻页功能
 */
function nonHouseChangePages(queryUrl, params, containerId) {
	var currvalue = document.getElementsByName("currentPage")[0].value;
	setJSONValue("currentPage", currvalue);
	params["currentPage"] = currvalue;
	for(var key in params){
		// 方法  
		if(typeof(params[key])=="function"){  
			//obj[p]();  
		}else{
			//把查询参数保存到searchMap
			setJSONValue(key, params[key]);
		}
  }
	//showSelectedField("${globalUrl}broker.show?actionMethod=dimquery");
	postDataByURL2(queryUrl, (params || null), (containerId || "changelist"));
}
}

if(typeof(showMySelectedField)=="undefined"){
/**
 * 
 * @param actionUrl
 */
function showMySelectedField(actionUrl){
	postDataByURL2(actionUrl, null, "changelist");
}
}
--%>
</script>

<script type="text/javascript">
$(document).ready(function(){
	if($("#checkbtn1")){
		$("#checkbtn1").click(function(){
			//saveCookies('imgShape');
			saveShowType('imgShape')
		}); 
	}
	
	if($("#checkbtn2")){ 
		$("#checkbtn2").click(function(){
			//saveCookies('dataShape');
			saveShowType('dataShape')
		}); 
	}
});

//收藏
function isCollectInformation(obj, collectType){
	var $clickCollect = $(obj);
	if($clickCollect.attr("isCollect")==0) {
	    //取消收藏
		keepOff($clickCollect.attr("collId"), $clickCollect.attr("id"), collectType);	
	} else {
	    //添加收藏
		keepOppen($clickCollect.attr("hosueobjectId"), $clickCollect.attr("houseprice"), $clickCollect.attr("id"), $clickCollect.attr("brokerId"), collectType);
	}
}

function keepOppen(searchno, priceCc, id, brokerId, collectType){
	var platCollection = {
			ObjectId: searchno,
			collectType: collectType,
			priceCc: priceCc,
			brokerId:brokerId
		};
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=addtoCollection";
	_keepOppen(actionUrl, platCollection, collectType, id );
}

//取消收藏
function keepOff(collectId , id, collectType){
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=cancleMyCollection&collectionId=" + collectId;
	_keepOff(actionUrl, id, collectType);
}
</script>

<%--
暂时禁用了QQ在线客服
<script type="text/javascript">
/* kfguin="4008966888";eid="218808P8z8p8x8R8R8z8q";ws="http://222.90.232.139:81/ddhb"; type="0";wpadomain="b"; */
${qqService}
</script>
<script type="text/javascript" src="${globalUrl}js/enterpriseQQ.js"></script>
 --%>
 