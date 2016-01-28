<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page import="com.huatek.framework.util.Constant"%>
<%@ page import="javax.servlet.ServletException,com.huatek.framework.exception.BusinessRuntimeException" %>
<%@ page import="com.huatek.framework.util.Util" %>
<%
BusinessRuntimeException exception = (BusinessRuntimeException) request.getAttribute("javax.servlet.error.exception");
if(exception!=null){
	exception.printStackTrace();
}
String error = exception.getErrorKey();
Object sendNum = session.getAttribute("sendNum");
/***
*打开窗口报错时直接打印错误信息.
*/
if("1".equals(request.getParameter("openWin"))){
	if(error.indexOf("error.")>=0||error.indexOf("Error.")>=0){
%>
	<spring:message code="<%=error%>"/>
<%  }else{
	out.println(exception.getMessage());
}
	return;
}
if(Util.isAjaxRequest(request)){
	response.setStatus(Constant.VALIDATE_ERROR_CODE);

	if(error.indexOf("error.")>=0||error.indexOf("Error.")>=0){
%>
	<spring:message code="<%=error%>"/>
<%
	}else{
		error = error.replaceAll("\"","\'");
		error = error.replaceAll("\n",";");
		error = error.replaceAll("\r",";");
		out.println(error);
	}
}else{
	//接口异常处理
	/*
	if(ThreadLocalClient.get().getOperator().getAcctName().equals(Util.ESB_ACCOUNT_NAME)){
		out.println(SendLogisticsOrderServiceImpl.GSON.toJson(new Ops2Response("-1",
				exception.getMessage(), false)));
		return;
	}*/
	if(sendNum==null){
		session.setAttribute("sendNum","1");
		session.setAttribute("javax.servlet.error.exception",exception);
		response.sendRedirect(request.getContextPath()+"/error.show?actionMethod=show");
	}else{
		session.removeAttribute("javax.servlet.error.exception");
		session.removeAttribute("sendNum");
		out.println(exception.getMessage());
		return;
	}
}
%>