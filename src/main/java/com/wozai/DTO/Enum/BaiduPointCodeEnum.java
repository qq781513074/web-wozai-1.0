package com.wozai.DTO.Enum;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 14-1-10
 * Time: 下午2:47
 * To change this template use File | Settings | File Templates.
 */
public enum BaiduPointCodeEnum {
        SUCCESS(0,"正常",true),
        SERVER_ERROR(1,"服务器内部错误",false),
        INVID_PARAM(2,"请求参数非法",false),
        PERMISSION_ERROR(3,"权限校验失败",false),
        NUMBER_CHECK_ERROR(4,"配额校验失败",false),
        AK_ERROR(5,"ak不存在或者非法",false),
        SERVER_ABANDON(101,"服务禁用",false),
        FAIL_FILTER(102,"不通过白名单或者安全码不对",false),
        OTHER_ERROR(200,"无权限或配额错误",false);
    private int code;
    private String info;
    private boolean isSuccess;
    private BaiduPointCodeEnum(int code,String info,boolean isSuccess){
        this.code = code;
        this.info = info;
        this.isSuccess = isSuccess;
    }
    public BaiduPointCodeEnum  getInstance(int i){
         switch (i){
             case 0:return SUCCESS;
             case 1:return SERVER_ERROR;
             case 2:return INVID_PARAM;
             case 3:return PERMISSION_ERROR;
             case 4:return NUMBER_CHECK_ERROR;
             case 5:return AK_ERROR;
             case 101:return SERVER_ABANDON;
             case 102:return FAIL_FILTER;
             default:
                 return OTHER_ERROR;
         }
    }
    public int getCode(){
        return code;
    }
    public String getInfo(){
        return info;
    }
    public boolean isSuccess(){
        return isSuccess;
    }
}
