<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-cn" />
<link rel="stylesheet" type="text/css" href="http://tj.5i5j.com/themes/new2013/files/css/jquery/blitzer/jquery-ui-1.8.23.custom.css?version=2013-12-23_10:30" />
<link rel="stylesheet" type="text/css" href="http://tj.5i5j.com/themes/new2013/files/map/css/main.css?version=2013-12-23_10:30" />
<link rel="stylesheet" type="text/css" href="http://tj.5i5j.com/themes/new2013/files/map/css/popup.css?version=2013-12-23_10:30" />
<link rel="stylesheet" type="text/css" href="http://tj.5i5j.com/themes/new2013/files/map/css/topic.css?version=2013-12-23_10:30" />
<link rel="stylesheet" type="text/css" href="http://tj.5i5j.com/themes/new2013/files/map/css/comment.css?version=2013-12-23_10:30" />
<link rel="stylesheet" type="text/css" href="http://tj.5i5j.com/themes/new2013/files/map/css/new_map.css?version=2013-12-23_10:30" />
<link rel="stylesheet" type="text/css" href="http://tj.5i5j.com/themes/new2013/files/map/css/mapfix.css?20130731?version=2013-12-23_10:30" />
<link rel="stylesheet" type="text/css" href="http://api.map.baidu.com/library/TrafficControl/1.4/src/TrafficControl_min.css" />
<link rel="stylesheet" type="text/css" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
<script type="text/javascript" src="${globalUrl}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${globalUrl}/js/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/DistanceTool/1.2/src/DistanceTool.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/TrafficControl/1.4/src/TrafficControl_min.js"></script>
<script type="text/javascript" src="http://tj.5i5j.com/themes/new2013/files/js/grade.js?version=2013-12-23_10:30"></script>
<title>【地图找房_按地图找二手房租房信息_地图找房源】-我爱我家官网</title>
<meta name="keywords" content="地图找房、按地图找二手房、地图找租房信息" />
<meta name="description" content="我爱我家为您提供地图找房、按地图找二手房、地图找租房信息,置业顾问推荐小区房源信息,根据地铁、定位、路况信息找房源就上伟业我爱我家,电话“95105890”" />
<meta name="robots" content="All" />
<link rel="Shortcut Icon" type="image/x-icon" href="/favicon.ico" />
</head>

<body>
	<!--转到列表页提示框-->
	<div class="popup_tip" style="display: none; z-index: 99991;">
		<div title="关闭" class="close"></div>
		<div class="title"></div>
		<div class="content">
			<div class="inner">
				<div class="arrow"></div>
				<h2>将为您转到列表页，谢谢！</h2>
				<div class="btn">
					<a href="javascript:void(0);" class="_ok">确定</a><a class="cjavascript:void(0);ncel _del" href="#">取消</a>
				</div>
			</div>
		</div>
	</div>
	<!--弹出框-->
	<div class="login_bg" style="display: none; z-index: 99990;"></div>
	<!--通知-->
	<div class="login_pop_box" style="display: none; z-index: 99991;" id="recommendPop"></div>

	<div class="position_tip">
		<div class="title"></div>
		<input id="searchAutoComplete" name="" type="text" />
		<div class="cl"></div>
		<div class="btn_box">
			<div class="go">
				<a href="javascript:void(0)" id="autoSearchGo"></a>
			</div>
			<div class="skip">
				<a href="javascript:void(0)">跳过此步</a>
			</div>
		</div>
		<div title="关闭" class="close"></div>
	</div>
	<div class="login_pop_box" id="ajax_login_box" style="display: none; z-index: 99991;">
		<div class="close_pop">
			<a title="关闭" href="javascript:void(0);"></a>
		</div>
		<div class="title_box">
			<h2>登录我爱我家</h2>
			<p>--我爱我家网站为您提供买卖租赁业务，让你体验优质服务！</p>
		</div>
		<div class="mbox">
			<div class="ll">
				<form target="_blank" id="login-form" action="/map" method="post">
					<div class="a1">
						<input name="LoginForm[username]" id="LoginForm_username"
							type="text" maxlength="15" />
					</div>
					<div class="a2">
						<input name="LoginForm[password]" id="LoginForm_password" type="password" maxlength="15" />
						<a title="登陆" href="javascript:void(0);" id="loginsubmit"></a>
					</div>
					<div class="cl"></div>
					<div class="loginInfo"></div>
					<div class="info">
						<div class="new"></div>
						<a href="javascript:void(0);">忘记密码</a> | <a href="#">注册新账号</a> | <a class="blue" href="#">地图找房使用帮助</a>
					</div>
					<div class="share">
						<h2>使用其他账号登录：</h2>
						<ul>
							<li class="sina"><a href="javascript:void(0);"></a></li>
							<li class="qq"><a href="javascript:void(0);"></a></li>
						</ul>
					</div>
				</form>
			</div>
		</div>
		<div class="cl"></div>
	</div>
	<div class="ssb_wrap" style="display: none; z-index: 99991;">
		<div class="a1" id="search_rent" style="display: none;">
			<div class="ssb_popup_box">
				<div class="title">
					<div class="close_pop">
						<a href="javascript:void(0);"></a>
					</div>
					<ul>
						<li class="m1"><a href="javascript:void(0);"></a></li>
						<li class="m2_current">
							<div class="m2_t_icon"></div> <a href="javascript:void(0);"></a>
						</li>
					</ul>
				</div>
				<div class="cl"></div>
				<div class="form_box">
					<!--地图租房搜索表单-->
					<form id="rentForm">
						<!--<input type="hidden" name="searchType" value="2">-->
						<!--租房需要传入的参数-->
						<input id='rmainArea' name='mainArea' type='hidden' />
						<!--区域-->
						<input id='rsubArea' name='subArea' type='hidden' />
						<!--区域下的版块-->
						<input id='rCostPre' name='hPricePre' type='hidden' />
						<!--租金-->
						<input id='rCostNext' name='hPriceNext' type='hidden' />
						<!--租金-->
						<input id='rAreaPre' name='hAreaPre' type='hidden' />
						<!--面积-->
						<input id='rAreaNext' name='hAreaNext' type='hidden' />
						<!--面积-->
						<input id='rFloor' name='rFloor' type='hidden' />
						<!--楼层-->
						<input id='rFace' name='rFace' type='hidden' />
						<!--朝向-->
						<input id='rSupport' name='rSupport' type='hidden' />
						<!--室内配套-->
						<input id='rHType' name='hType' type='hidden' />
						<!--房屋类型-->
						<input id='rDesc' name='rDesc' type='hidden' />
						<!--装修情况-->
						<input id='rRentType' name='rRentType' type='hidden' />
						<!--租赁类型-->

						<!--关键字搜索-->
						<div class="ssb">
							<div class="search_box2">
								<div class="s1">
									<div class="icon"></div>
									<input name="keyWord" id="mapRentKey" type="text" class="map_search_input" stype="rent" value="请输入关键字(楼盘名或路段名)" />
								</div>
								<div class="cl"></div>
							</div>
						</div>
					</form>
					<div class="cl"></div>

					<div class="item">
						<dl style="z-index: 10;">
							<dt>区域</dt>
							<dd>
								<div class="select_box" toValue="rmainArea" id="rentMainArea">
									<cite>选择区域</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0)" selectid='0'>不限</a>
										</div>
										<div class="list" x="120.15243" y="30.32502">
											<a selectid="1094" href="javascript:void(0)">拱墅</a>
										</div>
										<div class="list" x="120.18691" y="30.28702">
											<a selectid="1091" href="javascript:void(0)">下城</a>
										</div>
										<div class="list" x="120.17546" y="30.24897">
											<a selectid="1090" href="javascript:void(0)">上城</a>
										</div>
										<div class="list" x="120.21776" y="30.21436">
											<a selectid="1095" href="javascript:void(0)">滨江</a>
										</div>
										<div class="list" x="120.29829" y="30.42386">
											<a selectid="1096" href="javascript:void(0)">余杭</a>
										</div>
										<div class="list" x="120.27348" y="30.18265">
											<a selectid="1097" href="javascript:void(0)">萧山</a>
										</div>
										<div class="list" x="120.13438" y="30.26636">
											<a selectid="1092" href="javascript:void(0)">西湖</a>
										</div>
										<div class="list" x="120.21583" y="30.26461">
											<a selectid="1093" href="javascript:void(0)">江干</a>
										</div>
										<div class="list" x="0" y="0">
											<a selectid="1290" href="javascript:void(0)">富阳</a>
										</div>
									</div>
									<div class="arrow"></div>
								</div>
								<div class="select_box" toValue="rsubArea" id="rentSubArea">
									<cite>选择商圈</cite>
									<div class="sp_box"></div>
									<div class="arrow"></div>
								</div>
							</dd>
						</dl>
						<div class="cl"></div>
						<dl style="z-index: 9;">
							<dt>租金</dt>
							<dd>
								<div class="select_box" toValue="rCostPre">
									<cite>不限</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid='0'>不限</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='1'>1000元</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='2'>2000元</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='3'>3000元</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='4'>4000元</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='5'>5000元</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='6'>8000元</a>
										</div>

									</div>
									<div class="arrow"></div>
								</div>
								<div class="select_box" toValue="rCostNext">
									<cite>不限</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid='0'>不限</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='1'>1000元</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='2'>2000元</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='3'>3000元</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='4'>4000元</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='5'>5000元</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='6'>8000元</a>
										</div>

									</div>
									<div class="arrow"></div>
								</div>
							</dd>
						</dl>
						<div class="cl"></div>
						<dl>
							<dt>面积</dt>
							<dd>
								<div class="select_box" toValue="rAreaPre">
									<cite>不限</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid='0'>不限</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='1'>50㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='2'>70㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='3'>90㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='4'>110㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='5'>130㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='6'>150㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='7'>200㎡</a>
										</div>
									</div>
									<div class="arrow"></div>
								</div>
								<div class="select_box" toValue="rAreaNext">
									<cite>不限</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid='0'>不限</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='1'>50㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='2'>70㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='3'>90㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='4'>110㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='5'>130㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='6'>150㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='7'>200㎡</a>
										</div>

									</div>
									<div class="arrow"></div>
								</div>
							</dd>
						</dl>
					</div>
					<div class="item l_line">
						<dl class="w65" style="z-index: 10;">
							<dt>楼&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;层</dt>
							<dd>
								<div class="select_box" toValue="rFloor">
									<cite>不限</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid='0'>楼层</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='1'>低楼层</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='2'>中楼层</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='3'>高楼层</a>
										</div>
									</div>
									<div class="arrow"></div>
								</div>
							</dd>
						</dl>
						<div class="cl"></div>
						<dl class="w65" style="z-index: 9;">
							<dt>朝&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;向</dt>
							<dd>
								<div class="select_box" toValue="rFace">
									<cite>不限</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid='0'>朝向</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='1'>东</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='2'>西</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='3'>南</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='4'>北</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='5'>东南</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='6'>西南</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='7'>东北</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='8'>西北</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='9'>东西</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='10'>南北</a>
										</div>
									</div>
									<div class="arrow"></div>
								</div>
							</dd>
						</dl>
						<div class="cl"></div>
						<dl class="w65">
							<dt>室内配套</dt>
							<dd>
								<div class="select_box" toValue="rSupport">
									<cite>不限</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid='1'>电话</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='2'>洗衣机</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='3'>冰箱</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='4'>彩电</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='5'>空调</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='6'>热水器</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='7'>管道燃气</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='8'>暖气</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='9'>床</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='10'>网络</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='11'>电梯</a>
										</div>
									</div>
									<div class="arrow"></div>
								</div>
							</dd>
						</dl>
					</div>
					<div class="item l_line none">
						<dl class="w65" style="z-index: 10;">
							<dt>房屋类型</dt>
							<dd>
								<div class="select_box" toValue="rHType">
									<cite>不限</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid='0'>全部</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='1'>跃层复式</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='2'>单身公寓</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='3'>带包入住</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='4'>豪华装修</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='5'>商住两用</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='6'>短期出租</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='7'>经济合租</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='8'>佣金优惠</a>
										</div>
									</div>
									<div class="arrow"></div>
								</div>
							</dd>
						</dl>
						<div class="cl"></div>
						<dl class="w65" style="z-index: 9;">
							<dt>装修情况</dt>
							<dd>
								<div class="select_box" toValue="rDesc">
									<cite>不限</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid='0'></a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='1'>毛坯</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='2'>简装</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='3'>精装</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='4'>豪华</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='5'>其它</a>
										</div>
									</div>
									<div class="arrow"></div>
								</div>
							</dd>
						</dl>
						<div class="cl"></div>
						<dl class="w65">
							<dt>租赁类型</dt>
							<dd>
								<div class="select_box" toValue="rRentType">
									<cite>不限</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid='1'>整套</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='2'>合住</a>
										</div>

									</div>
									<div class="arrow"></div>
								</div>
							</dd>
						</dl>
					</div>
				</div>
				<div class="default_search_btn" id="rentSubBtn"></div>
				<!--<div class="go_btn2"><a href="javascript:void(0)"></a></div>-->
			</div>
		</div>
		<div class="a1" id="search_exchange">
			<div class="ssb_popup_box">
				<div class="title">
					<div class="close_pop">
						<a href="javascript:void(0);"></a>
					</div>
					<ul>
						<li class="m1_current">
							<div class="m1_t_icon"></div> <a href="javascript:void(0);"></a>
						</li>
						<li class="m2"><a href="javascript:void(0);"></a></li>
					</ul>
				</div>
				<div class="cl"></div>
				<div class="form_box">
					<!--地图二手房搜索表单-->
					<form id="exchangeForm">
						<input id='mainArea' name="mainArea" type='hidden' />
						<!--区域-->
						<input id='subArea' name="subArea" type='hidden' />
						<!--区域下的版块-->
						<input id='hPricePre' name='hPricePre' type='hidden' />
						<!--价格-->
						<input id='hPriceNext' name='hPriceNext' type='hidden' />
						<!--价格-->
						<input id='hAreaPre' name='hAreaPre' type='hidden' />
						<!--面积-->
						<input id='hAreaNext' name='hAreaNext' type='hidden' />
						<!--面积-->
						<input id='hRoomType' name='hRoomType' type='hidden' />
						<!--户型-->
						<input id='hAge' name='hAge' type='hidden' />
						<!--房龄-->
						<input id='hPark' name='hPark' type='hidden' />
						<!--停车位-->
						<input id='hType' name='hType' type='hidden' />
						<!--房屋类型-->
						<!--<input id='hCircle' name='hCircle' type='hidden'/>环线-->
						<input id='hChar' name='hChar' type='hidden' />
						<!--特色房源-->

						<!--关键字搜索-->
						<div class="ssb">
							<div class="search_box2">
								<div class="s1">
									<div class="icon"></div>
									<input id="mapExchangeKey" name="keyWord" type="text" class="map_search_input" stype="exchange" value="请输入关键字(楼盘名或路段名)">
								</div>
								<div class="cl"></div>
							</div>
						</div>
					</form>
					<div class="cl"></div>
					<div class="item">
						<dl style="z-index: 10;">
							<dt>区域</dt>
							<dd>
								<div class="select_box" toValue='mainArea' id="exchangeMainArea">
									<cite>选择区域</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0)" selectid='0'>不限</a>
										</div>
										<div class="list" x="120.15243" y="30.32502">
											<a selectid="1094" href="javascript:void(0)">拱墅</a>
										</div>
										<div class="list" x="120.18691" y="30.28702">
											<a selectid="1091" href="javascript:void(0)">下城</a>
										</div>
										<div class="list" x="120.17546" y="30.24897">
											<a selectid="1090" href="javascript:void(0)">上城</a>
										</div>
										<div class="list" x="120.21776" y="30.21436">
											<a selectid="1095" href="javascript:void(0)">滨江</a>
										</div>
										<div class="list" x="120.29829" y="30.42386">
											<a selectid="1096" href="javascript:void(0)">余杭</a>
										</div>
										<div class="list" x="120.27348" y="30.18265">
											<a selectid="1097" href="javascript:void(0)">萧山</a>
										</div>
										<div class="list" x="120.13438" y="30.26636">
											<a selectid="1092" href="javascript:void(0)">西湖</a>
										</div>
										<div class="list" x="120.21583" y="30.26461">
											<a selectid="1093" href="javascript:void(0)">江干</a>
										</div>
										<div class="list" x="0" y="0">
											<a selectid="1290" href="javascript:void(0)">富阳</a>
										</div>
									</div>
									<div class="arrow"></div>
								</div>
								<div class="select_box" toValue="subArea" id="exchangeSubArea"
									style="z-index: 1;">
									<cite>选择商圈</cite>
									<div class="sp_box"></div>
									<div class="arrow"></div>
								</div>
							</dd>
						</dl>
						<div class="cl"></div>
						<dl style="z-index: 9;">
							<dt>价格</dt>
							<dd>
								<div class="select_box" toValue="hPricePre">
									<cite>不限</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid='0'>不限</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='1'>50万</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='2'>80万</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='3'>100万</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='4'>150万</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='5'>200万</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='6'>300万</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='7'>500万</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='8'>500万</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='9'>800万</a>
										</div>

									</div>
									<div class="arrow"></div>
								</div>
								<div class="select_box" toValue="hPriceNext">
									<cite>不限</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid='0'>不限</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='1'>50万</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='2'>80万</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='3'>100万</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='4'>150万</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='5'>200万</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='6'>300万</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='7'>500万</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='8'>500万</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='9'>800万</a>
										</div>

									</div>
									<div class="arrow"></div>
								</div>
							</dd>
						</dl>
						<div class="cl"></div>
						<dl>
							<dt>面积</dt>
							<dd>
								<div class="select_box" toValue="hAreaPre">
									<cite>不限</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid='0'>不限</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='1'>50㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='2'>70㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='3'>90㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='4'>110㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='5'>130㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='6'>150㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='7'>200㎡</a>
										</div>
									</div>
									<div class="arrow"></div>
								</div>
								<div class="select_box" toValue="hAreaNext">
									<cite>不限</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid='0'>不限</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='1'>50㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='2'>70㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='3'>90㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='4'>110㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='5'>130㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='6'>150㎡</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='7'>200㎡</a>
										</div>

									</div>
									<div class="arrow"></div>
								</div>
							</dd>
						</dl>
					</div>
					<div class="item l_line">
						<dl class="w65" style="z-index: 10;">
							<dt>户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型</dt>
							<dd>
								<div class="select_box" toValue="hRoomType">
									<cite>选择户型</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid='0'>不限</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='1'>一居</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='2'>二居</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='3'>三居</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='4'>四居</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='5'>五居</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='6'>六居</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='7'>六居以上</a>
										</div>

									</div>
									<div class="arrow"></div>
								</div>
							</dd>
						</dl>
						<div class="cl"></div>
						<dl class="w65" style="z-index: 9;">
							<dt>房&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄</dt>
							<dd>
								<div class="select_box" toValue="hAge">
									<cite>不限</cite> <input type="hidden" name="hage">
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid='0'>不限</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='1'>1995年前</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='2'>1995-2000年</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='3'>2000-2005年</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='4'>2005-2010年</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='5'>2010年后</a>
										</div>

									</div>
									<div class="arrow"></div>
								</div>
							</dd>
						</dl>
						<div class="cl"></div>
						<dl class="w65">
							<dt>停&nbsp;车&nbsp;位</dt>
							<dd>
								<div class="select_box" toValue="hPark">
									<cite>不限</cite> <input type="hidden" name="pa">
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid='0'>不限</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='1'>有</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='2'>无</a>
										</div>
									</div>
									<div class="arrow"></div>
								</div>
							</dd>
						</dl>
					</div>
					<div class="item l_line">
						<dl class="w65" style="z-index: 10;">
							<dt>房屋类型</dt>
							<dd>
								<div class="select_box" toValue="hType">
									<cite>不限</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid='0'>不限</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='1'>已购公房</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='2'>商品房</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='3'>空置房</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='4'>使用权房</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='5'>央产</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='6'>其它</a>
										</div>
									</div>
									<div class="arrow"></div>
								</div>
							</dd>
						</dl>
						<!-- 
						<div class="cl"></div>
						<dl class="w65" style="z-index: 9;">
							<dt>环&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;线</dt>
							<dd>
								<div class="select_box" toValue="hCircle">
									<cite>不限</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid=''></a>
										</div>

									</div>
									<div class="arrow"></div>
								</div>
							</dd>
						</dl>
 						-->

						<div class="cl"></div>
						<dl class="w65">
							<dt>特色房源</dt>
							<dd>
								<div class="select_box" toValue="hChar">
									<cite>不限</cite>
									<div class="sp_box">
										<div class="list">
											<a href="javascript:void(0);" selectid='0'>不限</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='1'>学区</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='2'>商业</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='3'>新推</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='4'>钥匙</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='5'>特价</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='6'>婚房</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='7'>免税</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='8'>精装</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='9'>商住</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='10'>地铁</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='11'>低价</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='12'>观景</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='13'>免佣</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='14'>毛坯</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='15'>宜居</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='16'>阳光</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='17'>独家</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='18'>养生</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='19'>急售</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='20'>准新</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='21'>落户</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='22'>别墅</a>
										</div>
										<div class="list">
											<a href="javascript:void(0);" selectid='23'>豪宅</a>
										</div>
									</div>
									<div class="arrow"></div>
								</div>
							</dd>
						</dl>
					</div>
					</form>
				</div>
				<div class="default_search_btn" id="exchangeSubBtn"></div>
				<!--<div class="go_btn2"><a href="javascript:void(0)"></a></div>-->
			</div>
		</div>
	</div>

	<!--浏览过的小区-->
	<div class="bh_house" style="display: none; z-index: 99990;"></div>
	<!--end-->
	<!--地图头部-->
	<div id="new_map">
		<div class="new_map_logo">
			<img src="/themes/new2013/files/map/image/new_map_logo.gif" />
		</div>
		<div class="new_map_ss1">
			<h2>
				<input type="text" value="输入关键字（楼盘名或地段名）" />
			</h2>
		</div>
		<div class="new_map_ss2">
			<img src="/themes/new2013/files/map/image/new_map_ss2.gif" />
		</div>
		<div class="new_map_ss view_house">
			<a href="javascript:void(0)"></a>
		</div>
		<div class="new_map_ss subWay">
			<a href="javascript:void(0)"></a>
			<div id="new_map_xlk">
				<div class="new_map_xlk_wz">
					<ul>
						<li line="36" x="12022211" y="3026015">1号线</li>
						<li line="37" x="0" y="0">2号线</li>
						<li line="38" x="0" y="0">3号线</li>
						<li line="39" x="0" y="0">4号线</li>
						<li line="40" x="0" y="0">5号线</li>
						<li line="41" x="0" y="0">6号线</li>
						<li line="42" x="0" y="0">7号线</li>
						<li line="49" x="0" y="0">8号线</li>
					</ul>
				</div>
				<div class="new_map_xlk_jt">
					<img src="/themes/new2013/files/map/image/new_map_jt.png" />
				</div>
			</div>
		</div>
		<div class="new_map_tel">
			<img src="/themes/new2013/files/map/image/new_map_del.gif" />
		</div>
	</div>
	<!--地铁-->
	<div class="cl"></div>
	<div id="center">
		<div class="l_box">
			<div class="contrast_box" style='display: none;'>
				<div class="title">
					<div title="关闭" class="close"></div>
					<h2>房源对比</h2>
				</div>
				<div class="sub_info">
					你已经选择了 <span class="f14 emp_r english_font">1</span> 条房源
				</div>
				<div id="loadContrastBox"></div>
				<div class="c_btn">
					<a href="javascript:void(0)" id="mapContrastBtn"></a>
				</div>
			</div>
			<div class="map_box">
				<ul class="m_position_box">
					<li class="m1" title="圈选"></li>
					<li class="m2" title="定位"></li>
				</ul>
				<div class="nav" style="display: none;">
					<div class="title">
						<div title="展开" class="hide" style="display: none;"></div>
						<div title="收起" class="show" style="display: block;"></div>
					</div>
					<div class="item_box" style="display: block;">
						<div class="item m7">
							<a href="javascript:void(0)">定位</a>
						</div>
						<div class="item m6">
							<div class="new"></div>
							<a href="javascript:void(0);" class="yellow" id="_quanxuan"
								drawingtype="rectangle">圈选</a>
						</div>
						<div class="item m3">
							<a href="javascript:void(0);">路况信息</a>
						</div>

