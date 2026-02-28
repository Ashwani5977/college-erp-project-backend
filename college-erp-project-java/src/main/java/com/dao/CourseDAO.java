package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dto.CourseDTO;
import com.util.ConnectionFactory;

public class CourseDAO {
	public boolean addCourse(CourseDTO course) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql="insert into courses(course_name,course_code,department) values(?,?,?)";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, course.getCourseName());
			ps.setString(2, course.getCourseCode());
			ps.setString(3, course.getDepartment());
			int isAdded = ps.executeUpdate();
			return isAdded>0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return false;
	}

	public CourseDTO getCourseById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		CourseDTO cdto=null;
		ResultSet rs=null;
		try {
			String sql="select * from courses where id=?";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				int id2=rs.getInt("id");
				String course_name=rs.getString("course_name");
				String course_code=rs.getString("course_code");
				String department=rs.getString("department");
				cdto=new CourseDTO(id2, course_name, course_code, department);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return cdto;
	}

	public CourseDTO getCourseByCourseCode(String courseCode) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		CourseDTO cdto=null;
		try {
			String sql="select * from courses where course_code=?";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, courseCode);
			rs=ps.executeQuery();
			if(rs.next()) {
				int id2=rs.getInt("id");
				String course_name=rs.getString("course_name");
				String course_code=rs.getString("course_code");
				String department=rs.getString("department");
				cdto=new CourseDTO(id2, course_name, course_code, department);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return cdto;
	}

	public List<CourseDTO> getAllCourses() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		CourseDTO cdto=null;
		List<CourseDTO> lcdto=new ArrayList<CourseDTO>();
		try {
			String sql="select * from courses";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				int id2=rs.getInt("id");
				String course_name=rs.getString("course_name");
				String course_code=rs.getString("course_code");
				String department=rs.getString("department");
				cdto=new CourseDTO(id2, course_name, course_code, department);
				lcdto.add(cdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return lcdto;
	}

	public boolean updateCourse(CourseDTO course) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql="update courses set course_name=?, course_code=?, department=? where id=?";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, course.getCourseName());
			ps.setString(2, course.getCourseCode());
			ps.setString(3, course.getDepartment());
			ps.setInt(4, course.getId());
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

	public boolean deleteCourse(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql="delete from courses where id=?";
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

	public List<CourseDTO> getCoursesByDepartment(String department) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		CourseDTO cdto=null;
		List<CourseDTO> lcdto=new ArrayList<CourseDTO>();
		try {
			String sql="select * from courses where department=?";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, department);
			rs=ps.executeQuery();
			while (rs.next()) {
				int id2=rs.getInt("id");
				String course_name=rs.getString("course_name");
				String course_code=rs.getString("course_code");
				String department2=rs.getString("department");
				cdto=new CourseDTO(id2, course_name, course_code, department2);
				lcdto.add(cdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return lcdto;
	}
}
