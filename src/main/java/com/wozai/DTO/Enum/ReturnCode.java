package com.wozai.DTO.Enum;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 13-12-19
 * Time: 下午5:13
 * To change this template use File | Settings | File Templates.
 */
public enum ReturnCode {
    TOO_MANY_WRONGTIMES(1,"错误次数太多了需要验证码！"),
    CANNOT_FOUND_ID(2,"账号不存在！"),
    PWD_WRONG(3,"密码错误请重试！"),
    WRONG_TOO_FAST(4,"短时间内错误次数过多！"),
    HAS_LOGINED(5,"已经登陆过了，请先退出再重新登陆"),
    TOO_LONG_WAIT(6,"登陆超时"),
    SUCCESS(7,"成功"),
    VALIDATECODE_ERROR(8,"验证码错误"),
    HAS_ALREADY_EXSIT(9,"账号已经存在"),
    USERID_ISNULL(10,"账号不能为空"),
    USERPWD_ISNULL(11,"密码不能为空"),
    NICKNAME_ISNULL(12,"请填写昵称"),
    SQL_ERROR(13,"SQL出现异常");


    private int codeNum;
    private String codeInfo;
    private ReturnCode(int codeNum, String codeInfo){
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
