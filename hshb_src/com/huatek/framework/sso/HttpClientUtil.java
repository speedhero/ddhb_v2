package com.huatek.framework.sso;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientUtil {
	private static final Logger LOGGER = Logger.getLogger(HttpClientUtil.class);
	public static final Gson GSON = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	public static final String VERSION = "1.0";

	public static String getMethod(String url, String jsessionId) throws Exception {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter("http.connection.timeout", Integer.valueOf('\uea60'));
		httpclient.getParams().setParameter("http.socket.timeout", Integer.valueOf('\uea60'));
		HttpGet httpget = new HttpGet(url);
		if (jsessionId != null) {
			httpget.setHeader("Cookie", "JSESSIONID=" + jsessionId);
		}

		String var5;
		try {
			var5 = (String) httpclient.execute(httpget, new BasicResponseHandler());
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return var5;
	}

	public static String postMethod(String url, List<NameValuePair> data, String jsessionId) throws Exception {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter("http.connection.timeout", Integer.valueOf('\uea60'));
		httpclient.getParams().setParameter("http.socket.timeout", Integer.valueOf('\uea60'));
		HttpPost httpost = new HttpPost(url);
		if (jsessionId != null) {
			httpost.setHeader("Cookie", "JSESSIONID=" + jsessionId);
		}

		if (data != null) {
			httpost.setEntity(new UrlEncodedFormEntity(data, "UTF-8"));
		}

		try {
			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String var8 = EntityUtils.toString(entity);
				return var8;
			}
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return null;
	}

	public static String trimJSONSata(String data) {
		return data.indexOf("{") >= 0 && data.indexOf("}") >= 0 ? (data.startsWith("{") && data.endsWith("}") ? data : data
				.substring(data.indexOf("{") - 1, data.lastIndexOf("}") + 1)) : data;
	}

	public static String serializableEntity(Object obj) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		objectOutputStream.writeObject(obj);
		String serStr = byteArrayOutputStream.toString("ISO-8859-1");
		serStr = URLEncoder.encode(serStr, "UTF-8");
		objectOutputStream.close();
		byteArrayOutputStream.close();
		return serStr;
	}

	public static Object unSerializeableEntity(String objString) throws IOException, ClassNotFoundException {
		String redStr = URLDecoder.decode(objString, "UTF-8");
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(redStr.getBytes("ISO-8859-1"));
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
		objectInputStream.close();
		byteArrayInputStream.close();
		return objectInputStream.readObject();
	}
}
