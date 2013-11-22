package org.jatakasource.testcube.dao;

import java.io.Serializable;

import junit.framework.Assert;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.jatakasource.common.dao.BaseDao;
import org.jatakasource.common.dao.UniqueConstraintsException;
import org.jatakasource.common.model.IDomainObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * A parent-class for DAO test classes.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/common-dao-test-context.xml" })
@Transactional
public abstract class SpringDaoTest<ID extends Serializable, DAO extends BaseDao<T, ID>, T extends IDomainObject<ID>> implements DaoTest<ID, DAO, T> {

	Logger logger = Logger.getLogger(SpringDaoTest.class);

	protected T getValidEntity() {
		return getValidEntities().length > 0 ? getValidEntities()[0] : null;
	}

	@Test
	@Rollback
	public void testValidInstances() {
		for (T entity : getValidEntities()) {
			DAO dao = getDao();
			try {
				dao.save(entity);
			} catch (Exception ex) {
				Assert.fail("could not insert valid entity " + entity + ": " + ex.getMessage());
				logger.error(ex.getMessage(), ex);
			}
		}
	}

	@Test
	@Rollback
	public void testInvalidInstances() {
		for (T entity : getInvalidEntities()) {
			DAO dao = getDao();
			try {
				dao.save(entity);
				Assert.fail("did not fail inserting invalid entity: " + entity);
			} catch (Exception ex) {
				// ok
			}
		}
	}

	@Test
	@Rollback
	public void testInsert() {
		T entity = getValidEntity();
		DAO dao = getDao();
		try {
			if (entity != null) {
				dao.save(entity);
				T testEntity = dao.getById(entity.getId());
				Assert.assertEquals("inserted entity different from the one retrieved", entity, testEntity);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Assert.fail("could not insert valid entity " + entity + ": " + ex.getMessage());
		}
	}

	@Test
	@Rollback
	public void testUpdate() {
		T entity = getValidEntity();
		DAO dao = getDao();
		try {
			if (entity != null) {
				entity = dao.save(entity);
				change(entity);
				dao.update(entity);
				T test = dao.getById(entity.getId());
				Assert.assertEquals("entity update passed but did not occur", entity, test);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Assert.fail("could not insert valid entity " + entity + ": " + ex.getMessage());
		}
	}

	@Test
	@Rollback
	public void testDelete() {
		T entity = getValidEntity();
		DAO dao = getDao();
		try {
			if (entity != null) {
				entity = dao.save(entity);
				dao.delete(entity);
				T test = dao.getById(entity.getId());
				Assert.assertNull("entity did not get deleted as expected", test);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			Assert.fail("could not insert valid entity " + entity + ": " + ex.getMessage());
		}
	}

	@Test
	@Rollback
	public void testUniqueConstraints() {
		DAO dao = getDao();
		try {
			if (ArrayUtils.isNotEmpty(getValidEntity().uniqueConstraints())) {
				dao.save(getValidEntity());
				dao.save(getValidEntity());
				Assert.fail("Duplicated instance successfully saved");
			}
		} catch (UniqueConstraintsException ex) {
			Assert.assertTrue("Unique Constraints validation was successfully !!!", true);
		}
	}

	@Test
	public void getAll() {
		getDao().findAll();
	}
}
