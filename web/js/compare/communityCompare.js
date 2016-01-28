function reloadCompareDiv(cookieStr,globalurl){
	if(cookieStr == "secondHouseCompare"){
		$("#compareItem1").empty();
		$("#compareItem1").append('<div id="textContent1"><div class="db_tjs">1</div><div class="db_tjswd">继续添加对比二手房</div></div>');
		$("#compareItem2").empty();
		$("#compareItem2").append('<div id="textContent2"><div class="db_tjs">2</div><div class="db_tjswd">继续添加对比二手房</div></div>');
		$("#compareItem3").empty();
		$("#compareItem3").append('<div id="textContent3"><div class="db_tjs">3</div><div class="db_tjswd">继续添加对比二手房</div></div>');
		$("#compareItem4").empty();
		$("#compareItem4").append('<div id="textContent4"><div class="db_tjs">4</div><div class="db_tjswd">继续添加对比二手房</div></div>');
		$("#showCompare").attr("onclick","startCompare('"+globalurl+"houseSecond.show?actionMethod=houseSecondCompare')");
		$("#clearCompare").attr("onclick","clearCompareItem('secondHouseCompare')");
	}else if(cookieStr == "rentHouseCompare"){
		$("#compareItem1").empty();
		$("#compareItem1").append('<div id="textContent1"><div class="db_tjs">1</div><div class="db_tjswd">继续添加对比租房房源</div></div>');
		$("#compareItem2").empty();
		$("#compareItem2").append('<div id="textContent2"><div class="db_tjs">2</div><div class="db_tjswd">继续添加对比租房房源</div></div>');
		$("#compareItem3").empty();
		$("#compareItem3").append('<div id="textContent3"><div class="db_tjs">3</div><div class="db_tjswd">继续添加对比租房房源</div></div>');
		$("#compareItem4").empty();
		$("#compareItem4").append('<div id="textContent4"><div class="db_tjs">4</div><div class="db_tjswd">继续添加对比租房房源</div></div>');
		$("#showCompare").attr("onclick","startCompare('"+globalurl+"rent.show?actionMethod=houseRentCompare')");
		$("#clearCompare").attr("onclick","clearCompareItem('rentHouseCompare')");
	}else{
		$("#compareItem1").empty();
		$("#compareItem1").append('<div id="textContent1"><div class="db_tjs">1</div><div class="db_tjswd">继续添加对比小区</div></div>');
		$("#compareItem2").empty();
		$("#compareItem2").append('<div id="textContent2"><div class="db_tjs">2</div><div class="db_tjswd">继续添加对比小区</div></div>');
		$("#compareItem3").empty();
		$("#compareItem3").append('<div id="textContent3"><div class="db_tjs">3</div><div class="db_tjswd">继续添加对比小区</div></div>');
		$("#compareItem4").empty();
		$("#compareItem4").append('<div id="textContent4"><div class="db_tjs">4</div><div class="db_tjswd">继续添加对比小区</div></div>');
		$("#showCompare").attr("onclick","startCompare('"+globalurl+"community.show?actionMethod=communityCompare')");
		$("#clearCompare").attr("onclick","clearCompareItem('communityCompare')");
	}
}

function addCompareToDiv(obj,cookieFlag,compareId){
	if(cookieFlag == 'S'){
		addCompareItem(obj, 'secondHouseCompare');
	}else if(cookieFlag == 'R'){
		addCompareItem(obj, 'rentHouseCompare');
	}else{
		addCompareItem(obj, 'communityCompare');
	}
}

function reInitCompareDiv(cookieStr,globalurl){
	if(cookieStr == "secondHouseCompare"){
		$.ajax({
			type : "post",
			url : globalUrl+"userCompare.show?actionMethod=getCompareItem&cookieKeyStr=secondHouseCompare",
			dataType:'json',
			success : function (data) {
				var comparedItemInCookie = data;
				if (comparedItemInCookie == '' || comparedItemInCookie == null){
					comparedItemsArray=new Array();
				}else{
					comparedItemsArray = comparedItemInCookie; 
				}
				initComparedItems(comparedItemsArray, "secondHouseCompare");
				
				$.ajax({
					type : "post",
					url : globalUrl+"userCompare.show?actionMethod=getHistoryItem&cookieKeyStr=secondHouseHistory",
					dataType:'json',
					success : function (data) {
						var historyItemInCookie = data;
						if (historyItemInCookie == '' || historyItemInCookie == null){
							historyItemsArray = new Array();
						}else{
							historyItemsArray = historyItemInCookie;
						}
						
						initHistoryItems(historyItemsArray, "secondHouseHistory");  
					}
				});
			}
		});
	}else if(cookieStr == "rentHouseCompare"){
		$.ajax({
			type : "post",
			url : globalUrl+"userCompare.show?actionMethod=getCompareItem&cookieKeyStr=rentHouseCompare",
			dataType:'json',
			success : function (data) {
				var comparedItemInCookie = data;
				if (comparedItemInCookie == '' ||  comparedItemInCookie == null){
					comparedItemsArray=new Array();
				}else{
					comparedItemsArray = comparedItemInCookie; 
				}
				initComparedItems(comparedItemsArray, "rentHouseCompare");
				
				$.ajax({
					type : "post",
					url : globalUrl+"userCompare.show?actionMethod=getHistoryItem&cookieKeyStr=rentHouseHistory",
					dataType:'json',
					success : function (data) {
						var historyItemInCookie = data;
						if (historyItemInCookie == '' || historyItemInCookie == null){
							historyItemsArray = new Array();
						}else{
							historyItemsArray = historyItemInCookie;
						}
						
						initHistoryItems(historyItemsArray, "rentHouseHistory");  
					}
				});
			}
		});
	}else{
		$.ajax({
			type : "post",
			url : globalUrl+"userCompare.show?actionMethod=getCompareItem&cookieKeyStr=communityCompare",
			dataType:'json',
			success : function (data) {
				var comparedItemInCookie = data;
				if (comparedItemInCookie == '' ||  comparedItemInCookie == null){
					comparedItemsArray=new Array();
				}else{
					comparedItemsArray = comparedItemInCookie; 
				}
				initComparedItems(comparedItemsArray, "communityCompare");
				
				$.ajax({
					type : "post",
					url : globalUrl+"userCompare.show?actionMethod=getHistoryItem&cookieKeyStr=communityHistory",
					dataType:'json',
					success : function (data) {
						var historyItemInCookie = data;
						if (historyItemInCookie == '' ||  historyItemInCookie == null){
							historyItemsArray = new Array();
						}else{
							historyItemsArray = historyItemInCookie;
						}
						
						initHistoryItems(historyItemsArray, "communityHistory");  
					}
				});
			}
		});
	}
	
}