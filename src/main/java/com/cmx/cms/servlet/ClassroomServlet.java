package com.cmx.cms.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.dao.ClassroomDao;
import com.cmx.cms.model.Classroom;

@WebServlet("/classroom")
public class ClassroomServlet extends HttpServlet{
    private ClassroomDao classroomDao = new ClassroomDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            if("add".equals(action))
            {
                req.getRequestDispatcher("/WEB-INF/jsp/classroom-form.jsp").forward(req, resp);
            }
            else if("edit".equals(action))
            {
                req.setAttribute("classroom",classroomDao.getById(req.getParameter("id")));
                req.getRequestDispatcher("/WEB-INF/jsp/classroom-form.jsp").forward(req, resp);
            }
            else
            {
                req.setAttribute("classrooms",classroomDao.getAll());
                req.getRequestDispatcher("/WEB-INF/jsp/classroom-list.jsp").forward(req, resp);
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
            Classroom c;
            c = new Classroom();
            c.setClassroomId(req.getParameter("classroomId"));
            c.setBuilding(req.getParameter("building"));
            c.setCapacity(Integer.parseInt(req.getParameter("capacity")));
            try {
                classroomDao.add(c);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else if("update".equals(action))
        {
            Classroom c = new Classroom();
            c.setClassroomId(req.getParameter("classroomId"));
            c.setBuilding(req.getParameter("building"));
            c.setCapacity(Integer.parseInt(req.getParameter("capacity")));
            try {
                classroomDao.update(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if("delete".equals(action))
        {
            try {
                classroomDao.delete(req.getParameter("id"));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath()+"/classroom");
    }
            
}