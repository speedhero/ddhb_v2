/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.CommonLiveFacilityMapper;
import cn.hshb.web.biz.mybatis.model.CommonLiveFacility;
import cn.hshb.web.biz.mybatis.model.CommonLiveFacilityExample;
import cn.hshb.web.biz.service.CommonLiveFacilityService;

/**
 * @author ShengYoufu
 *
 */
@Service
public class CommonLiveFacilityServiceImpl implements CommonLiveFacilityService {
	//用静态变量缓存，避免多次查询
	private static List<CommonLiveFacility> facilityList = null;
	
	//最后刷新时间
	private static Long LAST_LOAD_TIME = System.currentTimeMillis();
	//刷新间隔
	private static Long RELOAD_INTERVAL = 24 * 3600L;
	
	@Autowired
	private CommonLiveFacilityMapper commonLiveFacilityMapper;
	
	@Override
	public List<CommonLiveFacility> getHouseFacilitys() {
		return getHouseFurnitures();
	}

	@Override
	public List<CommonLiveFacility> getHouseFurnitures() {
		return loadCommonLiveFacilitys();
	}

	@Override
	public CommonLiveFacility getHouseFurnitureByName(String name){
		for(CommonLiveFacility f: loadCommonLiveFacilitys()){
			if(f.getClfName().equals(name))
				return f;
		}
		return null;
	}
	
	protected List<CommonLiveFacility> loadCommonLiveFacilitys(){
		if(facilityList==null || facilityList.isEmpty() || isNeedReload()){
			synchronized (CommonLiveFacilityServiceImpl.class) {
				if(facilityList==null || facilityList.size()<1 || isNeedReload()) {
					CommonLiveFacilityExample example = new CommonLiveFacilityExample();
					example.createCriteria().andDeleteflagEqualTo(0);
					facilityList = commonLiveFacilityMapper.selectByExample(example);
					
					LAST_LOAD_TIME = System.currentTimeMillis();  //重新设置最后刷新时间
				}
			}
		}
		return facilityList;
	}

	/**
	 * 判断是否到了指定的刷新时间
	 * @return
	 */
	protected Boolean isNeedReload(){
		Long t = System.currentTimeMillis() - LAST_LOAD_TIME;
		return ( t / 1000 >= RELOAD_INTERVAL);
	}	
}
