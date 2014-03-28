package com.wozai.cache;

import com.wozai.DTO.ClassInfo;
import com.wozai.DTO.Classroom;
import com.wozai.service.LoctionManagerService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by zengzihao on 2014/3/27.
 */
public class SearchResultTaskJob {
    private static final Logger logger = Logger.getLogger("com.wozai.cache.LoginAuthTaskJob");


    private SearchResultMap map;
    private LoctionManagerService service;
    public SearchResultTaskJob(SearchResultMap map,LoctionManagerService service){
        this.map = map;
        this.service = service;
    }
    public void resultCheck(){
        logger.info("[查询缓存] 开始同步数据库数据");
        if (map.size() == 0){
            return;
        }
        for (ClassInfo info : map.keySet()){
            try {
                List<ClassInfo> list =  service.searchClassroom(info);
                map.get(info).setList(list);
            }catch (Exception e){
                logger.info("[查询缓存] 查询数据库数据出错");
            }
        }
        System.gc();
        logger.info("[查询缓存] 同步数据库数据完成");
    }
}
