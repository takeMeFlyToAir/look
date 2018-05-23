

package com.zzr.util.page;

import java.io.Serializable;

public class Pager<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int pageSize;
    private int pageNo;
    private int offset;
    private T condition;
    private String sortName;
    private String sortOrder;

    public Pager() {
        this.pageSize = 10;
        this.pageNo = 1;
        this.offset = 0;
    }

    public Pager(int pageSize) {
        this.pageSize = 10;
        this.pageNo = 1;
        this.offset = 0;
        this.setPageSize(pageSize);
    }

    public Pager(int pageSize, T condition) {
        this(pageSize);
        this.condition = condition;
    }

    public Pager(int pageSize, int pageNo) {
        this(pageSize);
        this.setPageNo(pageNo);
    }

    public Pager(int pageSize, int pageNo, int offset) {
        this.pageSize = 10;
        this.pageNo = 1;
        this.offset = 0;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.offset = offset;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        if(pageSize > 0) {
            this.pageSize = pageSize;
            this.recalcOffset();
        }

    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        if(pageNo > 0) {
            this.pageNo = pageNo;
            this.recalcOffset();
        }

    }

    private void recalcOffset() {
        if(this.pageNo > 0 && this.offset == 0) {
            this.offset = (this.pageNo - 1) * this.pageSize;
        }

    }

    public int getStart() {
        return this.offset;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setLimit(int limit) {
        this.setPageSize(limit);
    }

    public int getLimit() {
        return this.getPageSize();
    }

    public T getCondition() {
        return this.condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }

    public String getSortName() {
        return this.sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSort() {
        return this.sortName;
    }

    public void setSort(String sort) {
        this.sortName = sort;
    }

    public String getOrder() {
        return this.sortOrder;
    }

    public void setOrder(String order) {
        this.sortOrder = order;
    }

    public <K> Pager<K> clonePager() {
        Pager pager = new Pager(this.getPageSize(), this.getPageNo(), this.getOffset());
        pager.setSortName(this.getSortName());
        pager.setSortOrder(this.getSortOrder());
        return pager;
    }
}
