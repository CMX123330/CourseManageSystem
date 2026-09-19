package com.cmx.cms.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.dao.ClassDao;
import com.cmx.cms.model.Clazz;

@WebServlet("/class")
public class ClassServlet extends HttpServlet {

    private ClassDao classDao = new ClassDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/jsp/class-form.jsp").forward(req, resp);
        } else if ("edit".equals(action)) {
            Clazz clazz = new Clazz();
            String id = req.getParameter("id");
            try {
                clazz = classDao.getById(id);
                req.setAttribute("clazz", clazz);
                req.getRequestDispatcher("/WEB-INF/jsp/class-form.jsp").forward(req, resp);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                List<Clazz> classs = classDao.getAll();
                req.setAttribute("classs", classs);
                req.getRequestDispatcher("/WEB-INF/jsp/class-list.jsp").forward(req, resp);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    // save/update/delete
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if("save".equals(action))
        {
            Clazz c = new Clazz();
            c.setClassId(req.getParameter("classId"));
            c.setName(req.getParameter("name"));
            c.setMajorId((req.getParameter("majorId")));
            c.setGrade(Integer.parseInt(req.getParameter("grade")));
            c.setStudentCount(Integer.parseInt(req.getParameter("studentCount")));
            try {
                classDao.add(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if("update".equals(action))
        {
            Clazz c = new Clazz();
            c.setClassId(req.getParameter("classId"));
            c.setName(req.getParameter("name"));
            c.setMajorId((req.getParameter("majorId")));
            c.setGrade(Integer.parseInt(req.getParameter("grade")));
            c.setStudentCount(Integer.parseInt(req.getParameter("studentCount")));
            try {
                classDao.update(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if("delete".equals(action))
        {
            try {
                classDao.delete(req.getParameter("id"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath() + "/class");
    }

}
