package cn.hshb.web.biz.mybatis.model;

public class FwErrorsWithBLOBs extends FwErrors {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_errors.param_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String paramInfo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_errors.param_info
     *
     * @return the value of fw_errors.param_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getParamInfo() {
        return paramInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_errors.param_info
     *
     * @param paramInfo the value for fw_errors.param_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setParamInfo(String paramInfo) {
        this.paramInfo = paramInfo == null ? null : paramInfo.trim();
    }
}