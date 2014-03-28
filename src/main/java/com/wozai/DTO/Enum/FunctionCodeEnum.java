package com.wozai.DTO.Enum;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 14-1-10
 * Time: 下午1:25
 * To change this template use File | Settings | File Templates.
 */
public enum  FunctionCodeEnum {
    LEARN(1,"学习"),
    ACTIVITY(2,"活动"),
    HOUSE(3,"居住"),
    EATING(4,"吃饭");

    private int codeNum;
    private String codeInfo;
    private FunctionCodeEnum(int codeNum, String codeInfo){
        this.codeInfo = codeInfo;
        this.codeNum = codeNum;
    }

    public int getCodeNum() {
        return codeNum;
    }

    public String getCodeInfo() {
        return codeInfo;
    }
}
