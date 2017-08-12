package com.chao.peak.listener;

import com.chao.peak.bean.WebSite;
import com.chao.peak.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * servletContext监听器
 * 初始化一些数据，创建连接池，定时等功能。
 * Created by Chao on 2017/8/12.
 */
@WebListener
public class BaseSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        try {
            List<WebSite> webSite = findWebSite();
            if (webSite != null && webSite.size() > 0) {
                System.out.println("当前访客统计：" + webSite.get(0).getVisitCount());
                updateWebSite(webSite.get(0).getId(), webSite.get(0).getVisitCount() + 1);
            } else {
                createWebSite();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }

    public List<WebSite> findWebSite() throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        return qr.query("SELECT * FROM website;", new BeanListHandler<WebSite>(WebSite.class));
    }

    public void updateWebSite(int id, int count) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        qr.update("update website set visitCount=? where id=?", count, id);
    }

    public void createWebSite() throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        qr.update("INSERT INTO website VALUES(?,?,?)", 1, 1, 1);
    }
}
