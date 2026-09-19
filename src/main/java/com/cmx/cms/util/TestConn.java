package com.cmx.cms.util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class TestConn{
    public static void main(String[] args) {
        try(Connection conn = DBUtil.getConnection()){
            System.err.println("数据库连接成功"+conn);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM student");
            
            if(rs.next())
            {
                System.err.println("学生数量："+rs.getInt(1));
            }
        }
        catch(SQLException e)
        {
            System.err.println("连接失败");
            e.printStackTrace();
        }
    }
}