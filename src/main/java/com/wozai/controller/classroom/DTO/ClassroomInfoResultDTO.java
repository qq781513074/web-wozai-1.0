package com.wozai.controller.classroom.DTO;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zengzihao on 14-3-19.
 */
public class ClassroomInfoResultDTO implements Serializable {
    private String classroom_name;
    private Integer classroom_cur_man;
    private Integer classroom_max_man;
    private Long loc_id;
    private String curr_status;
    private Integer curr_status_code;
    private String classroom_type;
    private Integer class1;
    private Integer class2;
    private Integer class3;
    private Integer class4;
    private Integer class5;
    private Integer class6;
    private Integer class7;
    private Integer class8;
    private Integer class9;
    private Integer class10;
    private Integer class11;
    private Integer class12;
    private Integer class_date;
    private Integer del_flag;

    public String getClassroom_name() {
        return classroom_name;
    }

    public void setClassroom_name(String classroom_name) {
        this.classroom_name = classroom_name;
    }

    public Integer getClassroom_cur_man() {
        return classroom_cur_man;
    }

    public void setClassroom_cur_man(Integer classroom_cur_man) {
        this.classroom_cur_man = classroom_cur_man;
    }

    public Integer getClassroom_max_man() {
        return classroom_max_man;
    }

    public void setClassroom_max_man(Integer classroom_max_man) {
        this.classroom_max_man = classroom_max_man;
    }

    public Long getLoc_id() {
        return loc_id;
    }

    public void setLoc_id(Long loc_id) {
        this.loc_id = loc_id;
    }

    public String getCurr_status() {
        return curr_status;
    }

    public void setCurr_status(String curr_status) {
        this.curr_status = curr_status;
    }

    public Integer getCurr_status_code() {
        return curr_status_code;
    }

    public void setCurr_status_code(Integer curr_status_code) {
        this.curr_status_code = curr_status_code;
    }

    public String getClassroom_type() {
        return classroom_type;
    }

    public void setClassroom_type(String classroom_type) {
        this.classroom_type = classroom_type;
    }

    public Integer getClass1() {
        return class1;
    }

    public void setClass1(Integer class1) {
        this.class1 = class1;
    }

    public Integer getClass2() {
        return class2;
    }

    public void setClass2(Integer class2) {
        this.class2 = class2;
    }

    public Integer getClass3() {
        return class3;
    }

    public void setClass3(Integer class3) {
        this.class3 = class3;
    }

    public Integer getClass4() {
        return class4;
    }

    public void setClass4(Integer class4) {
        this.class4 = class4;
    }

    public Integer getClass5() {
        return class5;
    }

    public void setClass5(Integer class5) {
        this.class5 = class5;
    }

    public Integer getClass6() {
        return class6;
    }

    public void setClass6(Integer class6) {
        this.class6 = class6;
    }

    public Integer getClass7() {
        return class7;
    }

    public void setClass7(Integer class7) {
        this.class7 = class7;
    }

    public Integer getClass8() {
        return class8;
    }

    public void setClass8(Integer class8) {
        this.class8 = class8;
    }

    public Integer getClass9() {
        return class9;
    }

    public void setClass9(Integer class9) {
        this.class9 = class9;
    }

    public Integer getClass10() {
        return class10;
    }

    public void setClass10(Integer class10) {
        this.class10 = class10;
    }

    public Integer getClass11() {
        return class11;
    }

    public void setClass11(Integer class11) {
        this.class11 = class11;
    }

    public Integer getClass12() {
        return class12;
    }

    public void setClass12(Integer class12) {
        this.class12 = class12;
    }

    public Integer getClass_date() {
        return class_date;
    }

    public void setClass_date(Integer class_date) {
        this.class_date = class_date;
    }

    public Integer getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }

    @Override
    public String toString() {
        return "ClassroomInfoResultDTO{" +
                "classroom_name='" + classroom_name + '\'' +
                ", classroom_cur_man='" + classroom_cur_man + '\'' +
                ", classroom_max_man='" + classroom_max_man + '\'' +
                ", loc_id=" + loc_id +
                ", curr_status='" + curr_status + '\'' +
                ", curr_status_code=" + curr_status_code +
                ", classroom_type='" + classroom_type + '\'' +
                ", class1=" + class1 +
                ", class2=" + class2 +
                ", class3=" + class3 +
                ", class4=" + class4 +
                ", class5=" + class5 +
                ", class6=" + class6 +
                ", class7=" + class7 +
                ", class8=" + class8 +
                ", class9=" + class9 +
                ", class10=" + class10 +
                ", class11=" + class11 +
                ", class12=" + class12 +
                ", class_date=" + class_date +
                ", del_flag=" + del_flag +
                '}';
    }
}
