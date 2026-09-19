package com.cmx.cms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmx.cms.model.Major;
import com.cmx.cms.util.DBUtil;

public class MajorDao {
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());

    public List<Major> getAll() throws SQLException {
        String sql = "SELECT major_id AS majorId, name, department_id AS departmentId FROM major";
        return qr.query(sql, new BeanListHandler<>(Major.class));
    }

    public Major getById(String majorId) throws SQLException {
        String sql = "SELECT major_id AS majorId, name, department_id AS departmentId FROM major WHERE major_id = ?";
        return qr.query(sql, new BeanHandler<>(Major.class), majorId);
    }

    public int add(Major m) throws SQLException {
        String sql = "INSERT INTO major (major_id, name, department_id) VALUES (?, ?, ?)";
        return qr.update(sql, m.getMajorId(), m.getName(), m.getDepartmentId());
    }

    public int update(Major m) throws SQLException {
        String sql = "UPDATE major SET name = ?, department_id = ? WHERE major_id = ?";
        return qr.update(sql, m.getName(), m.getDepartmentId(), m.getMajorId());
    }

    public int delete(String majorId) throws SQLException {
        String sql = "DELETE FROM major WHERE major_id = ?";
        return qr.update(sql, majorId);
    }
}
