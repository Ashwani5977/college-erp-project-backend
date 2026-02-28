package com.controller;

import com.dto.StudentCourseDTO;
import com.service.StudentCourseService;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/student-course/*")
public class StudentCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private StudentCourseService studentCourseService;
	private Gson gson;

	public StudentCourseServlet() {

		studentCourseService = new StudentCourseService();

		gson = new Gson();

	}

	// GET → get all enrollments / get courses by studentId / get students by
	// courseId
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			String path = request.getPathInfo();

			// /api/student-course/all
			if (path != null && path.equals("/all")) {

				List<StudentCourseDTO> enrollments = studentCourseService.getAllEnrollments();

				String json = gson.toJson(enrollments);

				response.setStatus(HttpServletResponse.SC_OK);

				response.getWriter().write(json);

			}

			// /api/student-course/student?studentId=1
			else if (path != null && path.equals("/student")) {

				int studentId = Integer.parseInt(request.getParameter("studentId"));

				List<StudentCourseDTO> courses = studentCourseService.getCoursesByStudentId(studentId);

				String json = gson.toJson(courses);

				response.setStatus(HttpServletResponse.SC_OK);

				response.getWriter().write(json);

			}

			// /api/student-course/course?courseId=1
			else if (path != null && path.equals("/course")) {

				int courseId = Integer.parseInt(request.getParameter("courseId"));

				List<StudentCourseDTO> students = studentCourseService.getStudentsByCourseId(courseId);

				String json = gson.toJson(students);

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

	// POST → enroll student to course
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			BufferedReader reader = request.getReader();

			StudentCourseDTO enrollment = gson.fromJson(reader, StudentCourseDTO.class);

			boolean result = studentCourseService.enrollStudentToCourse(enrollment.getStudentId(),
					enrollment.getCourseId());

			String json = gson.toJson(result ? "Enrollment Added" : "Failed");

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

	// DELETE → remove enrollment
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			int studentId = Integer.parseInt(request.getParameter("studentId"));

			int courseId = Integer.parseInt(request.getParameter("courseId"));

			boolean result = studentCourseService.deleteEnrollment(studentId, courseId);

			String json = gson.toJson(result ? "Enrollment Deleted" : "Failed");

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