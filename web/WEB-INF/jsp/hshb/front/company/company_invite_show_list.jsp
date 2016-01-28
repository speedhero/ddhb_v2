<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--<style>
#interviewClass{width:670px; max-height:407px;}
#interviewClass .workPosition{width:180px;}
#interviewClass .workLocation{width:110px;}
#interviewClass .personCount{width:80px;}
#interviewClass .publishTime{width:110px;}
.hasUnderLine:hover{text-decoration: underline;}
.YP{width:80px;border-bottom: 1px solid #c3c3c3;}
.toYP{width:80px;}

</style>-->

<script type="text/javascript">
function toDetailPage(positionId){
	if (!isRemoviableDevice()){
		return;
	}
	window.open("${globalUrl}company" + positionId);  
}
<%--
//判断设备的屏幕是不是小屏幕
function isRemoviableDevice(){
	var winWidth = 1024;
	if (window.innerWidth){
		winWidth = window.innerWidth;
	}else if ((document.body) && (document.body.clientWidth)){
		winWidth = document.body.clientWidth;
	}
	if (winWidth <= 960){
		return true;
	}
	return false;
}
--%>
</script>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
	  <th scope="col">职位名称</th>
	  <th scope="col" align="center" width="100">工作地点 </th>
	  <th scope="col" align="center" width="80">招聘人数</th>
	  <th scope="col" align="center" width="100">发布时间</th>
	  <th scope="col" align="center" width="80">应聘</th>
	</tr>
	<c:forEach items="${RecruitPositionList }" var="position" >
		<tr onclick="toDetailPage('${position.id }')">
			<td>
				<a class="hasUnderLine" href="${globalUrl}company/positionDetail/${position.id }">
					<c:out value="${position.positionName }"></c:out>
				</a>
			</td>
		<td><div class="gonzdz"><c:out value="${position.workplace }"></c:out></div></td>
		<td><c:out value="${position.needed }"></c:out></td>
		<td><fmt:formatDate value="${position.createtime }" pattern="yyyy-MM-dd"/></td>
		<td><a class="yp" style="padding:0; background:none;" href="${globalUrl}company/positionDetail/${position.id }/true">我要应聘</a></td>
	</tr>
	</c:forEach>
</table>
<div class="page" style="display:block;">
	<c:import url="/WEB-INF/jsp/hshb/front/commonInfoShow/pager.jsp"/>
</div>
<!--右侧结束-->
