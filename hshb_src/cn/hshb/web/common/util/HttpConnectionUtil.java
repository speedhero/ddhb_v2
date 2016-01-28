/**
 * 
 */
package cn.hshb.web.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import cn.hshb.web.common.exception.HshbException;

/**
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0 http://www.hshb.cn
 * 
 */
public class HttpConnectionUtil {
	private final static Log logger = LogFactory.getLog(HttpConnectionUtil.class);

	
	/**
	 * 直接发送XML
	 * @param url
	 * @param xmlContent
	 * @param reqCharSet
	 * @param respCharSet
	 * @return
	 * @throws HshbException
	 */
	public static String postXML(String url, String xmlContent, String reqCharSet, String respCharSet) throws HshbException {
		return postXML(url, xmlContent, reqCharSet, respCharSet, false, 0, null, null);
	}
	
	/**
	 * 直接POST XML
	 * @param url
	 * @param xmlContent
	 * @return
	 */
	public static String postXML(String url, String xmlContent, String reqCharSet, String respCharSet, 
  		Boolean isHttps, int httpsPort, String keyStoreFile, String keyStoreKey) throws HshbException {
		BufferedReader reader = null;

		HttpClient httpclient = new DefaultHttpClient();
	    if(isHttps){
	    	Scheme sch = getSSLScheme(keyStoreFile, keyStoreKey, httpsPort);
				// 注册Scheme
				httpclient.getConnectionManager().getSchemeRegistry().register(sch);
	    }
	    
	    if(StringUtil.isEmpty(reqCharSet)) reqCharSet = "UTF-8";
	    if(StringUtil.isEmpty(respCharSet)) respCharSet = "UTF-8";
	    HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type", "text/xml; charset="+reqCharSet);

		try {
			StringEntity myEntity = new StringEntity(xmlContent, reqCharSet);
			httpPost.setEntity(myEntity);
			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				reader = new BufferedReader(new InputStreamReader(resEntity.getContent(), respCharSet));
				StringBuffer sb = new StringBuffer();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
					sb.append("\r\n");
				}
				return sb.toString();
			}

