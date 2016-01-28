package com.huatek.hbwebsite.saveQuery.entity;

public class SaveQueryConditions {
	private Integer queryId;					//自增长id
	private String queryConditions;				//保存的查询条件
	
	public SaveQueryConditions(){}
	
	public SaveQueryConditions(Integer queryId,String queryConditions){
		this.queryId = queryId;
		this.queryConditions = queryConditions;
	}
	
	public Integer getQueryId() {
		if(queryId == null)
			queryId = 0;
		return queryId;
	}
	public void setQueryId(Integer queryId) {
		this.queryId = queryId;
	}
	public String getQueryConditions() {
		return queryConditions;
	}
	public void setQueryConditions(String queryConditions) {
		this.queryConditions = queryConditions;
	}
	
	
}	
