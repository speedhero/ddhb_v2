<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${globalUrl}js/jquery.uploadify.min.js"></script>
<link rel="stylesheet" type="text/css" href="${globalUrl}js/uploadify.css"/>
<link type="text/css" rel="stylesheet" href="${globalUrl}css/corner_short.css">
<script type="text/javascript" src="${globalUrl}js/select/jquery.selectReplace.js"></script>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/select/jquery.select.css">
<script type="text/javascript">
var _hintText = "自高中起的教育经历\r\n"+
"例：\r\n"+
"2009-09至2012-07: XXXXX高级中学\r\n"+
"2012-09至2016-07: XXXXX大学XXX专业\r\n"+
"\r\n"+
"实习经历\r\n"+
"例：\r\n"+
"2010年9月在XXX超市当收银员\r\n"+
"2014年8月在XXX公司任XXX职位";
function changeDisplay(){
<c:choose>
  <c:when test="${position.id==68 || position.id==69 || position.id==70 || position.id==71 || position.id==72 || position.id==73 || position.id==74}">
  <%--校招特别处理 --%>
	$("#positionDetail").css("display","block");
	$("#toEmploy").css("display","block");
	$("#file").css("display","block");
	$("#button").css("display","block");
	$(".colorBlue").css("border-bottom", "1px solid #CCCCCC");
	$("#employ").css("display","none");
	$("#displayDetail").css("display","none");
	</c:when>
	<c:otherwise>
	$("#positionDetail").css("display","none");
	$("#toEmploy").css("display","block");
	$("#file").css("display","block");
	$("#button").css("display","block");
	$(".colorBlue").css("border-bottom", "1px solid #CCCCCC");
	$("#employ").css("display","none");
	</c:otherwise>
</c:choose>
}
function changes(){
	$("#positionDetail").css("display","block");
	$("#employ").css("display","none");
	$("#displayDetail").css("display","none");
}
function postmyform(formName, resultArea) {
	var dataString = $("#" + formName).serialize();
	actionUrl = $("#" + formName)[0].action;
	<%--清除掉教育经历框中的模板内容--%>
	if(_hintText){
		if($("#_gzjl_content").text().startsWith("自高中起的教育经历")){
			$("#_gzjl_content").text("");
		}
	}

	postBymyURL(actionUrl, dataString, "POST", "html",
			function(data){
				//$("#"+ resultArea).html(data);
				if(data.indexOf('保存成功') >= 0) {
					alert("信息已提交！");
					window.location.href = "${globalUrl}company.show?actionMethod=showinvite";
				}else if(data.indexOf('保存失败') >= 0){
					$("#"+ resultArea).html("信息重复提交！");
					//alert("信息重复提交！");
				}else if(data.indexOf('成功') >= 0){
					var dataContent = "<html>"+ data +"</html>";
					art.dialog({
						id:"jianli",
				 		title: "简历状态",
				 		content: dataContent,
				 		lock: true,
				 		drag: false,
				 	    resize: false,
				 	    max: false,
				 	    min: false,
				 	   	zIndex: 99999
					});
					//refresh();
					setTimeout(function () { 
						window.location.href = "${globalUrl}company.show?actionMethod=showinvite";
    				}, 5000);
				}else{
					$("#"+ resultArea).html(data);
				}
			}
	);
}

//上传控件
$(document).ready(function() {
	if ('${isDo}' == 'true'){
		changeDisplay();
	}
	$("form").replaceAllSelect();
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
	
	$('#titleindex').append('&nbsp;>&nbsp;职位详情');
   
	$("#file_upload").uploadify({
        //开启调试
        'debug' : false,
        //是否自动上传
        'auto':true,
        //按钮文字
        'buttonText':'点击选择文件',
        //超时时间
        'successTimeout':99999,
        //flash
        'swf': "${globalUrl}js/uploadify.swf",
        //服务器端脚本使用的文件对象的名称 $_FILES个['upload']
        'fileObjName':'fileName',
        //上传处理程序
        'uploader':'${globalUrl}company.show?actionMethod=dealResume',
        //允许上传的文件后缀
        'fileTypeExts':'*.doc;*.docx;*.wps;*.pdf',
        //上传文件的大小限制
        'fileSizeLimit':'5MB',
        //返回一个错误，选择文件的时候触发
        'onSelectError':function(file, errorCode, errorMsg){
            switch(errorCode) {
                case -110:
                	$("#file_state").css({color:"red"});
                	$("#file_state").html("文件 ["+file.name+"] 大小超出系统限制的"+$('#file_upload').uploadify('settings','fileSizeLimit')+"大小！");
                	//alert("文件 ["+file.name+"] 大小超出系统限制的"+$('#file_upload').uploadify('settings','fileSizeLimit')+"大小！");
                    break;
                case -120:
    				$("#file_state").css({color:"red"});
    				$("#file_state").html("文件 ["+file.name+"] 大小异常！");
                    //alert("文件 ["+file.name+"] 大小异常！");
                    break;
                case -130:
                	$("#file_state").css({color:"red"});
    				$("#file_state").html("文件 ["+file.name+"] 类型不正确！支持类型:doc、docx、wps、pdf");
                   // alert("文件 ["+file.name+"] 类型不正确！");
                    break;
            }
        },
        //检测FLASH失败调用
        'onFallback':function(){
        	$("#file_state").css({color:"red"});
			$("#file_state").html("您未安装flash控件，无法上传图片！请安装flash控件后再试。");
            //alert("您未安装flash控件，无法上传图片！请安装flash控件后再试。");
        },
        //上传到服务器，服务器返回相应信息到data里
        'onUploadSuccess':function(file, data, response){
        	var datejson = eval("(" + data + ")");
        	if (datejson.result == "success") {
        		$("#file_state").css({color:"green"});
        		$("#file_state").html("上传成功：["+ file.name +"]");
				//alert("上传成功！");
			}else {
				$("#file_state").css({color:"red"});
				$("#file_state").html("文件:["+ file.name +"]上传失败,请稍候再试");
				//alert("文件上传失败，请稍后再试...");
			}
        },
        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
        	$("#file_state").css({color:"red"});
			$("#file_state").html("文件上传失败，请稍后再试...");
            //alert("文件上传失败，请稍后再试...");
        }, 
    });
});
</script>

