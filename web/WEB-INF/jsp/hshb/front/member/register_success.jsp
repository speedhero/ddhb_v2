<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/css.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/more.css">
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
    	<span class="active fontSize"><c:if test="${uuidInvalidate eq null}">
    	恭喜您，会员注册成功！
    	</c:if>
    	<c:if test="${uuidInvalidate ne null}">
    	链接已经失效，请关闭页面！
    	</c:if></span>
    </div>
    <div class="formBtn fontSize" style="text-align:center;">
    <!-- 
        <input type="button" value="&lt;&lt;返回首页" onclick="window.location.href='${globalUrl}welcome.show?actionMethod=welcome'"/>&nbsp;&nbsp;
        <input type="button" value="&lt;&lt;登陆邮箱" onclick="window.location.href='http://mail.${mailDomain}.com'"/>
         -->
        <a onclick="window.location.href='${globalUrl}'">&lt;&lt;返回首页</a>&nbsp;&nbsp;
    </div>
</div>
<style type="text/css">
.formCell span.active{padding: 45px 0px;display:block;margin:0 auto;}
#registerSuccess .formCell{padding-left:20px;}
</style>