package com.wozai.common.utils;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 13-12-23
 * Time: 上午9:39
 * To change this template use File | Settings | File Templates.
 */
public class StringUtils {
    public static boolean isNotNull(Object ... args){
        boolean result = true;
        for(int i = 0;i < args.length ; i++){
            if (args == null){
                result = false;
                return   result;
            }
            if(args[i] instanceof String){
                if(((String)args[i]).trim().isEmpty()){
                    result = false;
                    return result;
                }
            }
        }
        return result;
    }
    public static boolean isNull(Object ... args){
        return !isNotNull(args);
    }

}
