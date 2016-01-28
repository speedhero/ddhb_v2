<%@page import="com.huatek.hbwebsite.util.FrontSystemSettingUtil"%>
<%@page import="com.huatek.hbwebsite.util.SystemTitleUtil"%>  
<%@page import="com.huatek.hbwebsite.util.CompanyInfo"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0" /> 
<title>${title } </title>
<link rel="shortcut icon" type="image/x-icon" href="${globalUrl}images/hshb16.ico" media="screen" />
<link rel="stylesheet" type="text/css" href="${globalUrl}css/home.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/index.css"/>
<script type="text/javascript" src="${globalUrl}js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${globalUrl}js/ht_frame.js"></script>

<script type="text/javascript" src="${globalUrl}js/cookie/cookie.js"></script>
<script type="text/javascript" src="${globalUrl}js/compare/frontCompare.js"></script>
<script type="text/javascript" src="${globalUrl}js/compare/frontHistory.js"></script>

<script type="text/javascript" src="${globalUrl}js/jquery.artDialog.source.js"></script>
<script type="text/javascript" src="${globalUrl}js/artDialog.iframeTools.source.js"></script>
<script type="text/javascript" src="${globalUrl}js/cookie/cookie.js"></script>
<script type="text/javascript" src="${globalUrl}js/hshb.common.js"></script>

<!-- 下面的日期控件不能跨域 -->
<script type="text/javascript">${qqService}</script>
<script type="text/javascript" src="${globalUrl}js/enterpriseQQ.js"></script>

<script type="text/javascript">
var globalUrl = '${globalUrl}';
var cannot_connect_server = '不能连接到应用服务器，请检查网络或服务是否开启。';
var welcome='<huatek:urlDecode value="${welcome}"/>';
$(document).ready(function(){
  if(welcome!=''){
    getData(welcome,'','workspace');
  }
});
</script>
<style>
.content{ font-family:"Microsoft YaHei",微软雅黑,"MicrosoftJhengHei",华文细黑,STHeiti,MingLiu; }
.condtentDiv{ font-weight:normal; font-style:normal; text-decoration:none; color:#333333; margin:0 auto; padding:0px; min-height:500px; height:auto; position:relative; }
.rightBar{ position:fixed; height:365px; width:64px; z-index:9999; cursor:pointer; top:170px; right:0; }
</style>
<script type="text/javascript">
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?c657266d252fd02531ca2079661b96c0";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
</head>

<body>
  <div class="topHeader"><div class="header"><tiles:insertAttribute name="ddhb.header" /></div></div>
  <div class="bannerBar" style="background-color: ${backColor }">
    <div class="backImg"><img src="${pictureHost }${backImg }" alt="" /></div>
  </div>
  <div class="body">
    <div id="body">
      <div id="workspace"><tiles:insertAttribute name="ddhb.body" /></div>
    </div>
    <tiles:insertAttribute name="ddhb.footer" />
  </div>
  <div class="rt_fda">
  <input type="hidden" id="loginStatusForIcon" value="<c:if test="${LoginMember == null }">no</c:if>" />
    <a href="javascript:void(0);" <c:if test="${LoginMember == null }">onclick="loginBox('favourate');"</c:if>
        <c:if test="${LoginMember != null }">onclick="favouritesManagement();"</c:if> class="fda_1"></a>
    <a onclick="openHistory('${showCompare}','${globalUrl}');" class="fda_2"></a>
    <a href="javascript:void(0);"  <c:if test="${LoginMember == null }">onclick="loginBox('memberPoints');"</c:if>
        <c:if test="${LoginMember != null }">onclick="memberPoints();"</c:if> class="fda_3"></a>
   <%--  <a onclick="talkQQ()" class="fda_4"></a>--%>
    <a href="javascript:void(0);" id="turnToTopButton" onclick="turnToTop();" style="display:none;" class="fda_5"></a>
  </div>
</body>
</html>