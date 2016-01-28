<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
var globalUrl = '${globalUrl}';
for(var i = 0; i < '${pageBean.totalPage}'; i++) {
	$("#cutpoints").append("<a href='javascript:postbycurrentpage("+(i+1)+")'>●</a>");
}

function postbycurrentpage(currentpage){
	var jsonStringInit = { 'id':'${community.id}', 'currentPage' : currentpage };
	if(currentpage > 0 && currentpage <= "${pageBean.totalPage}") {
		postDataByURL2('${globalUrl}community.show?actionMethod=cutbrokers',jsonStringInit,"professordiv");
	}
}
</script>
<div style="float: left; height: 100%; width: 26px; border: 1px solid blue;">
	<a href="javascript:postbycurrentpage('${pageBean.currentPage - 1}')"><</a>
</div>
<div id="brokersbody">
	<c:forEach var="broker" items="${pageBean.dataList }">
		<div id="borkerDiv" style="cursor: pointer;"
			onclick="window.open('${globalUrl}broker.show?actionMethod=brokerDetail&brokerId=${broker.id}')">
			<div id="borkerInfo">
				<div class="brokerTitle">需要帮助请联系我</div>
				<div class="brokerPic"><img alt="" src="${globalUrl}${broker.photograph}"></div>
				<div class="brokerName">
					<div class="brokerInfo">${broker.store.storeName} - ${broker.store.telephoneNo}</div>
					<div class="brokerInfo">${broker.bname}</div>
					<div class="brokerInfo">${broker.telephone }</div>
					<div class="brokerInfoImage">
						<img alt="" src="${globalUrl}images/img/u67_normal.png"> <img alt="" src="${globalUrl}images/img/u69_normal.png">
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
<div style="float: left; height: 100%; width: 26px; border: 1px solid blue;">
	<a href="javascript:postbycurrentpage('${pageBean.currentPage + 1}')">></a>
</div>
<div id="cutpoints"></div>