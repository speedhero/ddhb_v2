<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.huatek.framework.util.Constant,com.huatek.framework.util.ErrorsManagementUtil"%>
<%@page import="javax.servlet.ServletException,com.huatek.framework.util.Util,com.huatek.framework.exception.BusinessRuntimeException,com.huatek.framework.security.ThreadLocalClient" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page import="javax.servlet.http.HttpServletRequest" %>
<%
Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
if(ThreadLocalClient.get()==null){//非控制类的，不处理
	return;
}
if (Util.isAjaxRequest(request)){
	response.setStatus(Constant.VALIDATE_ERROR_CODE);
	if(exception==null){%>
		<%=ThreadLocalClient.get().getRequestURL()%><spring:message code="error.url.not.found"/>
	<%}else{
		out.println("Not found "+exception.getMessage().replaceAll("\"","\'"));
	}
}else if(session.getAttribute(com.huatek.framework.security.ClientInformationImpl.LOGIN_ACCOUNT)!=null){
	session.setAttribute("javax.servlet.error.exception",new BusinessRuntimeException("error.url.not.found"));
	if(exception!=null){
		exception.printStackTrace();
	}else{
		System.out.println("不能找到请求的路径:"+ThreadLocalClient.get().getRequestURL());
		out.println("不能找到请求的路径:"+ThreadLocalClient.get().getRequestURL());
	}
}
%>
