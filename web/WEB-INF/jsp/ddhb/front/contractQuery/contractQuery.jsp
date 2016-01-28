<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="org.apache.el.lang.ELSupport"%>
<%@page import="java.util.List"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<title>合同查询 - ${title } </title>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/style.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/more.css">
<script type="text/javascript" src="${globalUrl}js/select/jquery.selectReplace.js"></script>
<!--<link rel="stylesheet" type="text/css" href="${globalUrl}css/select/jquery.select.css">-->
<script type="text/javascript" src="${globalUrl}js/hshb.common.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  //var contractObj = $.parseJSON('${returnString}');
  $("#doSearch").click(doSearch);
});

/**
 * 执行查询操作
 */
function doSearch(){
  var cardNo = $("#cardNo").val();
  var phoneNum = $("#phoneNum").val();
  var verifyCode = $("#verifyCodeInput").val();
  $("#errordiv").html("");
  dataString = { cardNo : cardNo, phoneNum : phoneNum, verifyCode : verifyCode, }
  $.ajax({
    type : "post",
    url : "${globalUrl}contract.show?actionMethod=contractQuery",
    //dataType : "html",
    dataType : "json",
    data : dataString,
    async:false,
    success : function (data) {
	    //var resultObj = $.parseJSON('${returnString}');
	    resultObj = data;
	    if(typeof(resultObj) != "undefined"){
	      if(resultObj.resultCode == "success"){
	        //如果查询成功
	        processSuccess(resultObj);
	      }else{
	        //如果查询失败
	        processError(resultObj);
	      }
	    }else{
	      $("#errordiv").html("查询失败，服务器未返回有效的结果，请重试！");
	    }
	    //刷新验证码
	    reloadCaptcha();
    }
  });
}

/**
 * 对查询结果进行处理
 */
function processSuccess(contractObj){
  if (contractObj != null){
     $("#cardNo").val(contractObj.IDCardNo);
     $("#phoneNum").val(contractObj.phoneNum);
     $("#flowPanel").html("");
     for (var i = 0; i < contractObj.flows.length; i++){
       var str = "";
       str='<li ';
       if (contractObj.flows[i].state != '1'){
         str += 'class="hui" ';
       }
       str += '><div class="bz_tit"><span>';
       
       if(contractObj.flows[i].state == '1'){
         str += "<span style='color: #00BB00;'>已完成</span>";
       }else{
         str += "未完成";
       }
       str += '</span>步骤：' + contractObj.flows[i].step +'</div><div class="bz_con">'+ contractObj.flows[i].stepName +'<br>'
           + contractObj.flows[i].completionDate +'<p>流程步骤说明:<br>'+ contractObj.flows[i].stepRemarks +'</p></div>';
       if(i < contractObj.flows.length - 1){
         str += '<div class="bz_jt"></div>';
       }
  
       //str += '<div class="bz_m"><a href="#">更多</a></div>';
       $("#flowPanel").append(str);
     }
     $("#info").show();
   }else{
     $("#info").hide();
   };
}
/**
 * 对查询出错进行处理
 */
function processError(contractObj){
  $("#cardNo").val(contractObj.IDCardNo);
  $("#phoneNum").val(contractObj.phoneNum);
  $("#errordiv").html(contractObj.resultMessage);
}

/**
 * 打开房贷计算器
 */
function openDialog(){
  var url = "${globalUrl}houseSecond.show?actionMethod=getCalculator&unitPrice=${house.unitPrice}&area=${house.area}";
  $.ajax({
    type : "get",
    url : url,
    dataType : "html",
    async:false,
    success : function (data) {
      var dataContent = '<html>'+data+'</html>';
      art.dialog({
        id:'calculator',
        title: "&nbsp;&nbsp;个人住房商业贷款",
        left:"200px",
        content: dataContent,
        lock: false,
        drag: true,
        resize: false,
        max: false,
        min: false,
        zIndex: 999
      });
    }
  });
}

/**
 * 刷新验证码
 */
function reloadCaptcha() {  
  var captchaURL = $('#imageCaptcha').attr('src');  
  captchaURL = captchaURL.replace(captchaURL.substring(captchaURL.indexOf("=")+1, captchaURL.length), Math.floor(Math.random()*9999999999));  
  $('#imageCaptcha').attr('src', captchaURL);  
}
</script>

<div style="height:42px;line-height:38px; clear:both; overflow:hidden;padding-left:12px;color: #575f6e;"><a onclick='window.location.href="${globalUrl}welcome.show?actionMethod=welcome"' style="cursor: pointer;">首页</a>&nbsp;>&nbsp;合同查询</div>
<!--合同查询开始-->
  <div class="ht_left">
    <div class="ht_tit"><a href="javascript:void(0);" class="one">合同查询</a></div>
      <div class="ht_con">
        <div class="ht_form">
        <span>证件号码：</span>
          <input id="cardNo" type="text" class="ipt_220">
          <div class="clear"></div>
        </div>
        <div class="ht_form">
          <span>手机号码：</span>
          <input type="text" id="phoneNum" class="ipt_220">
          <div class="clear"></div>
        </div>
        <div class="ht_form">
          <span>验证码：</span><input type="text" name="verifyCode" id="verifyCodeInput" class="ipt_80"><b ><img src="${globalUrl}jcaptcha?id=1357744009" onclick="reloadCaptcha();" id="imageCaptcha" title="点击刷新" style="width:77px;height:25px;" ></b>
        </div>
        <div id="errordiv" style="font-size: 12px; color: red;"></div>
        <div class="tj_an"><a id="doSearch" href="javascript:void(0);">提 交</a></div>
      </div>
      <div class="htbz" >
        <ul id="flowPanel"></ul>
        <div class="clear"></div>
      </div>
      <!--<div id="info" style="display:none; color: red; line-height:1.5; padding: 20px;">
      	<b>温馨提示：</b><br/>
      	以上未完成流程的计划完成日期，是在一般常规情况下的办理需时，谨供参考，具体以您的客户经理通知或咨询的结果为准。
      </div>-->
      <div id="info" style="display:none; color: red; line-height:1.5; padding: 20px;">
      	<b>温馨提示：</b><br/>
      	建议您完成缴款后２４小时再通过以下任意方式向我公司验证收据:<br />
        1、电话验证：您可以在周一至周五（8：30-17：30）拨打我公司的全国房屋租售一线通4008-966-888查询 电话接通后报出您收据上的“校验编码”即可查询收据的真伪及收据履行进程。<br />
        2、网站验证：您可以登录www.hshb.cn网站查询。
      </div>
  </div>
  <div class="ht_rt">
    <span><a href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=1" class="wymf">我要买房</a></span>
    <span><a href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=2" class="wymaif">我要卖房</a></span>
    <span><a href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=3" class="wyzf">我要租房</a></span>
    <span><a href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=4" class="wycz">我要出租</a></span>
    <span><a onclick="openCalcDialog();" class="fdjsq">房贷计算器</a></span>
  </div>
<!--合同查询结束-->
