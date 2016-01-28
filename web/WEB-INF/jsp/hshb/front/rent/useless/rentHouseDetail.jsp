<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<link type="text/css" rel="stylesheet" href="${globalUrl}js/skins/gray.css">
<link href="${globalUrl}css/detailspage.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="${globalUrl}css/communityDetail.css" />
<link type="text/css" rel="stylesheet" href="${globalUrl}css/jquery.ad-gallery.css">
<script type="text/javascript" src="${globalUrl}js/jquery.nav.js"></script>
<script type="text/javascript" src="${globalUrl}js/jquery.artDialog.source.js"></script>
<script src="http://map.qq.com/api/js?v=2.exp&key=Z3YBZ-JVFHV-6W2P2-U653S-DCY22-KVFDG"></script>
<script src="${globalUrl}js/jquery.ad-gallery.js"></script>
<script type="text/javascript" src="${globalUrl}js/jsCharts/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="${globalUrl}js/jsCharts/jqplot.highlighter.min.js"></script>
<script type="text/javascript" src="${globalUrl}js/jsCharts/jqplot.dateAxisRenderer.min.js"></script>
<link type="text/css" rel="stylesheet" href="${globalUrl}css/jquery.jqplot.min.css" />
<!-- http://www.cnblogs.com/jiji262/archive/2012/05/15/2501792.html -->
<script type="text/javascript" src="${globalUrl}js/cookie/cookie.js"></script>
<script type="text/javascript" src="${globalUrl}js/compare/compare.js"></script>
<script type="text/javascript" src="${globalUrl}js/compare/history.js"></script>
<script type="text/javascript" src="${globalUrl}js/detail/detail.js"></script>
<script type="text/javascript" src="${globalUrl}js/commonGround/notice.js"></script>
<script type="text/javascript">
//$(document).ready(function(){
var searchService,map1,markers = [];
$(document).ready(function(){
  //相似房源 
  var idStr = $.cookie("lastSelected");
  if(idStr == "imgShape") {
	    $("#sshuxing").attr("onclick", 'window.open("${globalUrl}rent.show?actionMethod=dimquery&type=0&ispage=0&ddhb_one_shi=${house.shi}")');
	    $("#sslouceng").attr("onclick", 'window.open("${globalUrl}rent.show?actionMethod=dimquery&type=0&ispage=0&ddhb_two_storyCount=${house.storyCount - 3}@${house.storyCount + 3}&ddhb_one_vervicalLocation=${house.vervicalLocation}")');
	    $("#sschaoxiang").attr("onclick", 'window.open("${globalUrl}rent.show?actionMethod=dimquery&type=0&ispage=0&ddhb_one_orientations.id=${house.orientations.id}")');
	    $("#ssxiaoqu").attr("onclick", 'window.open("${globalUrl}rent.show?actionMethod=dimquery&type=0&ispage=0&ddhb_one_community.id=${house.community.id}")');
  } else {
	    $("#sshuxing").attr("onclick", 'window.open("${globalUrl}rent.show?actionMethod=dimquery&type=1&ispage=0&ddhb_one_shi=${house.shi}")');
	    $("#sslouceng").attr("onclick", 'window.open("${globalUrl}rent.show?actionMethod=dimquery&type=1&ispage=0&ddhb_two_storyCount=${house.storyCount - 3}@${house.storyCount + 3}&ddhb_one_vervicalLocation=${house.vervicalLocation}")');
	    $("#sschaoxiang").attr("onclick", 'window.open("${globalUrl}rent.show?actionMethod=dimquery&type=1&ispage=0&ddhb_one_orientations.id=${house.orientations.id}")');
	    $("#ssxiaoqu").attr("onclick", 'window.open("${globalUrl}rent.show?actionMethod=dimquery&type=1&ispage=0&ddhb_one_community.id=${house.community.id}")');
  }
  var arr = "${house.location}";
  var arrayLocation = arr.split(",");
    var center = new qq.maps.LatLng(arrayLocation[0],arrayLocation[1]);
    map1 = new qq.maps.Map(document.getElementById('communitydetaildivmappic'),{
        center: center,
        zoom: 13
    });
    var latlngBounds = new qq.maps.LatLngBounds();
    searchService = new qq.maps.SearchService({
        complete : function(results){
            var pois = results.detail.pois;
            $("#trafficeDiv").empty();
            if(pois.length == 0){
              $("<span style='font-size:12px; margin-top:20px;'>暂时没有数据</span>").appendTo("#trafficeDiv");
            }else {
              for(var i = 0; i < pois.length; i++){
                var min = Math.floor((pois[i].dist)/100);
                if(i < pois.length-1){
                  $('<div style="width: 100%;">'+
                    '<div style="width:63%; float: left; text-align: center; border-bottom:1px dashed #D0D0D0; border-right: 1px solid #E3E4E5;">'+
              '<span>'+pois[i].name +"&nbsp;&nbsp;"+ pois[i].address+'</span>'+
              '</div>'+
              '<div style="width:36%;float: left; text-align: center; border-bottom:1px dashed #D0D0D0;">'+
              '<span>步行'+pois[i].dist+'米，步行约'+min+'分钟'+'</span>'+
              '</div>'+
              '<div style="clean:both;"></div>' +
               '</div>'+
               '<div style="clear:both;"></div>'
            ).appendTo('#trafficeDiv');
                }else{
                  $('<div style="width: 100%;">'+
                    '<div style="width:63%; float: left; text-align: center;border-right: 1px solid #E3E4E5;">'+
                  '<span>'+pois[i].name +"&nbsp;&nbsp;"+ pois[i].address+'</span>'+
                  '</div>'+
                  '<div style="width:36%;float: left; text-align: center;">'+
                  '<span>步行'+pois[i].dist+'米，步行约'+min+'分钟'+'</span>'+
                  '</div>'+
                  '<div style="clean:both;"></div>' +
                   '</div>'+
                   '<div style="clear:both;"></div>'
                ).appendTo('#trafficeDiv');
                }
              }
            }
            for(var i = 0,l = pois.length;i < l; i++){
                var poi = pois[i];
                latlngBounds.extend(poi.latLng);  
                var marker = new qq.maps.Marker({
                    map:map1,
                    position: poi.latLng
                });
                marker.setTitle(poi.name);
                markers.push(marker);
            }
            map1.fitBounds(latlngBounds);
        }
    });
searchKeyword("交通",5,'traffic');
$(".around").css("background-color", "red");
}
);

