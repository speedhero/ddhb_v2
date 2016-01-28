/**
 * 
 */
package cn.hshb.web.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.hshb.web.common.exception.HshbException;

/**
 * @author ShengYoufu
 *
 */
public class ShortUrlUtil {
	private static final Log log = LogFactory.getLog(ShortUrlUtil.class);
	
	/**
	 * 生成短网址
	 * @param longURL
	 * @return
	 */
	public static String getShortURL(String longURL){
		String _sUrl = longURL;
		Boolean isError = false;
		try{
			_sUrl = getHSHBShortURL(longURL);
		}catch(Exception ex){
			log.error(ex);
			isError = true;
		}
		if(isError){
			try{
				_sUrl = getSinaShortURL(longURL);
			}catch(Exception ex){
				log.error(ex);
				isError = true;
			}
		}
		if(isError){
			try{
				_sUrl = getBaiduShortURL(longURL);
			}catch(Exception ex){
				log.error(ex);
				isError = true;
			}
		}
		if(isError){
			try{
				_sUrl = getIsGdShortURL(longURL);
			}catch(Exception ex){
				log.error(ex);
				isError = true;
			}
		}
		return _sUrl;
	}
	
	/**
	 * 判断一个字符串是否有效的HTTP地址或FTP地址
	 * @param url
	 * @return
	 */
    public static Boolean isValidURL(String url) {
        if (StringUtil.isEmpty(url)) return false;
        Pattern p =  Pattern.compile("^(http(s)?|ftp)://[^/\\?&]+(:[0-9]+)?(/[\\w- ./?%&=]*)?", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(url);
        return m.find();
    }
    
	/**
	 * 调用 豪世华邦自己的短网址接口
	 * @param longURL
	 * @return
	 * @throws HshbException 
	 */
	public static String getHSHBShortURL(String longURL) throws HshbException{
        String _dwzUrl = "http://s.hshb.cn/shorturl.jsp";
        String _userAgent = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; MALN; CIBA; InfoPath.2; .NET4.0C; .NET4.0E; Media Center PC 6.0; Tablet PC 2.0; AskTB5.6)";

        String _sUrl = longURL;
        String _reqUrl = _dwzUrl + "?url=" + longURL;
        try{
	        String respStr = HttpConnectionUtil.get(_reqUrl);
	        Pattern p =  Pattern.compile("\"url_short\": *\"([^\"]+)\"");
	        Matcher m = p.matcher(respStr);
	        if(m.find()){
	        	String sTmp = m.group(1);
	        	if(StringUtil.isNotEmpty(sTmp)){
	        		 _sUrl = sTmp;
	        	}
	        }
        }catch(Exception ex){
        	throw new HshbException(ex);
        }
        return _sUrl;
	}
	

