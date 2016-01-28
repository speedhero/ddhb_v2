package cn.hshb.web.biz.mybatis.model;

import java.util.Date;


public class MemberIntegralHistory extends MemberIntegralHistoryKey {
	/**
	 * add by myself field
	 */
	private PlatMemberInfo platMemberInfo;
	private PlatMemberInfo user;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_integral_history.member_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer memberId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_integral_history.integral
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer integral;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_integral_history.get_flag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer getFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_integral_history.gettedTime
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date gettedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_integral_history.comment
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String comment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_integral_history.userId
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_integral_history.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer deleteflag;

    
    public PlatMemberInfo getPlatMemberInfo() {
		return platMemberInfo;
	}

	public void setPlatMemberInfo(PlatMemberInfo platMemberInfo) {
		this.platMemberInfo = platMemberInfo;
	}

	public PlatMemberInfo getUser() {
		return user;
	}

	public void setUser(PlatMemberInfo user) {
		this.user = user;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_integral_history.member_id
     *
     * @return the value of member_integral_history.member_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_integral_history.member_id
     *
     * @param memberId the value for member_integral_history.member_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_integral_history.integral
     *
     * @return the value of member_integral_history.integral
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_integral_history.integral
     *
     * @param integral the value for member_integral_history.integral
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_integral_history.get_flag
     *
     * @return the value of member_integral_history.get_flag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getGetFlag() {
        return getFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_integral_history.get_flag
     *
     * @param getFlag the value for member_integral_history.get_flag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setGetFlag(Integer getFlag) {
        this.getFlag = getFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_integral_history.gettedTime
     *
     * @return the value of member_integral_history.gettedTime
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getGettedtime() {
        return gettedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_integral_history.gettedTime
     *
     * @param gettedtime the value for member_integral_history.gettedTime
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setGettedtime(Date gettedtime) {
        this.gettedtime = gettedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_integral_history.comment
     *
     * @return the value of member_integral_history.comment
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_integral_history.comment
     *
     * @param comment the value for member_integral_history.comment
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_integral_history.userId
     *
     * @return the value of member_integral_history.userId
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_integral_history.userId
     *
     * @param userid the value for member_integral_history.userId
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_integral_history.deleteflag
     *
     * @return the value of member_integral_history.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getDeleteflag() {
        return deleteflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_integral_history.deleteflag
     *
     * @param deleteflag the value for member_integral_history.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }
}