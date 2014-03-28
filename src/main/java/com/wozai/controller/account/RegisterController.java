package com.wozai.controller.account;

import com.google.gson.Gson;
import com.wozai.DTO.AccountInfo;
import com.wozai.DTO.Enum.ReturnCodeEnum;
import com.wozai.DTO.RegisterReturnDTO;
import com.wozai.common.BaseAjaxController;
import com.wozai.service.UserAccountManagerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 13-12-23
 * Time: 下午4:10
 * To change this template use File | Settings | File Templates.
 */
@RequestMapping("/common")
@Controller
public class RegisterController extends BaseAjaxController{
    private static final Logger logger = Logger.getLogger("com.wozai.controller.login.RegisterController");
    private static final Gson gson = new Gson();
    @Resource(name = "userAccount")
    UserAccountManagerService userAccountManager;

    @RequestMapping(value="/register.htm",method = RequestMethod.GET)
    public String  getRegister(){
        return "register";
    }

    @RequestMapping(value="/checkUserId.htm")
    public String checkUserId(String userId,Model model,HttpServletResponse response,HttpServletRequest request){
          boolean result = userAccountManager.userIdIsExsit(userId);
          setHeader(response);
          model.addAttribute("jsonDTO",gson.toJson(result));
          return "json";
    }
    @RequestMapping(value="/register.htm",method = RequestMethod.POST)
    public String register(Model model,HttpServletRequest request,HttpServletResponse response,AccountInfo accountInfo,String jsonDTO){
        long beginDate = Calendar.getInstance().getTimeInMillis();
        String successView = (request.getHeader("User-Agent").equals("Android-Client")) ? "json" : "login";
        String errorView = (request.getHeader("User-Agent").equals("Android-Client")) ? "json" : "register";
        setHeader(response);

        try{
            if(jsonDTO != null){
                accountInfo = gson.fromJson(jsonDTO,AccountInfo.class);
            }
           RegisterReturnDTO registerReturnDTO =  userAccountManager.tryRegister(request,accountInfo,model);
             if (registerReturnDTO.getResultCode() == ReturnCodeEnum.SUCCESS.getCodeNum()){
                registerReturnDTO.setUsingTimes(Calendar.getInstance().getTimeInMillis() - beginDate);
                 model.addAttribute("jsonDTO",gson.toJson(registerReturnDTO,RegisterReturnDTO.class));
                 model.addAttribute("userPwd","");
                 logger.info(new StringBuffer("Register Success! userId = ").append(accountInfo.getUserId()).append("UsingTimes = ").append(Calendar.getInstance().getTimeInMillis() - beginDate));
                 return successView;
            } else {
                 registerReturnDTO.setUsingTimes(Calendar.getInstance().getTimeInMillis() - beginDate);
                 model.addAttribute("jsonDTO",gson.toJson(registerReturnDTO,RegisterReturnDTO.class));
                 model.addAttribute("error",registerReturnDTO.getResultInfo());
                 logger.info(new StringBuffer("Register Fail INFO：").append(accountInfo).append("    ").append(jsonDTO).append(registerReturnDTO.getResultInfo()).append("用时：").append(Calendar.getInstance().getTimeInMillis() - beginDate));
                 return errorView;
             }
        }catch (Exception e){
            long usingtimes = Calendar.getInstance().getTimeInMillis() - beginDate;
            logger.info(new StringBuffer("Register Fail INFO：").append(accountInfo).append("    ").append(jsonDTO).append(e.toString()).append("用时：").append(usingtimes));
            model.addAttribute("error", "注册失败！");
            return errorView;
        }
    }
}
