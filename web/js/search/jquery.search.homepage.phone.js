;(function($) {
	$.fn.extend({
		"createSearchForPhone" : function(option) {
			optionDefault = {
				searchItems : {},
				displayHiddenBar : true
			};

			currentOptions = $.extend(optionDefault, option);
			searItems = currentOptions.searchItems;

			var menuBar = $("<div class='phoneMenuBar'></div>");
			$(this).append(menuBar);
			
			var searchContainerDiv=$("<div id=\"searchContainerDiv\"></div>");
			$(this).append(searchContainerDiv);
			$(searchContainerDiv).append("<div class='js_sc_phone'><input type='text' value=''><a href=\"#\"></a></div>");

			
			//创建菜单标题
			for (var i = 0; i < searItems.length; i++){
				var searchContainer = null;
				if ( i == 0){
					$(menuBar).append("<a class='menuItem_phone selectedMenuPhone phone_menuId_" + searItems[i].id + " phone_menuId_" + searItems[i].id + "_selected' id=\"menuId_" + searItems[i].id + "\" containerId='contentContainer_" + searItems[i].id + "'>" + searItems[i].searchLabel + "</a>");
					searchContainer = $("<div class=\"menuSeachItems_phone\" id=\"contentContainer_" + searItems[i].id + "\"></div>");
				}else{
					$(menuBar).append("<a class='menuItem_phone phone_menuId_" + searItems[i].id + " ' id=\"menuId_" + searItems[i].id + "\" containerId='contentContainer_" + searItems[i].id  + "'>" + searItems[i].searchLabel + "</a>");
					searchContainer = $("<div class=\"menuSeachItems_phone hiddenDiv\" id=\"contentContainer_" + searItems[i].id + "\"></div>");
				}
				$(searchContainerDiv).append(searchContainer);
				generateSelectElement(searItems[i], searchContainer);
			}
			$(menuBar).append("<div class='clearDiv'></div>");
			$(searchContainerDiv).append("<div class='js_cona_phone'><a>确定</a></div>");
			
			//生成下拉列表框的方法
			function generateSelectElement(subItem, moduleContainer){
				for (var i = 0; i < subItem.subItems.length; i++){
					var customerSeleContainer = $("<div class='customSelect'></div>");
					var label = $("<span class='label'>" + subItem.subItems[i].searchLabel + "</span>");
					$(customerSeleContainer).append(label);
					var selectContent = $("<div class='searchXL search'></div>");
					$(customerSeleContainer).append(selectContent);
					var container = $("<div class='cs'></div>");
					$(selectContent).append(container);
					$(container).append("<div class='csc' selectedvalue='' columnname='" + subItem.subItems[i].colunmName + "'>不限</div>");
					$(container).append("<div class='xljt'><img src='images/search/selectXL.png'></div>");
					var optionContainer = $("<div class=\"xlnr\"></div>");
					$(container).append(optionContainer);
					$(optionContainer).append("<a optionvalue='' class='unlimit'>不限</a>");
					var searchFiledsObj = subItem.subItems[i].searchFileds;
					var optionStr = "";
					for (var j = 0; j < searchFiledsObj.length; j++){
						var fieldValue = "";
						if (subItem.subItems[i].isRange == 1){
							fieldValue = searchFiledsObj[j].minFieldValue + "@" + searchFiledsObj[j].maxFieldValue;
						}else{
							fieldValue = searchFiledsObj[j].fieldValue;
						}
						optionStr += "<a optionValue=" + fieldValue + " >" + searchFiledsObj[j].fieldName + "</a>";
					}
					$(optionContainer).append(optionStr);
					$(moduleContainer).append(customerSeleContainer);
				}
			}
			
			$(".menuItem_phone").click(function(){
				var previousContainer = $(".selectedMenuPhone");
				$(previousContainer).removeClass("phone_menuId_1_selected");
				$(previousContainer).removeClass("phone_menuId_2_selected");
				$(previousContainer).removeClass("phone_menuId_3_selected");
				
				var selectedContainerId = $(this).attr("containerid");
				$(previousContainer).removeClass("selectedMenuPhone");
				$(this).addClass("selectedMenuPhone");
				$("#" + $(previousContainer).attr("containerid")).addClass("hiddenDiv");
				$("#" + selectedContainerId).removeClass("hiddenDiv");
				$("#" + $(previousContainer).attr("containerid")).find(".csc").each(function(index){
					$(this).attr("selectedvalue", "");
					$(this).html("不限");
				});
				$(this).addClass("phone_" + $(this).attr("id") + "_selected");
				
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
                $(this).find(".xljt").css("background", "#3FB8B1");
            }).mouseout(function(){
                $(this).find(".csc").removeClass("selectHoverVal");
                 $(this).find(".xljt").css("background", "#ADADAD");
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
                var optionValue = $(obj).attr("optionValue");
                
                var valueContainer = (currentSelect).find(".csc").first();
                $(valueContainer).attr("selectedValue", optionValue);
			}
			
			//点击确定按钮
			$(".js_cona_phone a").click(function(){
				var type = 1;
				var searchItems = "welcome.show?actionMethod=dimQuery&type=" + type;
				var moduleName = 0;
				var containerId = $(".selectedMenuPhone").attr("containerid");
				if (containerId == 'contentContainer_1'){
					searchItems = searchItems + "&searchModule=1";
				}else if (containerId == 'contentContainer_2'){
					searchItems = searchItems + "&searchModule=2";
				}else{
					searchItems = searchItems + "&searchModule=3";
				}
				
				$("#" + containerId).find(".csc").each(function(){
					var searchedColumnName = $(this).attr("columnname");
					if ($(this).attr("selectedvalue") != ""){
						searchItems = searchItems + "&" + searchedColumnName + "=" + $(this).attr("selectedvalue");
					}
				});
				if ($(".js_sc_phone input").val().trim() != ""){
					searchItems = searchItems + "&inputSearch=" + $(".js_sc_phone input").val().trim();
				}
				window.open(searchItems, 'newWindow','');
			});
		}
	});
})(jQuery);