	/**
	 * 通过新浪短网址服务生成短网址<br/>
	 * 请求格式：https://api.weibo.com/2/short_url/shorten.json?source=2043051649&url_long=http%3a%2f%2fzhidao.baidu.com%2flink%3furl%3dVuoMx1FBtNB712SRV8JBiczrfrtpbNjeBa3KdgmR7C8aNI1f8yPIvYf5y1Zn3hWumTsSeokHuvRLIEigEl73Oa<br/>
		    返回格式
		  {
		     "urls": [{
		     "object_type": "",
		     "result": true,
		     "url_short": "http://t.cn/RhUrooq",
		     "object_id": "",
		     "url_long": "http://zhidao.baidu.com/link?url=VuoMx1FBtNB712SRV8JBiczrfrtpbNjeBa3KdgmR7C8aNI1f8yPIvYf5y1Zn3hWumTsSeokHuvRLIEigEl73Oa",
		     "type": 0
		   }]
		  }
	 * @param longURL
	 * @return
	 * @throws HshbException 
	 */
    public static String getSinaShortURL(String longURL) throws HshbException {
    	String _dwzUrl = "https://api.weibo.com/2/short_url/shorten.json";
        String _sUrl = longURL;

        String _userAgent = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; MALN; CIBA; InfoPath.2; .NET4.0C; .NET4.0E; Media Center PC 6.0; Tablet PC 2.0; AskTB5.6)";
        String _reqUrl = _dwzUrl + "?source=2043051649&url_long=" + longURL;
        try{
        	String respStr = HttpConnectionUtil.get(_reqUrl);
	        Pattern p =  Pattern.compile("\"url_short\": *\"([^\"]+)\"");
	        Matcher m = p.matcher(respStr);
	        if(m.find()){
	        	String sTmp = m.group(1);
	        	if(StringUtil.isNotEmpty(sTmp)){
	        		 _sUrl = sTmp;
	        	}
	        }
        }catch(Exception ex){
        	throw new HshbException(ex);
        }
        return _sUrl;
    }
    
    /**
     * 把长域名置换成百度短域名<br/>
     * 百度短域名返回结果格式 ：<br/>
	{
		"tinyurl":"http:\/\/dwz.cn\/1ypiQ9",
		"status":0,
		"longurl":"http://www.hshb.cn/broker/initQuestionDetail/0357bd86-2ce8-45a8-97de-fbf56a2a14a4/1/6837bf81-a34c-49d4-a9c2-dd3a2f211927",
		"err_msg":""
	}    
     * @param longURL
     * @return
     * @throws HshbException 
     */
    public static String getBaiduShortURL(String longURL) throws HshbException {
        String _dwzUrl = "http://dwz.cn/create.php";
        String _userAgent = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; MALN; CIBA; InfoPath.2; .NET4.0C; .NET4.0E; Media Center PC 6.0; Tablet PC 2.0; AskTB5.6)";
        String _sUrl = longURL;
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("url", longURL);
        try{
        	String respStr = HttpConnectionUtil.postForm(_dwzUrl, params, "UTF-8", "UTF-8");
	        Pattern p =  Pattern.compile("\"tinyurl\": *\"([^\"]+)\"");
	        Matcher m = p.matcher(respStr);
	        if(m.find()){
	        	String sTmp = m.group(1);
	        	if(StringUtil.isNotEmpty(sTmp)){
	        		sTmp = sTmp.replace("\\", "");
	        		 _sUrl = sTmp;
	        	}
	        }
        }catch(Exception ex){
        	throw new HshbException(ex);
        }
        return _sUrl;
    }
    
    /**
     * 调用http://is.gd/ 生成短域名 
     * @param longURL
     * @return
     * @throws HshbException 
     */
    public static String getIsGdShortURL(String longURL) throws HshbException {
        String _dwzUrl = "http://is.gd/create.php";
        String _sUrl = longURL;
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("url", longURL);
        try{
        	String respStr = HttpConnectionUtil.postForm(_dwzUrl, params, "UTF-8", "UTF-8");
	        Pattern p =  Pattern.compile("Your shortened URL is:</b></p><input type=\"text\" class=\"tb\" id=\"short_url\" value=\"([^\"]+)\"");
	        Matcher m = p.matcher(respStr);
	        if(m.find()){
	        	String sTmp = m.group(1);
	        	if(StringUtil.isNotEmpty(sTmp)){
	        		 _sUrl = sTmp;
	        	}
	        }
        }catch(Exception ex){
        	throw new HshbException(ex);
        }
        return _sUrl;
    }
    
    public static void main(String[] args){
    	String lUrl = "http://www.hshb.cn/broker/initQuestionDetail/0357bd86-2ce8-45a8-97de-fbf56a2a14a4/1/6837bf81-a34c-49d4-a9c2-dd3a2f211927";
    	try{
	    	String sUrl = ShortUrlUtil.getHSHBShortURL(lUrl);
	    	System.out.println(sUrl);
	    	sUrl = ShortUrlUtil.getSinaShortURL(lUrl);
	    	System.out.println(sUrl);
	    	sUrl = ShortUrlUtil.getBaiduShortURL(lUrl);
	    	System.out.println(sUrl);
	    	sUrl = ShortUrlUtil.getIsGdShortURL(lUrl);
	    	System.out.println(sUrl);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
}
