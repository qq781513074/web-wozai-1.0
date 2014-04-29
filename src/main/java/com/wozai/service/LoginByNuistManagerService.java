package com.wozai.service;

import com.wozai.DTO.AccountInfo;
import com.wozai.cache.HttpLoginHelper;
import com.wozai.cache.LoginCheckFailList;
import com.wozai.cache.LoginObj;
import com.wozai.cache.LoginSuccessMap;
import com.wozai.common.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

/**
 * Created by zengzihao on 2014/3/25.
 */
@Service
@Scope("prototype")
public class LoginByNuistManagerService {
    private static final Logger logger = Logger.getLogger("com.wozai.service.LoginByNuistManagerService");
    @Resource
    private LoginSuccessMap<String,LoginObj> loginSuccessMap;
    @Resource
    private LoginCheckFailList<String> loginCheckFailList;
    @Resource
    private UserAccountManagerService userAccount;


    public String login4Nuist(String username,String password,String serialNo) {
        String result = null;
        logger.info("[mobile]调用loginByNuist接口 username :" + username);
        if (StringUtils.isNotNull(username, password)) {
            LoginObj obj = new LoginObj();
            obj.setUsername(username);
            obj.setPassword(password);
            obj.setLastTime(new Date());
            obj.setSerialNo(serialNo);
            if (loginSuccessMap.containsKey(username)) {
                LoginObj user = loginSuccessMap.get(username);
                if (user.getUsername().equals(obj.getUsername()) && user.getPassword().equals(obj.getPassword())) {
                    obj.setSerialNo(serialNo);
                    logger.info("[mobile] user" + username + "从缓存中登陆成功");
                    AccountInfo info = new AccountInfo();
                    info.setUserId(username);
                    info.setCreateTime(new Date());
                    info.setUserStatus("学号登陆|正常");
                    info.setJfen(0);
                    userAccount.addUser(info);
                    return "SUCCESS";
                }
            }
            try {
                if (HttpLoginHelper.LoginCheck(username, password)) {
                    loginSuccessMap.put(username, obj);
                    logger.info("[mobile] user"+username+"从web中登陆成功");
                    AccountInfo info = new AccountInfo();
                    info.setUserId(username);
                    info.setCreateTime(new Date());
                    info.setUserStatus("学号登陆|正常");
                    info.setJfen(0);
                    userAccount.addUser(info);
                    return "SUCCESS";
                } else {
                    return "FAILED";
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "ERROR";
            }
        }else {
            return "ERROR";
        }
    }

}
