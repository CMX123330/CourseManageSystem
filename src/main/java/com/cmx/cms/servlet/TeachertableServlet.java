package com.cmx.cms.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.dao.ClassDao;
import com.cmx.cms.dao.ScheduleDao;
import com.cmx.cms.dao.SemesterDao;
import com.cmx.cms.dao.TeacherDao;
@WebServlet ("/teachertable")
public class TeachertableServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String teacherId = req.getParameter("teacherId");
        String semesterId = req.getParameter("semesterId");
        try {
            req.setAttribute("schedules", new ScheduleDao().getByTeacherId(teacherId, semesterId));
            req.setAttribute("teachers",new TeacherDao().getAll());
            req.setAttribute("semesters", new SemesterDao().getAll());
            req.setAttribute("slots", Arrays.asList(1,3,5,7,9));
            req.getRequestDispatcher("/WEB-INF/jsp/teacher-timetable.jsp").forward(req, resp);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost(req, resp);
    }
}
