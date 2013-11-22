package org.jatakasource.testcube.dao.testcase;

import java.util.ArrayList;
import java.util.List;

import org.jatakasource.common.model.security.IUser;
import org.jatakasource.testcube.dao.SpringDaoTest;
import org.jatakasource.testcube.dao.product.ProductDao;
import org.jatakasource.testcube.dao.product.ProductDaoTest;
import org.jatakasource.testcube.dao.security.UserDao;
import org.jatakasource.testcube.dao.security.UserDaoTest;
import org.jatakasource.testcube.dao.testplan.TestPlanDao;
import org.jatakasource.testcube.dao.testplan.TestPlanDaoTest;
import org.jatakasource.testcube.model.product.IProduct;
import org.jatakasource.testcube.model.product.Product;
import org.jatakasource.testcube.model.security.User;
import org.jatakasource.testcube.model.testcase.CaseAttachment;
import org.jatakasource.testcube.model.testcase.CaseStatus;
import org.jatakasource.testcube.model.testcase.ICaseStatus;
import org.jatakasource.testcube.model.testcase.ITestCase;
import org.jatakasource.testcube.model.testcase.TestCase;
import org.jatakasource.testcube.model.testplan.ITestPlan;
import org.jatakasource.testcube.model.testplan.TestPlan;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class TestCaseDaoTest extends SpringDaoTest<Long, TestCaseDao, ITestCase> {

	@Autowired
	private TestCaseDao testCaseDao;

	@Autowired
	private UserDao userDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private TestPlanDao testPlanDao;
	@Autowired
	private CaseStatusDao caseStatusDao;
	@Autowired
	private CaseAttachmentDao caseAttachmentDao;

	private User user;
	private Product product;
	private TestPlan testPlan;
	private CaseStatus status;
	private CaseAttachment attachment;

	@Before
	public void before() {
		user = (User) prepareUser();
		product = (Product) prepareProduct();
		status = (CaseStatus) prepareStatus();

		testPlan = (TestPlan) prepareTestPlan(user, product);

		attachment = prepareCaseAttachment();
	}

	@Override
	public TestCaseDao getDao() {
		return testCaseDao;
	}

	@Override
	public ITestCase[] getValidEntities() {
		return new ITestCase[] { getValidTestCase(testPlan, user, status, attachment) };
	}

	@Override
	public ITestCase[] getInvalidEntities() {
		ITestCase testCase = (ITestCase) getValidEntity();

		testCase.setName(null);
		testCase.setCreatedBy(null);

		return new ITestCase[] { testCase };
	}

	@Override
	public void change(ITestCase entity) {
		entity.setDescription("TEST PLAN UPDATE");
	}

	public static ITestCase getValidTestCase(TestPlan testPlan, User user, CaseStatus status, CaseAttachment attachment) {
		TestCase testCase = new TestCase();
		testCase.setName("TestCase TEST NAME");
		testCase.setDescription("TestCase TEST DESC");
		testCase.setTestPlan(testPlan);

		testCase.setCreatedBy(user);
		testCase.setStatus(status);

		// Create attachments list
		List<CaseAttachment> attachments = new ArrayList<CaseAttachment>();
		attachments.add(attachment);

		testCase.setAttachments(attachments);

		return testCase;
	}

	private IUser prepareUser() {
		return (User) userDao.save(UserDaoTest.getValidUser());
	}

	private IProduct prepareProduct() {
		return (Product) productDao.save(ProductDaoTest.getValidProduct());
	}

	private ICaseStatus prepareStatus() {
		return (CaseStatus) caseStatusDao.save(CaseStatusDaoTest.getValidCaseStatus());
	}

	private ITestPlan prepareTestPlan(User user, Product product) {
		return (TestPlan) testPlanDao.save(TestPlanDaoTest.getValidTestPlan(user, product));
	}

	private CaseAttachment prepareCaseAttachment() {
		return (CaseAttachment) caseAttachmentDao.save((CaseAttachment) CaseAttachmentDaoTest.getValidPlanAttachment());
	}
}
