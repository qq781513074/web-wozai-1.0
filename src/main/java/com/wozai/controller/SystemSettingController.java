package com.wozai.controller;

import com.wozai.cache.LoginValueMap;
import com.wozai.common.BaseAjaxController;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zengzihao on 2014/3/25.
 */
@RequestMapping("/web")
@Controller
public class SystemSettingController extends BaseAjaxController{

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SystemSettingController.class);

    @RequestMapping(value = "/system.htm",method = RequestMethod.GET)
    public String getSystemPage(HttpServletRequest request, HttpServletResponse response){
        super.printOptInfo(logger,request);
        return "classroom/system";
    }
    @RequestMapping(value = "/system.htm",method = RequestMethod.POST)
    public String setSystemPage(String __VIEWSTATE,String url,HttpServletRequest request, HttpServletResponse response){
        super.printOptInfo(logger,request);
        LoginValueMap.set__VIEWSTATE(__VIEWSTATE);
        LoginValueMap.setUrl(url);
        return "classroom/system";
    }

}
