<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/css.css"/>
<style>
.login-area{
	width: 510px;
	height: auto;
	margin: 50px auto;
	margin-bottom: 0px;
	padding-bottom: 100px;
/* 	box-shadow:5px 5px 5px #888888; */
	/* background-image: url("../../images/homepage/32.png"); */
}
.input{
	width: 100%;height: 30px;
}
.login-nav{
	height: 43px;
	width: 100%;
	
}
.login-nav div{
	width: 128px; 
	float: left; 
	height: 100%; 
	color: #ffffff; 
	text-align: center; 
	line-height: 50px;
	cursor: pointer;
}
.logincontext{
	width: 100%;
	height: 240px;
	padding-top: 40px;
	background-color: #ffffff;
}
.account{
	width: 100%;
	height: 35px;
}
.account div{
	float: left;
	line-height:16px;
}
.accountname{
	width: 127px;
	height: 100%;
	text-align: center;
	line-height: 50px;
}
.accountnum{
	width:340px;
	height: 100%;
}
.loginsubmit{
	width: 110px;
	height: 38px;
	background-color: #3fb8b1;
    border: 2px solid #3fb8b1;
    border-radius: 5px;
    color: white;
    cursor: pointer;
    font-family: 微软雅黑;
    font-size: 13px;
}
.loginbut{
	width: 100px;
	height: 38px;
	margin-top: 35px;
	margin-left: 202px;
	margin-right: auto;
}
.registercontext{
	height: auto;
	width: 100%;
	padding: 40px 0;
	background-color: #ffffff;
}
.registerbutton{
	width: 53%;
	height: 38px;
	margin-top:35px;
	margin-left: auto;
	margin-right: auto;
}
.errors{
	color: red;
	font-size: 12px;
}
@media screen and (max-width: 960px)and (min-width: 758px) {
}
@media screen and (max-width: 758px) {
	.login-area {width : 471px}
	.loginbut{margin-left:180px;}
	
}
@media screen and (max-width: 470px) {
	.login-area {width : 311px}
	.accountnum {width : 160px;}
	#accName {width:160px;}
	.loginbut{margin-left:115px;}
}
</style>
<div class="login-area">
	<div class="login-nav" style="background: url('${globalUrl}images/housedetail/PCPad-4-2.png');">
				<div id="login" class="loginbutton" style="color: #000000; background: url('${globalUrl}images/housedetail/PCPad-4-1.png');">找回密码</div>
	</div>
	<form:form modelAttribute="platMemberInfo" name="platMemberInfo" id="platMemberInfo" action="${globalUrl}register_member/forgetPassword">
	<div id="logincondiv" class="logincontext">
		<div class="account">
			<div class="accountname" style="width:200px;">密码忘记了？别着急……</div>
		</div>
		<div class="account" style="margin-top: 20px;">
			<div class="accountname">用户名：</div>
			<div class="accountnum">
				<form:input path="accName" id="accName" size="30" maxlength="30" />
				<form:errors path="accName" cssClass="errors"/>
				<form:errors path="newPasswd" cssClass="errors"/>
			</div>
		</div>
			<div class="loginbut"><button class="loginsubmit" type="submit" onclick="trimForm('platMemberInfo');$('#platMemberInfo').submit();">发送密码链接</button>
			</div>
	</div>
	</form:form>
</div>
