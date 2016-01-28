<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript" src="${globalUrl}js/select/jquery.selectReplace.js"></script>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/select/jquery.select.css">
<title>问答&攻略 - ${title } </title>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/style.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/more.css">

<style>
.cSearch .xlxx {overflow-x:hidden;}

</style>
<script type="text/javascript">
var optionSelected = "房产买卖";

$(document).ready(function(){
	$(".tit_yc").replaceAllSelect();
	$('.everyType[value="${typeId}"]').addClass("one");
	$('.fcwd_tit').find('.selectedValue').html("&nbsp;&nbsp;&nbsp;" + optionSelected);
	
	$('.fcwd_ft').find('a[hasrelative="false"]').each(function() {
		var optionvalue = $(this).attr("optionvalue");
		$(this).attr('onclick', 'getData("${globalUrl}broker.show?actionMethod=getQuestionBySubType&subTypeId='+optionvalue+'","","eachFloat");');
	});
	var subtype = "${subTypeId}";
	if(null != subtype && "" != subtype){
		$('.everyType1[value="${subTypeId}"]').addClass("one");
		$('.everyType1[value="${subTypeId}"] > div').first().css("display", "block");
	}else {
		$(".everyType1 > div").first().css("display", "block");
	}
	
	$(".everyType").click(function(){
		$(".one").first().removeClass("one");
		$(this).addClass("one");
	});
	
	$(".everyType1").click(function(){
		//使 一级菜单 房屋买卖   样式不消失
		//$(".one").removeClass("one");
		$(".everyType1").removeClass("one");
		$(this).addClass("one");
	});
});

function showicon(id) {
	$(".everyType1 > div").css("display", "none");
	$("#" + id).css("display", "block");
}

function searchQuestion(inputId){
	var subtype = $(".one").attr("value");
	var searchString = $('#'+inputId).val();
	if(searchString == "请输入关键字检索..."){
		alert("请输入关键字检索");
		return;
	}
	var json = {"searchString" : searchString, "subtype": subtype };
	getMyData("${globalUrl}broker.show?actionMethod=getSearchQuestion", json, "eachFloat");
}

