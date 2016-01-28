<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="${globalUrl}js/jquery.artDialog.source.js"></script>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/css.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/more.css">
<style>
.tcc_zp {
    margin: 0 !important;
    position: inherit !important;
}
.yyfw_ls li {
	padding-left:115px;
    width: 66% !important;
}
@media screen and (max-width: 470px) {
.yyfw_ls li{
padding-left:0;
width: 99% !important;
}
}
</style>
<script type="text/javascript">
var flagEmail = true;

/* 邮箱格式验证 */
function matchEmail(num){
	var format = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/;
	if(!num.match(format)){
		flagEmail = false;
		return false;
	}else{
		flagEmail = true;	
		return true;
	}
}

function getEmail(num){
	if(num == "请输入邮箱地址..."){
		$("#noticeEmail").val("");
		$("#noticeEmail").css("color","#000000");
	}
}

function testEmail(num){
	var str = num.trim();
	if(str == ""){
		$("#noticeEmail").css("color","#CCCCCC");
		$("#noticeEmail").val("请输入邮箱地址...");
		flagEmail = true;
	}else if(!matchEmail(str)){
		$("#errordiv").css("color","red");
		$("#errordiv").css("font-size","12px");
		$("#errordiv").html('请输入正确的邮箱地址，如：baidu@163.com').show();
		flagEmail = false;
	}else {
		$("#noticeEmail").css("color","#000000");
		$("#noticeEmail").val(str);
		flagEmail = true;
	}
}

function postmyform(formName, resultArea) {
	var dataString = $("#" + formName).serialize();
	actionUrl = $("#" + formName)[0].action;
	if($('#noticeEmail').val().trim() == '请输入邮箱地址...') {
		alert("请输入邮箱地址!");
		/* infoBox("请输入邮箱地址!","消息"); */
		return;
	}else {
			testEmail($('#noticeEmail').val().trim());
	}
	if(flagEmail) {
		
		postBymyURL(actionUrl+"&emailAddress="+$("#noticeEmail").val(), dataString, "POST", "html",
			function(data) {
				if(data.indexOf('保存成功') >= 0) {
					if(data.indexOf('邮箱未验证') >= 0) {
						infoBox("添加成功,您的邮箱地址"+$("#noticeEmail").val()+"需要验证，系统已发送邮件到您的邮箱，请前往查看，根据提示完成验证后才能收取定阅信息");
						art.dialog({id: 'newsNotice'}).close();
						return;
					}
					if("${housetype}" == 1){
						window.location.href="${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=${searchno }&brokerId=${brokerId}";
					}else {
						window.location.href="${globalUrl}rent.show?actionMethod=houseRentDetail&houseNo=${searchno }&brokerId=${brokerId}";
					}
				} else if(data.indexOf("邮箱地址已存在")>=0){
					$("#errordiv").html('邮箱地址已存在').show();
				} else if(data.indexOf("验证码为必填项")>=0){
					$("#errordiv").html('验证码为必填项').show();
				} else if(data.indexOf("验证码错误")>=0){
					$("#errordiv").html('验证码错误').show();
				}
				reloadCaptcha();
			}
		);
	} 
}

</script>
<form:form id="newsNotice" modelAttribute="newsNotice"
	name="newsNotice" method="POST" action="${globalUrl}news.show?actionMethod=saveNoticeNews" >
	<div class="tcc_zp" >
		<div class="yyfw_ls">
			<ul>
                <li><span>邮箱：</span>
                    <input type="text" name="emailAddress" id="noticeEmail" value="请输入邮箱地址..." onfocus="getEmail(this.value)" onblur="testEmail(this.value)"  style="color: #CCCCCC;"/>
                </li>
                <li>
					<span>验证码：</span>
					<input value="" name="verifyCode" id="verifyCode" type="text" />
					<span><img src="${globalUrl}jcaptcha?id=1357744009" onclick="reloadCaptcha();" id="imageCaptcha" title="点击刷新"></span>
				</li>
                <li>
                	<p>定阅专业团队倾心打造的市场报告，把握最新的楼市动态和走势。</p>
                </li>
            </ul>
			<div class="clear"></div>
			<div class="tj_an">
				<div id="errordiv" style="font-size: 12px; color: red;"></div>
	        	<a  href="javascript:void(0);" onclick='trimForm("newsNotice");postmyform("newsNotice", "errordiv");'>提交</a>
       		</div>
       		<div class="yyfw_a">
       		<a onclick="art.dialog.list['newsNotice'].close();daikan();">预约服务</a>
	        <a onclick="art.dialog.list['newsNotice'].close();<c:if test="${housetype == 1 }">consignment();">我要买房</c:if><c:if test="${housetype == 2 }">consignmentRent();">我要租房</c:if></a>
	        <c:if test="${housetype == 1 }"><a onclick="art.dialog.list['newsNotice'].close();reduceNotice();">降价通知</a></c:if>
	        <a style="background-color:#d83939">订阅市场动态</a>
        </div>
		</div>
	</div>
</form:form>
