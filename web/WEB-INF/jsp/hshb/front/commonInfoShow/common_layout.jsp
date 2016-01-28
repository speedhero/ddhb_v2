<%@page import="com.huatek.hbwebsite.util.FrontSystemSettingUtil"%>
<%@page import="com.huatek.hbwebsite.util.SystemTitleUtil"%>
<%@page import="com.huatek.hbwebsite.util.CompanyInfo"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="h" uri="/WEB-INF/tld/hshb.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><tiles:getAsString name="common.title" /><h:meta infoType="title" /></title>
<meta name="keywords" content="<h:meta infoType="keyword" />" />
<meta name="description" content="<h:meta infoType="description" />" />
<link rel="shortcut icon" type="image/x-icon" href="${globalUrl}images/hshb16.ico" media="screen" />
<tiles:insertAttribute name="common.css" />
<tiles:insertAttribute name="common.js" />
</head>

<body id="workspace" style="margin-top: 0px;">
  <div class="body">
    <div id="body">
      <div>
        <tiles:insertAttribute name="common.header" />
      </div>
      <div id="mainBody">
				<div>
					<tiles:insertAttribute name="common.topbanner" />
				</div>
        <div>
          <tiles:insertAttribute name="common.body" />
        </div>
      </div>
			<tiles:insertAttribute name="common.compare_history" />
    </div>
  <div class="footerDiv">
    <tiles:insertAttribute name="common.footer" />
  </div>
  </div>
  <div class="rt_fda">
    <input type="hidden" id="loginStatusForIcon" value="<c:if test="${LoginMember == null }">no</c:if>" /> 
    <a <c:if test="${LoginMember == null }">onclick="loginBox('favourate');"</c:if> <c:if test="${LoginMember != null }">onclick="favouritesManagement();"</c:if> class="fda_1"></a> 
    <a class="fda_2" onclick="openHistory('${showCompare}','${globalUrl}');"></a> 
    <a <c:if test="${LoginMember == null }">onclick="loginBox('memberPoints');"</c:if> <c:if test="${LoginMember != null }">onclick="memberPoints();"</c:if> class="fda_3"></a> 
  	<%--   <a onclick="talkQQ()" class="fda_4"></a>--%> 
    <a id="turnToTopButton" onclick="turnToTop();" style="display: none;" class="fda_5"></a>
  </div>
</body>
</html>