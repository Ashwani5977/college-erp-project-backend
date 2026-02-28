package com.controller;

import com.dto.TeacherDTO;
import com.service.TeacherService;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/teacher/*")
public class TeacherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private TeacherService teacherService;
	private Gson gson;

	public TeacherServlet() {

		teacherService = new TeacherService();

		gson = new Gson();

	}

	// GET → get teacher by id / get teacher by userId / get all teachers
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			String path = request.getPathInfo();

			// /api/teacher/all
			if (path != null && path.equals("/all")) {

				List<TeacherDTO> teachers = teacherService.getAllTeachers();

				String json = gson.toJson(teachers);

				response.setStatus(HttpServletResponse.SC_OK);

				response.getWriter().write(json);

			}

			// /api/teacher/user?userId=5
			else if (path != null && path.equals("/user")) {

				int userId = Integer.parseInt(request.getParameter("userId"));

				TeacherDTO teacher = teacherService.getTeacherByUserId(userId);

				String json = gson.toJson(teacher);

				response.setStatus(HttpServletResponse.SC_OK);

				response.getWriter().write(json);

			}

			// /api/teacher?id=1
			else {

				int id = Integer.parseInt(request.getParameter("id"));

				TeacherDTO teacher = teacherService.getTeacherById(id);

				String json = gson.toJson(teacher);

				response.setStatus(HttpServletResponse.SC_OK);

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

	// POST → add teacher
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			BufferedReader reader = request.getReader();

			TeacherDTO teacher = gson.fromJson(reader, TeacherDTO.class);

			boolean result = teacherService.addTeacher(teacher);

			String json = gson.toJson(result ? "Teacher Added" : "Failed");

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

	// DELETE → delete teacher
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			int id = Integer.parseInt(request.getParameter("id"));

			boolean result = teacherService.deleteTeacher(id);

			String json = gson.toJson(result ? "Teacher Deleted" : "Failed");

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