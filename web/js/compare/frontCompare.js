///向对比栏中添加一个对比项
function addCompareItem(obj, cookieKeyStr,type){
	var flag = "";
	if(type==0){
		flag="HouseSecond";
		comparedItemsArray = comparedItemsArrayHouseSecond;
		closeCompareDiv("HouseRent");
	}else{
		flag="HouseRent";
		comparedItemsArray = comparedItemsArrayHouseRent;
		closeCompareDiv("HouseSecond");
	}
	$("#compareMenu"+flag).click();
	$("#hiddenCompareDiv"+flag).css("display", "block");
	$("#openCompareDiv"+flag).css("display", "none");
	if ($("#compareDiv"+flag).css("display") == 'none'){
		$("#compareDiv"+flag).css("display", "block");
	}
	if ($("#compareContentContainer"+flag).css("display") == 'none'){
		$("#compareContentContainer"+flag).css("display", "block");
	}
	if ($("#hiddenCompareDiv"+flag).css("display") == 'none'){
		$("#hiddenCompareDiv"+flag).css("display", "block");
	}
	setCookie("compareStatus","open");
	if ($(obj).attr("inCompareItem") == 'true'){
		alert("您已经加入对比了！");
		return;
	}
	if (comparedItemsArray.length == 4){
		alert("最多只能同时对比4个！");
	}
    var str = createObjStr(obj, comparedItemsArray, cookieKeyStr);
    addComparedItems(comparedItemsArray, str, cookieKeyStr,flag);
    $(obj).attr("inCompareItem", "true");
    $(obj).addClass("addCompare");//("background-color", "#cb4f4d");
}

//初始化对比框
function initComparedItems(comparedItemsArray, cookieKeyStr,flag){
	for (var i = 0; i < comparedItemsArray.length; i++){
		var compareId = comparedItemsArray[i].id;
		var brokerId = comparedItemsArray[i].brokerId;
		var url="";
		if(cookieKeyStr == "rentHouseCompare"){
			url=globalUrl+"rent.show?actionMethod=houseRentDetail&houseNo="+compareId+"&brokerId="+brokerId;
		}else if (cookieKeyStr ==  "secondHouseCompare"){
			url=globalUrl+"houseSecond.show?actionMethod=houseSecondDetail&houseNo="+compareId+"&brokerId="+brokerId;
		}else if (cookieKeyStr ==  "communityCompare"){
			url=globalUrl+"community.show?actionMethod=communityDetail&id=" + compareId;
		}
		
		var straa =  "<div class='houseCompareItem' comparedItemsArrayIndex='" + (comparedItemsArray.length - 1) + "'>" +
	    "<div class='db_pt'><a href='javascript:void(0);'><img src='" + comparedItemsArray[i].url + "' onclick='toPage(\"" + url + "\")'></a></div>" + 
	    "<div class='db_name'>" + comparedItemsArray[i].title + "</div>" +
	    "<div class='db_zd'><p><span><b>"  ;
		
		if(cookieKeyStr == "rentHouseCompare"){
			straa = straa + parseInt(comparedItemsArray[i].price) + "</b>元/每月</span><b>" + parseInt(comparedItemsArray[i].area) + "</b>平米</p><b>" + comparedItemsArray[i].shi + "</b>室<b>" + comparedItemsArray[i].ting + "</b>厅</div>";
		}else if(cookieKeyStr == "secondHouseCompare"){
			straa = straa + parseInt(comparedItemsArray[i].price/10000) + "</b>万</span><b>" + parseInt(comparedItemsArray[i].area) + "</b>平米</p><b>" + comparedItemsArray[i].shi + "</b>室<b>" + comparedItemsArray[i].ting + "</b>厅<p><b>"+ parseInt(comparedItemsArray[i].compareUnitPrice) +"</b>元/平米</p></div>";
		}
		else{
			straa = straa + parseInt(comparedItemsArray[i].price) + "</b>元/平米</span><b>" + parseInt(comparedItemsArray[i].area) + "</b>平米</p></div>";
		}
		
		straa = straa +	  "<div class='db_an deleteCompareItemDiv' comparedItemsArrayIndex='" + (comparedItemsArray.length - 1) + "' comparedHouseId='" + comparedItemsArray[i].id + "'><a href='javascript:void(0);' comparedHouseId='" + comparedItemsArray[i].id + "'>删除 </a></div>";
		$("#textContent"+flag + ( i + 1)).css("display", "none");
		$("#compareItem"+flag + ( i + 1)).append(straa);
		$("[compareId='" + comparedItemsArray[i].id + "']").attr("inCompareItem","true");
		$("[compareId='" + comparedItemsArray[i].id + "']").addClass("addCompare");//css("background-color", "#cb4f4d");
	}
     $(".deleteCompareItemDiv").click(function(){
        var index = $(this).attr("comparedItemsArrayIndex");
        var comparedHouseId = $(this).attr("comparedHouseId");
        $("[incompareitem][compareId='" + comparedHouseId + "']").attr("inCompareItem","false");
        $("[incompareitem][compareId='" + comparedHouseId + "']").removeClass("addCompare");//css("background-color", "#adadad");
        $(this).parent().remove();
        moveItems(index, comparedItemsArray);
        removeItemFromArrayT(index);  
        //setCookie(cookieKeyStr, JSON.stringify(comparedItemsArray));
        saveCompareItem(cookieKeyStr, JSON.stringify(comparedItemsArray));
    });
}

