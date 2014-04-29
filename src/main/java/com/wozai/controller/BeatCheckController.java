package com.wozai.controller;

import com.wozai.cache.LoginObj;
import com.wozai.cache.LoginSuccessMap;
import com.wozai.common.BaseAjaxController;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zengzihao on 2014/3/26.
 */
@Scope("prototype")
@Controller
public class BeatCheckController extends BaseAjaxController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BeatCheckController.class);
    @Resource
    private LoginSuccessMap<String,LoginObj> loginSuccessMap;
    @RequestMapping("/beatCheck.htm")
    public @ResponseBody String checkNet(HttpServletRequest request, HttpServletResponse response,String username,String serialNo){
        logger.info("[心跳线程]username = {} serialNo = {}" ,username,serialNo);
        if (loginSuccessMap.containsKey(username)){
            if (((LoginObj) loginSuccessMap.get(username)).getSerialNo() == null){
                ((LoginObj) loginSuccessMap.get(username)).setSerialNo(serialNo);
                return "T";
            }
            if(((LoginObj) loginSuccessMap.get(username)).getSerialNo().equals(serialNo)){
                return "T";
            }else {
                return "R";
            }
        }
        return "F";
    }
}
