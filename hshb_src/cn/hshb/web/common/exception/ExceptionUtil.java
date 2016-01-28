/**
 * 
 */
package cn.hshb.web.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Administrator
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 * 
 */
public class ExceptionUtil {
	/**
	 * 把异常堆栈转换成字符串
	 * @return
	 */
	public static String getStackTraceAsString(Throwable cause){
		return getStackTrace(cause);
	}
	
	public static String getStackTrace(Throwable cause){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			if(cause!=null){
				cause.printStackTrace(pw);
				return sw.toString();
			}else{
				return "";
			}
		} catch (Exception ex) {
			return "bad getErrorInfoFromException";
		}finally{
			if(sw!=null) try{ sw.close(); } catch(Exception ex){}
			if(pw!=null) try{ pw.close(); } catch(Exception ex){}
		}
	}
}