function changeRelativeSelect(selecctedValue) {
	window.location.href="${globalUrl}broker.show?actionMethod=getAllQuestion&typeId="+selecctedValue;
}
</script>
<form name="questionform">
<div class="Location"><a onclick='window.location.href="${globalUrl}"' >首页</a>&gt;房产问答</div>
<div class="fcwd_main">
            <!--房产问答开始-->
            <div class="fcwd_l">
            	<div class="fcwd_tit">
	            	<div class="tit_yc">
		            	<select onchange="javascript:sortselect('${globalUrl}houseSecond.show?actionMethod=dimquery')" relativeSelectId="true">
		            		
		            		<c:forEach items="${qestionStrategyTypeList }" var="questionType">
		            	 	<script type="text/javascript">
		            			if('${typeId}' == '${questionType.erpId}') {
		            				optionSelected = "${questionType.typeName}";
		            			}
		            		</script>
		            			<option value="${questionType.erpId}" <c:if test="${typeId} == ${questionType.erpId}">selected="selected"</c:if> >
		            				${questionType.typeName}
		            			</option>
		            		</c:forEach>
		            	</select>
	            	</div>
                    <div class="xl_com">
                    	<c:forEach items="${qestionStrategyTypeList }" var="questionType">
							<a href="javascript:void(0);" onclick='window.location.href="${globalUrl}broker.show?actionMethod=getAllQuestion&typeId=${questionType.erpId }"' value="${questionType.erpId }" class="everyType">${questionType.typeName }</a>
						</c:forEach>
                    </div>
                </div>
                <div class="fcwd_ft">
                	<div class="tit_yc">
		            	<select onchange="javascript:sortselect('${globalUrl}houseSecond.show?actionMethod=dimquery')">
		            		<c:forEach items="${questionStrategySubTypeList }" var="questionStrategySubType">
		            			<option value="${questionStrategySubType.erpId }">
		            				${questionStrategySubType.subTypeName }<span> （${questionStrategySubType.countQuestion }）</span>
		            			</option>
		            		</c:forEach>
		            	</select>
	            	</div>
               		<div class="xl_com">
	               		<c:forEach items="${questionStrategySubTypeList }" var="questionStrategySubType">
							<a href="javascript:void(0);" class="everyType1" onclick='getData("${globalUrl}broker.show?actionMethod=getQuestionBySubType&subTypeId=${questionStrategySubType.erpId }","","eachFloat");' value="${questionStrategySubType.erpId }">${questionStrategySubType.subTypeName }<span> （${questionStrategySubType.countQuestion }）</span></a>
						</c:forEach>
               		</div>
                </div>
                <div class="clear"></div>
                <div id="eachFloat"> 
                <c:if test="${pageBean.totalRows eq 0}">
				<div style="width: 160px; height: 50px; text-align: center; border: 0px; vertical-align: middle; padding-top: 30px; margin: 130px auto;">
					查询不到任何相关数据</div>
				</c:if>
				<c:if test="${pageBean.totalRows gt 0}">
                <div class="fc_ls_nr">
                	<div class="fc_sc">
					  <h2>问题</h2>
					  <input type="text" id="searchString" value="请输入关键字检索..." style="float:left;" onfocus='if(value=="请输入关键字检索...") {value =""}' onblur='if(value=="") {value ="请输入关键字检索..."}'><div class="fdjClass" onclick="searchQuestion('searchString');" style="background-image: url('${globalUrl}image/fdj.gif');background: transparent;" ></div>共有信息<b>${pageBean.totalRows }</b>条</div>
                </div>
                <div class="fc_newsls">
                	<!--<h2><span><input id="searchStringPhone" value="请输入关键字检索..." onfocus='if(value=="请输入关键字检索...") {value =""}' onblur='if(value=="") {value ="请输入关键字检索..."}' type="text"><div class="fdjClassIp" onclick="searchQuestion('searchStringPhone');" style="background-image: url('${globalUrl}image/fdj_ip.gif');background: transparent;" ></div></span>问题</h2>-->
                    <ul>
                    	<c:forEach items="${pageBean.dataList }" var="question" varStatus="questionCount">
                    		<li><span><fmt:formatDate value="${question.createTime }" pattern="yyyy-MM-dd"></fmt:formatDate></span><a href="javascript:void(0);" onclick='window.location.href="${globalUrl}broker.show?actionMethod=initQuestionDetail&questionId=${question.erpId }&typeId=${question.questionType.parentType.erpId }&subTypeId=${question.questionType.erpId }"'> · ${question.title }</a></li>
                    	</c:forEach>
                    </ul>
                    <div class="page" style="margin-top: 20px;">
						<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false"
					formName="questionform" offerPageSize="20,50,100" isExistForm="true"
					queryFunction="changePages('${subTypeId }')" />
					</div>
                </div>
                </c:if>
                </div>
            </div>
            <!--房产问答结束-->
            <!--房产问答右侧开始-->
            <div class="fcwd_r">
            	<div class="wytw_an"><a href="javascript:void(0);" onclick="window.location.href='${globalUrl}broker.show?actionMethod=addQuestion&questionId=0'">我要提问</a></div>
                <div class="zs_zj">
                	<div>
                    	<span>共有房产知识</span>
                        <p><b>${questionCount }</b>条</p>
                    </div>
                	<div class="divr">
                    	<span>共有相关专家</span>
                        <p><b>${brokerCount }</b>人</p>
                    </div>
                </div>
                <div class="wc_rt_ls">
                	<h2><a href="javascript:void(0);" onclick="window.location.href='${globalUrl}broker.show?actionMethod=getAllQuestion&typeId=1'">查看更多</a>房产买卖最新</h2>
                    <ul>
                    	<c:forEach items="${qestionStrategyListFirst }" var="qestionStrategyFirst">
							<li><a href="javascript:void(0);" onclick='window.location.href="${globalUrl}broker.show?actionMethod=initQuestionDetail&questionId=${qestionStrategyFirst.erpId }&typeId=${qestionStrategyFirst.questionType.parentType.erpId }&subTypeId=${qestionStrategyFirst.questionType.erpId }"'>${qestionStrategyFirst.title }</a></li>
						</c:forEach>
                    </ul>
                </div>
                <div class="wc_rt_ls">
                	<h2><a href="javascript:void(0);" onclick="window.location.href='${globalUrl}broker.show?actionMethod=getAllQuestion&typeId=5'" >查看更多</a>房产租赁最新</h2>
                    <ul>
                    	<c:forEach items="${qestionStrategyListSecond }" var="qestionStrategyListSecond">
							<li><a href="javascript:void(0);" onclick='window.location.href="${globalUrl}broker.show?actionMethod=initQuestionDetail&questionId=${qestionStrategyListSecond.erpId }&typeId=${qestionStrategyListSecond.questionType.parentType.erpId }&subTypeId=${qestionStrategyListSecond.questionType.erpId }"'>${qestionStrategyListSecond.title }</a></li>
						</c:forEach>
                    </ul>
                </div>
				<div class="ht_rt">
                    <span><a href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=1" class="wymf">我要买房</a></span>
                    <span><a href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=2" class="wymaif">我要卖房</a></span>
                    <span><a href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=3" class="wyzf">我要租房</a></span>
                    <span><a href="${globalUrl}consignment.show?actionMethod=consignmentDetail&type=4" class="wycz">我要出租</a></span>
                </div>
            </div>
            <div style="clear:both;"></div>
            <!--房产问答右侧结束-->
        </div>
</form>

<script type="text/javascript">
function changePages(subTypeId) {
	var currvalue = document.getElementsByName("currentPage")[0].value;
	/* setJSONValue("currentPage",currvalue); */
	var searchString = "${searchString}";
	var subtype = "${subtype}";
	if("" != searchString){
		json = {"searchString" : searchString, "subtype": subtype, "currentPage": currvalue};
		getMyData("${globalUrl}broker.show?actionMethod=getSearchQuestion", json, "eachFloat");
	}else{
		postDataByURL2('${globalUrl}broker.show?actionMethod=getQuestions&subTypeId='+subTypeId,'currentPage='+currvalue,"eachFloat");
	}
}
</script>