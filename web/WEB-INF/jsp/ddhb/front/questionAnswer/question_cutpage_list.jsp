<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
function searchQuestion(inputId){
	var subtype = $(".one").attr("value");
	var searchString = $('#'+inputId).val();
	if(searchString == "请输入关键字检索..."){
		alert("请输入关键字检索");
		return;
	}
	var json = {"searchString" : searchString, "subtype": subtype };
	getMyData("${globalUrl}broker.show?actionMethod=getSearchQuestion", json, "eachFloat");
}
</script>

<c:if test="${pageBean.totalRows eq 0}">
	<div style="width: 160px; height: 50px; text-align: center; border: 0px; vertical-align: middle; padding-top: 30px; margin: 130px auto;">查询不到任何相关数据</div>
</c:if>
<c:if test="${pageBean.totalRows gt 0}">
<div class="fc_ls_nr">
	<div class="fc_sc"><h2>问题</h2><input type="text" id="searchString" style="float:left;" value="请输入关键字检索..." onfocus='if(value=="请输入关键字检索...") {value =""}' onblur='if(value=="") {value ="请输入关键字检索..."}'><div class="fdjClass" onclick="searchQuestion('searchString');" style="background-image: url('${globalUrl}image/fdj.gif');background: transparent;" ></div>共有信息<b>${pageBean.totalRows }</b>条</div>
</div>
<div class="fc_newsls">
	<!--<h2><span><input id="searchStringPhone" value="请输入关键字检索..." onfocus='if(value=="请输入关键字检索...") {value =""}' onblur='if(value=="") {value ="请输入关键字检索..."}' type="text"><div class="fdjClassIp" onclick="searchQuestion('searchStringPhone');" style="background-image: url('${globalUrl}image/fdj_ip.gif');background: transparent;" ></div></span>问题</h2>-->
	<ul>
		<c:forEach items="${pageBean.dataList }" var="question" varStatus="questionCount">
		<li><span><fmt:formatDate value="${question.createTime }" pattern="yyyy-MM-dd"></fmt:formatDate></span><a href="javascript:void(0);" onclick='window.location.href="${globalUrl}broker.show?actionMethod=initQuestionDetail&questionId=${question.erpId }&typeId=${question.questionType.parentType.erpId }&subTypeId=${question.questionType.erpId }"'> · ${question.title }</a></li>
		</c:forEach>
	</ul>
	<div class="page" style="margin-top: 20px;">
		<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false"
		formName="questionform" offerPageSize="20,50,100" isExistForm="true"
		queryFunction="changePages('${subTypeId }')" />
	</div>
</div>
</c:if>

<script type="text/javascript">
	function changePages(subTypeId) {
		var currvalue = document.getElementsByName("currentPage")[0].value;
		/* setJSONValue("currentPage",currvalue); */
		var searchString = "${searchString}";
		var subtype = "${subtype}";
		if("" != searchString){
			json = {"searchString" : searchString, "subtype": subtype, "currentPage": currvalue};
			getMyData("${globalUrl}broker.show?actionMethod=getSearchQuestion", json, "eachFloat");
		}else{
			postDataByURL2('${globalUrl}broker.show?actionMethod=getQuestions&subTypeId='+subTypeId,'currentPage='+currvalue,"eachFloat");
		}
	}
</script>