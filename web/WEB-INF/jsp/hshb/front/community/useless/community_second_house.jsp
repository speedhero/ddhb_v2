<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style type="text/css">
#communitySecondHouseDiv { width: 100%; height: auto; }
#communitySecondHouseSearch { width: 100%; height: 300px; border: 1px solid blue; }
font { font-size:  0.8em; }
.houseList{width: 1050px;; margin:0 auto; text-align:left; border:1px solid #ccc; padding-left:2em 0; padding-bottom: 1.5em;}
.houseList .houseItem{width:20%;border:1px solid #ccc;height:200px;display:inline-block;*zoom:1;*display:inline;text-align:left;}
table{ border-collapse:collapse;border-spacing:0 }
table th{ background:#eeeeee; }
table th, table td{ border:solid 1px #ddd;text-align:center;padding:10px 0;font-size:12px;line-height:20px; }
a { text-decoration: none;}
.listitem {
	/* border: 1px solid black; */
	height: 350px;
	width: 192px;
	float: left;
	margin: 5px 5px 0px 5px; /*up right down left*/
}
.housephoto {
	position:relative;
	width: 100%;
	height: 170px;
	background-color: #E4E4E4;
}
.housephotorightup {
	position:absolute;
	height: 48px;
	width: 62px;
	right: 5px;
	background-color: white;
	text-align: center;
}
.housephotorightdown {
	position: absolute;
	right: 5px;
	bottom: 5px;
}

.cutpage {
	width: 100%;
}
</style>

<script type="text/javascript" >
$(document).ready(function() {
	/*
	var idStr = $.cookie("lastSelected");
	if(idStr == "imgShape") {
		$('#image_list').css("display", "block");
		$('#list_list').css("display", "none");
	} else {
		$('#list_list').css("display", "block");
		$('#image_list').css("display", "none");
	}
	*/
});
function saveCookies(_showTypeFlag) {
	if(searchMap){
		searchMap.put("ispage", "1");
	}
	/*
	if (StringId == "dataShape") {
		$('#list_list').css("display", "block");
		$('#image_list').css("display", "none");
	} else {
		$('#image_list').css("display", "block");
		$('#list_list').css("display", "none");
	}*/
	switchShowType(_showTypeFlag);
	
	$.cookie('lastSelected', _showTypeFlag, { expires : 365 }, { path : "/" }); // 存储 cookie , expires: 设定时间天数参数， path：设置cookies存储路径参数
}

function changelist(stringlist){
	if(stringlist == "dataShape") {
		$('#list_list').css("display", "block");
		$('#image_list').css("display", "none");
	} else {
		$('#image_list').css("display", "block");
		$('#list_list').css("display", "none");
	}
}
</script>
<div id="communitySecondHouseDiv">
	<div id="communitySecondHouseSearch">
		<h1>这里是搜索框</h1>
	</div>
	<div>
		<h3>共有 ${pageBean.totalRows} 套符合要求的房子
		<span style="float: right;  margin-right: 20px; ">
		<input id="imgShape" type="button" value="大图模式" onclick='changelist("image_list");saveCookies("imgShape");'/>
		<input id="dataShape" type="button" value="列表模式" onclick='changelist("list_list");saveCookies("dataShape");'/>
		</span>
		</h3>
	</div>
	<!-- 列表显示 -->
	<div class="houseItemDate" id="list_list">
		<table border="1">
			<thead>
				<tr>
					<th>加入对比</th>
					<th>查询编号</th>
					<th>标题</th>
					<th>小区/坐落</th>
					<th>房源卖点</th>
					<th>居室</th>
					<th>面积<br/>平米</th>
					<th>单价<br/>元/平米</th>
					<th>售价<br/>万元</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="house" items="${pageBean.dataList}">
				<tr>
					<td planid="${house.houseNo}" planname="${house.title}">
						<input type="checkbox" id="planid${house.houseNo}" onclick="actionplan(this)" name="compareplan" value="${house.houseNo}"/>
					</td>
					<td onclick="window.location.href='${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=${house.houseNo}&brokerId=${house.broker.id}'">${house.houseNo}</td>
					<td>${house.title}</td>
					<td>${house.community.communityName}</td>
					<td>
						<c:forEach var="tagItem" items="${tagList}">
							<c:forEach var="tagIds" items="${house.tagIdList}">
							<c:if test="${tagItem.id == tagIds}">
								${tagItem.tagName};
							</c:if>
							</c:forEach>
						</c:forEach>
					</td> 
					<td>${house.shi}<font>室</font>${house.ting}<font>厅</font>${house.wei}<font>卫</font></td>
					<td>${house.area}<font id="fontSize">平米</font></td>
					<td>${house.unitPrice}<font size="0.5em">元/平米</font></td>
					<td>${house.price}万元</td>
				</tr>
			</c:forEach>
			</tbody>
			</table>
		</div>
	<!-- 图片显示 -->
	<div id="image_list">
		<c:forEach var="house" items="${pageBean.dataList}">
			<div class="listitem" >
				<div class="housephoto">
					<div class="housephotorightup" onclick="window.location.href='${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo=${house.houseNo}&brokerId=${house.broker.id}'">
						<div class="font1" style="font-size: 20px; margin-top: 5px;">
							<fmt:formatNumber value="${house.price}" pattern="#"/>
						</div>
						<div class="font1" style="font-size: 12px">万元</div>
					</div>
					<div class="housephotorightdown">
						<c:if test="${LoginMember == null }">
							<a style="color: black; font-size: 22px" href="${globalUrl}login.show?actionMethod=loginCheck">☆</a>
						</c:if>
						<c:if test="${LoginMember != null }">
							<c:if test="${house.collectId != null}">
								<a class="collectionButton" id="collect${house.id}" style="color: black; font-size: 22px" collId="${house.collectId}"
								hosueobjectId="${house.objectId}" houseprice="${house.price}" isCollect="0" href="javascript:viod(0)">★</a>
							</c:if>
							<c:if test="${house.collectId == null}">
								<a class="collectionButton" id="collect${house.id}" style="color: black; font-size: 22px" 
								hosueobjectId="${house.objectId}" houseprice="${house.price}" href="javascript:viod(0)"
								isCollect="1">☆</a>
							</c:if>
						</c:if>
					</div>
				</div>
				<div class="font1" style="margin-left: 3px;">
					<div style="font-size: 17px; margin: 5px 0px 5px 0px;"><fmt:formatNumber value="${house.unitPrice}" pattern="#"/><font  style="font-size: 8px;"> 元/平米</font></div>
					<div style="font-size: 17px; font-weight: 600; height: 50px;">${house.title }</div>
					<div style="font-size: 16px;">71<font  style="font-size: 8px;"> 平米</font></div>
					<div style="font-size: 16px;">${house.shi}<font  style="font-size: 8px;"> 室</font>
						${house.ting}<font  style="font-size: 8px;"> 厅</font>
						${house.wei}<font  style="font-size: 8px;"> 卫</font>
					</div>
					<div>荣华里</div>
					<div>拱墅 - 拱振桥</div>
				</div>
			</div>
		</c:forEach>
		<div style="clear: both;"></div>
	</div>
	<div class="cutpage"><huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="true" formName="newsBean" offerPageSize="20,50,100" isExistForm="true" /></div>
</div>
