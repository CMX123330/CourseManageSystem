package com.cmx.cms.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.dao.SemesterDao;
import com.cmx.cms.model.Semester;

@WebServlet("/semester")
public class SemesterServlet extends HttpServlet{
    private SemesterDao semesterDao = new SemesterDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            if("add".equals(action))
            {
                req.getRequestDispatcher("/WEB-INF/jsp/semester-form.jsp").forward(req, resp);
            }
            else if("edit".equals(action))
            {
                req.setAttribute("semester",semesterDao.getById(req.getParameter("id")));
                req.getRequestDispatcher("/WEB-INF/jsp/semester-form.jsp").forward(req, resp);
            }
            else
            {
                req.setAttribute("semesters",semesterDao.getAll());
                req.getRequestDispatcher("/WEB-INF/jsp/semester-list.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if("save".equals(action))
        {
            Semester s;
            s = new Semester();
            s.setSemesterId(req.getParameter("semesterId"));
            s.setName(req.getParameter("name"));
            s.setStartDate(Date.valueOf(req.getParameter("startDate")));
            s.setEndDate(Date.valueOf(req.getParameter("endDate")));
            s.setTotalWeeks(Integer.parseInt(req.getParameter("totalWeeks")));
            try {
                semesterDao.add(s);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else if("update".equals(action))
        {
            Semester s = new Semester();
            s.setSemesterId(req.getParameter("semesterId"));
            s.setName(req.getParameter("name"));
            s.setStartDate(Date.valueOf(req.getParameter("startDate")));
            s.setEndDate(Date.valueOf(req.getParameter("endDate")));
            s.setTotalWeeks(Integer.parseInt(req.getParameter("totalWeeks")));
            try {
                semesterDao.update(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if("delete".equals(action))
        {
            try {
                semesterDao.delete(req.getParameter("id"));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath()+"/semester");
    }
            
}