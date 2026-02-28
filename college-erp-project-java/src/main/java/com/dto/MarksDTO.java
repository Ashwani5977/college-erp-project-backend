package com.dto;

public class MarksDTO {
	private int id;
	private int studentId;
	private int courseId;
	private int marks;
	private String examType;
	public MarksDTO(int id, int studentId, int courseId, int marks, String examType) {
	    this.id = id;
	    this.studentId = studentId;
	    this.courseId = courseId;
	    this.marks = marks;
	    this.examType = examType;
	}
	public MarksDTO() {
		
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
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	
}
