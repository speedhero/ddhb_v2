/**
 * Author:		Vincent Yang
 * Date:		2014-06-01
 * Description:	This jQuery plug-in is used to create a way to retrieve information
 */
;(function($){
    $.fn.extend({
        "createSearch":function(option){
        	var searchMap = new Map();
        	optionDefault={
    			searchItems:{},
    			url:'',
    			searchMap:searchMap,
    			displayHiddenBar:true
        	};
        	
        	currentOptions=$.extend(optionDefault, option);
        	searItems = currentOptions.searchItems;
        	searchMap = currentOptions.searchMap;
        	
        	var subItems = searItems[0].subItems;
        	
        	$(this).append("<div id='searchContent'><div id='cityDiv'></div><div id='countyDiv'></div><div id='decorationDiv'></div></div>");
        	$(this).append("<div id='selectContent' style='background-color:#ffffff;'></div>");
        	
        	for (var i = 0; i < subItems.length; i++){
        		if(subItems[i].hasSubValue == 1){
        			$("#cityDiv").append("<div class='itemLine'><div class='searchLabelContainer'><div class='searchLabel' style='width:auto; padding-right:5px;' searchItemId='" + subItems[i].id + "'>" + subItems[i].searchLabel + ":</div><div class='clearDiv'></div></div><div class='searchFieldContentDiv' fieldsContentId='" + subItems[i].id + "'></div><div class='clearDiv'></div></div>");
        		}else{
        			$("#decorationDiv").append("<div class='itemLine'><div class='searchLabelContainer'><div class='searchLabel' style='width:auto; padding-right:5px;' searchItemId='" + subItems[i].id + "'>" + subItems[i].searchLabel + ":</div><div class='clearDiv'></div></div><div class='searchFieldContentDiv' fieldsContentId='" + subItems[i].id + "'></div><div class='clearDiv'></div></div>");
        		}
    			addSearchField(subItems[i]);
        	}
        	
        	//add search field
            function addSearchField(searchItem){
            	var searchFieldsArray = searchItem.searchFileds;
            	$("div[fieldscontentid=" + searchItem.id + "]").append("<div class='searchField searchUnlimited selectedField' hasSubValue='true' columnName='" + searchItem.colunmName + "'><span>不限</span></div>");
            	for (var i = 0; i < searchFieldsArray.length; i++){
					$("div[fieldscontentid=" + searchItem.id + "]").append("<div class='searchField' hasSubValue='" + (searchItem.hasSubValue == 1? true : false) + "' columnName='" + searchItem.colunmName + "' fieldValue='" + searchFieldsArray[i].fieldValue + "'><span>" + searchFieldsArray[i].fieldName + "</span></div>");
					if (searchItem.hasSubValue == '1'){
						addSubField(searchFieldsArray[i]);
					}
            	}
            	$("div[fieldscontentid=" + searchItem.id + "]").append("<div class='clearDiv'></div>");
            }
            
            /**
             * 增加子项
             */
            function addSubField(searchField, searchItem){
            	$("#cityDiv").append("<div id='city_" + searchField.fieldValue + "' class='hiddenThisLine'><div class='itemLine'><div class='searchLabelContainer'><div class='searchLabel' style='width:auto; padding-right:5px;'>城区:</div><div class='clearDiv'></div></div><div class='searchFieldContentDiv' id='fc_" + searchField.fieldValue + "'></div><div class='clearDiv'></div></div></div>");
            	var contentStr = '';
            	var columnName = "";
            	var containerF = $("#fc_" + searchField.fieldValue);
            	for (k in searchField.subFiledsMap){
            		for (var i = 0; i < searchField.subFiledsMap[k].length; i++){
            			if (columnName == ""){
            				columnName = searchField.subFiledsMap[k][i].fieldColumnName;
            			}
            			contentStr += "<div class='searchField' columnName='" + columnName + "' fieldValue='" + searchField.subFiledsMap[k][i].fieldValue + "'><span>" + searchField.subFiledsMap[k][i].fieldName + "</span></div>"
            		}
				}
            	var defaultItem = "<div class='searchField searchUnlimited selectedField' columnName='" + columnName + "'><span>不限</span></div>";
            	contentStr = defaultItem + contentStr;
            	$(containerF).append(contentStr);
            }
            
            $(".searchField").click(function(){
            	var item = $(this);
            	//如果某项有子项，那么去掉当前显示的子项，显示该项的子项
            	if ($(item).attr("hassubvalue") == 'true'){
            		var currentDisplayLine = $(".displayThisLine");
            		$(currentDisplayLine).removeClass("displayThisLine");
            		$(currentDisplayLine).addClass("hiddenThisLine");
            		//移除该div下的所有选中项
            		var columnName = $(currentDisplayLine).find(".selectedField").attr("columnname");
            		searchMap.remove(columnName);
            		$(currentDisplayLine).find(".selectedField").removeClass("selectedField");
            		var showDisplayLine = $("#city_" + $(item).attr("fieldvalue"));
            		$(showDisplayLine).removeClass("hiddenThisLine");
            		$(showDisplayLine).addClass("displayThisLine");
            	}
            	
            	var currentSearchField = $(item).parent().find(".selectedField");
            	$(currentSearchField).removeClass("selectedField");
            	var currentSelectedColumn = $(currentSearchField).attr("columnname");
            	searchMap.remove(currentSelectedColumn);
            	$(item).addClass("selectedField");
            	var newColumn = $(item).attr("columnname");
            	var newValue = $(item).attr("fieldvalue");
            	if (typeof(newValue) == 'undefined'){
            		searchMap.remove(newColumn);
            	}else{
            		searchMap.put(newColumn, newValue);
            	}
            	showSelectedField();
            });
            
            String.prototype.trim = function()  
            {  
                return this.replace(/(^\s*)|(\s*$)/g, "");  
            };
            
            function removeSearchItemFromMap(obj){
            	columnName = $(obj).attr("columnName");
        		searchMap.remove(columnName);
            }
            
            function showSelectedField(){
            	var keyArray = searchMap.keys();
            	if (keyArray.length > 0){
            		var postUrl = "";
            		for (var i = 0; i < keyArray.length; i++){
            			postUrl += keyArray[i] + "=" + searchMap.get(keyArray[i]);
            			if (i < keyArray.length - 1){
            				postUrl += "&";
            			}
            			if(typeof(jsonParams) != "undefined") {
            				setJsonParam(keyArray[i], searchMap.get(keyArray[i]));
            			}
            		}
            	}
            	postDataByURL2(optionDefault.url, postUrl, "changelist");
            }
        }   
    }); 
})(jQuery);