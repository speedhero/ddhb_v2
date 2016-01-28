/**
 * 
 */
package cn.hshb.web.tags;

import java.util.Collection;
import java.util.Map;

import cn.hshb.web.common.util.StringUtil;

/**
 * @author Administrator
 *
 */
public class FunctionUtil {
	public static boolean contains(Collection<?> coll, Object o) {
		return coll.contains(o);
	}
	
	public static boolean containsKey(Map<Object, Object> map, Object key) {
		return map.containsKey(key);
	}
	
	public static boolean containsValue(Map<Object, Object> map, Object value) {
		return map.containsValue(value);
	}
	
	public static boolean containsEntry(Map<Object, Object> map, Object key, Object value) {
		if(containsKey(map, key)){
			return map.get(key).equals(value);
		}
		return false;
	}
	
	public static String concat(String str1, String str2) {
		if(StringUtil.isEmpty(str1)) str1 = "";
		if(StringUtil.isEmpty(str2)) str2 = "";
		return str1 + str2;
	}
	
	public static boolean isEmpty(String value) {
		return StringUtil.isEmpty(value);
	}

	public static boolean isNotEmpty(String value) {
		return StringUtil.isNotEmpty(value);
	}
	
	public static boolean isBlank(String value) {
		return StringUtil.isBlank(value);
	}
	
	public static boolean isNotBlank(String value) {
		return StringUtil.isNotBlank(value);
	}
	
	public static boolean isNumeric(String value) {
		return StringUtil.isNumeric(value);
	}
	
	/**
	 * 截取长字符串中的前若干字符
	 * @param value		要截取的原始字符串
	 * @param len		要截取的长度
	 * @param postfix	截取后加的后缀
	 * @return
	 */
	public static String limitStr(String value, Integer len, String postfix){
		if(StringUtil.isEmpty(value)) return value;
		if(len>0 && value.length()>len){
			value = value.substring(0, len);
			if(StringUtil.isNotEmpty(postfix))
				value += postfix;
		}
		return value;
	}
	
	/**
	 * 根据与正则表达式进行替换 <br/>
	 * 用String.replaceAll 方法实现
	 * @param str			待处理的字符串
	 * @param regex			正则表达式
	 * @param replacement	替换表达式
	 * @return
	 */
	public static String replaceAll(String str, String regex, String replacement){
		if(StringUtil.isEmpty(str)) return str;
		return str.replaceAll(regex, replacement);
	}
}
