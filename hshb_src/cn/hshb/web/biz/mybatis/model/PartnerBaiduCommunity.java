package cn.hshb.web.biz.mybatis.model;

import java.util.Date;
import java.util.List;

public class PartnerBaiduCommunity extends PartnerBaiduCommunityKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner_baidu_community.title
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner_baidu_community.lastmod
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date lastmod;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner_baidu_community.publish_time
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date publishTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner_baidu_community.is_sync
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Byte isSync;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner_baidu_community.status
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Byte status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column partner_baidu_community.title
     *
     * @return the value of partner_baidu_community.title
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column partner_baidu_community.title
     *
     * @param title the value for partner_baidu_community.title
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column partner_baidu_community.lastmod
     *
     * @return the value of partner_baidu_community.lastmod
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getLastmod() {
        return lastmod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column partner_baidu_community.lastmod
     *
     * @param lastmod the value for partner_baidu_community.lastmod
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setLastmod(Date lastmod) {
        this.lastmod = lastmod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column partner_baidu_community.publish_time
     *
     * @return the value of partner_baidu_community.publish_time
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column partner_baidu_community.publish_time
     *
     * @param publishTime the value for partner_baidu_community.publish_time
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column partner_baidu_community.is_sync
     *
     * @return the value of partner_baidu_community.is_sync
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Byte getIsSync() {
        return isSync;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column partner_baidu_community.is_sync
     *
     * @param isSync the value for partner_baidu_community.is_sync
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setIsSync(Byte isSync) {
        this.isSync = isSync;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column partner_baidu_community.status
     *
     * @return the value of partner_baidu_community.status
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column partner_baidu_community.status
     *
     * @param status the value for partner_baidu_community.status
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
    /* 自定义 */
    
    /**
     * 所对应的小区
     */
    private HouseCommunity community;

	public HouseCommunity getCommunity() {
		return community;
	}

	public void setCommunity(HouseCommunity community) {
		this.community = community;
	}
    
}