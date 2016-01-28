/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.CommonCbdWebsiteMapper;
import cn.hshb.web.biz.mybatis.dao.CommonCountyTwoMapper;
import cn.hshb.web.biz.mybatis.model.CommonCbdWebsite;
import cn.hshb.web.biz.mybatis.model.CommonCbdWebsiteExample;
import cn.hshb.web.biz.mybatis.model.CommonCountyTwo;
import cn.hshb.web.biz.mybatis.model.CommonCountyTwoExample;
import cn.hshb.web.biz.service.CommonCountyTwoService;
import cn.hshb.web.common.util.StringUtil;

/**
 * @author ShengYoufu
 *
 */
@Service
public class CommonCountyTwoServiceImpl implements CommonCountyTwoService {
	private static List<CommonCountyTwo> commonCountyTwoList = null;
	
	//最后刷新时间
	private static Long LAST_LOAD_TIME = System.currentTimeMillis();
	//刷新间隔
	private static Long RELOAD_INTERVAL = 24 * 3600L;
	
	@Autowired
	private CommonCountyTwoMapper commonCountyTwoMapper;
	
	@Autowired
	private CommonCbdWebsiteMapper commonCbdWebsiteMapper;

	/**
	 * 缓存杭州租房查询条件
	 * @return
	 */
	private synchronized List<CommonCountyTwo> findCommonCountyTwoList(){
		if(commonCountyTwoList == null || commonCountyTwoList.isEmpty() || isNeedReload()){
			CommonCountyTwoExample example = new CommonCountyTwoExample();
			example.createCriteria().andDeleteflagEqualTo(0);
			commonCountyTwoList = commonCountyTwoMapper.selectByExample(example);
			
			for(CommonCountyTwo c: commonCountyTwoList){
				List<Integer> cbdWebsiteIds = new ArrayList<Integer>();
				if(StringUtil.isNotEmpty(c.getCommonIds())){
					String[] ids = c.getCommonIds().split(",");
					for(String s: ids){
						cbdWebsiteIds.add(Integer.parseInt(s));
					}
				}
				//对取出的商圈Id进行查询并保存
				if(cbdWebsiteIds.size() > 0){
					CommonCbdWebsiteExample example1 = new CommonCbdWebsiteExample();
					example1.createCriteria().andWebsiteIdIn(cbdWebsiteIds);
					List<CommonCbdWebsite> list = commonCbdWebsiteMapper.selectByExample(example1);
					c.setCbdWebsiteList(list);
					c.setCbdWebisteIdList(cbdWebsiteIds);
				}
			}
			
			LAST_LOAD_TIME = System.currentTimeMillis();  //重新设置最后刷新时间
		}
		return commonCountyTwoList;
	}
	
	@Override
	public List<CommonCountyTwo> getCommonCountyTwoList(){
		if(commonCountyTwoList == null){
			return findCommonCountyTwoList();
		}else{
			return commonCountyTwoList;
		}
	}
	/**
	 * 随机查询租赁房热门小区
	 * @param count 数量
	 * @return
	 */
	@Override
	public List<CommonCbdWebsite> getRandomHotCommunityForRent(Integer count) {
		List<CommonCountyTwo> list = getCommonCountyTwoList();
		
		//获取全部id
		List<Integer> cbdWebsiteIdsList = new ArrayList<Integer>();
		for(CommonCountyTwo c: list){
			if(StringUtil.isNotEmpty(c.getCommonIds())){
				String[] ids = c.getCommonIds().split(",");
				for(String s: ids){
					cbdWebsiteIdsList.add(Integer.parseInt(s));
				}
			}
		}

		if(cbdWebsiteIdsList.size()>0){
			CommonCbdWebsiteExample example1 = new CommonCbdWebsiteExample();
			example1.createCriteria().andWebsiteIdIn(cbdWebsiteIdsList);
			example1.setOrderByClause(" rand() LIMIT " + count + " ");
			List<CommonCbdWebsite> cbdWebsiteList = commonCbdWebsiteMapper.selectByExample(example1);
			return cbdWebsiteList;
		}
		return null;
	}

	@Override
	public List<Integer> getCommonCbdWebsiteIdList(String id) {
		List<CommonCountyTwo> list = getCommonCountyTwoList();
		if(StringUtils.isNotEmpty(id)){
			for(CommonCountyTwo c : list){
				if(id.equals(c.getErpId()))
					return c.getCbdWebisteIdList();
			}
		}
		return null;
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
