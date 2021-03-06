package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.HousePicture;
import cn.hshb.web.biz.mybatis.model.HousePictureExample;
import cn.hshb.web.biz.mybatis.model.HousePictureKey;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface HousePictureMapper extends HshbBaseMapper<HousePicture> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_picture
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int countByExample(HousePictureExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_picture
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByExample(HousePictureExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_picture
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByPrimaryKey(HousePictureKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_picture
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insert(HousePicture record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_picture
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insertSelective(HousePicture record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_picture
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<HousePicture> selectByExample(HousePictureExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_picture
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    HousePicture selectByPrimaryKey(HousePictureKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_picture
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") HousePicture record, @Param("example") HousePictureExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_picture
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExample(@Param("record") HousePicture record, @Param("example") HousePictureExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_picture
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(HousePicture record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_picture
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKey(HousePicture record);
    
    /*****************************以下自定义方法**********************/
    /**
     *按房源上架ID分组统计房源照片数
     * @param example
     * @return
     */
    List<Map<String, Object>> groupByHouseIdByExample(HousePictureExample example);
    
    /**
     * 返回图片信息
     * @param houseId
     * @return
     */
    List<HousePicture> getByHouseId(String houseId);
    
    /**
     * 返回图片数量
     */
    Integer countByHouseId(String houseId); 
}