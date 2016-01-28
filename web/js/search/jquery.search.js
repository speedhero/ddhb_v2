/**
 * Author: Vincent Yang Date: 2014-06-01 Description: This jQuery plug-in is
 * used to create a way to retrieve information
 */
;
(function($) {
	$.fn.extend({
		"createSearch" : function(option) {
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
			//用来存放医院信息
			var hospital = new Array() ;
			//存放县区信息
			var county = new Array();
			
			var previousUrl = "";

			var privateItemMap = new Map();
			
			searchMap.put("ispage", 1);

			// define the search Menu Bar
			$(this).append("<div id='searchMenuBar'></div>");
			$(this).append("<div class='clearDiv'></div>");
			$(this).append("<div id='searchContent'></div>");
			// define the search item content
//			$("#searchContent").append("<div id='searchItemContent'><div id='privateSearchItemContent'></div><div id='publicSearchItemContent'><div id='notHidden'></div><div id='isHidden'></div></div></div>");
//			Modify by hejianbo 20150513
			$("#searchContent").append("<div id='searchItemContent'><div id='privateSearchItemContent'></div><div id='publicSearchItemContent'><div id='notHidden'></div><div id='isSelectList'><div class='searchLabelLeft'></div></div></div></div>");
			// define the selected search item panel
			$("#searchContent").append("<div class='clearDiv'></div>");
			$("#searchContent").append("<div id='selectedSearchItemPanel'><div id='allSelectedTitle'>本次找房条件:</div><div id='allSelectedItemsDiv'>&nbsp;</div><div id='operationDiv'></div><div class='clearDiv'></div></div>");
			$("#operationDiv").append("<div class='operationItemContainer disableOperation' diableFlag='false' id='shareContainer'><div class='oprationIcon disabledShareIcon'></div><div class='operationLabel'>分享</div><div class='clearDiv'></div></div>");
			$("#operationDiv").append("<div class='operationItemContainer disableOperation' diableFlag='false' id='saveContainer'><div class='oprationIcon disabledSaveIcon'></div><div class='operationLabel'>保存</div><div class='clearDiv'></div></div>");
			$("#operationDiv").append("<div class='operationItemContainer disableOperation' diableFlag='false' id='clearContainer'><div class='oprationIcon disabledClearIcon'></div><div class='operationLabel'>清除</div><div class='clearDiv'></div></div>");
			$("#operationDiv").append("<div class='clearDiv'></div>");

			var str = "<div id='sharePanel' style='height:30px; float:right; display:none;'>" +
					"<div id='shareToMobile' style='float:left;'><a title='分享到手机' style='margin-top:10px;' href='#'><img src='"+globalUrl+"images/search/shareToMobile.png' style='margin-top: 5px;'></a></div>" + 
					"<div id='bdshare' class='bdshare_t bds_tools get-codes-bdshare' data='{\"text\":''}'>" + "<a class='bds_qzone' onmousedown='startToShare()'></a> <a class='bds_tsina' onmousedown='startToShare()'></a>" + "<a class='bds_tqq' onmousedown='startToShare()'></a><a class='bds_sqq' onmousedown='startToShare()'></a></div></div>";
			$("#operationDiv").append(str);

			$("#sharePanel").focusout(function() {
				$(this).css("display", 'none');
			});
			
			$("#shareToMobile").click(function(){
				var selectedInfo = "&" + String(getAllSearchInfo());
				var selectedInfoStr =  selectedInfo.replaceAll("&", "|{}|");
				selectedInfoStr = optionDefault.url + selectedInfoStr;
				var imgSrc = globalUrl + "searchPicture.show?actionMethod=getSharePicture&savedUrl=" + selectedInfoStr;
				$("#searchPicture").attr("src", imgSrc);
				fangda();
			});

//			 define the hidden button div
			if (currentOptions.displayHiddenBar == true) {
//				$("#searchContent").append("<div id='hiddenButtonBar'><div id='expandOrHiddenBar'>更多搜索</div><div class='clearDiv'></div></div>");
			} else {
				$("#searchContent").append("<div id='hiddenButtonBar hiddenButtonBarClass'><div id='expandOrHiddenBar hiddenButtonBarClass'></div><div class='clearDiv'></div></div>");
//				$("#searchContent").append("<div style='height: 20px;'></div>");
			}
			$("#searchContent").append("<div class='clearDiv'></div>");
			
			for (var i = 0; i < searItems.length; i++) {
				// create the Search Menu Bar Item 显示一级菜单 学校Id 32
				if (i == 0) {
					$("#searchMenuBar").append("<div class='itemMenu itemMenuSelected' onlyShowPrivate='" 
							+ (searItems[i].onlyShowPrivate == 1 ? true : false) + "' id='searchMenu" + searItems[i].id + "' menuId='" 
							+ searItems[i].id + "'>" + searItems[i].searchLabel + "</div>");
				} else {
					$("#searchMenuBar").append("<div class='itemMenu' onlyShowPrivate='" + (searItems[i].onlyShowPrivate == 1 ? true : false) 
							+ "' id='searchMenu" + searItems[i].id + "' menuId='" + searItems[i].id + "'>" + searItems[i].searchLabel + "</div>");
				}
				//二级循环
				var subItems = searItems[i].subItems;
				for (var j = 0; j < subItems.length; j++) {
					// if the item is not public, it will be append into the
					// privateSearchItemContent
					if (subItems[j].isPublic == 0) {
						// each private Search Item Div is identified by
						// "privateSearchContent + searItems[i].id"
						var privateSearchContentObj = $("#privateSearchContent" + searItems[i].id);
						
						if ($(privateSearchContentObj).length > 0) {
							
							$(privateSearchContentObj).append("<div class='itemLine'><div class='searchLabelContainer'><div class='searchLabelLeft'></div><div class='searchLabel' searchItemId='" 
									+ subItems[j].id + "'>" + subItems[j].searchLabel 
									+ ":</div><div class='searchLabelRight'></div><div class='clearDiv'></div></div><div class='searchFieldContentDiv' superMenuId='" 
									+ searItems[i].id + "' fieldsContentId='" + subItems[j].id + "'></div><div class='clearDiv'></div></div>");
							// record the private columnName and super-search
							// item id
						} else {
							//每个一级菜单，第一次添加二级子菜单时 都会运行到这
							
							privateSearchContentObj = $("<div id='privateSearchContent" + searItems[i].id + "'></div>");
							$("#privateSearchItemContent").append(privateSearchContentObj);
							if (i != 0) {
								$(privateSearchContentObj).addClass("hiddenPrivateDiv");
							} else {
								$(privateSearchContentObj).addClass("diaplayPrivateDiv");
							}
							//TODO 当遇到  一级菜单学校--二级菜单学校时，把二级菜单学校隐藏，换成城区二级菜单
							if(subItems[j].id == 44 || subItems[j].id == 25	 ){
								$(privateSearchContentObj).append("<div class='itemLine'><div class='searchLabelContainer'><div class='searchLabelLeft'></div><div class='searchLabel' " 
										+ " >区域"   
										+ ":</div><div class='searchLabelRight'></div><div class='clearDiv'></div></div><div class='searchFieldContentDiv' superMenuId='" 
										+ searItems[i].id + "' fieldsContentId='" + subItems[j].id + "' ></div><div class='clearDiv'></div></div>");
								//下面这个是子菜单框
								$(privateSearchContentObj).append("<div class='subFielsDiv' class='hiddenPrivateDiv' subFieldsContainerId='" + subItems[j].id + "'></div>");
								addCountyField(subItems[j], searItems[i].id);
								continue;
							}
							$(privateSearchContentObj).append("<div class='itemLine'><div class='searchLabelContainer'><div class='searchLabelLeft'></div><div class='searchLabel' searchItemId='" 
									+ subItems[j].id + "'>" + subItems[j].searchLabel 
									+ ":</div><div class='searchLabelRight'></div><div class='clearDiv'></div></div><div class='searchFieldContentDiv' superMenuId='" 
									+ searItems[i].id + "' fieldsContentId='" + subItems[j].id + "'></div><div class='clearDiv'></div></div>");
						}
						if (subItems[j].hasSubValue == 1) {
							$(privateSearchContentObj).append("<div class='subFielsDiv' class='hiddenPrivateDiv' subFieldsContainerId='" + subItems[j].id + "'></div>");
						}
						if (subItems[j].isRange == 1) {
							privateItemMap.put(subItems[j].colunmName, searItems[i].id);
						}
					} else {
						//Modify By hejianbo 20150512
						//这里区分是否显示查询条件
						if (subItems[j].isHidden == 0) {
							$("#notHidden").append("<div class='itemLine'><div class='searchLabelContainer'><div class='searchLabelLeft'></div><div class='searchLabel' searchItemId='" 
									+ subItems[j].id + "'>" + subItems[j].searchLabel 
									+ ":</div><div class='searchLabelRight'></div><div class='clearDiv'></div></div><div class='searchFieldContentDiv' fieldsContentId='" 
									+ subItems[j].id + "'></div><div class='clearDiv'></div></div>");
						} else {
//							$("#isHidden").append("<div class='itemLine'><div class='searchLabelContainer'><div class='searchLabelLeft'></div><div class='searchLabel' searchItemId='" 
//									+ subItems[j].id + "'>" + subItems[j].searchLabel 
//									+ ":</div><div class='searchLabelRight'></div><div class='clearDiv'></div></div><div class='searchFieldContentDiv' fieldsContentId='" 
//									+ subItems[j].id + "'></div><div class='clearDiv'></div></div>");
							
//							<select id="m2s1" name="gender" onchange="setPlatSex();">
//	              			<c:if test="${platMemberInfo != null }">
//	              				<option value="0" <c:if test="${platMemberInfo.sex == 0 }">selected</c:if>>先生</option>
//	              				<option value="1" <c:if test="${platMemberInfo.sex == 1 }">selected</c:if>>女士</option>
//	              			</c:if>
//	                    </select>
							var htmlValue = $("#isSelectList").text();
							if(htmlValue.indexOf("筛选:") == -1){
								$("#isSelectList").append("<div class='searchLabel' style='float:left; padding-right:5px;width:50px;margin-right:5px;text-align:right;'>筛选:</div>");
							}
							$("#isSelectList").append("<div class='itemLint xiala xl_w90' style='float:left; padding-bottom:15px;' >" 
									+ "<select name='" + subItems[j].colunmName 
									+ "' columnName='" + subItems[j].colunmName
									+ "' searchItemId=" + subItems[j].id 
									+ " id=" + subItems[j].id
									+ " onchange=searchCondition('" + subItems[j].colunmName 
									+ "'); "  
									+">" 
									+ "<option value=''>"+subItems[j].searchLabel+"</option>" 
									+ "</select>" 
									+ "</div>");
							addSearchSelectOption(subItems[j], searItems[i].id);
							continue;
						}
					}
					addSearchField(subItems[j], searItems[i].id);
				}
			}
			$("#searchContent").append("<div style='clear:both;'></div>");

			var hasItem = false;
			var keyArray = searchMap.keys();
			for (var i = 0; i < keyArray.length; i++) {
				//显示从搜索框选项搜索的关键字  Modify by hejianbo
				if(keyArray[i] =="inputSearch"){
					//获取inputSearch的值
						var keyValue = searchMap.get(keyArray[i]);
//						var inputSearchValue = $("#inputSerchBut").attr("value");
						if(keyValue == ""){
							keyValue = $("#inputSerchBut").attr("value");
//							keyValue = $("[selectedcolumn='inputSearch']").html();
						}
						if( keyValue == "")
							continue;
						//把这个值写入
						initRemovePanel(keyArray[i], keyValue);
					}
				//END
				
				if (keyArray[i].indexOf("ddhb_") >= 0) {
					hasItem = true;
					//分享的时候，分享插件会给最后一个值加上#和一个序列号，所以要去掉才能正确选中该值
					var searchValue = searchMap.get(keyArray[i]);
					var finalSearchValue = searchValue;
					if (searchValue.indexOf("#") > 0){
						finalSearchValue = searchValue.substring(0, searchValue.indexOf("#"));
					}
					var sharedItem = $("#searchContent .searchField[columnName='" + keyArray[i] + "']");
					if ($(sharedItem).length > 0) {
						if ($(sharedItem).first().next().attr("isPrivateItem") == 'true') {
							var menuId = $(sharedItem).parent().attr("superMenuId");
							var firstMenuId = $(".itemMenuSelected").first().attr("menuId");
							initPrivateContent(firstMenuId, menuId);
							var selectedMenuItem = $(".itemMenu[menuId='" + menuId + "']");
							$(selectedMenuItem).addClass("itemMenuSelected");
							if ($(selectedMenuItem).attr("onlyShowPrivate") == 'true') {
								$("#publicSearchItemContent").css("display", "none");
							}
						}
						if ($(sharedItem).parent().parent().parent().attr("id") == 'isHidden') {
							$("#isHidden").css("display", "block");
						}
						if (finalSearchValue.indexOf(",") > 0) {
							var valueArray = finalSearchValue.split(",");
							for (var m = 0; m < valueArray.length; m++) {
								var item = $(".searchField[columnName='" + keyArray[i] + "'][fieldValue='" + valueArray[m] + "']");
								if ($(item).length > 0) {
									$(item).addClass("selectedField");
									initRemovePanel(keyArray[i], $(item).html());
								}
							}
						} else {
							var item = $("#searchContent .searchField[columnName='" + keyArray[i] + "'][fieldValue='" + finalSearchValue + "']");
							if ($(item).length > 0) {
								$(item).addClass("selectedField");
								$(item).children().next().next().css("display", "inline");
								var fieldsContentId = $(item).parent().attr("fieldsContentId");
								var fieldsContentIdI = fieldsContentId + "_" + finalSearchValue;
								var subContainer = $(".hiddenSubField[subFiledDivId='" + fieldsContentIdI + "']");
								if(fieldsContentIdI == "44" || fieldsContentId == "25"){
								$(subContainer).addClass("displaySubField_xq");
								}else{
									$(subContainer).addClass("displaySubField");
								}
								$(subContainer).removeClass("hiddenSubField");
								initRemovePanel(keyArray[i], $(item).children().first().html());
							} else {
								var isRange = $(sharedItem).first().next().attr("isrange");
								if (isRange == 'true' && finalSearchValue.indexOf("@") > 0) {
									var valueArray = finalSearchValue.split("@");
									$(".minValue[columnName='" + keyArray[i] + "']").val(valueArray[0]);
									$(".maxValue[columnName='" + keyArray[i] + "']").val(valueArray[1]);
									if ($(sharedItem).first().next().html().indexOf("万") > 0){
										$("#allSelectedItemsDiv").append("<div class='selectedSearchItemDiv'><div class='columnDiv' fieldvalue='" + finalSearchValue + "' selectedcolumn='" + keyArray[i] 
										+ "'>" + valueArray[0] + "-" + valueArray[1] + "万</div><div class='removeColumnDiv' ismulty='false' columnname='" + keyArray[i] + "' fieldvalue='" + finalSearchValue 
										+ "'></div><div class='clearDiv'></div></div>");
										$(".removeColumnDiv").click(function() {
											addRemoveColumnEvent($(this));
										});
									}else{
										$("#allSelectedItemsDiv").append("<div class='selectedSearchItemDiv'><div class='columnDiv' fieldvalue='" + finalSearchValue + "' selectedcolumn='" + keyArray[i] 
										+ "'>" + valueArray[0] + "-" + valueArray[1] + "平</div><div class='removeColumnDiv' ismulty='false' columnname='" + keyArray[i] + "' fieldvalue='" + finalSearchValue 
										+ "'></div><div class='clearDiv'></div></div>");
										$(".removeColumnDiv").click(function() {
											addRemoveColumnEvent($(this));
										});
									}
									
								}
							}
						}
					} else {
						var subField = $("#searchContent .subFieldItemDiv[fieldColumnName='" + keyArray[i] + "']");
						if ($(subField).length > 0) {
							var subFieldItem = $("#searchContent .subFieldItemDiv[fieldColumnName='" + keyArray[i] + "'][fieldValue='" + finalSearchValue + "']");
							if ($(subFieldItem).length > 0) {
								$(subFieldItem).addClass("subSelectedField");
								initRemovePanel(keyArray[i], $(subFieldItem).html());
								var superFieldItem = $("#searchContent .searchField[columnName='" + $(subFieldItem).attr("parentColunmName") 
										+ "'][fieldValue='" + $(subFieldItem).attr("parentFieldValue") + "']");
								$(superFieldItem).addClass("selectedField");
								var superSubFieldDivId = $(subFieldItem).attr("superSubFieldDivId");
								
								if(keyArray[i] == "ddhb_one_school.erpId"){
								$(".displaySubField_xq").addClass("hiddenSubField");
								$(".displaySubField_xq").removeClass("displaySubField_xq");

								$("[subFiledDivId='" + superSubFieldDivId + "']").addClass("displaySubField_xq");
								}else{
									$(".displaySubField").addClass("hiddenSubField");
									$(".displaySubField").removeClass("displaySubField");
									$("[subFiledDivId='" + superSubFieldDivId + "']").addClass("displaySubField");
								}
								$("[subFieldsContainerId='" + (superSubFieldDivId.split("_")[0]) + "']").css("display", "block");
								var firstMenuId = $(".itemMenuSelected").first().attr("menuId");
								var menuId = $(subFieldItem).attr("menuId");
								initPrivateContent(firstMenuId, menuId);
								$(".itemMenu[menuId='" + menuId + "']").addClass("itemMenuSelected");
							}
						}
					}
					$("#searchContent .searchUnlimited[columnName='" + keyArray[i] + "']").removeClass("selectedField");
				}
			}
			
			//distinguish whether there has default searchItem, if has ,enable the save and clear button and do query
			if (hasItem){
				anableButton();
			}

			//initialize the private content, make the current private content display, and hidden the default private content
			function initPrivateContent(firstMenuId, menuId) {
				$("#privateSearchContent" + firstMenuId).addClass("hiddenPrivateDiv");
				$("#privateSearchContent" + firstMenuId).removeClass("diaplayPrivateDiv");
				$("#privateSearchContent" + menuId).addClass("diaplayPrivateDiv");
				$(".itemMenuSelected").removeClass("itemMenuSelected");
			}

			$(".itemMenu").click(function() {
				$(".narrowIcon").css("display", "none");
				var onlyShowPrivate = $(this).attr("onlyShowPrivate");
				//是否只显示该菜单的私有对象，如果是，就隐藏掉公共对象
				if (onlyShowPrivate == "true") {
					$(".selectedField").each(function() {
						var columnName = $(this).attr("columnName");
						var fieldValue = $(this).attr("fieldValue");
						//如果不是不限被选中，那么去掉该选中项
						if (!$(this).hasClass("searchUnlimited")){
							$(this).removeClass("selectedField");
							searchMap.remove(columnName);
							initSelectedItem();
						}
						$(".removeColumnDiv[columnname='" + columnName + "'][fieldvalue='" + fieldValue + "']").first().parent().remove();
						$(".yearBefore").val("");
					});
					$(".subSelectedField").each(function() {
						var columnName = $(this).attr("fieldColumnName");
						var fieldValue = $(this).attr("fieldValue");
						$(this).removeClass("subSelectedField");
						searchMap.remove(columnName);
						initSelectedItem();
						$(".removeColumnDiv[columnname='" + columnName + "'][fieldvalue='" + fieldValue + "']").first().parent().remove();
					});
					$("#publicSearchItemContent").css("display", "none");
				} else {
					$("#publicSearchItemContent").css("display", "block");
				}
				//清理掉私有域中的Input对象
				clearPrivateInput($(".itemMenuSelected").first().attr("menuId"));
				//去掉上一个选中菜单项的样式
				$(".itemMenuSelected").each(function() {
					$(this).removeClass("itemMenuSelected");
				});

				$(".selectedField[isPrivateItem='true']").each(function() {
					columnName = $(this).attr("columnName");
					fieldValue = $(this).attr("fieldValue");
					$(this).removeClass("selectedField");
					$(".removeColumnDiv[columnname='" + columnName + "'][fieldvalue='" + fieldValue + "']").first().parent().remove();
					searchMap.remove(columnName);
					initSelectedItem();
					$(".searchUnlimited[columnName='" + columnName + "']").addClass("selectedField");
				});

				$(this).addClass("itemMenuSelected");
				var selectedSearchMenuDiv = $(this).attr("menuId");
				$(".diaplayPrivateDiv").removeClass("diaplayPrivateDiv").addClass("hiddenPrivateDiv");
				//TODO
				$("#privateSearchContent" + selectedSearchMenuDiv).addClass("diaplayPrivateDiv");
				$(".subFieldItemDiv").each(function() {
					if ($(this).hasClass("subSelectedField")) {
						removeSearchItemFromMap($(this));
						$(this).removeClass("subSelectedField");
						columnName = $(this).attr("fieldColumnName");
						fieldValue = $(this).attr("fieldValue");
						searchMap.remove(columnName);
						initSelectedItem();
						$(".removeColumnDiv[columnname='" + columnName + "'][fieldvalue='" + fieldValue + "']").first().parent().remove();
					}
				});
				$("[hasSubValue=true]").each(function() {
					if ($(this).hasClass("selectedField")) {
						if (!$(this).hasClass("searchUnlimited")) {
							$(this).removeClass("selectedField");
						}
						$(this).children().first().next().next().css("display", "none");
					}
				});

				$(".displaySubField").each(function() {
					$(this).removeClass("displaySubField");
					$(this).addClass("hiddenSubField");
				});
				$(".displaySubField_xq").each(function() {
					$(this).removeClass("displaySubField_xq");
					$(this).addClass("hiddenSubField");
				});
				initSelectedItem();
				showSelectedField();
			});

			$("#expandOrHiddenBar").click(function() {
				if ($("#isHidden").css("display") == 'block') {
					$("#expandOrHiddenBar").html("更多搜索");
					$("#isHidden").css("display", "none");
				} else {
					$("#expandOrHiddenBar").html("收起搜索");
					$("#isHidden").slideUp("5000");
					$("#isHidden").css("display", "block");
				}
			});
			//添加学校-区域 Modify by 何剑波
			function addCountyField(searchItem,menuId){
				var searchFieldsArray = searchItem.searchFileds;
				var fieldscontentIdObj = $("#searchContent div[fieldscontentid=" + searchItem.id + "]");
				$(fieldscontentIdObj).append("<div class='searchField searchUnlimited selectedField' hasSubValue='true'" 
						 + " columnName='" + county[0][2] + "'><span>不限</span></div>");
				//先把城区的显示
				for(var i = 0; i < county.length; i++){
					$(fieldscontentIdObj).append("<div class='searchField' hasSubValue='true' isPrivateItem='true'" 
							+ " columnName='" + county[i][2] + "' fieldValue='" 
							+ county[i][1] + "'><span>" + county[i][0]
							+ "</span></br><img src='"+globalUrl+"images/search/narrow.png' class='narrowIcon' style='display:none; width:auto; height:auto;'/></div>"
					);
					addSubSchoolGradeField(county[i], searchItem, menuId);
				}
			}
			// add search field  
			function addSearchField(searchItem, menuId) {
				//var globalUrl = 'http://www.hshb.cn/';
				var searchFieldsArray = searchItem.searchFileds;
				var fieldscontentIdObj = $("#searchContent div[fieldscontentid=" + searchItem.id + "]");
				$(fieldscontentIdObj).append("<div class='searchField searchUnlimited selectedField' hasSubValue='" 
						+ (searchItem.hasSubValue == 1 ? true : false) + "' columnName='" + searchItem.colunmName + "'><span>不限</span></div>");
				//循环取出查询的条件  三级循环
				if(searchItem.colunmName != "ddhb_one_hospital.hospitalName"){
				for (var i = 0; i < searchFieldsArray.length; i++) {
					if (searchItem.hasSubValue == 1) {
						//TODO 取出区域值
						if(searchItem.id == "34" || searchItem.id == "16"){
							county[i] = new Array();
							//区域名
							county[i][0] = searchFieldsArray[i].fieldName;
							//所对应的Id
							county[i][1] = searchFieldsArray[i].fieldValue;
							//搜索的字段
							county[i][2] = searchItem.colunmName;
						}
						
						$(fieldscontentIdObj).append("<div class='searchField' hasSubValue='true' isPrivateItem='" 
								+ (searchItem.isPublic == 0 ? true : false) + "' columnName='" + searchItem.colunmName + "' fieldValue='" 
								+ searchFieldsArray[i].fieldValue + "'><span>" + searchFieldsArray[i].fieldName 
								+ "</span></br><img src='"+globalUrl+"images/search/narrow.png' class='narrowIcon' style='display:none; width:auto; height:auto;'/></div>");
						addSubSearchField(searchFieldsArray[i], searchItem, menuId);
					} else {
						if (searchItem.isRange == 1) {
							$(fieldscontentIdObj).append("<div class='searchField' isRange='true' isPrivateItem='" 
									+ (searchItem.isPublic == 0 ? true : false) + "' hasSubValue='false' columnName='" + searchItem.colunmName 
									+ "' fieldValue='" + searchFieldsArray[i].minFieldValue + "@" + searchFieldsArray[i].maxFieldValue + "'><span>" 
									+ searchFieldsArray[i].fieldName + "</span></div>");
						} else {
							$(fieldscontentIdObj).append("<div class='searchField' isPrivateItem='" 
									+ (searchItem.isPublic == 0 ? true : false) + "' hasSubValue='false' ismulty='" + (searchItem.ismulty == 1 ? true : false) 
									+ "' columnName='" + searchItem.colunmName + "' fieldValue='" + searchFieldsArray[i].fieldValue + "'><span>" 
									+ searchFieldsArray[i].fieldName + "</span></div>");
						}
					}
				}
			}else{
				//医院处理
				//创建数组				
				hospital = new Array(searchFieldsArray.length); 
				for(var i=0 ; i<searchFieldsArray.length; i++){
					//创建二位数组
//					hospital[i] = new Array(2);
//					//医院的erpId
//					hospital[i][0] = searchFieldsArray[i].fieldValue;
					//医院的名字
					hospital[i] = searchFieldsArray[i].fieldName;
				}
			}
				//是否有输入框
				if (searchItem.isNeedInput == 1) {
					if (searchItem.isPublic == 0) {
						var searchInputDiv = $("<div class='inputDiv' hasSubValue='false'></div>");
						//为了区分 医院和其他信息
						var inputItem = "";
						if(searchItem.colunmName == "ddhb_one_hospital.hospitalName"){
						 inputItem = "<div class='rangeContentDiv' style='width:250px;'><input type='text' privateInput='true' columnName='" + searchItem.colunmName 
								+ "' class='inputElem' style='width:250px;' /><span>" 
								+ searchItem.unitName + "</span></div>";
						 $(searchInputDiv).width(300);
						}else{
							inputItem = "<div class='rangeContentDiv'><input type='text' privateInput='true' columnName='" + searchItem.colunmName 
								+ "' class='minValue'/>-<input type='text' privateInput='true' columnName='" + searchItem.colunmName + "' class='maxValue'/><span>" 
								+ searchItem.unitName + "</span></div>";	
						}
						$(searchInputDiv).append(inputItem);
						var searchButton = $("<div class='toSearchIcon' unitName='" + searchItem.unitName  + "' columnName='" + searchItem.colunmName + "'></div>");
						$(searchInputDiv).append(searchButton);
						$(searchButton).click(function() {
							addInputClickEvent($(this));
						});
						$(fieldscontentIdObj).append(searchInputDiv);
					} else {
						var searchInputDiv = $("<div class='inputDiv' hasSubValue='false'></div>");
						var inputItem = "<div class='rangeContentDiv'><input type='text' privateInput='false' columnName='" + searchItem.colunmName  + "' class='minValue'/>"
							+ "-<input type='text' privateInput='false' columnName='" + searchItem.colunmName + "' class='maxValue' /><span>"+ searchItem.unitName + "</span></div>";
						$(searchInputDiv).append(inputItem);
						var searchButton = $("<div class='toSearchIcon' unitName='" + searchItem.unitName + "' columnName='" + searchItem.colunmName + "'></div>");
						$(searchInputDiv).append(searchButton);
						$(searchButton).click(function(){
							addInputClickEvent($(this));
						});
						$(fieldscontentIdObj).append(searchInputDiv);
					}
				}
				if (searchItem.isRange == 1 && searchItem.isNeedInput == '2'){
					$(fieldscontentIdObj).append("<div class='searchField' columnName='" + searchItem.colunmName + "' isRange='" + (searchItem.isRange == 1?"true":"false") 
							+ "' fieldValue='0@3000-01-01'><input class='yearBefore' columnName='" + searchItem.colunmName + "'/><span>年以后</span></div>");
				}
				$(fieldscontentIdObj).append("<div class='clearDiv'></div>");
			}
			
			$(".yearBefore").blur(function(){
				var reg = new RegExp("^[0-9]{4}$");
				if (!reg.test($(this).val())){
					if ($(this).val() != ""){
						alert("请输入表示年份的四位数字！");
					}
					var columnName = $(this).attr("columnname");
					$(this).parent().removeClass("selectedField");
					$(this).parent().parent().find(".searchUnlimited[columnname='" + columnName + "']").addClass("selectedField");
					return;
				}
				var fieldValue = $(this).val() + "-01-01@3000-01-01";
				$(this).parent().attr("fieldValue", fieldValue);
				addSearchItemToMap($(this).parent());
				showSelectedField();
			});
			
			$(".searchField").click(function() {
				if ($("#allSelectedItemsDiv").html() == '&nbsp;'){
					$("#allSelectedItemsDiv").html("&nbsp;");
				}
				searchMap.put("ispage", 1);
				var fieldscontentid = $(this).parent().attr("fieldscontentid");
				var selectedSearchField = $(this);
				if ($(this).attr("hasSubValue") == 'true') {
					$(this).children().first().next().next().css("display", "inline");
				}
				//
				if ($(this).find("input").length <= 0){
					addSearchItemToMap($(this));
				}
				var fieldValue = $(this).attr("fieldValue");
				//if this item has input, clear the input value
				var inputField = $("input[columnname='" + $(this).attr("columnname") + "']");
				if ($(inputField).length > 0){
					$(inputField).each(function(){
						$(this).val("");
					});
				}
				
				$(".selectedField").each(function(index) {
					if ($(this).parent().attr("fieldscontentid") == fieldscontentid) {
						// if this item can multy selected, don't remove the
						// "selectedField" class
						if ($(this).children().length == 3) {
							if ($(this).attr("fieldValue") != fieldValue) {
								$(this).children().first().next().next().css("display", "none");
							}
						}
						if ($(selectedSearchField).hasClass("searchUnlimited")) {
							$(this).removeClass("selectedField");
							removeSearchItemFromMap($(this));
						} else if ($(this).attr("ismulty") != 'true') {
							$(this).removeClass("selectedField");
						}
					}
				});
				$(this).addClass("selectedField");
				// 如果某个选项有子项目
				if ($(this).attr("hassubvalue") == 'true') {
					$(".displaySubField").each(function() {
						$(this).removeClass("displaySubField");
						$(this).addClass("hiddenSubField");
						removeSearchItemFromMap($(this));
					});

					$(".displaySubField_xq").each(function() {
						$(this).removeClass("displaySubField_xq");
						$(this).addClass("hiddenSubField");
						removeSearchItemFromMap($(this));
					});
					
					// if a search field has subvalue heen selected, when seach
					// field changed, remove all the original subvalue
					$(".subSelectedField").each(function() {
						$(this).removeClass("subSelectedField");
						columnName = $(this).attr("fieldColumnName");
						fieldValue = $(this).attr("fieldValue");
						$(".removeColumnDiv[columnname='" + columnName + "'][fieldvalue='" + fieldValue + "']").first().parent().remove();
						searchMap.remove(columnName);
						initSelectedItem();
					});

					$("div[subfileddivid=" + fieldscontentid + "_" + $(this).attr("fieldvalue") + "]").removeClass("hiddenSubField");
					if(fieldscontentid == "44" || fieldscontentid == "25"){
						$("div[subfileddivid=" + fieldscontentid + "_" + $(this).attr("fieldvalue") + "]").addClass("displaySubField_xq");	
					}else{
						$("div[subfileddivid=" + fieldscontentid + "_" + $(this).attr("fieldvalue") + "]").addClass("displaySubField");
					}
					
					if ($("div[subfileddivid=" + fieldscontentid + "_" + $(this).attr("fieldvalue") + "]").children().length == 0) {
						$(".displaySubField").first().addClass("hiddenSubField");
						$(".displaySubField").first().removeClass("displaySubField");
						
						$(".displaySubField_xq").first().addClass("hiddenSubField");
						$(".displaySubField_xq").first().removeClass("displaySubField_xq");
					}
				}

				// hidden sub field
				if ($(this).hasClass("searchUnlimited")) {
					$(".displaySubField").first().addClass("hiddenSubField");
					$(".displaySubField").first().removeClass("displaySubField");
					$(".displaySubField_xq").first().addClass("hiddenSubField");
					$(".displaySubField_xq").first().removeClass("displaySubField_xq");
					removeSearchItemFromMap($(this));
				}
				
				showSelectedField();
			});

			$(".subFieldItemDiv").click(function() {
				$(".subSelectedField").each(function() {
					$(this).removeClass("subSelectedField");
					columnName = $(this).attr("fieldColumnName");
					fieldValue = $(this).attr("fieldValue");
					$(".removeColumnDiv[columnname='" + columnName + "'][fieldvalue='" + fieldValue + "']").first().parent().remove();
				});
				initSelectedItem();
				$(this).addClass("subSelectedField");
				columnName = $(this).attr("fieldColumnName");
				fieldValue = $(this).attr("fieldValue");
				var parentColunmName = $(this).attr("parentcolunmname");
				searchMap.remove(columnName);
				searchMap.remove("ddhb_one_school.erpId");
				initSelectedItem();
				searchMap.put(columnName, fieldValue);
				showSelectedField();
				$("#allSelectedItemsDiv").append("<div class='selectedSearchItemDiv'><div class='columnDiv' fieldvalue='" + fieldValue 
						+ "' selectedColumn='" + columnName + "'>" + $(this).html() + "</div><div class='removeColumnDiv' parentColumn='" + parentColunmName 
						+ "' columnname='" + columnName + "' fieldvalue='" + fieldValue + "'></div><div class='clearDiv'></div></div>");
				$(".removeColumnDiv").click(function() {
					addRemoveColumnEvent($(this));
				});
				anableButton();
				//supersubfielddivid  显示学校子菜单
				var supersubfielddivid = $(this).attr("supersubfielddivid")
				var _strId =  supersubfielddivid.substring(0,supersubfielddivid.lastIndexOf("_")); 
				var schoolDiv = $(".hiddenSubField[subfileddivid="+ supersubfielddivid +"]");
				if(schoolDiv.length > 0 ){
					//先去除所有
					$(".displaySubField[subParentSchoolGrade='"+ _strId +"']").addClass("hiddenSubField");
					$(".displaySubField[subParentSchoolGrade='"+ _strId +"']").removeClass("displaySubField");
					
					$(".hiddenSubField[subfileddivid="+ supersubfielddivid +"]").addClass("displaySubField");
					$(".hiddenSubField[subfileddivid="+ supersubfielddivid +"]").removeClass("hiddenSubField");
					
				}
				//TODO 
				///////////////////////////////////////////////////
//				$(".displaySubField").each(function() {
					//supersubfielddivid  显示学校子菜单
//					var supersubfielddivid = $(this).attr("supersubfielddivid")
//					var schoolDiv = $(".hiddenSubField[subfileddivid="+ supersubfielddivid +"]");
//				});

				$(".displaySubField_xq").each(function() {
//					$(this).removeClass("displaySubField_xq");
//					$(this).addClass("hiddenSubField");
					//removeSearchItemFromMap($(this));
				});
				//////////////////////////////////////////////////////
				
			});
			
			function addInputClickEvent(obj){
				var columnName = $(obj).attr("columnName");
				//搜索范围的关键字
				var searchValue = "";
				//显示关键字的内容
				var fieldValueLabel = "";
				//与医院的区分开来
				if(columnName == "ddhb_one_hospital.hospitalName"){
					$("input[columnName='" + columnName + "']").each(function() {
						if ($(this).hasClass("inputElem")) {
							searchValue = $(this).val();
							fieldValueLabel = searchValue; 
						}
					});
					if(searchValue=='')
						return;
				}else{
				var maxValue = "";
				var minValue = "";
				$("input[columnName='" + columnName + "']").each(function() {
					if ($(this).hasClass("minValue")) {
						minValue = $(this).val();
					}
					if ($(this).hasClass("maxValue")) {
						maxValue = $(this).val();
					}
				});
				if (maxValue == '' || minValue== ''){
					return;
				}
				if (parseFloat(maxValue) > minValue) {
					searchValue = minValue + "@" + maxValue;
					fieldValueLabel = minValue + "-" + maxValue + $(obj).attr("unitName");
				} else {
					searchValue = maxValue + "@" + minValue;
					fieldValueLabel = maxValue + "-" + minValue + $(obj).attr("unitName");
				}
				}
				
				var previousObj = $(".columnDiv[selectedcolumn='" + columnName + "']");
				if ($(previousObj).length > 0){
					removeFieldFromPanel($(previousObj), false);
				}
				
				//编写查询的范围
				var appendStr = "<div class='selectedSearchItemDiv'><div class='columnDiv' fieldvalue='" + searchValue
					+ "' selectedColumn='" + columnName + "'>" + fieldValueLabel + "</div><div class='removeColumnDiv' columnname='" + columnName 
					+ "' fieldvalue='" + searchValue + "'></div><div class='clearDiv'></div></div>";
				//添加内容
				$("#allSelectedItemsDiv").append(appendStr);
				
				$(".removeColumnDiv[columnname='" + columnName + "']").click(function() {
					removeFieldFromPanel($(this), true);
				});
				
				$(".selectedField[columnname='" + columnName + "']").removeClass("selectedField");
				
				var fieldValue = searchValue;
				searchMap.put(columnName, searchValue);
			}
			
			//TODO 方框的一级子菜单 20150609 Start
			function addSubSchoolGradeField(countyData, searchItem, menuId){
				var searchFieldsArray = searchItem.searchFileds;
				var subFieldsContainerObj = $("div[subfieldscontainerid=" + searchItem.id + "]");
				var str  = searchItem.id + "_" + countyData[1];
				var subFieldsDivObj = $("<div class='hiddenSubField' subFiledDivId='" + str + "' style='margin-bottom:5px;'></div>");
				$(subFieldsContainerObj).append(subFieldsDivObj);
				var elementStr = "";
				 elementStr += "<div class='subFileAndKeyContainer'><div class='subFieldsKey'>"+countyData[0]+":</div><div class='subFiledKeyValueDiv' subContainerDiv='"
				               + str + "_' ></div>";
				for(var i = 0; i < searchFieldsArray.length; i++){
					elementStr += "<div class='subFieldItemDiv' menuId='" + menuId + "' parentColunmName='" + countyData[2] + "' parentFieldValue='" 
					+ countyData[1] + "'  superSubFieldDivId='" + str + "_" + i +"' fieldColumnName='" 
					+ searchItem.colunmName + "' fieldValue='" + searchFieldsArray[i].fieldValue + "'>" 
					+ searchFieldsArray[i].fieldName + "</div>";
					
					//TODO 在下一级的子菜单，根据显示学校名字进行搜索
					var  arrayObj = searchFieldsArray[i].subFiledsMap["_"];
					//创建一个容器
					var subSchoolFieldsDivObj = $("<div class='hiddenSubField' subParentSchoolGrade='" + str + "' subFiledDivId='" + str + "_" + i + "'></div>");
					$(subFieldsContainerObj).append(subSchoolFieldsDivObj);
					var divContentStr = "";
					divContentStr += "<div class='subFileAndKeyContainer'><div class='subFieldsKey'>"+ searchFieldsArray[i].fieldName +":</div><div class='subFiledKeyValueDiv' subContainerDiv='"
			               + str + "_" + i + "' ></div>";
					var schoolNumber = 0;
					if(arrayObj){
						for(var j = 0; j < arrayObj.length; j++){
//							根据学校所在的城区进行分类 
							if(arrayObj[j].schoolCountryId == countyData[1]){
							divContentStr += "<div class='subFieldItemDiv ' menuId='" + menuId + "' parentColunmName='" + searchItem.colunmName + "' parentFieldValue='" 
							+ searchFieldsArray[i].fieldValue + "'  superSubFieldDivId='" + str + "' fieldColumnName='" 
							+ arrayObj[j].fieldColumnName + "' fieldValue='" + arrayObj[j].fieldValue + "'>" 
							+ arrayObj[j].fieldName + "</div>";
							schoolNumber ++;
							}
						}
						if(schoolNumber == 0){
							divContentStr += "该片区暂未录入"+ searchFieldsArray[i].fieldName ;
						}
					}else{
						divContentStr += "该片区暂未录入"+ searchFieldsArray[i].fieldName ;
					}
					divContentStr += "<div class='clearDiv'></div></div>";
					$(subSchoolFieldsDivObj).append(divContentStr);
					$(subSchoolFieldsDivObj).append("<div class='clearDiv'></div>");
					$(subFieldsContainerObj).append("<div class='clearDiv'></div>");
				}
				elementStr += "<div class='clearDiv'></div></div>";
				$(subFieldsDivObj).append(elementStr);
				
//				$(subFieldsDivObj).append("<div class='clearDiv'></div>");
				$(subFieldsContainerObj).append("<div class='clearDiv'></div>");
			}
			//END
			//正常子菜单
			function addSubSearchField(searchField, searchItem, menuId) {
				var subFieldsContainerObj = $("div[subfieldscontainerid=" + searchItem.id + "]");
				var subFieldsDivObj = $("<div class='hiddenSubField' subFiledDivId='" + searchItem.id + "_" + searchField.fieldValue + "'></div>");
				$(subFieldsContainerObj).append(subFieldsDivObj);
				for (k in searchField.subFiledsMap){
					var elementStr = "";
					if (k != '' && k != '_'){
						elementStr += "<div class='subFileAndKeyContainer'><div class='subFieldsKey'>" + k + "</div><div class='subFiledKeyValueDiv' subContainerDiv='" 
							+ searchItem.id + "_" + searchField.fieldValue + "_" + k + "'></div>";
						elementStr += generatSubElementStr(searchField, searchItem, menuId);
						elementStr += "<div class='clearDiv'></div></div>";
					}else if (k == '_'){
						elementStr += "<div class='subFileAndKeyContainer'><div class='subFieldsKey'></div><div class='subFiledKeyValueDiv' subContainerDiv='" 
							+ searchItem.id + "_" + searchField.fieldValue + "_" + k + "'></div><div class='clearDiv'>";
						elementStr += generatSubElementStr(searchField, searchItem, menuId);
						elementStr += "</div></div>";
					}
					$(subFieldsDivObj).append(elementStr);
				}
				$(subFieldsDivObj).append("<div class='clearDiv'></div>");
				$(subFieldsContainerObj).append("<div class='clearDiv'></div>");
			}
			
			function generatSubElementStr(searchField, searchItem, menuId){
				var str = "";
				for (var i = 0; i < searchField.subFiledsMap[k].length; i++) {
					str += "<div class='subFieldItemDiv' menuId='" + menuId + "' parentColunmName='" + searchItem.colunmName + "' parentFieldValue='" 
						+ searchField.fieldValue + "'  superSubFieldDivId='" + searchItem.id + "_" + searchField.fieldValue + "' fieldColumnName='" 
						+ searchField.subFiledsMap[k][i].fieldColumnName + "' fieldValue='" + searchField.subFiledsMap[k][i].fieldValue + "'>" 
						+ searchField.subFiledsMap[k][i].fieldName + "</div>";
				}
				return str;
			}
			
			
			function showSelectedField() {
				searchInput = "";
				var postUrl = getAllSearchInfo();
				if (postUrl != previousUrl){
					postDataByURL2(optionDefault.url, postUrl, "changelist");
					anableButton();
					previousUrl = postUrl;
				}
				var selectedCounter = $("#allSelectedItemsDiv").find(".selectedSearchItemDiv").length;
				if (selectedCounter == 0){
					disableButton();
				}
			}

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
			
			function addSearchItemToMap(obj) {
				var columnName = $(obj).attr("columnName");
				var fieldValue = $(obj).attr("fieldValue");
				var putedFieldValue = fieldValue;
				var fieldValueArray = new Array();
				var sortedValue = new Array();
				if ($(obj).attr("ismulty") == 'true') {
					var valueStr = searchMap.get(columnName);
					if (valueStr != null) {
						if (valueStr.toString().indexOf(",") > 0) {
							var valueArray = valueStr.toString().split(",");
							if (valueArray.length > 0) {
								for (var jj = 0; jj < valueArray.length; jj++) {
									if (valueArray[jj] == fieldValue) {
										return;
									}
								}
							}
						} else {
							if (valueStr == fieldValue) {
								return;
							}
						}
						putedFieldValue = putedFieldValue + "," + valueStr;
					}
					if (putedFieldValue.indexOf(",") > 0) {
						fieldValueArray = putedFieldValue.split(",");
						sortedValue = sortBubble(fieldValueArray);
					} else {
						sortedValue[0] = putedFieldValue;
					}

				} else {
					sortedValue[0] = putedFieldValue;
				}

				searchMap.put(columnName, sortedValue);
				var valuea = searchMap.get(columnName);
				// 如果当前选中的对象的ismulty的值非真，那么，移除在选中面板中的相应DIV，否则，不移除
				if ($(obj).attr("ismulty") != 'true') {
					var selectedColumnObj = $("div[selectedColumn='" + columnName + "']");
					if ($(selectedColumnObj).length > 0) {
						$(selectedColumnObj).first().parent().remove();
					}
				} else {
					// 如果当前选中的对象的ismulty的值真
					var selectedColumnObj = $("div[selectedColumn='" + columnName + "'][fieldValue='" + fieldValue + "']");
					if ($(selectedColumnObj).length > 0) {
						$(selectedColumnObj).first().parent().remove();
					}
				}

				var isMultiSelect = $(obj).attr("ismulty");
				
				// 如果选的是含有searchUnlimited 类的对象，那么，清除所有和它同级的对象
				if (!$(obj).hasClass("searchUnlimited")) {
					if ($(obj).attr("hasSubValue") == 'true') {
						$("#allSelectedItemsDiv").append("<div class='selectedSearchItemDiv'><div class='columnDiv' fieldValue='" 
								+ fieldValue + "' selectedColumn='" + columnName + "'>" + $(obj).children().first().html() + "</div><div class='removeColumnDiv' isMulty='" 
								+ (isMultiSelect == 'true'? true: false) + "' columnname='" + columnName + "' fieldvalue='" + fieldValue + "'></div><div class='clearDiv'></div></div>");
					} else {
						if ($(obj).find("input").length > 0){
							$("#allSelectedItemsDiv").append("<div class='selectedSearchItemDiv'><div class='columnDiv' fieldValue='" + fieldValue 
									+ "' selectedColumn='" + columnName + "'>" + ($(obj).children().first().val() + $(obj).children().first().next().html()) 
									+ "</div><div class='removeColumnDiv' isMulty='" + (isMultiSelect == 'true'? true: false) + "' columnname='" 
									+ columnName + "' fieldvalue='" + fieldValue + "'></div><div class='clearDiv'></div></div>");
						}else{
							$("#allSelectedItemsDiv").append("<div class='selectedSearchItemDiv'><div class='columnDiv' fieldValue='" + fieldValue 
									+ "' selectedColumn='" + columnName + "'>" + $(obj).children().first().html() + "</div><div class='removeColumnDiv' isMulty='" 
									+ (isMultiSelect == 'true'? true: false) + "' columnname='" + columnName + "' fieldvalue='" 
									+ fieldValue + "'></div><div class='clearDiv'></div></div>");
						}
					}
				} else {
					if ($("div[selectedColumn='" + columnName + "']").length > 0) {
						$("div[selectedColumn='" + columnName + "']").each(function() {
							$(this).parent().remove();
						});
					}
				}
				$(".removeColumnDiv[columnname='" + columnName + "'][fieldvalue='" + fieldValue + "']").click(function() {
					removeFieldFromPanel($(this), true);
				});
			}
			
			function removeFieldFromPanel(obj, postFlag){
				columnName = $(obj).attr("columnName");
				fieldValue = $(obj).attr("fieldValue");
				var isMulty = $(obj).attr("ismulty");
				var selectedItem = $(".selectedField[columnName='" + columnName + "'][fieldValue='" + fieldValue + "']");
				if ($(selectedItem).length > 0){
					if ($(selectedItem).attr("hasSubValue") == 'true') {
						$(selectedItem).children().first().next().next().css("display", "none");
						$(".displaySubField").addClass("hiddenSubField");
						$(".displaySubField").removeClass("displaySubField");
						$(".displaySubField_xq").addClass("hiddenSubField");
						$(".displaySubField_xq").removeClass("displaySubField_xq");
					}
					$(selectedItem).first().removeClass("selectedField");
				}
				
				var inputItem = $("input[columnname='" + columnName + "']");
				
				if ($(inputItem).length > 0){
					$(inputItem).each(function(index){
						$(this).val("");
					});
				}
				
				$(obj).parent().remove();
				if (isMulty === 'true'){
					var currentValue = searchMap.get(columnName);
					if (currentValue.length === 1){
						searchMap.remove(columnName);
						initSelectedItem();
						$(".searchUnlimited[columnName='" + columnName + "']").addClass("selectedField");
					}else{
						for (var i = 0; i < currentValue.length; i++){
							if (currentValue[i] == fieldValue){
								currentValue.splice(i,1);
								break;
							}
						}
						searchMap.put(columnName, currentValue);
					}
				}else{
					searchMap.remove(columnName);
					initSelectedItem();
					$(".searchUnlimited[columnName='" + columnName + "']").addClass("selectedField");
				}
				if (postFlag === true){
					showSelectedField();
				}
			}
			
			//从map中将一个键值对删除
			function removeSearchItemFromMap(obj) {
				columnName = $(obj).attr("columnName");
				searchMap.remove(columnName);
				initSelectedItem();
			}
			
			//点击清除按钮时
			$("#clearContainer").click(function() {
				$(".subSelectedField").each(function() {
					$(this).removeClass("subSelectedField");
				});
				$(".selectedField").each(function() {
					$(this).removeClass("selectedField");
				});
				$("#allSelectedItemsDiv>div").remove();
				$("input[columnName]").each(function() {
					$(this).val("");
				});
				$(".displaySubField").addClass("hiddenSubField");
				$(".displaySubField").removeClass("displaySubField");
				
				$(".displaySubField_xq").addClass("hiddenSubField");
				$(".displaySubField_xq").removeClass("displaySubField_xq");
				
				$(".searchField img").css("display", "none");
				$(".searchUnlimited").addClass("selectedField");
				clearSearchMap();
				searchMap.put("ispage", 1);
				disableButton();
				$("#sharePanel").css("display", "none");
				$("#allSelectedItemsDiv").html("&nbsp;");
				showSelectedField();
			});

			$(".toSearchIcon").click(function() {
				var columnName = $(this).attr("columnName");
				//值等于医院时 另算
				if(columnName == "ddhb_one_hospital.hospitalName"){
					var _searchValue = "";
					$("input[columnName='" + columnName + "']").each(function() {
						if ($(this).hasClass("inputElem")) {
							_searchValue = $(this).val();
						}
					});
					searchMap.put(columnName, _searchValue);
				}else{
				var maxValue = "";
				var minValue = "";
				$("input[columnName='" + columnName + "']").each(function() {
					if ($(this).hasClass("minValue")) {
						minValue = $(this).val();
					}
					if ($(this).hasClass("maxValue")) {
						maxValue = $(this).val();
					}
				});
				maxValue = parseFloat(maxValue);
				minValue = parseFloat(minValue);
				var searchValue = "";
				if (maxValue > minValue) {
					searchValue = minValue + "@" + maxValue;
				} else {
					searchValue = maxValue + "@" + minValue;
				}
				searchMap.put(columnName, searchValue);
				}
				showSelectedField();
			});

//			$("input[columnName]").blur(function() {
//				if (isNaN($(this).val())) {
//					alert("请输入数字！");
//					$(this).focus();
//					return;
//				}
//			});

			function clearPrivateInput(searchItemId) {
				$("input[columnName]").each(function() {
					if (privateItemMap.get($(this).attr("columnName")) == searchItemId) {
						$(this).val("");
					}
				});
			}

			function clearSearchMap() {
				var keys = searchMap.keys();
				for (var jj = 0; jj < keys.length; jj++) {
					if (keys[jj] != "type" && keys[jj] != "order" && keys[jj] != "sort" && keys[jj] != "ispage") {
						searchMap.remove(keys[jj]);
						initSelectedItem();
					}
				}
			}

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

			function initRemovePanel(key, content) {
				$("#allSelectedItemsDiv").append("<div class='selectedSearchItemDiv'><div class='columnDiv' fieldvalue='" 
						+ searchMap.get(key) + "' selectedColumn='" + key + "'>" + content + "</div><div class='removeColumnDiv' columnname='" 
						+ key + "' fieldvalue='" + searchMap.get(key) + "'></div><div class='clearDiv'></div></div>");
				$(".removeColumnDiv[columnname='" + key + "'][fieldvalue='" + searchMap.get(key) + "']").click(function() {
					searchMap.put("ispage", 1);
					addRemoveColumnEvent($(this));
					$(".searchUnlimited[columnname='" + key + "']").addClass("selectedField");
				});
			}

			function addRemoveColumnEvent(obj) {
				columnName = $(obj).attr("columnName");
				fieldValue = $(obj).attr("fieldValue");
				var itemColumn = $(".selectedField[columnName='" + columnName + "'][fieldValue='" + fieldValue + "']");
				$(itemColumn).removeClass("selectedField");
				$(itemColumn).children().first().next().next().css("display", 'none');
				var fieldsContentId = $(item).parent().attr("fieldsContentId");
				var fieldsContentIdI = fieldsContentId + "_" + fieldValue;
				if(fieldsContentId == "44" || fieldsContentId == "25" ){
					var subContainer = $(".displaySubField_xq[subFiledDivId='" + fieldsContentIdI + "']");
					$(subContainer).addClass("hiddenSubField");
					$(subContainer).removeClass("displaySubField_xq");
				}else{
					var subContainer = $(".displaySubField[subFiledDivId='" + fieldsContentIdI + "']");
					$(subContainer).addClass("hiddenSubField");
					$(subContainer).removeClass("displaySubField");
				}
				$(".subSelectedField[fieldColumnName='" + columnName + "'][fieldValue='" + fieldValue + "']").first().removeClass("subSelectedField");
				$(obj).parent().remove();
				searchMap.remove(columnName);
				//移除子项
				var subSelectedItem = $(".removeColumnDiv[parentcolumn='" + columnName + "']");
				if (subSelectedItem.length > 0){
					var subCulumnName = $(subSelectedItem).attr("columnname");
					searchMap.remove(subCulumnName);
					$(subSelectedItem).parent().remove();
				}
				
				if ($(itemColumn).length == 0){
					var items = $(".searchField[columnname='" + columnName + "']");
					if ($(items).length > 0){
						var firstItem = $(items).first().next();
						if ($(firstItem).attr("isrange") == 'true'){
							$("input[columnname='" + columnName + "']").val("");
							$(".searchUnlimited[columnname='" + columnName + "']").addClass("selectedField");
						}
					}
				}
				
				initSelectedItem();
				showSelectedField();
			}

			$("#shareContainer").click(function() {
				if ($(this).attr("diableFlag") == 'false') {
					return;
				}
				$("#sharePanel").css("display", "block");
			});

			$("#saveContainer").click(function() {
				if ($(this).attr("diableFlag") == 'false') {
					return;
				}
				var savedString = "";
				$(".columnDiv").each(function() {
					savedString += $(this).html();
					savedString += ",";
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
					url : globalUrl + "saveSearchField.show?actionMethod=saveSearchItem",
					data : dataString,
					dataType: "json",
					success : function(data) {
						var resultJson = data;
						if (resultJson.resultCode == '3'){
							alert("请登录后再保存");
							return;
						}else if (resultJson.resultCode == '2'){
							alert("您已经保存过该搜索条件了");
							return;
						}else if (resultJson.resultCode == '1'){
							alert("保存成功，您可以进入个人中心查看");
							var saveContainer = $("#saveContainer");
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

			function anableButton() {
				if ($("#allSelectedItemsDiv").children().length > 0) {
					$(".disabledClearIcon").addClass("clearIcon");
					$(".disabledClearIcon").removeClass("disabledClearIcon");
					$(".disabledShareIcon").addClass("shareIcon");
					$(".disabledShareIcon").removeClass("disabledShareIcon");
					$(".disabledSaveIcon").addClass("saveIcon");
					$(".disabledSaveIcon").removeClass("disabledSaveIcon");
					$(".operationItemContainer").addClass("anabledOperation");
					$(".operationItemContainer").removeClass("disableOperation");
					$(".operationItemContainer").attr("diableFlag", "true");
				}
			}

			function disableButton() {
				$(".clearIcon").addClass("disabledClearIcon");
				$(".clearIcon").removeClass("clearIcon");
				$(".shareIcon").addClass("disabledShareIcon");
				$(".shareIcon").removeClass("shareIcon");
				$(".saveIcon").addClass("disabledSaveIcon");
				$(".saveIcon").removeClass("saveIcon");
				$(".operationItemContainer").addClass("disableOperation");
				$(".operationItemContainer").removeClass("anabledOperation");
				$(".operationItemContainer").attr("diableFlag", "false");
			}
			
			function initSelectedItem(){
				if ($("#allSelectedItemsDiv").html() == ""){
					$("#allSelectedItemsDiv").html("&nbsp;");
				}
			}
			
			function addSearchSelectOption(searchItem, menuId){
				var selectId = "#" + searchItem.id;
				var searchFieldsArray = searchItem.searchFileds;
				for(var i = 0; i < searchFieldsArray.length; i++){
					if(searchItem.isRange == 1){
						$(selectId).append("<option value='"+ searchFieldsArray[i].minFieldValue + "@" + searchFieldsArray[i].maxFieldValue
								+"'>"+ searchFieldsArray[i].fieldName
								+"</option>");
					}else{
						$(selectId).append("<option value='"+ searchFieldsArray[i].fieldValue
														+"'>"+ searchFieldsArray[i].fieldName
														+"</option>");
				}
			}
			}
			//以下是输入医院名字关键字后提示下拉框
			 function EmailAutoComplete(options) {
					
					this.config = {
						targetCls      :  '.inputElem',       		// 目标input元素
						parentCls      :  '.rangeContentDiv',       // 当前input元素的父级类
						hiddenCls      :  '.hiddenCls',       		// 当前input隐藏域 
						searchForm     :  '.jqtransformdone', 		//form表单
						hoverBg        :  'hoverBg',        	  	// 鼠标移上去的背景
						inputValColor  :  'black',              	// 输入框输入提示颜色
						hospital        : hospital,					//数组	
						isSelectHide   : true,                		// 点击下拉框 是否隐藏 默认为true
						callback       : null                 		// 点击某一项回调函数
					};
					this.cache = {
						onlyFlag            : true,     // 只渲染一次
						currentIndex        : -1,
				        oldIndex            : -1
					};
					
					this.init(options);
				 }
			 EmailAutoComplete.prototype = {
						
						constructor: EmailAutoComplete,

						init: function(options){
							this.config = $.extend(this.config,options || {});

							var self = this,
								_config = self.config,
								_cache = self.cache;
							
							$(_config.targetCls).each(function(index,item){
								
								$(item).keyup(function(e){
									var target = e.target,
										targetVal = $.trim($(this).val()),
										keycode = e.keyCode,
										elemHeight = $(this).outerHeight(),
										elemWidth = $(this).outerWidth(),
										parentNode = $(this).closest(_config.parentCls);
									
									$(parentNode).css({'position':'relative'});
									// 如果输入框值为空的话 那么下拉框隐藏
									if(targetVal == '') {
										$(item).attr({'data-html':''});
										// 给隐藏域赋值
										$(_config.hiddenCls,parentNode).val('');

										_cache.currentIndex = -1;
										_cache.oldIndex = -1;
										$(".auto-tip",parentNode) && !$(".auto-tip",parentNode).hasClass('hidden') && $(".auto-tip",parentNode).addClass('hidden');
										self._removeBg(parentNode);
									}else {
										
										$(item).attr({'data-html':targetVal});

										// 给隐藏域赋值
										$(_config.hiddenCls,parentNode).val(targetVal);
										
										$(".auto-tip",parentNode) && $(".auto-tip",parentNode).hasClass('hidden') && $(".auto-tip",parentNode).removeClass('hidden');
										// 渲染下拉框内容
										self._renderHTML({keycode:keycode,e:e,target:target,targetVal:targetVal,height:elemHeight,width:elemWidth,parentNode:parentNode});
									}
									
									
								});
							});
							
						   // 阻止form表单默认enter键提交
						   $(_config.searchForm).each(function(index,item) {
								$(item).keydown(function(e){
									 var keyCode = e.keyCode;
									 if(keyCode == 13) {
										 return false;
									 }
								});
						   });

						   // 点击文档document时候 下拉框隐藏掉
						   $(document).click(function(e){
							  e.stopPropagation();
							  var target = e.target,
								  tagCls = _config.targetCls.replace(/^\./,'');

							  if(!$(target).hasClass(tagCls)) {
								 $('.auto-tip') && $('.auto-tip').each(function(index,item){
									 !$(item).hasClass('hidden') && $(item).addClass('hidden');
								 });
							  }
						   });
						},
						/*
						 * 渲染下拉框提示内容
						 * @param cfg{object}
						 */
						_renderHTML: function(cfg) {
							var self = this,
								_config = self.config,
								_cache = self.cache,
								curVal;
							var curIndex = self._keyCode(cfg.keycode);
							
							$('.auto-tip',cfg.parentNode).hasClass('hidden') && $('.auto-tip',cfg.parentNode).removeClass('hidden');
							if(curIndex > -1){
								// 键盘上下操作
								self._keyUpAndDown(cfg.targetVal,cfg.e,cfg.parentNode);
							}else {
								curVal = cfg.targetVal;
								if(_cache.onlyFlag) {
									$(cfg.parentNode).append('<input type="hidden" class="hiddenCls"/>');
									var wrap = '<ul class="auto-tip" style="text-align:left;">';

									for(var i = 0; i < _config.hospital.length; i++) {
										//列出所有的医院名字
											wrap += '<li class="p-index'+i+'" style="background:#FFFFFF;">'+'<span class="output-num"></span><em class="em" style="font-style: normal;" data-html="'+_config.hospital[i]+'">'+_config.hospital[i]+'</em></li>';
									}
									wrap += '</ul>';
									_cache.onlyFlag = false;
									$(cfg.parentNode).append(wrap);
									$('.auto-tip',cfg.parentNode).css({'position':'absolute','top':cfg.height,'width':cfg.width - 2 + 'px','left':0,
										'border':'1px solid #ccc','z-index':10000});
								}
								
								// 给所有li添加属性 data-html
								$('.auto-tip li',cfg.parentNode).each(function(index,item){
									//$('.output-num',item).html(curVal);
									!$('.output-num',item).hasClass(_config.inputValColor) && 
									$('.output-num',item).addClass(_config.inputValColor);
									var emVal = $.trim($('.em',item).attr('data-html'));
//									$(item).attr({'data-html':curVal + '' +emVal});
									$(item).attr({'data-html': emVal});
								});

								// 精确匹配内容
								self._accurateMate({target:cfg.target,parentNode:cfg.parentNode});

								// 鼠标移到某一项li上面时候
								self._itemHover(cfg.parentNode);
								
								// 点击对应的项时
								self._executeClick(cfg.parentNode);
							}
							
						},
						/**
						 * 精确匹配某项内容
						 */
						_accurateMate: function(cfg) {
							var self = this,
								_config = self.config,
								_cache = self.cache;
							
							var curVal = $.trim($(cfg.target,cfg.parentNode).attr('data-html')),
								newArrs = [];
								
							//把符合关键字的医院显示
								var i = 0;
								$.map(_config.hospital,function(n){
									var reg = curVal;
									if(n.indexOf(reg) != -1) {
										i++;
										if(i <= 4){
										newArrs.push(n);
										}
									}
								});
								if(newArrs.length > 0) {
									$('.auto-tip',cfg.parentNode).html('');
									$(".auto-tip",cfg.parentNode) && $(".auto-tip",cfg.parentNode).hasClass('hidden') && 
									$(".auto-tip",cfg.parentNode).removeClass('hidden');

									var html = '';
									for(var j = 0, jlen = newArrs.length; j < jlen; j++) {
										html += '<li class="p-index'+j+'" style="background:#FFFFFF;font-family:宋体;Font-style:normal;">'+'<span class="output-num"></span><em class="em" style="font-style: normal;" data-html="'+newArrs[j]+'">'+newArrs[j]+'</em></li>';
									}
									$('.auto-tip',cfg.parentNode).html(html);
									
									// 给所有li添加属性 data-html
									$('.auto-tip li',cfg.parentNode).each(function(index,item){
										//$('.output-num',item).html(prefix);
										!$('.output-num',item).hasClass(_config.inputValColor) && 
										$('.output-num',item).addClass(_config.inputValColor);

										var emVal = $.trim($('.em',item).attr('data-html'));
										
										$(item).attr('data-html','');
										$(item).attr({'data-html': emVal});
									});

									// 精确匹配到某项时候 让当前的索引等于初始值
									_cache.currentIndex = -1;
									_cache.oldIndex = -1;
									
									//可在下拉框中显示搜索的关键字
									//$('.auto-tip .output-num',cfg.parentNode).html(prefix);

									// 鼠标移到某一项li上面时候
									self._itemHover(cfg.parentNode);

									// 点击对应的项时
									self._executeClick(cfg.parentNode);
								}else {
									$(".auto-tip",cfg.parentNode) && !$(".auto-tip",cfg.parentNode).hasClass('hidden') && 
									$(".auto-tip",cfg.parentNode).addClass('hidden');
									$('.auto-tip',cfg.parentNode).html('');
								}
							
						},
						/*
						 * 鼠标移到某一项li上时
						 */
						_itemHover: function(parentNode) {
							var self = this,
								_config = self.config,
								_cache = self.cache;
							$('.auto-tip li',parentNode).hover(function(index,item) {
								!$(this).hasClass(_config.hoverBg) && $(this).addClass(_config.hoverBg);
							},function() {
								$(this).hasClass(_config.hoverBg) && $(this).removeClass(_config.hoverBg);
							});
						},
						/*
						 * 当输入框值为空时候 li项都删掉class hoverBg
						 */
						_removeBg: function(parentNode){
							var self = this,
								_config = self.config;

							$(".auto-tip li",parentNode).each(function(index,item){
								$(item).hasClass(_config.hoverBg) && $(item).removeClass(_config.hoverBg);
							});	
						},
						/**
					     * 键盘上下键操作
					     */
						 _keyUpAndDown: function(targetVal,e,parentNode) {
							var self = this,
								_cache = self.cache,
								_config = self.config;

							// 如果请求成功后 返回了数据(根据元素的长度来判断) 执行以下操作
							if($('.auto-tip' + ' li',parentNode) && $('.auto-tip' + ' li').length > 0) {

								var plen = $('.auto-tip' + ' li',parentNode).length,
									keyCode = e.keyCode;
									_cache.oldIndex = _cache.currentIndex;
								

								// 上移操作
								if(keyCode == 38) {
									if(_cache.currentIndex == -1) {
										_cache.currentIndex = plen - 1;
									}else {
										_cache.currentIndex = _cache.currentIndex - 1;
										if(_cache.currentIndex < 0) {
											_cache.currentIndex = plen - 1;
										}
									}
									if(_cache.currentIndex !== -1) {
										

										!$('.auto-tip .p-index'+_cache.currentIndex,parentNode).hasClass(_config.hoverBg) &&
					                    $('.auto-tip .p-index'+_cache.currentIndex,parentNode).addClass(_config.hoverBg).siblings().removeClass(_config.hoverBg);

										var curAttr = $('.auto-tip' + ' .p-index'+_cache.currentIndex,parentNode).attr('data-html');
										$(_config.targetCls,parentNode).val(curAttr);
										
										// 给隐藏域赋值
										$(_config.hiddenCls,parentNode).val(curAttr);
									}

								}else if(keyCode == 40) { //下移操作
									if(_cache.currentIndex == plen - 1) {
										_cache.currentIndex = 0;
									}else {
										_cache.currentIndex++;
										if(_cache.currentIndex > plen - 1) {
											_cache.currentIndex = 0;
										}
									}

									if(_cache.currentIndex !== -1) {
										
										!$('.auto-tip .p-index'+_cache.currentIndex,parentNode).hasClass(_config.hoverBg) &&
					                    $('.auto-tip .p-index'+_cache.currentIndex,parentNode).addClass(_config.hoverBg).siblings().removeClass(_config.hoverBg);
										
										var curAttr = $('.auto-tip' + ' .p-index'+_cache.currentIndex,parentNode).attr('data-html');
										$(_config.targetCls,parentNode).val(curAttr);
										// 给隐藏域赋值
										$(_config.hiddenCls,parentNode).val(curAttr);
									}
									
								}else if(keyCode == 13) { //回车操作
									var curVal = $('.auto-tip' + ' .p-index'+_cache.oldIndex,parentNode).attr('data-html');
									$(_config.targetCls,parentNode).val(curVal);
									
									// 给隐藏域赋值
									$(_config.hiddenCls,parentNode).val(curVal);

									if(_config.isSelectHide) {
										 !$(".auto-tip",parentNode).hasClass('hidden') && $(".auto-tip",parentNode).addClass('hidden');
									 }
									 _config.callback && $.isFunction(_config.callback) && _config.callback();

									_cache.currentIndex = -1;
									_cache.oldIndex = -1;
									
								}
							}
						 },
						 _keyCode: function(code) {
					         var arrs = ['17','18','38','40','37','39','33','34','35','46','36','13','45','44','145','19','20','9'];
					         for(var i = 0, ilen = arrs.length; i < ilen; i++) {
					             if(code == arrs[i]) {
					                 return i;
					             }
					         }
					         return -1;
					     },
						/**
						  * 当数据相同的时 点击对应的项时 返回数据
						  */
						 _executeClick: function(parentNode) {
							
							 var _self = this,
								 _config = _self.config;

							 $('.auto-tip' + ' li',parentNode).unbind('click');
							 $('.auto-tip' + ' li',parentNode).bind('click',function(e){
								  var dataAttr = $(this).attr('data-html');

								  $(_config.targetCls,parentNode).val(dataAttr);
								  if(_config.isSelectHide) {
									  !$(".auto-tip",parentNode).hasClass('hidden') && $(".auto-tip",parentNode).addClass('hidden');
								  }
								  // 给隐藏域赋值
								  $(_config.hiddenCls,parentNode).val(dataAttr);
								  _config.callback && $.isFunction(_config.callback) && _config.callback();
								  
							 });
						 }
					};
			// 初始化
			 $(function() {
			 	new EmailAutoComplete({});
			 });
		}
	});
})(jQuery);