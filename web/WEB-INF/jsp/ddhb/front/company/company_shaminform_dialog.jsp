<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/company.css"/>
<style>
.yyfw_an a.tj{float:none; margin:0 auto;display:block;}
@media screen and (max-width: 470px) {
  .tjPhoneClass { padding: 20px 0 50px 30% !important;}
}
</style>
<form:form modelAttribute="shamInfo" id="shamInfo" action="${globalUrl}company.show?actionMethod=subservice">
<div class="tcc_zpShamInfo" >
	<div class="yyfw_con">
		<input type="hidden" name="houseNo" value="<c:if test="${houseType==1 }">${house.houseNo }</c:if><c:if test="${houseType==2 }">${house.hourseNo }</c:if>" />
		<input type="hidden" name="houseType" value="${houseType}" />
		<input type="hidden" name="brokerId" value="${houseAppraise.broker.erpId}" />
		<div class="yyfw_bg"><strong>举报房源：<c:out value="${house.community.communityName }"></c:out></strong><span><fmt:formatNumber pattern="#" value="${house.area }"/>  平米</span><span>${house.shi }室${house.ting }厅${house.wei }卫</span><span><c:if test="${houseType == 1}"><b><fmt:formatNumber value="${house.price/10000 }"/></b>万元</c:if><c:if test="${houseType == 2}"><b><fmt:formatNumber value="${house.rentPrice }"/></b>元/月</c:if></span></div>
		<div class="yyfw_ls">
			<ul>
                <li style="width:100%;"><span>姓名：</span>
                	<input id="username" type="text" name="reportName" value="${LoginMember.accName}" style="width:64%; height:24px;">
                </li>
                <li style="width:100%;"><span>邮件：</span>
                    <input id="emailAddress" type="text" name="emailAddr" value="${LoginMember.emailAddress}" style="width:64%; height:24px;">
                </li>
                <li style="width:100%;"><span>*电话：</span>
                    <input id="telephoneNo" type="text" name="telephone" value="${LoginMember.mobilephone}" style="width:64%; height:24px;">
                </li>
            </ul>
            <div class="clear"></div>
        </div>
        <div class="yyfw_ls">
            <ul>
	             <li style="width:100%;"><span>*内容：</span>
	                <textarea style="width:65%;" id="content" rows="5" cols="40" name="content"></textarea>
	            </li>
	            <li style="width:100%;">
					<span>验证码：</span>
					<input value="" name="verifyCode" id="verifyCode" type="text" style="height: 25px; width: 100px;"/>
					<span><img src="${globalUrl}jcaptcha?id=1357744009" onclick="reloadCaptcha();" id="imageCaptcha" title="点击刷新"></span>
					<div style="clear: both;"></div>
				</li>
            </ul>
            
			<div class="clear"></div>
			<div class="yyfw_an" style="padding:20px 0px;">
				<div id="errordiv" style="font-size: 12px; color: red;"></div>
	        	<a class="tj" style="float:none; margin: 0 auto; display: block;" href="javascript:void(0);" onclick='trimForm("shamInfo");postmyform("shamInfo", "errordiv");'>提交</a>
       		</div>
		</div>
	</div>
</div>
<input type="hidden" value="1" name="serviceTypeid">
</form:form>
<script type="text/javascript">
function postmyform(formName, resultArea) {
	var dataString = $("#" + formName).serialize();
	actionUrl = $("#" + formName)[0].action;

	postBymyURL(actionUrl, dataString, "POST", "html",
		function(data) {
			$('#' + resultArea).html(data);
			if($("#issaved").val() == 1) {
				art.dialog({id: 'shaminformid'}).close();
				$("#username").val("");
				$("#emailAddress").val("");
				$("#telephoneNo").val("");
				$("#content").val("");
				art.dialog.close();
				infoBox("举报成功，我们会尽快核实，并跟您取得联系，感谢您的支持！");
			}
			reloadCaptcha("imageCaptcha");
		}
	);
}

</script>