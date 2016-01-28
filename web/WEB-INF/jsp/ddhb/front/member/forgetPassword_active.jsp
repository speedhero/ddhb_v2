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
<div class="login-area" id="registerSuccess">
	<div class="formCell">
    	<span class="active">您的信息已经成功提交，找回密码链接已发送到您的邮箱 ${email}.
							登录注册邮箱，按照邮件内容提示，点击链接进入BPS电子商务平台找回密码界面。如果没有收到邮件，请联系管理员：<a href="mailto:${mamagerMail}">${mamagerMail}</a></span>
    </div>
    <div class="formBtn">
        <input type="button" value="&lt;&lt;返回首页" onclick="window.location.href='${globalUrl}welcome.show?actionMethod=welcome'"/>&nbsp;&nbsp;
        <input type="button" value="&lt;&lt;登陆邮箱" onclick="window.location.href='http://mail.${mailDomain}.com'"/>
    </div>
</div>
