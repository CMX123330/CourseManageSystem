package com.cmx.cms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmx.cms.model.Schedule;
import com.cmx.cms.model.ScheduleView;
import com.cmx.cms.util.DBUtil;

public class ScheduleDao {
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());

    public List<Schedule> getAll() throws SQLException {
        String sql = "SELECT schedule_id AS scheduleId, offering_id AS offeringId, weekday, "
                + "start_slot AS startSlot, slot_count AS slotCount, classroom_id AS classroomId, "
                + "start_week AS startWeek, end_week AS endWeek, week_type AS weekType FROM schedule";
        return qr.query(sql, new BeanListHandler<>(Schedule.class));
    }

    public Schedule getById(String scheduleId) throws SQLException {
        String sql = "SELECT schedule_id AS scheduleId, offering_id AS offeringId, weekday, "
                + "start_slot AS startSlot, slot_count AS slotCount, classroom_id AS classroomId, "
                + "start_week AS startWeek, end_week AS endWeek, week_type AS weekType "
                + "FROM schedule WHERE schedule_id = ?";
        return qr.query(sql, new BeanHandler<>(Schedule.class), scheduleId);
    }

    public int add(Schedule s) throws SQLException {
        String sql = "INSERT INTO schedule (schedule_id, offering_id, weekday, start_slot, slot_count, "
                + "classroom_id, start_week, end_week, week_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return qr.update(sql, s.getScheduleId(), s.getOfferingId(), s.getWeekday(), s.getStartSlot(),
                s.getSlotCount(), s.getClassroomId(), s.getStartWeek(), s.getEndWeek(), s.getWeekType());
    }

    public int update(Schedule s) throws SQLException {
        String sql = "UPDATE schedule SET offering_id = ?, weekday = ?, start_slot = ?, slot_count = ?, "
                + "classroom_id = ?, start_week = ?, end_week = ?, week_type = ? WHERE schedule_id = ?";
        return qr.update(sql, s.getOfferingId(), s.getWeekday(), s.getStartSlot(), s.getSlotCount(),
                s.getClassroomId(), s.getStartWeek(), s.getEndWeek(), s.getWeekType(), s.getScheduleId());
    }

    public int delete(String scheduleId) throws SQLException {
        String sql = "DELETE FROM schedule WHERE schedule_id = ?";
        return qr.update(sql, scheduleId);
    }

    public List<Schedule> getByWeekday(int weekday) throws SQLException {
        String sql = "SELECT schedule_id AS scheduleId, offering_id AS offeringId, weekday, "
                + "start_slot AS startSlot, slot_count AS slotCount, classroom_id AS classroomId, "
                + "start_week AS startWeek, end_week AS endWeek, week_type AS weekType "
                + "FROM schedule WHERE weekday = ?";
        return qr.query(sql, new BeanListHandler<>(Schedule.class), weekday);
    }

    public List<ScheduleView> getViewList() throws SQLException {
        String sql = "SELECT s.schedule_id AS scheduleId, s.offering_id AS offeringId, "
                + "c.name AS courseName, t.name AS teacherName, "
                + "s.classroom_id AS classroomId, s.weekday, "
                + "s.start_slot AS startSlot, s.slot_count AS slotCount, "
                + "s.start_week AS startWeek, s.end_week AS endWeek, "
                + "s.week_type AS weekType "
                + "FROM schedule s "
                + "JOIN offering o ON s.offering_id = o.offering_id "
                + "JOIN course c ON o.course_id = c.course_id "
                + "JOIN teacher t ON o.teacher_id = t.teacher_id "
                + "ORDER BY s.weekday, s.start_slot";
        return qr.query(sql, new BeanListHandler<>(ScheduleView.class));
    }
    public List<ScheduleView> getByClassId(String classId,String semesterId) throws SQLException
    {
        String sql="SELECT s.schedule_id AS scheduleId, c.name AS courseName, t.name AS teacherName," +
                        "       s.classroom_id AS classroomId, s.weekday," + 
                        "       s.start_slot AS startSlot, s.slot_count AS slotCount, s.week_type AS weekType, " +
                        "       s.start_week AS startWeek, s.end_week AS endWeek " +
                        "FROM schedule s " +
                        "JOIN offering o ON s.offering_id = o.offering_id " + 
                        "JOIN offering_class oc ON o.offering_id = oc.offering_id " + 
                        "JOIN course c ON o.course_id = c.course_id " + 
                        "JOIN teacher t ON o.teacher_id = t.teacher_id " + 
                        "WHERE oc.class_id = ? " +
                        "AND o.semester_id = ? " +
                        "ORDER BY s.weekday, s.start_slot ";
        return qr.query(sql, new BeanListHandler<>(ScheduleView.class), classId, semesterId);
    }

    // 教师课表：教师直接挂开课表，不需要经过中间表
    public List<ScheduleView> getByTeacherId(String teacherId, String semesterId) throws SQLException {
        String sql = "SELECT s.schedule_id AS scheduleId, c.name AS courseName, t.name AS teacherName, "
                   + "s.classroom_id AS classroomId, s.weekday, "
                   + "s.start_slot AS startSlot, s.slot_count AS slotCount, s.week_type AS weekType, "
                   + "s.start_week AS startWeek, s.end_week AS endWeek "
                   + "FROM schedule s "
                   + "JOIN offering o ON s.offering_id = o.offering_id "
                   + "JOIN course c ON o.course_id = c.course_id "
                   + "JOIN teacher t ON o.teacher_id = t.teacher_id "
                   + "WHERE o.teacher_id = ? AND o.semester_id = ? "
                   + "ORDER BY s.weekday, s.start_slot";
        return qr.query(sql, new BeanListHandler<>(ScheduleView.class), teacherId, semesterId);
    }

    // 教室课表：教室直接是排课表的字段
    public List<ScheduleView> getByClassroomId(String classroomId, String semesterId) throws SQLException {
        String sql = "SELECT s.schedule_id AS scheduleId, c.name AS courseName, t.name AS teacherName, "
                   + "s.classroom_id AS classroomId, s.weekday, "
                   + "s.start_slot AS startSlot, s.slot_count AS slotCount, s.week_type AS weekType, "
                   + "s.start_week AS startWeek, s.end_week AS endWeek "
                   + "FROM schedule s "
                   + "JOIN offering o ON s.offering_id = o.offering_id "
                   + "JOIN course c ON o.course_id = c.course_id "
                   + "JOIN teacher t ON o.teacher_id = t.teacher_id "
                   + "WHERE s.classroom_id = ? AND o.semester_id = ? "
                   + "ORDER BY s.weekday, s.start_slot";
        return qr.query(sql, new BeanListHandler<>(ScheduleView.class), classroomId, semesterId);
    }
}
