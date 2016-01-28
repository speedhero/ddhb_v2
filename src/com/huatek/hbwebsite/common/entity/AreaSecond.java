package com.huatek.hbwebsite.common.entity;

import java.util.Date;
import java.util.Set;

import com.huatek.base.entity.BaseEntity;

/**
 * 租赁城区方位对象
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 *
 */
public class AreaSecond extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2041464848427815287L;
	private String erpId;
	private String countyName;	//城区方位名称
	private String cityId;			//城市ID
	private Date lastModified;	//最后更改时间
	private Date lastSync;
	private Integer deleteFlag;	//删除标志
	private Integer sortFlag;		//排序标志
	private String commonIds;		//对应小区ID列表
	
	private Set<Community> communitys;	//对应的小区列表
	
	public Set<Community> getCommunitys() {
		return communitys;
	}
	public void setCommunitys(Set<Community> communitys) {
		this.communitys = communitys;
	}
	public String getErpId() {
		return erpId;
	}
	public void setErpId(String erpId) {
		this.erpId = erpId;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public Date getLastSync() {
		return lastSync;
	}
	public void setLastSync(Date lastSync) {
		this.lastSync = lastSync;
	}
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Integer getSortFlag() {
		return sortFlag;
	}
	public void setSortFlag(Integer sortFlag) {
		this.sortFlag = sortFlag;
	}
	public String getCommonIds() {
		return commonIds;
	}
	public void setCommonIds(String commonIds) {
		this.commonIds = commonIds;
	}

}