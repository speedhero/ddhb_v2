<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee" %>
<%@ include file="/upload_init.jsp"%>
<%@ page language="java" import="com.huatek.framework.util.Parameter" %>
<link rel="stylesheet" type="text/css" href="${globalUrl}js/uploadify.css" />
<script type="text/javascript" src="${globalUrl}js/jquery.uploadify.min.js"></script>
<script type="text/javascript">
function refreshData(idArray,dataArray){
	//由于上传logo每次只能上传一个，所以数组全部取第一个值
	$('#showImageId').val(idArray[0]);
	$('#showPicPath').val(dataArray[0]);
	$("#imgshow").html("<img width='100' height='80' src=\"${imgUrl}/"+dataArray[0]+"\"/>");
}

function chooseImage(){
	artDialog({title:'图片管理',url:'${uploadUrl}/fileServer.do?actionMethod=welcome&receiver=<huatek:receiveImage/>&${ssoURL}', width:800, height:520,lock:true});
}
function saveHeader(){
	var imageId = $('#showImageId').val();
	var picPath = $('#showPicPath').val();
	if(imageId=='' || picPath==''){
		alert("没有上传新的图片，请先上传新图片！");
		return;
	}
	postDataByFormName('platMemberInfo','workspace');
}
</script>
<form:form modelAttribute="platMemberInfo" name="platMemberInfo" action="${globalUrl}memberInfo.do?actionMethod=initModifyHeader">
	<div class="manage-right">
    	<div class="manage-right-title"></div>
        <div class="modify-area">
        	 <div class="modify-area-form" id="imgshow">
            	<label for=""></label>
            	<c:if test="${platMemberInfo.picPath ne null}">
               	<img width='100' height='80' src='${imgUrl}/${platMemberInfo.picPath}'/>
               	</c:if>
               	<c:if test="${platMemberInfo.picPath == null}">
               	<img width='100' height='80' src='${globalUrl}images/account/header.jpg'/>
               	</c:if>
            </div>
        	<div class="modify-area-form">
            	<label for=""></label>
                <input type="button" value="上传" class="buttonCls" onclick="chooseImage()"/>
				<input type="hidden" name="imageId" id="showImageId" value="" readonly="readonly"/>
				<input type="hidden" name="picPath" id="showPicPath" value="" readonly="readonly"/>
            </div>
            <div class="modify-area-btn">
            	<input type="button" value="提&nbsp;&nbsp;交" onclick="saveHeader();"/>  &nbsp;&nbsp;
            </div>
        </div>
    </div>
	<div class="fix"></div>
</form:form>
