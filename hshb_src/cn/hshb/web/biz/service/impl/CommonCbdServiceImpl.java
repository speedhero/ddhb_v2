/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.CommonCbdMapper;
import cn.hshb.web.biz.mybatis.dao.CommonCbdWebsiteMapper;
import cn.hshb.web.biz.mybatis.model.CommonCbd;
import cn.hshb.web.biz.mybatis.model.CommonCbdExample;
import cn.hshb.web.biz.mybatis.model.CommonCbdWebsite;
import cn.hshb.web.biz.mybatis.model.CommonCbdWebsiteExample;
import cn.hshb.web.biz.service.CommonCbdService;
import cn.hshb.web.common.util.DeepCloneUtil;
import cn.hshb.web.partner.baidu.common.StringUtil;

/**
 * @author Administrator
 *
 */
@Service
public class CommonCbdServiceImpl implements CommonCbdService {
	//用静态变量缓存住商圈信息，避免多次查询
	private static List<CommonCbd> cbdList = null;
	
	private static List<CommonCbdWebsite> websiteCbdList = null;//网站商圈

	//最后刷新时间
	private static Long LAST_LOAD_TIME = System.currentTimeMillis();
	//刷新间隔
	private static Long RELOAD_INTERVAL = 24 * 3600L;

	@Autowired
	private CommonCbdMapper commonCbdMapper;
	
	@Autowired
	private CommonCbdWebsiteMapper commonCbdWebsiteMapper;

	@Override
	public List<CommonCbd> getCommonCbds() {
		loadAllCbd();
		return cbdList;
	}
	
	/**
	 * 根据ID查询ERP商圈
	 * @param cbdId
	 * @return
	 */
	@Override
	public CommonCbd getCommonCbdById(String cbdId) {
		List<CommonCbd> list = getCommonCbds();
		for(CommonCbd cbd: list){
			if(cbd.getErpId().equals(cbdId)) return cbd;
		}
		return null;
	}
	
	/**
	 * 根据网站商圈ID查询ERP商圈
	 * @param webCbdid
	 * @return
	 */
	@Override
	public List<CommonCbd> getCommonCbdByWebCbdId(Integer webCbdId ){
		List<CommonCbd> list = getCommonCbds();
		List<CommonCbd> retList = new ArrayList<CommonCbd>();
		for(CommonCbd cbd: list){
			if(cbd.getBelongTo()!=null && Integer.parseInt(cbd.getBelongTo())==webCbdId){
				retList.add(cbd);
			}
		}
		return retList;
	}
	
	/**
	 * 根据城区ID查询商圈
	 * @param countyId
	 * @return
	 */
	@Override
	public List<CommonCbd> getCommonCbdByCountyId(String countyId ){
		List<CommonCbd> list = getCommonCbds();
		List<CommonCbd> retList = new ArrayList<CommonCbd>();
		for(CommonCbd cbd: list){
			if(cbd.getCountyId().equals(countyId)){
				retList.add(cbd);
			}
		}
		return retList;
	}
	
	/**
	 * 根据城区ID取网站商圈
	 * @param countyId
	 * @return
	 */
	@Override
	public List<CommonCbdWebsite> getCommonCbdWebsiteByCountyId(String countyId ){
		getCommonCbds();
		List<CommonCbdWebsite> retList = new ArrayList<CommonCbdWebsite>();
		for(CommonCbdWebsite cbdw: websiteCbdList){
			if(cbdw.getCountyId().equals(countyId)){
				retList.add(cbdw);
			}
		}
		return retList;
	}
	
	/**
	 * 根据ERP商圈ID查询网站商圈
	 * @param cbdId ERP商圈ID 
	 * @return
	 */
	@Override
	public List<CommonCbdWebsite> getCommonCbdWebsiteByCbdId(String cbdId ){
		loadAllCbd();
		List<CommonCbdWebsite> retList = new ArrayList<CommonCbdWebsite>();
		for(CommonCbdWebsite cbdw: websiteCbdList){
			for(CommonCbd cbd :cbdw.getErpCbdList()){
				if(cbd.getErpId() == cbdId){
					try {
						retList.add((CommonCbdWebsite)DeepCloneUtil.cloneObject(cbdw));
					} catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
						Log.error("克隆网站商圈对象失败.", e);
						retList.add(cbdw);
					}
					continue;
				}
			}
		}
		return retList;
	}
	
	/**
	 * 根据网站商圈ID查询网站商圈
	 * @param cbdwId 网站商圈ID
	 * @return
	 */
	@Override
	public CommonCbdWebsite getCommonCbdWebsiteById(Integer cbdwId){
		loadAllCbd();
		for(CommonCbdWebsite cbdw: websiteCbdList){
			if(cbdw.getWebsiteId() == cbdwId){
				try {
					return (CommonCbdWebsite)DeepCloneUtil.cloneObject(cbdw);
				} catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
					Log.error("克隆网站商圈对象失败.", e);
					return cbdw;
				}
			}
		}
		return null;
	}
	
	/**
	 * 查询所有商圈
	 * @return
	 */
	protected void loadAllCbd() {
		if(cbdList==null || cbdList.size()<1 || isNeedReload()){
			synchronized (CommonCbdServiceImpl.class) {
				if(cbdList==null || cbdList.size()<1 || isNeedReload()) {
					CommonCbdExample example = new CommonCbdExample();
					example.createCriteria().andDeleteflagEqualTo(0);
					cbdList = commonCbdMapper.selectExtByExample(example);
			
					List<Integer> wIds = new ArrayList<Integer>();
					for(CommonCbd cbd: cbdList){
						String websiteId = cbd.getBelongTo();
						if(StringUtil.isNotEmpty(websiteId) && StringUtil.isNumeric(websiteId)){
							wIds.add(Integer.parseInt(websiteId));
						}
					}
					
					CommonCbdWebsiteExample example1 = new CommonCbdWebsiteExample();
					example1.createCriteria().andWebsiteIdIn(wIds).andDeleteFlagEqualTo(0);
			
					websiteCbdList = commonCbdWebsiteMapper.selectByExample(example1);
					for(CommonCbdWebsite cbdw: websiteCbdList){
						for(CommonCbd cbd: cbdList){
							if(cbdw.getWebsiteId().equals(cbd.getBelongTo())){
								cbd.setWebsiteCbd(cbdw);
								cbdw.getErpCbdList().add(cbd);
							}
						}
					}
					
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
