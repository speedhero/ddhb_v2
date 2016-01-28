<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
.detailsDiv{
	width:50%;
}
.detailsDivData{
	width: 341px;
	height: 40px;
	text-align: left;
	line-height: 40px;
	margin-left:100px;
}
.textField{
	height:30px;
	line-height: 30px;
}
.logincontext{
	width: 100%;
	height: 300px;
	padding-top: 40px;
	background-color: #ffffff;
}
.login-nav{
	height: 43px;
	width: 100%;
	
}
.contentBorBox{
	margin-left:10px;
}
.contentColumn{
	margin-left:50px;
}
.labelColumn{
	width:125px;
	float:left;
}
.editColumnInput{
	width:250px;
	display:none;
	height:40px;
}
.buttonStyle{
	width: 120px;
	height: 38px;
	background-color: #3FB8B1;
	border: 2px solid #3FB8B1;
	border-radius: 5px;
	cursor: pointer;
	text-align:center;
	line-height:37px;
	float:left;
	margin-left:10px;
}
.errorContainerList{
	color:red;
	font-size:10px;
	margin-left:100px;
}
</style>
<script type="text/javascript">
var errorMessage = "";
function editMemberMode(){
	$(".showDetails").hide();
	$(".editColumnInput").show();
	$("#saveButton").show();
	$("#cancelButton").show();
	$("#editButton").hide();
	$("#changePassword").hide();
	$("#mobilephoneEdit").val('${platMemberInfo.mobilephone}');
	$("#emailAddressEdit").val('${platMemberInfo.emailAddress}');
	$("#realNameEdit").val('${platMemberInfo.realName}');
	$("#identityCardEdit").val('${platMemberInfo.identityCard}');
}
function cancelEditMemberMode(){
	$(".showDetails").show();
	$(".editColumnInput").hide();
	$("#saveButton").hide();
	$("#cancelButton").hide();
	$("#editButton").show();
	$("#changePassword").show();
	$("#errorContainer").html("");
}
function clickSave(){
	var flag=0;
	errorMessage = "";
	flag = Verification(flag);
	if(flag == 1){
		$("#errorContainer").html(errorMessage);
		return;
	}
	if(confirm('确定保存?'))
	{
		url = "${globalUrl}memberInfo.do?actionMethod=editMemberInfo&mobilephone="+$("#mobilephoneEdit").val()+"&emailAccount="+$("#emailAddressEdit").val()+"&id="+$("#identityCardEdit").val()+"&sex="+$('input:radio:checked').val()+"&memberId="+${platMemberInfo.id}+"&realName="+$.trim( $("#realNameEdit").val());
		window.location.href = url;
	}
}
function Verification(flag) {
	var phoneNum = $("#mobilephoneEdit").val();
	var emailAccount = $("#emailAddressEdit").val();
	var realName =$.trim( $("#realNameEdit").val());
	var id = $("#identityCardEdit").val();
	if (!(/^1[3|4|5|8][0-9]\d{8}$/.test(phoneNum))) {
		if (errorMessage.length == 0) {
			errorMessage = "无效的手机号码"
		} else {
			errorMessage += ",无效的手机号码"
		}
		flag = 1;
	}

	var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if (!emailReg.test(emailAccount)) {
		if (errorMessage.length == 0) {
			errorMessage = "无效的邮箱地址"
		} else {
			errorMessage += ",无效的邮箱地址"
		}
		flag = 1;
	}

	var idreg = /(^\d{17}([0-9]|X)$)/;
	if (!idreg.test(id)) {
		if (errorMessage.length == 0) {
			errorMessage = "无效的身份证号码"
		} else {
			errorMessage += ",无效的身份证号码"
		}
		flag = 1;
	}
	if(realName.length == 0)
	{
		if (errorMessage.length == 0) {
			errorMessage = "真实姓名不能为空"
		} else {
			errorMessage += ",真实姓名不能为空"
		}
		flag = 1;	
	}
	return flag;
}
</script>
<div style="width:750px;height: auto; border-bottom: 1px solid #cccccc;">
	<div style="height: 25px;width:100%;">
		<div style="margin-left: 40px;margin-top: 25px;"><a onclick='window.location.href="${globalUrl}welcome.show?actionMethod=welcome"' style="cursor: pointer;">首页</a>&nbsp;>&nbsp;个人中心&nbsp;>&nbsp;个人信息管理</div>
	</div>
