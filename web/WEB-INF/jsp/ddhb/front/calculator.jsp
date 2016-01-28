<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.tcc_zp {margin: 0 !important;  position: inherit !important;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	for(var i = 1; i <= 20; i++){
		$('<option value='+i*12+'>'+i+'年('+ i*12+'期)</option>').appendTo("#yearNumber");
	}
	
	for(var i = 2; i <= 8;i++ ){
		$('<option value='+i+'>'+i+'成</option>').appendTo("#chengshu");
	}
	
	$("#dialogCaculator").replaceAllSelect();
	
});

function changeMethod(divId){
	if(divId == "areaAndPrice"){
		$("#areaAndPrice").css("display","block");
		$("#countMoney").css("display","none");
		$('#radioHidden').attr("value", 0);
	}else if(divId == "countMoney"){
		$("#areaAndPrice").css("display","none");
		$("#countMoney").css("display","block");
		$('#radioHidden').attr("value", 1);
	}
}

function calculator(){
	var flag = true;
	var type = $("#radioHidden").attr("value");
	var calType = $("#calType").val();
	var lilv = $("#lilv").val();
	var yearNumber = $("#yearNumber").val();
	var area = $("#area").val();
	var price = $("#price2").val();
	var chengshu = $("#chengshu").val();
	var money = $('#money').val()*10000;
	var reg = new RegExp("^[0-9]*$"); 
	
	if(type == 0){
		if(area=='' || price==''){
			flag = false;
			alert("请填写房屋面积、价格");
			return;
		}
		if(!reg.test(area) || !reg.test(price)){  
	        alert("请输入整数!");
	        flag = false;
	        return;
	    } 
	}else{
		if(money == ''){
			flag = false;
			alert("请填写贷款总数");
			return;
		}
		if(!reg.test(money)){  
	        alert("请输入整数!");
	        flag = false;
	        return;
	    }
	}
	if(lilv == ''){
		alert("请填写利率");
		flag = false;
		return;
	}
	var regG = new RegExp("^[0-9]{1,3}([.][0-9]+)*$"); 
	if(!regG.test(lilv)){  
        alert("请输入正确的利率!");
        return;
    } 
	if(calType == "benxi"){
		if(flag){
			$("#flowbenxi").empty();
			$.ajax({
				type: "post",
				url:"${globalUrl}calculator.show?actionMethod=calculateWithBenXi&area="+area+"&pricePerUnit="+price+"&percent="+chengshu+"&month="+yearNumber+"&rate="+lilv+"&moneyLoaded="+money,
				dataType: 'json',
				success: function(data){
					$('<div>房款总额：<span>'+data.housePrice+' 元</span></div>').appendTo("#flowbenxi");
					$('<div>贷款总额：<span>'+data.loadPrice+' 元</span></div>').appendTo("#flowbenxi");
					$('<div>还款总额：<span>'+data.priceToPay+' 元</span></div>').appendTo("#flowbenxi");
					$('<div>支付利息款：<span>'+data.interest+' 元</span></div>').appendTo("#flowbenxi");
					$('<div>月均还款：<span>'+data.payforPerMonth+' 元</span></div>').appendTo("#flowbenxi");
					$('<div>还款月数：<span>'+data.month+' 月</span></div>').appendTo("#flowbenxi");
				}
			});
		}
	}else if(calType == "benjin"){
		if(flag){
			$("#tbody").empty();
			$.ajax({
				type: "post",
				url:"${globalUrl}calculator.show?actionMethod=calculateWithBenJin&area="+area+"&pricePerUnit="+price+"&percent="+chengshu+"&month="+yearNumber+"&rate="+lilv+"&moneyLoaded="+money,
				dataType: 'json',
				success: function(data){
					for(var i = 0; i < yearNumber; i++){
						$('<tr><td>第'+ (i + 1) +'个月</td><td>'+data[i]+'元</td></tr>').appendTo("#tbody");
					}
				}
			});	
		}
	}
}
function changeResult(){
	var calType = $("#calType").val();
	if(calType == "benxi"){
		$('#flowbenjin').css("display","none");
		$('#flowbenxi').css("display","block");
	}else {
		$('#flowbenjin').css("display","block");
		$('#flowbenxi').css("display","none");
	}
}

