<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
.detailsDiv{
	width:50%;
}
.detailsDivData{
	width: 341px;
	height: 40px;
	text-align: left;
	line-height: 40px;
	margin-left:100px;
}
.textField{
	height:30px;
	line-height: 30px;
}
.logincontext{
	width: 100%;
	height: 300px;
	padding-top: 40px;
	background-color: #ffffff;
}
.login-nav{
	height: 43px;
	width: 100%;
	
}
.contentBorBox{
	margin-left:10px;
}
.contentColumn{
	margin-left:50px;
}
.labelColumn{
	width:125px;
	float:left;
}
.editColumnInput{
	width:250px;
	display:none;
	height:40px;
}
.buttonStyle{
	width: 120px;
	height: 38px;
	background-color: #3FB8B1;
	border: 2px solid #3FB8B1;
	border-radius: 5px;
	cursor: pointer;
	text-align:center;
	line-height:37px;
	float:left;
	margin-left:10px;
	color:#ffffff;
}
.errorContainerList{
	color:red;
	font-size:14px;
	margin-left:100px;
	margin-top: 10px;
}
</style>
<script type="text/javascript">
var errorMessage = "";
function editMemberMode(){
	$("#showDetails").hide();
	$("#editDetais").show();
	$("#saveButton").show();
	$("#cancelButton").show();
	$("#editButton").hide();
	$("#phoneDiv").hide();
	$("#changePassword").hide();
	$("#emailAddressEdit").attr("disabled",false);
	$("#realNameEdit").attr("disabled",false);
	$("#identityCardEdit").attr("disabled",false);
}
function cancelEditMemberMode(){
	$("#showDetails").show();
	$("#editDetais").hide();
	$("#saveButton").hide();
	$("#cancelButton").hide();
	$("#editButton").show();
	$("#phoneDiv").show();
	$("#changePassword").show();
	$("#errorContainer").html("");
	$("#emailAddressEdit").val('${platMemberInfo.emailAddress}');
	$("#realNameEdit").val('${platMemberInfo.realName}');
	$("#identityCardEdit").val('${platMemberInfo.identityCard}');
	$("#emailAddressEdit").attr("disabled","disabled");
	$("#realNameEdit").attr("disabled","disabled");
	$("#identityCardEdit").attr("disabled","disabled");
}
function clickSave(){
	var flag=0;
	trimForm("platMemberInfo");
	errorMessage = "";
	flag = Verification(flag);
	if(flag == 1){
		$("#errorContainer").html(errorMessage);
		return;
	}
	if(confirm('确定保存?')){
		url = "${globalUrl}memberInfo.do?actionMethod=editMemberInfo&mobilephone="+$("#mobilephoneEdit").val()+"&emailAccount="+$("#emailAddressEdit").val()+"&id="+$("#identityCardEdit").val()+"&sex="+$('input:radio:checked').val()+"&memberId="+${platMemberInfo.id}+"&realName="+$.trim( $("#realNameEdit").val());
		window.location.href = url;
	}
}


