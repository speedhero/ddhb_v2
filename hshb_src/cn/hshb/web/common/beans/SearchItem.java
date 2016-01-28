package cn.hshb.web.common.beans;

/**
 * 搜索条件项目
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 *
 */
public class SearchItem {
	private String id;
	private String searchLabel;
	private Boolean onlyShowPrivate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSearchLabel() {
		return searchLabel;
	}
	public void setSearchLabel(String searchLabel) {
		this.searchLabel = searchLabel;
	}
	public Boolean getOnlyShowPrivate() {
		return onlyShowPrivate;
	}
	public void setOnlyShowPrivate(Boolean onlyShowPrivate) {
		this.onlyShowPrivate = onlyShowPrivate;
	}
	
}
