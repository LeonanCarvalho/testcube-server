package org.jatakasource.testcube.dao.testplan;

import java.util.ArrayList;
import java.util.List;

import org.jatakasource.common.model.security.IUser;
import org.jatakasource.testcube.dao.SpringDaoTest;
import org.jatakasource.testcube.dao.product.ProductDao;
import org.jatakasource.testcube.dao.product.ProductDaoTest;
import org.jatakasource.testcube.dao.security.UserDao;
import org.jatakasource.testcube.dao.security.UserDaoTest;
import org.jatakasource.testcube.dao.testplan.TestPlanDao;
import org.jatakasource.testcube.model.product.IProduct;
import org.jatakasource.testcube.model.product.Product;
import org.jatakasource.testcube.model.security.User;
import org.jatakasource.testcube.model.testplan.ITestPlan;
import org.jatakasource.testcube.model.testplan.PlanAttachment;
import org.jatakasource.testcube.model.testplan.TestPlan;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class TestPlanDaoTest extends SpringDaoTest<Long, TestPlanDao, ITestPlan> {

	@Autowired
	private TestPlanDao testPlanDao;

	@Autowired
	private UserDao userDao;
	@Autowired
	private ProductDao productDao;
	
	private User user;
	private Product product;
	
	@Before
	public void before(){
		user = (User) prepareUser();
		product = (Product) prepareProduct();
	}
	
	@Override
	public TestPlanDao getDao() {
		return testPlanDao;
	}

	@Override
	public ITestPlan[] getValidEntities() {
		return new ITestPlan[] { getValidTestPlan(user, product) };
	}

	@Override
	public ITestPlan[] getInvalidEntities() {
		ITestPlan testPlan = (ITestPlan) getValidEntity();

		testPlan.setName(null);
		testPlan.setProduct(null);
		testPlan.setCreatedBy(null);

		return new ITestPlan[] { testPlan };
	}

	@Override
	public void change(ITestPlan entity) {
		entity.setDescription("TEST PLAN UPDATE");
	}

	public static ITestPlan getValidTestPlan(User user, Product product) {
		PlanAttachment attachment = (PlanAttachment) PlanAttachmentDaoTest.getValidPlanAttachment();

		TestPlan testPlan = new TestPlan();
		testPlan.setName("TESTPLAN TEST NAME");
		testPlan.setDescription("TESTPLAN TEST DESC");
		testPlan.setProduct(product);
		testPlan.setCreatedBy(user);
		
		// Create attachments list
		List<PlanAttachment> attachments = new ArrayList<PlanAttachment>();
		attachments.add(attachment);
		
		testPlan.setAttachments(attachments);
		
		return testPlan;
	}
	
	private IUser prepareUser(){		
		return (User) userDao.save(UserDaoTest.getValidUser());
	}
	
	private IProduct prepareProduct(){		
		return (Product) productDao.save(ProductDaoTest.getValidProduct());
	}

}
