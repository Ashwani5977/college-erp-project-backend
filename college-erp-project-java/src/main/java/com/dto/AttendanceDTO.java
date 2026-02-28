package com.dto;

public class AttendanceDTO {
	private int id;
	private int studentId;
	private int courseId;
	private String date;
	private String status;

	public AttendanceDTO(int id, int studentId, int courseId, String date, String status) {
		this.id = id;
		this.studentId = studentId;
		this.courseId = courseId;
		this.date = date;
		this.status = status;
	}

	public AttendanceDTO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
