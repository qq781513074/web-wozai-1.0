<%@ page import="java.util.HashSet" %>
<%@ page import="com.wozai.cache.LoginValueMap" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>我在后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
    <link href='http://fonts.googleapis.com/css?family=Belgrano' rel='stylesheet' type='text/css'>
    <!-- jQuery file -->
    <script src="/js/jquery.min.js"></script>
    <script src="/js/jquery.tabify.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        var $ = jQuery.noConflict();
        $(function() {
            $('#tabsmenu').tabify();
            $(".toggle_container").hide();
            $(".trigger").click(function(){
                $(this).toggleClass("active").next().slideToggle("slow");
                return false;
            });
        });
        function showLocale(today) {
            var year,month,day,hour,minute,second,weekday,strDate;
            weekday = today.getDay();
            switch(weekday) {
                case 0:{
                    strDate = "星期日";
                } break;
                case 1:{
                    strDate = "星期一";
                } break;
                case 2:{
                    strDate = "星期二";
                } break;
                case 3:{
                    strDate = "星期三";
                } break;
                case 4:{
                    strDate = "星期四";
                } break;
                case 5:{
                    strDate = "星期五";
                } break;
                case 6:{
                    strDate = "星期六";
                } break;
            }
            year = today.getYear()
            if(year<1900) year = year+1900;
            month = today.getMonth() + 1 + "";
            day = today.getDate() + "";
            hour = today.getHours() + "";
            minute = today.getMinutes() + "";
            second = today.getSeconds() + "";
            if ( month.length < 2)
                month = "0" + month;
            if ( day.length < 2)
                day = "0" + day;
            if ( hour.length < 2 )
                hour = "0" + hour;
            if ( minute.length < 2 )
                minute = "0" + minute;
            if ( second.length < 2 )
                second = "0" + second;

            return strDate + " " + year + "-"+ month + "-"+ day + " "+ hour + ":"+ minute + ":" + second;
        }

        function tick()
        {
            var today;
            today = new Date();
            document.getElementById("localtime").innerHTML = showLocale(today);
            document.forms
            window.setTimeout("tick()", 1000);
        }

    </script>
</head>
<body onload="tick();">
<div id="panelwrap">

<div class="header">
    <div class="title"><a href="/">后台管理系统</a></div>

    <div class="header_right"><span id="localtime"></span><br />欢迎<b><shiro:user><shiro:principal/>,</shiro:user></b><a href="#" class="settings">Settings</a> <a href="/logout.htm" class="logout">登出</a> </div>

    <div class="menu">
        <ul>
            <li><a href="/web/classroom/search.htm" class="selected">教室课表管理</a></li>
            <li><a href="#">Settings</a></li>
            <li><a href="#">Add a category</a></li>
            <li><a href="#">Edit categories</a></li>
            <li><a href="#">Categories</a></li>
            <li><a href="#">Options</a></li>
            <li><a href="#">Admin settings</a></li>
            <li><a href="#">Help</a></li>
        </ul>
    </div>

</div>

<div class="submenu">
    <ul>
        <li><a href="/web/classroom/search.htm">课表查询</a></li>
        <li><a href="/web/classroom/insert.htm">课表添加</a></li>
        <li><a href="/web/classroom/update.htm">课表修改</a></li>
        <li><a href="/web/classroom/delete.htm">课表删除</a></li>
        <li><a href="/web/system.htm" class="selected">系统参数</a></li>
    </ul>
</div>

<div class="center_content">
    <form action="/web/system.htm" method="post"/>
    <div id="right_wrap">
        <div id="right_content">
            <h2>教室查询</h2>
            <table style="padding: 10px">
                <tr>
                    <td>
                        <div class="form_row">
                            <label>URL</label>
                            <input class="form_input_tab" type="text" name="url" /><span id="errorMsg" style="color: red">${errorMsg}</span><span id="infoMsg" style="color: green">${infoMsg}</span>
                        </div>
                    </td>
                    <td>
                        <div class="form_row">
                            <label>__VIEWSTATE</label>
                            <input class="form_input_tab" type="text" name="__VIEWSTATE" /><span id="errorMsg" style="color: red">${errorMsg}</span><span id="infoMsg" style="color: green">${infoMsg}</span>
                        </div>
                    </td>
                    <td>
                        <input class="form_submit" type="submit" value="修改" />
                    </td>
                </tr>

                <tr>
                    <td>
                        <div class="form_row">
                            <label>URL</label>
                                <span  style="color: green"><%
                                    out.print(LoginValueMap.getUrl());
                                %></span>
                        </div>
                    </td>
                    <td>
                        <div class="form_row">
                            <label>__VIEWSTATE</label>
                           <span style="color: blue"><%
                               out.print(LoginValueMap.get__VIEWSTATE());
                           %></span>
                        </div>
                    </td>
                    <td>
                        <input class="form_submit" type="submit" value="修改" />
                    </td>
                </tr>
            </table>

        </div>
    </div><!-- end of right content-->
    </form>

    <div class="sidebar" id="sidebar">
        <h2>系统情况</h2>

        <ul>
            <li><a href="#">在线人数<%
                out.print(application.getAttribute("curMan")+"人");
            %></a></li>
            <li><a href="#">Web人数<%
                Integer result = 0;
                HashSet<HttpSession> sessions = (HashSet<HttpSession>)application.getAttribute("sessions");
                for(HttpSession se : sessions){
                    if(se.getAttribute("APP") == null){
                        result++;
                    }
                }
                out.print(result+"人");
            %></a></li>
            <li><a href="#">访问次数<%
                out.print(application.getAttribute("loginTimes")+"人");
            %></a></li>
            <li><a href="/javamelody">系统信息</a></li>
        </ul>

    </div>


    <div class="clear"></div>
</div> <!--end of center_content-->

<div class="footer">
    <div>
        <table style="text-align:center;margin-left:auto;margin-right:auto;">
            <tbody>
            <tr >
                <td><a href="http://www.mycodes.net/" title="联系我们" target="_blank">联系我们</a></td>
                <td><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
                <td><a href="http://www.mycodes.net/" title="意见建议" target="_blank">意见建议</a></td>
                <td><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
                <td><a href="http://www.mycodes.net/" title="意见建议" target="_blank">客户端下载</a></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</div>
</body>
</html>