//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zzr.core.repository.dao;

import com.zzr.core.repository.map.ZzrMapper;
import com.zzr.util.page.Pager;
import com.zzr.util.page.PagerResult;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.util.ArrayList;
import java.util.List;

public class BaseDaoBak<T> {
    protected ZzrMapper<T> mapper;

    public BaseDaoBak() {
    }

    @Autowired
    public void setMapper(ZzrMapper<T> mapper) {
        this.mapper = mapper;
    }

    public ZzrMapper<T> getMapper() {
        return this.mapper;
    }

    public T get(Object key) {
        return this.mapper.selectByPrimaryKey(key);
    }

    public T save(T entity) {
        return this.mapper.insertSelective(entity) == 1?entity:null;
    }

    public int saveUseGeneratedKeys(T entity) {
        return this.mapper.insertUseGeneratedKeys(entity);
    }

    public int saveListUseGeneratedKeys(List<T> list) {
        return this.mapper.insertList(list);
    }

    public int saveList(List<T> list) {
        return this.mapper.insertList(list);
    }

    public int updateAbsolute(T entity) {
        return this.mapper.updateByPrimaryKey(entity);
    }

    public int update(T entity) {
        return this.updateSelective(entity);
    }

    public int updateSelective(T entity) {
        return this.mapper.updateByPrimaryKeySelective(entity);
    }

    public int updateByConditionSelective(T entity, Condition condition) {
        return this.mapper.updateByExampleSelective(entity, condition);
    }

    public int delete(T entity) {
        return this.mapper.delete(entity);
    }

    public int deleteByKey(T entity) {
        return this.mapper.deleteByPrimaryKey(entity);
    }

    public int deleteByCondition(Condition condition) {
        return this.mapper.deleteByExample(condition);
    }

    public List<T> select(T entity) {
        return this.mapper.select(entity);
    }

    public List<T> selectByCondition(Condition condition) {
        return this.mapper.selectByExample(condition);
    }

    public int count(T entity) {
        return this.mapper.selectCount(entity);
    }

    public int countByCondition(Condition condition) {
        return this.mapper.selectCountByExample(condition);
    }

    public List<T> selectPage(Condition condition, int pageNum, int pageSize) {
        return (List)(pageNum > 0 && pageSize > 0?this.mapper.selectByExampleAndRowBounds(condition, new RowBounds((pageNum - 1) * pageSize, pageSize)):new ArrayList());
    }

    public PagerResult<T> selectPage(T entity, Pager<?> page) {
        PagerResult result = new PagerResult(page.getLimit(), page.getOffset());
        result.setTotal(this.mapper.selectCount(entity));
        int pageindex = page.getOffset() / page.getLimit() + 1;
        result.setRows(this.selectPage(entity, pageindex, page.getLimit()));
        return result;
    }

    public PagerResult<T> selectPage(Condition condition, Pager<?> pager) {
        int pageindex = pager.getOffset() / pager.getLimit() + 1;
        int recordCount = this.countByCondition(condition);
        int pageSize = pager.getLimit();
        int pageCount = (recordCount + pageSize) / pageSize;
        if(pageindex > pageCount) {
            pageindex = pageCount;
            pager.setOffset((pageCount - 1) * pageSize);
        }

        PagerResult result = new PagerResult(pager.getLimit(), pager.getOffset());
        result.setRows(this.selectPage(condition, pageindex, pageSize));
        result.setTotal(recordCount);
        return result;
    }

    public List<T> selectPage(T entity, int pageNum, int pageSize) {
        return (List)(pageNum > 0 && pageSize >= 0?(pageSize > 0?this.mapper.selectByRowBounds(entity, new RowBounds((pageNum - 1) * pageSize, pageSize)):this.mapper.select(entity)):new ArrayList());
    }

    public PagerResult<T> selectPage(Pager<T> bootstrapPager) {
        PagerResult result = new PagerResult(bootstrapPager.getLimit(), bootstrapPager.getOffset());
        result.setTotal(this.mapper.selectCount(bootstrapPager.getCondition()));
        int pageindex = bootstrapPager.getOffset() / bootstrapPager.getLimit() + 1;
        result.setRows(this.selectPage(bootstrapPager.getCondition(), pageindex, bootstrapPager.getLimit()));
        return result;
    }
}
