package com.huatek.hbwebsite.util;

import com.huatek.hbwebsite.house.entity.Apartment;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

public class HouseOrientationUtil {
	public static final HouseOrientationUtil houseOrientationUtil = new HouseOrientationUtil();
	private Map<String, Integer> orientationMap = new LinkedHashMap();
	private Map<String, Integer> decorationMap = new LinkedHashMap();
	private Map<String, Integer> usageMap = new LinkedHashMap();
	public static Map<String, String> zerotoNineHt = new Hashtable();

	static {
		zerotoNineHt.put("零", "0");
		zerotoNineHt.put("一", "1");
		zerotoNineHt.put("二", "2");
		zerotoNineHt.put("两", "2");
		zerotoNineHt.put("三", "3");
		zerotoNineHt.put("四", "4");
		zerotoNineHt.put("五", "5");
		zerotoNineHt.put("六", "6");
		zerotoNineHt.put("七", "7");
		zerotoNineHt.put("八", "8");
		zerotoNineHt.put("九", "9");
	}

	private HouseOrientationUtil() {
		this.initOrientationMap();
		this.initDecorationMap();
		this.initUsageMap();
	}

	private void initOrientationMap() {
		this.orientationMap.put("东", Integer.valueOf(1));
		this.orientationMap.put("西", Integer.valueOf(2));
		this.orientationMap.put("南", Integer.valueOf(3));
		this.orientationMap.put("北", Integer.valueOf(4));
		this.orientationMap.put("东南", Integer.valueOf(5));
		this.orientationMap.put("西南", Integer.valueOf(6));
		this.orientationMap.put("东北", Integer.valueOf(7));
		this.orientationMap.put("西北", Integer.valueOf(8));
		this.orientationMap.put("东西", Integer.valueOf(9));
		this.orientationMap.put("南北", Integer.valueOf(10));
	}

	private void initDecorationMap() {
		this.decorationMap.put("精装", Integer.valueOf(1));
		this.decorationMap.put("简装", Integer.valueOf(2));
		this.decorationMap.put("毛坯", Integer.valueOf(3));
		this.decorationMap.put("豪装", Integer.valueOf(4));
		this.decorationMap.put("中装", Integer.valueOf(5));
	}

	private void initUsageMap() {
		this.usageMap.put("住宅", Integer.valueOf(1));
		this.usageMap.put("商用", Integer.valueOf(2));
		this.usageMap.put("商住两用", Integer.valueOf(3));
	}

	public static HouseOrientationUtil getInstance() {
		return houseOrientationUtil;
	}

	public int convertOrientation(String orientationString) {
		Integer convertedInt = null;
		if (StringUtils.isEmpty(orientationString)) {
			return 0;
		} else {
			convertedInt = (Integer) this.orientationMap.get(orientationString.trim());
			return convertedInt == null ? 0 : convertedInt.intValue();
		}
	}

	public int convertDecoration(String decorationString) {
		Integer convertedInt = null;
		if (StringUtils.isEmpty(decorationString)) {
			return 0;
		} else {
			convertedInt = (Integer) this.decorationMap.get(decorationString.trim());
			return convertedInt == null ? 0 : convertedInt.intValue();
		}
	}

	public int convertUsage(String usgaeString) {
		Integer convertedInt = null;
		if (StringUtils.isEmpty(usgaeString)) {
			return 0;
		} else {
			convertedInt = (Integer) this.usageMap.get(usgaeString.trim());
			return convertedInt == null ? 0 : convertedInt.intValue();
		}
	}

	public Apartment splitApartment(String apartmentString) {
		String shi = "";
		String ting = "";
		String wei = "";
		String yangtai = "";
		int flag = 0;
		Pattern pattern = Pattern.compile("[0-9]*");
		int shiFlag = apartmentString.indexOf("室");
		if (shiFlag > 0) {
			shi = apartmentString.substring(flag, shiFlag);
			if (!pattern.matcher(shi).matches()) {
				shi = switchChToNum(shi);
			}

			flag = shiFlag + 1;
		} else {
			shiFlag = apartmentString.indexOf("房");
			if (shiFlag > 0) {
				shi = apartmentString.substring(flag, shiFlag);
				if (!pattern.matcher(shi).matches()) {
					shi = switchChToNum(shi);
				}

				flag = shiFlag + 1;
			} else {
				shi = "0";
			}
		}

		int tingFlag = apartmentString.indexOf("厅");
		if (tingFlag > 0) {
			ting = apartmentString.substring(flag, tingFlag);
			if (!pattern.matcher(ting).matches()) {
				ting = switchChToNum(ting);
			}

			flag = tingFlag + 1;
		} else {
			ting = "0";
		}

		int weiFlag = apartmentString.indexOf("卫");
		if (weiFlag > 0) {
			wei = apartmentString.substring(flag, weiFlag);
			if (!pattern.matcher(wei).matches()) {
				wei = switchChToNum(wei);
			}

			flag = weiFlag + 1;
		} else {
			wei = "0";
		}

		int yangtaiFlag = apartmentString.indexOf("阳台");
		if (yangtaiFlag > 0) {
			yangtai = apartmentString.substring(flag, yangtaiFlag);
			if (!pattern.matcher(yangtai).matches()) {
				yangtai = switchChToNum(yangtai);
			}

			flag = yangtaiFlag + 1;
		} else {
			yangtai = "0";
		}

		Apartment apartment = new Apartment();
		apartment.setShi(Integer.valueOf(shi).intValue());
		apartment.setWei(Integer.valueOf(wei).intValue());
		apartment.setTing(Integer.valueOf(ting).intValue());
		apartment.setBalcony(Integer.valueOf(yangtai).intValue());
		return apartment;
	}

	public static String switchChToNum(String ch) {
		String result = (String) zerotoNineHt.get(ch);
		return result;
	}
}
