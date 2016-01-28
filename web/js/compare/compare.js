/**
 * 向对比栏中添加一个对比项
 * @param obj
 * @param cookieKeyStr
 */
function addCompareItem(obj, cookieKeyStr){
	$("#compareMenu").click();
	$("#hiddenCompareDiv").css("display", "block");
	$("#openCompareDiv").css("display", "none");
	if ($("#compareDiv").css("display") == 'none'){
		$("#compareDiv").css("display", "block");
	}
	if ($("#compareContentContainer").css("display") == 'none'){
		$("#compareContentContainer").css("display", "block");
	}
	if ($("#hiddenCompareDiv").css("display") == 'none'){
		$("#hiddenCompareDiv").css("display", "block");
	}
	setCookie("compareStatus","open");
	var dataString = {"cookieKeyStr" : cookieKeyStr};
	$.ajax({
		type : "POST",
		url : globalUrl + "userCompare.show?actionMethod=getCompareItem",
		data : dataString,
		dataType: "json",
		success : function(data) {
			if(data != null){
				comparedItemsArray = data;
			}
			$("#compareContentContainer .houseCompareItem").each(function(){
				$(this).remove();
			});
			$("#textContent1").css("display", "block");
			$("#textContent2").css("display", "block");
			$("#textContent3").css("display", "block");
			$("#textContent4").css("display", "block");
			initComparedItems(comparedItemsArray, cookieKeyStr);
			if ($(obj).attr("inCompareItem") == 'true'){
				alert("您已经加入对比了！");
				return;
			}
			if (comparedItemsArray.length >= 4){
				alert("最多只能同时对比4个！");
			} else {
				var str = createObjStr(obj, comparedItemsArray, cookieKeyStr);
			    addComparedItems(comparedItemsArray, str, cookieKeyStr);
			    $(obj).attr("inCompareItem", "true");
			    $(obj).addClass("addCompare");//("background-color", "#cb4f4d");
			}
		},
		error:function(){
			alert("获取对比信息失败！");
		}
	});
}

/**
 * 初始化对比框
 * @param comparedItemsArray
 * @param cookieKeyStr
 */
function initComparedItems(comparedItemsArray, cookieKeyStr){
	for (var i = 0; i < comparedItemsArray.length; i++){
		var compareId = comparedItemsArray[i].id;
		var brokerId = comparedItemsArray[i].brokerId;
		var url="";
		if(cookieKeyStr == "rentHouseCompare"){
			//url=globalUrl+"rent.show?actionMethod=houseRentDetail&houseNo="+compareId+"&brokerId="+brokerId;
			//新的地址
			url = globalUrl + "chuzu/" + compareId + ".html";
		}else if (cookieKeyStr ==  "secondHouseCompare"){
			//url=globalUrl+"houseSecond.show?actionMethod=houseSecondDetail&houseNo="+compareId+"&brokerId="+brokerId;
			//新的地址
			url = globalUrl + "chushou/" + compareId + ".html";
		}else if (cookieKeyStr ==  "communityCompare"){
			//url=globalUrl+"community.show?actionMethod=communityDetail&id=" + compareId;
			//新的地址 
			url = globalUrl + "xiaoqu/" + compareId + ".html";
		}
		
		var straa =  "<div class='houseCompareItem' comparedItemsArrayIndex='" + (i+1) + "'>" +
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
		
		straa = straa +	  "<div class='db_an deleteCompareItemDiv' comparedItemsArrayIndex='" + (i+1) + "' comparedHouseId='" + comparedItemsArray[i].id + "'><a href='javascript:void(0);' comparedHouseId='" + comparedItemsArray[i].id + "'>删除 </a></div>";
		$("#textContent" + ( i + 1)).css("display", "none");
		$("#compareItem" + ( i + 1)).append(straa);
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
        //$("#textContent" + index).css("display", "block");
        //setCookie(cookieKeyStr, JSON.stringify(comparedItemsArray));
        saveCompareItem(cookieKeyStr, JSON.stringify(comparedItemsArray));
    });
}

/**
 * 创建对比项
 * @param obj
 * @param comparedItemsArray
 * @param cookieKeyStr
 * @returns {String}
 */
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
	var straa =  "<div class='houseCompareItem' comparedItemsArrayIndex='" + (comparedItemsArray.length) + "'>" +
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
	straa = straa+"<div class='db_an deleteCompareItemDiv' comparedItemsArrayIndex='" + (comparedItemsArray.length) + "' comparedHouseId='" + $(obj).attr("compareId") + "'><a href='javascript:void(0);' comparedHouseId='" + $(obj).attr("compareId") + "'>删除 </a></div>";
	//setCookie(cookieKeyStr, JSON.stringify(comparedItemsArray));
	saveCompareItem(cookieKeyStr, JSON.stringify(comparedItemsArray));
	return straa;
}

