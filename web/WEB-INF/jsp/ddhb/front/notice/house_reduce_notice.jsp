<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/css.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/dlzc.css">
<style>
.tcc_zp {
    margin: 0 !important;
    position: inherit !important;
}
.yyfw_ls li{
padding-left:93px;
width:75% !important;
}
.reduceTj{
float:left;
}
@media screen and (max-width: 470px) {
.yyfw_ls li{
padding-left:0;
width:97% !important;
}
.reduceTj{
float:none;
}
}
</style>
<script type="text/javascript">
function postmyform(formName, resultArea) {
	var dataString = $("#" + formName).serialize();
	actionUrl = $("#" + formName)[0].action;
	
	postBymyURL(actionUrl, dataString, "POST","html",
		function(data) {
			if(data.indexOf('手机未验证') >= 0) {
				validPhoneBox('您还没有绑定手机号码，是否前去绑定手机号码?',"art.dialog({id: 'phoneValidDialog'}).close();window.location.href='${globalUrl}usercenter.do?actionMethod=memberInformatinManage&isCutPage=false'","验证手机")/* window.location.href='usercenter.do?actionMethod=memberInformatinManage&isCutPage=false' */
				art.dialog({id: 'reduceNotice'}).close();
				return;
			}
			if(data.indexOf('已经订阅') >= 0){
				$("#errordiv").text('您已经订阅过该房源，请勿重复订阅！');
				return;
			}
			if("${housetype}" == 1){
				window.location.href="${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=${searchno }&brokerId=${brokerId}";
			}else {
				window.location.href="${globalUrl}rent.show?actionMethod=houseRentDetail&houseNo=${searchno }&brokerId=${brokerId}";
			}
		}
	);
}

function validPhoneBox(content,opeartion,opeartionContent){
	var dataContent = document.getElementById("phoneValidDialog");
	art.dialog({
		id:'phoneValidDialog',
 		title: "手机验证",
 		content: dataContent,
 		lock: true,
 		drag: false,
    resize: false,
    max: false,
    min: false,
  	zIndex: 99999
	});
	$("#phoneValidDialogContent").text(content);
	$("#operationBtn").attr("onclick",opeartion);
	$("#operationBtn").text(opeartionContent);
}
</script>
<form:form id="houseReduceNotice" modelAttribute="houseReduceNotice"
	name="houseReduceNotice" method="POST" action="${globalUrl}notice.show?actionMethod=addNoticeWithOut&houseId=${houseId }&brokerId=${brokerId }&houseNo=${searchno }&housetype=${housetype }" >
	<div class="tcc_zp" >
		<div class="yyfw_con">
			<div class="yyfw_bg"><strong><c:out value="${communityName }"></c:out></strong><span><fmt:formatNumber pattern="#" value="${houseArea }"/>  平米</span><span>${houseShi }室${houseTing }厅${houseWei }卫</span><span><c:if test="${housetype == 1}"><b><fmt:formatNumber value="${housePrice/10000 }"/></b>万元</c:if><c:if test="${housetype == 2}"><b><fmt:formatNumber value="${housePrice }"/></b>元/月</c:if></span></div>
			<div class="yyfw_ls">
				<ul>
				<c:if test="${LoginMember == null}">
					<li>您还未登录，请登录后再进行操作</li>
				</c:if>
				<c:if test="${LoginMember != null}">
            <li><span><input type="radio" checked="checked" name="typeFlag" value="0" style="width:auto;">邮箱：</span>
                <input type="text" name="noticeEmail" disabled="disabled" id="noticeEmail" value="${LoginMember.emailAddress }"  style="color: #CCCCCC;"/>
                <p style="float:left;">[必须登录]</p>
            </li>
            <li><span><input type="radio" name="typeFlag" value="1" style="width:auto;">手机号码：</span>
                <input type="text" name="noticePhone" disabled="disabled" id="noticePhone" value="${LoginMember.mobilephone }"   style="color: #CCCCCC;"/>
                <p>[必须为绑定手机号]</p>
                <input type="hidden" name="mobilephoneValidatedFlag" value="${LoginMember.mobilephoneValidatedFlag }"/>
            </li>
        </c:if>
        </ul>
			<div class="clear"></div>
			<div class="yyfw_an">
				<div id="errordiv" style="font-size: 12px; color: red;"></div>
		        <c:if test="${LoginMember != null}">
		       	<div style="margin : 0 auto;"> <a class="reduceTj tj" href="javascript:void(0);" onclick='trimForm("houseReduceNotice");postmyform("houseReduceNotice", "errordiv");'>确定订阅</a></div>
		        </c:if>
       		</div>
       		<div class="yyfw_a">
       		<a onclick="art.dialog.list['reduceNotice'].close();daikan();">预约服务</a>
	        <a onclick="art.dialog.list['reduceNotice'].close();<c:if test="${housetype == 1 }">consignment();">我要买房</c:if><c:if test="${housetype == 2 }">consignmentRent();">我要租房</c:if></a>
	        <a style="background-color:#d83939">降价通知</a>
	        <a onclick="art.dialog.list['reduceNotice'].close();newsNotice();">订阅市场动态</a>
        </div>
			</div>
		</div>
	</div>
</form:form>
