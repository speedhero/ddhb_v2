<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link type="text/css" rel="stylesheet" href="${globalUrl}css/jquery.slider.css" />
<!--[if IE]>
<style type="text/css">﻿
.valin{ display:block;}
.valin i { display:inline-block; height:100%; vertical-align:middle }
.valin img { vertical-align:middle }
</style>
<![endif]-->
<script type="text/javascript">
var _current_num = 0;
function resizeAlertImage(div) {
	var left = ($(document).width() - $("#" + div).width()) / 2 + "px";
	//var top = ($(window).height() - $("#" + div).height()) / 2 + "px";
	var top = $(document).scrollTop() + 1;
	$("#" + div).css("margin", "0").css("left", left).css("top", top);
}

function clickImage(idx){
	_current_num = idx;
	$("#fullmask").css({
    height: function () { return $(document).height(); }
	});
	$("#fullmask").show();
	resizeAlertImage("photoAlbum");
	$("#photoAlbum").show();
}
function hideAlbum(){
	$('#photoAlbum').hide();
	$('#fullmask').hide();
}
$(window).scroll(function () {
	resizeAlertImage("photoAlbum");
});
function scaleImage(o, w, h) {
	var img = new Image();
	img.src = o.src;
	if (img.width > 0 && img.height > 0) {
		if (img.width / img.height >= w / h) {
			if (img.width > w) {
				o.width = w;
				o.height = (img.height * w) / img.width;
			} else {
				o.width = img.width;
				o.height = img.height;
			}
			// o.alt = img.width + "x" + img.height;
		} else {
			if (img.height > h) {
				o.height = h;
				o.width = (img.width * h) / img.height;
			} else {
				o.width = img.width;
				o.height = img.height;
			}
			//o.alt = img.width + "x" + img.height;
		}
	}
}
</script>
<div id="fullmask" style="display:none;"></div>
<div id="photoAlbum" style="display:none;">
	<div class="album-box" id="album">
		<div class="album-top-bar"><a onclick="hideAlbum();">X</a></div>
		<div class="pre-btn" id="preBtn" title="上一张 "><i></i></div>
		<div class="next-btn" id="nextBtn" title="下一张 "><i></i></div>
		<div class="album-con">
			<%-- <div class="valin"><i></i><img src="images/a/1.jpg" alt="" id="realImg" /><p class="pic-alt" id="txtDes"></p></div> --%>
			<div class="valin"><i></i><img src="${globalUrl}images/save2.png" alt="" id="realImg" /><p class="pic-alt" id="txtDes"></p></div>
			<div id="imgLoading"></div>
		</div>
		<div class="thum-box" id="thumBox">
			<div class="thum-wrap" id="thumWrap"><ul class="imglist fix" id="photoList"></ul></div>
			<i class="thum-pre-btn" id="thumPreBtn" title="上一页"></i>
			<i class="thum-next-btn" id="thumNextBtn" title="下一页"></i>
		</div>
	</div>
		<textarea class="dn" id="albumDataList">
			<c:forEach var="pic" items="${Pictures}" varStatus="status">
				<li>
					<div> <img alt="${description}" src="${pictureHost}${pic.pictureUri}" onload="scaleImage(this,99,75)" alt=""></div>
					<p></p>
					<i class="dn">${pictureHost}${pic.pictureUri }</i>
					<i class="dn imgAlt"></i>
				</li>
			</c:forEach>
		</textarea>
	</div>
<script type="text/javascript" src="${globalUrl}js/jquery.slider.js?v=1409654123"></script>
