jQuery.cookie=function(name,value,options){  
  if(typeof value!='undefined'){
    options=options||{};  
    if(value===null){  
      value='';  
      options.expires=-1;  
    }  
    var expires='';  
    if(options.expires&&(typeof options.expires=='number'||options.expires.toUTCString)){
      var date;
      if(typeof options.expires=='number'){  
         date=new Date();
         date.setTime(date.getTime()+(options.expires * 24 * 60 * 60 * 1000));
       }else{
         date=options.expires;
      }
      expires=';expires='+date.toUTCString();
     }
    var path=options.path?';path='+options.path:'';
    var domain=options.domain?';domain='+options.domain:'';
    var secure=options.secure?';secure':'';
    document.cookie=[name,'=',encodeURIComponent(value),expires,path,domain,secure].join('');
  }else{
    var cookieValue=null;
    if(document.cookie&&document.cookie!=''){
      var cookies=document.cookie.split(';');
      for(var i=0;i<cookies.length;i++){
        var cookie=jQuery.trim(cookies[i]);
        if(cookie.substring(0,name.length+1)==(name+'=')){
          cookieValue=decodeURIComponent(cookie.substring(name.length+1));
          break;
        }
      }
    }
    return cookieValue;  
  }
};

function subscribe() {
	trimForm('subScribeInfo');
	document.subScribeInfo.action = "welcome.show?actionMethod=subscribe";
	document.subScribeInfo.submit();
}
/**
 * 
var timeout = 500;
var closetimer = 0;
var ddmenuitem = 0;
function jsddm_open() {
	jsddm_canceltimer();
	jsddm_close();
	ddmenuitem = $(this).find('ul').eq(0).css('visibility', 'visible');
}

function jsddm_close() {
	if (ddmenuitem)
		ddmenuitem.css('visibility', 'hidden');
}

function jsddm_timer() {
	closetimer = window.setTimeout(jsddm_close, timeout);
}

function jsddm_canceltimer() {
	if (closetimer) {
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}
$(document).ready(function() {
	$('#jsddm > li').bind('mouseover', jsddm_open);
	$('#jsddm > li').bind('mouseout', jsddm_timer);
});
$(document).ready(function() {
	$('.housephotorightdown a').click(function(e) {
		e.stopPropagation();
	});
});
*/

function isCollectHouseSend(obj, houseType) {
	var collObj = $(obj);
	if (collObj.attr("isCollect") == 0) {
		// 取消收藏
		keepOff(collObj.attr("id"), collObj.attr("collId"));
	} else {
		// 添加收藏
		keepOppen(houseType, 
				collObj.attr("id"), 
				collObj.attr("hosueobjectId"),
				collObj.attr("brokerId"),
				collObj.attr("houseprice")
				);
	}
}
function isCollectRent(obj, houseType) {
	var collObj = $(obj);
	if (collObj.attr("isCollect") == 0) {
		// 取消收藏
		keepOff(collObj.attr("collId"), collObj.attr("id"));
	} else {
		// 添加收藏
		keepOppen(collObj.attr("id"),
				houseType, 
				collObj.attr("hosueobjectId"), 
				collObj.attr("brokerId"),
				collObj.attr("houseprice") 
				);
	}

}
function moveClick(obj) {
	var $move = $(obj).parent();
	if ($move.find('.color_wd').is(":visible") == false) {
		// 显示
		$move.find('.color_wd').slideToggle("slow");
		animateOn($move);
		$move.find(".jiant_s").find("a").css("background-image",
				"url('image/jiant_x.png')");
		$move.find(".an_xq").css("display", "block");
	} else {
		// 取消
		$move.stop(true, true);
		$move.find('.color_wd').stop(true, true);
		animateOff($move);
		$move.find(".jiant_s").find("a").css("background-image",
				"url('image/jiant_s.png')");
		$move.find(".an_xq").css("display", "none");
		$move.find('.color_wd').slideToggle("slow");
	}
}

function animateOn(param) {
	param.animate({
		/* top:"80px" */
		top : "29px"
	});
}
function animateOff(param) {
	param.animate({
		/* top:"80px" */
		top : "174px"
	});
}

