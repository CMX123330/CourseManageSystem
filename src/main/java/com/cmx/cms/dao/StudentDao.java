package com.cmx.cms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmx.cms.model.Student;
import com.cmx.cms.util.DBUtil;

public class StudentDao {
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());

    public List<Student> getAll() throws SQLException {
        String sql = "SELECT student_id AS studentId, name, gender, class_id AS classId, phone FROM student";
        return qr.query(sql, new BeanListHandler<>(Student.class));
    }

    public Student getById(String studentId) throws SQLException {
        String sql = "SELECT student_id AS studentId, name, gender, class_id AS classId, phone FROM student WHERE student_id = ?";
        return qr.query(sql, new BeanHandler<>(Student.class), studentId);
    }

    public int add(Student s) throws SQLException {
        String sql = "INSERT INTO student (student_id, name, gender, class_id, phone) VALUES (?, ?, ?, ?, ?)";
        return qr.update(sql, s.getStudentId(), s.getName(), s.getGender(), s.getClassId(), s.getPhone());
    }

    public int update(Student s) throws SQLException {
        String sql = "UPDATE student SET name = ?, gender = ?, class_id = ?, phone = ? WHERE student_id = ?";
        return qr.update(sql, s.getName(), s.getGender(), s.getClassId(), s.getPhone(), s.getStudentId());
    }

    public int delete(String studentId) throws SQLException {
        String sql = "DELETE FROM student WHERE student_id = ?";
        return qr.update(sql, studentId);
    }
}
