package com.hp.cmcc.bboss.gprs.pojo;

import java.util.List;

public class HandleReturnPara {
	private List<String> records;
	private Integer errNum;
	
	public HandleReturnPara() {
		super();
	}

	public HandleReturnPara(List<String> records, Integer errNum) {
		super();
		this.records = records;
		this.errNum = errNum;
	}

	public List<String> getRecords() {
		return records;
	}

	public void setRecords(List<String> records) {
		this.records = records;
	}

	public Integer getErrNum() {
		return errNum;
	}

	public void setErrNum(Integer errNum) {
		this.errNum = errNum;
	}
	
	
}