<c:if test="${position.id==68 || position.id==69 || position.id==70 || position.id==71 || position.id==72 || position.id==73 || position.id==74}">
<%--校招特别处理 --%>
<script type="text/javascript">

$(document).ready(function() {
	$("#_gzjl").html("教育及实习经历：");
	if($("#_gzjl_content").text()==""){
		$("#_gzjl_content").text(_hintText);
	}
	$("#_gzjl_content").css("color", "#CCCCCC");
	$("#_gzjl_content").focus(function(){
		var _hTxt = $(this).text();
		if(_hTxt.startsWith("自高中起的教育经历")){
			$("#_gzjl_content").text("");
			$("#_gzjl_content").css("color","#000000");
		}
	});
});

if(!String.prototype.trim) {
  String.prototype.trim = function () {
    return this.replace(/^\s+|\s+$/g,'');
  };
}

/*! http://mths.be/startswith v0.2.0 by @mathias */
if (!String.prototype.startsWith) {
  (function() {
    'use strict'; // needed to support `apply`/`call` with `undefined`/`null`
    var defineProperty = (function() {
      // IE 8 only supports `Object.defineProperty` on DOM elements
      try {
        var object = {};
        var $defineProperty = Object.defineProperty;
        var result = $defineProperty(object, object, object) && $defineProperty;
      } catch(error) {}
      return result;
    }());
    var toString = {}.toString;
    var startsWith = function(search) {
      if (this == null) {
        throw TypeError();
      }
      var string = String(this);
      if (search && toString.call(search) == '[object RegExp]') {
        throw TypeError();
      }
      var stringLength = string.length;
      var searchString = String(search);
      var searchLength = searchString.length;
      var position = arguments.length > 1 ? arguments[1] : undefined;
      // `ToInteger`
      var pos = position ? Number(position) : 0;
      if (pos != pos) { // better `isNaN`
        pos = 0;
      }
      var start = Math.min(Math.max(pos, 0), stringLength);
      // Avoid the `indexOf` call if no match is possible
      if (searchLength + start > stringLength) {
        return false;
      }
      var index = -1;
      while (++index < searchLength) {
        if (string.charCodeAt(start + index) != searchString.charCodeAt(index)) {
          return false;
        }
      }
      return true;
    };
    if (defineProperty) {
      defineProperty(String.prototype, 'startsWith', {
        'value': startsWith,
        'configurable': true,
        'writable': true
      });
    } else {
      String.prototype.startsWith = startsWith;
    }
  }());
}
//-->
</script>
</c:if>

<div class="yc_zzy yc_zzyb">
	<h3><c:out value="${position.positionName }"></c:out></h3>
    <p>招聘人数：${position.needed } 人　<br>工作地点：${position.workPlace }　<br>职位类型：${position.positionType.typeName }　<br><fmt:formatDate value="${position.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
</div>
<div id="positionDetail">
<div class="yc_zzy">
	<h3>工作职责：</h3>
    <p>${position.description }</p>
</div>
<div class="yc_zzy">
	<h3>工作要求：</h3>
    <p>${position.requirement }</p>
</div>
<div class="yc_zzy">
	<h3>其他：</h3>
    <p>${position.otherinformation }</p>
