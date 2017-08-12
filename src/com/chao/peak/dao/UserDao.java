package com.chao.peak.dao;

import com.chao.peak.bean.UserBean;
import com.chao.peak.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Created by Chao on 2017/8/12.
 */
public class UserDao {


    public int register(UserBean userBean) throws SQLException {
        QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());
        String sql = "insert into user values(?,?,?,?,?,?)";
        int update = runner.update(sql, userBean.getId(), userBean.getUsername(), userBean.getPassword(), userBean.getEmail(), userBean.getStatus(), userBean.getActivation());
        return update;
    }

    public boolean login(UserBean userBean) throws SQLException {
        QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());
        String sql = "select * from user where username=? and password=? and status=1";
        UserBean query = runner.query(sql, new BeanHandler<UserBean>(UserBean.class), userBean.getUsername(), userBean.getPassword());
        return query != null;
    }

    public void active(String activeCode) throws SQLException {
        QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());
        String sql = "update user set status=? where activation=?";
        runner.update(sql, 1, activeCode);
    }
}
