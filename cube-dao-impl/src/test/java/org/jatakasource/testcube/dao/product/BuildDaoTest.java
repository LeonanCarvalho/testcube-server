package org.jatakasource.testcube.dao.product;

import org.jatakasource.testcube.dao.SpringDaoTest;
import org.jatakasource.testcube.dao.product.BuildDao;
import org.jatakasource.testcube.dao.product.ProductDao;
import org.jatakasource.testcube.model.product.Build;
import org.jatakasource.testcube.model.product.IBuild;
import org.jatakasource.testcube.model.product.IProduct;
import org.jatakasource.testcube.model.product.Product;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class BuildDaoTest extends SpringDaoTest<Long, BuildDao, IBuild> {

	@Autowired
	private BuildDao buildDao;
	@Autowired
	private ProductDao productDao;

	private Product product;

	@Before
	public void before() {
		product = (Product) prepareProduct();
	}

	@Override
	public BuildDao getDao() {
		return buildDao;
	}

	@Override
	public IBuild[] getValidEntities() {
		return new IBuild[] { getValidEnvironment() };
	}

	@Override
	public IBuild[] getInvalidEntities() {
		IBuild build = (IBuild) getValidEntity();

		build.setName(null);
		build.setDescription(null);
		build.setProduct(null);

		return new IBuild[] { build };
	}

	@Override
	public void change(IBuild build) {
		build.setDescription("TEST BUILD UPDATE");
	}

	public IBuild getValidEnvironment() {
		Build build = new Build();

		build.setName("TEST BUILD");
		build.setDescription("TEST BUILD");
		build.setProduct(product);

		return build;
	}

	private IProduct prepareProduct() {
		return (Product) productDao.save(ProductDaoTest.getValidProduct());
	}

}
