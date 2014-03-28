package com.wozai.controller.login;

import com.wozai.DTO.HttpRequestUtil;
import com.wozai.common.BaseAjaxController;
import com.wozai.common.utils.catchnuist.SimpleLogin;
import com.wozai.service.UserAccountManagerService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.*;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 13-12-19
 * Time: 下午12:54
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class LoginController extends BaseAjaxController{
    //使用logger打印日志
    private static final Logger logger = Logger.getLogger("com.wozai.controller.login.LoginController");

    @Resource(name = "userAccount")
    UserAccountManagerService userAccountManager;


    @RequestMapping(value="/login.htm",method = RequestMethod.GET)
    public String login(HttpServletRequest request){
        return "login";
    }

    /**
     * 用于登陆账号的校验
     * @param request
     * @param response
     */
    @RequestMapping(value = "/login.htm",method = RequestMethod.POST)
    public String checkUserAccount(String username,String password,Boolean rememberMe,Model model,HttpServletRequest request, HttpServletResponse response) {
        long beginDate = Calendar.getInstance().getTimeInMillis();
        String successView = (request.getHeader("User-Agent").equals("Android-Client")) ? "json" : "redirect:/welcome.htm";
        String errorView = (request.getHeader("User-Agent").equals("Android-Client")) ? "json" : "login";
        setHeader(response);
        if (StringUtils.isBlank(username)) {
            model.addAttribute("error", "请输入用户名");
            model.addAttribute("username", username);
            model.addAttribute("password", password);
            return "login";
        }
        if (StringUtils.isBlank(password)) {
            model.addAttribute("error", "请输入登录密码");
            model.addAttribute("username", username);
            model.addAttribute("password", password);
            return "login";
        }
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        if(Boolean.TRUE.equals(rememberMe)){
            token.setRememberMe(rememberMe);
        }else {
            token.setRememberMe(false);
        }
       try{

           user.login(token);
           HttpSession session = request.getSession();
           session.setAttribute("currUser",username);
           logger.info("login Success ! username = "+token.getUsername());
           return successView;
//              LoginReturnDTO loginReturnDTO = userAccountManager.tryLogin(request,userId,userPwd,model);
//              if (loginReturnDTO.getResultCode() == ReturnCodeEnum.SUCCESS.getCodeNum()){
//                     loginReturnDTO.setUsingTimes(Calendar.getInstance().getTimeInMillis() - beginDate);
//                     model.addAttribute("jsonDTO", new Gson().toJson(loginReturnDTO, LoginReturnDTO.class));
//                     model.addAttribute("loginReturnDTO",loginReturnDTO);
//                  logger.info(new StringBuffer("User:").append(userId).append("Try Login Success!").append("UsingTimes :").append(loginReturnDTO.getUsingTimes()));
//                  return successView;
//              }else {
//                  loginReturnDTO.setUsingTimes(Calendar.getInstance().getTimeInMillis() - beginDate);
//                  logger.info(new StringBuffer("User:").append(userId).append("Try Login Fail!，Reason：").append(loginReturnDTO.getAccountInfo()).append("UsingTimes ：").append(loginReturnDTO.getUsingTimes()));
//                  model.addAttribute("error", loginReturnDTO.getResultInfo());
//                  return errorView;
//              }
       }catch (CredentialsException e) {
           logger.info("用户登录密码错误, msg:" + e.getMessage());
           long usingtimes = Calendar.getInstance().getTimeInMillis() - beginDate;
           logger.info(new StringBuffer("User Try Login Fail! UserId:").append(username).append(e.toString()).append("UsingTimes ：").append(usingtimes));
           model.addAttribute("error", "登陆失败！");
           return errorView;
       }catch (AuthenticationException e) {
           logger.info("用户登录密码错误, msg:" + e.getMessage());
           long usingtimes = Calendar.getInstance().getTimeInMillis() - beginDate;
           logger.info(new StringBuffer("User Try Login Fail! UserId:").append(username).append(e.toString()).append("UsingTimes ：").append(usingtimes));
           model.addAttribute("error", "登陆失败！");
           return errorView;
       }catch (Exception e){
          long usingtimes = Calendar.getInstance().getTimeInMillis() - beginDate;
            logger.info(new StringBuffer("User Try Login Fail! UserId:").append(username).append(e.toString()).append("UsingTimes ：").append(usingtimes),e);
           model.addAttribute("error", "登陆失败！");
           return errorView;
        }
    }

    /**
     * 用于登陆账号的校验
     * @param request
     * @param response
     */
    @RequestMapping(value = "/loginByNuist.htm",method = RequestMethod.POST)
    public String checkUserAccountByNUIST(String username,String password,Boolean rememberMe,Model model,HttpServletRequest request, HttpServletResponse response) {
        String result = null;
        try {
            SimpleLogin login = new SimpleLogin();
            if(login.getLoginPage(username,password)){
                result = "SUCCESS";
                model.addAttribute("jsonDTO","SUCCESS");
            }else{
                result = "ERROR";
                model.addAttribute("jsonDTO","ERROR");
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = "SERVICE_ERROR";
            model.addAttribute("jsonDTO","SERVICE_ERROR");
        } catch (InterruptedException e) {
            e.printStackTrace();
            model.addAttribute("jsonDTO","SERVICE_ERROR");
            result = "SERVICE_ERROR";
        }
        logger.info(username +result);
        return "json";
    }
    @RequestMapping(value = "/logout.htm")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        SecurityUtils.getSubject().logout();
        return "logout";
    }

}
