<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style type="text/css">
#1 { border: solid; border-width: 1px; }
</style>

	<div id="type">
		<c:forEach var="type" items="${newTypeList}">
			<span><a href="#">${type.typeName}</a></span>
		</c:forEach>
	</div>
	<div class="gridPageBtn">
	<huatek:ajaxCutPage pageBean="${pageBean}" isOuterForm="true"
		formName="newsBean" offerPageSize="20,50,100" isExistForm="true" />
	</div>
	<div class="clear"></div>
	<table id="">
		<form modelAttribute="newsBean" name="newsBean">
			<tbody>
				<c:forEach var="newsBean" items="${pageBean.dataList}">
					<tr>
						<td>【${newsBean.newsType.typeName}】</td>
						<td>${newsBean.newsTitle} </td>
						<td>${newsBean.publishTime} </td>
					</tr>
				</c:forEach>
			</tbody>
		</form>
	</table>
