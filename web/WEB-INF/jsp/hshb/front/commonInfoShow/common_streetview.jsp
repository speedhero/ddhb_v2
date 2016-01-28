<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
if(typeof(_location) =="undefined" )
	var _location;

var searchService, qqMap, markers = [];
$(document).ready(function(){
	initTopMap();

	initLargeMap();

	initStreetView();
	
	initMapSearchBtn();
	
	$("#traffic").click();
});

/**
 * 获取小区地理坐标
 */
function getLocation(){
	var _location = "${house.community.location}";
	var _loc = _location || "30.273790,120.155100"; //如果未设置坐标，则默认为杭州市政府所在地
	var arrLocation = _loc.split(",");
	return arrLocation;
}

/**
 * 获取地图默认缩放比例
 */
function getMapZoom(){
	//地图默认缩放比例
	return 15;
}
/**
 * 初始化顶部小地图
 */
function initTopMap(){
	var pos = getLocation();
	try{
		/**上方小地图**/
		var centerPos = new qq.maps.LatLng(pos[0], pos[1]);
 		var _topMap = new qq.maps.Map(document.getElementById("topmap"), {
       // 地图的中心地理坐标。
       center: centerPos,
       zoom: getMapZoom()
 		});
    var marker = new qq.maps.Marker({
        position: centerPos,
        map: _topMap
		});
	}catch(e){
		if(window.console) console.log(e);
	}	
}

/**
 * 初始化大地图
 */
function initLargeMap(){
	var _innerHTMLTempl = '<div style="width: 100%; margin-top:5px;">' 
		+ '<div style="width:63%; float: left; border-bottom:1px dashed #F5F5F5; border-right: 1px solid #F5F5F5;">'
   	+ '<div style="background-image:url('+globalUrl+'images/housedetail/distantCircle.png) ;float:left;font-size:10px;color:#ffffff;width:14px;height:14px;line-height:13px;">_INDEX_</div>'
   	+ '<span class="mapFontSize" style="float: left; margin-left:6px;color:#666666">_NAME_&nbsp;&nbsp;_ADDRESS_</span>'
   	+ '</div>'
   	+ '<div style="width:36%;float: left; text-align: center; border-bottom:1px dashed #F5F5F5;">'
   	+ '<span class="mapFontSize" style="color:#575757">距离<span class="mapWordFontSize" style="color:#ff9b4b;">_DISTINCE_</span>米，步行约<span class="mapWordFontSize" style="color:#ff9b4b;">_TIME_</span>分钟</span>'
   	+ '</div>'
   	+ '<div style="clean:both;"></div>'
		+ '</div>'
		+ '<div style="clear:both;"></div>';

	var pos = getLocation();
	try{
		var centerPos = new qq.maps.LatLng(pos[0], pos[1]);
 		qqMap = new qq.maps.Map(document.getElementById("streetstreet"), {
       // 地图的中心地理坐标。
       center: centerPos,
       zoom: getMapZoom()
 		});

 		/*
 		var anchor1 = new soso.maps.Point(0, 0);
 		var size1 = new soso.maps.Size(24, 30);
 		var icon1 = new soso.maps.MarkerImage(globalUrl+'images/maker.png', size1, anchor1);
 	  var markerBig = new soso.maps.Marker({ icon: icon1, map: qqMap, position:centerPos});
		*/
 		var anchor1 = new qq.maps.Point(0, 0);
 		var size1 = new qq.maps.Size(24, 30);
 		var icon1 = new qq.maps.MarkerImage(globalUrl+'images/maker.png', size1, anchor1);
 	  var markerBig = new qq.maps.Marker({ icon: icon1, map: qqMap, position:centerPos});
		
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
        		var _inHtml = ((((_innerHTMLTempl.replace("_NAME_", pois[i].name))
        												.replace("_ADDRESS_", pois[i].address))
        												.replace("_DISTINCE_", pois[i].dist))
        												.replace("_TIME_", min))
        												.replace("_INDEX_", (i+1));
        		$(_inHtml).appendTo('#trafficeDiv');
        	}
        }
        for(var i = 0,l = pois.length;i < l; i++){
            var poi = pois[i];
            latlngBounds.extend(poi.latLng);  
            var marker = new qq.maps.Marker({
                map: qqMap,
                position: poi.latLng
            });
            marker.setTitle(poi.name);
            markers.push(marker);
        }
        //根据指定的范围调整地图视野。 http://lbs.qq.com/javascript_v2/doc/map.html
        //不能启用，启用后地图会缩得很小
        //qqMap.fitBounds(latlngBounds);
      }
    });
	}catch(e){
		if(window.console) console.log(e);
	}		
}

