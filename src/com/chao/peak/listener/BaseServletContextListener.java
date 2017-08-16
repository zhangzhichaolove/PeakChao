package com.chao.peak.listener;

import com.chao.peak.bean.WebSite;
import com.chao.peak.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * servletContext监听器
 * 初始化一些数据，创建连接池，定时等功能。
 * Created by Chao on 2017/8/12.
 */
@WebListener
public class BaseServletContextListener implements ServletContextListener {


    /**
     * 监听servletContext域对象的创建
     *
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("context创建了！");
        //得到时间类
        Calendar date = Calendar.getInstance();
        //设置时间为 xx-xx-xx 00:00:00
        date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE) + 1, 0, 0, 0);
        //一天的毫秒数
        long daySpan = 24 * 60 * 60 * 1000;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {//每天凌晨00：00执行(网站开启天数统计)
            @Override
            public void run() {
                System.out.println("任务执行了！");
                try {
                    List<WebSite> webSite = findWebSite();
                    if (webSite != null && webSite.size() > 0) {
                        //updateWebSite(webSite.get(0).getId(), webSite.get(0).getDayCount() + 1);
                    } else {
                        createWebSite();
                    }
                    System.out.println("更新完毕!");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, date.getTime(), daySpan);
    }

    /**
     * 监听servletContext域对象的销毁
     *
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("context销毁了！");
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
