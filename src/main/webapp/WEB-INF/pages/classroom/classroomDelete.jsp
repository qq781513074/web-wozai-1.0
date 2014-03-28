<%@ page import="java.util.HashSet" %>
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
        function doSubmit(flag){
            var url = "";
            if(flag == 1){
                url = "/web/classroom/update.htm";
            }
            if(flag == 0){
                url = "/web/classroom/delete.htm";
            }
            var bool = window.confirm("即将进行插入教室表数据,确定修改?");
            if(bool){
                document.form1.action = url;
                document.form1.submit();
            }else{
                return;
            }
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
        <li><a href="/web/classroom/delete.htm" class="selected">课表删除</a></li>
        <li><a href="/web/system.htm" >系统参数</a></li>
    </ul>
</div>

<div class="center_content">

<div id="right_wrap">

    <div id="right_content">
        <span id="errorMsg" style="color: red">${errorMsg}</span><span id="infoMsg" style="color: green">${infoMsg}</span>
        <form id="form1" name="form1" method="POST"/>
        <h2>教室信息删除</h2>
        <table style="padding: 10px">
            <tr>
                <td>
                    <div class="form_row_td">
                        <label>教室名称</label>
                        <input class="form_input_tab" type="text" name="classroom_name" />
                    </div>
                </td>
                <td>
                    <div class="form_row_td">
                        <label>星期</label>
                        <input class="form_input_tab" type="text" name="class_date" />
                    </div>
                </td>
                <td>
                    <div class="form_row_td">
                        <label>操作</label>
                        <select class="form_select" name="del_flag">
                            <option value="1" selected="selected">教室恢复</option>
                            <option value="0">教室删除</option>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <input class="form_submit" type="submit" value="普通操作" onclick="doSubmit(1)" />
                </td>
                <td>
                    <input class="form_submit" type="submit" value="物理操作" onclick="doSubmit(0)" />
                </td>
            </tr>
        </table>
        </form>

        <form action="/web/classroom/delete.htm" method="get"/>
        <h2>教室查询</h2>
        <table style="padding: 10px">
            <tr>
                <td>
                    <div class="form_row_td">
                        <label>教室名称</label>
                        <input class="form_input_tab" type="text" name="classroom_name" />
                    </div>
                </td>
                <td>
                    <select class="form_select" name="del_flag">
                        <option value="1" selected="selected">正常</option>
                        <option value="0">已删除</option>
                    </select>
                </td>
                <td>
                    <input class="form_submit" type="submit" value="查询" />
                </td>
            </tr>
        </table>
        </form>
        <h2>教室查询结果</h2>


        <table id="rounded-corner">
            <thead>
            <tr>
                <th></th>
                <th>教室名称</th>
                <th>当前人数</th>
                <th>最大人数</th>
                <th>地点Id</th>
                <th>当前状态</th>
                <th>当前状态码</th>
                <th>教室类型</th>
                <th>星期</th>
                <th>课程情况</th>
                <th>是否已删除</th>
            </tr>
            </thead>
            <tfoot>
            <tr>
                <td colspan="12">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut.</td>
            </tr>
            </tfoot>
            <tbody>
            <c:if test="${classList!=null}">
            <c:forEach var="info" items="${classList}" varStatus="obj">
            <c:if test="${obj.count%2 == '0'}">
            <tr class="odd">
                </c:if>
                <c:if test="${obj.count%2 == '1'}">
            <tr class="even">
                </c:if>
                <td>${obj.count}</td>
                <td>${info.classroom_name}</td>
                <td>${info.classroom_cur_man}</td>
                <td>${info.classroom_max_man}</td>
                <td>${info.loc_id}</td>
                <td>${info.curr_status}</td>
                <td>${info.curr_status_code}</td>
                <td>${info.classroom_type}</td>
                <td>${info.class_date}</td>
                <td>${info.class1}${info.class2}${info.class3}${info.class4}${info.class5}${info.class6}${info.class7}${info.class8}${info.class9}${info.class10}${info.class11}${info.class12}</td>
                <td>${info.del_flag}</td>
            </tr>
            </c:forEach>
            </c:if>
            <%--
            <tr class="odd">
                <td><input type="checkbox" name="" /></td>
                <td>Box Software</td>
                <td>Lorem ipsum dolor sit amet consectetur</td>
                <td>45$</td>
                <td>10/04/2011</td>
                <td>web design</td>
                <td>Alex</td>
                <td><a href="#"><img src="images/edit.png" alt="" title="" border="0" /></a></td>
                <td><a href="#"><img src="images/trash.gif" alt="" title="" border="0" /></a></td>
            </tr>
            <tr class="even">
                <td><input type="checkbox" name="" /></td>
                <td>Trial Software</td>
                <td>Lorem ipsum dolor sit amet consectetur</td>
                <td>155$</td>
                <td>12/04/2011</td>
                <td>web design</td>
                <td>Carrina</td>
                <td><a href="#"><img src="images/edit.png" alt="" title="" border="0" /></a></td>
                <td><a href="#"><img src="images/trash.gif" alt="" title="" border="0" /></a></td>
            </tr>
            <tr class="odd">
                <td><input type="checkbox" name="" /></td>
                <td>Hosting Pack</td>
                <td>Lorem ipsum dolor sit amet consectetur</td>
                <td>45$</td>
                <td>10/04/2011</td>
                <td>web design</td>
                <td>Alex</td>
                <td><a href="#"><img src="images/edit.png" alt="" title="" border="0" /></a></td>
                <td><a href="#"><img src="images/trash.gif" alt="" title="" border="0" /></a></td
                        ></tr>
            <tr class="even">
                <td><input type="checkbox" name="" /></td>
                <td>Duo Software</td>
                <td>Lorem ipsum dolor sit amet consectetur</td>
                <td>745$</td>
                <td>10/04/2011</td>
                <td>web design</td>
                <td>Alex</td>
                <td><a href="#"><img src="images/edit.png" alt="" title="" border="0" /></a></td>
                <td><a href="#"><img src="images/trash.gif" alt="" title="" border="0" /></a></td
                        ></tr>
            <tr class="odd">
                <td><input type="checkbox" name="" /></td>
                <td>Alavasti Software</td>
                <td>Lorem ipsum dolor sit amet consectetur</td>
                <td>45$</td>
                <td>10/04/2011</td>
                <td>web design</td>
                <td>John</td>
                <td><a href="#"><img src="images/edit.png" alt="" title="" border="0" /></a></td>
                <td><a href="#"><img src="images/trash.gif" alt="" title="" border="0" /></a></td
                        ></tr>
            <tr class="even">
                <td><input type="checkbox" name="" /></td>
                <td>Box Software</td>
                <td>Lorem ipsum dolor sit amet consectetur</td>
                <td>45$</td>
                <td>10/04/2011</td>
                <td>web design</td>
                <td>Doe</td>
                <td><a href="#"><img src="images/edit.png" alt="" title="" border="0" /></a></td>
                <td><a href="#"><img src="images/trash.gif" alt="" title="" border="0" /></a></td
                        ></tr>


            </tbody>--%>
        </table>
    </div>
</div><!-- end of right content-->

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