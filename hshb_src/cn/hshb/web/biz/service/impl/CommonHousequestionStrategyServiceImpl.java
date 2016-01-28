package cn.hshb.web.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.CommonHousequestionStrategyMapper;
import cn.hshb.web.biz.mybatis.model.CommonHousequestionStrategy;
import cn.hshb.web.biz.service.CommonHousequestionStrategyService;
import cn.hshb.web.partner.baidu.common.StringUtil;

@Service
public class CommonHousequestionStrategyServiceImpl implements CommonHousequestionStrategyService {

	@Autowired
	CommonHousequestionStrategyMapper commonHousequestionStrategyMapper;
	
	@Override
	public List<CommonHousequestionStrategy> getAllData(String commonHousequestStrategyTypeId) {
		List<CommonHousequestionStrategy> list = null;
		if(StringUtil.isNotEmpty(commonHousequestStrategyTypeId)){
//			list = commonHousequestionStrategyMapper.selectDataByStrategyTypeId(commonHousequestStrategyTypeId);
		}
		return list;
	}

}
