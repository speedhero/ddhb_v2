package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.FwDictionary;
import cn.hshb.web.biz.mybatis.model.FwDictionaryExample;
import cn.hshb.web.biz.mybatis.model.FwDictionaryKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FwDictionaryMapper extends HshbBaseMapper<FwDictionary> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_dictionary
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int countByExample(FwDictionaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_dictionary
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByExample(FwDictionaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_dictionary
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByPrimaryKey(FwDictionaryKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_dictionary
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insert(FwDictionary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_dictionary
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insertSelective(FwDictionary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_dictionary
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<FwDictionary> selectByExample(FwDictionaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_dictionary
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    FwDictionary selectByPrimaryKey(FwDictionaryKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_dictionary
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") FwDictionary record, @Param("example") FwDictionaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_dictionary
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExample(@Param("record") FwDictionary record, @Param("example") FwDictionaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_dictionary
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(FwDictionary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_dictionary
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKey(FwDictionary record);
}