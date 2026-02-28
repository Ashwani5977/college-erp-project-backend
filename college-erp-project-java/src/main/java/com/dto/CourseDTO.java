package com.dto;

public class CourseDTO {
	private int id;
	private String courseName;
	private String courseCode;
	private String department;

	public CourseDTO(int id, String courseName, String courseCode, String department) {
		this.id = id;
		this.courseName = courseName;
		this.courseCode = courseCode;
		this.department = department;
	}

	public CourseDTO() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
