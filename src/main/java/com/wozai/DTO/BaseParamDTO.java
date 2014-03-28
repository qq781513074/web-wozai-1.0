package com.wozai.DTO;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: cdfanyu
 * Date: 13-10-15
 * Time: 下午5:59
 * To change this template use File | Settings | File Templates.
 */
public class BaseParamDTO implements Serializable {


    private static final long serialVersionUID = -3521553457212773566L;
    /**
     * 分页使用的参数，分页大小
     */
    private Integer pageSize;

    /**
     * 分页使用的参数，当前分页号
     */
    private Integer pageNum;

    /**
     * 是否分页
     */
    private boolean page = true;

    /**
     * 查询记录开始行号
     */
    private Integer startIdx;

    /**
     * 查询记录结束行号
     */
    private Integer endIdx;

    /**
     * 应用编号
     */
    private String appCode;

    /**
     * id主键
     */
    private Long id;

    /**
     * 创建人
     */
    private String created;

    /**
     * 创建时间
     */
    private Date createdDate;

    /**
     * 查询创建时间范围的最早值
     */
    private Date earlyCreatedDate;

    /**
     * 查询创建时间范围的最晚值
     */
    private Date lateCreatedDate;

    /**
     * 创建ip
     */
    private String createdIp;

    /**
     * 修改人
     */
    private String modified;

    /**
     * 修改时间
     */
    private Date modifiedDate;

    /**
     * 查询修改时间范围的最早值
     */
    private Date earlyModifiedDate;

    /**
     * 查询修改时间范围的最晚值
     */
    private Date lateModifiedDate;

    /**
     * 修改ip
     */
    private String modifiedIp;

    private String local_disk;  //用户硬盘序列号信息;
    private String local_network; //用户获取网卡信息;
    private String local_nic;  //用户获取cpuid号信息

    /**
     * 登录凭证
     */
    private String auth;

    /**
     * 跟踪
     */
    private String track;

    public BaseParamDTO() {
//        try {
//            track = IPUtil.findLocalHost() + "-" + System.currentTimeMillis();
//        } catch (Exception e) {
//            //ignore
//        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public boolean getPage() {
        return page;
    }

    public void setPage(boolean page) {
        this.page = page;
    }

    public Integer getStartIdx() {
        return startIdx;
    }

    public void setStartIdx(Integer startIdx) {
        this.startIdx = startIdx;
    }

    public Integer getEndIdx() {
        return endIdx;
    }

    public void setEndIdx(Integer endIdx) {
        this.endIdx = endIdx;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getEarlyCreatedDate() {
        return earlyCreatedDate;
    }

    public void setEarlyCreatedDate(Date earlyCreatedDate) {
        this.earlyCreatedDate = earlyCreatedDate;
    }

    public Date getLateCreatedDate() {
        return lateCreatedDate;
    }

    public void setLateCreatedDate(Date lateCreatedDate) {
        this.lateCreatedDate = lateCreatedDate;
    }

    public String getCreatedIp() {
        return createdIp;
    }

    public void setCreatedIp(String createdIp) {
        this.createdIp = createdIp;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getEarlyModifiedDate() {
        return earlyModifiedDate;
    }

    public void setEarlyModifiedDate(Date earlyModifiedDate) {
        this.earlyModifiedDate = earlyModifiedDate;
    }

    public Date getLateModifiedDate() {
        return lateModifiedDate;
    }

    public void setLateModifiedDate(Date lateModifiedDate) {
        this.lateModifiedDate = lateModifiedDate;
    }

    public String getModifiedIp() {
        return modifiedIp;
    }

    public void setModifiedIp(String modifiedIp) {
        this.modifiedIp = modifiedIp;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getLocal_disk() {
        return local_disk;
    }

    public void setLocal_disk(String local_disk) {
        this.local_disk = local_disk;
    }

    public String getLocal_network() {
        return local_network;
    }

    public void setLocal_network(String local_network) {
        this.local_network = local_network;
    }

    public String getLocal_nic() {
        return local_nic;
    }

    public void setLocal_nic(String local_nic) {
        this.local_nic = local_nic;
    }

    public String getTrack() {
        return track;
    }


    public String getAuth() {
        return auth;
    }


    public void setTrack(String track) {
        this.track = track;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

}
