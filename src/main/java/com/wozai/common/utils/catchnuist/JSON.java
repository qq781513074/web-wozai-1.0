package com.wozai.common.utils.catchnuist;

import org.json.JSONObject;

public class JSON {
	public static void main(String[] args) {
	String msg = "{'status':0,'total':2,'size':2,'contents':[{'address':'\u6c5f\u82cf\u7701\u5357\u4eac\u5e02\u6d66\u53e3\u533a\u76d8\u57ce\u65b0\u8857','city':'\u5357\u4eac\u5e02','create_time':1393398077,'district':'\u6d66\u53e3\u533a','geotable_id':52520,'loc_id':1,'loc_name':'\u6587\u5fb7\u697c','location':[118.727692,32.209473],'modify_time':1393398869,'province':'\u6c5f\u82cf\u7701','tags':'classroom','title':'\u6587\u5fb7\u697c','uid':94292750,'coord_type':3,'type':0,'distance':326,'weight':0},{'title':'\u660e\u5fb7\u697c','location':[118.72532,32.210752],'city':'\u5357\u4eac\u5e02','create_time':1393398940,'geotable_id':52520,'address':'\u6c5f\u82cf\u7701\u5357\u4eac\u5e02\u6d66\u53e3\u533a\u76d8\u57ce\u65b0\u8857','tags':'classroom','province':'\u6c5f\u82cf\u7701','district':'\u6d66\u53e3\u533a','loc_name':'\u660e\u5fb7\u697c','loc_id':2,'uid':94302804,'coord_type':3,'type':0,'distance':192,'weight':0}]}";
	JSONObject obj = new JSONObject(msg);
	System.out.println(obj);
	}
}
