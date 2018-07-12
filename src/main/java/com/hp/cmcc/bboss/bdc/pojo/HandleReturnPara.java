package com.hp.cmcc.bboss.bdc.pojo;

import java.io.Serializable;
import java.util.List;

public class HandleReturnPara  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
