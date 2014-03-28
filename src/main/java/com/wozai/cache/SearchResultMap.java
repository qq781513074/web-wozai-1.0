package com.wozai.cache;

import com.wozai.DTO.ClassInfo;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by zengzihao on 2014/3/27.
 */
@Component
public class SearchResultMap extends Hashtable<ClassInfo,ClassSqlCache> {
    public static Integer maxCount  = 10;
    @Override
    public synchronized ClassSqlCache put(ClassInfo key, ClassSqlCache cache) {
         ClassSqlCache last = null;
         ClassSqlCache max = null;
         ClassSqlCache old1 = null;
         ClassSqlCache min1 = null;
         ClassSqlCache old2 = null;
         ClassSqlCache min2 = null;
        if (this.size() <= maxCount){
            return super.put(key, cache);
        }
        for(ClassInfo classInfo : this.keySet()){
            ClassSqlCache temp =  this.get(classInfo);
            if (last == null){
                last = temp;
                max = temp;
                old1 = temp;
                min1 = temp;
                old2 = temp;
                min2 = temp;
                continue;
            }
            if (last.getLastAccess().getTime() < temp.getLastAccess().getTime()){
                last = temp;
            }
            if (max.getCount() < temp.getCount()){
                max = temp;
            }
            if (old1.getLastAccess().getTime() > temp.getLastAccess().getTime()){
                old1 = temp;
            }else if (old2.getLastAccess().getTime() > temp.getLastAccess().getTime()){
                old2 = temp;
            }
            if (min1.getCount() > temp.getCount()){
                min1 = temp;
            }else if (min2.getCount() > temp.getCount()){
                min2 = temp;
            }
        }
        if (old1 == last || old1 == max){
            old1 = null;
        }else {
            remove(old1);
        }
        if (min1 == last || min1 == max){
            old1 = null;
        }else {
            remove(min1);
        }
        if (old2 == last || old2 == max){
            old2 = null;
        }else {
            remove(old2);
        }
        if (min2 == last || min2 == max){
            min2 = null;
        }else {
            remove(min2);
        }
        return super.put(key, cache);
    }


    public synchronized ClassSqlCache get4Select(Object key){
        ClassSqlCache cache = super.get(key);
        cache.setCount(cache.getCount()+1);
        cache.setLastAccess(new Date());
        return cache;
    }
}
