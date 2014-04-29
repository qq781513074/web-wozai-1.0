package com.wozai.DTO;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zengzihao on 2014/4/8.
 */
public class SearchCondition implements Serializable {
    private String username;
    private String lat;
    private String lng;
    private Date createdDate;
    private String condition;
    private String param;
    private String isFromCache;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getIsFromCache() {
        return isFromCache;
    }

    public void setIsFromCache(String isFromCache) {
        this.isFromCache = isFromCache;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "username='" + username + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", createdDate=" + createdDate +
                ", condition='" + condition + '\'' +
                ", param='" + param + '\'' +
                ", isFromCache=" + isFromCache +
                '}';
    }
}
