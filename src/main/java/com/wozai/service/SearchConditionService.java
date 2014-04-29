package com.wozai.service;

import com.wozai.DTO.SearchCondition;
import com.wozai.dao.SuperDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zengzihao on 2014/4/8.
 */
@Service
public class SearchConditionService {
    private static final Logger logger = Logger.getLogger("com.wozai.service.LoginByNuistManagerService");
    @Resource
    SuperDao superDao;
    public Boolean addConditionRecord(SearchCondition condition){
        logger.info("[统计信息]存储查询条件");
      Integer integer = superDao.insert("SearchConditionMapper.insertInfo",condition);
        if (integer > 0){
            logger.info("[统计信息]存储查询条件成功");
            return true;
        }else {
            return false;
        }
    }
}
