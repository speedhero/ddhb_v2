<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style type="text/css">
#recruitTable { border: solid; border-width: 1px; }
</style>

<div id="recruitInformationShow">
<table id="tb_information_table_list">
	<form modelAttribute="recruitPosition" name="recruitPosition">
			<thead>
				<tr>
					<th>职位名称</th>
					<th>部门</th>
					<th>职位描述</th>
					<th>其他信息</th>
					<th>信息创建时间</th>
					<th>信息发布时间</th>
					<th>需求</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="recruit" items="${pageBean.dataList}">
					<tr>
						<td>${recruit.positionName }</td>
						<td>${recruit.department }</td>
						<td>${recruit.description }</td>
						<td>${recruit.otherinformation }</td>
						<td>${recruit.createTime }</td>
						<td>${recruit.publishedTime }</td>
						<td>${recruit.requirement }</td>
					</tr>
				</c:forEach>
			</tbody>
	</form>
</table>
</div>