<%--
						<ul class="sub sub2" id="menu_sub_3">
							<li class="m1"><a href="javascript:void(0)">实时路况</a></li>
							<li class="m2"><a href="javascript:void(0)">路况预测</a></li>
							<!--实时路况-->
							<div class="ms_popup" id="trafficInfo1">
								<div title="关闭" class="close"></div>
								<div class="graphic"></div>
								<div class="refresh">
									<div class="refresh_icon"></div>
									更新时间：09:24
								</div>
							</div>
							<!--路况预测-->

							<div class="ms_popup" id="trafficInfo2">
								<div title="关闭" class="close"></div>
								<div class="graphic"></div>
								<p>星期日： 11:14</p>
								<div class="date">
									<span>一</span><span>二</span><span>三</span><span>四</span><span>五</span><span>六</span><span>日</span>
									<div class="cl"></div>
									<div class="th">星期</div>
								</div>
								<div class="date">
									<div class="th">时间</div>
									<div class="hour_bg">
										<div class="bar01">
											<div class="num">10:00</div>
										</div>
										<div class="bar02"></div>
									</div>
									<div class="sub_info">（基于历史路况统计预测 仅供参考）</div>
								</div>
							</div>
						</ul>

						<div class="item m4">
							<a href="javascript:void(0);">工具</a>
						</div>
						<ul class="sub sub2" id="menu_sub_4">
							<li class="m3"><a href="javascript:void(0);">测距</a></li>
							<li class="m4"><a href="#">截图</a></li>
							<li class="m5"><a href="javascript:void(0);">标记</a></li>
							<li class="m6"><a href="javascript:void(0);">分享</a></li>
							<!--分享box-->
							<div class="share_popup" id="share_popup">
								<div class="popup_main">
									<div class="share_title">您可将当前地图上的内容分享给好友</div>
									<div class="close" title="关闭"></div>
									<div class="content">
										<div class="mapshare_container">
											<div id="getLinkBtnCon" style="display: none;">
												<input type="button" value="复制"></input>
											</div>
											<input id="linkText" class="getLinkInput" type="text" value="">
												<div class="bds_tools">
													<span class="shareToSpan">分享到:</span> <span class="more_btn_span">>></span>
												</div>
										</div>
									</div>
								</div>
							</div>
							<li class="m7"><a href="javascript:void(0);">打印</a></li>
						</ul>
 --%>
 
						<div class="item m1"><a href="javascript:void(0);">厌恶设施</a></div>
						<ul class="sub" id="menu_sub_1">
							<li><input type="checkbox" value="加油站" />加油站</li>
							<li><input type="checkbox" value="变电所" />变电所</li>
							<li><input type="checkbox" value="高压电塔" />高压电塔</li>
							<li><input type="checkbox" value="殡仪馆" />殡仪馆</li>
							<li><input type="checkbox" value="垃圾场" />垃圾场</li>
						</ul>
					</div>
				</div>
				<div id="communityDetail" class="map_pop_box login_pop_box shadow" style="display: none;"></div>
				<div style="overflow: hidden; position: relative;">
					<div id="communityTip" class="mp_info02">
						<div class="ll_box">
							<img src="" width="100" height="75" style="display: block;">
						</div>
						<div class="rr_box" style="overflow: hidden;">
							<h2><a class="blue" href="javascript:void(0)"></a></h2>
							<p>均价：<span class="emp_y"></span>元/平<br> 相比上月：<span class="emp_y"></span></p>
						</div>
					</div>
					<div id="dituContent" class="map"></div>
				</div>
			</div>
			<div class="cl"></div>
		</div>
		<div class="cl"></div>
		<div class="r_box">
			<div class="user_info_box">
				<div class="login_box">
					亲，您还未登录。很多功能无法为您提供。<br />
					请<a href="/newLogin/login">登录</a> （第一次进入的用户可 <a href="/newLogin/register">注册</a> ）
				</div>
			</div>
			<div class="cl"></div>
			<div class="e_toolbar" style="display: none;">
				<ul class="a1">
					<li class="i1 current" title="房源"><a href="javascript:void(0);"></a></li>
					<!--
					<li class="i2" title="职业顾问"><a href="javascript:void(0);"></a></li>
          <li class="i3" title="店面"><a href="javascript:void(0);"></a></li>
          -->
					<li class="i4" title="学校"><a href="javascript:void(0);"></a></li>
					<li class="i5" title="超市"><a href="javascript:void(0);"></a></li>
					<li class="i6" title="医院"><a href="javascript:void(0);"></a></li>
					<li class="i8" title="KTV"><a href="javascript:void(0);"></a></li>
					<li class="i9" title="酒店"><a href="javascript:void(0);"></a></li>
					<li class="i7" title="展开更多"><a href="javascript:void(0);"></a></li>
				</ul>
				<!--弹出图标，当前状态加current-->
				<ul class="a2" style="display: none;">
					<li class="i3" title="银行"><a href="javascript:void(0);"></a></li>
					<li class="i4" title="酒吧"><a href="javascript:void(0);"></a></li>
					<li class="i5" title="邮局"><a href="javascript:void(0);"></a></li>
					<li class="i6" title="购物"><a href="javascript:void(0);"></a></li>
					<li class="i7" title="ATM"><a href="javascript:void(0);"></a></li>
					<!--
					<li class="i8" title="KTV"><a href="javascript:void(0);"></a></li>
          <li class="i9" title="酒店"><a href="javascript:void(0);"></a></li>
         	-->
					<li class="i10" title="网吧"><a href="javascript:void(0);"></a></li>
					<li class="i11" title="咖啡厅"><a href="javascript:void(0);"></a></li>
					<li class="i12" title="洗浴中心"><a href="javascript:void(0);"></a></li>
					<!--<li class="i13" title="加油站"><a href="javascript:void(0);"></a></li>-->
					<li class="i14" title="景点"><a href="javascript:void(0);"></a></li>
					<li class="close"><a title="关闭" href="javascript:void(0);"></a></li>
				</ul>
				<div class="cl"></div>
			</div>
			<div class="cl"></div>
			<div id="right_index" style="position: relative;">
				<div class="slide_bar h_524">
					<div class="bar">
						<div class="mousearea"></div>
					</div>
				</div>
				<div class="scroll" style="display: block;">
					<div style="left: 0px;">
						<div class="title">
							<span><a href="javascript:void(0);" id="topicLoad">更多&gt;&gt;</a></span>活动专题
						</div>
						<div class="topic" id="slides" slideNum="7">
							<!--不可用状态在previous和next后面加disable-->
							<div class="prev previous disable" style="position: absolute; z-index: 100;">
								<a href="javascript:void(0);"></a>
							</div>
							<div class="next nextlink disable" style="position: absolute; z-index: 100;">
								<a href="javascript:void(0);"></a>
							</div>
							<div class="slides_container" style="overflow: hidden; position: relative; display: block; height: 105px;">
								<a style="height: 105px; width: 270px; overflow: hidden;" href="http://hz.5i5j.com/zt/singles/hz.html" target="_blank">
									<img width="270" src="http://tj.5i5j.com/themes/new2013/files/map/image/topic/guanggj.jpg?version=2013-12-23_10:30" alt="" />
								</a>
								<a style="height: 105px; width: 270px; overflow: hidden;" href="http://hz.5i5j.com/septemberAndOctober" target="_blank">
									<img width="270" src="http://tj.5i5j.com/themes/new2013/files/map/image/topic/jinjiuyinshi.jpg?version=2013-12-23_10:30" alt="" />
								</a> 
								<a style="height: 105px; width: 270px; overflow: hidden;" href="http://hz.5i5j.com/schoolHouse" target="_blank">
									<img width="270" src="http://tj.5i5j.com/themes/new2013/files/map/image/topic/xuequfang.jpg?version=2013-12-23_10:30" alt="" />
								</a> 
								<a style="height: 105px; width: 270px; overflow: hidden;" href="http://hz.5i5j.com/recruitment" target="_blank">
									<img width="270" src="http://tj.5i5j.com/themes/new2013/files/map/image/topic/zhaopin.jpg?version=2013-12-23_10:30" alt="" />
								</a> 
								<a style="height: 105px; width: 270px; overflow: hidden;" href="http://hz.5i5j.com/tencent" target="_blank">
									<img width="270" src="http://tj.5i5j.com/themes/new2013/files/map/image/topic/weibozufang.jpg?version=2013-12-23_10:30" alt="" />
								</a> 
								<a style="height: 105px; width: 270px; overflow: hidden;" href="http://hz.5i5j.com/zuyou" target="_blank">
									<img width="270" src="http://tj.5i5j.com/themes/new2013/files/map/image/topic/hezu.jpg?version=2013-12-23_10:30" alt="" />
								</a> 
								<a style="height: 105px; width: 270px; overflow: hidden;" href="http://hz.5i5j.com/marriage" target="_blank">
									<img width="270" src="http://tj.5i5j.com/themes/new2013/files/map/image/topic/hunfang.jpg?version=2013-12-23_10:30" alt="" />
								</a>
							</div>
							<div class="cl"></div>
						</div>
						<div class="title">大家正在看</div>
						<div class="cmbox_wrap">
							<div class="arrow"></div>
							<div class="cmbox line_b">
								<ul class="allViewUl">
									<li x='120.14253' y='30.29385' communityId="20439" data='{"id":"20439","x":"12014253","y":"3029385","point":"120.14253|30.29385","districtName":"\u6587\u4e00\u8def","address":"\u534e\u6d77\u56ed","selladd":"\u6587\u4e00\u8def\u767d\u8361\u6d77\u4eba\u5bb6\u897f\u9762","price":"29991","img":"section\/2\/20439\/pihpalkb9a99c645.JPG","numExchange":"114","numRent":"34","meangrade":4.4}'>
										<div class="viewIntro">
											<span>有87个人正在看</span><span>华海园</span>
											<div class="cl"></div>
										</div>
										<div class="t1" style="display: none;">
											<h2>华海园</h2>
											<div class="info_box">
												<div class="pic">
													<img width="100" height="75" src="http://image.5i5j.com/picture/section/2/20439/pihpalkb9a99c645.JPG?version=2013-12-23_10:30" alt="" />
												</div>
												<div class="rr">
													<p style="display: block;">
														均价：<span class="emp_r english_font">29991</span>元/平米<br />文一路白荡海人家西面...
													</p>
													<div class="sell">114套</div>
													<div class="hire">34套</div>
												</div>
												<div class="cl"></div>
											</div>
										</div>
									</li>
									<li x='120.17954' y='30.24646' communityId="21513" data='{"id":"21513","x":"12017954","y":"3024646","point":"120.17954|30.24646","districtName":"\u57ce\u7ad9","address":"\u4fe1\u4f59\u91cc","selladd":"\u6cb3\u574a\u8857\u4e0e\u4e2d\u6cb3\u5357\u8def\u4ea4\u754c\u53e3","price":"21550","img":"section\/2\/21513\/nknaacpa3a5a8152.jpg","numExchange":"53","numRent":"29","meangrade":5}'>
										<div class="viewIntro">
											<span>有63个人正在看</span><span>信余里</span>
											<div class="cl"></div>
										</div>
										<div class="t1" style="display: none;">
											<h2>信余里</h2>
											<div class="info_box">
												<div class="pic">
													<img width="100" height="75" src="http://image.5i5j.com/picture/section/2/21513/nknaacpa3a5a8152.jpg?version=2013-12-23_10:30" alt="" />
												</div>
												<div class="rr">
													<p style="display: block;">
														均价：<span class="emp_r english_font">21550</span>元/平米<br />河坊街与中河南路交界...
													</p>
													<div class="sell">53套</div>
													<div class="hire">29套</div>
												</div>
												<div class="cl"></div>
											</div>
										</div>
									</li>
									<li x='120.2245' y='30.20536' communityId="206881" data='{"id":"206881","x":"12022450","y":"3020536","point":"120.2245|30.20536","districtName":"\u897f\u5174","address":"\u4e1c\u65b9\u90e1\u4e1c\u533a","selladd":"\u6ee8\u6c5f\u533a\u6625\u6653\u8def233\u53f7","price":"25117","img":"","numExchange":"166","numRent":"82","meangrade":4.4}'>
										<div class="viewIntro">
											<span>有17个人正在看</span><span>东方郡东区</span>
											<div class="cl"></div>
										</div>
										<div class="t1" style="display: none;">
											<h2>东方郡东区</h2>
											<div class="info_box">
												<div class="pic">
													<img width="100" height="75" src="http://tj.5i5j.com/themes/new2013/files/map/image/house_pic01.jpg?version=2013-12-23_10:30" alt="" />
												</div>
												<div class="rr">
													<p style="display: block;">
														均价：<span class="emp_r english_font">25117</span>元/平米<br />滨江区春晓路233号...
													</p>
													<div class="sell">166套</div>
													<div class="hire">82套</div>
												</div>
												<div class="cl"></div>
											</div>
										</div>
									</li>
									<li x='120.32401' y='30.31745' communityId="80338" data='{"id":"80338","x":"12032401","y":"3031745","point":"120.32401|30.31745","districtName":"\u4e5d\u5821","address":"\u5de6\u90bb\u53f3\u820d\u516c\u5bd3","selladd":"\u5de6\u90bb\u53f3\u820d\u516c\u5bd3","price":"15571","img":"section\/8\/80338\/plbedbnhe7b59dc8.jpg","numExchange":"81","numRent":"33","meangrade":4.4}'> <div class="viewIntro">
											<span>有19个人正在看</span><span>左邻右舍公寓</span>
											<div class="cl"></div>
										</div>
										<div class="t1" style="display: none;">
											<h2>左邻右舍公寓</h2>
											<div class="info_box">
												<div class="pic">
													<img width="100" height="75"
														src="http://image.5i5j.com/picture/section/8/80338/plbedbnhe7b59dc8.jpg?version=2013-12-23_10:30"
														alt="" />
												</div>
												<div class="rr">
													<p style="display: block;">
														均价：<span class="emp_r english_font">15571</span>元/平米<br />左邻右舍公寓
													</p>
													<div class="sell">81套</div>
													<div class="hire">33套</div>
												</div>
												<div class="cl"></div>
											</div>
										</div>
									</li>
									<li x='120.15019' y='30.28388' communityId="73912" data='{"id":"73912","x":"12015019","y":"3028388","point":"120.15019|30.28388","districtName":"\u672a\u77e5","address":"\u897f\u6eaa\u6570\u7801\u6e2f","selladd":"\u6587\u4e09\u8def","price":0,"img":"","numExchange":"0","numRent":"0","meangrade":4.8}'>
										<div class="viewIntro">
											<span>有89个人正在看</span><span>西溪数码港</span>
											<div class="cl"></div>
										</div>
										<div class="t1" style="display: none;">
											<h2>西溪数码港</h2>
											<div class="info_box">
												<div class="pic">
													<img width="100" height="75"
														src="http://tj.5i5j.com/themes/new2013/files/map/image/house_pic01.jpg?version=2013-12-23_10:30"
														alt="" />
												</div>
												<div class="rr">
													<p style="display: block;">
														均价：<span class="emp_r english_font">0</span>元/平米<br />文三路
													</p>
													<div class="sell">0套</div>
													<div class="hire">0套</div>
												</div>
												<div class="cl"></div>
											</div>
										</div>
									</li>
								</ul>
							</div>
							<div class="cl"></div>
						</div>
						<div class="ad_box">
							<a href="http://www.5i5j.com/4008" target="_blank"><img width="270" height="98" src="http://tj.5i5j.com/themes/new2013/files/map/image/4008980.jpg?version=2013-12-23_10:30" alt="" /></a>
						</div>
						<div class="title">热门标签</div>
						<div class="cmbox_wrap" style="margin-bottom: 10px;">
							<div class="cmbox">
								<div class="arrow"></div>
								<div class="t2">
									<p>
										<a href="/exchange/t1v1/" target="_blank">学区</a> | <a href="/exchange/t2v1/" target="_blank">商业</a> | <a href="/exchange/t3v1/" target="_blank">新推</a> | <a href="/exchange/t4v1/" target="_blank">钥匙</a> | <a href="/exchange/t5v1/" target="_blank">婚房</a> | <a href="/exchange/t6v1/" target="_blank">特价</a> | <a href="/exchange/t7v1/" target="_blank">免税</a> | <a href="/exchange/t8v1/" target="_blank">精装</a> | <a href="/exchange/t9v1/" target="_blank">地铁</a> | <a href="/exchange/t10v1/" target="_blank">低价</a> | <a href="/exchange/t11v1/" target="_blank">免佣</a> | <a href="/exchange/t12v1/" target="_blank">毛坯</a> | <a href="/exchange/t13v1/" target="_blank">独家</a> | <a href="/exchange/t14v1/" target="_blank">急售</a> | <a href="/exchange/t15v1/" target="_blank">准新</a> | <a href="/exchange/t16v1/" target="_blank">落户</a> | <a href="/exchange/t17v1/" target="_blank">别墅</a> | <a href="/exchange/t18v1/" target="_blank">豪宅</a>
									</p>
								</div>
							</div>
							<div class="cl"></div>
						</div>
					</div>
				</div>
			</div>
			<div id="rightRewrite" style="display: none;">
				<div id="communityInfo"></div>
				<div id="localSearchList">
					<div class="cmbox2">
						<div class="t1">
							<div class="sdr_title">
								<div class="arrow"></div>
								<span class="f14"></span>
							</div>
							<!--二选一,其中arrow和arrow2为向上和向下的的三角图标-->
							<ul class="sdr_title_tab">
								<li class="tab1 selected"><div class="arrow"></div>
									<a href="javascript:void(0)">二手房</a></li>
								<li class="tab2 ">
									<div class="arrow3" style="display: none;"></div>
									<a href="javascript:void(0)">租房</a>
								</li>
							</ul>
						</div>
					</div>
					<div id="searchList">
						<div class="cmbox_wrap nomargin">
							<div class="slide_bar h_524">
								<div class="bar">
									<div class="mousearea"></div>
								</div>
							</div>
							<div class="cmbox line_b h_524"></div>
						</div>
						<div class="cl"></div>
					</div>
					<div class="cl"></div>
				</div>
				<div id="communityRecommend"></div>
				<div id="mycomment"></div>
			</div>
		</div>
	</div>
	<div class="cl"></div>
