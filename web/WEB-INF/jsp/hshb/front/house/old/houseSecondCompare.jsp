<%@page import="com.huatek.hbwebsite.util.FrontSystemSettingUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<title>二手房对比 - ${title } </title>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/style.css"/>
<script type="text/javascript" src="${globalUrl}js/jquery.artDialog.source.js"></script>
<script type="text/javascript" src="${globalUrl}js/artDialog.iframeTools.source.js"></script>
<script type="text/javascript" src="${globalUrl}js/commonGround/notice.js"></script>
<link type="text/css" rel="stylesheet" href="${globalUrl}js/skins/gray.css">

<script type="text/javascript">
	$(document).ready(function() {
		$("[deleteImg]").click(function(){
			var imgId = $(this).attr("imgId");
			$("[tdClass='" + imgId + "']").each(function(){
				$(this).html("<span style='color:#FFFFFF;'>_</span>");
			});
		});
	});
	function turnPage(borkerId, houseId, houseNo){
		window.open("${globalUrl}houseSecond.show?actionMethod=houseSecondDetail&houseNo="+houseNo+"&brokerId="+borkerId);
	}
	//预约带看
	function daikan(borkerId, houseId, searchno,area,ting,shi,wei,conmmunintyName,price){
		var dataString={
				"brokerId": borkerId,
				"searchno": searchno,
				"houseId": houseId,
				"housetype":'1',
				"houseArea": area,
				"houseTing": ting,
				"houseShi": shi,
				"houseWei": wei,
				"communityName": conmmunintyName,
				"housePrice":price
		};
		var actionUrl = "${globalUrl}houseSecond.show?actionMethod=bespeak";
		var id = 'daikan';
		var title= "&nbsp;&nbsp;预约服务";
		_services(actionUrl, id, title, dataString);
	}
</script>
 <div class="Location"><a onclick='window.location.href="${globalUrl}welcome.show?actionMethod=welcome"' style="cursor: pointer;">首页</a> ><a onclick='window.location.href="${globalUrl}houseSecond.show?actionMethod=dimquery&type=1"' style="cursor: pointer;">二手房</a> >对比</div>
