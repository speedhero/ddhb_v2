<%@ page import="com.huatek.framework.util.Parameter,org.apache.log4j.Logger" %>
<%@ page import="com.huatek.framework.service.ConfigFactoryFactory" %>
<%
request.setAttribute("uploadUrl", ConfigFactoryFactory.getSysConfig("upload.url"));
request.setAttribute("imgUrl", ConfigFactoryFactory.getSysConfig("img.server.url"));
if(Logger.getLogger(Parameter.class).isDebugEnabled()){
	request.setAttribute("ssoURL","serverIp="+request.getServerName()+"&serverPort="+request.getServerPort());
}
%>