function searchKeyword(keyword, number, divId) {
  var arr = "${house.location}";
  var arrayLocation = arr.split(",");
  
  $('#searchHeader > div').removeClass('grayBack');
  $('#'+divId).addClass('grayBack');
    region = new qq.maps.LatLng(arrayLocation[0], arrayLocation[1]);
    searchService.setPageCapacity(number);
    searchService.searchNearBy(keyword, region, 1500);
}
function addBack(divId){
  $('#'+divId).addClass('testQ');
}

function removeBack(divId){
  $('#'+divId).removeClass('testQ');
} 
$(function(){
  //获取要定位元素距离浏览器顶部的距离
  var navH = $(".fu-dong").offset().top;
  
  //滚动条事件
  $(window).scroll(function(){
    
    //获取滚动条的滑动距离
    var scroH = $(this).scrollTop();
    
    //滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定
    if(scroH>=navH){
      $(".fu-dong").css({"position":"fixed","top":0});
    }else if(scroH<navH){
      $(".fu-dong").css({"position":"static"});
    }
  })
})
//})
function getBg(num){
    for (var id = 0; id <=4 ; id++) {
        if (id==num) {

            document.getElementById("mynav"+id).className="f-navon";
        }
        else{

            document.getElementById("mynav"+id).className="";
        }
    };
        
}
// 价格走势图 
function initCharts(size){
//价格走势图 
//利用JSTL动态定义多维数组
var arrayz = [
<c:forEach var="trend" items="${trendList}" varStatus="s">
  ["'" + "${trend.dateAndMonth}".substring(0,7) + "'", ${trend.unitPrice}]${s.last?'':','}
</c:forEach>
]
var plot2 = $.jqplot('priceChart', [arrayz], {
      title:'价格走势图',
      axes:{
        xaxis:{
          numberTicks:size,
          pad: 1,
          showTicks: true,
          tickInterval: '1 month',
          renderer:$.jqplot.DateAxisRenderer,
            tickOptions:{
              formatString:'%Y/%m'
            }
        },
        yaxis:{
          tickOptions:{
            formatString:'￥%.2f'
          }
        }
      },
      highlighter: {
          show: true,
          sizeAdjust: 10
      }
});//
}
$(document).ready(function init() {
  var comparedItemInCookie = '${rentHouseCompare}';
  if (comparedItemInCookie == ''){
    comparedItemsArray=new Array();
  }else{
    comparedItemsArray = JSON.parse(comparedItemInCookie); 
  }
    initComparedItems(comparedItemsArray, "rentHouseCompare");

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
  addHistoryItem(historyObj, "houseRentHistory", relativeUrl);

  //走势图
  var size = "${trendList.size()}" ;
  if(size == 0){
    $('#priceChart').text("暂时没有历史走势图!");
  } else {
    initCharts(size);
  }

  var arr = "${house.location}";
  var arrayLocation = arr.split(",");
  var center = new qq.maps.LatLng(arrayLocation[0], arrayLocation[1]);
  var map = new qq.maps.Map(document.getElementById("rent_house_map"), {
      // 地图的中心地理坐标。
      center: center,
      zoom: 13,
  });
  var marker = new qq.maps.Marker({
      position: center,
      map: map
  });
  var pano = new qq.maps.Panorama(document.getElementById('communitydetaildivmaparound'), {
    pano: '10051001111220105028000',
    disableMove: false,
    disableFullScreen: false,
    zoom:1,
    pov:{
      heading:20,
      pitch:15
    }
  });
    $('#rent_house_pic').css("display", "none");
    
    //*****record 5 view histories in Cookie*****//
    //see jQueryCookie : https://github.com/carhartl/jquery-cookie
    
    var houseid = $("#houseid").attr("title");
    var houseRentNo = "${house.hourseNo}";
  var temp = "";
  if($.cookie('houseRentHistory') == undefined || $.cookie('houseRentHistory') == "undefined"){
    //if cookie does not contain the key,init cookie
    $.cookie('houseRentHistory', houseRentNo);
  } else {
    var arrays = $.cookie('houseRentHistory').split(",");
    if(arrays.length < 5){
      for (var i = 0; i < arrays.length; i++) {
        if(arrays[i] != ""){
          temp += arrays[i] + ",";
        }
      }
      if(jQuery.inArray(houseRentNo, arrays) == -1){
        temp = temp + houseRentNo;
      }
    } else {
      //not contain, push and shift
      if(jQuery.inArray(houseRentNo, arrays) == -1){
        arrays.push(houseRentNo);//append to the end
        arrays.shift();//remove the first one
      }
      temp = arrays;
    }
    $.cookie('houseRentHistory', temp);//set cookie
  }
  //alert("Cookie : " + $.cookie('ctm'));
});

function getJson(name, value) {
  var json = {
    name:value
  }
  return json;
}

function changePicture(StringId){
  if (StringId == "rent_house_map") {
    $('#rent_house_map').css("display", "block");
    $('#rent_house_pic').css("display", "none");
  } else {
    $('#rent_house_pic').css("display", "block");
    $('#rent_house_map').css("display", "none");
  }
}
//预约服务
function daikan(){
	var actionUrl = "${globalUrl}houseSecond.show?actionMethod=bespeak&brokerId=${houseAppraise.broker.id}&housetype=2&houseId=${house.id}";
	var id = 'daikan';
	var title = "预约服务";
	//art.dialog()里的 width: 600,height: 500,没有设
	_services(actionurl, id, title, null, "get");
};

