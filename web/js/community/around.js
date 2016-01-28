//加入小区
function joinCommunityFunc2(btn , communityId){
	Jm.fn.ajaxSubmit({
		url: Jm.getBasePath() + "/register/joinCommunity",
		params: {community_id: communityId},
		callback: function(response){
			//$.dialog.tips("您已成功加入该小区");
			var joinedHtml = '<a class="comnt_unfollowBtn" href="javascript:;" rel="'+communityId+'">取消加入</a>',
                joinBtn = btn.parent().find(".comnt_followBtn:eq(0)");
			$(joinedHtml).insertBefore(joinBtn);
			//更新已加入的小区列表
			var communityName = btn.parents(".communityCard:eq(0)").find("h6:eq(0)").children("a").html(),
				joinCommBtnHtml = '<a rel="'+communityId+'" href="javascript:;">'+communityName+'<i class="i_unfollow" title="取消关注"></i></a>';
			
			$(joinCommBtnHtml).appendTo($(".cmnt_followList dl dd"));
			joinBtn.remove();
		}
	});
}
$(function(){
	$("#communityListBox").on("click" , ".comnt_followBtn" , function(){
		var _thisBtn = $(this),
			communityId = _thisBtn.attr("rel");
		
		Jm.setAutoReflesh(false);
		if(Jm.getUserAuth() === false){
			Jm.getLoginWin(function(obj , userInfo){
				loginedFunc(obj , userInfo);
				joinCommunityFunc2(_thisBtn , communityId);
			});
			return false;
		}else{
			joinCommunityFunc2(_thisBtn , communityId);
		}
		
	});
	//退出小区
	$(".cmnt_followList dl").on("click" , "a" , function(){
		var _thisBtn = $(this),
			communityId = _thisBtn.attr("rel");
		Jm.fn.ajaxSubmit({
			url: Jm.getBasePath() +"/account/setting/exitcommunity",
			params: {community_id: communityId},
			dataType: "html",
			//loadding: true,
			//loadTips: "操作正在进行中，请稍后...",
			callback: function(response){
				_thisBtn.remove();
				//更新当前页小区加入的状态
				
				cancelBox = $("#communityListBox .comnt_unfollowBtn[rel="+communityId+"]");
				var joinedHtml = '<a class="comnt_followBtn" href="javascript:;" rel="'+communityId+'">加入该小区</a>';
				$(joinedHtml).insertBefore(cancelBox);
				cancelBox.remove();
			}
		});
	});
	
	//退出小区
	$("#communityListBox").on("click" , ".comnt_unfollowBtn" , function(){
		var _thisBtn = $(this),
			communityId = _thisBtn.attr("rel");
		Jm.fn.ajaxSubmit({
			url: Jm.getBasePath() +"/account/setting/exitcommunity",
			params: {community_id: communityId},
			dataType: "html",
			//loadding: true,
			//loadTips: "操作正在进行中，请稍后...",
			callback: function(response){
				var joinedHtml = '<a class="comnt_followBtn" href="javascript:;" rel="'+communityId+'">加入该小区</a>',
                    cancelBox = _thisBtn;
				$(joinedHtml).insertBefore(cancelBox);
				cancelBox.remove();
				//更新已加入的小区按钮
				$(".cmnt_followList dl a[rel="+communityId+"]").remove();
			}
		});
	});
	
	//完成事件
	$("#signSubmitBtn").click(function(){
		if($(".cmnt_followList dl a").length > 0){
			Jm.fn.redirect(Jm.getBasePath() + '/');
		}else{
			$.dialog.tips("请先选择关注的小区");
		}
	});
});