//创建对比项
function createObjStr(obj, comparedItemsArray, cookieKeyStr){
	var compareId = $(obj).attr("compareId");
	var brokerId = $(obj).attr("brokerId");
	var url="";
	if(cookieKeyStr == "rentHouseCompare"){
		url=globalUrl+"rent.show?actionMethod=houseRentDetail&houseNo="+compareId+"&brokerId="+brokerId;
	}else if (cookieKeyStr ==  "secondHouseCompare"){
		url=globalUrl+"houseSecond.show?actionMethod=houseSecondDetail&houseNo="+compareId+"&brokerId="+brokerId;
	}else if (cookieKeyStr ==  "communityCompare"){
		url=globalUrl+"community.show?actionMethod=communityDetail&id=" + compareId;
	}
	var compareItem = {};
	compareItem.id = compareId;
	compareItem.brokerId = brokerId;
	compareItem.url = $(obj).attr("housePictureUrl");
	compareItem.title = $(obj).attr("compareTitle");
	compareItem.area = $(obj).attr("area");
	compareItem.shi = $(obj).attr("shi");
	compareItem.ting = $(obj).attr("ting");
	compareItem.price = $(obj).attr("comparePrice");
	compareItem.compareUnitPrice = $(obj).attr("compareUnitPrice");
	comparedItemsArray[comparedItemsArray.length] = compareItem;
	var straa =  "<div class='houseCompareItem' comparedItemsArrayIndex='" + (comparedItemsArray.length - 1) + "'>" +
    "<div class='db_pt'><a href='javascript:void(0);'><img src='" + $(obj).attr("housePictureUrl") + "' onclick='toPage(\"" + url + "\")'></a></div>" + 
    "<div class='db_name'>" + $(obj).attr("compareTitle") + "</div>" +
    "<div class='db_zd'><p><span><b>"  ;
	if(cookieKeyStr == "rentHouseCompare"){
		compareItem.unit = "房租（元/月）";	
		straa = straa +  parseInt($(obj).attr("comparePrice")) + "</b>元/每月</span><b>" + parseInt($(obj).attr("area")) + "</b>平米</p><b>" + $(obj).attr("shi") + "</b>室<b>" + $(obj).attr("ting") + "</b>厅</div>";
	}else if(cookieKeyStr == "secondHouseCompare"){
		compareItem.unit = "均价（元/平米）";	
		straa = straa + parseInt($(obj).attr("comparePrice")/10000) + "</b>万</span><b>" + parseInt($(obj).attr("area")) + "</b>平米</p><b>" + $(obj).attr("shi") + "</b>室<b>" + $(obj).attr("ting") + "</b>厅<p><b>"+parseInt($(obj).attr("compareUnitPrice"))+"</b>元/平米</p></div>";
	}
	else{
		straa = straa + parseInt($(obj).attr("comparePrice")) + "</b>元/平米</span><b>" + parseInt($(obj).attr("area")) + "</b>平米</p></div>";
	}
	straa = straa+"<div class='db_an deleteCompareItemDiv' comparedItemsArrayIndex='" + (comparedItemsArray.length - 1) + "' comparedHouseId='" + $(obj).attr("compareId") + "'><a href='javascript:void(0);' comparedHouseId='" + $(obj).attr("compareId") + "'>删除 </a></div>";
	//setCookie(cookieKeyStr, JSON.stringify(comparedItemsArray));
	saveCompareItem(cookieKeyStr, JSON.stringify(comparedItemsArray));
	return straa;
}

//把对像加入到对比栏中
function addComparedItems(comparedItemsArray, straa, cookieKeyStr,flag){
    $("#textContent"+flag + ( comparedItemsArray.length)).css("display", "none");
    $("#compareItem"+flag + (comparedItemsArray.length)).append(straa);
    //setCookie(cookieKeyStr, JSON.stringify(comparedItemsArray));
    saveCompareItem(cookieKeyStr, JSON.stringify(comparedItemsArray));
    $(".deleteCompareItemDiv[comparedItemsArrayIndex='" + (comparedItemsArray.length - 1) + "']").click(function(){
        var index = $(this).attr("comparedItemsArrayIndex");
        var comparedHouseId = $(this).attr("comparedHouseId");
        $("[incompareitem][compareId='" + comparedHouseId + "']").attr("inCompareItem","false");
        $("[incompareitem][compareId='" + comparedHouseId + "']").removeClass("addCompare");
        $(this).parent().remove();
        moveItems(index, comparedItemsArray);
        removeItemFromArrayT(index);  
        //setCookie(cookieKeyStr, JSON.stringify(comparedItemsArray));
        saveCompareItem(cookieKeyStr, JSON.stringify(comparedItemsArray));
        var indexcc = new Number(index) + 1;
        $("#textContent"+ flag + indexcc).css("display", "block");
    });
}

