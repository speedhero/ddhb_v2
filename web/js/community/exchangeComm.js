;(function($){
	Jm.require("/Jm/plugin/jquery.suggest.community.js");
	Jm.require("/Jm/plugin/slideto.js");
	Jm.require("/Jm/plugin/jquery.selectDown.js");
	Jm.require("/Jm/plugin/jquery.district.gls.js");
	$(document).ready(function(){
		//选择小区
		var params = $("#exChangeCommJs_v2").attr("src"),
			parStr = params.substring(params.indexOf('?')),
			module = Jm.fn.urlParamsParse(parStr , "module"),
			districtBoxId = Jm.fn.urlParamsParse(parStr , "districtId"),
			communityBoxId = Jm.fn.urlParamsParse(parStr , "communityBoxId"),
			multipleFlag = Jm.fn.urlParamsParse(parStr , "multipleFlag"),
			multipleFlag = multipleFlag === "Y" ? true : false,
			dataType = Jm.fn.urlParamsParse(parStr , "dataType"),
			dataType = dataType === "T" ? true : false,//T代表动态，S代表静态
			maxItemNum = Jm.fn.urlParamsParse(parStr , "maxItemNum");
		
		if($("#"+communityBoxId).length === 0) return false;
		var $communityBoxId = $("#"+communityBoxId),
			parentCommBox = $communityBoxId.parents(".exchangeCommBox:eq(0)"),
			hasSelectedBox = parentCommBox.find("#hadChooseCommSpan");
		
		var bindChooseFunc = function(community_arr){
			var suggestObj = $communityBoxId.suggestCommunity({
				communityData: community_arr,
				tabSpeed: 0,
				multiple: multipleFlag, //不支持多选
				maxSelectNum: maxItemNum,
				clickFunc: function(currComm , multiple){
					
				}
			});
			return suggestObj;
		}
		//tab
		parentCommBox.find("#exCommTab").jmtab();
		//已加入的小区的点击事件
		parentCommBox.find("#myComms").on("click" , "a.J_community" , function(){
			var currComm = $(this),
				commId = currComm.attr("rel"),
				commName = currComm.html();
			currComm.parent().addClass("selected").siblings().removeClass("selected");
			hasSelectedBox.html('<span>'+commName+' <input type="checkbox" name="community_id[]" value="'+commId+'" checked="checked" style="display:none;" /></span>');
		});
		
		var commCacheArr = new Array();
		var suggestObj = bindChooseFunc(null);
		var districtObj = $("#"+districtBoxId).district({
			url: Jm.getBasePath() + "/site/loadDistrict",
			glsDrow: true,
			slideSwitch: false,
			autoDropSwitch: true,
			isPopWin: true, //使用场景-弹窗
			successFunc: function(districtId,fieldName){
				if(fieldName === "district"){
					loadCommunityData(districtId);
				}
			}
		});
		
		var loadCommunityData = function(districtId){
			if($.inArray(districtId , commCacheArr) !== -1){
				suggestObj = bindChooseFunc($("body").data("communitys_"+districtId));
			}else{
				commCacheArr.push(districtId);
				var url = dataType ? Jm.getBasePath() + "/site/loadCommunityData/district_code/"+districtId : Jm.getBasePath() + '/uploads/'+districtId+'.js';
				$.ajax({
					url: url,
					dataType: "script",
					error: function(){
						suggestObj = bindChooseFunc(null);
					},
					success: function(){
						suggestObj = bindChooseFunc($("body").data("communitys_"+districtId));
					}
				});
			}
		};
		//编辑场景
		var defaultDistrictCode = $("#"+districtBoxId+" select[name=district]:eq(0)").val();
		if(defaultDistrictCode !== undefined && defaultDistrictCode !== ""){
			loadCommunityData(defaultDistrictCode);
		}
		
	});
})(jQuery);