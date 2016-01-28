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

import cn.hshb.web.biz.mybatis.dao.DdhbSystemSetMapper;
import cn.hshb.web.biz.mybatis.model.DdhbSystemSet;
import cn.hshb.web.biz.mybatis.model.DdhbSystemSetExample;
import cn.hshb.web.biz.service.DdhbSystemSetService;
import cn.hshb.web.common.util.DeepCloneUtil;

/**
 * @author ShengYoufu
 *
 */
@Service
public class DdhbSystemSetServiceImpl implements DdhbSystemSetService {
	private static final Log log = LogFactory.getLog(DdhbSystemSetServiceImpl.class);
	
	private static List<DdhbSystemSet> setList = new ArrayList<DdhbSystemSet>();
	
	//最后刷新时间
	private static Long LAST_LOAD_TIME = System.currentTimeMillis();
	//刷新间隔
	private static Long RELOAD_INTERVAL = 24 * 3600L;
	
	@Autowired
	private DdhbSystemSetMapper ddhbSystemSetMapper;
	
	@Override
	public List<DdhbSystemSet> getSystemSettings() {
		loadDdhbSystemSets();
		return setList;
	}

	@Override
	public DdhbSystemSet getSystemSettings(Integer setId) {
		loadDdhbSystemSets();
		for(DdhbSystemSet s: setList){
			if(s.getSetId().equals(setId)){
				try {
					return (DdhbSystemSet)DeepCloneUtil.cloneObject(s);
				} catch (IllegalArgumentException ex) {
					log.warn("根据设置ID取系统设置参数失败。", ex);
				} catch (IllegalAccessException ex) {
					log.warn("根据设置ID取系统设置参数失败。", ex);
				} catch (InstantiationException ex) {
					log.warn("根据设置ID取系统设置参数失败。", ex);
				}
				//如果克隆失败，则返回原始对象
				return s;
			}
		}
		return null;
	}


	@Override
	public List<DdhbSystemSet> getSystemSettings(String setName) {
		loadDdhbSystemSets();
		List<DdhbSystemSet> _setList = new ArrayList<DdhbSystemSet>();
		for(DdhbSystemSet s: setList){
			if(s.getSetName().equalsIgnoreCase(setName))
				try {
					_setList.add((DdhbSystemSet)DeepCloneUtil.cloneObject(s));
				} catch (IllegalArgumentException ex) {
					log.warn("根据设置名称取系统设置参数失败。", ex);
					_setList.add(s);
				} catch (IllegalAccessException ex) {
					log.warn("根据设置名称取系统设置参数失败。", ex);
					_setList.add(s);
				} catch (InstantiationException ex) {
					log.warn("根据设置名称取系统设置参数失败。", ex);
					_setList.add(s);
				}
		}
		return _setList;
	}
	
	@Override
	public DdhbSystemSet getSystemSetting(String setName) {
		loadDdhbSystemSets();
		for(DdhbSystemSet s: setList){
			if(s.getSetName().equalsIgnoreCase(setName)){
				try {
					return (DdhbSystemSet)DeepCloneUtil.cloneObject(s);
				} catch (IllegalArgumentException ex) {
					log.warn("根据设置名称取系统设置参数失败。", ex);
				} catch (IllegalAccessException ex) {
					log.warn("根据设置名称取系统设置参数失败。", ex);
				} catch (InstantiationException ex) {
					log.warn("根据设置名称取系统设置参数失败。", ex);
				}
				//如果克隆失败，则返回原始对象
				return s;
			}
		}
		return null;
	}

	protected void loadDdhbSystemSets(){
		if(setList==null || setList.isEmpty() || isNeedReload()){
			synchronized (DdhbSystemSetServiceImpl.class) {
				if(setList==null || setList.isEmpty() || isNeedReload()) {
					DdhbSystemSetExample example = new DdhbSystemSetExample();
					example.createCriteria().andDeleteflagEqualTo(0);
					setList = ddhbSystemSetMapper.selectByExample(example);
					
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
