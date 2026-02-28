package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dto.AttendanceDTO;
import com.util.ConnectionFactory;

public class AttendanceDAO {
	public boolean addAttendance(AttendanceDTO attendance) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into attendance(student_id,course_id,date,status) values(?,?,?,?)";
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, attendance.getStudentId());
			ps.setInt(2, attendance.getCourseId());
			ps.setString(3, attendance.getDate());
			ps.setString(4, attendance.getStatus());
			int isAdded = ps.executeUpdate();
			return isAdded > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return false;
	}

	public AttendanceDTO getAttendanceById(int id) {
		AttendanceDTO adto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select * from attendance where id=?";
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				int id2 = rs.getInt("id");
				int student_id = rs.getInt("student_id");
				int course_id = rs.getInt("course_id");
				String date = rs.getString("date");
				String status = rs.getString("status");
				adto = new AttendanceDTO(id2, student_id, course_id, date, status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return adto;
	}

	public List<AttendanceDTO> getAttendanceByStudentId(int studentId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<AttendanceDTO> ladto = new ArrayList<AttendanceDTO>();
		AttendanceDTO adto = null;
		try {
			String sql = "select * from attendance where student_id=?";
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, studentId);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id2 = rs.getInt("id");
				int student_id = rs.getInt("student_id");
				int course_id = rs.getInt("course_id");
				String date = rs.getString("date");
				String status = rs.getString("status");
				adto = new AttendanceDTO(id2, student_id, course_id, date, status);
				ladto.add(adto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return ladto;
	}

	public List<AttendanceDTO> getAttendanceByCourseId(int courseId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<AttendanceDTO> ladto = new ArrayList<AttendanceDTO>();
		AttendanceDTO adto = null;
		try {
			String sql = "select * from attendance where course_id=?";
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, courseId);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id2 = rs.getInt("id");
				int student_id = rs.getInt("student_id");
				int course_id = rs.getInt("course_id");
				String date = rs.getString("date");
				String status = rs.getString("status");
				adto = new AttendanceDTO(id2, student_id, course_id, date, status);
				ladto.add(adto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return ladto;
	}

	public AttendanceDTO getAttendanceByStudentCourseAndDate(int studentId, int courseId, String date) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AttendanceDTO adto = null;
		try {
			String sql = "select * from attendance where student_id=? and course_id=? and date=?";
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, studentId);
			ps.setInt(2, courseId);
			ps.setString(3, date);
			rs = ps.executeQuery();
			if (rs.next()) {
				int id2 = rs.getInt("id");
				int student_id = rs.getInt("student_id");
				int course_id = rs.getInt("course_id");
				String date2 = rs.getString("date");
				String status = rs.getString("status");
				adto = new AttendanceDTO(id2, student_id, course_id, date2, status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return adto;
	}

	public List<AttendanceDTO> getAttendanceByStudentAndCourse(int studentId, int courseId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<AttendanceDTO> ladto = new ArrayList<AttendanceDTO>();
		AttendanceDTO adto = null;
		try {
			String sql = "select * from attendance where student_id=? and course_id=?";
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, studentId);
			ps.setInt(2, courseId);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id2 = rs.getInt("id");
				int student_id = rs.getInt("student_id");
				int course_id = rs.getInt("course_id");
				String date = rs.getString("date");
				String status = rs.getString("status");
				adto = new AttendanceDTO(id2, student_id, course_id, date, status);
				ladto.add(adto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return ladto;
	}

	public List<AttendanceDTO> getAttendanceByCourseAndDate(int courseId, String date) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<AttendanceDTO> ladto = new ArrayList<AttendanceDTO>();
		AttendanceDTO adto = null;
		try {
			String sql = "select * from attendance where course_id=? and date=?";
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, courseId);
			ps.setString(2, date);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id2 = rs.getInt("id");
				int student_id = rs.getInt("student_id");
				int course_id = rs.getInt("course_id");
				String date2 = rs.getString("date");
				String status = rs.getString("status");
				adto = new AttendanceDTO(id2, student_id, course_id, date2, status);
				ladto.add(adto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return ladto;
	}

	public boolean updateAttendance(AttendanceDTO attendance) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "update attendance set student_id=?, course_id=?, date=?, status=? where id=?";
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);

			ps.setInt(1, attendance.getStudentId());
			ps.setInt(2, attendance.getCourseId());
			ps.setString(3, attendance.getDate());
			ps.setString(4, attendance.getStatus());
			ps.setInt(5, attendance.getId());

			int isUpdated = ps.executeUpdate();
			return isUpdated > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return false;
	}

	public boolean deleteAttendance(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "delete from attendance where id=?";
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
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

	public List<AttendanceDTO> getAllAttendance() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<AttendanceDTO> ladto = new ArrayList<AttendanceDTO>();
		AttendanceDTO adto = null;
		try {
			String sql = "select * from attendance";
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id2 = rs.getInt("id");
				int student_id = rs.getInt("student_id");
				int course_id = rs.getInt("course_id");
				String date = rs.getString("date");
				String status = rs.getString("status");
				adto = new AttendanceDTO(id2, student_id, course_id, date, status);
				ladto.add(adto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return ladto;
	}
}
