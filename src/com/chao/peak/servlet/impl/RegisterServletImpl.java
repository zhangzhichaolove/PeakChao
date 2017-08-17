package com.chao.peak.servlet.impl;

import com.chao.peak.bean.UserBean;
import com.chao.peak.bean.WebSite;
import com.chao.peak.dao.UserDaoI;
import com.chao.peak.dao.impl.UserDaoImpl;
import com.chao.peak.service.UserServiceI;

/**
 * Created by Chao on 2017/8/12.
 */
public class RegisterServletImpl implements UserServiceI {
    private UserDaoI userDaoI = new UserDaoImpl();

    @Override
    public boolean register(UserBean userBean) {
        return userDaoI.register(userBean);
    }

    @Override
    public boolean login(UserBean userBean) {
        return userDaoI.login(userBean);
    }

    @Override
    public WebSite findStatistics() {
        return userDaoI.findStatistics();
    }
}
