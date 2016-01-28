<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<title>问答&攻略 - ${title } </title>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/css.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/more.css">

<script type="text/javascript" src="${globalUrl}js/select/jquery.selectReplace.js"></script>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/select/jquery.select.css">

<script type="text/javascript">
$(document).ready(function(){
	$(".customerInput").focus(function(){
        var containerDiv = $(this).parent().parent();
        $(containerDiv).children().first().css("background-image", "url('${globalUrl}images/corner_short/green_left.png')");
        $(containerDiv).children().first().next().css("background-image", "url('${globalUrl}images/corner_short/green_middle.png')");
        $(containerDiv).children().first().next().next().css("background-image", "url('${globalUrl}images/corner_short/green_right.png')");
    });
	
    $(".customerInput").blur(function(){
        var containerDiv = $(this).parent().parent();
        $(containerDiv).children().first().css("background-image", "url('${globalUrl}images/corner_short/gray_left.png')");
        $(containerDiv).children().first().next().css("background-image", "url('${globalUrl}images/corner_short/gray_middle.png')");
        $(containerDiv).children().first().next().next().css("background-image", "url('${globalUrl}images/corner_short/gray_right.png')");
    });
	
    $("#titleInput").keyup(function(){
        var vv = $(this).val();
        len = vv.length;
        if (vv.length >= 20){
            $(this).val(vv.substring(0, 20));
            $("#titleCount").html(0);
        }else{
        	$("#titleCount").html(20-len);
        }
    });
    
    $("#contentarea").keyup(function(){
        var vv = $(this).val();
        len = vv.length;
        if (vv.length >= 200){
            $(this).val(vv.substring(0, 200));
            $("#contentCount").html(0);
        }else{
        	$("#contentCount").html(200-len);
        }
    });
    
	$("form").replaceAllSelect();
});
function postmyform(formName, resultArea) {
	var dataString = $("#" + formName).serialize();
	actionUrl = $("#" + formName)[0].action;
	postBymyURL(actionUrl, dataString, resultArea);
}

function postBymyURL(actionUrl, dataString, resultArea) {
	$.ajax({
		type : "POST",
		url : actionUrl,
		data : dataString,
		dataType : "html",
		success : function(data) {
			if(data.indexOf('保存成功') >= 0) {
				//var subtype = $("#questionStateSubType").attr("value");
				//var type = $("#questionStateType").attr("value");
				//window.location.href="${globalUrl}broker.show?actionMethod=getAllQuestion&typeId=1&questionStateType="+type+"&questionStateSubType="+subtype;
				window.location.href="${globalUrl}broker.show?actionMethod=getAllQuestion&typeId=1";
			}else {
				$("#"+ resultArea).html(data);
				reloadCaptcha();
			}
		}
	});
}

function changeRelativeSelect(selecctedValue){
	var typeId = $("#selectType").val();
	$("#questionStateType").attr("value", typeId);
	$.ajax({
		type : "POST",
		url : "${globalUrl}broker.show?actionMethod=getQuestionStategySubType&typeId=" + typeId,
		dataType : "json",
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		success : function (data) {
			$('#selectSubType').empty();
			$('<option value=0>--请选择--</option>').appendTo('#selectSubType');
			for(var i =0; i < data.length; i++){
				$('<option value='+ data[i].erpId +'>'+ data[i].subTypeName +'</option>').appendTo('#selectSubType');
			}
			$("#selectSubType").replaceSelect();
		}
	}); 
}

//用于刷新验证码
function refresh() {
	$("#verifyCodeImg").attr("src","RandomCodeCtrl?randum="+Math.random());
}
</script>

