/**
 * 
 */
package cn.hshb.web.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.huatek.framework.util.EntityUtil;


/**
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0 http://www.hshb.cn
 * 
 */
public class HttpClientTest {

	public static void test1() throws IOException	{
		HttpClient client = new DefaultHttpClient();
		HttpUriRequest request = new HttpPost("http://www.baidu.com/index.html");
		
		HttpResponse resp = client.execute(request);
		String contentEncoding = EntityUtils.getContentCharSet(resp.getEntity());
		String contentType = resp.getEntity().getContentType().getValue();
		Long contentLen = resp.getEntity().getContentLength();

//		InputStream is = resp.getEntity().getContent();
//		try {
//			BufferedReader reader = new BufferedReader(new InputStreamReader(is, contentEncoding));
//			System.out.println(reader.readLine());
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			is.close();
//		}
		
		//String content = EntityUtils.toString(resp.getEntity(), "UTF-8");
		String content = EntityUtils.toString(resp.getEntity());
		System.out.println(content);
		
		// 关闭连接.
	  client.getConnectionManager().shutdown();
	}

	
	public static void test2(){
		
	}
}
