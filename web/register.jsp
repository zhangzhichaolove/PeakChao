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
<script src="js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="js/jquery.validate.min.js" type="text/javascript"></script>
<style>
    body {
        text-align: center
    }

    .error {
        color: red
    }
</style>
<script type="text/javascript">
    $(function () {
        $("#registerForm").validate({
            rules: {
                "username": {
                    "required": true
                },
                "password": {
                    "required": true,
                    "rangelength": [6, 12]
                },
                "repassword": {
                    "required": true,
                    "rangelength": [6, 12],
                    "equalTo": "#password"
                },
                "email": {
                    "required": true,
                    "email": true
                },
                "sex": {
                    "required": true
                }
            },
            messages: {
                "username": {
                    "required": "用户名不能为空!"
                },
                "password": {
                    "required": "密码不能为空",
                    "rangelength": "密码长度6-12位"
                },
                "repassword": {
                    "required": "密码不能为空",
                    "rangelength": "密码长度6-12位",
                    "equalTo": "两次密码不一致"
                },
                "email": {
                    "required": "邮箱不能为空",
                    "email": "邮箱格式不正确"
                }
            }
        })
    })
</script>
<body>

<h2>注册</h2>
<form id="registerForm" method="post" action="${pageContext.request.contextPath}/register">
    账号<input type="text" id="username" name="username"></br>
    密码<input type="password" id="password" name="password"></br>
    重复<input type="password" name="repassword"></br>
    邮箱<input type="email" name="email"></br>
    <input type="submit" value="注册"></br>
</form>
</body>
</html>