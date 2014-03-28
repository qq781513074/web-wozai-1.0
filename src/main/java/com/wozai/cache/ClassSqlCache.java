package com.wozai.cache;

import com.wozai.DTO.ClassInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by zengzihao on 2014/3/27.
 */
public class ClassSqlCache implements Serializable {
    private Date lastAccess;
    private Integer count;
    private List<ClassInfo> list;

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ClassInfo> getList() {
        return list;
    }

    public void setList(List<ClassInfo> list) {
        this.list = list;
    }
}
