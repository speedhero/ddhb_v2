<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--
<script type="text/javascript">
function nonHouseChangePages() {
	var currvalue = document.getElementsByName("currentPage")[0].value;
	setJSONValue("currentPage", currvalue);
	showSelectedField("${globalUrl}broker.show?actionMethod=dimquery");
}
</script>
 --%>
<input type="hidden" id="houseTotleRows" value="${pageBean.totalRows}"/>
<c:choose>  
  <c:when test="${housetype eq 2}"><%@include file="/WEB-INF/jsp/ddhb/front/rent_list.jsp" %></c:when>  
  <c:otherwise><%@include file="/WEB-INF/jsp/ddhb/front/house_list.jsp" %></c:otherwise>  
</c:choose>
<div style="clear:both;"></div>
<div class="page">
	<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false"
	formName="houses" offerPageSize="20,50,100" isExistForm="true" 
	queryFunction="nonHouseChangePages('${globalUrl}broker.show?actionMethod=dimquery')"/>
</div>