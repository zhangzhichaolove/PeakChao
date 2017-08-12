package com.chao.peak.servlet;

import com.chao.peak.service.UserService;
import com.chao.peak.util.BaseHttpServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Chao on 2017/8/12.
 */
@WebServlet("/active")
public class ActiveServlet extends BaseHttpServlet {

    @Override
    public void httpRequest(HttpServletRequest req, HttpServletResponse resp) {
        String activeCode = req.getParameter("activeCode");
        UserService userService = new UserService();
        userService.active(activeCode);
        try {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