</div>
<div class="cl"></div>

<script type="text/javascript">
var _5i5jmap;
var domainRoot = '5i5j.com/map/';
var isToolShow = false; //推荐小区工具条专题判断变量
var communityHouseType = 'all';
var autoSearchVal = ''; //定位搜索的值
var autoSearchMaker = ''; //定位搜索的标注
$(function() {
	var options = {
		x : '120.167255402',
		y : '30.2460924147',
		cityName : '杭州',
		dataUrl : '/map/api/',
		defaultZoom : 10,
		mapBoxId : 'dituContent'
	};
	_5i5jmap = new myMap(options);
	//加载地图
	_5i5jmap.loadMap();
});
function myMap(options) {
	this.loadDataAjax = null;
	this.mapStatu = 'area';
	this.operate = { loadList : false };
	this.tools = { myDis : null };
	this.options = {
		x : '',
		y : '',
		cityName : '',
		dataUrl : '',
		defaultZoom : 13,
		mapBoxId : 'dituContent'
	};
	this.condition = {
		zoom : null,
		center : null, //经度:this.condition.center.lng 纬度:this.condition.center.lat
		x : null,
		y : null,
		bounds : null,
		sw : null, //经度:sw.lng//纬度:sw.lat
		ne : null, //经度:ne.lng//纬度:ne.lat
		searchType : '', //搜索类型[默认,二手房,租房]
		searchData : '' //执行条件,当搜索类型不为空时有效
	};
	this.community = {
		id : '', //当前小区ID
		x : '',
		y : '',
		data : '', //当前小区的json格式信息
		key : '', //当前小区的本地搜索关键词
		index : 0,
		keys : new Array(),
		localSearchResult : null,
		infoList : null
	}
	this.subWay = {//地铁搜索参数
		isSubWay : false,
		line : ''
	}
	this.mapObj = null;
	if (options) {
		if (options.x) { this.options.x = options.x; }
		if (options.y) { this.options.y = options.y; }
		if (options.cityName) { this.options.cityName = options.cityName; }
		if (options.dataUrl) { this.options.dataUrl = options.dataUrl; }
		if (options.defaultZoom) { this.options.defaultZoom = options.defaultZoom; }
		if (options.mapBoxId) { this.options.mapBoxId = options.mapBoxId; }
	}
	//设置地图窗口的大小及绑定窗口改变大小时的地图窗口大小
	mapBoxResize();
	$(window).resize(function() {
		mapBoxResize();
	});
}
/**
 * 载入地图并载入当前城市的城区
 * 绑定城区点击事件
 * @returns {undefined}
 */
myMap.prototype.loadMap = function() {
	var self = this;
	//加载页面交互事件(如鼠标移动到标注上等操作)
	_5i5jmap.addPageEvent();
	//创建地图对象
	_5i5jmap.mapObj = new BMap.Map(this.options.mapBoxId);
	//绑定事件,设置地图执行完全放大后移除放大事件
	_5i5jmap.mapObj.addEventListener('zoomend', zoomendEvent);
	_5i5jmap.mapObj.addEventListener('moveend', moveendEvent);
	//设置地图信息
	this.mapObj.enableScrollWheelZoom(); //禁用地图拖拽。
	this.mapObj.enableDragging();        //禁用滚轮放大缩小。
	this.mapObj.enableDoubleClickZoom(); //禁用双击放大。
	this.mapObj.addControl(new BMap.NavigationControl({ anchor : BMAP_ANCHOR_TOP_LEFT })); //添加绽放平移控件
	this.mapObj.centerAndZoom(new BMap.Point(this.options.x, this.options.y), this.options.defaultZoom); //设定地图的中心点和坐标并将地图显示在地图容器中
	this.mapObj.setCurrentCity(this.options.cityName); //设置当前显示城市,需要与坐标点一致
	//添加一些工具
	this.tools.myDis = new BMapLib.DistanceTool(this.mapObj);
	this.trafficInfo();
	//定位输入自动提示
	this.autoCompleteShow();
	this.searchAutoComplete();
};
</script>

<script type="text/javascript">
/**
 * 点击遮罩层及关闭按钮时执行关闭操作
 * @updateTime 2013-05-27 11:11
 */
$(function() {
	$('.login_bg,.ssb_popup_box .close_pop').live('click', function() {
		$('.login_bg').hide();
		//动画收起浏览过的小区
		$('.bh_house').animate({ height : '0px' }, 500, function() {
			$(this).hide();
		});
		if ($(this).hasClass('login_bg') && !($('.ssb_wrap').is(':hidden'))) {
			$('.login_bg').show();
		}
		if ($(this).hasClass('close_pop')) {
			$('.ssb_wrap').hide();
			$('.ssb').hide();
		}
		//定位
		if (!$('.position_tip').is(':hidden')) {
			$('.login_bg').show();
		}
		$('.login_pop_box').hide();
		// $('.search_box .s3').removeClass('hoverCli'); //浏览过的小区去掉背景
		$('#new_map .view_house a').removeClass('hoverCli'); //浏览过的小区去掉背景
	});
});

/**
 * 地图菜单功能
 */
$(function() {
	//菜单点击以及鼠标滑过效果
	var isListShow = true;
	$('.nav .item_box .item').click(function() {
		var thisDom = $(this);
		var netxtUl = thisDom.next('ul.sub');
		var parent = $(this).parent();
		var listUl = parent.find('ul.sub');
		//去除当前显示
		$('.nav .item_box .item').each(function() {
			$(this).removeClass('current');
		});
		//添加当前显示
		thisDom.addClass('current');

		//判断当前的菜单是否显示
		if (netxtUl.is(':hidden')) {
			isListShow = false;
		}

		//隐藏所有
		listUl.each(function() {
			$(this).hide();
		});

		//隐藏路况信息
		if (!$('#tcWrap').is(':hidden')) {
			$('#tcWrap #tcClose').trigger('click');
		}

		//隐藏分享功能
		$('#share_popup').hide();

		if (!isListShow) {
			netxtUl.show();
			isListShow = true;
		}
	}).mouseover(function() {
		var thisDom = $(this);
		//去除当前显示
		$('.nav .item_box .item').each(function() {
			if (($(this).next('ul.sub').is(":hidden"))) {
				$(this).removeClass('current');
			}
		});
		//添加当前显示
		thisDom.addClass('current');
	}).mouseout(function() {
		var nextUl = $(this).next('ul.sub');
		if (($(this).hasClass('m3') && nextUl.is(":hidden"))) {
			$(this).removeClass('current');
		}

		if ($(this).hasClass('m3') && $('#tcWrap').is(":hidden")) {
			$(this).removeClass('current');
		}
	});

	//关闭路况信息之后 去除菜单路况信息当前显示
	$('#tcClose').click(function() {
		$('.nav .item_box .m3').removeClass('current');
	});
	//地图菜单显隐
	$('.nav .title').click(function() {
		var title = $('.nav .title');
		$('.nav .item_box').slideToggle('fast', function() {
			if ($(this).is(':hidden')) {
				title.find('.hide').show();
				title.find('.show').hide();
			} else {
				title.find('.hide').hide();
				title.find('.show').show();
			}
		});
	});
	//菜单路况信息
	/*$('#menu_sub_3 li.m1').live({
	 click:function(){
	 $('#trafficInfo2').hide();
	 $('#trafficInfo1').toggle();
	 }
	 });
	 
	 $('#menu_sub_3 li.m2').live({
	 click:function(){
	 $('#trafficInfo1').hide();
	 $('#trafficInfo2').toggle();
	 }
	 });
	 
	 $('#trafficInfo1 .close, #trafficInfo2 .close').live({
	 click:function(){
	 $(this).parent().hide();
	 }
	 });*/

	//菜单分享
	$('#menu_sub_4 li.m6').live({
		click : function() {
			$('#share_popup').toggle();
		}
	});
	$('#share_popup .close').live({
		click : function() {
			$('#share_popup').hide();
		}
	});

	//显示更多分享按钮
	$('.bds_tools .more_btn_span').live({
		click : function() {
			$(this).hide();
			$('.bds_tools .more_btn').show();
		}
	});

	//菜单事件绑定{需要修改}
	$('.nav .item_box #menu_sub_1 li input:checkbox').live(
			'change',
			function() {
				var self = _5i5jmap;
				LocationSearch(self.community.key);
			}
	);

	//头部城市选择显隐
	var isTopCityShow = false;
	$('#header .city').live({
		mouseover : function() {
			var cityObj = $(this);
			cityObj.addClass('cityHover');
			$('#header .citymenu').show();
			isTopCityShow = true;
			$('#header .citymenu').mouseover(function() {
				$(this).show();
				cityObj.addClass('cityHover');
				isTopCityShow = true;
			}).mouseleave(function() {
				$(this).hide();
				cityObj.removeClass('cityHover');
				isTopCityShow = false;
			});
		},
		mouseleave : function() {
			if (isTopCityShow) {
				$('#header .citymenu').hide();
				$(this).removeClass('cityHover');
			} else {
				$('#header .citymenu').show();
			}
		}
	});

	//头部更多显隐
	var isTopMoreShow = false;
	$('#header .more').live({
		mouseover : function() {
			$('#header .moreList').show();
			isTopMoreShow = true;
			$('#header .moreList').mouseover(function() {
				$(this).show();
				isTopMoreShow = true;
			}).mouseleave(function() {
				$(this).hide();
				isTopMoreShow = false;
			});
		},
		mouseleave : function() {
			if (isTopMoreShow) {
				$('#header .moreList').hide();
			} else {
				$('#header .moreList').show();
			}
		}
	});

	//菜单定位显隐 (地图左上角 定位)
	$('.nav .item_box div.m7, .m_position_box .m2').live({
		click : function() {
			//$(this).addClass('current');
			$('.login_bg').show();
			$('.popup_tip').hide();
			$('.position_tip').show();
		}
	});

	$('.position_tip .close, .position_tip .btn_box .skip').live(
			'click', function() {
				$('.login_bg').hide();
				$('.position_tip').hide();
			}
	);
});


/**
 * 专题活动功能
 */
$(function() {
	//加载更多专题
	$('#loadMoreTopic').live('click', function() {
		var now = $(this);
		now.hide();
		var start = now.attr('start');
		$.ajax({
			url : '/map/moresubject',
			data : { start : start },
			success : function(data) {
				if ($.isEmptyObject(data)) {
					now.hide();
				} else {
					var oldlen = $('#topicUlwraper').find('li').length;
					$('#topicUlwraper').append(data);
					var newlen = $('#topicUlwraper').find('li').length;
					now.attr('start', length);
					var step = newlen - oldlen;
					if (step >= '8') { now.show(); }
				}
			}
		});
	});

	/**
	 * 显示更多专题活动
	 * @Modified lu.yingchuan@brightac.com.cn
	 * @updateTime 2013-05-25 15:47
	 */
	$('#topicLoad').live('click', function() {
		if ($('#topicBox').length == 0) {
			$('body').append('<div id="topicBox" class="topic_wrap"></div>');
			$.ajax({
				url : '/map/loadtopic/',
				success : function(data) {
					//$('#topicBox').height($('body').height());
					$('#topicBox').html(data);
					fly($('#topicBox'), 1000);
					$('body').attr({ style : "overflow:auto" });
					$('html').attr({ style : "overflow:auto" });
				}
			});
		} else {
			$('body').attr({ style : "overflow:auto" });
			$('html').attr({ style : "overflow:auto" });
			$('#topicBox').show();
			fly($('#topicBox'), 1000);
		}
	});
	
	//更多专题zoom效果    
	$('#topicUlwraper li .pic').live('mouseover', function() {
		var zoomDiv = '<div class="zoom"></div>';
		$(this).prepend(zoomDiv);
	}).live('mouseleave', function() {
		if ($(this).find('.zoom')) {
			$(this).find('.zoom').remove();
		}
	});
					
	/**
	 * 收起专题活动
	 *  @Modified lu.yingchuan@brightac.com.cn
	 * @updateTime 2013-05-25 15:47
	 */
	$('.ll_box .t2').live('click', function() {
		$('body').attr({ style : "overflow:hidden" });
		$('html').attr({ style : "overflow:hidden" });
		fly($('#topicBox'), 1000);
		$('body').scrollTop(0);
	});
					
	/**
	 * 右侧活动专题幻灯效果
	 */
	var slideNum;
	$('#slides').slides({
		play : 5000,
		pause : 2500,
		hoverPause : true,
		generatePagination : false
	});

	$('#slides .prev,#slides .next').live({
		mouseover : function() {
			$(this).removeClass('disable');
		},
		mouseout : function() {
			$(this).addClass('disable');
		}
	});
});


/**
 * 大家都在看
 */
$(function() {
	var self = _5i5jmap;
	$(".cmbox .allViewUl li").each(function(index) {
		if (index == 0) {
			$(this).find('.viewIntro').hide();
			$(this).find('.t1').show();
		} else {
			$(this).find('.viewIntro').show();
			$(this).find('.t1').hide();
		}
	});
	$(".cmbox .allViewUl li").live({
		mouseover : function() {
			$(".cmbox .allViewUl li .viewIntro").show();
			$(".cmbox .allViewUl li .t1").hide();
			$(this).find('.viewIntro').hide();
			$(this).find('.t1').show();
		},
		click : function() {
			if ($(this).attr('x') != '0' && $(this).attr('y') != '0') {
				self.mapObj.clearOverlays();
				self.community.id = $(this).attr('communityId');
				_5i5jmap.mapStatu = 'community';
				_5i5jmap.operate.loadList = false;
				_5i5jmap.mapObj.setZoom(15);
				_5i5jmap.loadTheCommunity();
				//self.mapObj.panTo(new BMap.Point($(this).attr('x'), $(this).attr('y')));
			}
		}
	});
	mapBoxResize();
});

/**
 * 显示浏览过的小区或房源
 * @Modified lu.yingchuan@brightac.com.cn
 * @updateTime 2013-05-27 11:11
 */
$(function() {
	var isOver = false;
	var isClick = false;
	$('#new_map .view_house a').live({
		click : function() {
			$('.bh_house').load('/map/loadBrowseHouses/', function() {
				isClick = true;
				// $('.search_box .s3').addClass('hoverCli');
				$(this).addClass('hoverCli');
				$('.bh_house').show();
				$('.bh_house').animate({ height : '330px' }, 500);
				$('.login_bg').show();
				visitHousesScroll();
			});
		},
		mouseover : function() {
			isOver = true;
			$(this).addClass('hoverCli');
		},
		mouseout : function() {
			if (!isClick && isOver) {
				$(this).removeClass('hoverCli');
				isOver = false;
			}
			isClick = false;
		}
	});
	
	$('.bh_house .bh1 ul li .item .b1').live({
		mouseover : function() {
			$('.bh_house .bh1 ul li .item .b1').show();
			$('.bh_house .bh1 ul li .item .b2').hide();
			$(this).hide();
			$(this).parent('.item').find('.b2').show();
		}
	});
	
	$('.bh_house .bh1 ul li .item .b2').live({
		mouseleave : function() {
			$('.bh_house .bh1 ul li .item .b1').show();
			$('.bh_house .bh1 ul li .item .b2').hide();
		}
	});
	
	/**
	 * 显示搜索框
	 */
	//旧版地图
	$('.search_box .s1,.search_box .s1_go').live('click', function() {
		$('.login_bg').show();
		$('.ssb').show();
		$('.ssb_wrap').show();
		//鼠标焦点在弹出框的输入框中
		$('#mapExchangeKey')[0].focus();
	});

	//新版地图
	$('#new_map .new_map_ss1 input, #new_map .new_map_ss2').live('click', function() {
		$('.login_bg').show();
		$('.ssb').show();
		$('.ssb_wrap').show();
		//鼠标焦点在弹出框的输入框中
		$('#mapExchangeKey')[0].focus();
	});

	/**
	 * 显示地铁线路
	 */
	var isSubWayShow = false;
	$('#new_map .subWay a').live({
		mouseover : function() {
			var subWay = $(this);
			$('#new_map_xlk').show();
			subWay.addClass('ahover');
			isSubWayShow = true;
			$('#new_map_xlk').mouseover(function() {
				$(this).show();
				subWay.addClass('ahover');
				isSubWayShow = true;
			}).mouseleave(function() {
				$(this).hide();
				subWay.removeClass('ahover');
				isSubWayShow = false;
			});
		},
		mouseleave : function() {
			if (isSubWayShow) {
				$('#new_map_xlk').hide();
				$('#new_map .subWay a').removeClass('ahover');
			} else {
				$('#new_map_xlk').show();
			}
		}
	});
});