function shaminform(){
  var dataContent = document.getElementById("dialoginform");
  var id = 'shaminformid';
  var title = "虚假举报";
  _operations(dataContent, id, title);
}
//收藏租赁
function keepOppen(){
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=addtoCollection";
	var searchno = $("#searchno").attr("title");
	  var collectType = 1;
	  var priceCc = $("#shoujia").attr("title");
	  var platCollection = {
	    ObjectId: searchno,
	    collectType: collectType,
	    priceCc: priceCc
	  };
	  _keepOppen(actionUrl, platCollection, collectType );
}

function changePic(StringId){
  if (StringId == "communitydetaildivmappic") {
    $('#communitydetaildivmappic').css("display", "block");
    $('#communitydetaildivmaparound').css("display", "none");
    $(".streetview").css("background-color", "#D7D7D7");
    $(".around").css("background-color", "red");
  } else {
    $('#communitydetaildivmaparound').css("display", "block");
    $('#communitydetaildivmappic').css("display", "none");
    $(".streetview").css("background-color", "red");
    $(".around").css("background-color", "#D7D7D7");
  }
}

//降价通知
function addReduceNotice(searchno) {
	 $('#reducenoticelink').attr("href", "javascropt:void(0)");
	  var reduceNotice = {
	    searchNo : searchno,
	    houseType : 2,
	  }
	  var actionUrl = "${globalUrl}notice.show?actionMethod=addNotice";
	  var type = "post";
	  var herfUrl = "${globalUrl}notice.show?actionMethod=inputInfoPage&searchno=${houseAppraise.searchno }";
	  _reduceNotice(actionUrl, type, reduceNotice, herfUrl);
}

//取消降价通知
function cancelReduceNotice(searchno) {
	$('#reducenoticelink').attr("href", "javascropt:void(0)");
	  var reduceNotice = {
	    searchNo : searchno,
	  }
	  var actionUrl = "${globalUrl}notice.show?actionMethod=cancelNotice";
	  var type = "post";
	  var herfUrl = "${globalUrl}notice.show?actionMethod=inputInfoPage&searchno=${houseAppraise.searchno }";
	  _reduceNotice(actionUrl, type, reduceNotice, herfUrl, "取消");
}

</script>
<script type="text/javascript">
function clickImage(){
  $("#gallery").remove();
  var htmlTest = "<div id='gallery' class='ad-gallery' style='background-color:#CCCCCC;'><div class='ad-image-wrapper'></div><div class='ad-controls'></div><div class='ad-nav'><div class='ad-thumbs'><ul class='ad-thumb-list' style='text-align: center;'></ul></div></div></div>";
  $("#containe").append(htmlTest);
  var contextHtml = "";
  var num = ${housePics.size()};
  for (var i = 0; i < num; i++){
    contextHtml += "<li style='display:inline-block;'><a href='${housePics.get(i).picUrl}"
    contextHtml += "'><img style='width:60px; height:60px;' src='${housePics.get(i).picUrl}"
    contextHtml += "' title='' alt='' class='image1'></a></li>";
  }
  $(".ad-thumb-list").append(contextHtml);
  var containerObj = document.getElementById("containe");
  $(".aui_content").css("background-color", "#000000");
  art.dialog({
    title: "室内照片",
    lock: true,
    content: containerObj,
    width:500,
    height:500,
    zIndex: 99999
    close: function(){
      $(".ad-preloads").remove();
      $(".ad-preloads").remove();
      $(".aui_content").css("background-color", "#F6F6F6");
    }
  });
  $('.ad-gallery').adGallery();
}

function clickImage2(){
  $("#gallery").remove();
  var htmlTest = "<div id='gallery' class='ad-gallery' style='background-color:#CCCCCC;'><div class='ad-image-wrapper'></div><div class='ad-controls'></div><div class='ad-nav'><div class='ad-thumbs'><ul class='ad-thumb-list' style='text-align: center;'></ul></div></div></div>";
  $("#containe").append(htmlTest);
  var contextHtml = "";
  var num = ${communityPics.size()};
  for (var i = 0; i < num; i++){
    contextHtml += "<li style='display:inline-block;'><a href='${communityPics.get(i).picUrl}"
    contextHtml += "'><img style='width:60px; height:60px;' src='${communityPics.get(i).picUrl}"
    contextHtml += "' title='' alt='' class='image1'></a></li>";
  }
  $(".ad-thumb-list").append(contextHtml);
  var containerObj = document.getElementById("containe");
  $(".aui_content").css("background-color", "#000000");
  art.dialog({
    title: "室内照片",
    lock: true,
    content: containerObj,
    width:500,
    height:500,
    zIndex: 99999
    close: function(){
      $(".ad-preloads").remove();
      $(".ad-preloads").remove();
      $(".aui_content").css("background-color", "#F6F6F6");
    }
  });
  $('.ad-gallery').adGallery();
}
</script>
<script type="text/javascript">
$(document).ready(function(){
  $('.hot-event').nav({
    t:5000,//轮播时间
    a:1000  //过渡时间
  });
});
</script>
<style>
.fu-dong{ z-index:99; }
.grayBack{ background-color: gray; }
.testQ{ background-color: gray; }
</style>
<div id="container">
  <div id="head">
    <p style="text-align: left">
      <span class="font" style="font-size: 13px; color: #0000ff;">首页>杭州二手房>${house.community.region.countyName }二手房>${house.community.communityName }二手房</span>
    </p>
    <p style="text-align: left">
      <span class="font" style="font-size: 28px;">${house.title}</span>
    </p>
    <p style="text-align: left">
      <span class="font" style="font-size: 13px;">${house.content}</span>
    </p>
  </div>
