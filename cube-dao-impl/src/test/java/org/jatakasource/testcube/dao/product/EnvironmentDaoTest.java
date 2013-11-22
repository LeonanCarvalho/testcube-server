package org.jatakasource.testcube.dao.product;

import org.jatakasource.testcube.dao.SpringDaoTest;
import org.jatakasource.testcube.dao.product.EnvironmentDao;
import org.jatakasource.testcube.dao.product.ProductDao;
import org.jatakasource.testcube.model.product.Environment;
import org.jatakasource.testcube.model.product.IEnvironment;
import org.jatakasource.testcube.model.product.IProduct;
import org.jatakasource.testcube.model.product.Product;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class EnvironmentDaoTest extends SpringDaoTest<Long, EnvironmentDao, IEnvironment> {

	@Autowired
	private EnvironmentDao environmentDao;
	@Autowired
	private ProductDao productDao;

	private Product product;

	@Before
	public void before() {
		product = (Product) prepareProduct();
	}

	@Override
	public EnvironmentDao getDao() {
		return environmentDao;
	}

	@Override
	public IEnvironment[] getValidEntities() {
		return new IEnvironment[] { getValidEnvironment() };
	}

	@Override
	public IEnvironment[] getInvalidEntities() {
		IEnvironment environment = (IEnvironment) getValidEntity();

		environment.setName(null);
		environment.setDescription(null);
		environment.setProduct(null);

		return new IEnvironment[] { environment };
	}

	@Override
	public void change(IEnvironment entity) {
		entity.setDescription("TEST ENVIRONMENT UPDATE");
	}

	public IEnvironment getValidEnvironment() {
		Environment environment = new Environment();

		environment.setName("TEST ENVIRONMENT");
		environment.setDescription("TEST ENVIRONMENT");
		environment.setProduct(product);

		return environment;
	}

	private IProduct prepareProduct() {
		return (Product) productDao.save(ProductDaoTest.getValidProduct());
	}

}
