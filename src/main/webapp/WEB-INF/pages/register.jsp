<%@page contentType="text/html;charset=UTF-8"%>
<html>
<head><title>注册</title>
<%--<script type="text/javascript" src="/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="/js/myjs/reg.js"></script>--%>
</head>
<body>
<form action="register.htm" method="POST">
    <div style="font-size:8px;color:red;margin-bottom:12px;margin-left:60px;">${error}</div>
    <table border="1" align="center">
        <tr>
            <td>账号</td>
            <td><input name="userId" value="${userId}"/></td>
        </tr>

        <tr>
            <td>密码</td>
            <td><input type="password" name="userPwd" value="${userPwd}"/></td>
        </tr>
        <tr>
            <td>昵称</td>
            <td><input type="text" name="nickname" value="${nickname}"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="提交"/></td>
        </tr>
    </table>
</form>
</body>
</html>