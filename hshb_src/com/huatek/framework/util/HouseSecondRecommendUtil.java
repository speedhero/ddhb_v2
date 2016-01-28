package com.huatek.framework.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class HouseSecondRecommendUtil {
	private Map<String, Integer> priceMap = new LinkedHashMap<String, Integer>();
	private Map<String, Integer> areaMap = new LinkedHashMap<String, Integer>();
	private Map<String, Integer> shiMap = new LinkedHashMap<String, Integer>();
	private Queue<String> priceQueue = new LinkedList<String>();
	private Queue<String> areaQueue = new LinkedList<String>();
	private Queue<String> shiQueue = new LinkedList<String>();
	
	private int queueSize;				//队列长度
	private static final Integer DEFAULT_QUEUE_SIZE = 4;

	public HouseSecondRecommendUtil(Integer queueSize) {
		setQueueSize(queueSize);
	}

	public void setQueueSize(Integer queueSize) {
		if (queueSize == null) {
			this.queueSize = DEFAULT_QUEUE_SIZE;
		} else {
			this.queueSize = queueSize;
		}
	}

	/**
	 * 
	 * @param price
	 * @return
	 */
	public String putPriceToQueue(Float price) {
		String priceRange;
		Integer counter;
		if (this.priceQueue.size() >= this.queueSize) {
			priceRange = (String) this.priceQueue.poll();
			if (priceRange != null) {
				counter = (Integer) this.priceMap.get(priceRange);
				if (counter.intValue() <= 1) {
					this.priceMap.remove(priceRange);
				} else {
					this.priceMap.put(priceRange, Integer.valueOf(counter.intValue() - 1));
				}
			}
		}

		priceRange = this.isExistprice(price);
		if (priceRange == null) {
			//指定价格上下10%作为最大最小价格
			Float counter1 = Float.valueOf((float) ((double) price.floatValue() * 0.9D));
			Float priceMax = Float.valueOf((float) ((double) price.floatValue() * 1.1D));
			priceRange = String.valueOf(counter1) + "," + priceMax;
		}

		counter = priceMap.get(priceRange);
		if (counter == null) {
			counter = 1;
		} else {
			counter = counter + 1;
		}

		this.priceMap.put(priceRange, counter);
		this.priceQueue.add(priceRange);
		return this.getMaxCount(1);
	}

	/**
	 * 把面积放入缓存Map
	 * @param area
	 * @return
	 */
	public String putAreaToQueue(Float area) {
		String areaRange;
		Integer counter;
		//当 areaQueue 的计数大于 queueSize,然后再map里-1,可以保证areaMap的key是最近用户所欢迎的面积范围
		if (areaQueue.size() >= queueSize) {
			areaRange = (String) areaQueue.poll();
			if (areaRange != null) {
				counter = areaMap.get(areaRange);
				if (counter <= 1) {
					areaMap.remove(areaRange);
				} else {
					areaMap.put(areaRange, counter - 1);
				}
			}
		}

		areaRange = this.isExistarea(area);
		if (areaRange == null) {
			//面积上下浮动10%作为最大最小值
			Float counter1 = Float.valueOf((float) ((double) area.floatValue() * 0.9D));
			Float areaMax = Float.valueOf((float) ((double) area.floatValue() * 1.1D));
			areaRange = String.valueOf(counter1) + "," + areaMax;
		}
		//计数
		counter = areaMap.get(areaRange);
		if (counter == null) {
			counter = 1;
		} else {
			counter = counter + 1;
		}

		areaMap.put(areaRange, counter);
		areaQueue.add(areaRange);
		//在getMaxCount中取出面积范围访问量最多的
		return getMaxCount(2);
	}

	/**
	 * 把居室放入缓存Map
	 * @param shi
	 * @return
	 */
	public String putShiToQueue(Integer shi) {
		String apartment = String.valueOf(shi);
		if (shiQueue.size() >= queueSize) {
			String counter = (String) shiQueue.poll();
			if (counter != null) {
				Integer counter1 = shiMap.get(counter);
				if (counter1 <= 1) {
					shiMap.remove(counter);
				} else {
					shiMap.put(counter, counter1 - 1);
				}
			}
		}

		Integer counter2 = shiMap.get(apartment);
		if (counter2 == null) {
			counter2 = 1;
		} else {
			counter2 = counter2 + 1;
		}

		shiMap.put(apartment, counter2);
		shiQueue.add(apartment);
		return getMaxCount(3);
	}

	private String getMaxCount(Integer i) {
		int maxValue = 0;
		String maxKey = "";
		switch (i.intValue()) {
			case 1:
				//返回的是 在价格范围中 
				for(String key: priceMap.keySet()){
					if ((Integer) this.priceMap.get(key) > maxValue) {
						maxValue = (Integer) this.priceMap.get(key);
						maxKey = key;
					}
				}
				break;
			case 2:
				for(String key: areaMap.keySet()){
					if (((Integer) this.areaMap.get(key)) > maxValue) {
						maxValue = (Integer) this.areaMap.get(key);
						maxKey = key;
					}
				}
				break;
			case 3:
				for(String key: shiMap.keySet()){
					if ((Integer) this.shiMap.get(key) > maxValue) {
						maxValue = (Integer) this.shiMap.get(key);
						maxKey = key;
					}
				}
				break;
		}

		return maxKey;
	}

	private String isExistprice(Float price) {
		String priceRange = null;
		Set priceSet = this.priceMap.keySet();
		Iterator var5 = priceSet.iterator();

		while (var5.hasNext()) {
			String priceRan = (String) var5.next();
			String[] priceRanges = priceRan.split(",");
			Float priceMin = Float.valueOf(priceRanges[0]);
			Float priceMax = Float.valueOf(priceRanges[1]);
			if (price.floatValue() >= priceMin.floatValue() && priceMax.floatValue() >= price.floatValue()) {
				priceRange = priceRan;
				break;
			}
		}

		return priceRange;
	}
	/**
	 * 判断缓存中是否已存在
	 * @param price
	 * @return
	 */
	private String isExistarea(Float area) {
		String areaRange = null;
		Set areaSet = this.areaMap.keySet();
		Iterator var5 = areaSet.iterator();

		while (var5.hasNext()) {
			String areaRan = (String) var5.next();
			String[] areaRanges = areaRan.split(",");
			Float areaMin = Float.valueOf(areaRanges[0]);
			Float areaMax = Float.valueOf(areaRanges[1]);
			//当面积在此范围内,则只用这范围
			if (area.floatValue() >= areaMin.floatValue() && areaMax.floatValue() >= area.floatValue()) {
				areaRange = areaRan;
				break;
			}
		}

		return areaRange;
	}
}
