<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.huatek.framework.sso.SSOServiceManagement"%>
<style type="text/css">
.searchItem {width:80%; margin:0 auto; text-align:left; border:1px solid #ccc; padding-left:2em 0;}
</style>
<div class="secondHeader">
	<div class="searchItem">
		<button>区域</button>
		<button>地铁</button>
		<button>学区</button>
		<button>预算</button>
	</div>
	<div class="searchItem">
		区域：&nbsp;不限
		<c:forEach var="areaItem" items="${area}">
			<span>${areaItem.countyName}</span>
		</c:forEach>
	</div>
</div>