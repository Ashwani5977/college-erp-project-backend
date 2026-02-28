package com.controller;

import com.dto.StudentDTO;
import com.service.StudentService;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/student/*")
public class StudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private StudentService studentService;
	private Gson gson;

	public StudentServlet() {

		studentService = new StudentService();

		gson = new Gson();

	}

	// GET → get all students / get student by id / get student by userId
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			String path = request.getPathInfo();

			// /api/student/all
			if (path != null && path.equals("/all")) {

				List<StudentDTO> students = studentService.getAllStudents();

				String json = gson.toJson(students);

				response.setStatus(HttpServletResponse.SC_OK);

				response.getWriter().write(json);

			}

			// /api/student/id?id=1
			else if (path != null && path.equals("/id")) {

				int id = Integer.parseInt(request.getParameter("id"));

				StudentDTO student = studentService.getStudentById(id);

				String json = gson.toJson(student);

				response.setStatus(HttpServletResponse.SC_OK);

				response.getWriter().write(json);

			}

			// /api/student/user?userId=1
			else if (path != null && path.equals("/user")) {

				int userId = Integer.parseInt(request.getParameter("userId"));

				StudentDTO student = studentService.getStudentByUserId(userId);

				String json = gson.toJson(student);

				response.setStatus(HttpServletResponse.SC_OK);

				response.getWriter().write(json);

			}

			else {

				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

				String json = gson.toJson("Invalid Request");

				response.getWriter().write(json);

			}

		}

		catch (Exception e) {

			e.printStackTrace();

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

			String json = gson.toJson("Error : " + e.getMessage());

			response.getWriter().write(json);

		}

	}

	// POST → add new student
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			BufferedReader reader = request.getReader();

			StudentDTO student = gson.fromJson(reader, StudentDTO.class);

			boolean result = studentService.addStudent(student);

			String json = gson.toJson(result ? "Student Added" : "Failed");

			response.setStatus(result ? HttpServletResponse.SC_CREATED : HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

			response.getWriter().write(json);

		}

		catch (Exception e) {

			e.printStackTrace();

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

			String json = gson.toJson("Error : " + e.getMessage());

			response.getWriter().write(json);

		}

	}

	// DELETE → delete student by id
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			int id = Integer.parseInt(request.getParameter("id"));

			boolean result = studentService.deleteStudent(id);

			String json = gson.toJson(result ? "Student Deleted" : "Failed");

			response.setStatus(result ? HttpServletResponse.SC_OK : HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

			response.getWriter().write(json);

		}

		catch (Exception e) {

			e.printStackTrace();

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

			String json = gson.toJson("Error : " + e.getMessage());

			response.getWriter().write(json);

		}

	}

}