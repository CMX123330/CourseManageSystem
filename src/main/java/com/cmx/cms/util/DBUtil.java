package com.cmx.cms.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;

public class DBUtil{
    private static final DruidDataSource datasource= new DruidDataSource();
    static{
        Properties props = new Properties();
        try {
            props.load(DBUtil.class.getClassLoader().getResourceAsStream("config.properties"));

        } catch (Exception e) {
            throw new RuntimeException("读取config.properties失败",e);
        }
            datasource.setUrl(props.getProperty("jdbc.url"));
            datasource.setUsername(props.getProperty("jdbc.username"));
            datasource.setPassword(props.getProperty("jdbc.password"));
    }
    public static Connection getConnection() throws SQLException
    {
        return datasource.getConnection();
    }
    public static DataSource getDataSource()
    {
        return datasource;
    }
}