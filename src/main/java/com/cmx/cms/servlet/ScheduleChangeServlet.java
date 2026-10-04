package com.cmx.cms.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.dao.ClassroomDao;
import com.cmx.cms.dao.ScheduleChangeDao;
import com.cmx.cms.dao.ScheduleDao;
import com.cmx.cms.model.ScheduleChange;
import com.cmx.cms.model.User;

@WebServlet("/scheduleChange")
public class ScheduleChangeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("apply".equals(action)) {
                // 申请表单：原排课信息 + 教室下拉
                req.setAttribute("sc", new ScheduleDao().getById(req.getParameter("scheduleId")));
                req.setAttribute("classrooms", new ClassroomDao().getAll());
                req.getRequestDispatcher("/WEB-INF/jsp/schedule-change-form.jsp").forward(req, resp);
            } else {
                // 列表：该教师自己的排课
                User u = (User) req.getSession().getAttribute("user");
                req.setAttribute("mySchedules", new ScheduleDao().getByTeacherId(u.getUserId(), "2025-2026-1"));
                req.getRequestDispatcher("/WEB-INF/jsp/schedule-change-list.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User u = (User) req.getSession().getAttribute("user");
        ScheduleChange sc = new ScheduleChange();
        sc.setChangeId(req.getParameter("changeId"));
        sc.setScheduleId(req.getParameter("scheduleId"));
        sc.setOfferingId(req.getParameter("offeringId"));
        sc.setTeacherId(u.getUserId());                       // 申请人 = 登录教师
        sc.setTargetWeekday(Integer.parseInt(req.getParameter("targetWeekday")));
        sc.setTargetStartSlot(Integer.parseInt(req.getParameter("targetStartSlot")));
        sc.setTargetSlotCount(Integer.parseInt(req.getParameter("targetSlotCount")));
        sc.setTargetClassroomId(req.getParameter("targetClassroomId"));
        sc.setReason(req.getParameter("reason"));
        sc.setStatus("待审批");                                // 初始状态硬编码
        try {
            new ScheduleChangeDao().add(sc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/scheduleChange");
    }
}
