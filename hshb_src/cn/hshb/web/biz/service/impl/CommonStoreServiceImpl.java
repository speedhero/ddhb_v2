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

import cn.hshb.web.biz.mybatis.dao.CommonStoreMapper;
import cn.hshb.web.biz.mybatis.model.CommonStore;
import cn.hshb.web.biz.mybatis.model.CommonStoreExample;
import cn.hshb.web.biz.service.CommonStoreService;
import cn.hshb.web.common.util.DeepCloneUtil;

/**
 * @author ShengYoufu
 *
 */
@Service
public class CommonStoreServiceImpl implements CommonStoreService {
	private static final Log log = LogFactory.getLog(CommonStoreServiceImpl.class);
	
	private static List<CommonStore> storeList = new ArrayList<CommonStore>();
	
	//最后刷新时间
	private static Long LAST_LOAD_TIME = System.currentTimeMillis();
	//刷新间隔
	private static Long RELOAD_INTERVAL = 24 * 3600L;

	@Autowired
	private CommonStoreMapper commonStoreMapper;
	
	@Override
	public CommonStore getCommonStore(String storeId) {
		loadCommonStores();
		for(CommonStore s: storeList){
			if(s.getErpId().equals(storeId)) return s;
		}
		loadCommonStores();
		for(CommonStore s: storeList){
			if(s.getErpId().equals(storeId)){
				try {
					return (CommonStore)DeepCloneUtil.cloneObject(s);
				} catch (IllegalArgumentException ex) {
					log.warn("Deep clone for CommonStore failed.", ex);
				} catch (IllegalAccessException ex) {
					log.warn("Deep clone for CommonStore failed.", ex);
				} catch (InstantiationException ex) {
					log.warn("Deep clone for CommonStore failed.", ex);
				}
				//如果克隆失败，则返回原始对象
				return s;
			}
		}
		return null;
	}

	@Override
	public List<CommonStore> getCommonStores() {
		loadCommonStores();
		return storeList;
	}

	@Override
	public CommonStore getCommonStoreByName(String name) {
		loadCommonStores();
		for(CommonStore s: storeList){
			if(s.getStoreName().equals(name)) return s;
		}
		return null;
	}
	
	/**
	 * 根据门店ID列表获取多个门信息
	 * @param storeIdList 门店ID列表
	 * @return
	 */
	@Override
	public List<CommonStore> getCommonStoresByIds(List<String> storeIdList){
		List<CommonStore> list = new ArrayList<CommonStore>();
		loadCommonStores();
		for(CommonStore s: storeList){
			if(storeIdList.contains(s.getErpId())){
				try {
					list.add((CommonStore)DeepCloneUtil.cloneObject(s));
				} catch (IllegalArgumentException ex) {
					log.warn("Deep clone for CommonStore failed.", ex);
					list.add(s);
				} catch (IllegalAccessException ex) {
					log.warn("Deep clone for CommonStore failed.", ex);
					list.add(s);
				} catch (InstantiationException ex) {
					log.warn("Deep clone for CommonStore failed.", ex);
					list.add(s);
				}
			}
		}
		return list;
		
	}

	protected void loadCommonStores(){
		if(storeList==null || storeList.isEmpty() || isNeedReload()){
			synchronized (CommonUsageServiceImpl.class) {
				if(storeList==null || storeList.isEmpty() || isNeedReload()) {
					CommonStoreExample example = new CommonStoreExample();
					example.createCriteria().andDeleteflagEqualTo(0);
					storeList = commonStoreMapper.selectByExample(example);

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
