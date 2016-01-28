package cn.hshb.web.biz.mybatis.model;

public class InputSearch {
	private String id;
	private String searchName;		//名称
	private String searchUrl;		//对应的url
	private String FirstLetter;		//首字母
	private String Spelling;		//全拼
	
	
	public String getFirstLetter() {
		return FirstLetter;
	}
	public void setFirstLetter(String firstLetter) {
		FirstLetter = firstLetter;
	}
	public String getSpelling() {
		return Spelling;
	}
	public void setSpelling(String spelling) {
		Spelling = spelling;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public String getSearchUrl() {
		return searchUrl;
	}
	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}
	
	
}
