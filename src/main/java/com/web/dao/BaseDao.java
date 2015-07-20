package com.web.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.web.bean.Pager;

public interface BaseDao<T, PK extends Serializable> {

	public T get(PK id);

	public T load(PK id);

	public List<T> get(PK[] ids);

	public T get(String propertyName, Object value);

	public List<T> getList(String propertyName, Object value);

	public List<T> getAll();

	public Long getTotalCount();

	/**
	 * 根据属性名、修改前后属性值判断在数据库中是否唯一(若新修改的值与原来值相等则直接返回true).
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param oldValue
	 *            修改前的属性值
	 * @param oldValue
	 *            修改后的属性值
	 * @return boolean
	 */
	public boolean isUnique(String propertyName, Object oldValue,
			Object newValue);

	public boolean isExist(String propertyName, Object value);

	public PK save(T entity);

	public void update(T entity);

	public void delete(T entity);

	public void delete(PK id);

	public void delete(PK[] ids);

	public void flush();

	public void clear();

	public void evict(Object object);

	/**
	 * 根据Pager对象进行查询(提供分页、查找、排序功能).
	 * 
	 * @param pager
	 *            Pager对象
	 * @return Pager对象
	 */
	public Pager findByPager(Pager pager);

	/**
	 * 根据Pager和DetachedCriteria对象进行查询(提供分页、查找、排序功能).
	 * 
	 * @param pager
	 *            Pager对象
	 * @return Pager对象
	 */
	public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria);

}