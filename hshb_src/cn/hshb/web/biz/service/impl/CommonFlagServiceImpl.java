/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.CommonFlagMapper;
import cn.hshb.web.biz.mybatis.model.CommonFlag;
import cn.hshb.web.biz.mybatis.model.CommonFlagExample;
import cn.hshb.web.biz.service.CommonFlagService;

/**
 * @author Administrator
 *
 */
@Service
public class CommonFlagServiceImpl implements CommonFlagService {
	
	private static List<CommonFlag> tagList = new ArrayList<CommonFlag>();
	
	
	//最后刷新时间
	private static Long LAST_LOAD_TIME = System.currentTimeMillis();
	//刷新间隔
	private static Long RELOAD_INTERVAL = 24 * 3600L;
	
	@Autowired
	private CommonFlagMapper commonFlagMapper;

	@Override
	public List<CommonFlag> getHouseTags() {
		loadHouseTags();
		return tagList;
	}
	
	/**
	 * 根据名称获取房源标签名称
	 * @param name
	 * @return
	 */
	@Override
	public CommonFlag getHouseTagByName(String name){
		loadHouseTags();
		for(CommonFlag f : tagList){
			if(f.getTagName().equals(name))
				return f;
		}
		return null;
	}
	
	protected void loadHouseTags(){
		if(tagList==null || tagList.isEmpty() || isNeedReload()){
			synchronized (CommonFlagServiceImpl.class) {
				if(tagList==null || tagList.size()<1 || isNeedReload()) {
					CommonFlagExample example = new CommonFlagExample();
					example.createCriteria().andDeleteflagEqualTo(0);
					tagList = commonFlagMapper.selectByExample(example);
					
					LAST_LOAD_TIME = System.currentTimeMillis();  //重新设置最后刷新时间
				}
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
