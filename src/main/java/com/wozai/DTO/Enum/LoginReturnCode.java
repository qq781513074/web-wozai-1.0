package com.wozai.DTO.Enum;

import com.wozai.DTO.AccountInfo;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 13-12-19
 * Time: 下午5:13
 * To change this template use File | Settings | File Templates.
 */
public enum LoginReturnCode {
    TOO_MANY_WRONGTIMES(1,"错误次数太多了需要验证码！"),
    CANNOT_FOUND_ID(2,"账号不存在！"),
    PWD_WRONG(3,"密码错误请重试！"),
    WRONG_TOO_FAST(4,"短时间内错误次数过多！"),
    HAS_LOGINED(5,"已经登陆过了，请先退出再重新登陆"),
    TOO_LONG_WAIT(6,"登陆超时"),
    SUCCESS(7,"成功"),
    VALIDATECODE_ERROR(8,"失败");


    private int codeNum;
    private String codeInfo;
    private LoginReturnCode(int codeNum,String codeInfo){
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
