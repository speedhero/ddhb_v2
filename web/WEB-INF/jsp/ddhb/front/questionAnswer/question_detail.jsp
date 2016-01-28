<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<title>问答&攻略 - ${title } </title>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/style.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/more.css">
<style>
#ques_con img{
	width: auto;
	height: auto;
	border: 0 none;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('.everyType[value="${typeId}"]').addClass("one");
	var subtype = "${subTypeId}";
	if(null != subtype && "" != subtype){
		$('.everyType1[value="${subTypeId}"]').addClass("one");
		$('.everyType1[value="${subTypeId}"] > div').first().css("display", "block");
	}else {
		$(".everyType1 > div").first().css("display", "block");
	}
	
	$(".everyType").click(function(){
		$(".one").first().removeClass("one");
		$(this).addClass("one");
	});
	
	$(".everyType1").click(function(){
		$(".one").removeClass("one");
		$(this).addClass("one");
	});
});

function showicon(id) {
	$(".everyType1 > div").css("display", "none");
	$("#" + id).css("display", "block");
}
</script>
<div class="Location"><a onclick='window.location.href="${globalUrl}"' style="cursor: pointer;">首页</a>&gt;产品问答</div>
        <div class="fcwd">
            <!--房产问答开始-->
            <div class="fcwd_l">
            	<div class="fcwd_tit">
                	<div class="tit_yc"><a href="#">房屋产权</a></div>
                    <div class="xl_com">
                    	<c:forEach items="${qestionStrategyTypeList }" var="questionType">
							<a href="javascript:void(0);" onclick='window.location.href="${globalUrl}broker.show?actionMethod=getAllQuestion&typeId=${questionType.erpId }"' value="${questionType.erpId }" class="everyType">${questionType.typeName }</a>
						</c:forEach>
                    </div>
                </div>
                <div class="fcwd_ft">
                	<div class="tit_yc"><a href="#">收房入住<span> （39）</span></a></div>
               		<div class="xl_com">
               		<c:forEach items="${questionStrategySubTypeList }" var="questionStrategySubType">
						<a href="javascript:void(0);" class="everyType1" onclick='getData("${globalUrl}broker.show?actionMethod=getQuestionBySubType&subTypeId=${questionStrategySubType.erpId }","","eachFloat");' value="${questionStrategySubType.erpId }">${questionStrategySubType.subTypeName }<span> （${questionStrategySubType.countQuestion }）</span></a>
					</c:forEach>
               		
               		</div>
                </div>
                <div class="clear"></div>
                <div id="eachFloat">
                <div  class="wd_zzy">
                	<div class="wen">${questionContent.title }</div>
                    <div class="wd_nr" id="ques_con">
                    	<span style="float:left">答：</span>
                        <p>${questionContent.content }</p>
                    </div>
                    <div class="wd_fl"><c:out value="${questionContent.questionType.parentType.typeName }"></c:out>-
					<c:out value="${questionContent.questionType.subTypeName }"></c:out>|  浏览：${questionContent.browsed }次  |  发布时间：<fmt:formatDate value="${questionContent.createTime }" pattern="yyyy-MM-dd"></fmt:formatDate> | 发布人：<c:if test="${questionContent.publishName eq ''}">游客</c:if><c:if test="${questionContent.publishName ne ''}"><c:out value="${questionContent.publishName}"></c:out></c:if></div>
					<c:if test="${fn:length(broderAnseredList) gt 0}">
					<c:forEach items="${broderAnseredList }" var="broderAnsered" varStatus="broder" begin="0" end="0">
                    <div class="wd_da">${broderAnsered.answeredContent }</div>
                    <%-- <c:if test="${broder.count == 1 }">
					<c:if test="${LoginMember != null }">
					 <div class="tj_an"><a href="javascript:void(0);" onclick="window.location.href='${globalUrl}broker.show?actionMethod=addQuestion&questionId=${broderAnsered.questionStategy.erpId}'">追加提问</a></div>
					</c:if>
					<c:if test="${LoginMember == null }">
					<div class="tj_an"><a href="javascript:void(0);"  onclick="window.location.href='${globalUrl}login.show?actionMethod=loginCheck'">追加提问</a></div>
					</c:if>
					</c:if> --%>
                   </c:forEach>
                    </c:if>
                </div>
                <div class="fc_newsls">
                	<h2>相关问题</h2>
                    <ul>
                    	<c:forEach items="${questionStrategyListBySubType }" var="question" varStatus="questionCount">
                    	<li><span><fmt:formatDate value="${question.createTime }" pattern="yyyy-MM-dd"></fmt:formatDate></span><a href="javascript:void(0);" onclick='window.location.href="${globalUrl}broker.show?actionMethod=initQuestionDetail&questionId=${question.erpId }&typeId=${question.questionType.parentType.erpId }&subTypeId=${question.questionType.erpId }"'> · ${question.title }</a></li>
                    	</c:forEach>
                    </ul>
                </div>
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
    </div>