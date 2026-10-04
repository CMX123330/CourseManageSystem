package com.cmx.cms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmx.cms.model.StudentOffering;
import com.cmx.cms.model.StudentOfferingView;
import com.cmx.cms.util.DBUtil;

public class StudentOfferingDao {
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());

    // ① 选课：插一行
    public int add(StudentOffering so) throws SQLException {
        String sql = "INSERT INTO student_offering (student_id, offering_id) VALUES (?, ?)";
        return qr.update(sql, so.getStudentId(), so.getOfferingId());
    }

    // ② 退课：删一行
    public int delete(String studentId, String offeringId) throws SQLException {
        String sql = "DELETE FROM student_offering WHERE student_id = ? AND offering_id = ?";
        return qr.update(sql, studentId, offeringId);
    }

    // ③ 我的已选列表（联表显示课程名/教师名，JOIN 老技能）
    public List<StudentOfferingView> getMyList(String studentId) throws SQLException {
        String sql = "SELECT so.offering_id AS offeringId, c.name AS courseName, "
                   + "t.name AS teacherName, o.weekly_hours AS weeklyHours "
                   + "FROM student_offering so "
                   + "JOIN offering o ON so.offering_id = o.offering_id "
                   + "JOIN course c ON o.course_id = c.course_id "
                   + "JOIN teacher t ON o.teacher_id = t.teacher_id "
                   + "WHERE so.student_id = ?";
        return qr.query(sql, new BeanListHandler<>(StudentOfferingView.class), studentId);
    }

    // ④ 可选的选修课（还没选过的）——注意 NOT IN 子查询
    public List<StudentOfferingView> getAvailable(String studentId, String semesterId) throws SQLException {
        String sql = "SELECT o.offering_id AS offeringId, c.name AS courseName, "
                   + "t.name AS teacherName, o.weekly_hours AS weeklyHours "
                   + "FROM offering o "
                   + "JOIN course c ON o.course_id = c.course_id "
                   + "JOIN teacher t ON o.teacher_id = t.teacher_id "
                   + "WHERE c.nature = '选修' AND o.semester_id = ? "
                   + "AND o.offering_id NOT IN "
                   + "(SELECT offering_id FROM student_offering WHERE student_id = ?)";
        return qr.query(sql, new BeanListHandler<>(StudentOfferingView.class), semesterId, studentId);
    }
}
