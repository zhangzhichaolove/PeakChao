package com.chao.peak.dao.impl;

import com.chao.peak.bean.UserBean;
import com.chao.peak.bean.WebSite;
import com.chao.peak.dao.UserDaoI;
import com.chao.peak.util.HibernateUtils;
import com.chao.peak.util.JsonUtil;
import org.hibernate.*;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Chao on 2017/8/13.
 */
public class UserDaoImpl implements UserDaoI {

    /**
     * 注册
     */
    @Override
    public boolean register(UserBean userBean) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Serializable save = session.save(userBean);
        transaction.commit();
        session.close();
        return save != null;
    }


    /**
     * 登陆
     */
    @Override
    public boolean login(UserBean userBean) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        //String hql = "from com.chao.peak.bean.UserBean where username=? and password=? and status=1";
        String hql = "from com.chao.peak.bean.UserBean where username=:name and password=:pwd and status=:state";
        Query query = session.createQuery(hql);
        //query.setString(0, userBean.getUsername());
        //query.setString(1, userBean.getPassword());
        //query.setParameter()
        query.setParameter("name", userBean.getUsername());
        query.setParameter("pwd", userBean.getPassword());
        query.setParameter("state", "1");
        List<UserBean> list = query.list();
        transaction.commit();
        session.close();
        return list.size() > 0;
    }

    /**
     * 服务器统计获取
     */
    @Override
    public WebSite findStatistics() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(WebSite.class);
        WebSite webSite = (WebSite) criteria.uniqueResult();
        System.out.println(webSite);
        transaction.commit();
        session.close();
        return webSite;
    }

    @Test
    public void limit() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from com.chao.peak.bean.UserBean ";
        Query query = session.createQuery(hql);
        query.setFirstResult(1);
        query.setMaxResults(2);
        List<UserBean> list = query.list();
        //UserBean o = (UserBean) query.uniqueResult();
        System.out.println(list);
        transaction.commit();
        session.close();
    }

    @Test
    //条件查询
    //HQL语句中,不可能出现任何数据库相关的信息的
    // > 				gt
    // >=				ge
    // <				lt
    // <=				le
    // ==				eq
    // !=				ne
    // in				in
    // between and		between
    // like 			like
    // is not null 		isNotNull
    // is null			isNull
    // or				or
    // and				and
    public void Criteria() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(UserBean.class);
        //criteria.add(Restrictions.eq("username", "test"));//条件
        //criteria.add(Restrictions.or(Restrictions.eq("username", "test"), Restrictions.eq("username", "chao")));//条件
//        criteria.setProjection(Projections.rowCount());//聚合函数
        List<UserBean> list = criteria.list();
        String s = JsonUtil.toJson(list);
        System.out.println(s);
//        Long list = (Long) criteria.uniqueResult();
//        System.out.println(list);
        transaction.commit();
        session.close();
    }

    @Test
    public void sql() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from user";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        List<Object[]> list = sqlQuery.list();
        for (Object[] bean : list) {
            System.out.println(Arrays.toString(bean));
        }
        transaction.commit();
        session.close();
    }

    @Test
    public void sql2() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from user WHERE username=? or username=?";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter(0, "test");
        sqlQuery.setParameter(1, "chao");
        sqlQuery.addEntity(UserBean.class);
        List<UserBean> list = sqlQuery.list();
        System.out.println(list);
        transaction.commit();
        session.close();
    }
}
