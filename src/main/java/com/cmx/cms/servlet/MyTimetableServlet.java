package com.cmx.cms.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.dao.ClassDao;
import com.cmx.cms.dao.ScheduleDao;
import com.cmx.cms.dao.SemesterDao;
import com.cmx.cms.dao.StudentDao;
import com.cmx.cms.model.User;
import com.cmx.cms.service.ScheduleService;

@WebServlet ("/mytimetable")
public class MyTimetableServlet extends HttpServlet{
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
        // ① 从会员卡取当前登录学生的学号
        User u = (User) req.getSession().getAttribute("user");
        String studentId = u.getUserId();

        // ② 查该学生所在班级
        String classId = new StudentDao().getById(studentId).getClassId();

        // ③ 学期：参数优先，没传用当前学期
        String semesterId = req.getParameter("semesterId");
        if (semesterId == null || semesterId.isEmpty()) {
            semesterId = "2025-2026-1";
        }

        // ④ 课表数据 + 页面要的全部塞 request
        req.setAttribute("schedules", new ScheduleDao().getByClassId(classId, semesterId));
        req.setAttribute("clazzs", new ClassDao().getAll());
        req.setAttribute("semesters", new SemesterDao().getAll());
        req.setAttribute("slots", Arrays.asList(1, 3, 5, 7, 9));
        req.setAttribute("holidays", new ScheduleService().getHolidayMarks(semesterId));
        req.getRequestDispatcher("/WEB-INF/jsp/timetable-list.jsp").forward(req, resp);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
