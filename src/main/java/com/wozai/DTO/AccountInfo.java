package com.wozai.DTO;

import org.apache.commons.lang.builder.ToStringBuilder;

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
    private String locInfo;
    private int unreadMsg;
    private int wrongTimes;
    private String userPwd;
    private String userStatus;
    private String defaultLoc;
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

    public String getLocInfo() {
        return locInfo;
    }

    public void setLocInfo(String locInfo) {
        this.locInfo = locInfo;
    }

    public int getUnreadMsg() {
        return unreadMsg;
    }

    public void setUnreadMsg(int unreadMsg) {
        this.unreadMsg = unreadMsg;
    }

    public int getWrongTimes() {
        return wrongTimes;
    }

    public void setWrongTimes(int wrongTimes) {
        this.wrongTimes = wrongTimes;
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

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }

    public String getDefaultLoc() {
        return defaultLoc;
    }

    public void setDefaultLoc(String defaultLoc) {
        this.defaultLoc = defaultLoc;
    }
}
