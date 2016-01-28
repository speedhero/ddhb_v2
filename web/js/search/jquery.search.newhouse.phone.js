/**
 * Author: Vincent Yang Date: 2014-06-01 Description: This jQuery plug-in is
 * used to create a way to retrieve information
 */
;
(function($) {
	$.fn.extend({
		"createSearchForPhone" : function(option) {
			$(this).empty();
			var searchMap = new Map();
			optionDefault = {
				searchItems : {},
				url : '',
				searchMap : searchMap,
				displayHiddenBar : true
			};

			currentOptions = $.extend(optionDefault, option);
			searItems = currentOptions.searchItems;
			searchMap = currentOptions.searchMap;
			
			var randomC = 0;
			
			//下拉列表框二级联动的时候使用该Map来查找对应的值
			var relativeValueMap = new Map();
			//定义一个键值对，键对应的修改时，改变value对应的对象
			var relativeMap = new Map();

			var privateItemMap = new Map();
			//当下拉列表框被点击时，此变量储存点击时该下拉列表框的值
			var currentValue;
			
			//缓存搜索容器
			var searchContainer = $(this);
			
			// define the search Menu Bar
			$(this).append("<div id='searchContent'></div>");
			// define the search item content
			$("#searchContent").append("<div id='searchItemContent'><div id='privateSearchItemContent'></div><div id='publicSearchItemContent'><div id='notHiddenDiv'></div><div id='isHiddenDiv'><div id='hp1'></div><div id='hm1'></div><div id='hp2'></div><div id='hm2'></div></div></div></div>");
			// define the selected search item panel
			$("#searchContent").append("<div class='clearDiv'></div>");
			
			
			var publicNotHiddenContainer = $("#notHiddenDiv");
			var publicHiddenContainer = $("#isHiddenDiv");
			var privateSearchItemContainer = $("#privateSearchItemContent");
			var hp1Container = $("#hp1");
			var hp2Container = $("#hp2");
			var hm1Container = $("#hm1");
			var hm2Container = $("#hm2");
			//对隐藏的进行计数
			var hiddenCount = 0;
			
			var privateClearDiv = $("<div class='clearDiv'></div>");
			
			for (var i = 0; i < searItems.length; i++) {
				var subItems = searItems[i].subItems;
				for (var j = 0; j < subItems.length; j++) {
					generateSelectElement(subItems[j], "parent", searItems[i].id);
				}
			}
			
			var clearDivStr = "<div class=\"clearDiv\"></div>";
			
			$(publicNotHiddenContainer).append(clearDivStr);
			$(publicHiddenContainer).append(clearDivStr);
			
			//生成下拉列表框的方法
			function generateSelectElement(subItem, flag, searchMenuId){
				var objId = "";
				var searchLabelStr = "";
				if (flag === "parent"){
					searchLabelStr = subItem.searchLabel;
					objId = subItem.parent + "_" + subItem.id;
				}else{
					searchLabelStr = subItem.subValueLabel;
					objId = subItem.parent + "_" + subItem.id + "_" + (randomC++);
					var keyId = subItem.parent + "_" + subItem.id;
					relativeMap.put(keyId, objId);
				}
				searchLabelStr = searchLabelStr.replaceAll("&nbsp;","");
				var labelLength = searchLabelStr.length;
				
				var addtionalStr = "";
				
				if (subItem.isPublic === 0){
					if (searchMenuId === defaultSelectedMenuId){
						addtionalStr = " class=\"customSelect displayClass\" searchMenuId=\"searchMenu" + searchMenuId + "\"";
					}else{
						addtionalStr = " class=\"customSelect hiddenClass\" searchMenuId=\"searchMenu" + searchMenuId + "\"";
					}
				}else{
					addtionalStr = " class=\"customSelect\"";
				}
				
				
				var selectElementObj = $("<div id=\"" + objId + "\" " + addtionalStr + "><span class=\"label\">" + searchLabelStr + "：</span></div>");
				var searchContainer = $("<div class=\"searchXL search\"></div>");
				if (labelLength === 4){
					searchContainer = $("<div class=\"searchXL search2\"></div>");	
				}
				var defaultSelect = $("<div class=\"cs\"><div class=\"csc\" selectedValue=\"\" columnName=\"" + (flag === "parent"? subItem.colunmName : "") + "\">请选择</div><div class=\"xljt\"><img src=\"images/search/selectXL.png\"/></div></div>");
				if (labelLength === 4){
					defaultSelect = $("<div class=\"cs\"><div class=\"csc cscShort\"  selectedValue=\"\" columnName=\"" + (flag === "parent"? subItem.colunmName : "") + "\">请选择</div><div class=\"xljt\"><img src=\"images/search/selectXL.png\"/></div></div>");
				}
				var selectDiv = $("<div class=\"xlnr\"></div>");
				$(defaultSelect).append(selectDiv);
				$(searchContainer).append(defaultSelect);
				$(selectElementObj).append(searchContainer);
				
				if (subItem.isPublic == 0){
					$(privateSearchItemContainer).append(selectElementObj);
				}else{
					if (subItem.isHidden === 0){
						$(publicNotHiddenContainer).append(selectElementObj);
					}else{
						hiddenCount = hiddenCount + 1;
						if (hiddenCount <= 3){
							$(hp1Container).append(selectElementObj);
							if (hiddenCount == 3){
								$(hp1Container).append("<div class=\"clearDiv\"></div>");
							}
						}else{
							$(hp2Container).append(selectElementObj);
						}
					}
				}
				$(hp2Container).append("<div class=\"clearDiv\"></div>");
				var optionStr = "<a optionValue=\"\" class=\"unlimit\">不限</a>";
				if (flag === "parent"){
					for (var i = 0; i < subItem.searchFileds.length; i++){
						var fieldValue = "";
						if (subItem.isRange == 1){
							fieldValue = subItem.searchFileds[i].minFieldValue + "@" + subItem.searchFileds[i].maxFieldValue;
						}else{
							fieldValue = subItem.searchFileds[i].fieldValue;
						}
						optionStr += "<a optionValue=" + fieldValue + " >" + subItem.searchFileds[i].fieldName + "</a>";
					}
				}
				$(selectDiv).append(optionStr);
				
				if (subItem.hasSubValue === 1){
					setRelationShip(subItem);
				}
				
				if (flag === "parent" && subItem.hasSubValue === 1){
					generateSelectElement(subItem, "final", searchMenuId);
				}
			}
			//将所有json Map中的数据放入一个数组中
			function getItemArray(searchField){
				var subFieldArray = new Array();
				for (k in searchField.subFiledsMap){
					subFieldArray = subFieldArray.concat(searchField.subFiledsMap[k]);
				}
				return subFieldArray;
			}
			
			//将下拉列表框的级联关系存到map中
			function setRelationShip(subItem){
				var searchFileds = subItem.searchFileds;
				var subRelationKey = "";
				for (var i = 0; i < searchFileds.length; i++){
					subRelationKey = subItem.parent + "_" + subItem.id + "_" + searchFileds[i].fieldValue;
					relativeValueMap.put(subRelationKey, getItemArray(searchFileds[i]));
				}
			}
			
			//点击下拉列表框弹出下拉列表项
			$(".csc").click(function(){
				var xlnrObj = $(this).parent().parent().find(".xlnr");
				if ($(xlnrObj).css("display") == "block"){
					$(xlnrObj).css("display", "none");
					return;
				}else{
					$(".xlnr").css("display", "none");
					$(xlnrObj).css("display", "block");
				}
				//储存当前的值
				currentValue = $(this).html();
			});
			//点击下拉列表框弹出下拉列表项
			$(".xljt").click(function(){
				var xlnrObj = $(this).parent().parent().find(".xlnr");
				if ($(xlnrObj).css("display") == "block"){
					$(xlnrObj).css("display", "none");
					return;
				}else{
					$(".xlnr").css("display", "none");
					$(xlnrObj).css("display", "block");
				}
				//储存当前的值
				currentValue = $(this).prev().html();
			});
			//鼠标悬停时设置颜色
			$(".searchXL").mouseover(function(){
                $(this).find(".csc").addClass("selectHoverVal");
                $(this).find(".xljt").addClass("cschover");
            }).mouseout(function(){
                $(this).find(".csc").removeClass("selectHoverVal");
                $(this).find(".xljt").removeClass("cschover");
            });
			//下拉列表框选中的触发
			$(".xlnr a").click(function(){
				optionClicked($(this));
            });
			
			//当第一级下拉列表框有新的选中时，改变第二级下拉列表框的值
			function optionClicked(obj){
				$(obj).parent().parent().find(".csc").html($(obj).html());
                $(obj).parent().css('display','none');
                //获取当前select的ID
                var currentSelect = $(obj).parent().parent().parent().parent();
                var currentId = $(currentSelect).attr("id");
                //找到该ID对应的二级级联列表框的ID
                relativeId = relativeMap.get(currentId);
                //获取该ID对应的对象
                var relativeObj = $("#" + relativeId);
                //获取选中的值
                var optionValue = $(obj).attr("optionValue");
                //组键对应的key
                var key = currentId + "_" + optionValue;
                //根据key来获取二级下拉列表框的内容
                var objArray = relativeValueMap.get(key);
                
                var optionStr = "<a optionValue=\"\" class=\"unlimit\">不限</a>";
                if (objArray != null && objArray.length > 0){
                	for (var i = 0; i < objArray.length; i++){
                		optionStr += "<a optionValue=" + objArray[i].fieldValue + " fieldColumnName=\"" + objArray[i].fieldColumnName + "\">" + objArray[i].fieldName + "</a>";
                	}
                }
                var exp = $(obj).attr("fieldColumnName");
                if(!(typeof(exp) == 'undefined')){
                	$(currentSelect).find(".csc").attr("columnname", $(obj).attr("fieldColumnName"));
                }
                var valueContainer = (currentSelect).find(".csc").first();
                $(valueContainer).attr("selectedValue", optionValue);
                $(relativeObj).find(".csc").html("请选择");
                $(relativeObj).find(".xlnr").html(optionStr);
                $(relativeObj).find(".xlnr a").click(function(){
                	optionClicked($(this));
                });
                var columnName = $(valueContainer).attr("columnname");
                var columnValue = $(valueContainer).attr("selectedvalue");
                
                if ($(obj).hasClass("unlimit")){
                	searchMap.remove(columnName);
                }else{
                	searchMap.put(columnName, columnValue);
                }
                $(obj).parent().parent().find(".csc").css("color", "#000000");
                
                
              //生成最终请求的url
                generatorRequestUrl();
			}
			
			//生成最终请求的url
			function generatorRequestUrl(){
				var urlStr = "";
				var keyArray = searchMap.keys();
				for (var i = 0; i < keyArray.length; i++){
					urlStr += keyArray[i] + "=" + searchMap.get(keyArray[i]) + "&";
				}
				if (urlStr.length > 0){
					urlStr = urlStr.substring(0, urlStr.length - 1);
				}
				postDataByURL2(optionDefault.url, urlStr, "changelist");
				anableButton(urlStr);
			}
			
			//排序
			function sortBubble(array) {
				var len = array.length, i, j, tmp;
				for (i = len - 1; i >= 1; i--) {
					for (j = 0; j <= i - 1; j++) {
						if (array[j] > array[j + 1]) {
							d = array[j + 1];
							array[j + 1] = array[j];
							array[j] = d;
						}
					}
				}
				return array;
			}
			
			//清除所有Map中的选项
			function clearSearchMap() {
				var keys = searchMap.keys();
				for (var jj = 0; jj < keys.length; jj++) {
					if (keys[jj] != "type" && keys[jj] != "order" && keys[jj] != "sort" && keys[jj] != "ispage") {
						searchMap.remove(keys[jj]);
					}
				}
			}
			
			//获取生成的链接
			function getAllSearchInfo() {
				var keyArray = searchMap.keys();
				var postUrl = "";
				if (keyArray.length > 0) {
					for (var i = 0; i < keyArray.length; i++) {
						postUrl += keyArray[i] + "=" + searchMap.get(keyArray[i]);
						if (i < keyArray.length - 1) {
							postUrl += "&";
						}
					}
				}
				return postUrl;
			}
			
			$(".cs").mouseover(function(){
				$(this).find(".csc").addClass("selectHover");
				$(this).find(".xljt").addClass("selectHover");
			});
			$(".cs").mouseout(function(){
				$(this).find(".csc").removeClass("selectHover");
				$(this).find(".xljt").removeClass("selectHover");
			});
		}
	});
})(jQuery);