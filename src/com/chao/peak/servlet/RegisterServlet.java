package com.chao.peak.servlet;

import com.chao.peak.bean.UserBean;
import com.chao.peak.service.UserService;
import com.chao.peak.util.BaseHttpServlet;
import com.chao.peak.util.CommonsUtils;
import com.chao.peak.util.ExecutorServiceUtils;
import com.chao.peak.util.MailUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.mail.MessagingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        userBean.setStatus(null);
        userBean.setActivation(CommonsUtils.getUUID());
        UserService userService = new UserService();
        boolean isRegister = userService.register(userBean);
        try {
            if (isRegister) {//注册成功
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
                String emailMsg = "恭喜您注册成功，请点击下面的连接进行激活账户"
                        + "<a href='http://www.peakchao.com:8080/active?activeCode=" + userBean.getActivation() + "'>"
                        + "http://www.peakchao.com:8080/active?activeCode=" + userBean.getActivation() + "</a>";
                ExecutorServiceUtils.getInstance().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            MailUtils.sendMail(userBean.getEmail(), emailMsg);
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                resp.sendRedirect(req.getContextPath() + "/register.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
