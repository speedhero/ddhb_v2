/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.HouseNewhouseGroupbuyMapper;
import cn.hshb.web.biz.mybatis.model.HouseNewhouseGroupbuy;
import cn.hshb.web.biz.mybatis.model.HouseNewhouseGroupbuyExample;
import cn.hshb.web.biz.service.HouseNewhouseGroupbuyService;

/**
 * @author ShengYoufu
 *
 */
@Service
public class HouseNewhouseGroupbuyServiceImpl implements HouseNewhouseGroupbuyService {
	private static final Log log = LogFactory.getLog(HouseNewhouseGroupbuyServiceImpl.class);
	
	@Autowired
	private HouseNewhouseGroupbuyMapper houseNewhouseGroupbuyMapper;
	
	/**
	 * 查询指定日期后(含指定日期)的团购人数
	 */
	@Override
	public List<HouseNewhouseGroupbuy> getNewHouseGroupBuyAfterDate(Date endDate) {
		HouseNewhouseGroupbuyExample example = new HouseNewhouseGroupbuyExample();
		example.createCriteria()
			.andApplyDateGreaterThanOrEqualTo(endDate)
			.andDeleteflagEqualTo(0);
		List<HouseNewhouseGroupbuy> list = houseNewhouseGroupbuyMapper.selectByExample(example);
		return list;
	}
	

	/**
	 * 查询团购人数
	 */
	@Override
	public Integer getCountGroupBuy() {
		HouseNewhouseGroupbuyExample example = new HouseNewhouseGroupbuyExample();
		example.createCriteria().andDeleteflagEqualTo(0);
		return houseNewhouseGroupbuyMapper.countByExample(example);
	}

	/**
	 * 根据楼盘ID统计团购参与人数
	 */
	@Override
	public List<Object> getHouseEntrants(Integer[] houseIds) {
		List<Integer> idList = new ArrayList<Integer>();
		for(Integer id: houseIds)
			idList.add(id);
		HouseNewhouseGroupbuyExample example = new HouseNewhouseGroupbuyExample();
		example.createCriteria().andNewhouseNoIn(idList);
		return houseNewhouseGroupbuyMapper.selectCountByHouseNo(example);
	}
	
	/**
	 * 根据楼盘ID统计团购参与人数
	 * @param houseIds
	 * @return
	 */
	@Override
	public List<Map<String, Integer>> getHouseGroupbuyCount(List<Integer> houseIds) {
		HouseNewhouseGroupbuyExample example = new HouseNewhouseGroupbuyExample();
		example.createCriteria().andNewhouseNoIn(houseIds);
		return (List<Map<String, Integer>>)houseNewhouseGroupbuyMapper.selectCountByHouseNo(example);
	}

	/**
	 * 根据新楼盘ID查询团购记录
	 * @param houseIds
	 * @return
	 */
	@Override
	public List<HouseNewhouseGroupbuy> getHouseGroupbuys(List<Integer> houseIds) {
		HouseNewhouseGroupbuyExample example = new HouseNewhouseGroupbuyExample();
		example.createCriteria()
			.andNewhouseNoIn(houseIds)
			.andDeleteflagEqualTo(0);
		return houseNewhouseGroupbuyMapper.selectByExample(example);
	}

	/**
	 * 根据楼盘ID统计团购参与人数
	 */
	@Override
	public Integer getHouseNewEntrantsById(Integer houseId) {
		HouseNewhouseGroupbuyExample example = new HouseNewhouseGroupbuyExample();
		example.createCriteria().andNewhouseNoEqualTo(houseId.intValue());
		return houseNewhouseGroupbuyMapper.countByExample(example);
	}

	/**
	 * 根据客户电话和新楼盘编号查询团购信息
	 * @param telephone	客户电话
	 * @param houseId	新楼盘ID
	 * @return
	 */
	@Override
	public Boolean isExistsGroupbuy(String telephone, Integer houseId) {
		HouseNewhouseGroupbuyExample example = new HouseNewhouseGroupbuyExample();
		example.createCriteria()
			.andClientTelephoneEqualTo(telephone)
			.andNewhouseNoEqualTo(houseId);
		List<HouseNewhouseGroupbuy> list = houseNewhouseGroupbuyMapper.selectByExample(example);
		return (list!=null && list.size()>0);
	}


	/**
	 * 保存新房团购记录
	 * @param record
	 * @return
	 */
	@Override
	public Boolean save(HouseNewhouseGroupbuy record) {
		int ret = houseNewhouseGroupbuyMapper.insertSelective(record);
		return ret>0;
	}	
	
}
