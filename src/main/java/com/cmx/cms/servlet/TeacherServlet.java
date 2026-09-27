package com.cmx.cms.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.dao.DepartmentDao;
import com.cmx.cms.dao.TeacherDao;
import com.cmx.cms.model.Teacher;
@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet{
       @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String Id = req.getParameter("id");
        if ("add".equals(action)) {
            try {
                req.setAttribute("departments", new DepartmentDao().getAll());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            req.getRequestDispatcher("/WEB-INF/jsp/teacher-form.jsp").forward(req, resp);
        }
        else if("edit".equals(action))
        {
            try {
                req.setAttribute("departments", new DepartmentDao().getAll());
                req.setAttribute("teacher", new TeacherDao().getById(Id));
                req.getRequestDispatcher("/WEB-INF/jsp/teacher-form.jsp").forward(req, resp);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        else{
            try {
                List<Teacher> teachers = new TeacherDao().getAll();
                req.setAttribute("teachers", teachers);
                req.getRequestDispatcher("/WEB-INF/jsp/teacher-list.jsp").forward(req, resp);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }            
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String Id = req.getParameter("id");
        req.setCharacterEncoding("UTF-8");
        TeacherDao tDao = new TeacherDao();
        if ("update".equals(action)) {            
            Teacher t = new Teacher();
            t.setTeacherId(req.getParameter("teacherId"));
            t.setName(req.getParameter("name"));
            t.setGender(req.getParameter("gender"));
            t.setTitle(req.getParameter("title"));
            t.setPhone(req.getParameter("phone"));
            t.setDepartmentId(req.getParameter("departmentId"));
            try {
                tDao.update(t);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else if("save".equals(action))
        {
            Teacher t = new Teacher();
            t.setTeacherId(req.getParameter("teacherId"));
            t.setName(req.getParameter("name"));
            t.setGender(req.getParameter("gender"));
            t.setTitle(req.getParameter("title"));
            t.setPhone(req.getParameter("phone"));
            t.setDepartmentId(req.getParameter("departmentId"));
            try {
                tDao.add(t);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else if("delete".equals(action))
        {
            try {
                tDao.delete(Id);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath()+"/teacher");
    }
}
