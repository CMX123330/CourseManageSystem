package com.cmx.cms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmx.cms.model.Course;
import com.cmx.cms.util.DBUtil;

public class CourseDao {
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());

    public List<Course> getAll() throws SQLException {
        String sql = "SELECT course_id AS courseId, name, hours, exam_type AS examType, credit, "
                   + "department_id AS departmentId, nature FROM course";
        return qr.query(sql, new BeanListHandler<>(Course.class));
    }

    public Course getById(String courseId) throws SQLException {
        String sql = "SELECT course_id AS courseId, name, hours, exam_type AS examType, credit, "
                   + "department_id AS departmentId, nature FROM course WHERE course_id = ?";
        return qr.query(sql, new BeanHandler<>(Course.class), courseId);
    }

    public int add(Course c) throws SQLException {
        String sql = "INSERT INTO course (course_id, name, hours, exam_type, credit, department_id, nature) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return qr.update(sql, c.getCourseId(), c.getName(), c.getHours(), c.getExamType(),
                c.getCredit(), c.getDepartmentId(), c.getNature());
    }

    public int update(Course c) throws SQLException {
        String sql = "UPDATE course SET name = ?, hours = ?, exam_type = ?, credit = ?, "
                   + "department_id = ?, nature = ? WHERE course_id = ?";
        return qr.update(sql, c.getName(), c.getHours(), c.getExamType(), c.getCredit(),
                c.getDepartmentId(), c.getNature(), c.getCourseId());
    }

    public int delete(String courseId) throws SQLException {
        String sql = "DELETE FROM course WHERE course_id = ?";
        return qr.update(sql, courseId);
    }
}
