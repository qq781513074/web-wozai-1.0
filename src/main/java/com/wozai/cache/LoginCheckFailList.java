package com.wozai.cache;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Vector;

/**
 * Created by zengzihao on 2014/3/24.
 */
@Component
public class LoginCheckFailList<String> extends Vector<String>{
    private static final Logger logger = Logger.getLogger("com.wozai.cache.LoginCheckFailList");
    @Override
    public synchronized boolean add(String string) {
        logger.info("[校验队列]用户"+string+"校验失败");
        return super.add(string);
    }
}
