package com.huatek.framework.sso;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class ApiSignUtil {
	public static String md5Signature(TreeMap<String, String> params, String secret) {
		String result = null;
		StringBuffer orgin = getBeforeSign(params, new StringBuffer(secret));
		if (orgin == null) {
			return result;
		} else {
			orgin.append(secret);

			try {
				MessageDigest e = MessageDigest.getInstance("MD5");
				result = byte2hex(e.digest(orgin.toString().getBytes("utf-8")));
				return result;
			} catch (Exception var5) {
				throw new RuntimeException("sign error !");
			}
		}
	}

	private static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		String stmp = "";

		for (int n = 0; n < b.length; ++n) {
			stmp = Integer.toHexString(b[n] & 255);
			if (stmp.length() == 1) {
				hs.append("0").append(stmp);
			} else {
				hs.append(stmp);
			}
		}

		return hs.toString().toUpperCase();
	}

	private static StringBuffer getBeforeSign(TreeMap<String, String> params, StringBuffer orgin) {
		if (params == null) {
			return null;
		} else {
			TreeMap treeMap = new TreeMap();
			treeMap.putAll(params);
			Iterator iter = treeMap.keySet().iterator();

			while (iter.hasNext()) {
				String name = (String) iter.next();
				orgin.append(name).append((String) params.get(name));
			}

			return orgin;
		}
	}

	public static String hmacSignature(TreeMap<String, String> params, String secret) {
		String result = null;
		StringBuffer orgin = getBeforeSign(params, new StringBuffer());
		if (orgin == null) {
			return result;
		} else {
			try {
				result = byte2hex(encryptHMAC(orgin.toString().getBytes("utf-8"), secret));
				return result;
			} catch (Exception var5) {
				throw new RuntimeException("sign error !");
			}
		}
	}

	public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("utf-8"), "HmacMD5");
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);
		return mac.doFinal(data);
	}
}
