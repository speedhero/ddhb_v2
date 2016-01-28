<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setAttribute("webUrl",request.getHeader("Referer"));
%>
<!-- 弹出框js引用 -->
<script type="text/javascript" src="${globalUrl}js/jquery.artDialog.source.js"></script>
<script type="text/javascript" src="${globalUrl}js/artDialog.iframeTools.source.js"></script>
<script type="text/javascript" src="${globalUrl}js/ht_frame.js"></script>
<link type="text/css" rel="stylesheet" href="${globalUrl}js/skins/gray.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/css.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/dlzc.css">
<div class="dlzc">
	<div class="dlzc_k">
		<form:form modelAttribute="platMemberInfo" name="platMemberInfo" action="${globalUrl}register_member/register" method="post" id="platMemberInfo">
			<div class="dlzc_con" id="registerdiv">
				<div class="form_item">
					<div class="name"><span style="color:red;">*</span>邮箱地址</div>
					<div class="rt_ipt"><input type="text" name="emailAddress" id="userEmail" class="input"></div>
					<span onclick="infoShow('emailAddressReason');" style="float: left;cursor:pointer; margin-top: 10px; color:red;">&nbsp;&nbsp;？</span>
					<div class="clear"></div>
				</div>
				<div class="form_item">
					<div class="name"><span style="color:red;">*</span>用户名称</div>
					<div class="rt_ipt"><input type="text" id="accName" name="accName" class="input"></div>
					<div class="clear"></div>
				</div>
				<div class="form_item">
					<div class="name"><span style="color:red;">*</span>设置密码</div>
					<div class="rt_ipt"><input type="password" id="password" name="password" class="input"></div>
					<div class="clear"></div>
				</div>
				<div class="form_item">
					<div class="name"><span style="color:red;">*</span>确认密码</div>
					<div class="rt_ipt"><input type="password" id="confirmPassWd" name="confirmPassWd" class="input"></div>
					<div class="clear"></div>
				</div>
				<div class="form_item">
					<div class="name">手机号码</div>
					<div class="rt_ipt">
						<input id="phoneNum" type="text" name="mobilephone" class="input">
						<p><a id="codeagain" class="hq">免费获取验证码</a></p>
					</div>
					<div class="clear"></div>
				</div>
				<div class="form_item">
					<div class="name">填验证码</div>
					<div class="rt_ipt">
						<input id="verifyCode" name="verifyCode" type="text" class="input">
						<p>
							<input name="checkbox" type="checkbox" value="off" style="margin-left:-10px" /> 我已阅读并同意《华邦地产服务条款》<a onclick="infoShow('privatePolicy');" style="cursor:pointer; color:red;">&nbsp;&nbsp;？</a>
						</p>
					</div>
					<div class="clear"></div>
				</div>
				<div id="errordiv" style="font-size: 12px; color: red;"></div>
				<div class="zcdl_an">
					<a href="javascript:void(0);" onclick='trimForm("platMemberInfo");postmyform("platMemberInfo", "errordiv");'>提交</a>
					<a href="javascript:void(0);" onclick="resetForm();">重置</a>
				</div>
			</div>
		</form:form>
	</div>
</div>
<input type="hidden" id="registerReason" value='${registerReason}'/>
<input type="hidden" id="emailAddressReason" value='${emailAddressReason}'/>
<input type="hidden" id="privatePolicy" value='${privatePolicy}'/>
<script type="text/javascript">
$(document).ready(function(){
	$("input[name='accName']").val("");
	$("input[name='passWd']").val("");
	var obj = $("input[name='checkbox']");
	obj.click( function () {
		if($(this).attr("checked") == "checked"){
			$(this).val("on");
		} else {
			$(this).val("off");
		}
	});
});
//注册、邮箱以及条款提示信息
function infoShow(typeFlag){
	var displayContent = "";
	if(typeFlag == "registerReason"){
		displayContent = $("#registerReason").val();
	}else if(typeFlag == "emailAddressReason"){
		displayContent = $("#emailAddressReason").val();
	}else{
		displayContent = $("#privatePolicy").val();
	}
	art.dialog({
		id:'showInfo',
 		title: "信息提示",
 		content: displayContent,
 		lock: true,
 		padding: '20px 20px',
 		drag: false,
    resize: false,
    max: false,
    min: false,
   	zIndex: 99999
	});
}
function postmyform(formName, resultArea) {
	var dataString = $("#" + formName).serialize();
	actionUrl = $("#" + formName)[0].action;
	
	postBymyURL(actionUrl, dataString, "POST","html",
		function(data) {
			if(data.indexOf('保存成功') >= 0) {
				alert("注册成功！");
				var email = $("#userEmail").val();
				var mailDomain = email.substring(email.indexOf("@")+1, email.indexOf("."));
				var mamagerMail = "${mamagerMail}";
				window.location.href="${globalUrl}register_member/activity?email="+email+"&mailDomain="+mailDomain+"&mamagerMail="+mamagerMail;
			}else {
				$('#' + resultArea).html(data);	
			}
		}
	);
}
//获取验证码倒计时
var wait;  
function get_code_time(obj) {
    if (wait == 0) {
    	obj.text("免费获取验证码");
    	obj.bind("click", function(){
    		var obj = $('#codeagain');
    		getCode(obj);
    	});
    } else {
    	obj.unbind("click");
    	obj.text(wait + "秒后重新获取");
        wait--;
        setTimeout(function() {
            get_code_time(obj);
        }, 1000);
    }
}

//获取验证码  
$('#codeagain').click(function() { 
	var obj = $(this);
	getCode(obj);
});

function getCode(obj){
    var phone = $("input[name='mobilephone']").val();
    if (phone == "") {
		alert('手机号不能为空');
	} else {
		$.ajax({  
	        url:"${globalUrl}register_member/phoneCode",  
	        type:"post",  
	        data: {'phone': phone},
	        dataType: "json",  
	        success: function (data) {
	        	wait = data.time;
            if(data.result == "success"){  
                alert("验证码已发送至您的手机");
                get_code_time(obj);  
            } else if(data.result == "black") {  
                alert("对不起，您输入的手机号码异常，不能注册为会员");  
            } else if (data.result == "empty") {
            	alert("手机号不能为空！");
						} else if (data.result == "numbError") {
							alert("手机号码格式有误！");
						} else {
            	alert("短信验证码发送失败");
            };
	        },  
	        error: function (data) {  
	            alert("短信验证码发送失败");  
	        }
	    });
	}
}
function resetForm(){
	$("#phoneNum").val("");
	$("#verifyCode").val("");
	$("#userEmail").val("");
	$("#accName").val("");
	$("#password").val("");
	$("#confirmPassWd").val("");
}
</script>