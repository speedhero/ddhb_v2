package com.huatek.hbwebsite.house.entity;

import cn.hshb.web.biz.mybatis.model.BBroker;

import com.huatek.hbwebsite.broker.entity.Broker;

public class Evaluation {
	private Broker broker;
	private String title;
	private String content;
	private String EvaluateTime;

	//新增经纪人
	private BBroker bBroker;
	
	public BBroker getbBroker() {
		return bBroker;
	}

	public void setbBroker(BBroker bBroker) {
		this.bBroker = bBroker;
	}

	public Broker getBroker() {
		return this.broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEvaluateTime() {
		return this.EvaluateTime;
	}

	public void setEvaluateTime(String evaluateTime) {
		this.EvaluateTime = evaluateTime;
	}
}
