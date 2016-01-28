package com.huatek.hbwebsite.util;

import com.huatek.hbwebsite.common.entity.HousePic;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.house.service.HouseSecondService;
import java.util.List;

public class HouseListPictureUtil {
	
	/**
	 * 查询 出租房源封面图片
	 * @param houseList
	 * @param houseSecondService
	 */
	public static void getCorrespondingRHPictures(List<HouseRent> houseList, HouseSecondService houseSecondService) {
		String[] houseIDArray = null;
		if (houseList != null && houseList.size() > 0) {
			houseIDArray = new String[houseList.size()];

			for (int ii = 0; ii < houseList.size(); ++ii) {
				houseIDArray[ii] = ((HouseRent) houseList.get(ii)).getShelvingID();
			}
		}

		if (houseIDArray != null) {
			List<HousePic> picList = houseSecondService.getHousePicByIdsAndPicType(houseIDArray, 2, 1, 0);
			if (houseList != null && houseList.size() > 0 && picList != null && picList.size() > 0) {
				for(HouseRent h: houseList){
					for(HousePic p: picList){
						if(h.getShelvingID().equals(p.getHouseId())){
							h.setPictureUrl(p.getPicUrl());
							break;
						}
					}
				}
			}
		}

	}

	/**
	 * 查询二手房源封面图片
	 * @param houseList	房源列表
	 * @param houseSecondService
	 */
	public static void getCorrespondingSHPictures(List<HouseSecond> houseList, HouseSecondService houseSecondService) {
		String[] houseIDArray = null;
		if (houseList != null && houseList.size() > 0) {
			houseIDArray = new String[houseList.size()];
			int ii=0;
			for(HouseSecond h: houseList){
				houseIDArray[ii++] = h.getShelvingID();
			}
		}

		if (houseIDArray != null) {
			List<HousePic> picList = houseSecondService.getHousePicByIdsAndPicType(houseIDArray, 1, 1, 0);
			if (houseList != null && houseList.size() > 0 && picList != null && picList.size() > 0) {
				for(HouseSecond h: houseList){
					for(HousePic p: picList){
						if(h.getShelvingID().equals(p.getHouseId())){
							h.setPictureUrl(p.getPicUrl());
							break;
						}
					}
				}
			}
		}

	}
}
