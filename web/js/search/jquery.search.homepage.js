;(function($) {
	$.fn.extend({
		"createSearch" : function(option) {
			optionDefault = {
				searchItems : {},
				displayHiddenBar : true
			};

			currentOptions = $.extend(optionDefault, option);
			searItems = currentOptions.searchItems;

			var menuBarContainer = $("<div id=\"menuBarContainer\" class=\"menuBar\"></div>");
			$(this).append(menuBarContainer);
			var searchContainerDiv=$("<div id=\"searchContainerDiv\"></div>");
			$(this).append(searchContainerDiv);
			$(searchContainerDiv).append("<div class=\"js_sc\"><input type=\"text\" value='请输入城区、商圈、小区名或内部编号查找房源...'><a href=\"#\"></a></div>");

			//创建菜单标题
			for (var i = 0; i < searItems.length; i++){
				var searchContainer = null;
				if ( i == 0){
					$(menuBarContainer).append("<a class=\"menuItem menuItemSelected menuId_" + searItems[i].id + " menuId_1selected\" id=\"menuId_" + searItems[i].id + "\" containerId='contentContainer_" + searItems[i].id + "'>" + searItems[i].searchLabel + "</a>");
					searchContainer = $("<div class=\"menuSeachItems\" id=\"contentContainer_" + searItems[i].id + "\"></div>");
				}else{
					$(menuBarContainer).append("<a class=\"menuItem menuId_" + searItems[i].id + "\" id=\"menuId_" + searItems[i].id + "\" containerId='contentContainer_" + searItems[i].id  + "'>" + searItems[i].searchLabel + "</a>");
					searchContainer = $("<div class=\"menuSeachItems hiddenDiv\" id=\"contentContainer_" + searItems[i].id + "\"></div>");
				}
				$(searchContainerDiv).append(searchContainer);
				createSearchItem(searItems[i], searchContainer);
			}
			
			$(searchContainerDiv).append("<div class='js_cona'><a>确定</a></div>");
			
			//创建选项
			function createSearchItem(searItem, searchContainer){
				for (var i = 0; i < searItem.subItems.length; i++){
					var searchItemLine = $("<div class='itemLine'></div>");
					$(searchItemLine).append("<span class='searchLabel'>" + searItem.subItems[i].searchLabel + "</span>");
					var fieldContainer = $("<div class='fieldContainer'></div>");
					var searchFiledsObj = searItem.subItems[i].searchFileds;
					$(fieldContainer).append("<div class='unlimited selectedField'>不限</div>");
					for (var j = 0; j < searchFiledsObj.length; j++){
						if (searItem.subItems[i].isRange == 0){
							$(fieldContainer).append("<div class='fieldItem' columnName='" + searItem.subItems[i].colunmName + "' fieldValue='" + searchFiledsObj[j].fieldValue + "'>" + searchFiledsObj[j].fieldName + "</div>");
						}else{
							$(fieldContainer).append("<div class='fieldItem' columnName='" + searItem.subItems[i].colunmName + "' minFieldValue='" + searchFiledsObj[j].minFieldValue + "' maxFieldValue='" + searchFiledsObj[j].maxFieldValue + "'>" + searchFiledsObj[j].fieldName + "</div>");
						}
					}
					$(fieldContainer).append("<div class='clearDiv'></div>");
					$(searchItemLine).append(fieldContainer);
					$(searchItemLine).append("<div class='clearDiv'></div>");
					$(searchContainer).append(searchItemLine);
				}
			}
			
			//切换菜单
			$(".menuItem").click(function(){
				var previousContainer = $(".menuItemSelected");
				
				$(previousContainer).removeClass("menuId_1selected");
				$(previousContainer).removeClass("menuId_2selected");
				$(previousContainer).removeClass("menuId_3selected");
				
				var previousContainerId = $(previousContainer).attr("containerId");
				var currentContainerId = $(this).attr("containerId");
				$("#" + previousContainerId).addClass("hiddenDiv");
				$("#" + currentContainerId).removeClass("hiddenDiv");
				$(previousContainer).removeClass("menuItemSelected");
				$(this).addClass("menuItemSelected");
				$(this).addClass($(this).attr("id") + "selected");
				
				$("#" + previousContainerId).find(".selectedField").removeClass("selectedField");
				$("#" + previousContainerId).find(".unlimited").addClass("selectedField");
			});
			
			$(".fieldItem").click(function(){
				$(this).parent().find(".selectedField").removeClass("selectedField");
				$(this).addClass("selectedField");
			});
			
			$(".js_sc input").focus(function(){
				var inputValue = $(this).val();
				if (inputValue === '请输入城区、商圈、小区名或内部编号查找房源...'){
					$(this).val("");
				}
			});
			$(".js_sc input").blur(function(){
				var inputValue = $(this).val();
				if (inputValue === ''){
					$(this).val("请输入城区、商圈、小区名或内部编号查找房源...");
				}
			});
			
			var type = 1;
			
			//点击搜索按钮 
			$(".js_cona a").click(function(){
				var searchItems = "welcome.show?actionMethod=dimQuery&type=" + type;
				var moduleName = 0;
				var containerId = $(".menuItemSelected").attr("containerid");
				if (containerId == 'contentContainer_1'){
					searchItems = searchItems + "&searchModule=1";
				}else if (containerId == 'contentContainer_2'){
					searchItems = searchItems + "&searchModule=2";
				}else{
					searchItems = searchItems + "&searchModule=3";
				}
				
				$("#" + containerId).find(".selectedField").each(function(){
					if ($(this).hasClass("fieldItem")){
						var searchedColumnName = $(this).attr("columnname");
						if (searchedColumnName != ''){
							if (searchedColumnName.indexOf("_one_") > 0){
								searchItems = searchItems + "&" + searchedColumnName + "=" + $(this).attr("fieldvalue");
							}else if (searchedColumnName.indexOf("_two_")>0){
								searchItems = searchItems + "&" + searchedColumnName + "=" + $(this).attr("minfieldvalue") + "@" + $(this).attr("maxfieldvalue");
							}
						}
					}
				});
				
				if ($(".js_sc input").val().trim() != "" && $(".js_sc input").val().trim() != "请输入城区、商圈、小区名或内部编号查找房源..."){
					searchItems = searchItems + "&inputSearch=" + $(".js_sc input").val().trim();
				}
				window.open(searchItems, 'newWindow','');
			});
			
			$(".unlimited").click(function(){
				$(this).parent().find(".selectedField").removeClass("selectedField");
				$(this).addClass("selectedField");
			});
			
			$(".js_sc a").click(function(){
				var searchItems = "welcome.show?actionMethod=dimQuery&type=" + type;
				var moduleName = 0;
				var containerId = $(".menuItemSelected").attr("containerid");
				if (containerId == 'contentContainer_1'){
					searchItems = searchItems + "&searchModule=1";
				}else if (containerId == 'contentContainer_2'){
					searchItems = searchItems + "&searchModule=2";
				}else{
					searchItems = searchItems + "&searchModule=3";
				}
				
				if ($(".js_sc input").val().trim() != "" && $(".js_sc input").val().trim() != "请输入城区、商圈、小区名或内部编号查找房源..."){
					searchItems = searchItems + "&inputSearch=" + $(".js_sc input").val().trim();
				}
				window.open(searchItems, 'newWindow','');
			});
		}
	});
})(jQuery);