package com.wozai.cache;


import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by zengzihao on 2014/3/25.
 */
public class LoginAuthTaskJob {
    private static final Logger logger = Logger.getLogger("com.wozai.cache.LoginAuthTaskJob");

    private LoginSuccessMap <String,LoginObj> loginSuccessMap;

    private LoginCheckFailList<String> loginCheckFailList;

    public LoginAuthTaskJob(LoginSuccessMap<String, LoginObj> loginSuccessMap, LoginCheckFailList<String> loginCheckFailList){
        this.loginSuccessMap = loginSuccessMap;
        this.loginCheckFailList = loginCheckFailList;
    }
    public void authCheck(){
        logger.info("[缓存队列]开始校验Nuist账号和密码");
        if (loginSuccessMap.size() == 0){
            return;
        }

        for(String username :loginSuccessMap.keySet()){
        try {
            if (!HttpLoginHelper.LoginCheck(loginSuccessMap.get(username).getUsername(),loginSuccessMap.get(username).getPassword())){
                loginCheckFailList.add(username);
            }else {
                logger.info("[缓存队列]用户："+username+"校验Nuist账号和密码成功");
            }
            } catch (IOException e) {
                e.printStackTrace();
                 logger.info("[缓存队列]用户："+username+"校验Nuist账号和密码异常",e);
            }
        }
        for(String username : loginCheckFailList){
            loginSuccessMap.remove(loginCheckFailList);
        }
        logger.info("[缓存队列]开始校验Nuist账号和密码完成,去除了"+loginCheckFailList.size());
        loginCheckFailList.clear();

    }
}
