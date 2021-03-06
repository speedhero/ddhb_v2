package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.CompanyCustomerService;
import cn.hshb.web.biz.mybatis.model.CompanyCustomerServiceExample;
import cn.hshb.web.biz.mybatis.model.CompanyCustomerServiceKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CompanyCustomerServiceMapper extends HshbBaseMapper<CompanyCustomerService> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_customer_service
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int countByExample(CompanyCustomerServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_customer_service
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByExample(CompanyCustomerServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_customer_service
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByPrimaryKey(CompanyCustomerServiceKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_customer_service
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insert(CompanyCustomerService record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_customer_service
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insertSelective(CompanyCustomerService record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_customer_service
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    List<CompanyCustomerService> selectByExample(CompanyCustomerServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_customer_service
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    CompanyCustomerService selectByPrimaryKey(CompanyCustomerServiceKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_customer_service
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") CompanyCustomerService record, @Param("example") CompanyCustomerServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_customer_service
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExample(@Param("record") CompanyCustomerService record, @Param("example") CompanyCustomerServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_customer_service
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(CompanyCustomerService record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_customer_service
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKey(CompanyCustomerService record);
}