<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="h" uri="/WEB-INF/tld/hshb.tld"%>
<%@page import="com.github.pagehelper.PageInfo,cn.hshb.web.biz.mybatis.model.*" %>
<%
int pageNum = 6;			//默认显示页码数
int startIdx = 1;
int endIdx   = pageNum + 1;

//PageInfo<HouseSecondHandHouse> pageBean = (PageInfo<HouseSecondHandHouse>)request.getAttribute("pageBean");
PageInfo pageBean = (PageInfo)request.getAttribute("pageBean");
startIdx = pageBean.getPageNum()- (pageNum / 2);
if(startIdx>=pageBean.getPages())
	startIdx = pageBean.getPages() - pageNum + 1;
if(startIdx<1) startIdx = 1;

endIdx = startIdx + pageNum;
if(endIdx>=pageBean.getPages()) 
	endIdx = pageBean.getPages();

if(startIdx > 1){
	//上pageNum条
	int prevPage = pageBean.getPageNum() - pageNum;
	if(prevPage<1) prevPage = 1;
	request.setAttribute("prevPage", prevPage);
}

if (endIdx < pageBean.getPages()) {
	//下pageNum条
	int nextPage = pageBean.getPageNum() + pageNum;
	if(nextPage >= pageBean.getPages()) nextPage = pageBean.getPages();
	request.setAttribute("nextPage", nextPage);
}

request.setAttribute("startIdx", startIdx);
request.setAttribute("endIdx", endIdx);
%>
<div class="page"> 
 <input type="hidden" name="currentPage" value="${pageBean.pageNum}" />
 <input type="hidden" name="pageSize" value="${pageBean.pageSize}" />
 <div class="ddhbeachNum">
  <ul>
<c:if test="${startIdx>1}">
	<c:if test="${pageBean.pageNum > 1}">
	<a href="<h:surl p="n${pageBean.pageNum - 1}"/>" class="zy">&lt;</a>
	</c:if>
	<a href="<h:surl p="n${prevPage}"/>">...</a>
</c:if>
<c:forEach var="ii" begin="${startIdx}" end="${endIdx}">
   <a href="<h:surl p="n${ii}"/>"${ii==pageBean.pageNum ? " class=\"one\"" : ""}" >${ii}</a>
</c:forEach>
<c:if test="${endIdx < pageBean.pages}">
   <a href="<h:surl p="n${nextPage}"/>">...</a>
	<c:if test="${pageBean.pageNum < pageBean.pages}">
	<a href="<h:surl p="n${pageBean.pageNum + 1}"/>" class="zy">&gt;</a>
	</c:if>
</c:if>
  </ul>
 </div>
</div>