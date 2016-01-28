/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.HouseCommunity;
import cn.hshb.web.biz.mybatis.model.HousePicture;
import cn.hshb.web.biz.mybatis.model.HouseRentHouse;
import cn.hshb.web.biz.mybatis.model.HouseRentHouseExt;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.house.enums.EnumHouseType;

/**
 * @author ShengYoufu
 *
 */
public interface HousePictureService {

	/**
	 * 根据房源（或小区）列表查询房源照片
	 * @param houseList
	 * @return
	 */
	public void loadPictureForHouse(List<? extends Object> houseList);
	
	/**
	 * 根据房源记录查询房源照片
	 * @param house
	 * @return
	 */
	public void loadPictureForHouse(HouseSecondHandHouse house);
	
	/**
	 * 根据房源记录查询房源照片
	 * @param house
	 * @return
	 */
	public void loadPictureForHouse(HouseRentHouse house);
	
	/**
	 * 根据房源记录查询房源照片
	 * @param house
	 * @return
	 */
	public void loadPictureForCommunity(HouseCommunity house);
	
	/**
	 * 根据房源ID和房源类型查询房源照片
	 * @param houseId		房源或小区ID
	 * @param houseType		房源类型，参见 cn.hshb.web.house.enums.EnumHouseType
	 * @return
	 */
	public List<HousePicture> getHousePictureByHouseId(String houseId, EnumHouseType houseType);	
	
	
	/**
	 * 根据房源查询房源照片数量
	 * @param houseList	
	 * @deprecated 废止，从房源表中直接读取图片数量
	 */
	public void queryPictureCountForHouse(List<? extends Object> houseList);	
	
	/**
	 * 给房源设置封面图片（如果已经有封面图片则不再设置）
	 * @param list
	 * @param houseSecondService
	 */
	public void getCorrespondingRHPictures(List<HouseRentHouseExt> list);
	
	/**
	 * 给房源设置封面图片（如果已经有封面图片则不再设置）
	 * @param houseList	房源列表
	 * @param houseSecondService
	 */
	public void getCorrespondingSHPictures(List<HouseSecondHandHouse> list);
	
	/**
	 * 只对封面图片这字段处理
	 * @param list
	 */
	public void handleHouseCoverImage( Object house);
	/**
	 * 
	 */
	public void handleHouseCoverImagebyList(List<? extends Object> houseList);
}
