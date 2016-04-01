package com.rails.core.frame.generic.dao;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@SuppressWarnings(value = { "unchecked", "unused" })
@Repository("GenericDaoImpl")
public class GenericDaoImpl<T> implements GenericDao<T> {

	@PersistenceContext
	protected EntityManager em;
	
	private Class<T> persistentClass;

	public GenericDaoImpl() {
	}

	public Class<T> getPersistentClass() {
		return this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Override
	public T save(T entity) throws Exception {
		em.persist(entity);
		return (T) entity;
	}

	@Override
	public void delete(T entity) throws Exception {
		Object object = em.merge(entity);
		em.remove(object);
	}

	@Override
	public T update(T entity) throws Exception {
		Object object = em.merge(entity);
		return (T) object;
	}

	@Override
	public List<T> findAll() throws Exception {
		List<T> resultList = null;
		String sql = "from " + getPersistentClass().getName() + " obj ";
		TypedQuery<T> query = em.createQuery(sql, this.getPersistentClass());
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public T findById(Object id) throws Exception {
		return (T) em.find(this.getPersistentClass(), id);
	}

	@Override
	public List<T> queryByString(String sql) throws Exception {
		List<T> resultList = null;
		resultList = (List<T>) em.createQuery(sql).getResultList();
		return resultList;
	}

	@Override
	public List<T> queryByMap(HashMap<String, Object> map) throws Exception {
		List<T> resultList = null;
		StringBuilder sql = new StringBuilder("SELECT o from " + getPersistentClass().getName() + " o WHERE ");
		StringBuilder conStr = new StringBuilder("");
		// 拼接条件
		for (String key : map.keySet()) {
			Object value = map.get(key);
			if (value instanceof List) {
				conStr = conStr.append(String.format(" o.%s in (:%s) and", key, key));
			} else {
				conStr = conStr.append(String.format(" o.%s = :%s and", key, key));
			}
		}
		// 去掉最后一个and
		sql.append(conStr.toString().substring(0, conStr.length() - 4));
		Query query = em.createQuery(sql.toString());
		for (String key : map.keySet()) {
			query = query.setParameter(key, map.get(key));
		}
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void clearTable() throws Exception {
		String sql = "DELETE FROM " + getPersistentClass().getName();
		Query q = em.createQuery(sql);
		q.executeUpdate();
	}

}
