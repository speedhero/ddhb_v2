package cn.hshb.web.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.HouseCommunityExpertMapper;
import cn.hshb.web.biz.mybatis.model.HouseCommunity;
import cn.hshb.web.biz.mybatis.model.HouseCommunityExpert;
import cn.hshb.web.biz.service.HouseCommunityExpertService;
import cn.hshb.web.partner.baidu.common.StringUtil;

@Service
public class HouseCommunityExpertServiceImpl implements HouseCommunityExpertService {

	@Autowired
	HouseCommunityExpertMapper houseCommunityExpert;
	
	@Override
	public List<HouseCommunityExpert> getList(String CommunityErpId) {
		if(StringUtil.isNotEmpty(CommunityErpId))
			return houseCommunityExpert.selectAssociateByMap(CommunityErpId);
		return null;
	}

	@Override
	public HouseCommunity loadHouseCOmmunityExpert(
			HouseCommunity houseCommunity) {
		if(houseCommunity != null){
			houseCommunity.setExpertList(getList(houseCommunity.getErpId()));
		}
		return houseCommunity;
	}
	
}
