package com.wozai.cache;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Vector;

/**
 * Created by zengzihao on 2014/3/24.
 */
@Component
public class LoginCheckFailList<String> extends Vector<String>{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginCheckFailList.class);
    @Override
    public synchronized boolean add(String string) {
        logger.info("[校验队列]用户 {} 校验失败",string);
        return super.add(string);
    }
}
