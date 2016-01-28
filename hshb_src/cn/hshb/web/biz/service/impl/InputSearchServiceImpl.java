package cn.hshb.web.biz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.opensymphony.oscache.util.StringUtil;

import cn.hshb.web.biz.mybatis.dao.CommonCbdWebsiteMapper;
import cn.hshb.web.biz.mybatis.dao.CommonSubwayMapper;
import cn.hshb.web.biz.mybatis.dao.CommonSubwayStationMapper;
import cn.hshb.web.biz.mybatis.model.CommonCbdWebsite;
import cn.hshb.web.biz.mybatis.model.CommonSubwayStation;
import cn.hshb.web.biz.mybatis.model.HouseCommunity;
import cn.hshb.web.biz.mybatis.model.InputSearch;
import cn.hshb.web.biz.service.HouseCommunityService;
import cn.hshb.web.biz.service.InputSearchService;
import cn.hshb.web.biz.util.ChineseConversionUtil;

@Service
public class InputSearchServiceImpl implements InputSearchService {
	private static final Log log =  LogFactory.getLog(InputSearchServiceImpl.class);
	private static List<InputSearch> inputSearchList = null;
	//最后刷新时间
	private static Long LAST_LOAD_TIME = System.currentTimeMillis();
	//刷新间隔
	private static Long RELOAD_INTERVAL = 1 * 3600L;
	//显示的数量
	private static final Integer SHOW_SIZE = 15;
	
	
	@Autowired
	HouseCommunityService houseCommunityService;
	@Autowired
	CommonCbdWebsiteMapper commonCbdWebsiteMapper;
	@Autowired
	CommonSubwayStationMapper commonSubwayStationMapper;
	
	@Override
	public String getSearchJsonData(String searchKeyword) {
		loadinputSearchList();
		String strJson = "";
		if(StringUtil.isEmpty(searchKeyword))
			return strJson;
		Gson gson = new Gson();
		//使用Map进行存储,list会使搜索名称重复
		List<InputSearch> _inputSearchList = new ArrayList<InputSearch>();
		Map<String, String> _inputSearchMap = new HashMap<String, String>();
		int i = 1;
		for(InputSearch is : inputSearchList){
			if(i > SHOW_SIZE)break;
			//比对首字母、全拼、名称
			if((is.getFirstLetter().toLowerCase()).startsWith(searchKeyword.toLowerCase()) 
					|| (is.getSpelling().toLowerCase()).startsWith(searchKeyword.toLowerCase()) 
					|| (is.getSearchName()).contains(searchKeyword) ){
				if(!_inputSearchMap.containsKey(is.getSearchName())){
					_inputSearchMap.put(is.getSearchName(), is.getSearchName());
					_inputSearchList.add(is);
					i++;
				}
			}
		}
		
		return gson.toJson(_inputSearchList);
	}
	private void loadinputSearchList(){
		if(inputSearchList == null || inputSearchList.isEmpty() || isNeedReload()){
			synchronized (InputSearchServiceImpl.class) {
				if(inputSearchList == null || inputSearchList.isEmpty() || isNeedReload()){
					inputSearchList = new ArrayList<InputSearch>();
					//加载小区
					List<HouseCommunity> communityList = houseCommunityService.getAllHouseCommunitys();
					if(communityList != null ){
						for(HouseCommunity hc : communityList){
							InputSearch is = new InputSearch();
							if(StringUtil.isEmpty(hc.getCommunityName()))
								continue;
							is.setId(hc.getErpId());
							is.setSearchName(hc.getCommunityName());
							is.setFirstLetter(ChineseConversionUtil.converterToFirstSpell(hc.getCommunityName()));
							is.setSpelling(ChineseConversionUtil.converterToSpell(hc.getCommunityName()));
							inputSearchList.add(is);
						}
					}
					//加载商圈
					List<CommonCbdWebsite> cbdWebsiteList = commonCbdWebsiteMapper.selectAllCbdWebsite();
					if(cbdWebsiteList != null){
						for(CommonCbdWebsite cc : cbdWebsiteList){
							InputSearch is = new InputSearch();
							if(StringUtil.isEmpty(cc.getName()))
								continue;
							is.setId((cc.getWebsiteId()).toString());
							is.setSearchName(cc.getName());
							is.setFirstLetter(ChineseConversionUtil.converterToFirstSpell(cc.getName()));
							is.setSpelling(ChineseConversionUtil.converterToSpell(cc.getName()));
							inputSearchList.add(is);
						}
					}
					//加载地铁站点
					List<CommonSubwayStation> stationList = commonSubwayStationMapper.selectAllSubwayStation();
					if(stationList != null){
						for(CommonSubwayStation css : stationList){
							InputSearch is = new InputSearch();
							if(StringUtil.isEmpty(css.getStationName()))
								continue;
							is.setId(css.getErpId());
							is.setSearchName(css.getStationName());
							is.setFirstLetter(ChineseConversionUtil.converterToFirstSpell(css.getStationName()));
							is.setSpelling(ChineseConversionUtil.converterToSpell(css.getStationName()));
							inputSearchList.add(is);
						}
					}
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
