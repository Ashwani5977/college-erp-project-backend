package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dto.UserDTO;
import com.util.ConnectionFactory;

public class UserDAO {
	public UserDTO login(String email, String password) {
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		UserDTO udt=null;
		try {
			String sql="select * from users where email=? and password=?";
			connection=ConnectionFactory.getConnection();
			ps=connection.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			rs=ps.executeQuery();
			if(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String email2=rs.getString("email");
				String password2=rs.getString("password");
				String role=rs.getString("role");
				udt=new UserDTO(id, name, email2, password2, role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(connection);
		}
		return udt;
	}

	public UserDTO getUserByEmail(String email) {
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		UserDTO udt=null;
		try {
			String sql="select * from users where email=?";
			connection=ConnectionFactory.getConnection();
			ps=connection.prepareStatement(sql);
			ps.setString(1, email);
			rs=ps.executeQuery();
			if(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String email2=rs.getString("email");
				String password2=rs.getString("password");
				String role=rs.getString("role");
				udt=new UserDTO(id, name, email2, password2, role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(connection);
		}
		return udt;
	}

	public int createUser(UserDTO user) {
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String sql="insert into users(name,email,password,role) values(?,?,?,?)";
			connection=ConnectionFactory.getConnection();
			ps=connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getRole());
			int isInserted = ps.executeUpdate();
			if(isInserted>0) {
				rs=ps.getGeneratedKeys();
				if(rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(connection);
		}
		return 0;
	}

	public UserDTO getUserById(int id) {
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		UserDTO udt=null;
		try {
			String sql="select * from users where id=?";
			connection=ConnectionFactory.getConnection();
			ps=connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				int id2=rs.getInt("id");
				String name=rs.getString("name");
				String email2=rs.getString("email");
				String password2=rs.getString("password");
				String role=rs.getString("role");
				udt=new UserDTO(id2, name, email2, password2, role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(ps);
			ConnectionFactory.close(connection);
		}
		return udt;
	}

	public boolean deleteUser(int id) {
		Connection connection=null;
		PreparedStatement ps=null;
		try {
			String sql="delete from users where id=?";
			connection=ConnectionFactory.getConnection();
			ps=connection.prepareStatement(sql);
			ps.setInt(1, id);
			int isDeleted = ps.executeUpdate();
			return isDeleted>0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(ps);
			ConnectionFactory.close(connection);
		}
		return false;
	}
}
