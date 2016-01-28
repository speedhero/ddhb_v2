<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/style.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/more.css">
<div class="fcwd_main">
	<%-- questionSize 用于计数 --%>
	<c:set var="questionSize" value="1"></c:set>
      <!--房产问答开始-->
      <div class="fcwd_l">
	    <div class="wda_tit">
		   <b>问答&amp;攻略</b>
		   <span></span>
		</div>
		<div class="wda_box">
		   <div class="wdbox_head">
		      <span>房屋买卖</span>
			  <a href="javascript:void(0);" onclick='window.location.href="${globalUrl}broker.show?actionMethod=getAllQuestion&typeId=1"'>更多>></a>
		   </div>
		   <ul>
		  	 <c:forEach var="housingSale" items="${housingSaleList}"> 
		      <li>
			     <a href="javascript:void(0);" onclick='window.open("${globalUrl}broker.show?actionMethod=initQuestionDetail&questionId=${housingSale.erpId }&typeId=${housingSale.questionType.parentType.erpId }&subTypeId=${housingSale.questionType.erpId }")'
			      id="question_${questionSize}">${housingSale.title }</a>
				 <span ><fmt:formatDate value="${housingSale.createTime }" pattern="yyyy-MM-dd"></fmt:formatDate></span>
			  </li>
			  <c:set var="questionSize" value="${questionSize+1 }"></c:set>
			 </c:forEach>
			 <c:if test="${housingSaleList.size() le 0 }">
			  	<li>暂无任何相关信息</li>
			  </c:if>
		   </ul>
		</div>
		<div class="wda_box wid_360 fl">
		   <div class="wdbox_head">
		      <span>银行贷款</span>
			  <a href="javascript:void(0);" onclick='window.location.href="${globalUrl}broker.show?actionMethod=getAllQuestion&typeId=3"'>更多>></a>
		   </div>
		   <ul>
		   <c:forEach items="${bankLoanList }" var="bankLoan">
		      <li>
			     <a href="javascript:void(0);" onclick='window.open("${globalUrl}broker.show?actionMethod=initQuestionDetail&questionId=${bankLoan.erpId }&typeId=${bankLoan.questionType.parentType.erpId }&subTypeId=${bankLoan.questionType.erpId }")'
					id="question_${questionSize}">${bankLoan.title }</a>
				 <span><fmt:formatDate value="${bankLoan.createTime }" pattern="yyyy-MM-dd"/></span>
			  </li>
			  <c:set var="questionSize" value="${questionSize+1 }"></c:set>
		   </c:forEach>
		    <c:if test="${bankLoanList.size() le 0 }">
			  	<li>暂无任何相关信息</li>
			  </c:if>
		   </ul>
		</div>
		<div class="wda_box wid_360 fr">
		   <div class="wdbox_head">
		      <span>房屋租赁</span>
			  <a href="javascript:void(0);" onclick='window.location.href="${globalUrl}broker.show?actionMethod=getAllQuestion&typeId=5"'>更多>></a>
		   </div>
		   <ul>
		 	  <c:forEach items="${housingLeaseList }" var="housingLease">
			     <li>
			     	<a href="javascript:void(0);" onclick='window.open("${globalUrl}broker.show?actionMethod=initQuestionDetail&questionId=${housingLease.erpId }&typeId=${housingLease.questionType.parentType.erpId }&subTypeId=${housingLease.questionType.erpId }")' 
			     		id="question_${questionSize}">${housingLease.title }</a>
				 	<span><fmt:formatDate value="${housingLease.createTime }" pattern="yyyy-MM-dd"/></span>
			 	 </li>
			  <c:set var="questionSize" value="${questionSize+1 }"></c:set>
			  </c:forEach>
			  <c:if test="${housingLeaseList.size() le 0 }">
			  	<li>暂无任何相关信息</li>
			  </c:if>
		   </ul>
		</div>
		<div class="wda_box wid_360 fl">
		   <div class="wdbox_head">
		      <span>房产知识</span>
			  <a href="javascript:void(0);" onclick='window.location.href="${globalUrl}broker.show?actionMethod=getAllQuestion&typeId=6"'>更多>></a>
		   </div>
		   <ul>
		      <c:forEach items="${realEstateKnowledgeList }" var="realEstateKnowledge">
			     <li>
			     	<a href="javascript:void(0);" onclick='window.open("${globalUrl}broker.show?actionMethod=initQuestionDetail&questionId=${realEstateKnowledge.erpId }&typeId=${realEstateKnowledge.questionType.parentType.erpId }&subTypeId=${realEstateKnowledge.questionType.erpId }")' 
			     		id="question_${questionSize}">${realEstateKnowledge.title }</a>
				 	<span><fmt:formatDate value="${realEstateKnowledge.createTime }" pattern="yyyy-MM-dd"/></span>
			 	 </li>
			  <c:set var="questionSize" value="${questionSize+1 }"></c:set>
			  </c:forEach>
			  <c:if test="${realEstateKnowledgeList.size() le 0 }">
			  	<li>暂无任何相关信息</li>
			  </c:if>
		   </ul>
		</div>
		<div class="wda_box wid_360 fr">
		   <div class="wdbox_head">
		      <span>房屋装修</span>
			  <a href="javascript:void(0);" onclick='window.location.href="${globalUrl}broker.show?actionMethod=getAllQuestion&typeId=7"'>更多>></a>
		   </div>
		   <ul>
		      <c:forEach items="${housingDecorationList }" var="housingDecoration">
			     <li>
			     	<a href="javascript:void(0);" onclick='window.open("${globalUrl}broker.show?actionMethod=initQuestionDetail&questionId=${housingDecoration.erpId }&typeId=${housingDecoration.questionType.parentType.erpId }&subTypeId=${housingDecoration.questionType.erpId }")' 
			     		id="question_${questionSize}">${housingDecoration.title }</a>
				 	<span><fmt:formatDate value="${housingDecoration.createTime }" pattern="yyyy-MM-dd"/></span>
			 	 </li>
			  <c:set var="questionSize" value="${questionSize+1 }"></c:set>
			  </c:forEach>
			   <c:if test="${housingDecorationList.size() le 0 }">
			  	<li>暂无任何相关信息</li>
			  </c:if>
		   </ul>
		</div>
		<div class="wda_box wid_360 fl">
		   <div class="wdbox_head">
		      <span>政策法规</span>
			  <a href="javascript:void(0);" onclick='window.location.href="${globalUrl}broker.show?actionMethod=getAllQuestion&typeId=8"'>更多>></a>
		   </div>
		   <ul>
		       <c:forEach items="${policyAndRegulationsList }" var="policyAndRegulations">
			     <li>
			     	<a href="javascript:void(0);" onclick='window.open("${globalUrl}broker.show?actionMethod=initQuestionDetail&questionId=${policyAndRegulations.erpId }&typeId=${policyAndRegulations.questionType.parentType.erpId }&subTypeId=${policyAndRegulations.questionType.erpId }")' 
			     		id="question_${questionSize}">${policyAndRegulations.title }</a>
				 	<span><fmt:formatDate value="${policyAndRegulations.createTime }" pattern="yyyy-MM-dd"/></span>
			 	 </li>
			  <c:set var="questionSize" value="${questionSize+1 }"></c:set>
			  </c:forEach>
			  <c:if test="${policyAndRegulationsList.size() le 0 }">
			  	<li>暂无任何相关信息</li>
			  </c:if>
		   </ul>
		</div>
		<div class="wda_box wid_360 fr">
		   <div class="wdbox_head">
		      <span>其他问题</span>
			  <a href="javascript:void(0);" onclick='window.location.href="${globalUrl}broker.show?actionMethod=getAllQuestion&typeId=9"'>更多>></a>
		   </div>
		   <ul>
		     <c:forEach items="${otherQuestionsList }" var="otherQuestions">
			     <li>
			     	<a href="javascript:void(0);" onclick='window.open("${globalUrl}broker.show?actionMethod=initQuestionDetail&questionId=${otherQuestions.erpId }&typeId=${otherQuestions.questionType.parentType.erpId }&subTypeId=${otherQuestions.questionType.erpId }")' 
			     		id="question_${questionSize}">${otherQuestions.title }</a>
				 	<span><fmt:formatDate value="${otherQuestions.createTime }" pattern="yyyy-MM-dd"/></span>
			 	 </li>
			  <c:set var="questionSize" value="${questionSize+1 }"></c:set>
			  </c:forEach>
			   <c:if test="${otherQuestionsList.size() le 0 }">
			  	<li>暂无任何相关信息</li>
			  </c:if>
		   </ul>
		</div>
      	<div class="wda_ls">
          	<ul>
          		<c:forEach items="${qestionStrategyTypeList }" var="questionType">
	<li><a href="javascript:void(0);" onclick='window.location.href="${globalUrl}broker.show?actionMethod=getAllQuestion&typeId=${questionType.erpId }"'><span><img src="${pictureHost}${questionType.iconUrl}" alt=""/></span></a><p>${questionType.typeName }</p></li>
