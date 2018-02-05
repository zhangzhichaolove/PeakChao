package com.chao.peak.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局中文乱码过滤
 * Created by Chao on 2017/8/11.
 */
@WebFilter("/*")
public class EncodeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        System.out.println("收到请求:" + request.getMethod() + "地址：" + request.getLocalAddr() + "端口：" + request.getLocalPort() + request.getContextPath());
        if (request.getMethod().equals("POST")) {
            response.setCharacterEncoding("UTF-8");
            filterChain.doFilter(servletRequest, response);
        } else {
            response.setContentType("text/html;charset=utf-8");
            //EnhanceRequest enhanceRequest = new EnhanceRequest(request);
            filterChain.doFilter(servletRequest, response);
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("EncodeFilter被回收了!");
    }

}
