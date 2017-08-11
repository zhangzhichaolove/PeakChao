package com.chao.peak.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;

/**
 * Created by Chao on 2017/8/11.
 */
public class EnhanceRequest extends HttpServletRequestWrapper {
    HttpServletRequest request;


    public EnhanceRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        String parameter = request.getParameter(name);
        try {
            parameter = new String(parameter.getBytes("iso8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return parameter;
    }
}
