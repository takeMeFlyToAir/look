package com.zzr.core.repository.dao;

import com.zzr.core.repository.map.ZzrMapper;
import com.zzr.util.page.Pager;
import com.zzr.util.page.PagerResult;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.entity.Condition;

import java.util.ArrayList;
import java.util.List;

public class BaseDaoHelper {

	/**
	 * 通过主键获取对象
	 * 
	 * @param key
	 *            主键，一般为Long
	 * @return
	 */
	public static <T> T get(Object key, ZzrMapper<T> mapper) {
		return mapper.selectByPrimaryKey(key);
	}

	/**
	 * 保存对象，用于新建
	 * 
	 * @param entity
	 * @return
	 */
	public static <T> T save(T entity, ZzrMapper<T> mapper) {
		if(mapper.insertSelective(entity)==1) {
			return entity;
		} else {
			return null;
		}
	}

	/**
	 * 保存对象并自动生成Id
	 * 
	 * @param entity
	 * @return
	 */
	public static <T> int saveUseGeneratedKeys(T entity, ZzrMapper<T> mapper) {
		return mapper.insertUseGeneratedKeys(entity);
	}

	/**
	 * 批量插入，必须主键字段是id
	 * 
	 * @param list
	 * @return
	 */
	public static <T> int saveListUseGeneratedKeys(List<T> list, ZzrMapper<T> mapper) {
		return mapper.insertList(list);
	}
	
	/**
	 * 批量插入，必须主键字段是id
	 * 
	 * @param list
	 * @return
	 */
	public static <T> int saveList(List<T> list, ZzrMapper<T> mapper) {
		return mapper.insertList(list);
	}

	/**
	 * 绝对更新对象，对于没有值的属性会将数据库对应字段清空
	 * 
	 * @param entity
	 * @return
	 */
	public static <T> int updateAbsolute(T entity, ZzrMapper<T> mapper) {
		return mapper.updateByPrimaryKey(entity);
	}
	
	/**
	 * 更新对象，对于没有值的属性会将数据库对应字段清空
	 * 
	 * @param entity
	 * @return
	 */
	public static <T> int update(T entity, ZzrMapper<T> mapper) {
		return updateSelective(entity, mapper);
	}

	/**
	 * 更新对象的非空属性字段
	 * 
	 * @param entity
	 * @return
	 */
	public static <T> int updateSelective(T entity, ZzrMapper<T> mapper) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 通过条件查询出对象，并将实体类对象中赋值的属性对应字段更新到条件查询出的对象
	 * 
	 * @param entity
	 * @param condition
	 * @return
	 */
	public static <T> int updateByConditionSelective(T entity, Condition condition, ZzrMapper<T> mapper) {
		return mapper.updateByExampleSelective(entity, condition);
	}

	/**
	 * 删除对象，条件为对象赋值的属性
	 * 
	 * @param entity
	 * @return
	 */
	public static <T> int delete(T entity, ZzrMapper<T> mapper) {
		return mapper.delete(entity);
	}

	/**
	 * 通过对象的主键属性查询并删除对象
	 * 
	 * @param entity
	 * @return
	 */
	public static <T> int deleteByKey(T entity, ZzrMapper<T> mapper) {
		return mapper.deleteByPrimaryKey(entity);
	}

	/**
	 * 通过调节查询出对象并删除
	 * 
	 * @param condition
	 * @return
	 */
	public static <T> int deleteByCondition(Condition condition, ZzrMapper<T> mapper) {
		return mapper.deleteByExample(condition);
	}

	/**
	 * 通过对象属性的条件查询出对象
	 * 
	 * @param entity
	 * @return
	 */
	public static <T> List<T> select(T entity, ZzrMapper<T> mapper) {
		return mapper.select(entity);
	}

	/**
	 * 通过条件查询出对象
	 * 
	 * @param condition
	 * @return
	 */
	public static <T> List<T> selectByCondition(Condition condition, ZzrMapper<T> mapper) {
		return mapper.selectByExample(condition);
	}

	/**
	 * 通过对象属性的条件查询出对象数量
	 * 
	 * @param entity
	 * @return
	 */
	public static <T> int count(T entity, ZzrMapper<T> mapper) {
		return mapper.selectCount(entity);
	}

	/**
	 * 通过条件查询出对象
	 * 
	 * @param condition
	 * @return
	 */
	public static <T> int countByCondition(Condition condition, ZzrMapper<T> mapper) {
		return mapper.selectCountByExample(condition);
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
	public static <T> List<T> selectPage(Condition condition, int pageNum, int pageSize, ZzrMapper<T> mapper) {
		if (pageNum <= 0 || pageSize <= 0) {
			return new ArrayList<T>();
		}
		return mapper.selectByExampleAndRowBounds(condition, new RowBounds((pageNum - 1) * pageSize, pageSize));
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
	public static <T> PagerResult<T> selectPage(T entity, Pager<?> page, ZzrMapper<T> mapper) {
		PagerResult<T> result = new PagerResult<T>(page.getLimit(), page.getOffset());
        result.setTotal(mapper.selectCount(entity));
        
        int pageindex = (page.getOffset()/page.getLimit()) + 1;
        result.setRows(selectPage(entity, pageindex, page.getLimit(), mapper));
        
		return result;
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
	public static <T> PagerResult<T> selectPage(Condition condition, Pager<?> pager, ZzrMapper<T> mapper) {
		int pageindex = (pager.getOffset() / pager.getLimit()) + 1;
		int recordCount = countByCondition(condition, mapper);
		int pageSize = pager.getLimit();
		//int pageCount = ((recordCount + pageSize - 1) / pageSize);
		int pageCount = ((recordCount + pageSize ) / pageSize);

		if (pageindex > pageCount) {
			pageindex = pageCount;
			pager.setOffset((pageCount - 1) * pageSize);
		}

		PagerResult<T> result = new PagerResult<T>(pager.getLimit(), pager.getOffset());
		result.setRows(selectPage(condition, pageindex, pageSize, mapper));
		result.setTotal(recordCount);
		return result;
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
	public static <T> List<T> selectPage(T entity, int pageNum, int pageSize, ZzrMapper<T> mapper) {
		if (pageNum <= 0 || pageSize < 0) {
			return new ArrayList<T>();
		}
		if (pageSize > 0) {
			return mapper.selectByRowBounds(entity, new RowBounds((pageNum - 1) * pageSize, pageSize));
		} else {
			return mapper.select(entity);
		}
	}

	/**
     * 根据分页条件获取分页数据
     * @param pager
     * @return
     */
    public static <T> PagerResult<T> selectPage(Pager<T> pager, ZzrMapper<T> mapper) {
        PagerResult<T> result = new PagerResult<T>(pager.getLimit(), pager.getOffset());
        result.setTotal(mapper.selectCount(pager.getCondition()));
        
        int pageindex = (pager.getOffset()/pager.getLimit()) + 1;
        result.setRows(selectPage(pager.getCondition(), pageindex, pager.getLimit(), mapper));
        
        return result;
    }
    
}
