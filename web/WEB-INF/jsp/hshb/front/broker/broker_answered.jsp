<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/jjr.css">	
<title>经纪人 - ${title } </title>
<style>
.brokerAddQuestion {
  background: none repeat scroll 0 0 #F79200;
  color: #fff;
  display: inline-block;
  font-size: 15px;
  height: 22px;
  line-height: 22px;
  text-align: center;
  width: 71px;
}
.brokerAddQuestion:hover{background:#f60;color:#fff;}
</style>
<script>
var jsoninit = {"brokerid" : '${brokerid }',};
function setJSONValue2(key, newValue){
	jsoninit[key] = newValue;
}
</script>
<!--列表开始-->
   <div class="jjr_wd" id="answeredDiv">
   	<div class="wd_tit">回答问题 <span>${pageBean.totalRows}</span> 条  (采纳答案 <span>${isaccept }</span> 条)
   		<a class="brokerAddQuestion" href="javascript:void(0);" onclick="window.location.href='${globalUrl}broker.show?actionMethod=addQuestion&questionId=0'">我要提问</a>
   	</div>
       <div class="wda_list">
       	<ul>
       		<c:forEach items="${pageBean.dataList}" var="answer">
           	<li>
            	<div class="wen"><c:out value="${answer.questionStategy.title }"></c:out></div>
                <div class="wd_nr">
                	<span>内容：</span>
                    <p><c:out value="${answer.questionStategy.content }"></c:out></p>
                </div>
                <div class="wd_fl">分类：
                	<c:out value="${answer.questionStategy.questionType.parentType.typeName }"></c:out>- <c:out value="${answer.questionStategy.questionType.subTypeName }"></c:out>  |  浏览：${answer.questionStategy.browsed }次  |  发布时间：<fmt:formatDate value="${answer.questionStategy.createTime }" type="date" /> | 发布人：<c:out value="${answer.questionStategy.user.accName}"></c:out>
                </div>
                <div class="wd_da"><c:out value="${answer.answeredContent }"></c:out></div>
               </li>
               </c:forEach>
           </ul>
       </div>
   </div>
<!--列表结束-->
   <div class="page">
   <huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false" 
   formName="houses" offerPageSize="20,50,100" isExistForm="true" 
   queryFunction="nonHouseChangePages('${globalUrl}broker.show?actionMethod=getBrokerAnswered', jsoninit, 'rightContent')"/>
   </div>
<%--
<script type="text/javascript">
function nonHouseChangePages() {
	var currvalue = document.getElementsByName("currentPage")[0].value;
	setJSONValue2("currentPage",currvalue);
	setJSONValue2("brokerId",brokerId);
	postDataByURL2('${globalUrl}broker.show?actionMethod=getBrokerAnswered',jsoninit,"rightContent");
}
</script>
 --%>