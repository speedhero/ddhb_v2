package cn.hshb.web.biz.mybatis.model;

public class BBrokerKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_broker.erp_id
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private String erpId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_broker.erp_id
     *
     * @return the value of b_broker.erp_id
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public String getErpId() {
        return erpId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_broker.erp_id
     *
     * @param erpId the value for b_broker.erp_id
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setErpId(String erpId) {
        this.erpId = erpId == null ? null : erpId.trim();
    }
}