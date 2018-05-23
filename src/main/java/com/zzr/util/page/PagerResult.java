//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zzr.util.page;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class PagerResult<T> implements Serializable {
    private static final long serialVersionUID = 3141067807832984876L;
    private int limit;
    private int offset;
    private List<T> rows;
    private int total;
    private String toUrl;
    private int count;
    private List<T> data;
    private int code=0; //状态码, 0表示成功
    private String msg;  //提示信息

    public PagerResult() {
        this.limit = 10;
        this.offset = 0;
        this.rows = Collections.emptyList();
    }

    public PagerResult(int limit) {
        this.limit = 10;
        this.offset = 0;
        this.rows = Collections.emptyList();
        this.setLimit(limit);
    }

    public PagerResult(int limit, int offset) {
        this(limit);
        this.setOffset(offset);
    }

    public PagerResult(int limit, int offset, String toUrl) {
        this(limit, offset);
        this.toUrl = toUrl;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        if(limit > 0) {
            this.limit = limit;
        }

    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int offset) {
        if(offset < 0) {
            offset = 0;
        }

        this.offset = offset;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<T> getRows() {
        return this.rows;
    }

    public int getCount() {
        return this.total;
    }

    public List<T> getData() {
        return this.rows;
    }

    public List<T> getResult() {
        return this.rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return this.total;
    }

    public int getTotalRows() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getToUrl() {
        return this.toUrl;
    }

    public void setToUrl(String toUrl) {
        this.toUrl = toUrl;
    }

    public int getPageCount() {
        return this.getTotal() % this.getLimit() == 0?this.getTotal() / this.getLimit():this.getTotal() / this.getLimit() + 1;
    }

    public int getPageSize() {
        return this.limit;
    }

    public int getPageNo() {
        return this.getOffset() % this.getLimit() == 0?this.getOffset() / this.getLimit() + 1:this.getOffset() / this.getLimit();
    }


    public <K> PagerResult<K> clonePagerResult(PagerResult<K> result) {
        if(result == null) {
            result = new PagerResult();
        }

        result.limit = this.limit;
        result.total = this.total;
        result.offset = this.offset;
        result.toUrl = this.toUrl;
        return result;
    }

    public static <T> PagerResult<T> emptyPagerResult() {
        PagerResult result = new PagerResult();
        result.setTotal(0);
        return result;
    }

    public interface Converter<T, K> {
        K convert(T var1);
    }
}
