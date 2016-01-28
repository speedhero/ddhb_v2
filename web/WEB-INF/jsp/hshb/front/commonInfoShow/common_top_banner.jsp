<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${sourceflag ne null && sourceflag eq 'graduationSeason' }">
<a href="${globalUrl}rent.show?actionMethod=graduationSeason" target="_czf"><img alt="" src="${pictureHost}/activityManage/banner_rent_graduationSeason.jpg" style="1000px; height: 80px; margin-top: 5px;"/></a>
</c:if>