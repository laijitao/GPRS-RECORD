package com.hp.cmcc.bboss.gprs;

public class BdcCdr {
	
	private String name;
	private String age;
	private String gender;
	private Integer fieldBegIdx;
	
	public BdcCdr() {
	}
	
	public BdcCdr(String name, String age, String gender,Integer fieldBegIdx) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.fieldBegIdx = fieldBegIdx;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAge() {
		return age;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getFieldBegIdx() {
		return fieldBegIdx;
	}

	public void setFieldBegIdx(Integer fieldBegIdx) {
		this.fieldBegIdx = fieldBegIdx;
	}

	@Override
	public String toString() {
		return "["+fieldBegIdx + "]";
	}
	
	
}
