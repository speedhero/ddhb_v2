<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.huatek.framework.sso.SSOServiceManagement" %>
<!-- 弹出框js引用 -->
<script type="text/javascript" src="${globalUrl}js/jquery.artDialog.source.js"></script>
<script type="text/javascript" src="${globalUrl}js/artDialog.iframeTools.source.js"></script>
<link rel="stylesheet" type="text/css" href="${globalUrl}js/skins/gray.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/dlzc.css">

<div id="t_nav">
  <div class="logo logoPc" style="cursor: pointer;"><img src="${pictureHost}${homeLogoPc}" title="${homePageLogoHover }"/></div>
  <div class="logo logoPad" style="cursor: pointer;"><img src="${pictureHost}${homeLogoPad}" title="${homePageLogoHover }"/></div>
  <div class="logo logoMb" style="cursor: pointer;"><img src="${pictureHost}${homeLogoPad}" title="${homePageLogoHover }"/></div>
  <div class="dl_zc"  <c:if test="${LoginMember != null }">style="display:none;"   </c:if>><a onclick="loginBox();">登录</a>|<a onclick="registerBox();">注册</a></div>
  <div class="dl_zc_h" style="z-index:9999;  <c:if test="${LoginMember == null }">display:none </c:if>" >
  		<a href="#" class="dl_name" style=" white-space: nowrap;overflow: hidden;text-overflow: ellipsis;"><c:out value="${LoginMember.accName }"></c:out></a>
       <div class="dlh_wd" >
       <a href="${globalUrl}usercenter.do?actionMethod=appointment&isCutPage=false">预约管理</a>
       <a href="${globalUrl}usercenter.do?actionMethod=contactQuery&isCutPage=false">合同进度查询</a>
       <div class="bor"></div>
       <a href="${globalUrl}usercenter.do?actionMethod=collectment&isCutPage=false">收藏夹管理</a>
       <a href="${globalUrl}usercenter.do?actionMethod=priceNotice&isCutPage=false">置换价格订阅</a>
       <a href="${globalUrl}usercenter.do?actionMethod=cutQuestion">问答管理</a>
       <div class="bor"></div>
       <a href="${globalUrl}usercenter.do?actionMethod=scoreManager&isCutPage=false">积分管理</a>
       <a href="${globalUrl}usercenter.do?actionMethod=memberInformatinManage&isCutPage=false">个人信息管理</a>
       <a onclick="if(confirm('确认退出吗？')){return true;}else{return false;}" href="${globalUrl}sso.show?actionMethod=logout">退出登录</a>
       </div>
  </div>
  <div class="nav">
  		<ul>
  			<li class="nav_a1" style="<c:if test="${backType == 1 }">background-color:#3FB8B1;</c:if>" onclick="window.location.href='${globalUrl}welcome.show?actionMethod=welcome'"><a href="#">首页</a></li>
           <li class="nav_a2" style="<c:if test="${backType == 2 }">background-color:#3FB8B1;</c:if>" onclick="window.location.href='${globalUrl}houseSecond.show?actionMethod=dimquery&type='+type2" ><a href="#">二手房</a></li>
           <li class="nav_a3" style="<c:if test="${backType == 3 }">background-color:#3FB8B1;</c:if>" onclick="window.location.href='${globalUrl}rent.show?actionMethod=dimquery&type='+type2" ><a href="#">租赁</a></li>
           <li class="nav_a5" style="<c:if test="${backType == 5 }">background-color:#3FB8B1;</c:if>" onclick="window.location.href='${globalUrl}houseNew.show?actionMethod=dimquery'"><a href="#">新房</a></li>
			<li class="nav_a4" style="<c:if test="${backType == 4 }">background-color:#3FB8B1;</c:if>" onclick="window.location.href='${globalUrl}community.show?actionMethod=dimquery&type='+type2"><a href="#">小区</a></li>
           <li class="nav_a6" style="<c:if test="${backType == 6 }">background-color:#3FB8B1;</c:if>" ><a href="#">更多</a>
           	<div class="nav_more">
           <!-- <p><a href="${globalUrl}community.show?actionMethod=dimquery&type='+type2">小区</a></p> -->
           	<a href="#" class="a_4" onclick="window.open('${globalUrl}contract.show?actionMethod=contractQuery')">交易查询</a>
           	<a href="#" class="a_3" onclick="window.open('${globalUrl}broker.show?actionMethod=questionAndAnswer')">问答&amp;攻略</a>
           	<a href="#" class="a_1" onclick="window.open('${globalUrl}company.show?actionMethod=showinvite')">加入华邦</a>
           	<a href="#" class="a_2" onclick="window.open('${globalUrl}company.show?actionMethod=showservice')">投诉建议</a>
           	<a href="#" class="a_5" onclick="addFavour();">收藏本站</a></div>
                 </li>
        		</ul>
        </div>
     </div>
<div style="clear: both; height: 0px; overflow: hidden;"></div>
