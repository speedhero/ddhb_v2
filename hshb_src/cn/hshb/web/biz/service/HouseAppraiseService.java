/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.HouseAppraise;

/**
 * @author Administrator
 *
 */
public interface HouseAppraiseService {

	/**
	 * 根据房源ID查询房源评价记录
	 * @param houseId 房源ID（房源编号）
	 * @return
	 */
	List<HouseAppraise> findAppraiseListByHouseId(String houseId);
	
	/**
	 * 根据经纪人ID和房源ID查询房源评价记录
	 * @param brokerId
	 * @param houseId
	 * @return
	 */
	List<HouseAppraise> findAppraiseListByBrokerIdHouseId(String brokerId, String houseId);
	
	/**
	 * 根据房源上架ID查询房源评价记录
	 * @param shelvingId 房源上架ID（房源编号）
	 * @return
	 */
	public List<HouseAppraise> findAppraiseListByShelvingId(String shelvingId);
}
