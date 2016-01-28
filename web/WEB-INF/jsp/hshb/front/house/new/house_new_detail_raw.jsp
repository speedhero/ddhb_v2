<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="h" uri="/WEB-INF/tld/hshb.tld"%>
<tiles:insertAttribute name="common.css" />
<tiles:insertAttribute name="common.js" />
<script type="text/javascript" src="${globalUrl}js/jquery.artDialog.source.js"></script>
<script type="text/javascript" src="${globalUrl}js/artDialog.iframeTools.source.js"></script>
<link type="text/css" rel="stylesheet" href="${globalUrl}js/skins/gray.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/style.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/ystg.css"/>
<script type="text/javascript">
window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"1","bdMiniList":["qzone","tsina","weixin","tqq","sqq"],"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};
with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
function signUp(){
  var remainsDay = parseInt($(".xm_wd").find(".remainsDay").html());
  var remainsHour = parseInt($(".xm_wd").find(".remainsHour").html());
  var remainsMinute = parseInt($(".xm_wd").find(".remainsMinute").html());
  var remiansSecond = parseInt($(".xm_wd").find(".remiansSecond").html());
  if (remainsDay <= 0 && remainsHour <= 0 && remainsMinute <= 0 && remiansSecond <= 0){
    return;
  }
  var content = document.getElementById("signupDialog");  
  art.dialog({
    id:'signupDialogid',
    title: "&nbsp;&nbsp;马上报名",
    width: 400,
    height: 300,
    content: content,
    drag: true,
    resize: true,
    max: false,
    min: false,
    lock: true,
    zIndex: 99999
  });
}
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
     _this.animate({
         marginTop:upHeight
     },speed,function(){
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
})
})(jQuery);

function pointClick(div){
  var position = $("#floatBar").css("position");
  var scroll_offset;
  if(position != "fixed") {
    scroll_offset = $("#" + div).offset().top - $("#floatBar").height() - 80;
  } else {
    scroll_offset = $("#" + div).offset().top - $("#floatBar").height();
  }
  
  $("body,html").animate({scrollTop:scroll_offset},0);
}

$(document).ready(function(){
  setInterval(startCount, 1000);
  $("#scrollDiv").Scroll({line:1,speed:500,timer:1000});
  
  var navH = $("#floatBar").offset().top;
  //滚动条事件
  $(window).scroll(function(){
    //获取滚动条的滑动距离
    var scroH = $(this).scrollTop();
    //滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定
    if(scroH>=navH){
       $("#floatBar").css("position","fixed").css("top", 0).css("margin-top", "-4px");
    }else if(scroH<navH){
      $("#floatBar").css("position","static").css("margin-top", "0px");
    }
  });
  $("#_Ten_rightDiv").css("z-index", "999");
});

function copyUrl(){ 
  var Url = document.URL;
  if(window.clipboardData) {
      window.clipboardData.setData("Text", Url);
      alert("复制成功，请粘贴到你的QQ/MSN上推荐给你的好友！\r\n\r\n内容如下：\r\n" + Url);
  } else{
    alert("当前浏览器不支持剪切板功能，请手动复制以下网址：\r\n\r\n"+Url);
  }
} 
</script>

<style type="text/css">
.hou_tit_con .zh_retun{padding:0 20px 0 10px; text-align:left;display:block;float:left;}

@media screen and (max-width: 470px) {
.hou_tit_con .zh_retun{padding:0 20px 0 10px; text-align:left;display:block;float:left; }

}
@media screen and (max-width: 360px) {
.hou_tit_con .zh_retun{padding:0 20px 0 10px; text-align:left;display:block;float:left; }

}
@media screen and (max-width: 320px) {
.hou_tit_con .zh_retun{padding:0 20px 0 10px; text-align:left;display:block;float:left; }

}
</style>

