<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link type="text/css" rel="stylesheet" href="${globalUrl}css/css.css">
<style type="text/css">
.formBtn a{
	padding: 0 25px;
	height: 34px;
	background: #3fb8b1;
	color: #fff;
	display: inline-table;
}

.formBtn a:hover{background: #1e9b94;}

.formBtn{
	margin-bottom: 30px;
}
.fontSize{font-size:14px;line-height: 30px;}
@media screen and (max-width: 470px){
.fontSize{font-size:12px;line-height: 33px;}
}
</style>
<div class="login-area" id="registerSuccess">
	<div class="formCell">
    	<span class="active fontSize" >您的信息已经成功提交，激活链接已发送到您的邮箱 ${email}.
							登录注册邮箱，按照邮件内容提示，激活您的帐户即可完成注册。如果没有收到邮件，请联系管理员：<a href="mailto:${mamagerMail}">${mamagerMail}</a></span>
    </div>
    <div class="formBtn fontSize" style="text-align:center;">
    <!-- 
        <input type="button" value="&lt;&lt;返回首页" onclick="window.location.href='${globalUrl}welcome.show?actionMethod=welcome'"/>&nbsp;&nbsp;
        <input type="button" value="&lt;&lt;登陆邮箱" onclick="window.location.href='http://mail.${mailDomain}.com'"/>
         -->
        <a onclick="window.location.href='${globalUrl}'">&lt;&lt;返回首页</a>&nbsp;&nbsp;
        <a onclick="window.location.href='http://mail.${mailDomain}.com'">&lt;&lt;登陆邮箱</a>
    </div>
</div>
<style type="text/css">
.formCell span.active{padding: 45px 0px;display:block;margin:0 auto;}
#registerSuccess .formCell{padding-left:20px;}
</style>