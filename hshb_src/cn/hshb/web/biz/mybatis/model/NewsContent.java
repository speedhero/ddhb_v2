package cn.hshb.web.biz.mybatis.model;

import java.util.Date;

public class NewsContent extends NewsContentKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column news_content.news_title
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String newsTitle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column news_content.news_type
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer newsType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column news_content.news_from
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String newsFrom;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column news_content.publish_time
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date publishTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column news_content.browsed
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer browsed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column news_content.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer deleteflag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column news_content.news_title
     *
     * @return the value of news_content.news_title
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getNewsTitle() {
        return newsTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column news_content.news_title
     *
     * @param newsTitle the value for news_content.news_title
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle == null ? null : newsTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column news_content.news_type
     *
     * @return the value of news_content.news_type
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getNewsType() {
        return newsType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column news_content.news_type
     *
     * @param newsType the value for news_content.news_type
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setNewsType(Integer newsType) {
        this.newsType = newsType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column news_content.news_from
     *
     * @return the value of news_content.news_from
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getNewsFrom() {
        return newsFrom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column news_content.news_from
     *
     * @param newsFrom the value for news_content.news_from
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setNewsFrom(String newsFrom) {
        this.newsFrom = newsFrom == null ? null : newsFrom.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column news_content.publish_time
     *
     * @return the value of news_content.publish_time
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column news_content.publish_time
     *
     * @param publishTime the value for news_content.publish_time
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column news_content.browsed
     *
     * @return the value of news_content.browsed
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getBrowsed() {
        return browsed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column news_content.browsed
     *
     * @param browsed the value for news_content.browsed
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setBrowsed(Integer browsed) {
        this.browsed = browsed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column news_content.deleteflag
     *
     * @return the value of news_content.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getDeleteflag() {
        return deleteflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column news_content.deleteflag
     *
     * @param deleteflag the value for news_content.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }
}