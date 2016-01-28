<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="header"></div>
<div class="about_main"> 
	<%@include file="/WEB-INF/jsp/ddhb/front/company/company_common_menu.jsp" %>
	<div class="cp_rt">
        	<div class="licn"><a onclick='window.location.href="${globalUrl}welcome.show?actionMethod=welcome"'>首页</a>&nbsp;>&nbsp;联系我们</div>
            <div class="cont_info">
            <p>${contact.companyAddress}</p>
        	</div>
    </div>
</div>
<script type="text/javascript"></script>