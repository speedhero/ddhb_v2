/****
 * This is a  frame of java script for AJAX UI. It depend on the AJAX Core API.
 * @author:Winner pan
 * @date:2011-3-12
 *
 */

if(!String.prototype.replaceAll){
String.prototype.replaceAll = function(s1,s2) {
	return this.replace(new RegExp(s1,"gm"),s2);
};
}

if(!String.prototype.trim){
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
}


/**
 * Show error message. Example data:{key:"value"} Please modify it according to
 * yourself UI need.
 * 
 * @param errorInfo
 *            is a JSON object containing key and value properties.
 * @return null
 */
function showErrorMessage(errorInfo) {
	var str = '';
	var count = 0;
	$.each(errorInfo, function(key, value) {
		if (count > 0) {
			str += '； ';
		}
		count++;
		str += value;
	});
	$('#error_box').html('<p><strong>失败：</strong>' + str + '</p>');
	$('#error_box').show();
}
/**
 * Redirect show error message.
 * 
 * @return
 */
function showErrorDirect(errorMessage) {

	var otherErrorBox = $('#error_box_other');

	if (typeof (otherErrorBox) != undefined && otherErrorBox != null
			&& typeof (otherErrorBox.parent()) != undefined) {
		var showValue = otherErrorBox.parent().css('display');
		if (showValue === 'block') 
		{
			$('#error_box_other').html('');
			$('#error_box_other').show();
			$('#error_box_other').html('<p><strong>失败：</strong>' + errorMessage + '</p>');
			return;
		}
	}
	$('#error_box').html('');
	$('#error_box').show();
	$('#error_box').html('<p><strong>失败：</strong>' + errorMessage + '</p>');
}
function showSuccessDirect(successMessage) {
	$('#success_box').html('');
	$('#success_box').show();
	if (successMessage == '') {
		successMessage = "修改完成。";
	}
	$('#success_box').html('<p><strong>成功：</strong>' + successMessage + '</p>');
}
/**
 * Hidden the error message box.
 * 
 * @return null
 */
function hidenErrorMessage() {
	$('#error_box').hide();
}
function hidenSuccessMessage() {
	$('#success_box').hide();
}
/**
 * @See Show error message.
 */
function showInfoMessage(inforMessage) {
	$('#error_box').hide();
	$('#info_box').html('');
	$('#info_box').show();
	var count = 0;
	$.each(errorInfo, function(key, value) {
		if (count > 0) {
			$('#info_box').append('； ');
		}
		count++;
		$('#info_box').append(value);
	});
}

/**
 * @param actionUrl
 *            action URL
 * @param dataString
 *            post data string. example: name=value&key=value
 * @param resultArea
 *            The area for showing success page.
 * @return
 */

function postDataByURL(actionUrl, dataString, resultArea, successMessage,
		callBack, beforeRender) {
	try {
		showProcess();
		hidenSuccessMessage();
		hidenErrorMessage();
		$.ajax({
			type : "POST",
			url : actionUrl,
			data : dataString,
			dataType : "html",
			success : function(data, statuText, responseObj) {
				hiddenProcess();
				if (data.indexOf('loginForm') > 0) {
					window.location.reload();
					return;
				}
				if (callBack && beforeRender) {
					callBack(data, 1);
				}
				$('#' + resultArea).html(data);
				if (successMessage) {
					showSuccessDirect(successMessage);
				}
				if (callBack && !beforeRender) {
					callBack(data, 1);
				}
				// auto_heightleft();
			},
			error : function(msg) {
				hiddenProcess();
				if (msg.status == 3000 || msg.status == 500) {
					showErrorDirect(msg.responseText);
				} else if (msg.status == 0) {
					showErrorDirect(cannot_connect_server);
				} else {
					$('#' + resultArea).html(msg.responseText);
				}
				if (callBack) {
					callBack(msg, -1);
				}
			}
		});
	} catch (e) {
		hiddenProcess();
		alert('出错了！');
	}
}

/**
 * 使用Ajax从服务器取数（HTML格式）并设置到指定对象中
 * @param actionUrl			取数地址
 * @param dataString		取数参数
 * @param resultArea		要显示内容的目标对象
 * @param successMessage	成功提示信息
 * @param callBack			回调函数
 * @param beforeRender		是否在生成显示内容前调用回调函数
 */
