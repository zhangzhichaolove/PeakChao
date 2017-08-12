package com.chao.peak.service;

import com.chao.peak.bean.UserBean;
import com.chao.peak.dao.UserDao;

import java.sql.SQLException;

/**
 * Created by Chao on 2017/8/12.
 */
public class UserService {
    UserDao userDao = new UserDao();

    public boolean register(UserBean userBean) {
        int row = 0;
        try {
            row = userDao.register(userBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row > 0;
    }

    public boolean login(UserBean userBean) throws SQLException {
        return userDao.login(userBean);
    }
}
