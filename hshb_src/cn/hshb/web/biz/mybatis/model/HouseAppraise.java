package cn.hshb.web.biz.mybatis.model;

import java.util.Date;

import cn.hshb.web.house.enums.EnumAppraiseType;
import cn.hshb.web.partner.baidu.common.StringUtil;

public class HouseAppraise extends HouseAppraiseKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_appraise.house_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String houseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_appraise.house_no
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String houseNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_appraise.house_type
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer houseType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_appraise.broker
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String broker;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_appraise.search_no
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String searchNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_appraise.title
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_appraise.content
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_appraise.appraise_date
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date appraiseDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_appraise.sortindex
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer sortindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_appraise.lastmodified
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date lastmodified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_appraise.lastsync
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date lastsync;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_appraise.shelving_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String shelvingId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_appraise.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer deleteflag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_appraise.house_id
     *
     * @return the value of house_appraise.house_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getHouseId() {
        return houseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_appraise.house_id
     *
     * @param houseId the value for house_appraise.house_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setHouseId(String houseId) {
        this.houseId = houseId == null ? null : houseId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_appraise.house_no
     *
     * @return the value of house_appraise.house_no
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getHouseNo() {
        return houseNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_appraise.house_no
     *
     * @param houseNo the value for house_appraise.house_no
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo == null ? null : houseNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_appraise.house_type
     *
     * @return the value of house_appraise.house_type
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getHouseType() {
        return houseType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_appraise.house_type
     *
     * @param houseType the value for house_appraise.house_type
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setHouseType(Integer houseType) {
        this.houseType = houseType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_appraise.broker
     *
     * @return the value of house_appraise.broker
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getBroker() {
        return broker;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_appraise.broker
     *
     * @param broker the value for house_appraise.broker
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setBroker(String broker) {
        this.broker = broker == null ? null : broker.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_appraise.search_no
     *
     * @return the value of house_appraise.search_no
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getSearchNo() {
        return searchNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_appraise.search_no
     *
     * @param searchNo the value for house_appraise.search_no
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setSearchNo(String searchNo) {
        this.searchNo = searchNo == null ? null : searchNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_appraise.title
     *
     * @return the value of house_appraise.title
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_appraise.title
     *
     * @param title the value for house_appraise.title
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_appraise.content
     *
     * @return the value of house_appraise.content
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_appraise.content
     *
     * @param content the value for house_appraise.content
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_appraise.appraise_date
     *
     * @return the value of house_appraise.appraise_date
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getAppraiseDate() {
        return appraiseDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_appraise.appraise_date
     *
     * @param appraiseDate the value for house_appraise.appraise_date
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setAppraiseDate(Date appraiseDate) {
        this.appraiseDate = appraiseDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_appraise.sortindex
     *
     * @return the value of house_appraise.sortindex
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getSortindex() {
        return sortindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_appraise.sortindex
     *
     * @param sortindex the value for house_appraise.sortindex
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setSortindex(Integer sortindex) {
        this.sortindex = sortindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_appraise.lastmodified
     *
     * @return the value of house_appraise.lastmodified
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getLastmodified() {
        return lastmodified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_appraise.lastmodified
     *
     * @param lastmodified the value for house_appraise.lastmodified
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_appraise.lastsync
     *
     * @return the value of house_appraise.lastsync
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getLastsync() {
        return lastsync;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_appraise.lastsync
     *
     * @param lastsync the value for house_appraise.lastsync
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setLastsync(Date lastsync) {
        this.lastsync = lastsync;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_appraise.shelving_id
     *
     * @return the value of house_appraise.shelving_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getShelvingId() {
        return shelvingId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_appraise.shelving_id
     *
     * @param shelvingId the value for house_appraise.shelving_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setShelvingId(String shelvingId) {
        this.shelvingId = shelvingId == null ? null : shelvingId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_appraise.deleteflag
     *
     * @return the value of house_appraise.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getDeleteflag() {
        return deleteflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_appraise.deleteflag
     *
     * @param deleteflag the value for house_appraise.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }
    
    
    /**********************以下自定义属性*****************/
    private BBroker publisher;		//房源评价发布人
    //新增字段 broker_name  evaluation_category
    private String brokerName;
    private String evaluationCategoryId;
    private String evaluationCategoryName;

    
	public String getBrokerName() {
		return brokerName;
	}

	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	public String getEvaluationCategoryId() {
		return evaluationCategoryId;
	}

	public void setEvaluationCategoryId(String evaluationCategoryId) {
		this.evaluationCategoryId = evaluationCategoryId;
		
		if(StringUtil.isNotEmpty(evaluationCategoryId)){
			for(EnumAppraiseType e : EnumAppraiseType.values()){
				if(e.getNumber().equals(evaluationCategoryId))
					this.evaluationCategoryName = e.getValue();
			}
		}
		
	}

	public String getEvaluationCategoryName() {
		return evaluationCategoryName;
	}

	public void setEvaluationCategoryName(String evaluationCategoryName) {
		this.evaluationCategoryName = evaluationCategoryName;
	}

	public BBroker getPublisher() {
		return publisher;
	}

	public void setPublisher(BBroker publisher) {
		this.publisher = publisher;
	}
    
    
}