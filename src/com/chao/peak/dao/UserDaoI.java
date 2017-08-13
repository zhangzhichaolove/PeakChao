package com.chao.peak.dao;

import com.chao.peak.bean.UserBean;

/**
 * Created by Chao on 2017/8/13.
 */
public interface UserDaoI {
    boolean register(UserBean userBean);

    boolean login(UserBean userBean);
}
