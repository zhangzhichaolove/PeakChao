package com.chao.peak.dao.impl;

import com.chao.peak.bean.UserBean;
import com.chao.peak.dao.UserDaoI;
import com.chao.peak.util.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
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
        String hql = "from com.chao.peak.bean.UserBean where username=? and password=? and status=1";
        Query query = session.createQuery(hql);
        query.setString(0, userBean.getUsername());
        query.setString(1, userBean.getPassword());
        List<UserBean> list = query.list();
        transaction.commit();
        session.close();
        return list.size() > 0;
    }

    @Test
    public void login() {

    }
}
