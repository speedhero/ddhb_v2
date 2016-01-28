/**
 * 
 */
package cn.hshb.web.partner.baidu.entity.houseSecondDel;

import java.util.Date;

/**
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 * 
 */
public class HouseSecondDel {
	private String shelvingID;
	private String title;
	private Date lastmod;
	private Date publishTime;
	private Integer isSync;
	public String getShelvingID() {
		return shelvingID;
	}
	public void setShelvingID(String shelvingID) {
		this.shelvingID = shelvingID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getLastmod() {
		return lastmod;
	}
	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Integer getIsSync() {
		return isSync;
	}
	public void setIsSync(Integer isSync) {
		this.isSync = isSync;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	private Integer status;
}
