package com.huatek.hbwebsite.contract.entity;

public class ContractFlowEntity {
	private String step;
	private String stepName;
	private String completionDate;
	private String state;
	private String stepRemarks;

	public String getCompletionDate() {
		return this.completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStepRemarks() {
		return this.stepRemarks;
	}

	public void setStepRemarks(String stepRemarks) {
		this.stepRemarks = stepRemarks;
	}

	public String getStep() {
		return this.step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getStepName() {
		return this.stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
}
