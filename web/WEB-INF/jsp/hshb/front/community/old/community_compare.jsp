<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" type="text/css" href="${globalUrl}css/css.css"/>
<title>小区对比 - ${title } </title>
<script type="text/javascript">
$(document).ready(function() {
	$("[deleteImg]").click(function(){
		var imgId = $(this).attr("imgId");
		$("[tdClass='" + imgId + "']").each(function(){
			$(this).html("<span style='color:#FFFFFF;'>_</span>");
		});
	});
});
function turnPage(id){
	window.open("${globalUrl}community.show?actionMethod=communityDetail&id="+id);
}
</script>
<div class="Location"><a onclick='window.location.href="${globalUrl}welcome.show?actionMethod=welcome"' style="cursor: pointer;">首页</a> ><a onclick='window.location.href="${globalUrl}community.show?actionMethod=dimquery&type=1"' style="cursor: pointer;">小区</a> >对比</div>
<div class="db_zzy">
        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <th scope="col" width="16%" valign="middle" class="td_1">&nbsp;</th>
                <c:forEach var="community" items="${communityList}" varStatus="index"> 
					 <th scope="col" width="21%" tdClass="${index.index }">
	                	<div class="dbz_name"><a onclick="window.open('${globalUrl}community.show?actionMethod=communityDetail&id=${community.erpId }')">${community.communityName}</a></div>
	                	<div class="db_an"><a href="javascript:void(0);" imgId="${index.index }" deleteImg>删除 </a></div>
	                </th>
				</c:forEach>
				<c:if test="${communityList.size() < 4}">
					<c:forEach begin="0" end="${3 - communityList.size() }" var="i" varStatus="index">
						<th scope="col" width="21%">
		                </th>
					</c:forEach>
				</c:if>
              </tr>
              <tr>
                <td valign="middle" class="td_1">基本信息</td>
                <c:forEach var="community" items="${communityList}" varStatus="index"> 
                		<td tdClass="${index.index }">
		                	<div class="pt"><a href="#"><img src="${pictureHost }${community.communityUrl }" alt="" onclick="turnPage(${community.erpId })" /></a></div>
		                    <div class="pt_name"><span>${community.communityName }</span></div>
		                </td>
					</c:forEach>
					<c:if test="${communityList.size() < 4}">
						<c:forEach begin="0" end="${3 - communityList.size() }" var="i" varStatus="index">
							<td tdClass="${communityList.size() + index.index  }"></td>
						</c:forEach>
					</c:if>
              </tr>
              <tr>
                <td valign="middle" class="td_1">均价</td>
                <c:forEach var="community" items="${communityList}"  varStatus="index">
                	<td tdClass="${index.index }"><b><fmt:formatNumber value="${community.currentSHAvePrice }" pattern="#"></fmt:formatNumber></span></b> 元/平米</td>
				</c:forEach>
				<c:if test="${communityList.size() < 4}">
					<c:forEach begin="0" end="${3 - communityList.size() }" var="i" varStatus="index">
						<td tdClass="${communityList.size() + index.index  }"></td>
					</c:forEach>
				</c:if>
              </tr>
              <tr>
                <td valign="middle" class="td_1">面积</td>
                <c:forEach var="community" items="${communityList}" varStatus="index">
               		<td tdClass="${index.index }"><b><fmt:formatNumber value="${community.communityArea }" pattern="#"></fmt:formatNumber></b> 平方米</td>
				</c:forEach>
				<c:if test="${communityList.size() < 4}">
					<c:forEach begin="0" end="${3 - communityList.size() }" var="i" varStatus="index">
						<td tdClass="${communityList.size() + index.index  }"></td>
					</c:forEach>
				</c:if>
              </tr>
              <tr>
                <td valign="middle" class="td_1">开发商</td>
                <c:forEach var="community" items="${communityList}" varStatus="index">
                	<td tdClass="${index.index }"><c:out value="${community.developer }"></c:out></td>
				</c:forEach>
				<c:if test="${communityList.size() < 4}">
					<c:forEach begin="0" end="${3 - communityList.size() }" var="i" varStatus="index">
						<td tdClass="${communityList.size() + index.index  }"></td>
					</c:forEach>
				</c:if>
              </tr>
              <tr>
                <td valign="middle" class="td_1">建成年代</td>
                <c:forEach var="community" items="${communityList}" varStatus="index">
                	<td tdClass="${index.index }"><b><fmt:formatDate value="${community.startSaleDate }" pattern="yyyy"/></span></b>年</td>
				</c:forEach>
				<c:if test="${communityList.size() < 4}">
					<c:forEach begin="0" end="${3 - communityList.size() }" var="i" varStatus="index">
						<td tdClass="${communityList.size() + index.index  }"></td>
					</c:forEach>
				</c:if>
              </tr>
              <tr>
                <td valign="middle" class="td_1">物业类型</td>
                <c:forEach var="community" items="${communityList}" varStatus="index">
                	<td tdClass="${index.index }"><c:out value="${community.propertyType.usageName }"></c:out></td>
				</c:forEach>
				<c:if test="${communityList.size() < 4}">
					<c:forEach begin="0" end="${3 - communityList.size() }" var="i" varStatus="index">
						<td tdClass="${communityList.size() + index.index  }"></td>
					</c:forEach>
				</c:if>
              </tr>
              <tr>
                <td valign="middle" class="td_1">物业公司</td>
                <c:forEach var="community" items="${communityList}" varStatus="index">
                	<td tdClass="${index.index }"><c:out value="${community.propertyManagement }"></c:out></td>
				</c:forEach>
				<c:if test="${communityList.size() < 4}">
					<c:forEach begin="0" end="${3 - communityList.size() }" var="i" varStatus="index">
						<td tdClass="${communityList.size() + index.index  }"></td>
					</c:forEach>
				</c:if>
              </tr>
              <tr>
                <td valign="middle" class="td_1">物业费</td>
                <c:forEach var="community" items="${communityList}" varStatus="index">
                	<td tdClass="${index.index }"><b><fmt:formatNumber value="${community.propertyExpense }" pattern="#.##"></fmt:formatNumber></b> 元/平米</td>
				</c:forEach>
				<c:if test="${communityList.size() < 4}">
					<c:forEach begin="0" end="${3 - communityList.size() }" var="i" varStatus="index">
						<td tdClass="${communityList.size() + index.index  }"></td>
					</c:forEach>
				</c:if>
              </tr>
              <tr>
                <td valign="middle" class="td_1">容积率</td>
                <c:forEach var="community" items="${communityList}" varStatus="index">
                	<td tdClass="${index.index }"><b><fmt:formatNumber value="${community.plotRatio }" pattern="#"></fmt:formatNumber></b></td>
				</c:forEach>
				<c:if test="${communityList.size() < 4}">
					<c:forEach begin="0" end="${3 - communityList.size() }" var="i" varStatus="index">
						<td tdClass="${communityList.size() + index.index  }"></td>
					</c:forEach>
				</c:if>
              </tr>
              <tr>
                <td valign="middle" class="td_1">绿化率</td>
                <c:forEach var="community" items="${communityList}" varStatus="index">
                	<td tdClass="${index.index }"><b><fmt:formatNumber value="${community.landScaping }" pattern="#.##"></fmt:formatNumber></b></td>
				</c:forEach>
				<c:if test="${communityList.size() < 4}">
					<c:forEach begin="0" end="${3 - communityList.size() }" var="i" varStatus="index">
						<td tdClass="${communityList.size() + index.index  }"></td>
					</c:forEach>
				</c:if>
              </tr>
              <tr>
                <td valign="middle" class="td_1">停车位</td>
                <c:forEach var="community" items="${communityList}" varStatus="index">
                	<td tdClass="${index.index }"><b><c:out value="${community.parkingCount}"></c:out></b>个</td>
				</c:forEach>
				<c:if test="${communityList.size() < 4}">
					<c:forEach begin="0" end="${3 - communityList.size() }" var="i" varStatus="index">
						<td tdClass="${communityList.size() + index.index  }"></td>
					</c:forEach>
				</c:if>
              </tr>
            </table>
        </div>