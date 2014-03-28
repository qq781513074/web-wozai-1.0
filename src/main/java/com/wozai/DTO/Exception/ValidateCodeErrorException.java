package com.wozai.DTO.Exception;

import com.wozai.DTO.Enum.LoginReturnCode;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 13-12-20
 * Time: 下午6:39
 * To change this template use File | Settings | File Templates.
 */
public class ValidateCodeErrorException extends Exception{
    @Override
    public String getMessage() {
        return LoginReturnCode.VALIDATECODE_ERROR.getCodeInfo();
    }
}
