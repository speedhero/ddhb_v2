<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.huatek.hbwebsite.util.CompanyInfo"%>
<%@page import="com.huatek.framework.sso.SSOServiceManagement"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %>

<link type="text/css" rel="stylesheet" href="${globalUrl}css/front_footer.css" />
<cache:cache key="_common_footer_footer_menu" time="3600">
<div id="links">
	<div class="links">
		<div id="Tabs" class="lk_a1">
		<c:forEach var="item" items="${footerMenuList }" varStatus="s1">
			<a ${s1.count eq 1?"class='one'":""} >${item.menuName }</a>
		</c:forEach>
		</div>
		<div id="TabsCon" class="lk_a2">
		<script type="text/javascript">
			$(document).ready(function(){
				lis = document.getElementById("Tabs").getElementsByTagName("a");
				uls = document.getElementById("TabsCon").getElementsByTagName("div");
				order = 0;
				for ( var i = 0; i < lis.length; i++) {
					lis[i].value = i;
					lis[i].onclick = function() {
						ChangeTabs(this.value);
					};
					uls[i].className = "hidden";
				}
				lis[order].className = "one";
				uls[order].className = "block";
			});
			function ChangeTabs(nowTab) {
				lis[order].className = "";
				uls[order].className = "hidden";
				order = nowTab
				lis[nowTab].className = "one";
				uls[nowTab].className = "block";
			}
		</script>
		<c:forEach items="${footerMenuList }" varStatus="s1" var="menu">
			<div class="${s1.count eq 1 ? 'block':'hidden'}">
  				<c:forEach  var="link" items="${menu.linkList}" varStatus="s2" >
	  				<a href="${link.linkUrl}">${link.linkName}</a>
  				</c:forEach>
			</div>
		</c:forEach>
        </div>
        <div class="lk_a2x" style="display:none;">${companyInfo }</div>
        <div id="friendLinkDiv" class="lk_pt" style="display:none;"><p>友情链接：</p><span id="frendLinkSpan"></span>
        	<div class="lk_wd" id="wordHref"></div>
        </div>
    </div>
</div>
</cache:cache>
<div id="copy">
	<div class="copy">
		<div class="cp_le">
			我们是——本土最大连锁中介<br /> 
			我们拥有——130家直营连锁门店<br /> 
			我们还拥有——2000余经纪人的资深服务团队<br />
			我们真诚服务——为15万户家庭圆了家的梦想<br /> 
			我们8年如一日——追求卓越
		</div>
		<div class="cp_center">
			<div class="cp_kh">
				<img alt="" src="${globalUrl}image/copy_kouhao.png" border="0" />
			</div>
			<div class="cp_na_add"></div>
			<div class="ap_link">
				<span class="lin_1k">服务电话<br />4008-966-888</span> 
				<span class="lin_2k">企业微信<br />hzhbdc</span> 
				<span class="lin_3k">新浪微博<br />@杭州华邦地产</span>
			</div>
		</div>
		<div class="cp_ri">
			<img alt="" src="${globalUrl}image/copy_lm_img.png" border="0" />
		</div>
	</div>
</div>
<div id="copy_link">
  	<div class="copy_l">
		<div class="cp_a">
			<span>
				<a style="text-decoration:none;" href="${globalUrl}company/showcontact" target="_showcontact">联系我们</a>
				<a style="text-decoration:none;" href="${globalUrl}company/showservice" target="_showservice">投诉建议</a>
				<a style="text-decoration:none; display:none;" href="${globalUrl}company/websitemap" target="_websitemap">站点地图</a>
				<a style="text-decoration:none;" href="${globalUrl}company/showinvite" target="_showinvite">加入华邦</a>
				<a style="text-decoration:none;" href="${globalUrl}company/shownews" target="_shownews">新闻动态</a>
       	<a style="text-decoration:none;" href="${globalUrl}company/companyinfo" target="_companyinfo">关于我们</a>
       	<a style="text-decoration:none;" href="${globalUrl}company/questionAndAnswer" target="_questionAndAnswer">问答攻略</a>
				<a style="text-decoration:none; display:none;" href="${globalUrl}company/questionAndAnswer" target="_questionAndAnswer">房产问答</a>
				<a style="text-decoration:none;" href="${globalUrl}contract/contractQuery" target="_contractQuery">交易查询</a>
     	</span>
   	</div>
     <div class="cp_txt">${companyCopy }</div>
   </div>
</div>

<%-- 百度统计及百度商桥 --%>
<script type='text/javascript'>
//收起百度商桥
function foldQiao(){
	//设置延迟执行  
	setTimeout(function () {
	  $(".qiao-icon-close").append("<span id='spclick'> </span>"); //直接点击.qiao-icon-close是无效的，先添加span再模拟点击span                 
	  $("#spclick").click();
	}, 2000);     //延迟1秒执行，根据网页加载速度设定
}
</script>

<script type="text/javascript">
var _hmt = _hmt || [];
/*
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?c657266d252fd02531ca2079661b96c0";
  var s = document.getElementsByTagName("script")[0];
  s.parentNode.insertBefore(hm, s);
  
  foldQiao();
})();
*/
$(document).ready(function(){
	  var hm = document.createElement("script");
	  hm.src = "//hm.baidu.com/hm.js?c657266d252fd02531ca2079661b96c0";
	  var s = document.getElementsByTagName("script")[0];
	  s.parentNode.insertBefore(hm, s);
	  
	  foldQiao();
});
</script>

