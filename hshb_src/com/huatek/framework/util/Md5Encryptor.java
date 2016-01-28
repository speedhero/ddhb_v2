package com.huatek.framework.util;

import com.huatek.framework.exception.BusinessRuntimeException;
import com.huatek.framework.util.EncryptService;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Encryptor implements EncryptService {
	private final String[] hexDigits = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c",
			"d", "e", "f" };

	public String encrypt(String originalStr) {
		String resultString = null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = this.byteArrayToHexString(md.digest(originalStr.getBytes()));
		} catch (NoSuchAlgorithmException var4) {
			;
		}

		return resultString;
	}

	private String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();

		for (int i = 0; i < b.length; ++i) {
			resultSb.append(this.byteToHexString(b[i]));
		}

		return resultSb.toString();
	}

	private String byteToHexString(byte b) {
		int n = b;
		if (b < 0) {
			n = b + 256;
		}

		int d1 = n / 16;
		int d2 = n % 16;
		return this.hexDigits[d1] + this.hexDigits[d2];
	}

	public String decrypt(String decryptString) {
		throw new BusinessRuntimeException("Not implements");
	}
	
	public static void main(String[] args){
		Md5Encryptor d = new Md5Encryptor();
		String s = d.encrypt("111111");
		String s_1 = d.decrypt(s);
	}
}
