package com.wozai.DTO;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by Administrator on 14-2-25.
 */
public class ClassInfoTransfer {
    private static final Logger logger = Logger.getLogger("com.wozai.DTO.ClassInfoTransfer");
    public static final String Direction_API = "http://api.map.baidu.com/geosearch/v3/nearby?ak=YkKmBRaQQqTgxGZwuWg3fVnr&geotable_id=52520&coord_type=3&radius=100000&location=";
    public static String page = "";
    public static Map<Long,String> distanceMap = new HashMap<Long, String>();
    public static ClassInfo transfer2Query(String condition,String lng,String lat,String startIdx,String endIdx){
        setPage(lng,lat);
        if(condition == null){
            condition = "";
        }
            logger.info("【用户信息：】输入条件:"+Arrays.toString(condition.getBytes()) + condition);
        ClassInfo classInfo = new ClassInfo();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        if(condition.contains("上午")){
            classInfo.setClass1(0);
            classInfo.setClass2(0);
            classInfo.setClass3(0);
            classInfo.setClass4(0);
        }
        if(condition.contains("下午")){
            classInfo.setClass5(0);
            classInfo.setClass6(0);
            classInfo.setClass7(0);
            classInfo.setClass8(0);
        }
        if(condition.contains("晚上")){
            classInfo.setClass9(0);
            classInfo.setClass10(0);
            classInfo.setClass11(0);
            classInfo.setClass12(0);
        }
        logger.info(condition.contains("明天")  +""+ !isEmpty(classInfo));
        logger.info(Arrays.toString("明天".getBytes()) +Arrays.toString(condition.getBytes())   +""+ !isEmpty(classInfo));
        if(condition.contains("明天")){
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            classInfo.setClass_date(calendar.get(Calendar.DAY_OF_WEEK) - 1 == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1);
            if(isEmpty(classInfo)){
                classInfo.setClass1(0);
                classInfo.setClass2(0);
                classInfo.setClass3(0);
                classInfo.setClass4(0);
                classInfo.setClass5(0);
                classInfo.setClass6(0);
                classInfo.setClass7(0);
                classInfo.setClass8(0);

            }
        }
        if(condition.contains("后天")){
            calendar.add(Calendar.DAY_OF_MONTH, 2);
            if(isEmpty(classInfo)){
                classInfo.setClass1(0);
                classInfo.setClass2(0);
                classInfo.setClass3(0);
                classInfo.setClass4(0);
                classInfo.setClass5(0);
                classInfo.setClass6(0);
                classInfo.setClass7(0);
                classInfo.setClass8(0);
            }
        }
        if(classInfo.getClass_date() == null) {
            String[] weekend = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
            for(int i = 1; i < 8 ;i++){
                if(condition.contains(weekend[i - 1])){
                   int flag =  calendar.get(Calendar.DAY_OF_WEEK) - 1 == 0 ? 7:calendar.get(Calendar.DAY_OF_WEEK) - 1;
                    if(flag <= i){
                        calendar.add(Calendar.DAY_OF_MONTH, i - flag);
                    }else{
                        calendar.add(Calendar.DAY_OF_MONTH, i+7 - flag);
                    }
                    classInfo.setClass_date(calendar.get(Calendar.DAY_OF_WEEK) - 1 == 0 ? 7:calendar.get(Calendar.DAY_OF_WEEK) - 1);
                    if(isEmpty(classInfo)){
                        classInfo.setClass1(0);
                        classInfo.setClass2(0);
                        classInfo.setClass3(0);
                        classInfo.setClass4(0);
                        classInfo.setClass5(0);
                        classInfo.setClass6(0);
                        classInfo.setClass7(0);
                        classInfo.setClass8(0);
                    }
                    break;
                }
            }
        }
        if(classInfo.getClass_date() == null && calendar.get(Calendar.HOUR_OF_DAY) >= 21){
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            classInfo.setClass_date(calendar.get(Calendar.DAY_OF_WEEK) - 1 == 0 ? 7:calendar.get(Calendar.DAY_OF_WEEK) - 1);
            classInfo.setClass1(0);
            classInfo.setClass2(0);
            classInfo.setClass3(0);
            classInfo.setClass4(0);
        }
        if(isEmpty(classInfo)){
            setClassEmpty(classInfo);
        }
        if(classInfo.getClass_date() == null){
            classInfo.setClass_date(calendar.get(Calendar.DAY_OF_WEEK) - 1 == 0 ? 7:calendar.get(Calendar.DAY_OF_WEEK) - 1);
        }
        if(startIdx == null){
            startIdx = "0";
        }
        if(endIdx == null){
            endIdx = "100";
        }
        classInfo.setStartIdx(Integer.valueOf(startIdx));
        classInfo.setEndIdx(Integer.valueOf(endIdx));
        return classInfo;
    }

