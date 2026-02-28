package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dto.StudentDTO;
import com.util.ConnectionFactory;

public class StudentDAO {
	public boolean addStudent(StudentDTO student) {
		Connection connection=null;
		PreparedStatement ps=null;
		try {
			String sql="insert into students(user_id,roll_number,department,year,phone) values(?,?,?,?,?)";
			connection=ConnectionFactory.getConnection();
			ps=connection.prepareStatement(sql);
			ps.setInt(1, student.getUserId());
			ps.setString(2, student.getRollNumber());
			ps.setString(3, student.getDepartment());
			ps.setInt(4, student.getYear());
			ps.setString(5, student.getPhone());
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
	public StudentDTO getStudentById(int id) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		StudentDTO sddt=null;
		try {
			String sql="select * from students where id=?";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				int id2=rs.getInt("id");
				int user_id=rs.getInt("user_id");
				String rollNo=rs.getString("roll_number");
				String department=rs.getString("department");
				int year=rs.getInt("year");
				String phone=rs.getString("phone");
				sddt=new StudentDTO(id2, user_id, rollNo, department, year, phone);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return sddt;
	}
	public StudentDTO getStudentByUserId(int userId) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		StudentDTO sddt=null;
		try {
			String sql="select * from students where user_id=?";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, userId);
			rs=ps.executeQuery();
			if(rs.next()) {
				int id2=rs.getInt("id");
				int user_id=rs.getInt("user_id");
				String rollNo=rs.getString("roll_number");
				String department=rs.getString("department");
				int year=rs.getInt("year");
				String phone=rs.getString("phone");
				sddt=new StudentDTO(id2, user_id, rollNo, department, year, phone);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return sddt;
	}
	public List<StudentDTO> getAllStudents() {
		List<StudentDTO> sdto=new ArrayList<StudentDTO>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String sql="select * from students";
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				StudentDTO sddt=null;
				int id2=rs.getInt("id");
				int user_id=rs.getInt("user_id");
				String rollNo=rs.getString("roll_number");
				String department=rs.getString("department");
				int year=rs.getInt("year");
				String phone=rs.getString("phone");
				sddt=new StudentDTO(id2, user_id, rollNo, department, year, phone);
				sdto.add(sddt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(con);
		}
		return sdto;
	}
	public boolean deleteStudent(int id) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			String sql="delete from students where id=?";
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
