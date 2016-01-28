
/**
 * 该文件主要是通过js语言模拟一个类似Java Map的功能
 */
if(!Map){
function Map() {
	/**
	 * 定义Map内存的键值对
	 */
	var struct = function(key, value) {
		this.key = key;
		this.value = value;
	};

	
	/**
	 * 向map中put一个键值对
	 */
	var put = function(key, value){
		for (var i = 0; i < this.arr.length; i++) {
			if ( this.arr[i].key === key ) {
				this.arr[i].value = value;
				return;
			}
		}
		this.arr[this.arr.length] = new struct(key, value);
	}
	var set = function(key, value){
		for (var i = 0; i < this.arr.length; i++) {
			if ( this.arr[i].key === key ) {
				this.arr[i].value = value;
				return;
			}
		}
		this.arr[this.arr.length] = new struct(key, value);
	};

	/**
	 * 根据key找到对应的值
	 */
	var get = function(key) {
		for (var i = 0; i < this.arr.length; i++) {
			if ( this.arr[i].key === key ) {
				return this.arr[i].value;
			}
		}
		return null;
	};

	/**
	 * 从map中移除当前key以及对应的值
	 */
	var remove = function(key) {
		var v;
		for (var i = 0; i < this.arr.length; i++) {
			v = this.arr.pop();
			if ( v.key === key ) continue;
			this.arr.unshift(v);
		}
	};

	/**
	 * 获取map中值放的key的个数
	 */
	var size = function() {
		return this.arr.length;
	};
    
	/**
	 * 获取存放在map中所有的key
	 */
	var keys = function(){
		this.keyArray = new Array();
		for (var i = 0; i < this.arr.length; i++){
			this.keyArray[i] = this.arr[i].key;
		}
		return this.keyArray;
	};
	
	var values = function(){
		this.valArray = new Array();
		for (var i = 0; i < this.arr.length; i++){
			this.valArray[i] = this.arr[i];
		}
		return this.valArray;
	};
    
	/**
	 * 判断map是否是空的
	 */
	var isEmpty = function() {
		return this.arr.length <= 0;
	};
	
    this.keyArray = new Array();
    this.valArray = new Array();
    
	this.arr = new Array();
	this.get = get;
	this.put = put;
	this.set = set;
	this.remove = remove;
	this.size = size;
	this.isEmpty = isEmpty;
	this.keys = keys;
	this.values = values;
}
}

if(Map && !Map.prototype.put){
	Map.prototype.put = Map.prototype.set;
}

if(Map && !Map.prototype.keys){
	Map.prototype.keys = function(){
		var ks = new Array();
		this.forEach(function (item, key, mapObj) {
		    ks[ks.length] = key;
		});
		return ks;
	}
}

if(Map && !Map.prototype.remove){
	//Map.prototype.remove = Map.prototype.delete;
	Map.prototype.remove = function(key){
		alert(Map);
		//self.delete(key);
	};
}

function ifDef(obj){
	return !(typeof(obj)=="undefined");
}

if(typeof(obj)=="undefined"){
/**
 * 在javascript console上输出信息
 * @param obj
 */
function log(obj){
	if(window.console) console.log(obj);
}
}

/**
 * 在页面中动态引入Javascript脚本
 * @param scriptPath	要引入的JS脚本路径
 */
function addJavaScript(scriptPath){
  var head= document.getElementsByTagName('head')[0];  
  var script= document.createElement('script');
  script.type= 'text/javascript';

  script.onreadystatechange= function () { if (this.readyState == 'complete') callback(); }
  script.onload= function(){ callback(); }
  script.src= scriptPath;
  head.appendChild(script);
}

/**
 * 加入收藏夹
 */
if(typeof(addFavour)=="undefined"){
function addFavour(){
  var ctrl = (navigator.userAgent.toLowerCase()).indexOf('mac') != -1 ? 'Command/Cmd' : 'Ctrl';
  if (document.all) {
    window.external.addFavorite(window.location, document.title);
  }else {
    alert('由于浏览器原因，请用'+ ctrl +'+D进行添加');
  }
}
}