//删除对比项
function removeItemFromArrayT(id){
    var length = comparedItemsArray.length;
    if (id == (length - 1)){
        comparedItemsArray.length = length - 1;
    }else{
        for (var i = 0; i < comparedItemsArray.length; i++){
            if (i > id){
                comparedItemsArray[i] = comparedItemsArray[i-1];
            }
        }
        comparedItemsArray.length = length - 1;
    } 
}

//移动对比项
function moveItems(indexs, comparedItemsArray){
    $(".houseCompareItem").each(function(index){
        var currentIndex = $(this).attr("comparedItemsArrayIndex");
        if (currentIndex > indexs){
            $("#compareItem" + (currentIndex)).append($(this));
            $(this).attr("comparedItemsArrayIndex", currentIndex - 1);
            $(this).children().last().attr("comparedItemsArrayIndex", currentIndex - 1);
        }
    });
     $("#textContent" + comparedItemsArray.length).css("display", 'block');
}

//清空对比栏
function clearCompareItem(cookieKeyStr){
	if (cookieKeyStr == "secondHouseCompare") {
		$("#textContentHouseSecond1").css("display", "block");
		$("#textContentHouseSecond2").css("display", "block");
		$("#textContentHouseSecond3").css("display", "block");
		$("#textContentHouseSecond4").css("display", "block");
		$("#compareContentContainerHouseSecond .houseCompareItem").each(function(){
			$(this).remove();
		});
		$("#searchContentContainer11 [inCompareItem='true']").each(function(){
			$(this).attr("inCompareItem", "false");
			$(this).removeClass("addCompare");
		});
	} else if (cookieKeyStr == "rentHouseCompare") {
		$("#textContentHouseRent1").css("display", "block");
		$("#textContentHouseRent2").css("display", "block");
		$("#textContentHouseRent3").css("display", "block");
		$("#textContentHouseRent4").css("display", "block");
		$("#compareContentContainerHouseRent .houseCompareItem").each(function(){
			$(this).remove();
		});
		$("#searchContentContainer21 [inCompareItem='true']").each(function(){
			$(this).attr("inCompareItem", "false");
			$(this).removeClass("addCompare");
		});
	}
	comparedItemsArray.length = 0;
	//delCookie(cookieKeyStr);
	clearCompareItemInBack(cookieKeyStr);
}

//对比按钮点击事件
function startCompare(url){
	var compareStr = "";
	$(".deleteCompareItemDiv").each(function(){
		var comparedHouseId = $(this).attr("comparedHouseId");
		compareStr = compareStr + "," + comparedHouseId;
	});
	if (compareStr === ""){
		return;
	}
	
	url = url + "&houseNos=" + compareStr.substring(1, compareStr.length);
	window.open(url);
}

//隐藏对比栏
function hiddenCompareDiv(flag){
		$("#compareContentContainer"+flag).css("display", "none");
		$("#historyListContainer"+flag).css("display", "none");
		$("#hiddenCompareDiv"+flag).css("display", "none");
		$("#openCompareDiv"+flag).css("display", "block");
		setCookie("compareStatus","hidden");
}

//打开对比栏
function openCompareDiv(flag){
		$("#compareMenu"+flag).click();
		$("#hiddenCompareDiv"+flag).css("display", "block");
		$("#openCompareDiv"+flag).css("display", "none");
		setCookie("compareStatus","open");
}

function closeCompareDiv(flag){
		$("#compareDiv"+flag).css("display", "none");
		setCookie("compareStatus","close");
}

//点击到某个页面
function toPage(url){
	window.open(url);
}

/**
 * 将对比字符串保存到后台中
 * @param cookieKeyStr
 * @param content
 */
function saveCompareItem(cookieKeyStr, content){
	var dataString = {"cookieKeyStr" : cookieKeyStr, "contentStr": content};
	$.ajax({
		type : "POST",
		url : globalUrl+"userCompare.show?actionMethod=addCompareItem",
		data : dataString,
		dataType: "json",
		success : function(data) {
			
		},
		error:function(){
			alert("添加对比房源失败！");
		}
	});
}

/**
 * 清空对比栏
 * @param cookieKeyStr
 * @param content
 */
function clearCompareItemInBack(cookieKeyStr){
	var dataString = {"cookieKeyStr" : cookieKeyStr};
	$.ajax({
		type : "POST",
		url : globalUrl+"userCompare.show?actionMethod=clearCompareItem",
		data : dataString,
		dataType: "json",
		success : function(data) {
			
		},
		error:function(){
			alert("清除对比房源失败！");
		}
	});
}
