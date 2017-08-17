package com.chao.peak.servlet;

import com.chao.peak.bean.WebSite;
import com.chao.peak.service.UserServiceI;
import com.chao.peak.servlet.impl.RegisterServletImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 主页入口
 * Created by Chao on 2017/8/11.
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceI userServiceI = new RegisterServletImpl();
        WebSite webSite = userServiceI.findStatistics();
//        String name = req.getParameter("name");
//        resp.getWriter().write("----");
//        req.setAttribute("dayCount", 10);
//        req.setAttribute("visitCount", 20);
        //resp.sendRedirect("index.jsp");//重定向
        req.setAttribute("webSite", webSite);
        req.getRequestDispatcher(req.getContextPath() + "/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
