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
<title>租房 - ${title } </title>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/houseDetail.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/jquery.ad-gallery.css">

<script type="text/javascript" src="${globalUrl}js/jquery.ad-gallery.js"></script>
<script type="text/javascript" src="${globalUrl}js/jquery.nav.js"></script>
<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
<script src="http://map.qq.com/api/js?v=2.exp&key=Z3YBZ-JVFHV-6W2P2-U653S-DCY22-KVFDG"></script>
<!-- 弹出框js引用 -->
<script type="text/javascript" src="${globalUrl}js/jquery.artDialog.source.js"></script>
<script type="text/javascript" src="${globalUrl}js/artDialog.iframeTools.source.js"></script>
<link type="text/css" rel="stylesheet" href="${globalUrl}js/skins/gray.css">
<!-- 价格走势图 -->
<script language="javascript" type="text/javascript" src="${globalUrl}js/jsCharts/excanvas.js"></script>
<!--[if IE]><script language="javascript" type="text/javascript" src="${globalUrl}js/jsCharts/excanvas.js"></script><![endif]-->
<script type="text/javascript" src="${globalUrl}js/jsCharts/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="${globalUrl}js/jsCharts/jqplot.highlighter.min.js"></script>
<script type="text/javascript" src="${globalUrl}js/jsCharts/jqplot.dateAxisRenderer.min.js"></script>
<link type="text/css" rel="stylesheet" href="${globalUrl}css/jquery.jqplot.min.css" />

<link rel="stylesheet" type="text/css" href="${globalUrl}css/style.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/zl.css">

<link type="text/css" rel="stylesheet" href="${globalUrl}css/album.css" />
<script type="text/javascript" src="${globalUrl}js/album.js" ></script>
<script type="text/javascript" src="${globalUrl}js/detail/detail.js"></script>
<script type="text/javascript" src="${globalUrl}js/commonGround/chatTools.js"></script>
<style>
.addCompare{background-color:inherit !important;}
.grayBack{ background-color: gray; }
.testQ{ background-color: gray; }
.aui_nw_right {
  background-image: url("${globalUrl}gray/ddhb_bak.png");
  height: 32px;
  margin-right: 0;
  width: 15px;
}
.aui_nw_left {
  background-image: url("${globalUrl}gray/ddhb_bak.png");
  height: 32px;
  margin-left: 0;
  width: 15px;
}
.aui_content {
  border: 0 none;
  text-align: left;
}
.aui_w, .aui_e {
  background-image: none;
}
.aui_close {
  background-image: url("${globalUrl}js/skins/gray/closeDialog.png");
  background-position: 0 -96px;
  height: 16px;
  right: 25px;
  text-indent: -9999em;
  top: 10px;
  width: 15px;
}
.fontColorWhite{
	color:#ffffff;
}
.fontColorGray{
	color:#666666;
}
</style>
<script type="text/javascript">


