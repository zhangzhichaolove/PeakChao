package com.chao.peak.service;

import com.chao.peak.bean.UserBean;
import com.chao.peak.bean.WebSite;

/**
 * Created by Chao on 2017/8/13.
 */
public interface UserServiceI {
    boolean register(UserBean userBean);

    boolean login(UserBean userBean);

    WebSite findStatistics();
}
