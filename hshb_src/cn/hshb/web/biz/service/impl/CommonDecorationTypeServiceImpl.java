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

import cn.hshb.web.biz.mybatis.dao.CommonDecorationTypeMapper;
import cn.hshb.web.biz.mybatis.model.CommonDecorationType;
import cn.hshb.web.biz.mybatis.model.CommonDecorationTypeExample;
import cn.hshb.web.biz.service.CommonDecorationTypeService;
import cn.hshb.web.common.util.DeepCloneUtil;

/**
 * @author ShengYoufu
 *
 */
@Service
public class CommonDecorationTypeServiceImpl implements CommonDecorationTypeService {
	private static final Log log = LogFactory.getLog(CommonDecorationTypeServiceImpl.class);
	
	private static List<CommonDecorationType> decorationList = new ArrayList<CommonDecorationType>();
	
	//最后刷新时间
	private static Long LAST_LOAD_TIME = System.currentTimeMillis();
	//刷新间隔
	private static Long RELOAD_INTERVAL = 24 * 3600L;
	
	@Autowired
	private CommonDecorationTypeMapper commonDecorationTypeMapper;
	
	/**
	 * 
	 * @return
	 */
	@Override
	public CommonDecorationType getDecorationType(String decorationId) {
		loadDecorationTypes();
		for(CommonDecorationType d: decorationList){
			if(d.getErpId().equals(decorationId)){
				try {
					return (CommonDecorationType)DeepCloneUtil.cloneObject(d);
				} catch (IllegalArgumentException ex) {
					log.warn("Deep clone for CommonDecorationType failed.", ex);
				} catch (IllegalAccessException ex) {
					log.warn("Deep clone for CommonDecorationType failed.", ex);
				} catch (InstantiationException ex) {
					log.warn("Deep clone for CommonDecorationType failed.", ex);
				}
				//如果克隆失败，则返回原始对象
				return d;
			}
		}
		return null;
	}
	
	/**
	 * 载入所有装修类别数据
	 * @return
	 */
	@Override
	public List<CommonDecorationType> getDecorationTypes() {
		loadDecorationTypes();
		return decorationList;
	}
	
	/**
	 * 根据 ID获取装修类别
	 * @param typeId
	 * @return
	 */
	@Override
	public CommonDecorationType getDecorationTypeById(String typeId){
		loadDecorationTypes();
		for(CommonDecorationType d: decorationList){
			if(d.getErpId().equals(typeId)) return d;
		}
		return null;
	}
	
	/**
	 * 根据名称获取装修类别
	 * @param name
	 * @return
	 */
	@Override
	public CommonDecorationType getDecorationTypeByName(String name){
		loadDecorationTypes();
		for(CommonDecorationType d: decorationList){
			if(d.getDecorationName().equals(name)) return d;
		}
		return null;
	}	
	
	protected void loadDecorationTypes(){
		if(decorationList==null || decorationList.isEmpty() || isNeedReload()){
			synchronized (CommonDecorationTypeServiceImpl.class) {
				if(decorationList==null || decorationList.size()<1 || isNeedReload()) {
					CommonDecorationTypeExample example = new CommonDecorationTypeExample();
					example.createCriteria().andDeleteflagEqualTo(0);
					decorationList = commonDecorationTypeMapper.selectByExample(example);
					
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
