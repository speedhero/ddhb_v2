/**
 * Author:		Vincent Yang
 * Date:		2014-06-01
 * Description:	This jQuery plug-in is used to create a way to retrieve information
 */
;(function($){
    $.fn.extend({
        "createSearchForPhone":function(option){
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
        	
        	var selectContentDiv = $("<div style='width:100%; min-width:40px;'></div>");
        	
        	$(this).append(selectContentDiv);
        	
        	
        	for (var i = 0; i < subItems.length; i++){
        		generateSelectElement(subItems[i]);
        	}
        	$(this).append("<div style='clear:both;'></div>");
        	
        	//生成下拉列表框的方法
			function generateSelectElement(subItem){
				var customSelectEle = $("<div class='customSelect'></div>");
				var labelSpan = $("<span class='label'>" + subItem.searchLabel + ":</span>");
				$(customSelectEle).append(labelSpan);
				var searchXLEle = $("<div class='searchXL'></div>");
				$(customSelectEle).append(searchXLEle);
				var csEle = $("<div class='cs'></div>");
				$(searchXLEle).append(csEle);
				var defaultSelect = $("<div class='csc' selectedvalue='' columnname='" + subItem.colunmName + "'>不限</div>");
				$(csEle).append(defaultSelect);
				var iconDiv = $("<div class='xljt' style='background: rgb(173, 173, 173);'><img src='images/search/selectXL.png'></div>");
				$(csEle).append(iconDiv);
				var xlnrEle = $("<div class='xlnr'></div>");
				$(csEle).append(xlnrEle);
				
				var optionStr = "<a optionvalue='' class='unlimit'>不限</a>";
				for (var i = 0; i < subItem.searchFileds.length; i++){
					optionStr += "<a optionvalue='" + subItem.searchFileds[i].fieldValue + "'>" + subItem.searchFileds[i].fieldName + "</a>";
				}
				$(xlnrEle).append(optionStr);
				$(selectContentDiv).append(customSelectEle);
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
			}
        }   
    }); 
})(jQuery);