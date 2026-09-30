package com.cmx.cms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cmx.cms.dao.OfferingClassDao;
import com.cmx.cms.dao.OfferingDao;
import com.cmx.cms.dao.ScheduleDao;
import com.cmx.cms.model.Offering;
import com.cmx.cms.model.OfferingClass;
import com.cmx.cms.model.Schedule;

public class ScheduleService {
    private ScheduleDao scheduleDao = new ScheduleDao();
    private OfferingDao offeringDao = new OfferingDao();
    private OfferingClassDao offeringClassDao = new OfferingClassDao();

    public List<String> checkConflicts(Schedule s, String excludeScheduleId) throws SQLException {
        List<String> conflicts = new ArrayList<>();
        Offering newOffering = offeringDao.getById(s.getOfferingId());
        List<OfferingClass> newClasses = offeringClassDao.getByOfferingId(s.getOfferingId());
        List<Schedule> candidates = scheduleDao.getByWeekday(s.getWeekday());
        for (Schedule c : candidates) {
            if (c.getScheduleId().equals(excludeScheduleId)) {
                continue;
            }
            boolean slotOverlap = s.getStartSlot() <= c.getStartSlot() + c.getSlotCount() - 1
                    && c.getStartSlot() <= s.getStartSlot() + s.getSlotCount() - 1;
            boolean weekOverlap = s.getStartWeek() <= c.getEndWeek() && c.getStartWeek() <= s.getEndWeek();
            boolean weekTypeConflict = weekTypeConflicts(s.getWeekType(), c.getWeekType());
            if (!(slotOverlap && weekOverlap && weekTypeConflict)) {
                continue;
            }
            Offering cOffering = offeringDao.getById(c.getOfferingId());
            if (s.getClassroomId().equals(c.getClassroomId())) {
                conflicts.add("教室冲突：周" + s.getWeekday() + "第" + s.getStartSlot() + "节 教室" + s.getClassroomId() + "已被"
                        + c.getScheduleId() + "占用");
            }
            if (newOffering.getTeacherId().equals(cOffering.getTeacherId())) {
                conflicts.add("教师冲突：教师" + newOffering.getTeacherId() + " 与 "
                        + c.getScheduleId() + " 时间重叠");
            }
            for (OfferingClass nc : newClasses) {
                for (OfferingClass cc : offeringClassDao.getByOfferingId(c.getOfferingId()))
                    if (nc.getClassId().equals(cc.getClassId())) {
                        conflicts.add("班级冲突：班级" + nc.getClassId() + " 与 "
                                + c.getScheduleId() + " 时间重叠");
                        break;
                    }
            }

        }
        return conflicts;
    }

    public List<String> checkConflicts(Schedule s) throws SQLException {
        List<String> conflicts = new ArrayList<>();
        Offering newOffering = offeringDao.getById(s.getOfferingId());
        List<OfferingClass> newClasses = offeringClassDao.getByOfferingId(s.getOfferingId());
        List<Schedule> candidates = scheduleDao.getByWeekday(s.getWeekday());
        for (Schedule c : candidates) {
            boolean slotOverlap = s.getStartSlot() <= c.getStartSlot() + c.getSlotCount() - 1
                    && c.getStartSlot() <= s.getStartSlot() + s.getSlotCount() - 1;
            boolean weekOverlap = s.getStartWeek() <= c.getEndWeek() && c.getStartWeek() <= s.getEndWeek();
            boolean weekTypeConflict = weekTypeConflicts(s.getWeekType(), c.getWeekType());
            if (!(slotOverlap && weekOverlap && weekTypeConflict)) {
                continue;
            }
            Offering cOffering = offeringDao.getById(c.getOfferingId());
            if (s.getClassroomId().equals(c.getClassroomId())) {
                conflicts.add("教室冲突：周" + s.getWeekday() + "第" + s.getStartSlot() + "节 教室" + s.getClassroomId() + "已被"
                        + c.getScheduleId() + "占用");
            }
            if (newOffering.getTeacherId().equals(cOffering.getTeacherId())) {
                conflicts.add("教师冲突：教师" + newOffering.getTeacherId() + " 与 "
                        + c.getScheduleId() + " 时间重叠");
            }
            for (OfferingClass nc : newClasses) {
                for (OfferingClass cc : offeringClassDao.getByOfferingId(c.getOfferingId()))
                    if (nc.getClassId().equals(cc.getClassId())) {
                        conflicts.add("班级冲突：班级" + nc.getClassId() + " 与 "
                                + c.getScheduleId() + " 时间重叠");
                        break;
                    }
            }
        }
        return conflicts;
    }

    private boolean weekTypeConflicts(String a, String b) {
        if ("全周".equals(a) || "全周".equals(b)) {
            return true;
        }
        return a.equals(b);
    }
}