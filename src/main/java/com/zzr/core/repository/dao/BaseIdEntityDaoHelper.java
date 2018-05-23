package com.zzr.core.repository.dao;

import com.zzr.core.base.entity.IdEntity;
import com.zzr.core.repository.map.ZzrMapper;
import com.zzr.util.common.EntityUtil;
import com.zzr.util.page.Pager;
import com.zzr.util.page.PagerResult;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 用于IdEntity的基础Service
 * @author li banggui
 *
 */
public class BaseIdEntityDaoHelper {

    /**
     * 通过主键获取对象
     *
     * @param key
     *            主键，一般为Long
     * @return
     */
    public static <T extends IdEntity> T get(Object key, ZzrMapper<T> mapper) {
        return BaseDaoHelper.get(key, mapper);
    }

    /* 
     * 保存对象
     */
    public static <T extends IdEntity> T save(T entity, ZzrMapper<T> mapper){
    	
    	initEntityPersistenceProperties(entity);
        return BaseDaoHelper.save(entity, mapper);
    }

	private static <T extends IdEntity> void initEntityPersistenceProperties(T entity) {
		if(entity.getId()==null){
    		entity.setId(EntityUtil.getAutoId());
    	}
		Timestamp now = getNow();
		entity.setCreateTime(now);
    	entity.setModifyTime(now);
    	entity.setUpdateCount(0);
    	entity.setDeleted(0);
	}

	protected static Timestamp getNow() {
		return new Timestamp(System.currentTimeMillis());
	}
    
    /* 
     * 保存对象，并字段生成id属性值
     */
    public static <T extends IdEntity> int saveUseGeneratedKeys(T entity, ZzrMapper<T> mapper) {
    	initEntityPersistenceProperties(entity);
        return BaseDaoHelper.saveUseGeneratedKeys(entity, mapper);
    }
    
    /* 
     * 批量插入，必须主键字段是id
     */
    public static <T extends IdEntity> int saveListUseGeneratedKeys(List<T> list, ZzrMapper<T> mapper) {
    	for(T entity: list) {
    		initEntityPersistenceProperties(entity);
    	}
        return BaseDaoHelper.saveListUseGeneratedKeys(list, mapper);
    }
    
    /**
	 * 批量插入，必须主键字段是id
	 * 
	 * @param list
	 * @return
	 */
	public static <T extends IdEntity> int saveList(List<T> list, ZzrMapper<T> mapper) {
		for(T entity: list) {
    		initEntityPersistenceProperties(entity);
    	}
        return BaseDaoHelper.saveList(list, mapper);
	}
    
    /* 
     * 更新对象，对于没有值的属性会将数据库对应字段清空
     */
    public static <T extends IdEntity> int update(T entity, ZzrMapper<T> mapper) {
    	entity.setModifyTime(getNow());
    	return BaseDaoHelper.update(entity, mapper);
    }

    /**
     * 绝对更新对象，对于没有值的属性会将数据库对应字段清空
     *
     * @param entity
     * @return
     */
    public static <T extends IdEntity> int updateAbsolute(T entity, ZzrMapper<T> mapper) {
        return BaseDaoHelper.updateAbsolute(entity, mapper);
    }

    /* 
     * 更新对象的非空属性字段
     */
    public static <T extends IdEntity> int updateSelective(T entity, ZzrMapper<T> mapper) {
    	entity.setModifyTime(getNow());
    	return BaseDaoHelper.updateSelective(entity, mapper);
    }

    /**
     * 通过条件查询出对象，并将实体类对象中赋值的属性对应字段更新到条件查询出的对象
     *
     * @param entity
     * @param condition
     * @return
     */
    public static <T extends IdEntity> int updateByConditionSelective(T entity, Condition condition, ZzrMapper<T> mapper) {
        return BaseDaoHelper.updateByConditionSelective(entity, condition, mapper);
    }
    
    public static <T extends IdEntity> int delete(T entity, ZzrMapper<T> mapper) {
    	//entity.setDeleted(1);
    	List<T> list = select(entity, mapper);
    	for(T deletedEntity: list) {
    		deletedEntity.setDeleted(1);
    		BaseDaoHelper.updateSelective(deletedEntity, mapper);
    	}
    	return list.size();
    }

