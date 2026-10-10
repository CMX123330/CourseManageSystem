package com.cmx.cms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmx.cms.model.MajorCourse;
import com.cmx.cms.util.DBUtil;

public class MajorCourseDao {
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
    public List<MajorCourse> getAll() throws SQLException {
    String sql = "SELECT major_id AS majorId, course_id AS courseId FROM major_course";
    return qr.query(sql, new BeanListHandler<>(MajorCourse.class));
}
}
