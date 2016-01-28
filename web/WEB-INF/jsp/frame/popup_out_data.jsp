<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.huatek.framework.util.Constant,com.huatek.framework.security.ThreadLocalClient" %>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.huatek.framework.util.Util" %>
<script type="text/javascript">
<!--
if(!parent){
	alert('调用不正确，调用父界面不存在.');
}else{
	<%
	String clickeEvent = Util.getString(request.getParameter("clickEvent"));
	 out.println("closeDialog();");
	if("confirm".equals(clickeEvent)){
		out.println("confirmSelect();");
	}else if("timeout".equals(clickeEvent)){
		out.println("refreshParent();");
	}
	%>	
}

function closeDialog(){
	parent.$(".ui_dialog").css("display","none");
    parent.$(".ui_overlay").css("display","none");
    parent.$(".ui_iframe").css("display","none");
    parent.$(".ui_lock").css("display","none");
}

function confirmSelect(){
	var idArr = [<%=request.getParameter("dataIds")%>];
	var valueArr = [<%=request.getParameter("dataValues")%>];
	parent.refreshData(idArr,valueArr);
}

function refreshParent(){
	parent.window.relocation();
}
//-->
</script>
