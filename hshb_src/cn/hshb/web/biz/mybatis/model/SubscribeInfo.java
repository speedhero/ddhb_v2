package cn.hshb.web.biz.mybatis.model;

public class SubscribeInfo extends SubscribeInfoKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column subscribe_info.member_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer memberId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column subscribe_info.email
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column subscribe_info.mobailphone
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String mobailphone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column subscribe_info.subscribe_type
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer subscribeType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column subscribe_info.room_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer roomId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column subscribe_info.subscribe_content
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String subscribeContent;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subscribe_info.member_id
     *
     * @return the value of subscribe_info.member_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subscribe_info.member_id
     *
     * @param memberId the value for subscribe_info.member_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subscribe_info.email
     *
     * @return the value of subscribe_info.email
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subscribe_info.email
     *
     * @param email the value for subscribe_info.email
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subscribe_info.mobailphone
     *
     * @return the value of subscribe_info.mobailphone
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getMobailphone() {
        return mobailphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subscribe_info.mobailphone
     *
     * @param mobailphone the value for subscribe_info.mobailphone
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setMobailphone(String mobailphone) {
        this.mobailphone = mobailphone == null ? null : mobailphone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subscribe_info.subscribe_type
     *
     * @return the value of subscribe_info.subscribe_type
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getSubscribeType() {
        return subscribeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subscribe_info.subscribe_type
     *
     * @param subscribeType the value for subscribe_info.subscribe_type
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setSubscribeType(Integer subscribeType) {
        this.subscribeType = subscribeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subscribe_info.room_id
     *
     * @return the value of subscribe_info.room_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subscribe_info.room_id
     *
     * @param roomId the value for subscribe_info.room_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subscribe_info.subscribe_content
     *
     * @return the value of subscribe_info.subscribe_content
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getSubscribeContent() {
        return subscribeContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subscribe_info.subscribe_content
     *
     * @param subscribeContent the value for subscribe_info.subscribe_content
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setSubscribeContent(String subscribeContent) {
        this.subscribeContent = subscribeContent == null ? null : subscribeContent.trim();
    }
}