<div id="header">
  <div id="header01">
    <div id="h01">
      <div id="h011">
        <div id="rent_house_map"></div>
        <div id="rent_house_pic" class="hot-event">
<c:forEach items="${houseHeadPics }" var="headpic">
          <div class="event-item" style="display: block;">
            <a href="#"><img src="${headpic.picUrl }" class="photo" style="width: 321px; height: 236px;" alt="室内图片" /></a>
          </div>
</c:forEach>
          <div class="switch-tab">
            <a href="javascript:void(0);" onclick="return false;" class="current">1</a> 
            <a href="javascript:void(0);" onclick="return false;">2</a> 
            <a href="javascript:void(0);" onclick="return false;">3</a> 
            <a href="javascript:void(0);" onclick="return false;">4</a> 
          </div>
        </div>
      </div>
      <div id="h012" onclick='changePicture("rent_house_pic");' style="cursor:pointer;">
        <p style="text-align: center"><span class="font" style="font-size: 13px;">照片</span></p>
      </div>
      <div id="h013" onclick='changePicture("rent_house_map");' style="cursor:pointer;">
        <p style="text-align: center"><span class="font" style="font-size: 13px;">地图</span></p>
      </div>
    </div>
    <div id="h02">
      <p>
<c:forEach items="${tagList}" var="tags">
  <c:forEach items="${house.tagIdList}" var="tagId">
        <c:if test="${tags.id == tagId}"><a style="border: 1px; text-align: center; font-size: 13px;">${tags.tagName}</a></c:if>
  </c:forEach>
</c:forEach>
      </p>
    </div>
  </div>
<div id="header02">
  <div id="shoujia" title="${house.rentPrice }">
    <p style="text-align: left"><span class="font">月租金（元/月）</span></p>
    <p style="text-align: left"><span class="font" style="font-size: 72px;"><fmt:formatNumber value="${house.rentPrice }" pattern="0"/></span></p>
  </div>
  <div id="danjia01">
    <p style="text-align: left"><span class="font">付款方式：${house.payforWay }</span></p>
    <p style="text-align: left"><span class="font">租赁形式：${house.rentType.rentTypeName }</span></p>
  </div>
  <div id="bijia">
    <div id="bijia01" style="float: left">
      <p style="text-align: left">
        <span class="font"><img src="${globalUrl}images/img/u72_normal.png"></span>
        <span class="font" style="color: #ff0000;">1.4%</span>
      </p>
      <p style="text-align: left; line-height: 0.8;"><span class="font" style="font-size: 10px;">最近30天单价浮动</span></p>
    </div>
    <div id="bijia02" style="float: left; position: relative; left: 9px;">
      <p style="text-align: left">
        <span class="font"><img src="${globalUrl}images/img/u76_normal.png"></span>
        <span class="font" style="color: #0000ff;">2.4%</span>
      </p>
      <p style="text-align: left; line-height: 0.8;"><span class="font" style="font-size: 10px;">所属小区均价参照</span></p>
    </div>
  </div>
  <div id="bijia03">
    <p style="text-align: center">
<c:if test="${LoginMember == null }">
      <a id="reducenoticelink" href='${globalUrl}notice.show?actionMethod=inputInfoPage&searchno=${houseAppraise.searchno }&houseType=2' style="font-size: 10px; color: #ffffff; cursor: pointer;">降价通知</a>
</c:if>
<c:if test="${LoginMember != null }">
  <c:if test='${noticeResult == "noticed" }'>
      <a href='javascript:cancelReduceNotice("${houseAppraise.searchno }");' style="font-size: 10px; color: #ffffff; cursor: pointer;">取消降价通知</a>
  </c:if>
  <c:if test='${noticeResult == "unnoticed" }'>
      <a href='javascript:addReduceNotice("${houseAppraise.searchno }");' style="font-size: 10px; color: #ffffff; cursor: pointer;">降价通知</a>
  </c:if>
</c:if>
    </p>
  </div>
  <div id="mianji">
    <p style="text-align: left"><span class="font">面积（平米）</span></p>
    <p style="text-align: left">约 <span class="font" style="font-size: 36px;">${house.community.region.countyName}</span></p>
  </div>
  <div id="daikuan" style="padding-top: 5px;">
    <p>
<c:forEach items="${furList}" var="furs">
      <c:if test="${furs.flag eq true}"><img alt="" src="${globalUrl}${furs.imgUrl}"></c:if>
      <c:if test="${furs.flag eq false }"><img alt="" src="${globalUrl}${furs.imgDisUrl}"></c:if>
</c:forEach>
    </p>
  </div>
</div>
<div id="header03">
  <div id="huxing">
    <p style="text-align: left"><span class="font">居室</span></p>
    <p style="text-align: left">
      <span class="font"><font1>${house.shi}</font1> <font2>室</font2> <font1>${house.ting}</font1><font2>厅</font2> <font1>${house.wei}</font1> <font2>卫</font2></span>
    </p>
  </div>
  <div id="shuxing">
    <span id="sshuxing"><p style="line-height: 2"><a style="font-size: 10px; color: #000000;" href="javascript:void(0)">相似居室</a></p></span>
  </div>
  <div class="louceng">
    <p style="text-align: left"><span class="font">楼层</span></p>
    <p style="text-align: left"><span class="font" style="font-size: 13px;">${house.storey}</span></p>
  </div>
<div class="slouceng">
  <span id="sslouceng">
  <p style="line-height: 2;"><a href="javascript:void(0)" style="font-size: 10px; color: #000000;">相似楼层</a></p>
</div>
<div class="louceng">
  <p style="text-align: left"><span class="font">朝向</span></p>
  <p style="text-align: left; line-height: 1;"><span class="font" style="font-size: 13px;">${house.orientations.orientationName}</span></p>
</div>
<div class="slouceng">
  <span id="sschaoxiang">
    <p style="position: relative; top: 20px; line-height: 2;"><a href="javascript:void(0)" style="font-size: 10px; color: #000000;">相似朝向</a></p>
  </span>
