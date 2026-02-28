package com.service;

import java.util.List;

import com.dao.TeacherCourseDAO;
import com.dto.TeacherCourseDTO;

public class TeacherCourseService {
	private TeacherCourseDAO teacherCourseDAO;

	public TeacherCourseService() {
		teacherCourseDAO = new TeacherCourseDAO();
	}

	public boolean assignCourseToTeacher(int teacherId, int courseId) {

		if (teacherId <= 0 || courseId <= 0) {
			return false;
		}

		boolean isAssigned = teacherCourseDAO.assignCourseToTeacher(teacherId, courseId);

		if (isAssigned) {
			return true;
		}

		return false;
	}

	public List<TeacherCourseDTO> getCoursesByTeacherId(int teacherId) {

		if (teacherId <= 0) {
			return null;
		}

		List<TeacherCourseDTO> courses = teacherCourseDAO.getCoursesByTeacherId(teacherId);

		if (courses != null && !courses.isEmpty()) {
			return courses;
		}

		return null;
	}

	public List<TeacherCourseDTO> getTeachersByCourseId(int courseId) {

		if (courseId <= 0) {
			return null;
		}

		List<TeacherCourseDTO> teachers = teacherCourseDAO.getTeachersByCourseId(courseId);

		if (teachers != null && !teachers.isEmpty()) {
			return teachers;
		}

		return null;
	}

	public boolean deleteAssignment(int teacherId, int courseId) {

		if (teacherId <= 0 || courseId <= 0) {
			return false;
		}

		boolean isDeleted = teacherCourseDAO.deleteAssignment(teacherId, courseId);

		if (isDeleted) {
			return true;
		}

		return false;
	}

	public List<TeacherCourseDTO> getAllAssignments() {

		List<TeacherCourseDTO> assignments = teacherCourseDAO.getAllAssignments();

		if (assignments != null && !assignments.isEmpty()) {
			return assignments;
		}

		return null;
	}
}
