;(function($){
	$(document).ready(function(){
		$('.topbar .quickMenu,.topbar .currLocation').hide(); // 隐藏用户菜单
		var validator = $("#register-form").validate({
			childHtml: '<i class="i_error"></i>',
			// debug:true,
            rules: {
                    "User[email]": {
                        required: true ,
                        email:true,
                        remote:{
                            url: Jm.getBasePath() + "/register/validate",
                            type: "post",
                            dataType: "json",
                            data: {
                                email: function() {return $("#email").val();}
                            }
                        }
                    },
                    "User[invitionCode]": {
                        remote:{
                            url: Jm.getBasePath() + "/register/InvitationCode",
                            type: "post",
                            dataType: "json",
                            data: {
                                invitionCode: function() {return $("#invitionCode").val();}
                            }
                        }
                    },
                    "User[nickname]": {
                        required: true ,
                        minlength: function(){
							return Jm.fn.isGBKword($("#User_nickname").val()) ? 2 : 4;
                        },
                        remote:{
                            url: Jm.getBasePath() + "/register/validate",
                            type: "post",
                            dataType: "json",
                            data: {
                                user_name: function() {return $("#username").val();}
                            }
                        }
                    },
                    "User[password]": {
                        required: true,
                        minlength: 6
                    },
                    "User[repassword]": {required: true , equalTo: "#User_password"},
                    "User[verifyCode]": {required: true},
            },
            messages: {
            	"User[email]": {required: "请输入邮箱" , remote:"该邮箱已经被注册过了" , email:"邮箱格式不正确"},
            	"User[nickname]": {required: "请输入用户名" , remote:"该用户名已经被注册了" , minlength:"英文昵称必须大于4，中文昵称必须大于2"},
            	"User[password]": {required: "请输入密码" , minlength:"密码长度不能小于6位"},
            	"User[repassword]": {required: "请再输入密码" , equalTo:"两次输入的密码不相同"},
                "User[invitionCode]": {required: "请输入邀请码" , remote:"邀请码无效"},
                "User[verifyCode]": {required: "请输入验证码"}
            },
            errorPlacement: function(label, element) {
	        	// position error label after generated textarea
            	label.insertAfter(element);
	        }
        });
		//如果强制需要邀请码，则必需
		 if($("#isInvite").val()==1){
			 $("#User_invitionCode").rules('add',{required:true}); 
		 }
	});
})(jQuery);