</c:forEach>
              </ul>
          </div>
      </div>
      <!--房产问答结束-->
      <!--房产问答右侧开始-->
      <div class="fcwd_r">
      	<div class="wytw_an"><a href="javascript:void(0);" onclick="window.location.href='${globalUrl}broker.show?actionMethod=addQuestion&questionId=0'">我要提问</a></div>
          <div class="zs_zj">
          	<div>
              	<span>共有房产知识</span>
                  <p><b>${questionCount }</b>条</p>
              </div>
          	<div class="divr">
              	<span>共有相关专家</span>
                  <p><b>${brokerCount }</b>人</p>
              </div>
          </div>
          <div class="wc_rt_ls">
          	<h2><a href="javascript:void(0);" onclick="window.location.href='${globalUrl}broker.show?actionMethod=getAllQuestion&typeId=1'">查看更多</a>房产买卖最新</h2>
              <ul>
              	<c:forEach items="${qestionStrategyListFirst }" var="qestionStrategyFirst">
					<li><a href="javascript:void(0);" onclick='window.location.href="${globalUrl}broker.show?actionMethod=initQuestionDetail&questionId=${qestionStrategyFirst.erpId }&typeId=${qestionStrategyFirst.questionType.parentType.erpId }&subTypeId=${qestionStrategyFirst.questionType.erpId }"'>${qestionStrategyFirst.title }</a></li>
				</c:forEach>
              </ul>
          </div>
          <div class="wc_rt_ls">
          	<h2><a href="javascript:void(0);" onclick="window.location.href='${globalUrl}broker.show?actionMethod=getAllQuestion&typeId=5'" >查看更多</a>房产租赁最新</h2>
              <ul>
              	<c:forEach items="${qestionStrategyListSecond }" var="qestionStrategyListSecond">
					<li><a href="javascript:void(0);" onclick='window.location.href="${globalUrl}broker.show?actionMethod=initQuestionDetail&questionId=${qestionStrategyListSecond.erpId }&typeId=${qestionStrategyListSecond.questionType.parentType.erpId }&subTypeId=${qestionStrategyListSecond.questionType.erpId }"'>${qestionStrategyListSecond.title }</a></li>
				</c:forEach>
              </ul>
          </div>
				<div class="ht_rt">
                    <span><a href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=1" class="wymf">我要买房</a></span>
                    <span><a href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=2" class="wymaif">我要卖房</a></span>
                    <span><a href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=3" class="wyzf">我要租房</a></span>
                    <span><a href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=4" class="wycz">我要出租</a></span>
                </div>
      </div>
      <!--房产问答右侧结束-->
</div>
<script type="text/javascript">
	$(document).ready(function(){
		var questionSize = ${questionSize};
		for(var i = 1;i <= questionSize; i++){
			var questionValue = $.trim($("#question_" + i).text());
			//处理房屋买卖字符
			if(i<=5){
				if(questionValue.length > 50){
					var _value = questionValue.substr(0, 50) + "...";
					$("#question_" + i).html(_value);
				}
			}else{
				if(questionValue.length > 20){
					var _value = questionValue.substr(0, 20) + "...";
					$("#question_" + i).html(_value);
				}
			}
		}
	});
</script>
