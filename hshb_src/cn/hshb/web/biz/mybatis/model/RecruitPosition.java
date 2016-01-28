package cn.hshb.web.biz.mybatis.model;

import java.util.Date;

public class RecruitPosition extends RecruitPositionKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_position.position_name
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String positionName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_position.typeid
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_position.departmentid
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer departmentid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_position.workplace
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String workplace;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_position.needed
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer needed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_position.description
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_position.requirement
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String requirement;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_position.otherinformation
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String otherinformation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_position.createtime
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_position.publishedtime
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date publishedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_position.ispublished
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer ispublished;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_position.publisher
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer publisher;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_position.emergencyflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer emergencyflag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_position.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer deleteflag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_position.position_name
     *
     * @return the value of recruit_position.position_name
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_position.position_name
     *
     * @param positionName the value for recruit_position.position_name
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_position.typeid
     *
     * @return the value of recruit_position.typeid
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_position.typeid
     *
     * @param typeid the value for recruit_position.typeid
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_position.departmentid
     *
     * @return the value of recruit_position.departmentid
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getDepartmentid() {
        return departmentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_position.departmentid
     *
     * @param departmentid the value for recruit_position.departmentid
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_position.workplace
     *
     * @return the value of recruit_position.workplace
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getWorkplace() {
        return workplace;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_position.workplace
     *
     * @param workplace the value for recruit_position.workplace
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setWorkplace(String workplace) {
        this.workplace = workplace == null ? null : workplace.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_position.needed
     *
     * @return the value of recruit_position.needed
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getNeeded() {
        return needed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_position.needed
     *
     * @param needed the value for recruit_position.needed
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setNeeded(Integer needed) {
        this.needed = needed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_position.description
     *
     * @return the value of recruit_position.description
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_position.description
     *
     * @param description the value for recruit_position.description
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_position.requirement
     *
     * @return the value of recruit_position.requirement
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getRequirement() {
        return requirement;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_position.requirement
     *
     * @param requirement the value for recruit_position.requirement
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setRequirement(String requirement) {
        this.requirement = requirement == null ? null : requirement.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_position.otherinformation
     *
     * @return the value of recruit_position.otherinformation
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getOtherinformation() {
        return otherinformation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_position.otherinformation
     *
     * @param otherinformation the value for recruit_position.otherinformation
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setOtherinformation(String otherinformation) {
        this.otherinformation = otherinformation == null ? null : otherinformation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_position.createtime
     *
     * @return the value of recruit_position.createtime
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_position.createtime
     *
     * @param createtime the value for recruit_position.createtime
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_position.publishedtime
     *
     * @return the value of recruit_position.publishedtime
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getPublishedtime() {
        return publishedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_position.publishedtime
     *
     * @param publishedtime the value for recruit_position.publishedtime
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setPublishedtime(Date publishedtime) {
        this.publishedtime = publishedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_position.ispublished
     *
     * @return the value of recruit_position.ispublished
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getIspublished() {
        return ispublished;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_position.ispublished
     *
     * @param ispublished the value for recruit_position.ispublished
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setIspublished(Integer ispublished) {
        this.ispublished = ispublished;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_position.publisher
     *
     * @return the value of recruit_position.publisher
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getPublisher() {
        return publisher;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_position.publisher
     *
     * @param publisher the value for recruit_position.publisher
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setPublisher(Integer publisher) {
        this.publisher = publisher;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_position.emergencyflag
     *
     * @return the value of recruit_position.emergencyflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getEmergencyflag() {
        return emergencyflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_position.emergencyflag
     *
     * @param emergencyflag the value for recruit_position.emergencyflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setEmergencyflag(Integer emergencyflag) {
        this.emergencyflag = emergencyflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_position.deleteflag
     *
     * @return the value of recruit_position.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getDeleteflag() {
        return deleteflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_position.deleteflag
     *
     * @param deleteflag the value for recruit_position.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }
    
    /* 自定义 */
    private String typeName; //职位名称

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
    
}