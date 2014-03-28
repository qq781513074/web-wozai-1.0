package com.wozai.DTO;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: cdfanyu
 * Date: 13-10-14
 * Time: 下午2:22
 * To change this template use File | Settings | File Templates.
 */
public class BaseResultDTO implements Serializable {


    private static final long serialVersionUID = 2196722280310177229L;
    /**
     * 实体主键
     */
    private Long id;

    /**
     * 记录创建人
     */
    private String created;

    /**
     * 记录创建时间
     */
    private Date createdDate;

    /**
     * 记录最后修改人
     */
    private String modified;

    /**
     * 记录最后修改时间
     */
    private Date modifiedDate;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
