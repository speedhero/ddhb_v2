<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link type="text/css" rel="stylesheet" href="${globalUrl}css/corner_short.css">
<style>
.signtitle{ font-size: 14px; font-weight: bold; line-height: 40px; color: #bc2626}
/* form input {height: 25px;line-height: 24px;padding-left: 5px;color: rgb(51, 51, 51);border: none;background: rgb(236, 237, 242);float: left;} */
#signupform input {
   background: none repeat scroll 0 0 #ecedf2;
   border: medium none;
   color: #333;
   float: left;
   height: 25px;
   line-height: 24px;
   padding-left: 5px;
   width: 140px;
}
form textarea {width: 265px;height: 70px;background: rgb(236, 237, 242);padding: 5px;line-height: 18px;font-size: 12px;color: rgb(51, 51, 51);border: 0; margin-top:10px;}
.labelClass{width:100px; text-align: right;float:left; line-height: 23px; padding-right:10px; margin-top:10px;}
.valueClass{width:290px; float:left; margin-top:10px;}
.valueClass input{width:90%;}
.conformButtonContainer{width:100%; height:40px; text-align: center;}
.conformButtonContainer>div{width: 120px;height: 34px;background: rgb(255, 103, 1);font-size: 18px;color: rgb(255, 255, 255); display:block; text-align: center; margin:0 auto; margin-top:16px;cursor:pointer; line-height:30px;}
#errordiv{text-align: center;}
@media screen and (max-width: 470px) {
	.valueClass{width:200px;}
	form textarea{width:175px;}
}
</style>
<div class="dialogTitle" style="background:#ffffff;">
<form:form modelAttribute="signupform" id="signupform" action="${globalUrl}/xinfang/buy">
	<div style="text-align: center;"><span class="signtitle">请填写一下相关资料（注意：带*为必填项）</span></div>
	<div>
		<div class="labelClass">*姓名：</div><div class="valueClass"><input type="text" name="clientName" id="clientName" value="${LoginMember.accName}" /></div>
	</div>
	<div>
		<div class="labelClass">*电话号码：</div><div class="valueClass"><input type="text" name="clientTelephone" id="clientTelephone" value="${LoginMember.mobilephone}" /></div>
	</div>
	<div>
		<div class="labelClass">E-mail地址：</div><div class="valueClass"><input type="text" name="clientEmail" id="clientEmail" value="${LoginMember.emailAddress}" /></div>
	</div>
	<div>
		<div class="labelClass">我的置业需求：</div><div><textarea name="clientproperties" id="clientproperties"></textarea></div>
	</div>
	<div>
		<div class="labelClass">验证码：</div>
		<div class="valueClass" style="width:90px;"><input style="width:90px;" value="" name="verifyCode" id="verifyCode" type="text" /></div>
		<div style="float: left; height: 25px; padding-left: 10px; margin-top: 10px; width: 77px;"><img src="${globalUrl}jcaptcha?id=1357744009" onclick="reloadCaptcha();" id="imageCaptcha" title="点击刷新"></div>
		<div style="clear: both;"></div>
	</div>
	<div id="errordiv" style="font-size: 12px; color: red;"></div>
	<div class="conformButtonContainer">
		<div onclick='trimForm("signupform"); postmyform("signupform", "errordiv");'>提交</div>
	</div>
	<input type="hidden" name="houseid" value="${houseNew.id }">
</form:form>
</div>
<script type="text/javascript">
function postmyform(formName, resultArea) {
	var dataString = $("#" + formName).serialize();
	actionUrl = $("#" + formName)[0].action;
	postBymyURL(actionUrl, dataString, "POST","html",
		function(data) {
			$('#' + resultArea).html(data);
			if($("#issaved").val() == 1) {
				art.dialog({id: 'signupDialogid'}).close();
				alert("参与成功！");
				window.location.reload();
			}
			reloadCaptcha("imageCaptcha");
		}
	);
}
</script>