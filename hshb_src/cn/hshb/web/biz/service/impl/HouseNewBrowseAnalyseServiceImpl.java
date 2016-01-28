/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.HouseNewBrowseAnalyseMapper;
import cn.hshb.web.biz.mybatis.model.HouseNewBrowseAnalyse;
import cn.hshb.web.biz.service.HouseNewBrowseAnalyseService;

/**
 * @author ShengYoufu
 *
 */
@Service
public class HouseNewBrowseAnalyseServiceImpl implements HouseNewBrowseAnalyseService {
	private static final Log log = LogFactory.getLog(HouseNewBrowseAnalyseServiceImpl.class);
	
	@Autowired
	private HouseNewBrowseAnalyseMapper houseNewBrowseAnalyseMapper;
	
	/**
	 * 保存新楼盘浏览记录
	 * @param record
	 * @return
	 */
	@Override
	public Boolean save(HouseNewBrowseAnalyse record){
		int ret = houseNewBrowseAnalyseMapper.insertSelective(record);
		return ret>0;
	}
	
}
