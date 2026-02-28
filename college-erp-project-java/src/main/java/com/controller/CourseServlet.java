package com.controller;

import com.dto.CourseDTO;
import com.service.CourseService;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/course/*")
public class CourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CourseService courseService;
	private Gson gson;

	public CourseServlet() {

		courseService = new CourseService();

		gson = new Gson();

	}

	// GET → get all courses / by id / by code / by department
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			String path = request.getPathInfo();

			// /api/course/all
			if (path != null && path.equals("/all")) {

				List<CourseDTO> courses = courseService.getAllCourses();

				String json = gson.toJson(courses);

				response.setStatus(HttpServletResponse.SC_OK);

				response.getWriter().write(json);

			}

			// /api/course/id?id=1
			else if (path != null && path.equals("/id")) {

				int id = Integer.parseInt(request.getParameter("id"));

				CourseDTO course = courseService.getCourseById(id);

				String json = gson.toJson(course);

				response.setStatus(HttpServletResponse.SC_OK);

				response.getWriter().write(json);

			}

			// /api/course/code?courseCode=CS101
			else if (path != null && path.equals("/code")) {

				String courseCode = request.getParameter("courseCode");

				CourseDTO course = courseService.getCourseByCourseCode(courseCode);

				String json = gson.toJson(course);

				response.setStatus(HttpServletResponse.SC_OK);

				response.getWriter().write(json);

			}

			// /api/course/department?department=ComputerScience
			else if (path != null && path.equals("/department")) {

				String department = request.getParameter("department");

				List<CourseDTO> courses = courseService.getCoursesByDepartment(department);

				String json = gson.toJson(courses);

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

	// POST → add new course
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			BufferedReader reader = request.getReader();

			CourseDTO course = gson.fromJson(reader, CourseDTO.class);

			boolean result = courseService.addCourse(course);

			String json = gson.toJson(result ? "Course Added" : "Failed");

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

	// PUT → update course
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			BufferedReader reader = request.getReader();

			CourseDTO course = gson.fromJson(reader, CourseDTO.class);

			boolean result = courseService.updateCourse(course);

			String json = gson.toJson(result ? "Course Updated" : "Failed");

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

	// DELETE → delete course by id
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			int id = Integer.parseInt(request.getParameter("id"));

			boolean result = courseService.deleteCourse(id);

			String json = gson.toJson(result ? "Course Deleted" : "Failed");

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