/**
 * 当前小区 右侧二手房鼠标滑动效果
 */
$(function() {
	$('#communityInfo .theCommunityexchange .cmbox .t1, #communityInfo .theCommunityrent  .cmbox .t1').live({
		mouseover : function() {
			$(this).removeClass('no_bg');
		},
		mouseout : function() {
			$(this).addClass('no_bg');
		}
	});
});

/**
 * 登陆注册功能,通知功能
 */
$(function() {
	/**
	 * 载入我的评论{需要修改}
	 */
	$('#commentLink').live('click', function() {
		$.ajax({
			url : '/map/loadComment/',
			success : function(data) {
				if (data == 'guest') {
					$('.login_bg,#ajax_login_box').show();
				} else {
					if ($('#right_index').is(':hidden')) {
						$('#rightRewrite #mycomment').html(data).show().siblings().hide();
					} else {
						$('#right_index').hide();
						$('#rightRewrite').show();
						$('#rightRewrite #mycomment').html(data).show().siblings().hide();
					}
					$('#commentLink').siblings().removeClass('current');
					$('#commentLink').parent().addClass('current');
					mapBoxResize();
				}
			}
		});
	});
 
	/**
	 * 用户登陆
	 */
	$('#loginsubmit').live('click', function() {
		$(this).html('');
		ajaxLogin();
		return false;
	});
	
	/**
	 * 显示登陆框
	 */
	$('#ajajxloginlink').live('click', function() {
		$('.login_bg,#ajax_login_box').show();
	});
	
	/**
	 * 显示注册框
	 */
	$('#ajajxregisterlink').live('click', function() {

	});
	
	/**
	 * 关闭注册框
	 */
	$('#ajax_login_box .close_pop').live('click', function() {
		if ($('.bh_house').is(':hidden')) {
			$('#ajax_login_box,.login_bg').hide();
		} else {
			$('#ajax_login_box').hide();
		}
	});

	//点击通知
	$('#noticePop').live({
		click : function() {
			$('#recommendPop').load('/map/notice', function() {
				$('.login_bg').show();
				$(this).show();
			});
		}
	});
	
	//弹出框为你推荐
	$('#recommendPop .pr_box li').live({
		mouseover : function() {
			$(this).addClass('current');
		},
		mouseout : function() {
			$(this).removeClass('current');
		}
	});

	//关闭为你推荐
	$('#recommendPop .close_pop').live('click', function() {
		$('#recommendPop').hide();
		$('.login_bg').hide();
	});

	//个人中心显隐
	$('.user_info_box li.m2').live('mouseover', function() {
		$('.user_info_box .m2_popup').show();
	});

	$('.m2_popup').live('mouseleave', function() {
		$(this).hide();
	});
});

/**
 * 根据当前窗口大小修改地图调试
 * @returns {undefined}
 */
function mapBoxResize() {
	//获取可视区域高度
	var bodyHeight = $(document.body).height();
	//获取内容区域调试
	var centerHeight = bodyHeight - 68;
	if (centerHeight < 500) {
		centerHeight = 500;
	}
	$('#center').height(centerHeight);
	//获取地图区域高度
	//var mapBoxHeight = centerHeight - 34 - 25 - 20 - 1;                
	$('#center .map_box .map').height(centerHeight);
	//自适应右侧调试
	//                //console.log(centerHeight);
	rightBoxResize(centerHeight);
	//获取地图宽度
	var centerWidth = $('#center').width();
	$('#center .map_box').width(centerWidth - 1);
	//显示滚动条
	$('.slide_bar').show();
}

/**
 * 右侧调试自适应
 */
function rightBoxResize(centerHeight) {
	//右侧可改变内容区域高度
	var rightContentHeight = centerHeight - 20 - 77;
	if ($('#right_index').css('display') == 'block') {
		//console.log($('#right_index').css('display'));
		//console.log('right_index reset');
		rightContentHeight = rightContentHeight;
		$('#right_index').height(rightContentHeight);
		$('#right_index .slide_bar').height(rightContentHeight);
		$('#right_index .scroll').height(rightContentHeight);
		readScroll('#right_index .scroll');
	} else {
		//console.log($('#rightRewrite').css('display'));
		if ($('#rightRewrite #communityInfo').css('display') == 'block') {
			//console.log('communityInfo reset');
			var cmbox_wrap_height = rightContentHeight - 57; //工具栏
			cmbox_wrap_height = cmbox_wrap_height - $('#communityInfo .cmbox2').height() - 8 - 1; //小区信息及选项卡
			$('#rightRewrite #communityInfo .set_info_house .cmbox_wrap').height(cmbox_wrap_height);
			$('#rightRewrite #communityInfo .set_info_house .line_b').height(cmbox_wrap_height);
			$('#rightRewrite #communityInfo .set_info_house .h_524').height(cmbox_wrap_height);
			readScroll('#rightRewrite #communityInfo .theCommunityexchange .line_b');
			readScroll('#rightRewrite #communityInfo .theCommunityrent .line_b');
		}
		if ($('#rightRewrite #localSearchList').css('display') == 'block') {
			//console.log('localSearchList reset');
			var cmbox_wrap_height = rightContentHeight - 57; //工具栏
			cmbox_wrap_height = cmbox_wrap_height - $('#localSearchList .cmbox2').height() - 8 - 1; //小区信息及选项卡
			$('#rightRewrite #localSearchList #searchList .cmbox_wrap').height(cmbox_wrap_height);
			$('#rightRewrite #localSearchList #searchList .line_b').height( cmbox_wrap_height );
			$('#rightRewrite #localSearchList #searchList .h_524').height( cmbox_wrap_height );
			readScroll('#rightRewrite #localSearchList #searchList .line_b');
		}
		if ($('#rightRewrite #communityRecommend').css('display') == 'block') {
			//console.log('communityRecommend reset');
			var cmbox_wrap_height = rightContentHeight - 55;
			$('#communityRecommend #recommendComunity .cmbox_wrap').height(cmbox_wrap_height);
			$('#communityRecommend #recommendComunity .line_b').height(cmbox_wrap_height);
			$('#communityRecommend #recommendComunity .h_524').height(cmbox_wrap_height);
			readScroll('#communityRecommend #recommendComunity .line_b');
		}
		if ($('#rightRewrite #mycomment').css('display') == 'block') {
			//console.log('mycomment reset');          
			var cmbox_wrap_height = rightContentHeight - 55;
			$('#mycomment .cmbox_wrap').height(cmbox_wrap_height);
			$('#mycomment .line_b').height(cmbox_wrap_height);
			$('#mycomment .h_524').height(cmbox_wrap_height);
			readScroll('#rightRewrite #mycomment .line_b');
		}
	}
}

/**
 * 调用滚动条
 * @param {type} obj
 * @param {type} horizontalType
 * @returns {undefined}
 */
function readScroll(obj, horizontalType) {
	//当前正在移动的item
	var activeItem;
	try {
		$(obj).sly('destroy');
	} catch (e) {
	}
	$(obj).sly({
		horizontal : horizontalType, //为1时,横向滚动
		itemNav : 'basic',
		smart : 1,
		activateOn : 'click',
		mouseDragging : 1,
		touchDragging : 1,
		releaseSwing : 1,
		startAt : 0,
		scrollBar : $(obj).parent().find('.slide_bar'),
		scrollBy : 1,
		activatePageOn : 'click',
		speed : 500,
		easing : 'easeOutExpo',
		dragHandle : 1,
		dynamicHandle : 1,
		clickBar : 1
	},
	{
		load : function() {
			$(obj).parent().find('.slide_bar .bar').height(
					$(obj).parent().find('.slide_bar .bar').height() - 2);

			if ($(obj).parent().find('.slide_bar .bar_arrow').length == '0') {
				$(obj).parent().find('.slide_bar .bar').after('<div class="bar_arrow"></div>');
			}
			if ($(obj).parents('.set_info_house').length != '0') {
				$(obj).parent().find('.slide_bar').css('top', '-31px');
			}
		},
		move : function() {
			//滚动时隐藏小区工具条
			$('#recommendToolbar').find('.tool_bar01').hide();
		},
		active : function(eventName, item) { activeItem = item; },
		moveEnd : function() {
			if (obj.indexOf("recommendComunity") > 0) {
				//触发 推荐小区 中鼠标滑过事件
				var nowItem = $(obj).find('div.t1').eq(activeItem);
				nowItem.trigger('mouseover', [ '1' ]);
			}
		}
	})
};

/**
 * 左右对齐事件
 * @param {type} index
 * @returns {undefined}
 */
function markerMouseout(index) {
	var marker_img = $('.BMap_Marker[class="BMap_Marker"]').eq(index).find('img');
	marker_img.css('left', marker_img.position().left - 240);
	//marker_img.css('top', marker_img.position().top);
}
function markerMouseover(index) {
	var marker_img = $('.BMap_Marker[class="BMap_Marker"]').eq(index).find('img');
	marker_img.css('left', marker_img.position().left + 240);
	//marker_img.css('top', marker_img.position().top);
}

/**
 * 关闭所有的浮层(非地图内部)
 */
function closeFloatDiv(exclude) {
	$('.login_bg').hide();
	$('.bh_house').hide();
	$('.ssb').hide();
	$('.ssb_wrap').hide();
	if (exclude == 'thecommunity') {
		$('#ajax_login_box').hide();
	} else {
		$('.login_pop_box').hide();
	}
	$('.search_box .s3').removeClass('hoverCli'); //浏览过的小区去掉背景
}

/**
 * 把对象从屏幕外飞入或飞出到屏幕外
 * @param {type} obj
 * @param {type} time
 * @returns {undefined}
 */
function fly(obj, t) {
	if (obj.css('left') == '0px') {
		obj.animate({ left : obj.width() + 'px' }, t, function() { obj.hide(); });
	} else {
		$('#topicBox').css('left', $('body').width());
		obj.animate({ left : '0px' }, t);
	}
}

/**
 * 鼠标移动上面的事件
 */
function map_house_list_pop_mouseover(obj) {
	obj.attr('index', obj.css('z-index'));
	obj.css('z-index', 0);
	var customTop = parseInt(obj.offset().top) - 183;
	var customLeft = parseInt(obj.offset().left - 112);
	$('#communityTip').attr({
		style : 'top:' + customTop + 'px;left:' + customLeft + 'px'
	});
	$('#communityTip a').html(obj.attr('community'));
	//气泡提示上的图片
	var imgUrl = obj.attr('imgurl');
	$('#communityTip .ll_box img').attr('src', imgUrl);
	//均价
	var price = obj.attr('price');
	$('#communityTip .rr_box p .emp_y').eq('0').html(price);
	//同比上月
	var percentage = obj.attr('percentage');
	var priceType = obj.attr('priceType');
	if (priceType == '1') {
		percentage = '↑：' + percentage + '%';
	} else {
		percentage = '↓：' + percentage + '%'
	}
	$('#communityTip .rr_box p .emp_y').eq('1').html(percentage);
	$('#communityTip').show();
	var className = obj.attr('class');
	switch (className) {
	case 'map_pop01':
		$('.map_pop01').removeClass('map_pop01_cur');
		obj.addClass('map_pop01_cur');
		break;
	case 'map_pop_exchange':
		$('.map_pop_exchange').removeClass('map_pop_exchange_cur');
		obj.addClass('map_pop_exchange_cur');
		break;
	case 'map_pop_rent':
		$('.map_pop_rent').removeClass('map_pop_rent_cur');
		obj.addClass('map_pop_rent_cur');
		break;
	}
}

function map_house_list_pop_mouseout(obj) {
	obj.css('z-index', obj.attr('index'));
	$('#communityTip').hide();
	if ($.trim(obj.attr('isopen')) != 'true') {
		if (obj.hasClass('map_pop01_cur')) {
			obj.removeClass('map_pop01_cur');
		} else if (obj.hasClass('map_pop_exchange_cur')) {
			obj.removeClass('map_pop_exchange_cur');
		} else if (obj.hasClass('map_pop_rent_cur')) {
			obj.removeClass('map_pop_rent_cur');
		}
	}
}

/**
 *关注小区
 */
$(function() {
	$('.attentionCommunity').live('click', function() {
		var thisObj = $(this);
		var communityid = thisObj.attr('communityId');
		var type = $(this).attr('type');
		$.ajax({
			type : "post",
			url : "community/favorites",
			dataType : 'json',
			data : { id : communityid },
			success : function(data) {
				if (data.status == 'success') {
					alert('关注成功！');
					if (type == 'recommend') {
						thisObj.addClass('map_main_hover');
						thisObj.find('span').html('已关注');
					}
				} else if (data.status == 'recall') {
					alert("您已经关注过该小区了！");
				} else {
					$('.login_bg').show();
					$('#ajax_login_box').show();
				}
			}
		});
	});
});

/**
 * 右侧工具栏
 */
$(function() {
	var self = _5i5jmap;
	//右侧工具栏事件绑定
	$('.e_toolbar .a1 li,.e_toolbar .a2 li').live('click', function() {
		self.community.key = '';//初始关键字
		//显示当前小区详情
		if ($(this).hasClass('i1') && $(this).parent().hasClass('a1')) {
			$('#rightRewrite #communityInfo').show().siblings().hide();
			self.mapObj.clearOverlays(); //清除地图上所有标注
			self.addTheCommunity(); //添加本小区标注
			$('#communityDetail').show();
			return false;
		}
	
		if ($(this).hasClass('i7') && $(this).parent().hasClass('a1') || $(this).hasClass('close') && $(this).parent().hasClass('a2')) {
			$('.e_toolbar .a2').slideToggle('normal');
			return false;
		}
		$('#communityDetail').hide();
		$('#communityTip').hide();
		//console.log('start location search');
		//$('#rightRewrite #localSearchList').html('本地搜索结果' + $(this).attr('title')).show().siblings().hide();
		LocationSearch($(this).attr('title'));
	});
});

/**
 * 小区弹出介绍信息功能
 */
$(function() {
	//周边配套显隐
	var isShow = false;
	//显示小区周边配套
	$('#communityDetail .phd_box_01 .peitao').live({
		click : function() {
			$("#communityDetail .phd_box_03").show();
			$("#communityDetail .phd_box_03").animate({ left : 0 }, 1000);
		}
	});
	
	//隐藏小区周边配套
	$("#communityDetail .phd_box_03 .aa").live({
		click : function() {
			//$("#communityDetail .phd_box_03").hide().css('left', '668px');
			$("#communityDetail .phd_box_03").animate({ left : 698 }, 1000);
		},
		mouseover : function() {
			$(this).addClass('aaHover');
		},
		mouseout : function() {
			$(this).removeClass('aaHover');
		}
	});
	//点击小区周边配套内容的效果
	$("#communityDetail .phd_box_03 .bb .info_box a").live({
		click : function() {
			var title = $(this).attr('title');
			//触发工具条上对应的配套事件
			$('.e_toolbar ul li').each(function() {
				var barTitle = $(this).attr('title');
				if (title == barTitle) {
					$(this).trigger('click');
					if ($(this).parent().hasClass('a2')) {
						$(this).parent().show();
					}
				}
			});
		}
	});
	//地图小区周边小区
	$('.login_map').click(function() {
		$(this).find('.around').toggle('slow');
	});
	//小区配套工具条
	$('#communityToolbar .a2 .close').click(function() {
		$('#communityToolbar .a2').hide();
	});
	var toolLiHover = false;
	var toolLiClick = false;
	$('.e_toolbar ul li').live({
		click : function() {
			var className = $(this).attr('class');
			if (className != 'i7') {
				$('.e_toolbar ul li').each(function() {
					$(this).removeClass('current');
				});
				$(this).addClass('current');
				toolLiClick = true;
			}
		},
		mouseover : function() {
			if (!$(this).hasClass("current")) {
				$(this).addClass('current');
				toolLiHover = true;
				toolLiClick = false;
			}
		},
		mouseout : function() {
			if (toolLiHover && !toolLiClick) {
				$(this).removeClass('current');
				toolLiHover = false;
				toolLiClick = false;
			}
		}
	});

	/*
	 *小区弹出框中的点击看过这个小区的人还看了 加载当前小区
	 */
	$('#communityDetail .phd_box_02 .communityWrap a').live('click', function() {
		if ($(this).attr('x') != '0' && $(this).attr('y') != '0') {
			//$('#communityDetail .close_pop').trigger('click');
			_5i5jmap.mapStatu = 'community';
			_5i5jmap.operate.loadList = false;
			_5i5jmap.mapObj.panTo(new BMap.Point($(this).attr('x'),
					$(this).attr('y')));
			_5i5jmap.community.id = $(this).attr('communityId');
			_5i5jmap.mapObj.setZoom(14);
			_5i5jmap.mapObj.clearOverlays();
			_5i5jmap.loadTheCommunity();
		}
	});
});


/**
 * 小区评论
 */
