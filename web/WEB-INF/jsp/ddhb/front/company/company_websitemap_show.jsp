<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/company.css"/>
<div class="header"></div>
<div class="bottonpicdiv"> 
	<%@include file="/WEB-INF/jsp/ddhb/front/company/company_common_menu.jsp" %>
	<div class="cp_rt">
    	<%--<div class="licn"><a href="#">首页</a>><a href="#">诚聘英才</a></div> --%>
    	<div class="licn"><a onclick='window.location.href="${globalUrl}welcome.show?actionMethod=welcome"'>首页</a>>站点地图</div>
        <div class="news_end">
        <img src="${globalUrl}images/homepage/sitemap.png" >
     </div>
	</div>
</div>
<script type="text/javascript">

</script>