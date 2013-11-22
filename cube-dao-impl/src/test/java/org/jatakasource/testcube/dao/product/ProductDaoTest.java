package org.jatakasource.testcube.dao.product;

import org.jatakasource.testcube.dao.SpringDaoTest;
import org.jatakasource.testcube.dao.product.ProductDao;
import org.jatakasource.testcube.model.product.IProduct;
import org.jatakasource.testcube.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductDaoTest extends SpringDaoTest<Long, ProductDao, IProduct> {
	@Autowired
	private ProductDao productDao;

	@Override
	public ProductDao getDao() {
		return productDao;
	}

	@Override
	public IProduct[] getValidEntities() {
		return new IProduct[] { getValidProduct() };
	}

	@Override
	public IProduct[] getInvalidEntities() {
		Product product = (Product) getValidEntity();

		product.setName(null);
		product.setDescription(null);

		return new IProduct[] { product };
	}

	@Override
	public void change(IProduct entity) {
		entity.setName("TEST PRODUCT UPDATE");
	}

	public static IProduct getValidProduct() {
		Product product = new Product();

		product.setName("TEST PRODUCT");
		product.setDescription("TEST PRODUCT");
		product.setEnabled(false);

		return product;
	}

}