</div>
</div>
<div id="employ" class="yczz_an"><a href="javascript:void(0);" onclick="changeDisplay()">我要应聘</a></div>
<div class="mob_mor"><a href="javascript:void(0)" onclick="$('#positionDetail').css('display', 'block')">更多</a><a href="javascript:void(0)" onclick="$('#positionDetail').css('display', 'none')">收起</a></div>
<form:form modelAttribute="recruitCandidate" name="recruitCandidate" action="${globalUrl}company.show?actionMethod=saveResume" id="recruitCandidate" enctype="multipart/form-data">
<div id="toEmploy" style="display:none">
<div id="displayDetail" class="yczz_an"><a href="javascript:void(0);" onclick="changes()">展开职位详情</a></div>
<div class="txypin">
   	<h2>填写简历</h2>
       <div class="yp_nr" style="padding:30px 10px;">
       	<ul>
           	<li>
               	<div class="wd">*姓名：</div>
                   <div class="input"><input name="name" type="text" class="inptxt"></div>
               </li>
           	<li class="duan">
               	<div class="wd">性别：</div>
                   <div class="input"><span><input name="gender" checked="checked" type="radio" value="0">男</span><input name="gender" type="radio" value="1">女</div>
               </li>
           	<li>
               	<div class="wd">出生日期：</div>
                   <div class="input">
                   	<div class="xiala xl_w80">
                   		<select id="year" name="year">
                   			<c:forEach begin="${1}" end="${30}" varStatus="index">
                   				<option value="${currentYear - index.index - 18}">${currentYear - index.index - 18}</option>
                   			</c:forEach>
                   		</select>
                       </div>
                   	<div class="xiala xl_w64">
                   		<select id="month" name="month">
                   			<c:forEach begin="1" end="12" varStatus="index">
                   				<option value="${index.index }">${index.index }</option>
                   			</c:forEach>
                   		</select>
                       </div>
                   	<div class="xiala xl_w64">
                   		<select id="day" name="day">
                   			<c:forEach begin="1" end="31" varStatus="index">
                   				<option value="${index.index }">${index.index }</option>
                   			</c:forEach>
                   		</select>
                       </div>
                   </div>
               </li>
           	<li class="duan">
               	<div class="wd">*学历：</div>
                   <div class="input">
                   	<div class="xiala xl_w145">
                       	<select name="degree" style="width:132px;height:28px" id="degree">
						 	<option value="" selected="">--请选择--</option>
							<option value="初中">初中</option>
							<option value="高中">高中</option>
							<option value="中技">中技</option>
							<option value="中专">中专</option>
							<option value="大专">大专</option>
							<option value="本科">本科</option>
							<option value="MBA">MBA</option>
							<option value="硕士">硕士</option>
							<option value="博士">博士</option>
							<option value="其他">其他</option>
						 </select>
                       </div>
                   </div>
               </li>
           	<li>
               	<div id="_gznx" class="wd">工作年限：</div>
                   <div class="input">
                   	<div class="xiala xl_w145 z9">
                       	<select name="yearworking" style="width:142px;height:22px" id="yearworking">
						 	<option value="" selected="">--请选择--</option>
							<option value="在校学生">在校学生</option>
							<option value="应届毕业生">应届毕业生</option>
							<option value="一年以上">一年以上</option>
							<option value="二年以上">二年以上</option>
							<option value="三年以上">三年以上</option>
							<option value="五年以上">五年以上</option>
							<option value="八年以上">八年以上</option>
							<option value="十年以上">十年以上</option>
						 </select>
                       </div>
                   </div>
               </li>
           </ul>
           <div class="clear"></div>
       </div>
       <div class="yp_nr yp_nr100" style="padding:30px 10px;">
       	<ul>
           	<li>
               	<div class="wd">*联系电话：</div>
                   <div class="input"><input name="telephone" type="text" class="inptxt"></div>
               </li>
           	<li>
               	<div class="wd">*联系邮箱：</div>
                   <div class="input"><input name="email" type="text" class="inptxt"></div>
               </li>
           </ul>
           <div class="clear"></div>
       </div>
       <div class="yp_nr yp_nr100" style="padding:30px 10px;">
       	<ul>
           	<li>
               	<div id="_gzjl" class="wd">*工作经历：</div>
                   <div class="input"><textarea id="_gzjl_content" name="workHistory" class="textarea"></textarea></div>
               </li>
               <li class="mt15"></li>
           	<li>
               	<div class="wd">附件：</div>
                   <div class="input"><input id="file_upload" name="file_upload" type="file" class="file"></div>
                   <div id="file_state" style="font-size:12px;" ></div>
               </li>
           </ul>
           <div class="clear"></div>
       </div>
   </div>
   <div id="errordiv" style="font-size: 12px; color: red;"></div>
   <div class="yczz_an"><a href="javascript:void(0);" onclick='trimForm("recruitCandidate");postmyform("recruitCandidate", "errordiv");'>提交简历</a></div>
  <!--  <div class="mob_mor"><a href="#">更多</a><a href="#">收起</a></div> -->
	<input type="hidden" name="positionId" value="${position.id }">
</div>
</form:form>
