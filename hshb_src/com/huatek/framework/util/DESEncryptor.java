package com.huatek.framework.util;

import com.huatek.framework.util.EncryptService;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DESEncryptor implements EncryptService {
	public static final String ALGORITHM = "DES";
	private static String strDefaultKey = "huatek";
	private static final Log LOGGER = LogFactory.getLog(DESEncryptor.class);
	private SecretKey desKey;

	public DESEncryptor() {
		try {
			MessageDigest e = MessageDigest.getInstance("MD5");
			e.update(strDefaultKey.getBytes());
			byte[] pswKey = e.digest();
			DESKeySpec desKeySpec = new DESKeySpec(pswKey);
			SecretKeyFactory desKeyFac = SecretKeyFactory.getInstance("DES");
			this.desKey = desKeyFac.generateSecret(desKeySpec);
		} catch (Exception var5) {
			LOGGER.error(var5.getMessage());
		}
	}

	public String encrypt(String password) {
		String enStr = null;

		try {
			Cipher e = Cipher.getInstance("DES");
			e.init(1, this.desKey);
			byte[] enPassword = e.doFinal(password.getBytes("UTF8"));
			enStr = (new BASE64Encoder()).encode(enPassword);
		} catch (Exception var5) {
			LOGGER.error(var5.getMessage());
		}

		return enStr;
	}

	public String decrypt(String password) {
		String deStr = null;

		try {
			Cipher e = Cipher.getInstance("DES");
			e.init(2, this.desKey);
			byte[] dePassword = (new BASE64Decoder()).decodeBuffer(password);
			byte[] dec = e.doFinal(dePassword);
			deStr = new String(dec, "UTF8");
		} catch (Exception var6) {
			LOGGER.error(var6.getMessage());
		}

		return deStr;
	}

	public static void main(String[] args) throws Exception {
		DESEncryptor d = new DESEncryptor();

		String s1 = d.encrypt("hbweb!2#4");
		System.out.println(s1);
		String s1_1 = d.decrypt(s1);
		
	}
}
