package com.cmx.cms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmx.cms.model.Classroom;
import com.cmx.cms.util.DBUtil;

public class ClassroomDao{
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
    //增
    public int add(Classroom c) throws SQLException
    {
        String sql = "INSERT INTO classroom (classroom_id,building,capacity) VALUES (?,?,?)";
        return qr.update(sql,c.getClassroomId(),c.getBuilding(),c.getCapacity());
    }
    public Classroom getById(String classroomId) throws SQLException {
        String sql = "SELECT classroom_id AS classroomId, building, capacity FROM classroom WHERE classroom_id = ?";
        return qr.query(sql, new BeanHandler<>(Classroom.class), classroomId);
    }
    //删
    public int delete(String classroomId) throws SQLException
    {
        String sql = "DELETE FROM classroom WHERE classroom_id = ?";
        return qr.update(sql,classroomId);
    }
    //改
    public int update(Classroom c) throws SQLException
    {
        String sql = "UPDATE classroom SET building=?,capacity=? WHERE classroom_id=?";
        return qr.update(sql,c.getBuilding(),c.getCapacity(),c.getClassroomId());
    }
    //查
    public List<Classroom> getAll() throws SQLException
    {
        String sql = "SELECT classroom_id AS classroomId, building, capacity FROM classroom";
        return qr.query(sql, new BeanListHandler<>(Classroom.class));
    }
}
