package com.wozai.service;

import com.wozai.DTO.*;
import com.wozai.cache.ClassSqlCache;
import com.wozai.cache.SearchResultMap;
import com.wozai.dao.SuperDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 14-1-10
 * Time: 下午5:09
 * To change this template use File | Settings | File Templates.
 */
@Service
public class LoctionManagerService {
    private static final Logger logger = Logger.getLogger("com.wozai.service.UserAccountManagerService");

    @Resource
    SearchResultMap searchResultMap;

    @Resource
    SuperDao superDao;
    public SearchLocReturnDTO searchClassroom(String lng,String lat,String condition,String startIdx,String endIdx){
        ClassInfo classInfo = ClassInfoTransfer.transfer2Query(condition,lng,lat,startIdx,endIdx);
        List<ClassInfo> list = null;
        if (searchResultMap.containsKey(classInfo)){
            logger.info("[查询缓存] 从缓存中查询成功" + classInfo);
            list = searchResultMap.get4Select(classInfo).getList();
        }else {
            list  = superDao.getList("SearchLocMapper.querylocList",classInfo);
            ClassSqlCache cache = new ClassSqlCache();
            cache.setLastAccess(new Date());
            cache.setCount(1);
            searchResultMap.put(classInfo,cache);
        }
        List<Classroom> resultList = ClassInfoTransfer.transfer2Result(list,lng,lat);
        SearchLocReturnDTO dto = new SearchLocReturnDTO();
        dto.setList(resultList);
        return dto;
    }
    public List<ClassInfo> searchClassroom(ClassInfo classInfo){
        List<ClassInfo> list =   superDao.getList("SearchLocMapper.querylocList",classInfo);
        if (list == null){
            return new ArrayList<ClassInfo>(0);
        }
        return list;
    }


}
