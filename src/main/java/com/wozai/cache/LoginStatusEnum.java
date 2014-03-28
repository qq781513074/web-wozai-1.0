package com.wozai.cache;

/**
 * Created by zengzihao on 2014/3/24.
 */
public enum  LoginStatusEnum {
   SUCCESS(1,"SUCCESS"),FAILED(2,"SERVICE_ERROR"),ERROR(3,"ERROR");
    private int code;
    private String msg;
    private LoginStatusEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public int getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }
}
