<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript"></script>
<div class="header"></div>
<div class="about_main"> 
  <%@include file="/WEB-INF/jsp/hshb/front/company/company_common_menu.jsp" %>
  <div class="cp_rt">
	<div class="licn"><a href="${globalUrl}">首页</a>&nbsp;>&nbsp;关于华邦</div>
	   <div class="cont_info">${company.introduceContent}</div>
  </div>
</div>
