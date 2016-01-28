/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.CommonUsageMapper;
import cn.hshb.web.biz.mybatis.model.CommonUsage;
import cn.hshb.web.biz.mybatis.model.CommonUsageExample;
import cn.hshb.web.biz.service.CommonUsageService;
import cn.hshb.web.common.util.DeepCloneUtil;

/**
 * @author ShengYoufu
 *
 */
@Service
public class CommonUsageServiceImpl implements CommonUsageService {
	private static final Log log = LogFactory.getLog(CommonUsageServiceImpl.class);
	
	private static List<CommonUsage> usageList = new ArrayList<CommonUsage>();
	
	//最后刷新时间
	private static Long LAST_LOAD_TIME = System.currentTimeMillis();
	//刷新间隔
	private static Long RELOAD_INTERVAL = 24 * 3600L;
	
	@Autowired
	private CommonUsageMapper commonUsageMapper;
	
	/**
	 * 根据ID获取房源用途
	 * @return
	 */
	@Override
	public CommonUsage getCommonUsage(String usageId) {
		loadCommonUsages();
		for(CommonUsage u: usageList){
			if(u.getErpId().equals(usageId)){
				try {
					return (CommonUsage)DeepCloneUtil.cloneObject(u);
				} catch (IllegalArgumentException ex) {
					log.warn("Deep clone for CommonUsage failed.", ex);
				} catch (IllegalAccessException ex) {
					log.warn("Deep clone for CommonUsage failed.", ex);
				} catch (InstantiationException ex) {
					log.warn("Deep clone for CommonUsage failed.", ex);
				}
				//如果克隆失败，则返回原始对象
				return u;
			}
		}
		return null;
	}
	
	/**
	 * 载入所有房源用途数据
	 * @return
	 */
	@Override
	public List<CommonUsage> getCommonUsages() {
		loadCommonUsages();
		return usageList;
	}
	
	/**
	 * 根据名称获取房源用途
	 * @param name
	 * @return
	 */
	@Override
	public CommonUsage getCommonUsageByName(String name){
		loadCommonUsages();
		for(CommonUsage u: usageList){
			if(u.getUsageName().equals(name)) return u;
		}
		return null;
	}	
	
	protected void loadCommonUsages(){
		if(usageList==null || usageList.isEmpty() || isNeedReload()){
			synchronized (CommonUsageServiceImpl.class) {
				if(usageList==null || usageList.isEmpty() || isNeedReload()) {
					CommonUsageExample example = new CommonUsageExample();
					example.createCriteria().andDeleteflagEqualTo(0);
					usageList = commonUsageMapper.selectByExample(example);
					
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
