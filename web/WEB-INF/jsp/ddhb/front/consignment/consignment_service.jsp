<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${globalUrl}js/jquery-ui-1.10.4.custom.js"></script>
<link href="${globalUrl}css/jquery-ui-1.10.4.custom.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${globalUrl}css/css.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/more.css">
<script type="text/javascript">
//用于刷新验证码
function refresh() {
	$("#verifyCodeImg").attr("src","RandomCodeCtrl?randum="+Math.random());
}
//验证码 验证
function verifyRandCode() {
	var verifyCode = $("#verifyCode").val();
	var randCode = <%=session.getAttribute("Rand")%>;
	if(verifyCode != randCode) {
		alert("验证码有误!");
		return false;
	}	
	return true;
}

function changecdb(){
	var cid = $("#s1").val();
	$('#countryidspan').attr('value', cid);
	$.ajax({
		type : "post",
		url : "${globalUrl}consignment.show?actionMethod=getcdb&cid=" + cid,
		dataType : "json",
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		success : function (data) {
				$('#s2').empty();
				for(var i =0; i < data.length; i++){
					$('<option value='+ data[i].id +'>'+ data[i].cbdName +'</option>').appendTo('#s2');
				}
		}
	}); 
} 

function selectcbd(){
	var cbdid = $("#s2").val();
	$('#cbdNospan').attr('value', cbdid);
}

function setRadioSpanVal(number){
	$('#radiospan').attr('value', number);
	if(number == 1){
		$('#s1').attr("disabled", "disabled");
		$('#s2').attr("disabled", "disabled");
		$('#t1').attr("disabled", "disabled");
	}else if(number ==2){
		$('#s1').removeAttr("disabled");
		$('#s2').removeAttr("disabled");
		$('#t1').attr("disabled", "disabled");
	}else if(number ==3){
		$('#s1').attr("disabled", "disabled");
		$('#s2').attr("disabled", "disabled");
		$('#t1').removeAttr("disabled");
	}
}

function setShi(){
	var tingid = $('#s3').val();
	$('#tingspan').attr('value', tingid);
}

function setTing(){
	var tingid = $('#s4').val();
	$('#shispan').attr('value', tingid);
}

function setWei(){
	var tingid = $('#s5').val();
	$('#weispan').attr('value', tingid);
}

function setPlatSex(){
	var platSex = $('#m2s1').val();
	$('#platsex').attr('value', tingid);
}

$(document).ready(function(){
	$('#s1').attr("disabled", "disabled");
	$('#s2').attr("disabled", "disabled");
	$('#t1').attr("disabled", "disabled");
});

$(function(){
	var a1 = $('#t1').autocomplete({
		source: function(request, response){
			$.ajax({
				url: "${globalUrl}community.show?actionMethod=getCommunityListMyName",
		    dataType:"json",
		    type: "POST",
		    data:{communityName: request.term},
		    success: function(data){
		    	response( $.map( getDateToshow(data), function( item ) { return item; }));
		    }
			});
		}
	});
	function getDateToshow(data){
		var communityNames=new Array();
		$.each(data, function( index, value ) {
		  communityNames[index] = value.communityName;
		});
		return communityNames;
	}
});
 
function changeItems(type){
	for(var i = 1;i < 5;i++){
		if(type == i){
			$("#buy"+i).toggleClass("one");
			try{
				var title = $(document).prop('title');
				if(title.indexOf("——")>0)
					title = title.substring(0, title.indexOf("——"));
				title += '——'+$("#buy"+i).text();
				$(document).prop('title', title);
			}catch(e){
				if(window.console) console.log(e);
			}
		}else{
			$("#buy"+i).removeClass("one");
		}
	}
	getData('${globalUrl}consignment.show?actionMethod=getPageData&type='+type,'','mainPage');
}
</script>
<div class="Location"><a href="${globalUrl}">首页</a> > <a href="${globalUrl}chushou">杭州二手房</a></div>
<div class="weituo">
	<div class="wt_tit">
		<a id="buy1" href="#"<c:if test="${Type == 1}"> class="one"</c:if> onclick="changeItems(1);">我要买房</a>
		<a id="buy2" href="#"<c:if test="${Type == 2}"> class="one"</c:if> onclick="changeItems(2);">我要卖房</a>
		<a id="buy3" href="#"<c:if test="${Type == 3}"> class="one"</c:if> onclick="changeItems(3);">我要租房</a>
		<a id="buy4" href="#"<c:if test="${Type == 4}"> class="one"</c:if> onclick="changeItems(4);">我要出租</a>
	</div>

	<div class="main mainBackgroundColor" id="mainPage">
		<c:if test="${pageName eq 'house_buy' }">
			<%@include file="/WEB-INF/jsp/ddhb/front/consignment/house_buy.jsp"%>
		</c:if>
		<c:if test="${pageName eq 'house_sell' }">
			<%@include file="/WEB-INF/jsp/ddhb/front/consignment/house_sell.jsp"%>
		</c:if>
		<c:if test="${pageName eq 'house_rent' }">
			<%@include file="/WEB-INF/jsp/ddhb/front/consignment/house_rent.jsp"%>
		</c:if>
		<c:if test="${pageName eq 'house_renting' }">
			<%@include file="/WEB-INF/jsp/ddhb/front/consignment/house_renting.jsp"%>
		</c:if>
	</div>
</div>
