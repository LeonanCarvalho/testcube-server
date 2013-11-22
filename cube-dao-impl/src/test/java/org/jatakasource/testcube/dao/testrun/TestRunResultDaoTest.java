package org.jatakasource.testcube.dao.testrun;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.jatakasource.common.model.security.IUser;
import org.jatakasource.testcube.dao.SpringDaoTest;
import org.jatakasource.testcube.dao.product.ProductDao;
import org.jatakasource.testcube.dao.product.ProductDaoTest;
import org.jatakasource.testcube.dao.product.VersionDao;
import org.jatakasource.testcube.dao.security.UserDao;
import org.jatakasource.testcube.dao.security.UserDaoTest;
import org.jatakasource.testcube.dao.testcase.CaseAttachmentDao;
import org.jatakasource.testcube.dao.testcase.CaseAttachmentDaoTest;
import org.jatakasource.testcube.dao.testcase.CaseStatusDao;
import org.jatakasource.testcube.dao.testcase.CaseStatusDaoTest;
import org.jatakasource.testcube.dao.testcase.TestCaseDao;
import org.jatakasource.testcube.dao.testcase.TestCaseDaoTest;
import org.jatakasource.testcube.dao.testplan.TestPlanDao;
import org.jatakasource.testcube.dao.testplan.TestPlanDaoTest;
import org.jatakasource.testcube.dao.version.VersionDaoTest;
import org.jatakasource.testcube.model.product.IProduct;
import org.jatakasource.testcube.model.product.IVersion;
import org.jatakasource.testcube.model.product.Product;
import org.jatakasource.testcube.model.product.Version;
import org.jatakasource.testcube.model.security.User;
import org.jatakasource.testcube.model.testcase.CaseAttachment;
import org.jatakasource.testcube.model.testcase.CaseStatus;
import org.jatakasource.testcube.model.testcase.ICaseStatus;
import org.jatakasource.testcube.model.testcase.ITestCase;
import org.jatakasource.testcube.model.testcase.TestCase;
import org.jatakasource.testcube.model.testplan.ITestPlan;
import org.jatakasource.testcube.model.testplan.TestPlan;
import org.jatakasource.testcube.model.testrun.IRunStatus;
import org.jatakasource.testcube.model.testrun.ITestRun;
import org.jatakasource.testcube.model.testrun.ITestRunResult;
import org.jatakasource.testcube.model.testrun.RunStatus;
import org.jatakasource.testcube.model.testrun.RunStatusPerDate;
import org.jatakasource.testcube.model.testrun.TestRun;
import org.jatakasource.testcube.model.testrun.TestRunResult;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

public class TestRunResultDaoTest extends SpringDaoTest<Long, TestRunResultDao, ITestRunResult> {

	@Autowired
	private TestRunResultDao testRunResultDao;

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
	@Autowired
	private TestRunDao testRunDao;
	@Autowired
	private CaseStatusDao caseStatusDao;
	@Autowired
	private CaseAttachmentDao caseAttachmentDao;
	@Autowired
	private TestCaseDao testCaseDao;

	private User user;
	private Product product;
	private Version version;
	private RunStatus status;
	private TestPlan testPlan;
	private TestRun testRun;
	private CaseStatus caseStatus;
	private TestCase testCase;
	private CaseAttachment caseAttachment;

	@Override
	public TestRunResultDao getDao() {
		return testRunResultDao;
	}

	@Before
	public void before() {
		user = (User) prepareUser();
		product = (Product) prepareProduct();
		version = (Version) prepareVersion();
		status = (RunStatus) prepareStatus();
		testPlan = (TestPlan) prepareTestPlan();
		testRun = (TestRun) prepareTestRun();
		caseStatus = (CaseStatus) prepareCaseStatus();
		caseAttachment = prepareCaseAttachment();
		testCase = (TestCase) prepareTestCase();

	}

	@Override
	public ITestRunResult[] getValidEntities() {
		return new ITestRunResult[] { getValidTestRun() };
	}

	@Override
	public ITestRunResult[] getInvalidEntities() {
		ITestRunResult result = (ITestRunResult) getValidEntity();

		result.setStatus(null);
		result.setTestcase(null);
		result.setTestRun(null);
		result.setUpdatedBy(null);

		return new ITestRunResult[] { result };
	}

	@Override
	public void change(ITestRunResult entity) {
		entity.setCreatedDate(null);
	}

	public ITestRunResult getValidTestRun() {
		TestRunResult result = new TestRunResult();

		result.setCreatedDate(new Date());
		result.setUpdatedDate(new Date());
		result.setStatus(status);
		result.setTestcase(testCase);
		result.setTestRun(testRun);
		result.setUpdatedBy(user);

		return result;
	}

	@Test
	@Rollback
	public void testStatusByDay() {
		// Create 3 runs
		getDao().save(getValidEntity());
		getDao().save(getValidEntity());
		getDao().save(getValidEntity());

		List<RunStatusPerDate> statisrics = getDao().getStatusByDay();

		Assert.assertNotNull(statisrics);
		Assert.assertEquals(14, statisrics.size());
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

	private ITestPlan prepareTestPlan() {
		return (TestPlan) testPlanDao.save(TestPlanDaoTest.getValidTestPlan(user, product));
	}

	private ITestRun prepareTestRun() {
		return (ITestRun) testRunDao.save(TestRunDaoTest.getValidTestRun(user, version, status, testPlan));
	}

	private ICaseStatus prepareCaseStatus() {
		return (CaseStatus) caseStatusDao.save(CaseStatusDaoTest.getValidCaseStatus());
	}

	private ITestCase prepareTestCase() {
		return (ITestCase) testCaseDao.save(TestCaseDaoTest.getValidTestCase(testPlan, user, caseStatus, caseAttachment));
	}

	private CaseAttachment prepareCaseAttachment() {
		return (CaseAttachment) caseAttachmentDao.save((CaseAttachment) CaseAttachmentDaoTest.getValidPlanAttachment());
	}
}
