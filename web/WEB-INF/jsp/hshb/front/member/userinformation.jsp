<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<title>个人中心- ${title } </title>
<style>
.usercenterContent{
	width: auto;
	height:auto;
	margin-top:32px;
	font-size:12px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;
}

.usercenterContent a{
	font-size:12px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;
}

.usercenterNavigationDiv {
	width: auto;
	height: auto;
	float: left;
	text-align:center;
	box-shadow:5px 5px 5px #888888;
	background: url('${globalUrl}images/memberCenter/PCPad-8-1.png');
}
.navbutton{
	width:195px;
	height: 54px;
}
.navbutton div{
	font-size: 16px;
	line-height: 45px;
}
.titlediv{
	height: 33px;
	width: 100.3%; 
	background: url('${globalUrl}images/memberCenter/mem.png');	
}
.title{
	margin-left: 20px; 
	line-height: 30px;
	font-size: 12px;
}
.caozuo{
	padding:5px;
	width:100%;
	height:100%;
	background:url('${globalUrl}images/memberCenter/PCPad-9-1.jpg');
	color:#000000; 
	cursor: pointer;
}

#navigation{
	font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#0000FF;
}
.recordCount {
	font-family:SimHei 黑体;
	font-size:24px;
	font-weight:normal;
	font-style:normal;text-decoration:none;color:#3FB8B1;
}
</style>
<script type="text/javascript">
var type = 1;
var jsoninit = {
	"type" : type
};
function setjsonvalue(key, value) {
	jsoninit[key] = value;
}
$(document).ready(function() {
	$("#resoved").click(function() {
		type = 1;
		setjsonvalue("type", type);
		getData('${globalUrl}usercenter.do?actionMethod=cutQuestion&type='+type,'','changelist');
	});
	$("#unresoved").click(function() {
		type = 0;
		setjsonvalue("type", type);
		getData('${globalUrl}usercenter.do?actionMethod=cutQuestion&type='+type,'','changelist');
	});
});
</script>
<div class="usercenterContent">
	<%@include file="/WEB-INF/jsp/ddhb/front/member/userinfomeun.jsp" %>
	<div class="usercenterDetailDiv" id="usercenterDetailDiv">
		<c:if test="${pageName eq 'appointment' }">
			<c:import url="/WEB-INF/jsp/ddhb/front/member/appointment.jsp"></c:import>
		</c:if>
		<c:if test="${pageName eq 'contactQuery' }">
			<c:import url="/WEB-INF/jsp/ddhb/front/member/contactQuery.jsp"></c:import>
		</c:if>
		<c:if test="${pageName eq 'collectment' }">
			<c:import url="/WEB-INF/jsp/ddhb/front/member/collectment.jsp"></c:import>
		</c:if>
		<c:if test="${pageName eq 'browseHistory' }">
			<c:import url="/WEB-INF/jsp/ddhb/front/member/browseHistory.jsp"></c:import>
		</c:if>
		<c:if test="${pageName eq 'savedField' }">
			<c:import url="/WEB-INF/jsp/ddhb/front/member/memberSavedField.jsp"></c:import>
		</c:if>
		<c:if test="${pageName eq 'priceNotice' }">
			<c:import url="/WEB-INF/jsp/ddhb/front/member/reduceNotice.jsp"></c:import>
		</c:if>
		<c:if test="${pageName eq 'answerdManage' }">
			<form:form name="answerdForm">
				<div id="changelist">
					<c:import url="/WEB-INF/jsp/ddhb/front/member/answerManage.jsp"></c:import>
				</div>
			</form:form>
		</c:if>
		<c:if test="${pageName eq 'scoreDeliver' }">
				<div id="changelist">
					<c:import url="/WEB-INF/jsp/ddhb/front/member/scoreDeliver.jsp"></c:import>
				</div>
		</c:if>
		<c:if test="${pageName eq 'memberInformatinManage' }">
			<c:import url="/WEB-INF/jsp/ddhb/front/member/edit_memberInfo.jsp"></c:import>
		</c:if>
		<c:if test="${pageName eq 'scoreManager' }">
			<form:form name="answerdForm">
				<div id="changelist">
					<c:import url="/WEB-INF/jsp/ddhb/front/member/scoreManager.jsp"></c:import>
				</div>
			</form:form>
		</c:if>
	<div style="clear:both;"></div>
	</div>
	<div style="clear:both;"></div>
</div>
