package com.controller;

import com.dto.TeacherCourseDTO;
import com.service.TeacherCourseService;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/teacher-course/*")
public class TeacherCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private TeacherCourseService teacherCourseService;
	private Gson gson;

	public TeacherCourseServlet() {

		teacherCourseService = new TeacherCourseService();

		gson = new Gson();

	}

	// GET → get all assignments / get courses by teacherId / get teachers by
	// courseId
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			String path = request.getPathInfo();

			// /api/teacher-course/all
			if (path != null && path.equals("/all")) {

				List<TeacherCourseDTO> assignments = teacherCourseService.getAllAssignments();

				String json = gson.toJson(assignments);

				response.setStatus(HttpServletResponse.SC_OK);

				response.getWriter().write(json);

			}

			// /api/teacher-course/teacher?teacherId=1
			else if (path != null && path.equals("/teacher")) {

				int teacherId = Integer.parseInt(request.getParameter("teacherId"));

				List<TeacherCourseDTO> courses = teacherCourseService.getCoursesByTeacherId(teacherId);

				String json = gson.toJson(courses);

				response.setStatus(HttpServletResponse.SC_OK);

				response.getWriter().write(json);

			}

			// /api/teacher-course/course?courseId=1
			else if (path != null && path.equals("/course")) {

				int courseId = Integer.parseInt(request.getParameter("courseId"));

				List<TeacherCourseDTO> teachers = teacherCourseService.getTeachersByCourseId(courseId);

				String json = gson.toJson(teachers);

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

	// POST → assign course to teacher
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			BufferedReader reader = request.getReader();

			TeacherCourseDTO assignment = gson.fromJson(reader, TeacherCourseDTO.class);

			boolean result = teacherCourseService.assignCourseToTeacher(assignment.getTeacherId(),
					assignment.getCourseId());

			String json = gson.toJson(result ? "Assignment Added" : "Failed");

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

	// DELETE → delete assignment
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			int teacherId = Integer.parseInt(request.getParameter("teacherId"));

			int courseId = Integer.parseInt(request.getParameter("courseId"));

			boolean result = teacherCourseService.deleteAssignment(teacherId, courseId);

			String json = gson.toJson(result ? "Assignment Deleted" : "Failed");

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