</div>
<div class="louceng">
  <p style="text-align: left"><span class="font">所属小区</span></p>
  <p style="text-align: left; line-height: 1;"><span class="font" style="font-size: 13px;">${house.community.communityName}</span></p>
</div>
<div class="slouceng">
  <span id="ssxiaoqu">
    <p style="position: relative; top: 20px; line-height: 2;"><a href="javascript:void(0)" style="font-size: 10px; color: #000000;">相同小区</a></p>
  </span>
</div>
  <div id="daikan">
    <div>
      <p style="padding-bottom: 1px;"><span class="font" style="font-size: 13px;">带看量</span></p>
      <p style="padding-top: 1px;"><span class="font" style="font-size: 20px;"></span>${house.daikan }
    </div>
    <div style="margin-left: -7px;">
      <p style="padding-bottom: 1px;"><span class="font" style="font-size: 13px;">浏览量</span></p>
      <p style="padding-top: 1px;"><span class="font" style="font-size: 20px;">${houseBrowsed }</span>
    </div>
    <div style="margin-left: -7px;">
      <p style="padding-bottom: 1px;"><span class="font" style="font-size: 13px;">评价</span></p>
      <p style="padding-top: 1px;"><span class="font" style="font-size: 20px;">${house.appCount }</span>
    </div>
  </div>
  <div class="dkan" onclick="daikan();" style="cursor:pointer;">
    <p style="text-align: center; padding-top: 4px;"><span class="font" style="font-size: 28px; color: #ffffff;">预约服务</span></p>
  </div>
  <div class="fenxiang">
    <div style="cursor:pointer;">
      <p><a id="compareContainerContainer" style="font-size: 10px; color: #000000;">对比</a></p>
    </div>
    <div style="cursor:pointer;" class="jiathis_style">
      <p><a class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis"  target="_blank" style="font-size: 10px; color: #000000;background-image: url('')">分享</a></p>
    </div>
    <div style="cursor:pointer;">
      <p><a onclick="shaminform();" style="font-size: 10px; color: #000000;">虚假举报</a></p>
    </div>
  </div>
</div>
<div id="header04">
  <div id="broker">
    <div id="borkerDiv" style="cursor: pointer;" onclick="window.open('${globalUrl}broker.show?actionMethod=brokerDetail&brokerId=${houseAppraise.broker.id}&housetype=2')">
      <div id="borkerInfo">
        <div class="brokerTitle">需要帮助请联系我</div>
        <div class="brokerPic"><img alt="" src="${globalUrl}${houseAppraise.broker.photograph}"></div>
        <div class="brokerName">
          <div class="brokerInfo">${houseAppraise.broker.store.storeName} - ${houseAppraise.broker.store.telephoneNo}</div>
          <div class="brokerInfo">${houseAppraise.broker.bname}</div>
          <div class="brokerInfo">${houseAppraise.broker.telephone }</div>
          <div class="brokerInfoImage"><img alt="" src="${globalUrl}images/img/u67_normal.png"><img alt="" src="${globalUrl}images/img/u69_normal.png"></div>
        </div>
      </div>
    </div>
    <div class="houseInfo">
      <div><img alt="123" src="${globalUrl}houseSecond.show?actionMethod=drawcode&id=${houseAppraise.id}"/></div>
      <div class="houseInfoDetil">
        <div class="InfoDe" style="border-bottom: 1px solid gray; line-height: 1.2;">
          <div class="queryNum">查询编号</div>
          <div id="searchno" class="queryNum" title="${houseAppraise.searchno }">${houseAppraise.searchno }</div>
        </div>
        <div class="InfoDe" style="line-height: 1.2;">
          <div class="queryNum">内部编号</div>
          <div class="queryNum"> ${house.hourseNo } </div>
        </div>
      </div>
    </div>
    <div class="scann" style="padding-top: 382px;">
      <p><span class="font" style="font-size: 13px;">扫描二维码获取房源信息</span></p>
    </div>
  </div>
</div>
</div>
<div id="nav">
  <div class="fu-dong" style="border-bottom: 1px solid #C8C6C6;">
    <div id="mynav0" onclick="javascript:getBg(0);window.location.href='#aa'" class="f-navon"><a>01 房源详情</a></div>
    <div id="mynav1" onclick="javascript:getBg(1);window.location.href='#bb'" class=""><a>02 室内照片</a></div>
    <div id="mynav2" onclick="javascript:getBg(2);window.location.href='#cc'" class=""><a>03 生活配套</a></div>
    <div id="mynav3" onclick="javascript:getBg(3);window.location.href='#dd'" class=""><a>04 小区相册</a></div>
    <div id="mynav4" onclick="javascript:getBg(4);window.location.href='#ee'" class=""><a>05 价格走势</a></div>
    <div class="w-max1">
      <c:if test="${LoginMember == null }"><a href="${globalUrl}login.show?actionMethod=loginCheck">关注房源</a></c:if> 
      <c:if test="${LoginMember != null }"><a href="javascript:keepOppen();">关注房源</a></c:if>
    </div>
  </div>