</div>
<div class="contentBorBox">
<form:form modelAttribute="platMemberInfo" name="platMemberInfo" action="${globalUrl}memberInfo.do?actionMethod=editMemberInfo">
<div id="logincondiv" class="logincontext">
	<div class="detailsDiv">
		<div class="detailsDivData">
		<div id="accName"><div class="labelColumn">用户名：</div><div  id="accNameInput" class="contentColumn">${platMemberInfo.accName}</div></div>
		</div>
		<div class="detailsDivData">
		<div id="mobilephone"><div class="labelColumn">手机号码：</div><div class=" contentColumn" id="mobilephoneInput" ><div class="showDetails">${platMemberInfo.mobilephone}</div><div class="editColumnInput"><input type="text" id="mobilephoneEdit" name="mobilephone"   class="textField"/></div></div></div>
		</div>
		<div class="detailsDivData">
		<div id="emailAddress"><div class="labelColumn">邮箱：</div><div class=" contentColumn" id="emailAddressInput" ><div class="showDetails">${platMemberInfo.emailAddress}</div><div class="editColumnInput"><input type="text" id="emailAddressEdit" name="emailAddress"   class="textField"/></div></div></div>
		</div>
		<div class="detailsDivData">
		<div id="realName"><div class="labelColumn">真实姓名：</div><div class=" contentColumn" id="realNameInput" ><div class="showDetails">${platMemberInfo.realName}</div><div class="editColumnInput"><input type="text" id="realNameEdit" name="realName"   class="textField"/></div></div></div>
		</div>
		<div class="detailsDivData">
		<div id="identityCard"><div class="labelColumn">身份证号码：</div><div class=" contentColumn" id="identityCardInput" ><div class="showDetails">${platMemberInfo.identityCard}</div><div class="editColumnInput"><input type="text" id="identityCardEdit" name="identityCard"  class="textField"/></div></div></div>
		</div>
		<div class="detailsDivData">
		<div id="sexName"><div class="labelColumn">性别：</div><div id="sexInput" class=" contentColumn" ><div class="showDetails"><c:if test="${platMemberInfo.sex eq '1'}">男 </c:if><c:if test="${platMemberInfo.sex eq '0'}">女 </c:if></div>
		<span class="editColumnInput"><input type="radio" id="male" name="sex" class="radio" value="1" <c:if test="${platMemberInfo.sex eq '1' or platMemberInfo.sex == null}"> checked="checked"</c:if>/>男
		<input type="radio" id="female" name="sex" class="radio" value="0" <c:if test="${platMemberInfo.sex eq '0'}"> checked="checked"</c:if>/>女</span></div></div>
		</div>
		</div>
		<div id="errorContainer" class="errorContainerList"></div>
		<div id="buttonDiv" style="margin-left:100px;float:left;">
		<div id="saveButton" style="display:none;" class="buttonStyle"  onclick="clickSave();">保&nbsp;&nbsp;存</div>
		<div id="cancelButton" style="display:none;" class="buttonStyle"  onclick="cancelEditMemberMode();">取消</div>
		<div id="editButton" class="buttonStyle" onclick="editMemberMode();">修改个人信息</div>
		<div id="changePassword" class="buttonStyle" onclick="window.open('${globalUrl}register_member/modifyPassword')" >修改密码</div>
		</div>
	</div>
</div>
</form:form>
</div>