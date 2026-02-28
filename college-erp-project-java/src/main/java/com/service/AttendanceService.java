package com.service;

import java.util.List;

import com.dao.AttendanceDAO;
import com.dto.AttendanceDTO;

public class AttendanceService {
	private AttendanceDAO attendanceDAO;

	public AttendanceService() {

		attendanceDAO = new AttendanceDAO();

	}

	public boolean addAttendance(AttendanceDTO attendance) {

		if (attendance == null) {
			return false;
		}

		if (attendance.getStudentId() <= 0) {
			return false;
		}

		if (attendance.getCourseId() <= 0) {
			return false;
		}

		if (attendance.getDate() == null || attendance.getDate().isEmpty()) {
			return false;
		}

		if (attendance.getStatus() == null || attendance.getStatus().isEmpty()) {
			return false;
		}

		boolean isAdded = attendanceDAO.addAttendance(attendance);

		if (isAdded) {
			return true;
		}

		return false;
	}

	public AttendanceDTO getAttendanceById(int id) {

		if (id <= 0) {
			return null;
		}

		AttendanceDTO attendance = attendanceDAO.getAttendanceById(id);

		if (attendance != null) {
			return attendance;
		}

		return null;
	}

	public List<AttendanceDTO> getAttendanceByStudentId(int studentId) {

		if (studentId <= 0) {
			return null;
		}

		List<AttendanceDTO> attendanceList = attendanceDAO.getAttendanceByStudentId(studentId);

		if (attendanceList != null && !attendanceList.isEmpty()) {
			return attendanceList;
		}

		return null;
	}

	public List<AttendanceDTO> getAttendanceByCourseId(int courseId) {

		if (courseId <= 0) {
			return null;
		}

		List<AttendanceDTO> attendanceList = attendanceDAO.getAttendanceByCourseId(courseId);

		if (attendanceList != null && !attendanceList.isEmpty()) {
			return attendanceList;
		}

		return null;
	}

	public AttendanceDTO getAttendanceByStudentCourseAndDate(int studentId, int courseId, String date) {

		if (studentId <= 0 || courseId <= 0) {
			return null;
		}

		if (date == null || date.isEmpty()) {
			return null;
		}

		AttendanceDTO attendance = attendanceDAO.getAttendanceByStudentCourseAndDate(studentId, courseId, date);

		if (attendance != null) {
			return attendance;
		}

		return null;
	}

	public List<AttendanceDTO> getAttendanceByStudentAndCourse(int studentId, int courseId) {

		if (studentId <= 0 || courseId <= 0) {
			return null;
		}

		List<AttendanceDTO> attendanceList = attendanceDAO.getAttendanceByStudentAndCourse(studentId, courseId);

		if (attendanceList != null && !attendanceList.isEmpty()) {
			return attendanceList;
		}

		return null;
	}

	public List<AttendanceDTO> getAttendanceByCourseAndDate(int courseId, String date) {

		if (courseId <= 0) {
			return null;
		}

		if (date == null || date.isEmpty()) {
			return null;
		}

		List<AttendanceDTO> attendanceList = attendanceDAO.getAttendanceByCourseAndDate(courseId, date);

		if (attendanceList != null && !attendanceList.isEmpty()) {
			return attendanceList;
		}

		return null;
	}

	public boolean updateAttendance(AttendanceDTO attendance) {

		if (attendance == null) {
			return false;
		}

		if (attendance.getId() <= 0) {
			return false;
		}

		boolean isUpdated = attendanceDAO.updateAttendance(attendance);

		if (isUpdated) {
			return true;
		}

		return false;
	}

	public boolean deleteAttendance(int id) {

		if (id <= 0) {
			return false;
		}

		boolean isDeleted = attendanceDAO.deleteAttendance(id);

		if (isDeleted) {
			return true;
		}

		return false;
	}

	public List<AttendanceDTO> getAllAttendance() {

		List<AttendanceDTO> attendanceList = attendanceDAO.getAllAttendance();

		if (attendanceList != null && !attendanceList.isEmpty()) {
			return attendanceList;
		}

		return null;
	}
}
