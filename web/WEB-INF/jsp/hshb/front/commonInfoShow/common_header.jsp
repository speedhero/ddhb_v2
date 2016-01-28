<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.huatek.framework.sso.SSOServiceManagement"%>
<%@taglib prefix="h" uri="/WEB-INF/tld/hshb.tld"%>
<div id="t_nav">
	<div class="nav_con">
		<div class="logo logoPc" style="cursor: pointer;">
			<a href="${globalUrl}"><img src="${pictureHost}${homeLogoPc}" title="${otherpageHover}" /></a>
		</div>
		<div class="logo logoPad" style="cursor: pointer;">
			<a href="${globalUrl}"><img src="${pictureHost}${homeLogoPad}" title="${otherpageHover}" /></a>
		</div>
		<div class="logo logoMb" style="cursor: pointer;">
			<a href="${globalUrl}"><img src="${pictureHost}${homeLogoMb}" title="${otherpageHover}" /></a>
		</div>
		<div class="dl_zc" <c:if test="${LoginMember != null }"> style="display:none;" </c:if>>
			<a onclick="loginBox();">登录</a>
			<a onclick="registerBox();">注册</a>
		</div>
		<div class="dl_zc_h"<c:if test="${LoginMember == null}"> style="display:none;" </c:if>>
			<a href="#" class="dl_name" style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;"><c:out value="${LoginMember.accName }"></c:out></a>
			<div class="dlh_wd">
				<a href="${globalUrl}usercenter.do?actionMethod=appointment&isCutPage=false">预约管理</a>
				<a href="${globalUrl}usercenter.do?actionMethod=contactQuery&isCutPage=false">合同进度查询</a>
				<div class="bor"></div>
				<a href="${globalUrl}usercenter.do?actionMethod=collectment&isCutPage=false">收藏夹管理</a>
				<a href="${globalUrl}usercenter.do?actionMethod=priceNotice&isCutPage=false">置换价格订阅</a>
				<a href="${globalUrl}usercenter.do?actionMethod=cutQuestion">问答管理</a>
				<div class="bor"></div>
				<a href="${globalUrl}usercenter.do?actionMethod=scoreManager&isCutPage=false">积分管理</a>
				<a href="${globalUrl}usercenter.do?actionMethod=memberInformatinManage&isCutPage=false">个人信息管理</a>
				<a onclick="if(confirm('确认退出吗？')){return true;}else{return false;}" href="${globalUrl}sso.show?actionMethod=logout">退出登录</a>
			</div>
		</div>

		<div class="nav">
			<ul>
				<li class="nav_a1" style="<c:if test="${backType == 1 }">background-color:#F79200;</c:if>" ><a href="${globalUrl}">首页</a></li>
		        <li class="nav_a2" style="<c:if test="${backType == 2 }">background-color:#F79200;</c:if>" ><a href="${globalUrl}chushou" >二手房</a></li>
		        <li class="nav_a3" style="<c:if test="${backType == 3 }">background-color:#F79200;</c:if>" ><a href="${globalUrl}chuzu" >租赁</a></li>
		        <li class="nav_a4" style="<c:if test="${backType == 4 }">background-color:#F79200;</c:if>" ><a href="${globalUrl}xinfang" >新房</a></li>
		        <li class="nav_a5" style="<c:if test="${backType == 5 }">background-color:#F79200;</c:if>"><a href="${globalUrl}xiaoqu" >小区</a></li>
				<li class="nav_a6" style="<c:if test="${backType == 6 }">background-color:#F79200;</c:if>"><a onclick="$('.nav_more').show();">更多</a>
					<div class="nav_more">
						<p><a href="${globalUrl}xiaoqu" target="_xiaoqu">小区</a></p>
						<a class="a_4" href="${globalUrl}contract/contractQuery" target="_contractQuery">交易查询</a>
						<a class="a_3" href="${globalUrl}broker/questionAndAnswer" target="_questionAndAnswer">问答&amp;攻略</a>
						<a class="a_1" href="${globalUrl}company/showinvite" target="_showinvite">加入华邦</a>
						<a class="a_2" href="${globalUrl}company/showservice" target="_showservice">投诉建议</a>
						<a class="a_5" href="#" onclick="addFavour();">收藏本站</a>
					</div></li>
			</ul>
		</div>
		<script type="text/javascript">
		$(document).ready(function(){
			//返回查询条件
			$("#topSearchBar").keyup(function(){
				var value = $("#topSearchBar").val();
				value = $.trim(value);
				if(value == "")return false;
				$.ajax({
					url:"${globalUrl}search/inputKeyword/" + value,
					type:"get",
					dataType:'json',
					success:function(data){
						var searItems = data;
						$("#searchResult").show();
						var s = "<ul>";
						for(var i=0; i < searItems.length; i++){
							var searItem = searItems[i];
							s +="<li name='searchNameLi' onClick=clickSearchItem('" + searItem.searchName + "'); >" + searItem.searchName + "</li>";
						}
						s += "</ul>";
						 $("#searchResult").html(s)
					}
				});
			});
		});
		function clickSearchItem(searchName){
			var a = searchName;
			$("#topSearchBar").val(searchName);
			$("#searchResult").html("");
			$("#searchResult").hide();
			document.getElementById("searchBtn").click();
		};
		$(document).click(function(e){
			try{
				e = window.event || e;	//兼容IE71
				obj = $(e.srcElement || e.target);
				if(!$(obj).is("#topSearchBar,#searchResult *")){
					$("#searchResult").html("");
					$("#searchResult").hide();
				}
			}catch(e){
				if(window.console) console.log(e);
			}
		});
		</script>
<div id="searchBox">
	<div class="lmtop_sc">
		<div class="sc_an">
			<a onclick="$('#headScanPadPhone').show();"></a>
		</div>
		<div class="sc_bd" id="headScanPadPhone" style="display: none;">
			<div class="jiao"></div>
			<div class="bd_an">
				<input id="textPhonePad" name="textfield" type="text" /><a id="searchBtnPad">搜索</a>
			</div>
		</div>
	</div>
	<div class="lmtop_sc">
		<div class="sc_an"><a href="#"></a></div>
		<div class="sc_bd">
			<div class="jiao"></div>
			<div class="bd_an"><input id="topSearchBar"  name="textfield" type="text" /><a id="searchBtn">搜索</a></div>
			<!-- 搜索选项start -->
		   	<div id="searchResult" class="bdChioce" style="display: none;"></div>
	   	<!-- 搜索选项end -->
		</div>
	</div>
</div>
	</div>
</div>
<div id="infoDialogHei" class="tm_hei" style="display: none;"></div>
<div id="infoDialog" class="tcc_zp" style="display: none; margin: 0 !important; position: inherit !important;">
	<div class="tjcg">
		<p id="infoDialogContent">提交成功，我们会尽快核实，并跟您取得联系，感谢您的支持！</p>
		<div class="jsq_an">
			<a href="javascript:void(0);" onclick="art.dialog.list['infoDialog'].close();location.reload();" class="tj">确定</a>
		</div>
	</div>
</div>
<div id="phoneValidDialog" class="tcc_zp" style="display:none;margin: 0 !important;position: inherit !important;">
    <div class="tjcg">
    	<p id="phoneValidDialogContent">手机短信定阅需要注册，是否前往注册界面?</p>
        <div class="jsq_an">
        	<a id="operationBtn" href="javascript:void(0);" class="tj">验证手机</a>
        	<a id="cancelBtn" href="javascript:void(0);" onclick="art.dialog.list['phoneValidDialog'].close();return false;" class="tj">取消</a>
        </div>
    </div>
</div>