package com.cmx.cms.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.dao.StudentOfferingDao;
import com.cmx.cms.model.StudentOffering;
import com.cmx.cms.model.User;

@WebServlet("/courseSelect")
public class CourseSelectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        User u = (User) req.getSession().getAttribute("user");
        String studentId = u.getUserId();
        try {
            req.setAttribute("myList", new StudentOfferingDao().getMyList(studentId));
            req.setAttribute("available", new StudentOfferingDao().getAvailable(studentId, "2025-2026-1"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/jsp/course-select.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String action = req.getParameter("action");
        User u = (User) req.getSession().getAttribute("user");
        String studentId = u.getUserId();
        StudentOfferingDao dao = new StudentOfferingDao();
        if ("select".equals(action)) {
            try {
                if (dao.isValidOffering(req.getParameter("offeringId"), "2025-2026-1"))
                {
                    StudentOffering so = new StudentOffering();
                    so.setStudentId(studentId);
                    so.setOfferingId(req.getParameter("offeringId"));
                    try {
                        dao.add(so);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } 
                }
                else{
                    req.setAttribute("error", "写入失败");
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if ("drop".equals(action)) {
            try {
                dao.delete(studentId, req.getParameter("offeringId"));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        }
        resp.sendRedirect(req.getContextPath() + "/courseSelect"); // PRG 刷新列表

    }
}
