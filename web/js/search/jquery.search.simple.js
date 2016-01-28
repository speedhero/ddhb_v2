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
        	
        	$(this).append("<div id='searchContent'></div>");
        	$(this).append("<div id='selectContent' style='background-color:#ffffff;'></div>");
        	
        	for (var i = 0; i < subItems.length; i++){
        		if (subItems[i].ismulty == '333'){
        			$("#selectContent").append("<div class='customerContainer' containerId='" + subItems[i].id + "'></div>");
        			addSelectedItem(subItems[i], subItems[i].id);
        		}else{
        			$("#searchContent").append("<div class='itemLine'><div class='searchLabelContainer'><div class='searchLabel' style='width:auto; padding-right:5px;' searchItemId='" + subItems[i].id + "'>" + subItems[i].searchLabel + ":</div><div class='clearDiv'></div></div><div class='searchFieldContentDiv' fieldsContentId='" + subItems[i].id + "'></div><div class='clearDiv'></div></div>");
        			addSearchField(subItems[i]);
        		}
        	}
        	$("#selectContent").append("<div class='clearDiv'></div>");
        	
        	
        	//add search field
            function addSearchField(searchItem){
            	var searchFieldsArray = searchItem.searchFileds;
            	$("div[fieldscontentid=" + searchItem.id + "]").append("<div class='searchField searchUnlimited selectedField' hasSubValue='true' columnName='" + searchItem.colunmName + "'><span>不限</span></div>");
            	for (var i = 0; i < searchFieldsArray.length; i++){
        			if (searchItem.isRange == 1){
    					$("div[fieldscontentid=" + searchItem.id + "]").append("<div class='searchField' columnName='" + searchItem.colunmName + "' fieldValue='" + 
    							searchFieldsArray[i].minFieldValue + "@" + searchFieldsArray[i].maxFieldValue
    							+ "'><span>" + searchFieldsArray[i].fieldName + "</span></div>");
        			}else{
    					$("div[fieldscontentid=" + searchItem.id + "]").append("<div class='searchField' ismulty='false' columnName='" + searchItem.colunmName + "' fieldValue='" + searchFieldsArray[i].fieldValue + "'><span>" + searchFieldsArray[i].fieldName + "</span></div>");
        			}
            	}
            	if (searchItem.isNeedInput == 1){
        			$("div[fieldscontentid=" + searchItem.id + "]").append("<div class='inputDiv' ><div class='rangeContentDiv'><input type='text' privateInput='true' columnName='" + searchItem.colunmName + "' class='minValue'/>一<input type='text' privateInput='true' columnName='" + searchItem.colunmName + "' class='maxValue'/><span>" + searchItem.unitName + "</span></div><div class='toSearchIcon' columnName='" + searchItem.colunmName + "'></div></div>");
        		}
            	$("div[fieldscontentid=" + searchItem.id + "]").append("<div class='clearDiv'></div>");
            }
            
            function addSelectedItem(searchItem, objectId){
            	var selectContainer = $("div[containerId='" + objectId + "']");
            	
            	var customSelectEle = $("<div class='customSelect'></div>");
            	if (searchItem.searchLabel.length > 2){
            		customSelectEle = $("<div class='customSelect' style='width:171px'></div>");
            	}
            	
				var labelSpan = $("<span class='label'>" + searchItem.searchLabel + ":</span>");
				$(customSelectEle).append(labelSpan);
				var searchXLEle = $("<div class='searchXL'></div>");
				$(customSelectEle).append(searchXLEle);
				var csEle = $("<div class='cs'></div>");
				$(searchXLEle).append(csEle);
				var defaultSelect = $("<div class='csc' selectedvalue='' columnname='" + searchItem.colunmName + "'>不限</div>");
				$(csEle).append(defaultSelect);
				var iconDiv = $("<div class='xljt'><img src='images/search/selectXL.png'></div>");
				$(csEle).append(iconDiv);
				var xlnrEle = $("<div class='xlnr'></div>");
				$(csEle).append(xlnrEle);
				
				var optionStr = "<a optionvalue='' class='unlimit'>不限</a>";
				for (var i = 0; i < searchItem.searchFileds.length; i++){
					optionStr += "<a optionvalue='" + searchItem.searchFileds[i].fieldValue + "'>" + searchItem.searchFileds[i].fieldName + "</a>";
				}
				$(xlnrEle).append(optionStr);
				$(selectContainer).append(customSelectEle);
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
			
			//下拉列表框选中的触发
			$(".xlnr a").click(function(){
				optionClicked($(this));
            });
			
			//当第一级下拉列表框有新的选中时，改变第二级下拉列表框的值
			function optionClicked(obj){
				$(obj).parent().parent().find(".csc").html($(obj).html());
                $(obj).parent().css('display','none');
                var currentSelect = $(obj).parent().parent().parent().parent();
                var optionValue = $(obj).attr("optionValue");
                var valueContainer = (currentSelect).find(".csc").first();
                $(valueContainer).attr("selectedValue", optionValue);
                var columnName = $(valueContainer).attr("columnname");
                var columnValue = $(valueContainer).attr("selectedvalue");
                if ($(obj).hasClass("unlimit")){
                	searchMap.remove(columnName);
                }else{
                	searchMap.put(columnName, columnValue);
                }
              //生成最终请求的url
                showSelectedField();
			}
            
            $(".searchField").click(function(){
            	var fieldscontentid = $(this).parent().attr("fieldscontentid");
            	var selectedSearchField = $(this);
            	$(".selectedField").each(function(index){
            		if ($(this).parent().attr("fieldscontentid") == fieldscontentid){
        				$(this).removeClass("selectedField");
        				removeSearchItemFromMap($(this));
            		}
            	});
            	
            	addSearchItemToMap($(this));
            	$(this).addClass("selectedField");
            	showSelectedField();
            });
            
            function addSearchItemToMap(obj){
            	var columnName = $(obj).attr("columnName");
            	var fieldValue = $(obj).attr("fieldValue");
            	
            	//如果选的是含有searchUnlimited 类的对象，那么，清除所有和它同级的对象
            	if (!$(obj).hasClass("searchUnlimited")){
            		searchMap.put(columnName, fieldValue);
            	}else{
            		if ($("div[selectedColumn='" + columnName + "']").length > 0){
                		$("div[selectedColumn='" + columnName + "']").each(function(){
                			$(this).parent().remove();
                		});
                	}
            		searchMap.remove(columnName);
            		
            	}
            	$("input[columnName='" + columnName + "']").each(function(){
            		$(this).val("");
            	});
            }
            
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
            
            $(".toSearchIcon").click(function() {
				var columnName = $(this).attr("columnName");
				var maxValue = "";
				var minValue = "";
				var returnFlag = false;
				$("input[columnName='" + columnName + "']").each(function() {
					if ($(this).hasClass("minValue")) {
						minValue = $(this).val();
						if (isNaN(minValue) || minValue.trim() == '') {
							alert("请输入数字！");
							returnFlag = true;
							return;
						}
						if ($(this).attr("columnname") == 'ddhb_two_price'){
							minValue = parseFloat(minValue) * 10000;
						}
					}
					if (returnFlag){
						return;
					}
					if ($(this).hasClass("maxValue")) {
						maxValue = $(this).val();
						if (isNaN(maxValue)  || maxValue.trim() == '') {
							alert("请输入数字！");
							returnFlag = true;
							return;
						}
						if ($(this).attr("columnname") == 'ddhb_two_price'){
							maxValue = parseFloat(maxValue) * 10000;
						}
					}
				});
				
				if (returnFlag){
					return;
				}
				
				var previousItem = $(".selectedField[columnname='" + columnName + "']");
				if ($(previousItem).length > 0){
					$(previousItem).removeClass("selectedField");
					searchMap.remove(columnName);
				}
				
				maxValue = parseFloat(maxValue);
				minValue = parseFloat(minValue);
				var searchValue = "";
				if (maxValue > minValue) {
					searchValue = minValue + "@" + maxValue;
				} else {
					searchValue = maxValue + "@" + minValue;
				}
				searchMap.put(columnName, searchValue);
				
				
				showSelectedField();
			});
        }   
    }); 
})(jQuery);