if( typeof(openModelAjaxDialog)=="undefined"){
/**
 * 打开一个Ajax取数的模态对话框
 * 要执行本函数，需引用:
 * ${globalUrl}js/jquery.artDialog.source.js
 * ${globalUrl}js/artDialog.iframeTools.source.js
 * @param id	对话框ID
 * @param title	对话框标题
 * @param url	取数URL
 * @param type	取数提交方法，GET or POST
 * @param datType	返回数据格式,html、json、XML等
 * @param postion	对话框位置属性，例如：postion: {zIndex: 9999, left: "180px", top: "20px"} 
 */
function openModelAjaxDialog(id, title, url, method, datType, postion){
  //弹出框js引用 
  var _dialogStr = "<link type='text/css' rel='stylesheet' href='"+globalUrl+"js/skins/gray.css'>";
  _dialogStr += "<link rel='stylesheet' type='text/css' href='"+globalUrl+"css/css.css'>";
  _dialogStr += "<link rel='stylesheet' type='text/css' href='"+globalUrl+"css/dlzc.css'>";

  var _method = method || "get";
  var _datType = datType || "html";
  $.ajax({
    url : url,
    type : _method,
    dataType : _datType,
    async: false,
    success : function (data) {
      data = _dialogStr + data;
      var dataContent = '<html>'+data+'</html>';
      art.dialog({
        id: id,
        title: title,
        content: dataContent,
        lock: true,
        drag: false,
        resize: false,
        max: false,
        min: false,
        zIndex: (postion && postion.zIndex || 999)
      });
    }
  });
}
}

/**
 * 房贷计算器对话框
 */
if(typeof(openCalcDialog)=="undefined"){
function openCalcDialog(unitPrice, houseArea){
  //var url = "${globalUrl}houseSecond.show?actionMethod=getCalculator&unitPrice=${house.unitPrice}&area=${house.area}";
  //var url = globalUrl+"houseSecond.show?actionMethod=getCalculator&unitPrice="+unitPrice+"&area="+houseArea;
  var url = globalUrl+"houseSecond.show?actionMethod=getCalculator";
  if(unitPrice)url += "&unitPrice="+unitPrice;
  if(houseArea) url += "&area="+houseArea;	
  openModelAjaxDialog('calculator', "&nbsp;&nbsp;个人住房商业贷款", url );
}
}

/**
 * 登录对话框
 * @param target
 * @param housetype
 */
if(typeof(loginBox)=="undefined"){
function loginBox(target, housetype){
  //var url = "${globalUrl}login.show?actionMethod=loginCheck&target="+(target || "");
  var url = globalUrl+"login.show?actionMethod=loginCheck";
  if(target && target != "") url = url+"&target="+target;
  if(housetype && housetype != "") url = url+"&housetype="+housetype;
  openModelAjaxDialog('login', "&nbsp;&nbsp;登录", url );
};
}

/**
 * 注册对话框
 */
if(typeof(registerBox)=="undefined"){
function registerBox(){
  //var url = "${globalUrl}register.show?actionMethod=register"; //GET显示注册框，POST提交注册信息
  var url = globalUrl+"register_member/register"; //GET显示注册框，POST提交注册信息
  openModelAjaxDialog('register', "&nbsp;&nbsp;注册<a onclick=\"infoShow('registerReason');\" style=\"cursor:pointer; color:red;\">？</a>", url );
};
}

/**
 * 显示提示对话框
 * @param content
 * @param title
 */
if(typeof(infoBox)=="undefined"){
function infoBox(content, title, getTagName,setTagName){
	var dialogTitle = title || "操作成功!";
	var _gTagName = getTagName || "infoDialog";
	var _sTagName = setTagName || "infoDialogContent";
	try{
		var dataContent = document.getElementById(_gTagName);
		art.dialog({
			id:'infoDialog',
	 		title: dialogTitle,
	 		content: dataContent,
	 		lock: true,
	 		drag: false,
		    resize: false,
		    max: false,
		    min: false,
		  	zIndex: 99999
		});
		$("#"+_sTagName).text(content);
	}catch(e){
		if(window.console) console.log(e);
	}
}
}

