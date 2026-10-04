package com.cmx.cms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmx.cms.model.Holiday;
import com.cmx.cms.util.DBUtil;

public class HolidayDao {
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());

    public List<Holiday> getAll() throws SQLException {
        String sql = "SELECT holiday_id AS holidayId, holiday_date AS holidayDate, name FROM holiday "
                + "ORDER BY holiday_date";
        return qr.query(sql, new BeanListHandler<>(Holiday.class));
    }

    public int add(Holiday h) throws SQLException {
        String sql = "INSERT INTO holiday (holiday_id, holiday_date, name) VALUES (?, ?, ?)";
        return qr.update(sql, h.getHolidayId(), h.getHolidayDate(), h.getName());
    }

    public int delete(String holidayId) throws SQLException {
        String sql = "DELETE FROM holiday WHERE holiday_id = ?";
        return qr.update(sql, holidayId);
    }
}
