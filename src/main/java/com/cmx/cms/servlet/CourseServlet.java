package com.cmx.cms.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.dao.CourseDao;
import com.cmx.cms.dao.DepartmentDao;
import com.cmx.cms.model.Course;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {

    private CourseDao courseDao = new CourseDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            // 新增表单：查院系列表给下拉用
            try {
                req.setAttribute("departments", new DepartmentDao().getAll());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            req.getRequestDispatcher("/WEB-INF/jsp/course-form.jsp").forward(req, resp);
        } else if ("edit".equals(action)) {
            try {
                // 编辑表单：回填课程 + 院系列表
                req.setAttribute("course", courseDao.getById(req.getParameter("id")));
                req.setAttribute("departments", new DepartmentDao().getAll());
                req.getRequestDispatcher("/WEB-INF/jsp/course-form.jsp").forward(req, resp);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                // 列表：全部课程
                req.setAttribute("courses", courseDao.getAll());
                req.getRequestDispatcher("/WEB-INF/jsp/course-list.jsp").forward(req, resp);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("save".equals(action)) {
            Course c = new Course();
            c.setCourseId(req.getParameter("courseId"));
            c.setName(req.getParameter("name"));
            c.setHours(Integer.parseInt(req.getParameter("hours")));
            c.setExamType(req.getParameter("examType"));
            c.setNature(req.getParameter("nature"));
            c.setCredit(new BigDecimal(req.getParameter("credit")));
            c.setDepartmentId(req.getParameter("departmentId"));
            try {
                courseDao.add(c);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("update".equals(action)) {
            Course c = new Course();
            c.setCourseId(req.getParameter("courseId"));
            c.setName(req.getParameter("name"));
            c.setHours(Integer.parseInt(req.getParameter("hours")));
            c.setExamType(req.getParameter("examType"));
            c.setNature(req.getParameter("nature"));
            c.setCredit(new BigDecimal(req.getParameter("credit")));
            c.setDepartmentId(req.getParameter("departmentId"));
            try {
                courseDao.update(c);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("delete".equals(action)) {
            try {
                courseDao.delete(req.getParameter("id"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath() + "/course");
    }

}
