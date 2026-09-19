package com.cmx.cms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmx.cms.model.Semester;
import com.cmx.cms.util.DBUtil;

public class SemesterDao{
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
    //增
    public int add(Semester s) throws SQLException
    {
        String sql = "insert into semester (semester_id,name,start_date,end_date,total_weeks) values(?,?,?,?,?)";
        return qr.update(sql,s.getSemesterId(),s.getName(),s.getStartDate(),s.getEndDate(),s.getTotalWeeks());
    }
    public Semester getById(String semesterId) throws SQLException {
        String sql = "SELECT semester_id AS semesterId, name, start_date AS startDate, end_date AS endDate, total_weeks AS totalWeeks FROM semester WHERE semester_id = ?";
        return qr.query(sql, new BeanHandler<>(Semester.class), semesterId);
    }
    //删
    public int delete(String semesterId) throws SQLException
    {
        String sql ="delete from semester where semester_id=?";
        return qr.update(sql,semesterId);
    }
    //改
    public int update(Semester s) throws SQLException
    {
        String sql="update semester set name=?,start_date=?,end_date=?,total_weeks=? where semester_id=?";
        return qr.update(sql,s.getName(),s.getStartDate(),s.getEndDate(),s.getTotalWeeks(),s.getSemesterId());
    }
    //查
    public List<Semester> getAll() throws SQLException{
        String sql = "SELECT semester_id AS semesterId,name,start_date AS startDate,end_date AS endDate,total_weeks AS totalWeeks FROM semester";
        return qr.query(sql, new BeanListHandler<>(Semester.class));
    }
    
}