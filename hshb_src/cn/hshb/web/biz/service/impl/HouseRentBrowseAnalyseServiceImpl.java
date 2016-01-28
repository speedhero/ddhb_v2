package cn.hshb.web.biz.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatek.hbwebsite.member.entity.PlatMemberInfo;

import cn.hshb.web.biz.mybatis.dao.HouseRentBrowseAnalyseMapper;
import cn.hshb.web.biz.mybatis.model.HouseRentBrowseAnalyse;
import cn.hshb.web.biz.mybatis.model.HouseRentHouse;
import cn.hshb.web.biz.service.HouseRentBrowseAnalyseService;

@Service
public class HouseRentBrowseAnalyseServiceImpl implements HouseRentBrowseAnalyseService {
	private static final Log log = LogFactory.getLog(HouseRentBrowseAnalyseServiceImpl.class);
	
	private static final Integer IS_MEMBER = 1 ;
	private static final Integer NOT_MEMBER = 0;
	
	@Autowired
	private HouseRentBrowseAnalyseMapper houseRentBrowseAnlyseMapper;
	
	/**
	 * 保存房源浏览分析对象
	 */
	@Override
	public void save(HouseRentHouse house, PlatMemberInfo member, String clientFlag) {
		HouseRentBrowseAnalyse houseAnalyse = new HouseRentBrowseAnalyse();
		houseAnalyse.setAnalyseId(UUID.randomUUID().toString());
		houseAnalyse.setHouseId(house.getHouseId());
		houseAnalyse.setBrowseTime(new Date());
		if(house.getCommunity()!=null &&house.getCommunity().getCbd() != null){
			houseAnalyse.setCbdId(house.getCommunity().getCbd().getErpId());
			houseAnalyse.setCbdName(house.getCommunity().getCbd().getCbdName());
			houseAnalyse.setCountyId(house.getCommunity().getCbd().getCounty().getErpId());
			houseAnalyse.setCountyName(house.getCommunity().getCbd().getCounty().getCountyName());
		}
		if(house.getCommunity()!=null){
			houseAnalyse.setCommunityId(house.getCommunity().getErpId());
			houseAnalyse.setCommunityName(house.getCommunity().getCommunityName());
		}
		houseAnalyse.setHouseArea(house.getArea());
		houseAnalyse.setRentPrice(house.getRentPrice());
		houseAnalyse.setClientflag(clientFlag);
		if(member != null){
			houseAnalyse.setIsMember(IS_MEMBER);
			houseAnalyse.setMemberName(member.getAccName());
			houseAnalyse.setRegisterTime(member.getRegTime());
		}else{
			houseAnalyse.setIsMember(NOT_MEMBER);
		}
		houseRentBrowseAnlyseMapper.insert(houseAnalyse);
	}

}
