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
			$(this).append("<div id='searchMenuBarPhone'></div>");
			$(this).append("<div class='clearDiv'></div>");
			$(this).append("<div id='searchContent'></div>");
			// define the search item content
			$("#searchContent").append("<div id='searchItemContent'><div id='privateSearchItemContent'></div><div id='publicSearchItemContent'><div id='notHiddenDiv'></div><div id='isHiddenDiv'><div id='hp1'></div><div id='hm1'></div><div id='hp2'></div><div id='hm2'></div></div></div></div>");
			// define the selected search item panel
			$("#searchContent").append("<div class='clearDiv'></div>");
			$("#searchContent").append("<div id='selectedItemPanel'><div id='operationDivForPhone'></div><div class='clearDiv'></div></div>");
			$("#operationDivForPhone").append("<div class='operationItemPhone'><div class='operationItemContainerPhone disableOperation' diableFlag='false' id='clearContainerPhone'><div class='oprationIcon disabledClearIcon'></div><div class='operationLabel'>清除</div><div class='clearDiv'></div></div></div>");
			$("#operationDivForPhone").append("<div class='operationItemPhone'><div class='operationItemContainerPhone disableOperation' diableFlag='false' id='saveContainerPhone'><div class='oprationIcon disabledSaveIcon'></div><div class='operationLabel'>保存</div><div class='clearDiv'></div></div></div>");
			$("#operationDivForPhone").append("<div class='operationItemPhone'><div class='operationItemContainerPhone disableOperation' diableFlag='false' id='shareContainerPhone'><div class='oprationIcon disabledShareIcon'></div><div class='operationLabel'>分享</div><div class='clearDiv'></div></div></div>");
			$("#operationDivForPhone").append("<div class='clearDiv'></div>");
			
			var str = "<div id='sharePanel' style='height:30px; float:right; display:none;'>"  + 
				"<div id='bdshare' class='bdshare_t bds_tools get-codes-bdshare' data='{\"text\":''}'>" + "<a class='bds_qzone' onmousedown='startToShare()'></a> <a class='bds_tsina' onmousedown='startToShare()'></a>" + "<a class='bds_tqq' onmousedown='startToShare()'></a><a class='bds_sqq' onmousedown='startToShare()'></a></div></div>";
			$("#operationDivForPhone").append(str);
			
			// define the hidden button div
			if (currentOptions.displayHiddenBar == true) {
				$("#searchContent").append("<div id='hiddenButtonBar'><a id='expandOrHiddenBar' isHidden='true'>更多搜索</div><div class='clearDiv'></div></div>");
			}
			
			$("#searchContent").append("<div class='clearDiv'></div>");
			
			var publicNotHiddenContainer = $("#notHiddenDiv");
			var publicHiddenContainer = $("#isHiddenDiv");
			var privateSearchItemContainer = $("#privateSearchItemContent");
			var hp1Container = $("#hp1");
			var hp2Container = $("#hp2");
			var hm1Container = $("#hm1");
			var hm2Container = $("#hm2");
			var defaultSelectedMenuId;
			//对隐藏的进行计数
			var hiddenCount = 0;
			//对多选的进行计数
			var multiCount = 0;
			
			var privateClearDiv = $("<div class='clearDiv'></div>");
			
			//生成搜索菜单
			for (var i = 0; i < searItems.length; i++) {
				if (i == 0) {
					defaultSelectedMenuId = searItems[i].id;
					$("#searchMenuBarPhone").append("<div class='itemMenuPhone phoneItemMenuSelected' onlyShowPrivate='" + (searItems[i].onlyShowPrivate == 1 ? true : false) + "' id='searchMenu" + searItems[i].id + "' menuId='" + searItems[i].id + "'>" + searItems[i].searchLabel + "</div>");
				} else {
					$("#searchMenuBarPhone").append("<div class='itemMenuPhone' onlyShowPrivate='" + (searItems[i].onlyShowPrivate == 1 ? true : false) + "' id='searchMenu" + searItems[i].id + "' menuId='" + searItems[i].id + "'>" + searItems[i].searchLabel + "</div>");
				}
			}
			
			for (var i = 0; i < searItems.length; i++) {
				var subItems = searItems[i].subItems;
				for (var j = 0; j < subItems.length; j++) {
					if (subItems[j].ismulty === 0){
						generateSelectElement(subItems[j], "parent", searItems[i].id);
					}else{
						multiCount = multiCount + 1;
						generateMultiItems(subItems[j]);
					}
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
			
			//选中分享时的非多选选中项
			var keyArray = searchMap.keys();
			var multiMap = new Map();
			for (var i = 0; i < keyArray.length; i++) {
				if (keyArray[i].indexOf("ddhb_") >= 0) {
					var searchValue = searchMap.get(keyArray[i]);
					var finalSearchValue = searchValue;
					if (searchValue.indexOf("#") > 0){
						finalSearchValue = searchValue.substring(0, searchValue.indexOf("#"));
					}
					
					var selectedItem = $(".customSelect").find("[columnname='" + keyArray[i] + "']");
					if ($(selectedItem).length == 0){
						multiMap.put(keyArray[i], finalSearchValue);
					}else{
						$(selectedItem).each(function(){
							var aObj = $(this).parent().find("a[optionvalue='" + finalSearchValue + "']");
							if ($(aObj).length > 0){
								$(this).attr("selectedvalue", finalSearchValue);
								$(this).html($(aObj).html());
							}
						});
					}
					if ($("#isHiddenDiv").find("div[columnname='" + keyArray[i] + "']").length > 0){
						$("#isHiddenDiv").css("display","block");
						$("#expandOrHiddenBar").attr("isHidden", "false");
						$("#expandOrHiddenBar").html("收起搜索");
					}
				}
			}
			//选中分享时的多选选中项
			if (multiMap.size() > 0){
				var multyKeyArray = multiMap.keys();
				for (var i = 0; i < multyKeyArray.length; i++) {
					var multyValue = multiMap.get(multyKeyArray[i]);
					if (multyValue.indexOf(",") > 0){
						var valueArray = String(multyValue).split(",");
						if (valueArray.length > 0){
							for (var j = 0; j < valueArray[j]; j++){
								var multiItem = $(".multiItem[colunmname='" + multyKeyArray[i] + "'][fieldvalue='" + valueArray[j] + "']");
								if ($(multiItem).length > 0){
									var bgColor = $(multiItem).attr("bgColor");
									if (bgColor == 'undefined'){
										bgColor = "#ababab";
									}
									$(multiItem).css("background-color", bgColor);
									$(multiItem).css("color", "#FFFFFF");
									$(multiItem).attr("isselected", "true");
								}
							}
						}
					}else{
						var multiItem = $(".multiItem[colunmname='" + multyKeyArray[i] + "'][fieldvalue='" + multyValue + "']");
						if ($(multiItem).length > 0){
							var bgColor = $(multiItem).attr("bgColor");
							if (bgColor == 'undefined'){
								bgColor = "#ababab";
							}
							$(multiItem).css("background-color", bgColor);
							$(multiItem).css("color", "#FFFFFF");
							$(multiItem).attr("isselected", "true");
						}
					}
					$("#isHiddenDiv").css("display","block");
					$("#expandOrHiddenBar").attr("isHidden", "false");
					$("#expandOrHiddenBar").html("收起搜索");
				}
			}
			
			//生成多选的框
			function generateMultiItems(subItem){
				var divContainer = $("<div></div>");
				var itemStr = "";
				for (var i = 0; i < subItem.searchFileds.length; i++){
					itemStr += "<div class=\"multiItem\" isSelected=\"false\" bgColor=\"" + subItem.searchFileds[i].bgColor + "\" colunmName=\"" + subItem.colunmName + "\" fieldValue=\"" + subItem.searchFileds[i].fieldValue + "\">" + subItem.searchFileds[i].fieldName + "</div>";
				}
				itemStr += "<div class=\"clearDiv\"></div>";
				$(divContainer).append(itemStr);
				if (multiCount === 1){
					$(hm1Container).append(divContainer);
				}else{
					$(hm2Container).append(divContainer);
				}
				
				$(divContainer).find(".multiItem").click(function(){
					if ($(this).attr("isSelected") == 'false'){
						//对于像租租赁房这样的多选项，由于使用的是图标，所以没有背景色，这时，就要确定一个背景色了。
						var bgColor = $(this).attr("bgColor");
						if (bgColor == 'undefined'){
							bgColor = "#ababab";
						}
						$(this).css("background-color", bgColor);
						$(this).css("color", "#FFFFFF");
						$(this).attr("isSelected", "true");
					}else{
						$(this).css("background-color", "#FFFFFF");
						$(this).css("color", "#000000");
						$(this).attr("isSelected", "false");
					}
				});
			}
			
			$(".itemMenuPhone").click(function() {
				var previousMenuId = $(".phoneItemMenuSelected").first().attr("id");
				$(".phoneItemMenuSelected").each(function() {
					$(this).removeClass("phoneItemMenuSelected");
				});
				var isOnlyShowPrive = $(this).attr("onlyshowprivate");
				$(this).addClass("phoneItemMenuSelected");
				var clickedMenuId = $(this).attr("id");
				var itemShouldShow = $("#searchItemContent").find(".customSelect[searchMenuId=" + clickedMenuId + "]");
				$(itemShouldShow).removeClass("hiddenClass");
				$(itemShouldShow).addClass("displayClass");
				var itemShouldHidden = $("#searchItemContent").find(".customSelect[searchMenuId=" + previousMenuId + "]");
				$(itemShouldHidden).removeClass("displayClass");
				$(itemShouldHidden).addClass("hiddenClass");
				$(itemShouldHidden).each(function(){
					var columnName = $(this).find(".csc").attr("columnname");
					searchMap.remove(columnName);
					$(this).find(".csc").attr("selectedValue", "");
					$(this).find(".csc").html("请选择");
				});
				
				if (isOnlyShowPrive == 'true'){
					$("#publicSearchItemContent").addClass("hiddenClass");
					$("#selectedItemPanel").css("margin-top", "20px");
					$("#privateSearchItemContent").append(privateClearDiv);
				}else{
					$("#publicSearchItemContent").removeClass("hiddenClass");
					$("#selectedItemPanel").css("margin-top", "0px");
					$(privateClearDiv).remove();
				}
				
			});
			
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
			
			//多选点击时
			$(".multiItem").click(function(){
				var isSelected = $(this).attr("isselected");
				var columnName = $(this).attr("colunmname");
				var selectedValue = $(this).attr("fieldvalue");
				var valueStoredInMap = searchMap.get(columnName);
				var valueArray = null;
				if (valueStoredInMap == null){
					if (isSelected == "true"){
						searchMap.put(columnName, selectedValue);
					}
				}else{
					if (isSelected == 'true'){
						valueStoredInMap = valueStoredInMap + "," + selectedValue;
						var valueArray = valueStoredInMap.toString().split(",");
						valueArray = sortBubble(valueArray);
						searchMap.put(columnName, valueArray.join(","));
					}else{
						if (valueStoredInMap.toString().indexOf(",") > 0){
							var tempArray = valueStoredInMap.toString().split(",");
							var resultValueStr = "";
							if (tempArray.length > 0){
								for (var jjj = 0; jjj < tempArray.length; jjj++){
									if (tempArray[jjj] != selectedValue){
										resultValueStr += ("," + tempArray[jjj]);
									}
								}
							}
							resultValueStr = resultValueStr.substring(1, resultValueStr.length);
							var valueArray = resultValueStr.toString().split(",");
							valueArray = sortBubble(valueArray);
							searchMap.put(columnName, valueArray.join(","));
						}else{
							searchMap.remove(columnName, selectedValue);
						}
					}
				}
				generatorRequestUrl();
			});
			
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
			
			$("#expandOrHiddenBar").click(function(){
				if ($(this).attr("isHidden") == 'true'){
					$("#isHiddenDiv").css("display","block");
					$(this).attr("isHidden", "false");
					$(this).html("收起搜索");
				}else{
					$("#isHiddenDiv").css("display","none");
					$(this).attr("isHidden", "true");
					$(this).html("更多搜索");
				}
				
			});
			
			//当有搜索项存在时，使下面的分享，清除等按钮置为可用
			function anableButton(str) {
				if (str != "") {
					$(".disabledClearIcon").addClass("clearIcon");
					$(".disabledClearIcon").removeClass("disabledClearIcon");
					$(".disabledShareIcon").addClass("shareIcon");
					$(".disabledShareIcon").removeClass("disabledShareIcon");
					$(".disabledSaveIcon").addClass("saveIcon");
					$(".disabledSaveIcon").removeClass("disabledSaveIcon");
					$(".operationItemContainerPhone").addClass("anabledOperation");
					$(".operationItemContainerPhone").removeClass("disableOperation");
					$(".operationItemContainerPhone").attr("diableFlag", "true");
				}
			}
			//当有没有搜索项存在时，使下面的分享，清除等按钮置为不可用
			function disableButton() {
				$(".clearIcon").addClass("disabledClearIcon");
				$(".clearIcon").removeClass("clearIcon");
				$(".shareIcon").addClass("disabledShareIcon");
				$(".shareIcon").removeClass("shareIcon");
				$(".saveIcon").addClass("disabledSaveIcon");
				$(".saveIcon").removeClass("saveIcon");
				$(".operationItemContainerPhone").addClass("disableOperation");
				$(".operationItemContainerPhone").removeClass("anabledOperation");
				$(".operationItemContainerPhone").attr("diableFlag", "false");
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
			
			//点击清除按钮时
			$("#clearContainerPhone").click(function() {
				$(searchContainer).find(".csc").each(function(){
					$(this).html("请选择");
					$(this).attr("selectedvalue", "");
				});
				
				$(searchContainer).find(".multiItem").each(function(){
					$(this).attr("isselected", "false");
					$(this).css("background-color", "#ffffff");
					$(this).css("color", "#000000");
				});
				clearSearchMap();
				generatorRequestUrl();
				disableButton();
			});
			
			$("#saveContainerPhone").click(function() {
				if ($(this).attr("diableFlag") == 'false') {
					return;
				}
				var savedString = "";
				$("#searchItemContent").find(".csc").each(function(){
					if ($(this).attr("selectedvalue") != ''){
						savedString += $(this).html();
						savedString += ",";
					}
				});
				
				$(".multiItem").each(function(){
					if ($(this).attr("isselected") == "true"){
						savedString += $(this).html();
						savedString += ",";
					}
				});
				if (savedString == '') {
					alert("您保存的查询条件不能为空");
					return null;
				}
				var selectedInfo = "&" + String(getAllSearchInfo());
				var selectedInfoStr =  selectedInfo.replace("&", "|{}|");
				selectedInfoStr = optionDefault.url + selectedInfoStr;
				
				var dataString = {"savedUrl" : selectedInfoStr, "fieldName": savedString};
				$.ajax({
					type : "POST",
					url : "saveSearchField.show?actionMethod=saveSearchItem",
					data : dataString,
					dataType: "json",
					success : function(data) {
						var resultJson = data;
						if (resultJson.resultCode == '3'){
							alert("请登录后再保存");
							return;
						}else if (resultJson.resultCode == '2'){
							alert("您已经保存过该搜索条件了");
						}else if (resultJson.resultCode == '1'){
							alert("保存成功，您可以进入个人中心查看");
							var saveContainer = $("#saveContainerPhone");
							$(saveContainer).addClass("disableOperation");
							$(saveContainer).removeClass("anabledOperation");
							$(saveContainer).attr("diableFlag", "false");
							var saveIcon = $(".saveIcon");
							$(saveIcon).addClass("disabledSaveIcon");
							$(saveIcon).removeClass("saveIcon");
						}
						
					},
					error : function() {
					}
				});
			});
			
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
			
			$("#shareContainerPhone").click(function() {
				if ($(this).attr("diableflag") == 'true'){
					$("#sharePanel").css("display", "block");
				}
			});
			
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