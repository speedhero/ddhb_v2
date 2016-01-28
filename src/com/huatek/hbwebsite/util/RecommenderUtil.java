package com.huatek.hbwebsite.util;

import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class RecommenderUtil {
	public static String getSQLAppendStr(float averagePrice, float averageArea, List<HouseSecond> houseSecondList) {
		StringBuffer sb = new StringBuffer("");
		sb.append("FROM HouseSecond t WHERE t.price BETWEEN ");
		sb.append((double) averagePrice - (double) averagePrice * 0.1D);
		sb.append(" and ");
		sb.append((double) averagePrice + (double) averagePrice * 0.1D);
		sb.append(" AND t.area BETWEEN ");
		sb.append((double) averageArea - (double) averageArea * 0.1D);
		sb.append(" and ");
		sb.append((double) averageArea + (double) averageArea * 0.1D);
		sb.append(" AND t.shi = " + getMaxShi((LinkedHashMap) sortByValue(getMapForShi(houseSecondList))));
		sb.append(" order by t.price asc, t.area asc");
		return sb.toString();
	}

	public static String getHouseSecondDefaultRule() {
		StringBuffer sb = new StringBuffer("");
		sb.append("FROM HouseSecond order by rand()");
		return sb.toString();
	}

	public static int getMaxShi(LinkedHashMap orderMap) {
		Set mapValues = orderMap.entrySet();
		int maplength = mapValues.size();
		Entry[] test = new Entry[maplength];
		mapValues.toArray(test);
		return ((Integer) test[0].getValue()).intValue();
	}

	public static int getMaxBusinessCircle(LinkedHashMap orderMap) {
		Set mapValues = orderMap.entrySet();
		int maplength = mapValues.size();
		Entry[] test = new Entry[maplength];
		mapValues.toArray(test);
		return ((Integer) test[0].getValue()).intValue();
	}

	public static Map sortByValue(Map map) {
		LinkedList list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Entry) o2).getValue()).compareTo(((Entry) o1).getValue());
			}
		});
		LinkedHashMap result = new LinkedHashMap();
		Iterator it = list.iterator();

		while (it.hasNext()) {
			Entry entry = (Entry) it.next();
			result.put(entry.getKey(), entry.getValue());
		}

		return result;
	}

	public static Map<Integer, Integer> getMapForShi(List<HouseSecond> houseSecondList) {
		HashMap mapForShi = new HashMap();

		for (int i = 0; i < houseSecondList.size(); ++i) {
			int shi = ((HouseSecond) houseSecondList.get(i)).getShi().intValue();
			if (!mapForShi.containsKey(Integer.valueOf(shi))) {
				mapForShi.put(Integer.valueOf(shi), Integer.valueOf(1));
			} else {
				mapForShi.put(Integer.valueOf(shi),
						Integer.valueOf(((Integer) mapForShi.get(Integer.valueOf(shi))).intValue() + 1));
			}
		}

		return mapForShi;
	}

	public static Map<Long, Integer> getMapForBusinessCircle(List<HouseSecond> houseSecondList) {
		HashMap mapForBusinessCircle = new HashMap();

		for (int i = 0; i < houseSecondList.size(); ++i) {
			long businessCircle = ((HouseSecond) houseSecondList.get(i)).getCommunity().getCbd().getId().longValue();
			if (!mapForBusinessCircle.containsKey(Long.valueOf(businessCircle))) {
				mapForBusinessCircle.put(Long.valueOf(businessCircle), Integer.valueOf(1));
			} else {
				mapForBusinessCircle.put(Long.valueOf(businessCircle),
						Integer.valueOf(((Integer) mapForBusinessCircle.get(Long.valueOf(businessCircle))).intValue() + 1));
			}
		}

		return mapForBusinessCircle;
	}

	public static String getSQLAppendStrForRent(float averagePrice, float averageArea, List<HouseRent> houseRentList) {
		StringBuffer sb = new StringBuffer("");
		sb.append("FROM HouseRent t WHERE t.rentPrice BETWEEN ");
		sb.append((double) averagePrice - (double) averagePrice * 0.1D);
		sb.append(" and ");
		sb.append((double) averagePrice + (double) averagePrice * 0.1D);
		sb.append(" AND t.area BETWEEN ");
		sb.append((double) averageArea - (double) averageArea * 0.1D);
		sb.append(" and ");
		sb.append((double) averageArea + (double) averageArea * 0.1D);
		sb.append(" AND t.shi = " + getMaxShi((LinkedHashMap) sortByValue(getMapForShiRent(houseRentList))));
		sb.append(" order by t.rentPrice asc, t.area asc");
		return sb.toString();
	}

	public static String getHouseRentDefaultRule() {
		StringBuffer sb = new StringBuffer("");
		sb.append("FROM HouseRent order by rand()");
		return sb.toString();
	}

	public static Map<Integer, Integer> getMapForShiRent(List<HouseRent> houseRentList) {
		HashMap mapForShi = new HashMap();

		for (int i = 0; i < houseRentList.size(); ++i) {
			int shi = ((HouseRent) houseRentList.get(i)).getShi();
			if (!mapForShi.containsKey(Integer.valueOf(shi))) {
				mapForShi.put(Integer.valueOf(shi), Integer.valueOf(1));
			} else {
				mapForShi.put(Integer.valueOf(shi),
						Integer.valueOf(((Integer) mapForShi.get(Integer.valueOf(shi))).intValue() + 1));
			}
		}

		return mapForShi;
	}
}