    /**
     * 通过对象的主键属性查询并删除对象
     *
     * @param entity
     * @return
     */
    public static <T extends IdEntity> int deleteByKey(T entity, ZzrMapper<T> mapper) {
        return BaseDaoHelper.deleteByKey(entity, mapper);
    }

	/**
	 * 通过调节查询出对象并删除
	 *
	 * @param condition
	 * @return
	 */
	public static <T extends IdEntity> int deleteByCondition(Condition condition, ZzrMapper<T> mapper) {
		List<T> list = mapper.selectByCondition(condition);
		if(!list.isEmpty()) {
			for(T entity: list) {
				entity.setDeleted(1);
				BaseDaoHelper.updateSelective(entity, mapper);
			}
			return list.size();
		} else {
			return 0;
		}
	}
    
    /**
     * <物理删除，特殊时候调用> 
     * <功能详细描述>
     * @author Banggui.Li
     * @date 2017年1月10日
     * @param entity
     * @return
     */
    public static <T extends IdEntity> int physicsDelete(T entity, ZzrMapper<T> mapper) {
    	return BaseDaoHelper.delete(entity, mapper);
    }
    
    
    /***************************************************
     * 
     * 
     * 
     ***************************************************/
    
    private static <T extends IdEntity> void addEntityDeletedCondition(T entity) {
    	entity.setDeleted(0);
    }
    
    private static <T extends IdEntity> void addConditionDeletedCondition(Condition condition) {
    	List<Criteria> criterias = condition.getOredCriteria();
		for(Criteria criteria: criterias) {
			criteria.andEqualTo("deleted", 0);
		}
    }
    
    /**
	 * 通过对象属性的条件查询出对象
	 * 
	 * @param entity
	 * @return
	 */
	public static <T extends IdEntity> List<T> select(T entity, ZzrMapper<T> mapper) {
		addEntityDeletedCondition(entity);
		return BaseDaoHelper.select(entity, mapper);
	}

	/**
	 * 通过条件查询出对象
	 * 
	 * @param condition
	 * @return
	 */
	public static <T extends IdEntity> List<T> selectByCondition(Condition condition, ZzrMapper<T> mapper) {
		addConditionDeletedCondition(condition);
		return BaseDaoHelper.selectByCondition(condition, mapper);
	}

	/**
	 * 通过对象属性的条件查询出对象数量
	 * 
	 * @param entity
	 * @return
	 */
	public static <T extends IdEntity> int count(T entity, ZzrMapper<T> mapper) {
		addEntityDeletedCondition(entity);
		return BaseDaoHelper.count(entity, mapper);
	}

	/**
	 * 通过条件查询出对象
	 * 
	 * @param condition
	 * @return
	 */
	public static <T extends IdEntity> int countByCondition(Condition condition, ZzrMapper<T> mapper) {
		addConditionDeletedCondition(condition);
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
	public static <T extends IdEntity> List<T> selectPage(Condition condition, int pageNum, int pageSize, ZzrMapper<T> mapper) {
		addConditionDeletedCondition(condition);
		return BaseDaoHelper.selectPage(condition, pageNum, pageSize, mapper);
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
	public static <T extends IdEntity> PagerResult<T> selectPage(T entity, Pager<?> pager, ZzrMapper<T> mapper) {
		addEntityDeletedCondition(entity);
		return BaseDaoHelper.selectPage(entity, pager, mapper);
		
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
	public static <T extends IdEntity> PagerResult<T> selectPage(Condition condition, Pager<?> pager, ZzrMapper<T> mapper) {
		addConditionDeletedCondition(condition);
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
	public static <T extends IdEntity> List<T> selectPage(T entity, int pageNum, int pageSize, ZzrMapper<T> mapper) {
		addEntityDeletedCondition(entity);
		return BaseDaoHelper.selectPage(entity, pageNum, pageSize, mapper);
	}

	/**
     * 根据分页条件获取分页数据
     * @param pager
     * @return
     */
    public static <T extends IdEntity> PagerResult<T> selectPage(Pager<T> pager, ZzrMapper<T> mapper) {
    	addEntityDeletedCondition(pager.getCondition());
    	return BaseDaoHelper.selectPage(pager, mapper);
    }
}
