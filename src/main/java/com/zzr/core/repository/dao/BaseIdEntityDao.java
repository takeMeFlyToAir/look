package com.zzr.core.repository.dao;

import com.zzr.core.base.entity.IdEntity;
import com.zzr.util.page.Pager;
import com.zzr.util.page.PagerResult;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * 用于IdEntity的基础Service
 * @author li banggui
 *
 * @param <T>
 */
public class BaseIdEntityDao<T extends IdEntity> extends BaseDao<T> {

    /* 
     * 保存对象
     */
    @Override
    public T save(T entity){
    	
        return BaseIdEntityDaoHelper.save(entity, getMapper());
    }
    /*
     * 保存对象，并字段生成id属性值
     */
    @Override
    public int saveUseGeneratedKeys(T entity){
        return BaseIdEntityDaoHelper.saveUseGeneratedKeys(entity, getMapper());
    }
    
    /* 
     * 批量插入，必须主键字段是id
     */
    @Override
    public int saveListUseGeneratedKeys(List<T> list){
        return BaseIdEntityDaoHelper.saveListUseGeneratedKeys(list, getMapper());
    }
    
    /**
	 * 批量插入，必须主键字段是id
	 * 
	 * @param list
	 * @return
	 */
	@Override
    public int saveList(List<T> list) {
        return BaseIdEntityDaoHelper.saveList(list, getMapper());
	}
    
    /* 
     * 更新对象，对于没有值的属性会将数据库对应字段清空
     */
    @Override
    public int update(T entity) {
    	return BaseIdEntityDaoHelper.update(entity, getMapper());
    }
    
    /* 
     * 更新对象的非空属性字段
     */
    @Override
    public int updateSelective(T entity) {
    	return BaseIdEntityDaoHelper.updateSelective(entity, getMapper());
    }
    
    @Override
    public int delete(T entity) {
    	return BaseIdEntityDaoHelper.delete(entity, getMapper());
    }

	/**
	 * 通过调节查询出对象并删除
	 *
	 * @param condition
	 * @return
	 */
	@Override
    public int deleteByCondition(Condition condition) {
		return BaseIdEntityDaoHelper.deleteByCondition(condition, getMapper());
	}
    
    /**
     * <物理删除，特殊时候调用> 
     * <功能详细描述>
     * @author Banggui.Li
     * @date 2017年1月10日
     * @param entity
     * @return
     */
    public int physicsDelete(T entity) {
    	return BaseIdEntityDaoHelper.physicsDelete(entity, getMapper());
    }
    
    
    /***************************************************
     * 
     * 
     * 
     ***************************************************/
    
    /**
	 * 通过对象属性的条件查询出对象
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> select(T entity) {
		return BaseIdEntityDaoHelper.select(entity, getMapper());
	}

	/**
	 * 通过条件查询出对象
	 * 
	 * @param condition
	 * @return
	 */
	@Override
    public List<T> selectByCondition(Condition condition) {
		return BaseIdEntityDaoHelper.selectByCondition(condition, getMapper());
	}

	/**
	 * 通过对象属性的条件查询出对象数量
	 * 
	 * @param entity
	 * @return
	 */
	@Override
    public int count(T entity) {
		return BaseIdEntityDaoHelper.count(entity, getMapper());
	}

	/**
	 * 通过条件查询出对象
	 * 
	 * @param condition
	 * @return
	 */
	@Override
    public int countByCondition(Condition condition) {
		return BaseIdEntityDaoHelper.countByCondition(condition, getMapper());
	}

	/**
	 * 单表分页查询
	 * 
	 * @param condition
	 *            查询条件
	 * @param pageNum
	 *            页码数
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	@Override
    public List<T> selectPage(Condition condition, int pageNum, int pageSize) {
		return BaseIdEntityDaoHelper.selectPage(condition, pageNum, pageSize, getMapper());
	}

	/**
	 * 单表分页查询
	 * 
	 * @param entity
	 *            查询条件
	 * @param pager
	 *            分页封装类
	 * @return
	 */
	@Override
    public PagerResult<T> selectPage(T entity, Pager<?> pager) {
		return BaseIdEntityDaoHelper.selectPage(entity, pager, getMapper());
		
	}

	/**
	 * 单表分页查询
	 * 
	 * @param condition
	 *            查询条件
	 * @param pager
	 *            分页封装类
	 * @return
	 */
	@Override
    public PagerResult<T> selectPage(Condition condition, Pager<?> pager) {
		return BaseIdEntityDaoHelper.selectPage(condition, pager, getMapper());
	}

	/**
	 * 单表分页查询
	 * 
	 * @param entity
	 *            查询条件
	 * @param pageNum
	 *            页码数
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	@Override
    public List<T> selectPage(T entity, int pageNum, int pageSize) {
		return BaseIdEntityDaoHelper.selectPage(entity, pageNum, pageSize, getMapper());
	}

	/**
     * 根据分页条件获取分页数据
     * @param pager
     * @return
     */
    @Override
    public PagerResult<T> selectPage(Pager<T> pager) {
    	return BaseIdEntityDaoHelper.selectPage(pager, getMapper());
    }
}