$(function() {
	//小区评论的字数限制提示
	$('.textarea_box #comment').live('keyup', function(evt) {
		var titleLength = $(this).val().length;
		evt = evt ? evt : window.event;
		if (evt.keyCode != 13) {
			if (titleLength >= 100) {
				alert("评论长度不能超过100个字");
			}
		}
	});

	/**
	 * 显示小区详细及评论
	 * @Creater lu.yingchuan@brightac.com.cn
	 * @createTime 2013-05-25 16:28
	 * @updateTime 2013-06-14 17:37
	 */
	var mapCommunityId = '';
	$('.phd_box_02 .communityComment,#recommendComunity .tool_bar01 .commenttool, #myCommentList .t1').live('click', function() {
		var val = $(this).attr('val');

		if (($('#communityCom').length == 0) || (mapCommunityId != val)) {
			if ($('#communityCom').length == 0) {
				$('body').append('<div id="communityCom"></div>');
			} else {
				$('#communityCom').html('');
				$('#communityCom').show();
			}
			$.ajax({
				url : '/map/community/',
				data : { id : val },
				success : function(data) {
					$('#communityCom').height('auto');
					$('#communityCom').css('z-index', 99989);
					$('#communityCom').css('min-height', '1000px');
					$('#communityCom').html(data);
					fly($('#communityCom'), 1000);
					mapCommunityId = val;
					$('body').attr({ style : "overflow:auto" });
					$('html').attr({ style : "overflow:auto" });
				}
			});
		} else {
			$('#communityCom').show();
			fly($('#communityCom'), 1000);
			$('body').attr({ style : "overflow:auto" });
			$('html').attr({ style : "overflow:auto" });
		}
	});

	/**
	 * 收起小区详细及评论
	 * @Creater lu.yingchuan@brightac.com.cn
	 * @createTime 2013-05-25 16:38
	 */
	$('#communityReturn').live('click', function() {
		$('body').attr({ style : "overflow:hidden" });
		$('html').attr({ style : "overflow:hidden" });
		$('#ajax_login_box').css({ 'top' : '20%' }); //重新定位登陆框
		fly($('#communityCom'), 1000);
		$('body').scrollTop(0);
	});

	/**
	 * 推荐小区工具条    
	 */
	$('#center .r_box ul.tool_bar01 .map_main').live({
		mouseover : function() {
			if (!$(this).hasClass('map_main_hover')) {
				$(this).addClass('map_main_hover');
			}
		},
		mouseout : function() {
			var html = $(this).find('span').html();
			if (html != '已关注') {
				$(this).removeClass('map_main_hover');
			}
		}
	});

	//小区评论页 我要评论hover效果
	$('.comment_box .c4_box .comment_btn').live({
		mouseover : function() {
			$(this).addClass('comment_btn_hover');
		},
		mouseout : function() {
			$(this).removeClass('comment_btn_hover');
		}
	});

	//小区评论页 提交评论hover效果
	$('.comment_box .c4_box .submit_btn').live({
		mouseover : function() {
			$(this).addClass('submit_btn_hover');
		},
		mouseout : function() {
			$(this).removeClass('submit_btn_hover');
		}
	});

	//添加小区印象
	$('.comment_box .tag_box .add').live('click', function() {
		$('.comment_box .cc .snt_box').toggle();
	});

	$('.comment_box .cc .snt_box a').live('click', function() {
		var tag = $('#tagImpressive').val();
		var communityid = $(this).attr('communityId');

		if (tag == '') {
			alert('请填写印象！');
			return false;
		} else {
			$.ajax({
				type : 'POST',
				url : '/community/writeTag/',
				dataType : 'json',
				data : { tag : escape(tag), section_id : communityid },
				success : function(result) {
					switch (result.status) {
					case 'ok':
						var conentDom = '<a href="javascript:void(0)">' + tag + '(1)</a>';
						$('.comment_box .tag_box').prepend(conentDom);
						alert('您的印象评价是 "' + tag + '"');
						break;
					case 'tagerror':
						alert('您输入的印象包含敏感词汇，请您修改后重新输入！');
						break;
					case 'error':
						alert('标记印象时发生错误,请重试!');
						break;
					case 'full':
						alert('您今天已经对该小区评价5次，请不要过多的评价一个小区！');
						break;
					case 'addnewtag':
						alert('您今天已经对该小区添加过新评价，不能再添加了！');
						break;
					default:
						alert('操作未成功,请重试!');
						break;
					};
				}
			});
		}
	});
});

//圈选  (地图左上角 圈选)
$("#_quanxuan, .m_position_box .m1").live('click', function() {
	var jsonBounds = {};
	var drawingManager;
	var verlayObj;
	var styleOptions = {
		strokeColor : "#000", 	//边线颜色。
		fillColor : "#000", 		//填充颜色。当参数为空时，将没有填充效果。
		strokeWeight : 1, 			//边线的宽度，以像素为单位。
		strokeOpacity : 0.8, 		//边线透明度，取值范围0 - 1。
		fillOpacity : 0.5, 			//填充的透明度，取值范围0 - 1。
		strokeStyle : 'dashed' 	//边线的样式，solid或dashed。
	}
	//实例化鼠标绘制工具 
	drawingManager = new BMapLib.DrawingManager(_5i5jmap.mapObj, {
		isOpen : false, //是否开启绘制模式
		enableDrawingTool : false, // 是否显示工具栏
		/*
    drawingToolOptions: {
      anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
      scale: 0.8 //工具栏缩放比例
    },
    */
		rectangleOptions : styleOptions   //矩形的样式
	});
	drawingManager.open();
	drawingManager.setDrawingMode(BMAP_DRAWING_RECTANGLE);
	//回调获得覆盖物信息
	var overlaycomplete = function(e) {
		verlayObj = e.overlay;
		drawingManager.close();//关闭画矩形
		_5i5jmap.mapObj.removeOverlay(verlayObj);
		var BoundsDb = verlayObj.getBounds();
		var ws = BoundsDb.getSouthWest();//西南角
		var en = BoundsDb.getNorthEast();//东北角
		var lx = ws.lng;//经度
		var ly = ws.lat;//纬度
		var rx = en.lng;//经度
		var ry = en.lat;//纬度
		var link = '/community//q' + lx + ',' + ly + ',' + rx + ',' + ry;
		$('.popup_tip a._ok').attr('href', link).attr('target', '_blank');
		$('.popup_tip').show(500);
	};
	drawingManager.addEventListener('overlaycomplete', overlaycomplete);
});

/**
 * 显示右侧指定数据
 */
myMap.prototype.showRight = function(id) {
	$('#rightRewrite #' + id).show();
	$('#rightRewrite #right_index').hide();
};

//type 为触发事件类型 1,直接划上去，2，其他事件触发
//推荐小区鼠标滑出
function recommendComunityMouseOut(obj, type) {
	obj.addClass('no_bg');
	if (type == '1') {
		if (isToolShow) {
			$('#recommendToolbar').find('.tool_bar01').hide();
		} else {
			if (obj.find('ul.tool_bar01').next('ul.tool_bar02').is(":hidden")) {
				//$('.cmbox_wrap .cmbox ul.tool_bar01').show();                                
			}
		}
	} else {
		$('#recommendToolbar').find('.tool_bar01').hide();
	}
}

var recommendCommunityId;
//推荐小区鼠标滑入
function recommendComunityMouseOver(obj, type) {
	var item = obj;
	item.removeClass('no_bg');
	var communityId = item.attr('communityid');
	var offset = item.offset();
	var top = (parseInt(offset.top) - 86) + 'px';
	$('#recommendToolbar').css('top', top);
	var bar1 = $('#recommendToolbar').find('.tool_bar01');
	var bar2 = bar1.next('ul.tool_bar02');
	if (type == '1') {
		//bar1.hide();
		var len = item.find('.recommendToolbar').length;
		if (len == '0') {
			//判断小区是否已关注
			if (recommendCommunityId != communityId) {
				recommendCommunityId = communityId;
				$.ajax({
					url : '/map/isCommunityCollect/',
					data : { id : communityId},
					success : function(data) {
						if (data == 'none') {
							bar1.find('.attentionCommunity').removeClass('map_main_hover');
							bar1.find('.attentionCommunity').find('span').html('加关注');
						} else {
							bar1.find('.attentionCommunity').addClass('map_main_hover');
							bar1.find('.attentionCommunity').find('span').html('已关注');
						}
					}
				});
			}

			//为工具栏属性赋值
			//工具条1
			bar1.attr('id', communityId + 'bar1');
			bar1.find('.attentionCommunity').attr('communityId', communityId);
			bar1.find('.commenttool').attr('val', communityId);
			bar1.find('.compare').attr('communityId', communityId);

			//工具条2
			bar2.attr('id', communityId + 'bar2');
			bar1.show();
			isToolShow = true;
		}
		
		bar1.mouseover(function() {
			$(this).show();
			isToolShow = true;
			$('#recommendComunity .cmbox_wrap .cmbox .t1').addClass('no_bg');
			item.removeClass('no_bg');
		}).mouseout(function() {
			$(this).hide();
			isToolShow = false;
			item.addClass('no_bg');
		});
	} else {
		bar1.show();
	}
}

//前面是获取到的矩形 json数据 jsonBounds 下面可以直接使用
$('.popup_tip .close').live('click', function() {
	$('.popup_tip').hide(500);
});
$("._ok").live('click', function() {
	$('.popup_tip').hide(500);
});
$("._del").live('click', function() {
	$('.popup_tip').hide(500);
	return false;
});

/**
 * 更新天气信息
 */
/*
$(function() {
	var weatherUrl = 'http://weather.news.qq.com/inc/minisite_255.js';
	if (weatherUrl)
		$.getScript(weatherUrl, function() {
			$('#weather').html(__minisite__weather__).show();
		});
});
*/

/**
 * 初使化右侧工具条
 * @returns {undefined}
 */
function etoolbar() {
	$('.e_toolbar').show();
	$('.e_toolbar .a2').hide();
	$('.e_toolbar li').removeClass('current');
	$('.e_toolbar .a1 li.i1').addClass('current');
	$('.e_toolbar').show();
}
 
/**
 * 转换为一个小数
 * @param {type} value
 * @returns {String}
 */
function returnFloat1(value) { //保留一位小数点
	value = Math.round(parseFloat(value) * 10) / 10;
	if (value.toString().indexOf(".") < 0)
		value = value.toString() + ".0";
	return value;
}

/**
 * 推荐小区及对比
 */
$(function() {
	//添加到对比小区中去
	$('#recommendComunity .tool_bar01 .compare').live({
		click : function() {
			$('.contrast_box').show();
			var clickObj = $(this);
			var communityId = clickObj.attr('communityId');
			var select = 'right_recommend_id_' + communityId;
			var infoBox = $('#' + select);
			var num = $('.contrast_box').find('.info_box').length;
			if (num >= 2) {
				alert('最多只能对比两个小区！');
				clickObj.parent().hide();
			} else {
				var item = infoBox.next('.info_box').clone();
				$('#loadContrastBox').append(item);
				var newNum = $('.contrast_box').find('.info_box').length;
				$('.contrast_box .sub_info span').html(newNum);
				clickObj.parent().hide();
				var toolBarWrap = clickObj.parentsUntil('.recommendToolbar').parent().clone();
				toolBarWrap.removeAttr('id');
				toolBarWrap.removeAttr('style');
				infoBox.append(toolBarWrap);
				toolBarWrap.find('.tool_bar02').show();
				clickObj.parent().next('ul.tool_bar02').attr('id', '');
			}

		}
	});
	
	//推荐小区鼠标滑动样式
	$("#recommendComunity .cmbox_wrap .cmbox .t1").live({
		mouseover : function(event, type) { //type为1时，为地图标注触发该点击操作                         
			recommendComunityMouseOver($(this), '1');
			//console.log($('#map_pop_lv2_' + $(this).attr('communityId')));
			if (type != '1') {
				map_house_list_pop_mouseover($('#map_pop_lv2_' + $(this).attr('communityId')));
			}
		},
		mouseout : function() {
			recommendComunityMouseOut($(this), '2');
			map_house_list_pop_mouseout($('#map_pop_lv2_' + $(this).attr('communityId')));
		},
		click : function(event, type) { //type为1时，为地图标注触发该点击操作                
			if (type != '1') {
				var communityId = $(this).attr('communityid');
				$('#map_pop_lv2_' + communityId).trigger('click');
			}
		}
	});

	//关闭推荐小区
	$('.contrast_box .title .close').live('click', function() {
		$('.contrast_box').hide();
		//正在对比工具条隐藏
		$('.info_box .a2_box .delCommunity').each(function() {
			var id = $(this).attr('communityId');
			$('.cmbox #' + id + 'bar2').parent().remove();
		});
		//清空对比项
		$('.contrast_box').find('.info_box').remove();
		$('.contrast_box .sub_info span').html('0');
	});

	//从房源对比中移除该小区
	$('.info_box .a2_box .delCommunity').live({
		click : function() {
			var id = $(this).attr('communityId');
			$('.cmbox #' + id + 'bar2').parent().remove();
			var box = $(this).parentsUntil('.info_box').parent();
			box.remove();
			var num = $('.contrast_box').find('.info_box').length;
			$('.contrast_box .sub_info span').html(num);
		},
		mouseover : function() {
			$(this).addClass('hover');
		},
		mouseout : function() {
			$(this).removeClass('hover');
		}
	});
	
	//开始对比
	$('#mapContrastBtn').live({
		click : function() {
			var num = $('.contrast_box').find('.info_box').length;
			var idArr = new Array();
			if (num < 2) {
				alert('最少选择两个小区！');
			} else {
				$('#loadContrastBox .info_box .a2_box .delCommunity').each(function() {
					var id = $(this).attr('communityId');
					idArr.push(id);
				});
				var idList = idArr.join(',');
				contrastLink = '/community/contrast/idList/' + idList;
				window.open(contrastLink);
			}
			//触发关闭房源对比框
			$('.contrast_box .title .close').trigger('click');
		}
	});
});

function zoomendEvent() {
	console.log('zoomend');
	switchOperate();
}

function moveendEvent() {
	console.log('moveend');
	console.log(_5i5jmap.operate.loadList);
	//移动时可刷新小区列表
	if (_5i5jmap.operate.loadList) {
		loadCommunity();
	}
	//移动时,当前小区状态下,可更新厌恶设施
	if (_5i5jmap.mapStatu == 'community') {

	}
}

/**
 * 根据当前地图缩放级别选择加载不同的数据
 * @returns {undefined}
 */
function switchOperate() {
	if (_5i5jmap.mapObj.getZoom() > 12) {
		if (_5i5jmap.community.id == '' && _5i5jmap.mapStatu != '') {
			_5i5jmap.mapStatu = 'list';
			_5i5jmap.operate.loadList = true;
		} else {
			_5i5jmap.mapStatu = 'community';
			_5i5jmap.operate.loadList = false;
		}
	} else if (_5i5jmap.mapObj.getZoom() <= 8) {
		autoSearchMaker = '';
		autoSearchVal = '';
		_5i5jmap.mapStatu = '';
		_5i5jmap.condition.searchType = 'all';
		_5i5jmap.mapStatu = 'city';
	} else {
		autoSearchMaker = '';
		autoSearchVal = '';
		_5i5jmap.condition.searchType = 'all';
		_5i5jmap.mapStatu = 'area';
	}
	console.log(_5i5jmap.mapStatu);
	switch (_5i5jmap.mapStatu) {
	case 'list'://初使为小区列表状态
		changeToList();
		break;
	case 'city'://
		changeToCity();
		break;
	case 'area'://
		changeToArea();
		break;
	case 'community'://
		changeToCommunity();
		break;
	default:
		break;
	}
}

/**
 * 重置地图信息
 */
myMap.prototype.resetCondition = function() {
	//console.log('重置地图信息');
	this.condition.zoom = this.mapObj.getZoom();
	this.condition.center = this.mapObj.getCenter(); //中心点
	this.condition.x = this.condition.center.lng;
	this.condition.y = this.condition.center.lat;
	this.condition.bounds = this.mapObj.getBounds(); //可视区域
	this.condition.sw = this.condition.bounds.getSouthWest(); //经度:sw.lng//纬度:sw.lat
	this.condition.ne = this.condition.bounds.getNorthEast();
};

/**
 * 绑定重新加载小区事件,以及放大缩小的时候需要执行的操作
 * @returns {changeToList}
 */
function changeToList() {
	_5i5jmap.mapObj.clearOverlays(); //清除地图上所有标注
	_5i5jmap.operate.loadList = true;
	loadCommunity();
}

function changeToCity() {
	$('#right_index').show();
	$('#rightRewrite').hide();
	_5i5jmap.mapObj.clearOverlays(); //清除地图上所有标注
	_5i5jmap.mapStatu = 'city';
	_5i5jmap.operate.loadList = false;
	loadCityData();
}

function changeToArea() {
	$('#right_index').show();
	$('#rightRewrite').hide();
	_5i5jmap.mapObj.clearOverlays(); //清除地图上所有标注
	_5i5jmap.mapStatu = 'area';
	_5i5jmap.operate.loadList = false;
	loadArea();
}

function changeToCommunity() {
	//可以重加载厌恶设施,其他的东西都不变
	_5i5jmap.LocationSearch();
}

function ajaxLogin() {
	$.post(
		'/user/mapLogin/',
		$('#login-form').serialize(),
		function(data) {
			if (data.status == '1') {
				if ($.trim(data.scripts) != 'null') {
					$.each(data.scripts, function(index, js) {
						$('head').append('<script type="text/javascript" src="' + js + '"><\/script>');
					});
				}
				$('.user_info_box').html(data.content);
				closeFloatDiv('thecommunity');
			}
			if (data.status == '0') {
				$('.loginInfo').html(data.content);
			}
		}, 
		'json'
	);
}
</script>

<script type="text/javascript">
/*
 *右侧二手房租房选项卡效果
 */
$('.sdr_title_tab li').live('click', function() {
  $(this).addClass("selected").siblings().removeClass("selected");
  var $div_li = $(".sdr_title_tab li");
  var div_index = $div_li.index(this);
  if (div_index == 0) {
    $(this).find('.arrow').show();
    $(this).attr('class', 'tab1');
    $(this).siblings().attr('class', 'tab2');
    $(this).siblings().find('.arrow3').hide();
  } else {
    $(this).find('.arrow3').show();
    $(this).attr('class', 'tab3');
    $(this).siblings().attr('class', 'tab2');
    $(this).siblings().find('.arrow').hide();
  }
  $(".set_info_house>div").eq(div_index).show().siblings().hide();
  readScroll('#rightRewrite #communityInfo .theCommunityexchange .line_b');
  readScroll('#rightRewrite #communityInfo .theCommunityrent .line_b');
})
</script>

<script type="text/javascript">
$(function() {
	//地图移动时 隐藏小区提示信息
	_5i5jmap.mapObj.addEventListener('moving', function() {
		$('#communityTip').hide();
	});
	//测距
	$('.nav .item_box #menu_sub_4 li.m3 a').live('click', function() {
		_5i5jmap.tools.myDis.open();
	});

	//路况信息                
	$('.nav .item_box .m3').live('click', function() {
		if ($('#tcWrap').is(':hidden')) {
			$('#tcBtn').trigger('click');
			$('#tcWrap').removeAttr('style').attr({ style : 'top:56px;right:147px;' });
		} else {
			$('#tcWrap #tcClose').trigger('click');
		}
	});
});

//路况信息
myMap.prototype.trafficInfo = function() {
	//路况信息               
	var ctrl = new BMapLib.TrafficControl({
		showPanel : false
		//是否显示路况提示面板 
	});
	this.mapObj.addControl(ctrl);
	ctrl.setAnchor(BMAP_ANCHOR_BOTTOM_RIGHT);
};

//显示定位功能
myMap.prototype.autoCompleteShow = function() {
	$('.login_bg').show();
	$('.position_tip').show();
};

//定位输入自动提示
myMap.prototype.searchAutoComplete = function() {
	//建立一个自动完成的对象
	var ac = new BMap.Autocomplete({
		input : "searchAutoComplete",
		location : _5i5jmap.options.cityName + '市'
	});
	
	ac.setLocation(_5i5jmap.options.cityName + '市');
	ac.addEventListener("onconfirm", function(e) { //鼠标点击下拉列表后的事件
		var _value = e.item.value;
		autoSearchVal = _value.province + _value.city + _value.district + _value.street + _value.business;
	});
};

/**
 * 城区标注点绑定鼠标事件
 */
