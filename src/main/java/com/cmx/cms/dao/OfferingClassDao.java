package com.cmx.cms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmx.cms.model.OfferingClass;
import com.cmx.cms.util.DBUtil;

/**
 * 开课-班级中间表 DAO。
 * 中间表没有自己的业务编号，所以没有 getById/update——
 * 它的方法围绕"某条开课挂了哪些班""某个班有哪些开课"来设计。
 */
public class OfferingClassDao {
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());

    public List<OfferingClass> getAll() throws SQLException {
        String sql = "SELECT offering_id AS offeringId, class_id AS classId FROM offering_class";
        return qr.query(sql, new BeanListHandler<>(OfferingClass.class));
    }

    // 一条开课对应的所有班级
    public List<OfferingClass> getByOfferingId(String offeringId) throws SQLException {
        String sql = "SELECT offering_id AS offeringId, class_id AS classId FROM offering_class WHERE offering_id = ?";
        return qr.query(sql, new BeanListHandler<>(OfferingClass.class), offeringId);
    }

    // 一个班级的所有开课
    public List<OfferingClass> getByClassId(String classId) throws SQLException {
        String sql = "SELECT offering_id AS offeringId, class_id AS classId FROM offering_class WHERE class_id = ?";
        return qr.query(sql, new BeanListHandler<>(OfferingClass.class), classId);
    }

    public int add(OfferingClass oc) throws SQLException {
        String sql = "INSERT INTO offering_class (offering_id, class_id) VALUES (?, ?)";
        return qr.update(sql, oc.getOfferingId(), oc.getClassId());
    }

    // 删除单条关联（某班从某开课移除）
    public int delete(String offeringId, String classId) throws SQLException {
        String sql = "DELETE FROM offering_class WHERE offering_id = ? AND class_id = ?";
        return qr.update(sql, offeringId, classId);
    }

    // 删除一条开课的全部班级关联（删开课记录前必须先调用，否则外键拦住）
    public int deleteByOfferingId(String offeringId) throws SQLException {
        String sql = "DELETE FROM offering_class WHERE offering_id = ?";
        return qr.update(sql, offeringId);
    }
}