function changeRelativeSelect(selecctedValue) {
	changeResult();
}

function resetCal(){
	$("#calType").change();
	$("#chengshu").val(2);
	$("#yearNumber").val(1);
	$("#money").val("");
	$("#areaRadio").attr("checked","checked");
	$("#flowbenxi").empty();
	$("#lilv").val("");
	$("#price2").val("<fmt:formatNumber pattern="#" value="${unitPrice}"/>");
	$("#area").val("<fmt:formatNumber pattern="#" value="${area}"/>");
	$("#calType").val("benxi");
	changeMethod("areaAndPrice");
	changeResult();
	//将成数的数字置为默认
	var defaultChengshuOption = $(".xlxx").find("a[selectid='chengshu']").first();
	$(defaultChengshuOption).parent().parent().find(".selectedValue").html($(defaultChengshuOption).html());
	//贷款期限的数字置为默认
	var defaultYearOption = $(".xlxx").find("a[selectid='yearNumber']").first();
	$(defaultYearOption).parent().parent().find(".selectedValue").html($(defaultYearOption).html());
	//贷款类别
	var defaultTypeOption = $(".xlxx").find("a[selectid='calType']").first();
	$(defaultTypeOption).parent().parent().find(".selectedValue").html($(defaultTypeOption).html());
}
</script>
<div class="tcc_zp" id="dialogCaculator">
    <div class="jsq_con" id="calculatorContent">
    	<div class="jsq_lwd">贷款类别：</div>
        <div class="jsq_r" style="width:150px;">
            <select id="calType" onchange="changeResult();" style="width:100%;" relativeselectid="true">
				<option value="benxi">等额本息</option>
				<option value="benjin">等额本金</option>
			</select>
           <div class="clear"></div>
        </div>
        <div class="clear"></div>
        <div class="line"></div>
        <div class="jsfs">
        	<div class="jsq_lwd">计算方式：</div>
            <div class="jsfs_r">
            	<div class="jsfs_line"><input id="areaRadio"  type="radio" name="methodCal" value="0" checked="checked" onclick='changeMethod("areaAndPrice");'> 根据面积、单位计算<div class="clear"></div></div>
            	<div id="areaAndPrice">
            	<div class="jsfs_line"><b>单价：</b><input type="text" id="price2" class="input" value='<fmt:formatNumber pattern="#" value="${unitPrice}"/>' /><span>元/平米</span><div class="clear"></div></div>
            	<div class="jsfs_line"><b>面积：</b><input type="text" id="area" class="input" value='<fmt:formatNumber pattern="#" value="${area}"/>' /><span>平方米</span><div class="clear"></div></div>
            	<div class="jsfs_line">
                	<b>按揭成数：</b>
                	<div class="jsq_r" style="width:150px;" ><select id="chengshu"></select>
                	</div>
                    <div class="clear"></div>
                </div>
                </div>
            	<div class="jsfs_line"><input id="countRadio" type="radio" name="methodCal" value="1" onclick='changeMethod("countMoney");'>根据贷款总额计算<input type="hidden" id="radioHidden"/><div class="clear"></div></div>
            	<div id="countMoney" style="display: none;margin-bottom:20px;">
				<div>贷款总额：<input type="text" id="money"/>万元</div>
				</div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="jsq_line">
            <div class="jsq_lwd">按揭年数：</div>
            <div class="jsq_r" style="width:150px;">
                <select id="yearNumber">
				</select>
            </div>
            <div class="clear"></div>
        </div>
        <div class="jsq_line">
            <div class="jsq_lwd">利率：</div>
            <div class="jsq_r"><input type="text" class="input" id="lilv" value="${houseSecRate }">  年利率</div>
            <div class="clear"></div>
        </div>
        <div class="jsq_an"><a href="javascript:void(0);" onclick="calculator();" class="tj">提交</a><a href="javascript:void(0);" onclick="resetCal();" class="cz">重新计算</a></div>
        <div class="clear"></div>
    </div>
    <div id="flowbenxi">
	
	</div>
    <div id="flowbenjin" style="display: none;overflow: scroll;height:100px;" >
	<table>
		<thead>
			<tr>
				<td>月份</td>
				<td>还款数额</td>
			</tr>
		</thead>
		<tbody id="tbody">
		</tbody>
	</table>
</div>
</div>