if(typeof(postBymyURL)=="undefined"){
/**
 * 异步调用服务器数据
 * @param actionUrl
 * @param dataString
 * @param resultArea 结果显示的区域，该参数不用，后面要去掉
 * @param method
 * @param datType
 * @param succCallback
 * @param errCallback
 */
//function postBymyURL(actionUrl, dataString, resultArea, method, datType, succCallback, errCallback) {
function postBymyURL(actionUrl, dataString, method, datType, succCallback, errCallback) {
	var _method = method || "POST";
	var _datType = datType || "html";
	var _succCallback = succCallback || function(data){};
	var _errCallback = errCallback || function(data){};
	$.ajax({
		url : actionUrl,
		data : dataString,
		type : _method,
		dataType : _datType,
		success : _succCallback,
		error : _errCallback
	});
}
}

if(typeof(reloadCaptcha)=="undefined"){
/**
 * 刷新验证码
 * @param imgCaptchaId 验证码图片对象ID
 */
function reloadCaptcha(imgCaptchaId) {
	var _imgCaptchaId = imgCaptchaId || "imageCaptcha";  //默认图片验证码对象ID是imageCaptcha
    var captchaURL = $('#'+_imgCaptchaId).attr('src');  
    captchaURL = captchaURL.replace(captchaURL.substring(captchaURL.indexOf("=")+1, captchaURL.length), Math.floor(Math.random()*9999999999));  
    $('#'+_imgCaptchaId).attr('src', captchaURL);  
}
}

if(typeof(scrollTo)=="undefind"){
function scrollTo(posId, scrollSpeed){
	var scroll_offset = $("#"+posId).offset();  //得到pos这个div层的offset，包含两个值，top和left  
	$("body,html").animate({
		scrollTop:scroll_offset.top  //让body的scrollTop等于pos的top，就实现了滚动   
	}, (scrollSpeed || 0));
}
}

var _searchHintText = "请输入城区、商圈、小区 ...";
/**
 * 顶部搜索框
 */
function topSearch(){
	var searchInput = $("#topSearchBar").val();
	//var housetype = 1;
	var houseType = "chushou";
	if (backType && backType == '3'){
		//housetype = 2;
		houseType = "chuzu";
	}else if (backType && backType == '5'){
		//housetype = 3;
		houseType = "xiaoqu";
	}
	if(searchInput == _searchHintText || searchInput.trim() == ""){
		alert("请输入搜索条件！");
	}else{
		//var searchActionUrl = "${globalUrl}houseSearch.show?actionMethod=doSearch&searchModule=1&type=1&houseType="+housetype;
		//searchActionUrl = searchActionUrl +"&searchInput="+searchInput;
		//var searchActionUrl = globalUrl+"welcome.show?actionMethod=dimQuery&type=1&searchModule="+housetype;
		//searchActionUrl = searchActionUrl +"&inputSearch="+searchInput;
		var searchControllerUrl = globalUrl + houseType + "/cx" + searchInput;
//		window.open(searchControllerUrl);
		location.href=searchControllerUrl;
	}
}


$(document).ready(function() {
	try{
		if($("#topSearchBar")){
			$("#topSearchBar").val(_searchHintText);
			$("#topSearchBar").focus(function(){ if ($(this).val() ==_searchHintText) $(this).val(""); });
			$("#topSearchBar").blur(function(){ if ($(this).val() =='') $(this).val(_searchHintText); });
			$("#searchBtn").click(function(){ topSearch(); });
			$("#topSearchBar").keyup(function(event){if(event.which==13) {topSearch();}});
			$("#topSearchBar").blur();
			
			//$("#textPhonePad").val(_searchHintText);
			//$("#textPhonePad").focus(function(){ if ($(this).val() ==_searchHintText) $(this).val(""); });
			//$("#textPhonePad").blur(function(){ if ($(this).val() =='') $(this).val(_searchHintText); });
			//$("#searchBtnPad").click(function(){ topSearch(); });
		}
	}catch(e){
		if(window.console) console.log(e);
	}
});


