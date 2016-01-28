<%@page import="org.apache.el.lang.ELSupport"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<title>活动详情 - ${title }</title>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/css.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/more.css">
<style>
.ht_left { background: none repeat scroll 0 0 #fff; float: left; margin-bottom: 60px; width: 100%; }
.ht_form { padding-bottom: 20px; font-size: 14px; }
#adContent span { float: none; }
</style>
<div style="margin-top: 20px; font-size: 12px; color: #999999; margin-bottom: 10px;">
	<span><a onclick='window.location.href="${globalUrl}welcome.show?actionMethod=welcome"' style="cursor: pointer;">首页</a>>活动详情</span>
</div>
<!--活动详情开始-->
<div class="ht_left" id="adContent">
	<div class="ht_tit">
		<a href="javascript:void(0);" class="one">${commonActivity.itemName }</a>
	</div>
	<div class="ht_con">
		<div class="ht_form">${commonActivity.content }</div>
	</div>
</div>
<!--活动详情结束-->