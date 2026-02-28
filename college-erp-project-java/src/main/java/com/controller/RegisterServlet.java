package com.controller;

import com.dto.UserDTO;
import com.service.UserService;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService;
	private Gson gson;

	public RegisterServlet() {
		userService = new UserService();
		gson = new Gson();
	}

	// POST → register a new user
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			BufferedReader reader = request.getReader();

			// Convert JSON body to UserDTO
			UserDTO user = gson.fromJson(reader, UserDTO.class);

			// Register user
			int userId = userService.registerUser(user);

			String json;
			if (userId > 0) {
				json = gson.toJson("User registered successfully with ID: " + userId);
				response.setStatus(HttpServletResponse.SC_CREATED);
			} else if (userService.isEmailAlreadyExists(user.getEmail())) {
				json = gson.toJson("Email already exists!");
				response.setStatus(HttpServletResponse.SC_CONFLICT);
			} else {
				json = gson.toJson("Failed to register user. Invalid data.");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}

			response.getWriter().write(json);

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			String json = gson.toJson("Error: " + e.getMessage());
			response.getWriter().write(json);
		}

	}

}