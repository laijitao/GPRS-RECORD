package com.hp.cmcc.bboss.gprs.exception;

public class ValidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errCode;
	private String errMsg;
	
	public ValidException() {
		super();
	}

	public ValidException(String errCode, String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String toString() {
		return  "{errCode:" +errCode+"," +"errMsg:"+ errMsg +"}";
	}
	
}
