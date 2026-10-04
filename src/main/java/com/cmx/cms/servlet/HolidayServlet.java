package com.cmx.cms.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.dao.HolidayDao;
import com.cmx.cms.model.Holiday;

@WebServlet("/holiday")
public class HolidayServlet extends HttpServlet {

    private HolidayDao holidayDao = new HolidayDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("add".equals(action)) {
                req.getRequestDispatcher("/WEB-INF/jsp/holiday-form.jsp").forward(req, resp);
            } else {
                req.setAttribute("holidays", holidayDao.getAll());
                req.getRequestDispatcher("/WEB-INF/jsp/holiday-list.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        try {
            if ("save".equals(action)) {
                Holiday h = new Holiday();
                h.setHolidayId(req.getParameter("holidayId"));
                h.setHolidayDate(Date.valueOf(req.getParameter("holidayDate")));
                h.setName(req.getParameter("name"));
                holidayDao.add(h);
            } else if ("delete".equals(action)) {
                holidayDao.delete(req.getParameter("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/holiday");
    }
}
