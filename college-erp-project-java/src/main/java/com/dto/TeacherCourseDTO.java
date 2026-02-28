package com.dto;

public class TeacherCourseDTO {
	private int id;
	private int teacherId;
	private int courseId;
	public TeacherCourseDTO(int id, int teacherId, int courseId) {
	    this.id = id;
	    this.teacherId = teacherId;
	    this.courseId = courseId;
	}
	public TeacherCourseDTO() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
}
