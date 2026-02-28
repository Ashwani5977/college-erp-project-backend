package com.dto;

public class TeacherDTO {
	private int id;
	private int userId;
	private String employeeId;
	private String department;
	private String phone;
	public TeacherDTO(int id, int userId, String employeeId, String department, String phone) {
	    this.id = id;
	    this.userId = userId;
	    this.employeeId = employeeId;
	    this.department = department;
	    this.phone = phone;
	}
	public TeacherDTO() {

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
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
