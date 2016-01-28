<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>
  $(document).ready(
      function() {
        $("#resoved").click(
            function() {
              type = 1;
              setjsonvalue("type", type);
              getData('${globalUrl}usercenter.do?actionMethod=cutQuestion&type=' + type, '', 'changelist');
            });
        $("#unresoved").click(
            function() {
              type = 0;
              setjsonvalue("type", type);
              getData('${globalUrl}usercenter.do?actionMethod=cutQuestion&type=' + type, '', 'changelist');
            });
      });
  function zhankai(obj) {
    $obj = $(obj);
    $target = $obj.parent().parent().parent().parent();
    if ($target.hasClass("gr_nlb_xs")) {
      $target.removeClass("gr_nlb_xs");
    } else {
      $target.addClass("gr_nlb_xs");
    }
  }
</script>
<style>
.ansermanageDiv table {
  border-bottom: 1px solid #CCCCCC;
  width: 100%;
}

.ansermanageDiv table th {
  text-align: left;
  padding: 0;
  height: 30px;
  background: url("${globalUrl}images/memberCenter/PCPad-4-2.png");
  color: #ffffff;
}

.ansermanageDiv table td {
  text-align: left;
  padding: 0;
  height: 30px;
  vertical-align: bottom;
  padding-bottom: 9px;
  border-top: 1px dashed #CCCCCC;
}

.dy {
  background: none repeat scroll 0 0 #a1acaa;
}

.que_is {
  margin-top: 10px;
  height: 30px;
}

.que_is li {
  float: left;
  font-size: 14px;
  line-height: 28px;
  cursor: pointer;
  padding: 0 10px;
}
</style>
<div class="cp_rt">
  <div class="licn">
    <a onclick='window.location.href="${globalUrl}"'>首页</a>&nbsp;>&nbsp;个人中心&nbsp;>&nbsp;问答管理
  </div>
  <div class="gr_xxi">
    您好，你目前提出了<b><c:out value="${anCount + qCount}"></c:out></b>个问题，其已解答<b>${anCount }</b>条，未解答<b>${qCount }</b>个。
  </div>
  <div class="que_is">
    <ul>
      <li id="resoved">已解决</li>
      <li class="dy" id="unresoved">未解决</li>
    </ul>
  </div>
  <div class="gr_ls" style="padding-top: 0px;">
    <div class="gr_tit">
      <ul>
        <li class="li_1">序号</li>
        <li class="li_2">标题</li>
        <li class="li_3">类型</li>
        <li class="li_4">发布时间</li>
        <li class="li_5">最后更新时间</li>
        <li class="li_6">操作</li>
      </ul>
    </div>
    <c:forEach var="question" items="${pageBean.dataList}" varStatus="status">
      <div class="gr_nlb">
        <div class="gr_nrb">
          <ul>
            <li class="li_1"><b><c:out value="${(pageBean.currentPage - 1) * pageBean.pageSize + status.index + 1 }"></c:out></b></li>
            <li class="li_2"><a style="cursor: pointer;" onclick='window.location.href="${globalUrl}broker.show?actionMethod=initQuestionDetail&questionId=${question.erpId }&typeId=${question.questionType.parentType.erpId }&subTypeId=${question.questionType.erpId }"'>${question.title}</a>
            </li>
            <li class="li_3"><span>类型:</span>${question.questionType.parentType.typeName}</li>
            <li class="li_4"><span>发布时间:</span>
              <div><fmt:formatDate value="${question.createTime}" pattern="yyyy-MM-dd" /></div>
              <div><span><fmt:formatDate value="${question.createTime}" pattern="HH:mm" /></span></div>
            </li>
            <li class="li_5"><span>最后更新时间:</span>
              <div><fmt:formatDate value="${question.createTime}" pattern="yyyy-MM-dd" /></div>
              <div><span><fmt:formatDate value="${question.createTime}" pattern="HH:mm" /></span></div>
            </li>
            <li class="li_6"><a href="javascript:void(0);" class="jl" onclick="zhankai(this);">问答详情</a></li>
          </ul>
        </div>
        <div class="lsjl">
          ${question.content }
          <div class="jiao"></div>
        </div>
        <div class="clear"></div>
      </div>
    </c:forEach>
  </div>
  <div class="page" style="display: block;">
    <huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false" formName="answerdForm" offerPageSize="20,50,100" isExistForm="true" queryFunction="changePages()" />
  </div>
</div>
<script type="text/javascript">
  function changePages() {
    var currvalue = document.getElementsByName("currentPage")[0].value;
    setjsonvalue("currentPage", currvalue);
    postDataByURL2('${globalUrl}usercenter.do?actionMethod=cutQuestion', jsoninit, "changelist");
  }
</script>