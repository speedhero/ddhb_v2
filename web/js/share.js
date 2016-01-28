	//分享 创建一个分享按钮对应的链接地址现在只添加了腾讯微博和新浪微博
	var shareIco = {
			"tqq": "http://v.t.qq.com/share/share.php?title={title}&url={url}",
			"tsina":"http://service.weibo.com/share/share.php?title={title}&url={url}",
			"trrw":"http://widget.renren.com/dialog/share?resourceUrl={url}&srcUrl={url}&title={title}&description=",
			"tdbw":"http://www.douban.com/share/service?href={url}&name={title}&text="
	};
	var shareIcoName = {
			"tqq":"腾讯微博",
			"tsina":"新浪微博",
			"trrw":"人人网",
			"tdbw":"豆瓣网"
	};
	
	function shareInformation(key){
		//店铺名店铺链接地址
		var content = $("#shopName").attr("value");
		var shopUrl = $("#broderWebShop").attr("value");
		content += "【" + $("#broderWebShop").attr("value") +  "】"; 
		//利用正则表达式 把值给替换
		window.open(formatModel(shareIco[key], {title:content,url:shopUrl}));
	}
	
	function formatModel(str,model){
		for(var k in model){
			var re = new RegExp("{" + k + "}", "g");
			str = str.replace(re, model[k]);
		}
		return str;
	}