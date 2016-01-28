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

import cn.hshb.web.biz.mybatis.dao.CommonActivityMapper;
import cn.hshb.web.biz.mybatis.dao.CommonAdandactivitybarMapper;
import cn.hshb.web.biz.mybatis.dao.CommonAdandactivityitemMapper;
import cn.hshb.web.biz.mybatis.model.CommonActivity;
import cn.hshb.web.biz.mybatis.model.CommonActivityExample;
import cn.hshb.web.biz.mybatis.model.CommonAdandactivitybar;
import cn.hshb.web.biz.mybatis.model.CommonAdandactivitybarExample;
import cn.hshb.web.biz.mybatis.model.CommonAdandactivityitem;
import cn.hshb.web.biz.mybatis.model.CommonAdandactivityitemExample;
import cn.hshb.web.biz.service.CommonActivityService;
import cn.hshb.web.common.util.StringUtil;

/**
 * @author ShengYoufu
 *
 */
@Service
public class CommonActivityServiceImpl implements CommonActivityService {
	private static final Log log = LogFactory.getLog(CommonActivityServiceImpl.class);
	
	//广告位缓存
	private static List<CommonAdandactivitybar> activityBarList = null;
	
	@Autowired
	private CommonActivityMapper commonActivityMapper;
	
	@Autowired
	private CommonAdandactivitybarMapper commonAdandactivitybarMapper;
	
	@Autowired
	private CommonAdandactivityitemMapper commonAdandactivityitemMapper;

	@Override
	public List<CommonActivity> loadActivity(String moduleId) {
		CommonActivityExample example = new CommonActivityExample();
		example.createCriteria()
			.andModuleIdLike(moduleId)
			.andEnabledFlagEqualTo(0)
			.andDeleteFlagEqualTo(0);
		
		example.setOrderByClause("actitity_type asc");
		List<CommonActivity> list = commonActivityMapper.selectByExample(example);
		return list;
	}

	/**
	 * 根据页面标志和位置标志获取广告条信息
	 * @param pageFlag			页面标志
	 * @param positionFlag		位置标志
	 * @return
	 */
	public CommonAdandactivitybar getActivityByPageAndPosition(Integer pageFlag, Integer positionFlag) {
		loadAdandActivityBars();
		for(CommonAdandactivitybar bar: activityBarList){
			if(bar.getPageflag()==pageFlag && bar.getPositionflag()==positionFlag){
				if(!(pageFlag == 1 && positionFlag == 2)){
					//pageFlag == 1 && positionFlag == 2表示页面底部的大广告条，其他小广告条则要求位置个数和已录入广告数相等才能显示
					if(bar.getItemcount()==bar.getCurrentcount()){
						return bar;
					}
				}else{
					return bar;
				}
			}
		}
		return null;
	}
	
	/**
	 * 根据广告条ID获取广告项目
	 * @param barId	广告条ID
	 * @return
	 */
	public List<CommonAdandactivityitem> getActivityItemByBarId(String barId) {
		loadAdandActivityBars();
		if(StringUtil.isEmpty(barId) || !StringUtil.isNumeric(barId)) return null;
		Integer _barId = Integer.parseInt(barId);
		for(CommonAdandactivitybar bar: activityBarList){
			if(bar.getBarId()==_barId){
				return bar.getActivityItems();
			}
		}
		return null;
	}

	/**
	 * 广告点击量加1
	 * @param adItemId	广告项ID
	 */
	public void addBrosweredCounter(Integer adItemId) {
		if(adItemId!=null)
			commonAdandactivityitemMapper.addBrowsedByItemId(adItemId);
	}	

	/**
	 * 装载所有广告条
	 */
	protected void loadAdandActivityBars(){
		if(activityBarList==null || activityBarList.isEmpty()){
			synchronized (CommonActivityServiceImpl.class) {
				if(activityBarList==null || activityBarList.size()<1) {
					CommonAdandactivitybarExample example = new CommonAdandactivitybarExample();
					example.createCriteria().andDeleteflagEqualTo(0);
					activityBarList = commonAdandactivitybarMapper.selectByExample(example);
					List<Integer> barIds = new ArrayList<Integer>();
					for(CommonAdandactivitybar bar: activityBarList){
						barIds.add(bar.getBarId());
					}
					
					//查询相关联的CommonAdandactivityitem
					CommonAdandactivityitemExample example1 = new CommonAdandactivityitemExample();
					example1.createCriteria().andBarIdIn(barIds).andDeleteFlagEqualTo(0);
					List<CommonAdandactivityitem> list = commonAdandactivityitemMapper.selectByExample(example1);
					for(CommonAdandactivitybar bar: activityBarList){
						for(CommonAdandactivityitem item: list){
							if(bar.getBarId()==item.getBarId()){
								bar.getActivityItems().add(item);
							}
						}
					}
				}
			}
		}
	}
	
}
