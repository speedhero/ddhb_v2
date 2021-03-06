package cn.hshb.web.biz.mybatis.model;

import java.util.Date;

public class HouseCommunityStationMapping extends HouseCommunityStationMappingKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_community_station_mapping.community_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String communityId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_community_station_mapping.station_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String stationId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_community_station_mapping.distance
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Float distance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_community_station_mapping.lastmodified
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date lastmodified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_community_station_mapping.lastsync
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date lastsync;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_community_station_mapping.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer deleteflag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_community_station_mapping.community_id
     *
     * @return the value of house_community_station_mapping.community_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getCommunityId() {
        return communityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_community_station_mapping.community_id
     *
     * @param communityId the value for house_community_station_mapping.community_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_community_station_mapping.station_id
     *
     * @return the value of house_community_station_mapping.station_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getStationId() {
        return stationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_community_station_mapping.station_id
     *
     * @param stationId the value for house_community_station_mapping.station_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setStationId(String stationId) {
        this.stationId = stationId == null ? null : stationId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_community_station_mapping.distance
     *
     * @return the value of house_community_station_mapping.distance
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Float getDistance() {
        return distance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_community_station_mapping.distance
     *
     * @param distance the value for house_community_station_mapping.distance
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDistance(Float distance) {
        this.distance = distance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_community_station_mapping.lastmodified
     *
     * @return the value of house_community_station_mapping.lastmodified
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getLastmodified() {
        return lastmodified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_community_station_mapping.lastmodified
     *
     * @param lastmodified the value for house_community_station_mapping.lastmodified
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_community_station_mapping.lastsync
     *
     * @return the value of house_community_station_mapping.lastsync
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getLastsync() {
        return lastsync;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_community_station_mapping.lastsync
     *
     * @param lastsync the value for house_community_station_mapping.lastsync
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setLastsync(Date lastsync) {
        this.lastsync = lastsync;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_community_station_mapping.deleteflag
     *
     * @return the value of house_community_station_mapping.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getDeleteflag() {
        return deleteflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_community_station_mapping.deleteflag
     *
     * @param deleteflag the value for house_community_station_mapping.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }
    
    /* 自定义 */
    /**
     * 车站
     */
    private CommonStation station;

	public CommonStation getStation() {
		return station;
	}

	public void setStation(CommonStation station) {
		this.station = station;
	}	
    
}