function postDataByURL2(actionUrl, dataString, resultArea, successMessage, callBack, beforeRender) {
	try {
		hidenSuccessMessage();
		hidenErrorMessage();
		$.ajax({
			type : "POST",
			url : actionUrl,
			data : dataString,
			dataType : "html",
			success : function(data, statuText, responseObj) {
				hiddenProcess();
				if (data.indexOf('loginForm') > 0) {
					window.location.reload();
					return;
				}
				if (callBack && beforeRender) {
					callBack(data, 1);
				}
				$('#' + resultArea).html(data);
				$("#dynamichousecount").html(($('#houseTotleRows').val()));
				$("#houseCount").html($("#houseCountHidden").val());
				$("#houseCountEnd").html($("#houseCountEndHidden").val());
				if (successMessage) {
					showSuccessDirect(successMessage);
				}
				if (callBack && !beforeRender) {
					callBack(data, 1);
				}
				// auto_heightleft();
			},
			error : function(msg) {
				hiddenProcess();
				if (msg.status == 3000 || msg.status == 500) {
					showErrorDirect(msg.responseText);
				} else if (msg.status == 0) {
					showErrorDirect(cannot_connect_server);
				} else {
					$('#' + resultArea).html(msg.responseText);
				}
				if (callBack) {
					callBack(msg, -1);
				}
			}
		});
	} catch (e) {
		hiddenProcess();
		alert('出错了！');
	}
}

/**
 * Post data by AJAX
 * 
 * @param formName
 *            the form name
 * @param resultArea
 *            The area for showing success page.
 * @return null
 */

function postDataByFormName(formName, resultArea, doSuccess, doError) {
	var dataString = $("#" + formName).serialize();
	actionUrl = $("#" + formName)[0].action;
	postDataByURL(actionUrl, dataString, resultArea, doSuccess, doError);
}
/**
 * Post data for delete
 * 
 * @param formName
 *            the form name
 * @param actionUrl
 *            Action URL
 * @param resultArea
 *            The area for showing success page.
 * @return
 */
function postDataForDelete(formName, actionUrl, resultArea) {
	var dataString = $("#" + formName).serialize();
	postDataByURL(actionUrl, dataString, resultArea);
}
/**
 * Get data by URL.
 * 
 * @param actionURL
 *            Action URL
 * @param dataString
 *            Post data string
 * @param resultArea
 *            The area for showing success page.
 * @return
 */
function getData(actionUrl, dataString, resultArea, successMessage, callBack) {
	try {
		showProcess();
		hidenErrorMessage();
		hidenSuccessMessage();
		$.ajax({
			type : "GET",
			url : actionUrl,
			data : dataString,
			dataType : "html",
			success : function(data, statuText, responseObj) {
				hiddenProcess();
				if (this.url.indexOf('actionMethod=logout') > 0) {
					window.location.reload();
					return;
				}
				if (data.indexOf('loginForm') > 0) {
					window.location.reload();
					return;
				}
				$('#' + resultArea).html(data);
				// auto_heightleft();
				if (successMessage) {
					showSuccessDirect(successMessage);
				}
				if (callBack) {
					callBack(data, 1);
				}

			},
			error : function(msg) {
				hiddenProcess();
				if (msg.status == 3000 || msg.status == 500) {
					if ($('#' + resultArea).html() == '正在加载......') {
						$('#' + resultArea).html('页面加载失败');
					}
					showErrorDirect(msg.responseText);
				} else if (msg.status == 0) {
					showErrorDirect(cannot_connect_server);
					if ($('#' + resultArea).html() == '正在加载......') {
						$('#' + resultArea).html('页面加载失败');
					}
				} else {
					$('#' + resultArea).html(msg.responseText);
				}
				if (callBack) {
					callBack(msg, -1);
				}
			}
		});
	} catch (e) {
		hiddenProcess();
		alert('出错了！');
	}
}