			// 另一种获取Response的方法
			// return EntityUtils.toString(resEntity);

		} catch (IOException ex) {
			throw new HshbException(ex);
		} finally {
			if (httpPost != null) {
				httpPost.abort();
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
			if (httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
		return "none";
	}


	/**
	 * 发送Post请求
	 * @param url
	 * @param params
	 * @param reqCharSet
	 * @param respCharSet
	 * @return
	 * @throws HshbException
	 */
	public static String postForm(String url, Map<String, String> params, String reqCharSet, String respCharSet) throws HshbException{
		return postForm(url, params, reqCharSet, respCharSet, false, 0, null, null);
	}

	/**
	 * 发送Post请求
	 * @param url
	 * @param params
	 * @param reqCharSet
	 * @param respCharSet
	 * @param isHttps
	 * @param httpsPort
	 * @param keyStoreFile
	 * @param keyStoreKey
	 * @return
	 * @throws HshbException
	 */
  public static String postForm(String url, Map<String, String> params, String reqCharSet, String respCharSet, 
  		Boolean isHttps, int httpsPort, String keyStoreFile, String keyStoreKey) throws HshbException {  
      // 创建默认的httpClient实例.  
      HttpClient httpclient = new DefaultHttpClient();  
      
      if(isHttps){
      	Scheme sch = getSSLScheme(keyStoreFile, keyStoreKey, httpsPort);
  			// 注册Scheme
  			httpclient.getConnectionManager().getSchemeRegistry().register(sch);
      }

      // 创建httppost  
      HttpPost httppost = new HttpPost(url);  
      // 创建参数队列  
      List<NameValuePair> formParams = new ArrayList<NameValuePair>();  
      for(Map.Entry<String, String> e: params.entrySet()){
      	formParams.add(new BasicNameValuePair(e.getKey(), e.getValue()));
      }
      
      if(StringUtil.isEmpty(reqCharSet)) reqCharSet = "UTF-8";
      if(StringUtil.isEmpty(respCharSet)) respCharSet = "UTF-8";
      UrlEncodedFormEntity uefEntity;  
      try {  
          uefEntity = new UrlEncodedFormEntity(formParams, reqCharSet);  
          httppost.setEntity(uefEntity);  
          logger.debug("executing request " + httppost.getURI());
          HttpResponse response;  
          response = httpclient.execute(httppost);  
          HttpEntity entity = response.getEntity();  
          if (entity != null) {
          	return EntityUtils.toString(entity, respCharSet);  
          }  
      } catch (ClientProtocolException ex) {  
      	throw new HshbException(ex);
      } catch (UnsupportedEncodingException ex) {  
      	throw new HshbException(ex);
      } catch (IOException ex) {  
      	throw new HshbException(ex);
      } finally {  
          // 关闭连接,释放资源  
          httpclient.getConnectionManager().shutdown();  
      }
      return null;
  }  

  /**
   * 发送get请求
   * @param url
   * @return
   * @throws HshbException
   */
  public static String get(String url) throws HshbException{
  	return get(url, false, 0, null, null);
  }
  /**
   * 发送get请求
   * @param url
   * @param isHttps
   * @param httpsPort
   * @param keyStoreFile
   * @param keyStoreKey
   * @return
   * @throws HshbException
   */
	public static String get(String url, Boolean isHttps, int httpsPort, String keyStoreFile, String keyStoreKey) throws HshbException {
		HttpClient httpclient = new DefaultHttpClient();
		
	    if(isHttps){
	    	Scheme sch = getSSLScheme(keyStoreFile, keyStoreKey, httpsPort);
				// 注册Scheme
				httpclient.getConnectionManager().getSchemeRegistry().register(sch);
	    }
		// 创建httpget.
		HttpGet httpget = new HttpGet(url);
		logger.debug("executing request " + httpget.getURI());

		try {
			// 执行get请求.
			HttpResponse response = httpclient.execute(httpget);
			// 获取响应实体
			HttpEntity entity = response.getEntity();
			// 打印响应状态
			logger.debug(response.getStatusLine());
			if (entity != null) {
				// 打印响应内容长度
				logger.debug("Response content length: " + entity.getContentLength());
				// 打印响应内容
				return EntityUtils.toString(entity);
			}
		} catch (ClientProtocolException ex) {
			throw new HshbException(ex);
		} catch (ParseException ex) {
			throw new HshbException(ex);
		} catch (IOException ex) {
			throw new HshbException(ex);
		} finally {
			// 关闭连接,释放资源
			httpclient.getConnectionManager().shutdown();
		}
		return null;
	}
	
	/**
	 * 构造SSL Scheme
	 * @param keystoreFile	keyStore文件路径
	 * @param keystoreKey	keyStore的密码
	 * @param port				https端口
	 * @return
	 */
	public static Scheme getSSLScheme(String keystoreFile, String keystoreKey, int port) throws HshbException{
		FileInputStream instream = null;
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			instream = new FileInputStream(new File(keystoreFile));
			
			// 加载keyStore d:\\tomcat.keystore
			trustStore.load(instream, keystoreKey.toCharArray());

			// 穿件Socket工厂,将trustStore注入
			SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);
			// 创建Scheme
			Scheme sch = new Scheme("https", port, socketFactory);

			return sch;
		}catch(IOException ex){
			throw new HshbException(ex);
		} catch (KeyStoreException ex) {
			throw new HshbException(ex);
		} catch (KeyManagementException ex) {
			throw new HshbException(ex);
		} catch (UnrecoverableKeyException ex) {
			throw new HshbException(ex);
		} catch (NoSuchAlgorithmException ex) {
			throw new HshbException(ex);
		} catch (CertificateException ex) {
			throw new HshbException(ex);
		}finally{
			try {
				instream.close();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
	}
	

	public static void main(String[] args) {
		String url = "http://localhost:8080/ddhb/postxml";
		String soap = "<xml>\r\n" + "<body>\r\n" + "传递过来的内容\r\n" + "</body>\r\n" + "</xml>";
		try{
			System.out.println(postXML(url, soap, "UTF-8", "UTF-8"));
		}catch(HshbException ex){
			ex.printStackTrace();
		}
		
	}
}
