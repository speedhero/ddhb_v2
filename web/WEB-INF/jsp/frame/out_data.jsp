<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.huatek.framework.util.Constant,com.huatek.framework.security.ThreadLocalClient" %>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<%

if (request.getAttribute(Constant.AJAX_ERROR)!=null){
	response.setStatus(Constant.VALIDATE_ERROR_CODE);
}
%>
<c:out value="${_out_data}" escapeXml="false"></c:out>