window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"1","bdMiniList":["qzone","tsina","weixin","tqq","sqq"],"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};
with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
function initCharts(size){
	var arrayz = [
	          	<c:forEach var="trend" items="${trendList}" varStatus="s">
	          	    ["${trend.calculateDate}".substring(0,10), ${trend.rentAveragePrice}]${s.last?'':','}
	          	</c:forEach>
	          	]
	
	var plot2 = $.jqplot('priceChart', [arrayz], {
		title:'价格走势图',
	      axes:{
	        xaxis:{
	          numberTicks:arrayz.length,
	          pad: 1,
	          showTicks: true,
			  tickInterval: '1 month',
	          renderer:$.jqplot.DateAxisRenderer,
	            tickOptions:{
	            	formatString:formatDateString
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
	          sizeAdjust: 15
	      }
	});//
	var plot3 = $.jqplot('priceChart3', [arrayz], {
		title:'价格走势图',
	      axes:{
	        xaxis:{
	          numberTicks:arrayz.length,
	          pad: 1,
	          showTicks: true,
			  tickInterval: '1 month',
	          renderer:$.jqplot.DateAxisRenderer,
	            tickOptions:{
	            	formatString:formatDateString
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
	          sizeAdjust: 15
	      }
	});//
}	
var searchService,map1,markers = [];

var historyItemsArray;
var comparedItemsArray;


$(document).ready(function() {
		//走势图
		var size = ${trendList.size()};
		if(size == 0){
			$('#priceChart').text("暂时没有历史走势图!");
			$('#priceChart3').text("暂时没有历史走势图!");
		} else {
			initCharts(size);
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
		/**切换导航**/
		$(".checkbtn").each(function() {
			$(this).click(function() {
				$(this).css("background","url('${globalUrl}images/housedetail/checkeditem.png')").css("color","#000000").css("height","88%");
				$(this).nextAll(".checkbtn").removeAttr("style").css("cursor", "pointer"); 
				$(this).prevAll(".checkbtn").removeAttr("style").css("cursor", "pointer");
			});
		}); 
		
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
		
		/**上方小地图**/
		var arr = "${house.community.location}";
		var arrayLocation = arr.split(",");
		var center = new qq.maps.LatLng(arrayLocation[0],
			arrayLocation[1]);
  		var map = new qq.maps.Map(document.getElementById("topmap"), {
	        // 地图的中心地理坐标。
	        center: center,
	        zoom: 13,
   		});
	    var marker = new qq.maps.Marker({
	        position: center,
	        map: map
		});
	    
	    var latLng = new qq.maps.LatLng(arrayLocation[0],arrayLocation[1]);
	    var panoService = new qq.maps.PanoramaService();
	    panoService.getPano(latLng, 1000, function(result){
	    	if(null != result){
	    		//街景地图
	    		var pano = new qq.maps.Panorama(document.getElementById('streetmap'), {
	    			pano: result.svid,
	    			disableMove: false,
	    			disableFullScreen: false,
	    			zoom:1,
	    			pov:{
	    				heading:20,
	    				pitch:15,
	    			}
	    		});
	    	}else {
	    		document.getElementById('streetmap').innerHTML = "暂无街景地图";
	    	}
	    });
	    
		$("#checkbtn2").click(function(){
			$("#checkbtn1").removeAttr("style").css("cursor", "pointer");
			$("#streetmap").css("display", "none");
			$("#streetstreet").css("display", "block");
		});
		$("#checkbtn1").click(function(){
			$("#checkbtn2").removeAttr("style").css("cursor", "pointer");
			$("#streetmap").css("display", "block");
			$("#streetstreet").css("display", "none");
		});
		
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
			}else if(scroH>=distant3){
				$("#facilities").addClass("one");
			}else if(scroH>=distant2){
				$("#housePhoto").addClass("one");
			}else {
				$("#details").addClass("one");
			} 
		}); 
		
		
		/**照片轮播**/
		$('.hot-event').nav({
			t:5000,	//轮播时间
			a:1000  //过渡时间
		});
		var arr = "${house.community.location}";
		var arrayLocation = arr.split(",");
		var center = new qq.maps.LatLng(arrayLocation[0],
			arrayLocation[1]);
  		var map1 = new qq.maps.Map(document.getElementById("streetstreet"), {
	        // 地图的中心地理坐标。
	        center: center,
	        zoom: 13,
   		});
  		
  		var anchor1 = new soso.maps.Point(0, 0);
  		var size1 = new soso.maps.Size(24, 30);
  		var icon1 = new soso.maps.MarkerImage('${globalUrl}images/maker.png', size1, anchor1);
  	    var markerBig = new soso.maps.Marker({ icon: icon1, map: map1, position:center});
  		
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
	            		$('<div style="width: 100%; margin-top:5px;">'+
		            			'<div style="width:63%; float: left; border-bottom:1px dashed #F5F5F5; border-right: 1px solid #F5F5F5;">'+'<div style="background-image:url(${globalUrl}images/housedetail/distantCircle.png) ;float:left;font-size:10px;color:#ffffff;width:14px;height:14px;line-height:13px;">'+(i+1)+'</div>'+
									'<span class="mapFontSize" style="float: left; margin-left:6px;color:#666666">'+pois[i].name +"&nbsp;&nbsp;"+ pois[i].address+'</span>'+
								'</div>'+
								'<div style="width:36%;float: left; text-align: center; border-bottom:1px dashed #F5F5F5;">'+
									'<span class="mapFontSize" style="color:#575757">距离<span class="mapWordFontSize" style="color:#ff9b4b;">'+pois[i].dist+'</span>米，步行约<span class="mapWordFontSize" style="color:#ff9b4b;">'+min+'</span>分钟'+'</span>'+
								'</div>'+
								'<div style="clean:both;"></div>' +
							 '</div>'+
							 '<div style="clear:both;"></div>'
							).appendTo('#trafficeDiv');
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
	    _searchKeyword("交通",5,'traffic');
	    
		initQRCode("${globalUrl}");
		
	    $(window).resize(function() {
	       initQRCode("${globalUrl}");
	    });
});
//周边设施
function _searchKeyword(keyword, number, divId){
	searchKeyword(keyword, number, divId, '${house.community.location}');
}

//初始化二维码
function initQRCode(globalUrl){
	var url = "${globalUrl}houseSpecial.show?actionMethod=drawcode&houseId=${houseId}&agentId=${houseId}&houseType=2&houseNo=${house.hourseNo}&pictureType=";
	_initQRCode(url);
};


function clickImageHead(index){
	resizeAlertImage("photoAlbumHead");
	$("#photoAlbumHead").show();
	initAlbum(index,"Head");
	$("#blackGround").show();
}
function clickCommImage(index){
	resizeAlertImage("photoComm");
	$("#photoComm").show();
	initAlbum(index,"Comm");
	$("#blackGround").show();
}
function resizeAlertImage(div) {
	var left = ($(document).width() - $("#" + div).width()) / 2 + "px";
	var top = ($(window).height() - $("#" + div).height()) / 2 + "px";
	$(".tcc_zp").css("margin", "0").css("left", left).css("top", top);
}
function showWeixin(broker_id){
	var tagName = "BWeixin_" + broker_id;
	_showWeixin(tagName, 'BWeixin', '评价人微信');
}
</script>
<div class="Location"><a onclick='window.location.href="${globalUrl}welcome.show?actionMethod=welcome"' style="cursor: pointer;">首页</a> > <a onclick='window.location.href="${globalUrl}rent.show?actionMethod=dimquery&type=1"' style="cursor: pointer;">杭州出租房</a> > <a onclick='window.location.href="${globalUrl}welcome.show?actionMethod=dimQuery&type=1&searchModule=2&ddhb_one_community.region.erpId=${house.community.region.erpId}"' style="cursor: pointer;">${house.community.region.countyName}出租房</a> > ${house.community.communityName}出租房</div>
    	<!--详情页头开始-->
        <div class="zhxq_t">
        	<div class="zh_dbt"><c:out value="${(houseAppraise != null) ? houseAppraise.title : house.title}"></c:out></div>
            <div class="dt_pt">
            	<div class="dt_pts hot-event" id="toppic">
            	<c:forEach items="${houseHeadPics }" var="headpic" varStatus="status">
							<div class="event-item dt_pts" style="display: block;">
								<img src="${pictureHost}${headpic.picUrl }" onclick="clickImageHead(${status.index+1});" class="photo" style="width: 100%;cursor: pointer;" alt="室内图片" />
							</div>
						  </c:forEach>
						<div class="switch-tab">
						 	<c:forEach items="${houseHeadPics }" var="headpic" varStatus="items">
								<a href="javascript:void(0);" onclick="return false;" <c:if test="${items.index == 0}">class="current"</c:if>></a> 
							</c:forEach>
						</div>
				</div>
				<div class="dt_pts" id="topmap" style="display: none;"></div>
                <div class="dt_bq">
                	<ul>
                    	<li  class="li_1 one"><a id="frontpic" onclick="changePic();"  class="a_1">照片</a><div class="jiao"></div></li>
                    	<li  class="li_2"><a id="frontmap" onclick="changeMap();" class="a_2">地图</a><div class="jiao"></div></li>
                    </ul>
                </div>
                <div class="dt_color">
                	<c:forEach items="${tagList}" var="tags">
					<c:forEach items="${house.tagIdList}" var="tagId">
						<c:if test="${tags.erpId == tagId}">
							<div style="float:left; margin: 0px 8px 10px 0px; padding:2px 11px; font-size: 14px; background-color:${tags.tagColor};color:${tags.fontColor};">
								${tags.tagName}
							</div>
						</c:if>
					</c:forEach>
				</c:forEach>
                </div>
            </div>
            <div class="jia_pm">
            	<div class="jpm_s">
                	<div class="sjia"><p>月租金（元/月）</p><b><fmt:formatNumber pattern="#" value="${house.rentPrice}"/></b></div>
                    <div class="mianji"><span>付款方式：</span>${house.payforWay }</div>
                    <div class="danjia"><p>租赁形式：</p><span>${house.rentType.rentTypeName }</span></div>
                </div>
                <div class="jpm_x">
                	<div class="sjiang">
                		<c:if test="${house.lastedThirtyPriceRatio >= 0}"><div class="sheng">最近<b>30</b>天单价浮动<span class="s">${house.lastedThirtyPriceRatio}%</span></div></c:if>
						<c:if test="${house.lastedThirtyPriceRatio < 0}"><div class="jiang">最近<b>30</b>天单价浮动<span class="j">${-house.lastedThirtyPriceRatio}%</span></div></c:if>
                        
                        <c:if test="${house.ratioToCommunity >=0 }"> <div class="sheng">所属小区均价参照<span class="s">${house.ratioToCommunity}%</span></div></c:if>
						<c:if test="${house.ratioToCommunity < 0}"> <div class="jiang">所属小区均价参照<span class="j">${-house.ratioToCommunity}%</span></div></c:if>
                    </div>
                    <div class="danjia"><p>面积（平米）：</p><b><fmt:formatNumber pattern="#" value="${house.area}"/>㎡</b></div>
                    <div class="zl_pz">
                    	<c:forEach items="${furList}" var="furs">
						<c:if test="${furs.flag eq true}">
							<img src="${pictureHost}${furs.imgUrl}" style="width:auto;" title="<c:out value="${furs.furName }"></c:out>">
						</c:if>
						<c:if test="${furs.flag eq false }">
							<img src="${pictureHost}${furs.imgDisUrl}" style="width:auto;" title="<c:out value="${furs.furName }"></c:out>">
						</c:if>
					</c:forEach>
                    </div>
                </div>
            </div>
            <div class="xs_k">
            	<div class="xs">
                	<ul>
                    	<li><span>户型：</span><p><a id="sshuxing" href="javascript:void(0);">相似户型</a><b>${house.shi}</b>室<b>${house.ting}</b>厅<b>${house.wei}</b>卫</p></li>
                    	<li><span>楼层：</span><p><a id="sslouceng" href="javascript:void(0);">相似楼层</a><b>${house.storyCount }</b>
                    	<c:if test="${house.vervicalLocation == 1}">层下部</c:if>
						<c:if test="${house.vervicalLocation == 2}">层中部</c:if>
						<c:if test="${house.vervicalLocation == 3}">层上部</c:if></p></li>
                    	<li><span>朝向：</span><p><a id="sschaoxiang" href="javascript:void(0);">相似朝向</a>${house.orientations.orientationName}</p></li>
                    	<li><span>所属小区：</span><p class="p"><a id="ssxiaoqu" href="javascript:void(0);">相似小区</a>${house.community.communityName}</p></li>
                    </ul>
                </div>
                <div class="san_k">
                	<ul>
                    	<li><p>带看量</p><b>${house.daikan}</b></li>
                    	<li><p>浏览量</p><b>1</b></li>
                    	<li class="nob"><p>评价</p><b>1</b></li>
                    </ul>
                </div>
                <div class="yuyue" style="background-color:#eeeeee"><a href="javascript:void(0);" >预约服务</a></div>
                <div class="dbfx"><a style="color:#eeeeee">对比</a><a style="float:left;width:1px;padding:0;color:#eeeeee">|</a><div class=" sharePaddingRight" style="float:left;color:#eeeeee" ><a style="padding:0 7px;width:24px;height:24px;line-height:26px;margin:0;color:#eeeeee">分享</a></div><a style="float:left;width:1px;padding:0;">|</a><a href="javascript:void(0)"  style="float:right;color:#eeeeee">虚假举报</a></div>
            </div>
            <div class="man">
            	<div class="r_bh">内部编号：<br>${house.hourseNo}</div>
                <div class="wd_lx">需要帮助请联系我</div>
                <div class="rwtp"><a href="#"><img onclick="window.open('${globalUrl}broker.show?actionMethod=brokerDetail&brokerId=${house.broker.erpId}&housetype=2')" src="${pictureHost}${house.broker.photograph}" alt=""/></a></div>
                <div class="m_con">
                    <div class="r_bh_c">内部编号：<br>${house.hourseNo}</div>
                    <div class="wd_lx_c">需要帮助请联系我</div>
                    <div class="name">${house.broker.store.storeName} - ${house.broker.bname}</div>
                    <div class="ihp">${house.broker.telephone}</div>
                    <div class="qqwx"><c:if test="${house.broker.qq != null && house.broker.qq != ''}"><a href="tencent://message/?uin=${house.broker.qq }&Site=qq&Menu=yes" onclick="qqcao(${house.broker.qq })"><img src="${globalUrl}image/qq.gif" alt=""/></a></c:if><c:if test="${house.broker.weCharUrl != null && house.broker.weCharUrl != ''}"><a><img src="${globalUrl}image/wx.gif" onclick="wchatDisplay();"/></a></c:if></div>
                </div>
                <div class="ewm" style="overflow: hidden;"><img id="qrCode" style="float:right;" src="" width="68" height="67" alt=""/><p><a onclick="alertQrCode2();">扫描二维码获取房源信息</a></p></div>
            </div>
        </div>
        <%-- <div id="fangda" style="display: none;"><img src="${globalUrl}houseSpecial.show?actionMethod=drawcode&houseId=${houseId}&agentId=${houseId}&houseType=2&houseNo=${house.hourseNo}&pictureType=alert"/></div> --%>
        <div id="BrokerWexin" style="display: none;"><img src="${pictureHost }${house.broker.weCharUrl}"/></div>
    	<!--详情页头结束-->
    	<!--内容开始-->
        <div class="nr">
        	<div class="nr_tit" id="checkbardiv">
                <div class="tit_a" id="titleBar">
                	<span onclick="arrowLeft();" class="myjt"></span>
                	<div id="nvBar">
                		<div id="barbar">
                			<a id="details" onclick="goDiv('${globalUrl}', 'itemcontent1')" class="a_1 one">房源详情</a>
		                	<a id="housePhoto" onclick="goDiv('${globalUrl}', 'itemcontent2')" class="a_2">室内照片</a>
		                	<a id="facilities" onclick="goDiv('${globalUrl}', 'itemcontent3')" class="a_3">生活配套</a>
		                	<a id="photo" onclick="goDiv('${globalUrl}', 'itemcontent4')" class="a_4">小区相册</a>
		                	<a id="price" onclick="goDiv('${globalUrl}', 'itemcontent5')">价格走势</a>
                		</div>
                	</div>
                	<span onclick="arrowRight();" class="myjtr" style="float:left;"></span>
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
                    <div class="nr_wd contentMore"><c:out value="${house.content }"></c:out></div>
                    <div class="nr_wd contentMore">
                    <c:forEach items="${house.community.nearStoreList}" var="nearStore" begin="0" end="0" varStatus="status">
                                                             最近门店：<c:out value="${nearStore.storeName }"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;
                                                             地址：<c:out value="${nearStore.storeAddress}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;                                 
                                                             电话：<c:out value="${nearStore.telephoneNo }"></c:out><br/>
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
                    	<p>室内照片共有 <c:out value="${housePics.size()}"/> 张照片 </p>
                    	<ul>
                    	<c:forEach items="${housePics}" var="headpic" varStatus="status" begin="0" end="0">
                        	<li><a href="javascript:void(0);"><img src="${pictureHost}${headpic.picUrl}" alt="" onclick="clickImage(${status.index+1});" /></a></li>
                        </c:forEach>
                        <c:forEach items="${housePics}" var="headpic" varStatus="status" begin="1">
                        	<li class="photoMore"><a href="javascript:void(0);"><img src="${pictureHost}${headpic.picUrl}" alt="" onclick="clickImage(${status.index+1});" /></a></li>
                        </c:forEach>
                        </ul>
                    </div>
                    <div class="shou" id="morePhoto"><a onclick="clickPhotoMore();" class="jia">展开更多</a></div>
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
                        	<li>竣工时间<p><fmt:formatDate value="${house.community.buildYear}" pattern="yyyy.MM.dd"/></p></li>
                        	<li>开发商<p>${house.community.developer }</p></li>
                        	<li>绿化率<p>${house.community.landScaping }%</p></li>
                        </ul>
                    </div>
                	<div class="d_x">
                    	<ul>
                        	<li>交通<p><c:forEach items="${cStaMapping }" var="cstms">
								${cstms.station.stationName },
							</c:forEach>
							<c:forEach items="${cSubMapping }" var="csubm">
								${csubm.subwayStation.stationName },
							</c:forEach></p></li>
                        </ul>
                    </div>
                    <div class="pt_jj">
                        <div class="pt_jj_tt"><a id="checkbtn2" onclick="changeAround();"  class="one">周边配套</a><a id="checkbtn1" onclick="changeStreetView();" >小区街景</a></div>
                        <div id="streetstreet" style="margin-top:10px; height: 248px; border: 4px solid #e8e8e8;">地图</div>
						<div id="streetmap" style="margin-top:10px; height: 248px; border: 4px solid #e8e8e8; display: none;">街景</div>
                    </div>
                    <div class="bq_nr moreMap">
                    	<div class="bq_nr_tt"><a id="traffic" onclick="_searchKeyword('交通',5,'traffic')" class="one">交通设施</a>
                    	<a onclick="_searchKeyword('学校',5,'school')" id="school">教育机构</a>
                    	<a onclick="_searchKeyword('医院',5,'hospital')" id="hospital">医疗机构</a>
                    	<a onclick="_searchKeyword('酒店',5,'hotal')" id="hotal">餐饮娱乐</a>
                    	<a onclick="_searchKeyword('超市',5,'supermarket')" id="supermarket">商场超市</a>
                    	<a onclick="_searchKeyword('公园',5,'park')" id="park">景观环境</a>
                    	</div>
                        <div class="bq_nr_bd" id="searchBody" >
                        	<div id="trafficeDiv" style="border: 1px solid #F5F5F5; min-height:50px; text-align: center; ">
							</div>
                        </div>
                    </div>
                    <div class="shou" id="mapMore"><a onclick="clickMoreMap();" class="jia">展开更多</a></div>
                </div>
            </div>
            <div class="nr_ar" id="itemcontent4">
            	<div class="nr_zb zb_4">
                	<div class="biao"></div>
                    <div class="b_wd">小区相册</div>
                </div>
                <div class="nr_yb">
                	<div class="nr_xcls">
                    	<p>小区共有 <c:out value="${communityPics.size()}"/> 张照片<c:if test="${communityPics.size() >0 }"> <a href="javascript:void(0);" onclick="window.open('${globalUrl}community.show?actionMethod=communityDetail&id=${communityPics.get(0).houseId}&mao=photo');return false;">浏览全部照片</a></c:if></p>
                    	<ul>
                    	<c:forEach items="${communityPics }" var="headpic" begin="0" end="0" varStatus="status">
                        	<li><a href="javascript:void(0);"><img src="${pictureHost }${headpic.picUrl }" alt="" onclick="clickCommImage(${status.index+1});"/></a></li>
                        </c:forEach>
                        <c:forEach items="${communityPics }" var="headpic" begin="1" varStatus="status">
                        	<li class="communityPhotoMore"><a href="javascript:void(0);"><img src="${pictureHost }${headpic.picUrl }" alt="" onclick="clickCommImage(${status.index+1});" onclick="clickCommImage(${status.index+1});"/></a></li>
                        </c:forEach>
                        </ul>
                    </div>
                    <div class="shou" id="moreCommunityPhoto"><a onclick="clickCommunityPhotoMore();" class="jia">展开更多</a></div>
                </div>
            </div>
            <div class="nr_ar" id="itemcontent5">
            <div class="nr_zb zb_5">
               	<div class="biao"></div>
                   <div class="b_wd">价格走势</div>
               </div>
            <div class="nr_yb">
                	<div id="priceChart" class="jgzs" style="width:390px; height:280px;">
					</div>
					<div id="priceChart3" class="jgzs" style="width:240px; height:140px;">
					</div>
					<div class="jgzssj" id="zsHidden">
						<div style="float:left;margin-top:5px">
							<div class="jtsj" style="background-color:#64AD70;">
								所在小区 本月均价
								<p>元/月</p>
								<fmt:formatNumber value="${thisMonthPriceCommunity}"/>
							</div>
							<div class="jtsj"  style="background-color:#DBDBDB;color:#000000;">
								所在小区 上月均价
								<p>元/月</p>
								<fmt:formatNumber value="${lastMonthPriceCommunity}"/>
							</div>
						</div>
						<div class="bfb" style="margin-top:5px;">
							<span class="bfbwz">${house.community.communityName}比上月<c:if test="${aroundLaseMonthCommunity ge 0}">增长</c:if><c:if test="${aroundLaseMonthCommunity lt 0}">下降</c:if></span>
							<span class="bfbsz" style="color:<c:if test="${aroundLaseMonthCommunity ge 0}">#F55A5A</c:if><c:if test="${aroundLaseMonthCommunity lt 0}">#64AD70</c:if>">
							 <c:if test="${aroundLaseMonthCommunity eq 0}">+${aroundLaseMonthCommunity }%</c:if><c:if test="${aroundLaseMonthCommunity gt 0}">+${fn:substring(aroundLaseMonthCommunity, 1, 5 )}%</c:if><c:if test="${aroundLaseMonthCommunity lt 0}">-<c:out value="${fn:substring(aroundLaseMonthCommunity, 1, 5 )}"></c:out>%</c:if>
							</span>
						</div>
						
					</div>
                    <div class="shou"><a class="jia" id="zsShow">展开更多</a></div>
                </div>
            </div>
        </div>
    	<!--内容结束-->
    	<!--顾问开始-->
        <div class="guwen">
        	<div class="nr_tt"><span>共有 ${appraiseList.size()} 位置业人员服务于本房源</span><b>服务本房源的置业顾问：</b></div>
            <div class="gw_ls">
            	<ul>
            	<c:forEach items="${appraiseList }" var="apps" begin="0" end="2">
                	<li>
                    	<div class="gwls_t" onclick="window.open('${globalUrl}broker.show?actionMethod=brokerDetail&brokerId=${apps.broker.erpId}&housetype=1')"><a href="javascript:void(0);"><img src="${pictureHost }${apps.broker.photograph}" alt=""/></a></div>
                        <div class="gw_r">
                        	<div class="gwname">${apps.broker.store.storeName} - ${apps.broker.bname}</div>
                            <div class="gw_tel">
                            <c:if test="${apps.broker.telephone != ' ' && apps.broker.telephone != null}"><b>${apps.broker.telephone}</b></c:if>
                            <c:if test="${apps.broker.qq != ' '  && apps.broker.qq != null}"><span><a href="tencent://message/?uin=${apps.broker.qq }&Site=qq&Menu=yes" onclick="qqcao(${apps.broker.qq })"></a><img src="${globalUrl}image/qq.gif" alt=""/></span></c:if>
                            <c:if test="${apps.broker.weCharUrl != '' && apps.broker.weCharUrl != null}"><span><img src="${globalUrl}image/wx.gif" onclick="showWeixin('${apps.broker.erpId }');" alt=""/></span></c:if>
                            </div>
                            <!-- <div id="BWeixin" style="display: none;"><img src="${pictureHost }${apps.broker.weCharUrl}"/></div> -->
                            <c:if test="${apps.broker ne null}">
								<div id="BWeixin_${apps.broker.erpId }" style="display: none;"><img src="${pictureHost }${apps.broker.weCharUrl}"/></div>
							</c:if>
                            <div class="gw_tj"><b>推荐理由：</b> ${apps.title}    </div>
                            <div class="gw_jj">${apps.content}</div>
                            <div class="gw_kf">近 30 天带看量：${apps.broker.daikan} 次<span>&nbsp;&nbsp;&nbsp;&nbsp;持有房源：${apps.broker.houseCount} 套</span></div>
                        </div>
                    </li>
                    </c:forEach>
                </ul>
                <div class="shou"><a href="#" class="jia">展开更多</a></div>
            </div>
             <%--
            不知道为什么 把价格走势给去掉 会导致地图显示不出 STRAT
            --%>
            <div class="nr_ar" id="itemcontent5" style="display:none;">
              <div id="priceChart" class="jgzs" style="width:390px; height:280px;"></div>
              <div id="priceChart3" class="jgzs" style="width:240px; height:140px;"></div>
            </div>
        </div>
    	<!--顾问结束-->

	<!-- 虚假举报隐藏框 -->
	<div id="dialoginform" style="display: none;">
	<%@include file="/WEB-INF/jsp/ddhb/front/company/company_shaminform_dialog.jsp" %>
</div>
<!-- 照片放大隐藏框 -->
	<div id="blackGround" class="tm_hei" style="display:none;position: fixed;"></div>
	<div id="photoAlbum" class="tcc_zp lanrenzhijia" style="display:none;position: fixed;">
		<div class="zp_con">
			<img src="${globalUrl}image/xxx.png" style="width:auto;height:auto;right:0;top:0;position: absolute;cursor: pointer;" onclick="$('#photoAlbum').hide();$('#blackGround').hide();">
			<span id="prevTop" class="btn prev"></span> 
			<span id="nextTop" class="btn next"></span>
		    	<div id="picBox" class="picBox">
				    <ul id="picCenter" class="cf">
				    <c:forEach var="pic" items="${housePics}" >
				    <li><a style="width:100%;height:100%;"><img src='${pictureHost}${pic.picUrl}'/><span>${house.community.communityName },<fmt:formatNumber value="${house.area }" pattern="#"></fmt:formatNumber>平米,${house.shi }室${house.ting }厅${house.wei }卫,<fmt:formatNumber value="${house.rentPrice}" pattern="#"></fmt:formatNumber>元/月</span></a></li>
				    </c:forEach>
				    </ul>
				</div>
		        <div id="listBox" class="listBox zp_xt">
		        	<ul id="picList" class="cf">
		        		<c:forEach var="pic" items="${housePics}" >
					    <li><i class='arr2'></i><a><img src='${pictureHost}${pic.picUrl}' /></a></li>
					    </c:forEach>
				    </ul>
		        </div>
	        </div>
	</div>
	<div id="photoAlbumHead" class="tcc_zp lanrenzhijia" style="display:none;position: fixed;">
		<div class="zp_con">
			<img src="${globalUrl}image/xxx.png" style="width:auto;height:auto;right:0;top:0;position: absolute;cursor: pointer;" onclick="$('#photoAlbumHead').hide();$('#blackGround').hide();">
			<span id="prevTopHead" class="btn prev"></span> 
			<span id="nextTopHead" class="btn next"></span>
	    	<div id="picBoxHead" class="picBox">
			    <ul id="picCenter" class="cf">
			    <c:forEach var="pic" items="${houseHeadPics}" >
			    <li><a style="width:100%;height:100%;"><img src='${pictureHost}${pic.picUrl}'/><span>${house.community.communityName },<fmt:formatNumber value="${house.area }" pattern="#"></fmt:formatNumber>平米,${house.shi }室${house.ting }厅${house.wei }卫,<fmt:formatNumber value="${house.rentPrice}" pattern="#"></fmt:formatNumber>元/月</span></a></li>
			    </c:forEach>
			    </ul>
			</div>
	        <div id="listBoxHead" class="listBox zp_xt">
	        	<ul id="picListHead" class="cf">
	        		<c:forEach var="pic" items="${houseHeadPics}" >
				    <li><i class='arr2'></i><a><img src='${pictureHost}${pic.picUrl}' /></a></li>
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
	            	<li><a style="width:100%;height:100%;"><img src="${pictureHost }${headpic.picUrl }" alt=""/></a></li>
	            </c:forEach>
	            </ul>
			</div>
			<div id="listBoxComm" class="listBox zp_xt">
				<ul id="picListComm" class="cf">
	        	<c:forEach items="${communityPics }" var="headpic">
	            	<li><i class='arr2'></i><a><img src="${pictureHost }${headpic.picUrl }" alt=""/></a></li>
	            </c:forEach>
	            </ul>
			</div>
		</div>
	</div>