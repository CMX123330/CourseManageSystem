package com.cmx.cms.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.dao.OfferingDao;
import com.cmx.cms.dao.SemesterDao;
import com.cmx.cms.model.Offering;

@WebServlet("/offering")
public class OfferingServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            try {
                req.setAttribute("Offerings", new OfferingDao().getAll());
                req.getRequestDispatcher("/WEB-INF/jsp/offfering.jsp").forward(req, resp);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        } else if ("edit".equals(action)) {
            req.setAttribute("", action);
        } else {
            try {
                String semesterId = req.getParameter("semesterId");
                req.setAttribute("offerings", new OfferingDao().getViewList(semesterId));
                req.setAttribute("semesters", new SemesterDao().getAll());
                req.getRequestDispatcher("/WEB-INF/jsp/offering-list.jsp").forward(req, resp);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String Id = req.getParameter("Id");
        req.setCharacterEncoding("UTF-8");
        if("update".equals(action))
        {
            Offering offering = new Offering();
            offering.setCourseId(req.getParameter("courseId"));
            offering.setOfferingId(req.getParameter("offeringId"));
            offering.setSemesterId(req.getParameter("semesterId"));
            offering.setTeacherId(req.getParameter("teacherId"));
            offering.setWeeklyHours(req.getParameter("weeklyHours"));
            try {
                new OfferingDao().add(offering);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else if ("delete".equals(action)) {
            try {
                new OfferingDao().delete(Id);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
