<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="duibi_w">
  <div id="compareDiv" class="duibi" style="display: none;">
    <div id="menuDiv" class="db_tit">
      <span> <a id="hiddenCompareDiv" onclick="hiddenCompareDiv()" href="javascript:void(0);">隐藏</a> <a
        id="openCompareDiv" style="display: none;" onclick="openCompareDiv();" href="javascript:void(0);">打开</a> <a
        id="closeCompareDiv" onclick="closeCompareDiv()" href="javascript:void(0);">关闭</a>
      </span> <a href="javascript:void(0)" id="compareMenu" class="one">房源对比</a>
      <a href="javascript:void(0);" id="historyDiv">浏览记录</a>
    </div>
    <div id="compareContentContainer" class="db_ls"
      style="display: none;">
      <ul id="compareContent">
        <li id="compareItem1">
          <div id="textContent1">
            <div class="db_tjs">1</div>
            <div class="db_tjswd">继续添加对比
              <c:if test="${showCompare == 'S'}">二手房</c:if>
              <c:if test="${showCompare == 'R'}">租房房源</c:if>
              <c:if test="${showCompare == 'C'}">小区</c:if>
            </div>
          </div>
        </li>
        <li id="compareItem2">
          <div id="textContent2">
            <div class="db_tjs">2</div>
            <div class="db_tjswd">继续添加对比
              <c:if test="${showCompare == 'S'}">二手房</c:if>
              <c:if test="${showCompare == 'R'}">租房房源</c:if>
              <c:if test="${showCompare == 'C'}">小区</c:if>
            </div>
          </div>
        </li>
        <li id="compareItem3">
          <div id="textContent3">
            <div class="db_tjs">3</div>
            <div class="db_tjswd">继续添加对比
              <c:if test="${showCompare == 'S'}">二手房</c:if>
              <c:if test="${showCompare == 'R'}">租房房源</c:if>
              <c:if test="${showCompare == 'C'}">小区</c:if>
            </div>
          </div>
        </li>
        <li id="compareItem4">
          <div id="textContent4">
            <div class="db_tjs">4</div>
            <div class="db_tjswd">继续添加对比
              <c:if test="${showCompare == 'S'}">二手房</c:if>
              <c:if test="${showCompare == 'R'}">租房房源</c:if>
              <c:if test="${showCompare == 'C'}">小区</c:if>
            </div>
          </div>
        </li>
        <li class="nob"><a id="showCompare" href="javascript:void(0);" 
        onclick="startCompare('<c:if test="${showCompare == 'S'}">${globalUrl}houseSecond.show?actionMethod=houseSecondCompare</c:if><c:if test="${showCompare == 'R'}">${globalUrl}rent.show?actionMethod=houseRentCompare</c:if><c:if test="${showCompare == 'C'}">${globalUrl}community.show?actionMethod=communityCompare</c:if>')">开始对比</a>
        <p>
            <a id="clearCompare" href="javascript:void(0);"
              onclick="clearCompareItem('<c:if test="${showCompare == 'S'}">secondHouseCompare</c:if><c:if test="${showCompare == 'R'}">rentHouseCompare</c:if><c:if test="${showCompare == 'C'}">communityCompare</c:if>')">清空对比栏</a>
          </p></li>
      </ul>
    </div>
    <div id="historyListContainer" class="db_ls" style="display: none;">
      <ul id="listContent">
        <li id="historyItem1">
          <div id="historyTextContent1">
            <div class="db_tjs">1</div>
            <div class="db_tjswd">
              <c:if test="${showCompare == 'S'}">二手房</c:if>
              <c:if test="${showCompare == 'R'}">租房房源</c:if>
              <c:if test="${showCompare == 'C'}">小区</c:if> 浏览历史
            </div>
          </div>
        </li>
        <li id="historyItem2">
          <div id="historyTextContent2">
            <div class="db_tjs">2</div>
            <div class="db_tjswd">
              <c:if test="${showCompare == 'S'}">二手房</c:if>
              <c:if test="${showCompare == 'R'}">租房房源</c:if>
              <c:if test="${showCompare == 'C'}">小区</c:if>浏览历史
            </div>
          </div>
        </li>
        <li id="historyItem3">
          <div id="historyTextContent3">
            <div class="db_tjs">3</div>
            <div class="db_tjswd">
              <c:if test="${showCompare == 'S'}">二手房</c:if>
              <c:if test="${showCompare == 'R'}">租房房源</c:if>
              <c:if test="${showCompare == 'C'}">小区</c:if>浏览历史
            </div>
          </div>
        </li>
        <li id="historyItem4">
          <div id="historyTextContent4">
            <div class="db_tjs">4</div>
            <div class="db_tjswd">
              <c:if test="${showCompare == 'S'}">二手房</c:if>
              <c:if test="${showCompare == 'R'}">租房房源</c:if>
              <c:if test="${showCompare == 'C'}">小区</c:if>浏览历史
            </div>
          </div>
        </li>
        <li class="nob"><c:if test="${LoginMember == null }">
            <c:if test="${showCompare == 'S'}">
              <a id="moreBtn" href="javascript:void(0);" onclick="loginBox('browseHistory','S');">更多</a>
            </c:if>
            <c:if test="${showCompare == 'R'}">
              <a id="moreBtn" href="javascript:void(0);" onclick="loginBox('browseHistory', 'R');">更多</a>
            </c:if>
            <c:if test="${showCompare == 'C'}">
              <a id="moreBtn" href="javascript:void(0);" onclick="loginBox('browseHistory');">更多</a>
            </c:if>
          </c:if> <c:if test="${LoginMember != null }">
            <c:if test="${showCompare == 'S'}">
              <a href='javascript:void(0);' onclick="window.open('${globalUrl}usercenter.do?actionMethod=browseHistory&isCutPage=false&houseType=0');">更多</a>
            </c:if>
            <c:if test="${showCompare == 'R'}">
              <a href='javascript:void(0);' onclick="window.open('${globalUrl}usercenter.do?actionMethod=browseHistory&isCutPage=false&houseType=1');">更多</a>
            </c:if>
            <c:if test="${showCompare == 'C'}">
              <a href='javascript:void(0);' onclick="window.open('${globalUrl}usercenter.do?actionMethod=browseHistory&isCutPage=false&houseType=2');">更多</a>
            </c:if>
          </c:if></li>
      </ul>
    </div>
  </div>
</div>
