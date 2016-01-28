package cn.hshb.web.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.CommonSubwayStationMapper;
import cn.hshb.web.biz.mybatis.model.CommonSubwayStation;
import cn.hshb.web.biz.mybatis.model.CommonSubwayStationExample;
import cn.hshb.web.biz.service.CommonSubwayStationService;

@Service
public class CommonSubwayStationServiceImpl implements
		CommonSubwayStationService {
	@Autowired
	CommonSubwayStationMapper commonSubwayStationMapper;
	
	@Override
	public List<CommonSubwayStation> getCommonSubwayStationByName(String name) {
		CommonSubwayStationExample  example = new CommonSubwayStationExample();
		example.createCriteria().andDeleteflagEqualTo(0).andStationNameLike(name);
		List<CommonSubwayStation> list =  commonSubwayStationMapper.selectByExample(example);
		return list;
	}

}
