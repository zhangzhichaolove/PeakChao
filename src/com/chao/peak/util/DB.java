package com.chao.peak.util;

import com.chao.peak.bean.WebSite;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Tset
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

    @Test
    public void fun1() {
        Configuration cfg = new Configuration().configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

//        Customer customer = new Customer();//增加
//        customer.setCust_name("百度");
//        customer.setCust_phone("13594347817");
//        session.save(customer);

        WebSite customer = session.get(WebSite.class, 1L);//查询
//        customer.setCust_name("Google谷歌");
//        session.update(customer);//修改
        System.out.println(customer);

//        session.delete(customer);//删除

        transaction.commit();
        //transaction.rollback();
        session.close();
        sessionFactory.close();
    }
}
