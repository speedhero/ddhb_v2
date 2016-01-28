<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${globalUrl}js/cmsDefault.js"></script>
<script type="text/javascript" src="${globalUrl}js/ht_frame.js"></script>
<style type="text/css">
#searchBox {
	width: auto;
	height: auto;
	background-color: green;
	margin-left: 10px;
	margin-top: 10px;
	}

.secondlia {
	font-size: 20px;
	color: white;
	text-decoration: none;
}
.secondli {
	float: left;
	list-style-type: none;	
	margin-left: 8px;
	margin-top: 15px;
}
#searchInput {
	width: 500px;
	height: 30px;
	margin-left: 15px;
	margin-top: 10px;
}

.thirdlia {
	text-decoration: none;
	font-size: 14px;
	color: white;
	margin-top: 2px;
}

.thirdli {
	margin-left:10px;
	float: left;
	list-style-type: none;
	font-size: 20px;
	color: white;
}

li a:hover {
	color: blue;
}

li a:visited {
	color: black;
}
</style>
<div id="searchBox">
	<ul id="secondSerchLev">
		<c:forEach items="${searchItems }" var="searchItem">
			<li class="secondli"><a href="javascript:void(0);" class="secondlia" onclick='getData("${globalUrl}searchMenu.show?actionMethod=initSearchMenu&modulename=home&id=${searchItem.id}","", "searchBox")'>${searchItem.searchLabel }</a></li>
		</c:forEach>
		<form>
			<input type="text" id="searchInput"/>
		</form>
	</ul>
	<ul id="third">
	<table>
		<c:forEach items="${searchItems }" var="searchItem" varStatus="s1">
			<c:forEach items="${searchItem.subItems }" var="subItem">
				<c:if test="${subItem.isHidden == 0 }">
					<tr style="display: block;">
						<td><li class="thirdli">${subItem.searchLabel }: </li></td>
						<ul class="thirdul">
							<c:forEach items="${subItem.searchFileds }" var="searchFiled">
								<td><li class="thirdli"><a href="javascript:void(0);" class="thirdlia" onClick="alert('${searchItem.searchLabel }=${searchFiled.fieldValue }');">${searchFiled.fieldName }</a></li></td>
							</c:forEach>
						</ul>
					</tr>
				</c:if>
				<c:if test="${subItem.isHidden == 1 }">
					<tr style="display: none;" class="hiddenTr">
						<td><li class="thirdli">${subItem.searchLabel }: </li></td>
						<ul class="thirdul">
							<c:forEach items="${subItem.searchFileds }" var="searchFiled">
								<td><li class="thirdli"><a href="javascript:void(0);" class="thirdlia" onClick="alert('${searchItem.searchLabel }=${searchFiled.fieldValue }');">${searchFiled.fieldName }</a></li></td>
							</c:forEach>
						</ul>
					</tr>
				</c:if>
			</c:forEach>
		</c:forEach>
	</table>
	</ul>
</div>
