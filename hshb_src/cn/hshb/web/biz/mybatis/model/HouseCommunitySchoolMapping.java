package cn.hshb.web.biz.mybatis.model;

import java.util.Date;

public class HouseCommunitySchoolMapping extends HouseCommunitySchoolMappingKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_community_school_mapping.community_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String communityId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_community_school_mapping.school_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String schoolId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_community_school_mapping.distance
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Float distance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_community_school_mapping.lastmodified
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date lastmodified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_community_school_mapping.lastsync
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date lastsync;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_community_school_mapping.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer deleteflag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_community_school_mapping.community_id
     *
     * @return the value of house_community_school_mapping.community_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getCommunityId() {
        return communityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_community_school_mapping.community_id
     *
     * @param communityId the value for house_community_school_mapping.community_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_community_school_mapping.school_id
     *
     * @return the value of house_community_school_mapping.school_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getSchoolId() {
        return schoolId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_community_school_mapping.school_id
     *
     * @param schoolId the value for house_community_school_mapping.school_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_community_school_mapping.distance
     *
     * @return the value of house_community_school_mapping.distance
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Float getDistance() {
        return distance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_community_school_mapping.distance
     *
     * @param distance the value for house_community_school_mapping.distance
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDistance(Float distance) {
        this.distance = distance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_community_school_mapping.lastmodified
     *
     * @return the value of house_community_school_mapping.lastmodified
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getLastmodified() {
        return lastmodified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_community_school_mapping.lastmodified
     *
     * @param lastmodified the value for house_community_school_mapping.lastmodified
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_community_school_mapping.lastsync
     *
     * @return the value of house_community_school_mapping.lastsync
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getLastsync() {
        return lastsync;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_community_school_mapping.lastsync
     *
     * @param lastsync the value for house_community_school_mapping.lastsync
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setLastsync(Date lastsync) {
        this.lastsync = lastsync;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_community_school_mapping.deleteflag
     *
     * @return the value of house_community_school_mapping.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getDeleteflag() {
        return deleteflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_community_school_mapping.deleteflag
     *
     * @param deleteflag the value for house_community_school_mapping.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }
    
    /**
     * 学校
     */
    private CommonStudyZone school;

	public CommonStudyZone getSchool() {
		return school;
	}

	public void setSchool(CommonStudyZone school) {
		this.school = school;
	}
    
}