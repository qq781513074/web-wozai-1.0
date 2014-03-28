package com.wozai.DTO;

import com.google.gson.Gson;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 13-12-23
 * Time: 下午4:29
 * To change this template use File | Settings | File Templates.
 */
public class RegisterReturnDTO {
    private int resultCode;
    private String resultInfo;
    private long usingTimes;
    private Gson gson = new Gson();
    public void setResultCode(int code){
        this.resultCode = code;
    }
    public void setResultInfo(String resultInfo){
        this.resultInfo = resultInfo;
    }
    public void setUsingTimes(long usingTimes){
        this.usingTimes = usingTimes;
    }

    public int getResultCode(){
        return this.resultCode;
    }
    public String getResultInfo(){
        return this.resultInfo;
    }
    public long getUsingTimes(){
        return this.usingTimes;
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
    public String toJson(){
        return gson.toJson(this,LoginReturnDTO.class);
    }

    public LoginReturnDTO createFromJson(String ajax){
        return gson.fromJson(ajax,LoginReturnDTO.class);
    }
}
