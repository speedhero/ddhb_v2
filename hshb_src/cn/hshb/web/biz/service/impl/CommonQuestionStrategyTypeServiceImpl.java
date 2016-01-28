package cn.hshb.web.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.CommonQuestionStrategyTypeMapper;
import cn.hshb.web.biz.mybatis.model.CommonQuestionStrategyType;
import cn.hshb.web.biz.mybatis.model.CommonQuestionStrategyTypeExample;
import cn.hshb.web.biz.service.CommonQuestionStrategyTypeService;

@Service
public class CommonQuestionStrategyTypeServiceImpl implements
		CommonQuestionStrategyTypeService {

	@Autowired
	CommonQuestionStrategyTypeMapper commonQuestionStrategyTypeMapper;
	
	@Override
	public List<CommonQuestionStrategyType> getAllData() {
		CommonQuestionStrategyTypeExample example = new CommonQuestionStrategyTypeExample();
		example.createCriteria().andDeleteflagEqualTo(0);
		List<CommonQuestionStrategyType> list = commonQuestionStrategyTypeMapper.selectByExample(example);
		return list != null? list : new ArrayList<CommonQuestionStrategyType>();
	}

}
