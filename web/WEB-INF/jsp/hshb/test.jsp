<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=8"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
		<script type="text/javascript" src="${globalUrl}js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="${globalUrl}js/search/myMap_new.js"></script>
		<script type="text/javascript" src="${globalUrl}js/search/jquery.search.phone.js"></script>
		<title>华邦地产 </title>
		<link rel="stylesheet" type="text/css" href="${globalUrl}css/searchPlugin/jquery.search.phone.css"/>
		<script type="text/javascript">
			String.prototype.replaceAll = function(s1,s2) {
			    return this.replace(new RegExp(s1,"gm"),s2);
			}
			
			var searchMap = new Map();
			
			$(document).ready(function(){
				var str = $.parseJSON('${jsonString}');
				option={
					searchItems:str,
					searchMap : searchMap
				};
				$("#searchMenuDiv").createSearch(option);
			});
		</script>
	</head>
	<body>
		<div id="searchMenuDiv" style="width:100%;  height: 400px"></div>
	</body>
</html>