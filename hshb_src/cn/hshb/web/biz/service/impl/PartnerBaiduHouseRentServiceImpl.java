package cn.hshb.web.biz.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.HouseRentHouseMapper;
import cn.hshb.web.biz.mybatis.dao.PartnerBaiduHouseRentMapper;
import cn.hshb.web.biz.mybatis.model.CommonLiveFacility;
import cn.hshb.web.biz.mybatis.model.HouseRentHouseExt;
import cn.hshb.web.biz.mybatis.model.PartnerBaiduHouseRent;
import cn.hshb.web.biz.service.CommonLiveFacilityService;
import cn.hshb.web.biz.service.HouseAppraiseService;
import cn.hshb.web.biz.service.HousePictureService;
import cn.hshb.web.biz.service.HouseRentService;
import cn.hshb.web.biz.service.PartnerBaiduHouseRentService;
import cn.hshb.web.partner.baidu.common.StringUtil;

@Service
public class PartnerBaiduHouseRentServiceImpl implements PartnerBaiduHouseRentService  {
	private static Log log = LogFactory.getLog(PartnerBaiduHouseRentServiceImpl.class);
	
	@Autowired
	HouseRentService houseRentHouseService;
	@Autowired
	PartnerBaiduHouseRentMapper partnerBaiduHouseRentMapper;
	@Autowired
	HousePictureService housePictureService; 
	@Autowired
	HouseAppraiseService houseAppraiseService;
	@Autowired
	CommonLiveFacilityService commonLiveFacilityService;
	@Autowired
	HouseRentHouseMapper houseRentHouseMapper;
	

	@Override
	public List<PartnerBaiduHouseRent> getDeleteHouseList() {
		List<PartnerBaiduHouseRent> pList = partnerBaiduHouseRentMapper.selectAllDeleteData();
		if(pList != null && pList.size() > 0){
			//获取最新的时间
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(PartnerBaiduHouseRent house : pList){
				house.setLastmod(Timestamp.valueOf(df.format(ts)));
			}
		}
		return pList;
	}
	@Override
	public List<PartnerBaiduHouseRent> getHouseList(int maxNumber) {
		List<PartnerBaiduHouseRent> houseList = partnerBaiduHouseRentMapper.selectAllData(maxNumber);
		
		if(houseList != null && houseList.size() >0){
			for(PartnerBaiduHouseRent pHouse : houseList){
				//获取 当前时间
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				pHouse.setHouseList(houseRentHouseMapper.findHousebyHouseId(pHouse.getHouseId()));
				
				if(pHouse.getHouseList() != null && pHouse.getHouseList().size() >0){
					HouseRentHouseExt house = pHouse.getHouseList().get(0);
					
					//为房源附上图片
					housePictureService.handleHouseCoverImage(house);
					//评论
					house.setAppraises(houseAppraiseService.findAppraiseListByHouseId(house.getHouseId()));
					//TODO 听说要改  家具
					List<CommonLiveFacility> liveList = commonLiveFacilityService.getHouseFurnitures();
					for(CommonLiveFacility live : liveList){
						if(StringUtil.isNotEmpty(house.getFurniture()) && house.getFurniture().contains(","+live.getErpId()+","))
							house.getFurnitures().add(live);
					}
					
					//装修程度
					
					//更新时间
					if(house.getLastmodified() != null)
						pHouse.setLastmod(house.getLastmodified());
					else
						pHouse.setLastmod(Timestamp.valueOf(df.format(ts)));
					pHouse.setPublishTime(Timestamp.valueOf(df.format(ts)));
					house.setBaiduHouseLastmod(pHouse.getLastmod());
					house.setBaiduHousePublishTime(pHouse.getPublishTime());
				}
			}
		}
		return houseList;
	}

	/**
	 * 更新数据上传状态
	 */
	@Override
	public void updateHouseInformation(List<PartnerBaiduHouseRent> houseList) {
		if(houseList != null && houseList.size() > 0){
			for(PartnerBaiduHouseRent pHouse : houseList){
				if(pHouse.getStatus() != 4 && pHouse.getStatus() != 3){
					HouseRentHouseExt  house = pHouse.getHouseList().get(0);
					pHouse.setBrokerName(StringUtil.isEmpty(house.getAgentName())?"未知":house.getAgentName());
					//盛经理的小手机
					pHouse.setTelephone(StringUtil.isEmpty(house.getTelephone())?"18967113307":house.getTelephone());
					pHouse.setBrokerErpId(house.getPublisherId());
					pHouse.setTitle(house.getTitle());
					float price = house.getRentPrice()==null ? 0f:house.getRentPrice();
					pHouse.setPrice(((int)price));
				}
				partnerBaiduHouseRentMapper.updateByPrimaryKey(pHouse);
				log.debug("租赁传递成功");
			}
		}
	}
}
