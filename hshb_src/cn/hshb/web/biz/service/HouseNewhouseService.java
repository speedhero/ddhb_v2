/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.Date;
import java.util.List;

import com.huatek.hbwebsite.house.entity.RemainTime;

import cn.hshb.web.biz.mybatis.model.HouseNewhouse;
import cn.hshb.web.biz.mybatis.model.HouseNewhouseExample;
import cn.hshb.web.biz.mybatis.model.HouseNewhouseWithBLOBs;

/**
 * @author ShengYoufu
 *
 */
public interface HouseNewhouseService {

	/**
	 * 根据新楼盘编号 查询新楼盘
	 * @param newHouseNo	新楼盘编号
	 * @return
	 */
	public HouseNewhouseWithBLOBs loadNewHouse(String newHouseNo);

	/**
	 * 载入新楼盘数据
	 * @param maxRows	要载入的记录数
	 * @return
	 */
	public List<HouseNewhouseWithBLOBs> loadNewHouse(Integer maxRows);
	
	/**
	 * 设置房源有效标志，必须在设置了RemainDays再调用 
	 * @param house
	 */
	public void setEnableFlag(HouseNewhouseWithBLOBs house);
	
	/**
	 * 计算团购剩余时间
	 * @param house
	 */
	public void setRemainDays(HouseNewhouseWithBLOBs house);
	
	/**
	 * 按首页推荐标志查询新楼盘（用在主页显示）
	 * @param count	要查询的记录数
	 * @return
	 */
	public List<HouseNewhouseWithBLOBs> loadNewHouseForHomepageRecommand(Integer count);
	
	/**
	 * 查询指定日期之前的新楼盘记录数
	 * @param date
	 * @return
	 */
	public Integer getCountHouseNewBeforeDate(Date date);
	
	/**
	 * 查询指定日期之后(含指定日期)的新楼盘记录数
	 * @param date	要判断的日期
	 * @return
	 */
	public Integer getCountHouseNewAfterDate(Date date);

	/**
	 * 查询新楼盘 
	 * @param pageNum	页码
	 * @param pageSize	每页记录数
	 * @return
	 */
	public List<HouseNewhouseWithBLOBs> getNewHouse(Integer pageNum, Integer pageSize);

	/**
	 * 给每个楼盘设置团购人数
	 * @param pageBean
	 * @return
	 */
	public void setHouseGroupbuys(List<HouseNewhouseWithBLOBs> houseList);
	
	/**
	 * 批量更新新房数据
	 * @param houseList
	 */
	public void updateHouseNewHouseData(List<HouseNewhouseWithBLOBs> houseList);
	
	/**
	 * 更新新房数据
	 * @param houseList
	 */
	public void updateHouseNewHouseData(HouseNewhouseWithBLOBs house);
	
	/**
	 * 更新新房数据
	 * @param houseList
	 */
	public void updateHouseNewHouseData(HouseNewhouse house);
}
