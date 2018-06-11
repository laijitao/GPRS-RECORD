package com.hp.cmcc.bboss.gprs.pojo;

import java.io.Serializable;

/**
 * The persistent class for the BBDC_TYPE_CDR database table.
 * 
 */
public class BbdcTypeCdr implements Serializable {
	private static final long serialVersionUID = 1L;

	private String dataAlign;     //对齐方式
	private String dataFiller;    //填充数据
	private String dataPattern;   //
	private String dataReplace;   //
	private String dataSeparator; //分隔符
	private Integer fieldBegIdx;  //字段开始索引
	private Integer fieldEndIdx;  //字段结束索引
	private Integer fieldLen;     //字段长度
	private String fieldName;     //字段名
	private String fieldType;     //字段类型
	private String fieldValue;    //字段值
	private String valName;       //
	private String valType;       //
	private String validateRegex; //字段校验正则表达式

	public BbdcTypeCdr() {
	}

	public BbdcTypeCdr(String fieldName, String dataSeparator, Integer fieldBegIdx, String fieldType) {
		super();
		this.dataSeparator = dataSeparator;
		this.fieldBegIdx = fieldBegIdx;
		this.fieldName = fieldName;
		this.fieldType = fieldType;
	}

	public BbdcTypeCdr(String dataAlign, String dataFiller, String dataPattern, String dataReplace,
			String dataSeparator, Integer fieldBegIdx, Integer fieldEndIdx, Integer fieldLen, String fieldName,
			String fieldType, String fieldValue, String valName, String valType, String validateRegex) {
		super();
		this.dataAlign = dataAlign;
		this.dataFiller = dataFiller;
		this.dataPattern = dataPattern;
		this.dataReplace = dataReplace;
		this.dataSeparator = dataSeparator;
		this.fieldBegIdx = fieldBegIdx;
		this.fieldEndIdx = fieldEndIdx;
		this.fieldLen = fieldLen;
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.fieldValue = fieldValue;
		this.valName = valName;
		this.valType = valType;
		this.validateRegex = validateRegex;
	}


	public String getDataAlign() {
		return this.dataAlign;
	}

	public void setDataAlign(String dataAlign) {
		this.dataAlign = dataAlign;
	}

	public String getDataFiller() {
		return this.dataFiller;
	}

	public void setDataFiller(String dataFiller) {
		this.dataFiller = dataFiller;
	}

	public String getDataPattern() {
		return this.dataPattern;
	}

	public void setDataPattern(String dataPattern) {
		this.dataPattern = dataPattern;
	}

	public String getDataReplace() {
		return this.dataReplace;
	}

	public void setDataReplace(String dataReplace) {
		this.dataReplace = dataReplace;
	}

	public String getDataSeparator() {
		return this.dataSeparator;
	}

	public void setDataSeparator(String dataSeparator) {
		this.dataSeparator = dataSeparator;
	}

	public Integer getFieldLen() {
		return this.fieldLen;
	}

	public void setFieldLen(Integer fieldLen) {
		this.fieldLen = fieldLen;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldValue() {
		return this.fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getValName() {
		return this.valName;
	}

	public void setValName(String valName) {
		this.valName = valName;
	}

	public String getValType() {
		return this.valType;
	}

	public void setValType(String valType) {
		this.valType = valType;
	}

	public String getValidateRegex() {
		return this.validateRegex;
	}

	public void setValidateRegex(String validateRegex) {
		this.validateRegex = validateRegex;
	}

	public Integer getFieldBegIdx() {
		return fieldBegIdx;
	}

	public void setFieldBegIdx(Integer fieldBegIdx) {
		this.fieldBegIdx = fieldBegIdx;
	}

	public Integer getFieldEndIdx() {
		return fieldEndIdx;
	}

	public void setFieldEndIdx(Integer fieldEndIdx) {
		this.fieldEndIdx = fieldEndIdx;
	}


}