<div class="db_zzy">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<th scope="col" width="16%" valign="middle" class="td_1">&nbsp;</th>
			<c:forEach var="house" items="${houseSecondList}" varStatus="index">
				<th scope="col" width="21%" tdClass="${index.index }">
					<div class="dbz_name">
						<a onclick="turnPage('${house.broker.erpId}', '${house.erpId }', '${house.houseNo }')">${(houseAppraise != null) ? houseAppraise.title : house.title}</a>
					</div>
					<div class="db_an">
						<a href="javascript:void(0);" imgId="${index.index }" deleteImg>删除</a>
					</div>
				</th>
			</c:forEach>
			<c:if test="${houseSecondList.size() < 4}">
				<c:forEach begin="0" end="${3 - houseSecondList.size() }" var="i" varStatus="index">
					<th scope="col" width="21%"></th>
				</c:forEach>
			</c:if>
		</tr>
		<tr>
			<td valign="middle" class="td_1">基本信息</td>
			<c:forEach var="house" items="${houseSecondList}" varStatus="index">
				<td tdClass="${index.index }">
					<div class="pt">
						<a href="#"><img src="${pictureHost }${house.pictureUrl }"
							alt=""
							onclick="turnPage('${house.broker.erpId}', '${house.erpId }', '${house.houseNo }')" /></a>
					</div>
					<div class="pt_name">
						<span>${house.community.communityName }</span>
					</div>
				</td>
			</c:forEach>
			<c:if test="${houseSecondList.size() < 4}">
				<c:forEach begin="0" end="${3 - houseSecondList.size() }" var="i" varStatus="index">
					<td tdClass="${houseSecondList.size() + index.index  }"></td>
				</c:forEach>
			</c:if>
		</tr>
		<tr>
			<td valign="middle" class="td_1">房源卖点</td>
			<c:forEach var="house" items="${houseSecondList}" varStatus="index">
				<td tdClass="${index.index }">
					<div class="db_color">
						<c:forEach items="${tagList}" var="tags">
							<c:forEach items="${house.tagIdList}" var="tagId">
								<c:if test="${tags.erpId == tagId}">
									<span style="color:${tags.fontColor};background-color:${tags.tagColor};">${tags.tagName}</span>
								</c:if>
							</c:forEach>
						</c:forEach>
					</div>
				</td>
			</c:forEach>
			<c:if test="${houseSecondList.size() < 4}">
				<c:forEach begin="0" end="${3 - houseSecondList.size() }" var="i" varStatus="index">
					<td tdClass="${houseSecondList.size() + index.index  }"></td>
				</c:forEach>
			</c:if>
		</tr>
		<tr>
			<td valign="middle" class="td_1">售价</td>
			<c:forEach var="house" items="${houseSecondList}" varStatus="index">
				<td tdClass="${index.index }"><b><fmt:formatNumber value="${house.price/10000 }" pattern="#"></fmt:formatNumber></span></b> 万元</td>
			</c:forEach>
			<c:if test="${houseSecondList.size() < 4}">
				<c:forEach begin="0" end="${3 - houseSecondList.size() }" var="i" varStatus="index">
					<td tdClass="${houseSecondList.size() + index.index  }"></td>
				</c:forEach>
			</c:if>
		</tr>
		<tr>
			<td valign="middle" class="td_1">面积</td>
			<c:forEach var="house" items="${houseSecondList}" varStatus="index">
				<td tdClass="${index.index }"><b><fmt:formatNumber value="${house.area }" pattern="#"></fmt:formatNumber></b> 平方米</td>
			</c:forEach>
			<c:if test="${houseSecondList.size() < 4}">
				<c:forEach begin="0" end="${3 - houseSecondList.size() }" var="i" varStatus="index">
					<td tdClass="${houseSecondList.size() + index.index  }"></td>
				</c:forEach>
			</c:if>
		</tr>
		<tr>
			<td valign="middle" class="td_1">单价</td>
			<c:forEach var="house" items="${houseSecondList}" varStatus="index">
				<td tdClass="${index.index }"><b><fmt:formatNumber value="${house.unitPrice }" pattern="#"></fmt:formatNumber></b> 元/平方米</td>
			</c:forEach>
			<c:if test="${houseSecondList.size() < 4}">
				<c:forEach begin="0" end="${3 - houseSecondList.size() }" var="i" varStatus="index">
					<td tdClass="${houseSecondList.size() + index.index  }"></td>
				</c:forEach>
			</c:if>
		</tr>
		<tr>
			<td valign="middle" class="td_1">单价浮动</td>
			<c:forEach var="house" items="${houseSecondList}" varStatus="index">
				<td tdClass="${index.index }">近30天单价： <c:if test="${house.lastedThirtyPriceRatio ge  0}">
						<span class="s">${house.lastedThirtyPriceRatio }%</span>
					</c:if> <c:if test="${house.lastedThirtyPriceRatio lt 0}">
						<span class="x"><c:out
								value="${fn:substring(house.lastedThirtyPriceRatio, 1, 5 )}"></c:out>%</span>
					</c:if>
				</td>
			</c:forEach>
			<c:if test="${houseSecondList.size() < 4}">
				<c:forEach begin="0" end="${3 - houseSecondList.size() }" var="i" varStatus="index">
					<td tdClass="${houseSecondList.size() + index.index  }"></td>
				</c:forEach>
			</c:if>
		</tr>
		<tr>
			<td valign="middle" class="td_1">均价参照</td>
			<c:forEach var="house" items="${houseSecondList}" varStatus="index">
				<td tdClass="${index.index }">比所属小区均价： <c:if test="${house.ratioToCommunity ge  0}">
						<span class="s">${house.ratioToCommunity }%</span>
					</c:if> <c:if test="${house.ratioToCommunity lt  0}">
						<span class="x"><c:out
								value="${fn:substring(house.ratioToCommunity, 1, 5 )}"></c:out>%</span>
					</c:if>
				</td>
			</c:forEach>
			<c:if test="${houseSecondList.size() < 4}">
				<c:forEach begin="0" end="${3 - houseSecondList.size() }" var="i" varStatus="index">
					<td tdClass="${houseSecondList.size() + index.index  }"></td>
				</c:forEach>
			</c:if>
		</tr>
		<tr>
			<td valign="middle" class="td_1">贷款助手</td>
			<c:forEach var="house" items="${houseSecondList}" varStatus="index">
				<td tdClass="${index.index }">30年 8成商贷 <b><fmt:formatNumber
							value="${house.loanAssets }" pattern="##"></fmt:formatNumber></b>元/月
				</td>
			</c:forEach>
			<c:if test="${houseSecondList.size() < 4}">
				<c:forEach begin="0" end="${3 - houseSecondList.size() }" var="i" varStatus="index">
					<td tdClass="${houseSecondList.size() + index.index  }"></td>
				</c:forEach>
			</c:if>
		</tr>
		<tr>
			<td valign="middle" class="td_1">户型</td>
			<c:forEach var="house" items="${houseSecondList}" varStatus="index">
				<td tdClass="${index.index }"><b>${house.shi }</b>室 <b>${house.ting }</b>厅
					<b>${house.wei }</b>卫</td>
			</c:forEach>
			<c:if test="${houseSecondList.size() < 4}">
				<c:forEach begin="0" end="${3 - houseSecondList.size() }" var="i" varStatus="index">
					<td tdClass="${houseSecondList.size() + index.index  }"></td>
				</c:forEach>
			</c:if>
		</tr>
		<tr>
			<td valign="middle" class="td_1">楼层</td>
			<c:forEach var="house" items="${houseSecondList}" varStatus="index">
				<td tdClass="${index.index }"><b>${house.storyCount}</b>
				<c:if test="${house.vervicalLocation == 1}">层下部</c:if>
					<c:if test="${house.vervicalLocation == 2}">层中部</c:if>
					<c:if test="${house.vervicalLocation == 3}">层上部</c:if></td>
			</c:forEach>
			<c:if test="${houseSecondList.size() < 4}">
				<c:forEach begin="0" end="${3 - houseSecondList.size() }" var="i" varStatus="index">
					<td tdClass="${houseSecondList.size() + index.index  }"></td>
				</c:forEach>
			</c:if>
		</tr>
		<tr>
			<td valign="middle" class="td_1">朝向</td>
			<c:forEach var="house" items="${houseSecondList}" varStatus="index">
				<td tdClass="${index.index }">${house.orientations.orientationName}</td>
			</c:forEach>
			<c:if test="${houseSecondList.size() < 4}">
				<c:forEach begin="0" end="${3 - houseSecondList.size() }" var="i" varStatus="index">
					<td tdClass="${houseSecondList.size() + index.index  }"></td>
				</c:forEach>
			</c:if>
		</tr>
		<tr>
			<td valign="middle" class="td_1">带看量</td>
			<c:forEach var="house" items="${houseSecondList}" varStatus="index">
				<td tdClass="${index.index }">${house.daikan }</td>
			</c:forEach>
			<c:if test="${houseSecondList.size() < 4}">
				<c:forEach begin="0" end="${3 - houseSecondList.size() }" var="i" varStatus="index">
					<td tdClass="${houseSecondList.size() + index.index  }"></td>
				</c:forEach>
			</c:if>
		</tr>
		<tr>
			<td valign="middle" class="td_1">浏览量</td>
			<c:forEach var="browse" items="${browseList}" varStatus="index">
				<td tdClass="${index.index }">${browse }</td>
			</c:forEach>
			<c:if test="${houseSecondList.size() < 4}">
				<c:forEach begin="0" end="${3 - houseSecondList.size() }" var="i" varStatus="index">
					<td tdClass="${houseSecondList.size() + index.index  }"></td>
				</c:forEach>
			</c:if>
		</tr>
		<tr>
			<td valign="middle" class="td_1">评价</td>
			<c:forEach var="house" items="${houseSecondList}" varStatus="index">
				<td tdClass="${index.index }">${house.appCount }</td>
			</c:forEach>
			<c:if test="${houseSecondList.size() < 4}">
				<c:forEach begin="0" end="${3 - houseSecondList.size() }" var="i"
					varStatus="index">
					<td tdClass="${houseSecondList.size() + index.index  }"></td>
				</c:forEach>
			</c:if>
		</tr>
		<tr>
			<td valign="middle" class="td_1"></td>
			<c:forEach var="house" items="${houseSecondList}" varStatus="index">
				<td tdClass="${index.index }">
					<div class="db_yy">
						<a href="javascript:void(0);" onclick="daikan('${house.broker.erpId}', '${house.erpId }', '${house.houseNo }','${house.area }','${house.ting }','${house.shi }','${house.wei }','${house.community.communityName }','${house.price }')">预约服务</a>
					</div>
				</td>
			</c:forEach>
			<c:if test="${houseSecondList.size() < 4}">
				<c:forEach begin="0" end="${3 - houseSecondList.size() }" var="i" varStatus="index">
					<td tdClass="${houseSecondList.size() + index.index  }"></td>
				</c:forEach>
			</c:if>
		</tr>
	</table>
</div>