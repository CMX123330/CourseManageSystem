package com.cmx.cms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmx.cms.model.Offering;
import com.cmx.cms.model.OfferingView;
import com.cmx.cms.util.DBUtil;

public class OfferingDao {
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());

    public List<OfferingView> getViewList(String semesterId) throws SQLException {

        String sql = "SELECT o.offering_id AS offeringId, s.name AS semesterName, "
                + "c.name AS courseName, t.name AS teacherName, "
                + "o.weekly_hours AS weeklyHours, "
                + "GROUP_CONCAT(DISTINCT cl.name ORDER BY cl.class_id) AS classNames "
                + "FROM offering o "
                + "JOIN semester s ON o.semester_id = s.semester_id "
                + "JOIN course c ON o.course_id = c.course_id "
                + "JOIN teacher t ON o.teacher_id = t.teacher_id "
                + "LEFT JOIN offering_class oc ON o.offering_id = oc.offering_id "
                + "LEFT JOIN clazz cl ON oc.class_id = cl.class_id ";

        if (semesterId == null || semesterId.isEmpty()) {
            return qr.query(sql + "GROUP BY o.offering_id, s.name, c.name, t.name, o.weekly_hours",
                    new BeanListHandler<>(OfferingView.class));
        }

        return qr.query(sql + "WHERE o.semester_id = ? "
                + "GROUP BY o.offering_id, s.name, c.name, t.name, o.weekly_hours",
                new BeanListHandler<>(OfferingView.class), semesterId);
    }

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

    public List<Offering> getElectives(String semesterId) throws SQLException {
        String sql = "SELECT o.offering_id AS offeringId, o.semester_id AS semesterId, "
                + "o.course_id AS courseId, o.teacher_id AS teacherId, o.weekly_hours AS weeklyHours "
                + "FROM offering o JOIN course c ON o.course_id = c.course_id "
                + "WHERE c.nature = '选修' AND o.semester_id = ?";
        return qr.query(sql, new BeanListHandler<>(Offering.class), semesterId);
    }
}
