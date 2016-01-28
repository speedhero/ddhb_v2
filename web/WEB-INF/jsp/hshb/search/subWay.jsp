<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${ht_globalUrl}/js/cmsDefault.js"></script>
<script type="text/javascript" src="${ht_globalUrl}/js/ht_frame.js"></script>
<style type="text/css">
	dd {
	color: #A2D2FF;
	font-size: 12px;
	padding: 0 10px;
	float:left;
	}
	a {
		color: white;
		font-size: 14px;
	}
	
	a:hover {
		color: blue;
	}

</style>

<c:forEach items="${subway}" var="subway">
	<dd><a href="javascript:void(0);" onclick="conditions('${subway.fieldName }');">${subway.fieldName }</a></dd>
</c:forEach>