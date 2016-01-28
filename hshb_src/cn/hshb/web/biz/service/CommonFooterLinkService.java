/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageHelper;

import cn.hshb.web.biz.mybatis.model.CommonFooterLink;
import cn.hshb.web.biz.mybatis.model.CommonFooterLinkExample;
import cn.hshb.web.biz.mybatis.model.CommonFooterMenu;
import cn.hshb.web.biz.mybatis.model.CommonFooterMenuExample;

/**
 * @author ShengYoufu
 *
 */
public interface CommonFooterLinkService {
	/**
	 * 查询面底SEO菜单
	 * @param pageNum	页码
	 * @param pageSize	每页记录数
	 * @return
	 */
	public List<CommonFooterMenu> getFooterMenu(Integer pageNum, Integer pageSize);

	/**
	 * 根据ID删除页底部SEO菜单
	 * @param ids
	 */
	public void deleteFooterMenu(Integer[] ids);

	/**
	 * 根据菜单ID删除页面底部SEO链接
	 * @param menuId
	 * @return
	 */
	public List<CommonFooterLink> getLinksByMenuId(Integer menuId);

	/**
	 * 随机查询二手房热门小区
	 * @param count 数量
	 * @return
	 */
	public List<CommonFooterLink> getRandomLinksForSale(Integer count);
}
