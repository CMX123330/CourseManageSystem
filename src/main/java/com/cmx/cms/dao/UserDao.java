package com.cmx.cms.dao;

import java.security.MessageDigest;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.cmx.cms.model.User;
import com.cmx.cms.util.DBUtil;

public class UserDao {
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
    public User getByCredentials(String userId,String password) throws SQLException{
        String sql = "select user_id as userId,password,role,name from user "
        + " WHere user_id = ? and password = ? ";
        return qr.query(sql, new BeanHandler<>(User.class),userId,password);
    }
    public static String md5(String intput) throws Exception
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(intput.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes)
        {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
