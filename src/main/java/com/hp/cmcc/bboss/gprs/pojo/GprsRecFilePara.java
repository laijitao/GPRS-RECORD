package com.hp.cmcc.bboss.gprs.pojo;

import java.io.Serializable;
import java.util.List;

public class GprsRecFilePara implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> fileBody;
	private List<BbdcTypeCdr> rule;
	private String fileName;
//	private Integer fileId;
	
	public GprsRecFilePara() {
		super();
	}

	public GprsRecFilePara(List<String> fileBody, List<BbdcTypeCdr> rule, String fileName) {
		super();
		this.fileBody = fileBody;
		this.rule = rule;
		this.fileName = fileName;
//		this.fileId = fileId;
	}

	public List<BbdcTypeCdr> getRule() {
		return rule;
	}

	public void setRule(List<BbdcTypeCdr> rule) {
		this.rule = rule;
	}

	public List<String> getFileBody() {
		return fileBody;
	}

	public void setFileBody(List<String> fileBody) {
		this.fileBody = fileBody;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

//	public Integer getfileId() {
//		return fileId;
//	}
//
//	public void setfileId(Integer fileId) {
//		this.fileId = fileId;
//	}

}
