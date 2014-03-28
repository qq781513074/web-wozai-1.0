package com.wozai.listener;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

/**
 * Created by zengzihao on 14-3-20.
 */
@Component
public class ContextHelper implements ServletContextAware {
    public static ContextHelper contextHelper;
    private ServletContext context;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }
    public Integer getCurMan(){
        Integer result = 0;
        HashSet<HttpSession> sessions = (HashSet<HttpSession>)context.getAttribute("sessions");
        result = sessions.size();
        return result;
    }
    public Integer getAppMan(){
        Integer result = 0;
        HashSet<HttpSession> sessions = (HashSet<HttpSession>)context.getAttribute("sessions");
        for(HttpSession session : sessions){
            //todo
            if(session.getAttribute("APP") != null){
               result++;
            }
        }
        return result;
    }
    public Integer getWebMan(){
        Integer result = 0;
        HashSet<HttpSession> sessions = (HashSet<HttpSession>)context.getAttribute("sessions");
        for(HttpSession session : sessions){
            if(session.getAttribute("App") == null){
                result++;
            }
        }
        return result;
    }

    public Integer getMaxMan(){
        return (Integer)context.getAttribute("maxMan");
    }

    public Integer getLoginTimes(){
        return (Integer)context.getAttribute("loginTimes");
    }
}
