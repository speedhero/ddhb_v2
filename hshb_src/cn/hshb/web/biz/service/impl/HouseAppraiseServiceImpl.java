/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.HouseAppraiseMapper;
import cn.hshb.web.biz.mybatis.model.HouseAppraise;
import cn.hshb.web.biz.mybatis.model.HouseAppraiseExample;
import cn.hshb.web.biz.service.HouseAppraiseService;

/**
 * @author Administrator
 *
 */
@Service
public class HouseAppraiseServiceImpl implements HouseAppraiseService {

	@Autowired
	private HouseAppraiseMapper houseAppraiseMapper;
	
	@Override
	public List<HouseAppraise> findAppraiseListByHouseId(String houseId) {
		//没有添加经纪人信息
//		HouseAppraiseExample example = new HouseAppraiseExample();
//		example.createCriteria().andHouseIdEqualTo(houseId).andDeleteflagEqualTo(0);
//		return houseAppraiseMapper.selectByExample(example);
		return houseAppraiseMapper.selectHouseAppraiseByHouseId(houseId);
	}

	@Override
	public List<HouseAppraise> findAppraiseListByBrokerIdHouseId(String brokerId, String houseId) {
		HouseAppraiseExample example = new HouseAppraiseExample();
		example.createCriteria()
			.andHouseIdEqualTo(houseId)
			.andBrokerEqualTo(brokerId)
			.andDeleteflagEqualTo(0);
		return houseAppraiseMapper.selectByExample(example);
	}
	
	public List<HouseAppraise> findAppraiseListByShelvingId(String shelvingId) {
		return houseAppraiseMapper.selectHouseAppraiseByShelvingId(shelvingId);
	}
}
