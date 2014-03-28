package com.wozai.DTO;

import java.io.Serializable;

/**
 * Created by Administrator on 14-2-25.
 */
public class Classroom implements Serializable{
    private String distance;
    private String name;
    private String msg;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