    public static List<Classroom> transfer2Result(List<ClassInfo> list,String lng,String lat){
        setDistance();
        List<Classroom> result = new ArrayList<Classroom>();
        for(ClassInfo c : list){
            Classroom classroom = new Classroom();
            classroom.setName(c.getClassroom_name());
            classroom.setMsg(setMsg(c));
//            classroom.setDistance(c.getLoc_id() == 1L ? "文德楼" : "明德楼");
            classroom.setDistance(distanceMap.get(c.getLoc_id()));
            result.add(classroom);
        }
        return result;
    }

    public static void setPage(String lng,String lat){
        logger.info("【用户信息：】 经纬度："+lng+","+lat);
        JSONObject json = new JSONObject();
        String url = new StringBuffer(Direction_API).append(lng).append(",").append(lat).toString();
        try {
            page = HttpRequestUtil.request(url, null, HttpRequestUtil.REQUEST_TYPE_GET, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setDistance(){
        JSONObject json ;
        try {
            page = page.replaceAll("\"","'");
            json = new JSONObject(page);
            JSONArray jsonArray = json.getJSONArray("contents");
            if (jsonArray != null && jsonArray.length() <= 0) {
                return ;
            }else{
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
                    distanceMap.put(Long.valueOf(jsonObject2.getInt("loc_id")), jsonObject2.getInt("distance") + "米");
                }
            }
        } catch (JSONException e) {
            logger.info("JSONException : msg = " + page,e);
        }
    }
    public static void setClassEmpty(ClassInfo c){
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int min = Calendar.getInstance().get(Calendar.MINUTE);
        if(hour < 8){
            c.setClass1(0);
        }
        if(hour < 8 || (hour == 8 && min < 45)){
            c.setClass2(0);
        }
        if(hour < 10 || (hour == 10 && min < 10)){
            c.setClass3(0);
        }
        if(hour < 11 || (hour == 11 && min < 5)){
            c.setClass4(0);
        }
        if(hour < 13 || (hour == 13 && min < 30)){
            c.setClass5(0);
        }
        if(hour < 14 || (hour == 14 && min < 25)){
            c.setClass6(0);
        }
        if(hour < 15 || (hour == 15 && min < 40)){
            c.setClass7(0);
        }
        if(hour < 16 || (hour == 16 && min < 35)){
            c.setClass8(0);
        }
        if(hour < 18 || (hour == 18 && min < 30)){
            c.setClass9(0);
        }
        if(hour < 19 || (hour == 19 && min < 25)){
            c.setClass10(0);
        }
        if(hour < 21){
            c.setClass11(0);
            c.setClass12(0);
        }
    }

    public static boolean isEmpty(ClassInfo c){
        return c.getClass1() == null && c.getClass2() == null
                && c.getClass3() == null&& c.getClass4() == null
                && c.getClass5() == null&& c.getClass6() == null
                && c.getClass7() == null&& c.getClass8() == null
                && c.getClass9() == null&& c.getClass10() == null
                && c.getClass11() == null&& c.getClass12() == null;
    }
    public static String setMsg(ClassInfo c){
        String[] weekend = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
        StringBuffer sb = new StringBuffer(weekend[c.getClass_date() - 1]);
        int flag = 0;
        if(c.getClass1() == 0){
            sb.append(1+",");
        }
        if(c.getClass2() == 0){
            sb.append(2+",");
        }
        if(c.getClass3() == 0){
            sb.append(3+",");
        }
        if(c.getClass4() == 0){
            sb.append(4+",");
        }
        if(c.getClass5() == 0){
            sb.append(5+",");
        }
        if(c.getClass6() == 0){
            sb.append(6+",");
        }
        if(c.getClass7() == 0){
            sb.append(7+",");
        }
        if(c.getClass8() == 0){
            sb.append(8+",");
        }
        if(c.getClass9() == 0){
            sb.append(9+",");
        }
        if(c.getClass10() == 0){
            sb.append(10+",");
        }
        sb.append("无课");
        return sb.toString();
    }
}
