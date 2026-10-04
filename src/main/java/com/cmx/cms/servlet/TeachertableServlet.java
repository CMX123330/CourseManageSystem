package com.cmx.cms.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.dao.ScheduleDao;
import com.cmx.cms.dao.SemesterDao;
import com.cmx.cms.dao.TeacherDao;
import com.cmx.cms.model.User;
import com.cmx.cms.service.ScheduleService;

@WebServlet("/teachertable")
public class TeachertableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String semesterId = req.getParameter("semesterId");
        if (semesterId == null || semesterId.isEmpty()) {
            semesterId = "2025-2026-1";
        }
        try {
            User u = (User) req.getSession().getAttribute("user");
            String teacherId = u.getUserId();
            req.setAttribute("teachers", new TeacherDao().getAll());
            req.setAttribute("semesters", new SemesterDao().getAll());
            req.setAttribute("schedules", new ScheduleDao().getByTeacherId(teacherId, semesterId));
            req.setAttribute("slots", Arrays.asList(1, 3, 5, 7, 9));
            req.setAttribute("holidays", new ScheduleService().getHolidayMarks(semesterId));
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
