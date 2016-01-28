package cn.hshb.web.biz.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.HouseCommunityHospitalMappingMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCommunitySchoolMappingMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCommunityStationMappingMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCommunitySubwayMappingMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCommunitySubwayStationMappingMapper;
import cn.hshb.web.biz.mybatis.dao.PartnerBaiduCommunityMapper;
import cn.hshb.web.biz.mybatis.model.CommonStation;
import cn.hshb.web.biz.mybatis.model.CommonStudyZone;
import cn.hshb.web.biz.mybatis.model.HouseCommunity;
import cn.hshb.web.biz.mybatis.model.HouseCommunityHospitalMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunitySchoolMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunityStationMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunitySubwayMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunitySubwayStationMapping;
import cn.hshb.web.biz.mybatis.model.PartnerBaiduCommunity;
import cn.hshb.web.biz.service.HouseCommunityService;
import cn.hshb.web.biz.service.PartnerBaiduCommunityService;

@Service
public class PartnerBaiduCommunityServiceImpl implements PartnerBaiduCommunityService {
    private static Log log = LogFactory.getLog(PartnerBaiduCommunityServiceImpl.class);
	@Autowired
	HouseCommunityService houseCommunityService;
	@Autowired
	PartnerBaiduCommunityMapper partnerBaiduCommunityMapper;
	@Autowired
	HouseCommunitySchoolMappingMapper houseCommunitySchoolMappingMapper;
	@Autowired
	HouseCommunityStationMappingMapper houseCommunityStationMappingMapper;
	@Autowired
	HouseCommunitySubwayStationMappingMapper houseCommunitySubwayStationMappingMapper;
	@Autowired
	HouseCommunityHospitalMappingMapper houseCommunityHospitalMappingMapper;
	
	@Override
	public List<PartnerBaiduCommunity> getCommunityList(int maxNumber) {
		List<PartnerBaiduCommunity> communityList = partnerBaiduCommunityMapper.selectBaiduCommunityData(maxNumber);
		if(communityList != null && communityList.size() > 0){
			for(PartnerBaiduCommunity bdCommunity : communityList){
				//加载小区
				bdCommunity.setCommunity(houseCommunityService.findCommunityByCommunityId(bdCommunity.getCommunityId()));
				if(bdCommunity.getCommunity() == null) continue;
				
				//获取当前时间
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				Map<String, String> map = new HashMap<String, String>();
				map.put("communityId", bdCommunity.getCommunityId());
				map.put("community", bdCommunity.getCommunityId());
				List<String> facilities = new ArrayList<String>();
				//小区周边设施
				//周边学校
				List<HouseCommunitySchoolMapping> schoolMappingList = houseCommunitySchoolMappingMapper.selectAssociateByMap(map);
				 if(schoolMappingList != null && schoolMappingList.size() > 0){
					 for(HouseCommunitySchoolMapping schoolMapping : schoolMappingList){
						 if(schoolMapping.getSchool() == null) continue;
						 facilities.add(schoolMapping.getSchool().getSzName());
					 }
				 }
				//周边地铁
				 List<HouseCommunitySubwayStationMapping> subwayMapping = houseCommunitySubwayStationMappingMapper.selectAssociateByMap(map);
				if(subwayMapping != null && subwayMapping.size() > 0){
					for(HouseCommunitySubwayStationMapping subway : subwayMapping){
						if(subway.getSubwayStation() == null) continue;
						facilities.add(subway.getSubwayStation().getStationName());
					}
				}
				 //周边车站
				 List<HouseCommunityStationMapping> stationMappingList = houseCommunityStationMappingMapper.selectAssociateByMap(map);
				 if(stationMappingList != null && stationMappingList.size() > 0){
					 for(HouseCommunityStationMapping stationMapping : stationMappingList){
						 if(stationMapping.getStation() == null) continue;
						 facilities.add(stationMapping.getStation().getStationName());
					 }
				 }
				 //周边医院
				List<HouseCommunityHospitalMapping> hospitalMappingList = houseCommunityHospitalMappingMapper.selectAssociateByMap(map);
				if(hospitalMappingList != null && hospitalMappingList.size() > 0){
					for(HouseCommunityHospitalMapping hospital : hospitalMappingList){
						if(hospital.getHospital() == null) continue;
						facilities.add(hospital.getHospital().getHospitalName());
					}
				}
				bdCommunity.getCommunity().setFacilities(facilities);
				
				//更新时间
				if(bdCommunity.getCommunity().getLastmodified() != null)
					bdCommunity.setLastmod(bdCommunity.getCommunity().getLastmodified());
				else 
					bdCommunity.setLastmod(Timestamp.valueOf(df.format(ts)));
				bdCommunity.setPublishTime(Timestamp.valueOf(df.format(ts)));
				bdCommunity.getCommunity().setBaiduHouseLastmod(bdCommunity.getLastmod());
				bdCommunity.getCommunity().setBaiduHousePublishTime(bdCommunity.getPublishTime());
			}
		}
		return communityList;
	}

	/**
	 * 
	 */
	@Override
	public void updateCommunityInformation(
			List<PartnerBaiduCommunity> communityList) {
		if(communityList != null && communityList.size() > 0){
			for(PartnerBaiduCommunity pCommunity : communityList){
				partnerBaiduCommunityMapper.updateByPrimaryKey(pCommunity);
			}
		}
	}

}
