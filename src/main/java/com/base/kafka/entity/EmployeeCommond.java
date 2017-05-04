package com.base.kafka.entity;

import java.io.Serializable;

public class EmployeeCommond implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String empName ;
	private String empNo ;
	private String empSex ;
	private Integer age ;
	private String address ;
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpSex() {
		return empSex;
	}
	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public EmployeeCommond(String empName, String empNo, String empSex,
			Integer age, String address) {
		super();
		this.empName = empName;
		this.empNo = empNo;
		this.empSex = empSex;
		this.age = age;
		this.address = address;
	}
	public EmployeeCommond() {
		super();
	}
	
	

}
