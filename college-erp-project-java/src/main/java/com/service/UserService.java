package com.service;

import com.dao.UserDAO;
import com.dto.UserDTO;

public class UserService {
	private UserDAO userDAO;

	public UserService() {
		userDAO = new UserDAO();
	}
	
	public UserDTO login(String email, String password) {
		if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
			return null;
		}
		UserDTO user = userDAO.login(email, password);
		if (user != null) {
			return user;
		}
		return null;
	}

	public int registerUser(UserDTO user) {

		if (user == null) {
			return 0;
		}

		if (user.getName() == null || user.getEmail() == null || user.getPassword() == null || user.getRole() == null
				|| user.getName().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()
				|| user.getRole().isEmpty()) {
			return 0;
		}

		UserDTO existingUser = userDAO.getUserByEmail(user.getEmail());

		if (existingUser != null) {
			return 0;
		}

		int userId = userDAO.createUser(user);

		return userId;
	}

	public UserDTO getUserById(int id) {
		if (id <= 0) {
			return null;
		}
		UserDTO user = userDAO.getUserById(id);
		if (user != null) {
			return user;
		}
		return null;
	}

	public UserDTO getUserByEmail(String email) {
		if (email == null || email.isEmpty()) {
			return null;
		}
		UserDTO user = userDAO.getUserByEmail(email);
		if (user != null) {
			return user;
		}
		return null;
	}

	public boolean deleteUser(int id) {
		if (id <= 0) {
			return false;
		}
		boolean isDeleted = userDAO.deleteUser(id);
		if (isDeleted) {
			return true;
		}
		return false;
	}

	public boolean isEmailAlreadyExists(String email) {
		if (email == null || email.isEmpty()) {
			return false;
		}
		UserDTO user = userDAO.getUserByEmail(email);
		if (user != null) {
			return true;
		}
		return false;
	}
}
