//预约、通知、市场动态

function _services(actionUrl, id, title, _dataString, _type){
	var dataString = _dataString;
	var type = _type;
	if(!_type) type = "post";
	$.ajax({
		type : type,
		url : actionUrl,
		data : dataString,
		dataType : "html",
		async:false,
		success : function (data) {
			var dataContent = '<html>'+data+'</html>';
			art.dialog({
				id:id,
		 		title: title,
		 		content: dataContent,
		 		lock: true,
		 		drag: false,
		 	    resize: false,
		 	    max: false,
		 	    min: false,
		 	   	zIndex: 99999
			});
			refresh();
		},
		error: function(data){
			alert("错误了");
		}
	});
}

//操作 如：举报
function _operations(dataContent, id, title){
	  var dataContent = dataContent;
	  
	  art.dialog({
	    id:id,
	    title: title,
	    width: 600,
	    height: 500,
	    content: dataContent,
	    lock: true,
	    drag: false,
	    resize: false,
	    max: false,
	    min: false,
	    zIndex: 99999
	  });
}

//降价通知
function _reduceNotice(actionUrl, type, reduceNotice, hrefUrl, _cancel) {
	var cancel = _cancel;
	if(!cancel)
		cancel = "添加";
	$.ajax({
	    type: type,
	    url: actionUrl,
	    dataType: "json",
	    data: reduceNotice,
	    success: function(data){
	      if(data.result == 'success'){
	        alert( cancel + "通知成功");
	        window.location.reload();
	        $('#reducenoticelink').attr("href", hrefUrl);
	      }else if(data.result == 'already') {
//	        $('#reducenoticelink').attr("href", "${globalUrl}notice.show?actionMethod=inputInfoPage&searchno=${houseAppraise.searchno }");
	    	  $('#reducenoticelink').attr("href", hrefUrl);
	        alert("该房源已经"+cancel+"通知");
	      }else {
	        alert(cancel + "失败，请重新尝试");
	        $('#reducenoticelink').attr("href", hrefUrl);
//	        $('#reducenoticelink').attr("href", "${globalUrl}notice.show?actionMethod=inputInfoPage&searchno=${houseAppraise.searchno }");
	      }
	    }
	  });
	}