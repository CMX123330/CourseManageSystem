package com.cmx.cms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmx.cms.model.Teacher;
import com.cmx.cms.util.DBUtil;

public class TeacherDao {
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());

    public List<Teacher> getAll() throws SQLException {
        String sql = "SELECT teacher_id AS teacherId, name, phone, gender, department_id AS departmentId, title FROM teacher";
        return qr.query(sql, new BeanListHandler<>(Teacher.class));
    }

    public Teacher getById(String teacherId) throws SQLException {
        String sql = "SELECT teacher_id AS teacherId, name, phone, gender, department_id AS departmentId, title FROM teacher WHERE teacher_id = ?";
        return qr.query(sql, new BeanHandler<>(Teacher.class), teacherId);
    }

    public int add(Teacher t) throws SQLException {
        String sql = "INSERT INTO teacher (teacher_id, name, phone, gender, department_id, title) VALUES (?, ?, ?, ?, ?, ?)";
        return qr.update(sql, t.getTeacherId(), t.getName(), t.getPhone(), t.getGender(), t.getDepartmentId(), t.getTitle());
    }

    public int update(Teacher t) throws SQLException {
        String sql = "UPDATE teacher SET name = ?, phone = ?, gender = ?, department_id = ?, title = ? WHERE teacher_id = ?";
        return qr.update(sql, t.getName(), t.getPhone(), t.getGender(), t.getDepartmentId(), t.getTitle(), t.getTeacherId());
    }

    public int delete(String teacherId) throws SQLException {
        String sql = "DELETE FROM teacher WHERE teacher_id = ?";
        return qr.update(sql, teacherId);
    }
}
