package cn.hshb.web.biz.mybatis.model;

public class HouseSecondHandSimpleHouse {
	private String houseNo;
	private String title;
	private Float price;
	private Float area;
	private Integer shi;
	private Integer ting;
	private Integer wei;
	private String coverImage;
	private Integer countyId;
	private String countyName;
	private Integer cbdId;
	private String cbdName;
	
	public String getHouseNo(){
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }
	
	public String getTitle(){
		return title;
	}
	public void setTitle(String title) {
        this.title = title;
    }
	
	public Float getPrice(){
		return price;
	}
	public void setPrice(Float price) {
        this.price = price;
    }
	
	public Float getArea(){
		return area;
	}
	public void setArea(Float area) {
        this.area = area;
    }
	
	public Integer getShi(){
		return shi;
	}
	public void setShi(Integer shi) {
        this.shi = shi;
    }
	
	public Integer getTing(){
		return ting;
	}
	public void setTing(Integer ting) {
        this.ting = ting;
    }
	
	public Integer getWei(){
		return wei;
	}
	public void setWei(Integer wei) {
        this.wei = wei;
    }
	
	public String getCoverImage(){
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
	
	public Integer getCountyId(){
		return countyId;
	}
	public void setCountyId(Integer countyId) {
        this.countyId = countyId;
    }
	
	public String getCountyName(){
		return countyName;
	}
	public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
	
	public Integer getCbdId(){
		return cbdId;
	}
	public void setCbdId(Integer cbdId) {
        this.cbdId = cbdId;
    }
	
	public String getCbdName(){
		return cbdName;
	}
	public void setCbdName(String cbdName) {
        this.cbdName = cbdName;
    }
}
