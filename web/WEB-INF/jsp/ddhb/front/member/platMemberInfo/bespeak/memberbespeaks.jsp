<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="contentBorBox">
	<form:form modelAttribute="MemberBespeak" name="MemberBespeak" action="${globalUrl}memberBespeak.do?actionMethod=getMemberBespeakList">
		<div>
			<table style="border: 1px solid; width:80%;">
			<caption style="font-size:20px">预约信息</caption>
				<tr>
					<th>预约类型</th>
					<th>经纪人</th>
					<th>预约开始时间</th>
					<th>预约结束时间</th>
					<th>联系人</th>
					<th>联系人电话</th>
					<th>邮箱</th>
					<th>创建时间</th>
					<th>更新时间</th>
				</tr>
				<c:forEach items="${memberBespeaks }" var="memberbespeak">
				<tr>
					<td>${memberbespeak.broker_type }</td>
					<td>${memberbespeak.platBroker.bname}</td>
					<td>${memberbespeak.bespeak_time_start }</td>
					<td>${memberbespeak.bespeak_time_end }</td>
					<td>${memberbespeak.contacts }</td>
					<td>${memberbespeak.contacts_phone }</td>
					<td>${memberbespeak.emails }</td>
					<td>${memberbespeak.created_time }</td>
					<td>${memberbespeak.modified_time }</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</form:form>
</div>