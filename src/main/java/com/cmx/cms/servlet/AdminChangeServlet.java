package com.cmx.cms.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmx.cms.dao.ScheduleChangeDao;
import com.cmx.cms.dao.ScheduleDao;
import com.cmx.cms.model.Schedule;
import com.cmx.cms.model.ScheduleChange;
import com.cmx.cms.service.ScheduleService;

@WebServlet("/changeApprove")
public class AdminChangeServlet extends HttpServlet {

    private ScheduleChangeDao changeDao = new ScheduleChangeDao();
    private ScheduleDao scheduleDao = new ScheduleDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 显示待审批列表
            req.setAttribute("pending", changeDao.getByStatus("待审批"));
            req.getRequestDispatcher("/WEB-INF/jsp/schedule-approve.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String changeId = req.getParameter("changeId");
        try {
            if ("approve".equals(action)) {
                // ① 查申请 + 原排课（三项沿用）
                ScheduleChange sc = changeDao.getById(changeId);
                Schedule original = scheduleDao.getById(sc.getScheduleId());

                // ② 构造目标排课
                Schedule target = new Schedule();
                target.setScheduleId(sc.getScheduleId());     // 编号不变
                target.setOfferingId(sc.getOfferingId());     // 课程不变
                target.setWeekday(sc.getTargetWeekday());
                target.setStartSlot(sc.getTargetStartSlot());
                target.setSlotCount(sc.getTargetSlotCount());
                target.setClassroomId(sc.getTargetClassroomId());
                target.setStartWeek(original.getStartWeek()); // 三项目标没存，沿用原值
                target.setEndWeek(original.getEndWeek());
                target.setWeekType(original.getWeekType());

                // ③ 冲突检测（排除原课自己）
                List<String> conflicts = new ScheduleService().checkConflicts(target, sc.getScheduleId());
                if (conflicts.isEmpty()) {
                    scheduleDao.update(target);                  // 改排课表
                    changeDao.updateStatus(changeId, "已通过");  // 改申请状态
                } else {
                    // 冲突：消息 + 重查列表 forward 回审批页
                    req.setAttribute("conflicts", conflicts);
                    req.setAttribute("pending", changeDao.getByStatus("待审批"));
                    req.getRequestDispatcher("/WEB-INF/jsp/schedule-approve.jsp").forward(req, resp);
                    return;
                }
            } else if ("reject".equals(action)) {
                changeDao.updateStatus(changeId, "已驳回");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/changeApprove");
    }
}
