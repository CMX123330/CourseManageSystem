package com.cmx.cms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmx.cms.model.Slot;
import com.cmx.cms.util.DBUtil;

public class SlotDao{
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
    //增
    public int add(Slot s) throws SQLException
    {
        String sql = "insert into Slot (slot_id,start_time,end_time) values (?,?,?)";
        return qr.update(sql,s.getSlotId(),s.getStartTime(),s.getEndTime());
    }
    public Slot getById(String SlotId) throws SQLException {
        String sql = "SELECT Slot_id AS SlotId, start_time as startTime,end_time as endTime FROM Slot WHERE Slot_id = ?";
        return qr.query(sql, new BeanHandler<>(Slot.class), SlotId);
    }
    //删
    public int delete(int slotId) throws SQLException
    {
        String sql = "delete from slot where slot_id = ?";
        return qr.update(sql,slotId);
    }
    //改
    public int update(Slot s) throws SQLException
    {
        String sql ="update slot set start_time=?,end_time=? where slot_id=?";
        return qr.update(sql,s.getStartTime(),s.getEndTime());
    }
    //查
    public List<Slot> getAll() throws SQLException
    {
        String sql ="SELECT slot_id AS SlotId,start_time AS startTime,end_time AS endTime FROM Slot";
        return qr.query(sql, new BeanListHandler<>(Slot.class));
    } 
}