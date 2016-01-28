/**
 * 
 */
package cn.hshb.web.biz.util;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 二手房源推荐类，推荐算法都在本类中
 * 租赁房源类
 * 附近小区 
 * @author Administrator
 *
 */
public class HouseRecommendUtil {
	private Map<String, Integer> priceMap = new LinkedHashMap<String, Integer>();
	private Map<String, Integer> areaMap = new LinkedHashMap<String, Integer>();
	private Map<String, Integer> shiMap = new LinkedHashMap<String, Integer>();
	private Map<String, Integer> cbdMap = new LinkedHashMap<String, Integer>();
	private Queue<String> priceQueue = new LinkedList<String>();
	private Queue<String> areaQueue = new LinkedList<String>();
	private Queue<String> shiQueue = new LinkedList<String>();
	private Queue<String> cbdQueue = new LinkedList<String>();
	private int queueSize;
	private static final Integer DEFAULT_QUEUE_SIZE = 4;

	public HouseRecommendUtil(Integer queueSize) {
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
	 * 1、如果队列已超过设定值，则从队列中弹出第一个
	 * 1.1、根据弹出的值从priceMap取出计数器
	 * 1.2、如果计数器值小于等于1，则从priceMap中移取该对象
	 * 1.3、如果计数器值大于1，则把priceMap该键对应的计数器值减1
	 * 2、根据给定的价格从priceMap中取出符合的价格范围
	 * 2.1、如果没能相符的价格范围，则以当前价格上下浮动10%作为价格范围
	 * 3、根据价格范围从priceMin中取计数器值
	 * 4、如果没有取到，则置计数器值为1，否则把计数器值加1
	 * 5、再把价格范围和计数器值放回到priceMap
	 * 6、把价格范围放入队列
	 * 7、从priceMap取出值（计数器值）最大那个对象的键（价格范围）返回
	 * 
	 * 
	 * 1、把价格范围和一个计数器值放在priceMap
	 * 2、如果
	 * @param price
	 * @return
	 */
	public String putPriceToQueue(Float price) {
		String priceRange;
		if (priceQueue.size() >= queueSize) {
			priceRange = priceQueue.poll();
			if (priceRange != null) {
				Integer counter = priceMap.get(priceRange);
				if (counter <= 1) {
					priceMap.remove(priceRange);
				} else {
					priceMap.put(priceRange, counter - 1);
				}
			}
		}

		priceRange = getPriceRange(price);
		if (priceRange == null) {
			Float priceMin = (float)(price * 0.9D);
			Float priceMax = (float)(price * 1.1D);
			priceRange = priceMin + "," + priceMax;
		}

		Integer counter = priceMap.get(priceRange);
		if (counter == null) {
			counter = 1;
		} else {
			counter += 1;
		}

		priceMap.put(priceRange, counter);
		priceQueue.add(priceRange);
		return getKeyByMaxValue(priceMap);
	}

	public String putAreaToQueue(Float area) {
		String areaRange;
		Integer counter;
		if (this.areaQueue.size() >= this.queueSize) {
			areaRange = (String) areaQueue.poll();
			if (areaRange != null) {
				counter = (Integer) areaMap.get(areaRange);
				if (counter.intValue() <= 1) {
					areaMap.remove(areaRange);
				} else {
					areaMap.put(areaRange, counter.intValue() - 1);
				}
			}
		}

		areaRange = getAreaRange(area);
		if (areaRange == null) {
			Float areaMin = Float.valueOf((float) ((double) area.floatValue() * 0.9D));
			Float areaMax = Float.valueOf((float) ((double) area.floatValue() * 1.1D));
			areaRange = areaMin + "," + areaMax;
		}

		counter = areaMap.get(areaRange);
		if (counter == null) {
			counter = 1;
		} else {
			counter = counter.intValue() + 1;
		}

		areaMap.put(areaRange, counter);
		areaQueue.add(areaRange);
		return this.getKeyByMaxValue(areaMap);
	}

	public String putShiToQueue(Integer shi) {
		String apartment = String.valueOf(shi);
		if (shiQueue.size() >= queueSize) {
			String counter = (String) shiQueue.poll();
			if (counter != null) {
				Integer counter1 = (Integer) shiMap.get(counter);
				if (counter1.intValue() <= 1) {
					shiMap.remove(counter);
				} else {
					shiMap.put(counter, counter1.intValue() - 1);
				}
			}
		}

		Integer counter2 = (Integer) shiMap.get(apartment);
		if (counter2 == null) {
			counter2 = Integer.valueOf(1);
		} else {
			counter2 = Integer.valueOf(counter2.intValue() + 1);
		}

		shiMap.put(apartment, counter2);
		shiQueue.add(apartment);
		return getKeyByMaxValue(shiMap);
	}
	
	public String putCbdToQueue(String cbdId){
		if(cbdQueue.size() > queueSize){
			String counter = (String) cbdQueue.poll();
			if(counter != null){
				Integer counter1 = (Integer) cbdMap.get(counter);
				if (counter1.intValue() <= 1) {
					cbdMap.remove(counter);
				} else {
					cbdMap.put(counter, counter1.intValue() - 1);
				}
			}
		}
		
		Integer counter2 = (Integer) cbdMap.get(cbdId);
		if (counter2 == null) {
			counter2 = Integer.valueOf(1);
		} else {
			counter2 = Integer.valueOf(counter2.intValue() + 1);
		}

		cbdMap.put(cbdId, counter2);
		cbdQueue.add(cbdId);
		return getKeyByMaxValue(cbdMap);
	}
	/**
	 * 从指定的Map中取出计数器值最大的对应的Key
	 * @param map
	 * @return
	 */
	private String getKeyByMaxValue(Map<String, Integer> map){
		int maxValue = 0;
		String maxKey = "";
		for(Map.Entry<String, Integer> e: map.entrySet()){
			if( e.getValue() > maxValue){
				maxValue =  e.getValue();
				maxKey = e.getKey();
			}
		}
		return maxKey;
	}
	
	/**
	 * 根据指定的值从Map中取出包含当前值的参数区间对应的key，例如价格区间、面积区间和居室区间
	 * 返回的值是以逗号分隔的字符串
	 * @param map
	 * @param val
	 * @return
	 */
	private String getRangeKeyByValue(Map<String, Integer> map, Float val){
		String rangeKey = null;
		for(Map.Entry<String, Integer> e: map.entrySet()){
			String key = e.getKey();
			String[] ranges = key.split(",");
			Float valMin = Float.valueOf(ranges[0]);
			Float valMax = Float.valueOf(ranges[1]);
			if ( valMin <= val && val <= valMax ) {
				rangeKey = key;
				break;
			}
		}
		return rangeKey;
	}
	
	/**
	 * 根据指定的价格取出相符的价格段
	 * @param price
	 * @return
	 */
	private String getPriceRange(Float price) {
		return getRangeKeyByValue(priceMap, price);
	}
	
	/**
	 * 根据指定的价格取出相符的价格段
	 * @param price
	 * @return
	 */
	private String getAreaRange(Float area) {
		return getRangeKeyByValue(areaMap, area);
	}
	
}
