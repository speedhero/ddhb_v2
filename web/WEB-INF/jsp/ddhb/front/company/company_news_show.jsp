<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="header"></div>
<div class="about_main"> 
	<%@include file="/WEB-INF/jsp/ddhb/front/company/company_common_menu.jsp" %>
	<!--右侧开始-->
        <div class="cp_rt">
        	<div class="licn"><a onclick='window.location.href="${globalUrl}welcome.show?actionMethod=welcome"' style="cursor: pointer;">首页</a>&nbsp;>&nbsp;新闻动态</div>
        	<div class="cont_news">
			<c:forEach var="item1" items="${typeList}" varStatus="status">
        	<c:set var="countItem" value="0"></c:set>
            <div id="title${item1.id}" class="<c:if test="${status.index != 0 }">none_ip</c:if>">
            	<div  class="news_tt">
            	<span><a href="javascript:void(0);" onclick="showMore(${item1.id});">查看更多</a></span>
            	<b>
            	<a href="javascript:void(0);" class="<c:if test="${status.index != 0 }">yc_a</c:if> titleIp <c:if test="${status.index == 0 }">one</c:if>">公司新闻</a>
            	<a href="javascript:void(0);" class="<c:if test="${status.index == 0 }">yc_a</c:if> titleIp <c:if test="${status.index != 0 }">one</c:if>">行业新闻</a>
            	</b>
            	</div>
                <div class="news_ls">
                	<ul>
                		<c:forEach var="item2" items="${pageBean.dataList}">
                		<c:if test="${countItem<5 }">
                		<c:if test="${item2.newsType.id == item1.id}">
                		<c:set var="countItem" value="${countItem+1}"></c:set>
                    	<li><span><c:out value="${fn:substring(item2.publishTime, 0, 10)}"/></span><a href="javascript:void(0);" onclick="openDetail(${item2.id});">
                    	<c:choose>  
							    <c:when test="${fn:length(item2.newsTitle) > 25}">  
							        <c:out value="${fn:substring(item2.newsTitle, 0, 25)}......" />  
							    </c:when>  
							   <c:otherwise>  
							      <c:out value="${item2.newsTitle}" />  
							    </c:otherwise>  
							</c:choose> 
							</a>
							</li>
                    	</c:if>
                    	</c:if>
                    	</c:forEach>
                    </ul>
                </div>
                <div class="mob_mor"><a href="javascript:void(0);" onclick="showMore(${item1.id});">更多</a></div>
            </div>
            
            </c:forEach>
            </div>
            
        </div>
    	<!--右侧结束-->
	</div>
	<div style="clear: both; height: 0px;"></div>
</div>
<script type="text/javascript">
function showMore(typeId){
	window.location.href = '${globalUrl}company.show?actionMethod=showNewsTypeList&typeId=' + typeId;
}

function openDetail(news_id){
	window.location.href = '${globalUrl}company.show?actionMethod=showNewsDetail&news_id=' + news_id;
}
</script>