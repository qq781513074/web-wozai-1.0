<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Welcome!</title>
    <script type="text/javascript" >
        function goUrl(url){
            document.form1.action = url;
            document.form1.submit();
        }
    </script>
</head>
    <body>
    <shiro:guest>
        Hi there!  Please <a href="login.jsp">Login</a> or <a href="signup.jsp">Signup</a> today!
    </shiro:guest>

    Welcome Agent ${accountInfo.userId}!
        <form id="form1" method="post"><input type="button" value="签到" onclick="goUrl()"/></form>
        <input type="button" value="退出登陆" onclick="location.href='logout.htm'" />
        <a href="/base.htm">Test</a>
        <br/>
        <a href="/web/classroom/searchPage.htm" >查找</a>
    </body>

</html>