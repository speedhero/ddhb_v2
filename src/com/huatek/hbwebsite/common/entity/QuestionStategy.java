package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.common.entity.QuestionStrategySubtype;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import net.dongdao.axis2.entity.toERP.QuestionStategySyn;

public class QuestionStategy extends BaseEntity {
	private static final long serialVersionUID = 3200537339876168940L;
	private QuestionStrategySubtype questionType;
	private String title;
	private String content;
	private String parentQuestionId;
	private Date createTime;
	private Date lastModifyTime;
	private Integer answeredFlag;
	private int isFinished;
	private int isShow;
	private int updateFlag;
	private Date lastModified;
	private Date lastSync;
	private int browsed;
	private int deleteFlag;
	private PlatMemberInfo user;
	private String publishName;
	private String erpId;
	private String verifyCode;
	private int synchronizedFlag;
	private Timestamp synchronizedTime;

	public int getSynchronizedFlag() {
		return this.synchronizedFlag;
	}

	public void setSynchronizedFlag(int synchronizedFlag) {
		this.synchronizedFlag = synchronizedFlag;
	}

	public Timestamp getSynchronizedTime() {
		return this.synchronizedTime;
	}

	public void setSynchronizedTime(Timestamp synchronizedTime) {
		this.synchronizedTime = synchronizedTime;
	}

	public String getVerifyCode() {
		return this.verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getErpId() {
		return this.erpId;
	}

	public String getPublishName() {
		return this.publishName;
	}

	public void setPublishName(String publishName) {
		this.publishName = publishName;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public QuestionStrategySubtype getQuestionType() {
		return this.questionType;
	}

	public void setQuestionType(QuestionStrategySubtype questionType) {
		this.questionType = questionType;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		//处理回答的内容,把HTML标签去掉
		Pattern p = Pattern.compile("&lt;[\\w/]+&gt;");
		String[] changeValues = p.split(this.content);
		String changeValue = "";
		for(String s : changeValues){
			changeValue += s ;
		}
		return changeValue;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getParentQuestionId() {
		return this.parentQuestionId;
	}

	public void setParentQuestionId(String parentQuestionId) {
		this.parentQuestionId = parentQuestionId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getBrowsed() {
		return this.browsed;
	}

	public void setBrowsed(int browsed) {
		this.browsed = browsed;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public PlatMemberInfo getUser() {
		return this.user;
	}

	public void setUser(PlatMemberInfo user) {
		this.user = user;
	}

	public Date getLastModifyTime() {
		return this.lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public Integer getAnsweredFlag() {
		return this.answeredFlag;
	}

	public void setAnsweredFlag(Integer answeredFlag) {
		this.answeredFlag = answeredFlag;
	}

	public int getIsFinished() {
		return this.isFinished;
	}

	public void setIsFinished(int isFinished) {
		this.isFinished = isFinished;
	}

	public int getIsShow() {
		return this.isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public int getUpdateFlag() {
		return this.updateFlag;
	}

	public void setUpdateFlag(int updateFlag) {
		this.updateFlag = updateFlag;
	}

	public Date getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Date getLastSync() {
		return this.lastSync;
	}

	public void setLastSync(Date lastSync) {
		this.lastSync = lastSync;
	}

	public QuestionStategySyn convertToSyncObj(String operationId) {
		QuestionStategySyn questionStategySyn = new QuestionStategySyn();
		questionStategySyn.setItemID(String.valueOf(getErpId()));
		if (operationId == null) {
			questionStategySyn.setOperationId("1");
		} else {
			questionStategySyn.setOperationId(operationId);
		}

		questionStategySyn.setContent(getContent());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = getCreateTime()==null ? "" : df.format(getCreateTime());
		questionStategySyn.setCreateTime(timeStr);
		if (getUser() == null) {
			questionStategySyn.setMemberID("");
			questionStategySyn.setMemberName("");
		} else {
			if (getUser().getId() == null) {
				questionStategySyn.setMemberID("");
			} else {
				questionStategySyn.setMemberID(String.valueOf(getUser().getId()));
			}

			if (getUser().getAccName() == null) {
				questionStategySyn.setMemberName("");
			} else {
				questionStategySyn.setMemberName(getUser().getAccName());
			}
		}

		questionStategySyn.setQuestionID(getErpId());
		if(getQuestionType()!=null)
			questionStategySyn.setQuestionType(getQuestionType().getErpId());
		else
			questionStategySyn.setQuestionType("");
		questionStategySyn.setTitle(getTitle());
		return questionStategySyn;
	}
}
