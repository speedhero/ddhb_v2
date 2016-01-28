package com.huatek.framework.sso;

import java.io.Serializable;

public class ResponseBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String resultCode;
	private String retData;
	private boolean success;
	private String resultMsg;
	private String status;

	public String getResultCode() {
		return this.resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getRetData() {
		return this.retData;
	}

	public void setRetData(String retData) {
		this.retData = retData;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getResultMsg() {
		return this.resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public ResponseBean() {
	}

	public ResponseBean(String resultCode, String resultMsg, boolean success) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
		this.success = success;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
