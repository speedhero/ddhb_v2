/**
 * 
 */
package cn.hshb.web.test;

import com.huatek.framework.util.DESEncryptor;

/**
 * @author Administrator
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 * 
 */
public class PasswdGen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DESEncryptor d = new DESEncryptor();
		String s = d.encrypt("root");
		d.decrypt(s);

		String s1 = d.encrypt("hbweb!2#4");
		System.out.println(s1);
		String s1_1 = d.decrypt(s1);
		System.out.println(s1_1);
	}

}
