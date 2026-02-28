package com.controller;

import com.dto.AttendanceDTO;
import com.service.AttendanceService;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/attendance/*")
public class AttendanceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private AttendanceService attendanceService;
	private Gson gson;

	public AttendanceServlet() {

		attendanceService = new AttendanceService();

		gson = new Gson();

	}

	// GET → get attendance by various filters / get all
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {

			String path = request.getPathInfo();

			// /api/attendance/all
			if (path != null && path.equals("/all")) {

				List<AttendanceDTO> attendanceList = attendanceService.getAllAttendance();

				String json = gson.toJson(attendanceList);

				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write(json);

			}

			// /api/attendance/id?id=1
			else if (path != null && path.equals("/id")) {

				int id = Integer.parseInt(request.getParameter("id"));

				AttendanceDTO attendance = attendanceService.getAttendanceById(id);

				String json = gson.toJson(attendance);

				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write(json);

			}

			// /api/attendance/student?studentId=1
			else if (path != null && path.equals("/student")) {

				int studentId = Integer.parseInt(request.getParameter("studentId"));

				List<AttendanceDTO> attendanceList = attendanceService.getAttendanceByStudentId(studentId);

				String json = gson.toJson(attendanceList);

				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write(json);

			}

			// /api/attendance/course?courseId=1
			else if (path != null && path.equals("/course")) {

				int courseId = Integer.parseInt(request.getParameter("courseId"));

				List<AttendanceDTO> attendanceList = attendanceService.getAttendanceByCourseId(courseId);

				String json = gson.toJson(attendanceList);

				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write(json);

			}

			// /api/attendance/student-course-date?studentId=1&courseId=1&date=2026-02-27
			else if (path != null && path.equals("/student-course-date")) {

				int studentId = Integer.parseInt(request.getParameter("studentId"));
				int courseId = Integer.parseInt(request.getParameter("courseId"));
				String date = request.getParameter("date");

				AttendanceDTO attendance = attendanceService.getAttendanceByStudentCourseAndDate(studentId, courseId,
						date);

				String json = gson.toJson(attendance);

				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write(json);

			}

			// /api/attendance/student-course?studentId=1&courseId=1
			else if (path != null && path.equals("/student-course")) {

				int studentId = Integer.parseInt(request.getParameter("studentId"));
				int courseId = Integer.parseInt(request.getParameter("courseId"));

				List<AttendanceDTO> attendanceList = attendanceService.getAttendanceByStudentAndCourse(studentId,
						courseId);

				String json = gson.toJson(attendanceList);

				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write(json);

			}

			// /api/attendance/course-date?courseId=1&date=2026-02-27
			else if (path != null && path.equals("/course-date")) {

				int courseId = Integer.parseInt(request.getParameter("courseId"));
				String date = request.getParameter("date");

				List<AttendanceDTO> attendanceList = attendanceService.getAttendanceByCourseAndDate(courseId, date);

				String json = gson.toJson(attendanceList);

				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write(json);

			}

			else {

				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				String json = gson.toJson("Invalid Request");
				response.getWriter().write(json);

			}

		} catch (Exception e) {

			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			String json = gson.toJson("Error : " + e.getMessage());
			response.getWriter().write(json);

		}

	}

	// POST → add attendance
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {

			BufferedReader reader = request.getReader();
			AttendanceDTO attendance = gson.fromJson(reader, AttendanceDTO.class);

			boolean result = attendanceService.addAttendance(attendance);

			String json = gson.toJson(result ? "Attendance Added" : "Failed");

			response.setStatus(result ? HttpServletResponse.SC_CREATED : HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write(json);

		} catch (Exception e) {

			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			String json = gson.toJson("Error : " + e.getMessage());
			response.getWriter().write(json);

		}

	}

	// PUT → update attendance
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {

			BufferedReader reader = request.getReader();
			AttendanceDTO attendance = gson.fromJson(reader, AttendanceDTO.class);

			boolean result = attendanceService.updateAttendance(attendance);

			String json = gson.toJson(result ? "Attendance Updated" : "Failed");

			response.setStatus(result ? HttpServletResponse.SC_OK : HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write(json);

		} catch (Exception e) {

			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			String json = gson.toJson("Error : " + e.getMessage());
			response.getWriter().write(json);

		}

	}

	// DELETE → delete attendance by id
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {

			int id = Integer.parseInt(request.getParameter("id"));

			boolean result = attendanceService.deleteAttendance(id);

			String json = gson.toJson(result ? "Attendance Deleted" : "Failed");

			response.setStatus(result ? HttpServletResponse.SC_OK : HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write(json);

		} catch (Exception e) {

			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			String json = gson.toJson("Error : " + e.getMessage());
			response.getWriter().write(json);

		}

	}

}