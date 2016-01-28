<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
function deleteItem(mid){
	var r=confirm("您确认要删除吗？");
	if(r==true){
		$.ajax({
			type : "post",
			url : "${globalUrl}saveSearchField.show?actionMethod=deleteSavedFiel&mid=" + mid,
			dataType : "json",
			success : function (data) {
				if (data.result == 'success') {
					alert("删除成功！");
					window.location.href = "${globalUrl}saveSearchField.show?actionMethod=listAll&isCutPage=false";
				}else {
					alert("操作失败！");
				}
			}
		}); 
    }
}
</script>
<div class="cp_rt">
        	<div class="licn"><a onclick='window.location.href="${globalUrl}welcome.show?actionMethod=welcome"'>首页</a>&nbsp;>&nbsp;个人中心&nbsp;>&nbsp;搜索保存记录</div>
            <div class="gr_xxi">您好，你目前共保存了<b>${pageBean.totalRows }</b>条搜索条件</div>
            <form:form name="savedFieldForm" id="savedFieldForm" action="${globalUrl}saveSearchField.show?actionMethod=listAll&isCutPage=true">
            <div class="gr_ls">
            	<div class="gr_tit">
                	<ul>
                    	<li class="li_1">序号</li>
                    	<li class="li_2">搜索内容</li>
                    	<li class="li_3">类型</li>
                    	<li class="li_4">保存时间</li>
                    	<li class="li_5" style="text-align: center;">操作</li>
                    </ul>
                </div>
                <c:forEach var="saveList" items="${pageBean.dataList}" varStatus="status">
                <div class="gr_nlb">
                	<div class="gr_nrb">
                        <ul>
                            <li class="li_1"><b><c:out value="${(pageBean.currentPage - 1) * pageBean.pageSize + status.index + 1 }"></c:out></b></li>
                            <li class="li_2">
								<a href="${globalUrl}${saveList.url }"><c:out value="${saveList.savedField }"></c:out></a>
							</li>
                            <li class="li_3"><span>类型:</span>
								<c:if test="${saveList.type eq 1 }">
									二手
								</c:if>
								<c:if test="${saveList.type eq 2 }">
									租赁
								</c:if>
								<c:if test="${saveList.type eq 3 }">
									小区
								</c:if>
							</li>
                            
                            <li class="li_4"><span>保存时间:</span>
								<fmt:formatDate value="${saveList.createDate }" pattern="yyyy-MM-dd HH:mm"/>
							</li>
                            <li class="li_5"><a href="javascript:void(0);" class="dy" onclick="deleteItem('${saveList.no}');"">删除</a></li>
                        </ul>
                    </div>
                    <div class="clear"></div>
                </div>
                </c:forEach>
            </div>
            <div class="page" style="display:block;">
				<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false" formName="savedFieldForm" offerPageSize="20,50,100" isExistForm="true" showAreal="usercenterDetailDiv"/>
			</div>
			</form:form>
        </div>
