<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<title>新房 - ${title }</title>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/style.css" />
<link rel="stylesheet" type="text/css" href="${globalUrl}css/ystg.css" />
<script type="text/javascript" src="${globalUrl}js/search/myMap_new.js"></script>
<script type="text/javascript" src="${globalUrl}js/housesecond/housesecond.js"></script>
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.newhouse.js"></script>
<link type="text/css" rel="stylesheet" href="${globalUrl}css/searchPlugin/jquery.search.css" />
<script type="text/javascript" src="${globalUrl}js/search/jquery.search.newhouse.phone.js"></script>
<link type="text/css" rel="stylesheet" href="${globalUrl}css/ad/adAndActivity.css">
<!--
<link type="text/css" rel="stylesheet" href="${globalUrl}css/searchPlugin/jquery.search.phone.css" />
<style type="text/css">
.searchFieldContentDiv { margin-left: 70px; }
.currentPageButton { background-color: #f87a13; color: #ffffff !important; }
.searchLabelLeft { background-image: none; float: left; height: 20px; width: 0px; }
.searchLabel { background-image: none; color: #000000; float: left; font-size: 14px; height: 20px; line-height: 20px;
  padding-left: 5px; text-align: center; width: 44px !important; }
.searchLabelRight { background-image: none; float: left; height: 20px; width: 0px; }
.searchLabelContainer { float: left; background-color: #D0D0D0; margin-left: 10px; }
.selectedField { color: #f87a13; text-align: center; }
.searchFieldContentDiv { margin-left: 80px; }
.companyRight {
  filter: progid:DXImageTransform.Microsoft.Shadow(color=#999999, direction=120, strength=4);
  -moz-box-shadow: 1px 1px 2px #999999;
  -webkit-box-shadow: 1px 1px 2px #999999;
  box-shadow: 1px 1px 2px #909090;
  background: #fff;
  color: #333;
}
.customSelect { margin-top: 0px; margin-bottom: 15px; }
.selectedField { color: #DC5F5D }
.displayThisLine { display: block; }
.hiddenThisLine { display: none; }
</style>-->

<script type="text/javascript">
var searchMap = new Map();
var postUrl = "";
var isopen = false;
function initSearchMap(){
  try{
	  searchMap.put('ispage', 1);  
  }catch(e){
	  if(window.console) console.log(e);
  }
}
$(document).ready(function(){
  setInterval(startCount, 1000);

	initSearchMap();  

	/*
  var str = $.parseJSON('${jsonString}');
  option={
    searchItems:str,
    url:'${globalUrl}houseNew.show?actionMethod=dimquery',
    searchMap:searchMap
  };
  
  try{
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
  }catch(e){
	  if(window.console) console.log(e);
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
	*/
	createSearchArea(_conditionJson, '${globalUrl}houseNew.show?actionMethod=dimquery');
	
  try{
	  $("#scrollDiv").Scroll({line:1,speed:500,timer:1000});  
 	}catch(e){
 		if(window.console) console.log(e);
 	}
 	$("#_Ten_rightDiv").css("z-index", "999");
});

function startCount(){
  $(".xm_wd").each(function(){
    var vvv = $(this);
    var remainsDay = parseInt($(vvv).find(".remainsDay").html());
    var remainsHour = parseInt($(vvv).find(".remainsHour").html());
    var remainsMinute = parseInt($(vvv).find(".remainsMinute").html());
    var remiansSecond = parseInt($(vvv).find(".remiansSecond").html());
    if (remainsDay <= 0 && remainsHour <= 0 && remainsMinute <= 0 && remiansSecond <= 0){
      $(vvv).find(".remainsDay").html(0);
      $(vvv).find(".remainsHour").html(0);
      $(vvv).find(".remainsMinute").html(0);
      $(vvv).find(".remiansSecond").html(0);
      return;
    }
    if (remiansSecond > 0){
      remiansSecond = remiansSecond - 1;
    }else{
      remiansSecond = 59;
      if (remainsMinute > 0){
        remainsMinute  = remainsMinute - 1;
      }else{
        remainsMinute = 59;
        if (remainsHour > 0){
          remainsHour = remainsHour - 1;
        }else{
          remainsHour = 23;
          if (remainsDay > 0){
            remainsDay = remainsDay - 1;
          }else{
            remainsDay = 0;
          };
        };
      };
    };
    $(vvv).find(".remainsDay").html(remainsDay);
    $(vvv).find(".remainsHour").html(remainsHour);
    $(vvv).find(".remainsMinute").html(remainsMinute);
    $(vvv).find(".remiansSecond").html(remiansSecond);
  });
}
</script>

<script type="text/javascript">
//滚动插件
(function($){
  $.fn.extend({
    Scroll:function(opt,callback){
      //参数初始化
      if(!opt) var opt={};
      var _this=this.eq(0).find("ul:first");
      var lineH=_this.find("li:first").height(), //获取行高
         line=opt.line?parseInt(opt.line,10):parseInt(this.height()/lineH,10), //每次滚动的行数，默认为一屏，即父容器高度
         speed=opt.speed?parseInt(opt.speed,10):500, //卷动速度，数值越大，速度越慢（毫秒）
         timer=opt.timer?parseInt(opt.timer,10):3000; //滚动的时间间隔（毫秒）
      if(line==0) line=1;
      var upHeight=0-line*lineH;
      //滚动函数
      scrollUp=function(){
    	_this.animate({marginTop:upHeight},speed,function(){
           for(i=1;i<=line;i++){
             _this.find("li:first").appendTo(_this);
           }
           _this.css({marginTop:0});
        });
      }
      //鼠标事件绑定
      _this.hover(function(){
              clearInterval(timerID);
      },function(){
              timerID=setInterval("scrollUp()",timer);
      }).mouseout();
    }
  });
})(jQuery);

</script>
<div class="Location">
  <a href="${globalUrl}welcome.show?actionMethod=welcome" class="font7blue">首页</a> > <a href="#" class="font7blue">杭州新盘</a>
</div>
<form name="houseNew">
  <!--检索开始-->
  <div class="xcon"><div id="searchMenuDiv"></div></div>

  <div class="ad_ban"><a href="#"><img src="${globalUrl}image/ad_banner05.jpg" alt="" /></a></div>
  <%@include file="/WEB-INF/jsp/ddhb/front/ad/adTopBar.jsp"%>
  <!--检索结束-->
  <div class="tongji">共有在售项目 ${houseCount } 个，售罄项目${houseCountEnd }个</div>
  <!--总计开始-->
  <div class="zongji">
    <div class="zjs">
      <%-- 
      <div class="zjs_k zjs_b"><p>楼盘总计：</p><b>${houseCount+houseCountEnd }</b></div>
      <div class="zjs_k"><p>参与人数总计：</p><b>${groupCount }</b></div>
     --%>
      <div class="zjs_k" style="padding-left: 50px;">
        <p>楼盘总计：</p><b>${houseCount+houseCountEnd }</b>
      </div>
    </div>
    <div class="r_tels">
      <div id="scrollDiv" style="overflow: hidden; max-height: 340px;">
        <ul>
          <c:forEach items="${groupBuyList }" var="person">
            <li class="li_1"><span>${fn:substring(person.clientName,0,1)}**</span>${fn:substring(person.clientTelephone,0,3)}****${fn:substring(person.clientTelephone,7,-1)}</li>
          </c:forEach>
        </ul>
      </div>
    </div>
      <div class="xingqu">
        <h2>我有兴趣，如何参加团购？</h2>
        <div class="buzt"><img src="${globalUrl}image/buz.gif" alt="" /></div>
        <%--
        <div class="buzt_iph"><img src="${globalUrl}image/buz_470.gif" alt="" /></div>
        <div class="buzt_ips"><img src="${globalUrl}image/buz_310.gif" alt="" /></div>
        --%>
      </div>
    <div class="clear"></div>
  </div>
  <!--总计结束-->
  <div class="tongji tongji_pad">共有在售项目 ${houseCount }个，售罄项目${houseCountEnd }个</div>
  <!--总计列表开始-->
  <div class="zj_ls_w">
    <div class="zj_ls">
      <div id="changelist">
        <input type="hidden" id="houseCountHidden" value="${houseCount}" />
        <input type="hidden" id="houseCountEndHidden" value="${houseCountEnd}" />
        <c:forEach var="houseNew" varStatus="i" items="${pageBean.dataList}">
          <div class="xiangm">
            <div class="fang_biao"><img src="${globalUrl}image/rt_biao.png" alt="" /></div>
						<div class="xm_pt">
              <img src="${pictureHost}${houseNew.pictureUrl}" style="width: 465px; height: 260px;" alt="" />
              <!-- <div class="xmbh">项目编号：${houseNew.projectNo}</div> -->
            </div>
            <div class="xm_wd">
              <h2>${houseNew.buildingName }</h2>
              <h3>${houseNew.adv }</h3>
              	<%-- 
              	<p>已有<b>${houseNew.entrants }</b>人报名<br>
              	剩余时间： <span class="remainsDay">${houseNew.remainDays.day }</span>天 
              	<span class="remainsHour">${houseNew.remainDays.hour }</span>小时 
              	<span class="remainsMinute">${houseNew.remainDays.min }</span>分 
              	<span class="remiansSecond">${houseNew.remainDays.sec }</span>秒 <br>
              	均价<span>${houseNew.averagePrice}</span>元/㎡起&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总价<span>${houseNew.minPrice }</span>万起</p>
              	 --%>
		        <div class="jg_new"><p>均价<span>${houseNew.averagePrice}</span>元/㎡起&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总价<span>${houseNew.minPrice }</span>万起</p></div>
		        <p>距离报名截止还有：<span>${houseNew.remainDays.day}</span>天
			           <span>${houseNew.remainDays.hour }</span>小时
			           <span>${houseNew.remainDays.min }</span>分</p>
				<div class="tel">咨询电话：<p>${houseNew.hotline}</p></div>
            </div>
            <div class="clear"></div>
            <div class="fzxx">
              <div class="wd">${houseNew.recommandContent}</div>              
            </div>
            <div class="clear"></div>
            <div class="qkk">
              <a onclick="window.open('${globalUrl}houseNew.show?actionMethod=showDetail&id=${houseNew.id}')">去看看</a>
            </div>
          </div>
        </c:forEach>
        <div class="page">
          <huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false"
            formName="houseNew" offerPageSize="20,50,100" isExistForm="true"
            queryFunction="changePages()" />
        </div>
      </div>
    </div>
  </div>
</form>
<script type="text/javascript">
function changePages() {
  var currvalue = document.getElementsByName("currentPage")[0].value;
  setJSONValue("currentPage", currvalue);
  showSelectedField("${globalUrl}houseNew.show?actionMethod=dimquery");
}
</script>

<%@include file="/WEB-INF/jsp/ddhb/front/ad/adButtompBar.jsp"%>