/**
 * 
 */
package cn.hshb.web.biz.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatek.framework.util.SpringContext;

import cn.hshb.web.biz.mybatis.dao.HouseRentHouseMapper;
import cn.hshb.web.biz.mybatis.dao.HouseSecondHandHouseMapper;
import cn.hshb.web.biz.mybatis.model.HouseRentHouse;
import cn.hshb.web.biz.mybatis.model.HouseRentHouseExample;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouseExample;
import cn.hshb.web.biz.service.UserCompareHistoryService;
import cn.hshb.web.common.helper.ApplicationContextProvider;
import cn.hshb.web.house.enums.EnumHouseType;

/**
 * @author Administrator
 *
 */
@Service
public class HouseBrowseCacheUtil {
	
	@Autowired
	private HouseSecondHandHouseMapper houseSecondHandHouseMapper;
	
	@Autowired
	private HouseRentHouseMapper houseRentHouseMapper;
	
	private static Map<Integer, Map<String, AtomicInteger>> houseTypeMap = new HashMap<Integer, Map<String, AtomicInteger>>();
	private static final HouseBrowseCacheUtil browseCache = new HouseBrowseCacheUtil();

	private HouseBrowseCacheUtil() {
		Map<String, AtomicInteger> secondBrowseMap = new HashMap<String, AtomicInteger>();
		Map<String, AtomicInteger> rentBrowseMap = new HashMap<String, AtomicInteger>();
		houseTypeMap.put(EnumHouseType.SALE.value(), secondBrowseMap);
		houseTypeMap.put(EnumHouseType.RENT.value(), rentBrowseMap);
	}

	public static HouseBrowseCacheUtil getInstance() {
		return browseCache;
	}

	/**
	 * 获取房源浏览量
	 * @param type		房源类型，参看EnumHouseType
	 * @param houseNo	房源ID
	 * @param browsed	浏览量
	 * @return
	 */
	public int getBrowsed(Integer type, String houseNo, Integer browsed) {
		if (type != EnumHouseType.SALE.value() && type != EnumHouseType.RENT.value()) {
			return 0;
		} else {
			Map<String, AtomicInteger> houseMap = (Map<String, AtomicInteger>) houseTypeMap.get(type);
			AtomicInteger browse;
			if (houseMap.containsKey(houseNo)) {
				return houseMap.get(houseNo).incrementAndGet();
			} else if (type == EnumHouseType.SALE.value()) {
				browse = new AtomicInteger(browsed + 1);
				houseMap.put(houseNo, browse);
			} else {
				browse = new AtomicInteger(browsed + 1);
				houseMap.put(houseNo, browse);
			}
			
			saveToDatabase();
			
			return browse.get();
		}
	}

	/**
	 * 把缓存中的房源浏览量保存到数据库
	 */
	public void saveToDatabase() {
		getHouseSecondHandHouseMapper();
		getHouseRentHouseMapper();

		Map<String, AtomicInteger> secondMap = (HashMap<String, AtomicInteger>) houseTypeMap.get(EnumHouseType.SALE.value());
		Map<String, AtomicInteger> rentMap = (HashMap<String, AtomicInteger>) houseTypeMap.get(EnumHouseType.RENT.value());

		for(Map.Entry<String, AtomicInteger> e: secondMap.entrySet()){
			String houseNo = e.getKey();
			AtomicInteger browsed = e.getValue();
			
			HouseSecondHandHouseExample example = new HouseSecondHandHouseExample();
			example.createCriteria().andHouseIdEqualTo(houseNo);
			
			HouseSecondHandHouse record = new HouseSecondHandHouse();
			record.setBrowsed(browsed.get());
			houseSecondHandHouseMapper.updateByExampleSelective(record, example);
		}
		
		for(Map.Entry<String, AtomicInteger> e: rentMap.entrySet()){
			String houseNo = e.getKey();
			AtomicInteger browsed  =e.getValue();
			
			HouseRentHouseExample example = new HouseRentHouseExample();
			example.createCriteria().andHouseIdEqualTo(houseNo);
			
			HouseRentHouse record = new HouseRentHouse();
			record.setBrowsed(browsed.get());
			houseRentHouseMapper.updateByExampleSelective(record, example);
		}
	}
	
	private HouseSecondHandHouseMapper getHouseSecondHandHouseMapper(){
		if (houseSecondHandHouseMapper == null) {
			Object obj = ApplicationContextProvider.getBeanOfType(HouseSecondHandHouseMapper.class);
			if(obj==null){
				obj = ApplicationContextProvider.getContext().getBean("houseSecondHandHouseMapper");
			}
			if(obj instanceof HouseSecondHandHouseMapper)
				houseSecondHandHouseMapper = (HouseSecondHandHouseMapper)obj;
		}
		return houseSecondHandHouseMapper;
	}
	
	private HouseRentHouseMapper getHouseRentHouseMapper(){
		if (houseRentHouseMapper == null) {
			Object obj = ApplicationContextProvider.getBeanOfType(HouseRentHouseMapper.class);
			if(obj==null){
				obj = ApplicationContextProvider.getContext().getBean("houseRentHouseMapper");
			}
			if(obj instanceof HouseRentHouseMapper)
				houseRentHouseMapper = (HouseRentHouseMapper)obj;
		}
		return houseRentHouseMapper;
	}
}
