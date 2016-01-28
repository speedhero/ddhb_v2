<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${globalUrl}js/select/jquery.selectReplace.js"></script>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/select/jquery.select.css">
<style>
.serviceType{width:200px; height:25px;}
.serviceType{width:130px;}
</style>

<script type="text/javascript">
$(document).ready(function(){
	$("form").replaceAllSelect();
});
</script>

<div class="header"></div>
<div class="about_main"> 
	<%@include file="/WEB-INF/jsp/ddhb/front/company/company_common_menu.jsp" %>
	<div class="cp_rt">
     	<div class="licn"><a onclick='window.location.href="${globalUrl}welcome.show?actionMethod=welcome"'>首页</a>&nbsp;>&nbsp;客户服务</div>
     	<form:form modelAttribute="customerService" id="customerService" action="${globalUrl}company.show?actionMethod=service">
         <div class="yp_nr yp_nr100" style="border-bottom:0px;">
         	<ul>
         	<li>
         	<div class="wd">*问题类型：</div>
             <div class="input">
             	<div class="xiala" style="width: 185px;">
                 	<input type="hidden" id="hiddenServiceType" name="serviceType">
			<select class="marginleft10" name="serviceTypeId" style="width: 185px;" id="serviceType">
					<option selected="selected" value="defaultvalue"><span class="">请选择问题类型</span></option>
				<c:forEach var="typeItem" items="${typeList}">
					<option value="${typeItem.id}">${typeItem.typeName}</option>
				</c:forEach>
			</select>
                 </div>
             </div>
             </li>
             <br>
             <li>
             <div class="wd">姓名：</div>
             <div class="input"><input name="username" type="text" class="inptxt" value="${platMemberInfo.accName }"></div>
             </li>
             <br>
             <li>
             <div class="wd">邮件：</div>
             <div class="input"><input name="emailAddress" type="text" class="inptxt" value="${platMemberInfo.emailAddress }"></div>
             </li>
             <br>
             <li>
             <div class="wd">*电话：</div>
             <div class="input"><input name="telephoneNo" type="text" class="inptxt" value="${platMemberInfo.mobilephone }"></div>
             </li>
             <br>
             <li style="margin-bottom: 120px;">
             <div class="wd">*内容：</div>
             <div class="input"><textarea name="content" class="textarea"></textarea></div>
             </li>
             <li style="padding-top: 20px;">
				<span style="float: left; text-align: right; width: 80px;">验证码：</span>
				<input value="" name="verifyCode" id="verifyCode" type="text" style="background: none repeat scroll 0 0 #ecedf2; border: medium none; color: #333; float: left; height: 25px; line-height: 24px; padding-left: 5px; width: 140px;"/>
				<span style="float: left; text-align: right; width: 80px;"><img src="${globalUrl}jcaptcha?id=1357744009" onclick="reloadCaptcha();" style="border: medium none;" id="imageCaptcha" title="点击刷新"></span>
			</li>
             <br>
             <li>
             <div class="yczz_an"><a href="javascript:void(0);" onclick='trimForm("customerService");$("#hiddenServiceType").val($("#serviceType").attr("value")); postmyform("customerService", "errordiv");'>提交</a></div>
             </li>
             </ul>
         </div>
         </form:form>
         <div id="errordiv" style="font-size: 12px; color: red;"></div>
     </div>
</div>
<script type="text/javascript">
function clearForm(formName) {
	$('#' + formName).find("select").each(function() {
		$(this ).find("option[value='defaultvalue']").attr('selected', "selected");
	});
	$('#' + formName).find("input").each(function() {
		if($(this).val() != '提交') {
			$(this).attr("value", '');
		}
	});
	$('#' + formName).find("textarea").each(function() {
		$(this).attr("value", '');
	});
}

function postmyform(formName, resultArea) {
	if($("select option:selected").val() == 'defaultvalue') {
		$("#errordiv").html('选一种问题类型吧!');
		return;
	}
	var dataString = $("#" + formName).serialize();
	actionUrl = $("#" + formName)[0].action;
	
	postBymyURL(actionUrl, dataString, "POST", "html",
			function(data){
				$('#' + resultArea).html(data);
				if($("#issaved").val() == 1) {
					clearForm('customerService');
					alert("数据提交成功，谢谢您的宝贵意见！");	
				}
				reloadCaptcha('imageCaptcha');
			}
	);
}
</script>