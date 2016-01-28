package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.PartnerBaiduHouseRent;

/**
 * 把百度数据实现接口
 * @author hejianbo
 *	2015年8月24日
 */
public interface PartnerBaiduHouseRentService {
	
	/**
	 * 返回数据
	 * @param maxNumber  上限条数
	 * @return
	 */
	 List<PartnerBaiduHouseRent> getHouseList(int maxNumber);
	
	 /**
	  * 返回需要删除的数据
	  * @return 
	  */
	 List<PartnerBaiduHouseRent> getDeleteHouseList();
	 
	 /**
	  * 更新数据状态
	  * @param houseList
	  */
	 void updateHouseInformation(List<PartnerBaiduHouseRent> houseList);
}
