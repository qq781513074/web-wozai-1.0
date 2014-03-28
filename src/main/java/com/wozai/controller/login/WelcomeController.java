package com.wozai.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 13-12-23
 * Time: 下午2:31
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class WelcomeController {
    @RequestMapping(value="/welcome.htm",method = RequestMethod.GET)
    public String login(HttpServletRequest request){
        return "welcome";
    }
    @RequestMapping(value="/base.htm",method = RequestMethod.GET)
    public String getBase(HttpServletRequest request){
        return "base";
    }
}
