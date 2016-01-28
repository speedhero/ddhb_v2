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
	width: 100%;
	height: auto;
	background-color: green;
	margin-top: 10px;
}

#searchInput {
	width: 500px;
	height: 30px;
	float: right;
	padding-left: 10px;
	margin-top: 10px;
}

.first:LINK {
	color: white;
	font-size: 20px;
}

.first:HOVER {
	color: blue;
	font-size: 20px;
}

dl {
	float: left;
	width: 650px;
}

dt {
	color: #FFFFFF;
	float: left;
	font-size: 14px;
	font-weight: bold;
	width: 50px;
}

dt a {
	color: #FFFFFF;
	font-size: 14px;
}

dd {
	color: #A2D2FF;
	float: left;
	font-size: 12px;
	padding: 0 10px;
}

#firstFlo {
	padding-top: 20px;
	padding-left: 10px;
}

#secondFlo {
	padding-top: 20px;
	padding-left: 10px;
}

#conditions {
	width: 650px;
	padding-top: 100px;
}
</style>
<!-- 修改css，显示隐藏字段 -->
<script>
	function showHiddenItems() {
		if ($('#showOrHidden').attr("value") == "more") {
			$('#showOrHidden').attr("value", "hidden");
			$(".hiddenDl").css("display", "block");
		} else {
			$('#showOrHidden').attr("value", "more");
			$(".hiddenDl").css("display", "none");
		}
	}

	var index = 0;
	var idArr = new Array();
	var conditionArr = new Array();
	
	function conditions(conditionkey, condition, STwoId) {
		var conditionString = "";
		if(idArr.length == 0){
			$('#conditionSpan').append('<a onclick="removeA('+ STwoId+ ');" id='+ STwoId+ ' ><img src="${globalUrl}images/remove.png" name="remove" style="width:10px; height:10px;"/>'+ condition + '</a>');
			idArr[index] = STwoId;
			conditionArr[index] = conditionkey;
			index += 1;
			//$('#conditionSpan').attr("value", conditionkey);
			//alert($("#conditionSpan").attr("value"));
		}else {
			for(var i = 0; i <= index; i++){
				if(idArr[i] == STwoId){
					removeA(STwoId);
					conditionArr[i] = conditionkey;
					break;
				}
			}
			$('#conditionSpan').append('<a onclick="removeA('+ STwoId+ ');" id='+ STwoId+ ' ><img src="${globalUrl}images/remove.png" name="remove" style="width:10px; height:10px;"/>'+ condition + '</a>');
			idArr[index] = STwoId;
			conditionArr[index] = conditionkey;
			for(var j = 0; j < conditionArr.length; j++){
				conditionString += conditionArr[j]+"&";
			}
			$('#conditionSpan').attr("value", conditionString);
		}
	}

	function removeA(STwoId) {
		$('#' + STwoId).remove();
	}

	function getSubWay(id) {
		$('#subWay').empty();
		$('#conditionSpan').empty();
		getData("${globalUrl}searchMenu.show?actionMethod=getSubWayData&id=" + id, "", "subWay");
	}
</script>
<div class="houseList">
<div id="searchBox">
	<div id="firstFlo">
		<c:forEach items="${searchItems }" var="searchItem">
			<a href="javascript:void(0);" class="first" onclick='getData("${globalUrl}searchMenu.show?actionMethod=initSearchMenu&modulename=secondhand&id=${searchItem.id}","", "searchBox")'>${searchItem.searchLabel}</a>
		</c:forEach>
	</div>
	<div id="secondFlo">
		<c:forEach items="${searchItems }" var="searchItem" varStatus="s1">
			<c:forEach items="${searchItem.subItems }" var="subItem" varStatus="s2">
				<!-- 显示项目 -->
				<c:if test="${subItem.isHidden == 0 }">
					<dl style="display: block">
						<dt>${subItem.searchLabel }:</dt>
						<c:choose>
							<c:when test="${s1.index == 1 }">
								<c:forEach items="${subItem.searchFileds }" var="searchFiled" varStatus="s3">
									<c:choose>
										<c:when test="${s2.index==0 }">
											<dd>
											<a href="javascript:void(0);" onclick="getSubWay('${searchFiled.id }');">${searchFiled.fieldName}</a>
											</dd>
										</c:when>
										<c:otherwise>
											<dd>
												<a href="javascript:void(0);"
												onclick="conditions('${searchItem.searchLabel }=${searchFiled.fieldValue }', '${searchFiled.fieldName }', '${searchFiled.searchItem.id }');">${searchFiled.fieldName
												}</a>
											</dd>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach items="${subItem.searchFileds }" var="searchFiled"
									varStatus="s3">
									<dd>
										<a href="javascript:void(0);"
											onclick="conditions('${searchItem.searchLabel }=${searchFiled.fieldValue }', '${searchFiled.fieldName }','${searchFiled.searchItem.id }');">${searchFiled.fieldName
											}</a>
									</dd>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						<dt></dt>
					</dl>
				</c:if>
				<!-- 隐藏项目 -->
				<c:if test="${subItem.isHidden == 1 }">
					<dl style="display: none" class="hiddenDl">
						<dt>${subItem.searchLabel }:</dt>
						<c:forEach items="${subItem.searchFileds }" var="searchFiled" varStatus="s3">
							<dd><a href="javascript:void(0);" onclick="conditions('${searchItem.searchLabel }=${searchFiled.fieldValue }', '${searchFiled.fieldName }','${searchFiled.searchItem.id }');">${searchFiled.fieldName }</a></dd>
						</c:forEach>
					</dl>
				</c:if>
				<!-- 显示地铁线路 -->
				<div id="subWay">
					<c:if test="${s2.index == 0 }">
						<dl>
							<c:forEach items="${searchItems }" var="searchItem">
								<c:forEach items="${searchItem.subItems }" var="subItem">
									<c:forEach items="${subItem.searchFileds }" var="searchFiled">
										<c:forEach items="${searchFiled.subField }" var="sub" varStatus="s4">
											<dd>
												<a href="javascript:void(0);" onclick="conditions('${searchItem.searchLabel }=${sub.fieldValue }', '${sub.fieldName }','${searchFiled.searchItem.id }');">${sub.fieldName}</a>
											</dd>
										</c:forEach>
									</c:forEach>
								</c:forEach>
							</c:forEach>
						</dl>
					</c:if>
				</div>
			</c:forEach>
		</c:forEach>
	</div>
	<div id="conditions">
		<label style="color: gray;">本次找房条件：</label><span id="conditionSpan" value=""></span><br />
		<input type="button" value="more" onclick="showHiddenItems();" id="showOrHidden">
	</div>
	<span id="conditionSpan" style="display:none;" value=""></span>
</div>
</div>