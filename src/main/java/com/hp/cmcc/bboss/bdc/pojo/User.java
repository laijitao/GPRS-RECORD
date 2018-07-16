package com.hp.cmcc.bboss.bdc.pojo;

import java.util.Date;

public class User {

	private String name;
	private int age;
	private String gender;
	private Date cT;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String name, int age, String gender,Date cT) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.cT = cT;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getcT() {
		return cT;
	}

	public void setcT(Date cT) {
		this.cT = cT;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", gender=" + gender + ", cT=" + cT + "]";
	}

	
}
