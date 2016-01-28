<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function doQuery(){
		$("#errordiv").empty();
		var cardNo = $("#cardNo").val();
		var contractNo = $("#contractNo").val();
		var verifyCode = $("#verifyCodeInput").val();
		var dataString = {"cardNo" : cardNo, "contractNo": contractNo,"verifyCode" : verifyCode};
		$.ajax({
			type : "POST",
			url : "${globalUrl}usercenter.do?actionMethod=doQuery",
			data : dataString,
			success : function(data) {
				if(data.indexOf("error") >= 0){
					var message = data.split("|");
					reloadCaptcha();
					$("#errordiv").text(message[1]);
				}else{
					reloadCaptcha();
					$("#flowPanel").html("");
					var contractObj = eval("(" + data + ")");
					if (contractObj != null){
						for (var i = 0; i < contractObj.flows.length; i++){
							var str = "";
							str='<li ';
							if (contractObj.flows[i].state != '1'){
								str += 'class="hui" ';
							}
							str += '><div class="bz_tit"><span>';
							
							if(contractObj.flows[i].state == '1'){
								str += "已完成";
							}else{
								str += "未完成";
							}
							str += '</span>步骤：' + contractObj.flows[i].step +'</div><div class="bz_con">'+ contractObj.flows[i].stepName +'<br>'
									+ contractObj.flows[i].completionDate +'<p>流程步骤说明:<br>'+ contractObj.flows[i].stepRemarks +'</p></div>';
							if(i < contractObj.flows.length - 1){
								str += '<div class="bz_jt"></div>';
							}		
							$("#flowPanel").append(str);
						}
						$("#info").show();
					}else{
						$("#info").hide();
					}
				}
				
			},
			error : function() {
			}
		});
	}
	function reloadCaptcha() {  
	    var captchaURL = $('#imageCaptcha').attr('src');  
	    captchaURL = captchaURL.replace(captchaURL.substring(captchaURL.indexOf("=")+1, captchaURL.length), Math.floor(Math.random()*9999999999));  
	    $('#imageCaptcha').attr('src', captchaURL);  
	}
</script>



<div class="cp_rt">
	<div class="licn"><a onclick='window.location.href="${globalUrl}welcome.show?actionMethod=welcome"'>个人中心</a>&nbsp;>&nbsp;合同查询</div>
	<div class="ht_left">
		<div class="ht_con">
			<div class="ht_form">
				<span>证件号码：</span>
				<input id="cardNo" type="text" class="ipt_220">
				<div class="clear"></div>
			</div>
			<div class="ht_form">
				<span>合同号码：</span>
				<input type="text" id="contractNo" class="ipt_220">
				<div class="clear"></div>
			</div>
			 <div class="ht_form">
		        <span>验证码：</span><input type="text" name="verifyCode" id="verifyCodeInput" class="ipt_80"><b ><img src="${globalUrl}jcaptcha?id=1357744009" onclick="reloadCaptcha();" id="imageCaptcha" title="点击刷新" style="width:77px;height:25px;" ></b>
		     </div>
		     <div id="errordiv" style="font-size: 12px; color: red;"></div>
		</div>
		<div class="tj_an"><a id="doSearch" onclick="doQuery()">提 交</a></div>
		<div class="htbz" >
			<ul id="flowPanel">
			</ul>
			<div class="clear"></div>
		</div>
		<div id="info" style="display:none;">温馨提示：以上未完成流程的计划完成日期，是在一般常规情况下的办理需时，谨供参考，具体以您的客户经理通知或咨询的结果为准</div>
	</div>
</div>
