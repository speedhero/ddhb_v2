<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link type="text/css" rel="stylesheet" href="${globalUrl}css/jquery.slider.css" />
<!--[if IE]>
<style type="text/css">?
.valin{ display:block;}
.valin i { display:inline-block; height:100%; vertical-align:middle }
.valin img { vertical-align:middle }
</style>
<![endif]-->
<script type="text/javascript">
var _current_num = 0;

function clickImage2(idx){
	_current_num = idx;
	$("#fullmask2").css({
    height: function () { return $(document).height(); }
	});
	$("#fullmask2").show();
	resizeAlertImage("photoAlbum2");
	$("#photoAlbum2").show();
}
function hideAlbum2(){
	$('#photoAlbum2').hide();
	$('#fullmask2').hide();
}
$(window).scroll(function () {
	resizeAlertImage("photoAlbum2");
});
</script>
<div id="fullmask2" style="display:none;"></div>
<div id="photoAlbum2" style="display:none;">
	<div class="album-box" id="album">
		<div class="album-top-bar"><a onclick="hideAlbum2();">X</a></div>
		<div class="pre-btn" id="preBtn2" title="上一张 "><i></i></div>
		<div class="next-btn" id="nextBtn2" title="下一张 "><i></i></div>
		<div class="album-con">
			<%-- <div class="valin"><i></i><img src="images/a/1.jpg" alt="" id="realImg" /><p class="pic-alt" id="txtDes"></p></div> --%>
			<div class="valin"><i></i><img src="${globalUrl}images/save2.png" alt="" id="realImg2" /><p class="pic-alt" id="txtDes2"></p></div>
			<div id="imgLoading2"></div>
		</div>
		<div class="thum-box" id="thumBox2">
			<div class="thum-wrap" id="thumWrap2"><ul class="imglist fix" id="photoList2"></ul></div>
			<i class="thum-pre-btn" id="thumPreBtn2" title="上一页"></i>
			<i class="thum-next-btn" id="thumNextBtn2" title="下一页"></i>
		</div>
	</div>
		<textarea class="dn" id="albumDataList2">
			<c:forEach var="pic" items="${communityPics}" varStatus="status">
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
