/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatek.hbwebsite.member.entity.PlatMemberInfo;

import cn.hshb.web.biz.mybatis.dao.HouseSecondBrowseAnalyseMapper;
import cn.hshb.web.biz.mybatis.model.HouseSecondBrowseAnalyse;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.biz.service.HouseSecondBrowseAnalyseService;

/**
 * @author ShengYoufu
 *
 */
@Service
public class HouseSecondBrowseAnalyseServiceImpl implements HouseSecondBrowseAnalyseService {
	private static final Log log = LogFactory.getLog(HouseSecondBrowseAnalyseServiceImpl.class);
	
	private static final Integer IS_MEMBER = 1;
	private static final Integer NOT_MEMBER = 0;

	@Autowired
	private HouseSecondBrowseAnalyseMapper houseSecondBrowseAnalyseMapper;
	
	/**
	 * 保存房源浏览分析对象
	 * @param house			房源对象
	 * @param member		会员对象
	 * @param clientFlag	客户端标志
	 */
	@Override
	public void save(HouseSecondHandHouse house, PlatMemberInfo member, String clientFlag) {
		HouseSecondBrowseAnalyse houseAnalyse = new HouseSecondBrowseAnalyse();
		houseAnalyse.setAnalyseId(UUID.randomUUID().toString());
		houseAnalyse.setHouseId(house.getHouseId());
		houseAnalyse.setBrowseTime(new Date());
		if (house.getCommunity().getCbd() != null) {
			houseAnalyse.setCbdId(house.getCommunity().getCbd().getErpId());
			houseAnalyse.setCbdName(house.getCommunity().getCbd().getCbdName());
			houseAnalyse.setCountyId(house.getCommunity().getCbd().getCounty().getErpId());
			houseAnalyse.setCountyName(house.getCommunity().getCbd().getCounty().getCountyName());
		}
		houseAnalyse.setCommunityId(house.getCommunity().getErpId());
		houseAnalyse.setCommunityName(house.getCommunity().getCommunityName());
		houseAnalyse.setHouseArea(house.getArea());
		houseAnalyse.setHousePrice(house.getPrice());
		houseAnalyse.setClientflag(clientFlag);
		if (member != null) {
			houseAnalyse.setIsMember(IS_MEMBER);
			houseAnalyse.setMemberName(member.getAccName());
			houseAnalyse.setRegisterTime(member.getRegTime());
		} else {
			houseAnalyse.setIsMember(NOT_MEMBER);
		}
		houseSecondBrowseAnalyseMapper.insert(houseAnalyse);
	}

}
