<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style type="text/css">
#brokersbody { height: auto; border: none; }
#borkerDiv { height: 300px; }
</style>

<form name="expertForm">
<div id="brokersbody">
	<c:forEach var="expert" items="${pageBean.dataList }">
		<div id="borkerDiv" style="cursor: pointer;"
			onclick="window.open('${globalUrl}broker.show?actionMethod=brokerDetail&brokerId=${expert.broker.id}&housetype=1')">
			<div id="borkerInfo">
				<div class="brokerTitle">需要帮助请联系我</div>
				<div class="brokerPic"><img alt="" src="${globalUrl}${expert.broker.photograph}"></div>
				<div class="brokerName">
					<div class="brokerInfo">${expert.broker.store.storeName} - ${broker.store.telephoneNo}</div>
					<div class="brokerInfo">${expert.broker.bname}</div>
					<div class="brokerInfo">${expert.broker.telephone }</div>
					<div class="brokerInfoImage">
						<img alt="" src="${globalUrl}images/img/u67_normal.png"> <img alt="" src="${globalUrl}images/img/u69_normal.png">
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<div style="clear: both;"></div>
	<div class="cutpage" style="margin-top: 0px;">
			<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false"
				formName="expertForm" offerPageSize="20,50,100" isExistForm="true"
				queryFunction="nonHouseChangePages('${globalUrl}community.show?actionMethod=brokerPage&communityId=${communityId}', null, 'changelist')" />
	</div>
</div>
</form>
<%--
<script type="text/javascript">
function nonHouseChangePages() {
	var currvalue = document.getElementsByName("currentPage")[0].value;
	/* setJSONValue("currentPage",currvalue); */
	postDataByURL2('${globalUrl}community.show?actionMethod=brokerPage&communityId=${communityId}', 'currentPage=' + currvalue, "changelist");
}
</script>
 --%>
