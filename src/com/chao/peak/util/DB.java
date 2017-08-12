package com.chao.peak.util;

import com.chao.peak.bean.WebSite;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Chao on 2017/8/12.
 */
public class DB {

    public static boolean findId(String tab, int id) {//website
        Connection connection = C3P0Util.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from " + tab + " where id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {//如果存在
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Test
    public void testa() {
        try {
            List<WebSite> webSite = findWebSite();
            if (webSite != null && webSite.size() > 0) {
                updateWebSite(webSite.get(0).getId(), webSite.get(0).getDayCount() + 1);
            } else {
                createWebSite();
            }
            System.out.println("更新完毕!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<WebSite> findWebSite() throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        return qr.query("SELECT * FROM website;", new BeanListHandler<WebSite>(WebSite.class));
    }

    public void updateWebSite(int id, int count) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        qr.update("update website set dayCount=? where id=?", count, id);
    }

    public void createWebSite() throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        qr.update("INSERT INTO website VALUES(?,?,?)", 1, 1, 0);
    }
}
