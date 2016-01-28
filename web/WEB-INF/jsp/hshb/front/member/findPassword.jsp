<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/css.css" />
<style>
.login-area {
  width: 510px;
  height: auto;
  margin: 50px auto;
  margin-bottom: 0px;
  padding-bottom: 100px;
  /*   box-shadow:5px 5px 5px #888888; */
  /* background-image: url("../../images/homepage/32.png"); */
}

.input {
  width: 100%;
  height: 30px;
}

.login-nav {
  height: 43px;
  width: 100%;
}

.login-nav div {
  width: 128px;
  float: left;
  height: 100%;
  color: #ffffff;
  text-align: center;
  line-height: 50px;
  cursor: pointer;
}

.logincontext {
  width: 100%;
  height: 240px;
  padding-top: 40px;
  background-color: #ffffff;
}

.account {
  width: 100%;
  height: 35px;
}

.account div {
  float: left;
  line-height: 16px;
}

.accountname {
  width: 125px;
  height: 100%;
  text-align: right;
  line-height: 50px;
  padding-right: 5px;
}

.accountnum {
  width: 355px;
  height: 100%;
}

.loginsubmit {
  width: 100px;
  height: 38px;
  background-color: #3fb8b1;
  border: 2px solid #3fb8b1;
  border-radius: 5px;
  color: white;
  cursor: pointer;
  font-family: 微软雅黑;
  font-size: 20px;
}

.loginbut {
  width: 100px;
  height: 38px;
  margin-top: 35px;
  margin-left: 156px;
  margin-right: auto;
}

.registercontext {
  height: auto;
  width: 100%;
  padding: 40px 0;
  background-color: #ffffff;
}

.registerbutton {
  width: 53%;
  height: 38px;
  margin-top: 35px;
  margin-left: auto;
  margin-right: auto;
}

.errors {
  color: red;
  font-size: 10px;
}
</style>
<c:if test="${uuidInvalidate eq null}">
  <div class="login-area">
    <div class="login-nav" style="background: url('${globalUrl}images/housedetail/PCPad-4-2.png');">
      <div id="login" class="loginbutton" style="color: #000000; background: url('${globalUrl}images/housedetail/PCPad-4-1.png');">找回密码</div>
    </div>
    <form:form modelAttribute="platMemberInfo" name="platMemberInfo" action="${globalUrl}register_member/findPassword">
      <div id="logincondiv" class="logincontext">
        <div class="account">
          <div class="accountname">您的用户名</div>
          <div class="accountnum">
            <input type="text" value="${platMemberInfo.accName}" id="accName" disabled="disabled" />
          </div>
        </div>
        <div class="account" style="margin-top: 20px;">
          <div class="accountname">
            <span style="color: red;">*</span>请输入新密码
          </div>
          <div class="accountnum">
            <input type="password" id="newPasswd" name="newPasswd" />
            <form:errors path="newPasswd" cssClass="errors" />
          </div>
        </div>
        <div class="account" style="margin-top: 20px;">
          <div class="accountname">
            <span style="color: red;">*</span>请确认新密码
          </div>
          <div class="accountnum">
            <input type="password" id="confirmNewPasswd" name="confirmNewPasswd" />
            <form:errors path="confirmNewPasswd" cssClass="errors" />
          </div>
        </div>
        <div class="loginbut">
          <button class="loginsubmit" type="submit" onclick="postDataByFormName('platMemberInfo','workspace');">提交</button>
        </div>
      </div>
    </form:form>
  </div>
</c:if>
<c:if test="${uuidInvalidate ne null}">
  <div class="login-area">
    <div class="login-nav"
      style="background: url('${globalUrl}images/housedetail/PCPad-4-2.png');">
      <div id="login" class="loginbutton"
        style="color: #000000; background: url('${globalUrl}images/housedetail/PCPad-4-1.png');">找回密码</div>
    </div>
    <form:form modelAttribute="platMemberInfo" name="platMemberInfo" id="platMemberInfo" action="${globalUrl}register_member/forgetPassword">
      <div id="logincondiv" class="logincontext">
        <div class="account">
          <div class="accountname" style="width: 200px;">链接已经失效，请关闭页面</div>
        </div>
      </div>
    </form:form>
  </div>
</c:if>

