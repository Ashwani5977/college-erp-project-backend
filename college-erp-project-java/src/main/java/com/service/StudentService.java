package com.service;

import java.util.List;

import com.dao.StudentDAO;
import com.dto.StudentDTO;

public class StudentService {
	private StudentDAO studentDAO;

	public StudentService() {
		studentDAO = new StudentDAO();
	}

	public boolean addStudent(StudentDTO student) {

		if (student == null) {
			return false;
		}

		if (student.getUserId() <= 0 || student.getRollNumber() == null || student.getRollNumber().isEmpty()
				|| student.getDepartment() == null || student.getDepartment().isEmpty() || student.getYear() <= 0
				|| student.getPhone() == null || student.getPhone().isEmpty()) {

			return false;
		}

		boolean isAdded = studentDAO.addStudent(student);

		if (isAdded) {
			return true;
		}

		return false;
	}

	public StudentDTO getStudentById(int id) {

		if (id <= 0) {
			return null;
		}

		StudentDTO student = studentDAO.getStudentById(id);

		if (student != null) {
			return student;
		}

		return null;
	}

	public StudentDTO getStudentByUserId(int userId) {

		if (userId <= 0) {
			return null;
		}

		StudentDTO student = studentDAO.getStudentByUserId(userId);

		if (student != null) {
			return student;
		}

		return null;
	}

	public List<StudentDTO> getAllStudents() {

		List<StudentDTO> students = studentDAO.getAllStudents();

		if (students != null && !students.isEmpty()) {
			return students;
		}

		return null;
	}

	public boolean deleteStudent(int id) {

		if (id <= 0) {
			return false;
		}

		boolean isDeleted = studentDAO.deleteStudent(id);

		if (isDeleted) {
			return true;
		}

		return false;
	}

}
