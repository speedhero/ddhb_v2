<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<title>委托服务 - ${title } </title>
<script type="text/javascript" src="${globalUrl}js/select/jquery.selectReplace.js"></script>
<script type="text/javascript" src="${globalUrl}js/commonGround/house_buyAndRent.js"></script>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/select/jquery.select.css">

<script type="text/javascript">
$(document).ready(function(){
	 $("form").replaceAllSelect();
	 reloadCaptcha();
});
</script>


<script type="text/javascript">
function changeRelativeSelect(selecctedValue) {
	var cid = selecctedValue;
	$('#countryidspan').attr('value', cid);
	$.ajax({
		type : "post",
		url : "${globalUrl}consignment.show?actionMethod=getcdb&cid=" + cid,
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(data) {
			$('#s2').empty();
			for (var i = 0; i < data.length; i++) {
				$('<option value='+ data[i].websiteId +'>'+ data[i].name + '</option>').appendTo('#s2');
			}
			$("#s2").replaceSelect();
		}
	});
}
$(document).ready(function() {
	$('#s1').attr("disabled", "disabled");
	$('#s2').attr("disabled", "disabled");
	//$('#textAuto').attr("disabled", "disabled");
});
$(function() {
	var a1 = $('#textAuto').autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "${globalUrl}community.show?actionMethod=getCommunityListMyName",
				dataType : "json",
				type : "POST",
				data : {
					communityName : request.term
				},
				success : function(data) {
					response($.map(
							getDateToshow(data),
							function(item) {
								return item;
							}));
				}
			});
		}
	});
	function getDateToshow(data) {
		var communityNames = new Array();
		$.each(data, function(index, value) {
			communityNames[index] = value.communityName;
		});
		return communityNames;
	}
	var strs = "";
	var furStr = "";
	$(".subBox").click(function(){
		if(furStr != ""){
			furStr = "";
		}
		$.each($(".subBox"), function(index,value){
			var flag = $(value).attr("checked");
			if(flag == "checked"){
				var str = $(this).attr("value");
				strs = str + ",";
				furStr = furStr + strs;
			}
		});
		$("#facility").attr("value",furStr);
	});
});

$(function() { 
	var fstr = "";
	var fustr = "";
	$("#checkAll").click(function() {
		var flag = $(this).attr("checked");
		$(".subBox").each(function() {
			if(flag == "checked"){
				$(this).attr("checked", flag);
				var fur = $(this).attr("value");
				fstr = fur +",";
				fustr = fustr + fstr;
			}else {
				$(this).removeAttr("checked");
				fustr = "";
			}
		});
		$("#facility").attr("value",fustr);
	});
	}) 
