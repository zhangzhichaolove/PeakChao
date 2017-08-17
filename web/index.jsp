<%--
  Created by IntelliJ IDEA.
  User: Chao
  Date: 2017/8/11
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="image/icon.png" type="image/gif">
    <title>巅峰-官方主页</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            list-style: none;
            border: none;
        }

        #zzsc {
            width: 920px;
            margin: 40px auto;
        }

        .text {
            text-align: center;
            /*margin-top: 10%;*/
            height: 100px;
            color: red;
            /*background:red;*/
            width: 100%;
            position: absolute;
            bottom: 0;
            left: 0;
        }
    </style>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="js/zzsc.js"></script>
</head>
<body>
<div id="zzsc"></div>
<div style="text-align:center;clear:both;margin-top:20px">
    <script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>
    <script src="/follow.js" type="text/javascript"></script>
</div>

<div class="text">服务器运行天数：${webSite.dayCount}<%
//    WebSite date = (WebSite) request.getAttribute("webSite");
//    out.println(date.getDayCount());
%>天&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    当前访问总数：${webSite.visitCount}次
</div>
<%--<div class="text" style=" text-align:center;">这里是想要居中的文字</div>--%>
</body>
</html>
