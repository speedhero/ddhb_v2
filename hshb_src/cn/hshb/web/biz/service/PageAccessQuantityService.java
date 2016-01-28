/**
 * 
 */
package cn.hshb.web.biz.service;

import cn.hshb.web.biz.mybatis.model.PageAccessQuantity;

/**
 * @author ShengYoufu
 *
 */
public interface PageAccessQuantityService {
	/**
	 * 根据页面ID获取页面访问量
	 * @param pageInfoId	页面ID
	 * @param description	页面说明
	 * @return
	 */
	public PageAccessQuantity getAccessQuantity(String pageInfoId, String description);
	
	/**
	 * 更新页面访问量
	 * @param pageAccessQuantity
	 */
	public void updateAccessQuantity(PageAccessQuantity pageAccessQuantity);
	
	/**
	 * 新增页面访问对象
	 * @param pageAccesQuantity
	 */
	public PageAccessQuantity insertAccessQuantity( String pageInfoId, String description);
}
