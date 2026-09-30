package com.cmx.cms.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.dao.ClassroomDao;
import com.cmx.cms.dao.OfferingDao;
import com.cmx.cms.dao.ScheduleDao;
import com.cmx.cms.model.Schedule;
import com.cmx.cms.service.ScheduleService;

@WebServlet("/schedule")
public class ScheduleServlet extends HttpServlet {

    private ScheduleDao scheduleDao = new ScheduleDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            try {
                // 开课下拉：用 getViewList 才能显示"课程名 - 教师名"；教室下拉：全部教室
                req.setAttribute("offerings", new OfferingDao().getViewList(null));
                req.setAttribute("classrooms", new ClassroomDao().getAll());
                req.getRequestDispatcher("/WEB-INF/jsp/schedule-form.jsp").forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("edit".equals(action)) {
            // 任务 4 再做：回填 + 冲突检测排除自己
            try {
                Schedule schedule = scheduleDao.getById(req.getParameter("id"));
                req.setAttribute("schedule", schedule);
                req.setAttribute("classrooms", new ClassroomDao().getAll());
                req.setAttribute("offerings", new OfferingDao().getViewList(null));
                req.getRequestDispatcher("/WEB-INF/jsp/schedule-form.jsp").forward(req, resp);

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            // 列表：联表显示课程名/教师名
            try {
                req.setAttribute("schedules", new ScheduleDao().getViewList());
                req.getRequestDispatcher("/WEB-INF/jsp/schedule-list.jsp").forward(req, resp);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("save".equals(action)) {
            Schedule s = new Schedule();
            s.setScheduleId(req.getParameter("scheduleId"));
            s.setOfferingId(req.getParameter("offeringId"));
            s.setClassroomId(req.getParameter("classroomId"));
            s.setWeekday(Integer.parseInt(req.getParameter("weekday")));
            s.setStartSlot(Integer.parseInt(req.getParameter("startSlot")));
            s.setSlotCount(Integer.parseInt(req.getParameter("slotCount")));
            s.setStartWeek(Integer.parseInt(req.getParameter("startWeek")));
            s.setEndWeek(Integer.parseInt(req.getParameter("endWeek")));
            s.setWeekType(req.getParameter("weekType"));
            try {
                ScheduleService service = new ScheduleService();
                List<String> conflicts = service.checkConflicts(s);
                if (conflicts.isEmpty()) {
                    scheduleDao.add(s); // 任务 3 在这里接冲突检测
                } else {
                    req.setAttribute("conflicts", conflicts);
                    req.setAttribute("offerings", new OfferingDao().getViewList(null));
                    req.setAttribute("classrooms", new ClassroomDao().getAll());
                    req.getRequestDispatcher("/WEB-INF/jsp/schedule-form.jsp").forward(req, resp);
                    return; // 走 forward 就不走下面的 redirect
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/schedule");
        } else if ("delete".equals(action)) {
            try {
                scheduleDao.delete(req.getParameter("id"));
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/schedule");
        } else if ("update".equals(action)) {
            try {
                Schedule schedule = scheduleDao.getById(req.getParameter("scheduleId"));
                schedule.setClassroomId(req.getParameter("classroomId"));
                schedule.setEndWeek(Integer.parseInt(req.getParameter("endWeek")));
                schedule.setOfferingId(req.getParameter("offeringId"));
                schedule.setScheduleId(req.getParameter("scheduleId"));
                schedule.setSlotCount(Integer.parseInt(req.getParameter("slotCount")));
                schedule.setStartSlot(Integer.parseInt(req.getParameter("startSlot")));
                schedule.setStartWeek(Integer.parseInt(req.getParameter("startWeek")));
                schedule.setWeekType(req.getParameter("weekType"));
                schedule.setWeekday(Integer.parseInt(req.getParameter("weekday")));
                try {
                ScheduleService service = new ScheduleService();
                List<String> conflicts = service.checkConflicts(schedule,schedule.getScheduleId());
                if (conflicts.isEmpty()) {
                    scheduleDao.update(schedule);
                    resp.sendRedirect(req.getContextPath()+"/schedule");
                } else {
                    req.setAttribute("conflicts", conflicts);
                    req.setAttribute("offerings", new OfferingDao().getViewList(null));
                    req.setAttribute("classrooms", new ClassroomDao().getAll());
                    req.getRequestDispatcher("/WEB-INF/jsp/schedule-form.jsp").forward(req, resp);
                    return; 
                }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}
