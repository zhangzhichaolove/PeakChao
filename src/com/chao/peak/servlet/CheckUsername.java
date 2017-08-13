package com.chao.peak.servlet;

import com.chao.peak.service.UserService;
import com.chao.peak.util.BaseHttpServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Chao on 2017/8/13.
 */
@WebServlet("/checkUsername")
public class CheckUsername extends BaseHttpServlet {
    @Override
    public void httpRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获得用户名
        String username = req.getParameter("username");
        UserService userService = new UserService();
        boolean isExist = userService.checkUsername(username);

        String json = "{\"isExist\":" + isExist + "}";

        resp.getWriter().write(json);
    }
}
