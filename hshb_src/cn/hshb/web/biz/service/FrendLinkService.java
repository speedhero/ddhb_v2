/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.FrendLink;

/**
 * @author ShengYoufu
 *
 */
public interface FrendLinkService {
	
	/**
	 * 载入所有友情链接
	 * @return
	 */
	List<FrendLink> getFrendLinks();
	
	
}