<div class="Location"><a href="#">首页</a> > <a href="#">我要提问</a></div>
 <div class="fcwd">
     <!--房产问答开始-->
     <form:form modelAttribute="questionStategy" name="questionStategy" id="questionStategy" action="${globalUrl}broker.show?actionMethod=addQuestionPost&questionId=${questionId }" >
     <div class="fcwd_l">
     	<div class="fctw">
         	<div class="biaot"><p><span>您还可以输入<span id="titleCount"  style="float: unset;">20</span>个字符</span>请输入您提问的标题 </p><input name="title" id="titleInput" type="text"></div>
             <div class="miaos"><p>您还可以输入<span> <span id="contentCount">200</span>个字符</p><textarea name="content" id="contentarea"></textarea></div>
             <div class="xzlx">
             	<p>请选择您的问题类型</p>
                 <div class="xiala xl_w145">
                     <select id="selectType" name="questionStategyType" relativeSelectId="true">
						<option>--请选择--</option>
						<c:forEach items="${qestionStrategyTypeList }" var="type">
							<option value="${type.erpId }">${type.typeName }</option>
						</c:forEach>
						<!--  
						<input type="hidden" name="questionStategyType" id="questionStateType"/>
						-->
					</select>
                 </div>
                 <div class="xiala xl_w145">
                 	<select id="selectSubType" name="questionStategySubType"><option value=0>--请选择--</option></select>
                 	<!--  
					<input type="hidden" name="questionStategySubType" id="questionStateSubType" val=""/>
					-->
                 </div>
             </div>
             <div class="yzm"><b>验证码<input name="verifyCode" id="verifyCode" type="text"></b><span><img src="${globalUrl}jcaptcha?id=1357744009" onclick="reloadCaptcha();" id="imageCaptcha" title="点击刷新"/></span></div>
         	<div id="errordiv" style="font-size: 12px; color: red;"></div>
             <div class="tj_an"><a href="javascript:void(0);" onclick='trimForm("questionStategy");postmyform("questionStategy", "errordiv");' >提 交</a></div>
         </div>
     </div>
     </form:form>
     <!--房产问答结束-->
     <!--房产问答右侧开始-->
     <div class="fcwd_r">
     	<div class="wytw_an"><a href="javascript:void(0);" onclick="window.location.href='${globalUrl}broker.show?actionMethod=addQuestion&questionId=0'">我要提问</a></div>
         <div class="zs_zj">
         	<div>
             	<span>共有房产知识</span>
                 <p><b>${questionCount }</b>条</p>
             </div>
         	<div class="divr">
             	<span>共有相关专家</span>
                 <p><b>${brokerCount }</b>人</p>
             </div>
         </div>
         <div class="wc_rt_ls">
         	<h2><a href="javascript:void(0);" onclick="window.location.href='${globalUrl}broker.show?actionMethod=getAllQuestion&typeId=1'">查看更多</a>房产买卖最新</h2>
             <ul>
             	<c:forEach items="${qestionStrategyListFirst }" var="qestionStrategyFirst">
					<li><a href="javascript:void(0);" onclick='window.location.href="${globalUrl}broker.show?actionMethod=initQuestionDetail&questionId=${qestionStrategyFirst.erpId }&typeId=${qestionStrategyFirst.questionType.parentType.erpId }&subTypeId=${qestionStrategyFirst.questionType.erpId }"'>${qestionStrategyFirst.title }</a></li>
				</c:forEach>
             </ul>
         </div>
         <div class="wc_rt_ls">
         	<h2><a href="javascript:void(0);" onclick="window.location.href='${globalUrl}broker.show?actionMethod=getAllQuestion&typeId=5'" >查看更多</a>房产租赁最新</h2>
             <ul>
             	<c:forEach items="${qestionStrategyListSecond }" var="qestionStrategyListSecond">
					<li><a href="javascript:void(0);" onclick='window.location.href="${globalUrl}broker.show?actionMethod=initQuestionDetail&questionId=${qestionStrategyListSecond.erpId }&typeId=${qestionStrategyListSecond.questionType.parentType.erpId }&subTypeId=${qestionStrategyListSecond.questionType.erpId }"'>${qestionStrategyListSecond.title }</a></li>
				</c:forEach>
             </ul>
         </div>
     </div>
     <!--房产问答右侧结束-->
 </div>
<script>
function reloadCaptcha() {  
    var captchaURL = $('#imageCaptcha').attr('src');  
    captchaURL = captchaURL.replace(captchaURL.substring(captchaURL.indexOf("=")+1, captchaURL.length), Math.floor(Math.random()*9999999999));  
    $('#imageCaptcha').attr('src', captchaURL);  
}
</script>
