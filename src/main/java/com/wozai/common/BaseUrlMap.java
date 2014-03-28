package com.wozai.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 14-1-10
 * Time: 下午1:54
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BaseUrlMap extends LinkedHashMap<String,String> {
    private static final Logger logger = LoggerFactory.getLogger(LinkedHashMap.class);

    public BaseUrlMap() {
        this.put("AK","XGHZ5xF4dV81pT1LYUhHfZON");
        this.put("getLocUrl","http://api.map.baidu.com/geocoder/v2/?ak=##&callback=renderReverse&location=##,##&output=json&pois=1");

    }
    public String getLocUrl(String latitude,String longitude){
        String url = this.get("getLocUrl");
        String[] param  = url.split("##");
        StringBuffer sb = new StringBuffer(param[0]);
        sb.append(this.get("AK"));
        sb.append(param[1]);
        sb.append(latitude);
        sb.append(param[2]);
        sb.append(longitude);
        sb.append(param[3]);
        return sb.toString();
    }

}
