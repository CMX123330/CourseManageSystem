package com.cmx.cms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmx.cms.model.Schedule;
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
}