function turnToTop(){
  $('body,html').animate({scrollTop:0},200);
}
function historyFootprints(){
  var url=globalUrl+"usercenter.do?actionMethod=browseHistory&isCutPage=false";
  window.open(url);
}
function memberPoints(){
  var url=globalUrl+"usercenter.do?actionMethod=scoreManager&isCutPage=false";
  window.open(url);
}
function favouritesManagement(){
  var url=globalUrl+"usercenter.do?actionMethod=collectment&isCutPage=false";
  window.open(url);
}
function customerService(){
  var url=globalUrl+"company.show?actionMethod=showservice";
  window.open(url);
}
function getUrlParam(name) {
  var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
  var r = window.location.search.substr(1).match(reg);  //匹配目标参数
  if (r!=null) return unescape(r[2]); return null; //返回参数值
}
function talkQQ(){
  $("#_Ten_rightDiv a").click();
}

if(typeof(showPriceChart)=="undefined"){
/**
 * 初始化价格走势图
 * @param data			数据对象
 * @param containerId	显示容器ID
 * @param label			显示标签
 * @param colour		显示的颜色
 */
function showPriceChart(data, containerId, label, colour){
  $("#"+containerId).show();
  var formatDateString = '%m月';
  var winWidth = 1024;
  if (window.innerWidth) {
    winWidth = window.innerWidth;
  } else if ((document.body) && (document.body.clientWidth)) {
    winWidth = document.body.clientWidth;
  }
  if (winWidth <= 758) {
    formatDateString = '%m月';
  }
  var plot1 = $.jqplot(containerId, [ data ], {
    //title : '二手房挂牌均价走势',
    axes : {
      xaxis : {
        //label: "月份", // x轴显示标题
        numberTicks : data.length,
        pad : 1,
        showTicks : true,
        tickInterval : '1 month',
        renderer : $.jqplot.DateAxisRenderer,
        tickOptions : {
          formatString : formatDateString
        }
      },
      yaxis : {
        //label: "均价", // y轴显示标题
        tickOptions : {
          formatString : '￥%.2f'
        }
      }
    },
    seriesDefaults: {
        show: true,     // wether to render the series.
        label: label,      // label to use in the legend for this line.
        color: (colour || ''),      // CSS color spec to use for the line.  Determined automatically.
        lineWidth: 1.5
    },
    legend: { 
    	show: true,//设置是否出现分类名称框（即所有分类的名称出现在图的某个位置） 
    	location: 'ne' // 分类名称框出现位置, nw, n, ne, e, se, s, sw, w. 
   	}, 
    highlighter : {
      show : true,
      sizeAdjust : 15
    }
  });//

  $("#"+containerId).hide();
}
}
/**
 * 首页价格走势图
 */
