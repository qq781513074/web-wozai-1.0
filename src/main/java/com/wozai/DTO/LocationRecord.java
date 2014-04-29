package com.wozai.DTO;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zengzihao on 2014/4/8.
 */
public class LocationRecord implements Serializable{
    private Long recordId;
    private String userId;
    private Long locInfoId;
    private Long locId;
    private String method;
    private Date joinTime;
    private Date leftTime;
    private String lat;
    private String lng;
    private Integer jfen;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getLocInfoId() {
        return locInfoId;
    }

    public void setLocInfoId(Long locInfoId) {
        this.locInfoId = locInfoId;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public Date getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(Date leftTime) {
        this.leftTime = leftTime;
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

    public Integer getJfen() {
        return jfen;
    }

    public void setJfen(Integer jfen) {
        this.jfen = jfen;
    }

    @Override
    public String toString() {
        return "LocationRecord{" +
                "userId='" + userId + '\'' +
                ", locInfoId=" + locInfoId +
                ", locId=" + locId +
                ", method=" + method +
                ", joinTime=" + joinTime +
                ", leftTime=" + leftTime +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", jfen=" + jfen +
                '}';
    }
}
