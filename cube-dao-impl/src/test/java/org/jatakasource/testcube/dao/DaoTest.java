package org.jatakasource.testcube.dao;

import java.io.Serializable;

import org.jatakasource.common.dao.BaseDao;

public interface DaoTest<ID extends Serializable, DAO extends BaseDao<T, ID>, T extends Object> {
	DAO getDao();

	T[] getValidEntities();

	T[] getInvalidEntities();

	void change(T entity);
}
