package cn.hshb.web.biz.mybatis.model;

public class CountyForNewHouse extends CountyForNewHouseKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column county_for_new_house.county_name
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private String countyName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column county_for_new_house.delete_flag
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private Integer deleteFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column county_for_new_house.city_id
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private Integer cityId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column county_for_new_house.county_name
     *
     * @return the value of county_for_new_house.county_name
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public String getCountyName() {
        return countyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column county_for_new_house.county_name
     *
     * @param countyName the value for county_for_new_house.county_name
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setCountyName(String countyName) {
        this.countyName = countyName == null ? null : countyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column county_for_new_house.delete_flag
     *
     * @return the value of county_for_new_house.delete_flag
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column county_for_new_house.delete_flag
     *
     * @param deleteFlag the value for county_for_new_house.delete_flag
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column county_for_new_house.city_id
     *
     * @return the value of county_for_new_house.city_id
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column county_for_new_house.city_id
     *
     * @param cityId the value for county_for_new_house.city_id
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}