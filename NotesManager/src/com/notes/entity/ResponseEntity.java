package com.notes.entity;

public class ResponseEntity {

	private String status;
	private String errorFlag;
	private String errorCode;
	private int agn;
	public int getAgn() {
		return agn;
	}
	public void setAgn(int agn) {
		this.agn = agn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorFlag() {
		return errorFlag;
	}
	public void setErrorFlag(String errorFlag) {
		this.errorFlag = errorFlag;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	

}
