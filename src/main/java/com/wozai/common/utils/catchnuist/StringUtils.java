package com.wozai.common.utils.catchnuist;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	public static final String __VIEWSTATE = "<input type=\"hidden\" name=\"__VIEWSTATE\" id=\"__VIEWSTATE\" value=\".*\" />";
	public static final String __EVENTVALIDATION = "<input type=\"hidden\" name=\"__EVENTVALIDATION\" id=\"__EVENTVALIDATION\" value=\".*\" />";
	public static Map<String,String> setParam(Map<String,String> map,String msg){
//		System.out.println(msg);
		Pattern p1 = Pattern.compile(__VIEWSTATE); 
		Pattern p2 = Pattern.compile(__EVENTVALIDATION);
		Matcher m1 = p1.matcher(msg);
		Matcher m2 = p2.matcher(msg);
		while(m1.find()) {
            String value = m1.group();
            String[] temp = value.split("value=\"");
            temp = temp[1].split("\" />");
            map.put("__VIEWSTATE", temp[0]);
        } 
		while(m2.find()) {
            String value = m2.group();
            String[] temp = value.split("value=\"");
            temp = temp[1].split("\" />");
            map.put("__EVENTVALIDATION", temp[0]);
        } 
		return map;
	}
}
