<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="header"></div>
<div class="about_main"> 
	<%@include file="/WEB-INF/jsp/hshb/front/company/company_common_menu.jsp" %>
	<!--右侧开始-->
        <div class="cp_rt">
        	<div class="licn"><a href="${globalUrl}" style="cursor: pointer;">首页</a>&nbsp;>&nbsp;新闻动态&nbsp;>${newsType.typeName}</div>
            <div class="cont_news">
            	<h2>${news.newsTitle}</h2>
                <div class="fu"><span>作者：${news.newsFrom }</span><span>发表时间：<fmt:formatDate value="${news.publishTime}" pattern="yyyy-MM-dd"/> </span><span>浏览量：${news.browsed}</span></div>
                <div class="nr">${news.newsContent}</div>
            </div>
        </div>
    	<!--右侧结束-->
	<div style="clear: both; height: 0px;"></div>
</div>
