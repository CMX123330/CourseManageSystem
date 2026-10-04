package com.cmx.cms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmx.cms.model.ScheduleChange;
import com.cmx.cms.util.DBUtil;

public class ScheduleChangeDao {
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());

    private String COLS = "change_id AS changeId, schedule_id AS scheduleId, offering_id AS offeringId, "
            + "teacher_id AS teacherId, target_weekday AS targetWeekday, "
            + "target_start_slot AS targetStartSlot, target_slot_count AS targetSlotCount, "
            + "target_classroom_id AS targetClassroomId, reason, status";

    // 提交调课申请
    public int add(ScheduleChange sc) throws SQLException {
        String sql = "INSERT INTO schedule_change (change_id, schedule_id, offering_id, teacher_id, "
                + "target_weekday, target_start_slot, target_slot_count, target_classroom_id, reason, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return qr.update(sql, sc.getChangeId(), sc.getScheduleId(), sc.getOfferingId(), sc.getTeacherId(),
                sc.getTargetWeekday(), sc.getTargetStartSlot(), sc.getTargetSlotCount(),
                sc.getTargetClassroomId(), sc.getReason(), sc.getStatus());
    }

    // 按状态查申请（管理员待审列表用 "待审批"）
    public List<ScheduleChange> getByStatus(String status) throws SQLException {
        String sql = "SELECT " + COLS + " FROM schedule_change WHERE status = ?";
        return qr.query(sql, new BeanListHandler<>(ScheduleChange.class), status);
    }

    // 教师的申请历史
    public List<ScheduleChange> getByTeacherId(String teacherId) throws SQLException {
        String sql = "SELECT " + COLS + " FROM schedule_change WHERE teacher_id = ?";
        return qr.query(sql, new BeanListHandler<>(ScheduleChange.class), teacherId);
    }

    // 审批：改状态（已通过 / 已驳回）
    public int updateStatus(String changeId, String status) throws SQLException {
        String sql = "UPDATE schedule_change SET status = ? WHERE change_id = ?";
        return qr.update(sql, status, changeId);
    }
    public ScheduleChange getById(String Id) throws SQLException
    {
        String sql = "SELECT " + COLS + " FROM schedule_change where change_id = ?";
        return qr.query(sql, new BeanHandler<>(ScheduleChange.class),Id);
    }
}
