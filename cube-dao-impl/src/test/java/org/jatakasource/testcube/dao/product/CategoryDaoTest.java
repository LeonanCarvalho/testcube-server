package org.jatakasource.testcube.dao.product;

import org.jatakasource.testcube.dao.SpringDaoTest;
import org.jatakasource.testcube.dao.product.CategoryDao;
import org.jatakasource.testcube.dao.product.ProductDao;
import org.jatakasource.testcube.model.product.Category;
import org.jatakasource.testcube.model.product.ICategory;
import org.jatakasource.testcube.model.product.IProduct;
import org.jatakasource.testcube.model.product.Product;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryDaoTest extends SpringDaoTest<Long, CategoryDao, ICategory> {

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ProductDao productDao;

	private Product product;

	@Before
	public void before() {
		product = (Product) prepareProduct();
	}

	@Override
	public CategoryDao getDao() {
		return categoryDao;
	}

	@Override
	public ICategory[] getValidEntities() {
		return new ICategory[] { getValidEnvironment() };
	}

	@Override
	public ICategory[] getInvalidEntities() {
		ICategory category = (ICategory) getValidEntity();

		category.setName(null);
		category.setDescription(null);
		category.setProduct(null);

		return new ICategory[] { category };
	}

	@Override
	public void change(ICategory category) {
		category.setDescription("TEST CATEGORY UPDATE");
	}

	public ICategory getValidEnvironment() {
		Category category = new Category();

		category.setName("TEST CATEGORY");
		category.setDescription("TEST CATEGORY");
		category.setProduct(product);

		return category;
	}

	private IProduct prepareProduct() {
		return (Product) productDao.save(ProductDaoTest.getValidProduct());
	}

}
