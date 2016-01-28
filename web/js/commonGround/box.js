//弹出登录、注册框、计算器
//url = "${globalUrl}login.show?actionMethod=loginCheck&target="+target

function _openDialog(id, title, url, type, datType, gray, dlzc, css){
  //弹出框js引用 
  var _dialogStr = gray + dlzc + css;
//	  "<link type='text/css' rel='stylesheet' href='${globalUrl}js/skins/gray.css'>";
//  _dialogStr += "<link rel='stylesheet' type='text/css' href='${globalUrl}css/css.css'>";
//  _dialogStr += "<link rel='stylesheet' type='text/css' href='${globalUrl}css/dlzc.css'>";
  
  //addJavaScript("${globalUrl}js/jquery.artDialog.source.js");
  //addJavaScript("${globalUrl}js/artDialog.iframeTools.source.js");

  if(!type) type="get";
  if(!datType) datType="html";
  $.ajax({
    type : type,
    url : url,
    dataType : datType,
    async: false,
    success : function (data) {
      data = _dialogStr + data;
      var dataContent = '<html>'+data+'</html>';
      art.dialog({
        id: id,
        title: title,
        content: dataContent,
        lock: true,
        drag: false,
        resize: false,
        max: false,
        min: false,
        zIndex: 99999
      });
    }
  });
}

//function _loginBox(housetype, actionUrl){
//	var flag = housetype;
//	var url = actionUrl;
//	if(flag != ""){
//		url = url+"&housetype="+flag;
//	}
//	$.ajax({
//		type : "get",
//		url : url,
//		dataType : "html",
//		async:false,
//		success : function (data) {
//			var dataContent = '<html>'+data+'</html>';
//			art.dialog({
//				id:'login',
//		 		title: "&nbsp;&nbsp;登录",
//		 		content: dataContent,
//		 		lock: true,
//		 		drag: false,
//		 	    resize: false,
//		 	    max: false,
//		 	    min: false,
//		 	   	zIndex: 999,
//			});
//		}
//	});
//};
//
////actionUrl = "${globalUrl}register.show?actionMethod=preRegist",
//function _registerBox(actionUrl){
//	$.ajax({
//		type : "get",
//		url : actionUrl,
//		dataType : "html",
//		async:false,
//		success : function (data) {
//			var dataContent = '<html>'+data+'</html>';
//			art.dialog({
//				id:'register',
//				title: "&nbsp;&nbsp;注册<a onclick=\"infoShow('registerReason');\" style=\"cursor:pointer; color:red;\">？</a>",
//		 		content: dataContent,
//		 		lock: true,
//		 		drag: false,
//		 	    resize: false,
//		 	    max: false,
//		 	    min: false,
//		 	   	zIndex: 999,
//			});
//		}
//	});
//};
//
function _infoBox(content, title, getTagName,setTagName){
	var dialogTitle = "提交成功";
	if(title != "" && title != null){
		dialogTitle = title;
	}
	var dataContent = document.getElementById(getTagName);
	art.dialog({
		id:getTagName,
 		title: dialogTitle,
 		content: dataContent,
 		lock: true,
 		drag: false,
 	    resize: false,
 	    max: false,
 	    min: false,
 	  	zIndex: 999999
	});
	$(setTagName).text(content);
}
