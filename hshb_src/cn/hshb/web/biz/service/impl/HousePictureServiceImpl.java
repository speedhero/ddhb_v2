/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.HousePictureMapper;
import cn.hshb.web.biz.mybatis.model.CommonStoreExample;
import cn.hshb.web.biz.mybatis.model.HouseCommunity;
import cn.hshb.web.biz.mybatis.model.HousePicture;
import cn.hshb.web.biz.mybatis.model.HousePictureExample;
import cn.hshb.web.biz.mybatis.model.HouseRentHouse;
import cn.hshb.web.biz.mybatis.model.HouseRentHouseExt;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.biz.service.HousePictureService;
import cn.hshb.web.common.util.StringUtil;
import cn.hshb.web.house.enums.EnumHouseType;

/**
 * @author ShengYoufu
 *
 */
@Service
public class HousePictureServiceImpl implements HousePictureService {
	private static final Log log = LogFactory.getLog(HousePictureServiceImpl.class);
	
	@Autowired
	private HousePictureMapper housePictureMapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public void loadPictureForHouse(List<? extends Object> houseList) {
		if(houseList == null || houseList.size()<1) return;
		
		EnumHouseType houseType = EnumHouseType.SALE;
		List<String> idList = new ArrayList<String>();
		Object h = houseList.get(0);
		if(h instanceof HouseSecondHandHouse){
			houseType = EnumHouseType.SALE;
			for(HouseSecondHandHouse house: (List<HouseSecondHandHouse>)houseList){
				idList.add(house.getErpId());
			}
		}else if(h instanceof HouseRentHouseExt){
			houseType = EnumHouseType.RENT;
			for(HouseRentHouseExt house: (List<HouseRentHouseExt>)houseList){
				idList.add(house.getErpId());
			}
		}else if(h instanceof HouseCommunity){
			houseType = EnumHouseType.COMMUNITY;
			for(HouseCommunity house: (List<HouseCommunity>)houseList){
				idList.add(house.getErpId());
			}
		}else{
			log.error("传入房源类型错误，必须是 HouseSecondHandHouse、HouseRentHouse或HouseCommunity.");
			return;
		}

		HousePictureExample example = new HousePictureExample();
		example.createCriteria()
			.andHouseIdIn(idList)
			.andHouseTypeEqualTo(houseType.value())
			.andDeleteflagEqualTo(0);
		List<HousePicture> picList = housePictureMapper.selectByExample(example);
		if(h instanceof HouseSecondHandHouse){
			for(HouseSecondHandHouse house: (List<HouseSecondHandHouse>)houseList){
				if(house.getPictures()==null) house.setPictures(new ArrayList<HousePicture>());
				for(HousePicture pic: picList){
					if(house.getErpId().equals(pic.getHouseId())){
						house.getPictures().add(pic);
					}
				}
			}
		}else if(h instanceof HouseRentHouse){
			for(HouseRentHouse house: (List<HouseRentHouse>)houseList){
				if(house.getPictures()==null) house.setPictures(new ArrayList<HousePicture>());
				for(HousePicture pic: picList){
					if(house.getErpId().equals(pic.getHouseId())){
						house.getPictures().add(pic);
					}
				}
			}
		}else if(h instanceof HouseCommunity){
			for(HouseCommunity house: (List<HouseCommunity>)houseList){
				if(house.getPictures()==null) house.setPictures(new ArrayList<HousePicture>());
				for(HousePicture pic: picList){
					if(house.getErpId().equals(pic.getHouseId())){
						house.getPictures().add(pic);
					}
				}
			}
		}
	}

