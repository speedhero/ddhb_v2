package cn.hshb.web.biz.mybatis.model;

import java.util.Date;
import java.util.List;

public class HouseCbdExpert extends HouseCbdExpertKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_cbd_expert.community_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String communityId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_cbd_expert.broker_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String brokerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_cbd_expert.lastmodified
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date lastmodified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_cbd_expert.lastsync
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date lastsync;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_cbd_expert.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer deleteflag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_cbd_expert.community_id
     *
     * @return the value of house_cbd_expert.community_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getCommunityId() {
        return communityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_cbd_expert.community_id
     *
     * @param communityId the value for house_cbd_expert.community_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_cbd_expert.broker_id
     *
     * @return the value of house_cbd_expert.broker_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getBrokerId() {
        return brokerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_cbd_expert.broker_id
     *
     * @param brokerId the value for house_cbd_expert.broker_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId == null ? null : brokerId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_cbd_expert.lastmodified
     *
     * @return the value of house_cbd_expert.lastmodified
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getLastmodified() {
        return lastmodified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_cbd_expert.lastmodified
     *
     * @param lastmodified the value for house_cbd_expert.lastmodified
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_cbd_expert.lastsync
     *
     * @return the value of house_cbd_expert.lastsync
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getLastsync() {
        return lastsync;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_cbd_expert.lastsync
     *
     * @param lastsync the value for house_cbd_expert.lastsync
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setLastsync(Date lastsync) {
        this.lastsync = lastsync;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_cbd_expert.deleteflag
     *
     * @return the value of house_cbd_expert.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getDeleteflag() {
        return deleteflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_cbd_expert.deleteflag
     *
     * @param deleteflag the value for house_cbd_expert.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }
    
    /*自定义*/
    /**
     * 对应商圈
     */
    private CommonCbd cbd;

	public CommonCbd getCbd() {
		return cbd;
	}

	public void setCbd(CommonCbd cbd) {
		this.cbd = cbd;
	}

    
}