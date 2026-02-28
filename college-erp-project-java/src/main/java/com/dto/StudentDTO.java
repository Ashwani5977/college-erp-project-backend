package com.dto;

public class StudentDTO {
	private int id;
	private int userId;
	private String rollNumber;
	private String department;
	private int year;
	private String phone;
	public StudentDTO(int id, int userId, String rollNumber, String department, int year, String phone) {
		this.id = id;
		this.userId = userId;
		this.rollNumber = rollNumber;
		this.department = department;
		this.year = year;
		this.phone = phone;
	}
	public StudentDTO() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
