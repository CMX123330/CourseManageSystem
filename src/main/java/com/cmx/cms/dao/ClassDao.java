package com.cmx.cms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmx.cms.model.Clazz;
import com.cmx.cms.util.DBUtil;

public class ClassDao{
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
    //增
    public int add(Clazz c) throws SQLException
    {
        String sql = "INSERT INTO clazz(class_id,name,major_id,grade,student_count) VALUES (?,?,?,?,?)";
        return qr.update(sql,c.getClassId(),c.getName(),c.getMajorId(),c.getGrade(),c.getStudentCount());
    }
    public Clazz getById(String classId) throws SQLException {
        String sql = "SELECT class_id AS classId, name, major_id AS majorId, grade, student_count AS studentCount FROM clazz WHERE class_id = ?";
        return qr.query(sql, new BeanHandler<>(Clazz.class), classId);
    }
    //删
    public int delete(String classId) throws SQLException
    {
        String sql = "DELETE FROM clazz WHERE class_id=?";
        return qr.update(sql,classId);
    }
    //改
    public int update(Clazz c) throws SQLException
    {
        String sql = "UPDATE clazz SET name=?,major_id=?,grade=?,student_count=? WHERE class_id=?";
        return qr.update(sql,c.getName(),c.getMajorId(),c.getGrade(),c.getStudentCount(),c.getClassId());
    }
    //查
    public List<Clazz> getAll() throws SQLException
    {
        String sql = "SELECT class_id AS classId, name, major_id AS majorId, grade, student_count AS studentCount FROM clazz";
        return qr.query(sql, new BeanListHandler<>(Clazz.class));
    }
}
