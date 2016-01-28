/**
 * 
 */
package cn.hshb.web.common.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0 http://www.hshb.cn
 * 
 */
public class StringUtil extends StringUtils {
	private static final Log log = LogFactory.getLog(StringUtil.class);

	//用来随机生成姓名的姓
	private static final String XING = "王李张刘陈杨黄孙周吴徐赵朱马胡郭林何高梁郑罗宋谢唐韩曹许邓萧冯曾程蔡彭潘袁于董余苏叶吕魏蒋田杜丁沈姜范江傅钟卢汪戴崔任陆廖姚方金邱夏谭韦贾邹石熊孟秦阎薛侯雷白龙段郝孔邵史毛常万顾赖武康贺严尹钱施牛洪龚";
	//用来随机生成名字的字
	private static final String MING = "嘉哲俊博妍乐佳涵晨宇怡泽子凡悦思奕依浩泓彤冰媛凯伊淇淳一洁茹清吉源渊和函妤宜云琪菱宣沂健信欣可洋萍荣榕含佑明雄梅芝英义淑卿乾亦芬萱昊芸天岚昕尧鸿棋琳孜娟宸林乔琦丞安毅凌泉坤晴竹娴婕恒渝菁龄弘佩勋宁元栋盈江卓春晋逸沅倩昱绮海圣承民智棠容羚峰钰涓新莉恩羽妮旭维家泰诗谚阳彬书苓汉蔚坚茵耘喆国仑良裕融致富德易虹纲筠奇平蓓真之凰桦玫强村沛汶锋彦延庭霞冠益劭钧薇亭瀚桓东滢恬瑾达群茜先洲溢楠基轩月美心茗丹森学文";
	
	//手机号码段
	private static final String[] PHONE_PERFIX = {"134","135","136","137","138","139","150","151","157","158","159","187","188","130","131","132","152","155","156","185","186","133","153","180","189"};
	
	/**
	 * 数字转汉字
	 * 
	 * @param num
	 * @return
	 */
	public static String numberToChinese(int num) {
		String s = "零一二三四五六七八九十";
		if (num >= 0 && num <= 10)
			return s.substring(num, num + 1);
		return String.valueOf(num);
	}

	/**
	 * 中文分词
	 * 
	 * @param str
	 * @return
	 */
	public static List<String> termSplitForCN(String str) {
		List<String> result = new ArrayList<String>();
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_46, true);
		TokenStream ts = null;
		try {
			ts = analyzer.tokenStream("field", str);
			CharTermAttribute term = (CharTermAttribute) ts.addAttribute(CharTermAttribute.class);
			ts.reset();

			while (ts.incrementToken()) {
				String s = term.toString();
				if (!isBlank(s))
					result.add(s);
			}
		} catch (IOException ex) {
			log.error(ex);
		} finally {
			if (ts != null) {
				try {
					ts.end();
				} catch (IOException e) {
					log.warn(e);
				}
				try {
					ts.close();
				} catch (IOException e) {
					log.warn(e);
				}
			}
			analyzer.close();
		}

