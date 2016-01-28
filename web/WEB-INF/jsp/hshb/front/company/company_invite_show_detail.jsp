<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
var type = 1;
var jsoninit = {
	"type" : type
};
function setjsonvalue(key, value) {
	jsoninit[key] = value;
}
function cutPostype(type) {
	setjsonvalue("type" , type);
	getData('${globalUrl}company/cutPostype/'+type,'','changelist');
}

$(document).ready(function(){
	//$('.everyType').first().addClass("one");
	
	$(".everyType").click(function(){
		$(this).prevAll().removeClass("one");
		$(this).nextAll().removeClass("one");
		$(this).addClass("one");
	});
	$(".everyType").click(function(){
		$(this).prevAll().removeClass("one");
		$(this).nextAll().removeClass("one");
		$(this).addClass("one");
		$(".sch").removeClass("sched");
	});
	$(".sch").click(function(){
		$(".everyType").removeClass("one");
		$(this).addClass("sched");
	});
});
</script>

<div class="yct">
<c:forEach items="${positionType }" var="postype" varStatus="status">
	<c:set var="cssStyle" value="${postype.id == 13 ? 'sch':'everyType' }" />
	<c:if test="${changeCss eq postype.id }">
	<a class="${cssStyle } one" href="javascript:void(0);" onclick="cutPostype(${postype.id });" >${postype.typename }</a>
	</c:if>
	<c:if test="${changeCss ne postype.id }">
	<a class="${cssStyle }" href="javascript:void(0);" onclick="cutPostype(${postype.id });" >${postype.typename }</a>
	</c:if>
</c:forEach>
</div>
<form name="positionForm">
<div id="changelist" class="yc_tb">
	<%@include file="/WEB-INF/jsp/hshb/front/company/company_invite_show_list.jsp" %>
</div>
</form>