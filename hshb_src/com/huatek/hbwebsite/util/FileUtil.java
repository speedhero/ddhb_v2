/**
 * 
 */
package com.huatek.hbwebsite.util;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 * 
 */
public class FileUtil {
	private static final Logger log = Logger.getLogger(FileUtil.class);
	
	/**
	 * 检测目录是否存在，不存在则新建
	 * @param path
	 * @return
	 */
	public static boolean checkAndCreateDir(String path) {
		File file = new File(path);
		try {
			if (!file.exists()) {
				try {
					if (!file.mkdirs())
						throw new IOException("创建目录失败！");
				} catch (SecurityException ex) {
					throw new IOException("创建目录失败，没有权限创建目录。", ex);
				}
			} else if (!file.isDirectory()) { throw new IOException("指定的目录不存在，存在一个同名的文件。"); }
			return true;
		} catch (IOException ex) {
			log.error(ex);
		}
		return false;
	}
}
