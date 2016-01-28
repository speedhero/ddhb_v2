/**
 * 
 */
package cn.hshb.web.partner.baidu.common;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 * 
 */
public class StringUtil extends cn.hshb.web.common.util.StringUtil {
	public static final String HSHB_DOMAIN = "www.hshb.cn";
	public static final String HSHB_NAME = "豪世华邦";
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	private static final SimpleDateFormat ydf = new SimpleDateFormat("yyyy");
	private static final DecimalFormat df = new DecimalFormat("#0.00");
	
	public static String dateToStr(Date date){
		return sdf.format(date);
	}
	public static String yearToStr(Date date){
		return ydf.format(date);
	}
	
	public static String moneyToStr(double val){
		return df.format(val);
	}
	
}
