<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${bottomADBar ne null }">
	<div class="adBar">
		<c:if test="${bottomADBar.style == '仅一条'}">
			<div class="PC1">
				<img class="imgClass"
					<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
					<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
					adItemId="${bottomADBar.items.get(0).id }"
					title="${bottomADBar.items.get(0).title }"
					src="${pictureHost }${bottomADBar.items.get(0).pictureIconPC}" />
			</div>
			<div class="PAD1">
				<img class="imgClass"
					<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
					<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
					adItemId="${bottomADBar.items.get(0).id }"
					title="${bottomADBar.items.get(0).title }"
					src="${pictureHost }${bottomADBar.items.get(0).pictureIconPad}" />
			</div>
			<div class="Phone1">
				<img class="imgClass"
					<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
					<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
					adItemId="${bottomADBar.items.get(0).id }"
					title="${bottomADBar.items.get(0).title }"
					src="${pictureHost }${bottomADBar.items.get(0).pictureIconPhone}" />
			</div>
		</c:if>
		<c:if test="${bottomADBar.style == '1:1:1:1' }">
			<div class="adContainer item1111ContPC">
				<div class="item1111">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPC}" />
				</div>
				<div class="item1111 item1111notfirst">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPC}" />
				</div>
				<div class="item1111 item1111notfirst">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(2).itemType == 0 }">toUrl="${bottomADBar.items.get(2).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(2).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(2).id}"</c:if>
						adItemId="${bottomADBar.items.get(2).id }"
						title="${bottomADBar.items.get(2).title }"
						src="${pictureHost }${bottomADBar.items.get(2).pictureIconPC}" />
				</div>
				<div class="item1111 item1111notfirst item111last">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(3).itemType == 0 }">toUrl="${bottomADBar.items.get(3).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(3).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(3).id}"</c:if>
						adItemId="${bottomADBar.items.get(3).id }"
						title="${bottomADBar.items.get(3).title }"
						src="${pictureHost }${bottomADBar.items.get(3).pictureIconPC}" />
				</div>
			</div>
			<div class="adContainer item1111ContPAD">
				<div class="item1111">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPad}" />
				</div>
				<div class="item1111 item1111notfirst">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPad}" />
				</div>
				<div class="item1111 item1111notfirst">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(2).itemType == 0 }">toUrl="${bottomADBar.items.get(2).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(2).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(2).id}"</c:if>
						adItemId="${bottomADBar.items.get(2).id }"
						title="${bottomADBar.items.get(2).title }"
						src="${pictureHost }${bottomADBar.items.get(2).pictureIconPad}" />
				</div>
				<div class="item1111 item1111notfirst item111last">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(3).itemType == 0 }">toUrl="${bottomADBar.items.get(3).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(3).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(3).id}"</c:if>
						adItemId="${bottomADBar.items.get(3).id }"
						title="${bottomADBar.items.get(3).title }"
						src="${pictureHost }${bottomADBar.items.get(3).pictureIconPad}" />
				</div>
			</div>
			<div class="adContainer item1111ContPHOME">
				<div class="item1111 ii">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPhone}" />
				</div>
				<div class="item1111 ii item1111notfirst">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPhone}" />
				</div>
				<div class="item1111 ii item1111notfirst">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(2).itemType == 0 }">toUrl="${bottomADBar.items.get(2).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(2).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(2).id}"</c:if>
						adItemId="${bottomADBar.items.get(2).id }"
						title="${bottomADBar.items.get(2).title }"
						src="${pictureHost }${bottomADBar.items.get(2).pictureIconPhone}" />
				</div>
				<div class="item1111 ii item1111notfirst item111last">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(3).itemType == 0 }">toUrl="${bottomADBar.items.get(3).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(3).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(3).id}"</c:if>
						adItemId="${bottomADBar.items.get(3).id }"
						title="${bottomADBar.items.get(3).title }"
						src="${pictureHost }${bottomADBar.items.get(3).pictureIconPhone}" />
				</div>
			</div>
			<div style="clear: both;"></div>
			<div class="navigationBar" style="width: 180px;">
				<ul>
					<li><a class="goLeftBut"></a></li>
					<li><a class="adian selectedaDian" position="1"></a></li>
					<li><a class="adian unselectedaDian" position="2"></a></li>
					<li><a class="adian unselectedaDian" position="3"></a></li>
					<li><a class="adian unselectedaDian" position="4"></a></li>
					<li><a class="toRightBut"></a></li>
				</ul>
			</div>
		</c:if>
		<c:if test="${bottomADBar.style == '1:3' }">
			<!-- PC端 -->
			<div class="adContainer item13ContPC">
				<div class="item13 item13first">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPC}" />
				</div>
				<div class="item13 item13last">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPC}" />
				</div>
			</div>
			<!-- pad -->
			<div class="adContainer item13ContPAD">
				<div class="item13 item13Padfirst">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPad}" />
				</div>
				<div class="item13 item13Padlast">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPad}" />
				</div>
			</div>
			<!-- Phone -->
			<div class="adContainer item13ContPHONE">
				<div class="item13 ii item13PHONE">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPhone}" />
				</div>
				<div class="item13 ii item13PHONE">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPhone}" />\
				</div>
			</div>
			<div style="clear: both;"></div>
			<div class="navigationBar" style="width: 180px;">
				<ul>
					<li><a class="goLeftBut"></a></li>
					<li><a class="adian selectedaDian" position="1"></a></li>
					<li><a class="adian unselectedaDian" position="2"></a></li>
					<li><a class="toRightBut"></a></li>
				</ul>
			</div>
		</c:if>
		<c:if test="${bottomADBar.style == '3:1' }">
			<!-- PC端 -->
			<div class="adContainer item31ContPC">
				<div class="item13 item31first">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPC}" />
				</div>
				<div class="item13 item31last">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPC}" />
				</div>
			</div>
			<!-- pad -->
			<div class="adContainer item31ContPAD">
				<div class="item13 item31Padfirst">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPad}" />
				</div>
				<div class="item13 item31Padlast">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPad}" />
				</div>
			</div>
			<!-- Phone -->
			<div class="adContainer item31ContPHONE">
				<div class="item13 ii  item31PHONE">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPhone}" />
				</div>
				<div class="item13 ii  item31PHONE">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPhone}" />
				</div>
			</div>
			<div style="clear: both;"></div>
			<div class="navigationBar" style="width: 120px;">
				<ul>
					<li><a class="goLeftBut"></a></li>
					<li><a class="adian selectedaDian" position="1"></a></li>
					<li><a class="adian unselectedaDian" position="2"></a></li>
					<li><a class="toRightBut"></a></li>
				</ul>
			</div>
		</c:if>
		<c:if test="${bottomADBar.style == '2:2' }">
			<!-- PC端 -->
			<div class="adContainer item22ContPC">
				<div class="item13 item22first">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPC}" />
				</div>
				<div class="item13 item22last">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPC}" />
				</div>
			</div>
			<div class="adContainer item22ContPAD">
				<div class="item13 item22first">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPad}" />
				</div>
				<div class="item13 item22last">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPad}" />
				</div>
			</div>
			<div class="adContainer item22ContPHONE">
				<div class="item13 ii  item22first">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPhone}" />
				</div>
				<div class="item13 ii  item22last">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPhone}" />
				</div>
			</div>
			<div style="clear: both;"></div>
			<div class="navigationBar" style="width: 120px;">
				<ul>
					<li><a class="goLeftBut"></a></li>
					<li><a class="adian selectedaDian" position="1"></a></li>
					<li><a class="adian unselectedaDian" position="2"></a></li>
					<li><a class="toRightBut"></a></li>
				</ul>
			</div>
		</c:if>
		<c:if test="${bottomADBar.style == '1:1:2' }">
			<!-- PC端 -->
			<div class="adContainer item112ContPC">
				<div class="item13 item112first">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPC}" />
				</div>
				<div class="item13 item112notFist">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPC}" />
				</div>
				<div class="item13 item112notFist item112last">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(2).itemType == 0 }">toUrl="${bottomADBar.items.get(2).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(2).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(2).id}"</c:if>
						adItemId="${bottomADBar.items.get(2).id }"
						title="${bottomADBar.items.get(2).title }"
						src="${pictureHost }${bottomADBar.items.get(2).pictureIconPC}" />
				</div>
			</div>
			<!-- pad -->
			<div class="adContainer item112ContPAD">
				<div class="item13 item112Pad">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPad}" />
				</div>
				<div class="item13 item112Pad item112PadNotFirst">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPad}" />
				</div>
				<div class="item13 item112Pad item112PadNotFirst">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(2).itemType == 0 }">toUrl="${bottomADBar.items.get(2).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(2).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(2).id}"</c:if>
						adItemId="${bottomADBar.items.get(2).id }"
						title="${bottomADBar.items.get(2).title }"
						src="${pictureHost }${bottomADBar.items.get(2).pictureIconPad}" />
				</div>
			</div>
			<!-- PHONE -->
			<div class="adContainer item112ContPHONE">
				<div class="item13 ii item112Pad">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						.
               			title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPhone}" />
				</div>
				<div class="item13 ii item112Pad item112PadNotFirst">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPhone}" />
				</div>
				<div class="item13 ii item112Pad item112PadNotFirst">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(2).itemType == 0 }">toUrl="${bottomADBar.items.get(2).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(2).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(2).id}"</c:if>
						adItemId="${bottomADBar.items.get(2).id }"
						title="${bottomADBar.items.get(2).title }"
						src="${pictureHost }${bottomADBar.items.get(2).pictureIconPhone}" />
				</div>
			</div>
			<div style="clear: both;"></div>
			<div class="navigationBar" style="width: 150px;">
				<ul>
					<li><a class="goLeftBut"></a></li>
					<li><a class="adian selectedaDian" position="1"></a></li>
					<li><a class="adian unselectedaDian" position="2"></a></li>
					<li><a class="adian unselectedaDian" position="3"></a></li>
					<li><a class="toRightBut"></a></li>
				</ul>
			</div>
		</c:if>
		<c:if test="${bottomADBar.style == '2:1:1' }">
			<!-- PC端 -->
			<div class="adContainer item211ContPC">
				<div class="item13 item211first">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPC}" />
				</div>
				<div class="item13 item211notFist">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPC}" />
				</div>
				<div class="item13 item211notFist item112last">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(2).itemType == 0 }">toUrl="${bottomADBar.items.get(2).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(2).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(2).id}"</c:if>
						adItemId="${bottomADBar.items.get(2).id }"
						title="${bottomADBar.items.get(2).title }"
						src="${pictureHost }${bottomADBar.items.get(2).pictureIconPC}" />
				</div>
			</div>
			<!-- pad -->
			<div class="adContainer item211ContPAD">
				<div class="item13 item211Pad">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPad}" />
				</div>
				<div class="item13 item211Pad item112PadNotFirst">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPad}" />
				</div>
				<div class="item13 item211Pad item112PadNotFirst">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(2).itemType == 0 }">toUrl="${bottomADBar.items.get(2).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(2).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(2).id}"</c:if>
						adItemId="${bottomADBar.items.get(2).id }"
						title="${bottomADBar.items.get(2).title }"
						src="${pictureHost }${bottomADBar.items.get(2).pictureIconPad}" />
				</div>
			</div>
			<!-- PHONE -->
			<div class="adContainer item211ContPHONE">
				<div class="item13 ii  item211Pad">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPhone}" />
				</div>
				<div class="item13 ii  item211Pad item112PadNotFirst">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPhone}" />
				</div>
				<div class="item13 ii item211Pad item112PadNotFirst">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(2).itemType == 0 }">toUrl="${bottomADBar.items.get(2).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(2).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(2).id}"</c:if>
						adItemId="${bottomADBar.items.get(2).id }"
						title="${bottomADBar.items.get(2).title }"
						src="${pictureHost }${bottomADBar.items.get(2).pictureIconPhone}" />
				</div>
			</div>
			<div style="clear: both;"></div>
			<div class="navigationBar" style="width: 150px;">
				<ul>
					<li><a class="goLeftBut"></a></li>
					<li><a class="adian selectedaDian" position="1"></a></li>
					<li><a class="adian unselectedaDian" position="2"></a></li>
					<li><a class="adian unselectedaDian" position="3"></a></li>
					<li><a class="toRightBut"></a></li>
				</ul>
			</div>
		</c:if>
		<c:if test="${bottomADBar.style == '1:2:1' }">
			<!-- PC端 -->
			<div class="adContainer item121ContPC">
				<div class="item13 item121first">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPC}" />
				</div>
				<div class="item13 item121notFist">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPC}" />
				</div>
				<div class="item13 item121notFist item121last">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(2).itemType == 0 }">toUrl="${bottomADBar.items.get(2).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(2).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(2).id}"</c:if>
						adItemId="${bottomADBar.items.get(2).id }"
						title="${bottomADBar.items.get(2).title }"
						src="${pictureHost }${bottomADBar.items.get(2).pictureIconPC}" />
				</div>
			</div>
			<!-- pad -->
			<div class="adContainer item121ContPAD">
				<div class="item13 item121Pad">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPad}" />
				</div>
				<div class="item13 item121Pad item112PadNotFirst">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPad}" />
				</div>
				<div class="item13 item121Pad item112PadNotFirst">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(2).itemType == 0 }">toUrl="${bottomADBar.items.get(2).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(2).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(2).id}"</c:if>
						adItemId="${bottomADBar.items.get(2).id }"
						title="${bottomADBar.items.get(2).title }"
						src="${pictureHost }${bottomADBar.items.get(2).pictureIconPad}" />
				</div>
			</div>
			<!-- PHONE -->
			<div class="adContainer item121ContPHONE">
				<div class="item13 ii  item121Pad">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(0).itemType == 0 }">toUrl="${bottomADBar.items.get(0).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(0).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(0).id}"</c:if>
						adItemId="${bottomADBar.items.get(0).id }"
						title="${bottomADBar.items.get(0).title }"
						src="${pictureHost }${bottomADBar.items.get(0).pictureIconPhone}" />
				</div>
				<div class="item13 ii  item121Pad item112PadNotFirst">
					<img class="imgClass"
						<c:if test="${bottomADBar.items.get(1).itemType == 0 }">toUrl="${bottomADBar.items.get(1).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(1).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(1).id}"</c:if>
						adItemId="${bottomADBar.items.get(1).id }"
						title="${bottomADBar.items.get(1).title }"
						src="${pictureHost }${bottomADBar.items.get(1).pictureIconPhone}" />
				</div>
				<div class="item13 ii  item121Pad item112PadNotFirst">
					. <img class="imgClass"
						<c:if test="${bottomADBar.items.get(2).itemType == 0 }">toUrl="${bottomADBar.items.get(2).linkedUrl }"</c:if>
						<c:if test="${bottomADBar.items.get(2).itemType == 1 }">toUrl="${globalUrl}welcome.show?actionMethod=activityDetail&id=${bottomADBar.items.get(2).id}"</c:if>
						adItemId="${bottomADBar.items.get(2).id }"
						title="${bottomADBar.items.get(2).title }"
						src="${pictureHost }${bottomADBar.items.get(2).pictureIconPhone}" />
				</div>
			</div>
			<div style="clear: both;"></div>
			<div class="navigationBar" style="width: 150px;">
				<ul>
					<li><a class="goLeftBut"></a></li>
					<li><a class="adian selectedaDian" position="1"></a></li>
					<li><a class="adian unselectedaDian" position="2"></a></li>
					<li><a class="adian unselectedaDian" position="3"></a></li>
					<li><a class="toRightBut"></a></li>
				</ul>
			</div>
		</c:if>
	</div>
</c:if>
