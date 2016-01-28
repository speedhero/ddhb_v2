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

import com.github.pagehelper.PageHelper;

import cn.hshb.web.biz.mybatis.dao.CommonFooterLinkMapper;
import cn.hshb.web.biz.mybatis.dao.CommonFooterMenuMapper;
import cn.hshb.web.biz.mybatis.model.CommonFooterLink;
import cn.hshb.web.biz.mybatis.model.CommonFooterLinkExample;
import cn.hshb.web.biz.mybatis.model.CommonFooterMenu;
import cn.hshb.web.biz.mybatis.model.CommonFooterMenuExample;
import cn.hshb.web.biz.service.CommonFooterLinkService;

/**
 * @author ShengYoufu
 *
 */
@Service
public class CommonFooterLinkServiceImpl implements CommonFooterLinkService {
	private static final Log log = LogFactory.getLog(CommonFooterLinkServiceImpl.class);
	
	private static final Integer PAGE_SIZE=10;
	
	@Autowired
	private CommonFooterMenuMapper commonFooterMenuMapper;

	@Autowired
	private CommonFooterLinkMapper commonFooterLinkMapper;
	
	/**
	 * 查询面底SEO菜单
	 * @param pageNum	页码
	 * @param pageSize	每页记录数
	 * @return
	 */
	@Override
	public List<CommonFooterMenu> getFooterMenu(Integer pageNum, Integer pageSize) {
		Integer pgNum = (pageNum==null || pageNum<1) ? 1 : pageNum;
		Integer pgSize = (pageSize==null || pageSize<1) ? PAGE_SIZE : pageSize;
		CommonFooterMenuExample example = new CommonFooterMenuExample();
		example.createCriteria().andDeleteflagEqualTo(0);
		PageHelper.startPage(pgNum, pgSize);
		return commonFooterMenuMapper.selectByExample(example);
	}

	/**
	 * 根据ID删除页底部SEO菜单
	 * @param ids
	 */
	@Override
	public void deleteFooterMenu(Integer[] ids) {
		List<Integer> idList = new ArrayList<Integer>();
		for(Integer i: ids){
			idList.add(i);
		}
		CommonFooterMenuExample example = new CommonFooterMenuExample();
		example.createCriteria().andMenuIdIn(idList);
		CommonFooterMenu record = new CommonFooterMenu();
		record.setDeleteflag(1);
		int ret = commonFooterMenuMapper.updateByExampleSelective(record, example);
	}

	/**
	 * 根据菜单ID删除页面底部SEO链接
	 * @param menuId
	 * @return
	 */
	@Override
	public List<CommonFooterLink> getLinksByMenuId(Integer menuId) {
		CommonFooterLinkExample example = new CommonFooterLinkExample();
		example.createCriteria()
			.andMenuIdEqualTo(menuId)
			.andDeleteflagEqualTo(0);
		return commonFooterLinkMapper.selectByExample(example);
	}

	/**
	 * 随机查询二手房热门小区
	 * @param count 数量
	 * @return
	 */
	@Override
	public List<CommonFooterLink> getRandomLinksForSale(Integer count) {
		CommonFooterLinkExample example = new CommonFooterLinkExample();
		example.createCriteria()
			.andLinkNameLike("%二手房%")
			.andDeleteflagEqualTo(0);

		//增加下面这个Order By即可实现随机
		example.setOrderByClause(" rand() LIMIT " + count + " ");
		
		return commonFooterLinkMapper.selectByExample(example);
	}
}