if(typeof(showHomePrice)=="undefined"){
	function showHomePrice(options, containerId, title){
		var formatDateString = '%m月';
		//左y坐标数据
		var yaxis_data1 = options[0].data;
		var  yaxis_data2= options[2].data;
		
		//右y坐标数据
		var y2axis_data = options[1].data;
		var myjqplot = $.jqplot(containerId, [yaxis_data1, y2axis_data, yaxis_data2], {
			title: title || "",
			axes : {
				xaxis : { 
					renderer : $.jqplot.DateAxisRenderer, 
					tickOptions : { formatString : '%m月' },
					numberTicks : yaxis_data1.length,
			        pad : 1,
			        showTicks : true,
			        tickInterval : '1 month'
				},
				yaxis : { labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
					tickOptions : { formatString : '￥%.2f' }
					},
				//y2axis : { tickOptions : { formatString : '￥%.2f' } }
				y2axis : { tickOptions : { formatString : '%.0f' } }
			},
			highlighter : {
				show : true
				// tooltipFormatString: '<b><i><span style="color:red;">hello</span></i></b> %.2f',
				// useAxesFormatters: false
			},
			cursor : { show : true },
		    legend: { 
		    	show: true,//设置是否出现分类名称框（即所有分类的名称出现在图的某个位置） 
		    	location: 'ne' // 分类名称框出现位置, nw, n, ne, e, se, s, sw, w. 
		   	},
			series : [
				{
					label: options[0].label, //数据线图例
					lineWidth : 2,
					color:options[0].color,
					highlighter : {
						show : true,
						tooltipContentEditor : function(str, seriesIndex, pointIndex, plot) {
							var date = plot.data[seriesIndex][pointIndex][0];
							var val = plot.data[seriesIndex][pointIndex][1];
							var html = "<div class='pop'>月份 : " + date;
							html += "  <br>均价: " + val;
							html += "  </div>";
							return html;
						}
					}
				},
				
				{
//					柱状图
					yaxis : 'y2axis',
					renderer: $.jqplot.BarRenderer,
					showMarker: true,
					color:options[1].color,
					rendererOptions: {
								   barPadding: -20,
		                           barWidth: 20,
		                           barMargin: 20
		                         },
		            lineWidth : 1,
					label: options[1].label, 
					showTicks: false,        // 是否显示刻度线以及坐标轴上的刻度值  
                    showTickMarks: false,     //设置是否显示刻度
                    autoscale: true,
                    borderWidth: 1,
                    shadowAlpha:0.5,
                    tickOptions: {
                        show: true,
                        showLabel: false,
                        showMark: false,
                        showGridline: true,
                        formatString: '￥%.2f'
                    }
//					highlighter : {
//						show : true,
//						tooltipContentEditor : function(str, seriesIndex, pointIndex, plot) {
//							var date = plot.data[seriesIndex][pointIndex][0];
//							var val = plot.data[seriesIndex][pointIndex][1];
//							var html = "<div class='pop'>月份 : " + date;
//							html += "  <br>平均租金: " + val;
//							html += "  </div>";
//							return html;
//						}
//					}
				},{
					label: options[2].label, //数据线图例
					color:options[2].color,
					lineWidth : 2,
					highlighter : {
						show : true,
						tooltipContentEditor : function(str, seriesIndex, pointIndex, plot) {
							var date = plot.data[seriesIndex][pointIndex][0];
							var val = plot.data[seriesIndex][pointIndex][1];
							var html = "<div class='pop'>月份 : " + date;
							html += "  <br>均价: " + val;
							html += "  </div>";
							return html;
						}
					}
				}
			]
		});
	}
}
if(typeof(showDoublePriceChart)=="undefined"){
/**
 * 生成双坐标曲线图
 * 用法示例：
	var options = [
     {data: data1, label: "二手房挂牌均价", color: "#00FF00"},
     {data: data2, label: "出租房挂牌均价", color: "#FF0000"}
	];
	showDoublePriceChart(options, "communityprice2", "近12个月房源价格走势");
 * 
 * @param options    	数据及配置
 * @param containerId	显示容器ID
 * @param title			图表标题
 */
function showDoublePriceChart(options, containerId, title){
	var formatDateString = '%m月';
	var yaxis_data = options[0].data;
	var y2axis_data = options[1].data;
	var myjqplot = $.jqplot(containerId, [yaxis_data, y2axis_data], {
		title: title || "",
		axes : {
			xaxis : { 
				renderer : $.jqplot.DateAxisRenderer, 
				tickOptions : { formatString : '%m月' },
				numberTicks : yaxis_data.length,
		        pad : 1,
		        showTicks : true,
		        tickInterval : '1 month'
			},
			yaxis : { tickOptions : { formatString : '￥%.2f' } },
			y2axis : { tickOptions : { showGridline:false, formatString : '￥%.2f' } }
		},
		highlighter : {
			show : true
			// tooltipFormatString: '<b><i><span style="color:red;">hello</span></i></b> %.2f',
			// useAxesFormatters: false
		},
		cursor : { show : true },
	    legend: { 
	    	show: true,//设置是否出现分类名称框（即所有分类的名称出现在图的某个位置） 
	    	location: 'ne' // 分类名称框出现位置, nw, n, ne, e, se, s, sw, w. 
	   	},
		series : [
			{
				label: options[0].label, //数据线图例
				lineWidth : 2,
				highlighter : {
					show : true,
					tooltipContentEditor : function(str, seriesIndex, pointIndex, plot) {
						var date = plot.data[seriesIndex][pointIndex][0];
						var val = plot.data[seriesIndex][pointIndex][1];
						var html = "<div class='pop'>月份 : " + date;
						html += "  <br>均价: " + val;
						html += "  </div>";
						return html;
					}
				}
			},
			{
				yaxis : 'y2axis',
				label: options[1].label, //数据线图例
				highlighter : {
					show : true,
					tooltipContentEditor : function(str, seriesIndex, pointIndex, plot) {
						var date = plot.data[seriesIndex][pointIndex][0];
						var val = plot.data[seriesIndex][pointIndex][1];
						var html = "<div class='pop'>月份 : " + date;
						html += "  <br>平均租金: " + val;
						html += "  </div>";
						return html;
					}
				}
			}
		]
	});
}
}