myMap.prototype.addPageEvent = function() {
	var self = this;
	//城区标注点事件
	$('.map_pop').live({
		click : function() {
			if ($(this).attr('domain') != '') {//城市图标
				window.location.href = 'http://' + $(this).attr('domain') + '.' + domainRoot;
				//console.log($(this));
			} else {//城区图标
				//console.log('点击城区');
				_5i5jmap.mapObj.setZoom(13);
				//设置当前城区为中心点.移动完成后加载数据
				_5i5jmap.mapObj.panTo(new BMap.Point($(this).attr('x'), $(this).attr('y')));
			}
			return false;
		},
		mouseover : function() {
			var popObj = $(this);
			var popId = popObj.attr('id');
			var testStr = 'map_pop_lv0';

			if (popId.indexOf(testStr) >= 0) {
				popObj.width(20);
			}

			//console.log($(this).attr('domain'));
			if ($(this).attr('domain') == '') {
				popObj.find('p').hide();
				popObj.find('span').show();
			}
			popObj.addClass('map_pop_cur');
			popObj.attr('index', popObj.css('z-index'));
			popObj.css('z-index', 0);
		},
		mouseout : function() {
			var popObj = $(this);
			if ($(this).attr('domain') == '') {
				popObj.find('p').show();
				popObj.find('span').hide();
			}
			popObj.removeClass('map_pop_cur');
			if (popObj.width() == 20) {
				popObj.width(47);
			}
			popObj.css('z-index', popObj.attr('index'));
		}
	});
			
	//二级地区事件
	$('.map_pop01,.map_pop_exchange,.map_pop_rent').live({
		click : function() {
			$('#communityTip').hide(); //隐藏提示气泡
			_5i5jmap.community.id = $(this).attr('communityId'); //标记当前小区为活跃状态
			_5i5jmap.community.key = '';
			if ($(this).hasClass('map_pop01')) {
				communityHouseType = 'all';
			} else if ($(this).hasClass('map_pop_exchange')) {
				communityHouseType = 'exchange';
			} else if ($(this).hasClass('map_pop_rent')) {
				communityHouseType = 'rent';
			}
			_5i5jmap.mapStatu = 'community';
			_5i5jmap.operate.loadList = false;
			_5i5jmap.mapObj.clearOverlays(); //清除地图上所有标注
			_5i5jmap.loadTheCommunity();
			return false;
		},
		mouseover : function() {
			map_house_list_pop_mouseover($(this));
			//右侧
			//console.log($('#right_recommend_id_' + $(this).attr('communityId')));
			$('#right_recommend_id_' + $(this).attr('communityId')).trigger('click', [ '1' ]);
			recommendComunityMouseOver($('#right_recommend_id_' + $(this).attr('communityId')), '2');
		},
		mouseout : function() {
			map_house_list_pop_mouseout($(this));
			//右侧
			//console.log($('#right_recommend_id_' + $(this).attr('communityId')));
			recommendComunityMouseOut($('#right_recommend_id_' + $(this).attr('communityId')), '2');
		}
	});

	//地铁事件
	$('#new_map_xlk .new_map_xlk_wz li').live({
		click : function() {
			var line = $(this).attr('line');
			var x = $(this).attr('x');
			var y = $(this).attr('y');
			_5i5jmap.subWay.isSubWay = true;
			_5i5jmap.subWay.line = line;
			var x1 = x / 100000;
			var y1 = y / 100000;
			var point = new BMap.Point(x1, y1);//定义一个中心点坐标
			_5i5jmap.mapObj.centerAndZoom(point, 13);//设定地图的中心点和坐标并将地图显示在地图容器中
			_5i5jmap.mapObj.clearOverlays();
			//loadCommunity();
		}
	});

	$('.map_pop_subway').live({
		click : function() {
			var x = $(this).attr('x');
			var y = $(this).attr('y');
			var x1 = x / 100000;
			var y1 = y / 100000;
			var point = new BMap.Point(x1, y1);//定义一个中心点坐标
			_5i5jmap.mapObj.centerAndZoom(point, 15);//设定地图的中心点和坐标并将地图显示在地图容器中
			_5i5jmap.mapObj.clearOverlays();
			loadCommunity();
		},
		mouseover : function() {
			if ($(this).attr('oldZIndex') == undefined) {
				$(this).attr('oldZIndex', $(this).css('z-index'));
			}
			$(this).css('z-index', 100);
			$(this).addClass('map_pop_subway_cur');
		},
		mouseout : function() {
			$(this).css('z-index', $(this).attr('oldZIndex'));
			$(this).removeClass('map_pop_subway_cur');
		}
	});
};

/**
 * 加载当前小区
 */
myMap.prototype.loadTheCommunity = function() {
	/**
	 * todo  关闭小区时取消当前小区状态
	 */
	_5i5jmap.mapStatu = 'community';
	_5i5jmap.operate.loadList = false;
	_5i5jmap.mapObj.setMinZoom(13);//如果是想要让直接就取消当前小区,则可以不用在这个地方加限制
	_5i5jmap.mapObj.setZoom(13);
	$('.nav').show(); //显示菜单
	//console.log('read the community start!');
	var self = this;
	this.loadDataAjax = $.ajax({
		url : _5i5jmap.options.dataUrl,
		type : 'GET',
		data : {
			dataType : 'thecommunity',
			id : _5i5jmap.community.id,
			type : communityHouseType
		},
		dataType : 'json',
		success : function(data) {
			_5i5jmap.mapStatu = 'community';
			_5i5jmap.operate.loadList = false;
			//console.log('read the community info is ok!');
			//console.log(data);
			if (_5i5jmap.community.keys.length == 0) {
				self.mapObj.clearOverlays();
			}
			_5i5jmap.mapObj.panTo(new BMap.Point(data.house.x,
					data.house.y));
			_5i5jmap.community.x = data.house.x;
			_5i5jmap.community.y = data.house.y;

			//console.log('bbb:');
			//console.log(data.house);
			_5i5jmap.community.data = data.house;
			_5i5jmap.community.data.id = data.house.Id;
			_5i5jmap.community.data.point = data.house.x + '|'
					+ data.house.y;
			_5i5jmap.community.data.title = data.house.address;
			_5i5jmap.community.data.exchange_price = data.house.price;
			_5i5jmap.community.data.percentage = data.house.plot_ratio;
			_5i5jmap.community.data.averagePrice = data.other.averagePrice;
			_5i5jmap.community.data.percentage = data.other.percentage;
			_5i5jmap.community.data.priceType = data.other.priceType;
					
			/**
			 * todo 更新图片规则
			 */
			_5i5jmap.community.data.imgurl = 'http://image.5i5j.com/picture/' + data.img;

			//console.log('当前小区');
			//console.log(data.house);
			_5i5jmap.addTheCommunity();
			
			/**
			 * 加载当前小区的标注(缩放,移动不影响,可设移动时,重置关键字)
			 * 再点一下则退出当前小区模式
			 */
			//显示当前小区的介绍,右侧显示当前小区
			$('#communityDetail').html(data.mapDetail).show();
			//载入右侧的小区信息
			$('#rightRewrite #communityInfo').html(data.rightDetail).show().siblings().hide();
			$('#rightRewrite #localSearchList').html(
					data.rightDetail);
			$('#right_index').hide();
			$('#rightRewrite').show();
			$('#communityTip').hide();
			//展开工具栏
			etoolbar(); //初使化工具栏  
			//重设窗口大小信息.自定义滚动条
			mapBoxResize();
		},
		/*
		 * 失败时,清除当前小区
		 */
		error : function() {
			//alert('加载小区信息失败,请重试!');
			//清除当前小区
			_5i5jmap.mapStatu = 'list';
			_5i5jmap.operate.loadList = true;
			_5i5jmap.closeTheCommunity();
		}
	});
};

myMap.prototype.addTheCommunity = function() {
	console.log('sss:');
	console.log(_5i5jmap.community.data);
	//重新添加当前小区
	var div = new ComplexCustomOverlay(_5i5jmap.community.data, 13);
	_5i5jmap.mapObj.addOverlay(div);
};

//关闭当前小区时,恢复事件,并重加载数据
$('#communityDetail .close_pop').live('click', function() {
	var self = _5i5jmap;
	//清除当前小区
	_5i5jmap.mapStatu = 'list';
	_5i5jmap.operate.loadList = true;
	self.closeTheCommunity();
});

/**
 * 关闭当前小区 
 */
myMap.prototype.closeTheCommunity = function() {
	_5i5jmap.community.id = '';
	_5i5jmap.community.data = '';
	_5i5jmap.community.key = '';
	$('.e_toolbar').hide();
	$('#communityDetail').hide();
	//右侧显示首页,请求执行完后,会更新此处实际显示内容
	// $('#right_index').show();
	$('#rightRewrite').hide();
	_5i5jmap.mapObj.clearOverlays();
	_5i5jmap.mapObj.setMinZoom(5);
	_5i5jmap.condition.searchType = 'all';
	loadCommunity();
	//console.log('关闭当前小区');
};

/**
 * 定位功能
 * 当前地图,缩放到指定级别后会自动加载周边小区
 */
$(function() {
	$('.position_tip #autoSearchGo').live('click', function() {
		positionLocation();
	});
});

function positionLocation() {
	_5i5jmap.condition.searchType = 'all';
	var inputVal = $('#searchAutoComplete').val();
	if (inputVal == '') {
		autoSearchMaker = '';
		autoSearchVal = '';
		_5i5jmap.mapStatu = '';
		$('.position_tip,.login_bg').hide();
	} else {
		_5i5jmap.mapStatu = '';
		_5i5jmap.operate.loadList = false;
		_5i5jmap.mapObj.addEventListener('zoomend', positionLocationSearch);
		if (_5i5jmap.mapObj.getZoom() == '15') {
			positionLocationSearch();
		} else {
			_5i5jmap.mapObj.setZoom(15);
		}
	}
}

function positionLocationSearch() {
	var inputVal = $('#searchAutoComplete').val();
	_5i5jmap.mapObj.removeEventListener('zoomend', positionLocationSearch);
	if (autoSearchVal == '') {
		autoSearchVal = inputVal;
	}
	var localMap = _5i5jmap.mapObj;
	localMap.clearOverlays(); //清除地图上所有覆盖物
	var local = new BMap.LocalSearch(_5i5jmap.options.cityName, {//智能搜索
		onSearchComplete : function() {
			autoSearchMaker = local.getResults().getPoi(0).point; //获取第一个智能搜索的结果
			_5i5jmap.mapStatu = 'list';
			_5i5jmap.operate.loadList = true;
			localMap.panTo(autoSearchMaker);
			//localMap.centerAndZoom(autoSearchMaker, 13);      //等重新搜索完数据之后再添加标注 参见loadcommunitylist
			$('.position_tip,.login_bg').hide();
			$('#searchAutoComplete').val('');
		},
	//renderOptions:{map: localMap,autoViewport:false,selectFirstResult:false}
	});
	local.search(autoSearchVal, { forceLocal : true });
}

// 复杂的自定义覆盖物
function ComplexCustomOverlay(value, lv) {
	//console.log(value);
	this._self = _5i5jmap;
	this._value = value;
	this._point = new BMap.Point(value.point.toString().split("|")[0],
			value.point.toString().split("|")[1]);
	this._countNumber = value.countNumber;
	this._title = value.title;
	this._lv = parseInt(lv);
	this._x = this._point.lng;
	this._y = this._point.lat;
}

ComplexCustomOverlay.prototype = new BMap.Overlay();
ComplexCustomOverlay.prototype.createOverlay = function(map) {
	this._map = map;
	var div = this._div = document.createElement("div");
	div.setAttribute('index', '');
	if (this._lv <= 8) {
		if (this._value.id == undefined) {
			this._value.id = this._value.Id;
		}
		div.id = 'map_pop_lv0_' + this._value.id;
		div.setAttribute('areaId', '');
		div.setAttribute('domain', this._value.id);
		div.className = "map_pop";
		var p = this._span = document.createElement("p");
		p.className = "cityName";
		div.appendChild(p);
		var span = document.createElement("span");
		div.appendChild(span);
		var title = this._title;
		if (title.substring(title.length - 1) == '区') {
			title = title.substring(0, title.length - 1);
		}
		p.appendChild(document.createTextNode(title));
		//                var numText = this._countNumber;
		span.appendChild(document.createTextNode(title));
		div.setAttribute('x', this._x);
		div.setAttribute('y', this._y);
		//隐藏掉气泡提示
		$('#communityTip').hide();
	} else if (this._lv <= '12') {
		div.id = 'map_pop_lv1_' + this._value.id;
		div.setAttribute('areaId', this._value.id);
		div.setAttribute('domain', '');
		div.className = "map_pop";
		var p = this._span = document.createElement("p");
		div.appendChild(p);
		var span = document.createElement("span");
		div.appendChild(span);
		var title = this._title;
		if (title.substring(title.length - 1) == '区') {
			title = title.substring(0, title.length - 1);
		}
		p.appendChild(document.createTextNode(title));
		// var numText = this._countNumber;
		var numText = '二手房 : ' + this._value.exchangeNumber + '套 | 租房 : ' + this._value.rentNumber + '套';
		span.appendChild(document.createTextNode(numText));
		div.setAttribute('x', this._x);
		div.setAttribute('y', this._y);
		//隐藏掉气泡提示
		$('#communityTip').hide();
	} else {
		//console.log('thecommunity');
		//console.log(this._value);
		if (this._value.type == '1') { //地铁标注
			div.id = 'map_pop_subWay_' + this._value.id;
			div.className = "map_pop_subway";
			var p = this._span = document.createElement("p");
			div.appendChild(p);
			var title = this._value.title;
			p.appendChild(document.createTextNode(title));
			div.setAttribute('x', this._value.x);
			div.setAttribute('y', this._value.y);
			div.setAttribute('point', this._value.point);
		} else { //小区标注
			div.id = 'map_pop_lv2_' + this._value.id;
			div.setAttribute('community', this._value.title);
			div.setAttribute('communityId', this._value.id);
			div.setAttribute('imgurl', this._value.imgurl);
			div.setAttribute('price', this._value.averagePrice);
			div.setAttribute('percentage', this._value.percentage);
			div.setAttribute('priceType', this._value.priceType);
			div.setAttribute('x', this._x);
			div.setAttribute('y', this._y);

			if (this._self.condition.searchType == 'exchange') {
				div.className = 'map_pop_exchange';
				if ($.trim(this._self.community.id) == this._value.id) {
					div.className = div.className + " map_pop_exchange_cur";
				}
			} else if (this._self.condition.searchType == 'rent') {
				div.className = 'map_pop_rent';
				if ($.trim(this._self.community.id) == this._value.id) {
					div.className = div.className + " map_pop_rent_cur";
				}
			} else {
				div.className = 'map_pop01';
				if ($.trim(this._self.community.id) == this._value.id) {
					div.className = div.className + " map_pop01_cur";
				}
			}
			if ($.trim(this._self.community.id) == this._value.id) {
				div.setAttribute('isopen', 'true');
			}
		}
	}
	div.style.position = "absolute";
	div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
	var arrow = this._arrow = document.createElement("div");
	arrow.style.position = "absolute";
	arrow.style.width = "11px";
	arrow.style.height = "10px";
	arrow.style.top = "22px";
	arrow.style.left = "10px";
	arrow.style.overflow = "hidden";
	arrow.style.backgroundPosition = "0px -20px";
	map.getPanes().labelPane.appendChild(div);
	return div;
};

ComplexCustomOverlay.prototype.initialize = function(map) {
	this._map = map;
	var div = this.createOverlay(map);
	return div;
};
ComplexCustomOverlay.prototype.draw = function() {
	var map = this._map;
	var pixel = map.pointToOverlayPixel(this._point);
	this._div.style.left = pixel.x - parseInt(this._arrow.style.left) - 0 + "px";
	this._div.style.top = pixel.y - 40 + "px";
};

/**
 * 获取标注周边物时的图标
 */
function getIcon(keyword) {
	switch (keyword) {
	case '职业顾问':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-264, -57), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '店面':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-264, -169), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '学校':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-264, -217), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '超市':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-264, -265), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '医院':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-264, -316), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '电影院':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-264, -374), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '餐饮':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-264, -431), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '银行':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-264, -480), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '酒吧':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-344, -480), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '邮局':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-342, -428), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '购物':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-342, -374), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case 'ATM':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-342, -317), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case 'KTV':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-342, -265), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '酒店':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-341, -214), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '网吧':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-339, -166), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '咖啡厅':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-344, -119), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '洗浴中心':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-342, -59), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
		/*右侧 的加油站,,现在使用厌恶设施的加油站  
	case '加油站':
	 var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
	 new BMap.Size(49, 49), // 标注显示大小
	 {
	 anchor: new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
	 imageOffset: new BMap.Size(-414, -60), // 这里相当于CSS sprites
	 imageSize: new BMap.Size(732, 1200)
	 });
	 break;
	 */
	case '景点':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-414, -121), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '丽人':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-414, -167), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '变电所':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(25, 35), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-268, -1031), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '高压电塔':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(25, 35), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-268, -985), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '殡仪馆':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(25, 35), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-268, -1080), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '垃圾场':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(25, 35), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-268, -1128), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	case '加油站':
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(25, 35), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-268, -935), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	default:
		var myIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
				new BMap.Size(49, 49), // 标注显示大小
				{
					anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
					imageOffset : new BMap.Size(-264, -113), // 这里相当于CSS sprites
					imageSize : new BMap.Size(732, 1200)
				});
		break;
	}
	return myIcon;
}

/**
 * 设置滚动条
 * @returns {undefined}
 */
function visitHousesScroll() {
	$('#visitBox').sly('destroy');
	$('#visitBox').sly({
		horizontal : 1, //为1时,横向滚动
		itemNav : 'basic',
		smart : 1,
		activateOn : 'click',
		mouseDragging : 1,
		touchDragging : 1,
		releaseSwing : 1,
		startAt : 0,
		scrollBar : $('#visitBox').parent().find('.bh_slide_bar'),
		scrollBy : 1,
		activatePageOn : 'click',
		speed : 500,
		easing : 'easeOutExpo',
		dragHandle : 1,
		dragContent : 1,
		dynamicHandle : 1,
		clickBar : 1,
		prevPage : $('#visitBox').find('.previous'),
		nextPage : $('#visitBox').find('.next')
	}, {
	//load: function() {
	//  $('.slide_bar .bar').height($('.slide_bar .bar').height() - 2);
	//}
	});
}

/**
 * 将返回的数据,在地图上钉出来
 * @param {type} data
 * @returns {undefined}
 */
myMap.prototype.addOverlay = function(data, lv) {
	var self = this;
	var div;
	$.each(data, function(key, value) {
		div = new ComplexCustomOverlay(value, lv);
		self.mapObj.addOverlay(div);
		if (lv >= 13) {
			$('#map_pop_lv2_' + value.id).data('val', value);
			//console.log($('#map_pop_lv2_' + value.id).data('val'));
		}
	});
};

/**
 * 载入视图内的小区
 * @returns {undefined}
 */