	/**
	 * 根据房源查询房源照片数量
	 * @param houseList	
	 * @deprecated 废止，从房源表中直接读取图片数量
	 */
	@Override
	public void queryPictureCountForHouse(List<? extends Object> houseList) {
		if(houseList == null || houseList.size()<1) return;

		EnumHouseType houseType = EnumHouseType.SALE;
		List<String> idList = new ArrayList<String>();
		Object h = houseList.get(0);
		if(h instanceof HouseSecondHandHouse){
			houseType = EnumHouseType.SALE;
			for(HouseSecondHandHouse house: (List<HouseSecondHandHouse>)houseList){
				idList.add(house.getErpId());
			}
		}else if(h instanceof HouseRentHouse){
			houseType = EnumHouseType.RENT;
			for(HouseRentHouse house: (List<HouseRentHouse>)houseList){
				idList.add(house.getErpId());
			}
		}else if(h instanceof HouseCommunity){
			houseType = EnumHouseType.COMMUNITY;
			for(HouseCommunity house: (List<HouseCommunity>)houseList){
				idList.add(house.getErpId());
			}
		}else{
			log.error("传入房源类型错误，必须是 HouseSecondHandHouse、HouseRentHouse或HouseCommunity.");
			return;
		}

		HousePictureExample example = new HousePictureExample();
		example.createCriteria()
			.andHouseIdIn(idList)
			.andHouseTypeEqualTo(houseType.value())
			.andDeleteflagEqualTo(0);
		List<Map<String, Object>> picCountList = housePictureMapper.groupByHouseIdByExample(example);

		for(Map<String, Object> map: picCountList){
			String shelvingId = (String)map.get("shelving_id");
			Long count = (Long)map.get("cnt");
			switch(houseType){
				case SALE:
					for(HouseSecondHandHouse house: (List<HouseSecondHandHouse>)houseList){
						if(house.getShelvingId().equalsIgnoreCase(shelvingId)){
							house.setPictureCount(count.intValue());
							break;
						}
					}
					break;
				case RENT:
					for(HouseRentHouse house: (List<HouseRentHouse>)houseList){
						if(house.getShelvingId().equalsIgnoreCase(shelvingId)){
							if(house instanceof HouseRentHouseExt)
								((HouseRentHouseExt)house).setPictureCount(count.intValue());
							break;
						}
					}
					break;
				case COMMUNITY:
					for(HouseCommunity house: (List<HouseCommunity>)houseList){
						if(house.getErpId().equalsIgnoreCase(shelvingId)){
							house.setPictureCount(count.intValue());
							break;
						}
					}
					break;
			}
		}
		
	}
	
	@Override
	public void loadPictureForHouse(HouseSecondHandHouse house) {
		String houseId = house.getErpId();
		List<HousePicture> picList = getHousePictureByHouseId(houseId, EnumHouseType.SALE);
		house.setPictures(picList);
	}

	@Override
	public void loadPictureForHouse(HouseRentHouse house) {
		String houseId = house.getErpId();
		List<HousePicture> picList = getHousePictureByHouseId(houseId, EnumHouseType.RENT);
		house.setPictures(picList);
	}
	private void loadPictureForHouse(HouseRentHouseExt house){
		String houseId = house.getErpId();
		List<HousePicture> picList = getHousePictureByHouseId(houseId, EnumHouseType.RENT);
		house.setPictures(picList);
	}
	@Override
	public void loadPictureForCommunity(HouseCommunity community) {
		String houseId = community.getErpId();
		List<HousePicture> picList = getHousePictureByHouseId(houseId, EnumHouseType.COMMUNITY);
		community.setPictures(picList);
	}
	
	/**
	 * 根据房源ID和房源类型查询房源照片
	 * @param houseId		房源或小区ID
	 * @param houseType		房源类型，参见 cn.hshb.web.house.enums.EnumHouseType
	 * @return
	 */
	public List<HousePicture> getHousePictureByHouseId(String houseId, EnumHouseType houseType){
		HousePictureExample example = new HousePictureExample();
		example.createCriteria()
			.andHouseIdEqualTo(houseId)
			.andHouseTypeEqualTo(houseType.value())
			.andDeleteflagEqualTo(0);
		List<HousePicture> picList = housePictureMapper.selectByExample(example);
		return picList;
	}
	
