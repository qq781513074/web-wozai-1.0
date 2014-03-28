package com.wozai.DTO;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 14-1-10
 * Time: 下午5:15
 * To change this template use File | Settings | File Templates.
 */
public class Condition {
   private boolean hasAirCondition;
   private boolean isQuite;
   private Date beginDate;
   private Date endDate;
    /**
     * true 为人少
     * false 为人多
     */
   private boolean manNum;
    /**
     * true order by close;
     */
   private boolean close;
    /**
     *  true  美女多的教室
     */
   private boolean female;

   private boolean hasCloseCantin;

   private Condition getCondition(){
       return null;
   }

    public boolean isHasAirCondition() {
        return hasAirCondition;
    }

    public void setHasAirCondition(boolean hasAirCondition) {
        this.hasAirCondition = hasAirCondition;
    }

    public boolean isQuite() {
        return isQuite;
    }

    public void setQuite(boolean quite) {
        isQuite = quite;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isManNum() {
        return manNum;
    }

    public void setManNum(boolean manNum) {
        this.manNum = manNum;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public boolean isFemale() {
        return female;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public boolean isHasCloseCantin() {
        return hasCloseCantin;
    }

    public void setHasCloseCantin(boolean hasCloseCantin) {
        this.hasCloseCantin = hasCloseCantin;
    }
}
