<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!--右侧开始-->
<input type="hidden" name="typeId" value="${newsType.id}">
<div class="licn"><a href="${globalUrl}" style="cursor: pointer;">首页</a>&nbsp;>&nbsp;新闻动态&nbsp;>&nbsp;${newsType.typeName}</div>
   <div class="cont_news">
   	<div class="news_tt"><b>${newsType.typeName}</b></div>
       <div class="news_ls">
       	<ul>
       		<c:forEach var="item1" items="${pageBean.dataList}">
				<c:if test="${newsType.id == item1.newsType.id}">
           		<li><span><c:out value="${fn:substring(item1.publishTime, 0, 10)}"/></span>
           		<a href="${globalUrl}company/showNewsDetail/${item1.id}"> <c:choose>  
  				<c:when test="${fn:length(item1.newsTitle) > 25}">  
      				<c:out value="${fn:substring(item1.newsTitle, 0, 25)}......" />  
  				</c:when>  
 				<c:otherwise>  
    				<c:out value="${item1.newsTitle}" />  
  				</c:otherwise>  
				</c:choose></a></li>
           	</c:if>
           	</c:forEach>
           </ul>
       </div>
       <div class="page" style="display:block;">
			<%-- <huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false" formName="appointForm" offerPageSize="20,50,100" isExistForm="true" queryFunction="showMySelectedField('${globalUrl}company.show?actionMethod=showNewsTypeListCutPage&typeId=${newsType.id }');"/> --%>
				<c:import url="/WEB-INF/jsp/hshb/front/commonInfoShow/pager.jsp"/>
		</div>
        </div>
	<!--右侧结束-->
