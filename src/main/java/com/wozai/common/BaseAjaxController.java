package com.wozai.common;
import com.wozai.DTO.JsonDTO;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.multiaction.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.io.PrintWriter;
import java.text.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 13-12-19
 * Time: 下午1:08
 * To change this template use File | Settings | File Templates.
 */
public class BaseAjaxController extends MultiActionController{
    private static final Logger logger = Logger.getLogger(BaseAjaxController.class);
    private static DateFormat normalDateFormat;
    private static DateFormat otherDateFormat;


    public void outJsonString(HttpServletResponse response, JsonDTO jsonDTO) {
        PrintWriter pw = null;
        try {
            response.setContentType("text/javascript;charset=UTF-8");
            response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
             pw = response.getWriter();
            if (jsonDTO instanceof JsonDTO){
                String json =  ((JsonDTO) jsonDTO).toJson();
                logger.info(json);
                pw.println(json);
            }
            pw.flush();
        }catch (Exception e){
            logger.info((new StringBuffer()).append("BaseAjaxController | outJsonString | ").append(jsonDTO).append(" | error:").append(e.getMessage()).toString());
        }finally {
           if (pw != null)
                 pw.close();
        }
    }
    public void setHeader(HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
    }
    /**
     * 将Request中的请求参数绑定到对象上
     *
     * @param request
     * @param object
     */
    protected void bindRequestParam(HttpServletRequest request, Object object) {
        ServletRequestDataBinder binder = new ServletRequestDataBinder(object);
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                try {
                    setValue(normalDateFormat.parse(value));
                } catch (ParseException e) {
                    try {
                        setValue(otherDateFormat.parse(value));
                    } catch (ParseException e1) {
                        setValue(null);
                    }
                }
            }

            public String getAsText() {
                return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format((Date) getValue());
            }

        });
        binder.bind(request);
    }

    public void printOptInfo(Logger logger,HttpServletRequest request,Boolean needSession){
        StringBuffer sb = new StringBuffer();
        sb.append("{IP:").append(getIpAddr(request));
        sb.append("&Port:").append(request.getRemotePort());
        sb.append("&Brower:").append(request.getHeader("User-Agent"));
        sb.append("&currUser:").append(request.getSession().getAttribute("currUser"));
        if(needSession){
            sb.append("&session:").append(request.getSession().getId());
        }
        sb.append("}");
        logger.info("[Web] begin doSomething info = " + sb.toString());
    }
    public void printOptInfo(Logger logger,HttpServletRequest request){
        printOptInfo(logger,request,true);
    }


    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
