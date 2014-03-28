package com.wozai.controller;

import com.google.gson.Gson;
import com.wozai.cache.*;
import com.wozai.common.BaseAjaxController;
import com.wozai.common.utils.StringUtils;
import com.wozai.common.utils.catchnuist.SimpleLogin;
import com.wozai.service.LoctionManagerService;
import com.wozai.service.LoginByNuistManagerService;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.SimpleThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by Administrator on 14-3-7.
 */
@RequestMapping("/mobile")
@Controller
public class AndroidController extends BaseAjaxController {
    private static final Logger logger = Logger.getLogger("com.wozai.controller.AndroidController");
    private static final Gson gson = new Gson();

    @Resource
    private LoctionManagerService loctionManagerService;
    @Resource
    private LoginByNuistManagerService loginByNuistManagerService;
    @Resource
    private LoginSuccessMap<String,LoginObj> loginSuccessMap;
    @Resource
    private LoginCheckFailList<String> loginCheckFailList;
    @RequestMapping(value = "/search.htm",method = RequestMethod.GET)
    public String checkUserAccount(String lng,String lat,String condition,String startIdx,String endIdx,Model model,HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("jsonDTO",loctionManagerService.searchClassroom(lng,lat,condition,startIdx,endIdx).toJson());
        return "json";
    }

    /**
     * 用于登陆账号的校验
     * @param request
     * @param response
     */
    @RequestMapping(value = "/loginByNuist.htm")
    public @ResponseBody String checkUserAccountByNUIST(String username,String password,String serialNo,Boolean rememberMe,Model model,HttpServletRequest request, HttpServletResponse response) {
        super.printOptInfo(logger,request,true);
        return loginByNuistManagerService.login4Nuist(username,password,serialNo);
    }

    public @ResponseBody String checkNet(HttpServletRequest request, HttpServletResponse response,String username,String serialNo){
        if (loginSuccessMap.containsKey(username)){
            if(((LoginObj) loginSuccessMap.get(username)).getSerialNo().equals(serialNo)){
                return "T";
            }
        }
        return "F";
    }
}
