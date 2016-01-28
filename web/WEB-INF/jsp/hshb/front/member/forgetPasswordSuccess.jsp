<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/css.css"/>
<div class="login-area" id="registerSuccess">
	<div class="formCell">
    	<span class="active">恭喜您，密码修改成功！如果您还有问题，请及时联系管理员：
    	<a href="mailto:${mamagerMail}">${mamagerMail}</a></span>
    </div>
</div>
<style type="text/css">
.formCell span.active{display: block; font-size: 18px; line-height: 30px; margin: 0 auto; padding: 50px;}
#registerSuccess .formCell{padding-left:20px;}
#registerSuccess .formBtn{padding-left:375px;}
</style>