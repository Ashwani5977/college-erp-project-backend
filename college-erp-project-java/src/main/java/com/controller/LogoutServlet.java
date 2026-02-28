package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import com.google.gson.Gson;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Gson gson;

    public LogoutServlet() {
        super();
        gson = new Gson();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false); // don't create if not exists

        if (session != null) {
            session.invalidate(); // destroy session
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(gson.toJson("Logged Out Successfully"));
    }
}