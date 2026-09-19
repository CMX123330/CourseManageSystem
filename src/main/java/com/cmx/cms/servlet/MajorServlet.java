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
import com.cmx.cms.dao.MajorDao;
import com.cmx.cms.model.Major;

@WebServlet("/major")
public class MajorServlet extends HttpServlet {

    private MajorDao majorDao = new MajorDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            try {
                req.setAttribute("departments", new DepartmentDao().getAll());
                req.getRequestDispatcher("/WEB-INF/jsp/major-form.jsp").forward(req, resp);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("edit".equals(action)) {
            Major major = new Major();
            String id = req.getParameter("id");
            try {
                major = majorDao.getById(id);
                req.setAttribute("major", major);
                req.setAttribute("departments", new DepartmentDao().getAll());
                req.getRequestDispatcher("/WEB-INF/jsp/major-form.jsp").forward(req, resp);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                List<Major> majors = majorDao.getAll();
                req.setAttribute("majors", majors);
                req.getRequestDispatcher("/WEB-INF/jsp/major-list.jsp").forward(req, resp);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("save".equals(action)) {
            Major d = new Major();
            d.setMajorId(req.getParameter("majorId"));
            d.setName(req.getParameter("name"));
            d.setDepartmentId(req.getParameter("departmentId"));
            try {
                majorDao.add(d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("update".equals(action)) {
            Major d = new Major();
            d.setMajorId(req.getParameter("majorId"));
            d.setName(req.getParameter("name"));
            d.setDepartmentId(req.getParameter("departmentId"));
            try {
                majorDao.update(d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("delete".equals(action)) {
            try {
                majorDao.delete(req.getParameter("id"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath() + "/major");
    }

}
