package com.cmx.cms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmx.cms.model.Offering;
import com.cmx.cms.util.DBUtil;

public class OfferingDao {
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());

    public List<Offering> getAll() throws SQLException {
        String sql = "SELECT offering_id AS offeringId, semester_id AS semesterId, course_id AS courseId, "
                   + "teacher_id AS teacherId, weekly_hours AS weeklyHours FROM offering";
        return qr.query(sql, new BeanListHandler<>(Offering.class));
    }

    public Offering getById(String offeringId) throws SQLException {
        String sql = "SELECT offering_id AS offeringId, semester_id AS semesterId, course_id AS courseId, "
                   + "teacher_id AS teacherId, weekly_hours AS weeklyHours FROM offering WHERE offering_id = ?";
        return qr.query(sql, new BeanHandler<>(Offering.class), offeringId);
    }

    public int add(Offering o) throws SQLException {
        String sql = "INSERT INTO offering (offering_id, semester_id, course_id, teacher_id, weekly_hours) "
                   + "VALUES (?, ?, ?, ?, ?)";
        return qr.update(sql, o.getOfferingId(), o.getSemesterId(), o.getCourseId(),
                o.getTeacherId(), o.getWeeklyHours());
    }

    public int update(Offering o) throws SQLException {
        String sql = "UPDATE offering SET semester_id = ?, course_id = ?, teacher_id = ?, weekly_hours = ? "
                   + "WHERE offering_id = ?";
        return qr.update(sql, o.getSemesterId(), o.getCourseId(), o.getTeacherId(),
                o.getWeeklyHours(), o.getOfferingId());
    }

    public int delete(String offeringId) throws SQLException {
        String sql = "DELETE FROM offering WHERE offering_id = ?";
        return qr.update(sql, offeringId);
    }
}
