package com.controller;

import com.service.UserService;
import com.dto.UserDTO;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService userService;
	private Gson gson;

	public LoginServlet() {
		super();
		userService = new UserService();
		gson = new Gson();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			// Read request body
			BufferedReader reader = request.getReader();
			UserDTO loginRequest = gson.fromJson(reader, UserDTO.class);

			// Authenticate user
			UserDTO user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());

			if (user != null) {

				// ✅ Store user in session (unchanged)
				HttpSession session = request.getSession();
				session.setAttribute("user", user);

				// 🔐 OPTIONAL SAFETY: remove password before sending to frontend
				user.setPassword(null);

				// ✅ Return FULL USER OBJECT instead of string
				String json = gson.toJson(user);

				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write(json);

			} else {

				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

				response.getWriter().write(gson.toJson("Invalid Email or Password"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write(gson.toJson("Error: " + e.getMessage()));
		}
	}
}