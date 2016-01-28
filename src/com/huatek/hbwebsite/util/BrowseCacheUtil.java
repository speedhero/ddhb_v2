package com.huatek.hbwebsite.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.huatek.framework.util.SpringContext;
import com.huatek.hbwebsite.house.service.HouseSecondService;
import com.huatek.hbwebsite.rent.service.RentService;

public class BrowseCacheUtil {
	private HouseSecondService houseSecondService = null;
	private RentService rentService = null;
	private static final Integer HOUSESECOND_TYPE = Integer.valueOf(1);
	private static final Integer HOUSERENT_TYPE = Integer.valueOf(2);
	private static Map<Integer, Map<String, AtomicInteger>> houseTypeMap = new HashMap<Integer, Map<String, AtomicInteger>>();
	private static final BrowseCacheUtil browseCache = new BrowseCacheUtil();

	public BrowseCacheUtil() {
		Map<String, AtomicInteger> secondBrowseMap = new HashMap<String, AtomicInteger>();
		Map<String, AtomicInteger> rentBrowseMap = new HashMap<String, AtomicInteger>();
		houseTypeMap.put(HOUSESECOND_TYPE, secondBrowseMap);
		houseTypeMap.put(HOUSERENT_TYPE, rentBrowseMap);
	}

	public static BrowseCacheUtil getInstance() {
		return browseCache;
	}

	public int getBrowsed(Integer type, String houseNo, Integer browsed) {
		if (type != HOUSESECOND_TYPE && type != HOUSERENT_TYPE) {
			return 0;
		} else {
			Map<String, AtomicInteger> houseMap = (Map<String, AtomicInteger>) houseTypeMap.get(type);
			AtomicInteger browse;
			if (houseMap.containsKey(houseNo)) {
				browse = (AtomicInteger) houseMap.get(houseNo);
				browse.set(browse.get() + 1);
				houseMap.put(houseNo, browse);
				return browse.get();
			} else if (type == HOUSESECOND_TYPE) {
				browse = new AtomicInteger(browsed.intValue() + 1);
				houseMap.put(houseNo, browse);
				return browse.get();
			} else {
				browse = new AtomicInteger(browsed.intValue() + 1);
				houseMap.put(houseNo, browse);
				return browse.get();
			}
		}
	}

	public void saveToDatabase() {
		if (houseSecondService == null) {
			houseSecondService = (HouseSecondService) SpringContext.getBean("houseSecondService");
		}

		if (rentService == null) {
			rentService = (RentService) SpringContext.getBean("rentService");
		}

		Map<String, AtomicInteger> secondMap = (HashMap<String, AtomicInteger>) houseTypeMap.get(HOUSESECOND_TYPE);
		Map<String, AtomicInteger> rentMap = (HashMap<String, AtomicInteger>) houseTypeMap.get(HOUSERENT_TYPE);

		for(Map.Entry<String, AtomicInteger> e: secondMap.entrySet()){
			String houseNo = e.getKey();
			AtomicInteger browsed  =e.getValue();
			houseSecondService.updateBrowsed(houseNo, browsed.get());
		}
		for(Map.Entry<String, AtomicInteger> e: rentMap.entrySet()){
			String houseNo = e.getKey();
			AtomicInteger browsed  =e.getValue();
			houseSecondService.updateBrowsed(houseNo, browsed.get());
		}
	}
}
