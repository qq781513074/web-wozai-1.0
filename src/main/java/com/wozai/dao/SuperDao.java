package com.wozai.dao;


import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 13-12-19
 * Time: 下午12:56
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class SuperDao extends SqlSessionDaoSupport implements ISuperDao {
       protected static Logger logger = Logger.getLogger(SuperDao.class);
    @Resource
    SqlSessionFactory sqlSessionFactory;
    @Override
    public Integer update(String statementName, Object parameterObject) {
        return this.getSqlSession().update(statementName,parameterObject);
    }

    @Override
    public Integer delete(String statementName, Object parameterObject) {
        return this.getSqlSession().delete(statementName,parameterObject);
    }

    @Override
    public int insert(String statementName, Object param) {
        return this.getSqlSession().insert(statementName, param);
    }

    @Override
    public <T> List<T> getList(String statementName, Object parameterObject) {
        return this.getSqlSession().selectList(statementName, parameterObject);
    }

    @Override
    public <T, V> Map<T, V> getMap(String statementName, Object parameterObject, String key) {
        return this.getSqlSession().selectMap(statementName, parameterObject, key);
    }

    @Override
    public <T> T getObject(String statementName, Object parameterObject) {
        return (T) this.getSqlSession().selectOne(statementName, parameterObject);
    }

}
