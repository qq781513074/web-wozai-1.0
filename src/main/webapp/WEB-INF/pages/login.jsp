<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>后台登陆</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
    <link href='http://fonts.googleapis.com/css?family=Belgrano' rel='stylesheet' type='text/css'>
</head>
<body>
<div id="loginpanelwrap">

    <div class="loginheader">
        <div class="logintitle"><a href="#">欢迎登陆</a></div>
    </div>
    <form action="/login.htm" method="post">

    <div class="loginform">
        <div class="loginform_row">
            <span style="text-align: center;color: red">${error}</span>
        </div>
        <div class="loginform_row">
            <label>账号:</label>
            <input type="text" class="loginform_input" name="username" />
        </div>
        <div class="loginform_row">
            <label>密码:</label>
            <input type="password" class="loginform_input" name="password" />
        </div>
        <div class="loginform_row">
            <input type="submit" class="loginform_submit" value="登陆" />
        </div>
        <div class="clear"></div>
    </div>
    </form>
</div>
</body>
</html>