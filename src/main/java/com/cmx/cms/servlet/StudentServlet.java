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
import com.cmx.cms.dao.StudentDao;
import com.cmx.cms.model.Student;

@WebServlet("/student")
public class StudentServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String Id = req.getParameter("id");
        if ("add".equals(action)) {
            try {
                req.setAttribute("clazzs", new ClassDao().getAll());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            req.getRequestDispatcher("/WEB-INF/jsp/student-form.jsp").forward(req, resp);
        }
        else if("edit".equals(action))
        {
            try {
                req.setAttribute("clazzs", new ClassDao().getAll());
                req.setAttribute("student", new StudentDao().getById(Id));
                req.getRequestDispatcher("/WEB-INF/jsp/student-form.jsp").forward(req, resp);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        else{
            try {
                List<Student> students = new StudentDao().getAll();
                req.setAttribute("students", students);
                req.getRequestDispatcher("/WEB-INF/jsp/student-list.jsp").forward(req, resp);
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
        StudentDao sDao = new StudentDao();
        if ("update".equals(action)) {            
            Student s = new Student();
            s.setStudentId(req.getParameter("studentId"));
            s.setName(req.getParameter("name"));
            s.setGender(req.getParameter("gender"));
            s.setClassId(req.getParameter("classId"));
            s.setPhone(req.getParameter("phone"));
            try {
                sDao.update(s);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else if("save".equals(action))
        {
            Student s = new Student();
            s.setStudentId(req.getParameter("studentId"));
            s.setName(req.getParameter("name"));
            s.setGender(req.getParameter("gender"));
            s.setClassId(req.getParameter("classId"));
            s.setPhone(req.getParameter("phone"));
            try {
                sDao.add(s);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else if("delete".equals(action))
        {
            try {
                sDao.delete(Id);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath()+"/student");
    }
}