</div>
<div id="main">
<div id="aa" class="m-01">
  <div id="m1-01"><p style="text-align: left"><span class="font">01 房源详情</span></p></div>
  <div id="m1-02"><hr></div>
  <div class="m1-03">
    <div class="m3-01">
      <p><span class="font" style="font-size: 13px; color: #999999;">住宅类型</span></p>
      <p><hr></p>
      <p><span class="font">${house.usage.usageName }</span></p>
    </div>
    <div class="m3-01">
      <p><span class="font" style="font-size: 13px; color: #999999;">看房时间</span></p>
      <p><hr></p>
      <p><span class="font">${house.timeToSee }</span></p>
    </div>
    <div class="m3-01">
      <p><span class="font" style="font-size: 13px; color: #999999;">装修</span></p>
      <p><hr></p>
      <p><span class="font">${house.decoration.decorationName }</span></p>
    </div>
    <div class="m3-01">
      <p><span class="font" style="font-size: 13px; color: #999999;">装修时间</span></p>
      <p><hr></p>
      <p><span class="font">${house.decorationTime }</span></p>
    </div>
  </div>
  <div class="m1-04">
    <p style="text-align: left;"><span class="font" style="font-weight: bold; font-size: 13px;">一、房源亮点：</span></p>
    <p style="text-align: left;"><span class="font" style="font-size: 13px;">&nbsp;</span></p>
    <p style="text-align: left;"><span class="font" style="font-size: 13px;">${house.characteristic }</span></p>
    <p style="text-align: left;"><span class="font" style="font-size: 13px;">&nbsp;</span></p>
    <p style="text-align: left;"><span class="font" style="font-weight: bold; font-size: 13px;">二、小区的详细信息与配套信息：非常成熟！</span></p>
    <p style="text-align: left;"><span class="font" style="font-size: 13px;">&nbsp;</span></p>
    <p style="text-align: left;">
      <span class="font" style="font-weight: bold; font-size: 13px;">小区：</span>
      <span class="font" style="font-size: 13px;">《${house.community.communityName }》</span>
    </p>
    <p style="text-align: left;">
      <span class="font" style="font-weight: bold; font-size: 13px;">开发商：</span>
      <span class="font" style="font-size: 13px;">${house.community.developer }；</span>
    </p>
    <p style="text-align: left;">
      <span class="font" style="font-weight: bold; font-size: 13px;">物业公司：</span>
      <span class="font" style="font-size: 13px;">${house.community.propertyManagement }；</span>
    </p>
    <p style="text-align: left;">
      <span class="font" style="font-weight: bold; font-size: 13px;">总户数：</span>
      <span class="font" style="font-size: 13px;">约${house.community.houseCount }户；</span>
    </p>
    <p style="text-align: left;">
      <span class="font" style="font-weight: bold; font-size: 13px;">物业管理费用：</span>
      <span class="font" style="font-size: 13px;">${house.community.propertyExpense }元/平方； </span>
    </p>
    <p style="text-align: left;">
      <span class="font" style="font-weight: bold; font-size: 13px;">开盘时间：</span>
      <span class="font" style="font-size: 13px;">${house.community.startSaleDate }；</span>
    </p>
    <p style="text-align: left;">
      <span class="font" style="font-weight: bold; font-size: 13px;">停车位：</span>
      <span class="font" style="font-size: 13px;">约${house.community.parkingCount }个；</span>
    </p>
    <p style="text-align: left;">
      <span class="font" style="font-weight: bold; font-size: 13px;">小区所属学区：</span>
      <span class="font" style="font-size: 13px;"><c:forEach items="${cSMapping }" var="csms">${csms.school.stuName } &nbsp;</c:forEach></span>
    </p>
    <p style="text-align: left;">
      <span class="font" style="font-weight: bold; font-size: 13px;">周边配套医院：</span>
      <span class="font" style="font-size: 13px;"><c:forEach items="${cHMapping }" var="chms">${chms.hospital.hospitalName } &nbsp;</c:forEach></span>
    </p>
    <p style="text-align: left;">
      <span class="font" style="font-weight: bold; font-size: 13px;">交通路线有：</span>
      <span class="font" style="font-size: 13px;">
        <c:forEach items="${cStaMapping }" var="cstam">${cstam.station.stationName },</c:forEach>
        <c:forEach items="${cSubMapping }" var="csubm">${csubm.subway.subwayName },</c:forEach>
      </span>
    </p>
  </div>
</div>
<div id="bb" class="m-02" style="height: 480px;">
  <div id="m2-01"><p style="text-align: left"><span class="font">02 室内照片</span></p></div>
  <div id="m2-02"><hr></div>
  <div class="m2-03">
<c:forEach items="${housePics }" var="headpic" begin="0" end="5">
    <div class="m2-031" onclick="clickImage()" style="border: 1px #000000 solid;padding:1px;"><img src="${headpic.picUrl }" height="196px" width="261px"></div>
</c:forEach>
  </div>
</div>
<div id="cc" class="m-02">
  <div id="m3-01"><p style="text-align: left"><span class="font">03 生活配套</span></p></div>
  <div id="m3-02"><hr></div>
  <div class="m3-03">
    <div class="m3-01">
      <p><span class="font" style="font-size: 13px; color: #999999;">小区名称</span></p>
      <p><hr></p>
      <p><span class="font">${house.community.communityName }</span></p>
    </div>
  <div class="m3-01">
    <p><span class="font" style="font-size: 13px; color: #999999;">竣工时间</span></p>
    <p><hr></p>
    <p><span class="font">${house.community.buildYear}</span></p>
  </div>
<div class="m3-01">
  <p><span class="font" style="font-size: 13px; color: #999999;">开发商</span></p>
  <p><hr></p>
  <p><span class="font">${house.community.developer }</span></p>
</div>
<div class="m3-01">
  <p><span class="font" style="font-size: 13px; color: #999999;">绿化率</span></p>
  <p><hr></p>
  <p><span class="font">${house.community.landScaping }%</span></p>
</div>
<div class="m3-01" style="width: 480px; margin-top: 15px;">
  <p><span class="font" style="font-size: 13px; color: #999999;">交通</span></p>
  <p><hr></p>
  <p>
    <span class="font">
      <c:forEach items="${cStaMapping }" var="cstam">${cstam.station.stationName },</c:forEach>
      <c:forEach items="${cSubMapping }" var="csubm">${csubm.subway.subwayName },</c:forEach>
    </span>
  </p>
