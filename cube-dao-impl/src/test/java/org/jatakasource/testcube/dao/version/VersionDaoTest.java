package org.jatakasource.testcube.dao.version;

import org.jatakasource.testcube.dao.SpringDaoTest;
import org.jatakasource.testcube.dao.product.ProductDao;
import org.jatakasource.testcube.dao.product.ProductDaoTest;
import org.jatakasource.testcube.dao.product.VersionDao;
import org.jatakasource.testcube.model.product.IProduct;
import org.jatakasource.testcube.model.product.IVersion;
import org.jatakasource.testcube.model.product.Product;
import org.jatakasource.testcube.model.product.Version;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class VersionDaoTest extends SpringDaoTest<Long, VersionDao, IVersion> {

	@Autowired
	private VersionDao versionDao;
	@Autowired
	private ProductDao productDao;

	private Product product;

	@Before
	public void before() {
		product = (Product) prepareProduct();
	}

	@Override
	public VersionDao getDao() {
		return versionDao;
	}

	@Override
	public IVersion[] getValidEntities() {
		return new IVersion[] { getValidVersion(product) };
	}

	@Override
	public IVersion[] getInvalidEntities() {
		IVersion version = (IVersion) getValidEntity();

		version.setName(null);
		version.setDescription(null);
		version.setProduct(null);

		return new IVersion[] { version };
	}

	@Override
	public void change(IVersion entity) {
		entity.setDescription("TEST PRODUCT UPDATE");
	}

	public static IVersion getValidVersion(Product product) {
		Version version = new Version();

		version.setName("TEST PRODUCT");
		version.setDescription("TEST PRODUCT");
		version.setProduct(product);
		
		return version;
	}

	private IProduct prepareProduct() {
		return (Product) productDao.save(ProductDaoTest.getValidProduct());
	}

}
