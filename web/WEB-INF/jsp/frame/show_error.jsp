<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="javax.servlet.ServletException,com.huatek.framework.exception.BusinessRuntimeException,com.huatek.framework.exception.BusinessCheckException" %>
<%@ page import="com.huatek.framework.util.Util" %>
<%
Throwable exception = (Throwable) request.getSession().getAttribute("javax.servlet.error.exception");
if(exception instanceof BusinessRuntimeException || exception instanceof BusinessCheckException){
	String errorCode = null;
	if(exception instanceof BusinessRuntimeException){
		errorCode = ((BusinessRuntimeException)exception).getErrorKey();
	 }else{
		 errorCode = ((BusinessCheckException)exception).getErrorKey();
	 }
	if(errorCode.indexOf("error.")>=0||errorCode.indexOf("Error.")>=0){
	%>
		<span class="errorMsg"><spring:message code="<%=errorCode%>"/></span>
	<%
	}else{%>
		<span class="errorMsg"><%=errorCode%></span>
<%
	}
}else{
	if(exception!=null){
		exception.printStackTrace();
	}
%>
	<span class="errorMsg">系统内部发生错误！</span>
<%
}
session.removeAttribute("javax.servlet.error.exception");
session.removeAttribute("sendNum");
%>