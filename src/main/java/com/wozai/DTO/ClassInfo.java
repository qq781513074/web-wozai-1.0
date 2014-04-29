package com.wozai.DTO;

import java.io.Serializable;
import java.sql.Date;

public class ClassInfo extends BaseParamDTO{
/*	   classroom_cur_man    int,
	   classroom_max_man    int,
	   classroom_name       varchar(40),
	   loc_id               bigint,
	   curr_status          varchar(100),
	   curr_status_code     int,
	   classroom_type       varchar(40),
	   class1               int,
	   class2               int,
	   class3               int,
	   class4               int,
	   class5               int,
	   class6               int,
	   class7               int,
	   class8               int,
	   class9               int,
	   class10              int,
	   class11              int,
	   class12              int,
	   class_date           int*/
	private Integer  classroom_cur_man;
	private Integer classroom_max_man;
	private String classroom_name = "";
	private Long loc_id;
	private String curr_status = "";
	private Integer curr_status_code;
	private String classroom_type = "";
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
	public String getClassroom_name() {
		return classroom_name;
	}
	public void setClassroom_name(String classroom_name) {
		this.classroom_name = classroom_name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassInfo)) return false;

        ClassInfo classInfo = (ClassInfo) o;

        if (class1 != null ? !class1.equals(classInfo.class1) : classInfo.class1 != null) return false;
        if (class10 != null ? !class10.equals(classInfo.class10) : classInfo.class10 != null) return false;
        if (class11 != null ? !class11.equals(classInfo.class11) : classInfo.class11 != null) return false;
        if (class12 != null ? !class12.equals(classInfo.class12) : classInfo.class12 != null) return false;
        if (class2 != null ? !class2.equals(classInfo.class2) : classInfo.class2 != null) return false;
        if (class3 != null ? !class3.equals(classInfo.class3) : classInfo.class3 != null) return false;
        if (class4 != null ? !class4.equals(classInfo.class4) : classInfo.class4 != null) return false;
        if (class5 != null ? !class5.equals(classInfo.class5) : classInfo.class5 != null) return false;
        if (class6 != null ? !class6.equals(classInfo.class6) : classInfo.class6 != null) return false;
        if (class7 != null ? !class7.equals(classInfo.class7) : classInfo.class7 != null) return false;
        if (class8 != null ? !class8.equals(classInfo.class8) : classInfo.class8 != null) return false;
        if (class9 != null ? !class9.equals(classInfo.class9) : classInfo.class9 != null) return false;
        if (class_date != null ? !class_date.equals(classInfo.class_date) : classInfo.class_date != null) return false;
        if (classroom_cur_man != null ? !classroom_cur_man.equals(classInfo.classroom_cur_man) : classInfo.classroom_cur_man != null)
            return false;
        if (classroom_max_man != null ? !classroom_max_man.equals(classInfo.classroom_max_man) : classInfo.classroom_max_man != null)
            return false;
        if (classroom_name != null ? !classroom_name.equals(classInfo.classroom_name) : classInfo.classroom_name != null)
            return false;
        if (classroom_type != null ? !classroom_type.equals(classInfo.classroom_type) : classInfo.classroom_type != null)
            return false;
        if (curr_status != null ? !curr_status.equals(classInfo.curr_status) : classInfo.curr_status != null)
            return false;
        if (curr_status_code != null ? !curr_status_code.equals(classInfo.curr_status_code) : classInfo.curr_status_code != null)
            return false;
        if (loc_id != null ? !loc_id.equals(classInfo.loc_id) : classInfo.loc_id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classroom_cur_man != null ? classroom_cur_man.hashCode() : 0;
        result = 31 * result + (classroom_max_man != null ? classroom_max_man.hashCode() : 0);
        result = 31 * result + (classroom_name != null ? classroom_name.hashCode() : 0);
        result = 31 * result + (loc_id != null ? loc_id.hashCode() : 0);
        result = 31 * result + (curr_status != null ? curr_status.hashCode() : 0);
        result = 31 * result + (curr_status_code != null ? curr_status_code.hashCode() : 0);
        result = 31 * result + (classroom_type != null ? classroom_type.hashCode() : 0);
        result = 31 * result + (class1 != null ? class1.hashCode() : 0);
        result = 31 * result + (class2 != null ? class2.hashCode() : 0);
        result = 31 * result + (class3 != null ? class3.hashCode() : 0);
        result = 31 * result + (class4 != null ? class4.hashCode() : 0);
        result = 31 * result + (class5 != null ? class5.hashCode() : 0);
        result = 31 * result + (class6 != null ? class6.hashCode() : 0);
        result = 31 * result + (class7 != null ? class7.hashCode() : 0);
        result = 31 * result + (class8 != null ? class8.hashCode() : 0);
        result = 31 * result + (class9 != null ? class9.hashCode() : 0);
        result = 31 * result + (class10 != null ? class10.hashCode() : 0);
        result = 31 * result + (class11 != null ? class11.hashCode() : 0);
        result = 31 * result + (class12 != null ? class12.hashCode() : 0);
        result = 31 * result + (class_date != null ? class_date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "classroom_cur_man=" + classroom_cur_man +
                ", classroom_max_man=" + classroom_max_man +
                ", classroom_name='" + classroom_name + '\'' +
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
                '}';
    }
}
