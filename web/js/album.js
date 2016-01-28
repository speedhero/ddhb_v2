// 代码整理：懒人之家 www.lanrenzhijia.com
function initAlbum(picIndex, flag) {
	function G(s) {
		return document.getElementById(s);
	}
	function getStyle(obj, attr) {
		if (obj.currentStyle) {
			return obj.currentStyle[attr];
		} else {
			return getComputedStyle(obj, false)[attr];
		}
	}
	function Animate(obj, json) {
		if (obj.timer) {
			clearInterval(obj.timer);
		}
		obj.timer = setInterval(function() {
			for ( var attr in json) {
				var iCur = parseInt(getStyle(obj, attr));
				iCur = iCur ? iCur : 0;
				var iSpeed = (json[attr] - iCur) / 5;
				iSpeed = iSpeed > 0 ? Math.ceil(iSpeed) : Math.floor(iSpeed);
				obj.style[attr] = iCur + iSpeed + 'px';
				if (iCur == json[attr]) {
					clearInterval(obj.timer);
				}
			}
		}, 30);
	}
	if (flag == undefined) {
		flag = "";
	}
	var oPic = G("picBox" + flag);
	var oList = G("listBox" + flag);

	var oPrevTop = G("prevTop" + flag);
	var oNextTop = G("nextTop" + flag);

	var oPicDiv = document.getElementById("picBox" + flag);
	var oListDiv = document.getElementById("listBox" + flag);
	var oPicLi = oPic.getElementsByTagName("li");
	var oListLi = oList.getElementsByTagName("li");
	var len1 = oPicLi.length;
	var len2 = oListLi.length;

	var oPicUl = oPic.getElementsByTagName("ul")[0];
	var oListUl = oList.getElementsByTagName("ul")[0];
	var w1 = oPicDiv.offsetWidth;
	var w2 = oListLi[0].offsetWidth;
	var w3 = oListDiv.offsetWidth / 5;
	var h1 = oListDiv.offsetHeight;
	oPicUl.style.width = w1 * len1 + "px";
	oListUl.style.width = (w3 + 12) * len2 + "px";

	for (var i = 0; i < len1; i++) {
		var tmp = $(oPicLi[i]);
		var tmp2 = $(oListLi[i]);
		tmp.css("width", w1);
		tmp2.css("width", w3);
		tmp2.css("height", h1);
	}

	var index = 0;

	var num = 4;
	var num2 = Math.ceil(num / 2);
	Change(picIndex);
	function Change(picIndex) {
		if (picIndex != 0) {
			index = picIndex - 1;
		}
		Animate(oPicUl, {
			left : -index * w1
		});
		if (index < num2) {
			Animate(oListUl, {
				left : 0
			});
		} else if (index + num2 <= len2) {
			Animate(oListUl, {
				left : -(index - num2 + 1) * Math.round(w3)
			});
		} else {
			Animate(oListUl, {
				left : -(len2 - num + 1) * Math.round(w3)
			});
		}

		for (var i = 0; i < len2; i++) {
			oListLi[i].className = "";
			if (i == index) {
				oListLi[i].className = "on";
			}
		}
	}
	oNextTop.onclick = function() {
		index++;
		index = index == len2 ? 0 : index;
		Change(0);
	}
	oPrevTop.onclick = function() {
		index--;
		index = index == -1 ? len2 - 1 : index;
		Change(0);
	}
	for (var i = 0; i < len2; i++) {
		oListLi[i].index = i;
		oListLi[i].onclick = function() {
			index = this.index;
			Change(0);
		}
	}
}
// 代码整理：懒人之家 www.lanrenzhijia.com
