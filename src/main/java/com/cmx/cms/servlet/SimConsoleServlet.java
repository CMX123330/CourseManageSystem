package com.cmx.cms.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.service.SimulationEngine;
@WebServlet("/simConsole")
public class SimConsoleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("state", SimulationEngine.getInstance().getState());
            req.setAttribute("stats", SimulationEngine.getInstance().getStats());
            req.getRequestDispatcher("/WEB-INF/jsp/sim-console.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String cmd = req.getParameter("cmd");
        try {
            if ("start".equals(cmd)) {
                SimulationEngine.getInstance().startSemester();
            } else if ("advance".equals(cmd)) {
                SimulationEngine.getInstance().tick();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/simConsole");
    }
}