	/**
	 * 给房源设置封面图片（如果已经有封面图片则不再设置）
	 * @param list
	 * @param houseSecondService
	 */
	@Override
	public void getCorrespondingRHPictures(List<HouseRentHouseExt> list) {
		loadPictureForHouse(list);
		for(HouseRentHouseExt h: list){
			if(StringUtil.isEmpty(h.getCoverImage())){
				//从房源所有图片中随机选取一张
				if(h.getPictures().size() == 0)continue;
					Integer idx = RandomUtils.nextInt(h.getPictures().size());
					h.setCoverImage(h.getPictures().get(idx).getPictureUri());
			}
		}
	}
	

	@Override
	public void handleHouseCoverImagebyList(List<? extends Object> houseList) {
		if(houseList != null || houseList.size() >0){
			for(Object ob : houseList)
				handleHouseCoverImage(ob);
		}
		
	}
	/**
	 * 只对封面图片这字段处理,没有图片 则加载
	 * @param list
	 */
	@Override
	public void handleHouseCoverImage(Object ob){
		if (ob != null) {
			if (ob instanceof HouseRentHouseExt) {
				HouseRentHouseExt h = (HouseRentHouseExt) ob;
				if (StringUtil.isEmpty(h.getCoverImage())) {
					// 从房源所有图片中随机选取一张
					if (h.getPictures() == null || h.getPictures().size() == 0){
						loadPictureForHouse(h);
						if(h.getPictures() == null || h.getPictures().size() == 0)return;
					}
					Integer idx = RandomUtils.nextInt(h.getPictures().size());
					h.setCoverImage(h.getPictures().get(idx).getPictureUri());
				}
			} else if (ob instanceof HouseRentHouse) {
				HouseRentHouse h = (HouseRentHouse) ob;
				if (StringUtil.isEmpty(h.getCoverImage())) {
					// 从房源所有图片中随机选取一张
					if (h.getPictures() == null || h.getPictures().size() == 0){
						loadPictureForHouse(h);
						if(h.getPictures() == null || h.getPictures().size() == 0)return;
					}
					Integer idx = RandomUtils.nextInt(h.getPictures().size());
					h.setCoverImage(h.getPictures().get(idx).getPictureUri());
				}
			} else if (ob instanceof HouseCommunity) {
				HouseCommunity h = (HouseCommunity) ob;
				if (StringUtil.isEmpty(h.getCoverImage())) {
					// 从房源所有图片中随机选取一张
					if (h.getPictures() == null || h.getPictures().size() == 0){
						loadPictureForCommunity(h);
						if(h.getPictures() == null || h.getPictures().size() == 0)return;
					}
					Integer idx = RandomUtils.nextInt(h.getPictures().size());
					h.setCoverImage(h.getPictures().get(idx).getPictureUri());
				}
			} else if (ob instanceof HouseSecondHandHouse) {
				HouseSecondHandHouse h = (HouseSecondHandHouse) ob;
				if (StringUtil.isEmpty(h.getCoverImage())) {
					// 从房源所有图片中随机选取一张
					if (h.getPictures() == null || h.getPictures().size() == 0){
						loadPictureForHouse(h);
						if(h.getPictures() == null || h.getPictures().size() == 0)return;
					}
					Integer idx = RandomUtils.nextInt(h.getPictures().size());
					h.setCoverImage(h.getPictures().get(idx).getPictureUri());
				}
			}
		}
	}
	/**
	 * 给房源设置封面图片（如果已经有封面图片则不再设置）
	 * @param houseList	房源列表
	 * @param houseSecondService
	 */
	@Override
	public void getCorrespondingSHPictures(List<HouseSecondHandHouse> list) {
		loadPictureForHouse(list);
		for(HouseSecondHandHouse h: list){
			if(StringUtil.isEmpty(h.getCoverImage())){
				//从房源所有图片中随机选取一张
				if(h.getPictures().size() != 0){
					Integer idx = RandomUtils.nextInt(h.getPictures().size());
					h.setCoverImage(h.getPictures().get(idx).getPictureUri());
				}
			}
		}
	}

}
