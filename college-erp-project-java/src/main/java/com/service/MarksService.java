package com.service;

import java.util.List;

import com.dao.MarksDAO;
import com.dto.MarksDTO;

public class MarksService {
	private MarksDAO marksDAO;

	public MarksService() {
		marksDAO = new MarksDAO();
	}

	public boolean addMarks(MarksDTO marks) {

		if (marks == null) {
			return false;
		}

		if (marks.getStudentId() <= 0 || marks.getCourseId() <= 0) {
			return false;
		}

		if (marks.getExamType() == null || marks.getExamType().isEmpty()) {
			return false;
		}

		boolean isAdded = marksDAO.addMarks(marks);

		if (isAdded) {
			return true;
		}

		return false;
	}

	public MarksDTO getMarksById(int id) {

		if (id <= 0) {
			return null;
		}

		MarksDTO marks = marksDAO.getMarksById(id);

		if (marks != null) {
			return marks;
		}

		return null;
	}

	public List<MarksDTO> getMarksByStudentId(int studentId) {

		if (studentId <= 0) {
			return null;
		}

		List<MarksDTO> marksList = marksDAO.getMarksByStudentId(studentId);

		if (marksList != null && !marksList.isEmpty()) {
			return marksList;
		}

		return null;
	}

	public List<MarksDTO> getMarksByCourseId(int courseId) {

		if (courseId <= 0) {
			return null;
		}

		List<MarksDTO> marksList = marksDAO.getMarksByCourseId(courseId);

		if (marksList != null && !marksList.isEmpty()) {
			return marksList;
		}

		return null;
	}

	public List<MarksDTO> getMarksByStudentAndCourse(int studentId, int courseId) {

		if (studentId <= 0 || courseId <= 0) {
			return null;
		}

		List<MarksDTO> marksList = marksDAO.getMarksByStudentAndCourse(studentId, courseId);

		if (marksList != null && !marksList.isEmpty()) {
			return marksList;
		}

		return null;
	}

	public MarksDTO getMarksByStudentCourseAndExamType(int studentId, int courseId, String examType) {

		if (studentId <= 0 || courseId <= 0 || examType == null || examType.isEmpty()) {
			return null;
		}

		MarksDTO marks = marksDAO.getMarksByStudentCourseAndExamType(studentId, courseId, examType);

		if (marks != null) {
			return marks;
		}

		return null;
	}

	public boolean updateMarks(MarksDTO marks) {

		if (marks == null) {
			return false;
		}

		boolean isUpdated = marksDAO.updateMarks(marks);

		if (isUpdated) {
			return true;
		}

		return false;
	}

	public boolean deleteMarks(int id) {

		if (id <= 0) {
			return false;
		}

		boolean isDeleted = marksDAO.deleteMarks(id);

		if (isDeleted) {
			return true;
		}

		return false;
	}

	public List<MarksDTO> getAllMarks() {

		List<MarksDTO> marksList = marksDAO.getAllMarks();

		if (marksList != null && !marksList.isEmpty()) {
			return marksList;
		}

		return null;
	}
}
