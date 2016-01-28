package com.huatek.framework.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MemberBrowseHistoryAnalyser {
	private Map<String, Integer> cbdMap = new LinkedHashMap();
	private Queue<String> cbdQueue = new LinkedList();
	private int queueSize;
	private static final Integer DEFAULT_QUEUE_SIZE = Integer.valueOf(4);

	public MemberBrowseHistoryAnalyser(Integer queueSize) {
		if (queueSize == null) {
			this.queueSize = DEFAULT_QUEUE_SIZE.intValue();
		} else {
			this.queueSize = queueSize.intValue();
		}

	}

	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}

	public String putCbdToQueue(String cbdId) {
		if (this.cbdQueue.size() >= this.queueSize) {
			String counter = (String) this.cbdQueue.poll();
			if (counter != null) {
				Integer counter1 = (Integer) this.cbdMap.get(counter);
				if (counter1.intValue() <= 1) {
					this.cbdMap.remove(counter);
				} else {
					this.cbdMap.put(counter, Integer.valueOf(counter1.intValue() - 1));
				}
			}
		}

		Integer counter2 = (Integer) this.cbdMap.get(cbdId);
		if (counter2 == null) {
			counter2 = Integer.valueOf(1);
		} else {
			counter2 = Integer.valueOf(counter2.intValue() + 1);
		}

		this.cbdMap.put(cbdId, counter2);
		this.cbdQueue.add(cbdId);
		return this.getMaxCbdCount();
	}

	private String getMaxCbdCount() {
		int maxValue = 0;
		String maxKey = "";
		Set keySet = this.cbdMap.keySet();
		Iterator var5 = keySet.iterator();

		while (var5.hasNext()) {
			String str = (String) var5.next();
			if (((Integer) this.cbdMap.get(str)).intValue() > maxValue) {
				maxValue = ((Integer) this.cbdMap.get(str)).intValue();
				maxKey = str;
			}
		}

		return maxKey;
	}
}
