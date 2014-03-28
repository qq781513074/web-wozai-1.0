package com.wozai.cache;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import java.lang.String;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by zengzihao on 2014/3/23.
 */
@Component
public class  LoginSuccessMap <String,LoginObj> extends Hashtable<String,LoginObj> {
    private static final Logger logger = Logger.getLogger("com.wozai.cache.LoginSuccessMap");
    @Override
    public synchronized LoginObj put(String key, LoginObj value) {
        logger.info("[校验队列]用户登录校验成功加入队列");
        return super.put(key, value);
    }
}
