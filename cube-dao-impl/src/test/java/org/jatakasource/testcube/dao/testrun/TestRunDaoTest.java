package org.jatakasource.testcube.dao.testrun;

import java.util.Date;

import org.jatakasource.common.model.security.IUser;
import org.jatakasource.testcube.dao.SpringDaoTest;
import org.jatakasource.testcube.dao.product.ProductDao;
import org.jatakasource.testcube.dao.product.ProductDaoTest;
import org.jatakasource.testcube.dao.product.VersionDao;
import org.jatakasource.testcube.dao.security.UserDao;
import org.jatakasource.testcube.dao.security.UserDaoTest;
import org.jatakasource.testcube.dao.testplan.TestPlanDao;
import org.jatakasource.testcube.dao.testplan.TestPlanDaoTest;
import org.jatakasource.testcube.dao.version.VersionDaoTest;
import org.jatakasource.testcube.model.product.IProduct;
import org.jatakasource.testcube.model.product.IVersion;
import org.jatakasource.testcube.model.product.Product;
import org.jatakasource.testcube.model.product.Version;
import org.jatakasource.testcube.model.security.User;
import org.jatakasource.testcube.model.testplan.ITestPlan;
import org.jatakasource.testcube.model.testplan.TestPlan;
import org.jatakasource.testcube.model.testrun.IRunStatus;
import org.jatakasource.testcube.model.testrun.ITestRun;
import org.jatakasource.testcube.model.testrun.RunStatus;
import org.jatakasource.testcube.model.testrun.TestRun;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class TestRunDaoTest extends SpringDaoTest<Long, TestRunDao, ITestRun> {

	@Autowired
	private TestRunDao testRunDao;

	@Autowired
	private UserDao userDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private VersionDao versionDao;
	@Autowired
	private RunStatusDao runStatusDao;
	@Autowired
	private TestPlanDao testPlanDao;

	private User user;
	private Product product;
	private Version version;
	private RunStatus status;
	private TestPlan testPlan;

	@Override
	public TestRunDao getDao() {
		return testRunDao;
	}

	@Before
	public void before() {
		user = (User) prepareUser();
		product = (Product) prepareProduct();
		version = (Version) prepareVersion();
		status = (RunStatus) prepareStatus();
		testPlan = (TestPlan) prepareTestPlan(user, product);
	}

	@Override
	public ITestRun[] getValidEntities() {
		return new ITestRun[] { getValidTestRun(user, version, status, testPlan) };
	}

	@Override
	public ITestRun[] getInvalidEntities() {
		ITestRun testRun = (ITestRun) getValidEntity();

		testRun.setName(null);
		testRun.setDescription(null);
		return new ITestRun[] { testRun };
	}

	@Override
	public void change(ITestRun entity) {
		entity.setDescription("TEST TESTRUN UPDATE");
	}

	public static ITestRun getValidTestRun(User user, Version version, RunStatus status, TestPlan testPlan) {
		TestRun testRun = new TestRun();

		testRun.setName("TEST TESTRUN");
		testRun.setDescription("TEST TESTRUN");
		testRun.setCreatedBy(user);
		testRun.setCreatedDate(new Date());
		testRun.setProductVersion(version);
		testRun.setStatus(status);
		testRun.setTestPlan(testPlan);

		return testRun;
	}

	private IUser prepareUser() {
		return (User) userDao.save(UserDaoTest.getValidUser());
	}

	private IProduct prepareProduct() {
		return (Product) productDao.save(ProductDaoTest.getValidProduct());
	}

	private IVersion prepareVersion() {
		return (Version) versionDao.save((Version) VersionDaoTest.getValidVersion(product));
	}

	private IRunStatus prepareStatus() {
		return (RunStatus) runStatusDao.save(RunStatusDaoTest.getValidRunStatus());
	}

	private ITestPlan prepareTestPlan(User user, Product product) {
		return (TestPlan) testPlanDao.save(TestPlanDaoTest.getValidTestPlan(user, product));
	}
}
