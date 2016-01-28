/**
 * 
 */
package cn.hshb.web.common.helper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.hshb.web.common.util.StringUtil;


/**
 * @author Administrator
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 * 
 */
public class HouseHelper {

	/**
	 * 计算房源所在楼层在总楼层的上、中还是下部
	 * @param Floor					房源所在楼层
	 * @param TotalFloor		总楼层
	 * @return
	 */
	public static int funFloorPoint(int Floor, int TotalFloor) {
		if (Floor != 0 && TotalFloor != 0) {
			float fl = (float) (TotalFloor / 3);
			int result;
			if (Floor <= fl) {
				result = 1;
			} else if (Floor < 2.0F * fl && Floor > fl) {
				result = 2;
			} else {
				result = 3;
			}
			return result;
		} else {
			return 1;
		}
	}
	
	/**
	 * 从搜索字符串中解析房源的室、厅、卫（用于查询条件）
	 * @param txt
	 */
	public static Map<String, Integer> parseHouseShiTingWei(String txt){
		Map<String, Integer> retMap = new HashMap<String, Integer>();
		if(StringUtil.isEmpty(txt)) return retMap;
		Pattern p1 = Pattern.compile("(.)(室|房)");
		Matcher m1 = p1.matcher(txt);
		if(m1.find()){
			String _shi = m1.group(1);
			int shi = StringUtil.charToNumber(_shi);
			if(shi>=0) retMap.put("shi", shi);
		}
		Pattern p2 = Pattern.compile("(.)厅");
		Matcher m2 = p2.matcher(txt);
		if(m2.find()){
			String _ting = m2.group(1);
			int ting = StringUtil.charToNumber(_ting);
			if(ting>=0) retMap.put("ting", ting);
		}
		Pattern p3 = Pattern.compile("(.)卫");
		Matcher m3 = p3.matcher(txt);
		if(m3.find()){
			String _wei = m3.group(1);
			int wei = StringUtil.charToNumber(_wei);
			if(wei>=0) retMap.put("wei", wei);
		}
		Pattern p4 = Pattern.compile("(.)阳台");
		Matcher m4 = p4.matcher(txt);
		if(m4.find()){
			String _yang = m4.group(1);
			int yang = StringUtil.charToNumber(_yang);
			if(yang>=0) retMap.put("yang", yang);
		}
		return retMap;
	}
	
	/**
	 * 从搜索字符串中解析房源朝向
	 * @param txt
	 * @return
	 */
	public static List<String> parseHouseOrientation(String txt){
		List<String> retList = new ArrayList<String>();
		Pattern p = Pattern.compile("(东南|西南|东北|西北|东西|南北|东|南|西|北)");
		Matcher m = p.matcher(txt);
		while(m.find()){
			String _ori = m.group(1);
			retList.add(_ori);
		}
		return retList;
	}
	
	/**
	 * 从搜索字符串中解析房源楼层
	 * @param txt
	 * @return
	 */
	public static int parseHouseStorey(String txt){
		Pattern p = Pattern.compile("(.)楼");
		Matcher m = p.matcher(txt);
		if(m.find()){
			String _storey = m.group(1);
			int storey = StringUtil.charToNumber(_storey);
			return storey;
		}
		return -1;
	}

	/**
	 * 从搜索字符串中解析房源装修程度
	 * @param txt
	 * @return
	 */
	public static List<String> parseHouseDecoration(String txt){
		List<String> retList = new ArrayList<String>();
		Pattern p = Pattern.compile("(精装|简装|毛坯|豪装|中装)");
		Matcher m = p.matcher(txt);
		while(m.find()){
			String _decora = m.group(1);
			retList.add(_decora);
		}
		return retList;
	}

	
	/** 
   * 坐标转换，腾讯地图转换成百度地图坐标 
   * @param lat 腾讯纬度 
   * @param lon 腾讯经度 
   * @return 返回结果：经度,纬度 
   */  
  public static String[] geoConvertTencent2Baidu(double lng, double lat){  
      double bd_lat, bd_lng;
      double x_pi=3.14159265358979324;
      double x = lng, y = lat;
      double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
      double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
      bd_lng = z * Math.cos(theta) + 0.0065;
      bd_lat = z * Math.sin(theta) + 0.006;
        
//      System.out.println("bd_lat:"+bd_lat);
//      System.out.println("bd_lng:"+bd_lng);
      DecimalFormat df = new DecimalFormat("#0.000000");
      
      return new String[] {df.format(bd_lng), df.format(bd_lat)};  
  }  

     
  /** 
   * 坐标转换，百度地图坐标转换成腾讯地图坐标 
   * @param lat  百度坐标纬度 
   * @param lon  百度坐标经度 
   * @return 返回结果：经度 , 纬度
   */  
  public static String[] geoConvertBaidu2Tencent(double lng, double lat){
      double tx_lat, tx_lng;
      double x_pi=3.14159265358979324;
      double x = lng - 0.0065, y = lat - 0.006;
      double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);  
      double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
      tx_lng = z * Math.cos(theta);
      tx_lat = z * Math.sin(theta);
//      return tx_lat+","+tx_lng;
      
      DecimalFormat df = new DecimalFormat("#0.000000");
      return new String[]{df.format(tx_lng), df.format(tx_lat)};
  }

	public static void main(String[] args){
//		String txt = "两房2厅3卫二阳台";
//		Map<String, Integer> list = HouseHelper.parseHouseShiTingWei(txt);

		String s = "30.201989,120.188752";
		String[] s1 = s.split(",|\\|");
		String[] s2 = HouseHelper.geoConvertBaidu2Tencent(Double.parseDouble(s1[1]), Double.parseDouble(s1[0]));
		System.out.println("new GEO: lng: " + s2[0] + ", lat: " + s2[1]);
	}
	
}