function loadCommunity() {
	_5i5jmap.resetCondition();
	if (_5i5jmap.condition.zoom <= 12) {
		return false;
	}
	//console.log('loadCommunity载入小区');
	//判断是否是地铁搜索
	var data = {};
	if (_5i5jmap.condition.searchType == 'exchange' || _5i5jmap.condition.searchType == 'rent') {
		data = {
			dataType : 'community',
			zoom : _5i5jmap.condition.zoom,
			searchType : _5i5jmap.condition.searchType,
			searchData : _5i5jmap.condition.searchData,
			line : _5i5jmap.subWay.line
		};
	} else {
		data = {
			dataType : 'community',
			zoom : _5i5jmap.condition.zoom,
			swLng : _5i5jmap.condition.sw.lng,
			swLat : _5i5jmap.condition.sw.lat,
			neLng : _5i5jmap.condition.ne.lng,
			neLat : _5i5jmap.condition.ne.lat,
			line : _5i5jmap.subWay.line
		};
	}
	this.loadDataAjax = $.ajax({
		url : _5i5jmap.options.dataUrl,
		type : 'GET',
		data : data,
		dataType : 'json',
		success : function(data) {
			//_5i5jmap.mapObj.removeEventListener('zoomend', formatCity);
			$('.nav').show(); //显示菜单
			if (data.houses.length > 0) {
				var firstHousePoint = data.houses[0].point;
				var x = firstHousePoint.toString().split("|")[0];
				var y = firstHousePoint.toString().split("|")[1];
				_5i5jmap.operate.loadList = false;
				_5i5jmap.mapObj.panTo(x, y);
				if (_5i5jmap.community.keys.length == 0) {
					_5i5jmap.mapObj.clearOverlays();
				}
				_5i5jmap.addOverlay(data.houses, _5i5jmap.condition.zoom);
				$('#rightRewrite #communityRecommend').html(data.recommend)
						.show().siblings().hide();
				$('#right_index').hide();
				$('#rightRewrite').show();
				//重设窗口大小信息.自定义滚动条
				mapBoxResize();

				//若有定位搜索则添加定位标注                                         
				if (autoSearchVal != '') {
					var locateIcon = new BMap.Icon("/themes/new2013/files/map/image/map_icon.png", //图片地址
							new BMap.Size(33, 44), // 标注显示大小
							{
								anchor : new BMap.Size(0, 35), // 标注底部小尖尖的偏移量
								imageOffset : new BMap.Size(-26, -742), // 这里相当于CSS sprites
								imageSize : new BMap.Size(732, 1200)
							});
					_5i5jmap.mapObj.addOverlay(new BMap.Marker( autoSearchMaker, { icon : locateIcon }));
					//_5i5jmap.mapObj.addOverlay(new BMap.Marker(autoSearchMaker));//,{icon:''}
				}
			} else {
				alert('没有找到小区');
				//console.log('没有找到小区数据');
			}
			//解除地铁搜索标志
			if (_5i5jmap.subWay.isSubWay) {
				_5i5jmap.subWay.isSubWay = false;
				_5i5jmap.subWay.line = '';
			}
		},
		error : function() {
			//解除地铁搜索标志
			if (_5i5jmap.subWay.isSubWay) {
				_5i5jmap.subWay.isSubWay = false;
				_5i5jmap.subWay.line = '';
			}
		}
	});
}

//载入城市数据
function loadCityData() {
	var self = this;
	this.loadDataAjax = $.ajax({
		url : _5i5jmap.options.dataUrl,
		type : 'GET',
		data : {
			dataType : 'city',
			zoom : _5i5jmap.condition.zoom
		},
		dataType : 'json',
		success : function(data) {
			$('#right_index').show();
			//执行结果处理
			if (data.houses.length > 0) {
				_5i5jmap.mapObj.clearOverlays();
				_5i5jmap.addOverlay(data.houses, _5i5jmap.mapObj
						.getZoom());
			} else {
				//console.log('没有找到城市数据');
			}
		},
		error : function() {
		}
	});
};

/**
 * 加载城区
 * 加载完成后,移动不做处理,缩放到一定级别,触发另外的事件
 * @returns {undefined}
 */
function loadArea() {
	//console.log('格式化城区信息');
	_5i5jmap.loadDataAjax = $.ajax({
		url : _5i5jmap.options.dataUrl,
		type : 'GET',
		data : {
			dataType : 'area',
			zoom : _5i5jmap.mapObj.getZoom()
		},
		dataType : 'json',
		success : function(data) {
			if (data.houses.length > 0) {
				_5i5jmap.mapObj.clearOverlays();
				_5i5jmap.addOverlay(data.houses, data.zoom);
				//绑定点击事件
			} else {
				alert('获取城区失败,请刷新页面重试!');
			}
		},
		error : function() {
			//alert('获取城区失败,请刷新页面重试!');
		}
	});
}
</script>

<script type="text/javascript">
$(function() {
	//鼠标划入时，选中以及以前的星星变亮
	$('.broder_plList li').live('mouseover', function() {
		$(this).parent().removeClass('selected'); //移除选中标示
		$(this).parent().children('li').children('span')
				.removeClass('star03'); //移除当前class的同级
		for (var i = 0; i <= $(this).index(); i++) {
			$(this).parent().children('li').eq(i).children(
					'span').addClass('star03');
		}
	});
	
	//鼠标移除时清空所有的星星，如果用户做单击选择，鼠标移除时需保留用户的选项
	$('.broder_plList li').live('mouseleave', function() {
		if (!$(this).parent().hasClass('selected')) {
			if (!$(this).siblings('input').attr('value')) {
				$(this).parent().children('li').children('span').removeClass('star03');
				for (var i = 0; i <= 4; i++) {
					$(this).parent().siblings('div').children('p').eq(i).css('display', 'none');
				}
			} else {
				$(this).parent().children('li').children('span').removeClass('star03');
				for (var i = 0; i < $(this).siblings('input').attr('value'); i++) {
					$(this).parent().children('li').eq(i).children('span').addClass('star03');
				}
				$(this).parent().siblings('div').children('p').eq($(this).siblings('input').attr('value') - 1).css('display', 'block');
			}
		}
	});
	
	//鼠标单击星星时，选中以及以前的星星变亮，显示提示信息
	$('.broder_plList li').live('click', function() {
		for (var i = 0; i <= $(this).index(); i++) {
			$(this).parent().siblings('div').children('p').css( 'display', 'none');
		}
		$(this).parent().siblings('div').children('p').eq($(this).index()).css('display', 'block');
		$(this).siblings('input').attr('value', $(this).index() + 1); //添加用户评分
		$(this).parent().addClass('selected'); //添加选中标示
	});
});
</script>

<script type="text/javascript">
$('#submit_comment').live('click', function() {
	var self = $(this);
	if ($('#comment').val() == '' || $('#comment').val() == '请输入您对该小区的评价') {
		alert('请填写评论内容。');
		return false;
	}
	if ($('#rate00').val() == '') {
		alert('请为社区环境评分.');
		return false;
	}
	//alert(document.getElementById("rate00").value);
	if ($('#rate01').val() == '') {
		alert('请为综合配套评分.');
		return false;
	}
	if ($('#rate02').val() == '') {
		alert('请为物业服务评分.');
		return false;
	}
	if ($('#rate03').val() == '') {
		alert('请为户型设计评分.');
		return false;
	}
	if ($('#rate05').val() == '') {
		alert('请为停车管理评分.');
		return false;
	}
	var data = {
		'comment_id' : self.attr('communityId'),
		'community_id' : self.attr('communityId'),
		'comment_type' : 1, //1.小区评论 2.评论回复
		'comment_content' : $("#comment").val(),
		'captchaReview' : $("#CommunityComment_captchaReview").val(),
		'environment' : $("#rate00").val(),
		'comprehensive' : $("#rate01").val(),
		'property_service' : $("#rate02").val(),
		'type_design' : $("#rate03").val(),
		'park' : $("#rate04").val()
	}
	$.ajax({
		type : "post",
		url : "/community/mapWriteReview",
		data : { 'data' : data },
		success : function(result) {
			if (result == 3) {
				var url = self.attr('url');
				$('#communityComment').load(url + '#communityComment .first1', function() {
					$('html, body').animate({ scrollTop : $("#communityComment").offset().top }, 1000);
				});
				$('#showMapComment').click();
				alert("您已经评论成功了！");
			} else if (result == 1) {
				alert('评分失败！');
			} else if (result == 2) {
				alert('评论失败');
			} else if (result == 4) {
				$('.login_bg').show();
				$('#ajax_login_box').show();
				$('#ajax_login_box').css({ 'top' : '90%' }); //重新定位登陆框
			}
		}
	});
	return false;
});
$("#comment").live({
	focus : function() {
		if ($(this).val() == "请输入您对该小区的评价") {
			$(this).val("");
		}
	},
	blur : function() {
		if ($(this).val() == "") {
			$(this).val("请输入您对该小区的评价");
		}
	}
});
$('#showMapComment').live('click', function() {
	$('#map_comment').toggle('slow');
});

function addCommunityTag(communityId, tag, self, num) {
	var data = {
		'section_id' : communityId,
		'tag' : tag
		//1.小区评论 2.评论回复
	}
	$.ajax({
		type : "post",
		url : "/community/writeTag",
		data : data,
		success : function(result) {
			if (result.status == 'ok') {
				$(self).html(tag + '（' + (num + 1) + '）');
				alert("您的印象评价是'" + tag + "'！");
			} else {
				alert('今天评价次数已满，不能再进行评价了');
			}
		}
	});
}
</script>
<!--## 地图小区评论end ## -->

<script type="text/javascript">
//对用户评价进行投票
var keynum = 1;
function addNum(commentid, type, num) {
	if (keynum == 1) {
		var val = $(num).html();
		var data = { 'id' : commentid, 'type' : type }
		$.ajax({
			type : "post",
			url : "/community/addCommentAgree",
			data : { 'data' : data },
			success : function(result) {
				keynum = 2;
				if (type == 'oppose') {
					var re = /反对（(\d*)）/;
					var nums = parseInt(re.exec(val)[1]) + 1;
					$(num).attr('class', 'vote');
					$(num).html('反对（' + nums + '）');
				} else if (type == 'agree') {
					var re = /赞同（(\d*)）/;
					var nums = parseInt(re.exec(val)[1]) + 1;
					$(num).html('赞同（' + nums + '）');
					$(num).attr('class', 'vote');
				}
			}
		});
		return false;
	} else {
		alert('您已经赞同或者反对过了！');
	}
}

//绑定ajax分页
$('#communityComment .turn_page a').live('click', function() {
	var url = $(this).attr('href');
	//	url=url.replace('community', 'ajaxComment');
	$('#communityComment').load( url + ' #communityComment .first1', function() {
		$('html, body').animate({ scrollTop : $("#communityComment").offset().top }, 1000);
	});
	return false;
});

$('#communityComment .turn_pagemap a').live('click', function() {
	var url = $(this).attr('href');
	//	url=url.replace('community', 'ajaxComment');
	$('#communityComment').load(url + ' #communityComment.first1', function() {
		$('html, body').animate({ scrollTop : $("#communityComment").offset().top }, 1000);
	});
	return false;
});
</script>

<!--搜索关键字提示-->
<script type="text/javascript">
$(function() {
	$('.map_search_input').each(function() {
		var key = $(this).attr('stype');
		$(this).autocomplete({
			minLength : 1,
			delay : 10,
			selectFirst : true,
			source : function(request, response) {
				$.ajax({
					url : "/data/searchKey?key=" + key,
					dataType : "json",
					data : request,
					success : function(data) {
						response(data);
					}
				});
			},
			select : function(event, ui) {
				$(this).val(ui.item.name);
				return false;
			},
			focus : function(event, ui) {
				//                                        if (key == 'exchange') {
				//                                                $('#mapExchangeKey').val(ui.item.name);
				//                                        } else {
				//                                                $('#mapRentKey').val(ui.item.name);
				//                                        }
				return false;
			}
		});
		$(this).data("autocomplete")._renderItem = function( ul, item) {
			if (item.arealevel == 3) {
				return $("<li style='width:665px;text-align:left;'></li>").data("item.autocomplete", item)
					.append("<a style='font-family:\"微软雅黑\";'><span style='float:right;'>约"
										+ item.houseNum
										+ '套</span>'
										+ item.name
										+ '   <span style="color:#FF6D00;">'
										+ item.name
										+ "</span></a>")
						.appendTo(ul);
			}
			if (item.arealevel == 4) {
				return $("<li style='width:665px;text-align:left;'></li>").data("item.autocomplete", item)
						.append(
								"<a style='font-family:\"微软雅黑\";'><span style='float:right;'>约"
										+ item.houseNum
										+ '套</span>'
										+ item.name
										+ '   <span style="color:#FF6D00;">'
										+ item.location
										+ "</span></a>")
						.appendTo(ul);
			}
			return $("<li style='width:665px;text-align:left;'></li>").data("item.autocomplete", item)
					.append(
							"<a style='font-family:\"微软雅黑\";'><span style='float:right;'>约"
									+ item.houseNum
									+ '套</span>'
									+ item.name
									+ '    <span style="color:#FF6D00;">'
									+ item.location
									+ "</span></a>")
					.appendTo(ul);
		};
	});
});
</script>

<script type="text/javascript">
/**
 * 判断地图中是否有周边配套标注
 */
