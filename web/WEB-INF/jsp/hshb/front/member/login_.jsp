<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setAttribute("webUrl",request.getHeader("Referer"));
%>
<form name="platLoginForm"  id="platLoginForm" method="post"  action="${globalUrl}frontLogin.do?actionMethod=doLogin">
	<input type="hidden" name="loginForm" value="loginForm"/>
	<input type="hidden" name="webUrl" value="${webUrl}"/>
		<div class="login-title">
			<div class="login-title-left">登录页面</div>
		    <div class="login-title-right"></div>
		  <div class="fix"></div>
		</div>
		<div class="login-area">
			<div class="formCell">
		    	<label for="userTxt">账&nbsp;&nbsp;号：</label><div class="clear"></div>
		        <input type="text"  id="userName" name="userName" onblur="javascript:if(this.value=='') this.value='用户名/邮箱/手机号码'" onfocus="javascript:if(this.value=='用户名/邮箱/手机号码') this.value=''" value="<c:out value="${userName}"/>"/>
		    </div>
		    <div class="formCell">
		    	<label for="psTxt">密&nbsp;&nbsp;码：</label><div class="clear"></div>
		        <input type="password" class="inText" name="password" value=""  />
		        <a href="javascript:;" onclick="forgetPassword();"><font color="blue">忘记密码？</font></a>
		    </div>
		    <div class="formBtn">
		        <input type="submit" value="登&nbsp;&nbsp;录" onclick="trimForm('platLoginForm');platLoginForm.submit();"/>
		    </div>
		    <div>
			<c:if test="${errorLable ne null && errorLable ne 'null'}">
				<font color=red size=2 ><spring:message code="${errorLable}"/></font>
			</c:if>
			<c:if test="${errorMessage ne null && errorMessage ne 'null'}">
				<font color=red size=2 >${errorMessage}</font>
			</c:if>
	     	</div>
		</div>
</form>
<script language="javascript">
function forgetPassword(){
	var userName = document.getElementById("userName").value;
	if(userName=='用户名/邮箱/手机号码'){
		alert("请先输入登陆账号信息");
		return;
	}
	window.location.href = '${globalUrl}register_member/forgetPassword?userName='+userName;
}
</script>