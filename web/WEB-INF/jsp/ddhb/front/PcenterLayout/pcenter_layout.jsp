<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><tiles:insertAttribute name="pcenter.layout.title" /></title>
<link rel="stylesheet" type="text/css" href="${ht_globalUrl}/css/cmsDefault.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/home.css"/>
<link rel="stylesheet" type="text/css" href="${globalUrl}css/store_account.css">
<link rel="stylesheet" type="text/css" href="${globalUrl}css/global.css">
<link rel="shortcut icon" href="${globalUrl}images/favi_10.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="${ht_globalUrl}/js/tree/zTreeStyle/zTreeStyle.css"/>
<link rel="stylesheet" type="text/css" href="${ht_globalUrl}/js/dialog/aero/aero.css" />
<script type="text/javascript" src="${globalUrl}js/jquery-1.8.3.js"></script>
<script type="text/javascript">
var globalUrl = '${globalUrl}';
</script>
</head>
<body class="wideScreen themeStore">
	<div style="left: 0px; top: 0px; visibility: hidden; position: absolute;" class="">
		<table class="ui_border">
			<tbody>
				<tr>
					<td class="ui_lt"></td>
					<td class="ui_t"></td>
					<td class="ui_rt"></td>
				</tr>
				<tr>
					<td class="ui_l"></td>
					<td class="ui_c"><div class="ui_inner">
							<table class="ui_dialog">
								<tbody>
									<tr>
										<td colspan="2"><div class="ui_title_bar">
												<div unselectable="on" class="ui_title" style="cursor: move;"></div>
												<div class="ui_title_buttons">
													<a title="最小化" href="javascript:void(0);" class="ui_min"
														style="display: inline-block;"><b class="ui_min_b"></b></a><a
														title="最大化" href="javascript:void(0);" class="ui_max"
														style="display: inline-block;"><b class="ui_max_b"></b></a><a
														title="还原" href="javascript:void(0);" class="ui_res"><b
														class="ui_res_b"></b><b class="ui_res_t"></b></a><a
														title="关闭(esc键)" href="javascript:void(0);"
														class="ui_close" style="display: inline-block;">×</a>
												</div>
											</div></td>
									</tr>
									<tr>
										<td class="ui_icon" style="display: none;"></td>
										<td class="ui_main" style="width: auto; height: auto;"><div class="ui_content" style="padding: 10px;"></div></td>
									</tr>
									<tr>
										<td colspan="2"><div class="ui_buttons" style="display: none;"></div></td>
									</tr>
								</tbody>
							</table>
						</div></td>
					<td class="ui_r"></td>
				</tr>
				<tr>
					<td class="ui_lb"></td>
					<td class="ui_b"></td>
					<td class="ui_rb" style="cursor: se-resize;"></td>
				</tr>
			</tbody>
		</table>
	</div>
	<tiles:insertAttribute name="pcenter.layout.header" />
<div class="accountPrimary clearfix contWrap" style="height:auto;min-height:600px;">
	<tiles:insertAttribute name="pcenter.layout.left" />
	<div class="frameBox">
		<div class="crumbsHead crumbs mb10">
			<a href="${globalUrl}welcome.show?actionMethod=welcome">首页</a><span class="split">&gt;</span>
		</div>
		<div class="nNote nSuccess" id="success_box" style="display:none">
	    </div>
	    <div class="nNote nFailure" id="error_box" style="display:none">
	    </div>
		<div id="workspace">
			<tiles:insertAttribute name="pcenter.layout.right" />
		</div>
	</div>
</div>
<tiles:insertAttribute name="pcenter.layout.footer" />
</body>
</html>