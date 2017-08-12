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
    <title>注册</title>
</head>
<body>

<h2>注册</h2>
<form method="post" action="${pageContext.request.contextPath}/register">
    账号<input type="text" name="username"></br>
    密码<input type="password" name="password"></br>
    <input type="submit" value="注册"></br>
</form>
</body>
</html>