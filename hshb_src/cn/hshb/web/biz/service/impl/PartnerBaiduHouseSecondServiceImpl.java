package cn.hshb.web.biz.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.oscache.util.StringUtil;

import cn.hshb.web.biz.mybatis.dao.HouseSecondHandHouseMapper;
import cn.hshb.web.biz.mybatis.dao.PartnerBaiduHouseSecondMapper;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.biz.mybatis.model.PartnerBaiduHouseSecond;
import cn.hshb.web.biz.service.HouseAppraiseService;
import cn.hshb.web.biz.service.HousePictureService;
import cn.hshb.web.biz.service.PartnerBaiduHouseSecondService;
@Service
public class PartnerBaiduHouseSecondServiceImpl implements	PartnerBaiduHouseSecondService {
	@Autowired
	PartnerBaiduHouseSecondMapper partnerBaiduHouseSecondMapper;
	@Autowired
	HouseSecondHandHouseMapper houseSecondHandHouseMapper;
	@Autowired
	HousePictureService housePictureService;
	@Autowired
	HouseAppraiseService houseAppraiseService;

	@Override
	public List<PartnerBaiduHouseSecond> getDeleteHouseList() {
		List<PartnerBaiduHouseSecond> pList = partnerBaiduHouseSecondMapper.selectAllDeleteData();
		if(pList != null && pList.size() > 0){
			//获取最新的时间
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(PartnerBaiduHouseSecond house : pList){
				house.setLastmod(Timestamp.valueOf(df.format(ts)));
			}
		}
		return pList;
		
	}
	@Override
	public List<PartnerBaiduHouseSecond> getHouseList(int maxNumber) {
		List<PartnerBaiduHouseSecond> houseList = partnerBaiduHouseSecondMapper.selectAllData(maxNumber);
		if(houseList != null && houseList.size() > 0 ){
			for(PartnerBaiduHouseSecond pHouse : houseList){
				//获取当前时间
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Map<String, Object> conditions = new HashMap<String, Object>();
				conditions.put("house_id", new String[]{pHouse.getHouseId()});
				pHouse.setHouseList(houseSecondHandHouseMapper.selectHouseForDetailByMap(conditions));
				
				if(pHouse.getHouseList() != null && pHouse.getHouseList().size() > 0){
					HouseSecondHandHouse house = pHouse.getHouseList().get(0);
					
					//为房源附上图片
					housePictureService.handleHouseCoverImage(house);
					//评论
					house.setAppraises(houseAppraiseService.findAppraiseListByHouseId(house.getHouseId()));
					
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

	@Override
	public void updateHouseInformation(List<PartnerBaiduHouseSecond> houseList) {
		if(houseList != null && houseList.size() > 0){
			for(PartnerBaiduHouseSecond pHouse : houseList){
				if(pHouse.getStatus() != 4 && pHouse.getStatus() != 3){
					HouseSecondHandHouse house = pHouse.getHouseList().get(0);
					pHouse.setBrokerName(house.getPublisher().getAgentName());
					pHouse.setTelephone(StringUtil.isEmpty(house.getPublisher().getTelephone())?"18967113307":house.getPublisher().getTelephone());
					pHouse.setTitle(house.getTitle());
					pHouse.setBrokerErpId(house.getPublisherId());
					float price = house.getPrice()==null ? 0f:house.getPrice();
					pHouse.setPrice(((int)price));
				}
				partnerBaiduHouseSecondMapper.updateByPrimaryKey(pHouse);
				Log.debug("二手房更新状态成功");
			}
		}

	}
}
