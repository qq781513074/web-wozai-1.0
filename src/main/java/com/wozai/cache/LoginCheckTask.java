package com.wozai.cache;

import com.wozai.common.utils.catchnuist.SimpleLogin;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zengzihao on 2014/3/23.
 */
public class LoginCheckTask implements Runnable {
    private static final Logger logger = Logger.getLogger("com.wozai.cache.LoginCheckTask");
    private LoginObj obj;
    private LoginSuccessMap <String,LoginObj> map;
    private LoginCheckFailList<String> list;
    public LoginCheckTask(LoginObj obj,LoginSuccessMap <String,LoginObj> map,LoginCheckFailList<String> list){
        this.obj = obj;
        this.map = map;
        this.list = list;
    }
    @Override
    public void run() {
           int i = 3;
           String result = "SUCCESS";
           while (i-- > 0) {
               try {
                   if (obj.getResponse() != null) {

                       LoginObj obj1 = map.get(obj.getUsername());
                       if (obj1 != null && obj1.getUsername().equals(obj.getUsername()) && obj1.getPassword().equals(obj.getPassword())) {
                            logger.info("[mobile]用户从缓存中登录成功");
                           try {
                               HttpServletResponse response = obj.getResponse();
                               response.setStatus(HttpServletResponse.SC_OK);
                               response.setHeader("Content-type","text/html;charset=UTF-8");
                               response.setContentLength(result.getBytes().length);
                               PrintWriter writer = new PrintWriter(response.getOutputStream(),true);
                               writer.println(result);
                               obj.setResponse(null);
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                            return;
                       }
                   }

                   if (HttpLoginHelper.LoginCheck(obj.getUsername(),obj.getPassword())) {
                       result = LoginStatusEnum.SUCCESS.getMsg();
                       if (obj.getResponse() != null) {
                           try {
                               HttpServletResponse response = obj.getResponse();
                               response.setStatus(HttpServletResponse.SC_OK);
                               response.setHeader("Content-type","text/html;charset=UTF-8");
                               response.setContentLength(result.getBytes().length);
                               PrintWriter writer = new PrintWriter(response.getOutputStream(),true);
                               writer.println(result);
                               obj.setResponse(null);
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                           obj.setResponse(null);
                       }
                       map.put(obj.getUsername(), obj);
                   } else {
                       result = LoginStatusEnum.ERROR.getMsg();
                       if (obj.getResponse() != null) {
                           try {
                               HttpServletResponse response = obj.getResponse();
                               response.setStatus(HttpServletResponse.SC_OK);
                               response.setHeader("Content-type","text/html;charset=UTF-8");
                               response.setContentLength(result.getBytes().length);
                               PrintWriter writer = new PrintWriter(response.getOutputStream(),true);
                               writer.println(result);
                               obj.setResponse(null);
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                       } else {
                           list.add(obj.getUsername());
                       }
                   }
                   return;
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }

}