myMap.prototype.isHasMaker = function() {
	var isHasMaker = false;
	var overlays = _5i5jmap.mapObj.getOverlays();
	if (overlays != '') {
		var length = overlays.length;
		for (var i = 0; i < length; i++) {
			var item = overlays[i];
			var tag = /(\w+)\(/.exec(item.constructor.toString())[1];
			if (tag == 'W') {
				isHasMaker = true;
			}
			if (isHasMaker) break;
		}
	}
	return isHasMaker;
};

/**
 * 执行搜索
 * @returns {undefined}
 */
myMap.prototype.LocationSearch = function() {
	var self = this;
	self.community.index = 0;
	self.community.localSearchResult = new Array();
	self.community.infoList = new Array();
	keys = self.community.keys;
	//console.log('正在搜索');
	//console.log(keys);

	//查询完毕的回调函数
	var searchComplete = function(results) {
		//console.log(results);
		if (local.getStatus() != BMAP_STATUS_SUCCESS) {
			return;
		}
		var info = null;
		for (var cnt = 0; cnt < results.getCurrentNumPois(); cnt++) {
			var point = results.getPoi(cnt);
			//resultList.push(addMarker(results, point, cnt));
			info = addMarker(results, point, self.community.index);
			if (self.community.key == results.keyword) {
				self.community.infoList.push(info);
				self.community.localSearchResult.push(self.getRightList(results.keyword, info, self.community.index));
				self.community.index++;
			}
		}
		if (_5i5jmap.community.infoList.length > 0) {
			self.localSearchUpdateRight();
			//重设窗口大小信息.自定义滚动条
			mapBoxResize();
		}
	};

	var addMarker = function(results, point, index) {
		var myIcon = getIcon(results.keyword);
		var marker = new BMap.Marker(point.point, { icon : myIcon });
		var html = "<div style='line-height:1.8em;font-size:12px;'>";
		
		var distance = self.mapObj.getDistance( new BMap.Point(self.community.x, self.community.y), point.point);
		html = html + "离该小区约" + returnFloat1(parseInt(distance) / 1000) + "公里";
		html = html + "</br>地址:" + point.address;
		if ($.trim(point.phoneNumber) != '' && $.trim(point.phoneNumber) != 'undefined')
			html = html + "</br>电话:" + point.phoneNumber + "</br>";
		html = html + "</div>";
		var infoWindow = new BMap.InfoWindow(html, {
			offset : new BMap.Size(20, -35),
			title : '<b style="font-size:14px;">' + point.title + '</b>',
			width : 250
		}); // 创建信息窗口对象
		//var label = new BMap.Label(String.fromCharCode(65 + index));
		var label = new BMap.Label(index);
		label.setStyle({
			border : '0',
			'background' : 'none',
			'color' : '#fff'
		});
		label.setOffset(new BMap.Size(12, 8));
		if (results.keyword == _5i5jmap.community.key) {
			marker.setLabel(label);
		}
		marker.disableDragging();
		//marker.addEventListener("click", function() {
		//  marker.openInfoWindow(infoWindow);
		//  $('#searchList .line_b .t1').addClass('no_bg');
		//  $('#searchList .line_b .t1').eq(index).removeClass('no_bg').click();
		//});
		marker.addEventListener("mouseout", function() {
			markerMouseout(index);
			marker.closeInfoWindow();
			if (results.keyword == _5i5jmap.community.key) {
				$('#searchList .line_b .t1').addClass('no_bg');
			}
		});
		marker.addEventListener("mouseover", function() {
			markerMouseover(index);
			marker.closeInfoWindow();
			marker.openInfoWindow(infoWindow);
			if (results.keyword == _5i5jmap.community.key) {
				$('#searchList .line_b .t1').addClass('no_bg');
				//console.log('右侧对应的记录');
				//console.log($('#searchList .line_b .t1').eq(index));
				$('#searchList .line_b .t1').eq(index).removeClass('no_bg').click();
			}
		});
		marker.enableMassClear(); //允许覆盖物在map.clearOverlays方法中被清除。
		self.mapObj.addOverlay(marker);
		var info = [];
		info['sn'] = index;
		info['title'] = point.title;
		
		var distance = self.mapObj.getDistance(new BMap.Point(self.community.x, self.community.y), point.point);
		info['distance'] = returnFloat1(parseInt(distance) / 1000);
		info['address'] = point.address;
		info['phoneNumber'] = point.phoneNumber;
		info['popinfo'] = infoWindow;
		info['marker'] = marker;
		return info;
	};

	this.mapObj.clearOverlays();
	if (self.community.id != '') {
		if (self.community.key != '') {
			this.createCircle(this.community.x, this.community.y, 2200);
			self.addTheCommunity(); //添加本小区标注
		}
		//                          self.loadTheCommunity();//直接加载正常.调用绑在的数据,就不正常了.
	} else {
		loadCommunity();
	}
	//执行搜索
	var local = new BMap.LocalSearch(this.mapObj, {
		onSearchComplete : searchComplete
	}); //构造一个查询
	for (i = 0; i < keys.length; i++) {
		if (keys[i] == self.community.key) {
			local.searchNearby(keys[i], new BMap.Point( this.community.x, this.community.y), 2000);
		} else {
			local.searchInBounds(keys[i], this.mapObj.getBounds());
		}
	}
	//if (keys.length == 0) {
	//  loadCommunity();
	//}
};

/**
 * 取一个结果
 */
myMap.prototype.getRightList = function(key, info, index) {
	var html = '';
	html += '<div class="t1 no_bg" index="' + index + '" key="' + key + '">';
	//***************************************
	html += '<div class="info_box2">';
	//-----------------------
	//小区名字区域
	html += '<div class="i1">';
	html += '<div class="ll_box">';
	//html += '<div class="num">' + String.fromCharCode(65 + info['sn']) + '</div>';
	html += '<div class="num">' + info['sn'] + '</div>';
	html += '<p><a href="javascript:void(0);" class="blue">' + info['title'] + '</a><br />';
	html += '离该小区<span class="emp_r">' + info['distance'] + '</span>公里</p>';
	html += '</div>';
	html += '<div class="rr_box"><img src="/themes/new2013/files/map/image/house_pic03.jpg" width="70" height="48" /></div>';
	html += '</div>';
	//-----------------------
	html += '</div>';
	//清除浮动
	html += '<div class="cl"></div>';
	//+++++++++++++++++++++++
	//小区信息区域
	html += '<div class="i2">';
	html += '<p>地址：' + info['address'] + '<br />';
	//判断是否有电话
	var phone = info['phoneNumber'];
	if (typeof phone === 'undefined') {
		phone = '';
	}
	html += '电话：' + phone + ' </p>';
	html += '</div>';
	//+++++++++++++++++++++++
	//***************************************
	html += '<div class="cl"></div>';
	html += '</div>';
	return html;
};
	
/**
 * 右侧搜索结果与右侧绑定
 */
$(function() {
	$('#searchList .line_b .t1').live({
		mouseover : function() {
			var popinfo = $(this).data('popinfo');
			var marker = $(this).data('marker');
			$(this).data('marker').closeInfoWindow();
			$(this).data('marker').openInfoWindow($(this).data('popinfo'));
			$(this).removeClass('no_bg');
			var marker_img = $('.BMap_Marker[class="BMap_Marker"]').eq($(this).attr('index')).find('img');
			marker_img.css('left',marker_img.position().left + 240);
		},
		mouseout : function() {
			$(this).addClass('no_bg');
			$(this).data('marker').closeInfoWindow();
			var marker_img = $('.BMap_Marker[class="BMap_Marker"]').eq($(this).attr('index')).find('img');
			marker_img.css('left',marker_img.position().left - 240);
		},
		click : function() {
		}
	});
});

/**
 * 更新右侧结果
 * @param {type} keyword
 * @param {type} resultList
 * @returns {undefined}
 */
myMap.prototype.localSearchUpdateRight = function() {
	var resultList = this.community.localSearchResult;
	//console.log(resultList);
	var infoList = this.community.infoList;
	//console.log(infoList);
	//console.log($('#localSearchList'));
	$('#localSearchList .sdr_title').html('<div class="arrow"></div><span class="f14">' + this.community.key + '</span>');
	$('#localSearchList .sdr_title').parent().find('ul').hide();
	if (this.community.id == '') {
		$('#localSearchList .sdr_title').find('p').remove();
	}
	$('#localSearchList .set_info_house').remove();
	var html = '<div class="menu"></div>';
	$('#localSearchList #searchList .line_b').html(html);
	for (i = 0; i < resultList.length; i++) {
		$('#localSearchList #searchList .line_b .menu').append(resultList[i]);
	}
	var tool = '<div class="bar"><div class="mousearea"></div></div>';
	$('#localSearchList #searchList .slide_bar').html(tool);
	$('#localSearchList #searchList .line_b .t1').each(function(index) {
		$(this).data('popinfo', infoList[index]['popinfo']);
		$(this).data('marker', infoList[index]['marker']);
	});
	//console.log('right show');
	$('#localSearchList').show().siblings().hide();
	$('#localSearchList #searchList').show();
	$('#right_index').hide();
	$('#rightRewrite').show();
	//this.readScroll('#searchList .line_b', 0);
};

/**
 * 根据坐标划圆
 */
myMap.prototype.createCircle = function(x, y, radio) {
	var mapCircle = new BMap.Circle(new BMap.Point(x, y), radio, {
		strokeWeight : 1,
		strokeColor : '#333',
		strokeOpacity : 0.2,
		fillColor : '#333',
		fillOpacity : 0.2
	});
	this.mapObj.addOverlay(mapCircle);
};

/**
 * 地图搜索
 */
$(function() {
	$('#LoginForm_password').live('keydown', function(evt) {
		evt = evt ? evt : window.event;
		if (evt.keyCode == 13) {
			ajaxLogin();
			return false;
		}
	});
	$('#mapExchangeKey').live('keydown', function(evt) {
		var val = $(this).val();
		if (val == '请输入关键字(楼盘名或路段名)') {
			$(this).val('');
		}
		evt = evt ? evt : window.event;
		if (evt.keyCode == 13) {
			mapSearch('exchange');
			return false;
		}
	});
	$('#mapRentKey').live('keydown', function(evt) {
		var val = $(this).val();
		if (val == '请输入关键字(楼盘名或路段名)') {
			$(this).val('');
		}
		evt = evt ? evt : window.event;
		if (evt.keyCode == 13) {
			mapSearch('rent');
			return false;
		}
	});
	$('#searchAutoComplete').live('keyup', function(evt) {
		evt = evt ? evt : window.event;
		if ($('#searchAutoComplete').val() != '' && evt.keyCode == 13) {
			positionLocation();
			return false;
		}
	});
	//二手房搜索
	$('.ssb_popup_box .go_btn1').live('click', function() {
		//console.log('search exchange');
		mapSearch('exchange');
	});
	//租房房搜索
	$('.ssb_popup_box .go_btn2').live('click', function() {
		mapSearch('rent');
	});
	/**
	 * 切换二手房搜索和租房搜索区域
	 */
	$('.ssb_popup_box .m1,.ssb_popup_box .m2').live('click', function() {
		if ($(this).hasClass('m1')) {
			$('#search_exchange').show();
			$('#search_rent').hide();
			$('#mapExchangeKey')[0].focus();
		} else {
			$('#search_exchange').hide();
			$('#search_rent').show();
			$('#mapRentKey')[0].focus();
		}
	});
	//二手房区域二级联动
	$('#exchangeMainArea .sp_box .list').live('click', function() {
		var areaid = $(this).find('a').attr('selectid');
		var text = $(this).find('a').html();
		if (text == '不限') {
			$("#exchangeSubArea .sp_box").html('');
		} else {
			$("#exchangeSubArea .sp_box").load("/map/sublocation/?location=" + areaid);
		}
		$('#exchangeSubArea').find('cite').html('选择商圈');
	});
	//租房区域二级联动
	$('#rentMainArea .sp_box .list').live('click', function() {
		var areaid = $(this).find('a').attr('selectid');
		var text = $(this).find('a').html();
		if (text == '不限') {
			$("#rentSubArea .sp_box").html('');
		} else {
			$("#rentSubArea .sp_box").load("/map/sublocation/?location=" + areaid);
		}
		$('#rentSubArea').find('cite').html('选择商圈');
	});
	/**
	 * 模拟下拉菜单
	 */
	$('.select_box').live('click', function() {
		$('.ssb_popup_box').unbind("click");
		var ul = $(this).find('.sp_box');
		var display = ul.is(':hidden');
		$('.select_box .sp_box').hide();

		if (display) {
			ul.slideDown("fast");
		} else {
			ul.slideUp("fast");
		}

		$('.ssb_popup_box').click(function() {
			$('.select_box .sp_box').hide();
		});
	});

	/**
	 * 选中下拉菜单
	 */
	$('.select_box .sp_box .list').live('click', function() {
		var txt = $(this).find('a').text();
		$(this).parents('.select_box').find('cite').html(txt);
		//$(this).parents('.select_box').find('input').val(txt);
		var value = $(this).find('a').attr("selectid");
		var toValue = $(this).parents('.select_box').attr('toValue');
		$('#' + toValue).val(value);
		var thisId = $(this).parents('.select_box').attr('id');
		if (thisId == 'rentMainArea' || thisId == 'rentSubArea' || thisId == 'exchangeMainArea' || thisId == 'exchangeSubArea') {
			$(this).parents('.select_box').attr('x', $(this).attr('x'));
			$(this).parents('.select_box').attr('y', $(this).attr('y'));
		}
		$(this).parents('.select_box').find('.sp_box').hide();

		var form = $('#' + toValue).parent('form'); //数据提交的form表单
		var formId = form.attr('id');
		if (formId == 'exchangeForm') {
			var isNull = true;
			form.find('input').each(function() {
				var val = $(this).val();
				if (val != '') {
					isNull = false;
				}
			});

			if (!isNull) {
				$('#exchangeSubBtn').removeClass().addClass('go_btn1');
			} else {
				$('#exchangeSubBtn').removeClass().addClass('default_search_btn');
			}
		} else {
			var isNull = true;
			form.find('input').each(function() {
				var val = $(this).val();
				if (val != '') {
					isNull = false;
				}
			});
			if (!isNull) {
				$('#rentSubBtn').removeClass().addClass('go_btn2');
			} else {
				$('#rentSubBtn').removeClass().addClass('default_search_btn');
			}
		}
		return false;
	});

	//搜索输入框输入判断
	$('#mapExchangeKey, #mapRentKey').live('keyup', function() {
		var form = $(this).parentsUntil('form').parent(); //数据提交的form表单
		var formId = form.attr('id');
		if (formId == 'exchangeForm') {
			var isNull = true;
			form.find('input').each(function() {
				var val = $(this).val();
				if (val != '') {
					isNull = false;
				}
			});

			if (!isNull) {
				$('#exchangeSubBtn').removeClass().addClass('go_btn1');
			} else {
				$('#exchangeSubBtn').removeClass().addClass('default_search_btn');
			}
		} else {
			var isNull = true;
			form.find('input').each(function() {
				var val = $(this).val();
				var name = $(this).attr('name');
				if (name != 'searchType') {
					if (val != '') {
						isNull = false;
					}
				}
			});

			if (!isNull) {
				$('#rentSubBtn').removeClass().addClass('go_btn2');
			} else {
				$('#rentSubBtn').removeClass().addClass('default_search_btn');
			}
		}
	});
});

/**
 * 执行二手房租房搜索,设置条件
 */
function mapSearch(key) {
	var self = _5i5jmap;
	_5i5jmap.mapObj.clearOverlays();
	_5i5jmap.mapObj.setZoom(13);
	_5i5jmap.operate.loadList = false;
	//设置当前小区为空
	self.community.id = '';
	self.condition.searchType = key;
	if (key == 'exchange') {
		if ($('#mapExchangeKey').val() == '请输入关键字(楼盘名或路段名)') {
			$('#mapExchangeKey').val('');
		}
		self.condition.searchData = $('#exchangeForm').serialize();
		if ($('#mainArea').val() != '') {
			if ($('#subArea').val() != '') {
				//console.log('移动1');
				var x = $('#exchangeSubArea').attr('x');
				var y = $('#exchangeSubArea').attr('y');
				var Point = new BMap.Point(x, y);
			} else {
				//console.log('移动2');
				var x = $('#exchangeMainArea').attr('x');
				var y = $('#exchangeMainArea').attr('y');
				var Point = new BMap.Point(x, y);
			}
		} else {
			//根据搜索条件找到第一个小区的位置定位过去
		}
		self.mapObj.panTo(Point);
	}
	if (key == 'rent') {
		if ($('#mapRentKey').val() == '请输入关键字(楼盘名或路段名)') {
			$('#mapRentKey').val('');
		}
		self.condition.searchData = $('#rentForm').serialize();
		if ($('#rmainArea').val() != '') {
			if ($('#rsubArea').val() != '') {
				//console.log('移动3');
				var x = $('#rentSubArea').attr('x');
				var y = $('#rentSubArea').attr('y');
				var Point = new BMap.Point(x, y);
			} else {
				//console.log('移动4');
				var x = $('#rentMainArea').attr('x');
				var y = $('#rentMainArea').attr('y');
				var Point = new BMap.Point(x, y);
			}
		} else {
		}
		self.mapObj.panTo(Point);
	}
	//console.log(self.condition.searchData);
	$('.ssb_wrap,.login_bg').hide();
	loadCommunity();
}

/**
 * 本地搜索
 * @param {type} key
 * @returns {undefined}
 */
function LocationSearch(key) {
	//console.log('正在执行本地搜索,关键字:' + key);
	var self = _5i5jmap;
	self.community.key = key;
	$('#communityDetail').hide();
	$('#communityTip').hide();
	locationSerchLoadKey();
	self.LocationSearch();
}
function locationSerchLoadKey() {
	var self = _5i5jmap;
	self.community.keys = new Array();
	if (self.community.key != '') {
		self.community.keys.push(self.community.key);
	}
	$('.nav .item_box #menu_sub_1 li input:checkbox').each(function() {
		if ($(this).is(':checked')) {
			self.community.keys.push($(this).val());
		}
	});
}
</script>

<style type="text/css">
body { overflow: hidden; }
html { *+overflow: hidden;}

#center .search_box { height: 34px; overflow: hidden; }
#center .map_box { border-top: 1px solid #D5D4D9; border-left: 1px solid #D5D4D9; }
.BMap_Marker .BMapLabel { border: none; }
#center .nav { height: auto; }
.mp_info02 .rr_box { overflow: hidden; width: 120px; }
.mp_info02 .rr_box a { overflow: hidden; width: 120px; height: 23px; line-height: 23px; display: block;}
.login_pop_box .info { margin-top: 15px; }
.topic_wrap { position: absolute; width: 100%; top: 0; left: 100%; padding: 0; z-index: 2000; display: block; }
.topic_wrap .ll_box { float: left; position: absolute; }
.topic_wrap .rr_box { width: auto; padding-left: 158px; }
.slides_container { overflow: hidden; }
#center .map_box .map { height: 520px; }
body { min-width: 1000px; }
.combox .allViewUl { border-bottom: 1px solid #D5D4D9; list-style: none; }
.loginInfo { height: 30px; line-height: 30px; text-align: left; color: red; margin-left: 40px; }
.cmbox  .allViewUl .viewIntro { background-color: #F9F9FB; border-right: 1px solid #D5D4D9; border-left: 1px solid #D5D4D9; border-top: 1px solid #D5D4D9; padding: 5px 10px; width: 249px; cursor: pointer; }
.cmbox .allViewUl .viewIntro span { color: #999999; float: left; line-height: 25px; text-align: left; width: 124px; }
.ssb_popup_box .form_box dd .select_box { width: 108px; padding-left: 0px; }
.ssb_popup_box .form_box dd .select_box cite { width: 98px; padding-left: 10px; display: block; font-family: "微软雅黑"; font-style: normal; }
#center .nav { z-index: 1000;}
#center .nav .title { cursor: pointer; }
#center .nav ul.defaultShow { display: block; }
.sp_box { display: none; }
.map_pop_box.shadow { left: 12%; top: 10%; box-shadow: 3px 2px 8px #000; position: absolute; z-index: 2000; width: 676px; padding: 2px 2px 10px 2px; background-color: #fff; margin: 0 auto; }
.map_pop_box .title_box, .map_pop_box .title_box2 { width: 676px; height: 38px; background: url(/themes/new2013/files/map/image/login_sp.jpg) repeat-x 0 -45px; }
.map_pop_box .title_box h2 { float: left; padding-left: 20px; text-align: left; }
.login_bg { z-index: 1999; }
.login_pop_box { z-index: 100000; }
.e_toolbar { z-index: 200; }
.bh_house { z-index: 99999; }
.map_pop_box.shadow { z-index: 1998; }
/*滚动条*/
.slide_bar {
	position: absolute;
	top: 0px;
	right: -7px;
	width: 2px;
	height: 600px;
	background: url("/themes/new2013/files/map/image/bar_bg.jpg") repeat-y scroll 0 0 transparent;
	line-height: 0;
}

.slide_bar .bar {
	width: 8px;
	background: url("/themes/new2013/files/map/image/bar_line.jpg") repeat-y scroll 0 0 transparent;
	cursor: pointer;
	padding: 0;
	top: 25px;
	right: -3px;
	border: 0;
	/*padding: 0;top: 0; right: -2px; width: 16px;*/
}

#right_index .slide_bar { right: 5px; }
.scrollbar .bar .mousearea { position: absolute; top: 0; left: -10px; width: 22px; height: 100%; }
.bh_house .bh_slide_bar .pointer_bar { left: 0px;}
.bh_house .bh1 .item .b2 .pic_wrap { left: 2px; top: 1px; z-index: 1000; }
/*浏览过的小区
.bh_house{ height:323px; background-position-y: 295px; }
*/
/*解决地图标注提示信息窗口影响被影响*/
#center .map_box .map img { width: auto; }
.topic_wrap { background-image: none; }
.topic_wrap .bg { width: 100%; height: 100%; z-index: 99990; position: absolute; left: 0px; background-color: #000; opacity: 0.6; filter: alpha(Opacity = 60); }
.topic_wrap .topic_conent { padding-left: 200px; z-index: 99992; position: relative; }
.topic_warp .rr_box { background: url(../image/topic/t_bg01.jpg) repeat-y left top; }
/*自动提示框*/
.ui-autocomplete { z-index: 99999; }
.map_pop p { line-height: 20px; }
<!--
.hide { display: none; }
-->
#searchList .cmbox .menu { left: *+0px; }
</style>

<!-- ########## 检 测 代 码 开 始 ########## -->
<!--
<noscript><img src="http://trc.adsage.com/trc/atac/conv.gif?id=1083" width="0px" style="display:none !important;"/></noscript>
<script type="text/javascript">
/*var pubsage_conv_id = 1083;
var pubsage_conv = pubsage_conv || [];
    function Ps_Click(num) {
        pubsage_conv.push(['sendClickEvent',false, num]);
        pubsage_conv.push(['sendTracking']);
    }
    (function () {
        try {
            var d = document;
            var at = d.createElement('script'); at.type = 'text/javascript'; at.async = true;
            at.src = ('https:' == document.location.protocol ? 'https:' : 'http:') + '//trc.adsage.com/trc/atac/conv_x.js?id=' + pubsage_conv_id;
            var s = d.getElementsByTagName('script')[0]; s.parentNode.insertBefore(at, s);
        } catch (e) { }
    })();*/
</script>
-->

<!-- ########## 检 测 代 码 结 束 ########## -->
<script type="text/javascript">
//var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
//document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fb43bb26486d6ce24dcd04b25a379cc12' type='text/javascript'%3E%3C/script%3E"));
var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-1318775-29']);
_gaq.push(['_setDomainName','none']);
_gaq.push(['_addOrganic', 'soso', 'w']);
_gaq.push(['_addOrganic', 'yodao', 'q']);
_gaq.push(['_addOrganic', 'sogou', 'query']);
_gaq.push(['_trackPageview']);
_gaq.push(['_trackPageLoadTime']);

_gaq.push(['t2._setAccount', 'UA-1318775-1']);
_gaq.push(['t2._addOrganic', 'soso', 'w']);
_gaq.push(['t2._addOrganic', 'yodao', 'q']);
_gaq.push(['t2._addOrganic', 'sogou', 'query']);
_gaq.push(['t2._setDomainName', '5i5j.com']);
_gaq.push(['t2._trackPageview']);
_gaq.push(['t2._trackPageLoadTime']);

(function() {
  var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
  ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
  var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
})();
    
//var _mvq = _mvq || [];
//_mvq.push(['$setAccount', 'm-23385-1']);
//_mvq.push(['$logConversion']);
//(function() {
//	var mvl = document.createElement('script');
//	mvl.type = 'text/javascript'; mvl.async = true;
//	mvl.src = ('https:' == document.location.protocol ? 'https://static-ssl.mediav.com/mvl.js' : 'http://static.mediav.com/mvl.js');
//	var s = document.getElementsByTagName('script')[0];
//	s.parentNode.insertBefore(mvl, s); 
//})();	
</script>
	<script type="text/javascript" src="http://tj.5i5j.com/themes/new2013/files/js/slides.min.jquery.js?version=2013-12-23_10:30"></script>
	<script type="text/javascript" src="http://tj.5i5j.com/themes/new2013/files/js/sly.min.js?version=2013-12-23_10:30"></script>
	<script type="text/javascript" src="http://tj.5i5j.com/themes/new2013/files/js/jquery.easing.min.js?version=2013-12-23_10:30"></script>
</body>
</html>