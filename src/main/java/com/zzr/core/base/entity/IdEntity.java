//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zzr.core.base.entity;

import java.sql.Timestamp;
import java.util.Date;

public abstract class IdEntity extends IdOnlyEntity {
    protected Integer updateCount;
    protected Timestamp createTime;
    protected Timestamp modifyTime;
    protected Integer deleted = Integer.valueOf(0);

    public void updateModifyTime() {
        this.setModifyTime(new Timestamp((new Date()).getTime()));
    }

    public IdEntity() {
    }

    public Integer getUpdateCount() {
        return this.updateCount;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public Timestamp getModifyTime() {
        return this.modifyTime;
    }

    public Integer getDeleted() {
        return this.deleted;
    }

    public void setUpdateCount(Integer updateCount) {
        this.updateCount = updateCount;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof IdEntity)) {
            return false;
        } else {
            IdEntity other = (IdEntity)o;
            if(!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Integer this$updateCount = this.getUpdateCount();
                    Integer other$updateCount = other.getUpdateCount();
                    if(this$updateCount == null) {
                        if(other$updateCount == null) {
                            break label59;
                        }
                    } else if(this$updateCount.equals(other$updateCount)) {
                        break label59;
                    }

                    return false;
                }

                Timestamp this$createTime = this.getCreateTime();
                Timestamp other$createTime = other.getCreateTime();
                if(this$createTime == null) {
                    if(other$createTime != null) {
                        return false;
                    }
                } else if(!this$createTime.equals(other$createTime)) {
                    return false;
                }

                Timestamp this$modifyTime = this.getModifyTime();
                Timestamp other$modifyTime = other.getModifyTime();
                if(this$modifyTime == null) {
                    if(other$modifyTime != null) {
                        return false;
                    }
                } else if(!this$modifyTime.equals(other$modifyTime)) {
                    return false;
                }

                Integer this$deleted = this.getDeleted();
                Integer other$deleted = other.getDeleted();
                if(this$deleted == null) {
                    if(other$deleted != null) {
                        return false;
                    }
                } else if(!this$deleted.equals(other$deleted)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Override
    protected boolean canEqual(Object other) {
        return other instanceof IdEntity;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        byte result = 1;
        Integer $updateCount = this.getUpdateCount();
        int result1 = result * 59 + ($updateCount == null?43:$updateCount.hashCode());
        Timestamp $createTime = this.getCreateTime();
        result1 = result1 * 59 + ($createTime == null?43:$createTime.hashCode());
        Timestamp $modifyTime = this.getModifyTime();
        result1 = result1 * 59 + ($modifyTime == null?43:$modifyTime.hashCode());
        Integer $deleted = this.getDeleted();
        result1 = result1 * 59 + ($deleted == null?43:$deleted.hashCode());
        return result1;
    }

    @Override
    public String toString() {
        return "IdEntity(updateCount=" + this.getUpdateCount() + ", createTime=" + this.getCreateTime() + ", modifyTime=" + this.getModifyTime() + ", deleted=" + this.getDeleted() + ")";
    }
}