var _searchHintText = "请输入城区、商圈、小区 等开始找房...";
$(document).ready(function(){
	$("#inputSearch").val(_searchHintText);
	
  //切换菜单
  $(".menuItem").click(function(){
    var _pCurrIdx = $(this).attr("idx");
    var previousContainer = $(".menuSeledted");
    var _pPrevIdx = $(previousContainer).attr("idx");
    $(previousContainer).removeClass("menuId_"+_pPrevIdx+"selected");
    $(previousContainer).removeClass("menuSeledted");
    
	$(this).addClass("menuId_"+_pCurrIdx+"selected");
	$(this).addClass("menuSeledted");

    $(".link_Key").each(function(){ 
    	$(this).hide();
    	var currIdx = $(this).attr("idx")
    	if(_pCurrIdx==currIdx) $(this).show();
    });
    //$("#searchModule").val(_houseType);
    $("#searchModule").val($(this).attr("hType"));
  });
  
  $("#searchContainerDiv .search_key").click(function(){
	  var txt = $(this).val();
	  if(txt==_searchHintText){
		  $(this).val("");
	  }
  });
  
  $("#searchContainerDiv .search_key").blur(function(){
	  if($(this).val()==""){
		  $(this).val(_searchHintText);
	  }
  });
  
  function doSearch(){
	  var searchModule = $("#searchModule").val();
	  var inputSearch = $("#inputSearch").val();
	  if(inputSearch=="" || inputSearch==_searchHintText) return;
//	  window.open(globalUrl+"welcome.show?actionMethod=dimQuery&type=1&searchModule="+searchModule+"&inputSearch="+inputSearch);
	  window.open(globalUrl + searchModule + "/cx" + inputSearch);
  }

  $("#priceChartTab li").click(function(){
	  $("#priceChartTab").find("li").each(function(){
	        $(this).removeClass("selected");
	        $(this).addClass("normal");
	   });
	  $(this).removeClass("normal");
	  $(this).addClass("selected");
	  
	  var _type = $(this).attr("typ");
	  $(".price_con").each(function(){
		  $(this).hide();
		  if($(this).attr("typ")==_type)
			  $(this).show();
	  });
  });
    
  try{
	  $("#search_botton").click(function(){doSearch();});
	  $("#inputSearch").keyup(function(event){if(event.which==13) {doSearch();}});
  }catch(e){
	  if(window.console) console.log(e);
  }

  try{
	  $("._secondhouse a").each(function(){
		  $(this).attr("target", "_esf");
	  });
	  $("._renthouse a").each(function(){
		  $(this).attr("target", "_czf");
	  });
	  $("#Menu a").each(function(){
		  $(this).attr("target", "_zxzx");
	  });
	  $("#house_New a").each(function(){
		  $(this).attr("target", "_xlp");
	  });
	  $("#links a").each(function(){
		  $(this).attr("target", "_rmxq");
	  });
  }catch(e){
	  if(window.console) console.log(e);
  }
});


function addJavaScript(scriptPath){
  var head= document.getElementsByTagName('head')[0];  
  var script= document.createElement('script');
  script.type= 'text/javascript';

  script.onreadystatechange= function () { if (this.readyState == 'complete') callback(); }
  script.onload= function(){ callback(); }
  script.src= scriptPath;
  head.appendChild(script);
}

var gray = "<link type='text/css' rel='stylesheet' href='"+globalUrl+"js/skins/gray.css'>";
var dlzc = "<link rel='stylesheet' type='text/css' href='"+globalUrl+"css/css.css'>";
var css = "<link rel='stylesheet' type='text/css' href='"+globalUrl+"css/dlzc.css'>";

/**
 * 房贷计算器
 */
function openCalcDialog(unitPrice, area){
  //var url = "${globalUrl}houseSecond.show?actionMethod=getCalculator&unitPrice=${house.unitPrice}&area=${house.area}";
  var url = globalUrl+"houseSecond.show?actionMethod=getCalculator";
  if(unitPrice)url += "&unitPrice="+unitPrice;
  if(area) url += "&area="+area;
  _openDialog('calculator', "&nbsp;&nbsp;个人住房商业贷款", url, "get", "html", gray, dlzc, css);
}

function loginBox(target,housetype){
  var flag = housetype;
  var url = globalUrl+"login.show?actionMethod=loginCheck&target="+target;
  if(flag != "") url = url+"&housetype="+flag;
  _openDialog('login', "&nbsp;&nbsp;登录", url, "get", "html", gray, dlzc, css);
};
function registerBox(){
  var url = globalUrl+"register_member/preRegist";
  _openDialog('register', "&nbsp;&nbsp;注册<a onclick=\"infoShow('registerReason');\" style=\"cursor:pointer; color:red;\">？</a>", url, "get", "html", gray, dlzc, css );
};


function addFavour(){
  var ctrl = (navigator.userAgent.toLowerCase()).indexOf('mac') != -1 ? 'Command/Cmd' : 'Ctrl';
  if (document.all) {
    window.external.addFavorite(window.location, document.title);
  }else {
    alert('由于浏览器原因，请用'+ ctrl +'+D进行添加');
  }
}
try{
	//根据cookie确定是显示大图模式还是列表模式
	var type2 = 1; //默认是列表模式
	var idStr = $.cookie("lastSelected");
	if (idStr == "imgShape") {
	  type2=0;	//大图模式
	}
}catch(e){
	if(window.console) console.log(e);
}

