package com.balajisiddi.model;

public class Student {
	
	int id;
	String name;
	String sclass;
	
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	public Student(int id, String name, String sclass) {
		super();
		this.id = id;
		this.name = name;
		this.sclass = sclass;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSclass() {
		return sclass;
	}
	public void setSclass(String sclass) {
		this.sclass = sclass;
	}
	
	
}
