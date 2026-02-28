package com.service;

import java.util.List;

import com.dao.StudentCourseDAO;
import com.dto.StudentCourseDTO;

public class StudentCourseService {
	private StudentCourseDAO studentCourseDAO;

	public StudentCourseService() {
		studentCourseDAO = new StudentCourseDAO();
	}

	public boolean enrollStudentToCourse(int studentId, int courseId) {

		if (studentId <= 0 || courseId <= 0) {
			return false;
		}

		boolean isEnrolled = studentCourseDAO.enrollStudentToCourse(studentId, courseId);

		if (isEnrolled) {
			return true;
		}

		return false;
	}

	public List<StudentCourseDTO> getCoursesByStudentId(int studentId) {

		if (studentId <= 0) {
			return null;
		}

		List<StudentCourseDTO> courses = studentCourseDAO.getCoursesByStudentId(studentId);

		if (courses != null && !courses.isEmpty()) {
			return courses;
		}

		return null;
	}

	public List<StudentCourseDTO> getStudentsByCourseId(int courseId) {

		if (courseId <= 0) {
			return null;
		}

		List<StudentCourseDTO> students = studentCourseDAO.getStudentsByCourseId(courseId);

		if (students != null && !students.isEmpty()) {
			return students;
		}

		return null;
	}

	public boolean deleteEnrollment(int studentId, int courseId) {

		if (studentId <= 0 || courseId <= 0) {
			return false;
		}

		boolean isDeleted = studentCourseDAO.deleteEnrollment(studentId, courseId);

		if (isDeleted) {
			return true;
		}

		return false;
	}

	public List<StudentCourseDTO> getAllEnrollments() {

		List<StudentCourseDTO> enrollments = studentCourseDAO.getAllEnrollments();

		if (enrollments != null && !enrollments.isEmpty()) {
			return enrollments;
		}

		return null;
	}
}
