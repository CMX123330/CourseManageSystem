package com.cmx.cms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmx.cms.model.Department;
import com.cmx.cms.util.DBUtil;
public class DepartmentDao{
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
    //增
    public int add(Department d) throws  SQLException
    {
        String sql = "insert into Department (department_id,name,dean,office_phone) values (?,?,?,?)";
        return qr.update(sql,d.getDepartmentId(),d.getName(),d.getDean(),d.getOfficePhone());
    }
    public Department getById(String departmentId) throws SQLException {
        String sql = "SELECT department_id AS departmentId, name, dean, office_phone FROM Department WHERE department_id = ?";
        return qr.query(sql, new BeanHandler<>(Department.class), departmentId);
    }
    //删
    public int delete(String departmentId) throws  SQLException
    {
        String sql = "delete from department where department_id=?";
        return qr.update(sql,departmentId);
    }
    //改
    public int update(Department d) throws SQLException
    {
        String sql = "update department set name=?,dean=?,office_phone=? where department_id=?";
        return  qr.update(sql,d.getName(),d.getDean(),d.getOfficePhone(),d.getDepartmentId());
    }
    //查
    public List<Department> getAll() throws SQLException
    {
        String sql = "SELECT department_id AS departmentId,name,dean,office_phone AS officePhone FROM department";
        return qr.query(sql, new BeanListHandler<>(Department.class));
    }
    
}