function getMyData(actionUrl, dataString, resultArea, successMessage, callBack) {
	try {
		showProcess();
		hidenErrorMessage();
		hidenSuccessMessage();
		$.ajax({
			type : "POST",
			url : actionUrl,
			data : dataString,
			dataType : "html",
			success : function(data, statuText, responseObj) {
				hiddenProcess();
				if (this.url.indexOf('actionMethod=logout') > 0) {
					window.location.reload();
					return;
				}
				if (data.indexOf('loginForm') > 0) {
					window.location.reload();
					return;
				}
				$('#' + resultArea).html(data);
				// auto_heightleft();
				if (successMessage) {
					showSuccessDirect(successMessage);
				}
				if (callBack) {
					callBack(data, 1);
				}

			},
			error : function(msg) {
				hiddenProcess();
				if (msg.status == 3000 || msg.status == 500) {
					if ($('#' + resultArea).html() == '正在加载......') {
						$('#' + resultArea).html('页面加载失败');
					}
					showErrorDirect(msg.responseText);
				} else if (msg.status == 0) {
					showErrorDirect(cannot_connect_server);
					if ($('#' + resultArea).html() == '正在加载......') {
						$('#' + resultArea).html('页面加载失败');
					}
				} else {
					$('#' + resultArea).html(msg.responseText);
				}
				if (callBack) {
					callBack(msg, -1);
				}
			}
		});
	} catch (e) {
		hiddenProcess();
		alert('出错了！');
	}
}
/**
 * Switch Data by URL.
 * 
 * @param actionURL
 *            Action URL
 * @param dataString
 *            Post data string
 * @param resultArea
 *            The area for showing success page.
 * @return
 */
var isCanSubmit = true;
/*******************************************************************************
 * 使用于一些页面采用了onBlur事件的操作.
 * 
 * @return 返回是否可以提交.
 */
function confirmSubmit() {
	if (!confirm('确定要提交吗？')) {
		return false;
	}
	if (!isCanSubmit) {
		alert("系统忙，请稍后提交!");
		return false;
	}
	return true;
}
function switchData(actionUrl, dataString, callBack) {
	isCanSubmit = false;
	try {
		$.ajax({
			type : "POST",
			url : actionUrl,
			data : dataString,
			dataType : "html",
			success : function(data, statuText, responseObj) {
				isCanSubmit = true;
				if (callBack) {
					callBack(data, 1);
				}
			},
			error : function(msg) {
				isCanSubmit = true;
				if (msg.status == 3000 || msg.status == 500) {
					showErrorDirect(msg.responseText);
				} else if (msg.status == 0) {
					showErrorDirect(cannot_connect_server);
				}
				if (callBack) {
					callBack(msg, -1);
				}

			}
		});
	} catch (e) {
		isCanSubmit = true;
		alert('出错了！');
	}
}
/**
 * Switch Data by URL.
 * 
 * @param actionURL
 *            Action URL
 * @param dataString
 *            Post data string
 * @param resultArea
 *            The area for showing success page.
 * @return
 */
function switchDataLock(actionUrl, dataString, isLock, callBack) {
	try {
		if (isLock) {
			showProcess();
		}
		$.ajax({
			type : "POST",
			url : actionUrl,
			data : dataString,
			dataType : "html",
			success : function(data, statuText, responseObj) {
				if (isLock) {
					hiddenProcess();
				}
				if (callBack) {
					callBack(data, 1);
				}
			},
			error : function(msg) {
				if (isLock) {
					hiddenProcess();
				}
				if (msg.status == 3000 || msg.status == 500) {
					showErrorDirect(msg.responseText);
				} else if (msg.status == 0) {
					showErrorDirect(cannot_connect_server);
				}
				if (callBack) {
					callBack(msg, -1);
				}
			}
		});
	} catch (e) {
		alert('出错了！');
	}
}
function showProcess() {
	/*showDiv("loading");*/

}
function hiddenProcess() {
	hideDiv("loading");
}
/**
 * Used for previous page or next page query.
 * 
 * @param formName
 *            Form name
 * @return
 */
function changePage(formName, workspace) {
	var showAreal = workspace;
	if (!showAreal) {
		showAreal = 'workspace';
	}
	postDataByFormName(formName, showAreal);
}
/*******************************************************************************
 * @param formName
 *            the form name
 * @param methodName
 *            Method name
 * @return urlString
 */
