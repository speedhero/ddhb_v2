/**
 * 
 */
package cn.hshb.web.biz.mybatis.dao;

import java.util.List;

/**
 * @author Administrator
 *
 */
public interface HshbBaseMapper<T> {
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	List<T> getById(String id);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	List<T> getById(Long id);
	
	/**
	 * 根据房源ID统计与房源有关信息的记录数
	 * 在房源照片、房源评价等功能中会用到
	 * TODO: 暂时先放在BaseMapper中，以后要考虑移到对应的子类中
	 * @param houseId
	 * @return
	 */
	Integer countByHouseId(String houseId);
	
	/**
	 * 根据SQL语句查询。<br/>
	 * 需要在XML Mapper中定义 selectBySQL 查询方法:<br/>
	 * <pre>
		<select id="selectBySQL" parameterType="String">
		${value}  
		</select>
		</pre>
	 * @param sql 
	 * @return
	 */
    @SuppressWarnings("rawtypes")
	List selectBySQL(String sql);
}