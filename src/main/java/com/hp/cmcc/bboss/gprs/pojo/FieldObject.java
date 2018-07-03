package com.hp.cmcc.bboss.gprs.pojo;

public class FieldObject {
	
	private String fieldName;
	private String fieldValue;
	private Integer fieldIndex;
	
	public FieldObject() {
		super();
	}

	public FieldObject(String fieldName, String fieldValue, Integer fieldIndex) {
		super();
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.fieldIndex = fieldIndex;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public Integer getFieldIndex() {
		return fieldIndex;
	}

	public void setFieldIndex(Integer fieldIndex) {
		this.fieldIndex = fieldIndex;
	}

	@Override
	public String toString() {
		return "FieldObject [fieldName=" + fieldName + ", fieldValue=" + fieldValue + ", fieldIndex=" + fieldIndex
				+ "]";
	}

	
}
