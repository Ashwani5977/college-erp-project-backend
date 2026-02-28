package com.service;

import java.util.List;

import com.dao.TeacherDAO;
import com.dto.TeacherDTO;

public class TeacherService {
	private TeacherDAO teacherDAO;

	public TeacherService() {
		teacherDAO = new TeacherDAO();
	}

	public boolean addTeacher(TeacherDTO teacher) {

		if(teacher == null) {
			return false;
		}

		if(teacher.getUserId() <= 0 ||
		   teacher.getEmployeeId() == null || teacher.getEmployeeId().isEmpty() ||
		   teacher.getDepartment() == null || teacher.getDepartment().isEmpty() ||
		   teacher.getPhone() == null || teacher.getPhone().isEmpty()) {

			return false;
		}

		boolean isAdded = teacherDAO.addTeacher(teacher);

		if(isAdded) {
			return true;
		}

		return false;
	}

	public TeacherDTO getTeacherById(int id) {

		if(id <= 0) {
			return null;
		}

		TeacherDTO teacher = teacherDAO.getTeacherById(id);

		if(teacher != null) {
			return teacher;
		}

		return null;
	}

	public TeacherDTO getTeacherByUserId(int userId) {

		if(userId <= 0) {
			return null;
		}

		TeacherDTO teacher = teacherDAO.getTeacherByUserId(userId);

		if(teacher != null) {
			return teacher;
		}

		return null;
	}

	public List<TeacherDTO> getAllTeachers() {

		List<TeacherDTO> teachers = teacherDAO.getAllTeachers();

		if(teachers != null && !teachers.isEmpty()) {
			return teachers;
		}

		return null;
	}

	public boolean deleteTeacher(int id) {

		if(id <= 0) {
			return false;
		}

		boolean isDeleted = teacherDAO.deleteTeacher(id);

		if(isDeleted) {
			return true;
		}

		return false;
	}
}