/**
 * 初始化街景地图
 */
function initStreetView(){
	var _svZoom = 1;  //街景地图缩放比例
	var pos = getLocation();
	try{
    var latLng = new qq.maps.LatLng(pos[0],pos[1]);
    var panoService = new qq.maps.PanoramaService();
    panoService.getPano(latLng, 1000, function(result){
    	if(null != result){
    		/**街景地图**/
    		var pano = new qq.maps.Panorama(document.getElementById('streetmap'), {
    			pano: result.svid,
    			disableMove: false,
    			disableFullScreen: false,
    			zoom: _svZoom,
    			pov:{ heading:20, pitch:15 }
    		});
    	}else {
    		document.getElementById('streetmap').innerHTML = "暂无街景地图";
    	}
    });
    
		$("#checkbtn1").click(function(){
			$("#checkbtn1").addClass("one");
			$("#checkbtn2").removeClass("one");
			$("#checkbtn2").removeAttr("style").css("cursor", "pointer");
			$("#streetmap").css("display", "block");
			$("#streetstreet").css("display", "none");
		});
	
    $("#checkbtn2").click(function(){
			$("#checkbtn2").addClass("one");
			$("#checkbtn1").removeClass("one");
			$("#checkbtn1").removeAttr("style").css("cursor", "pointer");
			$("#streetmap").css("display", "none");
			$("#streetstreet").css("display", "block");
		});

	}catch(e){
		if(window.console) console.log(e);
	}
}

//清除地图上的marker
function clearOverlays(overlays){
  var overlay;
  while(overlay = overlays.pop()){
    overlay.setMap(null);
  }
}

//搜索周边设施
function searchByKeyword(keyword, number, divId){
	clearOverlays(markers);
  markers.length=0;
	
	var pos = getLocation();
  $(".bq_nr_tt").find(".one").removeClass("one");
	$('#'+divId).addClass('one');
  region = new qq.maps.LatLng(pos[0], pos[1]);
  searchService.setPageCapacity(number);
  searchService.searchNearBy(keyword, region, 1500);
}

function initMapSearchBtn(){
	var _count = 20;
	$("#traffic").click(function(){ searchByKeyword('交通', _count, 'traffic'); });
	$("#school").click(function(){ searchByKeyword('学校', _count, 'school'); });
	$("#hospital").click(function(){ searchByKeyword('医院', _count, 'hospital'); });
	$("#hotal").click(function(){ searchByKeyword('酒店', _count, 'hotal'); });
	$("#supermarket").click(function(){ searchByKeyword('超市', _count, 'supermarket'); });
	$("#park").click(function(){ searchByKeyword('公园', _count,'park'); });
}

//左上角切换到图片模式
function changePic(){
	$("#frontpic").parent().addClass("one");
	$("#frontmap").parent().removeClass("one");
	
	$("#toppic").css("display", "block");
	$("#topmap").css("display", "none");
}

//左上角切换到地图模式
function changeMap(){
	$("#frontpic").parent().removeClass("one");
	$("#frontmap").parent().addClass("one");
	$("#toppic").css("display", "none");
	$("#topmap").css("display", "block");
}

$(this).addClass("frontpicHover");
$("#frontPicImg").css("visibility", "visible"); 
if(!isIphoneSafari()) {
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
</script>
<%-- 街景地图部分 --%>
<div class="pt_jj">
	<div class="pt_jj_tt">
		<a id="checkbtn2">周边配套</a> <a id="checkbtn1" class="one">小区街景</a>
	</div>
	<div id="streetstreet" style="margin-top: 10px; height: 248px; border: 4px solid #e8e8e8; display: none;">地图</div>
	<div id="streetmap" style="margin-top: 10px; height: 248px; border: 4px solid #e8e8e8;">街景</div>
</div>
<div class="bq_nr moreMap">
	<div class="bq_nr_tt">
		<a id="traffic" class="one">交通设施</a>
		<a id="school">教育机构</a> 
		<a id="hospital">医疗机构</a>
		<a id="hotal">餐饮娱乐</a> 
		<a id="supermarket">商场超市</a>
		<a id="park">景观环境</a>
	</div>
	<div class="bq_nr_bd" id="searchBody">
		<div id="trafficeDiv" style="border: 1px solid #F5F5F5; min-height: 50px; text-align: center;"></div>
	</div>
</div>
