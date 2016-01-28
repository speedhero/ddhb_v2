/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.CommonCbd;
import cn.hshb.web.biz.mybatis.model.CommonCbdWebsite;

/**
 * @author Administrator
 *
 */
public interface CommonCbdService {
	/**
	 * 获取所有商圈信息
	 * @return
	 */
	public List<CommonCbd> getCommonCbds();
	
	/**
	 * 根据ID查询ERP商圈
	 * @param cbdId
	 * @return
	 */
	public CommonCbd getCommonCbdById(String cbdId);
	
	/**
	 * 根据网站商圈ID查询ERP商圈
	 * @param webCbdid
	 * @return
	 */
	public List<CommonCbd> getCommonCbdByWebCbdId(Integer webCbdId );
	
	/**
	 * 根据城区ID查询商圈
	 * @param countyId
	 * @return
	 */
	public List<CommonCbd> getCommonCbdByCountyId(String countyId );
	
	/**
	 * 根据ERP商圈ID查询网站商圈
	 * @param webCbdid
	 * @return
	 */
	public List<CommonCbdWebsite> getCommonCbdWebsiteByCbdId(String cbdId );

	/**
	 * 根据城区ID取网站商圈
	 * @param countyId
	 * @return
	 */
	public List<CommonCbdWebsite> getCommonCbdWebsiteByCountyId(String countyId );
	
	
	/**
	 * 根据网站商圈ID查询网站商圈
	 * @param cbdwId 网站商圈ID
	 * @return
	 */
	public CommonCbdWebsite getCommonCbdWebsiteById(Integer cbdwId);	
}
