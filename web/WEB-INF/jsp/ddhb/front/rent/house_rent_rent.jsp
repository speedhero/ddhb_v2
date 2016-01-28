<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript" src="${globalUrl }/js/select/jquery.selectReplace.js"></script>
<link rel="stylesheet" type="text/css" href="${globalUrl}/css/more.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}/css/select/jquery.select.css">
<script type="text/javascript" src="${globalUrl}/js/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript" src="${globalUrl}/js/commonGround/house_buyAndRent.js"></script>
<link href="${globalUrl}/css/jquery-ui-1.10.4.custom.css" rel="stylesheet" />
<style>
.tcc_zp {
    margin: 0 !important;
    position: inherit !important;
}
.otherxq textarea{width:380px;height:40px; background:#ecedf2;padding:5px;line-height:18px;font-size:12px;color:#333;border:0;}
.txt_w490{width:250px;}
.wt_con{padding:20px 26px; clear:both; overflow:hidden;}
#ss1 span{background-color: inherit;float:inherit;text-align: left;width:inherit;}
#ss2 span{background-color: inherit;float:inherit;text-align: left;width:inherit;}
#ss3 span{background-color: inherit;float:inherit;text-align: left;width:inherit;}
#ss4 span{background-color: inherit;float:inherit;text-align: left;width:inherit;}
#ss5 span{background-color: inherit;float:inherit;text-align: left;width:inherit;}
#ss6 span{background-color: inherit;float:inherit;text-align: left;width:inherit;}
#ss7 span{background-color: inherit;float:inherit;text-align: left;width:inherit;}
.phoneDisplay{display:none;}
.pcDisplay{display:block;}
@media screen and (max-width: 758px){
.otherxq textarea{width:300px;}
.txt_w490{width:240px;}
}
@media screen and (max-width: 470px) {
.otherxq textarea{width:200px;}
.txt_w490{width:50px;}
.phoneDisplay{display:block;}
.pcDisplay{display:none;}
}

</style>
<script type="text/javascript">
	$(document).ready(function(){
		try{
			$("#form").replaceAllSelect();	
		}catch(e){
			if(window.console) console.log(e);
		}
	});
</script>

<script type="text/javascript">
	function changeRelativeSelect(selecctedValue) {
		var cid = selecctedValue;
		$('#countryidspan').attr('value', cid);
		$.ajax({
			type : "post",
			url : "${globalUrl}/consignment.show?actionMethod=getcdb&cid="
					+ cid,
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
		setRadioSpanVal(1);
	});
	$(function() {
		var a1 = $('#textAuto').autocomplete({
			source : function(request, response) {
				$.ajax({
					url : "${globalUrl }/community.show?actionMethod=getCommunityListMyName",
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
	});
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
	
</script>
<form:form modelAttribute="entrustRenthouse" name="entrustRenthouse" action="${globalUrl}consignment.show?actionMethod=addRenthouse" id="entrustRenthouse">
	<div class="tcc_zp" >
		<div class="wt_con">
		<h3>租房需求</h3>
		<div class="wt_tb">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="right" valign="top" class="td_1"><div class="td_1 pcDisplay">位置要求：</div></td>
					<td>
						<div class="phoneDisplay" >
							位置要求：
						</div>
						<div class="wt_wzyq">
							<div class="dx_ipt">
								<input id="selectbox1" type="radio" value="1" name="locFlag" checked="checked" onclick="setRadioSpanVal(1)">
							</div>
							<span>无明确位置要求</span>
							<div class="clear"></div>
						</div>
						<div class="wt_wzyq">
							<div class="dx_ipt">
								<input id="selectbox2" type="radio" value="2" name="locFlag" onclick="setRadioSpanVal(2)">
							</div>
							<span>有明确商圈要求</span>
							<div class="xiala xl_w90">
								<select id="s1" onchange="changecdb();" relativeSelectId="true">
									<option value="--选择--" selected="">--选择--</option>
	                                <c:forEach items="${areas }" var="area">
	                                <option value="${area.erpId}">${area.countyName }</option>
	                                </c:forEach>
	                            </select>
							</div>
							<div class="sq_r">
								<span class="sq">商圈</span>
								<div class="xiala xl_w90">
									<select id="s2" name="cbdNo" onchange="selectcbd();">
		                             	<option id="s21" value="--选择--" selected="">--选择--</option>
		                            </select>
								</div>
							</div>
							<div class="clear"></div>
						</div>
						<div class="wt_wzyq">
							<div class="dx_ipt">
								<input id="selectbox3" type="radio" value="3" name="locFlag" onclick="setRadioSpanVal(3)">
							</div>
							<span>有明确小区要求</span>
							<div class="txt_ipt">
								<input id="textAuto" name="communityName" type="text" value="请输入小区名称..." onfocus='if(value=="请输入小区名称...") {value ="";$(this).css({"color":"#333"})};' onblur='if(value=="") {value="请输入小区名称...";$(this).css({"color":"#CCCCCC"})};' style="color:#CCCCCC;!important" class="txt_w490">
							</div>
							<div class="clear"></div>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right" valign="top" class="td_1"><div class="td_1 pcDisplay">居室要求：</div></td>
					<td class="hxyq">
						<div class="phoneDisplay" >
							居室要求：<br>
						</div>
						<div class="xiala xl_w64">
							<select id="s3" name="minShi" onchange="setShi()">
		                         <c:forEach items="${houseType}" var="type">
		                           	<option value="${type.htCount}">${type.htCount}</option> 
		                         </c:forEach>
		                     </select>
						</div> <span style="padding:0 15px 0 0;">最少室</span>
						<div class="xiala xl_w64">
							<select id="s4" name="maxShi" onchange="setTing()">
		                         <c:forEach items="${houseType}" var="type">
		                           	<option value="${type.htCount}">${type.htCount}</option> 
		                         </c:forEach>
		                     </select>
						</div> <span style="padding:0 15px 0 0;">最多室</span>
						<div class="wt_ip310">
							<div class="xiala xl_w64">
								<!-- <a href="#" class="a">1</a>
								<div class="xlnr">
									<a href="#">1990</a><a href="#">1990</a><a href="#">1990</a><a
										href="#">1990</a><a href="#">1990</a><a href="#">1990</a>
								</div> -->
								<select id="s5" class="m1s3" name="ting" onchange="setWei()">
			                         <option value="1" selected="">1</option>
			                         <option value="2">2</option>
			                         <option value="3">3</option>
			                         <option value="4">4</option>
			                         <option value="5">5</option>
			                         <option value="6">6</option>
			                     </select>
							</div>
							<span style="padding:0 15px 0 0;">厅</span>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right" valign="top" class="td_1"><div class="td_1 pcDisplay">租赁方式：</div></td>
					<td class="hxyq">
						<div class="phoneDisplay" >
							租赁方式：<br>
						</div>
						<div class="xiala xl_w64">
							<select id="s1" name="rentType">
								<option value="整租" selected="">整租</option>
			                    <option value="合租">合租</option>
		                    </select>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right" valign="top" class="td_1"><div class="td_1 pcDisplay">室内设施：</div></td>
					<td>
						<div class="phoneDisplay" >
							室内设施：<br>
						</div>
						<div class="xiala" style="width:290px;">
							<div>
			          			<c:forEach items="${furList }" var="fur">
				           			<input class="subBox" type="checkbox" value="${fur.erpId }"/><span>&nbsp;&nbsp;${fur.furName }</span>
				           		</c:forEach>
			           			<input id="checkAll" type="checkbox" /><span>&nbsp;&nbsp;全选</span>
			          		</div>
			          		<input type="hidden"  name="facility" id="facility"/>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right" valign="top" class="td_1"><div class="td_1 pcDisplay">预算价位：</div></td>
					<td class="jiawei">
						<div class="phoneDisplay">
							预算价位：
						</div>
						<input id="t2" type="text" value="" name="minPrice"><span>—</span><input id="t2" type="text" value="" name="maxPrice">
						<span>元/月</span>
					</td>
				</tr>
				<tr>
					<td align="right" valign="top" class="td_1"><div class="td_1 pcDisplay">其他需求：</div></td>
					<td class="otherxq">
					<div class="phoneDisplay" >
							其他需求：
					</div>
					<textarea name="other" id="ta1" onfocus='if(value=="请输入其他需要说明的要求...") {value ="";$(this).css({"color":"#333"})};' onblur='if(value==""){value="请输入其他需要说明的要求...";$(this).css({"color":"#CCCCCC"})};' style="color:#CCCCCC;!important">请输入其他需要说明的要求...</textarea></td>
				</tr>
			</table>
		</div>
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
						<!-- <a href="#" class="a">先生</a>
						<div class="xlnr">
							<a href="#">先生</a><a href="#">女士</a>
						</div> -->
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
					<input id="m2t2" type="text" value="" name="otherContect">
				</div>
				<div class="clear"></div>
			</div>
			<div class="wtlx_ln">
				<span>验证码：</span>
				<div class="lx_ipt">
					<input value="" name="verifyCode" id="verifyCode" type="text" style="width:50px;">
				</div>
				<span class="yzm"><img style="width:80px;height:30px;" src="${globalUrl}/jcaptcha?id=1357744009" onclick="reloadCaptcha();" style="height:25px;width:70px;" id="imageCaptcha" title="点击刷新"></span>
				<div class="clear"></div>
			</div>
			<div id="errordiv" style="font-size: 12px; color: red; margin-top: 10px;"></div>
			<div class="tj_an">
				<a onclick='trimForm("entrustRenthouse");postmyform("entrustRenthouse", "errordiv");'>提 交</a>
			</div>
			<div class="yyfw_a">
	        	<a onclick="art.dialog.list['consignment'].close();daikan();">预约服务</a>
		        <a style="background-color:#d83939">我要租房</a>
		        <!-- <a onclick="art.dialog.list['consignment'].close();reduceNotice();">降价通知</a> -->
		        <a onclick="art.dialog.list['consignment'].close();newsNotice();">订阅市场动态</a>
	        </div>
			<div class="clear"></div>
		</div>
	</div>
	</div>
	
</form:form>

<script>
	function reloadCaptcha() {
		var captchaURL = $('#imageCaptcha').attr('src');
		captchaURL = captchaURL.replace(captchaURL.substring(captchaURL
				.indexOf("=") + 1, captchaURL.length), Math
				.floor(Math.random() * 9999999999));
		$('#imageCaptcha').attr('src', captchaURL);
	}
</script>
