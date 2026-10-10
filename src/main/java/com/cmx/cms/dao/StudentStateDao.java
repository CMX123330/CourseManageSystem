package com.cmx.cms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.cmx.cms.util.DBUtil;
import com.cmx.cms.model.StudentState;

public class StudentStateDao {
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
    public int add(StudentState s) throws SQLException{
        String sql = "insert into student_state (student_id,semester_id,energy,mood,diligence,attendance_rate)"
        +"values(?,?,?,?,?,?)";
        return qr.update(sql,s.getStudentId(),s.getSemesterId(),s.getEnergy(),s.getMood(),s.getDiligence(),s.getAttendanceRate());
    }
    public int update(StudentState s) throws SQLException{
        String sql = "update student_state set energy=?,mood=?,diligence=?,attendance_rate=? where student_id=? and semester_id=?";
        return qr.update(sql,s.getEnergy(),s.getMood(),s.getDiligence(),s.getAttendanceRate(),s.getStudentId(),s.getSemesterId());
    }
    public List<StudentState> getAll() throws SQLException{
        String sql = "select student_id as studentId,semester_id as semesterId,energy,mood,diligence,attendance_rate as attendanceRate from student_state";
        return qr.query(sql, new BeanListHandler<>(StudentState.class));
    }
}
