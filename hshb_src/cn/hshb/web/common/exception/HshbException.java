/**
 * 
 */
package cn.hshb.web.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 华邦网站异常基类
 * @author Sheng Youfu
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 * 
 */
public class HshbException extends Exception {
	private static final long serialVersionUID = 3609867157722889338L;

	public HshbException(){
		super();
	}

	public HshbException(String message){
		super(message);
	}
	
	public HshbException(String message, Throwable cause){
		super(message, cause);
	}
	
	public HshbException(Throwable cause){
		super(cause);
	}

	/**
	 * 把异常堆栈转换成字符串
	 * @return
	 */
	public String getStackTraceAsString(){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			if(getCause()!=null){
				getCause().printStackTrace(pw);
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
