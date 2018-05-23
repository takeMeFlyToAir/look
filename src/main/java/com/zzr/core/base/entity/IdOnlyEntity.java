//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zzr.core.base.entity;

import javax.persistence.Id;
import java.io.Serializable;

public abstract class IdOnlyEntity implements Serializable {
    private static final long serialVersionUID = 4023069244725177118L;
    @Id
    protected Long id;

    public IdOnlyEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof IdOnlyEntity)) {
            return false;
        } else {
            IdOnlyEntity other = (IdOnlyEntity)o;
            if(!other.canEqual(this)) {
                return false;
            } else {
                Long this$id = this.getId();
                Long other$id = other.getId();
                if(this$id == null) {
                    if(other$id != null) {
                        return false;
                    }
                } else if(!this$id.equals(other$id)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof IdOnlyEntity;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        byte result = 1;
        Long $id = this.getId();
        int result1 = result * 59 + ($id == null?43:$id.hashCode());
        return result1;
    }

    @Override
    public String toString() {
        return "IdOnlyEntity(id=" + this.getId() + ")";
    }
}
