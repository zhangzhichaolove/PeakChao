<%--
  Created by IntelliJ IDEA.
  User: Chao
  Date: 2017/8/12
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>

<h2>登陆</h2>
<form method="post" action="${pageContext.request.contextPath}/login">
    账号<input type="text" name="username"></br>
    密码<input type="password" name="password"></br>
    <input type="submit" value="登陆"></br>
    <a href="${pageContext.request.contextPath}/register"><input type="button" value="注册"/></a>
</form>
</body>
</html>
