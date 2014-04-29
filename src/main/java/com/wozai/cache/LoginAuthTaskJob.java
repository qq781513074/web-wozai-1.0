package com.wozai.cache;


import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by zengzihao on 2014/3/25.
 */
public class LoginAuthTaskJob {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginAuthTaskJob.class);

    private LoginSuccessMap <String,LoginObj> loginSuccessMap;

    private LoginCheckFailList<String> loginCheckFailList;

    public LoginAuthTaskJob(LoginSuccessMap<String, LoginObj> loginSuccessMap, LoginCheckFailList<String> loginCheckFailList){
        this.loginSuccessMap = loginSuccessMap;
        this.loginCheckFailList = loginCheckFailList;
    }
    public void authCheck(){
        HttpLoginHelper.setParam();
        logger.info("[缓存队列]开始校验Nuist账号和密码");
        if (loginSuccessMap.size() == 0){
            return;
        }

        for(String username :loginSuccessMap.keySet()){
        try {
            if (!HttpLoginHelper.LoginCheck(loginSuccessMap.get(username).getUsername(),loginSuccessMap.get(username).getPassword())){
                logger.info("[缓存队列]用户：username = {} 从web校验Nuist账号和密码失败",username);
                loginCheckFailList.add(username);
            }else {
                logger.info("[缓存队列]用户：username = {}校验Nuist账号和密码成功",username);

            }
            } catch (IOException e) {
                e.printStackTrace();
                 logger.error("[缓存队列]用户：username = {}校验Nuist账号和密码异常",username,e);
            }
        }
        for(String username : loginCheckFailList){
            loginSuccessMap.remove(username);
        }
        logger.info("[缓存队列]开始校验Nuist账号和密码完成,去除了 {}",loginCheckFailList.size());
        loginCheckFailList.clear();
        System.gc();
    }
}
