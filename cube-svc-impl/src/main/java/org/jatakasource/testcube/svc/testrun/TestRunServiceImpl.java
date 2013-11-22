/**
 * TestCube is an enterprise Test management tool.
 * Copyright (C) 2011 JatakaSource Ltd.
 *
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TestCube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with TestCube.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jatakasource.testcube.svc.testrun;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jatakasource.common.dao.BaseDao;
import org.jatakasource.common.model.paging.Paging;
import org.jatakasource.common.model.paging.Sorting;
import org.jatakasource.common.model.paging.SortingElement;
import org.jatakasource.common.svc.CRUDServiceImpl;
import org.jatakasource.common.svc.ReturnMessageEnum;
import org.jatakasource.common.svc.exception.ServiceRunTimeException;
import org.jatakasource.testcube.dao.testrun.TestRunDao;
import org.jatakasource.testcube.dao.testrun.TestRunResultDao;
import org.jatakasource.testcube.model.testcase.TestCase;
import org.jatakasource.testcube.model.testrun.ITestRun;
import org.jatakasource.testcube.model.testrun.ITestRunResult;
import org.jatakasource.testcube.model.testrun.TestRunPojo;
import org.jatakasource.testcube.model.testrun.TestRunStatisticsPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestRunServiceImpl extends CRUDServiceImpl<ITestRun, Long> implements TestRunService {

	@Autowired
	private TestRunDao testRunDao;
	@Autowired
	private TestRunResultDao testRunResultDao;
	@Autowired
	private TestRunResultService testRunResultService;

	@Override
	public BaseDao<ITestRun, Long> getDao() {
		return testRunDao;
	}

	@Override
	public List<ITestRun> getAll(Paging paging, Sorting sorting, String keyword, Long testPlanId) {
		return testRunDao.findAll(paging, sorting, keyword, testPlanId);
	}

	public List<ITestRun> getAll(Paging paging, Sorting sorting, String keyword, Long productId, Long testPlanId) {
		return testRunDao.findAll(paging, sorting, keyword, productId, testPlanId);
	}

	@Override
	public List<TestRunStatisticsPojo> getStatistics(List<Long> ids) {
		return testRunResultDao.getStatistics(ids);
	}

	@Override
	public ITestRun add(ITestRun pojo) {
		if (pojo.getTestPlan() == null) {
			throw new ServiceRunTimeException(ReturnMessageEnum.CRITICAL, "Unable to create Testrun without relation testcase !!!");
		}

		ITestRun testRun = super.add(pojo);

		// Get new results according to testPlan
		List<ITestRunResult> newResults = testRunResultService.getAll(null, new Sorting(new SortingElement(TestCase.FIELD_ID)), testRun.getTestPlan().getId());

		// Save result to DB
		testRunResultService.addAll(setTestRun(newResults, testRun));

		return testRun;
	}

	private List<ITestRunResult> setTestRun(List<ITestRunResult> runs, ITestRun testRun) {
		if (CollectionUtils.isNotEmpty(runs)) {
			for (ITestRunResult run : runs) {
				run.setTestRun((TestRunPojo) testRun);
			}
		}

		return runs;
	}
}
