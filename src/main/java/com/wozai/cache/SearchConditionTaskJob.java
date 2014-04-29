package com.wozai.cache;

import com.wozai.DTO.SearchCondition;
import com.wozai.service.SearchConditionService;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengzihao on 2014/4/8.
 */
public class SearchConditionTaskJob {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SearchConditionTaskJob.class);
    private SearchConditionService searchConditionService;
    private SearchConditionList searchConditionList;
    public SearchConditionTaskJob(SearchConditionService searchConditionService, SearchConditionList searchConditionList){
        this.searchConditionList = searchConditionList;
        this.searchConditionService = searchConditionService;
    }
    public void saveCondition(){
        logger.info("[统计信息]开始同步教室查询统计信息到数据库");
        List<SearchCondition> list = new ArrayList<SearchCondition>();
        for (SearchCondition condition:searchConditionList){
            list.add(condition);
        }
        for (SearchCondition condition:list){
            try{
                searchConditionService.addConditionRecord(condition);
            }catch (Exception e){
                logger.error("插入查询条件数据失败{}",condition,e);
            }
        }
        logger.info("[统计信息]开始同步教室查询统计信息到数据库完成{}条",list.size());
        searchConditionList.removeAll(list);
        list = null;
        System.gc();
    }
}
