<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.huatek.framework.util.Constant,com.huatek.framework.security.ThreadLocalClient" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%
if (request.getAttribute(Constant.AJAX_ERROR)!=null){
	response.setStatus(Constant.VALIDATE_ERROR_CODE);
}
%> --%>
<c:if test="${bespeakok != null}"><c:out value="预约成功" escapeXml="false"></c:out></c:if>
<c:if test="${targetUrl != null}"><c:out value="跳转页面" escapeXml="false"></c:out>targetUrlBegin=<c:out value="${targetUrl }"></c:out>=targetUrlEnd</c:if>
<c:if test="${exist != null}"><c:out value="邮箱地址已存在" escapeXml="false"></c:out></c:if>
<c:if test="${phoneBlock != null}"><c:out value="${phoneBlock}" escapeXml="false"></c:out></c:if>
<c:if test="${saveok != null}"><c:out value="保存成功" escapeXml="false"></c:out> accNameBegin=<c:out value="${accName }" escapeXml="false"></c:out>=accNameEnd  </c:if>
<c:if test="${savefalse != null}"><c:out value="保存失败" escapeXml="false"></c:out></c:if>
<c:if test="${issave != null}"><input id="issaved" type="hidden" value="1"></c:if>
<c:if test="${noValidEmail != null}"><c:out value="邮箱未验证" escapeXml="false"></c:out></c:if>
<c:if test="${noPhoneValid != null}"><c:out value="手机未验证" escapeXml="false"></c:out></c:if>
<c:if test="${isExist != null}"><c:out value="已经订阅" escapeXml="false"></c:out></c:if>
<c:if test="${verifyCodeNull != null}"><c:out value="验证码为必填项" escapeXml="false"></c:out></c:if>
<c:if test="${verifyCodeError != null}"><c:out value="验证码错误" escapeXml="false"></c:out></c:if>
<c:if test="${saveokMore != null}"><c:out value="保存成功" escapeXml="false"></c:out></c:if>
<c:if test="${sessionOut != null}"><c:out value="登陆失效" escapeXml="false"></c:out></c:if>
<c:out value="${_out_data}" escapeXml="false"></c:out>
<input id="email" type="hidden" value="${email}">
<input id="mamagerMail" type="hidden" value="${mamagerMail}">
<input id="mailDomain" type="hidden" value="${mailDomain}">
