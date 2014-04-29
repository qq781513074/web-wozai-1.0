package com.wozai.controller;

import com.google.gson.Gson;
import com.wozai.DTO.LocationRecord;
import com.wozai.DTO.UserLocInfo;
import com.wozai.cache.*;
import com.wozai.common.BaseAjaxController;
import com.wozai.common.utils.StringUtils;
import com.wozai.common.utils.catchnuist.SimpleLogin;
import com.wozai.service.LoctionManagerService;
import com.wozai.service.LoginByNuistManagerService;
import com.wozai.service.UserAccountManagerService;
import com.wozai.service.UserLocationRecordManagerService;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.SimpleThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 14-3-7.
 */
@RequestMapping("/mobile")
@Controller
public class AndroidController extends BaseAjaxController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AndroidController.class);
    private static final Gson gson = new Gson();

    @Resource
    private LoctionManagerService loctionManagerService;
    @Resource
    private LoginByNuistManagerService loginByNuistManagerService;
    @Resource
    private LoginSuccessMap<String,LoginObj> loginSuccessMap;
    @Resource
    private LoginCheckFailList<String> loginCheckFailList;
    @Resource
    private UserLocationRecordManagerService userLocationRecordManagerService;
    @Resource
    UserAccountManagerService userAccount;

    @RequestMapping(value = "/search.htm",method = RequestMethod.GET)
    public @ResponseBody String checkUserAccount(String username,String lng,String lat,String condition,String startIdx,String endIdx,Model model,HttpServletRequest request, HttpServletResponse response) {
        return loctionManagerService.getClassroom(username,lng,lat,condition,startIdx,endIdx).toJson();
    }

    /**
     * 用于登陆账号的校验
     * @param request
     * @param response
     */
    @RequestMapping(value = "/loginByNuist.htm")
    public @ResponseBody String checkUserAccountByNUIST(String username,String password,String serialNo,Boolean rememberMe,Model model,HttpServletRequest request, HttpServletResponse response) {
        super.printOptInfo(logger,request,false);
        return loginByNuistManagerService.login4Nuist(username,password,serialNo);
    }

    public @ResponseBody String checkNet(HttpServletRequest request, HttpServletResponse response,String username,String serialNo){
        if (loginSuccessMap.containsKey(username)){
            if(((LoginObj) loginSuccessMap.get(username)).getSerialNo().equals(serialNo)){
                return "T";
            }
        }
        return "F";
    }
    @RequestMapping(value = "/saveUserLoc.htm")
    public @ResponseBody String signUser(Model model,HttpServletRequest request, HttpServletResponse response,Long username,String lat,String lng,String locationName,Long locId,Long classroomId,String minMan,String maxMan,String method){
        super.printOptInfo(logger,request,false);
        UserLocInfo locInfo = new UserLocInfo();
        List<LocationRecord> resultList = new ArrayList<LocationRecord>();
        String status = new String();
        String rank = new String();
//        Integer jfen = 0;
        //todo 校验lng lat 与教室的位置
        //有以下三种情况 1.第一次签到  2.第二次及第二次之后 当天继续签到  3.出教室
        //先查询是否是当天第一次签到  如果当前查询没有记录 则认为是当天第一次签到
        try{
            List<LocationRecord> list =  userLocationRecordManagerService.getTodayUserLocationInfo(username.toString());
            if (list == null || list.size() <= 0){
                logger.info("[用户签到]用户当天第一次签到 {}",username);
                //当天没签到过
                logger.info("[用户签到]查询用户签到排行榜前10");
                resultList  =  userLocationRecordManagerService.getTodayEarlyUser(new Date());
                if (resultList!= null && resultList.size() >=0 && resultList.size() < 10){
                    status = "签到成功! 积分+20";
                    rank = "恭喜!你是第"+ (resultList.size()+1) + "个签到的哦";
                }else if (resultList!= null && resultList.size() >= 10){
                    LocationRecord locationRecord = new LocationRecord();
                    locationRecord.setJoinTime(new Date());
                    locationRecord.setUserId(username.toString());
                    status = "签到成功! 积分+20";
                    rank = "你是第"+userLocationRecordManagerService.getUserRank(locationRecord) + "个签到的哦,继续加油";
                }
                LocationRecord record = new LocationRecord();
                record.setJfen(20);
                record.setUserId(username.toString());
                record.setJoinTime(new Date());
                record.setLocId(locId);
                record.setLocInfoId(classroomId);
                record.setLat(lat);
                record.setLng(lng);
                record.setMethod(method);
                if (userLocationRecordManagerService.saveUserLoc(record,true)){
                    logger.info("[用户签到]存储用户签到信息成功 {}",record);
                    locInfo.setClassroom_name(locationName);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    locInfo.setDate(sdf.format(new Date()));
                    locInfo.setJfen(userAccount.getUser(username.toString()).getJfen());
                    resultList  =  userLocationRecordManagerService.getTodayEarlyUser(new Date());
                    locInfo.setList(resultList);
                    locInfo.setRank(rank);
                    locInfo.setStatus(status);
                    return gson.toJson(locInfo,UserLocInfo.class);
                }
            }else {
                //如果不是第一次签到  则查询出最近的签到数据  如果已经签到并且出教室的数据为空 则更新这条数据 出教室的时间  并且返回积分、
                //签出返回当天签到记录
                logger.info("[用户签到]用户当天非第一次签到 {}",username);
                LocationRecord last = list.get(0);
                if (last.getLocInfoId().equals(classroomId) && last.getJoinTime().equals(last.getLeftTime())){
                    //是同一个教室
                    logger.info("[用户签到]用户签到离开教室 {}",username);
                    //todo 每半小时校验是否在教室的地点
                    Date date = last.getJoinTime();
                    long time = (new Date().getTime() - date.getTime())/1000/60 ;//分钟
                    Integer count = ((int)time/60)*20 + ((int)time%60) /15 * 4;
                    last.setLeftTime(new Date());
                    last.setJfen(last.getJfen() + count);
                    if (userLocationRecordManagerService.userLeftLoc(last,count)){
                        logger.info("[用户签到]用户签到离开信息保存成功 {}",last);
                        locInfo.setClassroom_name(locationName);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        locInfo.setDate(sdf.format(new Date()));
                        locInfo.setJfen(userAccount.getUser(username.toString()).getJfen());
                        resultList  =  userLocationRecordManagerService.getTodayUserLocationInfo(username.toString());
                        locInfo.setList(resultList);
                        Integer rankNum = 0;
                        if ((rankNum = userLocationRecordManagerService.getUserRank(last)) > 10){
                            rank = "今天你是第"+rankNum + "个签到的哦,继续加油";
                        }else{
                            rank = "恭喜!今天你是第"+ rankNum + "个签到的哦";
                        }
                        locInfo.setRank(rank);
                        locInfo.setStatus("记录成功！自习时长"+time+"分钟,增加积分"+count);
                        return gson.toJson(locInfo,UserLocInfo.class);
                    }
                }else {
                    //不是同一个教室 或者 前面的都签到签出了
                    //签到返回排行榜
                    Integer rankNum = 0;
                    if((rankNum = userLocationRecordManagerService.getUserRank(last)) > 10){
                        rank = "今天你是第"+rankNum + "个签到的哦,继续加油";
                    }else{
                        rank = "恭喜!今天你是第"+ rankNum + "个签到的哦";
                    }
                    LocationRecord record = new LocationRecord();
                    record.setJfen(0);
                    record.setUserId(username.toString());
                    record.setJoinTime(new Date());
                    record.setLocId(locId);
                    record.setLocInfoId(classroomId);
                    record.setLat(lat);
                    record.setLng(lng);
                    record.setMethod(method);
                    if (userLocationRecordManagerService.saveUserLoc(record,false)){
                        logger.info("[用户签到]存储用户签到信息成功{}",record);
                        locInfo.setClassroom_name(locationName);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        locInfo.setDate(sdf.format(new Date()));
                        locInfo.setJfen(userAccount.getUser(username.toString()).getJfen());
                        resultList  =  userLocationRecordManagerService.getTodayEarlyUser(new Date());
                        locInfo.setList(resultList);
                        locInfo.setRank(rank);
                        status = "签到成功!";
                        locInfo.setStatus(status);
                        return gson.toJson(locInfo,UserLocInfo.class);
                    }
                }
            }
        }catch (Exception e){
            logger.error("[用户签到]发生异常",e);
            locInfo.setStatus("ERROR");
            return gson.toJson(locInfo,UserLocInfo.class);
        }
        locInfo.setStatus("ERROR");
        return gson.toJson(locInfo,UserLocInfo.class);
    }
}
