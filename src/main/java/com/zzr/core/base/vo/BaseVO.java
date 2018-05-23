package com.zzr.core.base.vo;

import java.sql.Timestamp;

/**
 * Created by sjgtw-zzr on 2018/3/16.
 */
public abstract class BaseVO {
    protected Long id;
    protected Timestamp createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