</script>
<form:form modelAttribute="entrustRenthouse" name="entrustRenthouse" action="${globalUrl}consignment.show?actionMethod=addRenthouse" id="entrustRenthouse">
<div class="wt_con">
	<h3>租赁需求</h3>
	<div class="wt_tb">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="right" valign="top" class="td_1"><div class="td_1">入小区名称：</div></td>
				<td>
        	    <div class="fywz">
            	  <ul>
                	<li><input id="textAuto" name="communityName" type="text" value="请输入小区名称..." onfocus='if(value=="请输入小区名称...") {value ="";$(this).css({"color":"#333"})};' onblur='if(value=="") {value="请输入小区名称...";$(this).css({"color":"#CCCCCC"})};' style="color:#CCCCCC;width:150px;!important" class="txt_w490" >
                	&nbsp;&nbsp;<font style="font-size:12px;">(或许您可以直接<a onclick="window.open('${globalUrl}rent.show?actionMethod=dimquery&type=2')" style="font-size:18px;">在线找房</a>，寻找您心目中家)</font>
					</li>
				  </ul>
				</div>
				</td>
			</tr>
			<tr>
				<td align="right" valign="top" class="td_1"><div class="td_1">称呼：</div></td>
				<td>
				<div class="wt_ip310">
				<div class="xiala xl_w90">
					<select id="m2s1" name="gender" onchange="setPlatSex();">
	              			<c:if test="${platMemberInfo != null }">
	              				<option value="0" <c:if test="${platMemberInfo.sex == 0 }">selected</c:if>>先生</option>
	              				<option value="1" <c:if test="${platMemberInfo.sex == 1 }">selected</c:if>>女士</option>
	              			</c:if>
	              			<c:if test="${platMemberInfo == null }">
	              				<option value="0" selected>先生</option>
	              				<option value="1" >女士</option>
	              			</c:if>
	                    </select>
				</div>
			</div>
				</td>
			</tr>
			<tr>
				<td align="right" valign="top" class="td_1"><div class="td_1">联系电话：</div></td>
				<td>
				<div class="wtlx_ln">
				<div class="lx_ipt">
				<input id="m2t2" type="text" value="${platMemberInfo.mobilephone }" name="phone">
				</div>
				<div class="clear"></div>
				</div>
				</td>
			</tr>
			<%-- 
			<tr>
			
				<td align="right" valign="top" class="td_1"><div class="td_1">居室要求：</div></td>
				<td class="hxyq">
					<div class="xiala xl_w64">
						<select id="s3" name="minShi" onchange="setShi()">
	                         <c:forEach items="${houseType}" var="type">
	                           	<option value="${type.htCount}">${type.htCount}</option> 
	                         </c:forEach>
	                     </select>
					</div> <span>最少室</span>
					<div class="xiala xl_w64">
						<select id="s4" name="maxShi" onchange="setTing()">
	                         <c:forEach items="${houseType}" var="type">
	                           	<option value="${type.htCount}">${type.htCount}</option> 
	                         </c:forEach>
	                     </select>
					</div> <span>最多室</span>
					<div class="wt_ip310">
						<div class="xiala xl_w64">
							<select id="s5" class="m1s3" name="ting" onchange="setWei()">
		                         <option value="1" selected="">1</option>
		                         <option value="2">2</option>
		                         <option value="3">3</option>
		                         <option value="4">4</option>
		                         <option value="5">5</option>
		                         <option value="6">6</option>
		                     </select>
						</div>
						<span>厅</span>
					</div>
				</td>
				
			</tr>
			<tr>
			
				<td align="right" valign="top" class="td_1"><div class="td_1">租赁方式：</div></td>
				<td class="hxyq">
					<div class="xiala xl_w64">
						<select id="s1" name="rentType">
							<option value="整租" selected="">整租</option>
		                    <option value="合租">合租</option>
	                    </select>
					</div>
				</td>
				
			</tr>
			<tr>
			
				<td align="right" valign="top" class="td_1"><div class="td_1">室内设施：</div></td>
				<td>
					<div class="xiala facilitiesWidth">
						<div>
		          			<c:forEach items="${furList }" var="fur">
			           			<input class="subBox" type="checkbox" value="${fur.furName }"/><span>&nbsp;&nbsp;${fur.furName }</span>
			           		</c:forEach>
		           			<input id="checkAll" type="checkbox" /><span>&nbsp;&nbsp;全选</span>
		          		</div>
		          		<input type="hidden"  name="facility" id="facility"/>
					</div>
				</td>
				
			</tr>
			<tr>
			
				<td align="right" valign="top" class="td_1"><div class="td_1">预算价位：</div></td>
				<td class="jiawei">
					<input id="minPrice" type="text" value="" name="minPrice"><span>—</span><input id="maxPrice" type="text" value="" name="maxPrice">
					<span>元/月</span>
				</td>
				
			</tr>
			--%>
			<tr>
				<td align="right" valign="top" class="td_1"><div class="td_1">其他需求：</div></td>
				<td class="otherxq"><textarea name="other" id="ta1" onfocus='if(value=="请输入其他需要说明的要求...") {value ="";$(this).css({"color":"#333"})};' onblur='if(value==""){value="请输入其他需要说明的要求...";$(this).css({"color":"#CCCCCC"})};' style="color:#CCCCCC;!important">请输入其他需要说明的要求...</textarea></td>
			</tr>
			
			<tr>
				<td align="right" valign="top" class="td_1"><div class="td_1">验证码：</div></td>
				<td>
				<div class="wtlx_ln">
				<span></span>
				<div class="lx_ipt">
				<input value="" name="verifyCode" id="verifyCode" type="text" class="ipt_70">
				</div>
				<span class="yzm"><img src="${globalUrl}jcaptcha?id=1357744009" onclick="reloadCaptcha();" id="imageCaptcha" title="点击刷新"></span>
				<div class="clear"></div>
				</div>
				</td>
			</tr>
			<tr>
				<td align="right" valign="top" class="td_1"><div class="td_1"></div></td>
				<td><div id="errordiv" style="font-size: 12px; color: red; margin-top: 10px;"></div></td>
			</tr>
			<tr>
				<td align="right" valign="top" class="td_1"><div class="td_1"></div></td>
				<td>
				
				<div id="errordiv" style="font-size: 12px; color: red; margin-top: 10px;"></div>
				<div class="tj_an">
				<a onclick='trimForm("entrustRenthouse");postmyform("entrustRenthouse", "errordiv");'>提 交</a>
				</div>
				</td>
			</tr>
		</table>
	</div>
	<%--
	<h3>联系方式</h3>
	<div class="wt_lxwm">
		<div class="wtlx_ln">
			<span>姓名：</span>
			<div class="lx_ipt">
				<input id="m2t1" type="text" value="${platMemberInfo.accName }" name="name">
			</div>
			<div class="wt_ip310">
				<span>称呼：</span>
				<div class="xiala xl_w90">
					<select id="m2s1" name="gender" onchange="setPlatSex();">
	              			<c:if test="${platMemberInfo != null }">
	              				<option value="0" <c:if test="${platMemberInfo.sex == 0 }">selected</c:if>>先生</option>
	              				<option value="1" <c:if test="${platMemberInfo.sex == 1 }">selected</c:if>>女士</option>
	              			</c:if>
	              			<c:if test="${platMemberInfo == null }">
	              				<option value="0" selected>先生</option>
	              				<option value="1" >女士</option>
	              			</c:if>
	                    </select>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="wtlx_ln">
			<span>联系电话：</span>
			<div class="lx_ipt">
				<input id="m2t2" type="text" value="${platMemberInfo.mobilephone }" name="phone">
			</div>
			<div class="clear"></div>
		</div>
		<div class="wtlx_ln">
			<span>其它联系方式：</span>
			<div class="lx_ipt">
				<input id="otherContect" type="text" value="" name="otherContect">
			</div>
			<div class="clear"></div>
		</div>
		<div class="wtlx_ln">
			<span>验证码：</span>
			<div class="lx_ipt">
				<input value="" name="verifyCode" id="verifyCode" type="text" class="ipt_70">
			</div>
			<span class="yzm"><img src="${globalUrl}jcaptcha?id=1357744009" onclick="reloadCaptcha();" id="imageCaptcha" title="点击刷新"></span>
			<div class="clear"></div>
		</div>
		<div id="errordiv" style="font-size: 12px; color: red; margin-top: 10px;"></div>
		<div class="tj_an">
			<a onclick='trimForm("entrustRenthouse");postmyform("entrustRenthouse", "errordiv");'>提 交</a>
			</div>
		</div>
		 --%>
	</div>
</form:form>
<script>
function reloadCaptcha() {  
    var captchaURL = $('#imageCaptcha').attr('src');  
    captchaURL = captchaURL.replace(captchaURL.substring(captchaURL.indexOf("=")+1, captchaURL.length), Math.floor(Math.random()*9999999999));  
    $('#imageCaptcha').attr('src', captchaURL);  
}
</script>