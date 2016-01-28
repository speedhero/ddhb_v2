package cn.hshb.web.biz.mybatis.model;

import java.util.Date;

public class MemberCollection extends MemberCollectionKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_collection.member_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer memberId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_collection.collect_type
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer collectType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_collection.object_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String objectId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_collection.created_time
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date createdTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_collection.price_collected
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Float priceCollected;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_collection.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer deleteflag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_collection.broker_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Object brokerId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_collection.member_id
     *
     * @return the value of member_collection.member_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_collection.member_id
     *
     * @param memberId the value for member_collection.member_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_collection.collect_type
     *
     * @return the value of member_collection.collect_type
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getCollectType() {
        return collectType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_collection.collect_type
     *
     * @param collectType the value for member_collection.collect_type
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setCollectType(Integer collectType) {
        this.collectType = collectType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_collection.object_id
     *
     * @return the value of member_collection.object_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_collection.object_id
     *
     * @param objectId the value for member_collection.object_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_collection.created_time
     *
     * @return the value of member_collection.created_time
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_collection.created_time
     *
     * @param createdTime the value for member_collection.created_time
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_collection.price_collected
     *
     * @return the value of member_collection.price_collected
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Float getPriceCollected() {
        return priceCollected;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_collection.price_collected
     *
     * @param priceCollected the value for member_collection.price_collected
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setPriceCollected(Float priceCollected) {
        this.priceCollected = priceCollected;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_collection.deleteflag
     *
     * @return the value of member_collection.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getDeleteflag() {
        return deleteflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_collection.deleteflag
     *
     * @param deleteflag the value for member_collection.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_collection.broker_id
     *
     * @return the value of member_collection.broker_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Object getBrokerId() {
        return brokerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_collection.broker_id
     *
     * @param brokerId the value for member_collection.broker_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setBrokerId(Object brokerId) {
        this.brokerId = brokerId;
    }
    
    
	/**
	 * 会员信息
	 */
	private MemberInfo memberInfo;

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

}