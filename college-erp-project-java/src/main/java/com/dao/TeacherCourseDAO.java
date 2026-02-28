package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dto.TeacherCourseDTO;
import com.util.ConnectionFactory;

public class TeacherCourseDAO {
	public boolean assignCourseToTeacher(int teacherId,int courseId) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			String sql="insert into teacher_course(teacher_id,course_id) values(?,?)";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, teacherId);
			ps.setInt(2, courseId);
			int isAssigned = ps.executeUpdate();
			return isAssigned>0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return false;
	}
	public List<TeacherCourseDTO> getCoursesByTeacherId(int teacherId) {
		List<TeacherCourseDTO> tcdt=new ArrayList<TeacherCourseDTO>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		TeacherCourseDTO tcdto=null;
		try {
			String sql="select * from teacher_course where teacher_id=?";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, teacherId);
			rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				int teacher_id=rs.getInt("teacher_id");
				int course_id=rs.getInt("course_id");
				tcdto=new TeacherCourseDTO(id, teacher_id, course_id);
				tcdt.add(tcdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return tcdt;
	}
	public List<TeacherCourseDTO> getTeachersByCourseId(int courseId) {
		List<TeacherCourseDTO> tcdt=new ArrayList<TeacherCourseDTO>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		TeacherCourseDTO tcdto=null;
		try {
			String sql="select * from teacher_course where course_id=?";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, courseId);
			rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				int teacher_id=rs.getInt("teacher_id");
				int course_id=rs.getInt("course_id");
				tcdto=new TeacherCourseDTO(id, teacher_id, course_id);
				tcdt.add(tcdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return tcdt;
	}
	public boolean deleteAssignment(int teacherId,int courseId) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			String sql="delete from teacher_course where teacher_id=? and course_id=?";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, teacherId);
			ps.setInt(2, courseId);
			int isDeleted = ps.executeUpdate();
			return isDeleted>0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return false;
	}
	public List<TeacherCourseDTO> getAllAssignments() {
		List<TeacherCourseDTO> tdto=new ArrayList<TeacherCourseDTO>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		TeacherCourseDTO tdt=null;
		try {
			String sql="select * from teacher_course";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				int teacher_id=rs.getInt("teacher_id");
				int course_id=rs.getInt("course_id");
				tdt=new TeacherCourseDTO(id, teacher_id, course_id);
				tdto.add(tdt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return tdto;
	}
}
