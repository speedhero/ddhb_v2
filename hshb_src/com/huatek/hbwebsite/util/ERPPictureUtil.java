package com.huatek.hbwebsite.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import cn.hshb.web.house.enums.EnumHouseType;

public class ERPPictureUtil {
	private static final Logger log = Logger.getLogger(ERPPictureUtil.class);
	
	private static final com.huatek.framework.util.Parameter PARAM = com.huatek.framework.util.Parameter.getInstance();

	
	
	/**
	 * 根据Base64格式的数据生成房源图片
	 * @param picUrl 图片地址    已修改   imgStr	图片数据
	 * 
	 * @param houseType		房源类型
	 * @param picFommat		图片格式
	 * @return
	 */
	public static String GenerateImage(String picUrl, EnumHouseType houseType, String picFommat) {
		
		if (picUrl == null) {
			return "";
		} else {
			try {
				//拼接路径
				String uploadPath = PARAM.getProperty("picture.upload.path");

				String urlPath = "";
				String fileName = UUID.randomUUID().toString();
				uploadPath = uploadPath + "/erpHousePicture";
				urlPath = urlPath + "/erpHousePicture";
				//if ("1".equals(houseType)) {
				if (EnumHouseType.SALE == houseType) {
					uploadPath = uploadPath + "/houseSecond";
					urlPath = urlPath + "/houseSecond";
				} else {
					uploadPath = uploadPath + "/houseRent";
					urlPath = urlPath + "/houseRent";
				}
				if(!FileUtil.checkAndCreateDir(uploadPath)){
					throw new IOException("目录["+uploadPath+"]创建失败。");
				}
				
				//访问图片Url
				HttpClient httpclient = null;
				HttpGet httpGet = null;

				// 设置超时
				// 创建HttpParams以用来设置HTTP参数
				HttpParams params = new BasicHttpParams();
				// 设置重定向,缺省为true
				HttpClientParams.setRedirecting(params, false);
				httpclient = new DefaultHttpClient(params);
				// 建立一个get方法请求,提交刷新
				httpGet = new HttpGet(picUrl);
				HttpResponse response = httpclient.execute(httpGet);
				// HttpStatus.SC_OK（即:200）服务器收到并理解客户端的请求而且正常处理了
				if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
					// 对象呼叫中止
					httpGet.abort();
					log.error("获取不到服务器上的图片:" + picUrl);
					return "";
				}
				HttpEntity entity = response.getEntity();
				FileOutputStream out = new FileOutputStream(uploadPath + "/" + fileName + "." + picFommat);
				if (entity != null) {
					// start 读取整个页面内容
					InputStream is = entity.getContent();
					byte b[] = new byte[32*1024];
					int j = 0;
					while( (j = is.read(b)) != -1 )
			        {
						out.write(b,0,j);
			        }
				}
				
				out.flush();
				out.close();
				response = null;
				return urlPath + "/" + fileName + "." + picFommat;
			} catch (IOException ex) {
				log.error(ex);
				return "";
			}
		}
	}
	public static void main(String[] args){
		String picUrl = "https://www.baidu.com/img/bd_logo11.png";
		String generationUrl = GenerateImage(picUrl,EnumHouseType.SALE,"png");
		System.out.println(generationUrl);
	}
}