if(typeof(keepOff)=="undefined"){
/**
 * 取消房源或小区收藏
 * @param continerId	返回信息显示区域ID
 * @param collectionId	收藏
 */
function keepOff(continerId, collectionId){
	var actionUrl = "${globalUrl}myCollection.do?actionMethod=cancleMyCollection&collectionId="+collectionId;
	$.ajax({
		type : "post",
		url : actionUrl,
		dataType : "json",
		data : platCollection,
		success : function (data) {
			if (data.result == 'success') {
				if(cType = '3'){
					$("#"+continerId).css("background-color", '#cb4f4d');
					$("#"+continerId).attr("isCollect", "0");
				}
				alert("收藏已取消！");
			} else {
				alert("取消收藏失败，请再试一次！");
			}
		}
	});
}
}

if(typeof(keepOppen)=="undefined"){
//收藏二手房_租赁  租赁cType：1 二手房cType：0 id 为 小区 传过来的 
/**
 * 收藏房源或小区
 * @param cType		房源类型，1：租赁， 0:二手房，其他：小区
 * @param _houseId	房源ID
 * @param _brokerId	经纪人ID
 * @param price		房源价格
 * @param id
 */
function keepOppen(containerId, cType, _houseId, _brokerId, price){
	var actionUrl = globalUrl + "myCollection.do?actionMethod=addtoCollection";

	var houseId = _houseId;
	var brokerId = _brokerId;
	var collectType = cType;
	var priceCc = price;
	var platCollection = {
		ObjectId: houseId,
		collectType: collectType,
		priceCc: priceCc || "",
		brokerId: brokerId || ""
	};
	var zTypeName = (cType=="1" || cType=="2")? "房源": "小区";
	zTypeName = cType == "4"?"新房":zTypeName;
	$.ajax({
		type : "post",
		url : actionUrl,
		dataType : "json",
		data : platCollection,
		success : function (data) {
			if (data.result == 'success') {
				if(cType = '3'){
					if($("#"+containerId)){
						//$("#"+containerId).css("background-color", '#cb4f4d');
						$("#"+containerId).attr("isCollect", "0");
					}
				}
				alert(zTypeName+"收藏成功！");
			} else if (data.result == 'recall') {
				alert("您已经收藏过该"+zTypeName+"了！");
			} else {
				alert(zTypeName+"收藏失败，请再试一次！");
			}
		}
	});
}
}

if(typeof(isIphoneSafari)=="undefined"){
/**
 * 检测是否是iPhone
 * 
 * @returns {Boolean}
 */
function isIphoneSafari(){
	return (navigator.userAgent.indexOf("Safari") > -1) 
			&& (navigator.userAgent.indexOf("Chrome") < 0) 
			&& (navigator.userAgent.indexOf("iPhone") > -1);
}
}

// 搜索条件容器
if(typeof(searchMap)=="undefined"){
var searchMap = new Map();	
}

if(typeof(setJSONValue)=="undefined"){
/**
 * 保存搜索条件
 * 
 * @param key
 * @param newValue
 */
function setJSONValue(key, newValue){
	// searchMap.put(key, newValue);
	if(!searchMap) initSearchMap();
	try{
		searchMap.put(key, newValue);
	}catch(e){
		if(window.console) console.log(e);
		searchMap.set(key, newValue);
	}
}
}

if(typeof(initSearchMap)=="undefined"){
/**
 * 初始化搜索条件容器
 */
function initSearchMap(){
	searchMap = new Map();
	try{
		searchMap.put("type", 0);
		searchMap.put("order", "Asc");
		searchMap.put("sort", "sortIndex");
		searchMap.put("ispage", 1);
	}catch(e){
		if(window.console) console.log(e);
		searchMap.set("type", 0);
		searchMap.set("order", "Asc");
		searchMap.set("sort", "sortIndex");
		searchMap.set("ispage", 1);
	}
}
}