function getActionUrl(formName, methodName) {
	var urlString = document.forms[formName].action;
	if (urlString.indexOf("actionMethod=") > 0) {
		postion = urlString.indexOf("&", urlString.indexOf("actionMethod="));
		if (postion < 0) {
			postion = urlString.length - 1;
		}
		urlString = urlString.substring(0, urlString.indexOf("actionMethod="))
				+ urlString.substring(postion);
	}
	urlString = urlString + (urlString.indexOf("?") > 0 ? "&" : "?")
			+ "actionMethod=" + methodName;
	return urlString;
}

var mouseleft = 0;
var mousetop = 0;
document.onmousemove = mouseMove;
function mouseMove(e) {
	if (!document.all) {
		mouseleft = e.pageX;
		mousetop = e.pageY;
	} else if (event && document.body && document.body.scrollLeft) {
		mouseleft = document.body.scrollLeft + event.clientX;
		mousetop = document.body.scrollTop + event.clientY;
	}

}
/*function showDiv(div_id) {
	if (!$("#" + div_id)[0]) {
		$(
				"<div id='" + div_id + "' style='display: none;'><img src='"
						+ globalUrl
						+ "images/icons/loading.gif' alt='正在加载.....' /></div>")
				.appendTo("body");
	}
	var div_obj = $("#" + div_id);
	var windowWidth = document.body.clientWidth;
	var windowHeight = document.body.clientHeight;
	var popupHeight = div_obj.height();
	var popupWidth = div_obj.width();
	if (!$("#mask")[0]) {
		$("<div id='mask' style='display: none; left: 0; top: 0'></div>")
				.appendTo("body");
	}
	// 添加并显示遮罩层
	$("#mask").css({
		"z-index" : "-99999",
		"opacity" : "0.6",
		"display" : "block",
		"position" : "absolute",
		"background-color" : "#fff"
	}).width(windowWidth).height(windowHeight);
	// .click(function() {hideDiv(div_id); })
	// .fadeIn(100);
	div_obj.css({
		"z-index" : "100000",
		"position" : "absolute",
		"left" : mouseleft,
		"top" : mousetop,
		"display" : "block"
	});
	// .animate({left: mouseleft, top: mousetop, opacity: "show" }, "fast");
}*/

function hideDiv(div_id) {
	$("#mask").css({
		"display" : "none"
	});
	$("#" + div_id).css({
		"display" : "none"
	});// .animate({left: 0, top: 0, opacity: "hide" }, "slow");
}

function trimForm(formName) {
	$('#' + formName).find("input").each(function() {
		$(this).attr("value", $(this).val().trim());
	});
	$('#' + formName).find("textarea").each(function() {
		$(this).attr("value", $(this).val().trim());
	});
}

function getInputValue(id) {
	var inputvalue = $('#' + id).attr("value").trim();
	$('#' + id).attr("value", inputvalue);
	return inputvalue;
}

function cleanText(queryConditions) {
	for ( var i = 0; i < queryConditions.length; i++) {
		$('#' + queryConditions[i]).val('');
	}
}
function jqchk() { // jquery获取复选框值
	var chk_value;
	$('input[name="id"]:checked').each(function() {
		chk_value = $(this).val();
		return;
	});
	if (chk_value == null) {
		alert('没有选择任何数据.');
		return false;
	}
	return true;
}

function deleteData(actionUrl, formId) {
	if (jqchk()) {
		if (confirm('确定要删除吗？')) {
			postDataByURL(actionUrl, $('#' + formId).serialize(), 'workspace');
		}
	}
}

function assignData(actionUrl) {
	if (jqchk()) {
		var checkValue;
		var isfalse;
		$('input[name="id"]:checked').each(function() {
			if (checkValue != null) {
				checkValue = null;
				alert('每次只能选择一条数据进行操作.');
				isfalse = true;
				return;
			}
			checkValue = $(this).val();
		});
		if (checkValue != null) {
			getData(actionUrl, 'id=' + checkValue, 'workspace');
		} else if (!isfalse) {
			alert('请选择一条数据进行操作.');
		}
	}
}

function checkAll(checkParent) {
	$('input[name="id"]').each(function() {
		$(this).attr('checked', checkParent.checked);
	});
}
