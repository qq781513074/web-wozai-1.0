package com.wozai.common.utils.catchnuist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReadClassRoom {
	public static Map<String,List<String>> read() throws FileNotFoundException{
		Map<String, List<String>> classroom = new HashMap<String, List<String>>();
		List<String> keylist = new ArrayList<String>();
		List<String> allList = new LinkedList<String>();
		Scanner scanner = new Scanner(new File("lib/list1.txt"));
		while(scanner.hasNext()){
			String str = scanner.nextLine();
			String msg[] = str.split("value=");
			String msgs[] = msg[1].split("\"");
			keylist.add(msgs[1]);
		}
		for(String key : keylist){
			scanner = new Scanner(new File("lib/"+ key+".txt"));
			List<String> list = new ArrayList<String>();
			int flag = 0 ;
			while(scanner.hasNext()){
				String temp = scanner.nextLine();
				if(temp.contains("DropDownList3")){
					flag = 1;
				}
				if(temp.contains("<option") && flag == 1){
					String msg[]  = temp.split("value=");
					String msgs[] = msg[1].split("\"");
					if(!msgs[1].trim().isEmpty()){
						list.add(msgs[1]);
						allList.add(msgs[1]);
					}
					
				}
				if(temp.contains("</select>") && flag == 1){
					classroom.put(key, list);
					break;
				}
			}
		}
		return classroom;
	}
	public static void main(String[] args) throws FileNotFoundException {
		Map<String,List<String>> map = read();
		
	}
}
