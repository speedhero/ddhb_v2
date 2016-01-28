/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.FrendLinkMapper;
import cn.hshb.web.biz.mybatis.model.FrendLink;
import cn.hshb.web.biz.mybatis.model.FrendLinkExample;
import cn.hshb.web.biz.service.FrendLinkService;

/**
 * @author ShengYoufu
 *
 */
@Service
public class FrendLinkServiceImpl implements FrendLinkService {
	//用静态变量缓存，避免多次查询
	private static List<FrendLink> linkList = null;
	
	//最后刷新时间
	private static Long LAST_LOAD_TIME = System.currentTimeMillis();
	//刷新间隔
	private static Long RELOAD_INTERVAL = 24 * 3600L;
	
	@Autowired
	private FrendLinkMapper frendLinkMapper;
	
	@Override
	public List<FrendLink> getFrendLinks() {
		loadFrendLinks();
		return linkList;
	}

	protected void loadFrendLinks(){
		if(linkList==null || linkList.isEmpty() || isNeedReload()){
			synchronized (CommonLiveFacilityServiceImpl.class) {
				if(linkList==null || linkList.isEmpty() || isNeedReload()) {
					FrendLinkExample example = new FrendLinkExample();
					example.createCriteria().andDeleteFlagEqualTo(0);
					linkList = frendLinkMapper.selectByExample(example);
					
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
