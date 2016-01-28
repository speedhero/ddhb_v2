$(function(){
//鼠标划入时，选中以及以前的星星变亮
$('.new_score_1h_main2').live('mouseover', function (){
		//$(this).parent().removeClass('selected'); //移除选中标示
		$(this).parent().children('div').children('img').attr('src', '/themes/new2013/files/common/images/star4.png'); //移除当前class的同级
		for(var i=1;i<=$(this).index();i++){
			$(this).parent().children('div').eq(i).children('img').eq(0).attr('src', '/themes/new2013/files/common/images/star3.png');
		}
		if($(this).siblings('.new_score_1h_main3_1').attr('value')>=1){
			//根据当前的类型展示文字
			switch($(this).parent().index()){
				case 2:
					 switch($(this).siblings('.new_score_1h_main3_1').attr('value')) {
						case '2':
							$(this).siblings('.new_score_1h_main3_1').html('不理想：出行生活均不方便');
							break;
						case '3':
							$(this).siblings('.new_score_1h_main3_1').html('一般般：出行便利配套较少');
							break;
						case '4':
							$(this).siblings('.new_score_1h_main3_1').html(' 还不错：服务设施还算齐全');
							break;
						case '5':
							$(this).siblings('.new_score_1h_main3_1').html(' 非常棒：配套完善非常方便');
							break;
						case '1':
							$(this).siblings('.new_score_1h_main3_1').html(' 非常差：配套极差不宜居住');
							break;
					 }
					break;
				case 3:
					 switch($(this).siblings('.new_score_1h_main3_1').attr('value')) {
						case '2':
							$(this).siblings('.new_score_1h_main3_1').html('不理想：服务尚可速度有待提升');
							break;
						case '3':
							$(this).siblings('.new_score_1h_main3_1').html(' 一般般：服务质量不错态度一般');
							break;
						case '4':
							$(this).siblings('.new_score_1h_main3_1').html(' 还不错：整体服务水平值得肯定');
							break;
						case '5':
							$(this).siblings('.new_score_1h_main3_1').html(' 非常棒：物业管理完善服务到位');
							break;
						case '1':
							$(this).siblings('.new_score_1h_main3_1').html('非常差：服务质量极差态度恶劣');
							break;
					 }
					break;
				case 4:
					 switch($(this).siblings('.new_score_1h_main3_1').attr('value')) {
						case '5':
							$(this).siblings('.new_score_1h_main3_1').html(' 非常棒：设计合理使用率高');
							break;
						case '4':
							$(this).siblings('.new_score_1h_main3_1').html('还不错：户型实用采光较好');
							break;
						case '3':
							$(this).siblings('.new_score_1h_main3_1').html(' 一般般：比较一般缺乏亮点');
							break;
						case '2':
							$(this).siblings('.new_score_1h_main3_1').html('不理想：户型结构不太理想');
							break;
						case '1':
							$(this).siblings('.new_score_1h_main3_1').html(' 非常差：户型糟糕采光极差');
							break;
					 }
					break;
				case 5:
					 switch($(this).siblings('.new_score_1h_main3_1').attr('value')) {
						case '2':
							$(this).siblings('.new_score_1h_main3_1').html('不理想：车位紧张管理一般');
							break;
						case '3':
							$(this).siblings('.new_score_1h_main3_1').html('	一般般：车位紧张管理不错');
							break;
						case '4':
							$(this).siblings('.new_score_1h_main3_1').html('还不错：车位充足价格合理');
							break;
						case '5':
							$(this).siblings('.new_score_1h_main3_1').html(' 非常棒：车位充足管理很好');
							break;
						case '1':
							$(this).siblings('.new_score_1h_main3_1').html('非常差：车位紧张收费太高');
							break;
					 }
					break;
				default:
					 switch($(this).siblings('.new_score_1h_main3_1').attr('value')) {
							case '2':
								$(this).siblings('.new_score_1h_main3_1').html('不理想：环境差卫生更糟');
								break;
							case '3':
								$(this).siblings('.new_score_1h_main3_1').html(' 一般般，环境好卫生一般');
								break;
							case '4':
								$(this).siblings('.new_score_1h_main3_1').html(' 还不错，整体环境比较好');
								break;
							case '5':
								$(this).siblings('.new_score_1h_main3_1').html(' 非常棒！世外桃源般环境');
								break;
							case '1':
								$(this).siblings('.new_score_1h_main3_1').html('非常差：脏乱差无人管理');
								break;
					 }
					break;
				}
		}else{
		switch($(this).parent().index()){
			case 2:
				 switch($(this).index()) {
					case 2:
						$(this).siblings('.new_score_1h_main3_1').html('不理想：出行生活均不方便');
						break;
					case 3:
						$(this).siblings('.new_score_1h_main3_1').html('一般般：出行便利配套较少');
						break;
					case 4:
						$(this).siblings('.new_score_1h_main3_1').html(' 还不错：服务设施还算齐全');
						break;
					case 5:
						$(this).siblings('.new_score_1h_main3_1').html(' 非常棒：配套完善非常方便');
						break;
					default:
						$(this).siblings('.new_score_1h_main3_1').html(' 非常差：配套极差不宜居住');
						break;
				 }
				break;
			case 3:
				 switch($(this).index()) {
					case 2:
						$(this).siblings('.new_score_1h_main3_1').html('不理想：服务尚可速度有待提升');
						break;
					case 3:
						$(this).siblings('.new_score_1h_main3_1').html(' 一般般：服务质量不错态度一般');
						break;
					case 4:
						$(this).siblings('.new_score_1h_main3_1').html(' 还不错：整体服务水平值得肯定');
						break;
					case 5:
						$(this).siblings('.new_score_1h_main3_1').html(' 非常棒：物业管理完善服务到位');
						break;
					default:
						$(this).siblings('.new_score_1h_main3_1').html('非常差：服务质量极差态度恶劣');
						break;
				 }
				break;
			case 4:
				 switch($(this).index()) {
					case 5:
						$(this).siblings('.new_score_1h_main3_1').html(' 非常棒：设计合理使用率高');
						break;
					case 4:
						$(this).siblings('.new_score_1h_main3_1').html('还不错：户型实用采光较好');
						break;
					case 3:
						$(this).siblings('.new_score_1h_main3_1').html(' 一般般：比较一般缺乏亮点');
						break;
					case 2:
						$(this).siblings('.new_score_1h_main3_1').html('不理想：户型结构不太理想');
						break;
					default:
						$(this).siblings('.new_score_1h_main3_1').html(' 非常差：户型糟糕采光极差');
						break;
				 }
				break;
			case 5:
				 switch($(this).index()) {
					case 2:
						$(this).siblings('.new_score_1h_main3_1').html('不理想：车位紧张管理一般');
						break;
					case 3:
						$(this).siblings('.new_score_1h_main3_1').html('	一般般：车位紧张管理不错');
						break;
					case 4:
						$(this).siblings('.new_score_1h_main3_1').html('还不错：车位充足价格合理');
						break;
					case 5:
						$(this).siblings('.new_score_1h_main3_1').html(' 非常棒：车位充足管理很好');
						break;
					default:
						$(this).siblings('.new_score_1h_main3_1').html('非常差：车位紧张收费太高');
						break;
				 }
				break;
			default:
				 switch($(this).index()) {
						case 2:
							$(this).siblings('.new_score_1h_main3_1').html('不理想：环境差卫生更糟');
							break;
						case 3:
							$(this).siblings('.new_score_1h_main3_1').html(' 一般般，环境好卫生一般');
							break;
						case 4:
							$(this).siblings('.new_score_1h_main3_1').html(' 还不错，整体环境比较好');
							break;
						case 5:
							$(this).siblings('.new_score_1h_main3_1').html(' 非常棒！世外桃源般环境');
							break;
						default:
							$(this).siblings('.new_score_1h_main3_1').html('非常差：脏乱差无人管理');
							break;
				 }
				break;
			}
		}
});
//鼠标移除时清空所有的星星，如果用户做单击选择，鼠标移除时需保留用户的选项
$('.new_score_1h_main2').live('mouseleave', function(){

	$(this).parent().children('div').children('img').attr('src', '/themes/new2013/files/common/images/star4.png'); //移除当前class的同级
	if (!$(this).parent().hasClass('selected')){
		$(this).siblings('.new_score_1h_main3_1').html('');
	}else {
		for(var i=1;i<=$(this).siblings('.new_score_1h_main3_1').attr('value');i++){
			$(this).parent().children('div').eq(i).children('img').eq(0).attr('src', '/themes/new2013/files/common/images/star3.png');
		}
	}

});
//鼠标单击星星时，选中以及以前的星星变亮，显示提示信息
$('.new_score_1h_main2').live('click', function(){
	$(this).siblings('.new_score_1h_main3_1').attr('value', $(this).index()); //添加用户评分
	$(this).parent().addClass('selected'); //添加选中标示
	//根据当前的类型展示文字
	switch($(this).parent().index()){
		case 2:
			 switch(parseInt($(this).siblings('.new_score_1h_main3_1').attr('value'))) {
				case 2:
					$(this).siblings('.new_score_1h_main3_1').html('不理想：出行生活均不方便');
					break;
				case 3:
					$(this).siblings('.new_score_1h_main3_1').html('一般般：出行便利配套较少');
					break;
				case 4:
					$(this).siblings('.new_score_1h_main3_1').html(' 还不错：服务设施还算齐全');
					break;
				case 5:
					$(this).siblings('.new_score_1h_main3_1').html(' 非常棒：配套完善非常方便');
					break;
				default:
					$(this).siblings('.new_score_1h_main3_1').html(' 非常差：配套极差不宜居住');
					break;
			 }
			break;
		case 3:
			 switch(parseInt($(this).siblings('.new_score_1h_main3_1').attr('value'))) {
				case 2:
					$(this).siblings('.new_score_1h_main3_1').html('不理想：服务尚可速度有待提升');
					break;
				case 3:
					$(this).siblings('.new_score_1h_main3_1').html(' 一般般：服务质量不错态度一般');
					break;
				case 4:
					$(this).siblings('.new_score_1h_main3_1').html(' 还不错：整体服务水平值得肯定');
					break;
				case 5:
					$(this).siblings('.new_score_1h_main3_1').html(' 非常棒：物业管理完善服务到位');
					break;
				default:
					$(this).siblings('.new_score_1h_main3_1').html('非常差：服务质量极差态度恶劣');
					break;
			 }
			break;
		case 4:
			 switch(parseInt($(this).siblings('.new_score_1h_main3_1').attr('value'))) {
				case 5:
					$(this).siblings('.new_score_1h_main3_1').html(' 非常棒：设计合理使用率高');
					break;
				case 4:
					$(this).siblings('.new_score_1h_main3_1').html('还不错：户型实用采光较好');
					break;
				case 3:
					$(this).siblings('.new_score_1h_main3_1').html(' 一般般：比较一般缺乏亮点');
					break;
				case 2:
					$(this).siblings('.new_score_1h_main3_1').html('不理想：户型结构不太理想');
					break;
				default:
					$(this).siblings('.new_score_1h_main3_1').html(' 非常差：户型糟糕采光极差');
					break;
			 }
			break;
		case 5:
			 switch(parseInt($(this).siblings('.new_score_1h_main3_1').attr('value'))) {
				case 2:
					$(this).siblings('.new_score_1h_main3_1').html('不理想：车位紧张管理一般');
					break;
				case 3:
					$(this).siblings('.new_score_1h_main3_1').html('	一般般：车位紧张管理不错');
					break;
				case 4:
					$(this).siblings('.new_score_1h_main3_1').html('还不错：车位充足价格合理');
					break;
				case 5:
					$(this).siblings('.new_score_1h_main3_1').html(' 非常棒：车位充足管理很好');
					break;
				default:
					$(this).siblings('.new_score_1h_main3_1').html('非常差：车位紧张收费太高');
					break;
			 }
			break;
		default:
			 switch(parseInt($(this).siblings('.new_score_1h_main3_1').attr('value'))) {
					case 2:
						$(this).siblings('.new_score_1h_main3_1').html('不理想：环境差卫生更糟');
						break;
					case 3:
						$(this).siblings('.new_score_1h_main3_1').html(' 一般般，环境好卫生一般');
						break;
					case 4:
						$(this).siblings('.new_score_1h_main3_1').html(' 还不错，整体环境比较好');
						break;
					case 5:
						$(this).siblings('.new_score_1h_main3_1').html(' 非常棒！世外桃源般环境');
						break;
					default:
						$(this).siblings('.new_score_1h_main3_1').html('非常差：脏乱差无人管理');
						break;
			 }
			break;
		}
});

/*
//鼠标移除时清空所有的星星，如果用户做单击选择，鼠标移除时需保留用户的选项
$('.broder_plList li').live('mouseleave', function(){
	if (!$(this).parent().hasClass('selected')){
		if(!$(this).siblings('input').attr('value')) {
			$(this).parent().children('li').children('span').removeClass('star03');
			for(var i=0;i<=4;i++){
				$(this).parent().siblings('div').children('p').eq(i).css('display', 'none');
			}
		}else {
			$(this).parent().children('li').children('span').removeClass('star03');
			for(var i=0;i<$(this).siblings('input').attr('value');i++){
				$(this).parent().children('li').eq(i).children('span').addClass('star03');
			}
			$(this).parent().siblings('div').children('p').eq($(this).siblings('input').attr('value')-1).css('display', 'block');
		}
	}
});
//鼠标单击星星时，选中以及以前的星星变亮，显示提示信息
$('.broder_plList li').live('click', function(){
	for(var i=0;i<=$(this).index();i++){
		$(this).parent().siblings('div').children('p').css('display', 'none');
	}
	$(this).parent().siblings('div').children('p').eq($(this).index()).css('display', 'block');
	$(this).siblings('input').attr('value', $(this).index()+1); //添加用户评分
	$(this).parent().addClass('selected'); //添加选中标示
});
*/

});
