<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
var globalUrl = '${globalUrl}';
</script>
<!-- 图片显示 -->
<div id="image_rent_house" style="width: 101%;">
	<%@include file="/WEB-INF/jsp/ddhb/front/rent/rent_list_broker.jsp"%>
	<div style="clear: both; height: 0px;"></div>
</div>
<input type="hidden" id="houseTotleRows" value="${pageBean.totalRows}" />
<div class="cutpage">
	<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false"
		formName="houses" offerPageSize="20,50,100" isExistForm="true"
		queryFunction="nonHouseChangePages('${globalUrl}broker.show?actionMethod=dimquery', jsonStringInit, 'changelist')" />
</div>
<%--
<script type="text/javascript">
	function nonHouseChangePages() {
		var currvalue = document.getElementsByName("currentPage")[0].value;
		setJSONValue("currentPage", currvalue);
		postDataByURL2('${globalUrl}broker.show?actionMethod=dimquery', jsonStringInit, "changelist");
	}
</script>
 --%>