package com.wozai.service;

import com.wozai.DTO.AccountInfo;
import com.wozai.DTO.Enum.ReturnCodeEnum;
import com.wozai.DTO.LoginReturnDTO;
import com.wozai.DTO.RegisterReturnDTO;
import com.wozai.common.utils.StringUtils;
import com.wozai.dao.SuperDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 13-12-19
 * Time: 下午3:54
 * To change this template use File | Settings | File Templates.
 */
@Service("userAccount")
public class UserAccountManagerService {
    private static final Logger logger = Logger.getLogger("com.wozai.service.UserAccountManagerService");

    @Resource
    SuperDao superDao;
    //todo  未使用
    public LoginReturnDTO tryLogin(HttpServletRequest request,String userId,String userPwd,Model model){
        LoginReturnDTO loginReturnDTO = new LoginReturnDTO();
        if(!StringUtils.isNotNull(userId)){
            loginReturnDTO.setResultCode(ReturnCodeEnum.CANNOT_FOUND_ID.getCodeNum());
            loginReturnDTO.setResultInfo(ReturnCodeEnum.CANNOT_FOUND_ID.getCodeInfo());
            logger.info("账号为空拒绝登陆");
            return loginReturnDTO;
        }
        model.addAttribute("userId",userId);
        if (!StringUtils.isNotNull(userPwd)) {
            loginReturnDTO.setResultCode(ReturnCodeEnum.PWD_WRONG.getCodeNum());
            loginReturnDTO.setResultInfo(ReturnCodeEnum.PWD_WRONG.getCodeInfo());
            logger.info(new StringBuffer("用户：").append(userId).append("登陆错误 原因：").append(loginReturnDTO.getResultInfo()));

            return loginReturnDTO;
        }
        model.addAttribute("userPwd",userPwd);
        AccountInfo accountInfo =(AccountInfo)superDao.getObject("AccountInfoMapper.queryOneAccountInfo",userId);
        if (null == accountInfo){
            loginReturnDTO.setResultCode(ReturnCodeEnum.CANNOT_FOUND_ID.getCodeNum());
            loginReturnDTO.setResultInfo(ReturnCodeEnum.CANNOT_FOUND_ID.getCodeInfo());
            logger.info(new StringBuffer("用户：").append(userId).append("登陆错误 原因：").append(loginReturnDTO.getResultInfo()));
            return loginReturnDTO;
        }
        if (accountInfo.getUserPwd().equals(userPwd)){
              loginReturnDTO.setResultCode(ReturnCodeEnum.SUCCESS.getCodeNum());
              loginReturnDTO.setResultInfo(ReturnCodeEnum.SUCCESS.getCodeInfo());
              loginReturnDTO.setAccountInfo(accountInfo);
              logger.info(new StringBuffer("用户登陆成功 ：userId = ").append(userId));
            //todo shiro
              request.getSession().setAttribute("accountInfo",accountInfo);
            return loginReturnDTO;
        } else {
            loginReturnDTO.setResultCode(ReturnCodeEnum.PWD_WRONG.getCodeNum());
            loginReturnDTO.setResultInfo(ReturnCodeEnum.PWD_WRONG.getCodeInfo());
            loginReturnDTO.setAccountInfo(accountInfo);
            logger.info(new StringBuffer("用户登陆失败 ：错误原因 ：").append(loginReturnDTO.getResultInfo()));
            return loginReturnDTO;
        }
    }
    public int getWrongTimes(String userId){
          return 0;
    }
    //todo  未使用
    public RegisterReturnDTO tryRegister(HttpServletRequest request,AccountInfo accountInfo,Model model){
        model.addAttribute("userId",accountInfo.getUserId());
        model.addAttribute("userPwd",accountInfo.getUserPwd());
        model.addAttribute("nickname",accountInfo.getNickname());
        RegisterReturnDTO registerReturnDTO = new RegisterReturnDTO();
        if (!StringUtils.isNotNull(accountInfo.getUserId())){
            registerReturnDTO.setResultCode(ReturnCodeEnum.USERID_ISNULL.getCodeNum());
            registerReturnDTO.setResultInfo(ReturnCodeEnum.USERID_ISNULL.getCodeInfo());
            logger.info(new StringBuffer("账号注册失败  userId：").append(accountInfo.getUserId()).append(" 原因：").append(registerReturnDTO.getResultInfo()));
            return registerReturnDTO;
        }
        if(!StringUtils.isNotNull(accountInfo.getUserPwd())){
            registerReturnDTO.setResultCode(ReturnCodeEnum.USERPWD_ISNULL.getCodeNum());
            registerReturnDTO.setResultInfo(ReturnCodeEnum.USERPWD_ISNULL.getCodeInfo());
            logger.info(new StringBuffer("账号注册失败  userId：").append(accountInfo.getUserId()).append(" 原因：").append(registerReturnDTO.getResultInfo()));
            return registerReturnDTO;
        }
        if(!StringUtils.isNotNull(accountInfo.getNickname())){
            registerReturnDTO.setResultCode(ReturnCodeEnum.NICKNAME_ISNULL.getCodeNum());
            registerReturnDTO.setResultInfo(ReturnCodeEnum.NICKNAME_ISNULL.getCodeInfo());
            logger.info(new StringBuffer("账号注册失败  userId：").append(accountInfo.getUserId()).append(" 原因：").append(registerReturnDTO.getResultInfo()));
            return registerReturnDTO;
        }
        if (!userIdIsExsit(accountInfo.getUserId())){
            try{
                superDao.insert("AccountInfoMapper.createAccount",accountInfo);
                registerReturnDTO.setResultCode(ReturnCodeEnum.SUCCESS.getCodeNum());
                registerReturnDTO.setResultInfo(ReturnCodeEnum.SUCCESS.getCodeInfo());
                return registerReturnDTO;
            }catch (Exception e){
                registerReturnDTO.setResultCode(ReturnCodeEnum.SQL_ERROR.getCodeNum());
                registerReturnDTO.setResultInfo(ReturnCodeEnum.SQL_ERROR.getCodeInfo());
                logger.info(new StringBuffer("账号注册失败  userId：").append(accountInfo.getUserId()).append(" 原因：").append(e.getMessage()));
                return registerReturnDTO;
            }
        }else {
            registerReturnDTO.setResultCode(ReturnCodeEnum.HAS_ALREADY_EXSIT.getCodeNum());
            registerReturnDTO.setResultInfo(ReturnCodeEnum.HAS_ALREADY_EXSIT.getCodeInfo());
            logger.info(new StringBuffer("账号注册失败  userId：").append(accountInfo.getUserId()).append(" 原因：").append(registerReturnDTO.getResultInfo()));
            return registerReturnDTO;
        }
    }

    public boolean userIdIsExsit(String userId){
        if (((Integer)superDao.getObject("AccountInfoMapper.userIdIsExsit",userId)) == 1){
             return true;
        }else {
            return false;
        }
    }

    public boolean addUser(AccountInfo info){
        if (info.getUserId() == null){
            return false;
        }
        if (userIdIsExsit(info.getUserId())){
            //不管是否插入 只要保证有用户存在 即为真
            return true;
        }else {
            logger.info("[用户管理]插入用户"+info);
            if (info.getJfen() == null){
                info.setJfen(0);
            }
          Integer i =  superDao.insert("AccountInfoMapper.createAccount",info);
            if (i > 0){
                logger.info("[用户管理]插入用户成功"+info);
                return true;
            }
        }
        return false;
    }

    public AccountInfo getUser(String userId){
        logger.info("[查询用户]查询用户信息userid = " + userId);
        return superDao.getObject("AccountInfoMapper.queryOneAccountInfo",userId);
    }

    public boolean updateUser(AccountInfo info){
        logger.info("[查询用户]更新用户信息userid = " + info);
        return superDao.update("AccountInfoMapper.updateInfo",info)>0 ? true : false;
    }
}
