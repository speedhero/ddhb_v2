<%@page import="org.apache.el.lang.ELSupport"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
.ht_form span {
width: 100px !important;
}
.titlediv{width: 669px;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		if('${maxChangeLimit - myScore.changedCount}' <= 0){
			$('div input').attr("disabled", "disabled");
			alert("您剩余转赠次数为0");
			window.location.href='${globalUrl}usercenter.do?actionMethod=scoreManager&isCutPage=false';
		}
		if('${phoneFlag}' != 1){
			alert("您还没有绑定手机号码，请先去个人信息管理中绑定手机号码！");
			window.location.href='${globalUrl}usercenter.do?actionMethod=scoreManager&isCutPage=false';
		}
		//获取验证码  
		$('#codeagain').click(function() { 
			var obj = $(this);
			getCode(obj);
		});
	});
	function postmyform(formName, resultArea) {
		var verifyCode = $("#verifyCode").val();
		if (verifyCode == null || verifyCode == "") {
			$("#"+ resultArea).html("请输入手机验证码！");
			return;
		}
		var dataString = $("#" + formName).serialize();
		actionUrl = $("#" + formName)[0].action;
		
		actionUrl += "&verifyCode=" + verifyCode;
		postBymyURL(actionUrl, dataString, "POST", "html",
			function(data) {
				if(data.indexOf('保存成功') >= 0) {
					alert("转赠成功！");
					window.location.href="${globalUrl}usercenter.do?actionMethod=scoreManager&isCutPage=false";
				}else {
					$("#"+ resultArea).html(data);
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
function getCode(obj){
  var phone = $("input[name='mobilephone']").val();
  if (phone == "") {
		alert('手机号不能为空');
	} else {
		$.ajax({  
       url:"${globalUrl}usercenter.do?actionMethod=phoneCode",  
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
				} else { alert("短信验证码发送失败"); };
    		},
       error: function (data) { alert("短信验证码发送失败"); }
    });
	}
}
</script>
<form:form name="scoreTable" action="${globalUrl}usercenter.do?actionMethod=deliverScoreAdd" id="scoreTable">
<div class="cp_rt">
 <div class="gr_xxi">您好，您的账户目前有积分<b>${myScore.integral }</b>分，其中<b>${myScore.changedIntegral }</b>积分可以用来在购买服务时折现，抵扣服务费用，或转赠他人。</div>
 
 <div class="ht_left">
		<div class="ht_con">
			<div class="ht_form">
				<span>转入帐号ID：</span>
				<input name="countName" type="text" class="ipt_220">
				<div class="clear"></div>
			</div>
			<div class="ht_form">
				<span>转出积分数额：</span>
				<input type="text" name="scoreNum" class="ipt_220">/${myScore.changedIntegral }
				<div class="clear"></div>
			</div>
			<div class="ht_form">
				<span>手机号码：</span>
				<input type="text" id="phoneNum" disabled="disabled" name="mobilephone" value="${phoneNumber }"  class="ipt_130"><a id="codeagain" class="hq">免费获取验证码</a>
				<div class="clear"></div>
			</div>
			<div class="ht_form">
				<span>请输入验证码：</span>
				<input type="text" id="verifyCode" name="verifyCode" class="ipt_220">
				<div class="clear"></div>
			</div>
			<div class="ht_form">
				<span>剩余赠予次数：</span>
				<c:if test="${maxChangeLimit - myScore.changedCount  <0}">0/${maxChangeLimit }</c:if> <c:if test="${maxChangeLimit - myScore.changedCount ge 0}">${maxChangeLimit - myScore.changedCount }/${maxChangeLimit }</c:if>
				<div class="clear"></div>
			</div>
		</div>
		<div id="errorContainer" style="color:red;"></div>
		<div class="tj_an" style="float: left; margin-left: 15%;"><a id="doSearch" onclick='trimForm("scoreTable");postmyform("scoreTable", "errorContainer");'>转赠</a></div>
		<div class="tj_an" style="float: left; margin-left: 10%;"><a href="${globalUrl}usercenter.do?actionMethod=scoreManager&isCutPage=false">返回</a></div>
		<div class="htbz" >
			<ul id="flowPanel">
			</ul>
			<div class="clear"></div>
		</div>
	</div>
</div>
</form:form>