/**
 * 把对像加入到对比栏中
 * @param comparedItemsArray
 * @param straa
 * @param cookieKeyStr
 */
function addComparedItems(comparedItemsArray, straa, cookieKeyStr){
    $("#textContent" + ( comparedItemsArray.length)).css("display", "none");
    $("#compareItem" + (comparedItemsArray.length)).append(straa);
    //setCookie(cookieKeyStr, JSON.stringify(comparedItemsArray));
    saveCompareItem(cookieKeyStr, JSON.stringify(comparedItemsArray));
    $(".deleteCompareItemDiv[comparedItemsArrayIndex='" + (comparedItemsArray.length ) + "']").click(function(){
        var index = $(this).attr("comparedItemsArrayIndex");
        var comparedHouseId = $(this).attr("comparedHouseId");
        $("[incompareitem][compareId='" + comparedHouseId + "']").attr("inCompareItem","false");
        $("[incompareitem][compareId='" + comparedHouseId + "']").removeClass("addCompare");
        $(this).parent().remove();
        moveItems(index, comparedItemsArray);
        removeItemFromArrayT(index);  
        //setCookie(cookieKeyStr, JSON.stringify(comparedItemsArray));
        saveCompareItem(cookieKeyStr, JSON.stringify(comparedItemsArray));
        comparedItemsArray="";
    });
}

/**
 * 删除对比项
 * @param id
 */
function removeItemFromArrayT(id){
    var length = comparedItemsArray.length;
    if (id == (length)){
        comparedItemsArray.length = length - 1;
    }else{
        for (var i = 0; i < comparedItemsArray.length; i++){
            if ((i+1) > id){
                comparedItemsArray[i-1] = comparedItemsArray[i];
            }
        }
        comparedItemsArray.length = length - 1;
    } 
}

/**
 * 移动对比项
 * @param indexs
 * @param comparedItemsArray
 */
function moveItems(indexs, comparedItemsArray){
    $(".houseCompareItem").each(function(index){
        var currentIndex = $(this).attr("comparedItemsArrayIndex");
        if (currentIndex > indexs){
            $("#compareItem" + (currentIndex-1)).append($(this));
            $(this).attr("comparedItemsArrayIndex", currentIndex - 1);
            $(this).children().last().attr("comparedItemsArrayIndex", currentIndex - 1);
        }
    });
     $("#textContent" + comparedItemsArray.length).css("display", 'block');
}

/**
 * 清空对比栏
 * @param cookieKeyStr
 */
function clearCompareItem(cookieKeyStr){
	$("#compareContentContainer .houseCompareItem").each(function(){
		$(this).remove();
	});
	$("#textContent1").css("display", "block");
	$("#textContent2").css("display", "block");
	$("#textContent3").css("display", "block");
	$("#textContent4").css("display", "block");
	$("[inCompareItem='true']").each(function(){
		$(this).attr("inCompareItem", "false");
		$(this).removeClass("addCompare");
	});
	comparedItemsArray.length = 0;
	delCookie(cookieKeyStr);
	clearCompareItemInBack(cookieKeyStr);
}

/**
 * 对比按钮点击事件
 * @param url
 */
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

/**
 * 隐藏对比栏
 */
function hiddenCompareDiv(){
	$("#compareContentContainer").css("display", "none");
	$("#compareDiv").css("display", "block");
	$("#historyListContainer").css("display", "none");
	$("#hiddenCompareDiv").css("display", "none");
	$("#openCompareDiv").css("display", "block");
	setCookie("compareStatus","hidden");
}

/**
 * 打开对比栏
 */
function openCompareDiv(){
	$("#compareMenu").click();
	$("#compareDiv").css("display", "block");
	$("#hiddenCompareDiv").css("display", "block");
	$("#openCompareDiv").css("display", "none");
	setCookie("compareStatus","open");
}

/**
 * 关闭Compare栏
 */
function closeCompareDiv(){
	$("#compareDiv").css("display", "none");
	setCookie("compareStatus","close");
}

/**
 * 点击到某个页面
 * @param url
 */
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
