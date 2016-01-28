/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.CommonCountyMapper;
import cn.hshb.web.biz.mybatis.model.CommonCounty;
import cn.hshb.web.biz.mybatis.model.CommonCountyExample;
import cn.hshb.web.biz.service.CommonCountyService;
import cn.hshb.web.common.util.DeepCloneUtil;

/**
 * @author ShengYoufu
 *
 */
@Service
public class CommonCountyServiceImpl implements CommonCountyService {
	private static final Log log = LogFactory.getLog(CommonCountyServiceImpl.class);
	//用静态变量缓存，避免多次查询
	private static List<CommonCounty> countyList = null;
	
	//最后刷新时间
	private static Long LAST_LOAD_TIME = System.currentTimeMillis();
	//刷新间隔
	private static Long RELOAD_INTERVAL = 24 * 3600L;
	
	@Autowired
	private CommonCountyMapper commonCountyMapper; 
	
	/**
	 * 查询所有城区
	 * @return
	 */
	@Override
	public CommonCounty getCounty(String countyId) {
		loadCommonCountyies();
		for(CommonCounty c: countyList){
			if(c.getErpId().equals(countyId)){
				try {
					return (CommonCounty)DeepCloneUtil.cloneObject(c);
				} catch (IllegalArgumentException ex) {
					log.warn("Deep clone for CommonCounty failed.", ex);
				} catch (IllegalAccessException ex) {
					log.warn("Deep clone for CommonCounty failed.", ex);
				} catch (InstantiationException ex) {
					log.warn("Deep clone for CommonCounty failed.", ex);
				}
				//如果克隆失败，则返回原始对象
				return c;
			}
		}
		return null;
	}
	
	/**
	 * 查询所有城区
	 * @return
	 */
	@Override
	public List<CommonCounty> getCounties() {
		loadCommonCountyies();
		return countyList;
	}
	
	/**
	 * 查询所有城区
	 * @return
	 */
	@Override
	public List<CommonCounty> getCommonCountyies() {
		loadCommonCountyies();
		return countyList;
	}

	protected void loadCommonCountyies(){
		if(countyList==null || countyList.isEmpty() || isNeedReload()){
			synchronized (CommonCountyServiceImpl.class) {
				if(countyList==null || countyList.size()<1 || isNeedReload()) {
					CommonCountyExample example = new CommonCountyExample();
					example.createCriteria().andDeleteflagEqualTo(0);
					example.setOrderByClause("sort_flag asc");
					
					countyList = commonCountyMapper.selectByExample(example);

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
