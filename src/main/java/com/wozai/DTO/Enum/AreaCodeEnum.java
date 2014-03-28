package com.wozai.DTO.Enum;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 14-1-10
 * Time: 下午1:32
 * To change this template use File | Settings | File Templates.
 */
public enum  AreaCodeEnum {
    SYS_GEO_AREA(1,"地理区域"),
    ADM_GEO_AREA(2,"人工划分"),
    BUS_GEO_AREA(3,"商家区域"),
    USR_GEO_AREA(4,"用户区域");

    private int codeNum;
    private String codeInfo;
    private AreaCodeEnum(int codeNum, String codeInfo){
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
