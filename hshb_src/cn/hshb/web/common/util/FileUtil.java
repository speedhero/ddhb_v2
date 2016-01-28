/**
 * 
 */
package cn.hshb.web.common.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;

import com.huatek.framework.util.Parameter;

/**
 * @author Administrator
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 * 
 */
public class FileUtil extends FileUtils {
	private static final Parameter PARAM = Parameter.getInstance();
	public static final String DATASYNC_XML_SAVE_PATH;
	public static Boolean IS_SAVE_XML = false;
	static {
		if (PARAM == null) {
			throw new RuntimeException("can not init system properties util.");
		} else {
			DATASYNC_XML_SAVE_PATH = PARAM.getProperty("datasync.xml.save.path");
			String isSaveXML = PARAM.getProperty("save.datasync.xml");
			if(!StringUtil.isEmpty(isSaveXML) 
					&& (isSaveXML.toUpperCase().startsWith("T") || isSaveXML.toUpperCase().startsWith("Y") || isSaveXML.startsWith("1")))
				IS_SAVE_XML = true;
		}
	}
	
	/**
	 * 把同步的XML内容保存到硬盘上，成功后返回保存路径 
	 * @param fileName
	 * @param fileContent
	 * @return
	 * @throws IOException
	 */
	public static String saveDataSyncXML(String fileName, String fileContent) throws IOException{
		if(!IS_SAVE_XML) return "";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String strNameTime = mDateFormat.format(calendar.getTime());
		String year = strNameTime.substring(0, 4);
		String month = strNameTime.substring(4, 6);
		if(!fileName.toLowerCase().endsWith(".xml"))
			fileName += ".xml";
		String strFilePath = "/" + DATASYNC_XML_SAVE_PATH + "/" + year + "/" + month + "/" + fileName;
		writeStringToFile(new File(strFilePath), fileContent, "UTF-8", false);
		return strFilePath;
	}
		
}
