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
import com.cmx.cms.model.Department;

@WebServlet("/department")
public class DepartmentServlet extends HttpServlet {

    private DepartmentDao departmentDao = new DepartmentDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/jsp/department-form.jsp").forward(req, resp);
        } else if ("edit".equals(action)) {
            Department department = new Department();
            String id = req.getParameter("id");
            try {
                department = departmentDao.getById(id);
                req.setAttribute("department", department);
                req.getRequestDispatcher("/WEB-INF/jsp/department-form.jsp").forward(req, resp);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                List<Department> departments = departmentDao.getAll();
                req.setAttribute("departments", departments);
                req.getRequestDispatcher("/WEB-INF/jsp/department-list.jsp").forward(req, resp);
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
            Department d = new Department();
            d.setDepartmentId(req.getParameter("departmentId"));
            d.setName(req.getParameter("name"));
            d.setDean(req.getParameter("dean"));
            d.setOfficePhone(req.getParameter("officePhone"));
            try {
                departmentDao.add(d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if("update".equals(action))
        {
            Department d = new Department();
            d.setDepartmentId(req.getParameter("departmentId"));
            d.setName(req.getParameter("name"));
            d.setDean(req.getParameter("dean"));
            d.setOfficePhone(req.getParameter("officePhone"));
            try {
                departmentDao.update(d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if("delete".equals(action))
        {
            try {
                departmentDao.delete(req.getParameter("id"));
            } catch (SQLException e) {
                if(e.getErrorCode() == 1451)
                {
                    req.setAttribute("error", "删除失败：该院系下还有专业或教师，请先删除相关数据");
                    try {
                        req.setAttribute("departments", departmentDao.getAll());
                    } catch (Exception ex) {
                        // TODO: handle exception
                        System.out.println("删除异常");
                        ex.printStackTrace();
                    }
                    req.getRequestDispatcher("/WEB-INF/jsp/department-list.jsp").forward(req, resp);
                    return ;
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/department");
    }

}