if(typeof(parseSearchCondition)=="undefined"){
/**
 * 从URL解析出查询参数并放入SearchMap
 */
function parseSearchCondition(_sUrl){
	try{
		if (_sUrl != ''){
			_sUrl = _sUrl.split("?")[1];
			var arrayTemp = _sUrl.split("&");
			for (var k = 0; k < arrayTemp.length; k++) {
				var key = arrayTemp[k].split("=")[0];
				var value = arrayTemp[k].split("=")[1];
				if (key != 'actionMethod'){
					searchMap.put(key, value);
				}
			}
		}
	}catch(e){
		if(window.console) console.log(e);
	}
}
}

/**
 * 初始化搜索条件容器
 */
$(document).ready(function(){
	initSearchMap();
});

/**
 * 通过Ajax从服务器取数(HTML格式)并显示在指定对象中
 * @param actionUrl		取数URL
 * @param renderArea	要显示的目标对象，如果未指定，则默认是"changelist"
 */
function showSelectedField(actionUrl, renderArea){
	var _renderArea = renderArea || "changelist";

	try{
		var postData = "";
		if(searchMap){
			var keyArray = searchMap.keys();
			if (keyArray.length > 0){
				for (var i = 0; i < keyArray.length; i++){
					postData += keyArray[i] + "=" + searchMap.get(keyArray[i]) + "&";
				}
			}
		}
		
		$("select[selectId]").each(function() {
			var columnName = $(this).attr("columnName");
			var fieldValue = $(this).val();
			if (fieldValue != -1) {
				if (posData === undefined) {
					posData = columnName + "=" + fieldValue;
				} else {
					posData += "&";
					posData += columnName + "=" + fieldValue;
				}
			}
		});
		
		//function postDataByURL2(actionUrl, dataString, resultArea, successMessage, callBack, beforeRender)
		postDataByURL2(actionUrl, postData, _renderArea);
	}catch(e){
		if(window.console) console.log(e);
	}
}

/**
 * 初始化页面右边滚动按钮
 */
$(document).ready(function(){
   $(window).scroll(function(){
	   try{
	     if ($(window).scrollTop()>100){
	      	$("#turnToTopButton").show();
	      }else{
	      	$("#turnToTopButton").hide();
	      }
	   }catch(e){
		   if(window.console) console.log(e);
	   }
   });
});

/**
//根据cookie判断默认显示大图模式还是列表模式
var _showType=1;	//默认列表模式
var _lastShowFlag = $.cookie("lastSelected");
if (_lastShowFlag == "imgShape") {
	_showType=0;	//大图模式
}
 */
var _showType = 1;
$(document).ready(function(){
	try{
		//根据cookie确定列表是显示大图模式还是列表模式
		_showType = ($.cookie && $.cookie("lastSelected") == "imgShape") ? 0 : 1;	//0是大图模式，1是列表模式
	}catch(e){
		if(window.console) console.log(e);
	}
});

//换页后，自动跳转到列表标题
//$(document).ready(function(){
//	try{
//		$.scrollTo('#listTop',500);
//	}catch(e){
//		if(window.console) console.log(e);
//	}
//});


if(typeof(setCookie)=="undefined"){
/**
 * 设置cookie
 * @param name		cookie名称
 * @param value		cookie值
 * @param expires	cookie过期时间（天），默认30天
 */
function setCookie(name, value, expires){
	var Days = expires || 30;
	var exp = new Date(); 
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
}

if(typeof(getCookie)=="undefined"){
/**
 * 读取cookie
 * @param name
 * @returns
 */
function getCookie(name){
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg)) 
		return unescape(arr[2]);
	else 
		return null;
}
}

if(typeof(hoverItem)=="undefined"){
/**
 * 
 * @param obj
 */
function hoverItem(obj){
	$obj = $(obj);
	$li = $obj.parent().parent();
	if($li.hasClass("hover")){
		$li.removeClass("hover");
	}else{
		$li.addClass("hover");
	}
}
}
if(typeof(hoverColumnItem)=="undefined"){
/**
 * 
 * @param obj
 */
function hoverColumnItem(obj){
	$obj = $(obj);
	$li = $obj;
	if($li.hasClass("hover")){
		$li.removeClass("hover");
	}else{
		$li.addClass("hover");
	}
}
}

