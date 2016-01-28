<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/style.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/more.css">
<title>更多 - ${title } </title>
<script type="text/javascript">
function initCss(){
	$(".body").css("background-attachment","scroll");
	$(".body").css("background-clip","border-box");
	$(".body").css("background-color","rgba(0, 0, 0, 0)");
	$(".body").css("background-origin","padding-box");
	$(".body").css("background-position","center top");
	$(".body").css("background-repeat","no-repeat");
	$(".body").css("background-size","auto auto");
}

$(function(){
	var flag = $("#type").val();
	$("#no" + flag).addClass("one");
	$("#no" + flag).nextAll(".navbutton").removeAttr("style").css("cursor", "pointer"); 
	$("#no" + flag).prevAll(".navbutton").removeAttr("style").css("cursor", "pointer");

	if(flag == "1"){
		/* $(".body").css("background-image","url('${globalUrl}images/company/aboutUs.png')");
		initCss() */
		$(".body").addClass("newsbg");
	}
	if(flag == "2"){
		$(".body").addClass("_newsbg");
	}
	if(flag == "3"){
		/* $(".body").css("background","url('${globalUrl}images/company/customerService.png')");
		initCss() */
		$(".body").addClass("servicebg");
	}
	if(flag == "4"){
		/* $(".body").css("background","url('${globalUrl}images/company/contactUs.png')");
		initCss() */
		$(".body").addClass("contentbg");
	}
	if(flag == "5"){
		/* $(".body").css("background","url('${globalUrl}images/company/interview.png')");
		initCss() */
		$(".body").addClass("invitebg");
	}
	if(flag == "6"){
		/* $(".body").css("background","url('${globalUrl}images/company/map.png')");
		initCss() */
		$(".body").addClass("_newsbg");
	}

});
</script>
<div class="cp_left">
	<input id="type" type="hidden" value="${flag }">
	<div class="ment_top">走近华邦</div>
	<ul id="mentList">
    	<li id="no1"><a href="${globalUrl}company/companyinfo" >关于我们<span></span></a></li>
    	<li id="no2"><a href="${globalUrl}company/shownews" >新闻动态<span></span></a></li>
    	<li id="no5"><a href="${globalUrl}company/showinvite" >诚聘英才<span></span></a></li>
    	<li id="no4"><a href="${globalUrl}company/showcontact" >联系我们<span></span></a></li>
    	<li id="no3"><a href="${globalUrl}company/showservice" >投诉建议<span></span></a></li>
    	<%--<li id="no6"><a href="javascript:void(0);" onclick="window.location.href='${globalUrl}company.show?actionMethod=websitemap'">站点地图<span></span></a></li>--%>
    </ul>
	<div class="content_top">联系我们</div>
	<div class="content_con">
	    地址：杭州市拱墅区潮王路225号红石中央大厦8F<br />
        电话：4008966888<br />
        邮箱：admin@hshb.cn<br />
        网址：http://www.hshb.cn
	</div>
</div>