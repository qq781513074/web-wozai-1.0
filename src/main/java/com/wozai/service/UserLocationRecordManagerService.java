package com.wozai.service;

import com.wozai.DTO.AccountInfo;
import com.wozai.DTO.LocationRecord;
import com.wozai.DTO.UserLocInfo;
import com.wozai.dao.SuperDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zengzihao on 2014/4/7.
 */
@Service
public class UserLocationRecordManagerService {
    private static final Logger logger = Logger.getLogger("com.wozai.service.UserLocationRecordManagerService");
    @Resource
    SuperDao superDao;

    @Resource
    private UserAccountManagerService userAccount;

    private List<LocationRecord> earlyUser = null;

    public Boolean addUserLocationInfo(){
        return true;
    }

    public Boolean getLastUserLocationInfo(){
        return true;
    }

    public List<LocationRecord> getTodayUserLocationInfo(String username){
        LocationRecord locationRecord = new LocationRecord();
        locationRecord.setUserId(username);
        List<LocationRecord> list = superDao.getList("UserLocInfoMapper.lastUserLoc",locationRecord);
        if (list == null){
            return null;
        }
        return list;
    }
    public Boolean updateUserLocationInfo(){
        return true;
    }

    public List<LocationRecord> getTodayEarlyUser(Date date){
        LocationRecord locationRecord = new LocationRecord();
        locationRecord.setJoinTime(date);
        if (earlyUser != null){
            TimeZone tz = TimeZone.getDefault();
            Date earlyDate = earlyUser.get(0).getJoinTime();
            Calendar calendar1 =  Calendar.getInstance(tz);
            calendar1.setTime(date);
            Calendar calendar2 =  Calendar.getInstance(tz);
            calendar2.setTime(new Date());
            if (calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)){
                return earlyUser;
            }
        }
        List<LocationRecord> list = superDao.getList("UserLocInfoMapper.earlyUser",locationRecord);
        if (earlyUser == null && list.size() >= 10){
            earlyUser = list;
        }
        return list;
    }

    public Integer getUserRank(LocationRecord locationRecord){
       logger.info("[用户签到]查询排名 param" + locationRecord);
       Integer rank =  superDao.getObject("UserLocInfoMapper.userRank",locationRecord);
        if (rank != null){
            rank++;
            logger.info("[用户签到]查询排名成功 param"+ locationRecord + "排名"+rank);
            return rank;
        }
        return 0;
    }

    public Boolean saveUserLoc(LocationRecord locInfo,Boolean isFirst){
        if (isFirst){
            AccountInfo info = new AccountInfo();
            info.setUserId(locInfo.getUserId());
            if (!userAccount.addUser(info)){
                return false;
            }
            info = userAccount.getUser(locInfo.getUserId());
            logger.info("[用户地点]成功查询用户信息");
            info.setJfen(info.getJfen()+20);
            userAccount.updateUser(info);
            logger.info("[用户地点]成功增加用户积分");
        }
        Integer i = superDao.insert("UserLocInfoMapper.insertInfo",locInfo);
        logger.info("[用户地点]成功增加用户签到地点");
        if(i >0 ){
           return true;
        }else {
            return false;
        }
    }

    public Boolean userLeftLoc(LocationRecord locInfo,Integer addJfen){
            logger.info("[用户签到]用户签到离开教室服务器保存"+locInfo);
        Integer i = superDao.insert("UserLocInfoMapper.leftLoc",locInfo);
        if(i >0 ){
            logger.info("[用户签到]用户签到离开教室清算积分"+locInfo);
            AccountInfo userInfo = userAccount.getUser(locInfo.getUserId());
            if (userInfo != null){
                userInfo.setJfen(userInfo.getJfen()+addJfen);
                if (userAccount.updateUser(userInfo)){
                    logger.info("[用户签到]用户签到离开教室服务器保存操作成功"+locInfo);
                    return true;
                }
            }
        }
        return false;
    }
}
