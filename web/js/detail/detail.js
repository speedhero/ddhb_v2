/**二手房 and 租赁 _详情 20150518**/

/**点击跳到指定div中**/
function goDiv(globalUrl, div, self) {
	if(self){
		//	二手房和租赁
	var position = $("#checkbardiv").css("position");
	var scroll_offset;
	if(position != "fixed") {
		scroll_offset = $("#" + div).offset().top - $("#checkbardiv").height() - 80;
		//小区详情滚动
		xiaoquXQ();
	} else {
		scroll_offset = $("#" + div).offset().top - $("#checkbardiv").height();
	}
	 $("html,body").animate({scrollTop:scroll_offset}, 'slow'); 
	}else{
		//	 小区详情滚动
	xiaoquXQ();
	var navH = $("#checkbardiv").offset().top;
		tmp = navH;
 	
 	$("#titleBar").find(".one").removeClass("one");
		$(self).addClass("one");
		var position = $("#checkbardiv").css("position");
		var scroll_offset;
		if(position != "fixed") {
			scroll_offset = $("#" + div).offset().top - $("#checkbardiv").height() - 80;
		} else {
			scroll_offset = $("#" + div).offset().top - $("#checkbardiv").height();
		}
		 $("html,body").animate({scrollTop:scroll_offset}, 'slow'); 
		 
		 	reloadCompareDiv("communityCompare",globalUrl);
			reloadHistoryDiv("communityHistory",globalUrl,$("#loginStatus").val());
			reInitCompareDiv('communityCompare',globalUrl);
	}
};
//小区详情滚动
function xiaoquXQ(){
	 $("#changePart1").show();
	 $("#changePart2").show();
	 $('#communityRentSecond').empty();
	 $("#communityRentSecond").css("display", "none");
	 $("#houseSecondBar").removeClass("one");
	 $("#houseRentBar").removeClass("one");
}

//收藏二手房_租赁  租赁cType：0 二手房cType：1, id 为 小区 传过来的 
 function _keepOppen(actionUrl, _platCollection, cType, id){
	var platCollection = _platCollection;
	var zTypeName = (cType=="1" || cType=="0")? "房源": "小区";
	zTypeName = cType == "4" ? "新房":zTypeName;
	$.ajax({
		type : "post",
		url : actionUrl,
		dataType : "json",
		data : platCollection,
		success : function (data) {
			if (data.result == 'success') {
				if(cType = '3'){
					//$("#"+id).css("background-color", '#cb4f4d');
					$("#"+id).attr("isCollect", "0");
				}else if(id){
						$("#"+id).attr("collId", data.collectId);
						//$("#"+id).css("background-color", '#cb4f4d'); 
						$("#"+id).attr("isCollect", "0");
				}
				alert("收藏成功！");
			} else if (data.result == 'recall') {
				alert("您已经添加过该"+zTypeName+"了！");
			} else {
				alert("该房"+zTypeName+"加失败！");
			}
		}
	});
}
//取消收藏 二手房0、租赁1、小区3
function _keepOff(actionUrl, id, cType){
	var _cType = (cType == "0" || cType == "1" )? "房源" : "小区";
$.ajax({
	type : "post",
	url :  actionUrl,
	dataType : "json",
	success : function (data) {
		if (data.result == 'success') {
			if(id){
			$("#"+id).css("background-color", '#adadad'); 
			$("#"+id).attr("isCollect", "1");
			}
		} else if (data.result == 'recall') {
			alert("您已经取消过该"+ _cType +"了！");
		} else {
			alert("该"+ _cType +"取消失败！");
		}
	}
});
} 

//虚假举报
function shaminform(){
	var dataContent = document.getElementById("dialoginform");
	art.dialog({
		id:'shaminformid',
 		title: "&nbsp;&nbsp;虚假举报",
 		content: dataContent,
 		lock: true,
 		drag: false,
 	    resize: false,
 	    max: false,
 	    min: false,
 	  	zIndex: 99999
	});
}

function _consignment(actionUrl){
	$.ajax({
		type : "post",
		url : actionUrl ,
		dataType : "html",
		async:false,
		success : function (data) {
			var dataContent = '<html>'+data+'</html>';
			art.dialog({
				id:'consignment',
		 		title: "&nbsp;&nbsp;置业顾问",
		 		content: dataContent,
		 		lock: true,
		 		drag: false,
		 	    resize: false,
		 	    max: false,
		 	    min: false,
		 	   	zIndex: 99999
			});
			refresh();
		}
	});
};

function clickCommunityPhotoMore(){
	$(".communityPhotoMore").removeClass("communityPhotoMore");
	$("#moreCommunityPhoto").hide();
}
function clickMoreMap(){
	$(".moreMap").css("display", "block");
	$("#mapMore").css("display", "none");
}

function clickPhotoMore(){
	$(".photoMore").removeClass("photoMore");
	$("#morePhoto").hide();
}
function clickContentMore(){
	$(".contentMore").removeClass("contentMore");
	$("#moreContent").hide();
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
	var showLeft = 0;
	if (leftCount < 70){
		showLeft = 0;
	}else{
		showLeft = containerWidth +70;
	}
	var ShowLeftStr = showLeft + "px";
	$("#barbar").animate({"margin-left":ShowLeftStr}, 'slow'); 
}


function alertQrCode2(){
	var dataContent = document.getElementById("qrCode2");
	art.dialog({
		id:'qrCode2',
 		title: "扫描二维码",
 		content: dataContent,
 		lock: true,
 		drag: false,
 	    resize: false,
 	    max: false,
 	    min: false,
 	  	zIndex: 99999
	});
}

//function clickImage(index){
//	$("#photoAlbum").show();
//}
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

window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"1","bdMiniList":["qzone","tsina","weixin","tqq","sqq"],"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};
with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
//价格走势图 
//利用JSTL动态定义多维数组
//小区的没有 合并进来
function _initCharts(size, _arrayz, number){
	var arrayz = _arrayz;
	var formatDateString = '%Y-%m';
	var winWidth = 1024;
	if (window.innerWidth) {
		winWidth = window.innerWidth;
	} else if ((document.body) && (document.body.clientWidth)) {
		winWidth = document.body.clientWidth;
	}
	if (winWidth <= 758) {
		formatDateString = '%m';
	}
	var plot2 = $.jqplot('communityprice'+number, [ arrayz ], {
		title : '价格走势图',
		axes : {
			xaxis : {
				numberTicks : size,
				pad : 1,
				showTicks : true,
				tickInterval : '1 month',
				renderer : $.jqplot.DateAxisRenderer,
				tickOptions : {
					formatString : formatDateString
				}
			},
			yaxis : {
				tickOptions : {
					formatString : '￥%.2f'
				}
			}
		},
		highlighter : {
			show : true,
			sizeAdjust : 10
		}
	});
	var plot3 = $.jqplot('communityprice'+ number + ''+ number, [ arrayz ], {
		title : '价格走势图',
		axes : {
			xaxis : {
				numberTicks : size,
				pad : 1,
				showTicks : true,
				tickInterval : '1 month',
				renderer : $.jqplot.DateAxisRenderer,
				tickOptions : {
					formatString : formatDateString
				}
			},
			yaxis : {
				tickOptions : {
					formatString : '￥%.2f'
				}
			}
		},
		highlighter : {
			show : true,
			sizeAdjust : 10
		}
	});
}
