/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.PageAccessQuantityMapper;
import cn.hshb.web.biz.mybatis.model.PageAccessQuantity;
import cn.hshb.web.biz.mybatis.model.PageAccessQuantityKey;
import cn.hshb.web.biz.service.PageAccessQuantityService;

/**
 * @author ShengYoufu
 *
 */
@Service
public class PageAccessQuantityServiceImpl implements PageAccessQuantityService {
	
	@Autowired
	private PageAccessQuantityMapper pageAccessQuantityMapper;
	
	
	/**
	 * 根据页面ID获取页面访问量
	 * @param pageInfoId	页面ID
	 * @param description	页面说明
	 * @return
	 */
	@Override
	public PageAccessQuantity getAccessQuantity(String pageInfoId, String description) {
		PageAccessQuantityKey key = new PageAccessQuantityKey();
		key.setPageInformationId(pageInfoId);
		PageAccessQuantity paq = pageAccessQuantityMapper.selectByPrimaryKey(key);
		return paq;
	}

	/**
	 * 更新页面访问量
	 * @param pageAccessQuantity
	 */
	@Override
	public void updateAccessQuantity(PageAccessQuantity pageAccessQuantity) {
		if(pageAccessQuantity != null){
			pageAccessQuantity.setAccessQuantity(pageAccessQuantity.getAccessQuantity()+1);
			pageAccessQuantityMapper.updateByPrimaryKey(pageAccessQuantity);
		}
	}

	@Override
	public PageAccessQuantity insertAccessQuantity(  String pageInfoId, String description) {
		PageAccessQuantity	pageAccesQuantity = new PageAccessQuantity();
		pageAccesQuantity.setPageInformationId(pageInfoId);
		pageAccesQuantity.setAccessQuantity(0);
		pageAccesQuantity.setDescription(description);
		pageAccessQuantityMapper.insert(pageAccesQuantity);
		return pageAccesQuantity;
	}
	
}
