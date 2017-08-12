package com.chao.peak.servlet;

import com.chao.peak.bean.UserBean;
import com.chao.peak.service.UserService;
import com.chao.peak.util.BaseHttpServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by Chao on 2017/8/12.
 */
@WebServlet("/register")
public class RegisterServlet extends BaseHttpServlet {

    @Override
    public void httpRequest(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String[]> userinfo = req.getParameterMap();
        UserBean userBean = new UserBean();
        try {
            BeanUtils.populate(userBean, userinfo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        userBean.setId(null);
        UserService userService = new UserService();
        boolean isRegister = userService.register(userBean);
        try {
            if (isRegister) {//注册成功
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
            } else {
                resp.sendRedirect(req.getContextPath() + "/register.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
