<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee" %>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/css.css"/>
<style>
.login-area{
	width: 510px;
	height: auto;
	margin: 55px auto;
	box-shadow:5px 5px 5px #888888;
}
.input{
	width: 50%;
	height: 30px;
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
	height: 330px;
	padding-top: 40px;
	background-color: #ffffff;
}
.account{
	width: 100%;
	height: 35px;
}
.account div{
	float: left;
}
.accountname{
	width: 127px;
	height: 100%;
	line-height: 30px;
	margin-left: 40px;
}
.accountnum{
	width:340px;
	height: 100%;
}
.loginsubmit{
	width: 100px;
	height: 38px;
	background-color: #3FB8B1;
	border:2px solid #3FB8B1;
	border-radius:5px;
	cursor: pointer;
}
.loginbut{
	width: 100px;
	height: 38px;
	margin-top: 35px;
	margin-left: auto;
	margin-right: auto;
}
@media screen and (max-width: 758px){
	.login-area{width:471px}
	
	.accountnum {width:176px}
	.input {width:95%;}
}
@media screen and (max-width: 470px) {
	.login-area{width:311px}
	.accountname {width:95px; margin-left:21px;}
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('#oldPasswd').val('');
})
</script>
<div class="login-area">
<div class="login-nav" style="background: url('${globalUrl}images/housedetail/PCPad-4-2.png');">
	<div id="login" class="loginbutton" style="color: #000000; background: url('${globalUrl}images/housedetail/PCPad-4-1.png');">修改密码</div>
</div>
<form:form modelAttribute="platMemberInfo" name="platMemberInfo" action="${globalUrl}register_member/modifyPassword">
	<div id="logincondiv" class="logincontext">
    	<c:if test="${uuidInvalidate eq null}">
        <div class="account">
			<div class="accountname">您的用户名：</div>
			<div class="accountnum"><input type="text" value="${platMemberInfo.accName}" id="username" disabled="disabled" class="input"/></div>
		</div>
		<div class="account" style="margin-top: 20px;">
			<div class="accountname">*请输入原密码：</div>
			<div class="accountnum"><input type="password" id="oldPasswd" name="oldPasswd" class="input"/>
			</div>
		</div>
		<div class="account" style="margin-top: 20px;">
			<div class="accountname">*请输入新密码：</div>
			<div class="accountnum"><input type="password" id="newPasswd" name="newPasswd" class="input"/>
			</div>
		</div>
		<div class="account" style="margin-top: 20px;">
			<div class="accountname">*请确认新密码：</div>
			<div class="accountnum"><input type="password" id="confirmNewPasswd" name="confirmNewPasswd" class="input"/>
            </div>
		</div>
			<div class="loginbut"><button class="loginsubmit" type="button" style="color:#ffffff;" onclick="trimForm('platMemberInfo');document.platMemberInfo.submit();">确定</button></div>
		<div>
		<div style="color:red;">
		&nbsp;&nbsp;&nbsp;&nbsp;<form:errors path="oldPasswd" cssClass="errors"/>
		&nbsp;&nbsp;&nbsp;&nbsp;<form:errors path="confirmNewPasswd" cssClass="errors"/>
		&nbsp;&nbsp;&nbsp;&nbsp;<form:errors path="newPasswd" cssClass="errors"/></div>
			<c:if test="${errorLable ne null && errorLable ne 'null'}">
				<font color=red size=2 ><spring:message code="${errorLable}"/></font>
			</c:if>
			<c:if test="${errorMessage ne null && errorMessage ne 'null'}">
				<font color=red size=2 >${errorMessage}</font>
			</c:if>
	    </div>
        </c:if>
        <c:if test="${uuidInvalidate ne null}">
        <span class="success">链接已经失效，请关闭页面！</span>
        </c:if>
    </div>
	<div class="fix"></div>
</form:form>
</div>