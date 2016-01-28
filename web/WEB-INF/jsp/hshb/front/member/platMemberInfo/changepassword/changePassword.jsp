<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee" %>
<form:form modelAttribute="platMemberInfo" name="platMemberInfo" action="${globalUrl}memberInfo.do?actionMethod=changePassword">
	<div class="manage-right">
    	<div class="manage-right-title">修改密码</div>
        <div class="modify-area">
        	<div class="modify-area-form">
            	<label for="username">您的用户名：</label>
                <input type="text" value="${platMemberInfo.accName}" id="accName" disabled="disabled"/>
            </div>
        	<div class="modify-area-form">
            	<label for="oldpassword">请输入原密码：<span class="redmark">*</span></label>
                <input type="password" id="oldPasswd" name="oldPasswd"/>
                <form:errors path="oldPasswd" cssClass="errors"/>
            </div>
            <div class="modify-area-form">
            	<label for="newpassword">请输入新密码：<span class="redmark">*</span></label>
                <input type="password" id="newPasswd" name="newPasswd"/>
				<form:errors path="newPasswd" cssClass="errors"/>
            </div>
            <div class="modify-area-form">
            	<label for="cofirmpassword">请确认新密码：<span class="redmark">*</span></label>
                <input type="password" id="confirmNewPasswd" name="confirmNewPasswd"/>
                <form:errors path="confirmNewPasswd" cssClass="errors"/>
            </div>
            <div class="modify-area-btn">
            	<input type="button" value="提&nbsp;&nbsp;交" onclick="postDataByFormName('platMemberInfo','workspace');"/>  &nbsp;&nbsp;
                <input type="reset" value="清&nbsp;&nbsp;空"/>
            </div>
        </div>
    </div>
	<div class="fix"></div>
</form:form>
<script language="javascript">
$(document).ready(function(){
	//alert(document.getElementById('oldPasswd').value);
	document.getElementById('oldPasswd').value = '';
});
</script>