		return result;
	}

	public static char CNNumToNumber(char a) {
		char hanzi[] = { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九', '两' };
		char alabo[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '2' };
		for (int i = 0; i < hanzi.length; i++)
			if (a == hanzi[i])
				return alabo[i];
		return ' ';
	}

	/**
	 * 文本转换为数字
	 * 
	 * @param txt
	 * @return
	 */
	public static int charToNumber(String chr){
		if(isEmpty(chr)) return -1;
		if(isNumeric(chr)) 
			return Integer.parseInt(chr);
		char c = CNNumToNumber(chr.charAt(0));
		if(' ' == c) return -1;
		else
			return Integer.parseInt(c+"");
	}
	
	/**
	 * 生成随机姓名
	 * @param wordCount 名的字数
	 * @return
	 */
	public static String getRandName(Integer wordCount){
		String xing = RandomStringUtils.random(1, XING);
		if(wordCount==null) wordCount = 2;
		String ming = RandomStringUtils.random(wordCount, MING);
		return xing + ming;
	}
	
	/**
	 * 生成随机的电话号码
	 * @return
	 */
	public static String getRandPhoneNum(){
		String perfix = PHONE_PERFIX[RandomUtils.nextInt(PHONE_PERFIX.length)];
		String postfix = RandomStringUtils.randomNumeric(8);
		return perfix + postfix;
	}

	/**
	 * 生成指定长度的随机字符串
	 * @param len
	 * @return
	 */
	public static String getRandStr(Integer len){
		return RandomStringUtils.randomAlphabetic(len);
	}
	
	/**
	 * 生成指定长度范围的随机字符串
	 * @param len1	最小长度
	 * @param len2	最大长度
	 * @return
	 */
	public static String getRandStr(Integer len1, Integer len2){
		Integer len = 0; 
		while(len<len1)
			len = RandomUtils.nextInt(len2);
		return RandomStringUtils.randomAlphabetic(len);
	}
	
	/**
	 * 在字符串前面加字符串首字的拼音首字母
	 * 
	 * @param str
	 * @return
	 */
	public static String getPinYinHeadChar(String str) {
		String convert = "";

		for (int j = 0; j < str.length(); ++j) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert = convert + pinyinArray[0].charAt(0);
			} else {
				convert = convert + word;
			}
		}
		return convert;
	}

	/**
	 * 获得字符串拼音首字母
	 * 
	 * @param str
	 * @return
	 */
	public static String getPinYinHeadFirstChar(String str) {
		String convert = "";
		for (int ii = 0; ii < 1; ++ii) {
			char word = str.charAt(ii);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert = convert + pinyinArray[0].charAt(0);
			} else {
				convert = convert + word;
			}
		}
		return convert;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean GetStrLength(String str) {
		return StringUtil.isNotBlank(str);
	}
	
	/**
	 * 
	 * @param sum
	 * @return
	 */
	public static String convertBinary(int sum) {
		StringBuffer binary = new StringBuffer();
		while (sum != 0 && sum != 1) {
			binary.insert(0, sum % 2);
			sum = sum / 2;
			if (sum == 0 || sum == 1) {
				binary.insert(0, sum % 2);
			}
		}
		return binary.toString();
	}
	
	/**
	 * 解析同步中的日期型字符串
	 * @param str
	 * @return
	 */
	public static java.sql.Date GetDate(String str) {
		String yearStr = str;
		if (str != null && str.trim().length() != 0) {
			if (!str.matches("^[0-9]{4,4}$")) {
				if (!str.matches("^[0-9]{4,4}-[0-9]{2,2}$") && !str.matches("^[0-9]{4,4}-[0-9]{2,2}-[0-9]{2,2}$")) {
					yearStr = "2000";
				} else {
					yearStr = str.substring(0, str.indexOf("-"));
				}
			}
		} else {
			yearStr = "2000";
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date ud = null;

		try {
			ud = df.parse(yearStr + "-01-01");
		} catch (ParseException ex) {
			log.error(ex);
		}

		java.sql.Date sd = new java.sql.Date(ud.getTime());
		return sd;
	}
	
	
	public static Integer parseInt(String val, Integer defaultVal){
		if(isNumeric(val))
			return Integer.parseInt(val);
		else
			return defaultVal;
	}
	
	public static Float parseFloat(String val, Float defaultVal){
		if(isNumeric(val))
			return Float.parseFloat(val);
		else
			return defaultVal;
	}
	
	public static Long parseLong(String val, Long defaultVal){
		if(isNumeric(val))
			return Long.parseLong(val);
		else
			return defaultVal;
	}
	
	public static Double parseDouble(String val, Double defaultVal){
		if(isNumeric(val))
			return Double.parseDouble(val);
		else
			return defaultVal;
	}

		

}
