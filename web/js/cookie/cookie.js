/**
 * jQuery cookie功能扩展
 * 
 * name	要设置的cookie名称
 * value 要设置的cookie值，传入值时，表示设置cookie，不传值时，表示取cookie值
 * options 参数对象数据
 * 	options.expires 过期时间(天)或过期日期(date)
 * 	options.path cookie的path
 * 	options.domain cookie的domain
 * 	options.secure cookie的secure
 */
$.cookie=function(name,value,options){  
  if(typeof value!='undefined'){
    options=options||{};  
    if(value===null){  
      value='';  
      options.expires=-1;  
    }
    var expires='';  
    if(options.expires&&(typeof options.expires=='number'||options.expires.toUTCString)){
      var date;
      if(typeof options.expires=='number'){  
         date=new Date();
         date.setTime(date.getTime()+(options.expires * 24 * 60 * 60 * 1000));
       }else{
         date=options.expires;
      }
      expires=';expires='+date.toUTCString();
     }
    var path=options.path?';path='+options.path:'';
    var domain=options.domain?';domain='+options.domain:'';
    var secure=options.secure?';secure':'';
    document.cookie=[name,'=',encodeURIComponent(value),expires,path,domain,secure].join('');
  }else{
    var cookieValue=null;
    if(document.cookie&&document.cookie!=''){
      var cookies=document.cookie.split(';');
      for(var i=0;i<cookies.length;i++){
        var cookie=jQuery.trim(cookies[i]);
        if(cookie.substring(0,name.length+1)==(name+'=')){
          cookieValue=decodeURIComponent(cookie.substring(name.length+1));
          break;
        }
      }
    }
    return cookieValue;  
  }
};


/**
 * 该文件主要是用来获取cookie的操作的
 */
//添加cookie
function setCookie(name,value, expireDays){
    var Days = expireDays || 30;
    var exp = new Date(); 
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

//取得cookie的值
function getCookie(name, defaultVal){
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg)) 
    	return unescape(arr[2]);
    else 
    	return defaultVal || null;
}

//删除cookie
function delCookie(name){
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}