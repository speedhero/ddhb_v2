<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setAttribute("webUrl",request.getHeader("Referer")); %>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/dlzc.css">
<script type="text/javascript" src="${globalUrl}js/ht_frame.js"></script>
<script type="text/javascript" src="${globalUrl}js/hshb.common.js"></script>
<style>
.form_item .rt_ipt .veryfyInput{width:180px;height:34px;padding-left:5px;color:#333;border:none; background:#ecedf2;float:left;}
@media screen and (max-width: 470px) {
.form_item .rt_ipt .veryfyInput{width:100px;}
}
</style>
<div class="dlzc">
	<div class="dlzc_k">
		<form modelAttribute="platMemberInfo" name="platLoginForm" id="platLoginForm" method="post" action="${globalUrl}frontLogin.do?actionMethod=doLogin&target=${target }&housetype=${housetype }">
			<input type="hidden" name="loginForm" value="loginForm" />
			<input type="hidden" name="housetype" id="housetype" value="${housetype }" />
			<div class="dlzc_con" id="logincondiv">
				<div class="form_item">
					<div class="name name_dl">账号</div>
					<div class="rt_ipt">
						<c:if test="${userName != null }">
							<input id="accountnum" name="userName" type="text" class="input"
								value='<c:out value="${userName }"></c:out>'
								onfocus='if($.trim(value)=="用户名/邮箱/手机号码") {value ="";$(this).css({"color":"#333"})};'
								onblur='if($.trim(value)==""){value="用户名/邮箱/手机号码";$(this).css({"color":"#CCCCCC"})};'>
						</c:if>
						<c:if test="${userName == null }">
							<input id="accountnum" name="userName" type="text" class="input"
								value='<c:if test="${userName != null }"><c:out value="${userName }"></c:out></c:if>用户名/邮箱/手机号码'
								onfocus='if($.trim(value)=="用户名/邮箱/手机号码") {value ="";$(this).css({"color":"#333"})};'
								onblur='if($.trim(value)==""){value="用户名/邮箱/手机号码";$(this).css({"color":"#CCCCCC"})};'>
						</c:if>
					</div>
					<div class="clear"></div>
				</div>
				<div class="form_item">
					<div class="name name_dl">密码</div>
					<div class="rt_ipt"><input type="password" name="password" class="input"></div>
					<div class="clear"></div>
				</div>
				<div class="form_item">
					<div class="name name_dl">验证码</div>
					<div class="rt_ipt">
        		<input type="text" name="verifyCode" id="verifyCodeInput" class="veryfyInput"><b ><img src="${globalUrl}jcaptcha?id=1357744009" onclick="reloadCaptcha();" id="imageCaptcha" title="点击刷新" style="width:77px;height:25px;" ></b>
        		<p><a onclick="forgetPassword();" class="zh">忘记密码</a></p>
        	</div>
        	<div class="clear"></div>
        </div>
				<div id="errordiv" style="font-size: 12px; color: red;"></div>
				<div class="zcdl_an">
					<a href="javascript:void(0);" onclick='trimForm("platLoginForm");postmyform("platLoginForm", "errordiv");'>登录</a>
				</div>
			</div>
		</form>
	</div>
</div>
<div style="display: none;" id="decodeHtml"></div>
<script type="text/javascript">
function forgetPassword(){
	var userName = document.getElementById("accountnum").value;
	window.location.href = '${globalUrl}register_member/forgetPassword?userName='+userName;
}
function postmyform(formName, resultArea) {
	var dataString = $("#" + formName).serialize();
	actionUrl = $("#" + formName)[0].action;
	
	if($("#housetype").val() != ""){
		actionUrl += "&housetype="+$("#housetype").val();
	}
	postBymyURL(actionUrl, dataString, "POST","html",
		function(data) {
			reloadCaptcha("imageCaptcha");
			if(data.indexOf('跳转页面') >= 0){
				var targetUrl=data.substring(data.indexOf('targetUrlBegin=')+15,data.indexOf('=targetUrlEnd'));
				$("#decodeHtml").html(targetUrl);
				window.location.href = $("#decodeHtml").text();
				return;
			}else if(data.indexOf('保存成功') >= 0) {
				location.reload();
			}else {
				$('#' + resultArea).html(data);
				if (data.indexOf('锁') > 0){
					changeCount();
				}
			}
		}
	);
}

function changeCount(){
	var data = $('#errordiv').html();
	var count = data.substring(data.indexOf('请') + 1, data.indexOf('秒'));
	count = parseInt(count);
	data = data.replace(count, (count - 1));
	count = count - 1;
	$('#errordiv').html(data);
	if (count > 0){
		setTimeout("changeCount()",1000);
	}
}

</script>