</div>
</div>
<div style="clear: both;"></div>
<div class="communitydetaildivmap" style="margin-left: 0; width: 94%; margin-left:4%; margin-top: 30px;">
  <div class="communitydetaildivmapbtn">
    <div class="around" onclick='changePic("communitydetaildivmappic");'><div><span>周边配套</span></div></div>
    <div class="streetview" onclick='changePic("communitydetaildivmaparound");'><div><span>小区街景</span></div></div>
  </div>
  <div style="clear: both;height: 0px;"></div>
  <div id="communitydetaildivmappic"></div>
  <div id="communitydetaildivmaparound"></div>
</div>
<div id="leftsupport" style="width:94%;float: left; margin-left: 4%">
  <div id="searchHeader" style="width:100%; height:30px; margin-top: 10px; background-color: #F5F5F5; border: 1px solid gray;">
    <div onclick="searchKeyword('交通',5,'traffic')" style="float: left; width:16%; height:30px;  text-align: center;" id="traffic" onmouseover="addBack('traffic');" onmouseout="removeBack('traffic');">
      <a href="javascript:void(0)" style="cursor: pointer;" ><span style="font-size: 14px;">交通设施(<span id="trafficCount">5</span>)</span></a>
    </div>
    <div onclick="searchKeyword('学校',3,'school')" style="float: left; width:16%; text-align: center; height:30px;" id="school" onmouseover="addBack('school');" onmouseout="removeBack('school');">
      <a href="javascript:void(0)" style="cursor: pointer;"><span style="font-size: 14px;">教育机构(<span id="educationCount">3</span>)</span></a>
    </div>
    <div onclick="searchKeyword('医院',7,'hospital')" style="float: left; width:16%; text-align: center; height:30px; " id="hospital" on onmouseover="addBack('hospital');" onmouseout="removeBack('hospital');">
      <a href="javascript:void(0)" style="cursor: pointer;"><span style="font-size: 14px;">医疗机构(<span id="hospitalCount">7</span>)</span></a>
    </div>
    <div onclick="searchKeyword('酒店',8,'hotal')" style="float: left; width:16%; text-align: center; height:30px; " id="hotal" onmouseover="addBack('hotal');" onmouseout="removeBack('hotal');">
      <a href="javascript:void(0)" style="cursor: pointer;"><span style="font-size: 14px;">餐饮娱乐(<span id="restaurantCount">8</span>)</span></a>
    </div>
    <div onclick="searchKeyword('超市',12,'supermarket')" style="float: left; width:16%; text-align: center; height:30px; " id="supermarket" onmouseover="addBack('supermarket');" onmouseout="removeBack('supermarket');">
      <a href="javascript:void(0)" style="cursor: pointer;"><span style="font-size: 14px;">商场超市(<span id="supermarketCount">12</span>)</span></a>
    </div>
    <div onclick="searchKeyword('公园',9,'park')" style="float: left; width:16%; text-align: center; height:30px; " id="park" onmouseover="addBack('park');" onmouseout="removeBack('park');">
      <a href="javascript:void(0)" style="cursor: pointer;"><span style="font-size: 14px;">景观环境(<span id="parkCount">9</span>)</span></a>
    </div>
  </div>
  <div id="searchBody" style="width:100%; font-size: 14px;">
    <div style="text-align:center;line-height:30px; background-color: #F5F5F5;border: 1px solid gray;  height:23px; width: 100%; margin-top: 10px; height: 30px" id="searchHeaderDiv">
      <div style="width:63%; height:30px; float: left; text-align: center; border-right: 1px solid gray;"><span style="padding-top:10px;">设施名称</span></div>
      <div style="width:36%; height:30px; float: left; text-align: center;"><span>距离</span></div>
    </div>
    <div id="trafficeDiv" style="border: 1px solid gray; min-height:50px; text-align: center; "></div>
  </div>
</div>
</div>
<div style="clear: both;"></div>
<div id="dd" class="m-02">
  <div class="m4-01">
    <div class="m4-011"><p style="text-align: left"><span class="font">04 小区相册</span></p></div>
    <div class="m4-012"><p style="text-align: right;"><span class="font" style="font-size: 13px;"> 小群相册共有 8 张照片 | 浏览全部照片</span></p></div>
  </div>
  <div id="m4-02"><hr></div>
  <div class="m2-03">
<c:forEach items="${communityPics }" var="headpic" begin="0" end="5">
    <div class="m2-031" onclick="clickImage2()" style="border: 1px #000000 solid;padding:1px;"><img src="${headpic.picUrl }" height="196px" width="261px"></div>
</c:forEach>
  </div>
</div>
<div style="clear: both;"></div>
<div id="ee" class="m-02" style="height: 295px;">
  <div id="m5-01"><p style="text-align: left"><span class="font">05 价格走势</span></p></div>
  <div id="m5-02"><hr></div>
  <div class="m5-h">
    <div class="m5h1" id="priceChart"></div>
    <div id="priceChart2" style="float: left; height: 250px; width: 400px; padding: 5px 0px 0px 30px;">
      <!-- ▼▲ -->
      <span class="font" style="font-size: 12px;">本案单价（元/平米）</span><br> <span class="font" style="display: inline-block; font-size: 28px; width: 250px;"><%-- ${house.unitPrice} --%></span>
      <span class="font" style="font-size: 12px; color: #333333;">比上月<%-- ${flag >= 0?'▲':'▼'} --%></span>
      <span class="font" style="font-size: 16px; font-weight: bold; color: #fb131f;"><%-- ${flag >= 0 ? flag : -flag}% --%></span>
      <br> <br> <span class="font" style="font-size: 12px;">本案上月挂牌价（元/平米）</span><br>
      <span class="font" style="font-size: 28px;"><%-- ${lastMonthPrice} --%></span><br> <br>
      <span class="font" style="font-size: 12px;">XXX小区 本月均价（元/平米）</span><br> <span class="font" style="display: inline-block; font-size: 28px; width: 250px;">undefined</span>
      <span class="font" style="font-size: 12px; color: #333333;">比上月<%-- ${s.last?'▲':'▼'} --%></span>
      <span class="font" style="font-size: 16px; font-weight: bold; color: #fb131f;">NaN%</span>
    </div>
  </div>
