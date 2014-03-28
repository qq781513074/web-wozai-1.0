package com.wozai.listener;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

/**
 * Created by zengzihao on 14-3-20.
 */
public class MyHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session= se.getSession();
        ServletContext application=session.getServletContext();
        HashSet<HttpSession> sessions=(HashSet<HttpSession>)application.getAttribute("sessions");
        Integer maxMan = (Integer)application.getAttribute("maxMan");
        Integer loginTimes = (Integer)application.getAttribute("loginTimes");
        Integer curMan = (Integer)application.getAttribute("curMan");
        Integer appMan = (Integer)application.getAttribute("appMan");
        Integer webMan = (Integer)application.getAttribute("webMan");
        if(sessions==null){
            sessions=new HashSet<HttpSession>();
            maxMan = new Integer(1);
            loginTimes = new Integer(1);
            curMan = new Integer(0);
            appMan = new Integer(0);
            webMan = new Integer(0);
            application.setAttribute("curMan",curMan);
            application.setAttribute("appMan",appMan);
            application.setAttribute("webMan",webMan);
            application.setAttribute("sessions",sessions);
            application.setAttribute("maxMan",maxMan);
            application.setAttribute("loginTimes",loginTimes);
        }
        sessions.add(session);
        application.setAttribute("curMan",sessions.size());
        application.setAttribute("loginTimes",++loginTimes);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session= se.getSession();
        ServletContext application=session.getServletContext();
        HashSet<HttpSession> sessions=(HashSet<HttpSession>)application.getAttribute("sessions");
        sessions.remove(session);
        application.setAttribute("curMan",sessions.size());
    }

}