<div class="zhxq_t">
  <div class="hou_tit_con" style="position:fixed;width:1000px;left:0;top:0;background:#fff; z-index:999999;">
  	<div class="zh_dbt">
  		<a href="http://m.hshb.cn/xinfang" class="zh_retun">&lt;&nbsp;返回</a>
  		<span style="float:left;">${h:limitStr(houseNew.houseAdv, 20, "...")}</span>
  	</div>
  </div>
  <div class="xmdt_pt" style="margin-top:55px;">
   	<div class="fang_biao"><img src="${globalUrl}image/rt_biao.png" alt="" /></div>
		<img src="${pictureHost}${houseNew.pictureUrl}" style="width: 465px; height: 285px;" alt=""/>
   	<!-- <div class="xmbh">项目编号：${houseNew.projectNo}</div> -->
  </div>
  <div class="xm_cen_wd" style="margin-top:55px;">
    <h3>${houseNew.buildingName}</h3>
    <p>总价：<b>${houseNew.housePrice }</b>万起</p>
		<p>均价：<span>${houseNew.averagePrice}</span>元/㎡起</p>
		<div class="jg_new">
		  <p style="padding-left:0;">报名：距离报名截止还有<br />
		    <span class="ti" style="margin-left:40px;">${houseNew.remainDays.day}</span><label>天${houseNew.remainDays.day}</label>
		    <span class="ti">${houseNew.remainDays.hour }</span><label>小时${houseNew.remainDays.hour }</label>
		    <span class="ti">${houseNew.remainDays.min }</span><label>分</label>
		    <a onclick="signUp();">马上报名</a>
		    <!--<div class="<c:if test='${houseNew.enableFlag eq false}'>disableClickClass</c:if><c:if test='${houseNew.enableFlag eq true}'>enableClickClass</c:if>" onclick="signUp();">马上报名</div>-->
		  </p>
		  <div style="clear:both;"></div>
		</div>
		<p>楼盘地址：${houseNew.buildingAddress}</p>
		<p>楼盘开发商：${houseNew.developer}</p>
		<div class="tel">${houseNew.hotline}</div>
		<div class="dbfx" style="float:right; padding-right:5px;">
			<a class="gz" href="javascript:void(0);" onclick="${LoginMember == null? "loginBox()" : "keepOppen()"};">收藏</a>
			<a class="jb" href="javascript:void(0)" onclick="shaminform();">虚假举报</a>
			<a class="dbi" id="compareContainerContainer"
				housePictureUrl="${pictureHost}${house.pictureUrl }"
				inCompareItem="false"
				onclick="addCompareItem(this, 'rentHouseCompare')"
				compareId="${house.hourseNo }" historyType="R"
				brokerId="${(houseAppraise != null) ? houseAppraise.broker.erpId : house.broker.erpId}"
				compareTitle="${(houseAppraise != null) ? houseAppraise.title : house.title}"
				area="${house.area }" shi="${house.shi }" ting="${house.ting }"
				comparePrice="${house.rentPrice}" style="float: left;">对比</a>
			<a class="fx bds_more" data-cmd="more">分享</a><span class="pl">评价(${appraiseList.size()})</span>
		</div>
		<div style="clear:both;"></div>
  </div>
  <div class="xingqu_xx" style="margin-top:55px;">
    <h2>我有兴趣，如何参加团购？</h2>
    <div class="buzt"><img src="${globalUrl}image/buz2.gif" alt=""/></div>
  </div>
</div>

<div class="fzxx_detail">
   <div class="wd">${houseNew.recommandContent}</div>
   <div class="ys_ewm">
     <div class="ewm_pt"><img src="${globalUrl}/xinfang/qrcode?id=${houseNew.newhouseNo}" alt=""/></div>
   </div>
</div>
<div class="zj_ls_w">
  <div class="zj_ls_detail">
    <div class="nr_tit" id="floatBar">
      <div class="tit_a">
	      <a class="one" onClick="pointClick('sellPoint');">楼盘卖点</a>
	      <a onClick="pointClick('salePoint');">活动优惠</a>
	      <a onClick="pointClick('detailsPoint');">楼盘介绍</a>
	      <a onClick="pointClick('aroundPoint')" class="none">周边配套</a>
      </div>
    </div>
	  <div style="clear:both;"></div>
      <div class="ys_xxnr">
          <h3 id="sellPoint"><b>楼盘卖点：</b></h3>
          <div class="xxnr">${houseNew.sellingPoint}</div>
          <h3 id="salePoint"><b>活动优惠：</b></h3>
          <div class="xxnr">${houseNew.salesPromotion}</div>
          <h3 id="detailsPoint"><b>楼盘介绍：</b></h3>
          <div class="xxnr">${houseNew.buildingIntroduce}</div>
          <h3 id="aroundPoint"><b>周边配套：</b></h3>
          <div class="xxnr">${houseNew.houseFacility}</div>
      </div>
      <div class="clear"></div>
  </div>
</div>
<div id="signupDialog" style="display:none">
<%@include file="/WEB-INF/jsp/ddhb/front/houseNew/sign_up_dialog.jsp" %>
</div>
