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
import com.cmx.cms.dao.CourseDao;
import com.cmx.cms.dao.OfferingClassDao;
import com.cmx.cms.dao.OfferingDao;
import com.cmx.cms.dao.SemesterDao;
import com.cmx.cms.dao.TeacherDao;
import com.cmx.cms.model.Offering;
import com.cmx.cms.model.OfferingClass;

@WebServlet("/offering")
public class OfferingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            try {
                req.setAttribute("courses", new CourseDao().getAll());
                req.setAttribute("teachers", new TeacherDao().getAll());
                req.setAttribute("clazzs", new ClassDao().getAll());
                req.setAttribute("semesters", new SemesterDao().getAll());
                req.getRequestDispatcher("/WEB-INF/jsp/offering-form.jsp").forward(req, resp);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        } else if ("edit".equals(action)) {
            try {
                String id = req.getParameter("id");
                Offering offering = new OfferingDao().getById(id);
                req.setAttribute("offering", offering);
                req.setAttribute("teachers", new TeacherDao().getAll());
                req.setAttribute("courses", new CourseDao().getAll());
                req.setAttribute("clazzs", new ClassDao().getAll());
                req.setAttribute("semesters", new SemesterDao().getAll());
                List<OfferingClass> ocs = new OfferingClassDao().getByOfferingId(id);
                StringBuilder sb = new StringBuilder(",");
                for (OfferingClass oc : ocs) {
                    sb.append(oc.getClassId()).append(",");
                }
                req.setAttribute("checkedClassIds", sb.toString());
                req.getRequestDispatcher("/WEB-INF/jsp/offering-form.jsp").forward(req, resp);
            } catch (Exception e) {
                // TODO: handle exception
            }
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
        String Id = req.getParameter("id");
        req.setCharacterEncoding("UTF-8");
        if ("update".equals(action)) {
            Offering o = new Offering();
            o.setOfferingId(req.getParameter("offeringId"));
            o.setSemesterId(req.getParameter("semesterId"));
            o.setCourseId(req.getParameter("courseId"));
            o.setTeacherId(req.getParameter("teacherId"));
            o.setWeeklyHours(Integer.parseInt(req.getParameter("weeklyHours")));
            try {
                new OfferingDao().update(o); 
                new OfferingClassDao().deleteByOfferingId(o.getOfferingId()); 
                String[] classIds = req.getParameterValues("classId");
                if (classIds != null) { 
                    for (String classId : classIds) {
                        OfferingClass oc = new OfferingClass();
                        oc.setOfferingId(o.getOfferingId());
                        oc.setClassId(classId);
                        new OfferingClassDao().add(oc);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/offering"); // ④ 别忘重定向
        }

        else if ("delete".equals(action)) {
            try {
                new OfferingClassDao().deleteByOfferingId(Id);
                new OfferingDao().delete(Id);
            } catch (SQLException e) {
                if(e.getErrorCode()==1451)
                {
                    req.setAttribute("error", "删除失败：该开课已有排课记录，请先删除排课");
                    try {
                        req.setAttribute("offerings", new OfferingDao().getViewList(null));
                        req.setAttribute("semesters", new SemesterDao().getAll());
                    } catch (Exception ex) {
                        // TODO: handle exception
                        ex.printStackTrace();
                    }
                    req.getRequestDispatcher("/WEB-INF/jsp/offering-list.jsp").forward(req, resp);
                    return;
                }
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if ("save".equals(action)) {
            Offering o = new Offering();
            o.setOfferingId(req.getParameter("offeringId"));
            o.setSemesterId(req.getParameter("semesterId"));
            o.setCourseId(req.getParameter("courseId"));
            o.setTeacherId(req.getParameter("teacherId"));
            o.setWeeklyHours(Integer.parseInt(req.getParameter("weeklyHours")));
            try {
                new OfferingDao().add(o);

                String[] classIds = req.getParameterValues("classId");
                if (classIds != null) {
                    for (String classId : classIds) {
                        OfferingClass oc = new OfferingClass();
                        oc.setOfferingId(o.getOfferingId());
                        oc.setClassId(classId);
                        new OfferingClassDao().add(oc);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/offering");
        }

    }
}
