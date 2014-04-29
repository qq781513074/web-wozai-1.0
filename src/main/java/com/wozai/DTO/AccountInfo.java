package com.wozai.DTO;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 13-12-19
 * Time: 下午5:15
 * To change this template use File | Settings | File Templates.
 */
public class AccountInfo {
   private String userId;
    private String nickname;
    private Integer unreadMsg;
    private Integer wrongTimes;
    private String userPwd;
    private String userStatus;
    private String locId;
    private String defaultLoc;
    private Date createTime;
    private Integer jfen;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getUnreadMsg() {
        return unreadMsg;
    }

    public void setUnreadMsg(Integer unreadMsg) {
        this.unreadMsg = unreadMsg;
    }

    public Integer getWrongTimes() {
        return wrongTimes;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getDefaultLoc() {
        return defaultLoc;
    }

    public void setDefaultLoc(String defaultLoc) {
        this.defaultLoc = defaultLoc;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getJfen() {
        return jfen;
    }

    public void setJfen(Integer jfen) {
        this.jfen = jfen;
    }



    public void setWrongTimes(Integer wrongTimes) {
        this.wrongTimes = wrongTimes;
    }

    public String getLocId() {
        return locId;
    }

    public void setLocId(String locId) {
        this.locId = locId;
    }

}
