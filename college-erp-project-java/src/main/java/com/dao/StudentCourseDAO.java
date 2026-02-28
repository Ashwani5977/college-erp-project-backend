package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dto.StudentCourseDTO;
import com.util.ConnectionFactory;

public class StudentCourseDAO {
	public boolean enrollStudentToCourse(int studentId, int courseId) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into student_course(student_id,course_id) values(?,?)";
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, studentId);
			ps.setInt(2, courseId);
			int isEnrolled = ps.executeUpdate();
			return isEnrolled > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return false;
	}

	public List<StudentCourseDTO> getCoursesByStudentId(int studentId) {
		List<StudentCourseDTO> scdto = new ArrayList<StudentCourseDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StudentCourseDTO stdo = null;
		try {
			String sql = "select * from student_course where student_id=?";
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, studentId);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int student_id = rs.getInt("student_id");
				int course_id = rs.getInt("course_id");
				stdo = new StudentCourseDTO(id, student_id, course_id);
				scdto.add(stdo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return scdto;
	}

	public List<StudentCourseDTO> getStudentsByCourseId(int courseId) {
		List<StudentCourseDTO> scdto = new ArrayList<StudentCourseDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StudentCourseDTO stdo = null;
		try {
			String sql = "select * from student_course where course_id=?";
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, courseId);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int student_id = rs.getInt("student_id");
				int course_id = rs.getInt("course_id");
				stdo = new StudentCourseDTO(id, student_id, course_id);
				scdto.add(stdo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return scdto;
	}

	public boolean deleteEnrollment(int studentId, int courseId) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "delete from student_course where student_id=? and course_id=?";
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, studentId);
			ps.setInt(2, courseId);
			int isDeleted = ps.executeUpdate();
			return isDeleted > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return false;
	}

	public List<StudentCourseDTO> getAllEnrollments() {
		List<StudentCourseDTO> scdto = new ArrayList<StudentCourseDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StudentCourseDTO stdo = null;
		try {
			String sql = "select * from student_course";
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int student_id = rs.getInt("student_id");
				int course_id = rs.getInt("course_id");
				stdo = new StudentCourseDTO(id, student_id, course_id);
				scdto.add(stdo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return scdto;
	}

}
