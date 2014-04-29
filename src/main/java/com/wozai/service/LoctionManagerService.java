package com.wozai.service;

import com.wozai.DTO.*;
import com.wozai.cache.ClassSqlCache;
import com.wozai.cache.SearchConditionList;
import com.wozai.cache.SearchResultMap;
import com.wozai.dao.SuperDao;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoctionManagerService.class);

    @Resource
    SearchResultMap searchResultMap;
    @Resource
    SearchConditionList searchConditionList;

    @Resource
    SuperDao superDao;
    public SearchLocReturnDTO getClassroom(String username,String lng,String lat,String condition,String startIdx,String endIdx){
        logger.info("用户：{}的查询条件为 {} 地点{},{}",username,condition,lat,lng);
        ClassInfo classInfo = ClassInfoTransfer.transfer2Query(condition,lng,lat,startIdx,endIdx);
        SearchCondition conditions = new SearchCondition();
        conditions.setCondition(condition);
        conditions.setLat(lat);
        conditions.setLng(lng);
        List<ClassInfo> list = null;
        if (searchResultMap.containsKey(classInfo)){
            logger.info("[查询缓存] 从缓存中查询成功" + classInfo);
            conditions.setIsFromCache("是");
            ClassSqlCache cache = searchResultMap.get4Select(classInfo);
            cache.setCount(cache.getCount()+1);
            cache.setLastAccess(new Date());
            list = cache.getList();
        }else {
            list  = superDao.getList("SearchLocMapper.querylocList",classInfo);
            ClassSqlCache cache = new ClassSqlCache();
            cache.setLastAccess(new Date());
            cache.setCount(1);
            logger.info("[查询缓存] 从数据库中查询成功" + classInfo);
            conditions.setIsFromCache("否");
            searchResultMap.put(classInfo,cache);
        }
        conditions.setParam(classInfo.toString());
        searchConditionList.add(conditions);
        List<Classroom> resultList = ClassInfoTransfer.transfer2Result(list,lng,lat);
        SearchLocReturnDTO dto = new SearchLocReturnDTO();
        dto.setList(resultList);
        return dto;
    }
    public List<ClassInfo> searchClassroom(ClassInfo classInfo){
        List<ClassInfo> list = superDao.getList("SearchLocMapper.querylocList",classInfo);
        if (list == null){
            return new ArrayList<ClassInfo>(0);
        }
        return list;
    }


}
