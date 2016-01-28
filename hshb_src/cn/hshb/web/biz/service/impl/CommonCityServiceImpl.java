/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.CommonCityMapper;
import cn.hshb.web.biz.mybatis.model.CommonCity;
import cn.hshb.web.biz.mybatis.model.CommonCityExample;
import cn.hshb.web.biz.service.CommonCityService;

/**
 * @author ShengYoufu
 *
 */
@Service
public class CommonCityServiceImpl implements CommonCityService {
	//用静态变量缓存，避免多次查询
	private static List<CommonCity> cityList = null;

	//最后刷新时间
	private static Long LAST_LOAD_TIME = System.currentTimeMillis();
	//刷新间隔
	private static Long RELOAD_INTERVAL = 24 * 3600L;
	
	@Autowired
	private CommonCityMapper commonCityMapper;
	
	/**
	 * 根据城市ID取得城市记录
	 * @param cityId
	 * @return
	 */
	@Override
	public CommonCity getCommonCity(String cityId) {
		loadCommonCities();
		for(CommonCity c: cityList){
			if(c.getErpId().equals(cityId))
				return c;
		}
		return null;
	}	
	
	@Override
	public List<CommonCity> getCommonCities() {
		loadCommonCities();
		return cityList;
	}
	
	protected void loadCommonCities(){
		if(cityList==null || cityList.isEmpty() || isNeedReload()){
			synchronized (CommonCityServiceImpl.class) {
				if(cityList==null || cityList.size()<1 || isNeedReload()) {
					CommonCityExample example = new CommonCityExample();
					example.createCriteria().andDeleteflagEqualTo(0);
					cityList = commonCityMapper.selectByExample(example);
				}

				LAST_LOAD_TIME = System.currentTimeMillis();  //重新设置最后刷新时间
			}
		}
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
