<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!--<style>
.companycontent table th{
	text-align: left;
	padding: 0;
	border-bottom: #C3C3C3 solid 1px;
	height:30px;
	vertical-align: top;
	color: #333333;
    font-size: 12px;
    font-style: normal;
    font-weight: normal;
    text-decoration: none;
}

.ddhbeachNum ul li a {
    color: #333333;
    font-size: 11px;
    font-family: 黑体;
}
.companycontent table td{
	text-align: left;
	padding: 0;
	height:30px;
	vertical-align: bottom;
	color: #333333;
    font-size: 13px;
    font-style: normal;
    font-weight: normal;
    text-decoration: none;
}
.font{
	color: #333333;
    font-size: 13px;
    font-style: normal;
    font-weight: normal;
    text-decoration: none;
}

</style>-->
<div class="header"></div>
<div class="about_main"> 	
	<%@include file="/WEB-INF/jsp/hshb/front/company/company_common_menu.jsp" %>
	<div class="cp_rt">
		<div class="licn"><a onclick='window.location.href="${globalUrl}"' style="cursor: pointer;">首页</a>&nbsp;>&nbsp;诚聘英才</div>
		<div class="cont_news">
			<c:if test="${pageName eq 'list' }">
				<%@include file="/WEB-INF/jsp/hshb/front/company/company_invite_show_detail.jsp" %>
			</c:if>
			<c:if test="${pageName eq 'Detail' }">
				<%@include file="/WEB-INF/jsp/hshb/front/company/company_invite_show_positiondetail.jsp" %>
			</c:if>
		</div>
	</div>
	<div style="clear: both; height: 0px;"></div>
</div>
<script type="text/javascript">

</script>