package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dto.StudentDTO;
import com.dto.TeacherDTO;
import com.util.ConnectionFactory;

public class TeacherDAO {
	public boolean addTeacher(TeacherDTO teacher) {
		Connection connection=null;
		PreparedStatement ps=null;
		try {
			String sql="insert into teachers(user_id,employee_id,department,phone) values(?,?,?,?)";
			connection=ConnectionFactory.getConnection();
			ps=connection.prepareStatement(sql);
			ps.setInt(1, teacher.getUserId());
			ps.setString(2, teacher.getEmployeeId());
			ps.setString(3, teacher.getDepartment());
			ps.setString(4, teacher.getPhone());
			int isAdded = ps.executeUpdate();
			return isAdded>0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(ps);
			ConnectionFactory.close(connection);
		}
		return false;
	}
	public TeacherDTO getTeacherById(int id) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		TeacherDTO tdto=null;
		try {
			String sql="select * from teachers where id=?";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				int id2=rs.getInt("id");
				int user_id=rs.getInt("user_id");
				String employee_id=rs.getString("employee_id");
				String department=rs.getString("department");
				String phone=rs.getString("phone");
				tdto=new TeacherDTO(id2, user_id, employee_id, department, phone);
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
	public TeacherDTO getTeacherByUserId(int userId) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		TeacherDTO tdto=null;
		try {
			String sql="select * from teachers where user_id=?";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, userId);
			rs=ps.executeQuery();
			if(rs.next()) {
				int id2=rs.getInt("id");
				int user_id=rs.getInt("user_id");
				String employee_id=rs.getString("employee_id");
				String department=rs.getString("department");
				String phone=rs.getString("phone");
				tdto=new TeacherDTO(id2, user_id, employee_id, department, phone);
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
	public List<TeacherDTO> getAllTeachers() {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		TeacherDTO tdto=null;
		List<TeacherDTO> tddt=new ArrayList<TeacherDTO>();
		try {
			String sql="select * from teachers";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				int id2=rs.getInt("id");
				int user_id=rs.getInt("user_id");
				String employee_id=rs.getString("employee_id");
				String department=rs.getString("department");
				String phone=rs.getString("phone");
				tdto=new TeacherDTO(id2, user_id, employee_id, department, phone);
				tddt.add(tdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return tddt;
	}
	public boolean deleteTeacher(int id) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			String sql="delete from teachers where id=?";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
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
}