function Verification(flag) {
	var emailAccount = $("#emailAddressEdit").val();
	var realName =$.trim( $("#realNameEdit").val());
	var id = $("#identityCardEdit").val();

	var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if (!emailReg.test(emailAccount)) {
		if (errorMessage.length == 0) {
			errorMessage = "无效的邮箱地址"
		} else {
			errorMessage += ",无效的邮箱地址"
		}
		flag = 1;
	}

	var idreg = /(^\d{17}([0-9]|X)$)/;
	if(id != ""){
		if (!idreg.test(id)) {
			if (errorMessage.length == 0) {
				errorMessage = "无效的身份证号码";
			} else {
				errorMessage += ",无效的身份证号码";
			}
			flag = 1;
		};
	}
	if(realName.length > 10)
	{
		if (errorMessage.length == 0) {
			errorMessage = "真实姓名超长";
		} else {
			errorMessage += ",真实姓名超长";
		}
		flag = 1;	
	}
	tagetUrl = "${globalUrl}memberInfo.do?actionMethod=validEmailUserd&emailAccount="+$("#emailAddressEdit").val()+"&memberId="+${platMemberInfo.id};
	var options = {
			   url:tagetUrl,
			   type:'POST',
			   async:false,
			   dataType:"text",
			   success:function(result){
				   if(result == "email.userd.error"){
					   if (errorMessage.length == 0) {
							errorMessage = "已经使用的邮箱地址"
						} else {
							errorMessage += ",已经使用的邮箱地址"
						}
						flag = 1;
				   }
			   }
			    };
	$.ajax(options);
	
	return flag;

}
function activeMobilePhone(){
	var dialogTitle = "绑定手机号码";
	var dataContent = document.getElementById("activeRemoveDialog");
	art.dialog({
		id:'activeRemoveDialog',
 		title: dialogTitle,
 		content: dataContent,
 		lock: true,
 		drag: false,
 	    resize: false,
 	    max: false,
 	    min: false,
 	  	zIndex: 999999
	});
	$("#activeRemoveDialogContent").text(content);
}
function removeMobilePhone(){
	var dialogTitle = "解除手机号码";
	var dataContent = document.getElementById("activeRemoveDialog");
	art.dialog({
		id:'activeRemoveDialog',
 		title: dialogTitle,
 		content: dataContent,
 		lock: true,
 		drag: false,
 	    resize: false,
 	    max: false,
 	    min: false,
 	  	zIndex: 999999
	});
	$("#activeRemoveDialogContent").text(content);
}
function doActiveOrRemove(){
	if ($("#phoneNum").val().trim() == '') {
		alert('请输入手机号码！');
		return;
	}
	if('${tmpMember.mobilephoneValidatedFlag == 0 }' == 'true'){
		$.ajax({  
	        url:"${globalUrl}usercenter.do?actionMethod=activePhoneNumber",  
	        type:"post",  
	        dataType:'json',
	        data: {'phone': $("#phoneNum").val(),'verifyCode':$("#verifyCode").val(),'memberId':'${platMemberInfo.id}'},
	        success: function (data) {
	        	if(data.result == "codeError"){
	        		$("#errordiv").text('验证码不正确');
	        	}else if(data.result == "phoneNumberExists"){
	        		$("#errordiv").text('该手机号码已经注册');
	        	}else{
	        		art.dialog.list['activeRemoveDialog'].close();
	        		window.location.href="";
	        	}
	        },  
	        error: function (data) {  
	            alert("出错，请稍后重试");
	        }
	    });
	}else{
		$.ajax({  
	        url:"${globalUrl}usercenter.do?actionMethod=removePhoneNumber",  
	        type:"post",  
	        dataType:'json',
	        data: {'phone': $("#phoneNum").val(),'verifyCode':$("#verifyCode").val(),'memberId':'${platMemberInfo.id}'},
	        success: function (data) {
	        	if(data.result == "codeError"){
	        		$("#errordiv").text('验证码不正确');
	        	}else if(data.result == "phoneNumberExists"){
	        		$("#errordiv").text('该手机号码已经注册');
	        	}else{
	        		art.dialog.list['activeRemoveDialog'].close();
	        		window.location.href="";
	        	}
	        },  
	        error: function (data) {  
	            alert("出错，请稍后重试");  
	        }
	    });
	}
}
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
function getCode(obj){
    var phone = $("input[name='mobilephone']").val();
    if (phone == "") {
		alert('手机号不能为空');
	} else {
		$.ajax({  
	        url:"${globalUrl}usercenter.do?actionMethod=phoneCode&mobilephone="+$("#mobilephoneEdit").val(),  
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
$(document).ready(function(){
	$('#codeagain').click(function() { 
		var obj = $(this);
		getCode(obj);
	});
});
</script>
<div class="meinfo_link">
	<div style="height: 25px;width:100%;">
		<div style="margin-top: 25px;"><a onclick='window.location.href="${globalUrl}"' style="cursor: pointer;">首页</a>&nbsp;>&nbsp;个人中心&nbsp;>&nbsp;个人信息管理</div>
	</div>
</div>
<form:form modelAttribute="platMemberInfo" name="platMemberInfo" action="${globalUrl}memberInfo.do?actionMethod=editMemberInfo">
<div class="cp_rt">
<div class="txypin">
 	<h2>个人信息</h2>
     <div class="yp_nr">
     	<ul>
         	<li>
             	<div class="wd">用户名：</div>
                 <div class="input"><input name="name" type="text" class="inptxt" disabled="disabled" value="${platMemberInfo.accName}"></div>
             </li>
         	<li class="duan">
             	<div class="wd">性别：</div>
             	<div id="showDetails"><c:if test="${platMemberInfo.sex eq '0'}">男 </c:if><c:if test="${platMemberInfo.sex eq '1'}">女 </c:if></div>
             	<div id="editDetais" style="display:none;">
                 <input type="radio" id="male" name="sex" class="radio" value="0" <c:if test="${platMemberInfo.sex eq '0' or platMemberInfo.sex == null}"> checked="checked"</c:if>/>男
				<input type="radio" id="female" name="sex" class="radio" value="1" <c:if test="${platMemberInfo.sex eq '1'}"> checked="checked"</c:if>/>女
			</div>
                 </li>
             	<li class="duan">
                 	<div class="wd">邮箱：</div>
                     <div class="input">
                     <input type="text" id="emailAddressEdit" name="emailAddress" disabled="disabled"   class="inptxt" value=" ${platMemberInfo.emailAddress}" />
                 </div>
             </li>
         	<li>
             	<div class="wd">真实姓名：</div>
                 <div class="input">
                 	<input type="text" id="realNameEdit" name="realName" disabled="disabled"   class="inptxt" value=" ${platMemberInfo.realName}" />
                 </div>
             </li>
             <li>
             	<div class="wd">身份证号：</div>
                 <div class="input">
                 	<input type="text" id="identityCardEdit" name="identityCard"  disabled="disabled"  class="inptxt" value=" ${platMemberInfo.identityCard}" />
                 </div>
             </li>
         </ul>
         <div class="clear"></div>
         
     </div>
      <div class="clear"></div>
     <div class="yp_nr" id="phoneDiv">
     	<ul>
     		<li style="width:100%;">
             	<div class="wd">手机号码：</div>
                  <div class="input"><input  id="mobilephoneEdit" disabled="disabled"  type="text" class="inptxt" value="${tmpMember.mobilephone}">
                  <c:if test="${tmpMember.mobilephoneValidatedFlag == 0 }">
                  <a id="phoneNumberButton" onclick="activeMobilePhone();">绑定手机号码</a>
                  </c:if>
                  
                  <c:if test="${tmpMember.mobilephoneValidatedFlag == 1 }">
                  <a id="phoneNumberButton"  onclick="removeMobilePhone();">解除手机号码</a>
                  </c:if>
                  </div>
             </li>
     	</ul>
     	<div class="clear"></div>
    	</div>
     <div id="errorContainer" style="color:red;"></div>
     <div class="yczz_an"><div id="saveButton" style="display:none;" class="buttonStyle"  onclick="clickSave();">保&nbsp;&nbsp;存</div>
<div id="cancelButton" style="display:none;" class="buttonStyle"  onclick="cancelEditMemberMode();">取消</div>
<div id="editButton" class="buttonStyle" onclick="editMemberMode();">修改个人信息</div>
<div id="changePassword" class="buttonStyle" onclick="window.open('${globalUrl}register_member/modifyPassword')" >修改密码</div></div>
 </div>
</div>
</form:form>
<div id="activeRemoveDialog" class="tcc_zp" style="display:none;margin: 0 !important; position: inherit !important;">
    <div class="tjcg">
    	<div class="form_item">
			<div class="name">手机号码</div>
			<div class="rt_ipt">
				<input id="phoneNum" <c:if test="${tmpMember.mobilephoneValidatedFlag == 1 }">disabled = "disabled"</c:if> type="text" name="mobilephone" class="input" value="${tmpMember.mobilephone}">
				<p style="background:url()">
					<a id="codeagain" class="hq">免费获取验证码</a>
				</p>
			</div>
			<div class="clear"></div>
		</div>
		<div class="form_item">
			<div class="name">填验证码</div>
			<div class="rt_ipt">
				<input id="verifyCode" name="verifyCode" type="text" class="input">
			</div>
			<div class="clear"></div>
		</div>
		<div id="errordiv" style="font-size: 12px; color: red;"></div>
        <div class="jsq_an"><a onclick="doActiveOrRemove();" class="tj">确定</a></div>
    </div>
</div>