package com.wozai.common.utils.catchnuist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoadClassroom {
	public static final String path = "lib/class/";
	public static Integer week = 0;
	public static Map<String,Map<Integer, List<String>>> classroomMap = new HashMap<String, Map<Integer,List<String>>>();
	public static List<File> getFileList(){
		List<File> fileList = new ArrayList<File>();
		for(File f : new File(path).listFiles()){
			fileList.add(f);
		}
		return fileList;
	}
	public static Map<Integer, List<String>> setClass(File f) throws FileNotFoundException{
		Map<Integer, List<String>> resultMap = new HashMap<Integer, List<String>>();
		String regex[] = {"1~2","3~4","5~6","7~8","9~10","11~12"};
		Scanner scanner = new Scanner(f);
		while(scanner.hasNextLine()){
			String msg = scanner.nextLine();
			setClassInfo(resultMap,match(msg));
		}
		return resultMap;
	}
	public static String match(String msg){
		String result = "";
		Pattern p = Pattern.compile("<td width=\"184\" align=\"left\" valign=\"top\" bgcolor=\"#FFFFFF\">.*</td>");
		Matcher m = p.matcher(msg);
		if(m.find()){
			week+=1;
			String[] temp = m.group().split("<td width=\"184\" align=\"left\" valign=\"top\" bgcolor=\"#FFFFFF\">");
			result = temp[1].split("</td>")[0];
		}
		return result;
	}
	public static void setClassInfo(Map<Integer, List<String>> map,String msg){
		if(msg != null && !msg.trim().isEmpty()){
			if(map.get(((week-1)%7) + 1) == null){
				map.put(((week-1)%7) + 1, new LinkedList<String>());
			}
			map.get(((week-1)%7) + 1).add(msg);
		}
	}
	public static void setAll() throws FileNotFoundException{
		for(File f : getFileList()){
			String key = f.getAbsolutePath().replace("F:\\wozai-web-new\\CatchClass\\lib\\class\\", "");
			classroomMap.put(key, setClass(f));
		}
	}
	public static void main(String[] args) throws FileNotFoundException {
		setAll();
		List<ClassInfo> classList = new ArrayList<ClassInfo>();
		for(String key : classroomMap.keySet()){
			Map<Integer, List<String>> map = classroomMap.get(key);
			for(int i = 1; i < 8; i ++){
				ClassInfo classInfo = new ClassInfo();
				classInfo.setClassroom_name(key);
				classInfo.setClass_date(i);
				if(key.contains("明德")){
					classInfo.setLoc_id(2L);
				}
				if(key.contains("文德")){
					classInfo.setLoc_id(1L);
				}
				List<String> list = map.get(i);
				for(int j = 0 ; j < 6 ;j ++){
						switch(j){
						case 0 : 
							classInfo.setClass1(list.get(j).contains("nbsp;") ? 0:1);
							classInfo.setClass2(list.get(j).contains("nbsp;") ? 0:1);
							break;
						case 1 : 
							classInfo.setClass3(list.get(j).contains("nbsp;") ? 0:1);
							classInfo.setClass4(list.get(j).contains("nbsp;") ? 0:1);
							break;
						case 2 : 
							classInfo.setClass5(list.get(j).contains("nbsp;") ? 0:1);
							classInfo.setClass6(list.get(j).contains("nbsp;") ? 0:1);
							break;
						case 3 : 
							classInfo.setClass7(list.get(j).contains("nbsp;") ? 0:1);
							classInfo.setClass8(list.get(j).contains("nbsp;") ? 0:1);
							break;
						case 4 : 
							classInfo.setClass9(list.get(j).contains("nbsp;") ? 0:1);
							classInfo.setClass10(list.get(j).contains("nbsp;") ? 0:1);
							break;
						case 5 : 
							classInfo.setClass11(list.get(j).contains("nbsp;") ? 0:1);
							classInfo.setClass12(list.get(j).contains("nbsp;") ? 0:1);
							break;
					}
				}
				classList.add(classInfo);
			}
		}
		PrintWriter pw = new PrintWriter(new FileOutputStream(new File("lib/Classroomlist.txt")),true);
		for(ClassInfo c : classList){
			StringBuffer sb = new StringBuffer();
			sb.append("0,0,").append("\"").append(c.getClassroom_name()).append("\",").append(c.getLoc_id()).append(",").append("\"").append(c.getCurr_status()).append("\",").append(c.getCurr_status_code()).append(",").append("\"").append(c.getClassroom_type()).append("\",")
				.append(c.getClass1()).append(",")
				.append(c.getClass2()).append(",")
				.append(c.getClass3()).append(",")
				.append(c.getClass4()).append(",")
				.append(c.getClass5()).append(",")
				.append(c.getClass6()).append(",")
				.append(c.getClass7()).append(",")
				.append(c.getClass8()).append(",")
				.append(c.getClass9()).append(",")
				.append(c.getClass10()).append(",")
				.append(c.getClass11()).append(",")
				.append(c.getClass12()).append(",").append(c.getClass_date());
			pw.println("insert into classroom_info(classroom_cur_man,classroom_max_man,classroom_name,loc_id,curr_status,curr_status_code,classroom_type,class1,class2,class3,class4,class5,class6,class7,class8,class9,class10,class11,class12,class_date) values(" + sb.toString()+ ");");
		}
	}
}
