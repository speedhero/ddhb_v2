package cn.hshb.web.house.enums;

public enum EnumAppraiseType {
	ZHONG_FANG("1","重房评价"),
	GEN_DAN("2","跟单评价"),
	PU_TONG("3","普通评价");
	

	private String number;
	private String value;
	
	
	private EnumAppraiseType(String number, String value){
		this.number = number;
		this.value = value;
	}

	/**
	 * 输入编号,返回值
	 * @param number/
	 * @return
	 */
	public EnumAppraiseType fromNumber(String number){
		for(EnumAppraiseType e : EnumAppraiseType.values()){
			if(e.getNumber().equals(number)){
				return e;
			}
		}
		throw new IllegalArgumentException("指定的评价类型["+number+"]不存在.");	
	}
	public EnumAppraiseType fromValue(String value){
		for(EnumAppraiseType e : EnumAppraiseType.values()){
			if(e.getValue().equals(value))
				return e;
		}
		throw new IllegalArgumentException("指定的评价编号["+value+"]不存在.");
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
