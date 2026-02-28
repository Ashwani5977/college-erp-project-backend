package com.service;

import java.util.List;

import com.dao.CourseDAO;
import com.dto.CourseDTO;

public class CourseService {
	private CourseDAO courseDAO;

	public CourseService() {

		courseDAO = new CourseDAO();

	}

	public boolean addCourse(CourseDTO course) {

		if (course == null) {
			return false;
		}

		if (course.getCourseName() == null || course.getCourseName().isEmpty()) {
			return false;
		}

		if (course.getCourseCode() == null || course.getCourseCode().isEmpty()) {
			return false;
		}

		if (course.getDepartment() == null || course.getDepartment().isEmpty()) {
			return false;
		}

		boolean isAdded = courseDAO.addCourse(course);

		if (isAdded) {
			return true;
		}

		return false;
	}

	public CourseDTO getCourseById(int id) {

		if (id <= 0) {
			return null;
		}

		CourseDTO course = courseDAO.getCourseById(id);

		if (course != null) {
			return course;
		}

		return null;
	}

	public CourseDTO getCourseByCourseCode(String courseCode) {

		if (courseCode == null || courseCode.isEmpty()) {
			return null;
		}

		CourseDTO course = courseDAO.getCourseByCourseCode(courseCode);

		if (course != null) {
			return course;
		}

		return null;
	}

	public List<CourseDTO> getAllCourses() {

		List<CourseDTO> courseList = courseDAO.getAllCourses();

		if (courseList != null && !courseList.isEmpty()) {
			return courseList;
		}

		return null;
	}

	public boolean updateCourse(CourseDTO course) {

		if (course == null) {
			return false;
		}

		if (course.getId() <= 0) {
			return false;
		}

		boolean isUpdated = courseDAO.updateCourse(course);

		if (isUpdated) {
			return true;
		}

		return false;
	}

	public boolean deleteCourse(int id) {

		if (id <= 0) {
			return false;
		}

		boolean isDeleted = courseDAO.deleteCourse(id);

		if (isDeleted) {
			return true;
		}

		return false;
	}

	public List<CourseDTO> getCoursesByDepartment(String department) {

		if (department == null || department.isEmpty()) {
			return null;
		}

		List<CourseDTO> courseList = courseDAO.getCoursesByDepartment(department);

		if (courseList != null && !courseList.isEmpty()) {
			return courseList;
		}

		return null;
	}
}
