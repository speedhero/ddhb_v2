package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.FwAccountDuty;
import cn.hshb.web.biz.mybatis.model.FwAccountDutyExample;
import cn.hshb.web.biz.mybatis.model.FwAccountDutyKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FwAccountDutyMapper extends HshbBaseMapper<FwAccountDuty> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_account_duty
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int countByExample(FwAccountDutyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_account_duty
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByExample(FwAccountDutyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_account_duty
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByPrimaryKey(FwAccountDutyKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_account_duty
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insert(FwAccountDuty record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_account_duty
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insertSelective(FwAccountDuty record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_account_duty
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<FwAccountDuty> selectByExample(FwAccountDutyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_account_duty
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    FwAccountDuty selectByPrimaryKey(FwAccountDutyKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_account_duty
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") FwAccountDuty record, @Param("example") FwAccountDutyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_account_duty
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExample(@Param("record") FwAccountDuty record, @Param("example") FwAccountDutyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_account_duty
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(FwAccountDuty record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_account_duty
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKey(FwAccountDuty record);
}