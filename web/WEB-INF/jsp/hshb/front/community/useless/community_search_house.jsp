<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="huatek" uri="http://www.dongdao.net/j2ee"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
var globalUrl = '${globalUrl}';
</script>
<style type="text/css">
.font1 {font-size:14px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;}
.font7blue {font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#0000FF;}
ul {list-style: none;}
ul li {}
.floatleft{
	float:left;
}
.brokerarea {
	height: 390px;
	width: 298px;
	margin-top: 10px;
	float: left;
}

.brokerright {
	width: 640px;
	margin-top: 10px;
	float: left;
}
.switcharea {
	border: #797979 solid 1px;
	height: 41px;
	width: 119px;
	text-align:center;
	float: left;
	font-size: 13px;
	cursor: pointer;
}

.switcharea span {
	line-height: 40px;
}


.rightup {
	height: 300px;
	width: 100%;
	margin-top: 10px;
	border: #CCCCCC solid 1px;
}
.rightmiddle {
	width: 95.1875%;
	padding-top: 15px;
	margin: 0 auto;
}
.rightmiddle>span{
	float: right;
	margin-right: 15px;
	font-size: 14px;
}
.rightdown {
	width: 95%;
	margin: auto;
	margin-top: 10px;
}
.innerleft{
	width: 129px;
	height: 275px;
	float: left;
	text-align: center;
	border: #797979 solid 1px;
}
.innerright{
	width: 155px;
	height: 275px;
	float: left;
	margin-left: 10px;
}
.innerdown {
	width: 100%;
	height: 80px;
	margin-top: 18px;
}
#rightContent {
}
/**搜索条件**/
.selectNumberScreen dl dd.roundDiv{
	border-radius: 3px;
	width: auto;
	height:auto;
	margin-left:60px;
	padding-left:10px;
	border:1px dashed #CCC;
}
#selectedplan{
	margin:10px;
}
.show_no{display:none}
.show_yes{display:inline-block}
h1,h2,h3,h4,h5,h6,p,ul,ol,li,form,img,dl,dt,dd,table,th,td,blockquote,fieldset,div,strong,label,em{margin:0;padding:0;border:0;}
ul,ol,li{list-style:none;}
input,button{margin:0;font-size:12px;vertical-align:middle;}
body{font-size:12px;font-family:Arial, Helvetica, sans-serif; color:#333; margin:0 auto; }
table{border-collapse:collapse;border-spacing:0;}
dd a{font-size:12px; color:#333; text-decoration:none;cursor:pointer;}
dd a:hover{ text-decoration:none;}

.selectNumberScreen{width:auto; height:auto; margin:20px auto; font-size:12px;}
.screenBox dl {width:auto; overflow: hidden}
.screenBox dl.noborder {border-bottom: 0px; border-left: 0px; border-top: 0px; border-right: 0px}
.screenBox dl.goodstype {border-bottom: #e6e6e6 1px dashed}
.screenBox dl dt {float: left; height: 30px}
.screenBox dl dt {text-align: right; width: 85px; height: 22px; font-weight: 600; padding-top: 8px}
.screenBox dl dd {position: relative; padding-bottom: 5px; padding-left: 0px; width: 480px; padding-right: 48px; float: left; height: auto; overflow: hidden; padding-top: 0px}
.screenBox dl dd a {line-height: 14px; margin: 7px 15px 2px 2px; display: inline-block; color: #36c; overflow: hidden; text-decoration: none;padding:3px; border-radius: 3px;}
.screenBox dl dd a:hover {background: #00AAFF; color: #fff ;padding:3px; border-radius: 3px;}
.screenBox dl dd a.selected {background: #39c; color: #fff;padding:3px; border-radius: 3px;}
.screenBox dl dd a.selected:hover {cursor:default}
.screenBox dl dd span.more {position: absolute; width: 39px; display: block; height: 14px; top: 6px; cursor: pointer; right: 1px}
.screenBox dl dd span.more label {display: inline-block; cursor: pointer}
.hasBeenSelected {width:auto; margin-top: 10px; padding-top: 10px;overflow: hidden; background:#E9E9E9}
.hasBeenSelected dl {width: auto; overflow: hidden}
.hasBeenSelected dl dt {float: left}
.hasBeenSelected dl dd {float: left}
.hasBeenSelected dl dt {text-align: right; line-height: 30px; width: 108px; height: 30px; font-weight: 600}
.hasBeenSelected dl dd {padding-bottom: 4px; padding-left: 0px; width: 676px; padding-right: 0px; padding-top: 0px}
.selectedInfor {border-bottom: #4096EE 1px solid; position: relative; border-left: #4096EE 1px solid; padding-bottom: 1px; margin: 4px 10px 0px 0px; padding-left: 5px; padding-right: 17px; display: block; white-space: nowrap; background: #fff; float: left; height: 17px; border-top: #4096EE 1px solid; border-right: #4096EE 1px solid; padding-top: 1px}
.selectedInfor label {color: #4096EE}
.eliminateCriteria {line-height: 21px; margin-top: 4px; width: 80px; float: left; color: #f60; cursor: pointer; font-weight: 600}
.selectedInfor em {background: url("${globalUrl}img/x.gif") no-repeat;cursor: pointer;display: block;height: 13px;overflow: hidden;position: absolute;right: 2px;top: 3px;width: 13px;}
.screenBox dl dd a.show_no{display:none}
.screenBox dl dd a.show_yes{display:inline-block}
.screenBox dl dd b{margin:0;padding:0;}
.screenBox dl dd b.show_no{display:none}
.screenBox dl dd b.show_yes{display:inline-block}
.screenBox.not-show{display:none}
dl.not-show{
	display:none;
}
dl.show{
	display:block;
}
.condition2{
	width:30px;
	height:12px;
	top: -10px;
	position: relative;
}
.condition2:focus{
	border:2px solid #999;
	outline:#EEE Solid 1px;
}
.unitName{
	margin:0;
	padding:0;
	display:inline-block;
	height: 12px;
	top: -8px;
	position: relative;
}
/**搜索条件**/
</style>
<script type="text/javascript">
function setJSONValue2(key, newValue){
	jsoninit[key] = newValue;
}
var order = "Asc";
var sort = "sortIndex";
var housetype = '${housetype}';
var comId = "${comId}";
var jsoninit = {
	"communityId" : comId
};
var jsonStringInit = {
	'order':order,
	'sort' : sort,
	'communityId':comId,
	'housetype':housetype
};

/**
 * 替换JSON字符串的值
 */
function setJSONValue(key, newValue){
	jsonStringInit[key] = newValue;
}

function removeFromJSON(key){
	delete jsonStringInit[key];
}

function defaultJSON(){
	jsonStringInit = {
		'order':order,
		'sort' : sort,
		'communityId':communityId,
		'housetype':housetype
	};
}
function initSearchBox0(){
	var jsonString = $.parseJSON('${jsonString}');
	//var jsonString = $.parseJSON(xxx);
	for (var i = 0; i < jsonString.length; i++) {
		var subItems = jsonString[i].subItems;
		for (var j = 0; j < subItems.length; j++) {
			var searchLabel = subItems[j].searchLabel;
			var colunmName1 = subItems[j].colunmName;
			var isHidden = subItems[j].isHidden;
			if(j<3){
				$("#selectList0").append("<dl class=' listIndex " + colunmName1 + " "+"'><dt>"+ searchLabel +"：</dt><dd cn='"+ colunmName1 +"' id='b"+ j +"'><a cn='"+colunmName1+"' value='' minfieldvalue='' maxfieldvalue=''>不限</a></dd>");
			} else{
				$("#selectList0").append("<dl class=' listIndex "+colunmName1+" "+"'><dt>"+ searchLabel +"：</dt><dd cn='"+ colunmName1 +"' class='w100' id='b"+ j +"'></dd>");
			}
				var searchFileds = subItems[j].searchFileds;
				if(j>=3){
					$("#b" + j).append("<select id='bbb"+ j +"'><option value='0'>"+"不限"+"</option></select>");
				}
				for (var k = 0; k < searchFileds.length; k++) {
					var sf = searchFileds[k];
					
					//属性值 参数值
					var key = "";
					var value = "";
					if(colunmName1 in jsonStringInit){
						key = colunmName1;
						value = jsonStringInit[colunmName1];
					}
					//alert(j);
					
					if(j<3){
						$("#b" + j).append("<a cn='"+colunmName1+"'class='xx"+k+"' value='"+ sf.fieldValue +"' minFieldValue='"+ sf.minFieldValue +"' maxFieldValue='"+ sf.maxFieldValue +"'>"+ sf.fieldName +"</a>");
					} else{
						$("#bbb" + j).append("<option value='"+sf.minFieldValue+"'>"+sf.fieldName+"</option>");
					}
				}
				//isNeedInput
				var isNeedInput = subItems[j].isNeedInput;
				var unitName = subItems[j].unitName;
		}//for j...
	}
	$("#selectList0").append("</dl>");
	var last = '';
	$('.dda').each(function () {
	    if ($(this).attr('attr') != last) {
	    	var clazz = $(this).attr('class');
	        $(this).before("<b class='"+clazz+"'>" + $(this).attr('attr') + '</b>');
	        last = $(this).attr('attr');
	    }
	});
	//********************************************************************//
	var dlNum  =$("#selectList0").find("dl");
    for (i = 0; i < dlNum.length; i++) {
        $(".hasBeenSelected .clearList").append("<div class=\"selectedInfor selectedShow0\" style=\"display:none\"><span></span><label></label><em></em></div>");
    }
    var refresh = "true";
    $("#selectList0 a ").live("click", function(){
    	var clazz = $(this).attr('class');
    	var pp = $(this).parent();
        var text = $(this).text();
        var selectedShow = $(".selectedShow0");
        var textTypeIndex =$(this).parents("dl").index();
        var textType =$(this).parent("dd").siblings("dt").text();
        index = textTypeIndex;
        $(".clearDd").show();
        $(".selectedShow0").eq(index).show();
        $(this).addClass("selected").siblings().removeClass("selected");
        selectedShow.eq(index).find("span").text(textType);
        selectedShow.eq(index).find("label").text(text);
        var show = $(".selectedShow0").length - $(".selectedShow0:hidden").length;
        if (show > 1) {
        	//清除本次找房条件
            //$(".eliminateCriteria").show();
        }
		if($(this).hasClass('selected')){
			var key = $(this).attr('cn');
			var value = $(this).attr('value');
			if(value == undefined || value == "undefined"){
				var mfv = $(this).attr('minFieldValue');
				if(mfv == undefined || mfv == "undefined"){
					var fieldValue = $(this).attr('fieldValue');
					key = "ddhb_one_community.street.id";
					value = fieldValue;
				} else {
					var value = $(this).attr('minFieldValue') + "@" + $(this).attr('maxFieldValue');
				}
			}
			setJSONValue(key, value);
			console.log(jsonStringInit);
		}
		postDataByURL2("${globalUrl}community.show?actionMethod=dimdetailquery", jsonStringInit, "changelist");
    });
    $(".selectedShow0 em").live("click",function(){
        $(this).parents(".selectedShow0").hide();
        var textTypeIndex =$(this).parents(".selectedShow0").index();
        index = textTypeIndex;
        var temp = $(".listIndex").eq(index).find("a.selected");
        $(".listIndex").eq(index).find("a").removeClass("selected");
        //点击X号: 取消选择,同时给JSON重新赋值,调用AJAX
        var key = temp.attr('cn');
		removeFromJSON(key);

		//ddhb_one_community.region.id 区域  ddhb_one_community.cbd.id 区域二级
		//ddhb_one_subwayline.id  地铁 ddhb_one_subwaystation.id 地铁二级
		//ddhb_one_sztype.id 学校 ddhb_one_school.id 学校二级
		//如果是二级菜单,则需要被把它的父节点也去掉:
		if(key=="ddhb_one_community.region.id"){
			removeFromJSON("ddhb_one_community.cbd.id");
		}
		if(key=="ddhb_one_subwayline.id"){
			removeFromJSON("ddhb_one_subwaystation.id");
		}
		if(key=="ddhb_one_sztype.id"){
			removeFromJSON("ddhb_one_school.id");
		}
		if(key=="ddhb_one_inital"){
			removeFromJSON("ddhb_one_communityName");
		}
		
		postDataByURL2("${globalUrl}community.show?actionMethod=dimdetailquery", jsonStringInit, "changelist");
        if($(".listIndex .selected").length < 2){
            $(".eliminateCriteria").hide();
        }
    });
    $(".eliminateCriteria").live("click",function(){
        $(".selectedShow0").hide();
        $(this).hide();
        $(".listIndex a ").removeClass("selected");
    });
}
	
	function searchByInput(id){
		var inputImg = $(id+'Img');
		inputImg.click(function() {
			var inputMin = $(id+'Min').val();
			var inputMax = $(id+'Max').val();
			var unitName = inputImg.prev("span");
			if(inputMin == "" || inputMin == undefined){
				alert("请输入数值后再进行搜索!");
			} else if(inputMax == "") {
				alert("请输入数值后再进行搜索!");
			} else if(inputMin >= inputMax) {
				alert("最小值不能>=最大值!");
			} else if(!$.isNumeric(inputMin) || !$.isNumeric(inputMax)){
				alert("输入的不是数字!");
			} else {
				//如果有被选中的,清除
				$(id).find("a").each(function(){
		   			$(this).removeClass("selected");
		   	        var selectedShow = $(".selectedShow0");
		   	        var textTypeIndex =$(this).parents("dl").index();
		   	        var textType =$(this).parent("dd").siblings("dt").text();
		   	        index = textTypeIndex;
		   	        $(".clearDd").show();
		   	        $(".selectedShow0").eq(index).show();
		   	        selectedShow.eq(index).find("span").text(textType);
		   	        selectedShow.eq(index).find("label").text(inputMin + unitName.text() + " - "+inputMax + unitName.text());
		   		});
				//搜索开始
				setJSONValue("ispage", 1);
				setJSONValue($(id).attr('cn'), inputMin+"@"+inputMax);
				postDataByURL2("${globalUrl}community.show?actionMethod=dimdetailquery", jsonStringInit, "changelist");
			}
		});
	}

$(document).ready(function() {
	//加载搜索框-区域
	initSearchBox0();
	//首付-价格-预算 3个输入框搜索
	searchByInput('#b3');
	searchByInput('#b6');
	searchByInput('#b7');
	
	$('#selectpaixu').change(function(){ 
		var tempstr = $(this).children('option:selected').val();
	  	if(tempstr == "价格由高至低") {
	  		if(housetype == "1") {
	  			setJSONValue("sort", "price");
		  		setJSONValue("order", "Desc");
		  		sort = "price";
		  		order = "Desc";
	  		} else {
	  			setJSONValue("sort", "rentPrice");
		  		setJSONValue("order", "Desc");
		  		sort = "rentPrice";
		  		order = "Desc";
	  		}
	  		postDataByURL2("${globalUrl}community.show?actionMethod=dimdetailquery", jsonStringInit, "changelist");
	  	} else if(tempstr == "价格由低至高"){	
	  		if(housetype == "1") {
	  			setJSONValue("sort", "price");
		  		setJSONValue("order", "Asc");
		  		sort = "price";
		  		order = "Asc";
	  		} else {
	  			setJSONValue("sort", "rentPrice");
		  		setJSONValue("order", "Asc");
		  		sort = "rentPrice";
		  		order = "Asc";
	  		}
	  		
	  		postDataByURL2("${globalUrl}community.show?actionMethod=dimdetailquery", jsonStringInit, "changelist");
	  	} else if(tempstr == "面积由大至小"){
	  		setJSONValue("sort", "area");
	  		setJSONValue("order", "Desc");
	  		sort = "area";
	  		order = "Desc";
	  		postDataByURL2("${globalUrl}community.show?actionMethod=dimdetailquery", jsonStringInit, "changelist");
	  	} else if(tempstr == "面积由小至大"){
	  		setJSONValue("sort", "area");
	  		setJSONValue("order", "Asc");
	  		sort = "area";
	  		order = "Asc";
	  		postDataByURL2("${globalUrl}community.show?actionMethod=dimdetailquery", jsonStringInit, "changelist");
	  	} else {
	 		setJSONValue("sort", "sortIndex");
	  		setJSONValue("order", "Asc");
	  		sort = "sortIndex";
	  		order = "Asc";
		}
	  });
});

</script>
<div class="rightup">
	<!-- START -->
	<!-- 待售/待租 -->
	<div class="selectNumberScreen">
		<div id="selectList0" class="screenBox screenBackground show">
			<!-- dynamic genarated ...  -->			
		</div>
	</div>
	<!-- END -->
</div>
<div style="clear: both;"></div>
<div class="rightmiddle">
<div style="float: left;  height: 50px;">
	<span style="font-size: 18px; font-weight: normal; font-style: normal; text- decoration: none; color: #333;">
		共有 <span id="dynamichousecount" >${pageBean.totalRows}</span> 套符合要求的房子</span>
</div>
<div style="float: right; height: 50px;">
	<span style="font-size: 13px; font-weight: normal; font-style: normal; text- decoration: none; color: #333;">排序：</span>
	<select id="selectpaixu" name="selectpx" style="width: 100px; cursor: pointer;">
		<option value="默认排序" selected="selected">默认排序</option>
		<option value="价格由高至低">价格由高至低</option>
		<option value="价格由低至高">价格由低至高</option>
		<option value="面积由大至小">面积由大至小</option>
		<option value="面积由小至大">面积由小至大</option>
	</select>
</div>
</div>
<div style="clear: both;"></div>
<div class="rightdown">
<div id="changelist">
	<c:choose>  
	  <c:when test="${housetype eq 2}"><%@include file="/WEB-INF/jsp/ddhb/front/rent_list.jsp" %></c:when>  
	  <c:otherwise><%@include file="/WEB-INF/jsp/ddhb/front/house_list.jsp" %></c:otherwise>  
	</c:choose>
	<div style="clear:both;"></div>
	<div class="cutpage">
		<huatek:ddhbajaxCutPage pageBean="${pageBean}" isOuterForm="false"
		formName="houses" offerPageSize="20,50,100" isExistForm="true" queryFunction="changePages()"/>
	</div>
</div>
</div>
<script type="text/javascript">
function changePages() {
	var currvalue = document.getElementsByName("currentPage")[0].value;
	setJSONValue("currentPage", currvalue);
	postDataByURL2('${globalUrl}community.show?actionMethod=dimdetailquery',jsonStringInit,"changelist");
}
</script>