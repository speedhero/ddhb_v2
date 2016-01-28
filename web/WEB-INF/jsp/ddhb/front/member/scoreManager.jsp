<%@page import="org.apache.el.lang.ELSupport"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
$(document).ready(function() {
	$("#deliverBtn").click(function(){
			window.location.href='${globalUrl}usercenter.do?actionMethod=deliverScore';
	});
});
</script>
<style type="text/css">
.scoremanagerDiv table {
	border-bottom: 1px solid #CCCCCC;
	width: 100%;
}

.scoremanagerDiv table th {
	text-align: left;
	padding: 0;
	height: 30px;
	background: url("${globalUrl}images/memberCenter/PCPad-4-2.png");
	color: #ffffff;
}

.scoremanagerDiv table td {
	text-align: left;
	padding: 0;
	height: 30px;
	vertical-align: bottom;
	padding-bottom: 9px;
	border-top: 1px dashed #CCCCCC;
}

.firstleftup {
	width: 5px;
	height: 5px;
	background-image: url('${globalUrl}images/memberCenter/left_up.png');
}

.firstright {
	width: 5px;
	height: 5px;
	background-image: url('${globalUrl}images/memberCenter/right-up.png');
}

.firstmiddle {
	height: 5px;
	background-image: url('${globalUrl}images/memberCenter/middle_up.png');
}

.secondleft {
	width: 5px;
	background-image: url('${globalUrl}images/memberCenter/left_middle.png');
}

.secondright {
	width: 6px;
	background-image: url('${globalUrl}images/memberCenter/right_middle.png');
}

.secondmiddle {
	background-image: url('${globalUrl}images/memberCenter/middle_middle.png');
}

.thirdleft {
	width: 5px;
	height: 5px;
	background-image: url('${globalUrl}images/memberCenter/left_down.png');
}

.thirdright {
	width: 5px;
	height: 5px;
	background-image: url('${globalUrl}images/memberCenter/right_down.png');
}

.thirdmiddle {
	height: 5px;
	background-image: url('${globalUrl}images/memberCenter/middle_down.png');
}

.buttonStyle {
	background-color: #3fb8b1;
	border: 2px solid #3fb8b1;
	margin-bottom: 10px;
	color: #ffffff;
	cursor: pointer;
	height: 38px;
	line-height: 30px;
	text-align: center;
	width: 120px;
}
</style>
<div class="cp_rt">
	<div class="licn"><a onclick='window.location.href="${globalUrl}welcome.show?actionMethod=welcome"'>首页</a>&nbsp;>&nbsp;个人中心&nbsp;>&nbsp;积分管理</div>
	  <div class="gr_xxi">您好，您的账户目前有积分<b>${myScore.integral }</b>分，其中<b>${myScore.changedIntegral }</b>积分可以用来在购买服务时折现，抵扣服务费用，或转赠他人。</div>
	  <div class="gr_ls">
	  	<input id="deliverBtn" class="buttonStyle" type="button" value="我要将积分转赠" >
	  	<div class="gr_tit">
       	<ul>
           	<li class="li_1">序号</li>
           	<li class="li_4">积分</li>
           	<li class="li_6">时间</li>
           	<li class="li_6">备注</li>
           	<li class="li_6">参与用户</li>
           </ul>
       </div>
	<c:forEach items="${pageBean.dataList }" var="scoreHistory" varStatus="status">
		<div class="gr_nlb">
			<div class="gr_nrb">
				<ul>
					<li class="li_1"><b><c:out value="${(pageBean.currentPage - 1) * pageBean.pageSize + status.index + 1 }"></c:out></b></li>
					<li class="li_4"><span>积分:</span>${scoreHistory.integral }</li>
					<li class="li_6"><span>时间:</span><fmt:formatDate value="${scoreHistory.gettedTime }" pattern="yyyy-MM-dd"/></li>
					<li class="li_6"><span>备注:</span>${scoreHistory.comment }</li>
					<li class="li_6"><span>参与用户:</span>
						<c:choose>
						<c:when test="${scoreHistory.user == null}">系统赠送</c:when>
						<c:otherwise><c:if test="${scoreHistory.user.accName == ''}">系统赠送</c:if>
						<c:if test="${scoreHistory.user.accName != null}">${scoreHistory.user.accName}</c:if>
						</c:otherwise>
						</c:choose> 
					</li>
                 </ul>
                </div>
                <div class="clear"></div>
            </div>
            </c:forEach>
        </div>
        <div class="page" style="display:block;">
		<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false" formName="answerdForm" offerPageSize="20,50,100" isExistForm="true" queryFunction="changePages()"/>
</div>
</div>
<script type="text/javascript">
function changePages() {
	var currvalue = document.getElementsByName("currentPage")[0].value;
	setjsonvalue("currentPage", currvalue);
	postDataByURL2('${globalUrl}usercenter.do?actionMethod=scoreManager&isCutPage=true',jsoninit,"changelist");
}
</script>