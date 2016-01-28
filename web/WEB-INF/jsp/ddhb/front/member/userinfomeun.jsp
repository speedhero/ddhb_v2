<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/css.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/more.css">
<title>个人中心 - ${title } </title>
<script type="text/javascript">
$(function(){
	var flag = $("#type").val();
	$(".body").addClass("geren");
	$("#no" + flag).addClass("one");
});
</script>
<input id="type" type="hidden" value="${flag }">
<div class="cp_left">
	<ul>
    	<li id="no1"><a href="javascript:void(0);" onclick="window.location.href='${globalUrl}usercenter.do?actionMethod=appointment&isCutPage=false'">预约管理<span></span></a></li>
    	<li id="no2"><a href="javascript:void(0);" onclick="window.location.href='${globalUrl}usercenter.do?actionMethod=contactQuery&isCutPage=false'">合同进度查询<span></span></a></li>
    	<li id="no3"><a href="javascript:void(0);"  onclick="window.location.href='${globalUrl}usercenter.do?actionMethod=collectment&isCutPage=false'">收藏夹管理<span></span></a></li>
    	<li id="no4"><a href="javascript:void(0);" onclick="window.location.href='${globalUrl}usercenter.do?actionMethod=priceNotice&isCutPage=false'">置换价格订阅<span></span></a></li>
    	<li id="no5"><a href="javascript:void(0);" onclick="window.location.href='${globalUrl}usercenter.do?actionMethod=cutQuestion'">问答管理<span></span></a></li>
    	<li id="no6"><a href="javascript:void(0);" onclick="window.location.href='${globalUrl}usercenter.do?actionMethod=browseHistory&isCutPage=false'">我的足迹<span></span></a></li>
    	<li id="no9"><a href="javascript:void(0);" onclick="window.location.href='${globalUrl}saveSearchField.show?actionMethod=listAll&isCutPage=false'">搜索保存记录<span></span></a></li>
    	<li id="no7"><a href="javascript:void(0);" onclick="window.location.href='${globalUrl}usercenter.do?actionMethod=scoreManager&isCutPage=false'">积分管理<span></span></a></li>
    	<li id="no8"><a href="javascript:void(0);" onclick="window.location.href='${globalUrl}usercenter.do?actionMethod=memberInformatinManage&isCutPage=false'">个人信息管理<span></span></a></li>
    </ul>
</div>
