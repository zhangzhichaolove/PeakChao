package com.chao.peak.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

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

}
