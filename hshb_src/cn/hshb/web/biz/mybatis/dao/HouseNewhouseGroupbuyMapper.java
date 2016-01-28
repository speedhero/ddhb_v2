package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.HouseNewhouseGroupbuy;
import cn.hshb.web.biz.mybatis.model.HouseNewhouseGroupbuyExample;
import cn.hshb.web.biz.mybatis.model.HouseNewhouseGroupbuyKey;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface HouseNewhouseGroupbuyMapper extends HshbBaseMapper<HouseNewhouseGroupbuy> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_newhouse_groupbuy
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int countByExample(HouseNewhouseGroupbuyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_newhouse_groupbuy
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByExample(HouseNewhouseGroupbuyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_newhouse_groupbuy
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByPrimaryKey(HouseNewhouseGroupbuyKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_newhouse_groupbuy
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insert(HouseNewhouseGroupbuy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_newhouse_groupbuy
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insertSelective(HouseNewhouseGroupbuy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_newhouse_groupbuy
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<HouseNewhouseGroupbuy> selectByExample(HouseNewhouseGroupbuyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_newhouse_groupbuy
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    HouseNewhouseGroupbuy selectByPrimaryKey(HouseNewhouseGroupbuyKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_newhouse_groupbuy
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") HouseNewhouseGroupbuy record, @Param("example") HouseNewhouseGroupbuyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_newhouse_groupbuy
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExample(@Param("record") HouseNewhouseGroupbuy record, @Param("example") HouseNewhouseGroupbuyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_newhouse_groupbuy
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(HouseNewhouseGroupbuy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_newhouse_groupbuy
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKey(HouseNewhouseGroupbuy record);
    
    /************************以下为自定义方法*********************/
    /**
     * 根据新楼盘编号统计每个新楼盘的团购数
     * @param example
     * @return
     */
    List selectCountByHouseNo(HouseNewhouseGroupbuyExample example);
}