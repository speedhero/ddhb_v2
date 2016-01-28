package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.PartnerBaiduHouseSecond;

public interface PartnerBaiduHouseSecondService {
	
	/**
	 * 返回数据
	 * @param maxNumber 上限条数
	 * @return
	 */
	List<PartnerBaiduHouseSecond> getHouseList(int maxNumber);
	/**
	 * 返回需要删除的数据
	 * @return
	 */
	List<PartnerBaiduHouseSecond> getDeleteHouseList();
	
	/**
	 * 更新数据状态
	 * @param houseList
	 */
	void updateHouseInformation(List<PartnerBaiduHouseSecond> houseList);
	
	
}
