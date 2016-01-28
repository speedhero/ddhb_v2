<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/dlzc.css">
<style>
i{color: #ff0000}
.tcc_zp {margin: 0 !important;  position: inherit !important;}
.regAndLogin{float:left;}
#submitS{display:block; float:left; width: 120px; height:34px; background-color: rgb(63, 184, 177); font-size: 18px; color: rgb(255, 255, 255); text-align: center;}
#submitS:hover{background: rgb(17, 162, 154);}
.submitContainer{padding: 20px 0px 50px 0px; width:250px; line-height:34px; margin: 0 auto;}
.regAndLogin a{margin-left:20px; font-size:14px;}
.regAndLogin a:hover{color: rgb(63, 184, 177);}
</style>
<script type="text/javascript" src="${globalUrl}js/ht_frame.js"></script>
<script type="text/javascript" src="${globalUrl}js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${globalUrl}js/commonGround/notice.js"></script>
<script type="text/javascript" >var globalUrl = '${globalUrl}';</script>
<script type="text/javascript">
function GetDateStr(AddDayCount) {
	var dd = new Date();
	dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
	var y = dd.getFullYear();
	var yy = y.toString().substr(2, 2);
	var m = dd.getMonth();//获取当前月份的日期
	var month=new Array(12);
	month[0]="01";
	month[1]="02";
	month[2]="03";
	month[3]="04";
	month[4]="05";
	month[5]="06";
	month[6]="07";
	month[7]="08";
	month[8]="09";
	month[9]="10";
	month[10]="11";
	month[11]="12";
	var d = dd.getDate();
	var q = dd.getDay();
	if(q == 0){
		q = "日";
	}else if(q == 1){
		q = "一";
	}else if(q == 2){
		q = "二";
	}else if(q == 3){
		q = "三";
	}else if(q == 4){
		q = "四";
	}else if(q == 5){
		q = "五";
	}else if(q == 6){
		q = "六";
	}
	return yy+"-"+month[m]+"-"+d+"[周"+q+"]";
	}
function DaytoLocal(AddDayCount){
	var dd = new Date();
	dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
	var q = dd.getDay();
	if(q == 0){
		q = "日";
	}else if(q == 1){
		q = "一";
	}else if(q == 2){
		q = "二";
	}else if(q == 3){
		q = "三";
	}else if(q == 4){
		q = "四";
	}else if(q == 5){
		q = "五";
	}else if(q == 6){
		q = "六";
	}
	return "周"+q;
}
function GetDate(AddDayCount) {
	var dd = new Date();
	dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
	var y = dd.getFullYear();
	var m = dd.getMonth()+1;//获取当前月份的日期
	var d = dd.getDate();
	return y+"-"+m+"-"+d;
	}
$(document).ready(function(){
	$('<option value="' + GetDate(0) + '">今天['+ DaytoLocal(0) +']</option>').appendTo('#sel1');
	$('<option value="' + GetDate(1) + '">明天['+ DaytoLocal(1) +']</option>').appendTo('#sel1');
	$('<option value="' + GetDate(2) + '">后天['+ DaytoLocal(2) +']</option>').appendTo('#sel1');
	for(var i=3; i<7; i++){
		$('<option value="' + GetDate(i) + '">'+ GetDateStr(i) +'</option>').appendTo('#sel1');
	};
	$("#select1").append("<a optionValue=" + GetDate(0) + " selectId='sel1' displayId='xiala_sel1'>" + "今天[" + DaytoLocal(0) +"]</a>");
	$("#select1").append("<a optionValue=" + GetDate(1) + " selectId='sel1' displayId='xiala_sel1'>" + "明天[" + DaytoLocal(1) +"]</a>");
	$("#select1").append("<a optionValue=" + GetDate(2) + " selectId='sel1' displayId='xiala_sel1'>" + "后天[" + DaytoLocal(2) +"]</a>");
	for(var i=3; i<7; i++){
		$("#select1").append("<a optionValue=" + GetDate(i) + " selectId='sel1' displayId='xiala_sel1'>" + GetDateStr(i) + "</a>");
	};
	
	$(".xlnr a").click(function(){
		var relativeSelect = $("#" + $(this).attr("selectId"));
		var displayObj = $("#" + $(this).attr("displayId"));
		var optionValue = $(this).attr("optionValue");
		$(relativeSelect).val(optionValue);
		$(displayObj).html($(this).html());
		$(this).parent().css("display", "none");
	});
	
	$(".xiala>a").click(function(){
		var xlnrObj = $(this).parent().find(".xlnr");
		if ($(xlnrObj).css("display") == 'block'){
			$(xlnrObj).css("display", "none");
		}else{
			$(xlnrObj).css("display", "block");
		}
	});
});
//用于刷新验证码
function refresh() {
	$("#verifyCodeImg").attr("src","RandomCodeCtrl?randum="+Math.random());
}
function postmyform(formName, resultArea) {
	var dataString = $("#" + formName).serialize();
	actionUrl = $("#" + formName)[0].action;

	postBymyURL(actionUrl, dataString, "POST", "html",
		function(data) {
			if(data.indexOf('预约成功') >= 0) {
				art.dialog({id: 'daikan'}).close();
				infoBox("预约已提交！");
			}else if(data.indexOf('验证码错误') >= 0){
				reloadCaptcha();
				$('#' + resultArea).html(data);
			}else {
				reloadCaptcha();
				$('#' + resultArea).html(data);
			}
		}
	);
}

function reduceNotice(){
	var dataString={
			"brokerId":'${brokerId}',
			"searchno":'${searchno}',
			"houseId":'${houseId}',
			"housetype":'${housetype}',
			"houseArea":'${houseArea}',
			"houseTing":'${houseTing}',
			"houseShi":'${houseShi}',
			"houseWei":'${houseWei}',
			"communityName":'${communityName}',
			"housePrice":'${housePrice}'
	};
	var actionUrl = "${globalUrl}notice.show?actionMethod=inputInfoPage";
	var id = 'reduceNotice';
	var title= "&nbsp;&nbsp;降价通知";
	_services(actionUrl, id, title, dataString);
}

function newsNotice(){
	var actionUrl = "${globalUrl}news.show?actionMethod=noticeNews&searchno=${searchno}&brokerId=${brokerId }&houseType=${housetype }&houseId=${houseId }";
	_services(actionUrl, 'newsNotice', "&nbsp;&nbsp;订阅市场动态", null, "GET");
	
}

function reset(id){
	art.dialog.list[id].close();
	if(id == "newsNotice"){
		newsNotice();
	}else{
	reduceNotice();
	}
}
</script>
<form:form modelAttribute="memberBespeak" name="memberBespeak" action="${globalUrl}houseSecond.show?actionMethod=subBespeak" id="memberBespeak">
<input type="hidden" name="houseId" value="${houseId}"/>
<input type="hidden" name="brokerId" value="${brokerId}"/>
<input type="hidden" name="housetype" value="${housetype}"/>
<input type="hidden" name="searchno" value="${searchno}"/>
<div class="tcc_zp">
	<!-- <h2><a href="#"></a>预约服务</h2> -->
    <div class="yyfw_con">
    	<div class="yyfw_bg"><strong>${communityName }</strong><span><fmt:formatNumber value="${houseArea }"/>平米</span><span>${houseShi }室${houseTing }厅${houseWei }卫</span><span><c:if test="${housetype == 1}"><b><fmt:formatNumber value="${housePrice/10000 }"/></b>万元</c:if><c:if test="${housetype == 2}"><b><fmt:formatNumber value="${housePrice }"/></b>元/月</c:if></span></div>
        <div class="yyfw_ls">
        	<h3>请选择预约时间：</h3>
            <ul>
            	<li class="li_1"><span><i>*&nbsp;</i>日期：</span>
                    <div class="xiala xl_w145">
                        <a class="a" id="xiala_sel1">请选择</a>
                        <div class="xlnr" id="select1"></div>
                        <select name="date" id="sel1" style="display:none;">
			               	<option value="">请选择</option>
			            </select>
                    </div>
                </li>
            	<li class="li_2"><span><i>*&nbsp;</i>到店时间：</span>
                    <div class="xiala xl_w145">
                        <a class="a" id="xiala_sel2">请选择</a>
                        <div class="xlnr">
                        	<a optionValue="8,10" selectId="sel2" displayId="xiala_sel2">08:00 - 10:00</a>
                        	<a optionValue="10,12" selectId="sel2" displayId="xiala_sel2">10:00 - 12:00</a>
                        	<a optionValue="12,14" selectId="sel2" displayId="xiala_sel2">12:00 - 14:00</a>
                        	<a optionValue="14,16" selectId="sel2" displayId="xiala_sel2">14:00 - 16:00</a>
                        	<a optionValue="16,18" selectId="sel2" displayId="xiala_sel2">16:00 - 18:00</a>
                        	<a optionValue="18,20" selectId="sel2" displayId="xiala_sel2">18:00 - 20:00</a>
                        	<a optionValue="20,21" selectId="sel2" displayId="xiala_sel2">20:00 - 21:00</a>
                        </div>
                       	<select id="sel2" name="timeBespeak" style="display:none;">
			               	<option value="">请选择</option>
			               	<option value="8,10">08:00 - 10:00</option>
											<option value="10,12">10:00 - 12:00</option>
											<option value="12,14">12:00 - 14:00</option>
											<option value="14,16">14:00 - 16:00</option>
											<option value="16,18">16:00 - 18:00</option>
											<option value="18,20">18:00 - 20:00</option>
											<option value="20,21">20:00 - 21:00</option>
		               </select>
                    </div>
                </li>
            </ul>
            <div class="clear"></div>
        </div>
        <div class="yyfw_ls">
        	<h3>联系方式：</h3>
            <ul>
            	<li><span><i>*&nbsp;</i>姓名：</span><input name="contacts" type="text" id="text2" value="${LoginMember.accName}"></li>
            	<li><span><i>*&nbsp;</i>称呼：</span>
                    <div class="xiala xl_w80">
                        <a class="a" id="xiala_sel3">
                        	 <c:if test="${LoginMember.sex == 0 || LoginMember == null}">先生</c:if>
                        	 <c:if test="${LoginMember.sex == 1 }">女士</c:if>
						</a>
                        <div class="xlnr">
                        	<a optionValue="0" selectId="sel3" displayId="xiala_sel3">先生</a>
                        	<a optionValue="1" selectId="sel3" displayId="xiala_sel3">女士</a>
                        </div>
                        <select id="sel3" name="gender" style="display:none;">
		                	<c:if test="${platMemberInfo != null }">
		                		<option value="0" <c:if test="${LoginMember.sex == 0 }">selected</c:if>>先生</option>
		                		<option value="1" <c:if test="${LoginMember.sex == 1 }">selected</c:if>>女士</option>
		                	</c:if>
		                	<c:if test="${platMemberInfo == null }">
		                		<option value="0" selected>先生</option>
		                		<option value="1" >女士</option>
		                	</c:if>
		                </select>
                    </div>
                </li>
            	<li class="nofd"><span><i>*&nbsp;</i>联系电话：</span><input name="contactsPhone" type="text" id="text1" value="${LoginMember.mobilephone}"></li>
            	<li class="nofd"><span><i>*&nbsp;</i>验证码：</span><input type="text" id="text3" name="verifyCode" id="verifyCode" class="ipt_80"><b><img src="${globalUrl}jcaptcha?id=1357744009" onclick="reloadCaptcha();" id="imageCaptcha" title="点击刷新"></b></li>
            </ul>
            <div class="clear"></div>
        </div>
        <div id="errordiv" style="font-size: 12px; color: red;"></div>
        <div class="submitContainer">
       		<a href="javascript:void(0);" id="submitS" onclick='trimForm("memberBespeak");postmyform("memberBespeak", "errordiv");'>提交</a>
	        <c:if test="${LoginMember == null}">
	        	<div class="regAndLogin">
	        		<a onclick="art.dialog.list['daikan'].close();loginBox();">登录3</a>
		        	<a onclick="art.dialog.list['daikan'].close();registerBox();">注册</a>
	        	</div>
	        </c:if>
	        <div style="clear: both;"></div>
        </div>
        <div class="yyfw_a">
        	<a style="background-color:#d83939">预约服务</a>
	        <a onclick="art.dialog.list['daikan'].close();<c:if test="${housetype == 1 }">consignment();">我要买房</c:if><c:if test="${housetype == 2 }">consignmentRent();">我要租房</c:if></a>
	        <c:if test="${housetype == 1 }"><a onclick="art.dialog.list['daikan'].close();reduceNotice();">降价通知</a></c:if>
	        <a onclick="art.dialog.list['daikan'].close();newsNotice();">订阅市场动态</a>
        </div>
		<div class="clear"></div>
    </div>
</div>
</form:form>
<script>
function reloadCaptcha() {  
    var captchaURL = $('#imageCaptcha').attr('src');  
    captchaURL = captchaURL.replace(captchaURL.substring(captchaURL.indexOf("=")+1, captchaURL.length), Math.floor(Math.random()*9999999999));  
    $('#imageCaptcha').attr('src', captchaURL);  
}
</script>