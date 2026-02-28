package com.controller;

import com.dto.MarksDTO;
import com.service.MarksService;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/marks/*")
public class MarksServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private MarksService marksService;
	private Gson gson;

	public MarksServlet() {

		marksService = new MarksService();

		gson = new Gson();

	}

	// GET → get all marks / by id / by student / by course / by student & course /
	// by student, course & examType
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			String path = request.getPathInfo();

			// /api/marks/all
			if (path != null && path.equals("/all")) {

				List<MarksDTO> marksList = marksService.getAllMarks();

				String json = gson.toJson(marksList);

				response.setStatus(HttpServletResponse.SC_OK);

				response.getWriter().write(json);

			}

			// /api/marks/id?id=1
			else if (path != null && path.equals("/id")) {

				int id = Integer.parseInt(request.getParameter("id"));

				MarksDTO marks = marksService.getMarksById(id);

				String json = gson.toJson(marks);

				response.setStatus(HttpServletResponse.SC_OK);

				response.getWriter().write(json);

			}

			// /api/marks/student?studentId=1
			else if (path != null && path.equals("/student")) {

				int studentId = Integer.parseInt(request.getParameter("studentId"));

				List<MarksDTO> marksList = marksService.getMarksByStudentId(studentId);

				String json = gson.toJson(marksList);

				response.setStatus(HttpServletResponse.SC_OK);

				response.getWriter().write(json);

			}

			// /api/marks/course?courseId=1
			else if (path != null && path.equals("/course")) {

				int courseId = Integer.parseInt(request.getParameter("courseId"));

				List<MarksDTO> marksList = marksService.getMarksByCourseId(courseId);

				String json = gson.toJson(marksList);

				response.setStatus(HttpServletResponse.SC_OK);

				response.getWriter().write(json);

			}

			// /api/marks/student-course?studentId=1&courseId=2
			else if (path != null && path.equals("/student-course")) {

				int studentId = Integer.parseInt(request.getParameter("studentId"));
				int courseId = Integer.parseInt(request.getParameter("courseId"));

				List<MarksDTO> marksList = marksService.getMarksByStudentAndCourse(studentId, courseId);

				String json = gson.toJson(marksList);

				response.setStatus(HttpServletResponse.SC_OK);

				response.getWriter().write(json);

			}

			// /api/marks/student-course-exam?studentId=1&courseId=2&examType=Midterm
			else if (path != null && path.equals("/student-course-exam")) {

				int studentId = Integer.parseInt(request.getParameter("studentId"));
				int courseId = Integer.parseInt(request.getParameter("courseId"));
				String examType = request.getParameter("examType");

				MarksDTO marks = marksService.getMarksByStudentCourseAndExamType(studentId, courseId, examType);

				String json = gson.toJson(marks);

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

	// POST → add new marks
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			BufferedReader reader = request.getReader();

			MarksDTO marks = gson.fromJson(reader, MarksDTO.class);

			boolean result = marksService.addMarks(marks);

			String json = gson.toJson(result ? "Marks Added" : "Failed");

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

	// PUT → update marks
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			BufferedReader reader = request.getReader();

			MarksDTO marks = gson.fromJson(reader, MarksDTO.class);

			boolean result = marksService.updateMarks(marks);

			String json = gson.toJson(result ? "Marks Updated" : "Failed");

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

	// DELETE → delete marks by id
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.setCharacterEncoding("UTF-8");

		try {

			int id = Integer.parseInt(request.getParameter("id"));

			boolean result = marksService.deleteMarks(id);

			String json = gson.toJson(result ? "Marks Deleted" : "Failed");

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