//我要买房 租房 我要卖房 ， 我要租房
//house_buy.jsp,house_rent.jsp house_renting.jsp, house_sell.jsp
//house_second_buy.jsp 、house_rent_rent.jsp

//使用该方法的 已被注释 house_buy.jsp , house_rent.jsp
function setRadioSpanVal(number) {
	if (number == 1) {
		$('#s1').attr("disabled", "disabled");
		$('#s2').attr("disabled", "disabled");
		$('#s1').attr("isDisableFlag", "true");
		$('#s2').attr("isDisableFlag", "true");
		$("#select_s1").find(".valueContainer").attr("isdisableflag", "true");
		$("#select_s2").find(".valueContainer").attr("isdisableflag", "true");
		// $('#textAuto').attr("disabled", "disabled");
	} else if (number == 2) {
		$('#s1').removeAttr("disabled");
		$('#s2').removeAttr("disabled");
		$('#s1').attr("isDisableFlag", "false");
		$('#s2').attr("isDisableFlag", "false");
		$("#select_s1").find(".valueContainer").attr("isdisableflag", "false");
		$("#select_s2").find(".valueContainer").attr("isdisableflag", "false");
		// $('#textAuto').attr("disabled", "disabled");
	} else if (number == 3) {
		$('#s1').attr("disabled", "disabled");
		$('#s2').attr("disabled", "disabled");
		$('#s1').attr("isDisableFlag", "true");
		$('#s2').attr("isDisableFlag", "true");
		$("#select_s1").find(".valueContainer").attr("isdisableflag", "true");
		$("#select_s2").find(".valueContainer").attr("isdisableflag", "true");
		// $('#textAuto').removeAttr("disabled");
	}
}

//点击 提交
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
			if (data.indexOf('保存成功') >= 0) {
				$("#errordiv").empty();
				alert("信息已提交！");
			} else {
				$("#" + resultArea).html(data);
			}
				reloadCaptcha();
		}
	});
}