if(typeof(fangda)=="undefined"){
/**
 * 显示二维码放大图
 */
function fangda(){
	var dataContent = document.getElementById("searchPicture");
	art.dialog({
		id:'searchPictures',
		title: "请扫描二维码",
		content: dataContent,
		lock: true,
		drag: false,
		resize: false,
		max: false,
		min: false,
		zIndex: 99999
	});
}
}


if(typeof(shareCondition)=="undefined"){
/**
 * 分享查询条件
 * @param _baseUrl
 * @returns {String}
 */
function shareCondition(_baseUrl){
	var currentURL = document.location.toString();
	
	if (currentURL.indexOf("welcome.show") > 0){
		currentURL = currentURL.replace("welcome.show", _baseUrl);
	}
	
	currentURL = currentURL.split("&")[0];
	
	var keyArray = searchMap.keys();
	if (keyArray.length > 0){
		var postUrl = "";
		for (var i = 0; i < keyArray.length; i++){
			if (keyArray[i] != 'ispage'){
				postUrl += keyArray[i] + "=" + searchMap.get(keyArray[i]);
			}else{
				postUrl += keyArray[i] + "=0";
			}
			if (i < keyArray.length - 1){
				postUrl += "&";
			}
		}
	}
	
	$("select[selectId]").each(function(){
		var columnName = $(this).attr("columnName");
		var fieldValue = $(this).val();
		if (fieldValue != -1){
			if (postUrl === undefined){
				postUrl = columnName + "=" + fieldValue;
			}else{
				postUrl += "&";
				postUrl += columnName + "=" + fieldValue;
			}
		}
	});
	
	var returnedUrl = "";
	var dataString = {"sharedUrl": currentURL + "&" + postUrl};
	$.ajax({
		type : "POST",
		url : globalUrl + "toShortUrl.show?actionMethod=shortUrl",
		data : dataString,
		dataType: "json",
		async: false,
		success : function(data) {
			if (data.operationStatus){
				returnedUrl = data.returnedUrl;
			}else{
				alert("分享失败，请联系管理员！");
			}
		},
		error : function() {
			alert("分享失败，请联系管理员！");
		}
	});
	return returnedUrl;
}
}

if(typeof(startShare)=="undefined"){
/**
 * 分享搜索条件
 * @param _baseUrl
 */
function startShare(_baseUrl){
	var currentURL = document.location.toString();
	var _bUrl = currentURL.match(/[\w]+?\.show/gi);
	var urlToShare = shareCondition(_baseUrl || _bUrl);
    $("#bdshare").attr("data", "{'url':'" + urlToShare + "'}");
}
}

if(typeof(saveShowType)=="undefined"){
/**
 * 保存显示模式状态
 * @param _showTypeFlag	显示模式ID
 * @param _queryUrl		查询Url
 * @param _queryParam	查询参数
 * @param _containerId	
 */
function saveShowType(_showTypeFlag) {
	if(searchMap){
		searchMap.put("ispage", "1");
	}
	switchShowType(_showTypeFlag);
/*
	if(_queryParam)
		postDataByURL2(_queryUrl, _queryParam, (_containerId || "changelist"));
	else
		showSelectedField(_queryUrl, (_containerId || "changelist"));
*/
	if(typeof(refreshPageAfterChangeShowType)=="function")
		refreshPageAfterChangeShowType();

	$.cookie('lastSelected', _showTypeFlag, { expires : 365 }, { path : "/" }); // 存储 cookie , expires: 设定时间天数参数， path：设置cookies存储路径参数
}
}

if(typeof(compareIsOpen)=="undefined"){
/**
 * 标记对比栏是打开的
 */
function compareIsOpen(){
	var status = getCookie("compareStatus");
	if(status == "open"){
		openCompareDiv();
	}else if(status == "hidden"){
		hiddenCompareDiv();
	}else{
		closeCompareDiv();
	}
}
}
