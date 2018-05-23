package com.zzr.core.repository.dao;

import com.zzr.core.repository.map.ZzrMapper;
import com.zzr.util.page.Pager;
import com.zzr.util.page.PagerResult;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

public class BaseDao<T> {


	protected ZzrMapper<T> mapper;

	@Autowired
	public void setMapper(ZzrMapper<T> mapper) {
		this.mapper = mapper;
	}

	public ZzrMapper<T> getMapper() {
		return mapper;
	}

	/**
	 * 通过主键获取对象
	 * 
	 * @param key
	 *            主键，一般为Long
	 * @return
	 */
	public T get(Object key) {
		return BaseDaoHelper.get(key, mapper);
	}

	/**
	 * 保存对象，用于新建
	 * 
	 * @param entity
	 * @return
	 */
	public T save(T entity) {
		return BaseDaoHelper.save(entity, mapper);
	}

	/**
	 * 保存对象并自动生成Id
	 * 
	 * @param entity
	 * @return
	 */
	public int saveUseGeneratedKeys(T entity) {
		return BaseDaoHelper.saveUseGeneratedKeys(entity, mapper);
	}

	/**
	 * 批量插入，必须主键字段是id
	 * 
	 * @param list
	 * @return
	 */
	public int saveListUseGeneratedKeys(List<T> list) {
		return BaseDaoHelper.saveListUseGeneratedKeys(list, mapper);
	}
	
	/**
	 * 批量插入，必须主键字段是id
	 * 
	 * @param list
	 * @return
	 */
	public int saveList(List<T> list) {
		return BaseDaoHelper.saveList(list, mapper);
	}

	/**
	 * 绝对更新对象，对于没有值的属性会将数据库对应字段清空
	 * 
	 * @param entity
	 * @return
	 */
	public int updateAbsolute(T entity) {
		return BaseDaoHelper.updateAbsolute(entity, mapper);
	}
	
	/**
	 * 更新对象，对于没有值的属性会将数据库对应字段清空
	 * 
	 * @param entity
	 * @return
	 */
	public int update(T entity) {
		return BaseDaoHelper.update(entity, mapper);
	}

	/**
	 * 更新对象的非空属性字段
	 * 
	 * @param entity
	 * @return
	 */
	public int updateSelective(T entity) {
		return BaseDaoHelper.updateSelective(entity, mapper);
	}

	/**
	 * 通过条件查询出对象，并将实体类对象中赋值的属性对应字段更新到条件查询出的对象
	 * 
	 * @param entity
	 * @param condition
	 * @return
	 */
	public int updateByConditionSelective(T entity, Condition condition) {
		return BaseDaoHelper.updateByConditionSelective(entity, condition, mapper);
	}

	/**
	 * 删除对象，条件为对象赋值的属性
	 * 
	 * @param entity
	 * @return
	 */
	public int delete(T entity) {
		return BaseDaoHelper.delete(entity, mapper);
	}

	/**
	 * 通过对象的主键属性查询并删除对象
	 * 
	 * @param entity
	 * @return
	 */
	public int deleteByKey(T entity) {
		return BaseDaoHelper.deleteByKey(entity, mapper);
	}

	/**
	 * 通过调节查询出对象并删除
	 * 
	 * @param condition
	 * @return
	 */
	public int deleteByCondition(Condition condition) {
		return BaseDaoHelper.deleteByCondition(condition, mapper);
	}

	/**
	 * 通过对象属性的条件查询出对象
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> select(T entity) {
		return BaseDaoHelper.select(entity, mapper);
	}

	/**
	 * 通过条件查询出对象
	 * 
	 * @param condition
	 * @return
	 */
	public List<T> selectByCondition(Condition condition) {
		return BaseDaoHelper.selectByCondition(condition, mapper);
	}

	/**
	 * 通过对象属性的条件查询出对象数量
	 * 
	 * @param entity
	 * @return
	 */
	public int count(T entity) {
		return BaseDaoHelper.count(entity, mapper);
	}

	/**
	 * 通过条件查询出对象
	 * 
	 * @param condition
	 * @return
	 */
	public int countByCondition(Condition condition) {
		return BaseDaoHelper.countByCondition(condition, mapper);
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
	public List<T> selectPage(Condition condition, int pageNum, int pageSize) {
		return BaseDaoHelper.selectPage(condition, pageNum, pageSize, mapper);
	}

	/**
	 * 单表分页查询
	 * 
	 * @param page
	 *            查询条件
	 * @param page
	 *            分页封装类
	 * @return
	 */
	public PagerResult<T> selectPage(T entity, Pager<?> page) {
		return BaseDaoHelper.selectPage(entity, page, mapper);
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
	public PagerResult<T> selectPage(Condition condition, Pager<?> pager) {
		return BaseDaoHelper.selectPage(condition, pager, mapper);
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
	public List<T> selectPage(T entity, int pageNum, int pageSize) {
		return BaseDaoHelper.selectPage(entity, pageNum, pageSize, mapper);
	}

	/**
     * 根据分页条件获取分页数据
     * @param pager
     * @return
     */
    public PagerResult<T> selectPage(Pager<T> pager) {
        return BaseDaoHelper.selectPage(pager, mapper);
    }
    
}
