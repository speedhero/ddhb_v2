package cn.hshb.web.biz.mybatis.model;

import java.util.Date;

public class UserBrowsedHistory extends UserBrowsedHistoryKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_browsed_history.second_hand_house
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String secondHandHouse;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_browsed_history.rent_house
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String rentHouse;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_browsed_history.community
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String community;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_browsed_history.last_browse_time
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date lastBrowseTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_browsed_history.second_hand_house
     *
     * @return the value of user_browsed_history.second_hand_house
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getSecondHandHouse() {
        return secondHandHouse;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_browsed_history.second_hand_house
     *
     * @param secondHandHouse the value for user_browsed_history.second_hand_house
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setSecondHandHouse(String secondHandHouse) {
        this.secondHandHouse = secondHandHouse == null ? null : secondHandHouse.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_browsed_history.rent_house
     *
     * @return the value of user_browsed_history.rent_house
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getRentHouse() {
        return rentHouse;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_browsed_history.rent_house
     *
     * @param rentHouse the value for user_browsed_history.rent_house
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setRentHouse(String rentHouse) {
        this.rentHouse = rentHouse == null ? null : rentHouse.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_browsed_history.community
     *
     * @return the value of user_browsed_history.community
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getCommunity() {
        return community;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_browsed_history.community
     *
     * @param community the value for user_browsed_history.community
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setCommunity(String community) {
        this.community = community == null ? null : community.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_browsed_history.last_browse_time
     *
     * @return the value of user_browsed_history.last_browse_time
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getLastBrowseTime() {
        return lastBrowseTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_browsed_history.last_browse_time
     *
     * @param lastBrowseTime the value for user_browsed_history.last_browse_time
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setLastBrowseTime(Date lastBrowseTime) {
        this.lastBrowseTime = lastBrowseTime;
    }
}