</div>
<div class="m-02" style="height: auto">
  <div class="m6-01">
    <div class="m4-011"><p style="text-align: left"><span class="font">服务本房源的置业顾问</span></p></div>
    <div class="m4-012"><p style="text-align: right;"><span class="font" style="font-size: 13px;">共有 ${fn:length(appraiseList)}位置业人员服务于本房源</span></p></div>
  </div>
  <div id="m6-02"><hr></div>
<c:forEach items="${appraiseList }" var="apps">
  <div class="m6-03">
    <div class="m6b1">
      <div class="m6b11">
        <div class="m6b111"><img src="${globalUrl}${apps.broker.photograph}"></div>
        <div class="m6b111" style="width: 672px; padding-left: 125px;">
          <p><span class="font" style="">${apps.broker.bname }</span></p>
          <p>
            <span class="font">13812344321</span>&nbsp; <span class="font"><img src="${globalUrl}images/img/u67_normal.png" align="top"></span>&nbsp;
            <span class="font"><img src="${globalUrl}images/img/u69_normal.png" align="top"></span>
          </p>
          <p><hr></p>
          <p><span class="font" style="font-size: 13px;">推荐理由： ${house.title}</span></p>
          <p>&nbsp;</p>
          <p><span class="font" style="font-size: 13px;">近 30 天带看量： 23 次&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;持有房源：5 套</span></p>
        </div>
      </div>
    </div>
  </div>
</c:forEach>
</div>
<div class="m-02" style="height: 960px;">
<div id="m5-01"><p style="text-align: left"><span class="font">您可能喜欢</span></p></div>
<div id="m5-02"><hr></div>
<div class="m7-03">
<c:forEach var="houseR" items="${recommendList}">
<div class="m7b1">
  <div class="m7b11">
    <img src="${globalUrl}images/img/u181_normal.png" style="cursor: pointer" onclick="window.location.href='${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&hourseNo=${houseR.hourseNo}'">
  </div>
  <div class="m7b12">
    <div class="m7b121">
      <p><span class="font" style="font-size: 13px;">租金（元/月）</span></p>
      <p><span class="font" style="font-size: 20px;">${house.rentPrice }</span></p>
      <p><span class="font" style="font-size: 13px;">付款方式</span></p>
      <p><span class="font" style="font-size: 20px;">${house.payforWay }</span></p>
      <p><span class="font" style="font-size: 13px;">面积（平米）</span></p>
      <p><span class="font" style="font-size: 20px;">${houseR.area}</span></p>
      <p><span class="font" style="font-size: 13px; font-weight: bold;">&nbsp;</span></p>
      <p>
        <span class="font" style="font-size: 13px; font-weight: bold;">居室：</span>
        <span class="font" style="font-size: 13px;">${houseR.shi}室 ${houseR.ting}厅 ${houseR.wei}卫</span>
      </p>
      <p>
        <span class="font" style="font-size: 13px; font-weight: bold;">楼层：</span>
        <span class="font" style="font-size: 13px;">${houseR.storey}</span>
      </p>
      <p>
        <span class="font" style="font-size: 13px; font-weight: bold;">朝向：</span>
        <span class="font" style="font-size: 13px;">${houseR.orientations.orientationName}</span>
      </p>
      <p>
        <span class="font" style="font-size: 13px; font-weight: bold;">小区：</span>
        <span class="font" style="font-size: 13px;">${houseR.community.communityName}</span>
      </p>
    </div>
    <div class="m7b122">
      <div style="margin-top: 30px;">
        <p>
          <span class="font" style="font-size: 13px; color: #0000FF;">
          ${houseR.rentPrice - house.rentPrice > 0 ? "高于":"低于"}本案${houseR.rentPrice - house.rentPrice > 0 ? (houseR.rentPrice - house.rentPrice):-(houseR.rentPrice - house.rentPrice)} 万元</span>
        </p>
      </div>
      <div>
        <p>
          <span class="font" style="font-size: 13px; color: #0000FF;">
          <%-- ${houseR.unitPrice - house.unitPrice > 0 ? "高于":"低于"}本案${houseR.unitPrice - house.unitPrice > 0 ? (houseR.unitPrice - house.unitPrice):-(houseR.unitPrice - house.unitPrice)} --%>元/平米</span>
        </p>
      </div>
      <div>
        <p><span class="font" style="font-size: 13px; color: #0000FF;">${houseR.area - house.area > 0 ? "高于":"低于"}本案${houseR.area - house.area > 0 ? (houseR.area - house.area):-(houseR.area - house.area)} 平米</span></p>
      </div>
    </div>
  </div>
</div>
</c:forEach>
</div>
<!-- m7-03 -->
</div>
</div>
</div>
<div style="display: none;">
  <div id="containe">
    <div id="gallery" class="ad-gallery">
      <div class="ad-image-wrapper"></div>
      <div class="ad-controls"></div>
      <div class="ad-nav">
        <div class="ad-thumbs">
          <ul class="ad-thumb-list" style="text-align: center;"></ul>
        </div>
      </div>
    </div>
  </div>
</div>

<div id="dialoginform" style="display: none;">
<%@include file="/WEB-INF/jsp/ddhb/front/company/company_shaminform_dialog.jsp" %>
</div>
<script type="text/javascript" >    
var jiathis_config = {
  data_track_clickback: true,
  siteNum: 5,
  sm: "weixin,qzone,cqq,tsina,tqq",
  summary: "",
  boldNum: 5,
  shortUrl: false,
  hideMore: true,
  siteName: "华邦网分享"
}
</script>
<script type="text/javascript" src="http://v3.jiathis.com/code_mini/jia.js?uid=1929134" charset="utf-8"></script>
