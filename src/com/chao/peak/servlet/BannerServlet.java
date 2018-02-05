package com.chao.peak.servlet;

import com.chao.peak.model.BannerModel;
import com.chao.peak.util.BaseHttpServlet;
import com.chao.peak.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chao on 2018-02-05.
 */
@WebServlet("/banner")
public class BannerServlet extends BaseHttpServlet {

    @Override
    public void httpRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BannerModel model = new BannerModel();
        List<String> banner = new ArrayList<>();
        banner.add("./img/1.png");
        banner.add("./img/2.png");
        banner.add("./img/3.png");
        model.setBanner(banner);
        resp.getWriter().write(JsonUtil.toJsonData(model));
    }

}
