package com.wozai.DTO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zengzihao on 2014/4/8.
 */
public class UserLocInfo implements Serializable{
    private String username;
    private Integer jfen;
    private String status;
    private String classroom_name;
    private String Date;
    private String rank;
    private List<LocationRecord> list;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getJfen() {
        return jfen;
    }

    public void setJfen(Integer jfen) {
        this.jfen = jfen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClassroom_name() {
        return classroom_name;
    }

    public void setClassroom_name(String classroom_name) {
        this.classroom_name = classroom_name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public List<LocationRecord> getList() {
        return list;
    }

    public void setList(List<LocationRecord> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "UserLocInfo{" +
                "username='" + username + '\'' +
                ", jfen=" + jfen +
                ", status='" + status + '\'' +
                ", classroom_name='" + classroom_name + '\'' +
                ", Date='" + Date + '\'' +
                ", rank='" + rank + '\'' +
                ", list=" + list +
                '}';
    }
}
