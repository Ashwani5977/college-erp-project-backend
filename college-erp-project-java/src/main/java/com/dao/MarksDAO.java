package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dto.MarksDTO;
import com.util.ConnectionFactory;

public class MarksDAO {

	public boolean addMarks(MarksDTO marks) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into marks(student_id,course_id,marks,exam_type) values(?,?,?,?)";
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, marks.getStudentId());
			ps.setInt(2, marks.getCourseId());
			ps.setInt(3, marks.getMarks());
			ps.setString(4, marks.getExamType());
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

	public MarksDTO getMarksById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MarksDTO mdto = null;
		try {
			String sql = "select * from marks where id=?";
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				int id2 = rs.getInt("id");
				int student_id = rs.getInt("student_id");
				int course_id = rs.getInt("course_id");
				int marks = rs.getInt("marks");
				String exam_type = rs.getString("exam_type");
				mdto = new MarksDTO(id2, student_id, course_id, marks, exam_type);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(ps);
			ConnectionFactory.close(rs);
			ConnectionFactory.close(con);
		}
		return mdto;
	}

	public List<MarksDTO> getMarksByStudentId(int studentId) {
		Connection con = null;
		PreparedStatement ps = null;
		MarksDTO mdto = null;
		ResultSet rs = null;
		List<MarksDTO> lmdto = new ArrayList<MarksDTO>();
		try {
			String sql = "select * from marks where student_id=?";
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, studentId);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id2 = rs.getInt("id");
				int student_id = rs.getInt("student_id");
				int course_id = rs.getInt("course_id");
				int marks = rs.getInt("marks");
				String exam_type = rs.getString("exam_type");
				mdto = new MarksDTO(id2, student_id, course_id, marks, exam_type);
				lmdto.add(mdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(ps);
			ConnectionFactory.close(rs);
			ConnectionFactory.close(con);
		}
		return lmdto;
	}

	public List<MarksDTO> getMarksByCourseId(int courseId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<MarksDTO> lmdto = new ArrayList<MarksDTO>();
		MarksDTO mdto = null;
		try {
			String sql = "select * from marks where course_id=?";
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, courseId);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id2 = rs.getInt("id");
				int student_id = rs.getInt("student_id");
				int course_id = rs.getInt("course_id");
				int marks = rs.getInt("marks");
				String exam_type = rs.getString("exam_type");
				mdto = new MarksDTO(id2, student_id, course_id, marks, exam_type);
				lmdto.add(mdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return lmdto;
	}

	public List<MarksDTO> getMarksByStudentAndCourse(int studentId, int courseId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		MarksDTO mdto=null;
		List<MarksDTO> lmdto=new ArrayList<MarksDTO>();
		try {
			String sql="select * from marks where student_id=? and course_id=?";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, studentId);
			ps.setInt(2, courseId);
			rs=ps.executeQuery();
			while(rs.next()) {
				int id2 = rs.getInt("id");
				int student_id = rs.getInt("student_id");
				int course_id = rs.getInt("course_id");
				int marks = rs.getInt("marks");
				String exam_type = rs.getString("exam_type");
				mdto = new MarksDTO(id2, student_id, course_id, marks, exam_type);
				lmdto.add(mdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return lmdto;
	}

	public MarksDTO getMarksByStudentCourseAndExamType(int studentId, int courseId, String examType) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		MarksDTO mdto=null;
		try {
			String sql="select * from marks where student_id=? and course_id=? and exam_type=?";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, studentId);
			ps.setInt(2, courseId);
			ps.setString(3, examType);
			rs=ps.executeQuery();
			if(rs.next()) {
				int id2 = rs.getInt("id");
				int student_id = rs.getInt("student_id");
				int course_id = rs.getInt("course_id");
				int marks = rs.getInt("marks");
				String exam_type = rs.getString("exam_type");
				mdto = new MarksDTO(id2, student_id, course_id, marks, exam_type);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return mdto;
	}

	public boolean updateMarks(MarksDTO marks) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql="update marks set marks=? where student_id=? and course_id=? and exam_type=?";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, marks.getMarks());
			ps.setInt(2, marks.getStudentId());
			ps.setInt(3, marks.getCourseId());
			ps.setString(4, marks.getExamType());
			int isUpdated = ps.executeUpdate();
			return isUpdated>0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return false;
	}

	public boolean deleteMarks(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql="delete from marks where id=?";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			int isDeleted = ps.executeUpdate();
			return isDeleted>0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return false;
	}

	public List<MarksDTO> getAllMarks() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		MarksDTO mdto=null;
		List<MarksDTO> lmdto=new ArrayList<MarksDTO>();
		try {
			String sql="select * from marks";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				int id2 = rs.getInt("id");
				int student_id = rs.getInt("student_id");
				int course_id = rs.getInt("course_id");
				int marks = rs.getInt("marks");
				String exam_type = rs.getString("exam_type");
				mdto = new MarksDTO(id2, student_id, course_id, marks, exam_type);
				lmdto.add(mdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return lmdto;
	}
}
