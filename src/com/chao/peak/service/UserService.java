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

    public void active(String activeCode) {
        try {
            userDao.active(activeCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //校验用户名是否存在
    public boolean checkUsername(String username) {
        Long isExist = 0L;
        try {
            isExist = userDao.checkUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isExist > 0;
    }
}
