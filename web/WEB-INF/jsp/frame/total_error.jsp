<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="com.huatek.framework.util.Util,
com.huatek.framework.util.Constant,
com.huatek.framework.tag.HtmlUtil,
com.huatek.framework.util.ErrorsManagementUtil,
com.huatek.framework.security.ThreadLocalClient,
com.huatek.framework.sso.HttpClientUtil,
com.huatek.framework.sso.ResponseBean,
cn.hshb.web.common.exception.ExceptionUtils,
cn.hshb.web.common.util.CustomBase64
"%>
<%
Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
if (exception != null) {
	exception.printStackTrace();
} else {
	exception = new Exception("系统发生未知错误,请检查相关代码");
}
ErrorsManagementUtil.saveErrorMsg(request, exception);

String error = null;
String fullStackTrace = "";
/***
 *打开窗口报错时直接打印错误信息.
 */
if ("1".equals(request.getParameter("openWin"))) {
	out.println(exception.getMessage());
	return;
}
if (Util.isAjaxRequest(request)) {
	response.setStatus(Constant.VALIDATE_ERROR_CODE);
	//String error = null;
	if (exception.getCause() != null) {
		Throwable cause = exception.getCause();
		while (cause.getCause() != null && !cause.getCause().getClass().equals(cause.getClass())) {
			cause = cause.getCause();
		}
		error = cause.getMessage();
	} else {
		if (exception.getMessage() != null && exception.getMessage().length() > 0) {
			error = exception.getMessage();
		} else {
			error = exception.getClass().getName();
		}
	}
	if (error != null) {
		error = error.replaceAll("\"", "\'");
		error = error.replaceAll("\n", ";");
		error = error.replaceAll("\r", ";");
		if (error.indexOf("Cannot delete or update") >= 0) {
			String errorInfo = HtmlUtil.getMysqlForeignKeyErrorDicInfo(error, "foreignKey");
			if (errorInfo == null) {
				errorInfo = "该数据被其它数据引用，不能删除。";
			}
			out.println(errorInfo);
		} else if (error.indexOf("Duplicate entry") >= 0) {
			String errorInfo = HtmlUtil.getMysqlErrorDicInfo(error, "uniqueKey");
			if (errorInfo == null) {
				errorInfo = "违反唯一约束条件。";
			}
			out.println(errorInfo);
		} else {
			out.println(error);
		}
	}
} else {
	//接口异常处理
	if (ThreadLocalClient.get()!=null && ThreadLocalClient.get().getActionURL()!=null 
		&& ThreadLocalClient.get().getActionURL().indexOf(".rcp") > 0) {
		String errorMessage = exception.getMessage();
		out.println(HttpClientUtil.GSON.toJson(new ResponseBean("-1", errorMessage, false)));
		return;
	}
	if (session.getAttribute("javax.servlet.error.exception") != null) {
		//Util.getReturnMessage 该方法包含对ESB接口发送数据
		//out.print("系统发生错误：" + exception.getLocalizedMessage());
		out.print("很抱歉，您访问的网页出了点小状况，请返回重新试一次看看。");
		fullStackTrace = ExceptionUtils.getStackTraceAsString(exception);
		session.removeAttribute("javax.servlet.error.exception");
		if (session.getAttribute("sendNum") != null) {
			session.removeAttribute("sendNum");
		}
	} else {
		//out.print("系统发生错误：" + exception.getLocalizedMessage());
		out.print("很抱歉，您访问的网页出了点小状况，请返回重新试一次看看。");
		fullStackTrace = ExceptionUtils.getStackTraceAsString(exception);
	}
}

if(fullStackTrace != null && !"".equals(fullStackTrace.trim())){
	out.println("<!-- ");
	out.println(CustomBase64.encode(fullStackTrace.getBytes()));
	out.println("-->");
}
%>
